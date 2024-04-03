package cn.topcheer.halberd.app.api.framework.vo;


public interface DbColumn {

    /**
     * @see DataStoreType.Type#name
     */
    String getDbType();

    String getSchema();

    String getTableName();

    String getTableFullName();

    String getColumnName();

    /**
     *
     * @return 带参数的字段类型
     */
    String getColumnType();

    /**
     *
     * @return 不带参数的字段类型
     */
    String getDataType();

    /**
     *
     * @return 数据库字段类型对应的jdbc type
     * @see java.sql.Types
     */
    int getJdbcType();

    Long getCharLength();

    Integer getNumericPrecision();

    Integer getNumericScale();

    Boolean isPrimaryKey();

    Boolean isNullable();

    String getComment();
}
