package cn.topcheer.halberd.app.api.minidev.result;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 判断任务Result
 *
 * @author szs
 * @date 2023-12-20
 */
@Data
public class JudgeTaskResult {


    @ApiModelProperty(value = "主表id")
    private String mainId;


    @ApiModelProperty(value = "状态（true，false）")
    private boolean status;


    @ApiModelProperty(value = "信息")
    private String msg;


}

