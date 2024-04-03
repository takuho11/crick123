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
 *  数据仓-人才-合作人员表
 */
@Entity
@Table(name = "BI_TALENT_RY")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiTalentRy {

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
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "")
    private String type;

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
    @TableField("NAME")
    @Column(columnDefinition = "NAME")
    @FieldDes(name = "name", lenth = "255", type = "String", memo = "")
    private String name;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("GENDER")
    @Column(columnDefinition = "GENDER")
    @FieldDes(name = "gender", lenth = "255", type = "String", memo = "")
    private String gender;

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
    @TableField("BIRTHDAY")
    @Column(columnDefinition = "BIRTHDAY")
    @FieldDes(name = "birthday", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date birthday;

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
    @TableField("TITLE")
    @Column(columnDefinition = "TITLE")
    @FieldDes(name = "title", lenth = "255", type = "String", memo = "")
    private String title;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("POST")
    @Column(columnDefinition = "POST")
    @FieldDes(name = "post", lenth = "255", type = "String", memo = "")
    private String post;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("WORKUNIT")
    @Column(columnDefinition = "WORKUNIT")
    @FieldDes(name = "workunit", lenth = "255", type = "String", memo = "")
    private String workunit;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CREDITCODE")
    @Column(columnDefinition = "CREDITCODE")
    @FieldDes(name = "creditcode", lenth = "255", type = "String", memo = "")
    private String creditcode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MOBILE")
    @Column(columnDefinition = "MOBILE")
    @FieldDes(name = "mobile", lenth = "255", type = "String", memo = "")
    private String mobile;

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
    @TableField("TITLEDETAILONE")
    @Column(columnDefinition = "TITLEDETAILONE")
    @FieldDes(name = "titledetailone", lenth = "255", type = "String", memo = "")
    private String titledetailone;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TITLEDETAILTWO")
    @Column(columnDefinition = "TITLEDETAILTWO")
    @FieldDes(name = "titledetailtwo", lenth = "255", type = "String", memo = "")
    private String titledetailtwo;


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
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
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
    public String getName (){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }

    /**
    *  
    */
    public String getGender (){
        return gender;
    }
    public void setGender (String gender){
        this.gender = gender;
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
    public Date getBirthday (){
        return birthday;
    }
    public void setBirthday (Date birthday){
        this.birthday = birthday;
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
    public String getTitle (){
        return title;
    }
    public void setTitle (String title){
        this.title = title;
    }

    /**
    *  
    */
    public String getPost (){
        return post;
    }
    public void setPost (String post){
        this.post = post;
    }

    /**
    *  
    */
    public String getWorkunit (){
        return workunit;
    }
    public void setWorkunit (String workunit){
        this.workunit = workunit;
    }

    /**
    *  
    */
    public String getCreditcode (){
        return creditcode;
    }
    public void setCreditcode (String creditcode){
        this.creditcode = creditcode;
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
    public String getTitledetailone (){
        return titledetailone;
    }
    public void setTitledetailone (String titledetailone){
        this.titledetailone = titledetailone;
    }

    /**
    *  
    */
    public String getTitledetailtwo (){
        return titledetailtwo;
    }
    public void setTitledetailtwo (String titledetailtwo){
        this.titledetailtwo = titledetailtwo;
    }

}