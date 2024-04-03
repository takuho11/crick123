package cn.topcheer.halberd.app.dao.db.metadata;


import cn.topcheer.halberd.app.api.framework.db.DbPath;
import cn.topcheer.halberd.app.api.framework.vo.CommonDbTableOrView;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;
import cn.topcheer.halberd.app.api.framework.vo.oracle.OracleColumn;
import cn.topcheer.halberd.app.api.framework.vo.oracle.OracleSchema;
import cn.topcheer.halberd.app.api.framework.vo.oracle.OracleTable;
import cn.topcheer.halberd.app.api.framework.vo.oracle.OracleView;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.stream.Collectors;

import static cn.topcheer.halberd.app.dao.db.metadata.JdbcResultConvertor.convertResult;

public class OracleMetaData extends AbstractDbMetaData {


    @Override
    public String getCurrentSchema() {
        return this.getJdbcTemplate().queryForObject("SELECT SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA') FROM DUAL", String.class);
    }


    @Override
    public List<String> getSchemas() {
        return this.listSchemas().stream().map(OracleSchema::getName).collect(Collectors.toList());
    }

    /**
     * @return
     * @see <a href="http://www.oracle-wiki.net/premium:startdocsdefaultschemas#mgmt_view">Oracle 11g Default Schemas</a>
     */
    @DbPath(value = "/schemas", seq = 0)
    public List<OracleSchema> listSchemas(){
        String sql =
                "SELECT USERNAME, CREATED\n" +
                "FROM SYS.ALL_USERS\n" +
                "WHERE USERNAME NOT IN ('ANONYMOUS', 'APEX_030200', 'APEX_PUBLIC_USER', 'APPQOSSYS', 'BI', 'CTXSYS', 'DBSNMP', 'DIP', 'EXFSYS', 'FLOWS_FILES', 'HR', 'IX', 'MDDATA', 'MDSYS', 'MGMT_VIEW', 'OE', 'OLAPSYS', 'ORACLE_OCM', 'ORDDATA', 'ORDPLUGINS', 'ORDSYS', 'OUTLN', 'OWBSYS', 'OWBSYS_AUDIT', 'PM', 'SCOTT', 'SH', 'SI_INFORMTN_SCHEMA', 'SPATIAL_CSW_ADMIN_USR', 'SPATIAL_WFS_ADMIN_USR', 'SYS', 'SYSMAN', 'SYSTEM', 'WMSYS', 'XDB', 'XS$NULL') \n" +
                "ORDER BY USERNAME";
        return convertResult(this.getJdbcTemplate().queryForList(sql), OracleSchema.class);
    }

    @DbPath(value = "/schemas/{schemaName}", seq = 1)
    public OracleSchema schemaDetail(String schemaName){
        String sql =
                "SELECT USERNAME, CREATED\n" +
                "FROM SYS.ALL_USERS\n" +
                "WHERE USERNAME = ?";
        return convertResult(JdbcResultConvertor.getOne(this.getJdbcTemplate().queryForList(sql, schemaName)), OracleSchema.class);
    }

    private RowMapper<DbTableOrView> tableOrViewRowMapper = (rs, rowNum) -> {
        CommonDbTableOrView t = CommonDbTableOrView.builder()
                .dbType(this.getDbType())
                .schema(rs.getString("OWNER"))
                .name(rs.getString("OBJECT_NAME"))
                .comment(rs.getString("COMMENTS"))
                .view("VIEW".equals(rs.getString("OBJECT_TYPE")))
                .build();
        t.setFullName(t.getSchema() + "." + t.getName());
        return t;
    };

    @Override
    public List<DbTableOrView> getAllTableOrViews(String schema) {
        schema = this.ifBlankGetDefaultSchema(schema);
        String sql =
                "SELECT o.OWNER, o.OBJECT_NAME, o.OBJECT_TYPE, tc.COMMENTS\n" +
                "FROM SYS.ALL_OBJECTS o\n" +
                "LEFT JOIN SYS.ALL_TAB_COMMENTS tc ON o.OWNER = tc.OWNER AND o.OBJECT_NAME = tc.TABLE_NAME AND o.OBJECT_TYPE = tc.TABLE_TYPE\n" +
                "WHERE o.OWNER = ?\n" +
                "AND o.OBJECT_TYPE IN ('TABLE', 'VIEW')\n" +
                "ORDER BY o.OBJECT_TYPE, o.OBJECT_NAME";
        return this.getJdbcTemplate().query(sql, tableOrViewRowMapper, schema);
    }

