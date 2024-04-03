package cn.topcheer.halberd.app.dao.db.metadata;


import cn.topcheer.halberd.app.api.framework.db.DbPath;
import cn.topcheer.halberd.app.api.framework.vo.CommonDbTableOrView;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;
import cn.topcheer.halberd.app.api.framework.vo.mysql.MySqlColumn;
import cn.topcheer.halberd.app.api.framework.vo.mysql.MySqlDatabase;
import cn.topcheer.halberd.app.api.framework.vo.mysql.MySqlTable;
import cn.topcheer.halberd.app.api.framework.vo.mysql.MySqlView;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.topcheer.halberd.app.dao.db.metadata.JdbcResultConvertor.convertResult;

public class MySqlMetaData extends AbstractDbMetaData {

    /**
     * 如果连接字符串没指定，则选择"mysql"
     * @return
     */
    @Override
    public String getCurrentSchema() {
        return Optional.ofNullable(this.getJdbcTemplate().queryForObject("SELECT DATABASE()", String.class)).orElse("mysql");
    }

    @DbPath(value = "/databases", seq = 0)
    public List<MySqlDatabase> listDatabases(){
        String sql =
                "SELECT SCHEMA_NAME, DEFAULT_CHARACTER_SET_NAME, DEFAULT_COLLATION_NAME \n" +
                "FROM INFORMATION_SCHEMA.SCHEMATA\n" +
                "WHERE SCHEMA_NAME NOT IN ('sys', 'mysql', 'information_schema', 'performance_schema')\n" +
                "ORDER BY SCHEMA_NAME";
        return convertResult(this.getJdbcTemplate().queryForList(sql), MySqlDatabase.class);
    }

    @Override
    public List<String> getSchemas() {
        String sql =
                "SELECT SCHEMA_NAME\n" +
                "FROM INFORMATION_SCHEMA.SCHEMATA\n" +
                "WHERE SCHEMA_NAME NOT IN ('sys', 'mysql', 'information_schema', 'performance_schema')\n" +
                "ORDER BY SCHEMA_NAME";
        return this.getJdbcTemplate().queryForList(sql, String.class);
    }

    @DbPath(value = "/databases/{databaseName}", seq = 1)
    public MySqlDatabase databaseDetail(String databaseName){
        String sql =
                "SELECT SCHEMA_NAME, DEFAULT_CHARACTER_SET_NAME, DEFAULT_COLLATION_NAME \n" +
                "FROM INFORMATION_SCHEMA.SCHEMATA \n" +
                "WHERE SCHEMA_NAME = ?";
        return convertResult(JdbcResultConvertor.getOne(this.getJdbcTemplate().queryForList(sql, databaseName)), MySqlDatabase.class);
    }


    private RowMapper<DbTableOrView> tableOrViewRowMapper = (rs, rowNum) -> {
        CommonDbTableOrView t = CommonDbTableOrView.builder()
                .dbType(this.getDbType())
                .schema(rs.getString("TABLE_SCHEMA"))
                .name(rs.getString("TABLE_NAME"))
                .comment(rs.getString("TABLE_COMMENT"))
                .view("VIEW".equals(rs.getString("TABLE_TYPE")))
                .build();
        t.setFullName(t.getSchema() + "." + t.getName());
        return t;
    };

    @Override
    public List<DbTableOrView> getAllTableOrViews(String schema) {
        schema = this.ifBlankGetDefaultSchema(schema);
        String sql =
                "SELECT TABLE_SCHEMA, TABLE_NAME, TABLE_COMMENT, TABLE_TYPE\n" +
                "FROM INFORMATION_SCHEMA.TABLES\n" +
                "WHERE TABLE_SCHEMA = ?\n" +
                "ORDER BY TABLE_TYPE, CREATE_TIME";
        return this.getJdbcTemplate().query(sql, tableOrViewRowMapper, schema);
    }

    @Override
    public List<DbTableOrView> getAllTableOrViewsByName(String schema, String name) {
        schema = this.ifBlankGetDefaultSchema(schema);
        String sql =
                "SELECT TABLE_SCHEMA, TABLE_NAME, TABLE_COMMENT, TABLE_TYPE\n" +
                        "FROM INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = ?\n" +
                        "and table_name like ?\n" +
                        "ORDER BY TABLE_TYPE, CREATE_TIME";
        return this.getJdbcTemplate().query(sql, tableOrViewRowMapper, schema, "%" +name+ "%");
    }

    @Override
    public DbTableOrView getTableOrView(String schema, String tableOrViewName) {
        schema = this.ifBlankGetDefaultSchema(schema);
        String sql =
                "SELECT TABLE_SCHEMA, TABLE_NAME, TABLE_COMMENT, TABLE_TYPE\n" +
                "FROM INFORMATION_SCHEMA.TABLES\n" +
                "WHERE TABLE_SCHEMA = ?\n" +
                "AND TABLE_NAME = ?\n";
        return JdbcResultConvertor.getOne(this.getJdbcTemplate().query(sql, tableOrViewRowMapper, schema, tableOrViewName));
    }

