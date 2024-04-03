package cn.topcheer.halberd.app.biz.sql.generator;


import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.dto.DbTableInfoDTO;
import cn.topcheer.halberd.app.api.framework.dto.dolphin.DataIntegrationDTO;
import cn.topcheer.halberd.app.api.framework.entity.dolphin.ColumnTransferInfo;
import cn.topcheer.halberd.app.api.framework.exception.DataCenterException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component(value = "sqlGeneratorForMysql")
public class SqlGeneratorForMysql extends AbstactSqlGenerator {


    // type
    private static final List<String> MYSQL_INT_TYPE = new ArrayList<>();
    // type(numericPrecision,decimalPlaces)
    private static final List<String> MYSQL_DECIMAL_TYPE = new ArrayList<>();
    // type(length)
    private static final List<String> MYSQL_CHAR_TYPE = new ArrayList<>();
    // type
    private static final List<String> MYSQL_TEXT_AND_DATE_TYPE = new ArrayList<>();
    // type(length)
    private static final List<String> MYSQL_BINARY_TYPE = new ArrayList<>();
    // type
    private static final List<String> MYSQL_LOB_TYPE = new ArrayList<>();


    static {
        MYSQL_INT_TYPE.add("bool");
        MYSQL_INT_TYPE.add("tinyint");
        MYSQL_INT_TYPE.add("smallint");
        MYSQL_INT_TYPE.add("mediumint");
        MYSQL_INT_TYPE.add("integer");
        MYSQL_INT_TYPE.add("int");
        MYSQL_INT_TYPE.add("bigint");

        MYSQL_DECIMAL_TYPE.add("decimal");
        MYSQL_DECIMAL_TYPE.add("float");
        MYSQL_DECIMAL_TYPE.add("double");

        MYSQL_CHAR_TYPE.add("char");
        MYSQL_CHAR_TYPE.add("varchar");

        MYSQL_TEXT_AND_DATE_TYPE.add("tinytext");
        MYSQL_TEXT_AND_DATE_TYPE.add("text");
        MYSQL_TEXT_AND_DATE_TYPE.add("mediumtext");
        MYSQL_TEXT_AND_DATE_TYPE.add("longtext");
        MYSQL_TEXT_AND_DATE_TYPE.add("year");
        MYSQL_TEXT_AND_DATE_TYPE.add("date");
        MYSQL_TEXT_AND_DATE_TYPE.add("time");
        MYSQL_TEXT_AND_DATE_TYPE.add("datetime");
        MYSQL_TEXT_AND_DATE_TYPE.add("timestamp");

        MYSQL_BINARY_TYPE.add("binary");
        MYSQL_BINARY_TYPE.add("bit");
        MYSQL_BINARY_TYPE.add("varbinary");

        MYSQL_LOB_TYPE.add("tinyblob");
        MYSQL_LOB_TYPE.add("blob");
        MYSQL_LOB_TYPE.add("mediumblob");
        MYSQL_LOB_TYPE.add("longblob");
    }


    private static final DataStoreType.Type sqlType =DataStoreType.Type.MYSQL ;
    private static final String CREATE_TABLE_PREX = "CREATE TABLE if not exists ";
    private static final String DEFAULT_ENGINE_STRING = "ENGINE=InnoDB ";
    private static final String DEFAULT_CHARSET_STRING = "DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ";
    private static final String DOT_CHAR = ".";
    private static final String END_CHAR = ";";
    private static final String SPACE_CHAR = " ";
    private static final String TRUNCATE = "truncate";

    @Override
    public String createTableSql(DataIntegrationDTO dataIntegrationDTO, int incLevel) {
        DbTableInfoDTO tableInfo = dataIntegrationDTO.getTargetTableInfo();
        List<ColumnTransferInfo> columnInfos = dataIntegrationDTO.getColumnTransferInfos();
        DataStoreType.Type dataStoreType = tableInfo.getDataStoreType();
        super.checkDataStoreType(dataStoreType,sqlType);
        String schema = tableInfo.getSchema();
        String tableName = tableInfo.getTableName();
        if("ods".equals(tableInfo.getDataLevel())){
            tableName = "ods_" + tableInfo.getBusinessDomain() + "_" + tableInfo.getTableName() + "_full";
        }
        String createSql = CREATE_TABLE_PREX
                + schema + DOT_CHAR + tableName + SPACE_CHAR
                + getColumnString(columnInfos) +
                DEFAULT_ENGINE_STRING + DEFAULT_CHARSET_STRING
                + getTableCommentString(tableInfo)
                + END_CHAR;
        return createSql;
    }



