package cn.topcheer.halberd.app.api.framework.enumerate;


import cn.topcheer.pms2.common.enumerate.EnumBase;

public enum ApiState implements EnumBase {
    undev("未开发"),
    deved("已开发"),
    online("已上线"),
    offline("已下线");

    private String describe;

    ApiState(String describe){
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }
}
