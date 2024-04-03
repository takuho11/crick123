package cn.topcheer.halberd.app.api.framework.enumerate;


import cn.topcheer.pms2.common.enumerate.EnumBase;

public enum CompareType implements EnumBase {
    eq("等于"),
    ne("不等于"),
    gt("大于"),
    ge("大于等于"),
    lt("小于"),
    le("小于等于"),
    in("IN"),
    between("BETWEEN"),
    like("模糊"),
    likeLeft("左模糊"),
    likeRight("右模糊"),
    isNull("为空"),
    notNull("非空");

    private String describe;

    CompareType(String describe){
        this.describe = describe;
    }

    @Override
    public String getDescribe() {
        return this.describe;
    }
}
