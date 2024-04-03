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
 *  数据仓-股权信息
 */
@Entity
@Table(name = "BI_ENT_OS")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiEntOs {

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "40", type = "String", memo = "")
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
    @TableField("SHAREHOLDER")
    @Column(columnDefinition = "SHAREHOLDER")
    @FieldDes(name = "shareholder", lenth = "255", type = "String", memo = "")
    private String shareholder;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISFOUNDER")
    @Column(columnDefinition = "ISFOUNDER")
    @FieldDes(name = "isfounder", lenth = "255", type = "String", memo = "")
    private String isfounder;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EQUITYTYPE")
    @Column(columnDefinition = "EQUITYTYPE")
    @FieldDes(name = "equitytype", lenth = "255", type = "String", memo = "")
    private String equitytype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EQUITYRATIO")
    @Column(columnDefinition = "EQUITYRATIO")
    private String equityratio;

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
    @TableField("TOTALINVESTMENT")
    @Column(columnDefinition = "TOTALINVESTMENT")
    private String totalinvestment;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PERSONPERCENT")
    @Column(columnDefinition = "PERSONPERCENT")
    private String personpercent;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("FOREIGNPERCENT")
    @Column(columnDefinition = "FOREIGNPERCENT")
    private String foreignpercent;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("NATIONPERCENT")
    @Column(columnDefinition = "NATIONPERCENT")
    private String nationpercent;

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
    public String getShareholder (){
        return shareholder;
    }
    public void setShareholder (String shareholder){
        this.shareholder = shareholder;
    }

    /**
    *  
    */
    public String getIsfounder (){
        return isfounder;
    }
    public void setIsfounder (String isfounder){
        this.isfounder = isfounder;
    }

    /**
    *  
    */
    public String getEquitytype (){
        return equitytype;
    }
    public void setEquitytype (String equitytype){
        this.equitytype = equitytype;
    }

    /**
    *  
    */
    public String getEquityratio (){
        return equityratio;
    }
    public void setEquityratio (String equityratio){
        this.equityratio = equityratio;
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
    public String getTotalinvestment (){
        return totalinvestment;
    }
    public void setTotalinvestment (String totalinvestment){
        this.totalinvestment = totalinvestment;
    }

    /**
    *  
    */
    public String getPersonpercent (){
        return personpercent;
    }
    public void setPersonpercent (String personpercent){
        this.personpercent = personpercent;
    }

    /**
    *  
    */
    public String getForeignpercent (){
        return foreignpercent;
    }
    public void setForeignpercent (String foreignpercent){
        this.foreignpercent = foreignpercent;
    }

    /**
    *  
    */
    public String getNationpercent (){
        return nationpercent;
    }
    public void setNationpercent (String nationpercent){
        this.nationpercent = nationpercent;
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