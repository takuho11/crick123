/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2017-1-23 9:49:28
 *
 */
package cn.topcheer.pms2.api.zjk;


import cn.topcheer.pms2.api.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="ZJK_RYJBXX_UPDATE")
public class ZjkRyjbxxUpdate {


	/**
 	 *  专家id
 	 */
	@Id
	private String id;


	/**
 	 *  专家姓名
 	 */
	private String name;


	/**
 	 *  性别
 	 */
	private String gender;


	/**
 	 *  出生日期
 	 */
	private String birthday;


	/**
 	 *  出生地
 	 */
	private String birthplace;
	

	/**
 	 *  国别或地区
 	 */
	private String nationality;


	/**
	 *  民族
	 */
	private String nation;
	

	/**
 	 *  单位名称
 	 */
	private String organization;


	/**
	 *  单位地址
	 */
	private String organization_address;


	/**
 	 *  单位性质
 	 */
	private String organization_property;
	

	/**
 	 *  单位所在省
 	 */
	private String organization_province;
	

	/**
 	 *  单位所在市
 	 */
	private String organization_city;


	/**
 	 *  单位所在县
 	 */
	private String organization_county;


	/**
	 *  单位联系电话
	 */
	private String organization_contactphone;


	/**
	 *  单位联系人
	 */
	private String organization_contactman;


	/**
 	 *  通讯地址
 	 */
	private String postal_address;
	

	/**
 	 *  邮政编码
 	 */
	private String postcode;
	

	/**
 	 *  职称（正高级、副高级、中级）
 	 */
	private String position_title;


	/**
	 *  职称授予时间
	 */
	private Date positiondate;
	

	/**
 	 *
 	 */
	private String position_level;


	/**
 	 *	现从事专业
 	 */
	private String workforprofession;


	/**
 	 *  职务
 	 */
	@FieldDes(name="professional_title",lenth="255",type="String",memo="职务")
	private String professional_title;
	

	/**
 	 *  最高学历
 	 */
	private String education;
	

	/**
 	 *  最高学位
 	 */
	private String degree;
	

	/**
 	 *  手机号码
 	 */
	private String mobile_telephone;
	

	/**
 	 *  办公室电话
 	 */
	private String office_telephone;
	

	/**
 	 *  住宅电话
 	 */
	private String home_telephone;
	

	/**
 	 *  传真
 	 */
	private String fax;
	

	/**
 	 *  邮箱
 	 */
	private String email;
	

	/**
 	 *  证件类型
 	 */
	private String id_type;
	

	/**
 	 *  证件号码
 	 */
	private String id_number;
	

	/**
 	 *  开户银行
 	 */
	private String bank_name;


	/**
	 *  所属支行
	 */
	private String bank_zhihang;


	/**
	 *  开户姓名
	 */
	private String bank_accountname;


	/**
 	 *  开户账号
 	 */
	private String bank_account;


	/**
	 *	个人简介
	 */
	private String introduce;


	/**
 	 *  政治面貌
 	 */
	private String political;


	/**
 	 *  专家类型
 	 */
	private String expert_type;


	/**
 	 *  
 	 */
	private String cdxmlx;
	

	/**
 	 *  
 	 */
	private String zjlbxx;
	

	/**
 	 *  
 	 */
	private String rktj;
	

	/**
 	 *  
 	 */
	private String zjlbxx1;
	

	/**
 	 *  关键词
 	 */
	private String keywords;


	/**
 	 *  流程id
 	 */
	private String minicurrentstateid;
	

	/**
 	 *  流程状态
 	 */
	private String minicurrentstate;
	

	/**
 	 *  更新时间
 	 */
	private Date updatelasttime;


	/**
	 *  保存时间
	 */
	private Date savedate;


	/**
	 *  提交时间
	 */
	private Date submitdate;


	/**
 	 *  
 	 */
	private String belonglabid;
	

	/**
 	 *  是否院士
 	 */
	private String yuanshi;


	/**
 	 *  是否享受特殊津贴
 	 */
	private String subsidy;


	/**
 	 *  是否是博士生导师
 	 */
	private String istutor;


	/**
 	 *  所在部门（院系）
 	 */
	private String jtgzdw;
	

	/**
 	 *  市内、市外
 	 */
	private String region;

	/**
	 *  省内、省外
	 */
	private String province_region;


