package cn.topcheer.halberd.app.api.framework.dto.dolphin.taskparams;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class DataxTaskParams extends TaskParams {

    private List<Map<String, Object>> localParams;

    private List<Map<String, Object>> resourceList;

    private int customConfig;

    private String dsType;

    // dolphin的数据源id
    private long dataSource;

    private String dtType;

    // dolphin的数据源id
    private long dataTarget;

    private String sql;

    private String targetTable;

    private int jobSpeedByte;

    private int jobSpeedRecord;

    // 前置sql列表
    private List<String> preStatements;

    // 后置sql列表
    private List<String> postStatements;

    private int xms;

    private int xmx;


    public static DataxTaskParams assembleTaskParams (
            DataxTaskParams taskParams,
            String dsType,
            String dtType,
            long dataSource,
            long dataTarget,
            String sql,
            String targetTable,
            List<String> preStatements,
            List<String> postStatements) {
        if (null == taskParams) {
            taskParams = generateDefaultTaskParams();
        }
        taskParams.setDsType("DORIS".equalsIgnoreCase(dsType) ? "MYSQL" : dsType);
        taskParams.setDtType("DORIS".equalsIgnoreCase(dtType) ? "MYSQL" : dtType);
        taskParams.setDataSource(dataSource);
        taskParams.setDataTarget(dataTarget);
        taskParams.setSql(sql);
        taskParams.setTargetTable(targetTable);
        taskParams.setPreStatements(preStatements);
        taskParams.setPostStatements(postStatements);
        return taskParams;
    }


    private static DataxTaskParams generateDefaultTaskParams() {
        DataxTaskParams taskParams = new DataxTaskParams();
        taskParams.setLocalParams(Collections.emptyList());
        taskParams.setResourceList(Collections.emptyList());
        taskParams.setCustomConfig(0);
        taskParams.setJobSpeedByte(0);
        taskParams.setJobSpeedRecord(0);
        taskParams.setXms(1);
        taskParams.setXmx(1);
        return taskParams;
    }

}

