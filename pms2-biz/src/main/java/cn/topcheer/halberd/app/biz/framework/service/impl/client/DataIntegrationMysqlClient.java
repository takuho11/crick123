package cn.topcheer.halberd.app.biz.framework.service.impl.client;


import cn.topcheer.halberd.app.api.framework.dto.db.dataintegration.DataIntegrationMySqlColumn;
import cn.topcheer.halberd.app.api.framework.exception.DataCenterException;
import cn.topcheer.halberd.app.dao.db.DataSourceAndType;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.topcheer.halberd.app.api.framework.ResultEnum.CREATE_TABLE_FAIL;


@Component
public class DataIntegrationMysqlClient {


    /**
     * 本服务的数据库连接池
     */
    @Autowired
    private JdbcTemplate datacenterJdbcTemplate;


    public List<DataIntegrationMySqlColumn> tableColumns(Long dataSourceId, JdbcTemplate jdbcTemplate, String databaseName, String tableName) {
        String sql =
                "SELECT * " +
                        "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                        "WHERE TABLE_SCHEMA = ?\n" +
                        "AND TABLE_NAME = ?\n" +
                        "ORDER BY ORDINAL_POSITION";
        List<DataIntegrationMySqlColumn> columns = jdbcTemplate.query(sql, DataIntegrationMySqlColumn.rowMapper,databaseName, tableName);

        String otherInfoSql =
                "SELECT col.table_schema, col.table_name, col.column_name, col.dc_name \n" +
                        "FROM am_db_info_columns col \n" +
                        "JOIN am_db_version v ON col.dc_version_id = v.id \n" +
                        "WHERE col.dc_datasource_id = ?\n" +
                        "AND col.table_schema = ? \n" +
                        "AND col.table_name = ? \n" +
                        "AND v.`current` = 1";
        List<Map<String, Object>> infoList = datacenterJdbcTemplate.queryForList(otherInfoSql, dataSourceId, databaseName, tableName);
        return addColumnInfo(columns, infoList);
    }


    public void createTable(DataSourceAndType dataSourceAndType, String createSql) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceAndType.getDruidDataSource());
            jdbcTemplate.execute(createSql);
        } catch (Exception e) {
            throw new DataCenterException(e,CREATE_TABLE_FAIL.code);
        }
    }


    private List<DataIntegrationMySqlColumn> addColumnInfo(List<DataIntegrationMySqlColumn> columns, List<Map<String, Object>> infoList){
        if(infoList == null || infoList.isEmpty()){
            return columns;
        }
        Map<String, Map<String, Object>> infoMap = infoList.stream()
                .filter(info ->
                        !StringUtil.isBlank(info.get("table_schema").toString())
                                && !StringUtil.isBlank(info.get("table_name").toString())
                                && !StringUtil.isBlank(info.get("column_name").toString())
                )
                .collect(Collectors.toMap(
                        info -> info.get("table_schema") + "." + info.get("table_name") + "." + info.get("column_name"),
                        info -> info
                ));
        return columns.stream().map(column -> {
            String key = column.getTableSchema() + "." + column.getTableName() + "." + column.getName();
            if(infoMap.containsKey(key)){
                Map<String, Object> info = infoMap.get(key);
                column.setChineseName(info.get("dc_name") + "");
            }
            return column;
        }).collect(Collectors.toList());
    }



}
