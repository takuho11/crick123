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
 *  数据仓-企业相关人员表
 */
@Entity
@Table(name = "BI_ENT_RY")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiEntRy {

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
    @TableField("RYTYPE")
    @Column(columnDefinition = "RYTYPE")
    @FieldDes(name = "rytype", lenth = "255", type = "String", memo = "")
    private String rytype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("NAME")
    @Column(columnDefinition = "NAME")
    @FieldDes(name = "name", lenth = "255", type = "String", memo = "")
    private String name;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EDUCATION")
    @Column(columnDefinition = "EDUCATION")
    @FieldDes(name = "education", lenth = "255", type = "String", memo = "")
    private String education;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("DEGREE")
    @Column(columnDefinition = "DEGREE")
    @FieldDes(name = "degree", lenth = "255", type = "String", memo = "")
    private String degree;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MOBILE")
    @Column(columnDefinition = "MOBILE")
    @FieldDes(name = "mobile", lenth = "255", type = "String", memo = "")
    private String mobile;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TELEPHONE")
    @Column(columnDefinition = "TELEPHONE")
    @FieldDes(name = "telephone", lenth = "255", type = "String", memo = "")
    private String telephone;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CERTIFICATENAME")
    @Column(columnDefinition = "CERTIFICATENAME")
    @FieldDes(name = "certificatename", lenth = "255", type = "String", memo = "")
    private String certificatename;

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
    @TableField("EMAIL")
    @Column(columnDefinition = "EMAIL")
    @FieldDes(name = "email", lenth = "255", type = "String", memo = "")
    private String email;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ADMINISTRATIVEDUTY")
    @Column(columnDefinition = "ADMINISTRATIVEDUTY")
    @FieldDes(name = "administrativeduty", lenth = "255", type = "String", memo = "")
    private String administrativeduty;

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
    @TableField("WQCHECK")
    @Column(columnDefinition = "WQCHECK")
    private String wqcheck;

    /**
     *  传真
     */
    @ApiModelProperty("传真")
    @TableField("FAX")
    @Column(columnDefinition = "FAX")
    @FieldDes(name = "fax", lenth = "255", type = "String", memo = "传真")
    private String fax;


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
    public String getRytype (){
        return rytype;
    }
    public void setRytype (String rytype){
        this.rytype = rytype;
    }

    /**
    *  
    */
    public String getName (){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }

    /**
    *  
    */
    public String getEducation (){
        return education;
    }
    public void setEducation (String education){
        this.education = education;
    }

    /**
    *  
    */
    public String getDegree (){
        return degree;
    }
    public void setDegree (String degree){
        this.degree = degree;
    }

    /**
    *  
    */
    public String getMobile (){
        return mobile;
    }
    public void setMobile (String mobile){
        this.mobile = mobile;
    }

    /**
    *  
    */
    public String getTelephone (){
        return telephone;
    }
    public void setTelephone (String telephone){
        this.telephone = telephone;
    }

    /**
    *  
    */
    public String getCertificatename (){
        return certificatename;
    }
    public void setCertificatename (String certificatename){
        this.certificatename = certificatename;
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
    public String getEmail (){
        return email;
    }
    public void setEmail (String email){
        this.email = email;
    }

    /**
    *  
    */
    public String getAdministrativeduty (){
        return administrativeduty;
    }
    public void setAdministrativeduty (String administrativeduty){
        this.administrativeduty = administrativeduty;
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
    public String getWqcheck (){
        return wqcheck;
    }
    public void setWqcheck (String wqcheck){
        this.wqcheck = wqcheck;
    }

    /**
    *  传真
    */
    public String getFax (){
        return fax;
    }
    public void setFax (String fax){
        this.fax = fax;
    }

}