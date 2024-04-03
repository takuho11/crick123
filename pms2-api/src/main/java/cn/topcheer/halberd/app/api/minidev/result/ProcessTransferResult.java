package cn.topcheer.halberd.app.api.minidev.result;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 流程流转Result
 *
 * @author szs
 * @date 2023-11-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessTransferResult {


    @ApiModelProperty(value = "操作人")
    private String operationUserName;


    @ApiModelProperty(value = "单位名称")
    private String enterpriseName;


    @ApiModelProperty(value = "操作节点")
    private String operationNode;


    @ApiModelProperty(value = "操作结果")
    private String operationResult;


    @ApiModelProperty(value = "操作意见")
    private String operationOpinion;


    @ApiModelProperty(value = "操作时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date operationDate;


}

