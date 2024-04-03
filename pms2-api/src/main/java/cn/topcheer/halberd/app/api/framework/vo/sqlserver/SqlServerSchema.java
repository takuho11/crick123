package cn.topcheer.halberd.app.api.framework.vo.sqlserver;

import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DbObject(type = "database")
public class SqlServerSchema {

    @DbObjectKey
    @JsonAlias("name")
    @DbProp(label = "名称")
    private String name;

    @JsonAlias("schema_id")
    private String schemaId;

}
