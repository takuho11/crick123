package cn.topcheer.halberd.app.api.framework.service;


import cn.topcheer.halberd.app.api.framework.entity.api.AmDataSource;
import cn.topcheer.halberd.app.api.framework.vo.DBResult;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;

import java.util.List;

public interface DbInfoService {

    List<AmDataSource> getEnableDataSources();

    List<String> getSchemas(Long dataSourceId);


    List<DbTableOrView> getAllTableOrViews(Long dataSourceId, String schema);


    DbTableOrView getTableOrView(Long dataSourceId, String schema, String tableOrViewName);


    List<DbColumn> getColumns(Long dataSourceId, String schema, String tableOrViewName);

    /**
     *
     * 通过路径来查询数据库的信息，以此来达到查询不同数据库的不同特性的元数据信息
     * 路径有两种类型
     *  const path: 形如 /datasources
     *  param path: 形如 /datasources/{datasourceName}
     *
     * @param path 查询路径
     * @param type 为"tree"时，返回{@link DBTree}, 为"detail",返回其他类型
     * @return
     */
    DBResult getDbInfoByPath(List<String> path, String type);


    List<DbTableOrView> getAllTableOrViewsByName(Long dataSourceId, String schema, String name);

    /**
     * 获取自己创建的数据源
     * @author zuowentao
     * @return 数据源列表
     */
    List<AmDataSource> getMyDataSources();
}
