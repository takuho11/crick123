package cn.topcheer.halberd.app.api.minidev.dto;


import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 业务定义配置
 * </p>
 *
 * @author szs
 * @since 2023-08-28
 */
@Setter
@Getter
@ApiModel(value = "业务定义配置", description = "业务定义配置")
public class MiniBizDefConfigDTO extends IdDTO {


    @ApiModelProperty(value = "业务版本id")
    private Long bizVersionId;


    @ApiModelProperty(value = "主表id")
    private String mainId;


}