    @DbPath(value = "/databases/{databaseName}/tables", seq = 2)
    public List<MySqlTable> databaseTables(String databaseName){
        String sql =
                "SELECT TABLE_SCHEMA, TABLE_NAME, `ENGINE`, ROW_FORMAT, `AUTO_INCREMENT`, CREATE_TIME, TABLE_COLLATION, TABLE_COMMENT\n" +
                "FROM INFORMATION_SCHEMA.TABLES\n" +
                "WHERE TABLE_SCHEMA = ?\n" +
                "AND TABLE_TYPE = 'BASE TABLE'\n" +
                "ORDER BY CREATE_TIME";
        return convertResult(this.getJdbcTemplate().queryForList(sql, databaseName), MySqlTable.class);
    }


    @DbPath(value = "/databases/{databaseName}/tables/{tableName}", seq = 3)
    public MySqlTable table(String databaseName, String tableName){
        String sql =
                "SELECT TABLE_SCHEMA, TABLE_NAME, `ENGINE`, ROW_FORMAT, `AUTO_INCREMENT`, CREATE_TIME, TABLE_COLLATION, TABLE_COMMENT\n" +
                "FROM INFORMATION_SCHEMA.TABLES\n" +
                "WHERE TABLE_SCHEMA = ?\n" +
                "AND TABLE_NAME = ?\n" +
                "AND TABLE_TYPE = 'BASE TABLE'";
        return convertResult(JdbcResultConvertor.getOne(this.getJdbcTemplate().queryForList(sql, databaseName, tableName)), MySqlTable.class);
    }

    @DbPath(value = "/databases/{databaseName}/tables/{tableName}/columns", seq = 4)
    public List<MySqlColumn> tableColumns(String databaseName, String tableName){
        return this.getMySqlColumns(databaseName, tableName);
    }

    @DbPath(value = "/databases/{databaseName}/tables/{tableName}/ddl", seq = 5)
    public String tableDDL(String databaseName, String tableName){
        String sql = String.format("SHOW CREATE TABLE `%s`.`%s`", databaseName, tableName);
        return this.getJdbcTemplate().queryForObject(sql, (rs, rowNum) ->  rs.getString("Create Table"));
    }


    @DbPath(value = "/databases/{databaseName}/views", seq = 6)
    public List<MySqlView> databaseViews(String databaseName){
        String sql =
                "SELECT TABLE_SCHEMA, TABLE_NAME\n" +
                "FROM INFORMATION_SCHEMA.VIEWS\n" +
                "WHERE TABLE_SCHEMA = ?";
        return convertResult(this.getJdbcTemplate().queryForList(sql, databaseName), MySqlView.class);
    }


    @DbPath(value = "/databases/{databaseName}/views/{viewName}", seq = 7)
    public MySqlView view(String databaseName, String viewName){
        String sql =
                "SELECT TABLE_SCHEMA, TABLE_NAME\n" +
                "FROM INFORMATION_SCHEMA.VIEWS\n" +
                "WHERE TABLE_SCHEMA = ?\n" +
                "AND TABLE_NAME = ?";
        return convertResult(JdbcResultConvertor.getOne(this.getJdbcTemplate().queryForList(sql, databaseName, viewName)), MySqlView.class);
    }

    @DbPath(value = "/databases/{databaseName}/views/{viewName}/columns", seq = 8)
    public List<MySqlColumn> viewColumns(String databaseName, String viewName){
        return this.getMySqlColumns(databaseName, viewName);
    }


    @DbPath(value = "/databases/{databaseName}/views/{viewName}/viewSource", seq = 9)
    public String viewSource(String databaseName, String viewName){
        String sql = String.format("SHOW CREATE VIEW `%s`.`%s`", databaseName, viewName);
        return this.getJdbcTemplate().queryForObject(sql, (rs, rowNum) ->  rs.getString("Create View"));
    }


    @Override
    public List<DbColumn> getColumns(String schema, String tableOrViewName) {
        schema = this.ifBlankGetDefaultSchema(schema);
        return getMySqlColumns(schema, tableOrViewName).stream().map(DbColumn.class::cast).collect(Collectors.toList());
    }


    public List<MySqlColumn> getMySqlColumns(String databaseName, String tableOrViewName) {
        String sql =
                "SELECT TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, ORDINAL_POSITION, COLUMN_DEFAULT, IS_NULLABLE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, CHARACTER_OCTET_LENGTH, NUMERIC_PRECISION, NUMERIC_SCALE, DATETIME_PRECISION, COLUMN_TYPE, CHARACTER_SET_NAME, COLLATION_NAME, COLUMN_KEY, EXTRA, COLUMN_COMMENT \n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_SCHEMA = ?\n" +
                "AND TABLE_NAME = ?\n" +
                "ORDER BY ORDINAL_POSITION";
        return convertResult(this.getJdbcTemplate().queryForList(sql, databaseName, tableOrViewName), MySqlColumn.class);
    }
}
