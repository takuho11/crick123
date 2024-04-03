/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:32:16
 */
package cn.topcheer.pms2.api.project.entity.prize;

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
 *  科技奖励-人员表
 */
@Entity
@Table(name="PMS_PRIZE_XMRY")
@ClassInfo(name = "科技奖励-人员表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizeXmry {

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
     *  性别
     */
	@ApiModelProperty("性别")
	@TableField("GENDER")
	@Column(columnDefinition = "GENDER")
	private String gender;

	/**
     *  排名
     */
	@ApiModelProperty("排名")
	@TableField("RANKING")
	@Column(columnDefinition = "RANKING")
	private String ranking;

	/**
     *  国籍
     */
	@ApiModelProperty("国籍")
	@TableField("NATIONALITY")
	@Column(columnDefinition = "NATIONALITY")
	private String nationality;

	/**
     *  出生年月
     */
	@ApiModelProperty("出生年月")
	@TableField("BIRTHDAY")
	@Column(columnDefinition = "BIRTHDAY")
	@FieldDes(name = "birthday", type = "Date", memo = "出生年月")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date birthday;

	/**
     *  出生地
     */
	@ApiModelProperty("出生地")
	@TableField("BIRTHPLACE")
	@Column(columnDefinition = "BIRTHPLACE")
	private String birthplace;

	/**
     *  民族
     */
	@ApiModelProperty("民族")
	@TableField("NATION")
	@Column(columnDefinition = "NATION")
	private String nation;

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
     *  归国人员
     */
	@ApiModelProperty("归国人员")
	@TableField("RETURNEE")
	@Column(columnDefinition = "RETURNEE")
	private String returnee;

	/**
     *  归国时间
     */
	@ApiModelProperty("归国时间")
	@TableField("RETURNEEDATE")
	@Column(columnDefinition = "RETURNEEDATE")
	@FieldDes(name = "returneedate", type = "Date", memo = "归国时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date returneedate;

	/**
     *  技术职称
     */
	@ApiModelProperty("技术职称")
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
     *  毕业学校
     */
	@ApiModelProperty("毕业学校")
	@TableField("GRADUATEDSCHOOL")
	@Column(columnDefinition = "GRADUATEDSCHOOL")
	private String graduatedschool;

	/**
     *  毕业时间
     */
	@ApiModelProperty("毕业时间")
	@TableField("GRADUATEDTIME")
	@Column(columnDefinition = "GRADUATEDTIME")
	@FieldDes(name = "graduatedtime", type = "Date", memo = "毕业时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date graduatedtime;

	/**
     *  所学专业
     */
	@ApiModelProperty("所学专业")
	@TableField("MAJOR")
	@Column(columnDefinition = "MAJOR")
	private String major;

	/**
     *  从事专业
     */
	@ApiModelProperty("从事专业")
	@TableField("PROFESSION")
	@Column(columnDefinition = "PROFESSION")
	private String profession;

	/**
     *  电子邮箱
     */
	@ApiModelProperty("电子邮箱")
	@TableField("EMAIL")
	@Column(columnDefinition = "EMAIL")
	private String email;

	/**
     *  办公电话
     */
	@ApiModelProperty("办公电话")
	@TableField("TELEPHONE")
	@Column(columnDefinition = "TELEPHONE")
	private String telephone;

	/**
     *  移动电话
     */
	@ApiModelProperty("移动电话")
	@TableField("MOBILE")
	@Column(columnDefinition = "MOBILE")
	private String mobile;

	/**
     *  通讯地址
     */
	@ApiModelProperty("通讯地址")
	@TableField("ADDRESS")
	@Column(columnDefinition = "ADDRESS")
	private String address;

	/**
     *  邮政编码
     */
	@ApiModelProperty("邮政编码")
	@TableField("POSTALCODE")
	@Column(columnDefinition = "POSTALCODE")
	private String postalcode;

	/**
     *  工作单位
     */
	@ApiModelProperty("工作单位")
	@TableField("WORKPLACE")
	@Column(columnDefinition = "WORKPLACE")
	private String workplace;

	/**
     *  行政职务
     */
	@ApiModelProperty("行政职务")
	@TableField("POST")
	@Column(columnDefinition = "POST")
	private String post;

	/**
     *  二级单位
     */
	@ApiModelProperty("二级单位")
	@TableField("SECONDUNIT")
	@Column(columnDefinition = "SECONDUNIT")
	private String secondunit;

	/**
     *  党派
     */
	@ApiModelProperty("党派")
	@TableField("POLITICALSTATUS")
	@Column(columnDefinition = "POLITICALSTATUS")
	private String politicalstatus;

	/**
     *  完成单位
     */
	@ApiModelProperty("完成单位")
	@TableField("FINISHUNIT")
	@Column(columnDefinition = "FINISHUNIT")
	private String finishunit;

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
     *  单位性质
     */
	@ApiModelProperty("单位性质")
	@TableField("UNITTYPE")
	@Column(columnDefinition = "UNITTYPE")
	private String unittype;

	/**
     *  参加本项目的开始时间
     */
	@ApiModelProperty("参加本项目的开始时间")
	@TableField("STARTDATE")
	@Column(columnDefinition = "STARTDATE")
	@FieldDes(name = "startdate", type = "Date", memo = "参加本项目的开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date startdate;

	/**
     *  参加本项目的接收时间
     */
	@ApiModelProperty("参加本项目的接收时间")
	@TableField("ENDDATE")
	@Column(columnDefinition = "ENDDATE")
	@FieldDes(name = "enddate", type = "Date", memo = "参加本项目的接收时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date enddate;

	/**
     *  对本项目主要学术贡献
     */
	@ApiModelProperty("对本项目主要学术贡献")
	@TableField("CONTRIBUTE")
	@Column(columnDefinition = "CONTRIBUTE")
	private String contribute;

	/**
     *  曾获国家、省级科技奖励情况
     */
	@ApiModelProperty("曾获国家、省级科技奖励情况")
	@TableField("EVERAWARD")
	@Column(columnDefinition = "EVERAWARD")
	private String everaward;

	/**
	 *  学位授予时间
	 */
	@ApiModelProperty("学位授予时间")
	@TableField("DEGREEDATE")
	@Column(columnDefinition = "DEGREEDATE")
	@FieldDes(name = "degreeDate", type = "Date", memo = "学位授予时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date degreedate;

	/**
	 *  是否为院士（0否，1是）
	 */
	@ApiModelProperty("是否为院士（0否，1是）")
	@TableField("IS_ACADEMICIAN")
	@Column(columnDefinition = "IS_ACADEMICIAN")
	private String isAcademician;

	/**
	 *  院士当选时间
	 */
	@ApiModelProperty("院士当选时间")
	@TableField("ACADEMICIAN_DATE")
	@Column(columnDefinition = "ACADEMICIAN_DATE")
	@FieldDes(name = "ACADEMICIAN_DATE", type = "Date", memo = "院士当选时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date academicianDate;

	/**
	 *  传真
	 */
	@ApiModelProperty("传真")
	@TableField("FAX")
	@Column(columnDefinition = "FAX")
	private String fax;

	/**
	 *  人员意见
	 */
	@ApiModelProperty("人员意见")
	@TableField("OPINION")
	@Column(columnDefinition = "OPINION")
	private String opinion;

	/**
	 *  人员意见-推荐奖等
	 */
	@ApiModelProperty("人员意见-推荐奖等")
	@TableField("SUGGEST_LEVEL")
	@Column(columnDefinition = "SUGGEST_LEVEL")
	private String suggestLevel;

	/**
	 *  学部
	 */
	@ApiModelProperty("学部")
	@TableField("DEPARTMENT")
	@Column(columnDefinition = "DEPARTMENT")
	private String department;

	/**
	 *  专家类型
	 */
	@ApiModelProperty("专家类型")
	@TableField("EXPERT_TYPE")
	@Column(columnDefinition = "EXPERT_TYPE")
	private String expertType;

	/**
	 *  是否责任专家
	 */
	@ApiModelProperty("是否责任专家")
	@TableField("IS_REPONSIBLE")
	@Column(columnDefinition = "IS_REPONSIBLE")
	private String isResponsible;

	/**
	 *  院士类型
	 */
	@ApiModelProperty("院士类型")
	@TableField("ACADEMICIAN_TYPE")
	@Column(columnDefinition = "ACADEMICIAN_TYPE")
	private String academicianType;

	/**
	 *  头像地址
	 */
	@ApiModelProperty("头像地址")
	@TableField("ID_PHOTO")
	@Column(columnDefinition = "ID_PHOTO")
	private String idPhoto;

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
	*  性别
	*/
	public String getGender (){
		return gender;
	}
	public void setGender (String gender){
		this.gender = gender;
	}

	/**
	*  排名
	*/
	public String getRanking (){
		return ranking;
	}
	public void setRanking (String ranking){
		this.ranking = ranking;
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
	*  出生年月
	*/
	public Date getBirthday (){
		return birthday;
	}
	public void setBirthday (Date birthday){
		this.birthday = birthday;
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
	*  民族
	*/
	public String getNation (){
		return nation;
	}
	public void setNation (String nation){
		this.nation = nation;
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
	*  归国人员
	*/
	public String getReturnee (){
		return returnee;
	}
	public void setReturnee (String returnee){
		this.returnee = returnee;
	}

	/**
	*  归国时间
	*/
	public Date getReturneedate (){
		return returneedate;
	}
	public void setReturneedate (Date returneedate){
		this.returneedate = returneedate;
	}

	/**
	*  技术职称
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
	*  毕业学校
	*/
	public String getGraduatedschool (){
		return graduatedschool;
	}
	public void setGraduatedschool (String graduatedschool){
		this.graduatedschool = graduatedschool;
	}

	/**
	*  毕业时间
	*/
	public Date getGraduatedtime (){
		return graduatedtime;
	}
	public void setGraduatedtime (Date graduatedtime){
		this.graduatedtime = graduatedtime;
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
	*  从事专业
	*/
	public String getProfession (){
		return profession;
	}
	public void setProfession (String profession){
		this.profession = profession;
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
	*  办公电话
	*/
	public String getTelephone (){
		return telephone;
	}
	public void setTelephone (String telephone){
		this.telephone = telephone;
	}

	/**
	*  移动电话
	*/
	public String getMobile (){
		return mobile;
	}
	public void setMobile (String mobile){
		this.mobile = mobile;
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
	*  邮政编码
	*/
	public String getPostalcode (){
		return postalcode;
	}
	public void setPostalcode (String postalcode){
		this.postalcode = postalcode;
	}

	/**
	*  工作单位
	*/
	public String getWorkplace (){
		return workplace;
	}
	public void setWorkplace (String workplace){
		this.workplace = workplace;
	}

	/**
	*  行政职务
	*/
	public String getPost (){
		return post;
	}
	public void setPost (String post){
		this.post = post;
	}

	/**
	*  二级单位
	*/
	public String getSecondunit (){
		return secondunit;
	}
	public void setSecondunit (String secondunit){
		this.secondunit = secondunit;
	}

	/**
	*  党派
	*/
	public String getPoliticalstatus (){
		return politicalstatus;
	}
	public void setPoliticalstatus (String politicalstatus){
		this.politicalstatus = politicalstatus;
	}

	/**
	*  完成单位
	*/
	public String getFinishunit (){
		return finishunit;
	}
	public void setFinishunit (String finishunit){
		this.finishunit = finishunit;
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
	*  单位性质
	*/
	public String getUnittype (){
		return unittype;
	}
	public void setUnittype (String unittype){
		this.unittype = unittype;
	}

	/**
	*  参加本项目的开始时间
	*/
	public Date getStartdate (){
		return startdate;
	}
	public void setStartdate (Date startdate){
		this.startdate = startdate;
	}

	/**
	*  参加本项目的接收时间
	*/
	public Date getEnddate (){
		return enddate;
	}
	public void setEnddate (Date enddate){
		this.enddate = enddate;
	}

	/**
	*  对本项目主要学术贡献
	*/
	public String getContribute (){
		return contribute;
	}
	public void setContribute (String contribute){
		this.contribute = contribute;
	}

	/**
	*  曾获国家、省级科技奖励情况
	*/
	public String getEveraward (){
		return everaward;
	}
	public void setEveraward (String everaward){
		this.everaward = everaward;
	}

	public Date getDegreedate() {
		return degreedate;
	}

	public void setDegreedate(Date degreedate) {
		this.degreedate = degreedate;
	}

	public String getIsAcademician() {
		return isAcademician;
	}

	public void setIsAcademician(String isAcademician) {
		this.isAcademician = isAcademician;
	}

	public Date getAcademicianDate() {
		return academicianDate;
	}

	public void setAcademicianDate(Date academicianDate) {
		this.academicianDate = academicianDate;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getSuggestLevel() {
		return suggestLevel;
	}

	public void setSuggestLevel(String suggestLevel) {
		this.suggestLevel = suggestLevel;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getExpertType() {
		return expertType;
	}

	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}

	public String getIsResponsible() {
		return isResponsible;
	}

	public void setIsResponsible(String isResponsible) {
		this.isResponsible = isResponsible;
	}

	public String getAcademicianType() {
		return academicianType;
	}

	public void setAcademicianType(String academicianType) {
		this.academicianType = academicianType;
	}

	public String getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(String idPhoto) {
		this.idPhoto = idPhoto;
	}
}
