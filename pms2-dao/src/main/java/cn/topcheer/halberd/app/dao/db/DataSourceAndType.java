package cn.topcheer.halberd.app.dao.db;


import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.dto.db.ExecuteSqlRequest;
import cn.topcheer.halberd.app.api.framework.dto.db.ExecuteSqlResult;
import cn.topcheer.halberd.app.dao.db.dialect.ISqlDialect;
import cn.topcheer.halberd.app.dao.db.dialect.SqlDialectFactory;
import cn.topcheer.halberd.app.dao.db.metadata.DbMetaDataFactory;
import cn.topcheer.halberd.app.dao.db.metadata.IDbMetaData;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLShowStatement;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Map;


public class DataSourceAndType {

    @Getter
    protected Long id;

    @Getter
    protected DruidDataSource druidDataSource;

    @Getter
    protected JdbcTemplate jdbcTemplate;

    @Getter
    protected String type;

    @Getter
    protected IDbMetaData metaData;

    @Getter
    protected ISqlDialect sqlDialect;

    @Getter
    private JdbcTransactionManager jdbcTransactionManager;


    private DataSourceAndType() {
    }


    public static DataSourceAndType newInstance(Long id, DruidDataSource druidDataSource, String type){
        DataSourceAndType wrapper = new DataSourceAndType();
        wrapper.id = id;
        wrapper.druidDataSource = druidDataSource;
        wrapper.jdbcTemplate = new JdbcTemplate(druidDataSource);
        wrapper.type = type;
        wrapper.sqlDialect = SqlDialectFactory.getDialectFromType(type);
        wrapper.metaData = DbMetaDataFactory.getMetaData(wrapper);
        wrapper.jdbcTransactionManager = new JdbcTransactionManager(druidDataSource);//lazyInit
        return wrapper;
    }



    public ExecuteSqlResult executeSelectSql(ExecuteSqlRequest request){
        String sql = StringUtils.trim(request.getSql());
        if(!isSelectStatement(sql, this.type)){
            throw new ServiceException("禁止使用查询以外的语句！");
        }
        if(!request.isPaging()){
            List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql);
            return new ExecuteSqlResult(result.size(), result);
        }
        int page = request.getPage();
        int size = request.getSize();
        String paginationSql = this.sqlDialect.generatePaginationSql(sql, (page - 1) * size, size);
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(paginationSql);

        String countSql = this.sqlDialect.generateCountSql(sql);
        Long totalElements = this.jdbcTemplate.queryForObject(countSql, Long.class);
        return new ExecuteSqlResult(totalElements, result);
    }

    private boolean isSelectStatement(String sql, String dbType){
        try{
            List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, DataStoreType.Type.toDruidDbType(dbType));
            for (SQLStatement sqlStatement : stmtList) {
                if(!(sqlStatement instanceof SQLSelectStatement) && ! (sqlStatement instanceof SQLShowStatement)){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            return false;
            //return sql.matches("^(?i)(\\s*)(select)(\\s+)(((?!(insert|delete|update|create)).)+)$");
        }
    }

    /**
     *
     * @param sql
     * @param data 全部的参数
     * @param batchSize
     * @return
     */
    public int[] batchUpdateWithTrans(String sql, List<Object[]> data, int batchSize){
        TransactionStatus status = this.jdbcTransactionManager.getTransaction(new DefaultTransactionDefinition());
        int[] result = new int[data.size()];
        int fromIndex = 0;
        int toIndex = 0;
        try{
            for(fromIndex=0; fromIndex<data.size(); fromIndex+=batchSize){
                toIndex = Math.min(fromIndex + batchSize, data.size());
                int[] subResult = this.jdbcTemplate.batchUpdate(sql, data.subList(fromIndex,toIndex));
                System.arraycopy(subResult, 0, result, fromIndex, subResult.length);
            }
            this.jdbcTransactionManager.commit(status);
            return result;
        }catch (Exception ex){
            this.jdbcTransactionManager.rollback(status);
            if(ex.getCause() instanceof BatchUpdateException){
                BatchUpdateException batchUpdateException = (BatchUpdateException) ex.getCause();
                int[] updateCounts = batchUpdateException.getUpdateCounts();
                for(int i=0; i<updateCounts.length && updateCounts[i]>=0; i++){
                    fromIndex ++;
                }
                throw new DataSourceWrapperBatchUpdateException(fromIndex, batchUpdateException.getMessage(), ex);
            }
            throw new DataSourceWrapperBatchUpdateException(new int[]{fromIndex, toIndex}, ex.getMessage(), ex);
        }
    }

    public static class DataSourceWrapperBatchUpdateException extends RuntimeException{

        @Getter
        private Integer errorIndex;

        @Getter
        private int[] errorIndexRange;

        public DataSourceWrapperBatchUpdateException(int errorIndex, String message, Throwable cause) {
            super(message, cause);
            this.errorIndex = errorIndex;
        }

        public DataSourceWrapperBatchUpdateException(int[] errorIndexRange, String message, Throwable cause) {
            super(message, cause);
            this.errorIndexRange = errorIndexRange;
        }
    }



}
