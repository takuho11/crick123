
package cn.topcheer.halberd.app.api.framework.entity.sys;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 区域信息表
 */
@Entity
@Table(name = "PMS_AREA")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PmsArea implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @ApiModelProperty("主键ID")
    private String id;


    @ApiModelProperty("组织编码")
    private String code;


    @ApiModelProperty("组织名称")
    private String name;


    @ApiModelProperty("上级id")
    private String parentareaid;


    @ApiModelProperty("级别")
    private Integer arealevel;


    @ApiModelProperty("是否删除（0未删除，1已删除）")
    private Integer isDeleted;


    @ApiModelProperty("是否使能（0否，1是）")
    private Integer enable;


    @ApiModelProperty("排序")
    private Integer seq;


}