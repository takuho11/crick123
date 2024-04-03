package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 导入标准模板
 * </p>
 *
 * @author szs
 * @since 2023-08-23
 */
@Setter
@Getter
@ApiModel(value = "导入标准模板", description = "导入标准模板")
public class ImportTemplateDTO {


    @Valid
    @NotNull(message = "业务定义id必填")
    @ApiModelProperty(value = "业务定义id")
    private Long bizDefId;


    @Valid
    @NotNull(message = "标准模板id必填")
    @ApiModelProperty(value = "标准模板id")
    private Long sdBizDefId;


}
