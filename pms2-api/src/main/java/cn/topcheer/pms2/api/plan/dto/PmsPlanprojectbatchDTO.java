package cn.topcheer.pms2.api.plan.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 查询DTO
 *
 * @author szs
 * @date 2023-11-09
 */
@Data
public class PmsPlanprojectbatchDTO {


    @Valid
    @NotBlank(message = "小批次ID必填")
    @ApiModelProperty(value = "小批次ID", required = true)
    private String pmsPlanprojectbatchId;


    @Valid
    @NotBlank(message = "业务类型必填")
    @ApiModelProperty(value = "业务类型", required = true)
    private String planprojectType;


}

