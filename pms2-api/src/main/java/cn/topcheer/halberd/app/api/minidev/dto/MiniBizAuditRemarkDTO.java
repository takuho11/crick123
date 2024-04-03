package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 业务批注
 * </p>
 *
 * @author szs
 * @since 2023-11-16
 */
@Data
@ApiModel(value = "业务批注", description = "业务批注")
public class MiniBizAuditRemarkDTO {


    @Valid
    @NotBlank(message = "主表id必填")
    @ApiModelProperty(value = "主表id", required = true)
    private String mainId;



    @ApiModelProperty(value = "子表id")
    private String tableId;


    @Valid
    @NotBlank(message = "字段Code必填")
    @ApiModelProperty(value = "字段Code", required = true)
    private String fieldCode;


}
