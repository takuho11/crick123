package cn.topcheer.halberd.app.dao.db.dialect;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.SQLOver;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.sqlserver.ast.SQLServerSelectQueryBlock;
import com.alibaba.druid.util.JdbcConstants;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.List;
import java.util.function.BiFunction;

public class SqlServerDialect extends CommonSqlDialect{


    @Override
    public String escapeSchema(String schema) {
        int i = schema.indexOf('.');
        String database = schema.substring(0, i);
        String s = schema.substring(i+1);
        return getLeftEscapeChar() + database + getRightEscapeChar() + '.' + getLeftEscapeChar() + s + getRightEscapeChar();
    }


    private static final SQLAllColumnExpr STAR = new SQLAllColumnExpr();

    /**
     * COUNT(*)
     */
    private static final SQLAggregateExpr COUNT_STAR = new SQLAggregateExpr("COUNT", null, STAR);


    /**
     * ROW_NUMBER() OVER ( ORDER BY CURRENT_TIMESTAMP )
     */
    private static final SQLAggregateExpr ROW_NUM_OVER_TIMESTAMP;

    static {
        ROW_NUM_OVER_TIMESTAMP = new SQLAggregateExpr("ROW_NUMBER");
        ROW_NUM_OVER_TIMESTAMP.setOver(new SQLOver(new SQLOrderBy(new SQLIdentifierExpr("CURRENT_TIMESTAMP"))));
    }



    /**
     * 注意ROW_NUMBER()从1开始
     * SELECT  *
     * FROM    ( SELECT    ROW_NUMBER() OVER ( ORDER BY OrderDate ) AS RowNum, *
     *           FROM      Orders
     *           WHERE     OrderDate >= '1980-01-01'
     *         ) AS RowConstrainedResult
     * WHERE   RowNum >= 1
     *     AND RowNum < 20
     * ORDER BY RowNum
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public String generatePaginationSql(String sql, long offset, long limit) {

        return parseSql(sql, (selectStatement, sqlServerSelectQueryBlock) -> {

            SQLOrderBy originalOrderBy = sqlServerSelectQueryBlock.getOrderBy();
            //去掉order by
            sqlServerSelectQueryBlock.setOrderBy(null);
            //加上别名
            addAlias(sqlServerSelectQueryBlock.getSelectList());
            //加上 ROW_NUMBER() OVER ( ORDER BY xxx ) AS RowNum
            String rowNumColumn = "[" + this.getRandomStringSqlNotContain(sql, "tz_row_", "_num", 5) + "]";
            sqlServerSelectQueryBlock.getSelectList().add(new SQLSelectItem(getRowNumOver(originalOrderBy), rowNumColumn));


            //合成"SELECT  * \n" +
            //    "FROM (%s) AS RowConstrainedResult\n" +
            //    "WHERE rn >= 0\n" +
            //    "AND rn < 100\n" +
            //    "ORDER BY rn"
            SQLServerSelectQueryBlock resultQuery = new SQLServerSelectQueryBlock();
            resultQuery.addSelectItem(new SQLSelectItem(STAR));
            resultQuery.setFrom(sqlServerSelectQueryBlock, "RowConstrainedResult");
            resultQuery.addWhere(new SQLBinaryOpExpr(new SQLIdentifierExpr(rowNumColumn), SQLBinaryOperator.GreaterThanOrEqual, SQLIntegerExpr.ofIntOrLong(offset + 1)));
            resultQuery.addWhere(new SQLBinaryOpExpr(new SQLIdentifierExpr(rowNumColumn), SQLBinaryOperator.LessThan, SQLIntegerExpr.ofIntOrLong(offset + 1 + limit)));
            resultQuery.addOrderBy(new SQLIdentifierExpr(rowNumColumn));

            selectStatement.getSelect().setQuery(resultQuery);
            return selectStatement.toString();
        });
    }


    /**
     * 分析语句，把order by去掉，并且每个列加上别名(排除 * 和 a.b)
     * @param sql
     * @return
     */
    @Override
    public String generateCountSql(String sql) {
        return parseSql(sql, (selectStatement, sqlServerSelectQueryBlock) -> {
            //去掉order by
            sqlServerSelectQueryBlock.setOrderBy(null);
            //加上别名
            addAlias(sqlServerSelectQueryBlock.getSelectList());

            //合成 select count(*) from ( %s ) as a
            SQLServerSelectQueryBlock countQuery = new SQLServerSelectQueryBlock();
            countQuery.setFrom(sqlServerSelectQueryBlock, "a");
            countQuery.getSelectList().add(new SQLSelectItem(COUNT_STAR));
            selectStatement.getSelect().setQuery(countQuery);

            return selectStatement.toString();
        });
    }


    private static <R> R parseSql(String sql, BiFunction<SQLSelectStatement, SQLServerSelectQueryBlock, R> sqlGenerateFunc){
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, JdbcConstants.SQL_SERVER);
        if(stmtList == null || stmtList.size() != 1){
            throw new UnsupportedOperationException("未能解析sql:" + sql);
        }
        if(!(stmtList.get(0) instanceof SQLSelectStatement)){
            throw new IllegalStateException("非查询语句:" + sql);
        }
        SQLSelectStatement selectStatement = (SQLSelectStatement) stmtList.get(0);
        SQLSelect sqlSelect = selectStatement.getSelect();
        SQLSelectQuery sqlSelectQuery = sqlSelect.getQuery();
        if(!(sqlSelectQuery instanceof SQLServerSelectQueryBlock)){
            throw new UnsupportedOperationException("未能解析sql:" + sql);
        }
        SQLServerSelectQueryBlock sqlServerSelectQueryBlock = (SQLServerSelectQueryBlock) sqlSelectQuery;

