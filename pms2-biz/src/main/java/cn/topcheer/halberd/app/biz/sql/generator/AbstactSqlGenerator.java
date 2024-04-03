package cn.topcheer.halberd.app.biz.sql.generator;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.dto.DbTableInfoDTO;
import cn.topcheer.halberd.app.api.framework.dto.dolphin.DataIntegrationDTO;
import cn.topcheer.halberd.app.api.framework.exception.DataCenterException;

import static cn.topcheer.halberd.app.api.framework.ResultEnum.SQL_GENERATE_TYPE_ERROR;

public abstract class AbstactSqlGenerator {


    protected void checkDataStoreType(DataStoreType.Type type, DataStoreType.Type generatorType) {
        if (generatorType != type) {
            throw new DataCenterException(SQL_GENERATE_TYPE_ERROR.code);
        }
    }

    public abstract String createTableSql(DataIntegrationDTO dataIntegrationDTO, int incLevel);

    public abstract String getTruncateTargetTableSQL(DbTableInfoDTO targetTableInfo) throws DataCenterException;

    public abstract String getDolphinDataxSQL(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException;

    public abstract String getInsertSQLFromSToOds(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException;

}
