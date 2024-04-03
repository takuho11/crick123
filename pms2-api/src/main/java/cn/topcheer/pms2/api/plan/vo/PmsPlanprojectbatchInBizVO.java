package cn.topcheer.pms2.api.plan.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询VO
 *
 * @author szs
 * @date 2023-11-09
 */
@Data
public class PmsPlanprojectbatchInBizVO {


    @ApiModelProperty(value = "小批次ID")
    private String pmsPlanprojectbatchId;


    @ApiModelProperty(value = "批次类型")
    private String planprojectType;


    @ApiModelProperty(value = "批次类型名称")
    private String planprojectTypeName;


    @ApiModelProperty("业务模板id")
    private Long miniBizDefId;


    @ApiModelProperty("业务版本id")
    private Long miniBizVersionId;


    @ApiModelProperty("排序")
    private Integer seq;


}

