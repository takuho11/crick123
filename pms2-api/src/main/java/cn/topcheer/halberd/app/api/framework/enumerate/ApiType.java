package cn.topcheer.halberd.app.api.framework.enumerate;

import cn.topcheer.pms2.common.enumerate.EnumBase;

public enum ApiType implements EnumBase {


    script("数据服务"),
    service("注册服务"),
    exchange("交互服务"),
    templateScript("数据服务2"),
    crud("单表增删改查")
    ;

    private String describe;

    ApiType(String describe){
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

}
