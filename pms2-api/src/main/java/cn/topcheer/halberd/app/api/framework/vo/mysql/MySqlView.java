package cn.topcheer.halberd.app.api.framework.vo.mysql;

import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;
import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DbObject(type = "view")
public class MySqlView implements DbTableOrView {

    @JsonAlias("TABLE_SCHEMA")
    @DbProp(label = "库名", seq = 1)
    private String database;

    @DbObjectKey
    @JsonAlias("TABLE_NAME")
    @DbProp(label = "名称", seq = 2)
    private String name;

    @Override
    public String getDbType() {
        return DataStoreType.Type.MYSQL.getName();
    }

    @Override
    public String getSchema() {
        return this.database;
    }

    @Override
    public String getFullName() {
        return this.database + "." + this.name;
    }

    @Override
    public String getComment() {
        return null;
    }

    @Override
    public boolean isView() {
        return true;
    }
}
