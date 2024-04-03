package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 允许修改DTO
 *
 * @author szs
 * @date 2023-12-05
 */
@Data
public class AllowUpdateDTO {


    @Valid
    @NotNull(message = "类型必填")
    @ApiModelProperty(value = "类型（1通用，2自定义）", required = true)
    private Integer type;


    @Valid
    @NotBlank(message = "批次ID必填")
    @ApiModelProperty(value = "批次ID", required = true)
    private String batchId;


    @Valid
    @NotBlank(message = "业务类型必填")
    @ApiModelProperty(value = "业务类型", required = true)
    private String planprojectType;


    @ApiModelProperty(value = "主表ID")
    private String mainId;


}

