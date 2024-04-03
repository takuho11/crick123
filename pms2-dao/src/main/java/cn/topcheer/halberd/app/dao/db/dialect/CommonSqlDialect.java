package cn.topcheer.halberd.app.dao.db.dialect;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonSqlDialect implements ISqlDialect{
    @Override
    public String escapeSchema(String schema) {
        return getLeftEscapeChar() + schema + getRightEscapeChar();
    }

    @Override
    public String escapeTableName(String tableName) {
        return getLeftEscapeChar() + tableName + getRightEscapeChar();
    }

    protected String getRandomStringSqlNotContain(String sql, String prefix, String suffix, int length){
        int i = 0;
        String randomStr;
        do{
            randomStr = prefix + RandomStringUtils.randomAlphanumeric(length) + suffix;
            i++;

            if(i > 1000){
                throw new IllegalStateException("存在字符冲突，无法生成语句, 源sql:" + sql);
            }
        }while (sql.toLowerCase().contains(randomStr.toLowerCase()));

        return randomStr;
    }



    @Override
    public String generatePaginationSql(String sql, long offset, long limit) {
        return sql + String.format(" LIMIT %s OFFSET %s", limit, offset);
    }

    @Override
    public String generateCountSql(String sql) {
        return String.format("select count(*) from ( %s ) a", sql);
    }

    @Override
    public String validationQuery() {
        return "select 1";
    }

    @Override
    public char getLeftEscapeChar() {
        return ' ';
    }

    @Override
    public char getRightEscapeChar() {
        return ' ';
    }


}
