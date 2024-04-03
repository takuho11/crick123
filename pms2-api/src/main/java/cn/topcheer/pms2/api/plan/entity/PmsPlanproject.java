/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-1-8 13:09:42
 *
 */
package cn.topcheer.pms2.api.plan.entity;

import cn.topcheer.pms2.api.annotation.FieldDes;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 大批次表
 */
@Entity
@Table(name="PMS_PLANPROJECT")
public class PmsPlanproject {


	/**
 	 *  
 	 */
	private String id;

	/**
 	 *  计划项目名称
 	 */
	private String projectname;

	/**
 	 *  计划项目简称
 	 */
	private String projectshortname;

	/**
 	 *  计划项目类别
 	 */
	private String plantype;
	
	/**
 	 *  计划项目类别代码1
 	 */
	private String applicationtypecode1;

	/**
 	 *  计划项目类别1
 	 */
	private String applicationtypename1;

	/**
 	 *  计划项目类别代码2
 	 */
	private String applicationtypecode2;

	/**
 	 *  竞争类、平台类、普惠类
 	 */
	private String applicationtypename2;

	/**
	 *  1启用 0 暂停 2 不启用
	 */
	private Integer state;

	/**
	 * 来自哪个系统
	 */
	private String system;

	/**
	 * 二级类型
	 */
	private String category;

	/**
	 * 合同流程中大处室id，方便处室查询处室内部合同
	 */
	private String ht_departmentid;

	/**
	 * 责任处室id
	 */
	private String departmentid;

	/**
	 * 用于首页展示分类的处室id
	 */
	private String home_departmentid;

	/**
	 * 跳转其他系统的地址
	 */
	private String othersystemurl;

	/**
	 * 申报提示，如果这个字段有值，默认先取
	 */
	private String sbtips;

	/**
	 * 排序
	 */
	private Integer seq;

	/**
	 * 是否为基金项目，1表示是，空表示不是
	 */
	private String isjj;

	/**
	 * 是否为预申报批次，1表示是，空表示不是
	 */
	private String hasprepare;

	/**
	 * 领域集合：比如重点研发有xxx领域区别
	 */
	private String fields;

	private String olddata;//来源于老系统？old：null

	/**
	 *  技术领域分类，sys_paramtype的分类ID
	 */
	private String rankid;

	/**
	 * 工作流配置
	 */
	private String workflowdefineid;

	/**
 	 *  技术领域字段，对于pms_projectbase表中的字段名称
 	 */
	private String rankcolumn;

	//专家抽取的时候是否能继续抽之前评审过的专家
	private Integer finishtypenum;
	/**
	 *  会评技术领域字段，对于pms_projectbase表中的字段名称
	 */
	private String rankcolumn_hp;
	//该列值名称的项目，必须由一样的专家评审
	private String sameexpert;
	/**
	 *  综评技术领域字段，对于pms_projectbase表中的字段名称
	 */
	private String rankcolumn_zp;

	private String markkey;

	private String zjzwfwurl;

	/*年度报告填报期开始时间*/
	private Date ndbg_startdate;
	/*年度报告填报期结束时间*/
	private Date ndbg_enddate;
	/*年度报告推荐单位审核结束时间*/
	private Date ndbg_tjdwshenddate;
	private Date hbz_startdate;//后补助开始时间
	private Date hbz_enddate;//后补助结束时间
	private String groupname;//大批次进行分组后的名称
	private String reviewtype;//评审类型（ReviewTypeEnum：DECLARE，TECH_AWARDS）

	private String ishallshow;//是否办事大厅显示；值：1表示显示；0 or空 表示不显示

	private String companyId;//单位id

	private String innovatechain;//创新链条  for Gz
	private String faseobject;//支持对象 for Gz
	private String handler;//经办人 for Gz （目前不用）
	private String supportcontent;//支持内容  for Gz
	private String supportbasis;//支持依据  for Gz
	private String supportway;//支持方式  for Gz
	private String belongsubject;//所属专题  for Gz

	/**
	 *  政务网事项编码
	 */
	@ApiModelProperty("政务网事项编码")
	@TableField("MATTER_CODE")
	@Column(columnDefinition = "MATTER_CODE")
	private String matterCode;

	/**
	 *  政务网事项类别
	 */
	@ApiModelProperty("政务网事项类别")
	@TableField("MATTER_TYPE")
	@Column(columnDefinition = "MATTER_TYPE")
	private String matterType;

	/**
	 * 政务网办件类型
	 */
	@ApiModelProperty("政务网办件类型")
	@TableField("HANDLE_DOCUMENT_TYPE")
	@Column(columnDefinition = "HANDLE_DOCUMENT_TYPE")
	private String handleDocumentType;

