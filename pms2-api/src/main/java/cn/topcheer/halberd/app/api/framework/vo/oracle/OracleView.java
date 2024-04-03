package cn.topcheer.halberd.app.api.framework.vo.oracle;

import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;
import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
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
@DbObject(type = "view")
public class OracleView implements DbTableOrView {

    @JsonAlias("OWNER")
    @DbProp(label = "模式", seq = 1, showInGrid = false)
    private String schema;

    @DbObjectKey
    @JsonAlias("VIEW_NAME")
    @DbProp(label = "视图名", seq = 2)
    private String name;

    @JsonAlias("READ_ONLY")
    @JsonDeserialize(using = BooleanStringDeserializer.class)
    @DbProp(label = "只读", seq = 5)
    private Boolean readOnly;

    @JsonAlias("COMMENTS")
    @DbProp(label = "注释", seq = 6)
    private String comment;


    @Override
    public String getDbType() {
        return DataStoreType.Type.ORACLE.getName();
    }

    @Override
    public String getFullName() {
        return this.schema + "." + this.name;
    }

    @Override
    public boolean isView() {
        return true;
    }
}
