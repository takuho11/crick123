package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.json.JSONObject;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 保存数据
 * </p>
 *
 * @author szs
 * @since 2023-11-06
 */
@Data
@ApiModel(value = "保存数据", description = "保存数据")
public class MiniSaveAllTableDTO {


    @Valid
    @NotBlank(message = "主表id必填")
    @ApiModelProperty(value = "主表id", required = true)
    private String mainid;


    @Valid
    @NotBlank(message = "批次id必填")
    @ApiModelProperty(value = "批次id", required = true)
    private String batchid;


    @Valid
    @NotBlank(message = "类型必填")
    @ApiModelProperty(value = "类型", required = true)
    private String mold;


    @Valid
    @NotBlank(message = "流转类型必填")
    @ApiModelProperty(value = "流转类型", required = true)
    private String flowtype;


    @Valid
    @NotBlank(message = "批次类型必填")
    @ApiModelProperty(value = "批次类型", required = true)
    private String type;


    @Valid
    @NotNull(message = "数据必填")
    @ApiModelProperty(value = "数据", required = true)
    private JSONObject dataObject;


    @ApiModelProperty(value = "业务模板id")
    private Long bizDefId;


    @ApiModelProperty(value = "业务版本id")
    private Long bizVersionId;


    @ApiModelProperty(value = "是否添加历史版本（0否，1是）")
    private Integer isAddHistoryVersion;


    @ApiModelProperty(value = "是否判断流转状态（0否，1是）,为空默认需要判断1")
    private Integer isJudgeState;


}
