package cn.topcheer.halberd.app.dao.db.dialect;

public interface ISqlDialect {

    String generatePaginationSql(String sql, long offset, long limit);

    String generateCountSql(String sql);

    String validationQuery();

    char getLeftEscapeChar();

    char getRightEscapeChar();

    /**
     * 对schema进行转义
     * @return
     */
    String escapeSchema(String schema);

    String escapeTableName(String tableName);

}
