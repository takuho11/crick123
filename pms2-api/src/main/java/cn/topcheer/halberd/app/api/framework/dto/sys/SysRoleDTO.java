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
 * 角色
 * </p>
 *
 * @author szs
 * @since 2024-01-22
 */
@Setter
@Getter
@ApiModel(value = "角色", description = "角色")
public class SysRoleDTO {


    @ApiModelProperty(value = "主键ID")
    private String id;


    @Valid
    @NotBlank(message = "角色名称必填")
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;


    @Valid
    @NotBlank(message = "角色编码必填")
    @ApiModelProperty(value = "角色编码", required = true)
    private String roleCode;


    @Valid
    @NotBlank(message = "角色分组必填")
    @ApiModelProperty(value = "角色分组", required = true)
    private String fz;


    @ApiModelProperty(value = "单位id")
    private String enterPriseId;


    @Valid
    @NotNull(message = "排序必填")
    @ApiModelProperty(value = "排序", required = true)
    private Integer seq;


    @ApiModelProperty(value = "是否公共（0否，1是）")
    private Integer isPublic;


    @ApiModelProperty(value = "说明")
    private String description;


}
