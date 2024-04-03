package cn.topcheer.halberd.app.api.framework.vo.mysql;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.common.json.BooleanStringDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mysql.cj.MysqlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DbObject(type = "column")
public class MySqlColumn implements DbColumn {

    @JsonAlias("TABLE_SCHEMA")
    @DbProp(label = "数据库", seq = 0, showInGrid = false)
    private String database;

    @JsonAlias("TABLE_NAME")
    @DbProp(label = "表", seq = 1, showInGrid = false)
    private String tableName;

    @DbObjectKey
    @JsonAlias("COLUMN_NAME")
    @DbProp(label = "列名", seq = 2)
    private String columnName;

    @JsonAlias("ORDINAL_POSITION")
    @DbProp(label = "排序", seq = 4)
    private Integer seq;

    @JsonAlias("COLUMN_DEFAULT")
    @DbProp(label = "默认值", seq = 5)
    private String columnDefault;

    @JsonAlias("IS_NULLABLE")
    @JsonDeserialize(using = BooleanStringDeserializer.class)
    @DbProp(label = "允许为空", seq = 6)
    private Boolean nullable;

    @JsonAlias("DATA_TYPE")
    private String dataType;

    @JsonAlias("COLUMN_TYPE")
    @DbProp(label = "类型", seq = 7)
    private String columnType;

    @Override
    public int getJdbcType() {
        return MysqlType.getByName(this.columnType).getJdbcType();
    }

    @JsonAlias("CHARACTER_MAXIMUM_LENGTH")
    private Long charLength;

    @JsonAlias("CHARACTER_OCTET_LENGTH")
    private Long charByteLength;

    @JsonAlias("NUMERIC_PRECISION")
    private Integer numericPrecision;

    @JsonAlias("NUMERIC_SCALE")
    private Integer numericScale;

    @JsonAlias("DATETIME_PRECISION")
    private Integer datetimePrecision;

    @JsonAlias("CHARACTER_SET_NAME")
    @DbProp(label = "字符集", seq = 8)
    private String characterSetName;

    @JsonAlias("COLLATION_NAME")
    @DbProp(label = "排序规则", seq = 9)
    private String collationName;

    @JsonAlias("COLUMN_KEY")
    @DbProp(label = "键", seq = 10)
    private String columnKey;

    @JsonAlias("EXTRA")
    @DbProp(label = "extra", seq = 11)
    private String extra;

    @JsonAlias("COLUMN_COMMENT")
    @DbProp(label = "注释", seq = 12)
    private String comment;


    @Override
    public String getDbType(){
        return DataStoreType.Type.MYSQL.getName();
    }

    @Override
    public String getSchema() {
        return this.database;
    }

    @Override
    public String getTableFullName() {
        return this.database + "." + this.tableName;
    }

    @Override
    public Boolean isPrimaryKey() {
        return "PRI".equalsIgnoreCase(this.columnKey);
    }

    @Override
    public Boolean isNullable() {
        return this.nullable;
    }
}
