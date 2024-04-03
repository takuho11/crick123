package cn.topcheer.halberd.app.dao.db.dialect;

public class OracleDialect extends CommonSqlDialect {


    /**
     * 注意rownum从1开始
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public String generatePaginationSql(String sql, long offset, long limit) {
        String rowNumColumnName = this.getRandomStringSqlNotContain(sql, "tz_row_", "_num", 5);
        return String.format(
                "SELECT * \n" +
                "FROM (SELECT A.*, rownum \"%s\" \n" +
                "FROM ( %s ) A\n" +
                "WHERE rownum < %d)\n" +
                "WHERE \"%s\" >= %d",
                rowNumColumnName,
                sql,
                offset + 1 + limit,
                rowNumColumnName,
                offset + 1
        );
    }

    @Override
    public String validationQuery() {
        return "select 1 from dual";
    }

    @Override
    public char getLeftEscapeChar() {
        return '"';
    }

    @Override
    public char getRightEscapeChar() {
        return '"';
    }
}
