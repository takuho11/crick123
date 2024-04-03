package cn.topcheer.halberd.app.dao.db.metadata;


import cn.topcheer.halberd.app.api.framework.vo.DBResult;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;

import java.util.List;

public interface IDbMetaData {

    List<String> getSchemas();

    /**
     * 展示doris数据湖能使用的schema
     * @return
     */
    List<String> getSchemasFilterByDorisDataLakeRule();

    /**
     * 转换成doris数据湖的schema
     * @param schema
     * @return
     */
    String transToDorisDataLakeSchema(String schema);

    String getCurrentSchema();

    /**
     * 从当前schema获取所有表和视图
     * @return
     */
    List<DbTableOrView> getAllTableOrViews();

    List<DbTableOrView> getAllTableOrViews(String schema);


    /**
     * 从当前schema获取
     * @param tableOrViewName
     * @return
     */
    DbTableOrView getTableOrView(String tableOrViewName);

    DbTableOrView getTableOrView(String schema, String tableOrViewName);

    /**
     * 从当前schema获取
     * @param tableOrViewName
     * @return
     */
    List<DbColumn> getColumns(String tableOrViewName);

    List<DbColumn> getColumns(String schema, String tableOrViewName);

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


    List<DbTableOrView> getAllTableOrViewsByName(String schema, String name);
}
