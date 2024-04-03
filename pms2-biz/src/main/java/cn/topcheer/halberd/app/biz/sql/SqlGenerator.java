package cn.topcheer.halberd.app.biz.sql;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.dto.DbTableInfoDTO;
import cn.topcheer.halberd.app.api.framework.dto.dolphin.DataIntegrationDTO;
import cn.topcheer.halberd.app.api.framework.exception.DataCenterException;
import cn.topcheer.halberd.app.biz.sql.generator.AbstactSqlGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static cn.topcheer.halberd.app.api.framework.ResultEnum.SQL_GENERATE_TYPE_NOT_SUPPORT;

@Component
public class SqlGenerator {

    @Autowired
    private Map<String, AbstactSqlGenerator> sqlGeneratorMap;


    public static final Map<String, String> DATA_TYPE_AND_BEAN_NAME = new HashMap<>();

    static {
        DATA_TYPE_AND_BEAN_NAME.put(DataStoreType.Type.MYSQL.getName(), "sqlGeneratorForMysql");
        DATA_TYPE_AND_BEAN_NAME.put(DataStoreType.Type.DORIS.getName(), "sqlGeneratorForDoris");

    }

    public String createTableSql(DataIntegrationDTO dataIntegrationDTO, int incLevel) {
        DbTableInfoDTO targetTableInfo = dataIntegrationDTO.getTargetTableInfo();
        DataStoreType.Type dataStoreType = targetTableInfo.getDataStoreType();
        AbstactSqlGenerator abstactSqlGenerator = getGenerator(dataStoreType);
        return abstactSqlGenerator.createTableSql( dataIntegrationDTO, incLevel);
    }

    public String getTruncateTargetTableSQL(DbTableInfoDTO targetTableInfo) throws DataCenterException {
        DataStoreType.Type dataStoreType = targetTableInfo.getDataStoreType();
        AbstactSqlGenerator abstactSqlGenerator = getGenerator(dataStoreType);
        return abstactSqlGenerator.getTruncateTargetTableSQL(targetTableInfo);
    }


    public String getDolphinDataxSQL(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException {
        DbTableInfoDTO targetTableInfo = dataIntegrationDTO.getTargetTableInfo();
        DataStoreType.Type dataStoreType = targetTableInfo.getDataStoreType();
        AbstactSqlGenerator abstactSqlGenerator = getGenerator(dataStoreType);
        return abstactSqlGenerator.getDolphinDataxSQL(dataIntegrationDTO);
    }


    public String getInsertSQLFromSToOds(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException {
        DbTableInfoDTO targetTableInfo = dataIntegrationDTO.getTargetTableInfo();
        DataStoreType.Type dataStoreType = targetTableInfo.getDataStoreType();
        AbstactSqlGenerator abstactSqlGenerator = getGenerator(dataStoreType);
        return abstactSqlGenerator.getInsertSQLFromSToOds(dataIntegrationDTO);
    }


    private AbstactSqlGenerator getGenerator(DataStoreType.Type dataStoreType){
        String generatorName = DATA_TYPE_AND_BEAN_NAME.get(dataStoreType.getName());
        if(StringUtils.isBlank(generatorName)){
            throw new DataCenterException(SQL_GENERATE_TYPE_NOT_SUPPORT.code);
        }
        AbstactSqlGenerator abstactSqlGenerator = sqlGeneratorMap.get(generatorName);
        if(null == abstactSqlGenerator){
            throw new DataCenterException(SQL_GENERATE_TYPE_NOT_SUPPORT.code);
        }
        return abstactSqlGenerator;
    }

}
