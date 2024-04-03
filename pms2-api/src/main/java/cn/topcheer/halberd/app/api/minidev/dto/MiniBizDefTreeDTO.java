package cn.topcheer.halberd.app.api.minidev.dto;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 业务定义树
 * </p>
 *
 * @author szs
 * @since 2023-08-23
 */
@Setter
@Getter
@ApiModel(value = "业务定义树", description = "业务定义树")
public class MiniBizDefTreeDTO extends IdDTO {


    @Valid
    @NotNull(message = "版本id必填")
    @ApiModelProperty(value = "版本id")
    private Long bizVersionId;


}
