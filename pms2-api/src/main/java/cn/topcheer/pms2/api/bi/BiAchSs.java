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
 *  数据仓-标准制定表
 */
@Entity
@Table(name = "BI_ACH_SS")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiAchSs {

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
    @TableField("STANDARDNO")
    @Column(columnDefinition = "STANDARDNO")
    @FieldDes(name = "standardno", lenth = "255", type = "String", memo = "")
    private String standardno;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("STANDARDNAME")
    @Column(columnDefinition = "STANDARDNAME")
    @FieldDes(name = "standardname", lenth = "255", type = "String", memo = "")
    private String standardname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("STANDARDTYPE")
    @Column(columnDefinition = "STANDARDTYPE")
    @FieldDes(name = "standardtype", lenth = "255", type = "String", memo = "")
    private String standardtype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ANNOUNCEMENTDATE")
    @Column(columnDefinition = "ANNOUNCEMENTDATE")
    @FieldDes(name = "announcementdate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date announcementdate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EXECUTIONDATE")
    @Column(columnDefinition = "EXECUTIONDATE")
    @FieldDes(name = "executiondate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date executiondate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("HOSTANDPARTICIPATE")
    @Column(columnDefinition = "HOSTANDPARTICIPATE")
    @FieldDes(name = "hostandparticipate", lenth = "255", type = "String", memo = "")
    private String hostandparticipate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("UNITRANK")
    @Column(columnDefinition = "UNITRANK")
    @FieldDes(name = "unitrank", type = "Integer", memo = "")
    private Integer unitrank;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISEFFECTIVE")
    @Column(columnDefinition = "ISEFFECTIVE")
    @FieldDes(name = "iseffective", lenth = "255", type = "String", memo = "")
    private String iseffective;

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
    public String getStandardno (){
        return standardno;
    }
    public void setStandardno (String standardno){
        this.standardno = standardno;
    }

    /**
    *  
    */
    public String getStandardname (){
        return standardname;
    }
    public void setStandardname (String standardname){
        this.standardname = standardname;
    }

    /**
    *  
    */
    public String getStandardtype (){
        return standardtype;
    }
    public void setStandardtype (String standardtype){
        this.standardtype = standardtype;
    }

    /**
    *  
    */
    public Date getAnnouncementdate (){
        return announcementdate;
    }
    public void setAnnouncementdate (Date announcementdate){
        this.announcementdate = announcementdate;
    }

    /**
    *  
    */
    public Date getExecutiondate (){
        return executiondate;
    }
    public void setExecutiondate (Date executiondate){
        this.executiondate = executiondate;
    }

    /**
    *  
    */
    public String getHostandparticipate (){
        return hostandparticipate;
    }
    public void setHostandparticipate (String hostandparticipate){
        this.hostandparticipate = hostandparticipate;
    }

    /**
    *  
    */
    public Integer getUnitrank (){
        return unitrank;
    }
    public void setUnitrank (Integer unitrank){
        this.unitrank = unitrank;
    }

    /**
    *  
    */
    public String getIseffective (){
        return iseffective;
    }
    public void setIseffective (String iseffective){
        this.iseffective = iseffective;
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