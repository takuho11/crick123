package cn.topcheer.halberd.app.biz.framework.service.impl;


import cn.topcheer.halberd.app.api.framework.dto.api.AmDataSourceDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmDataSource;
import cn.topcheer.halberd.app.api.framework.service.DbInfoService;
import cn.topcheer.halberd.app.api.framework.vo.DBResult;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;
import cn.topcheer.halberd.app.biz.framework.service.DataSourceManagementService;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbInfoServiceImpl implements DbInfoService {

    @Autowired
    private DataSourceManagementService dataSourceManagementService;


    @Override
    public List<AmDataSource> getEnableDataSources() {
        return dataSourceManagementService.findAllEnabled(new AmDataSourceDTO());
    }

    /**
     * 获取自己创建的数据源
     * @return 数据源列表
     */
    @Override
    public List<AmDataSource> getMyDataSources() {
        AmDataSourceDTO dataSourceDTO = new AmDataSourceDTO();
        dataSourceDTO.setApproveStatus(2);
        // 如果是管理员就查全部，如果非管理员就查自己
        if(!AuthUtil.isAdmin()){
            dataSourceDTO.setCreateUser(AuthUtil.getUserId());
        }
        return dataSourceManagementService.findAllEnabled(dataSourceDTO);
    }

    @Override
    public List<String> getSchemas(Long dataSourceId) {
        return dataSourceManagementService.getDataSourceAndType(dataSourceId.toString()).getMetaData().getSchemas();
    }

    @Override
    public List<DbTableOrView> getAllTableOrViews(Long dataSourceId, String schema) {
        if(dataSourceId == null){
            return null;
        }
        return dataSourceManagementService.getDataSourceAndType(dataSourceId.toString()).getMetaData().getAllTableOrViews(schema);
    }

    @Override
    public DbTableOrView getTableOrView(Long dataSourceId, String schema, String tableOrViewName) {
        return dataSourceManagementService.getDataSourceAndType(dataSourceId.toString()).getMetaData().getTableOrView(schema, tableOrViewName);
    }

    @Override
    public List<DbColumn> getColumns(Long dataSourceId, String schema, String tableOrViewName) {
        return dataSourceManagementService.getDataSourceAndType(dataSourceId.toString()).getMetaData().getColumns(schema, tableOrViewName);
    }

    @Override
    public DBResult getDbInfoByPath(List<String> path, String type) {
        return dataSourceManagementService.getDbInfoByPath(path, type);
    }

    @Override
    public List<DbTableOrView> getAllTableOrViewsByName(Long dataSourceId, String schema, String name) {
        if(dataSourceId == null){
            return null;
        }
        return dataSourceManagementService.getDataSourceAndType(dataSourceId.toString()).getMetaData().getAllTableOrViewsByName(schema, name);
    }

}
