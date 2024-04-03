package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 批量完成任务DTO
 *
 * @author szs
 * @date 2023-12-05
 */
@Data
public class BatchCompleteTaskDTO {


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


    @Valid
    @NotBlank(message = "任务操作结果必填")
    @ApiModelProperty(value = "任务操作结果，取标准按钮的prop", required = true)
    private String taskResult;


    @Valid
    @NotBlank(message = "任务操作结果名称必填")
    @ApiModelProperty(value = "任务操作结果名称，取标准按钮的name", required = true)
    private String taskResultName;


    @Valid
    @NotBlank(message = "任务操作意见必填")
    @ApiModelProperty(value = "任务操作意见", required = true)
    private String taskComment;


    @ApiModelProperty(value = "修改字段版本id")
    private Long miniBizAllowUpdateId;


    @ApiModelProperty(value = "是否判断时间")
    private Boolean isJudgeTime;


    @ApiModelProperty(value = "时间编码，为空就不用判断时间")
    private String timeCode;


    @ApiModelProperty(value = "是否限制指标")
    private Boolean isLimit;


    @ApiModelProperty(value = "限制默认数据")
    private Integer limitNum;


    @ApiModelProperty(value = "限制结果，消耗或退回（pass、back）")
    private String limitResult;


    @ApiModelProperty(value = "限制业务类型")
    private String limitGridType;


    /**
     * 获取业务ids
     *
     * @return List
     */
    public List<String> getBusinessIds() {
        if (this.getBusinessTaskList() != null) {
            return this.getBusinessTaskList().stream().map(BusinessTaskDTO::getMainId).distinct().collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * 获取任务ids
     *
     * @return List
     */
    public List<String> getTaskIds() {
        if (this.getBusinessTaskList() != null) {
            return this.getBusinessTaskList().stream().map(BusinessTaskDTO::getMinicurrentstateid).distinct().collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * 获取第一个任务的名称
     *
     * @return String
     */
    public String getFirstTaskName() {

        return this.getBusinessTaskList() != null && this.getBusinessTaskList().size() > 0 ? this.getBusinessTaskList().get(0).getMinicurrentstate() : "";
    }


}

