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
 * 政府部门
 * </p>
 *
 * @author szs
 * @since 2024-01-12
 */
@Setter
@Getter
@ApiModel(value = "政府部门", description = "政府部门")
public class SysAgencyDTO {


    @ApiModelProperty(value = "主键ID")
    private String id;


    @Valid
    @NotBlank(message = "部门编码必填")
    @ApiModelProperty(value = "部门编码", required = true)
    private String code;


    @Valid
    @NotBlank(message = "部门名称必填")
    @ApiModelProperty(value = "部门名称", required = true)
    private String name;


    @Valid
    @NotBlank(message = "组织机构id必填")
    @ApiModelProperty(value = "组织机构id", required = true)
    private String organizationId;


    @Valid
    @NotNull(message = "是否使能必填")
    @ApiModelProperty(value = "是否使能（0否，1是）", required = true)
    private Integer enable;


    @Valid
    @NotNull(message = "排序必填")
    @ApiModelProperty(value = "排序", required = true)
    private Integer seq;


    @ApiModelProperty(value = "备注")
    private String memo;


}
