package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 业务任务DTO
 *
 * @author szs
 * @date 2024-03-01
 */
@Data
public class BusinessTaskDTO {


    @ApiModelProperty(value = "主表id")
    private String mainId;


    @ApiModelProperty("流程定义key，没传默认取批次绑定的流程第一个")
    private String miniCurrentProcessDefKey;


    @ApiModelProperty("流程节点定义key，没传那就不用判断")
    private String miniCurrentTaskDefKey;


    @ApiModelProperty(value = "流程节点任务ID")
    private String minicurrentstateid;


    @ApiModelProperty(value = "流程节点状态")
    private String minicurrentstate;


}

