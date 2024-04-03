package cn.topcheer.halberd.app.api.minidev.result;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 流程路径Result
 *
 * @author szs
 * @date 2023-11-22
 */
@Data
@AllArgsConstructor
public class ProcessPathResult {


    @ApiModelProperty(value = "单位ID")
    private String enterpriseId;


    @ApiModelProperty(value = "单位名称")
    private String enterpriseName;


    @ApiModelProperty(value = "联系人")
    private String linkman;


    @ApiModelProperty(value = "联系人手机")
    private String mobile;


    @ApiModelProperty(value = "联系电话")
    private String telephone;


}

