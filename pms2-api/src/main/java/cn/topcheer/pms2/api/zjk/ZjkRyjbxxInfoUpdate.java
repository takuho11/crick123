/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-11-27 17:57:05
 *
 */
package cn.topcheer.pms2.api.zjk;


import cn.topcheer.pms2.api.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name="ZJK_RYJBXX_INFO_UPDATE")
public class ZjkRyjbxxInfoUpdate {


	/**
	 *
	 */
	@FieldDes(name="id",lenth="255",type="String",memo="")
	private String id;

	/**
	 *
	 */
	@FieldDes(name="zjkryjbxxupdateid",lenth="255",type="String",memo="")
	private String zjkryjbxxupdateid;

	/**
	 *  财务类专家符合的入库条件
	 */
	@FieldDes(name="finance",lenth="5000",type="String",memo="财务类专家符合的入库条件")
	private String finance;

	/**
	 *  熟悉科技经费审计的注册会计师
	 */
	@FieldDes(name="finance_zckjs",lenth="600",type="String",memo="熟悉科技经费审计的注册会计师")
	private String finance_zckjs;

	/**
	 *  具有会计、审计、经济专业副高级(含) 以上专业技 术职称， 取得专业技术高级资格或水平证书，或取得中级职称并从事相关行业工作 10 年以上
	 */
	@FieldDes(name="finance_gjkjs",lenth="600",type="String",memo="具有会计、审计、经济专业副高级(含) 以上专业技 术职称， 取得专业技术高级资格或水平证书，或取得中级职称并从事相关行业工作 10 年以上")
	private String finance_gjkjs;

	/**
	 *  省属以上高校、医院、科研院所，上市公司、大型国有企业等具有副高级(含) 以上职称财务(审计) 部门负责人
	 */
	@FieldDes(name="finance_cwfzr",lenth="600",type="String",memo="省属以上高校、医院、科研院所，上市公司、大型国有企业等具有副高级(含) 以上职称财务(审计) 部门负责人")
	private String finance_cwfzr;

	/**
	 *  具有副高级(含) 以上职称的法学专家或国家三级律 师以上资格的人员或律师事务所合伙人或从事相关行业 10 年以上
	 */
	@FieldDes(name="finance_lssws",lenth="600",type="String",memo="具有副高级(含) 以上职称的法学专家或国家三级律 师以上资格的人员或律师事务所合伙人或从事相关行业 10 年以上")
	private String finance_lssws;

	/**
	 *  在银行、证券、股权、信托、保险、融资租赁、融资 担保、资产管理、创业投资等金融机构从事具备3 年以上的创业 投资、银行信贷或上市辅导工作经验， 成功主导或参与 2 个以上投资或信贷案例的中高级管理人员
	 */
	@FieldDes(name="finance_bank",lenth="600",type="String",memo="在银行、证券、股权、信托、保险、融资租赁、融资 担保、资产管理、创业投资等金融机构从事具备3 年以上的创业 投资、银行信贷或上市辅导工作经验， 成功主导或参与 2 个以上投资或信贷案例的中高级管理人员")
	private String finance_bank;

	/**
	 *  管理类专家符合的入库条件
	 */
	@FieldDes(name="management",lenth="5000",type="String",memo="管理类专家符合的入库条件")
	private String management;

	/**
	 *  科技型上市公司、高新技术企业、省级以上高新技术 产业开发区、农业科技园区、科技企业孵化器、众创空间等的高级管理人员
	 */
	@FieldDes(name="management_company",lenth="600",type="String",memo="科技型上市公司、高新技术企业、省级以上高新技术 产业开发区、农业科技园区、科技企业孵化器、众创空间等的高级管理人员")
	private String management_company;

	/**
	 *  政府机关或事业单位县处级(含) 以上职务、省属以上高校、科研院所副处级(含) 以上职务的行政管理人才
	 */
	@FieldDes(name="management_gov",lenth="600",type="String",memo="政府机关或事业单位县处级(含) 以上职务、省属以上高校、科研院所副处级(含) 以上职务的行政管理人才")
	private String management_gov;

	/**
	 *  国家级和省级重点实验室、工程技术研究中心、技术 创新中心、临床医学研究中心、科技资源共享服务平台、科技企 业孵化器、众创空间、大学科技园等创新创业载体等创新平台负责人，省人才基地负责人
	 */
	@FieldDes(name="management_lab",lenth="600",type="String",memo="国家级和省级重点实验室、工程技术研究中心、技术 创新中心、临床医学研究中心、科技资源共享服务平台、科技企 业孵化器、众创空间、大学科技园等创新创业载体等创新平台负责人，省人才基地负责人")
	private String management_lab;

