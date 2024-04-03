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
 * 复制业务模板
 * </p>
 *
 * @author szs
 * @since 2023-08-24
 */
@Setter
@Getter
@ApiModel(value = "复制业务模板", description = "复制业务模板")
public class CopyBizDefDTO {


    @Valid
    @NotNull(message = "复制业务模板id必填")
    @ApiModelProperty(value = "复制业务模板id")
    private Long copyBizDefId;


    @Valid
    @NotBlank(message = "复制业务模板名称必填")
    @ApiModelProperty(value = "复制业务模板名称")
    private String copyBizDefName;


}
