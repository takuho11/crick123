package cn.topcheer.halberd.app.biz.sql.generator;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.dto.DbTableInfoDTO;
import cn.topcheer.halberd.app.api.framework.dto.dolphin.DataIntegrationDTO;
import cn.topcheer.halberd.app.api.framework.entity.dolphin.ColumnTransferInfo;
import cn.topcheer.halberd.app.api.framework.exception.DataCenterException;
import cn.topcheer.halberd.app.biz.config.DorisConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static cn.topcheer.halberd.app.api.framework.ResultEnum.INC_COLUMN_TYPE_NOT_SUPPORT;
import static cn.topcheer.halberd.app.api.framework.ResultEnum.INC_TYPE_NOT_SUPPORT;


@Component(value = "sqlGeneratorForDoris")
public class SqlGeneratorForDoris extends AbstactSqlGenerator {


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

    private static final Map<String, String> TYPE_TO_DORIS = new HashMap<>();


    static {
        TYPE_TO_DORIS.put("boolean", "tinyint");
        TYPE_TO_DORIS.put("bool", "tinyint");
        TYPE_TO_DORIS.put("mediumint", "bigint");
        TYPE_TO_DORIS.put("integer", "int");

        TYPE_TO_DORIS.put("tinytext", "string");
        TYPE_TO_DORIS.put("text", "string");
        TYPE_TO_DORIS.put("mediumtext", "string");
        TYPE_TO_DORIS.put("longtext", "string");

        TYPE_TO_DORIS.put("binary", "string");
        TYPE_TO_DORIS.put("bit", "string");
        TYPE_TO_DORIS.put("varbinary", "string");

        TYPE_TO_DORIS.put("tinyblob", "string");
        TYPE_TO_DORIS.put("blob", "string");
        TYPE_TO_DORIS.put("mediumblob", "string");
        TYPE_TO_DORIS.put("longblob", "string");

        TYPE_TO_DORIS.put("timestamp", "datetime");
        TYPE_TO_DORIS.put("time", "datetime");
        TYPE_TO_DORIS.put("year", "date");

        MYSQL_INT_TYPE.add("bool");
        MYSQL_INT_TYPE.add("tinyint");
        MYSQL_INT_TYPE.add("smallint");
        MYSQL_INT_TYPE.add("mediumint");
        MYSQL_INT_TYPE.add("integer");
        MYSQL_INT_TYPE.add("int");
        MYSQL_INT_TYPE.add("bigint");
        MYSQL_INT_TYPE.add("float");
        MYSQL_INT_TYPE.add("double");

        MYSQL_DECIMAL_TYPE.add("decimal");

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


    private final DataStoreType.Type sqlType = DataStoreType.Type.DORIS;

    private static final String CREATE_TABLE_PREX = "CREATE TABLE if not exists ";
    private static final String DEFAULT_PROPERTIES_STRING =
            " PROPERTIES (\n" +
                    "\"replication_allocation\" = \"tag.location.default: " +
                    "${replicationNum}" +
                    "\",\n" +
                    "\"in_memory\" = \"false\",\n" +
                    "\"storage_format\" = \"V2\",\n" +
                    "\"disable_auto_compaction\" = \"false\"\n" +
                    ") ";
    private static final String DEFAULT_PROPERTIES_STRING_WITH_PARTITION_MONTH =
            " PROPERTIES\n" +
                    "(\n" +
                    "  \"dynamic_partition.enable\" = \"true\",\n" +
                    "  \"dynamic_partition.time_unit\" = \"month\",\n" +
                    "  \"dynamic_partition.replication_num\" = \"" +
                    "${replicationNum}" +
                    "\",\n" +
                    "  \"dynamic_partition.start\" = \"-7\",\n" +
                    "  \"dynamic_partition.end\" = \"7\",\n" +
                    "  \"dynamic_partition.create_history_partition\" = \"true\",\n" +
                    "  \"dynamic_partition.history_partition_num\" = \"7\",\n" +
                    "  \"dynamic_partition.prefix\" = \"p\",\n" +
                    "  \"dynamic_partition.buckets\" = \"4\",\n" +
                    "  \"replication_num\" = \"" +
                    "${replicationNum}" +
                    "\"\n" +
                    ")";
    private static final String DEFAULT_PROPERTIES_STRING_WITH_PARTITION_DAY =
            " PROPERTIES\n" +
                    "(\n" +
                    "  \"dynamic_partition.enable\" = \"true\",\n" +
                    "  \"dynamic_partition.time_unit\" = \"day\",\n" +
                    "  \"dynamic_partition.replication_num\" = \"" +
                    "${replicationNum}" +
                    "\",\n" +
                    "  \"dynamic_partition.start\" = \"-7\",\n" +
                    "  \"dynamic_partition.end\" = \"7\",\n" +
                    "  \"dynamic_partition.create_history_partition\" = \"true\",\n" +
                    "  \"dynamic_partition.history_partition_num\" = \"7\",\n" +
                    "  \"dynamic_partition.prefix\" = \"p\",\n" +
                    "  \"dynamic_partition.buckets\" = \"4\",\n" +
                    "  \"replication_num\" = \"" +
                    "${replicationNum}" +
                    "\"\n" +
                    ")";
    private static final String DEFAULT_DISTRIBUTION_PRE_STRING = "DISTRIBUTED BY HASH(";
    private static final String DEFAULT_DISTRIBUTION_PRO_STRING = ") BUCKETS 4 ";
    private static final String END_CHAR = ";";
    private static final String SPACE_CHAR = " ";
    private static final String LINE_BREAK = " \n";

    private static final String TRUNCATE = "truncate";
    private static final String DOT_CHAR = ".";


    private static final String ODS_INSERT_SQL_TEMPLATE = "INSERT INTO `${odsSchema}`.`${odsTable}` ( \n" +
            "${insertColList} )\n" +
            "SELECT \n ${columnMap} FROM `${odsSchema}`.`${odsTable}`\n" +
            "WHERE ${tz_ptime} = (\n" +
            "    SELECT MAX(${tz_ptime}) FROM `${odsSchema}`.`${odsTable}`\n" +
            ");\n" +
            "INSERT INTO `${odsSchema}`.`${odsTable}` \n" +
            "SELECT * FROM `${sSchema}`.`${sTable}` AS t1\n" +
            "WHERE ${tz_ptime} = (\n" +
            "    SELECT MAX(${tz_ptime}) FROM `${sSchema}`.`${sTable}`\n" +
            ");";


    @Autowired
    DorisConfig dorisConfig;

    //    参数 incLevel:
//            是否是增量: 0=不是增量情况 ,1=s ,2=ods
//            s层需要duplicated模型，id列，增量时间列，tz_ptime列作为排序列。
//            ods层建议unique模型，id列，增量时间列，tz_ptime列作为key序列。
//            两层的列名应当是一样的。
    @Override
    public String createTableSql(DataIntegrationDTO dataIntegrationDTO, int incLevel) {
        DbTableInfoDTO tableInfo = dataIntegrationDTO.getTargetTableInfo();
        List<ColumnTransferInfo> columnInfos = dataIntegrationDTO.getColumnTransferInfos();
        DataStoreType.Type dataStoreType = tableInfo.getDataStoreType();
        super.checkDataStoreType(dataStoreType, sqlType);

        if (0 == incLevel) {
            // unique key
            return createFullTableSql(tableInfo, columnInfos);
        } else if (1 == incLevel) {
            // 分区周期
            String targetPartitionCycle = dataIntegrationDTO.getTargetPartitionCycle();
            // 基准字段
            String sourceIncColumn = dataIntegrationDTO.getSourceIncColumn();
            // duplicate
            return createIncSTableSql(tableInfo, columnInfos, targetPartitionCycle, sourceIncColumn);
        } else if (2 == incLevel) {
            // 分区周期
            String targetPartitionCycle = dataIntegrationDTO.getTargetPartitionCycle();
            // 基准字段
            String sourceIncColumn = dataIntegrationDTO.getSourceIncColumn();
            // unique key 有 tz_ptime
            return createIncOdsTableSql(tableInfo, columnInfos, targetPartitionCycle, sourceIncColumn);
        }
        throw new DataCenterException(INC_TYPE_NOT_SUPPORT.code);
    }

    @Override
    public String getTruncateTargetTableSQL(DbTableInfoDTO targetTableInfo) throws DataCenterException {
        String tableName = targetTableInfo.getTableName();
        if ("ods".equals(targetTableInfo.getDataLevel())) {
            tableName = "ods_" + targetTableInfo.getBusinessDomain() + "_" + targetTableInfo.getTableName() + "_full";
        }
        String TruncateSql = TRUNCATE + SPACE_CHAR + "TABLE" + SPACE_CHAR
                + targetTableInfo.getSchema() + DOT_CHAR + tableName + END_CHAR;
        return TruncateSql;
    }


    @Override
    public String getDolphinDataxSQL(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException {
        boolean incSync = dataIntegrationDTO.isIncSync();
        String targetPartitionCycle = dataIntegrationDTO.getTargetPartitionCycle();
        DbTableInfoDTO sourceTableInfo = dataIntegrationDTO.getSourceTableInfo();
        List<ColumnTransferInfo> columnTransferInfos = dataIntegrationDTO.getColumnTransferInfos();

        StringBuilder sb = new StringBuilder();
        if (incSync) {
            if ("month".equals(targetPartitionCycle)) {
                sb.append("STR_TO_DATE(CONCAT(date_format(curdate(), '%Y%m' ),'01') ,'%Y%m%d') as tz_ptime_m ,");
            } else {
                sb.append("curdate() as tz_ptime_d ,");
            }
        }

        for (ColumnTransferInfo columnTransferInfo : columnTransferInfos) {
            String sourceName = columnTransferInfo.getSourceName();
            String targetName = columnTransferInfo.getName();
            sb.append(sourceName).append(" as ").append(targetName).append(",").append(LINE_BREAK);
        }
        sb.deleteCharAt(sb.lastIndexOf(","));

        String sql = "select " + LINE_BREAK +
                sb.toString()
                + " from " + LINE_BREAK
                + sourceTableInfo.getSchema() + "." + sourceTableInfo.getTableName();
        String where = "";
        if (incSync) {
            String sourceIncColumn = dataIntegrationDTO.getSourceIncColumn();
            String sourceIncColumnTimeType = dataIntegrationDTO.getSourceIncColumnTimeType();
            if ("datetime".equals(sourceIncColumnTimeType)) {
                if ("month".equals(targetPartitionCycle)) {
                    where = " WHERE " + sourceIncColumn + " >= STR_TO_DATE(CONCAT('${startFrom}','-01'), '%Y-%m-%d')"
                            + LINE_BREAK + "  AND " + sourceIncColumn + "< STR_TO_DATE(CONCAT('${endWithout}','-01'), '%Y-%m-%d')";
                } else {
                    where = " WHERE " + sourceIncColumn + " >= STR_TO_DATE('${startFrom}', '%Y-%m-%d ')"
                            + LINE_BREAK + "  AND " + sourceIncColumn + "< STR_TO_DATE('${endWithout}', '%Y-%m-%d')";
                }
            } else if ("string".equals(sourceIncColumnTimeType)) {
                where = " WHERE CONCAT( " + sourceIncColumn + ",\"15\") >= CONCAT('${startFrom}',\"01\")"
                        + LINE_BREAK + " AND CONCAT(" + sourceIncColumn + ",\"15\") < CONCAT('${endWithout}',\"01\")";
            } else {
                throw new DataCenterException(INC_COLUMN_TYPE_NOT_SUPPORT.code);
            }
        }
        return sql + LINE_BREAK + where + END_CHAR;
    }

    @Override
    public String getInsertSQLFromSToOds(DataIntegrationDTO dataIntegrationDTO) throws DataCenterException {
        String targetPartitionCycle = dataIntegrationDTO.getTargetPartitionCycle();
        DbTableInfoDTO sourceTableInfo = dataIntegrationDTO.getSourceTableInfo();
        DbTableInfoDTO targetTableInfo = dataIntegrationDTO.getTargetTableInfo();
        String sTable = "s_" + targetTableInfo.getBusinessDomain() + "_" + targetTableInfo.getTableName() + "_inc_" + targetPartitionCycle;
        String odsTable = targetTableInfo.getTableName();
        if ("ods".equals(targetTableInfo.getDataLevel())) {
            odsTable = "ods_" + targetTableInfo.getBusinessDomain() + "_" + targetTableInfo.getTableName() + "_inc_" + targetPartitionCycle;
        }
        String sSchema = targetTableInfo.getSchema();
        String odsSchema = targetTableInfo.getSchema();
        String tzPtime = "tz_ptime_" + targetPartitionCycle.substring(0, 1);
        String result = ODS_INSERT_SQL_TEMPLATE.replace("${tz_ptime}", tzPtime);
        result = result.replace("${sSchema}", sSchema);
        result = result.replace("${sTable}", sTable);
        result = result.replace("${odsSchema}", odsSchema);
        result = result.replace("${odsTable}", odsTable);
        String s2OdsMapString = getS2OdsMapString(dataIntegrationDTO);
        result = result.replace("${columnMap}", s2OdsMapString);
        String insertColList = getS2OdsInsertColListString(dataIntegrationDTO);
        result = result.replace("${insertColList}", insertColList);
        return result;
    }


    private String getS2OdsMapString(DataIntegrationDTO dataIntegrationDTO) {
        List<ColumnTransferInfo> columnTransferInfos = dataIntegrationDTO.getColumnTransferInfos();
        boolean month = dataIntegrationDTO.getTargetPartitionCycle().startsWith("m");
        String tzPtime = "tz_ptime_" + dataIntegrationDTO.getTargetPartitionCycle().substring(0, 1);
        StringBuilder sb = new StringBuilder();
        if (month) {
            sb.append("CAST (CONCAT(LEFT(CURDATE(),8),'01')  AS  date) AS ").append(tzPtime);
        } else {
            sb.append("CURDATE() AS ").append(tzPtime);
        }
        sb.append(",").append(LINE_BREAK);
        for (ColumnTransferInfo columnTransferInfo : columnTransferInfos) {
            sb.append(columnTransferInfo.getName()).append(" AS ").append(columnTransferInfo.getName()).append(",").append(LINE_BREAK);
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    private String getS2OdsInsertColListString(DataIntegrationDTO dataIntegrationDTO) {
        List<ColumnTransferInfo> columnTransferInfos = dataIntegrationDTO.getColumnTransferInfos();
        boolean month = dataIntegrationDTO.getTargetPartitionCycle().startsWith("m");
        String tzPtime = "tz_ptime_" + dataIntegrationDTO.getTargetPartitionCycle().substring(0, 1);
        StringBuilder sb = new StringBuilder();
        sb.append(tzPtime).append(",").append(LINE_BREAK);
        for (ColumnTransferInfo columnTransferInfo : columnTransferInfos) {
            sb.append(columnTransferInfo.getName()).append(",").append(LINE_BREAK);
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }


    private String createFullTableSql(DbTableInfoDTO tableInfo, List<ColumnTransferInfo> columnInfos) {
        String schema = tableInfo.getSchema();
        String tableName = tableInfo.getTableName();
        if ("ods".equals(tableInfo.getDataLevel())) {
            tableName = "ods_" + tableInfo.getBusinessDomain() + "_" + tableInfo.getTableName() + "_full";
        }
        String createSql = CREATE_TABLE_PREX
                + schema + "." + tableName + SPACE_CHAR
                + getColumnStringForFull(columnInfos, getTableCommentString(tableInfo))
                + DEFAULT_PROPERTIES_STRING.replace("${replicationNum}", dorisConfig.getReplicationNum()) + END_CHAR;
        return createSql + LINE_BREAK;
    }


    private String createIncSTableSql(DbTableInfoDTO tableInfo, List<ColumnTransferInfo> columnInfos,
                                      String targetPartitionCycle, String sourceIncColumn) {
        String schema = tableInfo.getSchema();
        String tableName = "s_" + tableInfo.getBusinessDomain() + "_" + tableInfo.getTableName() + "_inc_" + targetPartitionCycle;
        String createSql = CREATE_TABLE_PREX
                + schema + "." + tableName + SPACE_CHAR
                + getColumnStringForS(columnInfos, getTableCommentString(tableInfo), targetPartitionCycle, sourceIncColumn)
                + DEFAULT_PROPERTIES_STRING.replace("${replicationNum}", dorisConfig.getReplicationNum()) + END_CHAR;
        return createSql + LINE_BREAK;
    }

    private String createIncOdsTableSql(DbTableInfoDTO tableInfo, List<ColumnTransferInfo> columnInfos,
                                        String targetPartitionCycle, String sourceIncColumn) {
        String schema = tableInfo.getSchema();
        String tableName = tableInfo.getTableName();
        if ("ods".equals(tableInfo.getDataLevel())) {
            tableName = "ods_" + tableInfo.getBusinessDomain() + "_" + tableInfo.getTableName() + "_inc_" + targetPartitionCycle;
        }
        String createSql = CREATE_TABLE_PREX
                + schema + "." + tableName + SPACE_CHAR
                + getColumnStringForODS(columnInfos, getTableCommentString(tableInfo),
                targetPartitionCycle, sourceIncColumn);

        if ("month".equals(targetPartitionCycle)) {
            createSql = createSql + DEFAULT_PROPERTIES_STRING_WITH_PARTITION_MONTH.replace("${replicationNum}", dorisConfig.getReplicationNum()) + END_CHAR;
        } else {
            createSql = createSql + DEFAULT_PROPERTIES_STRING_WITH_PARTITION_DAY.replace("${replicationNum}", dorisConfig.getReplicationNum()) + END_CHAR;
        }
        return createSql + LINE_BREAK;
    }


    private String getTableCommentString(DbTableInfoDTO tableInfo) {
        if (StringUtils.isBlank(tableInfo.getComment())) {
            return "";
        }
        return " COMMENT '" + tableInfo.getComment() + "' ";
    }


    private String getColumnStringForFull(List<ColumnTransferInfo> columnTransferInfos, String tableComment) {


        Map<String, ColumnTransferInfo> colMap = columnTransferInfos.stream().collect(Collectors.toMap(ColumnTransferInfo::getName, x -> x));
        StringBuilder builder = new StringBuilder("( \n");

        List<String> primaryKeyCol = new ArrayList<>();
        List<String> valueCol = new ArrayList<>();

        for (ColumnTransferInfo columnTransferInfo : columnTransferInfos) {
            String name = columnTransferInfo.getName();
            if (columnTransferInfo.isPrimaryKey()) {
                primaryKeyCol.add(name);
            } else {
                valueCol.add(name);
            }
        }

        for (String column : primaryKeyCol) {
            ColumnTransferInfo columnTransferInfo = colMap.get(column);
            String completeType = generateCompleteType(columnTransferInfo);
            builder.append("`").append(columnTransferInfo.getName()).append("` ").append(completeType).append(SPACE_CHAR);
            if (!columnTransferInfo.isNullable()) {
                builder.append("NOT NULL ");
            }
            if (!StringUtils.isBlank(columnTransferInfo.getComment())) {
                builder.append("COMMENT '").append(columnTransferInfo.getComment()).append("'");
            }
            builder.append(",").append(LINE_BREAK);
        }
        if (valueCol.isEmpty()) {
            builder.deleteCharAt(builder.lastIndexOf(","));
        } else {
            for (String column : valueCol) {
                ColumnTransferInfo columnTransferInfo = colMap.get(column);
                String completeType = generateCompleteType(columnTransferInfo);
                builder.append("`").append(columnTransferInfo.getName()).append("` ").append(completeType).append(SPACE_CHAR);
                if (!columnTransferInfo.isNullable()) {
                    builder.append("NOT NULL ");
                }
                if (!StringUtils.isBlank(columnTransferInfo.getComment())) {
                    builder.append("COMMENT '").append(columnTransferInfo.getComment()).append("'");
                }
                builder.append(",").append(LINE_BREAK);
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
        }
        builder.append(")").append(LINE_BREAK);

        if (!primaryKeyCol.isEmpty()) {
            String primaryKeys = String.join(",", primaryKeyCol);
            builder.append("UNIQUE KEY (").append(primaryKeys).append(") ").append(LINE_BREAK);
            builder.append(tableComment).append(SPACE_CHAR).append(LINE_BREAK);
            builder.append(DEFAULT_DISTRIBUTION_PRE_STRING).append(primaryKeys).append(DEFAULT_DISTRIBUTION_PRO_STRING).append(LINE_BREAK);
        } else {
            String s = valueCol.get(0);
            builder.append("DUPLICATE KEY (").append(s).append(") ").append(LINE_BREAK);
            builder.append(tableComment).append(SPACE_CHAR).append(LINE_BREAK);
            builder.append(DEFAULT_DISTRIBUTION_PRE_STRING).append(s).append(DEFAULT_DISTRIBUTION_PRO_STRING).append(LINE_BREAK);
        }

        return builder.toString();
    }


//    用户
//
//            基准字段的
//    名称
//    类型 datetime？  string  -》 yyyyMMdd
//
//            分区的周期
//
//
//    SELECT
//    STR_TO_DATE(CONCAT(date_format(curdate(), '%Y%m' ),'01') ,'%Y%m%d') as tz_ptime_m;
//
//
//    SELECT
//    curdate() as tz_ptime_d;
//
//-- -------------------------------
//    基准字段  字符串
//
//    startFrom
//    endWithout 两个参数  格式同于字符串即可 如：[yyyy-MM-dd] 令用户选择     -> 影响两个参数作为全局参数的赋值。
//
//            // 分隔符  空 -  其他太特殊的不保证没问题 工作流去全局参数 统一是这个
//
//
//            [yyyyMM] [yyyy-MM-dd]
//
//    分区周期
//
//    字符串 月  格式:[yyyyMM]
//    WHERE
//    CONCAT(period,"15") >= CONCAT('${startFrom}',"01")
//    and
//    CONCAT(period,"15") < CONCAT('${endWithout}',"01")
//
//
//    字符串 日  格式:[yyyyMMdd]
//    WHERE
//    CONCAT(period,"15") >= CONCAT('${startFrom}','01')
//    and
//    CONCAT(period,"15") < CONCAT('${endWithout}','01')
//
//    相同
//
//-- -------------------------------------------------
//
//    基准字段 datetime 日 [yyyy-MM-dd]
//    startFrom
//    endWithout 两个参数  格式  [yyyy-MM-dd]
//
//    WHERE
//    period >= STR_TO_DATE('${startFrom}', '%Y-%m-%d %H:%i:%s')
//    AND
// period < STR_TO_DATE('${endWithout}', '%Y-%m-%d %H:%i:%s');
//
//
//    基准字段 datetime 月 [yyyy-MM-dd]
//    startFrom
//    endWithout 两个参数  格式  [yyyy-MM]
//
//    WHERE
//    period >= STR_TO_DATE(CONCAT('${startFrom}','-01'), '%Y-%m-%d %H:%i:%s')
//    AND
// period < STR_TO_DATE(CONCAT('${endWithout}','-01'), '%Y-%m-%d %H:%i:%s');
//
//    类似
//
//
//
//-- ------------------------------------------------
//    SELECT
//    STR_TO_DATE(CONCAT('${startFrom}','01'), '%Y%m%d %H:%i:%s');
//
//    SELECT
//    CONCAT('${startFrom}','-01');
//
//
//    SELECT  '你' < '我'


    private String getColumnStringForS(List<ColumnTransferInfo> columnTransferInfos,
                                       String tableComment, String targetPartitionCycle, String sourceIncColumn) {


        Map<String, ColumnTransferInfo> colMap = columnTransferInfos.stream().collect(Collectors.toMap(ColumnTransferInfo::getName, x -> x));
        StringBuilder builder = new StringBuilder("( \n");

        Set<String> primaryKeyCol = new LinkedHashSet<>();
        Set<String> valueCol = new LinkedHashSet<>();

        for (ColumnTransferInfo columnTransferInfo : columnTransferInfos) {
            String name = columnTransferInfo.getName();
            if (columnTransferInfo.isPrimaryKey()) {
                primaryKeyCol.add(name);
            } else {
                valueCol.add(name);
            }
        }

        String tzPtime = "tz_ptime_" + targetPartitionCycle.substring(0, 1);
        primaryKeyCol.add(tzPtime);
        primaryKeyCol.add(sourceIncColumn);
        valueCol.remove(tzPtime);
        valueCol.remove(sourceIncColumn);
        for (String column : primaryKeyCol) {
            ColumnTransferInfo columnTransferInfo = colMap.get(column);
            if (null == columnTransferInfo) {
                // 添加内部字段 如 tz_ptime
                builder.append("`").append(column).append("` ").append("date NOT NULL,").append(LINE_BREAK);
                continue;
            }

            String completeType = generateCompleteType(columnTransferInfo);
            builder.append("`").append(columnTransferInfo.getName()).append("` ").append(completeType).append(SPACE_CHAR);
            if (!columnTransferInfo.isNullable()) {
                builder.append("NOT NULL ");
            }
            if (!StringUtils.isBlank(columnTransferInfo.getComment())) {
                builder.append("COMMENT '").append(columnTransferInfo.getComment()).append("'");
            }
            builder.append(",").append(LINE_BREAK);
        }
        if (valueCol.isEmpty()) {
            builder.deleteCharAt(builder.lastIndexOf(","));
        } else {
            for (String column : valueCol) {
                ColumnTransferInfo columnTransferInfo = colMap.get(column);
                String completeType = generateCompleteType(columnTransferInfo);
                builder.append("`").append(columnTransferInfo.getName()).append("` ").append(completeType).append(SPACE_CHAR);
                if (!columnTransferInfo.isNullable()) {
                    builder.append("NOT NULL ");
                }
                if (!StringUtils.isBlank(columnTransferInfo.getComment())) {
                    builder.append("COMMENT '").append(columnTransferInfo.getComment()).append("'");
                }
                builder.append(",").append(LINE_BREAK);
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
        }
        builder.append(")").append(LINE_BREAK);

        String primaryKeys = String.join(",", primaryKeyCol);
        builder.append("DUPLICATE KEY (").append(primaryKeys).append(") ").append(LINE_BREAK);
        builder.append(tableComment).append(SPACE_CHAR).append(LINE_BREAK);
        builder.append(DEFAULT_DISTRIBUTION_PRE_STRING).append(primaryKeys).append(DEFAULT_DISTRIBUTION_PRO_STRING).append(LINE_BREAK);
        return builder.toString();
    }


    private String getColumnStringForODS(List<ColumnTransferInfo> columnTransferInfos,
                                         String tableComment, String targetPartitionCycle, String sourceIncColumn) {


        Map<String, ColumnTransferInfo> colMap = columnTransferInfos.stream().collect(Collectors.toMap(ColumnTransferInfo::getName, x -> x));
        StringBuilder builder = new StringBuilder("( \n");

        Set<String> primaryKeyCol = new LinkedHashSet<>();
        Set<String> valueCol = new LinkedHashSet<>();

        for (ColumnTransferInfo columnTransferInfo : columnTransferInfos) {
            String name = columnTransferInfo.getName();
            if (columnTransferInfo.isPrimaryKey()) {
                primaryKeyCol.add(name);
            } else {
                valueCol.add(name);
            }
        }

        String tzPtime = "tz_ptime_" + targetPartitionCycle.substring(0, 1);
        primaryKeyCol.add(tzPtime);
        primaryKeyCol.add(sourceIncColumn);
        valueCol.remove(tzPtime);
        valueCol.remove(sourceIncColumn);
        for (String column : primaryKeyCol) {
            ColumnTransferInfo columnTransferInfo = colMap.get(column);
            if (null == columnTransferInfo) {
                // 添加内部字段 如 tz_ptime
                builder.append("`").append(column).append("` ").append("date NOT NULL,").append(LINE_BREAK);
                continue;
            }
            String completeType = generateCompleteType(columnTransferInfo);
            builder.append("`").append(columnTransferInfo.getName()).append("` ").append(completeType).append(SPACE_CHAR);
            if (!columnTransferInfo.isNullable()) {
                builder.append("NOT NULL ");
            }
            if (!StringUtils.isBlank(columnTransferInfo.getComment())) {
                builder.append("COMMENT '").append(columnTransferInfo.getComment()).append("'");
            }
            builder.append(",").append(LINE_BREAK);
        }
        if (valueCol.isEmpty()) {
            builder.deleteCharAt(builder.lastIndexOf(","));
        } else {
            for (String column : valueCol) {
                ColumnTransferInfo columnTransferInfo = colMap.get(column);
                String completeType = generateCompleteType(columnTransferInfo);
                builder.append("`").append(columnTransferInfo.getName()).append("` ").append(completeType).append(SPACE_CHAR);
                if (!columnTransferInfo.isNullable()) {
                    builder.append("NOT NULL ");
                }
                if (!StringUtils.isBlank(columnTransferInfo.getComment())) {
                    builder.append("COMMENT '").append(columnTransferInfo.getComment()).append("'");
                }
                builder.append(",").append(LINE_BREAK);
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
        }
        builder.append(")").append(SPACE_CHAR).append(LINE_BREAK);

        String primaryKeys = String.join(",", primaryKeyCol);
        builder.append("UNIQUE KEY (").append(primaryKeys).append(") ").append(LINE_BREAK);
        builder.append(tableComment).append(SPACE_CHAR).append(LINE_BREAK);
        builder.append("PARTITION BY RANGE(").append(tzPtime).append(") () ").append(SPACE_CHAR).append(LINE_BREAK);
        builder.append(DEFAULT_DISTRIBUTION_PRE_STRING).append(primaryKeys).append(DEFAULT_DISTRIBUTION_PRO_STRING).append(LINE_BREAK);
        return builder.toString();
    }


    private String generateCompleteType(ColumnTransferInfo columnTransferInfo) {
        if (null == columnTransferInfo) {
            return "date";
        }
        String type = columnTransferInfo.getType();
        if (TYPE_TO_DORIS.containsKey(type)) {
            type = TYPE_TO_DORIS.get(type);
        }
        if (MYSQL_INT_TYPE.contains(type) || MYSQL_TEXT_AND_DATE_TYPE.contains(type) || MYSQL_LOB_TYPE.contains(type)) {
            return type;
        } else if (MYSQL_CHAR_TYPE.contains(type) || MYSQL_BINARY_TYPE.contains(type)) {
            long destLength = columnTransferInfo.getDestLength();
            return type + "(" + Math.min(destLength * 4, 60000) + ")";
        } else if (MYSQL_DECIMAL_TYPE.contains(type)) {
            int destNumericPrecision = columnTransferInfo.getDestNumericPrecision();
            int destDecimalPlaces = columnTransferInfo.getDestDecimalPlaces();
            return type + "(" + destNumericPrecision + "," + destDecimalPlaces + ")";
        } else {
            return type;
        }
    }


}
