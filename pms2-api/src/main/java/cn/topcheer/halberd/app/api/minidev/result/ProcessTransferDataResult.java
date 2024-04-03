package cn.topcheer.halberd.app.api.minidev.result;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 流程流转数据Result
 *
 * @author szs
 * @date 2023-12-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessTransferDataResult {


    @ApiModelProperty(value = "当前任务名称")
    private String taskName;


    @ApiModelProperty(value = "流转记录")
    private List<ProcessTransferResult> transferList;


}

