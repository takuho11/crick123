package cn.topcheer.pms2.biz.flow.service.listener;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.flow.model.FlowEvent;
import cn.topcheer.halberd.flow.service.FlowprocessListener;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * ProcessApplicationProjectListener
 *
 * @author szs
 * @date 2024-03-18
 */
@Service
public class ProcessApplicationProjectListener implements FlowprocessListener {


    @Override
    public void taskCreated(FlowEvent event) {
        System.err.println(">>>>>>>ProcessApplicationProjectListener.taskCreated：" + JSON.toJSONString(event));

        // 组装参数
        JSONObject param = this.makeParam(event);

        // 执行服务方法-修改审批状态
        this.runServiceMethod(param.getString("mainTableServiceName"), "updateMinicurrentState", param);

        // 执行服务方法-当前流程任务创建
        //this.runServiceMethod(param.getString("mainTableServiceName"), "minicurrentTaskCreated", param);

    }


    @Override
    public void taskAssigned(FlowEvent event) {
        System.err.println(">>>>>>>ProcessApplicationProjectListener.taskAssigned：" + JSON.toJSONString(event));
    }


    @Override
    public void taskCompleted(FlowEvent event) {
        System.err.println(">>>>>>>ProcessApplicationProjectListener.taskCompleted：" + JSON.toJSONString(event));

        // 组装参数
        JSONObject param = this.makeParam(event);

        // 执行服务方法-当前流程任务完成
        //this.runServiceMethod(param.getString("mainTableServiceName"), "minicurrentTaskCompleted", param);

    }


    @Override
    public void processCompleted(FlowEvent event) {
        System.err.println(">>>>>>>ProcessApplicationProjectListener.processCompleted：" + JSON.toJSONString(event));

        // 组装参数
        JSONObject param = this.makeParam(event);

        // 流程状态
        String status = null;

        // pass为true 表示完成，其他的可能是撤回、撤销等
        if (event.getProcessVariables().containsKey("pass") && (boolean) event.getProcessVariables().get("pass")) {
            status = "完成";
        }

        // 完成情况
        if (event.getProcessVariables().containsKey("wf_process_terminate")) {
            if ("true".equals(event.getProcessVariables().get("wf_process_terminate"))) {
                // 终止
                status = "终止";

            } else if ("withdraw".equals(event.getProcessVariables().get("wf_process_terminate"))) {
                // 撤销
                status = "撤销";

            }

        }

        param.put("minicurrentstate", Util.ifEmptyToStr(status));

        // 执行服务方法-修改审批状态
        this.runServiceMethod(param.getString("mainTableServiceName"), "updateMinicurrentState", param);

    }

    @Override
    public void processStarted(FlowEvent event) {
        System.err.println(">>>>>>>ProcessApplicationProjectListener.processStarted：" + JSON.toJSONString(event));
    }


    /**
     * 组装参数
     *
     * @param event FlowEvent
     * @return JSONObject
     * @author szs
     * @date 2024-03-19
     */
    private JSONObject makeParam(FlowEvent event) {
        // 业务id
        String businessId = (String) event.getProcessVariables().get("business_id");

        // 流程状态
        String status = event.getTaskName();

        // 流程状态id
        String statusId = event.getTaskId();

        // 额外参数
        HashMap startVars = (HashMap) event.getProcessVariables().get("startVars");

        // 主表服务名称
        String mainTableServiceName = (String) startVars.get("mainTableServiceName");

        // 操作结果，默认取task_result_custom，如果为空那就取task_result
        String taskResult = "wf_pass";
        String taskResultName = "通过";
        String taskName = "用户填报";
        if (event.getProcessVariables().containsKey("taskVariables")) {
            // 任务参数
            HashMap taskVariables = (HashMap) event.getProcessVariables().get("taskVariables");
            if (taskVariables.containsKey("task_result_custom")) {
                taskResult = (String) taskVariables.get("task_result_custom");
            }

            if (StringUtil.isBlank(taskResult)) {
                taskResult = (String) taskVariables.get("task_result");
            }

            if (taskVariables.containsKey("task_result_custom_name")) {
                taskResultName = (String) taskVariables.get("task_result_custom_name");
            }

            if (taskVariables.containsKey("task_name")) {
                taskName = (String) taskVariables.get("task_name");
            }

        }

        // 请求参数
        JSONObject param = new JSONObject();
        param.put("mainId", Util.ifEmptyToStr(businessId));
        param.put("minicurrentstate", Util.ifEmptyToStr(status));
        param.put("minicurrentstateid", Util.ifEmptyToStr(statusId));
        param.put("miniCurrentTaskDefKey", Util.ifEmptyToStr(event.getTaskDefinitionKey()));
        param.put("miniCurrentProcessDefKey", Util.ifEmptyToStr(event.getProcessDefinitionKey()));
        param.put("taskResult", Util.ifEmptyToStr(taskResult));
        param.put("taskResultName", Util.ifEmptyToStr(taskResultName));
        param.put("taskName", Util.ifEmptyToStr(taskName));
        param.put("mainTableServiceName", Util.ifEmptyToStr(mainTableServiceName));

        return param;
    }


    /**
     * 执行服务方法
     *
     * @param serviceName 服务名称
     * @param methodName  方法名称
     * @param param       请求参数
     * @author szs
     * @date 2024-03-13
     */
    private void runServiceMethod(String serviceName, String methodName, JSONObject param) {
        System.err.println(">>>>>>>ProcessApplicationProjectListener.runServiceMethod：serviceName=" + serviceName + "&methodName=" + methodName + "&param=" + JSON.toJSONString(param));

        if (StringUtils.isBlank(serviceName)) {
            System.err.println(">>>>>>>ProcessApplicationProjectListener.runServiceMethod-serviceName为空");
            return;
        }

        if (StringUtils.isBlank(methodName)) {
            System.err.println(">>>>>>>ProcessApplicationProjectListener.runServiceMethod-methodName为空");
            return;
        }

        try {
            // 动态执行Service方法
            Util.springInvokeMethod(serviceName, methodName, new Object[]{param});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
