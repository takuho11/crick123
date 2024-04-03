package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 发起流程DTO
 *
 * @author szs
 * @date 2023-11-21
 */
@Data
public class StartProcessDTO {


    @Valid
    @NotBlank(message = "批次ID必填")
    @ApiModelProperty(value = "批次ID", required = true)
    private String batchId;


    @Valid
    @NotBlank(message = "业务类型必填")
    @ApiModelProperty(value = "业务类型", required = true)
    private String type;


    @Valid
    @NotBlank(message = "主表ID必填")
    @ApiModelProperty(value = "主表ID", required = true)
    private String mainId;


    @ApiModelProperty("流程定义key，没传默认取批次绑定的流程第一个")
    private String processDefKey;


//    @ApiModelProperty("审核人id")
//    private String auditor;


}

