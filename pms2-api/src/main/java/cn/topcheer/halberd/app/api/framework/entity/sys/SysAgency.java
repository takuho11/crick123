
package cn.topcheer.halberd.app.api.framework.entity.sys;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 政府部门表
 */
@Entity
@Table(name = "SYS_AGENCY")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SysAgency implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @ApiModelProperty("主键ID")
    private String id;


    @ApiModelProperty("部门编码")
    private String code;


    @ApiModelProperty("部门名称")
    private String name;


    @ApiModelProperty("组织机构")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGANIZATION_ID")
    private SysOrganization sysOrganization;


    @ApiModelProperty("备注")
    private String memo;


    @ApiModelProperty("创建人")
    private String createUser;


    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @ApiModelProperty("更新人")
    private String updateUser;


    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    @ApiModelProperty("是否删除（0未删除，1已删除）")
    private Integer isDeleted;


    @ApiModelProperty("是否使能（0否，1是）")
    private Integer enable;


    @ApiModelProperty("排序")
    private Integer seq;


}