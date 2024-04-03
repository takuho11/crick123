/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-3 16:42:11
 */
package cn.topcheer.pms2.api.project.entity.projectBase;

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
 *  计划项目
 */
@MainTable
@Entity
@Table(name="PMS_PROJECTBASE")
@ClassInfo(name = "计划项目", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbase {

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date savedate;

	/**
	 *  固定字段:每次更新数据时间
	 */
	@ApiModelProperty("固定字段:每次更新数据时间")
	@TableField("UPDATELASTTIME")
	@Column(columnDefinition = "UPDATELASTTIME")
	@FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
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
     *  所属类别
     */
	@ApiModelProperty("所属类别")
	@TableField("CATEGORY")
	@Column(columnDefinition = "CATEGORY")
	private String category;
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
     *  立项标记
     */
	@ApiModelProperty("立项标记")
	@TableField("LXFLAG")
	@Column(columnDefinition = "LXFLAG")
	private String lxflag;
	/**
     *  公示时间
     */
	@ApiModelProperty("公示时间")
	@TableField("GSDATE")
	@Column(columnDefinition = "GSDATE")
	@FieldDes(name = "gsdate", type = "Date", memo = "公示时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date gsdate;
	/**
     *  研究期限
     */
	@ApiModelProperty("研究期限")
	@TableField("RESEARCHDEADLINE")
	@Column(columnDefinition = "RESEARCHDEADLINE")
	private String researchdeadline;
	/**
     *  研究方向
     */
	@ApiModelProperty("研究方向")
	@TableField("RESEARCHDIRECTION")
	@Column(columnDefinition = "RESEARCHDIRECTION")
	private String researchdirection;
	/**
     *  关键字-中文
     */
	@ApiModelProperty("关键字-中文")
	@TableField("KEYWORDSZW")
	@Column(columnDefinition = "KEYWORDSZW")
	private String keywordszw;
	/**
     *  关键字-英文
     */
	@ApiModelProperty("关键字-英文")
	@TableField("KEYWORDSYW")
	@Column(columnDefinition = "KEYWORDSYW")
	private String keywordsyw;
	/**
     *  研究领域
     */
	@ApiModelProperty("研究领域")
	@TableField("RESEARCHDOMAIN")
	@Column(columnDefinition = "RESEARCHDOMAIN")
	private String researchdomain;
	/**
     *  项目简介
     */
	@ApiModelProperty("项目简介")
	@TableField("PROJECTDESCRIPTION")
	@Column(columnDefinition = "PROJECTDESCRIPTION")
	private String projectdescription;
	/**
     *  项目与《技术榜单》或《指南》的衔接性
     */
	@ApiModelProperty("项目与《技术榜单》或《指南》的衔接性")
	@TableField("PROJECTCONNECTIVITY")
	@Column(columnDefinition = "PROJECTCONNECTIVITY")
	private String projectconnectivity;
	/**
     *  学科代码编号
     */
	@ApiModelProperty("学科代码编号")
	@TableField("SUBJECTCODE")
	@Column(columnDefinition = "SUBJECTCODE")
	private String subjectcode;
	/**
     *  是否删除（0未删除，1已删除）
     */
	@ApiModelProperty("是否删除（0未删除，1已删除）")
	@TableField("IS_DELETED")
	@Column(columnDefinition = "IS_DELETED")
	private Integer isDeleted;

	/**
	 *  指南领域
	 */
	@ApiModelProperty("指南领域")
	@TableField("GUIDE_DOMAIN")
	@Column(columnDefinition = "GUIDE_DOMAIN")
	private String guideDomain;

	/**
	 *  技术来源
	 */
	@ApiModelProperty("技术来源")
	@TableField("TECH_SOURCE")
	@Column(columnDefinition = "TECH_SOURCE")
	private String techSource;

	/**
	 *  项目活动类型
	 */
	@ApiModelProperty("项目活动类型")
	@TableField("PROJECT_ACT_TYPE")
	@Column(columnDefinition = "PROJECT_ACT_TYPE")
	private String projectActType;

	/**
	 *  创新类型
	 */
	@ApiModelProperty("创新类型")
	@TableField("INNOVATE_TYPE")
	@Column(columnDefinition = "INNOVATE_TYPE")
	private String innovateType;

	/**
	 *  项目完成时的应用类型
	 */
	@ApiModelProperty("项目完成时的应用类型")
	@TableField("APPLICATION_TYPE")
	@Column(columnDefinition = "APPLICATION_TYPE")
	private String applicationType;

	/**
	 *  负责人电话
	 */
	@ApiModelProperty("负责人电话")
	@TableField("FZR_MOBILE")
	@Column(columnDefinition = "FZR_MOBILE")
	@FieldDes(name = "fzr_mobile", lenth = "200", type = "String", memo = "负责人电话")
	private String fzrMobile;

	/**
	 *  是否属于国家秘密
	 */
	@ApiModelProperty("是否属于国家秘密")
	@TableField("IS_SECRET")
	@Column(columnDefinition = "IS_SECRET")
	private String isSecret;

	/**
	 *  是否涉嫌伦理
	 */
	@ApiModelProperty("是否涉嫌伦理")
	@TableField("IS_SUSPECTED_ETHICS")
	@Column(columnDefinition = "IS_SUSPECTED_ETHICS")
	private String isSuspectedEthics;

	/**
	 *  项目归口领域
	 */
	@ApiModelProperty("项目归口领域")
	@TableField("PROJECT_FOCUS_AREA")
	@Column(columnDefinition = "PROJECT_FOCUS_AREA")
	private String projectFocusArea;

	/**
	 *  指南主题
	 */
	@ApiModelProperty("指南主题")
	@TableField("GUIDE_THEME")
	@Column(columnDefinition = "GUIDE_THEME")
	private String guideTheme;

	/**
	 *  指南其他（请说明）
	 */
	@ApiModelProperty("指南其他（请说明）")
	@TableField("GUIDE_OTHER")
	@Column(columnDefinition = "GUIDE_OTHER")
	private String guideOther;

	/**
	*  固定字段:唯一标识
	*/
		@Id
	public String getId (){
	return id;
	}
	public void setId (String id){
	this.id=id;
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

	public Date getSavedate() {
		return savedate;
	}

	public void setSavedate(Date savedate) {
		this.savedate = savedate;
	}

	public Date getUpdatelasttime() {
		return updatelasttime;
	}

	public void setUpdatelasttime(Date updatelasttime) {
		this.updatelasttime = updatelasttime;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	*  单位ID(PMS_ENTERPRISE)
	*/
	public String getEnterpriseid (){
	return enterpriseid;
	}
	public void setEnterpriseid (String enterpriseid){
	this.enterpriseid=enterpriseid;
	}

	/**
	*  用户ID(SYS_USER)
	*/
	public String getDeclarantid (){
	return declarantid;
	}
	public void setDeclarantid (String declarantid){
	this.declarantid=declarantid;
	}

	/**
	*  大批次ID(PMS_PLANPROJECT)
	*/
	public String getPlanprojectid (){
	return planprojectid;
	}
	public void setPlanprojectid (String planprojectid){
	this.planprojectid=planprojectid;
	}

	/**
	*  小批次ID(PMS_PLANPROJECTBATCH)
	*/
	public String getPlanprojectbatchid (){
	return planprojectbatchid;
	}
	public void setPlanprojectbatchid (String planprojectbatchid){
	this.planprojectbatchid=planprojectbatchid;
	}

	/**
	*  流程状态
	*/
	public String getMinicurrentstate (){
	return minicurrentstate;
	}
	public void setMinicurrentstate (String minicurrentstate){
	this.minicurrentstate=minicurrentstate;
	}

	/**
	*  流程状态ID
	*/
	public String getMinicurrentstateid (){
	return minicurrentstateid;
	}
	public void setMinicurrentstateid (String minicurrentstateid){
	this.minicurrentstateid=minicurrentstateid;
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
	this.applicationno=applicationno;
	}

	/**
	*  项目名称
	*/
	public String getProjectname (){
	return projectname;
	}
	public void setProjectname (String projectname){
	this.projectname=projectname;
	}

	/**
	*  计划类别
	*/
	public String getPlanprojecttype (){
	return planprojecttype;
	}
	public void setPlanprojecttype (String planprojecttype){
	this.planprojecttype=planprojecttype;
	}

	/**
	*  负责人
	*/
	public String getProjectleader (){
	return projectleader;
	}
	public void setProjectleader (String projectleader){
	this.projectleader=projectleader;
	}

	/**
	*  承担单位
	*/
	public String getMainorganizers (){
	return mainorganizers;
	}
	public void setMainorganizers (String mainorganizers){
	this.mainorganizers=mainorganizers;
	}

	/**
	*  提交时间
	*/
	public Date getSubmitdate (){
	return submitdate;
	}
	public void setSubmitdate (Date submitdate){
	this.submitdate=submitdate;
	}

	/**
	*  处室ID
	*/
	public String getBelonglabid (){
	return belonglabid;
	}
	public void setBelonglabid (String belonglabid){
	this.belonglabid=belonglabid;
	}

	/**
	*  处室名称
	*/
	public String getBelonglab (){
	return belonglab;
	}
	public void setBelonglab (String belonglab){
	this.belonglab=belonglab;
	}

	/**
	*  推荐单位退回
	*/
	public String getTjdwback (){
	return tjdwback;
	}
	public void setTjdwback (String tjdwback){
	this.tjdwback=tjdwback;
	}

	/**
	*  一直允许上报或审核
	*/
	public String getAllowsbsh (){
	return allowsbsh;
	}
	public void setAllowsbsh (String allowsbsh){
	this.allowsbsh=allowsbsh;
	}

	/**
	*  流程更新时间
	*/
	public Date getFlowpointupdatetime (){
	return flowpointupdatetime;
	}
	public void setFlowpointupdatetime (Date flowpointupdatetime){
	this.flowpointupdatetime=flowpointupdatetime;
	}

	/**
	*  一级推荐单位ID
	*/
	public String getCitycasemanagementid (){
	return citycasemanagementid;
	}
	public void setCitycasemanagementid (String citycasemanagementid){
	this.citycasemanagementid=citycasemanagementid;
	}

	/**
	*  一级推荐单位名称
	*/
	public String getCitycasemanagement (){
	return citycasemanagement;
	}
	public void setCitycasemanagement (String citycasemanagement){
	this.citycasemanagement=citycasemanagement;
	}

	/**
	*  二级推荐单位ID
	*/
	public String getCountycasemanagementid (){
	return countycasemanagementid;
	}
	public void setCountycasemanagementid (String countycasemanagementid){
	this.countycasemanagementid=countycasemanagementid;
	}

	/**
	*  二级推荐单位名称
	*/
	public String getCountycasemanagement (){
	return countycasemanagement;
	}
	public void setCountycasemanagement (String countycasemanagement){
	this.countycasemanagement=countycasemanagement;
	}

	/**
	*  推荐单位性质
	*/
	public String getCasemanagementnature (){
	return casemanagementnature;
	}
	public void setCasemanagementnature (String casemanagementnature){
	this.casemanagementnature=casemanagementnature;
	}

	/**
	*  开始时间
	*/
	public Date getStartdate (){
	return startdate;
	}
	public void setStartdate (Date startdate){
	this.startdate=startdate;
	}

	/**
	*  结束时间
	*/
	public Date getEnddate (){
	return enddate;
	}
	public void setEnddate (Date enddate){
	this.enddate=enddate;
	}

	/**
	*  自筹经费
	*/
	public BigDecimal getProjectsumforself (){
	return projectsumforself;
	}
	public void setProjectsumforself (BigDecimal projectsumforself){
	this.projectsumforself=projectsumforself;
	}

	/**
	*  省拨经费
	*/
	public BigDecimal getProjectsumforgov (){
	return projectsumforgov;
	}
	public void setProjectsumforgov (BigDecimal projectsumforgov){
	this.projectsumforgov=projectsumforgov;
	}

	/**
	*  其他经费
	*/
	public BigDecimal getProjectsumforother (){
	return projectsumforother;
	}
	public void setProjectsumforother (BigDecimal projectsumforother){
	this.projectsumforother=projectsumforother;
	}

	/**
	*  总经费
	*/
	public BigDecimal getProjectsumtotal (){
	return projectsumtotal;
	}
	public void setProjectsumtotal (BigDecimal projectsumtotal){
	this.projectsumtotal=projectsumtotal;
	}

	/**
	*  申报种类
	*/
	public String getProjectapplytype (){
	return projectapplytype;
	}
	public void setProjectapplytype (String projectapplytype){
	this.projectapplytype=projectapplytype;
	}

	/**
	*  申报、项目类型
	*/
	public String getProjecttype (){
	return projecttype;
	}
	public void setProjecttype (String projecttype){
	this.projecttype=projecttype;
	}

	/**
	*  所属类别
	*/
	public String getCategory (){
	return category;
	}
	public void setCategory (String category){
	this.category=category;
	}

	/**
	*  水印
	*/
	public String getIsaddwatermark (){
	return isaddwatermark;
	}
	public void setIsaddwatermark (String isaddwatermark){
	this.isaddwatermark=isaddwatermark;
	}

	/**
	*  合同编号
	*/
	public String getContractno (){
	return contractno;
	}
	public void setContractno (String contractno){
	this.contractno=contractno;
	}

	/**
	*  支持方向ID
	*/
	public String getSupportdirectionid (){
	return supportdirectionid;
	}
	public void setSupportdirectionid (String supportdirectionid){
	this.supportdirectionid=supportdirectionid;
	}

	/**
	*  支持方向
	*/
	public String getSupportdirection (){
	return supportdirection;
	}
	public void setSupportdirection (String supportdirection){
	this.supportdirection=supportdirection;
	}

	/**
	*  立项标记
	*/
	public String getLxflag (){
	return lxflag;
	}
	public void setLxflag (String lxflag){
	this.lxflag=lxflag;
	}

	/**
	*  公示时间
	*/
	public Date getGsdate (){
	return gsdate;
	}
	public void setGsdate (Date gsdate){
	this.gsdate=gsdate;
	}

	/**
	*  研究期限
	*/
	public String getResearchdeadline (){
	return researchdeadline;
	}
	public void setResearchdeadline (String researchdeadline){
	this.researchdeadline=researchdeadline;
	}

	/**
	*  研究方向
	*/
	public String getResearchdirection (){
	return researchdirection;
	}
	public void setResearchdirection (String researchdirection){
	this.researchdirection=researchdirection;
	}

	/**
	*  关键字-中文
	*/
	public String getKeywordszw (){
	return keywordszw;
	}
	public void setKeywordszw (String keywordszw){
	this.keywordszw=keywordszw;
	}

	/**
	*  关键字-英文
	*/
	public String getKeywordsyw (){
	return keywordsyw;
	}
	public void setKeywordsyw (String keywordsyw){
	this.keywordsyw=keywordsyw;
	}

	/**
	*  研究领域
	*/
	public String getResearchdomain (){
	return researchdomain;
	}
	public void setResearchdomain (String researchdomain){
	this.researchdomain=researchdomain;
	}

	/**
	*  项目简介
	*/
	public String getProjectdescription (){
	return projectdescription;
	}
	public void setProjectdescription (String projectdescription){
	this.projectdescription=projectdescription;
	}

	/**
	*  项目与《技术榜单》或《指南》的衔接性
	*/
	public String getProjectconnectivity (){
	return projectconnectivity;
	}
	public void setProjectconnectivity (String projectconnectivity){
	this.projectconnectivity=projectconnectivity;
	}

	/**
	*  学科代码编号
	*/
	public String getSubjectcode (){
	return subjectcode;
	}
	public void setSubjectcode (String subjectcode){
	this.subjectcode=subjectcode;
	}

	/**
	*  是否删除（0未删除，1已删除）
	*/
	public Integer getIsDeleted (){
	return isDeleted;
	}
	public void setIsDeleted (Integer isDeleted){
	this.isDeleted=isDeleted;
	}

	public String getGuideDomain() {
		return guideDomain;
	}

	public void setGuideDomain(String guideDomain) {
		this.guideDomain = guideDomain;
	}

	public String getTechSource() {
		return techSource;
	}

	public void setTechSource(String techSource) {
		this.techSource = techSource;
	}

	public String getProjectActType() {
		return projectActType;
	}

	public void setProjectActType(String projectActType) {
		this.projectActType = projectActType;
	}

	public String getInnovateType() {
		return innovateType;
	}

	public void setInnovateType(String innovateType) {
		this.innovateType = innovateType;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getFzrMobile() {
		return fzrMobile;
	}

	public void setFzrMobile(String fzrMobile) {
		this.fzrMobile = fzrMobile;
	}

	public String getIsSecret() {
		return isSecret;
	}

	public void setIsSecret(String isSecret) {
		this.isSecret = isSecret;
	}

	public String getIsSuspectedEthics() {
		return isSuspectedEthics;
	}

	public void setIsSuspectedEthics(String isSuspectedEthics) {
		this.isSuspectedEthics = isSuspectedEthics;
	}

	public String getProjectFocusArea() {
		return projectFocusArea;
	}

	public void setProjectFocusArea(String projectFocusArea) {
		this.projectFocusArea = projectFocusArea;
	}

	public String getGuideTheme() {
		return guideTheme;
	}

	public void setGuideTheme(String guideTheme) {
		this.guideTheme = guideTheme;
	}

	public String getGuideOther() {
		return guideOther;
	}

	public void setGuideOther(String guideOther) {
		this.guideOther = guideOther;
	}
}