	/**
	 * 政务网办件层级
	 */
	@ApiModelProperty("政务网办件层级")
	@TableField("HANDLE_GRADE")
	@Column(columnDefinition = "HANDLE_GRADE")
	private String handleGrade;

	/**
	 * 政务网业务申报系统
	 */
	@ApiModelProperty("政务网业务申报系统")
	@TableField("BUSINESS_SYSTEM")
	@Column(columnDefinition = "BUSINESS_SYSTEM")
	private String businessSystem;

	/**
	 * 政务网业务审批系统
	 */
	@ApiModelProperty("政务网业务审批系统")
	@TableField("BUSINESS_EXAMINE_SYSTEM")
	@Column(columnDefinition = "BUSINESS_EXAMINE_SYSTEM")
	private String businessExamineSystem;

	/**
	 *  事项所属区划
	 */
	@ApiModelProperty("事项所属区划")
	@TableField("ITEM_REGION_CODE")
	@Column(columnDefinition = "ITEM_REGION_CODE")
	private String itemRegionCode;

	/**
	 *  事项所属区划名称
	 */
	@ApiModelProperty("事项所属区划名称")
	@TableField("ITEM_REGION_NAME")
	@Column(columnDefinition = "ITEM_REGION_NAME")
	private String itemRegionName;

	/**
	 *  事项所属部门
	 */
	@ApiModelProperty("事项所属部门")
	@TableField("ITEM_ORGAN_CODE")
	@Column(columnDefinition = "ITEM_ORGAN_CODE")
	private String itemOrganCode;

	/**
	 *  事项所属部门名称
	 */
	@ApiModelProperty("事项所属部门名称")
	@TableField("ITEM_ORGAN_NAME")
	@Column(columnDefinition = "ITEM_ORGAN_NAME")
	private String itemOrganName;

	/**
	 *  事项办理承诺时限
	 */
	@ApiModelProperty("事项办理承诺时限")
	@TableField("PROMISETIME_LIMIT")
	@Column(columnDefinition = "PROMISETIME_LIMIT")
	@FieldDes(name = "promisetime_limit", type = "Integer", memo = "事项办理承诺时限")
	private Integer promisetimeLimit;

	/**
	 *  事项办理承诺时限单位
	 */
	@ApiModelProperty("事项办理承诺时限单位")
	@TableField("PROMISETIME_UNIT")
	@Column(columnDefinition = "PROMISETIME_UNIT")
	private String promisetimeUnit;

	/**
	 *  法定时限
	 */
	@ApiModelProperty("法定时限")
	@TableField("TIME_LIMIT")
	@Column(columnDefinition = "TIME_LIMIT")
	@FieldDes(name = "time_limit", type = "Integer", memo = "法定时限")
	private Integer timeLimit;

	/**
	 *  规定办理时限的单位
	 */
	@ApiModelProperty("规定办理时限的单位")
	@TableField("TIME_UNIT")
	@Column(columnDefinition = "TIME_UNIT")
	private String timeUnit;

	/**
	 * 归口单位
	 */
	@ApiModelProperty("归口单位")
	@TableField("ORGANIZE_ID")
	@Column(columnDefinition = "ORGANIZE_ID")
	private String organizeId;

	/**
	 * 归口父级单位ID
	 */
	@ApiModelProperty("归口父级单位ID")
	@TableField("ORGANIZE_PARENT_ID")
	@Column(columnDefinition = "ORGANIZE_PARENT_ID")
	private String organizeParentId;

	/**
	 * 创建用户id
	 * @return
	 */
	@ApiModelProperty("创建用户id")
	@TableField("CREATE_USER")
	@Column(columnDefinition = "CREATE_USER")
	private String createUser;
	/*-----------------------------------分割线-----------------------------------*/



	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  计划项目名称
 	 */
	
	public String getProjectname (){
		return projectname;
	}
	public void setProjectname (String projectname){
		  this.projectname=projectname;
	}


	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	/**
 	 *  计划项目简称
 	 */
	
	public String getProjectshortname (){
		return projectshortname;
	}
	public void setProjectshortname (String projectshortname){
		  this.projectshortname=projectshortname;
	}


	public String getZjzwfwurl() {
		return zjzwfwurl;
	}

	public void setZjzwfwurl(String zjzwfwurl) {
		this.zjzwfwurl = zjzwfwurl;
	}

	public String getMarkkey() {
		return markkey;
	}

	public void setMarkkey(String markkey) {
		this.markkey = markkey;
	}




	public String getRankcolumn_hp() {
		return rankcolumn_hp;
	}
	public void setRankcolumn_hp(String rankcolumn_hp) {
		this.rankcolumn_hp = rankcolumn_hp;
	}

	public String getRankcolumn_zp() {
		return rankcolumn_zp;
	}
	public void setRankcolumn_zp(String rankcolumn_zp) {
		this.rankcolumn_zp = rankcolumn_zp;
	}


