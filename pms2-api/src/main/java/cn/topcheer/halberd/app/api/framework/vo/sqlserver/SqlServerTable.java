package cn.topcheer.halberd.app.api.framework.vo.sqlserver;

import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DbObject(type = "table")
public class SqlServerTable {

    @JsonAlias("database_name")
    @DbProp(label = "数据库", seq = 1, showInGrid = false)
    private String database;

    @JsonAlias("schema_name")
    @DbProp(label = "模式", seq = 2, showInGrid = false)
    private String schema;

    @DbObjectKey
    @JsonAlias("name")
    @DbProp(label = "表名", seq = 3)
    private String name;

    @JsonAlias("object_id")
    private Integer objectId;

    @DbProp(label = "创建时间", seq = 4)
    @JsonAlias("create_date")
    private String createDate;

    @DbProp(label = "修改时间", seq = 5)
    @JsonAlias("modify_date")
    private String modifyDate;

    @DbProp(label = "注释", seq = 6)
    @JsonAlias("comment")
    private String comment;
}
