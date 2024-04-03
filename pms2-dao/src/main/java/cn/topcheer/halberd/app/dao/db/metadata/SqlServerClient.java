package cn.topcheer.halberd.app.dao.db.metadata;//package cn.topcheer.datacenter.apimanager.biz.impl.db.client;
//
//import cn.topcheer.datacenter.apimanager.api.annotation.db.DbPath;
//import cn.topcheer.datacenter.apimanager.api.db.vo.sqlserver.SqlServerDatabase;
//import cn.topcheer.datacenter.apimanager.api.db.vo.sqlserver.SqlServerSchema;
//import cn.topcheer.datacenter.apimanager.api.db.vo.sqlserver.SqlServerTable;
//import cn.topcheer.datacenter.apimanager.api.db.vo.sqlserver.SqlServerView;
//import cn.topcheer.datacenter.apimanager.biz.impl.db.AbstractDbClient;
//import com.baomidou.dynamic.datasource.annotation.DS;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@DS("datacenter")
//public class SqlServerClient extends AbstractDbClient{
//
//
//    /**
//     * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/system-catalog-views/sys-databases-transact-sql?view=sql-server-ver16">sys.databases (Transact-SQL)</a>
//     */
//    @DbPath(value = "/databases", seq = 0)
//    public List<SqlServerDatabase> databases(JdbcTemplate jdbcTemplate){
//        String sql =
//                "SELECT name, create_date, compatibility_level, collation_name, user_access_desc, is_read_only, state_desc, recovery_model_desc \n" +
//                "FROM sys.databases \n" +
//                "WHERE name NOT IN ('master','model','msdb','tempdb') \n" +
//                "ORDER BY name";
//        return convertResult(jdbcTemplate.queryForList(sql), SqlServerDatabase.class);
//    }
//
//    @DbPath(value = "/databases/{databaseName}", seq = 1)
//    public SqlServerDatabase databaseDetail(JdbcTemplate jdbcTemplate, String databaseName){
//        String sql =
//                "SELECT name, create_date, compatibility_level, collation_name, user_access_desc, is_read_only, state_desc, recovery_model_desc \n" +
//                "FROM sys.databases \n" +
//                "WHERE name = ?";
//        return convertResult(getMap(jdbcTemplate.queryForList(sql, databaseName)), SqlServerDatabase.class);
//    }
//
//
//    @DbPath(value = "/databases/{databaseName}/schemas", seq = 2)
//    public List<SqlServerSchema> schemas(JdbcTemplate jdbcTemplate, String databaseName){
//        String sql =
//                String.format(
//                "SELECT DISTINCT s.name, s.schema_id\n" +
//                "FROM [%s].sys.objects o\n" +
//                "JOIN [%s].sys.schemas s ON o.schema_id = s.schema_id\n" +
//                "WHERE s.name NOT IN ('sys', 'INFORMATION_SCHEMA', 'guest')\n" +
//                "ORDER BY s.schema_id", databaseName, databaseName);
//        return convertResult(jdbcTemplate.queryForList(sql), SqlServerSchema.class);
//    }
//
//
//    @DbPath(value = "/databases/{databaseName}/schemas/{schemaName}/tables", seq = 3)
//    public List<SqlServerTable> tables(JdbcTemplate jdbcTemplate, String databaseName, String schemaName){
//        String sql =
//                String.format(
//                "SELECT '%s' AS database_name, s.name AS schema_name, t.name, t.object_id, t.create_date, t.modify_date, ep.value AS comment \n" +
//                "FROM [%s].sys.tables t \n" +
//                "JOIN [%s].sys.schemas s ON t.schema_id = s.schema_id \n" +
//                "LEFT JOIN [%s].sys.extended_properties ep ON ep.class = 1 AND ep.major_id = t.object_id AND ep.minor_id = 0 AND ep.name='MS_Description' \n" +
//                "WHERE t.schema_id = ? \n" +
//                "AND t.type = 'U' \n" +
//                "ORDER BY t.name", databaseName, databaseName, databaseName, databaseName);
//        return convertResult(jdbcTemplate.queryForList(sql, schemaName), SqlServerTable.class);
//    }
//
//
//    @DbPath(value = "/databases/{databaseName}/schemas/{schemaId}/tables/{tableId}", seq = 3)
//    public SqlServerTable tableDetail(JdbcTemplate jdbcTemplate, String databaseName, Integer schemaId, Integer tableId){
//        String sql =
//                String.format(
//                "SELECT '%s' AS database_name, s.name AS schema_name, t.name, t.object_id, t.create_date, t.modify_date, ep.value AS comment \n" +
//                "FROM [%s].sys.tables t \n" +
//                "JOIN [%s].sys.schemas s ON t.schema_id = s.schema_id \n" +
//                "LEFT JOIN [%s].sys.extended_properties ep ON ep.class = 1 AND ep.major_id = t.object_id AND ep.minor_id = 0 AND ep.name='MS_Description' \n" +
//                "WHERE t.schema_id = ? \n" +
//                "AND t.type = 'U' \n" +
//                "AND t.object_id = ?", databaseName, databaseName, databaseName, databaseName);
//        return convertResult(getMap(jdbcTemplate.queryForList(sql, schemaId, tableId)), SqlServerTable.class);
//    }
//
//
//
//
//
//    @DbPath(value = "/databases/{databaseName}/schemas/{schemaId}/views", seq = 3)
//    public List<SqlServerView> views(JdbcTemplate jdbcTemplate, String databaseName, Integer schemaId){
//        String sql =
//                String.format(
//                "SELECT '%s' AS database_name, s.name AS schema_name, v.name, v.object_id, v.create_date, v.modify_date, ep.value AS comment \n" +
//                "FROM [%s].sys.views v \n" +
//                "JOIN [%s].sys.schemas s ON v.schema_id = s.schema_id \n" +
//                "LEFT JOIN [%s].sys.extended_properties ep ON ep.class = 1 AND ep.major_id = v.object_id AND ep.minor_id = 0 AND ep.name='MS_Description' \n" +
//                "WHERE v.schema_id = ? \n" +
//                "AND v.type = 'V' \n" +
//                "ORDER BY v.name", databaseName, databaseName, databaseName, databaseName);
//        return convertResult(jdbcTemplate.queryForList(sql, schemaId), SqlServerView.class);
//    }
//
//
//    @DbPath(value = "/databases/{databaseName}/schemas/{schemaId}/views/{viewId}", seq = 3)
//    public SqlServerView viewDetail(JdbcTemplate jdbcTemplate, String databaseName, Integer schemaId, Integer viewId){
//        String sql =
//                String.format(
//                "SELECT '%s' AS database_name, s.name AS schema_name, v.name, v.object_id, v.create_date, v.modify_date, ep.value AS comment \n" +
//                "FROM [%s].sys.views v \n" +
//                "JOIN [%s].sys.schemas s ON v.schema_id = s.schema_id \n" +
//                "LEFT JOIN [%s].sys.extended_properties ep ON ep.class = 1 AND ep.major_id = v.object_id AND ep.minor_id = 0 AND ep.name='MS_Description' \n" +
//                "WHERE v.schema_id = ? \n" +
//                "AND v.type = 'V' \n" +
//                "AND v.object_id = ?", databaseName, databaseName, databaseName, databaseName);
//        return convertResult(getMap(jdbcTemplate.queryForList(sql, schemaId, viewId)), SqlServerView.class);
//    }
//
//
//
//
//
//    @Override
//    protected boolean getColumnNameCaseIgnored() {
//        return false;
//    }
//}
