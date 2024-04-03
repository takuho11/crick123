package cn.topcheer.pms2.api.plan.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 业务类别
 *
 * @author whq
 * @date 2023-11-09
 */
@Data
@Entity
@Table(name = "PMS_PLANTYPE")
public class PmsPlantype implements Serializable {
    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("排序")
    private Integer seq;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
