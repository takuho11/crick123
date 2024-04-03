package cn.topcheer.halberd.app.api.framework.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonDbColumn implements DbColumn {

    private String dbType;

    private String schema;

    private String tableName;

    private String tableFullName;

    private String columnName;

    private String columnType;

    private String dataType;

    private int jdbcType;

    private Long charLength;

    private Integer numericPrecision;

    private Integer numericScale;

    private Boolean primaryKey;

    private Boolean nullable;

    private String comment;

    @Override
    public Boolean isPrimaryKey() {
        return primaryKey;
    }

    @Override
    public Boolean isNullable() {
        return nullable;
    }
}