	/**
	 *  所学专业
	 */
	private String major;


	/**
	 *  用户id
	 */
	private String userid;


	/**
	 *  导入标记
	 *  专家来源  2为省厅专家，其余为广州专家（不是专家所属）
	 */
	private String drbj;


	/**
	 *  单位id
	 */
	private String unitid;


	/**
	 *  单位统一社会信用代码
	 */
	private String uniformcode;


	/**
	 *  单位组织机构代码
	 */
	private String orginizationcode;


	/**
	 *  毕业学校
	 */
	private String school;


	/**
	 *  所在院系
	 */
	private String schooldepartment;


	/**
	 *  银行联号
	 */
	private String bankinterbank;


	/**
	 *  注册时间
	 */
	private Date registerdate;


	/**
	 *  毕业时间
	 */
	private Date schooldate;


	/**
	 *  留学国家
	 */
	private String studycountry;


	/**
	 *  入库标记---空：未入库，1：入库，2：出库
	 */
	private String rkflag;


	/**
	 *  单位管理员审核专家标记，空：未提交，0：审核中；1：审核通过；2：审核不通过
	 */
	private String approvestate;


	/**
	 *  是否是核心专家 1：是 0或空：否
	 */
	private String iscoreexpert;


	/**
	 *  是否是基金核心专家 1：是 0或空：否
	 */
	private String isjjcoreexpert;


	/**
	 *  是否可以抽取　0不可抽取
	 */
	private String iscanpick;


	/**
	 *  诚信分数
	 */
	private Double sciencescore;


	/**
	 *  是否确认信用1：是，0：否
	 */
	private String ischeckcredit;


	/**
	 *  专家信用确认时间
	 */
	private Date checkcreditdate;


	/**
	 *  专家信用等级 默认为AA
	 */
	private String creditrank;


	/**
	 *  是否企业专家  0:非企业专家; 1:企业专家
	 */
	private String isqyzj;


	/**
	 *  专家拒绝信用管理协议说明
	 */
	private String denycreditreason;


	/**
	 *  熟悉外语（种类）名称
	 */
	private String foreignlanguage;


	/**
	 *  熟悉程度
	 */
	private String languagelevel;


	/**
	 *  相关网址
	 */
	private String website;


	/**
	 *  是否愿意参加省内各地市科技咨询工作
	 */
	private String iswillsn;


	/**
	 *  是否愿意参加省外科技咨询工作
	 */
	private String iswillsw;


	/**
	 *  汇款地址
	 */
	private String remittanceaddress;


	/**
	 *  符合的入库条件
	 */
	private String expert_classify;


	/**
	 *  专业领域
	 */
	private String expert_field;


	/**
	 *  投资方向
	 */
	private String investment_orientation;


	/**
	 *  是否服务大赛
	 */
	private String competition;


	/**
	 *  服务大赛领域
	 */
	private String competition_field;


	/**
	 *  公示时间
	 */
	private Date gsdate;


	/**
	 *  是否公示异议 1：是；null：否
	 */
	private String gsdissent;


	/**
	 *  异议内容
	 */
	private String dissentcontent;


	/**
	 *  异议时间
	 */
	private Date dissentdate;

	/**
	 *  湾区调用（null为未调用）
	 */
	private String wqcheck;

	/**
	 * 具体工作单位统一社会信用代码（有学院存学院，没有学院存高校/单位）
	 */
	private String jtgzdw_uniformcode;

	/**
	 *  短信标记
	 */
	@FieldDes(name="messageflag",lenth="50",type="String",memo="短信标记")
	private String messageflag;

	/**
	 *  承诺书标记
	 */
	@FieldDes(name="cnsflag",lenth="255",type="String",memo="承诺书标记")
	private String cnsflag;

	/**
	 *  承诺书时间
	 */
	@FieldDes(name="cnsdate",type="Date",memo="承诺书时间")
	private Date cnsdate;

	/**
	 *  是否有国际合作经历
	 */
	@FieldDes(name="gjhzjl",lenth="255",type="String",memo="是否有国际合作经历")
	private String gjhzjl;

	/**
	 *  是否有科普工作经历
	 */
	@FieldDes(name="kpgzjl",lenth="255",type="String",memo="是否有科普工作经历")
	private String kpgzjl;

