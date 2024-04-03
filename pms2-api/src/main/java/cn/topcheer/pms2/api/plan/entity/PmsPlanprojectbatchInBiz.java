package cn.topcheer.pms2.api.plan.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 小批次绑定业务模板中间表
 *
 * @author szs
 * @date 2023-11-09
 */
@Data
@Entity
@Table(name = "PMS_PLANPROJECTBATCH_IN_BIZ")
public class PmsPlanprojectbatchInBiz implements Serializable {


    @Id
    @ApiModelProperty("主键ID")
    private String id;


    @ApiModelProperty("小批次ID")
    private String pmsPlanprojectbatchId;


    @ApiModelProperty("业务类型")
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLANPROJECT_TYPE", referencedColumnName = "CODE")
    private PmsPlantype pmsPlantype;


    @ApiModelProperty("业务模板id")
    private Long miniBizDefId;


    @ApiModelProperty("业务版本id")
    private Long miniBizVersionId;


    @ApiModelProperty("创建人")
    private String createUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;


    @ApiModelProperty("更新人")
    private String updateUser;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private Date updateTime;


    @ApiModelProperty("排序")
    private Integer seq;

    @ApiModelProperty("关联category")
    private String categoryCode;


    /**
     * 获取业务类型
     */
    public String getPlanprojectType() {

        return pmsPlantype != null ? pmsPlantype.getCode() : "";
    }

    /**
     * 获取业务类型名称
     */
    public String getPlanprojectTypeName() {

        return pmsPlantype != null ? pmsPlantype.getName() : "";
    }


}

