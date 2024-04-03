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
 *  数据仓-期刊任职表
 */
@Entity
@Table(name = "BI_ACH_AO")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiAchAo {

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
    @TableField("NAME")
    @Column(columnDefinition = "NAME")
    @FieldDes(name = "name", lenth = "255", type = "String", memo = "")
    private String name;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ORGANIZATIONNAME")
    @Column(columnDefinition = "ORGANIZATIONNAME")
    @FieldDes(name = "organizationname", lenth = "255", type = "String", memo = "")
    private String organizationname;

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
    @TableField("TERMOFOFFICE")
    @Column(columnDefinition = "TERMOFOFFICE")
    @FieldDes(name = "termofoffice", lenth = "255", type = "String", memo = "")
    private String termofoffice;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("STARTDATE")
    @Column(columnDefinition = "STARTDATE")
    @FieldDes(name = "startdate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date startdate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ENDDATE")
    @Column(columnDefinition = "ENDDATE")
    @FieldDes(name = "enddate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date enddate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CATEGORY")
    @Column(columnDefinition = "CATEGORY")
    @FieldDes(name = "category", lenth = "255", type = "String", memo = "")
    private String category;

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
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "")
    private String type;

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
    public String getName (){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }

    /**
    *  
    */
    public String getOrganizationname (){
        return organizationname;
    }
    public void setOrganizationname (String organizationname){
        this.organizationname = organizationname;
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
    public String getTermofoffice (){
        return termofoffice;
    }
    public void setTermofoffice (String termofoffice){
        this.termofoffice = termofoffice;
    }

    /**
    *  
    */
    public Date getStartdate (){
        return startdate;
    }
    public void setStartdate (Date startdate){
        this.startdate = startdate;
    }

    /**
    *  
    */
    public Date getEnddate (){
        return enddate;
    }
    public void setEnddate (Date enddate){
        this.enddate = enddate;
    }

    /**
    *  
    */
    public String getCategory (){
        return category;
    }
    public void setCategory (String category){
        this.category = category;
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
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
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

}