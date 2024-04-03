package cn.topcheer.halberd.app.api.framework.dto.db.dataintegration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Setter
@Builder
public class DataIntegrationMySqlColumn {

    private String tableSchema;
    private String tableName;
    private String name;
    private Integer seq;
    private String columnDefault;
    private Boolean nullable;
    private Long charMaxLength;
    private Integer numericPrecision;
    private Integer numericScale;
    private String type;
    private String characterSetName;
    private String collationName;
    private Boolean keyBtree;
    private Boolean primaryKey;
    private String comment;

    private String chineseName;


    public static final RowMapper<DataIntegrationMySqlColumn> rowMapper = (rs, rowNum) ->
            DataIntegrationMySqlColumn.builder()
                    .tableSchema(rs.getString("TABLE_SCHEMA"))
                    .tableName(rs.getString("TABLE_NAME"))
                    .name(rs.getString("COLUMN_NAME"))
                    .seq(Integer.parseInt(rs.getString("ORDINAL_POSITION")))
                    .columnDefault(rs.getString("COLUMN_DEFAULT"))
                    .nullable("YES".equalsIgnoreCase(rs.getString("IS_NULLABLE")))
                    .charMaxLength(rs.getLong("CHARACTER_MAXIMUM_LENGTH"))
                    .numericPrecision(rs.getInt("NUMERIC_PRECISION"))
                    .numericScale(rs.getInt("NUMERIC_SCALE"))
                    .type(rs.getString("DATA_TYPE"))
                    .characterSetName(rs.getString("CHARACTER_SET_NAME"))
                    .collationName(rs.getString("COLLATION_NAME"))
                    .keyBtree(rs.getString("COLUMN_KEY").contains("MUL"))
                    .primaryKey(rs.getString("COLUMN_KEY").contains("PRI"))
                    .comment(rs.getString("COLUMN_COMMENT"))
                    .build();





}
