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
 * 复制标准模板
 * </p>
 *
 * @author szs
 * @since 2023-10-23
 */
@Setter
@Getter
@ApiModel(value = "复制标准模板", description = "复制标准模板")
public class CopySdBizDefDTO {


    @Valid
    @NotNull(message = "复制标准模板id必填")
    @ApiModelProperty(value = "复制标准模板id")
    private Long copySdBizDefId;


    @Valid
    @NotBlank(message = "复制标准模板编码必填")
    @ApiModelProperty(value = "复制标准模板编码")
    private String copySdBizDefCode;


    @Valid
    @NotBlank(message = "复制标准模板名称必填")
    @ApiModelProperty(value = "复制标准模板名称")
    private String copySdBizDefName;


}
