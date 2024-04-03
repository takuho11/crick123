package cn.topcheer.pms2.api.plan.dto;


import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInFlow;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 保存DTO
 *
 * @author szs
 * @date 2023-11-21
 */
@Data
public class SavePmsPlanprojectbatchInFlowDTO {


    @Valid
    @NotBlank(message = "小批次ID必填")
    @ApiModelProperty(value = "小批次ID", required = true)
    private String pmsPlanprojectbatchId;


    @Valid
    @NotBlank(message = "批次类型必填")
    @ApiModelProperty(value = "批次类型", required = true)
    private String planprojectType;


    @Valid
    @NotNull(message = "小批次关联流程列表必填")
    @ApiModelProperty(value = "小批次关联流程列表", required = true)
    private List<PmsPlanprojectbatchInFlow> batchInFlowList;


}