    @Override
    public String getTruncateTargetTableSQL( DbTableInfoDTO targetTableInfo) throws DataCenterException {
        String tableName = targetTableInfo.getTableName();
        if("ods".equals(targetTableInfo.getDataLevel())){
            tableName = "ods_" + targetTableInfo.getBusinessDomain() + "_" + targetTableInfo.getTableName() + "_full";
        }
        String TruncateSql = TRUNCATE + SPACE_CHAR + targetTableInfo.getSchema() + DOT_CHAR + tableName + END_CHAR;
        return TruncateSql;
    }

    @Override
    public String getDolphinDataxSQL(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException {

        DbTableInfoDTO targetTableInfo = dataIntegrationDTO.getTargetTableInfo();
        DbTableInfoDTO sourceTableInfo = dataIntegrationDTO.getSourceTableInfo();
        List<ColumnTransferInfo> columnTransferInfos = dataIntegrationDTO.getColumnTransferInfos();

        StringBuilder sb = new StringBuilder();

        for (ColumnTransferInfo columnTransferInfo : columnTransferInfos) {
            String sourceName = columnTransferInfo.getSourceName();
            String targetName = columnTransferInfo.getName();
            sb.append(sourceName).append(" as ").append(targetName).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));

        String sql = "select " +
                sb.toString()
                + " from " + sourceTableInfo.getSchema() + "." + sourceTableInfo.getTableName() + ";";

        return sql;
    }

    @Override
    public String getInsertSQLFromSToOds(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException {
        return "";
    }


    private String getTableCommentString(DbTableInfoDTO tableInfo) {
        if(StringUtils.isBlank(tableInfo.getComment())){
            return "";
        }
        return " COMMENT='" + tableInfo.getComment() + "' ";
    }


    private String getColumnString(List<ColumnTransferInfo> columnTransferInfos) {
        StringBuilder builder = new StringBuilder("( ");

        List<String> primaryKeyCol = new ArrayList<>();
        List<String> btreeCol = new ArrayList<>();

        for (ColumnTransferInfo columnTransferInfo : columnTransferInfos) {
            String name = columnTransferInfo.getName();
            String completeType = generateCompleteType(columnTransferInfo);
            if (columnTransferInfo.isPrimaryKey()) {
                primaryKeyCol.add(name);
            }
            if (columnTransferInfo.isKeyBtree()) {
                btreeCol.add(name);
            }
            builder.append("`").append(name).append("` ").append(completeType).append(" ");
            if (!columnTransferInfo.isNullable()) {
                builder.append("NOT NULL ");
            }
            if (!StringUtils.isBlank(columnTransferInfo.getComment())) {
                builder.append("COMMENT '").append(columnTransferInfo.getComment()).append("'");
            }
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        if (!primaryKeyCol.isEmpty()) {
            String primaryKeys = String.join(",", primaryKeyCol);
            builder.append(",").append("PRIMARY KEY (").append(primaryKeys).append(")");
        }
        if (!btreeCol.isEmpty()) {
            builder.append(",");
            for (String bkey : btreeCol) {
                builder.append("KEY ").append("index_").append(UUID.randomUUID().toString().replaceAll("-",""))
                        .append(" (").append(bkey).append(")  USING BTREE,");
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
        }
        builder.append(")");
        return builder.toString();
    }


    private String generateCompleteType(ColumnTransferInfo columnTransferInfo) {
        String type = columnTransferInfo.getType().toLowerCase();
        if (MYSQL_INT_TYPE.contains(type) || MYSQL_TEXT_AND_DATE_TYPE.contains(type) || MYSQL_LOB_TYPE.contains(type)) {
            return type;
        } else if (MYSQL_CHAR_TYPE.contains(type) || MYSQL_BINARY_TYPE.contains(type)) {
            long destLength = columnTransferInfo.getDestLength();
            return type + "(" + destLength + ")";
        } else if (MYSQL_DECIMAL_TYPE.contains(type)) {
            int destNumericPrecision = columnTransferInfo.getDestNumericPrecision();
            int destDecimalPlaces = columnTransferInfo.getDestDecimalPlaces();
            return type + "(" + destNumericPrecision + "," + destDecimalPlaces + ")";
        } else {
            return type;
        }
    }




}