	/**
	 *  科技类社会团体、产业技术创新战略联盟、行业协会学会的主要负责人
	 */
	@FieldDes(name="management_society",lenth="600",type="String",memo="科技类社会团体、产业技术创新战略联盟、行业协会学会的主要负责人")
	private String management_society;


	/**
	 *  从事知识产权管理、科技成果转移转化等工作的机构主要负责人；或具有知识产权、技术推广服务等国家专业资质证
	 */
	@FieldDes(name="management_consulting",lenth="600",type="String",memo="从事知识产权管理、科技成果转移转化等工作的机构主要负责人；或具有知识产权、技术推广服务等国家专业资质证")
	private String management_consulting;

	/**
	 *  省级以上政府或相关行业部门的规划编制者
	 */
	@FieldDes(name="management_writer",lenth="600",type="String",memo="省级以上政府或相关行业部门的规划编制者")
	private String management_writer;

	/**
	 *  科普传播、创作或科普活动组织的科普基地负责人
	 */
	@FieldDes(name="management_kpjd",lenth="600",type="String",memo="科普传播、创作或科普活动组织的科普基地负责人")
	private String management_kpjd;

	/**
	 *  技术类专家符合的入库条件
	 */
	@FieldDes(name="technology",lenth="5000",type="String",memo="技术类专家符合的入库条件")
	private String technology;

	/**
	 *  具有副高级及以上职称(或取得专业技术高级资格或水平证书)或博士后出站并在相关领域开展研究工作 5 年以上
	 */
	@FieldDes(name="technology_title",lenth="600",type="String",memo="具有副高级及以上职称(或取得专业技术高级资格或水平证书)或博士后出站并在相关领域开展研究工作 5 年以上")
	private String technology_title;

	/**
	 *  作为项目(课题) 第一负责人承担过省部级以上科技计划项目(课题) ,并验收通过的项目负责人
	 */
	@FieldDes(name="technology_project_leader",lenth="600",type="String",memo="作为项目(课题) 第一负责人承担过省部级以上科技计划项目(课题) ,并验收通过的项目负责人")
	private String technology_project_leader;

	/**
	 *  省部级二等奖以上科技奖励主要完成人(排名前 3)或省部级三等奖以上科技奖励主要完成人(排名第 1)
	 */
	@FieldDes(name="technology_award_leader",lenth="600",type="String",memo="省部级二等奖以上科技奖励主要完成人(排名前 3)或省部级三等奖以上科技奖励主要完成人(排名第 1)")
	private String technology_award_leader;

	/**
	 *  获得专利金奖或入选省部级以上人才计划的优秀人才
	 */
	@FieldDes(name="technology_rcjh",lenth="600",type="String",memo="获得专利金奖或入选省部级以上人才计划的优秀人才")
	private String technology_rcjh;

	/**
	 *  创新型领军企业、高新技术企业、规上科技型中小企 业、有研发活动的规上工业企业、农业龙头企业等的核心技术负责人，技术先进性服务企业技术服务负责人
	 */
	@FieldDes(name="technology_backbone",lenth="600",type="String",memo="创新型领军企业、高新技术企业、规上科技型中小企 业、有研发活动的规上工业企业、农业龙头企业等的核心技术负责人，技术先进性服务企业技术服务负责人")
	private String technology_backbone;

	/**
	 *  在本产业技术领域获得过国际专利、奖项的优秀海外、境外人才
	 */
	@FieldDes(name="technology_jwrc",lenth="600",type="String",memo="在本产业技术领域获得过国际专利、奖项的优秀海外、境外人才")
	private String technology_jwrc;

	/**
	 *  专家称号
	 */
	@FieldDes(name="talenttitle",lenth="5000",type="String",memo="专家称号")
	private String talenttitle;

	/**
	 *  两院院士
	 */
	@FieldDes(name="lyys",lenth="255",type="String",memo="两院院士")
	private String lyys;

	/**
	 *  长江学者
	 */
	@FieldDes(name="cjxz",lenth="255",type="String",memo="长江学者")
	private String cjxz;

	/**
	 *  国家杰出青年科学基金获得者
	 */
	@FieldDes(name="gjjcqn",lenth="255",type="String",memo="国家杰出青年科学基金获得者")
	private String gjjcqn;

