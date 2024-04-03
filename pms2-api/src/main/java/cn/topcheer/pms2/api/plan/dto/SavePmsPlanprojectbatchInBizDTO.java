package cn.topcheer.pms2.api.plan.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 保存DTO
 *
 * @author szs
 * @date 2023-11-09
 */
@Data
public class SavePmsPlanprojectbatchInBizDTO {


    @Valid
    @NotBlank(message = "小批次ID必填")
    @ApiModelProperty(value = "小批次ID", required = true)
    private String pmsPlanprojectbatchId;


    @Valid
    @NotBlank(message = "批次类型必填")
    @ApiModelProperty(value = "批次类型", required = true)
    private String planprojectType;


    @ApiModelProperty("业务模板id")
    private Long miniBizDefId;


    @ApiModelProperty("业务版本id")
    private Long miniBizVersionId;


}