	/**
	 *  0类专家标记理由
	 */
	@FieldDes(name="zero_reason",lenth="1000",type="String",memo="0类专家标记理由")
	private String zero_reason;

	/**
	 *  1类专家标记理由
	 */
	@FieldDes(name="one_reason",lenth="255",type="String",memo="1类专家标记理由")
	private String one_reason;

	/**
	 *  主聘任单位名称（双聘专家）
	 */
	@FieldDes(name="main_unitname",type="String",memo="主聘任单位名称（双聘专家）")
	private String main_unitname;

	/**
	 *  主聘任单位统一社会信用代码（双聘专家）
	 */
	@FieldDes(name="main_uniformcode",type="String",memo="主聘任单位统一社会信用代码（双聘专家）")
	private String main_uniformcode;

	/**
	 *  执业资格
	 */
	@FieldDes(name="zyzg",lenth="255",type="String",memo="执业资格")
	private String zyzg;

	/**
	 *  是否外国专家
	 */
	@FieldDes(name="isforeign",lenth="255",type="String",memo="是否外国专家")
	private String isforeign;

	/**
	 *  外国专家类型
	 */
	@FieldDes(name="foreigntype",lenth="1000",type="String",memo="外国专家类型")
	private String foreigntype;

	/**
	 * 一级职称
	 */
	@FieldDes(name="titledetailone",lenth="255",type="String",memo="一级职称")
	private String titledetailone;

	/**
	 * 二级职称
	 */
	@FieldDes(name="titledetailtwo",lenth="255",type="String",memo="二级职称")
	private String titledetailtwo;

	/**
	 *  省
	 */
	@FieldDes(name="province",lenth="255",type="String",memo="省")
	private String province;

	/**
	 *  市
	 */
	@FieldDes(name="city",lenth="255",type="String",memo="市")
	private String city;

	/**
	 *  县
	 */
	@FieldDes(name="county",lenth="255",type="String",memo="县")
	private String county;

	/**
	 *  地址
	 */
	@FieldDes(name="address",lenth="255",type="String",memo="地址")
	private String address;

	/**
	 *  开户行所在省
	 */
	@FieldDes(name="bank_province",lenth="255",type="String",memo="开户行所在省")
	private String bank_province;
	/**
	 *  开户行所在省
	 */
	@FieldDes(name="bank_city",lenth="255",type="String",memo="开户行所在市")
	private String bank_city;


	/**
	 *  推荐单位id
	 */
	@FieldDes(name="casemanagementid", lenth="255", type="String", memo="推荐单位id")
	private String casemanagementid;

	/**
	 *  推荐单位名称
	 */
	@FieldDes(name="casemanagement", lenth="255", type="String", memo="推荐单位名称")
	private String casemanagement;

	/**
	 *  专家称号类型
	 */
	@FieldDes(name="expert_title", lenth="500", type="String", memo="专家称号类型")
	private String expert_title;

	/**
	 *  变更推荐单位id
	 */
	@FieldDes(name="changecasemanagementid", lenth="255", type="String", memo="变更推荐单位id")
	private String changecasemanagementid;

	/**
	 *  变更推荐单位名称
	 */
	@FieldDes(name="changecasemanagement", lenth="255", type="String", memo="变更推荐单位名称")
	private String changecasemanagement;




	/*--------------------------------------------------------------------下面是 get/set集合-----------------------------------------------------*/

	public String getWqcheck() {
		return wqcheck;
	}

	public void setWqcheck(String wqcheck) {
		this.wqcheck = wqcheck;
	}

	public String getIscanpick() {
		return iscanpick;
	}
	public void setIscanpick(String iscanpick) {
		this.iscanpick = iscanpick;
	}


