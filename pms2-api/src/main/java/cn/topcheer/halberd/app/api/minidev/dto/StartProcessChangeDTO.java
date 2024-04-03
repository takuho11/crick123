package cn.topcheer.halberd.app.api.minidev.dto;


import cn.topcheer.pms2.api.annotation.FieldDes;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 发起流程DTO
 *
 * @author szs
 * @date 2023-11-21
 */
@Data
public class StartProcessChangeDTO {


    @Valid
    @NotBlank(message = "批次ID必填")
    @ApiModelProperty(value = "批次ID", required = true)
    private String batchId;


    @Valid
    @NotBlank(message = "业务类型必填")
    @ApiModelProperty(value = "业务类型", required = true)
    private String type;


    @Valid
    @NotBlank(message = "主表ID必填")
    @ApiModelProperty(value = "主表ID", required = true)
    private String mainId;

    @Valid
    @NotBlank(message = "流程编码")
    @ApiModelProperty(value = "主表ID", required = true)
    private String processDefKey;

    /**
     * 县推荐单位
     */
    @ApiModelProperty("县推荐单位")
    @TableField("COUNTRYCASEMANAGEMENT")
    @Column(columnDefinition = "COUNTRYCASEMANAGEMENT")
    private String countrycasemanagement;

    /**
     * 县推荐单位ID
     */
    @ApiModelProperty("县推荐单位ID")
    @TableField("COUNTRYCASEMANAGEMENTID")
    @Column(columnDefinition = "COUNTRYCASEMANAGEMENTID")
    private String countrycasemanagementid;

    /**
     * 市推荐单位
     */
    @ApiModelProperty("市推荐单位")
    @TableField("CITYCASEMANAGEMENT")
    @Column(columnDefinition = "CITYCASEMANAGEMENT")
    private String citycasemanagement;

    /**
     * 市推荐单位ID
     */
    @ApiModelProperty("市推荐单位ID")
    @TableField("CITYCASEMANAGEMENTID")
    @Column(columnDefinition = "CITYCASEMANAGEMENTID")
    private String citycasemanagementid;

    /**
     * 归口部门类型
     */
    @ApiModelProperty("归口部门类型")
    @TableField("CASEMANAGEMENTTYPE")
    @Column(columnDefinition = "CASEMANAGEMENTTYPE")
    @FieldDes(name = "casemanagementtype", lenth = "255", type = "String", memo = "归口部门类型")
    private String casemanagementtype;

    /**
     * 单位主体
     */
    @ApiModelProperty("单位主体")
    @TableField("ENTERPRISEID")
    private String enterpriseID;

    /**
     * 主体代码
     */
    @ApiModelProperty("主体代码")
    @TableField("creditcode")
    private String creditcode;

    /**
     * 主体名称
     */
    @ApiModelProperty("主体代码")
    @TableField("unitname")
    private String unitname;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @TableField("startdate")
    @FieldDes(name = "startdate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date startdate;

    /**
     * 工作国别或地区
     */
    @ApiModelProperty("工作国别或地区")
    @TableField("workplace")
    private String workplace;

    /**
     * 职务
     */
    @ApiModelProperty("职务")
    @TableField("post")
    private String post;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    @TableField("address")
    private String address;

    /**
     * 职称
     */
    @ApiModelProperty("职称")
    @TableField("DEGREE")
    @Column(columnDefinition = "DEGREE")
    @FieldDes(name = "degree", lenth = "255", type = "String", memo = "")
    private String degree;

    /**
     * 职称授予时间
     */
    @ApiModelProperty("职称授予时间")
    @TableField("degreedate")
    @FieldDes(name = "degreedate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date degreedate;

    /**
     * 职称系列
     */
    @ApiModelProperty("职称系列")
    @TableField("titledetailone")
    private String titledetailone;

    /**
     * 职称名称
     */
    @ApiModelProperty("职称名称")
    @TableField("titledetailtwo")
    private String titledetailtwo;

    /**
     * 在职情况
     */
    @ApiModelProperty("在职情况")
    @TableField("employerstatus")
    private String employerstatus;
}

