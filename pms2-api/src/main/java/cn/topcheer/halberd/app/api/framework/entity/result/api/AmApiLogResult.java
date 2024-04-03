package cn.topcheer.halberd.app.api.framework.entity.result.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApiLog;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * ApiLogResult
 *
 * @author szs
 * @date 2023-09-11
 */
@Getter
@Setter
public class AmApiLogResult extends AmApiLog {


    @ApiModelProperty(value = "API名称")
    private String apiName;


    @ApiModelProperty(value = "用户名称")
    private String userName;


    @ApiModelProperty(value = "用户账号")
    private String userAccount;


    @ApiModelProperty(value = "应用密钥")
    private String clientSecret;


}
