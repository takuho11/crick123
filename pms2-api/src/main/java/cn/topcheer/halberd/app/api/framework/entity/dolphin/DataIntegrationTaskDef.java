package cn.topcheer.halberd.app.api.framework.entity.dolphin;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseUuidEntity;

import java.io.Serializable;

@Data
@TableName("data_integration_task_def")
@EqualsAndHashCode()
@ApiModel(value = "DataIntegrationTaskDef对象", description = "数据集成任务定义")
public class DataIntegrationTaskDef extends BaseUuidEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "dol工程code")
    @TableField(value = "dol_project_code")
    private String dolProjectCode;

    @ApiModelProperty(value = "dol工作流code")
    @TableField(value = "dol_process_code")
    // 如果直接操作dolphin将任务移动，数据将不一致。目前我们不支持任务移动的操作。
    // 原生dolphin任务移动的逻辑存在bug，正好不可用
    private String dolProcessCode;

    @ApiModelProperty(value = "dol任务code")
    @TableField(value = "dol_task_code")
    private String dolTaskCode;

    @ApiModelProperty(value = "源的数据类型")
    @TableField(value = "source_data_type")
    private String sourceDataType;

    @ApiModelProperty(value = "目的的数据类型")
    @TableField(value = "target_data_type")
    private String targetDataType;


    @ApiModelProperty(value = "中台保存的工作流id")
    @TableField(value = "flow_def_id")
    private String flowDefId;


    @ApiModelProperty(value = "定义的ui结构")
    // 如果绕开中台，直接在dolphin修改，ui和实际运行的可能不一致
    @TableField(value = "ui")
    private String ui;

    @ApiModelProperty(value = "版本")
    // 如果绕开中台，直接在dolphin修改，中台存储的version和dol的将不相同
    @TableField(value = "version")
    private String version;


    @ApiModelProperty(value = "创建人姓名")
    @TableField(value = "create_user_name")
    private String createUserName;


    @ApiModelProperty(value = "其他的code序列，逗号分隔")
    @TableField(value = "other_dol_task_codes")
    private String otherDolTaskCodes;


    @ApiModelProperty(value = "是否是增量同步，1表示增量，0表示全量")
    @TableField(value = "inc")
    private int inc;




}
