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
 * 数据仓-派生企业表
 */
@Entity
@Table(name = "BI_ACH_DTE")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiAchDte {

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
    @TableField("UNITNAME")
    @Column(columnDefinition = "UNITNAME")
    @FieldDes(name = "unitname", lenth = "255", type = "String", memo = "")
    private String unitname;

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
    @TableField("INVESTMENTFUND")
    @Column(columnDefinition = "INVESTMENTFUND")
    private String investmentfund;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EQUITYSITUATION")
    @Column(columnDefinition = "EQUITYSITUATION")
    @FieldDes(name = "equitysituation", lenth = "255", type = "String", memo = "")
    private String equitysituation;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MAINPRODUCT")
    @Column(columnDefinition = "MAINPRODUCT")
    @FieldDes(name = "mainproduct", lenth = "255", type = "String", memo = "")
    private String mainproduct;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("YEAR")
    @Column(columnDefinition = "YEAR")
    @FieldDes(name = "year", lenth = "255", type = "String", memo = "")
    private String year;

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
    @TableField("WORKTYPE")
    @Column(columnDefinition = "WORKTYPE")
    @FieldDes(name = "worktype", lenth = "255", type = "String", memo = "")
    private String worktype;

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
    public String getUnitname (){
        return unitname;
    }
    public void setUnitname (String unitname){
        this.unitname = unitname;
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
    public String getInvestmentfund (){
        return investmentfund;
    }
    public void setInvestmentfund (String investmentfund){
        this.investmentfund = investmentfund;
    }

    /**
    *  
    */
    public String getEquitysituation (){
        return equitysituation;
    }
    public void setEquitysituation (String equitysituation){
        this.equitysituation = equitysituation;
    }

    /**
    *  
    */
    public String getMainproduct (){
        return mainproduct;
    }
    public void setMainproduct (String mainproduct){
        this.mainproduct = mainproduct;
    }

    /**
    *  
    */
    public String getYear (){
        return year;
    }
    public void setYear (String year){
        this.year = year;
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
    public String getWorktype (){
        return worktype;
    }
    public void setWorktype (String worktype){
        this.worktype = worktype;
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