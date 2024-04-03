package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 判断任务DTO
 *
 * @author szs
 * @date 2023-12-20
 */
@Data
public class JudgeTaskDTO {


    @Valid
    @NotBlank(message = "批次ID必填")
    @ApiModelProperty(value = "批次ID", required = true)
    private String batchId;


    @Valid
    @NotBlank(message = "业务类型必填")
    @ApiModelProperty(value = "业务类型", required = true)
    private String type;


    @Valid
    @NotNull(message = "业务任务列表必填")
    @ApiModelProperty(value = "业务任务列表", required = true)
    private List<BusinessTaskDTO> businessTaskList;


}

