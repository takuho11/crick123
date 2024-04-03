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
 * 部门
 * </p>
 *
 * @author szs
 * @since 2023-11-01
 */
@Setter
@Getter
@ApiModel(value = "部门", description = "部门")
public class PmsDepartmentDTO {


    @ApiModelProperty(value = "主键ID")
    private String id;


    @Valid
    @NotBlank(message = "部门名称必填")
    @ApiModelProperty(value = "部门名称", required = true)
    private String name;


    @Valid
    @NotBlank(message = "部门编码必填")
    @ApiModelProperty(value = "部门编码", required = true)
    private String departmentcode;


    @Valid
    @NotBlank(message = "部门简称必填")
    @ApiModelProperty(value = "部门简称", required = true)
    private String shortname;


    @ApiModelProperty(value = "单位id")
    private String enterPriseId;


    @ApiModelProperty(value = "负责人")
    private String leader;


    @ApiModelProperty(value = "手机号码")
    private String telephone;


    @Valid
    @NotNull(message = "排序必填")
    @ApiModelProperty(value = "排序", required = true)
    private Integer seq;


    @ApiModelProperty(value = "备注")
    private String emeo;


}
