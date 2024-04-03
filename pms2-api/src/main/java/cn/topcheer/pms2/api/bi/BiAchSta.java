/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 15:56:30
 */
package cn.topcheer.pms2.api.bi;

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
 *  数据仓-科技奖励表
 */
@Entity
@Table(name = "BI_ACH_STA")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiAchSta {

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "")
    private String id;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "")
    private Integer seq;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AWARDTYPE")
    @Column(columnDefinition = "AWARDTYPE")
    @FieldDes(name = "awardtype", lenth = "255", type = "String", memo = "")
    private String awardtype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AWARDPROJECTNAME")
    @Column(columnDefinition = "AWARDPROJECTNAME")
    @FieldDes(name = "awardprojectname", lenth = "255", type = "String", memo = "")
    private String awardprojectname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AWARDLEVEL")
    @Column(columnDefinition = "AWARDLEVEL")
    @FieldDes(name = "awardlevel", lenth = "255", type = "String", memo = "")
    private String awardlevel;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AWARDRANK")
    @Column(columnDefinition = "AWARDRANK")
    @FieldDes(name = "awardrank", lenth = "255", type = "String", memo = "")
    private String awardrank;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AWARDDEPARTMENT")
    @Column(columnDefinition = "AWARDDEPARTMENT")
    @FieldDes(name = "awarddepartment", lenth = "255", type = "String", memo = "")
    private String awarddepartment;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CERTIFICATENUMBER")
    @Column(columnDefinition = "CERTIFICATENUMBER")
    @FieldDes(name = "certificatenumber", lenth = "255", type = "String", memo = "")
    private String certificatenumber;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AWARDDATE")
    @Column(columnDefinition = "AWARDDATE")
    @FieldDes(name = "awarddate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date awarddate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("UNITAWARDSEQ")
    @Column(columnDefinition = "UNITAWARDSEQ")
    @FieldDes(name = "unitawardseq", lenth = "255", type = "String", memo = "")
    private String unitawardseq;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AWARDYEAR")
    @Column(columnDefinition = "AWARDYEAR")
    @FieldDes(name = "awardyear", lenth = "255", type = "String", memo = "")
    private String awardyear;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AWARDBONUS")
    @Column(columnDefinition = "AWARDBONUS")
    private String awardbonus;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "")
    private String type;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MAINID")
    @Column(columnDefinition = "MAINID")
    @FieldDes(name = "mainid", lenth = "255", type = "String", memo = "")
    private String mainid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    @FieldDes(name = "sourceid", lenth = "255", type = "String", memo = "")
    private String sourceid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISSHOW")
    @Column(columnDefinition = "ISSHOW")
    @FieldDes(name = "isshow", lenth = "255", type = "String", memo = "")
    private String isshow;

    /**
     *  获奖人多个用；隔开
     */
    @ApiModelProperty("获奖人多个用；隔开")
    @TableField("PSN_NAME")
    @Column(columnDefinition = "PSN_NAME")
    @FieldDes(name = "psn_name", lenth = "2000", type = "String", memo = "获奖人多个用；隔开")
    private String psnName;

    /**
     *  获奖单位名单以；隔开
     */
    @ApiModelProperty("获奖单位名单以；隔开")
    @TableField("ORG_NAME")
    @Column(columnDefinition = "ORG_NAME")
    @FieldDes(name = "org_name", lenth = "2000", type = "String", memo = "获奖单位名单以；隔开")
    private String orgName;

    /**
     *  统一社会信用代码
     */
    @ApiModelProperty("统一社会信用代码")
    @TableField("ORG_SHXY_NO")
    @Column(columnDefinition = "ORG_SHXY_NO")
    @FieldDes(name = "org_shxy_no", lenth = "400", type = "String", memo = "统一社会信用代码")
    private String orgShxyNo;

    /**
     *  提名单位提名专家
     */
    @ApiModelProperty("提名单位提名专家")
    @TableField("TM_OBJNAME")
    @Column(columnDefinition = "TM_OBJNAME")
    @FieldDes(name = "tm_objname", lenth = "400", type = "String", memo = "提名单位提名专家")
    private String tmObjname;

    /**
     *  现从事专业
     */
    @ApiModelProperty("现从事专业")
    @TableField("MAJOR")
    @Column(columnDefinition = "MAJOR")
    @FieldDes(name = "major", lenth = "400", type = "String", memo = "现从事专业")
    private String major;

    /**
     *  国籍或地区
     */
    @ApiModelProperty("国籍或地区")
    @TableField("COUNTRY_NAME")
    @Column(columnDefinition = "COUNTRY_NAME")
    @FieldDes(name = "country_name", lenth = "400", type = "String", memo = "国籍或地区")
    private String countryName;

    /**
     *  与广东省合作的主要单位
     */
    @ApiModelProperty("与广东省合作的主要单位")
    @TableField("HZ_ORGNAME")
    @Column(columnDefinition = "HZ_ORGNAME")
    @FieldDes(name = "hz_orgname", lenth = "400", type = "String", memo = "与广东省合作的主要单位")
    private String hzOrgname;

    /**
     *  所属学科
     */
    @ApiModelProperty("所属学科")
    @TableField("SUBJECT_NAME")
    @Column(columnDefinition = "SUBJECT_NAME")
    @FieldDes(name = "subject_name", lenth = "400", type = "String", memo = "所属学科")
    private String subjectName;

    /**
     *  数据更新时间
     */
    @ApiModelProperty("数据更新时间")
    @TableField("update_time")
    @Column(columnDefinition = "update_time")
    @FieldDes(name = "update_time", type = "Date", memo = "数据更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date updateTime;

    /**
     *  专题名称
     */
    @ApiModelProperty("专题名称")
    @TableField("GRANT_SUBJECT_NAME")
    @Column(columnDefinition = "GRANT_SUBJECT_NAME")
    @FieldDes(name = "grant_subject_name", lenth = "400", type = "String", memo = "专题名称")
    private String grantSubjectName;

    /**
     *  项目编码
     */
    @ApiModelProperty("项目编码")
    @TableField("PRP_CODE")
    @Column(columnDefinition = "PRP_CODE")
    @FieldDes(name = "prp_code", type = "Long", memo = "项目编码")
    private Long prpCode;

    /**
     *  保存时间
     */
    @ApiModelProperty("保存时间")
    @TableField("SAVEDATE")
    @Column(columnDefinition = "SAVEDATE")
    @FieldDes(name = "savedate", type = "Date", memo = "保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;

    /**
     *  最后一次更新时间
     */
    @ApiModelProperty("最后一次更新时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "最后一次更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("DRBJ")
    @Column(columnDefinition = "DRBJ")
    @FieldDes(name = "drbj", lenth = "255", type = "String", memo = "")
    private String drbj;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SUBJECT_CODE")
    @Column(columnDefinition = "SUBJECT_CODE")
    @FieldDes(name = "subject_code", lenth = "255", type = "String", memo = "")
    private String subjectCode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SUBJECT_TYPE")
    @Column(columnDefinition = "SUBJECT_TYPE")
    @FieldDes(name = "subject_type", lenth = "255", type = "String", memo = "")
    private String subjectType;


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
    *  
    */
    public Integer getSeq (){
        return seq;
    }
    public void setSeq (Integer seq){
        this.seq = seq;
    }

    /**
    *  
    */
    public String getAwardtype (){
        return awardtype;
    }
    public void setAwardtype (String awardtype){
        this.awardtype = awardtype;
    }

    /**
    *  
    */
    public String getAwardprojectname (){
        return awardprojectname;
    }
    public void setAwardprojectname (String awardprojectname){
        this.awardprojectname = awardprojectname;
    }

    /**
    *  
    */
    public String getAwardlevel (){
        return awardlevel;
    }
    public void setAwardlevel (String awardlevel){
        this.awardlevel = awardlevel;
    }

    /**
    *  
    */
    public String getAwardrank (){
        return awardrank;
    }
    public void setAwardrank (String awardrank){
        this.awardrank = awardrank;
    }

    /**
    *  
    */
    public String getAwarddepartment (){
        return awarddepartment;
    }
    public void setAwarddepartment (String awarddepartment){
        this.awarddepartment = awarddepartment;
    }

    /**
    *  
    */
    public String getCertificatenumber (){
        return certificatenumber;
    }
    public void setCertificatenumber (String certificatenumber){
        this.certificatenumber = certificatenumber;
    }

    /**
    *  
    */
    public Date getAwarddate (){
        return awarddate;
    }
    public void setAwarddate (Date awarddate){
        this.awarddate = awarddate;
    }

    /**
    *  
    */
    public String getUnitawardseq (){
        return unitawardseq;
    }
    public void setUnitawardseq (String unitawardseq){
        this.unitawardseq = unitawardseq;
    }

    /**
    *  
    */
    public String getAwardyear (){
        return awardyear;
    }
    public void setAwardyear (String awardyear){
        this.awardyear = awardyear;
    }

    /**
    *  
    */
    public String getAwardbonus (){
        return awardbonus;
    }
    public void setAwardbonus (String awardbonus){
        this.awardbonus = awardbonus;
    }

    /**
    *  
    */
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
    }

    /**
    *  
    */
    public String getMainid (){
        return mainid;
    }
    public void setMainid (String mainid){
        this.mainid = mainid;
    }

    /**
    *  
    */
    public String getSourceid (){
        return sourceid;
    }
    public void setSourceid (String sourceid){
        this.sourceid = sourceid;
    }

    /**
    *  
    */
    public String getIsshow (){
        return isshow;
    }
    public void setIsshow (String isshow){
        this.isshow = isshow;
    }

    /**
    *  获奖人多个用；隔开
    */
    public String getPsnName (){
        return psnName;
    }
    public void setPsnName (String psnName){
        this.psnName = psnName;
    }

    /**
    *  获奖单位名单以；隔开
    */
    public String getOrgName (){
        return orgName;
    }
    public void setOrgName (String orgName){
        this.orgName = orgName;
    }

    /**
    *  统一社会信用代码
    */
    public String getOrgShxyNo (){
        return orgShxyNo;
    }
    public void setOrgShxyNo (String orgShxyNo){
        this.orgShxyNo = orgShxyNo;
    }

    /**
    *  提名单位提名专家
    */
    public String getTmObjname (){
        return tmObjname;
    }
    public void setTmObjname (String tmObjname){
        this.tmObjname = tmObjname;
    }

    /**
    *  现从事专业
    */
    public String getMajor (){
        return major;
    }
    public void setMajor (String major){
        this.major = major;
    }

    /**
    *  国籍或地区
    */
    public String getCountryName (){
        return countryName;
    }
    public void setCountryName (String countryName){
        this.countryName = countryName;
    }

    /**
    *  与广东省合作的主要单位
    */
    public String getHzOrgname (){
        return hzOrgname;
    }
    public void setHzOrgname (String hzOrgname){
        this.hzOrgname = hzOrgname;
    }

    /**
    *  所属学科
    */
    public String getSubjectName (){
        return subjectName;
    }
    public void setSubjectName (String subjectName){
        this.subjectName = subjectName;
    }

    /**
    *  数据更新时间
    */
    public Date getUpdateTime (){
        return updateTime;
    }
    public void setUpdateTime (Date updateTime){
        this.updateTime = updateTime;
    }

    /**
    *  专题名称
    */
    public String getGrantSubjectName (){
        return grantSubjectName;
    }
    public void setGrantSubjectName (String grantSubjectName){
        this.grantSubjectName = grantSubjectName;
    }

    /**
    *  项目编码
    */
    public Long getPrpCode (){
        return prpCode;
    }
    public void setPrpCode (Long prpCode){
        this.prpCode = prpCode;
    }

    /**
    *  保存时间
    */
    public Date getSavedate (){
        return savedate;
    }
    public void setSavedate (Date savedate){
        this.savedate = savedate;
    }

    /**
    *  最后一次更新时间
    */
    public Date getUpdatelasttime (){
        return updatelasttime;
    }
    public void setUpdatelasttime (Date updatelasttime){
        this.updatelasttime = updatelasttime;
    }

    /**
    *  
    */
    public String getDrbj (){
        return drbj;
    }
    public void setDrbj (String drbj){
        this.drbj = drbj;
    }

    /**
    *  
    */
    public String getSubjectCode (){
        return subjectCode;
    }
    public void setSubjectCode (String subjectCode){
        this.subjectCode = subjectCode;
    }

    /**
    *  
    */
    public String getSubjectType (){
        return subjectType;
    }
    public void setSubjectType (String subjectType){
        this.subjectType = subjectType;
    }

}