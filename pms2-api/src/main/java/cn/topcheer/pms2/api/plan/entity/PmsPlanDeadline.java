/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2024年3月13日 下午2:57:24
 */
package cn.topcheer.pms2.api.plan.entity;

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
 * 批次流程时间配置表
 */
@Entity
@Table(name = "PMS_PLAN_DEADLINE")
@ClassInfo(name = "批次流程时间配置表", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPlanDeadline {

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
    private String id;

    /**
     * 固定字段:数据类型
     */
    @ApiModelProperty("固定字段:数据类型")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    private String type;

    /**
     * 固定字段:关联主表id
     */
    @ApiModelProperty("固定字段:关联主表id")
    @TableField("MAINID")
    @Column(columnDefinition = "MAINID")
    private String mainid;

    /**
     * 固定字段:关联子表id
     */
    @ApiModelProperty("固定字段:关联子表id")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    private String sourceid;

    /**
     * 固定字段:第一次保存时间
     */
    @ApiModelProperty("固定字段:第一次保存时间")
    @TableField("SAVEDATE")
    @Column(columnDefinition = "SAVEDATE")
    @FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;

    /**
     * 固定字段:每次更新数据时间
     */
    @ApiModelProperty("固定字段:每次更新数据时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;

    /**
     * 固定字段:排序
     */
    @ApiModelProperty("固定字段:排序")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
    private Integer seq;

    /**
     * 固定字段:备注
     */
    @ApiModelProperty("固定字段:备注")
    @TableField("MEMO")
    @Column(columnDefinition = "MEMO")
    private String memo;

    /**
     * 关联业务 id,暂仅关联小批次
     */
    @ApiModelProperty("关联业务 id,暂仅关联小批次")
    @TableField("BATCH_ID")
    @Column(columnDefinition = "BATCH_ID")
    private String batchId;

    /**
     * 控制开关
     */
    @ApiModelProperty("控制开关")
    @TableField("ENABLE")
    @Column(columnDefinition = "ENABLE")
    private Boolean enable;

    /**
     * 节点编码
     */
    @ApiModelProperty("节点编码")
    @TableField("CODE")
    @Column(columnDefinition = "CODE")
    private String code;

    /**
     * 节点说明(中文解释业务)
     */
    @ApiModelProperty("节点说明(中文解释业务)")
    @TableField("DESCRIPTION")
    @Column(columnDefinition = "DESCRIPTION")
    private String description;

    /**
     * 开始时间（精确到秒）
     */
    @ApiModelProperty("开始时间（精确到秒）")
    @TableField("START_TIME")
    @Column(columnDefinition = "START_TIME")
    @FieldDes(name = "START_TIME", type = "Date", memo = "开始时间（精确到秒）")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date startTime;


    /**
     * 结束时间（精确到秒）
     */
    @ApiModelProperty("结束时间（精确到秒）")
    @TableField("END_TIME")
    @Column(columnDefinition = "END_TIME")
    @FieldDes(name = "END_TIME", type = "Date", memo = "结束时间（精确到秒）")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date endTime;


    /**
     * 提示语句(返给前台或流程)
     */
    @ApiModelProperty("提示语句(返给前台或流程)")
    @TableField("MESSAGE")
    @Column(columnDefinition = "MESSAGE")
    private String message;

    /**
     * 逻辑删除字段（0未删除，1删除）
     */
    @ApiModelProperty("逻辑删除字段（0未删除，1删除）")
    @TableField("IS_DELETE")
    @Column(columnDefinition = "IS_DELETE")
    private String isDelete;


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
     * 固定字段:数据类型
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 固定字段:关联主表id
     */
    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    /**
     * 固定字段:关联子表id
     */
    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    /**
     * 固定字段:第一次保存时间
     */
    public Date getSavedate() {
        return savedate;
    }

    public void setSavedate(Date savedate) {
        this.savedate = savedate;
    }

    /**
     * 固定字段:每次更新数据时间
     */
    public Date getUpdatelasttime() {
        return updatelasttime;
    }

    public void setUpdatelasttime(Date updatelasttime) {
        this.updatelasttime = updatelasttime;
    }

    /**
     * 固定字段:排序
     */
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 固定字段:备注
     */
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 关联业务 id,暂仅关联小批次
     */
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * 控制开关
     */
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * 节点编码
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 节点说明(中文解释业务)
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 提示语句(返给前台或流程)
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}