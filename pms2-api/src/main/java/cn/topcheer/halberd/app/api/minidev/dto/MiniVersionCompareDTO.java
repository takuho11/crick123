package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 版本比对
 * </p>
 *
 * @author szs
 * @since 2023-11-17
 */
@Data
@ApiModel(value = "版本比对", description = "版本比对")
public class MiniVersionCompareDTO extends MiniInitAllTableDTO {


    @Valid
    @NotNull(message = "历史版本id必填")
    @ApiModelProperty(value = "历史版本id", required = true)
    private Long bizHistoryVersionId;


}
