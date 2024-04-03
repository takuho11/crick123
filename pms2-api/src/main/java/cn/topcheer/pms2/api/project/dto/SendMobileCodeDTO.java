package cn.topcheer.pms2.api.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Copyright (C)
 * FileName: SendMobileCodeDTO
 *
 * @Author: maokai
 * Date:     2024/3/21 9:18
 * Description:
 */
@ApiModel("发送手机验证码参数")
public class SendMobileCodeDTO {
    @ApiModelProperty(value = "手机号",required = true)
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$",message = "手机号码格式不正确")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

