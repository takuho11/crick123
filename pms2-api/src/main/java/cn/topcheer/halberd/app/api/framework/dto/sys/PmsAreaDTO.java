package cn.topcheer.halberd.app.api.framework.dto.sys;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * <p>
 * 区域信息
 * </p>
 *
 * @author szs
 * @since 2024-01-12
 */
@Setter
@Getter
@ApiModel(value = "区域信息", description = "区域信息")
public class PmsAreaDTO {


    @ApiModelProperty(value = "主键ID")
    private String id;


    @Valid
    @NotBlank(message = "编码必填")
    @ApiModelProperty(value = "编码", required = true)
    private String code;


    @Valid
    @NotBlank(message = "名称必填")
    @ApiModelProperty(value = "名称", required = true)
    private String name;


    @ApiModelProperty(value = "上级id")
    private String parentareaid;


    @Valid
    @NotNull(message = "是否使能必填")
    @ApiModelProperty(value = "是否使能（0否，1是）", required = true)
    private Integer enable;


    @Valid
    @NotNull(message = "排序必填")
    @ApiModelProperty(value = "排序", required = true)
    private Integer seq;



}
