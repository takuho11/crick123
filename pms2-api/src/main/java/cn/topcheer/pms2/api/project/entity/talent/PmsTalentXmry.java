/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:30:14
 */
package cn.topcheer.pms2.api.project.entity.talent;

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
 *  科技人才-人员表
 */
@Entity
@Table(name="PMS_TALENT_XMRY")
@ClassInfo(name = "科技人才-人员表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentXmry {

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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date savedate;

	/**
     *  固定字段:每次更新数据时间
     */
	@ApiModelProperty("固定字段:每次更新数据时间")
	@TableField("UPDATELASTTIME")
	@Column(columnDefinition = "UPDATELASTTIME")
	@FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
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
     *  姓名
     */
	@ApiModelProperty("姓名")
	@TableField("NAME")
	@Column(columnDefinition = "NAME")
	private String name;

	/**
     *  外文名
     */
	@ApiModelProperty("外文名")
	@TableField("FOREIGNNAME")
	@Column(columnDefinition = "FOREIGNNAME")
	private String foreignname;

	/**
     *  国籍
     */
	@ApiModelProperty("国籍")
	@TableField("NATIONALITY")
	@Column(columnDefinition = "NATIONALITY")
	private String nationality;

	/**
     *  民族
     */
	@ApiModelProperty("民族")
	@TableField("NATION")
	@Column(columnDefinition = "NATION")
	private String nation;

	/**
     *  政治面貌
     */
	@ApiModelProperty("政治面貌")
	@TableField("POLITICAL_STATUS")
	@Column(columnDefinition = "POLITICAL_STATUS")
	private String politicalStatus;

	/**
     *  证件类型
     */
	@ApiModelProperty("证件类型")
	@TableField("CERTIFICATENAME")
	@Column(columnDefinition = "CERTIFICATENAME")
	private String certificatename;

	/**
     *  证件号码
     */
	@ApiModelProperty("证件号码")
	@TableField("CERTIFICATENUMBER")
	@Column(columnDefinition = "CERTIFICATENUMBER")
	private String certificatenumber;

	/**
     *  出生日期
     */
	@ApiModelProperty("出生日期")
	@TableField("BIRTHDAY")
	@Column(columnDefinition = "BIRTHDAY")
	@FieldDes(name = "birthday", type = "Date", memo = "出生日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date birthday;

	/**
     *  性别
     */
	@ApiModelProperty("性别")
	@TableField("GENDER")
	@Column(columnDefinition = "GENDER")
	private String gender;

	/**
     *  手机号码
     */
	@ApiModelProperty("手机号码")
	@TableField("MOBILE")
	@Column(columnDefinition = "MOBILE")
	private String mobile;

	/**
     *  电子邮箱
     */
	@ApiModelProperty("电子邮箱")
	@TableField("EMAIL")
	@Column(columnDefinition = "EMAIL")
	private String email;

	/**
     *  职务
     */
	@ApiModelProperty("职务")
	@TableField("POST")
	@Column(columnDefinition = "POST")
	private String post;

	/**
     *  职务
     */
	@ApiModelProperty("专业技术职务")
	@TableField("TECH_POST")
	@Column(columnDefinition = "TECH_POST")
	private String techPost;

	/**
     *  职称
     */
	@ApiModelProperty("职称")
	@TableField("TITLE")
	@Column(columnDefinition = "TITLE")
	private String title;

	/**
     *  最高学历
     */
	@ApiModelProperty("最高学历")
	@TableField("EDUCATION")
	@Column(columnDefinition = "EDUCATION")
	private String education;

	/**
     *  最高学位
     */
	@ApiModelProperty("最高学位")
	@TableField("DEGREE")
	@Column(columnDefinition = "DEGREE")
	private String degree;

	/**
     *  最高学历毕业学校
     */
	@ApiModelProperty("最高学历毕业学校")
	@TableField("GRADUATEDSCHOOL")
	@Column(columnDefinition = "GRADUATEDSCHOOL")
	private String graduatedschool;

	/**
     *  从事专业
     */
	@ApiModelProperty("从事专业")
	@TableField("PROFESSION")
	@Column(columnDefinition = "PROFESSION")
	private String profession;

	/**
     *  地址
     */
	@ApiModelProperty("地址")
	@TableField("ADDRESS")
	@Column(columnDefinition = "ADDRESS")
	private String address;

	/**
     *  省
     */
	@ApiModelProperty("省")
	@TableField("PROVINCE")
	@Column(columnDefinition = "PROVINCE")
	private String province;

	/**
     *  市
     */
	@ApiModelProperty("市")
	@TableField("CITY")
	@Column(columnDefinition = "CITY")
	private String city;

	/**
     *  县
     */
	@ApiModelProperty("县")
	@TableField("COUNTY")
	@Column(columnDefinition = "COUNTY")
	private String county;

	/**
     *  街道
     */
	@ApiModelProperty("街道")
	@TableField("STREET")
	@Column(columnDefinition = "STREET")
	private String street;

	/**
     *  所学专业
     */
	@ApiModelProperty("所学专业")
	@TableField("MAJOR")
	@Column(columnDefinition = "MAJOR")
	private String major;

	/**
     *  籍贯
     */
	@ApiModelProperty("籍贯")
	@TableField("NATIVEPLACE")
	@Column(columnDefinition = "NATIVEPLACE")
	private String nativeplace;

	/**
     *  传真
     */
	@ApiModelProperty("传真")
	@TableField("FAX")
	@Column(columnDefinition = "FAX")
	private String fax;

	/**
     *  工作单位
     */
	@ApiModelProperty("工作单位")
	@TableField("WORKUNIT")
	@Column(columnDefinition = "WORKUNIT")
	private String workunit;

	/**
     *  是否现任法人单位党政主要负责人
     */
	@ApiModelProperty("是否现任法人单位党政主要负责人")
	@TableField("IS_CHARGE")
	@Column(columnDefinition = "IS_CHARGE")
	private String isCharge;

	/**
     *  是否为海归人才
     */
	@ApiModelProperty("是否为海归人才")
	@TableField("IS_RETURNEE")
	@Column(columnDefinition = "IS_RETURNEE")
	private String isReturnee;

	/**
     *  回国工作时间
     */
	@ApiModelProperty("回国工作时间")
	@TableField("RETURNEE_DATE")
	@Column(columnDefinition = "RETURNEE_DATE")
	@FieldDes(name = "returnee_date", type = "Date", memo = "回国工作时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date returneeDate;

	/**
     *  主要研究方向
     */
	@ApiModelProperty("主要研究方向")
	@TableField("RESEARCH_DIRECTION")
	@Column(columnDefinition = "RESEARCH_DIRECTION")
	private String researchDirection;

	/**
     *  获得的学术荣誉称号
     */
	@ApiModelProperty("获得的学术荣誉称号")
	@TableField("HONOR_TITLE")
	@Column(columnDefinition = "HONOR_TITLE")
	private String honorTitle;

	/**
     *  已入选的人才计划
     */
	@ApiModelProperty("已入选的人才计划")
	@TableField("BE_SELECTED_TELANT_PLAN")
	@Column(columnDefinition = "BE_SELECTED_TELANT_PLAN")
	private String beSelectedTelantPlan;

	/**
     *  申报资格条件
     */
	@ApiModelProperty("申报资格条件")
	@TableField("QUALIFICATION")
	@Column(columnDefinition = "QUALIFICATION")
	private String qualification;

	/**
     *  办公电话
     */
	@ApiModelProperty("办公电话")
	@TableField("TELEPHONE")
	@Column(columnDefinition = "TELEPHONE")
	private String telephone;

	/**
     *  邮编
     */
	@ApiModelProperty("邮编")
	@TableField("POSTAL_CODE")
	@Column(columnDefinition = "POSTAL_CODE")
	private String postalCode;

	/**
     *  出生地
     */
	@ApiModelProperty("出生地")
	@TableField("BIRTHPLACE")
	@Column(columnDefinition = "BIRTHPLACE")
	private String birthplace;

	/**
     *  参加工作时间
     */
	@ApiModelProperty("参加工作时间")
	@TableField("PARTICIPATE_DATE")
	@Column(columnDefinition = "PARTICIPATE_DATE")
	@FieldDes(name = "participate_date", type = "Date", memo = "参加工作时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date participateDate;

	/**
     *  引进后工作岗位
     */
	@ApiModelProperty("引进后工作岗位")
	@TableField("IMPORT_JOB")
	@Column(columnDefinition = "IMPORT_JOB")
	private String importJob;

	/**
     *  引进后职务/职称
     */
	@ApiModelProperty("引进后职务/职称")
	@TableField("IMPORT_TITLE")
	@Column(columnDefinition = "IMPORT_TITLE")
	private String importTitle;

	/**
     *  从事工作内容
     */
	@ApiModelProperty("从事工作内容")
	@TableField("IMPORT_WORK_TASK")
	@Column(columnDefinition = "IMPORT_WORK_TASK")
	private String importWorkTask;

	/**
     *  引进工作合同开始时间
     */
	@ApiModelProperty("引进工作合同开始时间")
	@TableField("IMPORT_START")
	@Column(columnDefinition = "IMPORT_START")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date importStart;

	/**
	 *  引进工作合同结束时间
	 */
	@ApiModelProperty("引进工作合同结束时间")
	@TableField("IMPORT_END")
	@Column(columnDefinition = "IMPORT_END")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date importEnd;

	/**
     *  申报人税前年薪（万元）
     */
	@ApiModelProperty("申报人税前年薪（万元）")
	@TableField("YEAR_SALARY")
	@Column(columnDefinition = "YEAR_SALARY")
	@FieldDes(name = "year_salary", type = "BigDecimal", memo = "申报人税前年薪（万元）")
	private BigDecimal yearSalary;

	/**
	 *  专业领域
	 */
	@ApiModelProperty("专业领域")
	@TableField("PROFESSIONAL_FIELD")
	@Column(columnDefinition = "PROFESSIONAL_FIELD")
	private String professionalField;

	/**
	 *  申报层次
	 */
	@ApiModelProperty("申报层次")
	@TableField("DECLARE_LEVEL")
	@Column(columnDefinition = "DECLARE_LEVEL")
	private String declareLevel;

	/**
	 *  主持或参与
	 */
	@ApiModelProperty("人才类别")
	@TableField("TALENT_TYPE")
	@Column(columnDefinition = "TALENT_TYPE")
	private String talentType;

	/**
	 *  头像地址
	 */
	@ApiModelProperty("头像地址")
	@TableField("ID_PHOTO")
	@Column(columnDefinition = "ID_PHOTO")
	private String idPhoto;

	@ApiModelProperty("入选人才项目时间(日期)")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	@TableField("SELECTED_TALENT_PROJECT_DATE")
	@Column(columnDefinition = "SELECTED_TALENT_PROJECT_DATE")
	private Date selectedTalentProjectDate;

	@ApiModelProperty("申请奖励档次”")
	@TableField("REWARD_GRADLE")
	@Column(columnDefinition = "REWARD_GRADLE")
	private String rewardGradle;


	@ApiModelProperty("入选人才项目层次")
	@TableField("TALENT_LEVEL")
	@Column(columnDefinition = "TALENT_LEVEL")
	private String talentLevel;

	@ApiModelProperty("入选人才项目层次(值)")
	@TableField("TALENT_LEVEL_OPTION")
	@Column(columnDefinition = "TALENT_LEVEL_OPTION")
	private String talentLevelOption;

	@ApiModelProperty("申请奖励档次(值)")
	@TableField("REWARD_GRADE_OPTION")
	@Column(columnDefinition = "REWARD_GRADE_OPTION")
	private String rewardGradeOption;

	@ApiModelProperty("引进方式")
	@TableField("LEAD_IN_METHOD")
	@Column(columnDefinition = "LEAD_IN_METHOD")
	private String leadInMethod;

	@ApiModelProperty("引进时间")
	@TableField("LEAD_IN_TIME")
	@Column(columnDefinition = "LEAD_IN_TIME")
	private String leadInTime;

	@ApiModelProperty("认定条件")
	@TableField("RECOGNITION_CRITERIA")
	@Column(columnDefinition = "RECOGNITION_CRITERIA")
	private String recognitionCriteria;


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
	*  姓名
	*/
	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name = name;
	}

	/**
	*  外文名
	*/
	public String getForeignname (){
		return foreignname;
	}
	public void setForeignname (String foreignname){
		this.foreignname = foreignname;
	}

	/**
	*  国籍
	*/
	public String getNationality (){
		return nationality;
	}
	public void setNationality (String nationality){
		this.nationality = nationality;
	}

	/**
	*  民族
	*/
	public String getNation (){
		return nation;
	}
	public void setNation (String nation){
		this.nation = nation;
	}

	/**
	*  政治面貌
	*/
	public String getPoliticalStatus (){
		return politicalStatus;
	}
	public void setPoliticalStatus (String politicalStatus){
		this.politicalStatus = politicalStatus;
	}

	/**
	*  证件类型
	*/
	public String getCertificatename (){
		return certificatename;
	}
	public void setCertificatename (String certificatename){
		this.certificatename = certificatename;
	}

	/**
	*  证件号码
	*/
	public String getCertificatenumber (){
		return certificatenumber;
	}
	public void setCertificatenumber (String certificatenumber){
		this.certificatenumber = certificatenumber;
	}

	/**
	*  出生日期
	*/
	public Date getBirthday (){
		return birthday;
	}
	public void setBirthday (Date birthday){
		this.birthday = birthday;
	}

	/**
	*  性别
	*/
	public String getGender (){
		return gender;
	}
	public void setGender (String gender){
		this.gender = gender;
	}

	/**
	*  手机号码
	*/
	public String getMobile (){
		return mobile;
	}
	public void setMobile (String mobile){
		this.mobile = mobile;
	}

	/**
	*  电子邮箱
	*/
	public String getEmail (){
		return email;
	}
	public void setEmail (String email){
		this.email = email;
	}

	/**
	*  职务
	*/
	public String getPost (){
		return post;
	}
	public void setPost (String post){
		this.post = post;
	}

	/**
	*  职称
	*/
	public String getTitle (){
		return title;
	}
	public void setTitle (String title){
		this.title = title;
	}

	/**
	*  最高学历
	*/
	public String getEducation (){
		return education;
	}
	public void setEducation (String education){
		this.education = education;
	}

	/**
	*  最高学位
	*/
	public String getDegree (){
		return degree;
	}
	public void setDegree (String degree){
		this.degree = degree;
	}

	/**
	*  最高学历毕业学校
	*/
	public String getGraduatedschool (){
		return graduatedschool;
	}
	public void setGraduatedschool (String graduatedschool){
		this.graduatedschool = graduatedschool;
	}

	/**
	*  从事专业
	*/
	public String getProfession (){
		return profession;
	}
	public void setProfession (String profession){
		this.profession = profession;
	}

	/**
	*  地址
	*/
	public String getAddress (){
		return address;
	}
	public void setAddress (String address){
		this.address = address;
	}

	/**
	*  省
	*/
	public String getProvince (){
		return province;
	}
	public void setProvince (String province){
		this.province = province;
	}

	/**
	*  市
	*/
	public String getCity (){
		return city;
	}
	public void setCity (String city){
		this.city = city;
	}

	/**
	*  县
	*/
	public String getCounty (){
		return county;
	}
	public void setCounty (String county){
		this.county = county;
	}

	/**
	*  街道
	*/
	public String getStreet (){
		return street;
	}
	public void setStreet (String street){
		this.street = street;
	}

	/**
	*  所学专业
	*/
	public String getMajor (){
		return major;
	}
	public void setMajor (String major){
		this.major = major;
	}

	/**
	*  籍贯
	*/
	public String getNativeplace (){
		return nativeplace;
	}
	public void setNativeplace (String nativeplace){
		this.nativeplace = nativeplace;
	}

	/**
	*  传真
	*/
	public String getFax (){
		return fax;
	}
	public void setFax (String fax){
		this.fax = fax;
	}

	/**
	*  工作单位
	*/
	public String getWorkunit (){
		return workunit;
	}
	public void setWorkunit (String workunit){
		this.workunit = workunit;
	}

	/**
	*  是否现任法人单位党政主要负责人
	*/
	public String getIsCharge (){
		return isCharge;
	}
	public void setIsCharge (String isCharge){
		this.isCharge = isCharge;
	}

	/**
	*  是否为海归人才
	*/
	public String getIsReturnee (){
		return isReturnee;
	}
	public void setIsReturnee (String isReturnee){
		this.isReturnee = isReturnee;
	}

	/**
	*  回国工作时间
	*/
	public Date getReturneeDate (){
		return returneeDate;
	}
	public void setReturneeDate (Date returneeDate){
		this.returneeDate = returneeDate;
	}

	/**
	*  主要研究方向
	*/
	public String getResearchDirection (){
		return researchDirection;
	}
	public void setResearchDirection (String researchDirection){
		this.researchDirection = researchDirection;
	}

	/**
	*  获得的学术荣誉称号
	*/
	public String getHonorTitle (){
		return honorTitle;
	}
	public void setHonorTitle (String honorTitle){
		this.honorTitle = honorTitle;
	}

	/**
	*  已入选的人才计划
	*/
	public String getBeSelectedTelantPlan (){
		return beSelectedTelantPlan;
	}
	public void setBeSelectedTelantPlan (String beSelectedTelantPlan){
		this.beSelectedTelantPlan = beSelectedTelantPlan;
	}

	/**
	*  申报资格条件
	*/
	public String getQualification (){
		return qualification;
	}
	public void setQualification (String qualification){
		this.qualification = qualification;
	}

	/**
	*  办公电话
	*/
	public String getTelephone (){
		return telephone;
	}
	public void setTelephone (String telephone){
		this.telephone = telephone;
	}

	/**
	*  邮编
	*/
	public String getPostalCode (){
		return postalCode;
	}
	public void setPostalCode (String postalCode){
		this.postalCode = postalCode;
	}

	/**
	*  出生地
	*/
	public String getBirthplace (){
		return birthplace;
	}
	public void setBirthplace (String birthplace){
		this.birthplace = birthplace;
	}

	/**
	*  参加工作时间
	*/
	public Date getParticipateDate (){
		return participateDate;
	}
	public void setParticipateDate (Date participateDate){
		this.participateDate = participateDate;
	}

	/**
	*  引进后工作岗位
	*/
	public String getImportJob (){
		return importJob;
	}
	public void setImportJob (String importJob){
		this.importJob = importJob;
	}

	/**
	*  引进后职务/职称
	*/
	public String getImportTitle (){
		return importTitle;
	}
	public void setImportTitle (String importTitle){
		this.importTitle = importTitle;
	}

	/**
	*  从事工作内容
	*/
	public String getImportWorkTask (){
		return importWorkTask;
	}
	public void setImportWorkTask (String importWorkTask){
		this.importWorkTask = importWorkTask;
	}

	public Date getImportStart() {
		return importStart;
	}

	public void setImportStart(Date importStart) {
		this.importStart = importStart;
	}

	public Date getImportEnd() {
		return importEnd;
	}

	public void setImportEnd(Date importEnd) {
		this.importEnd = importEnd;
	}

	/**
	*  申报人税前年薪（万元）
	*/
	public BigDecimal getYearSalary (){
		return yearSalary;
	}
	public void setYearSalary (BigDecimal yearSalary){
		this.yearSalary = yearSalary;
	}

	public String getTechPost() {
		return techPost;
	}

	public void setTechPost(String techPost) {
		this.techPost = techPost;
	}

	public String getProfessionalField() {
		return professionalField;
	}

	public void setProfessionalField(String professionalField) {
		this.professionalField = professionalField;
	}

	public String getDeclareLevel() {
		return declareLevel;
	}

	public void setDeclareLevel(String declareLevel) {
		this.declareLevel = declareLevel;
	}

	public String getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(String idPhoto) {
		this.idPhoto = idPhoto;
	}

	public String getTalentType() {
		return talentType;
	}

	public void setTalentType(String talentType) {
		this.talentType = talentType;
	}

	public Date getSelectedTalentProjectDate() {
		return selectedTalentProjectDate;
	}

	public void setSelectedTalentProjectDate(Date selectedTalentProjectDate) {
		this.selectedTalentProjectDate = selectedTalentProjectDate;
	}

	public String getRewardGradle() {
		return rewardGradle;
	}

	public void setRewardGradle(String rewardGradle) {
		this.rewardGradle = rewardGradle;
	}

	public String getTalentLevel() {
		return talentLevel;
	}

	public void setTalentLevel(String talentLevel) {
		this.talentLevel = talentLevel;
	}

	public String getTalentLevelOption() {
		return talentLevelOption;
	}

	public void setTalentLevelOption(String talentLevelOption) {
		this.talentLevelOption = talentLevelOption;
	}

	public String getRewardGradeOption() {
		return rewardGradeOption;
	}

	public void setRewardGradeOption(String rewardGradeOption) {
		this.rewardGradeOption = rewardGradeOption;
	}

	public String getLeadInMethod() {
		return leadInMethod;
	}

	public void setLeadInMethod(String leadInMethod) {
		this.leadInMethod = leadInMethod;
	}

	public String getLeadInTime() {
		return leadInTime;
	}

	public void setLeadInTime(String leadInTime) {
		this.leadInTime = leadInTime;
	}

	public String getRecognitionCriteria() {
		return recognitionCriteria;
	}

	public void setRecognitionCriteria(String recognitionCriteria) {
		this.recognitionCriteria = recognitionCriteria;
	}
}
