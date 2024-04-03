package cn.topcheer.halberd.app.api.framework.dto.dolphin.taskparams;


import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class SqlTaskParams extends TaskParams{

    private List<Map<String, Object>> localParams;

    private List<Map<String, Object>> resourceList;

    // 数据源type，doris在dolphin中也是mysql驱动
    private String type;

    // dolphin的数据源id
    private long datasource;

    // 主处理sql
    private String sql;

    // "0"表示查询，"1"表示非查询
    private String sqlType;

    // 前置sql列表
    private List<String> preStatements;

    // 后置sql列表
    private List<String> postStatements;

    // 非查询时的分隔符，一般为 ';'
    private String segmentSeparator;

    // 查询时的limit行数
    private Integer displayRows;


    public static SqlTaskParams assembleTaskParams(
            SqlTaskParams taskParams,
            String type,
            long dataSource,
            String sql,
            String sqlType,
            String segmentSeparator,
            int displayRows,
            List<String> preStatements,
            List<String> postStatements) {
        if (null == taskParams) {
            taskParams = new SqlTaskParams();
        }
        taskParams.setLocalParams(Collections.emptyList());
        taskParams.setResourceList(Collections.emptyList());
        taskParams.setType("DORIS".equalsIgnoreCase(type) ? "MYSQL" : type);
        taskParams.setDatasource(dataSource);
        taskParams.setSql(sql);
        taskParams.setSqlType(sqlType);
        taskParams.setSegmentSeparator(segmentSeparator);
        taskParams.setDisplayRows(displayRows);
        taskParams.setPreStatements(preStatements);
        taskParams.setPostStatements(postStatements);
        return taskParams;
    }




}

