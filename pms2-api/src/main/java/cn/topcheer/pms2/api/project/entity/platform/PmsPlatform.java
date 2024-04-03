/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:33:52
 */
package cn.topcheer.pms2.api.project.entity.platform;

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
 *  平台载体主表
 */
@Entity
@MainTable
@Table(name="PMS_PLATFORM")
@ClassInfo(name = "平台载体主表", module = SysModuleEnum.TECH_PLATFORM, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPlatform {

	/**
     *  固定字段:唯一标识
     */
	@ApiModelProperty("固定字段:唯一标识")
	@TableField("ID")
	@Column(columnDefinition = "ID")
	@FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
	private String id;

	/**
     *  单位ID(PMS_ENTERPRISE)
     */
	@ApiModelProperty("单位ID(PMS_ENTERPRISE)")
	@TableField("ENTERPRISEID")
	@Column(columnDefinition = "ENTERPRISEID")
	private String enterpriseid;

	/**
     *  用户ID(SYS_USER)
     */
	@ApiModelProperty("用户ID(SYS_USER)")
	@TableField("DECLARANTID")
	@Column(columnDefinition = "DECLARANTID")
	private String declarantid;

	/**
     *  大批次ID(PMS_PLANPROJECT)
     */
	@ApiModelProperty("大批次ID(PMS_PLANPROJECT)")
	@TableField("PLANPROJECTID")
	@Column(columnDefinition = "PLANPROJECTID")
	private String planprojectid;

	/**
     *  小批次ID(PMS_PLANPROJECTBATCH)
     */
	@ApiModelProperty("小批次ID(PMS_PLANPROJECTBATCH)")
	@TableField("PLANPROJECTBATCHID")
	@Column(columnDefinition = "PLANPROJECTBATCHID")
	private String planprojectbatchid;

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
     *  申请编号
     */
	@ApiModelProperty("申请编号")
	@TableField("APPLICATIONNO")
	@Column(columnDefinition = "APPLICATIONNO")
	private String applicationno;

	/**
     *  项目名称
     */
	@ApiModelProperty("项目名称")
	@TableField("PROJECTNAME")
	@Column(columnDefinition = "PROJECTNAME")
	private String projectname;

	/**
     *  计划类别
     */
	@ApiModelProperty("计划类别")
	@TableField("PLANPROJECTTYPE")
	@Column(columnDefinition = "PLANPROJECTTYPE")
	private String planprojecttype;

	/**
     *  负责人
     */
	@ApiModelProperty("负责人")
	@TableField("PROJECTLEADER")
	@Column(columnDefinition = "PROJECTLEADER")
	private String projectleader;

	/**
     *  承担单位
     */
	@ApiModelProperty("承担单位")
	@TableField("MAINORGANIZERS")
	@Column(columnDefinition = "MAINORGANIZERS")
	private String mainorganizers;

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
     *  处室ID
     */
	@ApiModelProperty("处室ID")
	@TableField("BELONGLABID")
	@Column(columnDefinition = "BELONGLABID")
	private String belonglabid;

	/**
     *  处室名称
     */
	@ApiModelProperty("处室名称")
	@TableField("BELONGLAB")
	@Column(columnDefinition = "BELONGLAB")
	private String belonglab;

	/**
     *  推荐单位退回
     */
	@ApiModelProperty("推荐单位退回")
	@TableField("TJDWBACK")
	@Column(columnDefinition = "TJDWBACK")
	private String tjdwback;

	/**
     *  一直允许上报或审核
     */
	@ApiModelProperty("一直允许上报或审核")
	@TableField("ALLOWSBSH")
	@Column(columnDefinition = "ALLOWSBSH")
	private String allowsbsh;

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
     *  一级推荐单位ID
     */
	@ApiModelProperty("一级推荐单位ID")
	@TableField("CITYCASEMANAGEMENTID")
	@Column(columnDefinition = "CITYCASEMANAGEMENTID")
	private String citycasemanagementid;

	/**
     *  一级推荐单位名称
     */
	@ApiModelProperty("一级推荐单位名称")
	@TableField("CITYCASEMANAGEMENT")
	@Column(columnDefinition = "CITYCASEMANAGEMENT")
	private String citycasemanagement;

	/**
     *  二级推荐单位ID
     */
	@ApiModelProperty("二级推荐单位ID")
	@TableField("COUNTYCASEMANAGEMENTID")
	@Column(columnDefinition = "COUNTYCASEMANAGEMENTID")
	private String countycasemanagementid;

	/**
     *  二级推荐单位名称
     */
	@ApiModelProperty("二级推荐单位名称")
	@TableField("COUNTYCASEMANAGEMENT")
	@Column(columnDefinition = "COUNTYCASEMANAGEMENT")
	private String countycasemanagement;

	/**
     *  推荐单位性质
     */
	@ApiModelProperty("推荐单位性质")
	@TableField("CASEMANAGEMENTNATURE")
	@Column(columnDefinition = "CASEMANAGEMENTNATURE")
	private String casemanagementnature;

	/**
     *  开始时间
     */
	@ApiModelProperty("开始时间")
	@TableField("STARTDATE")
	@Column(columnDefinition = "STARTDATE")
	@FieldDes(name = "startdate", type = "Date", memo = "开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date startdate;

	/**
     *  结束时间
     */
	@ApiModelProperty("结束时间")
	@TableField("ENDDATE")
	@Column(columnDefinition = "ENDDATE")
	@FieldDes(name = "enddate", type = "Date", memo = "结束时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date enddate;

	/**
     *  自筹经费
     */
	@ApiModelProperty("自筹经费")
	@TableField("PROJECTSUMFORSELF")
	@Column(columnDefinition = "PROJECTSUMFORSELF")
	@FieldDes(name = "projectsumforself", type = "BigDecimal", memo = "自筹经费")
	private BigDecimal projectsumforself;

	/**
     *  省拨经费
     */
	@ApiModelProperty("省拨经费")
	@TableField("PROJECTSUMFORGOV")
	@Column(columnDefinition = "PROJECTSUMFORGOV")
	@FieldDes(name = "projectsumforgov", type = "BigDecimal", memo = "省拨经费")
	private BigDecimal projectsumforgov;

	/**
     *  其他经费
     */
	@ApiModelProperty("其他经费")
	@TableField("PROJECTSUMFOROTHER")
	@Column(columnDefinition = "PROJECTSUMFOROTHER")
	@FieldDes(name = "projectsumforother", type = "BigDecimal", memo = "其他经费")
	private BigDecimal projectsumforother;

	/**
     *  总经费
     */
	@ApiModelProperty("总经费")
	@TableField("PROJECTSUMTOTAL")
	@Column(columnDefinition = "PROJECTSUMTOTAL")
	@FieldDes(name = "projectsumtotal", type = "BigDecimal", memo = "总经费")
	private BigDecimal projectsumtotal;

	/**
     *  申报种类
     */
	@ApiModelProperty("申报种类")
	@TableField("PROJECTAPPLYTYPE")
	@Column(columnDefinition = "PROJECTAPPLYTYPE")
	private String projectapplytype;

	/**
     *  申报、项目类型
     */
	@ApiModelProperty("申报、项目类型")
	@TableField("PROJECTTYPE")
	@Column(columnDefinition = "PROJECTTYPE")
	private String projecttype;

	/**
     *  水印
     */
	@ApiModelProperty("水印")
	@TableField("ISADDWATERMARK")
	@Column(columnDefinition = "ISADDWATERMARK")
	private String isaddwatermark;

	/**
     *  合同编号
     */
	@ApiModelProperty("合同编号")
	@TableField("CONTRACTNO")
	@Column(columnDefinition = "CONTRACTNO")
	private String contractno;

	/**
     *  项目联系人
     */
	@ApiModelProperty("项目联系人")
	@TableField("PROJECTLINK")
	@Column(columnDefinition = "PROJECTLINK")
	private String projectlink;

	/**
     *  支持方向ID
     */
	@ApiModelProperty("支持方向ID")
	@TableField("SUPPORTDIRECTIONID")
	@Column(columnDefinition = "SUPPORTDIRECTIONID")
	private String supportdirectionid;

	/**
     *  支持方向
     */
	@ApiModelProperty("支持方向")
	@TableField("SUPPORTDIRECTION")
	@Column(columnDefinition = "SUPPORTDIRECTION")
	private String supportdirection;

	/**
     *  支持子方向ID
     */
	@ApiModelProperty("支持子方向ID")
	@TableField("SUPPORTDIRECTIONCHILDID")
	@Column(columnDefinition = "SUPPORTDIRECTIONCHILDID")
	private String supportdirectionchildid;

	/**
     *  支持子方向
     */
	@ApiModelProperty("支持子方向")
	@TableField("SUPPORTDIRECTIONCHILD")
	@Column(columnDefinition = "SUPPORTDIRECTIONCHILD")
	private String supportdirectionchild;

	/**
	 *  是否删除（0未删除，1已删除）
	 */
	@ApiModelProperty("是否删除（0未删除，1已删除）")
	@TableField("IS_DELETED")
	@Column(columnDefinition = "IS_DELETED")
	private Integer isDeleted;

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
	*  单位ID(PMS_ENTERPRISE)
	*/
	public String getEnterpriseid (){
		return enterpriseid;
	}
	public void setEnterpriseid (String enterpriseid){
		this.enterpriseid = enterpriseid;
	}

	/**
	*  用户ID(SYS_USER)
	*/
	public String getDeclarantid (){
		return declarantid;
	}
	public void setDeclarantid (String declarantid){
		this.declarantid = declarantid;
	}

	/**
	*  大批次ID(PMS_PLANPROJECT)
	*/
	public String getPlanprojectid (){
		return planprojectid;
	}
	public void setPlanprojectid (String planprojectid){
		this.planprojectid = planprojectid;
	}

	/**
	*  小批次ID(PMS_PLANPROJECTBATCH)
	*/
	public String getPlanprojectbatchid (){
		return planprojectbatchid;
	}
	public void setPlanprojectbatchid (String planprojectbatchid){
		this.planprojectbatchid = planprojectbatchid;
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
	*  申请编号
	*/
	public String getApplicationno (){
		return applicationno;
	}
	public void setApplicationno (String applicationno){
		this.applicationno = applicationno;
	}

	/**
	*  项目名称
	*/
	public String getProjectname (){
		return projectname;
	}
	public void setProjectname (String projectname){
		this.projectname = projectname;
	}

	/**
	*  计划类别
	*/
	public String getPlanprojecttype (){
		return planprojecttype;
	}
	public void setPlanprojecttype (String planprojecttype){
		this.planprojecttype = planprojecttype;
	}

	/**
	*  负责人
	*/
	public String getProjectleader (){
		return projectleader;
	}
	public void setProjectleader (String projectleader){
		this.projectleader = projectleader;
	}

	/**
	*  承担单位
	*/
	public String getMainorganizers (){
		return mainorganizers;
	}
	public void setMainorganizers (String mainorganizers){
		this.mainorganizers = mainorganizers;
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
	*  处室ID
	*/
	public String getBelonglabid (){
		return belonglabid;
	}
	public void setBelonglabid (String belonglabid){
		this.belonglabid = belonglabid;
	}

	/**
	*  处室名称
	*/
	public String getBelonglab (){
		return belonglab;
	}
	public void setBelonglab (String belonglab){
		this.belonglab = belonglab;
	}

	/**
	*  推荐单位退回
	*/
	public String getTjdwback (){
		return tjdwback;
	}
	public void setTjdwback (String tjdwback){
		this.tjdwback = tjdwback;
	}

	/**
	*  一直允许上报或审核
	*/
	public String getAllowsbsh (){
		return allowsbsh;
	}
	public void setAllowsbsh (String allowsbsh){
		this.allowsbsh = allowsbsh;
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
	*  一级推荐单位ID
	*/
	public String getCitycasemanagementid (){
		return citycasemanagementid;
	}
	public void setCitycasemanagementid (String citycasemanagementid){
		this.citycasemanagementid = citycasemanagementid;
	}

	/**
	*  一级推荐单位名称
	*/
	public String getCitycasemanagement (){
		return citycasemanagement;
	}
	public void setCitycasemanagement (String citycasemanagement){
		this.citycasemanagement = citycasemanagement;
	}

	/**
	*  二级推荐单位ID
	*/
	public String getCountycasemanagementid (){
		return countycasemanagementid;
	}
	public void setCountycasemanagementid (String countycasemanagementid){
		this.countycasemanagementid = countycasemanagementid;
	}

	/**
	*  二级推荐单位名称
	*/
	public String getCountycasemanagement (){
		return countycasemanagement;
	}
	public void setCountycasemanagement (String countycasemanagement){
		this.countycasemanagement = countycasemanagement;
	}

	/**
	*  推荐单位性质
	*/
	public String getCasemanagementnature (){
		return casemanagementnature;
	}
	public void setCasemanagementnature (String casemanagementnature){
		this.casemanagementnature = casemanagementnature;
	}

	/**
	*  开始时间
	*/
	public Date getStartdate (){
		return startdate;
	}
	public void setStartdate (Date startdate){
		this.startdate = startdate;
	}

	/**
	*  结束时间
	*/
	public Date getEnddate (){
		return enddate;
	}
	public void setEnddate (Date enddate){
		this.enddate = enddate;
	}

	/**
	*  自筹经费
	*/
	public BigDecimal getProjectsumforself (){
		return projectsumforself;
	}
	public void setProjectsumforself (BigDecimal projectsumforself){
		this.projectsumforself = projectsumforself;
	}

	/**
	*  省拨经费
	*/
	public BigDecimal getProjectsumforgov (){
		return projectsumforgov;
	}
	public void setProjectsumforgov (BigDecimal projectsumforgov){
		this.projectsumforgov = projectsumforgov;
	}

	/**
	*  其他经费
	*/
	public BigDecimal getProjectsumforother (){
		return projectsumforother;
	}
	public void setProjectsumforother (BigDecimal projectsumforother){
		this.projectsumforother = projectsumforother;
	}

	/**
	*  总经费
	*/
	public BigDecimal getProjectsumtotal (){
		return projectsumtotal;
	}
	public void setProjectsumtotal (BigDecimal projectsumtotal){
		this.projectsumtotal = projectsumtotal;
	}

	/**
	*  申报种类
	*/
	public String getProjectapplytype (){
		return projectapplytype;
	}
	public void setProjectapplytype (String projectapplytype){
		this.projectapplytype = projectapplytype;
	}

	/**
	*  申报、项目类型
	*/
	public String getProjecttype (){
		return projecttype;
	}
	public void setProjecttype (String projecttype){
		this.projecttype = projecttype;
	}

	/**
	*  水印
	*/
	public String getIsaddwatermark (){
		return isaddwatermark;
	}
	public void setIsaddwatermark (String isaddwatermark){
		this.isaddwatermark = isaddwatermark;
	}

	/**
	*  合同编号
	*/
	public String getContractno (){
		return contractno;
	}
	public void setContractno (String contractno){
		this.contractno = contractno;
	}

	/**
	*  项目联系人
	*/
	public String getProjectlink (){
		return projectlink;
	}
	public void setProjectlink (String projectlink){
		this.projectlink = projectlink;
	}

	/**
	*  支持方向ID
	*/
	public String getSupportdirectionid (){
		return supportdirectionid;
	}
	public void setSupportdirectionid (String supportdirectionid){
		this.supportdirectionid = supportdirectionid;
	}

	/**
	*  支持方向
	*/
	public String getSupportdirection (){
		return supportdirection;
	}
	public void setSupportdirection (String supportdirection){
		this.supportdirection = supportdirection;
	}

	/**
	*  支持子方向ID
	*/
	public String getSupportdirectionchildid (){
		return supportdirectionchildid;
	}
	public void setSupportdirectionchildid (String supportdirectionchildid){
		this.supportdirectionchildid = supportdirectionchildid;
	}

	/**
	*  支持子方向
	*/
	public String getSupportdirectionchild (){
		return supportdirectionchild;
	}
	public void setSupportdirectionchild (String supportdirectionchild){
		this.supportdirectionchild = supportdirectionchild;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}