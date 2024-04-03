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
@DbObject(type = "table")
public class OracleTable implements DbTableOrView {

    @JsonAlias("OWNER")
    @DbProp(label = "模式", seq = 1, showInGrid = false)
    private String schema;

    @DbObjectKey
    @JsonAlias("TABLE_NAME")
    @DbProp(label = "表名", seq = 2)
    private String name;

    @JsonAlias("LOGGING")
    @JsonDeserialize(using = BooleanStringDeserializer.class)
    @DbProp(label = "是否开启日志", seq = 5)
    private Boolean logging;

    @JsonAlias("BACKED_UP")
    @JsonDeserialize(using = BooleanStringDeserializer.class)
    @DbProp(label = "更新后是否备份", seq = 6)
    private Boolean backUp;

    @JsonAlias("NUM_ROWS")
    @DbProp(label = "行数(大致)", seq = 7)
    private Long numRows;

    @JsonAlias("DROPPED")
    @JsonDeserialize(using = BooleanStringDeserializer.class)
    @DbProp(label = "是否已删除", seq = 8)
    private Boolean dropped;

    @JsonAlias("COMMENTS")
    @DbProp(label = "注释", seq = 9)
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
        return false;
    }
}
