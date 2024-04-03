/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024年3月8日 上午9:19:59
 */
package  cn.topcheer.pms2.api.sys;

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
 *  政务网过程保存表
 */
@Entity
@Table(name = "PMS_ZWW_PROCESS_SAVE")
@ClassInfo(name = "政务网过程保存表", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProcessSave {

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date savedate;

    /**
     *  固定字段:每次更新数据时间
     */
    @ApiModelProperty("固定字段:每次更新数据时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
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
     *  办件编号
     */
    @ApiModelProperty("办件编号")
    @TableField("PROJECT_NO")
    @Column(columnDefinition = "PROJECT_NO")
    private String projectNo;

    /**
     *  办理环节名称
     */
    @ApiModelProperty("办理环节名称")
    @TableField("PROCESS_NAME")
    @Column(columnDefinition = "PROCESS_NAME")
    private String processName;

    /**
     *  环节开始时间(以Sting格式传入接收后转换成时间格式yyyy-MM-dd hh:mm:ss)
     */
    @ApiModelProperty("环节开始时间(以Sting格式传入接收后转换成时间格式yyyy-MM-dd hh:mm:ss)")
    @TableField("EVENT_START_TIME")
    @Column(columnDefinition = "EVENT_START_TIME")
    @FieldDes(name = "event_start_time", type = "Date", memo = "环节开始时间(以Sting格式传入接收后转换成时间格式yyyy-MM-dd hh:mm:ss)")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date eventStartTime;

    /**
     *  环节结束时间(yyyy-MM-dd hh:mm:ss)
     */
    @ApiModelProperty("环节结束时间(yyyy-MM-dd hh:mm:ss)")
    @TableField("EVENT_END_TIME")
    @Column(columnDefinition = "EVENT_END_TIME")
    @FieldDes(name = "event_end_time", type = "Date", memo = "环节结束时间(yyyy-MM-dd hh:mm:ss)")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date eventEndTime;

    /**
     *  办件状态
     */
    @ApiModelProperty("办件状态")
    @TableField("BUSINESS_STATE")
    @Column(columnDefinition = "BUSINESS_STATE")
    private String businessState;

    /**
     *  办理人姓名编码
     */
    @ApiModelProperty("办理人姓名编码")
    @TableField("HANDLE_USER_CODE")
    @Column(columnDefinition = "HANDLE_USER_CODE")
    private String handleUserCode;

    /**
     *  办理区划编号
     */
    @ApiModelProperty("办理区划编号")
    @TableField("REGION_CODE")
    @Column(columnDefinition = "REGION_CODE")
    private String regionCode;

    /**
     *  环节办理承诺时限
     */
    @ApiModelProperty("环节办理承诺时限")
    @TableField("EVENT_LIMIT")
    @Column(columnDefinition = "EVENT_LIMIT")
    private String eventLimit;

    /**
     *  备注
     */
    @ApiModelProperty("备注")
    @TableField("REMARK")
    @Column(columnDefinition = "REMARK")
    private String remark;

    /**
     *  环节办理承诺时限单位
     */
    @ApiModelProperty("环节办理承诺时限单位")
    @TableField("EVENT_UNIT")
    @Column(columnDefinition = "EVENT_UNIT")
    private String eventUnit;

    /**
     *  办理部门名称
     */
    @ApiModelProperty("办理部门名称")
    @TableField("ORG_NAME")
    @Column(columnDefinition = "ORG_NAME")
    private String orgName;

    /**
     *  业务动作
     */
    @ApiModelProperty("业务动作")
    @TableField("EVENT_NAME")
    @Column(columnDefinition = "EVENT_NAME")
    private String eventName;

    /**
     *  办件阶段
     */
    @ApiModelProperty("办件阶段")
    @TableField("BUSINESS_STAGE")
    @Column(columnDefinition = "BUSINESS_STAGE")
    private String businessStage;

    /**
     *  办理人姓名
     */
    @ApiModelProperty("办理人姓名")
    @TableField("HANDLE_USER_NAME")
    @Column(columnDefinition = "HANDLE_USER_NAME")
    private String handleUserName;

    /**
     *  办理部门编号
     */
    @ApiModelProperty("办理部门编号")
    @TableField("ORG_CODE")
    @Column(columnDefinition = "ORG_CODE")
    private String orgCode;

    /**
     *  办理区划名称
     */
    @ApiModelProperty("办理区划名称")
    @TableField("REGION_NAME")
    @Column(columnDefinition = "REGION_NAME")
    private String regionName;

    /**
     *  记录唯一标识
     */
    @ApiModelProperty("记录唯一标识")
    @TableField("ROW_GUID")
    @Column(columnDefinition = "ROW_GUID")
    private String rowGuid;

    /**
     *  办理意见
     */
    @ApiModelProperty("办理意见")
    @TableField("HANDLE_EXPLAIN")
    @Column(columnDefinition = "HANDLE_EXPLAIN")
    private String handleExplain;


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
     *  办件编号
     */
    public String getProjectNo (){
        return projectNo;
    }
    public void setProjectNo (String projectNo){
        this.projectNo = projectNo;
    }

    /**
     *  办理环节名称
     */
    public String getProcessName (){
        return processName;
    }
    public void setProcessName (String processName){
        this.processName = processName;
    }

    /**
     *  环节开始时间(以Sting格式传入接收后转换成时间格式yyyy-MM-dd hh:mm:ss)
     */
    public Date getEventStartTime (){
        return eventStartTime;
    }
    public void setEventStartTime (Date eventStartTime){
        this.eventStartTime = eventStartTime;
    }

    /**
     *  环节结束时间(yyyy-MM-dd hh:mm:ss)
     */
    public Date getEventEndTime (){
        return eventEndTime;
    }
    public void setEventEndTime (Date eventEndTime){
        this.eventEndTime = eventEndTime;
    }

    /**
     *  办件状态
     */
    public String getBusinessState (){
        return businessState;
    }
    public void setBusinessState (String businessState){
        this.businessState = businessState;
    }

    /**
     *  办理人姓名编码
     */
    public String getHandleUserCode (){
        return handleUserCode;
    }
    public void setHandleUserCode (String handleUserCode){
        this.handleUserCode = handleUserCode;
    }

    /**
     *  办理区划编号
     */
    public String getRegionCode (){
        return regionCode;
    }
    public void setRegionCode (String regionCode){
        this.regionCode = regionCode;
    }

    /**
     *  环节办理承诺时限
     */
    public String getEventLimit (){
        return eventLimit;
    }
    public void setEventLimit (String eventLimit){
        this.eventLimit = eventLimit;
    }

    /**
     *  备注
     */
    public String getRemark (){
        return remark;
    }
    public void setRemark (String remark){
        this.remark = remark;
    }

    /**
     *  环节办理承诺时限单位
     */
    public String getEventUnit (){
        return eventUnit;
    }
    public void setEventUnit (String eventUnit){
        this.eventUnit = eventUnit;
    }

    /**
     *  办理部门名称
     */
    public String getOrgName (){
        return orgName;
    }
    public void setOrgName (String orgName){
        this.orgName = orgName;
    }

    /**
     *  业务动作
     */
    public String getEventName (){
        return eventName;
    }
    public void setEventName (String eventName){
        this.eventName = eventName;
    }

    /**
     *  办件阶段
     */
    public String getBusinessStage (){
        return businessStage;
    }
    public void setBusinessStage (String businessStage){
        this.businessStage = businessStage;
    }

    /**
     *  办理人姓名
     */
    public String getHandleUserName (){
        return handleUserName;
    }
    public void setHandleUserName (String handleUserName){
        this.handleUserName = handleUserName;
    }

    /**
     *  办理部门编号
     */
    public String getOrgCode (){
        return orgCode;
    }
    public void setOrgCode (String orgCode){
        this.orgCode = orgCode;
    }

    /**
     *  办理区划名称
     */
    public String getRegionName (){
        return regionName;
    }
    public void setRegionName (String regionName){
        this.regionName = regionName;
    }

    /**
     *  记录唯一标识
     */
    public String getRowGuid (){
        return rowGuid;
    }
    public void setRowGuid (String rowGuid){
        this.rowGuid = rowGuid;
    }

    /**
     *  办理意见
     */
    public String getHandleExplain (){
        return handleExplain;
    }
    public void setHandleExplain (String handleExplain){
        this.handleExplain = handleExplain;
    }

}