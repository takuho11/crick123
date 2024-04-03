package cn.topcheer.halberd.app.api.minidev.result;


import cn.topcheer.halberd.flow.entity.BatchTaskJob;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 批次任务Result
 *
 * @author szs
 * @date 2024-01-02
 */
@Data
public class BatchTaskResult extends BatchTaskJob {


    @ApiModelProperty(value = "任务名称")
    private String taskName;


}