	/**
	 *  国家级有突出贡献专家
	 */
	@FieldDes(name="gjjtcgx",lenth="255",type="String",memo="国家级有突出贡献专家")
	private String gjjtcgx;

	/**
	 *  省部级有突出贡献专家
	 */
	@FieldDes(name="sbjtcgx",lenth="255",type="String",memo="省部级有突出贡献专家")
	private String sbjtcgx;

	/**
	 *  国家级学术、技术带头人
	 */
	@FieldDes(name="gjjdtr",lenth="255",type="String",memo="国家级学术、技术带头人")
	private String gjjdtr;

	/**
	 *  省部级学术、技术带头人
	 */
	@FieldDes(name="sbjdtr",lenth="255",type="String",memo="省部级学术、技术带头人")
	private String sbjdtr;

	/**
	 *  国家级教学名师
	 */
	@FieldDes(name="gjjjxms",lenth="255",type="String",memo="国家级教学名师")
	private String gjjjxms;

	/**
	 *  863、973计划首席科学家
	 */
	@FieldDes(name="sxkjx",lenth="255",type="String",memo="863、973计划首席科学家")
	private String sxkjx;

	/**
	 *  国务院特贴
	 */
	@FieldDes(name="gwytt",lenth="255",type="String",memo="国务院特贴")
	private String gwytt;

	/**
	 *  省政府特贴
	 */
	@FieldDes(name="szftt",lenth="255",type="String",memo="省政府特贴")
	private String szftt;

	/**
	 *  核心专家
	 */
	@FieldDes(name="hxzj",lenth="255",type="String",memo="核心专家")
	private String hxzj;

	/**
	 *  省管专家
	 */
	@FieldDes(name="sgzj",lenth="255",type="String",memo="省管专家")
	private String sgzj;

	/**
	 *  贵州省“百人领军人才”
	 */
	@FieldDes(name="brljrc",lenth="255",type="String",memo="贵州省“百人领军人才”")
	private String brljrc;

	/**
	 *  贵州省“千人创新创业人才”
	 */
	@FieldDes(name="qrcxcyrc",lenth="255",type="String",memo="贵州省“千人创新创业人才” ")
	private String qrcxcyrc;

	/**
	 *  贵州省十百千人才
	 */
	@FieldDes(name="sbqrc",lenth="255",type="String",memo="贵州省十百千人才")
	private String sbqrc;



