package cn.topcheer.pms2.biz.flow.service.condition;

import cn.topcheer.halberd.app.api.framework.dto.api.Result;
import cn.topcheer.pms2.biz.sys.PmsEnterpriselimitService;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * DirectionLimitCondition
 *
 * @author szs
 * @date 2024-03-12
 */
@Service
public class DirectionLimitCondition {


    @Autowired
    private PmsEnterpriselimitService pmsEnterpriselimitService;


    /**
     * 打印日志
     *
     * @param obj 数据
     * @author szs
     * @date 2024-03-12
     */
    public void printLog(Object obj) {
        System.err.println(">>>>>>>DirectionLimitCondition.printLog：" + JSON.toJSONString(obj));
    }


    /**
     * 消耗或退回指标
     *
     * @param task            当前用户任务
     * @param vars            自定义参数
     * @param result          消耗或退回（pass、back）
     * @param type            业务类型
     * @param defaultLimitNum 默认限制数量
     * @return Result
     * @author szs
     * @date 2024-03-12
     */
    public Result<String> updateDirectionLimit(Task task, Map<String, Object> vars, String result, String type, Integer defaultLimitNum) {
        System.err.println(">>>>>>>DirectionLimitCondition.updateDirectionLimit：taskId=" + task.getId() + "&taskName=" + task.getName() + "&result=" + result + "&type=" + type + "&defaultLimitNum=" + defaultLimitNum);
        System.err.println(">>>>>>>DirectionLimitCondition.updateDirectionLimit：vars=" + JSON.toJSONString(vars));

        // 业务id
        String businessId = (String) vars.get("business_id");

        // 操作结果
        String taskResult = (String) vars.getOrDefault("task_result", "wf_reject");

        try {

            // 通过，消耗指标
            boolean bo1 = "wf_pass".equals(taskResult) && "pass".equals(result);

            // 驳回，退回指标
            boolean bo2 = "wf_reject".equals(taskResult) && "back".equals(result);

            if (bo1 || bo2) {
                // 消耗或退回指标
                JSONObject object = pmsEnterpriselimitService.updateDirectionLimit(businessId, type, defaultLimitNum, result);
                return Result.of(object.getBoolean("result") ? 0 : -1, object.getString("note"), null, object.getBoolean("result"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.successOf("操作成功");
    }


}
