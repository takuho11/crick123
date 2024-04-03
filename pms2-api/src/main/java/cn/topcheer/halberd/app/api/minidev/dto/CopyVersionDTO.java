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
 * 复制版本
 * </p>
 *
 * @author szs
 * @since 2023-08-24
 */
@Setter
@Getter
@ApiModel(value = "复制版本", description = "复制版本")
public class CopyVersionDTO {


    @Valid
    @NotNull(message = "复制版本id必填")
    @ApiModelProperty(value = "复制版本id")
    private Long copyVersionId;


    @Valid
    @NotBlank(message = "复制版本名称必填")
    @ApiModelProperty(value = "复制版本名称")
    private String copyVersionName;


}
