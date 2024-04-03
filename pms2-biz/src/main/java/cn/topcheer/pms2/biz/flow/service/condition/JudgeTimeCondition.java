package cn.topcheer.pms2.biz.flow.service.condition;

import cn.topcheer.halberd.app.api.framework.dto.api.Result;
import cn.topcheer.pms2.api.plan.entity.PmsPlanDeadline;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanDeadlineService;
import com.alibaba.fastjson.JSON;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * JudgeTimeCondition
 *
 * @author szs
 * @date 2024-03-13
 */
@Service
public class JudgeTimeCondition {


    @Autowired
    private PmsPlanDeadlineService pmsPlanDeadlineService;


    /**
     * 打印日志
     *
     * @param obj 数据
     * @author szs
     * @date 2024-03-13
     */
    public void printLog(Object obj) {
        System.err.println(">>>>>>>JudgeTimeCondition.printLog：" + JSON.toJSONString(obj));
    }


    /**
     * 判断时间
     *
     * @param task     当前用户任务
     * @param vars     自定义参数
     * @param timeCode 时间编码
     * @return Result
     * @author szs
     * @date 2024-03-13
     */
    public Result<String> judgeTime(Task task, Map<String, Object> vars, String timeCode) {
        System.err.println(">>>>>>>JudgeTimeCondition.judgeTime：taskId=" + task.getId() + "&taskName=" + task.getName() + "&timeCode=" + timeCode);
        System.err.println(">>>>>>>JudgeTimeCondition.judgeTime：vars=" + JSON.toJSONString(vars));

        // 额外参数
        HashMap startVars = (HashMap) vars.get("startVars");

        // 批次id
        String batchid = (String) startVars.get("batchid");

        // 当前时间
        Date date = new Date();

        // 根据批次id和时间编码查询配置的时间
        PmsPlanDeadline pmsPlanDeadline = pmsPlanDeadlineService.getPmsPlanDeadline(batchid, timeCode);

        // 跟当前系统时间进行比较
        if (pmsPlanDeadline != null) {
            boolean bo1 = pmsPlanDeadline.getStartTime() != null && date.compareTo(pmsPlanDeadline.getStartTime()) < 0;
            boolean bo2 = pmsPlanDeadline.getEndTime() != null && date.compareTo(pmsPlanDeadline.getEndTime()) > 0;

            if (bo1 || bo2) {
                return Result.failOf(pmsPlanDeadline.getMessage());
            }
        }

        return Result.successOf("操作成功");
    }


}
