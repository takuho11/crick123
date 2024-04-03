/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-1-8 13:09:40
 *
 */
package cn.topcheer.pms2.api.pdfDownload;

import javax.persistence.*;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name = "PMS_CONTRACTCHANGE")
public class PmsContractChange {

	/**
	 * id
	 */
	private String id;

	/**
	 * (项目名称)
	 */
	private String projectname;

	/**
	 * (计划编号)
	 */
	private String planno;

	/**
	 * (承担单位名称)
	 */
	private String assumeenterprisename;

	/**
	 * (负责人)
	 */
	private String chargeman;

	/**
	 * (归口管理部门)
	 */
	private String casemanagement;

	/**
	 * (归口管理部门)
	 */
	private String casemanagementcode;

	/**
	 * (主管处室)
	 */
	private String chiefdepartment;

	/**
	 * (项目主管)
	 */
	private String chieldofficals;

	/**
	 * (项目开始日期)
	 */
	private Date projectstartdate;

	/**
	 * (项目完成日期)
	 */
	private Date projectenddate;

	/**
	 * (变更内容选项)
	 */
	private String changetype;

	/**
	 * (变更数据)
	 */
	private String changedata;

	/**
	 * (变更原因)
	 */
	private String changereason;

	/**
	 * (申请日期)
	 */
	private Date applicatedate;

	/**
	 * (申请人)
	 */
	private String applicator;

	/**
	 * (申请人ID)
	 */
	private String applicatorid;

	/**
	 * (合同id)
	 */
//	private CrtContractJbxx crtContractJbxx;
	
	/**
	 * 上报日期
	 */
	private Date submitdate;
	
	/**
	 * 保存日期
	 */
	private Date savedate;
	/**
	 * 流程id
	 */
	private String minicurrentstateid;
	/**
	 * 流程状态
	 */
	private String minicurrentstate;
	/**
	 * 流程更新状态
	 */
	private Date updatelasttime;

	/**
	 * 用户发起变更，计财处发起变更
	 */
	private String whochange;

	/**
	 * 变更次数
	 */
	private Integer changenum;

	/**
	 * 新老版本
	 * @return
     */
	private String version;
	
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * (项目名称)
	 */

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	/**
	 * (计划编号)
	 */

	public String getPlanno() {
		return planno;
	}

	public void setPlanno(String planno) {
		this.planno = planno;
	}

	/**
	 * (承担单位名称)
	 */

	public String getAssumeenterprisename() {
		return assumeenterprisename;
	}

	public void setAssumeenterprisename(String assumeenterprisename) {
		this.assumeenterprisename = assumeenterprisename;
	}

	/**
	 * (负责人)
	 */

	public String getChargeman() {
		return chargeman;
	}

	public void setChargeman(String chargeman) {
		this.chargeman = chargeman;
	}

	/**
	 * (归口管理部门)
	 */

	public String getCasemanagement() {
		return casemanagement;
	}

	public void setCasemanagement(String casemanagement) {
		this.casemanagement = casemanagement;
	}

	/**
	 * (归口管理部门)
	 */

	public String getCasemanagementcode() {
		return casemanagementcode;
	}

	public void setCasemanagementcode(String casemanagementcode) {
		this.casemanagementcode = casemanagementcode;
	}

	/**
	 * (主管处室)
	 */

	public String getChiefdepartment() {
		return chiefdepartment;
	}

	public void setChiefdepartment(String chiefdepartment) {
		this.chiefdepartment = chiefdepartment;
	}

	/**
	 * (项目主管)
	 */

	public String getChieldofficals() {
		return chieldofficals;
	}

	public void setChieldofficals(String chieldofficals) {
		this.chieldofficals = chieldofficals;
	}

	/**
	 * (项目开始日期)
	 */

	public Date getProjectstartdate() {
		return projectstartdate;
	}

	public void setProjectstartdate(Date projectstartdate) {
		this.projectstartdate = projectstartdate;
	}

	/**
	 * (项目完成日期)
	 */

	public Date getProjectenddate() {
		return projectenddate;
	}

	public void setProjectenddate(Date projectenddate) {
		this.projectenddate = projectenddate;
	}

	/**
	 * (变更内容选项)
	 */

	public String getChangetype() {
		return changetype;
	}

	public void setChangetype(String changetype) {
		this.changetype = changetype;
	}

	/**
	 * (变更数据)
	 */

	public String getChangedata() {
		return changedata;
	}

	public void setChangedata(String changedata) {
		this.changedata = changedata;
	}

	/**
	 * (变更原因)
	 */

	public String getChangereason() {
		return changereason;
	}

	public void setChangereason(String changereason) {
		this.changereason = changereason;
	}

	/**
	 * (申请日期)
	 */

	public Date getApplicatedate() {
		return applicatedate;
	}

	public void setApplicatedate(Date applicatedate) {
		this.applicatedate = applicatedate;
	}

	/**
	 * (申请人)
	 */

	public String getApplicator() {
		return applicator;
	}

	public void setApplicator(String applicator) {
		this.applicator = applicator;
	}

	/**
	 * (申请人ID)
	 */

	public String getApplicatorid() {
		return applicatorid;
	}

	public void setApplicatorid(String applicatorid) {
		this.applicatorid = applicatorid;
	}

	/**
	 * (合同id)
	 */

//	@ManyToOne
//	@JoinColumn(name = "CONTRACTID")
//	public CrtContractJbxx getCrtContractJbxx() {
//		return crtContractJbxx;
//	}

//	public void setCrtContractJbxx(CrtContractJbxx crtContractJbxx) {
//		this.crtContractJbxx = crtContractJbxx;
//	}

	private String changeflag;

	public String getChangeflag() {
		return changeflag;
	}

	public void setChangeflag(String changeflag) {
		this.changeflag = changeflag;
	}
	
	private String changestate;

	public String getChangestate() {
		return changestate;
	}

	public void setChangestate(String changestate) {
		this.changestate = changestate;
	}

	public Date getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(Date submitdate) {
		this.submitdate = submitdate;
	}
	
	public Date getSavedate(){
		return savedate;
	}
	
	public void setSavedate(Date savedate){
		this.savedate = savedate;
	}

	public String getMinicurrentstateid() {
		return minicurrentstateid;
	}

	public void setMinicurrentstateid(String minicurrentstateid) {
		this.minicurrentstateid = minicurrentstateid;
	}

	public String getMinicurrentstate() {
		return minicurrentstate;
	}

	public void setMinicurrentstate(String minicurrentstate) {
		this.minicurrentstate = minicurrentstate;
	}

	public Date getUpdatelasttime() {
		return updatelasttime;
	}

	public void setUpdatelasttime(Date updatelasttime) {
		this.updatelasttime = updatelasttime;
	}


	public String getWhochange() {
		return whochange;
	}

	public void setWhochange(String whochange) {
		this.whochange = whochange;
	}

	public Integer getChangenum() {
		return changenum;
	}

	public void setChangenum(Integer changenum) {
		this.changenum = changenum;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}