package cn.topcheer.halberd.app.api.framework.dto;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class DbTableInfoDTO {

    @ApiModelProperty(value = "数据源id")
    private String dataSourceId;

    @ApiModelProperty(value = "数据源Name")
    private String dataSourceName;

    @ApiModelProperty(value = "schema")
    private String schema;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "数据分层")
    private String dataLevel;

    @ApiModelProperty(value = "业务领域")
    private String businessDomain;

    @ApiModelProperty(value = "表注释")
    private String comment;

    @ApiModelProperty(value = "其它说明")
    private String memo;

    @ApiModelProperty(value = "数据存储介质的类型")
    private DataStoreType.Type dataStoreType;


    // 目标表ods层分区周期
    @ApiModelProperty(value = "分区周期： day/month 两种 ，默认是天")
    private String targetPartitionCycle;

    // 源表的增量基准字段
    @ApiModelProperty(value = "增量同步的基准字段，用于生成任务中的datax sql where 条件。")
    private String sourceIncColumn;



    public void setDataStoreTypeByString(String dataStoreTypeStr) {
        if(StringUtils.isBlank(dataStoreTypeStr)){
            return;
        }
        String s = dataStoreTypeStr.trim().toUpperCase();
        for (DataStoreType.Type type : DataStoreType.Type.values()) {
            if(s.equals(type.name())){
                this.dataStoreType = type;
                return;
            }
        }
    }
}
