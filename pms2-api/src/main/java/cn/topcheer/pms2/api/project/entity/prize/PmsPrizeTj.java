/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2024-2-23 15:29:37
 */
package cn.topcheer.pms2.api.project.entity.prize;

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
 *
 */
@Entity
@Table(name = "PMS_PRIZE_TJ")
@ClassInfo(name = "", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizeTj {

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "1024", type = "String", memo = "")
    private String id;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("MAINID")
    @Column(columnDefinition = "MAINID")
    @FieldDes(name = "mainid", lenth = "1024", type = "String", memo = "")
    private String mainid;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SCISUM")
    @Column(columnDefinition = "SCISUM")
    @FieldDes(name = "scisum", type = "Integer", memo = "")
    private Integer scisum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("EISUM")
    @Column(columnDefinition = "EISUM")
    @FieldDes(name = "eisum", type = "Integer", memo = "")
    private Integer eisum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("TOTALSUM")
    @Column(columnDefinition = "TOTALSUM")
    @FieldDes(name = "totalsum", type = "Integer", memo = "")
    private Integer totalsum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("NEWTAXSUM")
    @Column(columnDefinition = "NEWTAXSUM")
    private String newtaxsum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SQFMZLSUM")
    @Column(columnDefinition = "SQFMZLSUM")
    @FieldDes(name = "sqfmzlsum", type = "Integer", memo = "")
    private Integer sqfmzlsum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SQZSCQSUM")
    @Column(columnDefinition = "SQZSCQSUM")
    @FieldDes(name = "sqzscqsum", type = "Integer", memo = "")
    private Integer sqzscqsum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SLSCISUM")
    @Column(columnDefinition = "SLSCISUM")
    @FieldDes(name = "slscisum", type = "Integer", memo = "")
    private Integer slscisum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SLEISUM")
    @Column(columnDefinition = "SLEISUM")
    @FieldDes(name = "sleisum", type = "Integer", memo = "")
    private Integer sleisum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SLTOTALSUM")
    @Column(columnDefinition = "SLTOTALSUM")
    @FieldDes(name = "sltotalsum", type = "Integer", memo = "")
    private Integer sltotalsum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("TYSCISUM")
    @Column(columnDefinition = "TYSCISUM")
    @FieldDes(name = "tyscisum", type = "Integer", memo = "")
    private Integer tyscisum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("TYEISUM")
    @Column(columnDefinition = "TYEISUM")
    @FieldDes(name = "tyeisum", type = "Integer", memo = "")
    private Integer tyeisum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("TYTOTALSUM")
    @Column(columnDefinition = "TYTOTALSUM")
    @FieldDes(name = "tytotalsum", type = "Integer", memo = "")
    private Integer tytotalsum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("INFLUENCESUM")
    @Column(columnDefinition = "INFLUENCESUM")
    private String influencesum;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "1024", type = "String", memo = "")
    private String type;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("INSTRUMENT_COUNT")
    @Column(columnDefinition = "INSTRUMENT_COUNT")
    @FieldDes(name = "instrument_count", type = "Integer", memo = "")
    private Integer instrumentCount;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("INSTRUMENT_TOTAL")
    @Column(columnDefinition = "INSTRUMENT_TOTAL")
    private String instrumentTotal;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SAVEDATE")
    @Column(columnDefinition = "SAVEDATE")
    @FieldDes(name = "savedate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;

    /**
     *  单位数量
     */
    @ApiModelProperty("单位数量")
    @TableField("UNIT_NUM")
    @Column(columnDefinition = "UNIT_NUM")
    @FieldDes(name = "unit_num", type = "BigDecimal", memo = "单位数量")
    private BigDecimal unitNum;

    /**
     *  员工数量
     */
    @ApiModelProperty("员工数量")
    @TableField("STAFF_NUM")
    @Column(columnDefinition = "STAFF_NUM")
    @FieldDes(name = "staff_num", type = "BigDecimal", memo = "员工数量")
    private BigDecimal staffNum;


    /**
     *  固定字段:排序
     */
    @ApiModelProperty("固定字段:排序")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
    private Integer seq;


    /**
     *
     */
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     */
    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    /**
     *
     */
    public Integer getScisum() {
        return scisum;
    }

    public void setScisum(Integer scisum) {
        this.scisum = scisum;
    }

    /**
     *
     */
    public Integer getEisum() {
        return eisum;
    }

    public void setEisum(Integer eisum) {
        this.eisum = eisum;
    }

    /**
     *
     */
    public Integer getTotalsum() {
        return totalsum;
    }

    public void setTotalsum(Integer totalsum) {
        this.totalsum = totalsum;
    }

    /**
     *
     */
    public String getNewtaxsum() {
        return newtaxsum;
    }

    public void setNewtaxsum(String newtaxsum) {
        this.newtaxsum = newtaxsum;
    }

    /**
     *
     */
    public Integer getSqfmzlsum() {
        return sqfmzlsum;
    }

    public void setSqfmzlsum(Integer sqfmzlsum) {
        this.sqfmzlsum = sqfmzlsum;
    }

    /**
     *
     */
    public Integer getSqzscqsum() {
        return sqzscqsum;
    }

    public void setSqzscqsum(Integer sqzscqsum) {
        this.sqzscqsum = sqzscqsum;
    }

    /**
     *
     */
    public Integer getSlscisum() {
        return slscisum;
    }

    public void setSlscisum(Integer slscisum) {
        this.slscisum = slscisum;
    }

    /**
     *
     */
    public Integer getSleisum() {
        return sleisum;
    }

    public void setSleisum(Integer sleisum) {
        this.sleisum = sleisum;
    }

    /**
     *
     */
    public Integer getSltotalsum() {
        return sltotalsum;
    }

    public void setSltotalsum(Integer sltotalsum) {
        this.sltotalsum = sltotalsum;
    }

    /**
     *
     */
    public Integer getTyscisum() {
        return tyscisum;
    }

    public void setTyscisum(Integer tyscisum) {
        this.tyscisum = tyscisum;
    }

    /**
     *
     */
    public Integer getTyeisum() {
        return tyeisum;
    }

    public void setTyeisum(Integer tyeisum) {
        this.tyeisum = tyeisum;
    }

    /**
     *
     */
    public Integer getTytotalsum() {
        return tytotalsum;
    }

    public void setTytotalsum(Integer tytotalsum) {
        this.tytotalsum = tytotalsum;
    }

    /**
     *
     */
    public String getInfluencesum() {
        return influencesum;
    }

    public void setInfluencesum(String influencesum) {
        this.influencesum = influencesum;
    }

    /**
     *
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     */
    public Integer getInstrumentCount() {
        return instrumentCount;
    }

    public void setInstrumentCount(Integer instrumentCount) {
        this.instrumentCount = instrumentCount;
    }

    /**
     *
     */
    public String getInstrumentTotal() {
        return instrumentTotal;
    }

    public void setInstrumentTotal(String instrumentTotal) {
        this.instrumentTotal = instrumentTotal;
    }

    /**
     *
     */
    public Date getSavedate() {
        return savedate;
    }

    public void setSavedate(Date savedate) {
        this.savedate = savedate;
    }

    /**
     *
     */
    public Date getUpdatelasttime() {
        return updatelasttime;
    }

    public void setUpdatelasttime(Date updatelasttime) {
        this.updatelasttime = updatelasttime;
    }

    /**
     *  单位数量
     */
    public BigDecimal getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(BigDecimal unitNum) {
        this.unitNum = unitNum;
    }

    /**
     *  员工数量
     */
    public BigDecimal getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(BigDecimal staffNum) {
        this.staffNum = staffNum;
    }


    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }


}