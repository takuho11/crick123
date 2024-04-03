package cn.topcheer.halberd.app.api.framework.dto.sys;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;


/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author szs
 * @since 2023-10-31
 */
@Setter
@Getter
@ApiModel(value = "系统用户", description = "系统用户")
public class SysUserDTO {


    @ApiModelProperty(value = "主键ID")
    private String id;


    @Valid
    @NotBlank(message = "账号必填")
    @ApiModelProperty(value = "账号", required = true)
    private String userid;


    @ApiModelProperty(value = "密码")
    private String password;


    @Valid
    @NotBlank(message = "姓名必填")
    @ApiModelProperty(value = "姓名", required = true)
    private String name;


    @ApiModelProperty(value = "性别（0女，1男）")
    private BigDecimal sex;


    @ApiModelProperty(value = "手机号码")
    private String mobile;


    @ApiModelProperty(value = "电子邮箱")
    private String email;


    @ApiModelProperty(value = "角色ids")
    private List<String> roleIds;


    @ApiModelProperty(value = "单位ids")
    private List<String> enterPriseIds;


    @ApiModelProperty(value = "部门ids")
    private List<String> deptIds;


    @ApiModelProperty(value = "单位id")
    private String enterPriseId;


    @ApiModelProperty(value = "部门id")
    private String deptId;


    @ApiModelProperty(value = "是否仅仅下级（0否，1是）")
    private Integer isNext;


}
