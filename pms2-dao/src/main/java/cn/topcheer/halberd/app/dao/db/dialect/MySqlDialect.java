package cn.topcheer.halberd.app.dao.db.dialect;

public class MySqlDialect extends CommonSqlDialect {

    @Override
    public String generatePaginationSql(String sql, long offset, long limit) {
        return sql + String.format(" LIMIT %s, %s", offset, limit);
    }

    @Override
    public char getLeftEscapeChar() {
        return '`';
    }

    @Override
    public char getRightEscapeChar() {
        return '`';
    }
}
