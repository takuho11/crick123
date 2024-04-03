package cn.topcheer.halberd.app.api.framework.dto.dolphin;

import cn.topcheer.halberd.app.api.framework.entity.dolphin.ColumnTransferInfo;
import cn.topcheer.halberd.app.api.framework.dto.DbTableInfoDTO;
import lombok.Data;

import java.util.List;

@Data
public class DataIntegrationDTO {


    // 工作流名称 新建时必填
    private String processName;

    // 任务名称
    private String name;

    // 任务所属工程的code
    private long projectCode;

    // 任务描述
    private String description;

    // 数据流向，具体的流向在sourceTableInfo与targetTableInfo中提取，并保存在task层
    @Deprecated
    private String flowDirection;

    // 列转换信息
    private List<ColumnTransferInfo> columnTransferInfos;

    // 来源表信息
    private DbTableInfoDTO sourceTableInfo;

    // 来源目的信息
    private DbTableInfoDTO targetTableInfo;

    // 想要加入的工作流code
    private long targetProcessCode;


    // 目标表ods层分区周期，分区周期： day/month 两种 ，默认是天
    private String targetPartitionCycle;

    // 源表的增量基准字段。，用于生成任务中的datax sql where 条件。  需要是string类型或时间类型
    private String sourceIncColumn;

    // 是否增量同步
    private boolean incSync;

    // 基准字段要求语义必须是时间，但实际情况是支持两种：datetime，string。若是string，则由sourceIncColumnTimeSplit决定格式。
    // 如果是时间，则必须是 - ，即：全局参数格式必须是 "yyyy-MM-dd" 或 "yyyy-MM"
    private String sourceIncColumnTimeType;

    // 基准字段是string时的分隔符  如 "-" -> "yyyy-MM-dd" 或 "yyyy-MM"  (由 targetPartitionCycle 决定)
    private String sourceIncColumnTimeSplit;

}
