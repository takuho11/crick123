package cn.topcheer.halberd.app.dao.db.dialect;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;

public class SqlDialectFactory {
    private SqlDialectFactory(){}

    private static MySqlDialect mySqlDialect = new MySqlDialect();
    private static DorisDialect dorisDialect = new DorisDialect();
    private static OracleDialect oracleDialect = new OracleDialect();
    private static SqlServerDialect sqlServerDialect = new SqlServerDialect();
    private static CommonSqlDialect commonSqlDialect = new CommonSqlDialect();


    public static ISqlDialect getDialectFromType(String type){
        if (DataStoreType.Type.MYSQL.getName().equals(type)){
            return mySqlDialect;
        } else if (DataStoreType.Type.DORIS.getName().equals(type)) {
            return dorisDialect;
        } else if (DataStoreType.Type.ORACLE.getName().equals(type)) {
            return oracleDialect;
        } else if (DataStoreType.Type.SQLSERVER.getName().equals(type)) {
            return sqlServerDialect;
        } else {
            return commonSqlDialect;
        }
    }
}
