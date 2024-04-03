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
@TableName("data_integration_flow_def")
@EqualsAndHashCode()
@ApiModel(value = "DataIntegrationFlowDef对象", description = "数据集成工作流定义")
public class DataIntegrationFlowDef extends BaseUuidEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工作流名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "dol项目code")
    @TableField(value = "dol_project_code")
    // 如果直接操作dolphin将工作流移动，数据将不一致。目前我们不支持工作流移动的操作。
    // 原生dolphin也没有移动的逻辑
    private String dolProjectCode;

    @ApiModelProperty(value = "dol工作流code")
    @TableField(value = "dol_process_code")
    private String dolProcessCode;

    @ApiModelProperty(value = "数据流向-弃用")
    @TableField(value = "flow_direction")
    @Deprecated
    private String flowDirection;


    @ApiModelProperty(value = "ui结构")
    @TableField(value = "ui")
    @Deprecated
    private String ui;


    // yyyyMM 或 yyyy-MM-dd 等
    @ApiModelProperty(value = "增量同步全局时间参数的样式")
    @TableField(value = "inc_time_param_style")
    private String incTimeParamStyle;


    @ApiModelProperty(value = "是否已上线")
    @TableField(value = "release_stat")
    private int releaseStat;


}
