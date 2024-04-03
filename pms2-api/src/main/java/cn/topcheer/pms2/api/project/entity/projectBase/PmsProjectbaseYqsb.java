/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024年1月6日 上午11:07:52
 */
package cn.topcheer.pms2.api.project.entity.projectBase;

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
 *  计划项目-仪器设备
 */
@Entity
@Table(name = "PMS_PROJECTBASE_YQSB")
@ClassInfo(name = "计划项目-仪器设备", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseYqsb {

    /**
     *  固定字段:唯一标识
     */
    @ApiModelProperty("固定字段:唯一标识")
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
     *  序号
     */
    @ApiModelProperty("序号")
    @TableField("NO")
    @Column(columnDefinition = "NO")
    private String no;

    /**
     *  仪器名称
     */
    @ApiModelProperty("仪器名称")
    @TableField("NAME")
    @Column(columnDefinition = "NAME")
    private String name;

    /**
     *  规格型号
     */
    @ApiModelProperty("规格型号")
    @TableField("MODEL")
    @Column(columnDefinition = "MODEL")
    private String model;

    /**
     *  数量
     */
    @ApiModelProperty("数量")
    @TableField("NUM")
    @Column(columnDefinition = "NUM")
    @FieldDes(name = "num", type = "BigDecimal", memo = "数量")
    private BigDecimal num;

    /**
     *  单价
     */
    @ApiModelProperty("单价")
    @TableField("PRIZE")
    @Column(columnDefinition = "PRIZE")
    @FieldDes(name = "prize", type = "BigDecimal", memo = "单价")
    private BigDecimal prize;

    /**
     *  金额（万元）
     */
    @ApiModelProperty("金额（万元）")
    @TableField("FUNDS")
    @Column(columnDefinition = "FUNDS")
    @FieldDes(name = "funds", type = "BigDecimal", memo = "金额（万元）")
    private BigDecimal funds;

    /**
     *  资金来源
     */
    @ApiModelProperty("资金来源")
    @TableField("FUNDS_SOURCE")
    @Column(columnDefinition = "FUNDS_SOURCE")
    private String fundsSource;

    /**
     *  用途
     */
    @ApiModelProperty("用途")
    @TableField("PURPOSE")
    @Column(columnDefinition = "PURPOSE")
    private String purpose;

    /**
     *  合计（万元）
     */
    @ApiModelProperty("合计（万元）")
    @TableField("TOTAL")
    @Column(columnDefinition = "TOTAL")
    @FieldDes(name = "total", type = "BigDecimal", memo = "合计（万元）")
    private BigDecimal total;


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
    *  序号
    */
    public String getNo (){
        return no;
    }
    public void setNo (String no){
        this.no = no;
    }

    /**
    *  仪器名称
    */
    public String getName (){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }

    /**
    *  规格型号
    */
    public String getModel (){
        return model;
    }
    public void setModel (String model){
        this.model = model;
    }

    /**
    *  数量
    */
    public BigDecimal getNum (){
        return num;
    }
    public void setNum (BigDecimal num){
        this.num = num;
    }

    /**
    *  单价
    */
    public BigDecimal getPrize (){
        return prize;
    }
    public void setPrize (BigDecimal prize){
        this.prize = prize;
    }

    /**
    *  金额（万元）
    */
    public BigDecimal getFunds (){
        return funds;
    }
    public void setFunds (BigDecimal funds){
        this.funds = funds;
    }

    /**
    *  资金来源
    */
    public String getFundsSource (){
        return fundsSource;
    }
    public void setFundsSource (String fundsSource){
        this.fundsSource = fundsSource;
    }

    /**
    *  用途
    */
    public String getPurpose (){
        return purpose;
    }
    public void setPurpose (String purpose){
        this.purpose = purpose;
    }

    /**
    *  合计（万元）
    */
    public BigDecimal getTotal (){
        return total;
    }
    public void setTotal (BigDecimal total){
        this.total = total;
    }

}