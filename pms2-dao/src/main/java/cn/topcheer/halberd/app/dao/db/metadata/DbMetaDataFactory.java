package cn.topcheer.halberd.app.dao.db.metadata;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.dao.db.DataSourceAndType;

public class DbMetaDataFactory {
    private DbMetaDataFactory(){}

    public static IDbMetaData getMetaData(DataSourceAndType wrapper){
        String type = wrapper.getType();
        if (DataStoreType.Type.MYSQL.getName().equals(type)){
            MySqlMetaData mySqlMetaData = new MySqlMetaData();
            mySqlMetaData.setDataSourceWrapper(wrapper);
            return mySqlMetaData;
        } else if (DataStoreType.Type.DORIS.getName().equals(type)) {
            DorisMetaData dorisMetaData = new DorisMetaData();
            dorisMetaData.setDataSourceWrapper(wrapper);
            return dorisMetaData;
        } else if (DataStoreType.Type.ORACLE.getName().equals(type)) {
            OracleMetaData oracleMetaData = new OracleMetaData();
            oracleMetaData.setDataSourceWrapper(wrapper);
            return oracleMetaData;
        } else {
            JdbcMetaData jdbcMetaData = new JdbcMetaData();
            jdbcMetaData.setDataSourceWrapper(wrapper);
            return jdbcMetaData;
        }
    }
}
