package cn.topcheer.halberd.app.api.framework.vo.sqlserver;

import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import cn.topcheer.halberd.app.common.json.BooleanStringDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DbObject(type = "database")
public class SqlServerDatabase {

    @DbObjectKey
    @JsonAlias("name")
    @DbProp(label = "名称", seq = 1)
    private String name;

    @JsonAlias("create_date")
    @DbProp(label = "创建/重命名时间", seq = 2)
    private String createDate;

    @JsonAlias("compatibility_level")
    private Integer compatibilityLevel;

    @DbProp(label = "兼容性", seq = 3)
    public String getCompatibilityLevelDesc(){
        switch (this.compatibilityLevel){
            case 70: return "SQL Server 7.0 到 SQL Server 2008 (10.0.x)";
            case 80: return "SQL Server 2000 (8.x) 到 SQL Server 2008 R2 (10.50.x)";
            case 90: return "SQL Server 2008 (10.0.x) 到 SQL Server 2012 (11.x)";
            case 100: return "SQL Server 2008 (10.0.x) 及更高版本，以及 Azure SQL Database";
            case 110: return "SQL Server 2012 (11.x) 及更高版本，以及 Azure SQL Database";
            case 120: return "SQL Server 2014 (12.x) 及更高版本，以及 Azure SQL Database";
            case 130: return "SQL Server 2016 (13.x) 及更高版本，以及 Azure SQL Database";
            case 140: return "SQL Server 2017 (14.x) 及更高版本，以及 Azure SQL Database";
            case 150: return "SQL Server 2019 (15.x) 及更高版本，以及 Azure SQL Database";
            case 160: return "SQL Server 2022 (16.x) 及更高版本，以及 Azure SQL Database";
            default: return this.compatibilityLevel + "";
        }
    }

    @DbProp(label = "默认排序规则", seq = 4)
    @JsonAlias("collation_name")
    private String collationName;

    @DbProp(label = "用户访问设置", seq = 5)
    @JsonAlias("user_access_desc")
    private String userAccess;

    @DbProp(label = "是否只读", seq = 6)
    @JsonAlias("is_read_only")
    @JsonDeserialize(using = BooleanStringDeserializer.class)
    private Boolean readOnly;

    @DbProp(label = "状态", seq = 7)
    @JsonAlias("state_desc")
    private String state;

    @DbProp(label = "恢复模式", seq = 8)
    @JsonAlias("recovery_model_desc")
    private String recoveryModel;

}
