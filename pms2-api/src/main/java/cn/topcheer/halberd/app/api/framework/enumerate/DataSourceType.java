package cn.topcheer.halberd.app.api.framework.enumerate;

import cn.topcheer.pms2.common.enumerate.EnumBase;

public enum DataSourceType implements EnumBase {
    MySQL,
    Oracle,
    RestFul,
    WebService,
    DORIS,
    SQLSERVER,
    POSTGRESQL,
    DM,
    Unknown;

    @Override
    public String getDescribe() {
        
        return this.name();
    }

    public static DataSourceType compatibleValueOf(String dbName) {
        for (DataSourceType value : DataSourceType.values()) {
            if(value.getDescribe().toLowerCase().equals(dbName.toLowerCase())){
                return value;
            }
        }
        return Unknown;
    }
}
