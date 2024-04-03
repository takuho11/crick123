package cn.topcheer.pms2.api.plan.vo;

import cn.topcheer.pms2.api.plan.entity.PmsPlantype;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.util.List;

/**
 * 查询VO
 *
 * @author whq
 * @date 2023-11-09
 */
@Data
public class PmsPlancategoryVO {
    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("排序")
    private Integer seq;

    @ApiModelProperty("默认业务类型")
    private String defaultType;

    @ApiModelProperty("默认业务类型对象")
    private List<PmsPlantype> pmsPlantypeList;
}
