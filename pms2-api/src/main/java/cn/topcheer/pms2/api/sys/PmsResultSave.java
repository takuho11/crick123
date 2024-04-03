/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024年3月8日 上午9:19:59
 */
package  cn.topcheer.pms2.api.sys;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  政务网调用结果记录表
 */
@Entity
@Table(name = "PMS_ZWW_RESULT_SAVE")
@ClassInfo(name = "政务网调用结果记录表", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsResultSave {

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
    private String id;

    /**
     *  固定字段:数据类型
     */
    @ApiModelProperty("固定字段:数据类型")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    private String type;

    /**
     *  固定字段:关联主表id
     */
    @ApiModelProperty("固定字段:关联主表id")
    @TableField("MAINID")
    @Column(columnDefinition = "MAINID")
    private String mainid;

    /**
     *  固定字段:关联子表id
     */
    @ApiModelProperty("固定字段:关联子表id")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    private String sourceid;

    /**
     *  固定字段:第一次保存时间
     */
    @ApiModelProperty("固定字段:第一次保存时间")
    @TableField("SAVEDATE")
    @Column(columnDefinition = "SAVEDATE")
    @FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date savedate;

    /**
     *  固定字段:每次更新数据时间
     */
    @ApiModelProperty("固定字段:每次更新数据时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updatelasttime;

    /**
     *  固定字段:排序
     */
    @ApiModelProperty("固定字段:排序")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
    private Integer seq;

    /**
     *  固定字段:备注
     */
    @ApiModelProperty("固定字段:备注")
    @TableField("MEMO")
    @Column(columnDefinition = "MEMO")
    private String memo;

    /**
     *  办结部门名称
     */
    @ApiModelProperty("办结部门名称")
    @TableField("ORG_NAME")
    @Column(columnDefinition = "ORG_NAME")
    private String orgName;

    /**
     *  办理结果
     */
    @ApiModelProperty("办理结果")
    @TableField("RESULT_TYPE")
    @Column(columnDefinition = "RESULT_TYPE")
    private String resultType;

    /**
     *  办结人姓名编码
     */
    @ApiModelProperty("办结人姓名编码")
    @TableField("HANDLE_USER_CODE")
    @Column(columnDefinition = "HANDLE_USER_CODE")
    private String handleUserCode;

    /**
     *  办件结果描述
     */
    @ApiModelProperty("办件结果描述")
    @TableField("RESULT_EXPLAIN")
    @Column(columnDefinition = "RESULT_EXPLAIN")
    private String resultExplain;

    /**
     *  办结部门编号
     */
    @ApiModelProperty("办结部门编号")
    @TableField("ORG_CODE")
    @Column(columnDefinition = "ORG_CODE")
    private String orgCode;

    /**
     *  办结区划名称
     */
    @ApiModelProperty("办结区划名称")
    @TableField("REGION_NAME")
    @Column(columnDefinition = "REGION_NAME")
    @FieldDes(name = "region_name", type = "String", memo = "办结区划名称")
    private String regionName;

    /**
     *  办结人员姓名
     */
    @ApiModelProperty("办结人员姓名")
    @TableField("HANDLE_USER_NAME")
    @Column(columnDefinition = "HANDLE_USER_NAME")
    private String handleUserName;

    /**
     *  办结时间(以Sting格式传入接 yyyy-MM-ddhh:mm:ss收后转换成时间格式)
     */
    @ApiModelProperty("办结时间(以Sting格式传入接 yyyy-MM-ddhh:mm:ss收后转换成时间格式)")
    @TableField("RESULT_DATE")
    @Column(columnDefinition = "RESULT_DATE")
    @FieldDes(name = "result_date", type = "Date", memo = "办结时间(以Sting格式传入接 yyyy-MM-ddhh:mm:ss收后转换成时间格式)")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date resultDate;

    /**
     *  办结区划编号
     */
    @ApiModelProperty("办结区划编号")
    @TableField("REGION_CODE")
    @Column(columnDefinition = "REGION_CODE")
    private String regionCode;

    /**
     *  办件编号
     */
    @ApiModelProperty("办件编号")
    @TableField("PROJECT_NO")
    @Column(columnDefinition = "PROJECT_NO")
    private String projectNo;

    /**
     *  备注
     */
    @ApiModelProperty("备注")
    @TableField("REMARK")
    @Column(columnDefinition = "REMARK")
    private String remark;

    /**
     *  记录唯一标识
     */
    @ApiModelProperty("记录唯一标识")
    @TableField("ROW_GUID")
    @Column(columnDefinition = "ROW_GUID")
    private String rowGuid;


    /**
     *
     */
    @Id
    public String getId (){
        return id;
    }
    public void setId (String id){
        this.id = id;
    }

    /**
     *  固定字段:数据类型
     */
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
    }

    /**
     *  固定字段:关联主表id
     */
    public String getMainid (){
        return mainid;
    }
    public void setMainid (String mainid){
        this.mainid = mainid;
    }

    /**
     *  固定字段:关联子表id
     */
    public String getSourceid (){
        return sourceid;
    }
    public void setSourceid (String sourceid){
        this.sourceid = sourceid;
    }

    /**
     *  固定字段:第一次保存时间
     */
    public Date getSavedate (){
        return savedate;
    }
    public void setSavedate (Date savedate){
        this.savedate = savedate;
    }

    /**
     *  固定字段:每次更新数据时间
     */
    public Date getUpdatelasttime (){
        return updatelasttime;
    }
    public void setUpdatelasttime (Date updatelasttime){
        this.updatelasttime = updatelasttime;
    }

    /**
     *  固定字段:排序
     */
    public Integer getSeq (){
        return seq;
    }
    public void setSeq (Integer seq){
        this.seq = seq;
    }

    /**
     *  固定字段:备注
     */
    public String getMemo (){
        return memo;
    }
    public void setMemo (String memo){
        this.memo = memo;
    }

    /**
     *  办结部门名称
     */
    public String getOrgName (){
        return orgName;
    }
    public void setOrgName (String orgName){
        this.orgName = orgName;
    }

    /**
     *  办理结果
     */
    public String getResultType (){
        return resultType;
    }
    public void setResultType (String resultType){
        this.resultType = resultType;
    }

    /**
     *  办结人姓名编码
     */
    public String getHandleUserCode (){
        return handleUserCode;
    }
    public void setHandleUserCode (String handleUserCode){
        this.handleUserCode = handleUserCode;
    }

    /**
     *  办件结果描述
     */
    public String getResultExplain (){
        return resultExplain;
    }
    public void setResultExplain (String resultExplain){
        this.resultExplain = resultExplain;
    }

    /**
     *  办结部门编号
     */
    public String getOrgCode (){
        return orgCode;
    }
    public void setOrgCode (String orgCode){
        this.orgCode = orgCode;
    }

    /**
     *  办结区划名称
     */
    public String getRegionName (){
        return regionName;
    }
    public void setRegionName (String regionName){
        this.regionName = regionName;
    }

    /**
     *  办结人员姓名
     */
    public String getHandleUserName (){
        return handleUserName;
    }
    public void setHandleUserName (String handleUserName){
        this.handleUserName = handleUserName;
    }

    /**
     *  办结时间(以Sting格式传入接 yyyy-MM-ddhh:mm:ss收后转换成时间格式)
     */
    public Date getResultDate (){
        return resultDate;
    }
    public void setResultDate (Date resultDate){
        this.resultDate = resultDate;
    }

    /**
     *  办结区划编号
     */
    public String getRegionCode (){
        return regionCode;
    }
    public void setRegionCode (String regionCode){
        this.regionCode = regionCode;
    }

    /**
     *  办件编号
     */
    public String getProjectNo (){
        return projectNo;
    }
    public void setProjectNo (String projectNo){
        this.projectNo = projectNo;
    }

    /**
     *  备注
     */
    public String getRemark (){
        return remark;
    }
    public void setRemark (String remark){
        this.remark = remark;
    }

    /**
     *  记录唯一标识
     */
    public String getRowGuid (){
        return rowGuid;
    }
    public void setRowGuid (String rowGuid){
        this.rowGuid = rowGuid;
    }

}