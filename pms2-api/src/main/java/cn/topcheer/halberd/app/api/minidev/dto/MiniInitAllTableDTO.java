package cn.topcheer.halberd.app.api.minidev.dto;


import lombok.Data;
import net.sf.json.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 获取数据
 * </p>
 *
 * @author szs
 * @since 2023-11-01
 */
@Data
@ApiModel(value = "获取数据", description = "获取数据")
public class MiniInitAllTableDTO {


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


    @ApiModelProperty(value = "是否转至今，用于日期，9999 转 至今")
    private Boolean isToNow;


}
