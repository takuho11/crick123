package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 业务定义和业务模板
 * </p>
 *
 * @author szs
 * @since 2024-03-04
 */
@Setter
@Getter
@ApiModel(value = "业务定义和业务模板", description = "业务定义和业务模板")
public class DefAndVersionDTO {


    @Valid
    @NotNull(message = "业务定义id必填")
    @ApiModelProperty(value = "业务定义id")
    private Long bizDefId;


    @Valid
    @NotNull(message = "业务版本id必填")
    @ApiModelProperty(value = "业务版本id")
    private Long bizVersionId;


}
