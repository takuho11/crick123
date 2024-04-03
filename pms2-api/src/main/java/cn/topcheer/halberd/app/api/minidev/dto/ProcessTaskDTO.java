package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 流程任务DTO
 *
 * @author szs
 * @date 2024-03-26
 */
@Data
public class ProcessTaskDTO {


    @Valid
    @NotBlank(message = "业务类型必填")
    @ApiModelProperty(value = "业务类型", required = true)
    private String type;


    @Valid
    @NotBlank(message = "批次ID必填")
    @ApiModelProperty(value = "批次ID", required = true)
    private String batchId;


    @Valid
    @NotBlank(message = "主表ID必填")
    @ApiModelProperty(value = "主表ID", required = true)
    private String mainId;


    @ApiModelProperty(value = "流程定义key")
    private String miniCurrentProcessDefKey;


    @Valid
    @NotBlank(message = "流程节点状态必填")
    @ApiModelProperty(value = "流程节点状态", required = true)
    private String minicurrentstate;


}

