package cn.topcheer.halberd.app.api.framework.vo.mysql;

import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DbObject(type = "database")
public class MySqlDatabase {

    @DbObjectKey
    @JsonAlias("SCHEMA_NAME")
    @DbProp(label = "名称", seq = 1)
    private String name;

    @JsonAlias("DEFAULT_CHARACTER_SET_NAME")
    @DbProp(label = "默认字符集", seq = 2)
    private String charset;

    @JsonAlias("DEFAULT_COLLATION_NAME")
    @DbProp(label = "默认排序规则", seq = 3)
    private String collation;
}