	/**
 	 *  
 	 */
	
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}



	/**
 	 *  
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}




	/**
 	 *  
 	 */
	
	public String getGender (){
		return gender;
	}
	public void setGender (String gender){
		  this.gender=gender;
	}


	/**
 	 *  
 	 */
	
	public String getBirthday (){
		return birthday;
	}
	public void setBirthday (String birthday){
		  this.birthday=birthday;
	}


	/**
 	 *  
 	 */
	
	public String getBirthplace (){
		return birthplace;
	}
	public void setBirthplace (String birthplace){
		  this.birthplace=birthplace;
	}


	/**
 	 *  
 	 */
	
	public String getNationality (){
		return nationality;
	}
	public void setNationality (String nationality){
		  this.nationality=nationality;
	}



	/**
 	 *  
 	 */
	
	public String getOrganization (){
		return organization;
	}
	public void setOrganization (String organization){
		  this.organization=organization;
	}


	/**
 	 *  
 	 */
	
	public String getOrganization_property (){
		return organization_property;
	}
	public void setOrganization_property (String organization_property){
		  this.organization_property=organization_property;
	}


	/**
 	 *  
 	 */
	
	public String getOrganization_province (){
		return organization_province;
	}
	public void setOrganization_province (String organization_province){
		  this.organization_province=organization_province;
	}


	/**
 	 *  
 	 */
	
	public String getOrganization_city (){
		return organization_city;
	}
	public void setOrganization_city (String organization_city){
		  this.organization_city=organization_city;
	}


	/**
 	 *  
 	 */
	
	public String getPostal_address (){
		return postal_address;
	}
	public void setPostal_address (String postal_address){
		  this.postal_address=postal_address;
	}


	/**
 	 *  
 	 */
	
	public String getPostcode (){
		return postcode;
	}
	public void setPostcode (String postcode){
		  this.postcode=postcode;
	}


	/**
 	 *  
 	 */
	
	public String getPosition_title (){
		return position_title;
	}
	public void setPosition_title (String position_title){
		  this.position_title=position_title;
	}


	/**
 	 *  
 	 */
	
	public String getPosition_level (){
		return position_level;
	}
	public void setPosition_level (String position_level){
		  this.position_level=position_level;
	}


	/**
 	 *  
 	 */
	
	public String getProfessional_title (){
		return professional_title;
	}
	public void setProfessional_title (String professional_title){
		  this.professional_title=professional_title;
	}



	/**
 	 *  
 	 */
	
	public String getEducation (){
		return education;
	}
	public void setEducation (String education){
		  this.education=education;
	}


	/**
 	 *  
 	 */
	
	public String getDegree (){
		return degree;
	}
	public void setDegree (String degree){
		  this.degree=degree;
	}


	/**
 	 *  
 	 */
	
	public String getMobile_telephone (){
		return mobile_telephone;
	}
	public void setMobile_telephone (String mobile_telephone){
		  this.mobile_telephone=mobile_telephone;
	}


	/**
 	 *  
 	 */
	
	public String getOffice_telephone (){
		return office_telephone;
	}
	public void setOffice_telephone (String office_telephone){
		  this.office_telephone=office_telephone;
	}


	/**
 	 *  
 	 */
	
	public String getHome_telephone (){
		return home_telephone;
	}
	public void setHome_telephone (String home_telephone){
		  this.home_telephone=home_telephone;
	}


	/**
 	 *  
 	 */
	
	public String getFax (){
		return fax;
	}
	public void setFax (String fax){
		  this.fax=fax;
	}


	/**
 	 *  
 	 */
	
	public String getEmail (){
		return email;
	}
	public void setEmail (String email){
		  this.email=email;
	}



	/**
 	 *  
 	 */
	
	public String getId_type (){
		return id_type;
	}
	public void setId_type (String id_type){
		  this.id_type=id_type;
	}


	/**
 	 *  
 	 */
	
	public String getId_number (){
		return id_number;
	}
	public void setId_number (String id_number){
		  this.id_number=id_number;
	}


	/**
 	 *  
 	 */
	
	public String getBank_name (){
		return bank_name;
	}
	public void setBank_name (String bank_name){
		  this.bank_name=bank_name;
	}


	/**
 	 *  
 	 */
	
	public String getBank_account (){
		return bank_account;
	}
	public void setBank_account (String bank_account){
		  this.bank_account=bank_account;
	}



	/**
 	 *  
 	 */
	
	public String getIntroduce (){
		return introduce;
	}
	public void setIntroduce (String introduce){
		  this.introduce=introduce;
	}


	/**
 	 *  
 	 */
	
	public String getPolitical (){
		return political;
	}
	public void setPolitical (String political){
		  this.political=political;
	}



	/**
 	 *  
 	 */
	
	public String getExpert_type (){
		return expert_type;
	}
	public void setExpert_type (String expert_type){
		  this.expert_type=expert_type;
	}




	/**
 	 *  
 	 */
	
	public String getCdxmlx (){
		return cdxmlx;
	}
	public void setCdxmlx (String cdxmlx){
		  this.cdxmlx=cdxmlx;
	}


	/**
 	 *  
 	 */
	
	public String getZjlbxx (){
		return zjlbxx;
	}
	public void setZjlbxx (String zjlbxx){
		  this.zjlbxx=zjlbxx;
	}


	/**
 	 *  
 	 */
	
	public String getRktj (){
		return rktj;
	}
	public void setRktj (String rktj){
		  this.rktj=rktj;
	}


	/**
 	 *  
 	 */
	
	public String getZjlbxx1 (){
		return zjlbxx1;
	}
	public void setZjlbxx1 (String zjlbxx1){
		  this.zjlbxx1=zjlbxx1;
	}


	/**
 	 *  
 	 */
	
	public String getKeywords (){
		return keywords;
	}
	public void setKeywords (String keywords){
		  this.keywords=keywords;
	}



	/**
 	 *  
 	 */
	
	public String getOrganization_address (){
		return organization_address;
	}
	public void setOrganization_address (String organization_address){
		  this.organization_address=organization_address;
	}



	/**
 	 *  
 	 */
	
	public Date getSubmitdate (){
		return submitdate;
	}
	public void setSubmitdate (Date submitdate){
		  this.submitdate=submitdate;
	}


	/**
 	 *  
 	 */
	
	public String getMinicurrentstateid (){
		return minicurrentstateid;
	}
	public void setMinicurrentstateid (String minicurrentstateid){
		  this.minicurrentstateid=minicurrentstateid;
	}


	/**
 	 *  
 	 */
	
	public String getMinicurrentstate (){
		return minicurrentstate;
	}
	public void setMinicurrentstate (String minicurrentstate){
		  this.minicurrentstate=minicurrentstate;
	}


	/**
 	 *  
 	 */
	
	public Date getUpdatelasttime (){
		return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
		  this.updatelasttime=updatelasttime;
	}


	/**
 	 *  
 	 */
	
	public String getBelonglabid (){
		return belonglabid;
	}
	public void setBelonglabid (String belonglabid){
		  this.belonglabid=belonglabid;
	}



	/**
 	 *  
 	 */
	
	public String getYuanshi (){
		return yuanshi;
	}
	public void setYuanshi (String yuanshi){
		  this.yuanshi=yuanshi;
	}


	/**
 	 *  
 	 */
	
	public String getUserid (){
		return userid;
	}
	public void setUserid (String userid){
		  this.userid=userid;
	}


	/**
 	 *  
 	 */
	
	public String getJtgzdw (){
		return jtgzdw;
	}
	public void setJtgzdw (String jtgzdw){
		  this.jtgzdw=jtgzdw;
	}


	/**
 	 *  
 	 */
	
	public String getRegion (){
		return region;
	}
	public void setRegion (String region){
		  this.region=region;
	}


	/**
 	 *  
 	 */
	

	public Date getSavedate() {
		return savedate;
	}
	public void setSavedate(Date savedate) {
		this.savedate = savedate;
	}


	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public String getUniformcode() {
		return uniformcode;
	}
	public void setUniformcode(String uniformcode) {
		this.uniformcode = uniformcode;
	}

	public String getOrginizationcode() {
		return orginizationcode;
	}
	public void setOrginizationcode(String orginizationcode) {
		this.orginizationcode = orginizationcode;
	}

	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchooldepartment() {
		return schooldepartment;
	}
	public void setSchooldepartment(String schooldepartment) {
		this.schooldepartment = schooldepartment;
	}

	public String getBank_accountname() {
		return bank_accountname;
	}
	public void setBank_accountname(String bank_accountname) {
		this.bank_accountname = bank_accountname;
	}

	public String getBankinterbank() {
		return bankinterbank;
	}
	public void setBankinterbank(String bankinterbank) {
		this.bankinterbank = bankinterbank;
	}

	public Date getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	public String getRkflag() {
		return rkflag;
	}
	public void setRkflag(String rkflag) {
		this.rkflag = rkflag;
	}

	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	public Date getSchooldate() {
		return schooldate;
	}
	public void setSchooldate(Date schooldate) {
		this.schooldate = schooldate;
	}

	public String getStudycountry() {
		return studycountry;
	}
	public void setStudycountry(String studycountry) {
		this.studycountry = studycountry;
	}

	public String getApprovestate() {
		return approvestate;
	}
	public void setApprovestate(String approvestate) {
		this.approvestate = approvestate;
	}

	public String getIscoreexpert() {
		return iscoreexpert;
	}
	public void setIscoreexpert(String iscoreexpert) {
		this.iscoreexpert = iscoreexpert;
	}

	public String getIsjjcoreexpert() {
		return isjjcoreexpert;
	}
	public void setIsjjcoreexpert(String isjjcoreexpert) {
		this.isjjcoreexpert = isjjcoreexpert;
	}

	public Double getSciencescore() {
		return sciencescore;
	}
	public void setSciencescore(Double sciencescore) {
		this.sciencescore = sciencescore;
	}

	public String getNation() {return nation;}
	public void setNation(String nation) {this.nation = nation;}

	public String getOrganization_county() {return organization_county;}
	public void setOrganization_county(String organization_county) {this.organization_county = organization_county;}

	public String getOrganization_contactphone() {return organization_contactphone;}
	public void setOrganization_contactphone(String organization_contactphone) {this.organization_contactphone = organization_contactphone;}

	public String getOrganization_contactman() {return organization_contactman;}
	public void setOrganization_contactman(String organization_contactman) {this.organization_contactman = organization_contactman;}

	public Date getPositiondate() {return positiondate;}
	public void setPositiondate(Date positiondate) {this.positiondate = positiondate;}

	public String getWorkforprofession() {return workforprofession;}
	public void setWorkforprofession(String workforprofession) {this.workforprofession = workforprofession;}

	public String getSubsidy() {return subsidy;}
	public void setSubsidy(String subsidy) {this.subsidy = subsidy;}

	public String getIstutor() {return istutor;}
	public void setIstutor(String istutor) {this.istutor = istutor;}

	public String getIscheckcredit() {return ischeckcredit;}
	public void setIscheckcredit(String ischeckcredit) {this.ischeckcredit = ischeckcredit;}

	public Date getCheckcreditdate() {return checkcreditdate;}
	public void setCheckcreditdate(Date checkcreditdate) {this.checkcreditdate = checkcreditdate;}

	public String getCreditrank() {return creditrank;}
	public void setCreditrank(String creditrank) {this.creditrank = creditrank;}

	public String getIsqyzj() {return isqyzj;}
	public void setIsqyzj(String isqyzj) {this.isqyzj = isqyzj;}

	public String getDenycreditreason() {return denycreditreason;}
	public void setDenycreditreason(String denycreditreason) {this.denycreditreason = denycreditreason;}

	public String getForeignlanguage() {return foreignlanguage;}
	public void setForeignlanguage(String foreignlanguage) {this.foreignlanguage = foreignlanguage;}

	public String getLanguagelevel() {return languagelevel;}
	public void setLanguagelevel(String languagelevel) {this.languagelevel = languagelevel;}

	public String getWebsite() {return website;}
	public void setWebsite(String website) {this.website = website;}

	public String getIswillsn() {return iswillsn;}
	public void setIswillsn(String iswillsn) {this.iswillsn = iswillsn;}

	public String getIswillsw() {return iswillsw;}
	public void setIswillsw(String iswillsw) {this.iswillsw = iswillsw;}

	public String getRemittanceaddress() {return remittanceaddress;}
	public void setRemittanceaddress(String remittanceaddress) {this.remittanceaddress = remittanceaddress;}

	public String getDrbj() { return drbj; }
	public void setDrbj(String drbj) { this.drbj = drbj; }

	public String getExpert_classify() { return expert_classify; }
	public void setExpert_classify(String expert_classify) { this.expert_classify = expert_classify; }

	public String getExpert_field() { return expert_field; }
	public void setExpert_field(String expert_field) { this.expert_field = expert_field; }

	public String getInvestment_orientation() { return investment_orientation; }
	public void setInvestment_orientation(String investment_orientation) { this.investment_orientation = investment_orientation; }

	public String getCompetition() { return competition; }
	public void setCompetition(String competition) { this.competition = competition; }

	public String getCompetition_field() { return competition_field; }
	public void setCompetition_field(String competition_field) { this.competition_field = competition_field; }

	public Date getGsdate() { return gsdate; }
	public void setGsdate(Date gsdate) { this.gsdate = gsdate; }

	public String getGsdissent() { return gsdissent; }
	public void setGsdissent(String gsdissent) { this.gsdissent = gsdissent; }

	public String getDissentcontent() { return dissentcontent; }
	public void setDissentcontent(String dissentcontent) { this.dissentcontent = dissentcontent; }

	public Date getDissentdate() { return dissentdate; }
	public void setDissentdate(Date dissentdate) { this.dissentdate = dissentdate; }

	public String getJtgzdw_uniformcode() {
		return jtgzdw_uniformcode;
	}
	public void setJtgzdw_uniformcode(String jtgzdw_uniformcode) {
		this.jtgzdw_uniformcode = jtgzdw_uniformcode;
	}

	public String getMessageflag() { return messageflag; }
	public void setMessageflag(String messageflag) { this.messageflag = messageflag; }

	public String getCnsflag() {
		return cnsflag;
	}

	public void setCnsflag(String cnsflag) {
		this.cnsflag = cnsflag;
	}

	public Date getCnsdate() {
		return cnsdate;
	}

	public void setCnsdate(Date cnsdate) {
		this.cnsdate = cnsdate;
	}

	public String getProvince_region() {
		return province_region;
	}

	public void setProvince_region(String province_region) {
		this.province_region = province_region;
	}

	public String getGjhzjl() {
		return gjhzjl;
	}

	public void setGjhzjl(String gjhzjl) {
		this.gjhzjl = gjhzjl;
	}

	public String getKpgzjl() {
		return kpgzjl;
	}

	public void setKpgzjl(String kpgzjl) {
		this.kpgzjl = kpgzjl;
	}

	public String getZero_reason() {
		return zero_reason;
	}

	public void setZero_reason(String zero_reason) {
		this.zero_reason = zero_reason;
	}

	public String getOne_reason() {
		return one_reason;
	}

	public void setOne_reason(String one_reason) {
		this.one_reason = one_reason;
	}

	public String getBank_zhihang() { return bank_zhihang; }

	public void setBank_zhihang(String bank_zhihang) { this.bank_zhihang = bank_zhihang; }

	public String getMain_unitname() {
		return main_unitname;
	}

	public void setMain_unitname(String main_unitname) {
		this.main_unitname = main_unitname;
	}

	public String getMain_uniformcode() {
		return main_uniformcode;
	}

	public void setMain_uniformcode(String main_uniformcode) {
		this.main_uniformcode = main_uniformcode;
	}

	public String getZyzg() {
		return zyzg;
	}

	public void setZyzg(String zyzg) {
		this.zyzg = zyzg;
	}

	public String getIsforeign() {
		return isforeign;
	}

	public void setIsforeign(String isforeign) {
		this.isforeign = isforeign;
	}

	public String getTitledetailone() {
		return titledetailone;
	}

	public void setTitledetailone(String titledetailone) {
		this.titledetailone = titledetailone;
	}

	public String getTitledetailtwo() {
		return titledetailtwo;
	}

	public void setTitledetailtwo(String titledetailtwo) {
		this.titledetailtwo = titledetailtwo;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getForeigntype() {
		return foreigntype;
	}

	public void setForeigntype(String foreigntype) {
		this.foreigntype = foreigntype;
	}

	public String getBank_province() {
		return bank_province;
	}

	public void setBank_province(String bank_province) {
		this.bank_province = bank_province;
	}

	public String getBank_city() {
		return bank_city;
	}

	public void setBank_city(String bank_city) {
		this.bank_city = bank_city;
	}

	public String getCasemanagementid() {
		return casemanagementid;
	}

	public void setCasemanagementid(String casemanagementid) {
		this.casemanagementid = casemanagementid;
	}

	public String getCasemanagement() {
		return casemanagement;
	}

	public void setCasemanagement(String casemanagement) {
		this.casemanagement = casemanagement;
	}

	public String getExpert_title() {
		return expert_title;
	}

	public void setExpert_title(String expert_title) {
		this.expert_title = expert_title;
	}

	public String getChangecasemanagementid() {
		return changecasemanagementid;
	}

	public void setChangecasemanagementid(String changecasemanagementid) {
		this.changecasemanagementid = changecasemanagementid;
	}

	public String getChangecasemanagement() {
		return changecasemanagement;
	}

	public void setChangecasemanagement(String changecasemanagement) {
		this.changecasemanagement = changecasemanagement;
	}
}