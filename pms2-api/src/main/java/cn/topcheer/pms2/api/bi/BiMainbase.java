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
import cn.topcheer.pms2.api.annotation.MainTable;
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
 *  数据仓-主表
 */
@Entity
@Table(name = "BI_MAINBASE")
@MainTable
@ClassInfo(name = "数据仓主表", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiMainbase {

    /**
     *  主键id
     */
    @ApiModelProperty("主键id")
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
     *  固定字段:关联子表id
     */
    @ApiModelProperty("固定字段:关联子表id")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    private String sourceid;

    /**
     *  是否删除（0未删除，1已删除）
     */
    @ApiModelProperty("是否删除（0未删除，1已删除）")
    @TableField("IS_DELETED")
    @Column(columnDefinition = "IS_DELETED")
    private Integer isDeleted;

    /**
     *  企业ID
     */
    @ApiModelProperty("企业ID")
    @TableField("ENTERPRISEID")
    @Column(columnDefinition = "ENTERPRISEID")
    private String enterpriseid;

    /**
     *  申报人ID
     */
    @ApiModelProperty("申报人ID")
    @TableField("DECLARANTID")
    @Column(columnDefinition = "DECLARANTID")
    private String declarantid;

    /**
     *  批次ID
     */
    @ApiModelProperty("批次ID")
    @TableField("PLANPROJECTBATCHID")
    @Column(columnDefinition = "PLANPROJECTBATCHID")
    private String planprojectbatchid;

    /**
     *  大批次ID
     */
    @ApiModelProperty("大批次ID")
    @TableField("PLANPROJECTID")
    @Column(columnDefinition = "PLANPROJECTID")
    private String planprojectid;

    /**
     *  流程状态
     */
    @ApiModelProperty("流程状态")
    @TableField("MINICURRENTSTATE")
    @Column(columnDefinition = "MINICURRENTSTATE")
    private String minicurrentstate;

    /**
     *  流程状态ID
     */
    @ApiModelProperty("流程状态ID")
    @TableField("MINICURRENTSTATEID")
    @Column(columnDefinition = "MINICURRENTSTATEID")
    private String minicurrentstateid;

    /**
     *  流程节点定义key
     */
    @ApiModelProperty("流程节点定义key")
    @TableField("MINI_CURRENT_TASK_DEF_KEY")
    @Column(columnDefinition = "MINI_CURRENT_TASK_DEF_KEY")
    private String miniCurrentTaskDefKey;

    /**
     *  流程定义key
     */
    @ApiModelProperty("流程定义key")
    @TableField("MINI_CURRENT_PROCESS_DEF_KEY")
    @Column(columnDefinition = "MINI_CURRENT_PROCESS_DEF_KEY")
    private String miniCurrentProcessDefKey;

    /**
     *  提交时间
     */
    @ApiModelProperty("提交时间")
    @TableField("SUBMITDATE")
    @Column(columnDefinition = "SUBMITDATE")
    @FieldDes(name = "submitdate", type = "Date", memo = "提交时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date submitdate;

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
     *  流程更新时间
     */
    @ApiModelProperty("流程更新时间")
    @TableField("FLOWPOINTUPDATETIME")
    @Column(columnDefinition = "FLOWPOINTUPDATETIME")
    @FieldDes(name = "flowpointupdatetime", type = "Date", memo = "流程更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date flowpointupdatetime;

    /**
     *  县推荐单位
     */
    @ApiModelProperty("县推荐单位")
    @TableField("COUNTRYCASEMANAGEMENT")
    @Column(columnDefinition = "COUNTRYCASEMANAGEMENT")
    private String countrycasemanagement;

    /**
     *  县推荐单位ID
     */
    @ApiModelProperty("县推荐单位ID")
    @TableField("COUNTRYCASEMANAGEMENTID")
    @Column(columnDefinition = "COUNTRYCASEMANAGEMENTID")
    private String countrycasemanagementid;

    /**
     *  县推荐单位(修改字段名称与其余主表一致)
     */
    @ApiModelProperty("县推荐单位")
    @TableField("COUNTYCASEMANAGEMENT")
    @Column(columnDefinition = "COUNTYCASEMANAGEMENT")
    private String countycasemanagement;

    /**
     *  县推荐单位ID(修改字段名称与其余主表一致)
     */
    @ApiModelProperty("县推荐单位ID")
    @TableField("COUNTYCASEMANAGEMENTID")
    @Column(columnDefinition = "COUNTYCASEMANAGEMENTID")
    private String countycasemanagementid;

    /**
     *  市推荐单位
     */
    @ApiModelProperty("市推荐单位")
    @TableField("CITYCASEMANAGEMENT")
    @Column(columnDefinition = "CITYCASEMANAGEMENT")
    private String citycasemanagement;

    /**
     *  市推荐单位ID
     */
    @ApiModelProperty("市推荐单位ID")
    @TableField("CITYCASEMANAGEMENTID")
    @Column(columnDefinition = "CITYCASEMANAGEMENTID")
    private String citycasemanagementid;

    /**
     *  列表显示名称
     */
    @ApiModelProperty("列表显示名称")
    @TableField("SHOWNAME")
    @Column(columnDefinition = "SHOWNAME")
    private String showname;

    /**
     *  列表显示级别
     */
    @ApiModelProperty("列表显示级别")
    @TableField("SHOWRANK")
    @Column(columnDefinition = "SHOWRANK")
    private String showrank;

    /**
     *  所属企业
     */
    @ApiModelProperty("所属企业")
    @TableField("BELONGUNIT")
    @Column(columnDefinition = "BELONGUNIT")
    private String belongunit;

    /**
     *  所属集团
     */
    @ApiModelProperty("所属集团")
    @TableField("BELONGGROUP")
    @Column(columnDefinition = "BELONGGROUP")
    private String belonggroup;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CHANGECITYCASEMANAGEMENT")
    @Column(columnDefinition = "CHANGECITYCASEMANAGEMENT")
    @FieldDes(name = "changecitycasemanagement", lenth = "255", type = "String", memo = "")
    private String changecitycasemanagement;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CHANGECITYCASEMANAGEMENTID")
    @Column(columnDefinition = "CHANGECITYCASEMANAGEMENTID")
    @FieldDes(name = "changecitycasemanagementid", lenth = "255", type = "String", memo = "")
    private String changecitycasemanagementid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CHANGEUNITNAME")
    @Column(columnDefinition = "CHANGEUNITNAME")
    @FieldDes(name = "changeunitname", lenth = "255", type = "String", memo = "")
    private String changeunitname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CHANGEUNITID")
    @Column(columnDefinition = "CHANGEUNITID")
    @FieldDes(name = "changeunitid", lenth = "255", type = "String", memo = "")
    private String changeunitid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("DRBJ")
    @Column(columnDefinition = "DRBJ")
    @FieldDes(name = "drbj", lenth = "50", type = "String", memo = "")
    private String drbj;

    /**
     *  是否可以修改组织单位
     */
    @ApiModelProperty("是否可以修改组织单位")
    @TableField("ZZDWFLAG")
    @Column(columnDefinition = "ZZDWFLAG")
    @FieldDes(name = "zzdwflag", lenth = "50", type = "String", memo = "是否可以修改组织单位")
    private String zzdwflag;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PSZXBACK")
    @Column(columnDefinition = "PSZXBACK")
    @FieldDes(name = "pszxback", lenth = "255", type = "String", memo = "")
    private String pszxback;

    /**
     *  是否已阅读
     */
    @ApiModelProperty("是否已阅读")
    @TableField("ISREAD")
    @Column(columnDefinition = "ISREAD")
    @FieldDes(name = "isread", lenth = "255", type = "String", memo = "是否已阅读")
    private String isread;

    /**
     *  logo
     */
    @ApiModelProperty("logo")
    @TableField("LOGO")
    @Column(columnDefinition = "LOGO")
    private String logo;


    /**
    *  主键id
    */
        @Id
    public String getId (){
        return id;
    }
    public void setId (String id){
        this.id = id;
    }

    /**
    *  企业ID
    */
    public String getEnterpriseid (){
        return enterpriseid;
    }
    public void setEnterpriseid (String enterpriseid){
        this.enterpriseid = enterpriseid;
    }

    /**
    *  申报人ID
    */
    public String getDeclarantid (){
        return declarantid;
    }
    public void setDeclarantid (String declarantid){
        this.declarantid = declarantid;
    }

    /**
    *  批次ID
    */
    public String getPlanprojectbatchid (){
        return planprojectbatchid;
    }
    public void setPlanprojectbatchid (String planprojectbatchid){
        this.planprojectbatchid = planprojectbatchid;
    }

    /**
    *  大批次ID
    */
    public String getPlanprojectid (){
        return planprojectid;
    }
    public void setPlanprojectid (String planprojectid){
        this.planprojectid = planprojectid;
    }

    /**
    *  流程状态
    */
    public String getMinicurrentstate (){
        return minicurrentstate;
    }
    public void setMinicurrentstate (String minicurrentstate){
        this.minicurrentstate = minicurrentstate;
    }

    /**
    *  流程状态ID
    */
    public String getMinicurrentstateid (){
        return minicurrentstateid;
    }
    public void setMinicurrentstateid (String minicurrentstateid){
        this.minicurrentstateid = minicurrentstateid;
    }

    public String getMiniCurrentTaskDefKey() {
        return miniCurrentTaskDefKey;
    }

    public void setMiniCurrentTaskDefKey(String miniCurrentTaskDefKey) {
        this.miniCurrentTaskDefKey = miniCurrentTaskDefKey;
    }

    public String getMiniCurrentProcessDefKey() {
        return miniCurrentProcessDefKey;
    }

    public void setMiniCurrentProcessDefKey(String miniCurrentProcessDefKey) {
        this.miniCurrentProcessDefKey = miniCurrentProcessDefKey;
    }

    /**
    *  提交时间
    */
    public Date getSubmitdate (){
        return submitdate;
    }
    public void setSubmitdate (Date submitdate){
        this.submitdate = submitdate;
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

    /**
    *  流程更新时间
    */
    public Date getFlowpointupdatetime (){
        return flowpointupdatetime;
    }
    public void setFlowpointupdatetime (Date flowpointupdatetime){
        this.flowpointupdatetime = flowpointupdatetime;
    }

    /**
    *  县推荐单位
    */
    public String getCountrycasemanagement (){
        return countrycasemanagement;
    }
    public void setCountrycasemanagement (String countrycasemanagement){
        this.countrycasemanagement = countrycasemanagement;
    }

    /**
    *  县推荐单位ID
    */
    public String getCountrycasemanagementid (){
        return countrycasemanagementid;
    }
    public void setCountrycasemanagementid (String countrycasemanagementid){
        this.countrycasemanagementid = countrycasemanagementid;
    }

    /**
     *  县推荐单位(修改字段名称与其余主表一致)
     */
    public String getCountycasemanagement (){
        return countycasemanagement;
    }
    public void setCountycasemanagement (String countycasemanagement){
        this.countycasemanagement = countycasemanagement;
    }

    /**
     *  县推荐单位ID(修改字段名称与其余主表一致)
     */
    public String getCountycasemanagementid (){
        return countycasemanagementid;
    }
    public void setCountycasemanagementid (String countycasemanagementid){
        this.countycasemanagementid = countycasemanagementid;
    }

    /**
    *  市推荐单位
    */
    public String getCitycasemanagement (){
        return citycasemanagement;
    }
    public void setCitycasemanagement (String citycasemanagement){
        this.citycasemanagement = citycasemanagement;
    }

    /**
    *  市推荐单位ID
    */
    public String getCitycasemanagementid (){
        return citycasemanagementid;
    }
    public void setCitycasemanagementid (String citycasemanagementid){
        this.citycasemanagementid = citycasemanagementid;
    }

    /**
    *  列表显示名称
    */
    public String getShowname (){
        return showname;
    }
    public void setShowname (String showname){
        this.showname = showname;
    }

    /**
    *  列表显示级别
    */
    public String getShowrank (){
        return showrank;
    }
    public void setShowrank (String showrank){
        this.showrank = showrank;
    }

    /**
    *  所属企业
    */
    public String getBelongunit (){
        return belongunit;
    }
    public void setBelongunit (String belongunit){
        this.belongunit = belongunit;
    }

    /**
    *  所属集团
    */
    public String getBelonggroup (){
        return belonggroup;
    }
    public void setBelonggroup (String belonggroup){
        this.belonggroup = belonggroup;
    }

    /**
    *  
    */
    public String getChangecitycasemanagement (){
        return changecitycasemanagement;
    }
    public void setChangecitycasemanagement (String changecitycasemanagement){
        this.changecitycasemanagement = changecitycasemanagement;
    }

    /**
    *  
    */
    public String getChangecitycasemanagementid (){
        return changecitycasemanagementid;
    }
    public void setChangecitycasemanagementid (String changecitycasemanagementid){
        this.changecitycasemanagementid = changecitycasemanagementid;
    }

    /**
    *  
    */
    public String getChangeunitname (){
        return changeunitname;
    }
    public void setChangeunitname (String changeunitname){
        this.changeunitname = changeunitname;
    }

    /**
    *  
    */
    public String getChangeunitid (){
        return changeunitid;
    }
    public void setChangeunitid (String changeunitid){
        this.changeunitid = changeunitid;
    }

    /**
    *  
    */
    public String getDrbj (){
        return drbj;
    }
    public void setDrbj (String drbj){
        this.drbj = drbj;
    }

    /**
    *  是否可以修改组织单位
    */
    public String getZzdwflag (){
        return zzdwflag;
    }
    public void setZzdwflag (String zzdwflag){
        this.zzdwflag = zzdwflag;
    }

    /**
    *  
    */
    public String getPszxback (){
        return pszxback;
    }
    public void setPszxback (String pszxback){
        this.pszxback = pszxback;
    }

    /**
    *  是否已阅读
    */
    public String getIsread (){
        return isread;
    }
    public void setIsread (String isread){
        this.isread = isread;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}