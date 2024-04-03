package cn.topcheer.halberd.app.api.framework.dto.sys;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * <p>
 * 单位
 * </p>
 *
 * @author szs
 * @since 2023-11-01
 */
@Setter
@Getter
@ApiModel(value = "单位", description = "单位")
public class PmsEnterpriseDTO {


    @ApiModelProperty(value = "主键ID")
    private String id;


    @Valid
    @NotBlank(message = "单位名称必填")
    @ApiModelProperty(value = "单位名称", required = true)
    private String name;


    @ApiModelProperty(value = "上级单位id")
    private String parentId;


    @Valid
    @NotBlank(message = "统一社会信用代码必填")
    @ApiModelProperty(value = "统一社会信用代码", required = true)
    private String uniformcode;


    @Valid
    @NotBlank(message = "企业类型必填")
    @ApiModelProperty(value = "企业类型", required = true)
    private String unittype;


    @ApiModelProperty(value = "管理员")
    private String linkman;


    @ApiModelProperty(value = "移动电话")
    private String mobile;


    @ApiModelProperty(value = "工作电话")
    private String telephone;


    @ApiModelProperty(value = "所属区域id")
    private String areaId;


    @ApiModelProperty(value = "是否仅仅下级（0否，1是）")
    private Integer isNext;


}
