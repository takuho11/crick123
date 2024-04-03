package cn.topcheer.halberd.app.dao.db.metadata;

import cn.topcheer.halberd.app.api.framework.vo.CommonDbColumn;
import cn.topcheer.halberd.app.api.framework.vo.CommonDbTableOrView;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JdbcMetaData extends AbstractDbMetaData {

    private enum DbSchemaType{
        CATALOG, SCHEMA, BOTH
    }

    private DbSchemaType getDbSchemaType(DatabaseMetaData databaseMetaData) throws SQLException {
        boolean isCatalogAtStart = databaseMetaData.isCatalogAtStart();
        boolean supportsCatalogs = databaseMetaData.supportsCatalogsInDataManipulation();
        boolean supportsSchemas = databaseMetaData.supportsSchemasInDataManipulation();

        if(isCatalogAtStart && supportsCatalogs && supportsSchemas){
            return DbSchemaType.BOTH;
        } else if(isCatalogAtStart && supportsCatalogs){
            return DbSchemaType.CATALOG;
        }else if((!supportsCatalogs) && supportsSchemas){
            return DbSchemaType.SCHEMA;
        }else {
            throw new UnknownError ("不知道这是什么schema");
        }
    }

    private List<String> getCatalogs(DatabaseMetaData databaseMetaData) throws SQLException {
        return new RowMapperResultSetExtractor<>(
                (rs, rowNum) -> rs.getString("TABLE_CAT")
        ).extractData(databaseMetaData.getCatalogs());
    }

    private List<String> getSchemas(DatabaseMetaData databaseMetaData, String catalog) throws SQLException {
        ResultSet resultSet;
        if(StringUtils.isBlank(catalog)){
            resultSet = databaseMetaData.getSchemas();
        }else {
            resultSet = databaseMetaData.getSchemas(catalog, null);
        }
        return new RowMapperResultSetExtractor<>(
                (rs, rowNum) -> {
                    String cat = rs.getString("TABLE_CATALOG");
                    if(StringUtils.isBlank(cat) && StringUtils.isNotBlank(catalog)){
                        cat = catalog;
                    }
                    String schem = rs.getString("TABLE_SCHEM");
                    return Stream.of(cat, schem).filter(StringUtils::isNotBlank).collect(Collectors.joining("."));
                }
        ).extractData(resultSet);
    }

    @Override
    public List<String> getSchemas() {
        try(Connection connection = this.getJdbcTemplate().getDataSource().getConnection()){
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            DbSchemaType schemaType = getDbSchemaType(databaseMetaData);

            if(schemaType == DbSchemaType.CATALOG){
                return getCatalogs(databaseMetaData);
            } else if(schemaType == DbSchemaType.SCHEMA){
                return getSchemas(databaseMetaData, null);
            } else {
                List<String> catalogs = getCatalogs(databaseMetaData);
                List<String> result = new ArrayList<>();
                for (String cat: catalogs) {
                    result.addAll(getSchemas(databaseMetaData, cat));
                }
                return result;
            }
        }catch(SQLException e){
            throw new IllegalStateException("获取schemas失败", e);
        }
    }

    @Override
    public String getCurrentSchema() {
        throw new UnsupportedOperationException("未实现获取当前schema信息");
    }


    private String[] getCatalogAndSchema(DatabaseMetaData databaseMetaData, String schema) throws SQLException{
        if(StringUtils.isBlank(schema)){
            return new String[]{null, null};
        }
        String[] arr = schema.split("\\.");
        if(arr.length == 2){
            return arr;
        }
        DbSchemaType schemaType = getDbSchemaType(databaseMetaData);
        if(schemaType == DbSchemaType.CATALOG){
            return new String[]{schema, null};
        }else if(schemaType == DbSchemaType.SCHEMA){
            return new String[]{null, schema};
        }else {
            return new String[]{null, schema};//todo
        }
    }

    private RowMapper<DbTableOrView> tableOrViewRowMapper = (rs, rowNum) -> {
        String schem = Stream.of(
                rs.getString("TABLE_CAT"),
                rs.getString("TABLE_SCHEM")
        ).filter(StringUtils::isNotBlank).collect(Collectors.joining("."));
        String tableName = rs.getString("TABLE_NAME");
        return (DbTableOrView) CommonDbTableOrView.builder()
                .schema(schem)
                .name(tableName)
                .fullName(schem + "." + tableName)
                .comment(rs.getString("REMARKS"))
                .view("VIEW".equals(rs.getString("TABLE_TYPE")))
                .build();
    };

    @Override
    public List<DbTableOrView> getAllTableOrViews(String schema) {
        try(Connection connection = this.getJdbcTemplate().getDataSource().getConnection()){
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String[] schemaParams = getCatalogAndSchema(databaseMetaData, schema);
            return new RowMapperResultSetExtractor<>(tableOrViewRowMapper).extractData(databaseMetaData.getTables(schemaParams[0], schemaParams[1], "%", new String[]{"TABLE", "VIEW"}));
        }catch(SQLException e){
            throw new IllegalStateException("获取tables失败", e);
        }
    }

    @Override
    public List<DbTableOrView> getAllTableOrViewsByName(String schema, String name) {
        try(Connection connection = this.getJdbcTemplate().getDataSource().getConnection()){
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String[] schemaParams = getCatalogAndSchema(databaseMetaData, schema);
            return new RowMapperResultSetExtractor<>(tableOrViewRowMapper).extractData(databaseMetaData.getTables(schemaParams[0], schemaParams[1], "%"+name+"%", new String[]{"TABLE", "VIEW"}));
        }catch(SQLException e){
            throw new IllegalStateException("获取tables失败", e);
        }
    }


    @Override
    public DbTableOrView getTableOrView(String schema, String tableOrViewName) {
        try(Connection connection = this.getJdbcTemplate().getDataSource().getConnection()){
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String[] schemaParams = getCatalogAndSchema(databaseMetaData, schema);
            return JdbcResultConvertor.getOne(new RowMapperResultSetExtractor<>(tableOrViewRowMapper).extractData(databaseMetaData.getTables(schemaParams[0], schemaParams[1], tableOrViewName, new String[]{"TABLE", "VIEW"})));
        }catch(SQLException e){
            throw new IllegalStateException("获取tables失败", e);
        }
    }

    @Override
    public List<DbColumn> getColumns(String schema, String tableOrViewName) {
        try(Connection connection = this.getJdbcTemplate().getDataSource().getConnection()){
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String[] schemaParams = getCatalogAndSchema(databaseMetaData, schema);
            return new RowMapperResultSetExtractor<>(
                    (rs, rowNum) -> {
                        String schem = Stream.of(
                                rs.getString("TABLE_CAT"),
                                rs.getString("TABLE_SCHEM")
                        ).filter(StringUtils::isNotBlank).collect(Collectors.joining("."));
                        String tn = rs.getString("TABLE_NAME");
                        Boolean nullable = null;
                        String nullableStr = rs.getString("IS_NULLABLE");
                        if("YES".equals(nullableStr)){
                            nullable = true;
                        } else if ("NO".equals(nullableStr)) {
                            nullable = false;
                        }
                        return (DbColumn) CommonDbColumn.builder()
                                .schema(schem)
                                .tableName(tn)
                                .tableFullName(schem + "." + tn)
                                .columnName(rs.getString("COLUMN_NAME"))
                                .dataType(rs.getString("TYPE_NAME"))
                                .columnType(rs.getString("TYPE_NAME"))
                                .jdbcType(rs.getInt("DATA_TYPE"))
                                .charLength(rs.getLong("COLUMN_SIZE"))
                                .numericPrecision(rs.getInt("COLUMN_SIZE"))//todo
                                .numericScale(rs.getInt("DECIMAL_DIGITS"))
                                .nullable(nullable)
                                .comment(rs.getString("REMARKS"))
                                .build();
                    }
            ).extractData(databaseMetaData.getColumns(schemaParams[0], schemaParams[1], tableOrViewName, "%"));
        }catch(SQLException e){
            throw new IllegalStateException("获取tables失败", e);
        }
    }

}