    @Override
    public List<DbTableOrView> getAllTableOrViewsByName(String schema, String name) {
        schema = this.ifBlankGetDefaultSchema(schema);
        String sql =
                "SELECT o.OWNER, o.OBJECT_NAME, o.OBJECT_TYPE, tc.COMMENTS\n" +
                        "FROM SYS.ALL_OBJECTS o\n" +
                        "LEFT JOIN SYS.ALL_TAB_COMMENTS tc ON o.OWNER = tc.OWNER AND o.OBJECT_NAME = tc.TABLE_NAME AND o.OBJECT_TYPE = tc.TABLE_TYPE\n" +
                        "WHERE o.OWNER = ?\n" +
                        "AND o.OBJECT_TYPE IN ('TABLE', 'VIEW')\n" +
                        "and o.OBJECT_NAME like ?\n" +
                        "ORDER BY o.OBJECT_TYPE, o.OBJECT_NAME";
        return this.getJdbcTemplate().query(sql, tableOrViewRowMapper, schema, "%" +name+ "%");
    }

    @Override
    public DbTableOrView getTableOrView(String schema, String tableOrViewName) {
        schema = this.ifBlankGetDefaultSchema(schema);
        String sql =
                "SELECT o.OWNER, o.OBJECT_NAME, o.OBJECT_TYPE, tc.COMMENTS\n" +
                "FROM SYS.ALL_OBJECTS o\n" +
                "LEFT JOIN SYS.ALL_TAB_COMMENTS tc ON o.OWNER = tc.OWNER AND o.OBJECT_NAME = tc.TABLE_NAME AND o.OBJECT_TYPE = tc.TABLE_TYPE\n" +
                "WHERE o.OWNER = ?\n" +
                "AND o.OBJECT_TYPE IN ('TABLE', 'VIEW')\n" +
                "AND o.OBJECT_NAME = ?";
        return JdbcResultConvertor.getOne(this.getJdbcTemplate().query(sql, tableOrViewRowMapper, schema, tableOrViewName));
    }

    /**
     *
     * @param schemaName
     * @return
     * @see <a href="https://docs.oracle.com/cd/E24693_01/server.11203/e24448/statviews_1003.htm">ALL_TABLES vs ALL_ALL_TABLES</a>
     */
    @DbPath(value = "/schemas/{schemaName}/tables", seq = 2)
    public List<OracleTable> schemaTables(String schemaName){
        String sql =
                "SELECT t.OWNER, t.TABLE_NAME, t.LOGGING, t.BACKED_UP, t.NUM_ROWS, t.DROPPED, tc.COMMENTS\n" +
                "FROM SYS.ALL_TABLES t\n" +
                "LEFT JOIN SYS.ALL_TAB_COMMENTS tc ON t.OWNER = tc.OWNER AND t.TABLE_NAME = tc.TABLE_NAME AND tc.TABLE_TYPE = 'TABLE'\n" +
                "WHERE t.OWNER = ?\n" +
                "ORDER BY t.TABLE_NAME";
        return convertResult(this.getJdbcTemplate().queryForList(sql, schemaName), OracleTable.class);
    }


    @DbPath(value = "/schemas/{schemaName}/tables/{tableName}", seq = 3)
    public OracleTable table(String schemaName, String tableName){
        String sql =
                "SELECT t.OWNER, t.TABLE_NAME, t.LOGGING, t.BACKED_UP, t.NUM_ROWS, t.DROPPED, tc.COMMENTS\n" +
                "FROM SYS.ALL_TABLES t\n" +
                "LEFT JOIN SYS.ALL_TAB_COMMENTS tc ON t.OWNER = tc.OWNER AND t.TABLE_NAME = tc.TABLE_NAME AND tc.TABLE_TYPE = 'TABLE'\n" +
                "WHERE t.OWNER = ?\n" +
                "AND t.TABLE_NAME = ?";
        return convertResult(JdbcResultConvertor.getOne(this.getJdbcTemplate().queryForList(sql, schemaName, tableName)), OracleTable.class);
    }


    @DbPath(value = "/schemas/{schemaName}/tables/{tableName}/columns", seq = 4)
    public List<OracleColumn> tableColumns(String schemaName, String tableName){
        return this.getOracleColumns(schemaName, tableName);
    }


    @DbPath(value = "/schemas/{schemaName}/tables/{tableName}/ddl", seq = 5)
    public String tableDDL(String schemaName, String tableName){
        String sql = "SELECT DBMS_METADATA.GET_DDL('TABLE', ?, ?) FROM DUAL";
        try{
            return this.getJdbcTemplate().queryForObject(sql, String.class, tableName, schemaName);
        }catch (org.springframework.jdbc.UncategorizedSQLException e){
            if("99999".equals(e.getSQLException().getSQLState()) && 31603 == e.getSQLException().getErrorCode()){
                return "-- 表"+ tableName + "未能找到或者没有DBMS_METADATA的访问权限！";
            }else {
                throw e;
            }
        }
    }

