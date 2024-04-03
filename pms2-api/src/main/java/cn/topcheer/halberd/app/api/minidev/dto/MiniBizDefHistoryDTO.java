package cn.topcheer.halberd.app.api.minidev.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 业务定义历史DTO
 *
 * @author szs
 * @date 2024-03-27
 */
@Data
@ApiModel(value = "业务定义历史DTO", description = "业务定义历史DTO")
public class MiniBizDefHistoryDTO {


    @ApiModelProperty(value = "业务模板定义ID")
    private Long bizDefId;


    @ApiModelProperty(value = "业务模板版本ID")
    private Long bizVersionId;


}
