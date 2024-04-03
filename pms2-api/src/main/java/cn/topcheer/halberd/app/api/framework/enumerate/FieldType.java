package cn.topcheer.halberd.app.api.framework.enumerate;


import cn.topcheer.pms2.common.enumerate.EnumBase;

public enum FieldType implements EnumBase {
    String("字符串"),
    Long("长整数"),
    Integer("整数"),
    Short("短整数"),
    Double("小数"),
    LocalDateTime("日期时间"),
    Date("日期");

    private String describe;

    FieldType(String describe){
        this.describe = describe;
    }

    public java.lang.String getDescribe() {
        return describe;
    }
}
