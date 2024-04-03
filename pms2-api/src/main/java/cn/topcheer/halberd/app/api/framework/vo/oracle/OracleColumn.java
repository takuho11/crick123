package cn.topcheer.halberd.app.api.framework.vo.oracle;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.db.DbObject;
import cn.topcheer.halberd.app.api.framework.db.DbObjectKey;
import cn.topcheer.halberd.app.api.framework.db.DbProp;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.common.json.BooleanStringDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.sql.Types;
import java.util.Arrays;

/**
 * todo:完善oracle数据类型解析
 */
@Getter
@Setter
@DbObject(type = "column")
public class OracleColumn implements DbColumn {

    @JsonAlias("OWNER")
    @DbProp(label = "模式", seq = 0, showInGrid = false)
    private String schema;

    @JsonAlias("TABLE_NAME")
    @DbProp(label = "表名", seq = 1, showInGrid = false)
    private String tableName;

    @DbObjectKey
    @JsonAlias("COLUMN_NAME")
    @DbProp(label = "列名", seq = 2)
    private String columnName;

    @JsonAlias("DATA_TYPE")
    private String dataType;

    @Override
    public int getJdbcType() {
        switch (this.dataType){
            case "CHAR": return Types.CHAR;
            case "VARCHAR2": return Types.VARCHAR;
            case "NUMBER": return Types.DECIMAL;
            case "LONG": return Types.LONGVARCHAR;
            case "DATE":
            case "TIMESTAMP(6)": return Types.TIMESTAMP;
            case "RAW": return Types.VARBINARY;
            case "LONG RAW": return Types.LONGVARBINARY;
            case "BLOB": return Types.BLOB;
            case "CLOB": return Types.CLOB;
            case "BFILE": return -13;
            case "FLOAT": return Types.FLOAT;
            case "TIMESTAMP(6) WITH TIME ZONE": return -101;
            case "TIMESTAMP(6) WITH LOCAL TIME ZONE": return -102;
            case "INTERVAL YEAR(2) TO MONTH": return -103;
            case "INTERVAL DAY(2) TO SECOND(6)": return -104;
            case "BINARY_FLOAT": return 100;
            case "BINARY_DOUBLE": return 101;
            case "XMLTYPE": return Types.SQLXML;
            default: return Types.OTHER;
        }
    }

    @JsonAlias("DATA_LENGTH")
    private Long dataLength;

    @JsonAlias("DATA_PRECISION")
    private Integer numericPrecision;

    @JsonAlias("DATA_SCALE")
    private Integer numericScale;

    @JsonAlias("COLUMN_ID")
    @DbProp(label = "排序", seq = 4)
    private Integer seq;

    @JsonAlias("IS_PK")
    @JsonDeserialize(using = BooleanStringDeserializer.class)
    @DbProp(label = "主键", seq = 6)
    private Boolean primaryKey;

    @JsonAlias("NULLABLE")
    @JsonDeserialize(using = BooleanStringDeserializer.class)
    @DbProp(label = "允许为空", seq = 7)
    private Boolean nullable;

    @JsonAlias("DEFAULT_LENGTH")
    private Long defaultLength;

    @JsonAlias("DATA_DEFAULT")
    @DbProp(label = "默认值", seq = 8)
    private String dataDefault;

    @JsonAlias("CHARACTER_SET_NAME")
    @DbProp(label = "字符集", seq = 9)
    private String characterSetName;

    @JsonAlias("CHAR_COL_DECL_LENGTH")
    private Long charColDeclLength;

    @JsonAlias("CHAR_LENGTH")
    private Long charLength;

    @JsonAlias("CHAR_USED")
    private String charUsed;

    @JsonAlias("COMMENTS")
    @DbProp(label = "注释", seq = 10)
    private String comment;


    @Override
    public String getDbType() {
        return DataStoreType.Type.ORACLE.getName();
    }

    @Override
    public String getTableFullName() {
        return this.schema + "." + this.tableName;
    }

    /**
     *
     * @return
     * @see <a href="https://docs.oracle.com/cd/B28359_01/server.111/b28318/datatype.htm#CNCPT313">Overview of Numeric Datatypes</a>
     */
    @Override
    @DbProp(label = "类型", seq = 5)
    public String getColumnType() {
        if("NUMBER".equals(this.dataType)){
            if(this.numericPrecision != null && this.numericScale!=null){
                return String.format("NUMBER(%s,%s)", this.numericPrecision, this.numericScale);
            }else if(this.numericPrecision != null){
                return String.format("NUMBER(%s)", this.numericPrecision);
            } else if (this.numericScale!=null) {
                return String.format("NUMBER(*,%s)", this.numericScale);
            } else {
                return "NUMBER";
            }
        }
        if("FLOAT".equals(this.dataType)){
            return String.format("FLOAT(%s)", this.numericPrecision);
        }
        if("NCHAR".equals(this.dataType) || "NVARCHAR2".equals(this.dataType)){
            return String.format("%s(%s)", this.dataType, this.charLength);
        }
        if("CHAR".equals(this.dataType) || "VARCHAR".equals(this.dataType) ||"VARCHAR2".equals(this.dataType)){
            if("C".equals(this.charUsed)){
                return String.format("%s(%s CHAR)", this.dataType, this.charLength);
            } else if ("B".equals(this.charUsed)) {
                return String.format("%s(%s BYTE)", this.dataType, this.dataLength);
            }
        }
        return this.dataType;
    }

    @Override
    public Long getCharLength() {
        if(isStringType()){
            return this.charLength;
        }else {
            return null;
        }
    }

    @Override
    public Boolean isPrimaryKey() {
        return primaryKey;
    }

    @Override
    public Boolean isNullable() {
        return nullable;
    }


    private boolean isStringType(){
        return Arrays.asList("CHAR", "VARCHAR2", "NCHAR", "NVARCHAR2").contains(this.dataType);
    }
}