	public String getSameexpert() {return sameexpert;}
	public void setSameexpert(String sameexpert) {this.sameexpert = sameexpert;}

	public Integer getFinishtypenum() {
		return finishtypenum;
	}

	public void setFinishtypenum(Integer finishtypenum) {
		this.finishtypenum = finishtypenum;
	}

	public String getRankcolumn() {
		return rankcolumn;
	}
	public void setRankcolumn(String rankcolumn) {
		this.rankcolumn = rankcolumn;
	}

	public String getOlddata() {
		return olddata;
	}
	public void setOlddata(String olddata) {
		this.olddata = olddata;
	}

	public String getRankid() {
		return rankid;
	}
	public void setRankid(String rankid) {
		this.rankid = rankid;
	}

	/**
 	 *  计划项目类别
 	 */
	
	public String getPlantype (){
		return plantype;
	}
	public void setPlantype (String plantype){
		  this.plantype=plantype;
	}


	/**
 	 *  计划项目类别代码1
 	 */
	
	public String getApplicationtypecode1 (){
		return applicationtypecode1;
	}
	public void setApplicationtypecode1 (String applicationtypecode1){
		  this.applicationtypecode1=applicationtypecode1;
	}


	/**
 	 *  计划项目类别1
 	 */
	
	public String getApplicationtypename1 (){
		return applicationtypename1;
	}
	public void setApplicationtypename1 (String applicationtypename1){
		  this.applicationtypename1=applicationtypename1;
	}


	/**
 	 *  计划项目类别代码2
 	 */
	
	public String getApplicationtypecode2 (){
		return applicationtypecode2;
	}
	public void setApplicationtypecode2 (String applicationtypecode2){
		  this.applicationtypecode2=applicationtypecode2;
	}


	/**
 	 *  计划项目类别2
 	 */
	
	public String getApplicationtypename2 (){
		return applicationtypename2;
	}
	public void setApplicationtypename2 (String applicationtypename2){
		  this.applicationtypename2=applicationtypename2;
	}


	/**
 	 *  1启用 0 暂停 2 不启用
 	 */
	
	public Integer getState(){
		return state;
	}
	public void setState (Integer state){
		  this.state=state;
	}



	private List<PmsPlanprojectbatch> pmsPlanprojectbatchs;
	
	@OneToMany(mappedBy= "pmsPlanproject",cascade={CascadeType.ALL})
	public List<PmsPlanprojectbatch> getPmsPlanprojectbatchs(){
		return pmsPlanprojectbatchs;
	}
	
	public void setPmsPlanprojectbatchs (List<PmsPlanprojectbatch> pmsPlanprojectbatchs){
		  this.pmsPlanprojectbatchs=pmsPlanprojectbatchs;
	}	
	
//	/**
// 	 *  页面模板Id
// 	 */
//	private PmsPagetemplate pmsPagetemplate;
//
//	@ManyToOne
//	@JoinColumn(name="PAGETEMPLATEID")
//	public PmsPagetemplate getPmsPagetemplate (){
//		return pmsPagetemplate;
//	}
//	public void setPmsPagetemplate (PmsPagetemplate pmsPagetemplate){
//		  this.pmsPagetemplate=pmsPagetemplate;
//	}
    
	/**
 	 *  申报指南ID
 	 */
	private PmsDeclarationguide pmsDeclarationguide;
	
	@ManyToOne
	@JoinColumn(name="DECLARATIONGUIDEID")
	public PmsDeclarationguide getPmsDeclarationguide (){
		return pmsDeclarationguide;
	}
	public void setPmsDeclarationguide (PmsDeclarationguide pmsDeclarationguide){
		  this.pmsDeclarationguide=pmsDeclarationguide;
	}

	public String getWorkflowdefineid() {
		return workflowdefineid;
	}
	public void setWorkflowdefineid(String workflowdefineid) {
		this.workflowdefineid = workflowdefineid;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}


	public String getHt_departmentid() {
		return ht_departmentid;
	}

	public void setHt_departmentid(String ht_departmentid) {
		this.ht_departmentid = ht_departmentid;
	}


	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getOthersystemurl() {
		return othersystemurl;
	}

	public void setOthersystemurl(String othersystemurl) {
		this.othersystemurl = othersystemurl;
	}


	public String getSbtips() {
		return sbtips;
	}

