package cn.topcheer.halberd.app.api.framework.dto.sys;


import cn.topcheer.pms2.api.sys.SysUserPhone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 批量保存用户手机号
 * </p>
 *
 * @author szs
 * @since 2024-02-27
 */
@Setter
@Getter
@ApiModel(value = "批量保存用户手机号", description = "批量保存用户手机号")
public class BatchSaveSysUserPhoneDTO {


    @Valid
    @NotBlank(message = "账号必填")
    @ApiModelProperty(value = "账号", required = true)
    private String userid;


    @ApiModelProperty(value = "手机号")
    private List<SysUserPhone> userPhoneList;


}
