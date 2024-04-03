package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.json.JSONObject;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 判断数据
 * </p>
 *
 * @author szs
 * @since 2023-12-19
 */
@Data
@ApiModel(value = "判断数据", description = "判断数据")
public class MiniJudgeAllTableDTO {


    @Valid
    @NotBlank(message = "主表id必填")
    @ApiModelProperty(value = "主表id", required = true)
    private String mainid;


    @Valid
    @NotBlank(message = "批次id必填")
    @ApiModelProperty(value = "批次id", required = true)
    private String batchid;


    @ApiModelProperty(value = "数据")
    private JSONObject dataObject;


    @ApiModelProperty(value = "业务模板id")
    private Long bizDefId;


    @ApiModelProperty(value = "业务版本id")
    private Long bizVersionId;


    @ApiModelProperty(value = "是否判断流转状态（0否，1是）,为空默认需要判断1")
    private Integer isJudgeState;


    @Valid
    @NotBlank(message = "业务类型必填")
    @ApiModelProperty(value = "业务类型", required = true)
    private String type;


}
