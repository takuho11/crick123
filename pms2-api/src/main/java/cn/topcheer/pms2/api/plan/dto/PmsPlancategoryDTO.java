package cn.topcheer.pms2.api.plan.dto;

import cn.topcheer.pms2.api.plan.entity.PmsPlantype;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
public class PmsPlancategoryDTO {

    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("分类")
    private String category;

    @ApiModelProperty("")
    private String entrance;

    @ApiModelProperty("排序")
    private Integer seq;

    @ApiModelProperty("默认业务类型")
    private String defaultType;

    @ApiModelProperty("默认业务类型对象")
    private List<PmsPlantype> pmsPlantypeList;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("部门")
    private String itemOrganCode;

    @ApiModelProperty("归口单位")
    private String organizeId;

    @ApiModelProperty("访问权限")
    private String roleIds;
}
