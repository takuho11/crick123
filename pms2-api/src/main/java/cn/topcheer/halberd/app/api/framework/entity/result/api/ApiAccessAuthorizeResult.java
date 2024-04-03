package cn.topcheer.halberd.app.api.framework.entity.result.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * ApiAccessAuthorizeResult
 *
 * @author szs
 * @date 2023-09-24
 */
@Getter
@Setter
public class ApiAccessAuthorizeResult {


    @ApiModelProperty(value = "是否访问鉴权（0否，1是）")
    private Boolean isAccessAuthorize = false;


    @ApiModelProperty(value = "今天访问次数")
    private Long todayNum = 0L;


    @ApiModelProperty(value = "总访问次数")
    private Long totalNum = 0L;


    @ApiModelProperty(value = "是否到期")
    private Boolean isExpired = false;


}
