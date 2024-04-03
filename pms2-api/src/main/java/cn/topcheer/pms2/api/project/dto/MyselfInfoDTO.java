package cn.topcheer.pms2.api.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Copyright (C)
 * FileName: MyselfInfoDTO
 *
 * @Author: maokai
 * Date:     2024/3/20 9:41
 * Description:
 */
@ApiModel("通过手机验证码获取个人信息")
public class MyselfInfoDTO {

    @ApiModelProperty(value = "手机验证码",required = true)
    @NotBlank(message = "手机验证码不能为空")
    @Pattern(regexp = "(^\\d{4})|(^\\d{6})$",message = "手机号码不正确")
    private String mobileCode ;
    @ApiModelProperty(value = "手机号",required = true)
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$",message = "手机号不正确")
    private String mobile ;
    @ApiModelProperty(value = "手机验证码key",required = true)
    @NotBlank(message = "手机验证码不能为空")
    @Length(min = 8,max = 64,message ="手机验证码不正确" )
    private String smsCaptchaKey;

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCaptchaKey() {
        return smsCaptchaKey;
    }

    public void setSmsCaptchaKey(String smsCaptchaKey) {
        this.smsCaptchaKey = smsCaptchaKey;
    }
}