    @DbPath(value = "/schemas/{schemaName}/views", seq = 6)
    public List<OracleView> schemaViews(String schemaName){
        String sql =
                "SELECT v.OWNER, v.VIEW_NAME, v.READ_ONLY, c.COMMENTS \n" +
                "FROM SYS.ALL_VIEWS v \n" +
                "LEFT JOIN SYS.ALL_TAB_COMMENTS c ON v.OWNER = c.OWNER AND v.VIEW_NAME = c.TABLE_NAME AND c.TABLE_TYPE = 'VIEW' \n" +
                "WHERE v.OWNER = ? \n" +
                "ORDER BY v.VIEW_NAME";
        return convertResult(this.getJdbcTemplate().queryForList(sql, schemaName), OracleView.class);
    }


    @DbPath(value = "/schemas/{schemaName}/views/{viewName}", seq = 7)
    public OracleView view(String schemaName, String viewName){
        String sql =
                "SELECT v.OWNER, v.VIEW_NAME, v.READ_ONLY, c.COMMENTS \n" +
                "FROM ALL_VIEWS v \n" +
                "LEFT JOIN SYS.ALL_TAB_COMMENTS c ON v.OWNER = c.OWNER AND v.VIEW_NAME = c.TABLE_NAME AND c.TABLE_TYPE = 'VIEW' \n" +
                "WHERE v.OWNER = ? \n" +
                "AND v.VIEW_NAME = ?";
        return convertResult(JdbcResultConvertor.getOne(this.getJdbcTemplate().queryForList(sql, schemaName, viewName)), OracleView.class);
    }

    @DbPath(value = "/schemas/{schemaName}/views/{viewName}/columns", seq = 8)
    public List<OracleColumn> viewColumns(String schemaName, String viewName){
        return this.getOracleColumns(schemaName, viewName);
    }


    @DbPath(value = "/schemas/{schemaName}/views/{viewName}/ddl", seq = 9)
    public String viewDDL(String schemaName, String viewName){
        String sql = "SELECT DBMS_METADATA.GET_DDL('VIEW', ?, ?) FROM DUAL";
        try{
            return this.getJdbcTemplate().queryForObject(sql, String.class, viewName, schemaName);
        }catch (org.springframework.jdbc.UncategorizedSQLException e){
            sql = "SELECT TEXT FROM SYS.ALL_VIEWS WHERE OWNER = ? AND VIEW_NAME = ?";
            return this.getJdbcTemplate().queryForObject(sql, String.class, schemaName, viewName);
        }
    }

    @Override
    public List<DbColumn> getColumns(String schemaName, String tableOrViewName) {
        schemaName = this.ifBlankGetDefaultSchema(schemaName);
        return getOracleColumns(schemaName, tableOrViewName).stream().map(DbColumn.class::cast).collect(Collectors.toList());
    }



    /**
     * @see <a href="https://docs.oracle.com/database/121/REFRN/GUID-85036F42-140A-406B-BE11-0AC49A00DBA3.htm#REFRN20276">ALL_TAB_COLS vs ALL_TAB_COLUMNS</a>
     */
    public List<OracleColumn> getOracleColumns(String schemaName, String tableOrViewName) {
        String sql =
                "SELECT \n" +
                    "CASE WHEN p.CONSTRAINT_NAME IS NOT NULL THEN 'YES' ELSE 'NO' END IS_PK, \n" +
                    "cm.COMMENTS, c.OWNER, c.TABLE_NAME, c.COLUMN_NAME, c.DATA_TYPE, c.DATA_LENGTH, c.DATA_PRECISION, c.DATA_SCALE, c.NULLABLE, c.COLUMN_ID, c.DEFAULT_LENGTH, c.DATA_DEFAULT, c.CHARACTER_SET_NAME, c.CHAR_COL_DECL_LENGTH, c.CHAR_LENGTH, c.CHAR_USED \n" +
                "FROM SYS.ALL_TAB_COLUMNS c \n" +
                "LEFT JOIN SYS.ALL_COL_COMMENTS cm ON c.OWNER = cm.OWNER AND c.TABLE_NAME = cm.TABLE_NAME AND c.COLUMN_NAME = cm.COLUMN_NAME \n" +
                "LEFT JOIN ( \n" +
                    "SELECT col.OWNER, col.TABLE_NAME, col.COLUMN_NAME, col.CONSTRAINT_NAME \n" +
                    "FROM SYS.ALL_CONSTRAINTS cons \n" +
                    "JOIN SYS.ALL_CONS_COLUMNS col ON cons.OWNER = col.OWNER AND cons.TABLE_NAME = col.TABLE_NAME AND cons.CONSTRAINT_NAME = col.CONSTRAINT_NAME \n" +
                    "WHERE cons.CONSTRAINT_TYPE = 'P' \n" +
                ") p ON c.OWNER = p.OWNER AND c.TABLE_NAME = p.TABLE_NAME AND c.COLUMN_NAME = p.COLUMN_NAME \n" +
                "WHERE c.OWNER = ? \n" +
                "AND c.TABLE_NAME = ? \n" +
                "ORDER BY c.COLUMN_ID";
        return convertResult(this.getJdbcTemplate().queryForList(sql, schemaName, tableOrViewName), OracleColumn.class);
    }
}
