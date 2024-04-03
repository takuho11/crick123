/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024年3月12日 下午12:15:32
 */
package  cn.topcheer.pms2.api.project.entity.talent;

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
 *  科技人才-科技创新平台和人才情况表
 */
@Entity
@Table(name = "PMS_TALENT_STAFFEDU")
@ClassInfo(name = "科技人才-科技创新平台和人才情况表", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentStaffedu {

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
     *  省级及以上科技创新平台名称
     */
    @ApiModelProperty("省级及以上科技创新平台名称")
    @TableField("PLATFORM_NAME")
    @Column(columnDefinition = "PLATFORM_NAME")
    private String platformName;

    /**
     *  研发人员总数（人）
     */
    @ApiModelProperty("研发人员总数（人）")
    @TableField("PERSON_TOTAL")
    @Column(columnDefinition = "PERSON_TOTAL")
    private Integer personTotal;

    /**
     *  博士研究生（人）
     */
    @ApiModelProperty("博士研究生（人）")
    @TableField("DOCTORAL_TOTAL")
    @Column(columnDefinition = "DOCTORAL_TOTAL")
    private Integer doctoralTotal;

    /**
     *  硕士研究生（人）
     */
    @ApiModelProperty("硕士研究生（人）")
    @TableField("MASTER_TOTAL")
    @Column(columnDefinition = "MASTER_TOTAL")
    private Integer masterTotal;

    /**
     *  大学本科（人）
     */
    @ApiModelProperty("大学本科（人）")
    @TableField("BACHELOR_TOTAL")
    @Column(columnDefinition = "BACHELOR_TOTAL")
    private Integer bachelorTotal;

    /**
     *  正高级（人）
     */
    @ApiModelProperty("正高级（人）")
    @TableField("ZGJ_TOTAL")
    @Column(columnDefinition = "ZGJ_TOTAL")
    private Integer zgjTotal;

    /**
     *  副高级（人）
     */
    @ApiModelProperty("副高级（人）")
    @TableField("FGJ_TOTAL")
    @Column(columnDefinition = "FGJ_TOTAL")
    private Integer fgjTotal;

    /**
     *  中级（人）
     */
    @ApiModelProperty("中级（人）")
    @TableField("ZJ_TOTAL")
    @Column(columnDefinition = "ZJ_TOTAL")
    private Integer zjTotal;


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
    *  省级及以上科技创新平台名称
    */
    public String getPlatformName (){
        return platformName;
    }
    public void setPlatformName (String platformName){
        this.platformName = platformName;
    }

    /**
    *  研发人员总数（人）
    */
    public Integer getPersonTotal (){
        return personTotal;
    }
    public void setPersonTotal (Integer personTotal){
        this.personTotal = personTotal;
    }

    /**
    *  博士研究生（人）
    */
    public Integer getDoctoralTotal (){
        return doctoralTotal;
    }
    public void setDoctoralTotal (Integer doctoralTotal){
        this.doctoralTotal = doctoralTotal;
    }

    /**
    *  硕士研究生（人）
    */
    public Integer getMasterTotal (){
        return masterTotal;
    }
    public void setMasterTotal (Integer masterTotal){
        this.masterTotal = masterTotal;
    }

    /**
    *  大学本科（人）
    */
    public Integer getBachelorTotal (){
        return bachelorTotal;
    }
    public void setBachelorTotal (Integer bachelorTotal){
        this.bachelorTotal = bachelorTotal;
    }

    /**
    *  正高级（人）
    */
    public Integer getZgjTotal (){
        return zgjTotal;
    }
    public void setZgjTotal (Integer zgjTotal){
        this.zgjTotal = zgjTotal;
    }

    /**
    *  副高级（人）
    */
    public Integer getFgjTotal (){
        return fgjTotal;
    }
    public void setFgjTotal (Integer fgjTotal){
        this.fgjTotal = fgjTotal;
    }

    /**
    *  中级（人）
    */
    public Integer getZjTotal (){
        return zjTotal;
    }
    public void setZjTotal (Integer zjTotal){
        this.zjTotal = zjTotal;
    }

}