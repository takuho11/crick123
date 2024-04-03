/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024年1月11日 上午10:28:46
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
 *  数据仓-人/单位信息表
 */
@Entity
@Table(name = "BI_ACH_INFO")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiAchInfo {

    /**
     *  固定字段:唯一标识
     */
    @ApiModelProperty("固定字段:唯一标识")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
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
     *  固定字段:关联子表id
     */
    @ApiModelProperty("固定字段:关联子表id")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    @FieldDes(name = "sourceid", lenth = "255", type = "String", memo = "固定字段:关联子表id")
    private String sourceid;

    /**
     *  固定字段:数据类型
     */
    @ApiModelProperty("固定字段:数据类型")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "固定字段:数据类型")
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
    @TableField("CREDITCODE")
    @Column(columnDefinition = "CREDITCODE")
    @FieldDes(name = "creditcode", lenth = "255", type = "String", memo = "")
    private String creditcode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("RANK")
    @Column(columnDefinition = "RANK")
    @FieldDes(name = "rank", type = "Integer", memo = "")
    private Integer rank;

    /**
     *  固定字段:第一次保存时间
     */
    @ApiModelProperty("固定字段:第一次保存时间")
    @TableField("SAVEDATE")
    @Column(columnDefinition = "SAVEDATE")
    @FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;

    /**
     *  固定字段:每次更新数据时间
     */
    @ApiModelProperty("固定字段:每次更新数据时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;

    /**
     *  MEMO
     */
    @ApiModelProperty("MEMO")
    @TableField("MEMO")
    @Column(columnDefinition = "MEMO")
    private String memo;


    /**
    *  固定字段:唯一标识
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
    *  固定字段:关联子表id
    */
    public String getSourceid (){
        return sourceid;
    }
    public void setSourceid (String sourceid){
        this.sourceid = sourceid;
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
    public String getCreditcode (){
        return creditcode;
    }
    public void setCreditcode (String creditcode){
        this.creditcode = creditcode;
    }

    /**
    *  
    */
    public Integer getRank (){
        return rank;
    }
    public void setRank (Integer rank){
        this.rank = rank;
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
    *  MEMO
    */
    public String getMemo (){
        return memo;
    }
    public void setMemo (String memo){
        this.memo = memo;
    }

}