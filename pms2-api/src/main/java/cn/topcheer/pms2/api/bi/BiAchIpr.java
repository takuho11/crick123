/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 15:56:31
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
 *  数据仓-知识产权表
 */
@Entity
@Table(name = "BI_ACH_IPR")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiAchIpr {

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
    @TableField("PROJECTNAME")
    @Column(columnDefinition = "PROJECTNAME")
    @FieldDes(name = "projectname", lenth = "255", type = "String", memo = "")
    private String projectname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PATENTTYPE")
    @Column(columnDefinition = "PATENTTYPE")
    @FieldDes(name = "patenttype", lenth = "255", type = "String", memo = "")
    private String patenttype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PATENTNAME")
    @Column(columnDefinition = "PATENTNAME")
    @FieldDes(name = "patentname", lenth = "2000", type = "String", memo = "")
    private String patentname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("INVENTORS")
    @Column(columnDefinition = "INVENTORS")
    @FieldDes(name = "inventors", lenth = "2000", type = "String", memo = "")
    private String inventors;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PATENTEE")
    @Column(columnDefinition = "PATENTEE")
    @FieldDes(name = "patentee", lenth = "2000", type = "String", memo = "")
    private String patentee;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISINTERNATIONAL")
    @Column(columnDefinition = "ISINTERNATIONAL")
    @FieldDes(name = "isinternational", lenth = "255", type = "String", memo = "")
    private String isinternational;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("IPCNO")
    @Column(columnDefinition = "IPCNO")
    @FieldDes(name = "ipcno", lenth = "255", type = "String", memo = "")
    private String ipcno;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("INVENTORSRANK")
    @Column(columnDefinition = "INVENTORSRANK")
    @FieldDes(name = "inventorsrank", type = "Integer", memo = "")
    private Integer inventorsrank;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AUTHORIZEDCOUNTRY")
    @Column(columnDefinition = "AUTHORIZEDCOUNTRY")
    @FieldDes(name = "authorizedcountry", lenth = "255", type = "String", memo = "")
    private String authorizedcountry;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ACCESSTYPE")
    @Column(columnDefinition = "ACCESSTYPE")
    @FieldDes(name = "accesstype", lenth = "255", type = "String", memo = "")
    private String accesstype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("APPLICATIONNO")
    @Column(columnDefinition = "APPLICATIONNO")
    @FieldDes(name = "applicationno", lenth = "255", type = "String", memo = "")
    private String applicationno;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AUTHORIZATIONDATE")
    @Column(columnDefinition = "AUTHORIZATIONDATE")
    @FieldDes(name = "authorizationdate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date authorizationdate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("INDUSTRIALFIELD")
    @Column(columnDefinition = "INDUSTRIALFIELD")
    @FieldDes(name = "industrialfield", lenth = "255", type = "String", memo = "")
    private String industrialfield;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AUTHORIZATIONDEADLINE")
    @Column(columnDefinition = "AUTHORIZATIONDEADLINE")
    @FieldDes(name = "authorizationdeadline", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date authorizationdeadline;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TRANSFORMATION")
    @Column(columnDefinition = "TRANSFORMATION")
    @FieldDes(name = "transformation", lenth = "255", type = "String", memo = "")
    private String transformation;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("STATUS")
    @Column(columnDefinition = "STATUS")
    @FieldDes(name = "status", lenth = "255", type = "String", memo = "")
    private String status;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("RELATIONSHIP")
    @Column(columnDefinition = "RELATIONSHIP")
    @FieldDes(name = "relationship", lenth = "255", type = "String", memo = "")
    private String relationship;

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
    @TableField("MEMO")
    @Column(columnDefinition = "MEMO")
    @FieldDes(name = "memo", lenth = "255", type = "String", memo = "")
    private String memo;

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
    @TableField("NAME_TOP_THREE")
    @Column(columnDefinition = "NAME_TOP_THREE")
    @FieldDes(name = "nameTopThree", lenth = "255", type = "String", memo = "")
    private String nameTopThree;


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
    public String getProjectname (){
        return projectname;
    }
    public void setProjectname (String projectname){
        this.projectname = projectname;
    }

    /**
    *  
    */
    public String getPatenttype (){
        return patenttype;
    }
    public void setPatenttype (String patenttype){
        this.patenttype = patenttype;
    }

    /**
    *  
    */
    public String getPatentname (){
        return patentname;
    }
    public void setPatentname (String patentname){
        this.patentname = patentname;
    }

    /**
    *  
    */
    public String getInventors (){
        return inventors;
    }
    public void setInventors (String inventors){
        this.inventors = inventors;
    }

    /**
    *  
    */
    public String getPatentee (){
        return patentee;
    }
    public void setPatentee (String patentee){
        this.patentee = patentee;
    }

    /**
    *  
    */
    public String getIsinternational (){
        return isinternational;
    }
    public void setIsinternational (String isinternational){
        this.isinternational = isinternational;
    }

    /**
    *  
    */
    public String getIpcno (){
        return ipcno;
    }
    public void setIpcno (String ipcno){
        this.ipcno = ipcno;
    }

    /**
    *  
    */
    public Integer getInventorsrank (){
        return inventorsrank;
    }
    public void setInventorsrank (Integer inventorsrank){
        this.inventorsrank = inventorsrank;
    }

    /**
    *  
    */
    public String getAuthorizedcountry (){
        return authorizedcountry;
    }
    public void setAuthorizedcountry (String authorizedcountry){
        this.authorizedcountry = authorizedcountry;
    }

    /**
    *  
    */
    public String getAccesstype (){
        return accesstype;
    }
    public void setAccesstype (String accesstype){
        this.accesstype = accesstype;
    }

    /**
    *  
    */
    public String getApplicationno (){
        return applicationno;
    }
    public void setApplicationno (String applicationno){
        this.applicationno = applicationno;
    }

    /**
    *  
    */
    public Date getAuthorizationdate (){
        return authorizationdate;
    }
    public void setAuthorizationdate (Date authorizationdate){
        this.authorizationdate = authorizationdate;
    }

    /**
    *  
    */
    public String getIndustrialfield (){
        return industrialfield;
    }
    public void setIndustrialfield (String industrialfield){
        this.industrialfield = industrialfield;
    }

    /**
    *  
    */
    public Date getAuthorizationdeadline (){
        return authorizationdeadline;
    }
    public void setAuthorizationdeadline (Date authorizationdeadline){
        this.authorizationdeadline = authorizationdeadline;
    }

    /**
    *  
    */
    public String getTransformation (){
        return transformation;
    }
    public void setTransformation (String transformation){
        this.transformation = transformation;
    }

    /**
    *  
    */
    public String getStatus (){
        return status;
    }
    public void setStatus (String status){
        this.status = status;
    }

    /**
    *  
    */
    public String getRelationship (){
        return relationship;
    }
    public void setRelationship (String relationship){
        this.relationship = relationship;
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
    public String getMemo (){
        return memo;
    }
    public void setMemo (String memo){
        this.memo = memo;
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

    public String getNameTopThree() {
        return nameTopThree;
    }

    public void setNameTopThree(String nameTopThree) {
        this.nameTopThree = nameTopThree;
    }
}