	public void setSbtips(String sbtips) {
		this.sbtips = sbtips;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public Date getNdbg_startdate() {
		return ndbg_startdate;
	}

	public void setNdbg_startdate(Date ndbg_startdate) {
		this.ndbg_startdate = ndbg_startdate;
	}

	public Date getNdbg_enddate() {
		return ndbg_enddate;
	}

	public void setNdbg_enddate(Date ndbg_enddate) {
		this.ndbg_enddate = ndbg_enddate;
	}

	public Date getNdbg_tjdwshenddate() {
		return ndbg_tjdwshenddate;
	}

	public void setNdbg_tjdwshenddate(Date ndbg_tjdwshenddate) {
		this.ndbg_tjdwshenddate = ndbg_tjdwshenddate;
	}

	public String getHome_departmentid() {
		return home_departmentid;
	}

	public void setHome_departmentid(String home_departmentid) {
		this.home_departmentid = home_departmentid;
	}

	public Date getHbz_startdate() {
		return hbz_startdate;
	}

	public void setHbz_startdate(Date hbz_startdate) {
		this.hbz_startdate = hbz_startdate;
	}

	public Date getHbz_enddate() {
		return hbz_enddate;
	}

	public void setHbz_enddate(Date hbz_enddate) {
		this.hbz_enddate = hbz_enddate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getIsjj() {
		return isjj;
	}

	public void setIsjj(String isjj) {
		this.isjj = isjj;
	}

	public String getReviewtype() {
		return reviewtype;
	}

	public void setReviewtype(String reviewtype) {
		this.reviewtype = reviewtype;
	}


	public String getHasprepare() {
		return hasprepare;
	}

	public void setHasprepare(String hasprepare) {
		this.hasprepare = hasprepare;
	}

	public String getInnovatechain() {
		return innovatechain;
	}

	public void setInnovatechain(String innovatechain) {
		this.innovatechain = innovatechain;
	}

	public String getFaseobject() {
		return faseobject;
	}

	public void setFaseobject(String faseobject) {
		this.faseobject = faseobject;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getIshallshow() {
		return ishallshow;
	}

	public void setIshallshow(String ishallshow) {
		this.ishallshow = ishallshow;
	}

	public String getSupportcontent() {
		return supportcontent;
	}

	public void setSupportcontent(String supportcontent) {
		this.supportcontent = supportcontent;
	}

	public String getSupportbasis() {
		return supportbasis;
	}

	public void setSupportbasis(String supportbasis) {
		this.supportbasis = supportbasis;
	}

	public String getSupportway() {
		return supportway;
	}

	public void setSupportway(String supportway) {
		this.supportway = supportway;
	}

	public String getBelongsubject() {
		return belongsubject;
	}

	public void setBelongsubject(String belongsubject) {
		this.belongsubject = belongsubject;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getMatterCode() {
		return matterCode;
	}

	public void setMatterCode(String matterCode) {
		this.matterCode = matterCode;
	}

	public String getMatterType() {
		return matterType;
	}

	public void setMatterType(String matterType) {
		this.matterType = matterType;
	}

	public String getHandleDocumentType() {
		return handleDocumentType;
	}

	public void setHandleDocumentType(String handleDocumentType) {
		this.handleDocumentType = handleDocumentType;
	}

	public String getHandleGrade() {
		return handleGrade;
	}

	public void setHandleGrade(String handleGrade) {
		this.handleGrade = handleGrade;
	}

	public String getBusinessSystem() {
		return businessSystem;
	}

	public void setBusinessSystem(String businessSystem) {
		this.businessSystem = businessSystem;
	}

	public String getBusinessExamineSystem() {
		return businessExamineSystem;
	}

	public void setBusinessExamineSystem(String businessExamineSystem) {
		this.businessExamineSystem = businessExamineSystem;
	}

	public String getItemRegionCode() {
		return itemRegionCode;
	}

	public void setItemRegionCode(String itemRegionCode) {
		this.itemRegionCode = itemRegionCode;
	}

	public String getItemRegionName() {
		return itemRegionName;
	}

	public void setItemRegionName(String itemRegionName) {
		this.itemRegionName = itemRegionName;
	}

	public String getItemOrganCode() {
		return itemOrganCode;
	}

	public void setItemOrganCode(String itemOrganCode) {
		this.itemOrganCode = itemOrganCode;
	}

	public String getItemOrganName() {
		return itemOrganName;
	}

	public void setItemOrganName(String itemOrganName) {
		this.itemOrganName = itemOrganName;
	}

	public Integer getPromisetimeLimit() {
		return promisetimeLimit;
	}

	public void setPromisetimeLimit(Integer promisetimeLimit) {
		this.promisetimeLimit = promisetimeLimit;
	}

	public String getPromisetimeUnit() {
		return promisetimeUnit;
	}

	public void setPromisetimeUnit(String promisetimeUnit) {
		this.promisetimeUnit = promisetimeUnit;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public String getOrganizeId() {
		return organizeId;
	}

	public void setOrganizeId(String organizeId) {
		this.organizeId = organizeId;
	}

	public String getOrganizeParentId() {
		return organizeParentId;
	}

	public void setOrganizeParentId(String organizeParentId) {
		this.organizeParentId = organizeParentId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
}

