package cn.topcheer.pms2.biz.job;

import cn.topcheer.halberd.app.api.minidev.service.MiniCommonService;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.modules.system.entity.SysPlanningTask;
import cn.topcheer.halberd.core.job.AbstractJob;
import cn.topcheer.halberd.core.utils.DebugUtils;
import cn.topcheer.pms2.api.plan.entity.PmsPlanDeadline;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanDeadlineService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.quartz.JobExecutionContext;
import org.springblade.core.tool.utils.StringUtil;

import java.util.*;


/**
 * 流程自动终止-定时任务
 *
 * @author szs
 * @date 2024-03-11
 */
@Slf4j
public class AutoTerminationJob extends AbstractJob {


    @Override
    public void exec(JobExecutionContext jobExecutionContext, SysPlanningTask sysPlanningTask) {
        log.info("AutoTerminationJob-开始：" + JSON.toJSONString(sysPlanningTask));

        if (sysPlanningTask == null || StringUtil.isBlank(sysPlanningTask.getTransmittingData())) {
            log.error("AutoTerminationJob-排程参数异常");
            return;
        }

        // 自定义参数
        JSONArray paramArray = JSONArray.fromObject(sysPlanningTask.getTransmittingData());

        // 公共服务
        MiniCommonService miniCommonService = this.getBean(MiniCommonService.class);

        // 批次配置时间
        PmsPlanDeadlineService pmsPlanDeadlineService = this.getBean(PmsPlanDeadlineService.class);

        // 遍历查询业务数据
        for (int i = 0; i < paramArray.size(); i++) {
            // 处理业务数据
            this.dealBusinessData(paramArray.getJSONObject(i), pmsPlanDeadlineService, miniCommonService);
        }

        log.info("AutoTerminationJob-结束");
    }


    /**
     * 处理业务数据
     *
     * @param object                 JSONObject
     * @param pmsPlanDeadlineService PmsPlanDeadlineService
     * @param miniCommonService      MiniCommonService
     * @author szs
     * @date 2024-03-13
     */
    private void dealBusinessData(JSONObject object, PmsPlanDeadlineService pmsPlanDeadlineService, MiniCommonService miniCommonService) {
        String mainTableServiceName = object.getString("serviceName");
        String taskName = object.getString("taskName");
        String processDefKey = object.getString("processDefKey");
        String timeCode = object.getString("timeCode");
        String gridType = object.getString("gridType");

        try {
            // 动态执行Service方法-获取业务数据
            List<Object> list = (List<Object>) Util.springInvokeMethod(mainTableServiceName, "getBusinessDataList", new Object[]{taskName, processDefKey});

            // 批次配置时间Map，临时存储在Map中，不用每次查询
            HashMap<String, Boolean> judgeTimeMap = new HashMap<>();

            // 用户-任务ids
            HashMap<String, List<String>> userTaskMap = new HashMap<>();

            for (Object o : list) {
                JSONObject dataJson = JSONObject.fromObject(o);

                // 批次id
                String batchId = dataJson.getString("planprojectbatchid");

                // 申报人id
                String declarantid = dataJson.getString("declarantid");

                // 判断时间
                Boolean judgeTime = this.judgeTime(judgeTimeMap, pmsPlanDeadlineService, batchId, timeCode);
                if (judgeTime) {
                    List<String> taskIds = userTaskMap.getOrDefault(declarantid, new ArrayList<>());
                    taskIds.add(dataJson.getString("minicurrentstateid"));
                    userTaskMap.put(declarantid, taskIds);
                }
            }

            // 遍历用户任务Map，按照用户进行小批次审核终止
            if (userTaskMap.size() > 0) {
                for (String key : userTaskMap.keySet()) {
                    // 任务ids
                    List<String> taskIds = userTaskMap.get(key);
                    if (taskIds.size() == 0) {
                        continue;
                    }

                    // 批量终止任务
                    miniCommonService.batchFlowUserTask(key, taskIds, "", "wf_terminate_timeout", "超时自动办结", "超时自动办结", gridType);
                    log.info("AutoTerminationJob-批量终止任务-userId=" + key + "&任务数量=" + taskIds.size());

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 判断时间
     *
     * @param judgeTimeMap           HashMap
     * @param pmsPlanDeadlineService PmsPlanDeadlineService
     * @param batchId                批次id
     * @param timeCode               时间编码
     * @return Boolean
     * @author szs
     * @date 2024-03-15
     */
    private Boolean judgeTime(HashMap<String, Boolean> judgeTimeMap, PmsPlanDeadlineService pmsPlanDeadlineService, String batchId, String timeCode) {
        if (!judgeTimeMap.containsKey(batchId)) {
            PmsPlanDeadline pmsPlanDeadline = pmsPlanDeadlineService.getPmsPlanDeadline(batchId, timeCode);
            if (pmsPlanDeadline == null) {
                judgeTimeMap.put(batchId, false);

            } else {
                // 当前时间
                Date date = new Date();

                // 当前时间在时间段内，需要自动终止
                boolean bo1 = pmsPlanDeadline.getStartTime() == null || date.compareTo(pmsPlanDeadline.getStartTime()) >= 0;
                boolean bo2 = pmsPlanDeadline.getEndTime() == null || date.compareTo(pmsPlanDeadline.getEndTime()) <= 0;
                judgeTimeMap.put(batchId, bo1 && bo2);

            }
        }

        return judgeTimeMap.get(batchId);
    }


}
