package cn.topcheer.pms2.api.plan.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 小批次绑定业流程中间表
 *
 * @author szs
 * @date 2023-11-21
 */
@Data
@Entity
@Table(name = "PMS_PLANPROJECTBATCH_IN_FLOW")
public class PmsPlanprojectbatchInFlow implements Serializable {


    @Id
    @ApiModelProperty("主键ID")
    private String id;


    @ApiModelProperty("小批次ID")
    private String pmsPlanprojectbatchId;


    @ApiModelProperty("业务类型")
    private String planprojectType;


    @ApiModelProperty("流程定义key")
    private String processDefKey;


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


}

