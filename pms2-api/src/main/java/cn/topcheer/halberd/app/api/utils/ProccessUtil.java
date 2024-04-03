package cn.topcheer.halberd.app.api.utils;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;

public class ProccessUtil {

    public enum OperationEnum {
        assignee("assignee", "变更审核人"),
        dispatch("dispatch", "调度"),
        transfer("transfer", "转办"),
        delegate("delegate", "委托"),
        pass("pass", "通过"),
        rollback("rollback", "驳回"),
        terminate("terminate", "终止"),
        withdraw("withdraw", "撤销"),
        addMultiInstance("addMultiInstance", "加签"),
        deleteMultiInstance("deleteMultiInstance", "减签"),
        recall("recall", "撤回"),
        deleteProcess("deleteProcess", "删除流程"),
        comment("comment", "通过");
        private String name;
        private String code;

        OperationEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

    }


    public static String getOperationName(String codeString) {
        for (OperationEnum oe : OperationEnum.values()) {
            if (oe.code.equalsIgnoreCase(codeString)) {
                return oe.name;
            }
        }
        return null;
    }

}
