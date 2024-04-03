package cn.topcheer.halberd.app.api.framework.service;


import cn.topcheer.halberd.app.api.framework.dto.DbColumnDTO;
import cn.topcheer.halberd.app.api.framework.dto.DbTableInfoDTO;
import cn.topcheer.halberd.app.api.framework.dto.dolphin.DataIntegrationDTO;
import cn.topcheer.halberd.app.api.framework.exception.DataCenterException;

import java.util.List;

public interface DictService {

    boolean saveDictAndTableInfo(DbTableInfoDTO dbTableInfoDTO, List<? extends DbColumnDTO> columns) throws DataCenterException;

    String getDolphinDataxSQL (DataIntegrationDTO dataIntegrationDTO) throws DataCenterException;

//    参数 incLevel:
//            是否是增量: 0=不是增量情况 ,1=s ,2=ods
//            s层需要duplicated模型，id列，增量时间列，tz_ptime列作为排序列。
//            ods层建议unique模型，id列，增量时间列，tz_ptime列作为key序列。
//            两层的列名应当是一样的。
    String getCreateTargetTableSQL(DataIntegrationDTO dataIntegrationDTO,int incLevel) throws DataCenterException;

    String getTruncateTargetTableSQL(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException;

    String getInsertSQLFromSToOds(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException;

}
