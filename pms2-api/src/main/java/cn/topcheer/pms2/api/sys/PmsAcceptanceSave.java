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
 *  政务网受理保存表
 */
@Entity
@Table(name = "PMS_ZWW_ACCEPTANCE_SAVE")
@ClassInfo(name = "政务网受理保存表", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsAcceptanceSave {

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
     *  申请人证件号码
     */
    @ApiModelProperty("申请人证件号码")
    @TableField("APPLYER_PAGE_CODE")
    @Column(columnDefinition = "APPLYER_PAGE_CODE")
    private String applyerPageCode;

    /**
     *  记录唯一标识
     */
    @ApiModelProperty("记录唯一标识")
    @TableField("ROW_GUID")
    @Column(columnDefinition = "ROW_GUID")
    private String rowGuid;

    /**
     *  办件类型
     */
    @ApiModelProperty("办件类型")
    @TableField("PROJECT_TYPE")
    @Column(columnDefinition = "PROJECT_TYPE")
    private String projectType;

    /**
     *  数据系统来源
     */
    @ApiModelProperty("数据系统来源")
    @TableField("DATA_FROM")
    @Column(columnDefinition = "DATA_FROM")
    private String dataFrom;

    /**
     *  通讯地址
     */
    @ApiModelProperty("通讯地址")
    @TableField("ADDRESS")
    @Column(columnDefinition = "ADDRESS")
    private String address;

    /**
     *  法定时限
     */
    @ApiModelProperty("法定时限")
    @TableField("TIME_LIMIT")
    @Column(columnDefinition = "TIME_LIMIT")
    @FieldDes(name = "time_limit", type = "Integer", memo = "法定时限")
    private Integer timeLimit;

    /**
     *  事项办理承诺时限单位
     */
    @ApiModelProperty("事项办理承诺时限单位")
    @TableField("PROMISETIME_UNIT")
    @Column(columnDefinition = "PROMISETIME_UNIT")
    private String promisetimeUnit;

    /**
     *  受理时间 (yyyy-MM-dd hh:mm:ss)
     */
    @ApiModelProperty("受理时间 (yyyy-MM-dd hh:mm:ss)")
    @TableField("ACCEPT_DATE")
    @Column(columnDefinition = "ACCEPT_DATE")
    @FieldDes(name = "accept_date", type = "Date", memo = "受理时间 (yyyy-MM-dd hh:mm:ss)")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date acceptDate;

    /**
     *  事项所属部门名称
     */
    @ApiModelProperty("事项所属部门名称")
    @TableField("ITEM_ORGAN_NAME")
    @Column(columnDefinition = "ITEM_ORGAN_NAME")
    private String itemOrganName;

    /**
     *  受理人姓名
     */
    @ApiModelProperty("受理人姓名")
    @TableField("ACCEPT_NAME")
    @Column(columnDefinition = "ACCEPT_NAME")
    private String acceptName;

    /**
     *  联系人/代理人证件号码
     */
    @ApiModelProperty("联系人/代理人证件号码")
    @TableField("CONTACT_CODE")
    @Column(columnDefinition = "CONTACT_CODE")
    private String contactCode;

    /**
     *  事项编码
     */
    @ApiModelProperty("事项编码")
    @TableField("TASK_ITEM_CODE")
    @Column(columnDefinition = "TASK_ITEM_CODE")
    private String taskItemCode;

    /**
     *  事项名称
     */
    @ApiModelProperty("事项名称")
    @TableField("TASK_NAME")
    @Column(columnDefinition = "TASK_NAME")
    private String taskName;

    /**
     *  联系人/代理人手机号码
     */
    @ApiModelProperty("联系人/代理人手机号码")
    @TableField("CONTACT_MOBILE")
    @Column(columnDefinition = "CONTACT_MOBILE")
    private String contactMobile;

    /**
     *  申请人证件类型
     */
    @ApiModelProperty("申请人证件类型")
    @TableField("APPLYER_PAGE_TYPE")
    @Column(columnDefinition = "APPLYER_PAGE_TYPE")
    private String applyerPageType;

    /**
     *  受理人id
     */
    @ApiModelProperty("受理人id")
    @TableField("ACCEPT_PERSON_ID")
    @Column(columnDefinition = "ACCEPT_PERSON_ID")
    private String acceptPersonId;

    /**
     *  申请类型
     */
    @ApiModelProperty("申请类型")
    @TableField("APPLY_TYPE")
    @Column(columnDefinition = "APPLY_TYPE")
    private String applyType;

    /**
     *  申报途径
     */
    @ApiModelProperty("申报途径")
    @TableField("APPLY_CHANNEL")
    @Column(columnDefinition = "APPLY_CHANNEL")
    private String applyChannel;

    /**
     *  数据来源
     */
    @ApiModelProperty("数据来源")
    @TableField("DATA_SOURCE")
    @Column(columnDefinition = "DATA_SOURCE")
    private String dataSource;

    /**
     *  申请人名称
     */
    @ApiModelProperty("申请人名称")
    @TableField("APPLYER_NAME")
    @Column(columnDefinition = "APPLYER_NAME")
    private String applyerName;

    /**
     *  联系人/代理人姓名
     */
    @ApiModelProperty("联系人/代理人姓名")
    @TableField("CONTACT_NAME")
    @Column(columnDefinition = "CONTACT_NAME")
    private String contactName;

    /**
     *  事项所属部门
     */
    @ApiModelProperty("事项所属部门")
    @TableField("ITEM_ORGAN_CODE")
    @Column(columnDefinition = "ITEM_ORGAN_CODE")
    private String itemOrganCode;

    /**
     *  事项办理承诺时限
     */
    @ApiModelProperty("事项办理承诺时限")
    @TableField("PROMISETIME_LIMIT")
    @Column(columnDefinition = "PROMISETIME_LIMIT")
    @FieldDes(name = "promisetime_limit", type = "Integer", memo = "事项办理承诺时限")
    private Integer promisetimeLimit;

    /**
     *  申请时间（yyyy-MM-dd hh:mm:ss）
     */
    @ApiModelProperty("申请时间（yyyy-MM-dd hh:mm:ss）")
    @TableField("APPLY_DATE")
    @Column(columnDefinition = "APPLY_DATE")
    @FieldDes(name = "apply_date", type = "Date", memo = "申请时间（yyyy-MM-dd hh:mm:ss）")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date applyDate;

    /**
     *  事项所属区划名称
     */
    @ApiModelProperty("事项所属区划名称")
    @TableField("ITEM_REGION_NAME")
    @Column(columnDefinition = "ITEM_REGION_NAME")
    private String itemRegionName;

    /**
     *  联系人/代理人证件类型
     */
    @ApiModelProperty("联系人/代理人证件类型")
    @TableField("CONTACT_TYPE")
    @Column(columnDefinition = "CONTACT_TYPE")
    private String contactType;

    /**
     *  事项办件唯一编号
     */
    @ApiModelProperty("事项办件唯一编号")
    @TableField("PROJECT_NO")
    @Column(columnDefinition = "PROJECT_NO")
    private String projectNo;

    /**
     *  事项所属区划
     */
    @ApiModelProperty("事项所属区划")
    @TableField("ITEM_REGION_CODE")
    @Column(columnDefinition = "ITEM_REGION_CODE")
    private String itemRegionCode;

    /**
     *  申请人类型
     */
    @ApiModelProperty("申请人类型")
    @TableField("APPLYER_TYPE")
    @Column(columnDefinition = "APPLYER_TYPE")
    private String applyerType;

    /**
     *  法定代表人
     */
    @ApiModelProperty("法定代表人")
    @TableField("LEREP")
    @Column(columnDefinition = "LEREP")
    private String lerep;

    /**
     *  规定办理时限的单位
     */
    @ApiModelProperty("规定办理时限的单位")
    @TableField("TIME_UNIT")
    @Column(columnDefinition = "TIME_UNIT")
    private String timeUnit;


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
     *  申请人证件号码
     */
    public String getApplyerPageCode (){
        return applyerPageCode;
    }
    public void setApplyerPageCode (String applyerPageCode){
        this.applyerPageCode = applyerPageCode;
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
     *  办件类型
     */
    public String getProjectType (){
        return projectType;
    }
    public void setProjectType (String projectType){
        this.projectType = projectType;
    }

    /**
     *  数据系统来源
     */
    public String getDataFrom (){
        return dataFrom;
    }
    public void setDataFrom (String dataFrom){
        this.dataFrom = dataFrom;
    }

    /**
     *  通讯地址
     */
    public String getAddress (){
        return address;
    }
    public void setAddress (String address){
        this.address = address;
    }

    /**
     *  法定时限
     */
    public Integer getTimeLimit (){
        return timeLimit;
    }
    public void setTimeLimit (Integer timeLimit){
        this.timeLimit = timeLimit;
    }

    /**
     *  事项办理承诺时限单位
     */
    public String getPromisetimeUnit (){
        return promisetimeUnit;
    }
    public void setPromisetimeUnit (String promisetimeUnit){
        this.promisetimeUnit = promisetimeUnit;
    }

    /**
     *  受理时间 (yyyy-MM-dd hh:mm:ss)
     */
    public Date getAcceptDate (){
        return acceptDate;
    }
    public void setAcceptDate (Date acceptDate){
        this.acceptDate = acceptDate;
    }

    /**
     *  事项所属部门名称
     */
    public String getItemOrganName (){
        return itemOrganName;
    }
    public void setItemOrganName (String itemOrganName){
        this.itemOrganName = itemOrganName;
    }

    /**
     *  受理人姓名
     */
    public String getAcceptName (){
        return acceptName;
    }
    public void setAcceptName (String acceptName){
        this.acceptName = acceptName;
    }

    /**
     *  联系人/代理人证件号码
     */
    public String getContactCode (){
        return contactCode;
    }
    public void setContactCode (String contactCode){
        this.contactCode = contactCode;
    }

    /**
     *  事项编码
     */
    public String getTaskItemCode (){
        return taskItemCode;
    }
    public void setTaskItemCode (String taskItemCode){
        this.taskItemCode = taskItemCode;
    }

    /**
     *  事项名称
     */
    public String getTaskName (){
        return taskName;
    }
    public void setTaskName (String taskName){
        this.taskName = taskName;
    }

    /**
     *  联系人/代理人手机号码
     */
    public String getContactMobile (){
        return contactMobile;
    }
    public void setContactMobile (String contactMobile){
        this.contactMobile = contactMobile;
    }

    /**
     *  申请人证件类型
     */
    public String getApplyerPageType (){
        return applyerPageType;
    }
    public void setApplyerPageType (String applyerPageType){
        this.applyerPageType = applyerPageType;
    }

    /**
     *  受理人id
     */
    public String getAcceptPersonId (){
        return acceptPersonId;
    }
    public void setAcceptPersonId (String acceptPersonId){
        this.acceptPersonId = acceptPersonId;
    }

    /**
     *  申请类型
     */
    public String getApplyType (){
        return applyType;
    }
    public void setApplyType (String applyType){
        this.applyType = applyType;
    }

    /**
     *  申报途径
     */
    public String getApplyChannel (){
        return applyChannel;
    }
    public void setApplyChannel (String applyChannel){
        this.applyChannel = applyChannel;
    }

    /**
     *  数据来源
     */
    public String getDataSource (){
        return dataSource;
    }
    public void setDataSource (String dataSource){
        this.dataSource = dataSource;
    }

    /**
     *  申请人名称
     */
    public String getApplyerName (){
        return applyerName;
    }
    public void setApplyerName (String applyerName){
        this.applyerName = applyerName;
    }

    /**
     *  联系人/代理人姓名
     */
    public String getContactName (){
        return contactName;
    }
    public void setContactName (String contactName){
        this.contactName = contactName;
    }

    /**
     *  事项所属部门
     */
    public String getItemOrganCode (){
        return itemOrganCode;
    }
    public void setItemOrganCode (String itemOrganCode){
        this.itemOrganCode = itemOrganCode;
    }

    /**
     *  事项办理承诺时限
     */
    public Integer getPromisetimeLimit (){
        return promisetimeLimit;
    }
    public void setPromisetimeLimit (Integer promisetimeLimit){
        this.promisetimeLimit = promisetimeLimit;
    }

    /**
     *  申请时间（yyyy-MM-dd hh:mm:ss）
     */
    public Date getApplyDate (){
        return applyDate;
    }
    public void setApplyDate (Date applyDate){
        this.applyDate = applyDate;
    }

    /**
     *  事项所属区划名称
     */
    public String getItemRegionName (){
        return itemRegionName;
    }
    public void setItemRegionName (String itemRegionName){
        this.itemRegionName = itemRegionName;
    }

    /**
     *  联系人/代理人证件类型
     */
    public String getContactType (){
        return contactType;
    }
    public void setContactType (String contactType){
        this.contactType = contactType;
    }

    /**
     *  事项办件唯一编号
     */
    public String getProjectNo (){
        return projectNo;
    }
    public void setProjectNo (String projectNo){
        this.projectNo = projectNo;
    }

    /**
     *  事项所属区划
     */
    public String getItemRegionCode (){
        return itemRegionCode;
    }
    public void setItemRegionCode (String itemRegionCode){
        this.itemRegionCode = itemRegionCode;
    }

    /**
     *  申请人类型
     */
    public String getApplyerType (){
        return applyerType;
    }
    public void setApplyerType (String applyerType){
        this.applyerType = applyerType;
    }

    /**
     *  法定代表人
     */
    public String getLerep (){
        return lerep;
    }
    public void setLerep (String lerep){
        this.lerep = lerep;
    }

    /**
     *  规定办理时限的单位
     */
    public String getTimeUnit (){
        return timeUnit;
    }
    public void setTimeUnit (String timeUnit){
        this.timeUnit = timeUnit;
    }

}