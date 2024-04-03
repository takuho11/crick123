package cn.topcheer.halberd.app.api.framework.enumerate;


import cn.topcheer.pms2.common.enumerate.EnumBase;

public enum ApiChildType implements EnumBase {
    select("查询"),
    insert("插入"),
    update("更新"),
    delete("删除");

    ApiChildType(String describe){
            this.describe = describe;
    }

    private String describe;
    @Override
    public String getDescribe() {
        return this.describe;
    }
}
