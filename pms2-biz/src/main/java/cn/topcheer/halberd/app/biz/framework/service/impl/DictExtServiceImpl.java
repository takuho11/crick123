package cn.topcheer.halberd.app.biz.framework.service.impl;


import cn.topcheer.halberd.app.api.framework.dto.DbColumnDTO;
import cn.topcheer.halberd.app.api.framework.dto.DbTableInfoDTO;
import cn.topcheer.halberd.app.api.framework.dto.dolphin.DataIntegrationDTO;
import cn.topcheer.halberd.app.api.framework.exception.DataCenterException;
import cn.topcheer.halberd.app.api.framework.service.DictService;
import cn.topcheer.halberd.app.biz.sql.SqlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DictExtServiceImpl implements DictService {



    @Autowired
    SqlGenerator sqlGenerator;



    @Override
    @Transactional
    public boolean saveDictAndTableInfo(DbTableInfoDTO dbTableInfoDTO, List<? extends DbColumnDTO> columns) throws DataCenterException {
//
//        Long dataSourceId = Long.parseLong(dbTableInfoDTO.getDataSourceId());
//        //todo 需要修改
//        String tableFullName = dbTableInfoDTO.getSchema() + "." + dbTableInfoDTO.getTableName();
//        DbTableOrViewEntity dbTableOrViewEntity =
//                DbTableOrViewEntity.builder()
//                        .datasourceId(dataSourceId)
//                        .tableSchema(dbTableInfoDTO.getSchema())
//                        .tableName(dbTableInfoDTO.getTableName())
//                        .tableFullName(tableFullName)
//                        .dataLevel(dbTableInfoDTO.getDataLevel())
//                        .businessDomain(dbTableInfoDTO.getBusinessDomain())
//                        .comment(dbTableInfoDTO.getComment())
//                        .memo(dbTableInfoDTO.getMemo())
//                        .view(false)
//                        .dbType(dbTableInfoDTO.getDataStoreType().getName())
//                        .build();
//
//        List<DbColumnEntity> dbColumnEntityList = columns.stream().map(c ->
//            DbColumnEntity.builder()
//                    .datasourceId(dataSourceId)
//                    .tableSchema(dbTableInfoDTO.getSchema())
//                    .tableName(dbTableInfoDTO.getTableName())
//                    .tableFullName(tableFullName)
//                    .columnName(c.getName())
//                    .chineseName(c.getChineseName())
//                    .columnType(c.getType())
//                    .charLength((long) c.getDestLength())
//                    .numericPrecision(c.getDestNumericPrecision())
//                    .numericScale(c.getDestDecimalPlaces())
//                    .primaryKey(c.isPrimaryKey())
//                    .nullable(c.isNullable())
//                    .comment(c.getComment())
//                    .memo(c.getMemo())
//                    .dataType(dbTableInfoDTO.getDataStoreType().getName())
//                    .build()
//        ).collect(Collectors.toList());
//
//        dbEntityService.save(dbTableOrViewEntity, dbColumnEntityList);
        return true;
    }


    @Override
    public String getCreateTargetTableSQL(DataIntegrationDTO dataIntegrationDTO, int incLevel) throws DataCenterException {
        return sqlGenerator.createTableSql(dataIntegrationDTO, incLevel);
    }

    @Override
    public String getTruncateTargetTableSQL(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException {
        DbTableInfoDTO targetTableInfo = dataIntegrationDTO.getTargetTableInfo();
        return sqlGenerator.getTruncateTargetTableSQL(targetTableInfo);
    }

    @Override
    public String getInsertSQLFromSToOds(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException {
        return sqlGenerator.getInsertSQLFromSToOds(dataIntegrationDTO);
    }


    @Override
    public String getDolphinDataxSQL(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException {
        return sqlGenerator.getDolphinDataxSQL(dataIntegrationDTO);
    }


}