	/**
	 *
	 */
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		this.id=id;
	}


	/**
	 *
	 */

	public String getZjkryjbxxupdateid (){
		return zjkryjbxxupdateid;
	}
	public void setZjkryjbxxupdateid (String zjkryjbxxupdateid){
		this.zjkryjbxxupdateid=zjkryjbxxupdateid;
	}


	public String getFinance() {
		return finance;
	}

	public void setFinance(String finance) {
		this.finance = finance;
	}

	public String getFinance_zckjs() {
		return finance_zckjs;
	}

	public void setFinance_zckjs(String finance_zckjs) {
		this.finance_zckjs = finance_zckjs;
	}

	public String getFinance_gjkjs() {
		return finance_gjkjs;
	}

	public void setFinance_gjkjs(String finance_gjkjs) {
		this.finance_gjkjs = finance_gjkjs;
	}

	public String getFinance_cwfzr() {
		return finance_cwfzr;
	}

	public void setFinance_cwfzr(String finance_cwfzr) {
		this.finance_cwfzr = finance_cwfzr;
	}

	public String getFinance_lssws() {
		return finance_lssws;
	}

	public void setFinance_lssws(String finance_lssws) {
		this.finance_lssws = finance_lssws;
	}

	public String getFinance_bank() {
		return finance_bank;
	}

	public void setFinance_bank(String finance_bank) {
		this.finance_bank = finance_bank;
	}

	public String getManagement() {
		return management;
	}

	public void setManagement(String management) {
		this.management = management;
	}

	public String getManagement_company() {
		return management_company;
	}

	public void setManagement_company(String management_company) {
		this.management_company = management_company;
	}

	public String getManagement_gov() {
		return management_gov;
	}

	public void setManagement_gov(String management_gov) {
		this.management_gov = management_gov;
	}

	public String getManagement_lab() {
		return management_lab;
	}

	public void setManagement_lab(String management_lab) {
		this.management_lab = management_lab;
	}

	public String getManagement_society() {
		return management_society;
	}

	public void setManagement_society(String management_society) {
		this.management_society = management_society;
	}

	public String getManagement_consulting() {
		return management_consulting;
	}

	public void setManagement_consulting(String management_consulting) {
		this.management_consulting = management_consulting;
	}

	public String getManagement_writer() {
		return management_writer;
	}

	public void setManagement_writer(String management_writer) {
		this.management_writer = management_writer;
	}

	public String getManagement_kpjd() {
		return management_kpjd;
	}

	public void setManagement_kpjd(String management_kpjd) {
		this.management_kpjd = management_kpjd;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getTechnology_title() {
		return technology_title;
	}

	public void setTechnology_title(String technology_title) {
		this.technology_title = technology_title;
	}

	public String getTechnology_project_leader() {
		return technology_project_leader;
	}

	public void setTechnology_project_leader(String technology_project_leader) {
		this.technology_project_leader = technology_project_leader;
	}

	public String getTechnology_award_leader() {
		return technology_award_leader;
	}

	public void setTechnology_award_leader(String technology_award_leader) {
		this.technology_award_leader = technology_award_leader;
	}

	public String getTechnology_rcjh() {
		return technology_rcjh;
	}

	public void setTechnology_rcjh(String technology_rcjh) {
		this.technology_rcjh = technology_rcjh;
	}

	public String getTechnology_backbone() {
		return technology_backbone;
	}

	public void setTechnology_backbone(String technology_backbone) {
		this.technology_backbone = technology_backbone;
	}

	public String getTechnology_jwrc() {
		return technology_jwrc;
	}

	public void setTechnology_jwrc(String technology_jwrc) {
		this.technology_jwrc = technology_jwrc;
	}

	public String getTalenttitle() {
		return talenttitle;
	}

	public void setTalenttitle(String talenttitle) {
		this.talenttitle = talenttitle;
	}

	public String getLyys() {
		return lyys;
	}

	public void setLyys(String lyys) {
		this.lyys = lyys;
	}

	public String getCjxz() {
		return cjxz;
	}

	public void setCjxz(String cjxz) {
		this.cjxz = cjxz;
	}

	public String getGjjcqn() {
		return gjjcqn;
	}

	public void setGjjcqn(String gjjcqn) {
		this.gjjcqn = gjjcqn;
	}

	public String getGjjtcgx() {
		return gjjtcgx;
	}

	public void setGjjtcgx(String gjjtcgx) {
		this.gjjtcgx = gjjtcgx;
	}

	public String getSbjtcgx() {
		return sbjtcgx;
	}

	public void setSbjtcgx(String sbjtcgx) {
		this.sbjtcgx = sbjtcgx;
	}

	public String getGjjdtr() {
		return gjjdtr;
	}

	public void setGjjdtr(String gjjdtr) {
		this.gjjdtr = gjjdtr;
	}

	public String getSbjdtr() {
		return sbjdtr;
	}

	public void setSbjdtr(String sbjdtr) {
		this.sbjdtr = sbjdtr;
	}

	public String getGjjjxms() {
		return gjjjxms;
	}

	public void setGjjjxms(String gjjjxms) {
		this.gjjjxms = gjjjxms;
	}

	public String getSxkjx() {
		return sxkjx;
	}

	public void setSxkjx(String sxkjx) {
		this.sxkjx = sxkjx;
	}

	public String getGwytt() {
		return gwytt;
	}

	public void setGwytt(String gwytt) {
		this.gwytt = gwytt;
	}

	public String getSzftt() {
		return szftt;
	}

	public void setSzftt(String szftt) {
		this.szftt = szftt;
	}

	public String getHxzj() {
		return hxzj;
	}

	public void setHxzj(String hxzj) {
		this.hxzj = hxzj;
	}

	public String getSgzj() {
		return sgzj;
	}

	public void setSgzj(String sgzj) {
		this.sgzj = sgzj;
	}

	public String getBrljrc() {
		return brljrc;
	}

	public void setBrljrc(String brljrc) {
		this.brljrc = brljrc;
	}

	public String getQrcxcyrc() {
		return qrcxcyrc;
	}

	public void setQrcxcyrc(String qrcxcyrc) {
		this.qrcxcyrc = qrcxcyrc;
	}

	public String getSbqrc() {
		return sbqrc;
	}

	public void setSbqrc(String sbqrc) {
		this.sbqrc = sbqrc;
	}
}