        return sqlGenerateFunc.apply(selectStatement, sqlServerSelectQueryBlock);
    }

    private static SQLServerSelectQueryBlock getSQLServerSelectQueryBlock(String sql, SQLSelectStatement selectStatement){
        SQLSelect sqlSelect = selectStatement.getSelect();
        SQLSelectQuery sqlSelectQuery = sqlSelect.getQuery();
        if(!(sqlSelectQuery instanceof SQLServerSelectQueryBlock)){
            throw new UnsupportedOperationException("未能解析sql:" + sql);
        }
        return (SQLServerSelectQueryBlock) sqlSelectQuery;
    }


    private static void addAlias(List<SQLSelectItem> sqlSelectItems){
        sqlSelectItems.forEach(sqlSelectItem -> {
            if(needAlias(sqlSelectItem)){
                sqlSelectItem.setAlias(String.format("[%s]", sqlSelectItem.getExpr().toString()));
            }
        });
    }

    /**
     * 排除 *, a.*, a.col 的情况
     * @param sqlSelectItem
     * @return
     */
    private static boolean needAlias(SQLSelectItem sqlSelectItem){
        SQLExpr expr = sqlSelectItem.getExpr();
        return !(expr instanceof SQLAllColumnExpr) &&
                !(expr instanceof SQLPropertyExpr) &&
                StringUtils.isBlank(sqlSelectItem.getAlias());
    }




    /**
     * 获取 ROW_NUMBER() OVER ( ORDER BY xxx )
     */
    private SQLAggregateExpr getRowNumOver(SQLOrderBy originalOrderBy){
        SQLAggregateExpr sqlAggregateExpr;
        if(originalOrderBy == null){
            sqlAggregateExpr = ROW_NUM_OVER_TIMESTAMP;
        }else {
            sqlAggregateExpr = new SQLAggregateExpr("ROW_NUMBER");
            sqlAggregateExpr.setOver(new SQLOver(originalOrderBy));
        }
        return sqlAggregateExpr;
    }

    @Override
    public char getLeftEscapeChar() {
        return '[';
    }

    @Override
    public char getRightEscapeChar() {
        return ']';
    }

    @Override
    public String validationQuery() {
        return "select 1 as a";
    }


    public static void main(String[] args){
        SqlServerDialect dialect = new SqlServerDialect();
        //PagerUtils.limit(sql, dbType, (int) offset, (int) limit);
        //PagerUtils.count(sql, dbType);
        //DruidSqlDialect dialect = new DruidSqlDialect(DbType.sqlserver);
        String sql0 = "select * from ImportantEnterpriseInstitute.dbo.KJCXTX_ProjectForMonth";

        String sql1 = "select \n" +
                "a.*,\n" +
                "\tNum [N], \n" +
                "\tID as i_d, \n" +
                "\t1, \n" +
                "\t'',\n" +
                "\tCURRENT_TIMESTAMP,\n" +
                "\tOBJECT_DEFINITION(object_id('INFORMATION_SCHEMA.COLUMNS')) \n" +
                "from ImportantEnterpriseInstitute.dbo.KJCXTX_ProjectForMonth a order by a.Num";
        String sql2 = "SELECT 1, *\n" +
                "FROM(\n" +
                "\tSELECT ROW_NUMBER() OVER (ORDER BY Num) AS [rn], a.*\n" +
                "\tFROM (\n" +
                "\t\tselect * from ImportantEnterpriseInstitute.dbo.KJCXTX_ProjectForMonth\n" +
                "\t) AS a\n" +
                ") AS b\n" +
                "WHERE [rn] >= 0 + 1\n" +
                "AND [rn] < 0 + 1 + 81\n" +
                "ORDER BY [rn]";
        String sql3 = "WITH selectTemp AS (\n" +
                "\tSELECT TOP 90 PERCENT ROW_NUMBER() OVER (ORDER BY Num) as __row_number__, * \n" +
                "\tFROM ImportantEnterpriseInstitute.dbo.KJCXTX_ProjectForMonth order by Num\n" +
                ") select 1, * from selectTemp order by [Catalog] ";

        System.out.println("--------------------------------------------------------------------------");
        System.out.println(sql0);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(dialect.generateCountSql(sql0));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(dialect.generatePaginationSql(sql0, 2, 100));
        System.out.println("--------------------------------------------------------------------------");


        System.out.println("--------------------------------------------------------------------------");
        System.out.println(sql1);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(dialect.generateCountSql(sql1));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(dialect.generatePaginationSql(sql1, 2, 100));
        System.out.println("--------------------------------------------------------------------------");

        System.out.println("--------------------------------------------------------------------------");
        System.out.println(sql2);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(dialect.generateCountSql(sql2));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(dialect.generatePaginationSql(sql2, 2, 100));
        System.out.println("--------------------------------------------------------------------------");

        System.out.println("--------------------------------------------------------------------------");
        System.out.println(sql3);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(dialect.generateCountSql(sql3));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(dialect.generatePaginationSql(sql3, 2, 100));
        System.out.println("--------------------------------------------------------------------------");
    }
}
