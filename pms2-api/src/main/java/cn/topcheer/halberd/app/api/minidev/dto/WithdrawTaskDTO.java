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
 * 撤回任务DTO
 *
 * @author szs
 * @date 2024-03-15
 */
@Data
public class WithdrawTaskDTO {


    @Valid
    @NotBlank(message = "批次ID必填")
    @ApiModelProperty(value = "批次ID", required = true)
    private String batchId;


    @Valid
    @NotBlank(message = "业务类型必填")
    @ApiModelProperty(value = "业务类型", required = true)
    private String type;


    @Valid
    @NotBlank(message = "主表ID必填")
    @ApiModelProperty(value = "主表ID", required = true)
    private String mainId;


    @ApiModelProperty("流程定义key，没传默认取批次绑定的流程第一个")
    private String processDefKey;


    @ApiModelProperty(value = "允许状态集合")
    private List<String> allowStatusList;


    @ApiModelProperty(value = "是否判断时间")
    private Boolean isJudgeTime;


    @ApiModelProperty("时间编码")
    private String timeCode;


    @ApiModelProperty(value = "是否限制指标")
    private Boolean isLimit;


    @ApiModelProperty(value = "限制默认数据")
    private Integer limitNum;


    @ApiModelProperty(value = "限制结果，消耗或退回（pass、back）")
    private String limitResult;


    @ApiModelProperty(value = "限制业务类型")
    private String limitGridType;



}

