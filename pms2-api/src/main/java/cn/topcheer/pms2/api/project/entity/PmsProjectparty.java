/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-1-25 17:15:07
 *
 */
package cn.topcheer.pms2.api.project.entity;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.annotation.PersonTable;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  项目人员表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@PersonTable
@ClassInfo(name = "人员信息", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@Entity
@Table(name="PMS_PROJECTPARTY")
public class PmsProjectparty {

	/**
	 *  主键
	 */
	@FieldDes(name="id",lenth="255",type="String",memo="主键")
	private String id;

	/**
	 *  姓名
	 */
	@FieldDes(name="name",lenth="255",type="String",memo="姓名")
	private String name;

	/**
	 *  性别
	 */
	@FieldDes(name="sex",lenth="255",type="String",memo="性别")
	private String sex;

	/**
	 *  证件名称
	 */
	@FieldDes(name="certificatename",lenth="255",type="String",memo="证件名称")
	private String certificatename;

	/**
	 *  证件号码
	 */
	@FieldDes(name="certificatenumber",lenth="255",type="String",memo="证件号码")
	private String certificatenumber;

	/**
	 *  出生日期
	 */
	@FieldDes(name="birthday",type="Date",memo="出生日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date birthday;

	/**
	 *  移动电话
	 */
	@FieldDes(name="mobilephone",lenth="255",type="String",memo="移动电话")
	private String mobilephone;

	/**
	 *  固定电话
	 */
	@FieldDes(name="telphone",lenth="255",type="String",memo="固定电话")
	private String telphone;

	/**
	 *  技术职称
	 */
	@FieldDes(name="professionalpost",lenth="255",type="String",memo="技术职称")
	private String professionalpost;

	/**
	 *  从事专业
	 */
	@FieldDes(name="workforprofession",lenth="500",type="String",memo="从事专业")
	private String workforprofession;

	/**
	 *  学历
	 */
	@FieldDes(name="education",lenth="500",type="String",memo="学历")
	private String education;

	/**
	 *  学位
	 */
	@FieldDes(name="degree",lenth="500",type="String",memo="学位")
	private String degree;

	/**
	 *  工作单位
	 */
	@FieldDes(name="workplace",lenth="1000",type="String",memo="工作单位")
	private String workplace;

	/**
	 *  电子信息
	 */
	@FieldDes(name="email",lenth="2000",type="String",memo="电子信息")
	private String email;

	/**
	 *  项目分工
	 */
	@FieldDes(name="programrole",lenth="2000",type="String",memo="项目分工")
	private String programrole;

	/**
	 *  主表关联id
	 */
	@FieldDes(name="mainid",lenth="255",type="String",memo="主表关联id")
	private String mainid;

	/**
	 *  邮政编码
	 */
	@FieldDes(name="postalcode",lenth="400",type="String",memo="邮政编码")
	private String postalcode;

	/**
	 *  通讯地址
	 */
	@FieldDes(name="postaladdress",lenth="600",type="String",memo="通讯地址")
	private String postaladdress;

	/**
	 *  排序
	 */
	@FieldDes(name="seq",type="Integer",memo="排序")
	private Integer seq;

	/**
	 *  加入本项目起止年月
	 */
	@FieldDes(name="joinstarttime",type="Date",memo="加入本项目起止年月")
	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+08:00")
	private Date joinstarttime;

	/**
	 *  加入本项目起止年月
	 */
	@FieldDes(name="joinendtime",type="Date",memo="加入本项目起止年月")
	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+08:00")
	private Date joinendtime;

	/**
	 *  参与项目研发时间（月）
	 */
	@FieldDes(name="createtime",type="Integer",memo="参与项目研发时间（月）")
	private Integer createtime;

	/**
	 *  出生地
	 */
	@FieldDes(name="birthplace",lenth="255",type="String",memo="出生地")
	private String birthplace;

	/**
	 *  民族
	 */
	@FieldDes(name="nation",lenth="255",type="String",memo="民族")
	private String nation;

	/**
	 *  政治面貌
	 */
	@FieldDes(name="political",lenth="255",type="String",memo="政治面貌")
	private String political;

	/**
	 *  行政职务
	 */
	@FieldDes(name="administrativeduty",lenth="1000",type="String",memo="行政职务")
	private String administrativeduty;

	/**
	 *  所学专业
	 */
	@FieldDes(name="major",lenth="1000",type="String",memo="所学专业")
	private String major;

	/**
	 *  毕业学校
	 */
	@FieldDes(name="graduateschool",lenth="1000",type="String",memo="毕业学校")
	private String graduateschool;

	/**
	 *  毕业时间
	 */
	@FieldDes(name="graduatetime",type="Date",memo="毕业时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date graduatetime;

	/**
	 *  曾获科技奖励情况
	 */
	@FieldDes(name="everaward",lenth="1000",type="String",memo="曾获科技奖励情况")
	private String everaward;

	/**
	 *  对本项目主要学术.技术贡献
	 */
	@FieldDes(name="contribution",lenth="2000",type="String",memo="对本项目主要学术.技术贡献")
	private String contribution;

	/**
	 *  主要参加人员分类
	 */
	@FieldDes(name="classify",lenth="255",type="String",memo="主要参加人员分类")
	private String classify;

	/**
	 *  备注
	 */
	@FieldDes(name="memo",lenth="2000",type="String",memo="备注")
	private String memo;

	/**
	 *  传真
	 */
	@FieldDes(name="fax",lenth="255",type="String",memo="传真")
	private String fax;

	/**
	 *  年龄
	 */
	@FieldDes(name="age",type="Integer",memo="年龄")
	private Integer age;

	/**
	 *  国内国外
	 */
	@FieldDes(name="gngwdw",lenth="255",type="String",memo="国内国外")
	private String gngwdw;

	/**
	 *  留学国别--hlj
	 */
	@FieldDes(name="studycountry",lenth="255",type="String",memo="留学国别--hlj")
	private String studycountry;

	/**
	 *  归国时间--hlj
	 */
	@FieldDes(name="returntime",type="Date",memo="归国时间--hlj")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date returntime;

	/**
	 *  年参加时间(月)---hlj参考合同
	 */
	@FieldDes(name="ncjsj",type="Integer",memo="年参加时间(月)---hlj参考合同")
	private Integer ncjsj;

	/**
	 *  参加项目开始时间
	 */
	@FieldDes(name="startdate",type="Date",memo="参加项目开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date startdate;

	/**
	 *  参加项目结束时间
	 */
	@FieldDes(name="enddate",type="Date",memo="参加项目结束时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date enddate;

	/**
	 *  一级职称
	 */
	@FieldDes(name="titleseries",lenth="255",type="String",memo="一级职称")
	private String titleseries;

	/**
	 *  二级职称
	 */
	@FieldDes(name="titlename",lenth="255",type="String",memo="二级职称")
	private String titlename;

	/**
	 *  最终职称
	 */
	@FieldDes(name="duty",lenth="255",type="String",memo="最终职称")
	private String duty;

	/**
	 *  国籍
	 */
	@FieldDes(name="nationality",lenth="255",type="String",memo="国籍")
	private String nationality;

	/**
	 *  qq号
	 */
	@FieldDes(name="qqnumber",lenth="255",type="String",memo="qq号")
	private String qqnumber;

	/**
	 *  微信号
	 */
	@FieldDes(name="vxnumber",lenth="255",type="String",memo="微信号")
	private String vxnumber;

	/**
	 *  父表关联id
	 */
	@FieldDes(name="sourceid",lenth="255",type="String",memo="父表关联id")
	private String sourceid;

	/**
	 *  类型
	 */
	@FieldDes(name="type",lenth="255",type="String",memo="类型")
	private String type;

	/**
	 *  学院（所）
	 */
	@FieldDes(name="collegename",lenth="255",type="String",memo="学院（所）")
	private String collegename;

	/**
	 *  所在单位的统一社会信用代码
	 */
	@FieldDes(name="shehuixingyong",lenth="255",type="String",memo="所在单位的统一社会信用代码")
	private String shehuixingyong;

	/**
	 *  学位授予国家(或地区)
	 */
	@FieldDes(name="degreenationality",lenth="255",type="String",memo="学位授予国家(或地区)")
	private String degreenationality;



	/**
	 *  主键
	 */

	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		this.id=id;
	}


	/**
	 *  姓名
	 */

	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name=name;
	}


	/**
	 *  性别
	 */

	public String getSex (){
		return sex;
	}
	public void setSex (String sex){
		this.sex=sex;
	}


	/**
	 *  证件名称
	 */

	public String getCertificatename (){
		return certificatename;
	}
	public void setCertificatename (String certificatename){
		this.certificatename=certificatename;
	}


	/**
	 *  证件号码
	 */

	public String getCertificatenumber (){
		return certificatenumber;
	}
	public void setCertificatenumber (String certificatenumber){
		this.certificatenumber=certificatenumber;
	}


	/**
	 *  出生日期
	 */

	public Date getBirthday (){
		return birthday;
	}
	public void setBirthday (Date birthday){
		this.birthday=birthday;
	}


	/**
	 *  移动电话
	 */

	public String getMobilephone (){
		return mobilephone;
	}
	public void setMobilephone (String mobilephone){
		this.mobilephone=mobilephone;
	}


	/**
	 *  固定电话
	 */

	public String getTelphone (){
		return telphone;
	}
	public void setTelphone (String telphone){
		this.telphone=telphone;
	}


	/**
	 *  技术职称
	 */

	public String getProfessionalpost (){
		return professionalpost;
	}
	public void setProfessionalpost (String professionalpost){
		this.professionalpost=professionalpost;
	}


	/**
	 *  从事专业
	 */

	public String getWorkforprofession (){
		return workforprofession;
	}
	public void setWorkforprofession (String workforprofession){
		this.workforprofession=workforprofession;
	}


	/**
	 *  学历
	 */

	public String getEducation (){
		return education;
	}
	public void setEducation (String education){
		this.education=education;
	}


	/**
	 *  学位
	 */

	public String getDegree (){
		return degree;
	}
	public void setDegree (String degree){
		this.degree=degree;
	}


	/**
	 *  工作单位
	 */

	public String getWorkplace (){
		return workplace;
	}
	public void setWorkplace (String workplace){
		this.workplace=workplace;
	}


	/**
	 *  电子信息
	 */

	public String getEmail (){
		return email;
	}
	public void setEmail (String email){
		this.email=email;
	}


	/**
	 *  项目分工
	 */

	public String getProgramrole (){
		return programrole;
	}
	public void setProgramrole (String programrole){
		this.programrole=programrole;
	}


	/**
	 *  主表关联id
	 */

	public String getMainid (){
		return mainid;
	}
	public void setMainid (String mainid){
		this.mainid=mainid;
	}


	/**
	 *  邮政编码
	 */

	public String getPostalcode (){
		return postalcode;
	}
	public void setPostalcode (String postalcode){
		this.postalcode=postalcode;
	}


	/**
	 *  通讯地址
	 */

	public String getPostaladdress (){
		return postaladdress;
	}
	public void setPostaladdress (String postaladdress){
		this.postaladdress=postaladdress;
	}


	/**
	 *  排序
	 */

	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		this.seq=seq;
	}


	/**
	 *  加入本项目起止年月
	 */

	public Date getJoinstarttime (){
		return joinstarttime;
	}
	public void setJoinstarttime (Date joinstarttime){
		this.joinstarttime=joinstarttime;
	}


	/**
	 *  加入本项目起止年月
	 */

	public Date getJoinendtime (){
		return joinendtime;
	}
	public void setJoinendtime (Date joinendtime){
		this.joinendtime=joinendtime;
	}


	/**
	 *  参与项目研发时间（月）
	 */

	public Integer getCreatetime (){
		return createtime;
	}
	public void setCreatetime (Integer createtime){
		this.createtime=createtime;
	}


	/**
	 *  出生地
	 */

	public String getBirthplace (){
		return birthplace;
	}
	public void setBirthplace (String birthplace){
		this.birthplace=birthplace;
	}


	/**
	 *  民族
	 */

	public String getNation (){
		return nation;
	}
	public void setNation (String nation){
		this.nation=nation;
	}


	/**
	 *  政治面貌
	 */

	public String getPolitical (){
		return political;
	}
	public void setPolitical (String political){
		this.political=political;
	}


	/**
	 *  行政职务
	 */

	public String getAdministrativeduty (){
		return administrativeduty;
	}
	public void setAdministrativeduty (String administrativeduty){
		this.administrativeduty=administrativeduty;
	}


	/**
	 *  所学专业
	 */

	public String getMajor (){
		return major;
	}
	public void setMajor (String major){
		this.major=major;
	}


	/**
	 *  毕业学校
	 */

	public String getGraduateschool (){
		return graduateschool;
	}
	public void setGraduateschool (String graduateschool){
		this.graduateschool=graduateschool;
	}


	/**
	 *  毕业时间
	 */

	public Date getGraduatetime (){
		return graduatetime;
	}
	public void setGraduatetime (Date graduatetime){
		this.graduatetime=graduatetime;
	}


	/**
	 *  曾获科技奖励情况
	 */

	public String getEveraward (){
		return everaward;
	}
	public void setEveraward (String everaward){
		this.everaward=everaward;
	}


	/**
	 *  对本项目主要学术.技术贡献
	 */

	public String getContribution (){
		return contribution;
	}
	public void setContribution (String contribution){
		this.contribution=contribution;
	}


	/**
	 *  主要参加人员分类
	 */

	public String getClassify (){
		return classify;
	}
	public void setClassify (String classify){
		this.classify=classify;
	}


	/**
	 *  备注
	 */

	public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		this.memo=memo;
	}


	/**
	 *  传真
	 */

	public String getFax (){
		return fax;
	}
	public void setFax (String fax){
		this.fax=fax;
	}


	/**
	 *  年龄
	 */

	public Integer getAge (){
		return age;
	}
	public void setAge (Integer age){
		this.age=age;
	}


	/**
	 *  国内国外
	 */

	public String getGngwdw (){
		return gngwdw;
	}
	public void setGngwdw (String gngwdw){
		this.gngwdw=gngwdw;
	}


	/**
	 *  留学国别--hlj
	 */

	public String getStudycountry (){
		return studycountry;
	}
	public void setStudycountry (String studycountry){
		this.studycountry=studycountry;
	}


	/**
	 *  归国时间--hlj
	 */

	public Date getReturntime (){
		return returntime;
	}
	public void setReturntime (Date returntime){
		this.returntime=returntime;
	}


	/**
	 *  年参加时间(月)---hlj参考合同
	 */

	public Integer getNcjsj (){
		return ncjsj;
	}
	public void setNcjsj (Integer ncjsj){
		this.ncjsj=ncjsj;
	}


	/**
	 *  参加项目开始时间
	 */

	public Date getStartdate (){
		return startdate;
	}
	public void setStartdate (Date startdate){
		this.startdate=startdate;
	}


	/**
	 *  参加项目结束时间
	 */

	public Date getEnddate (){
		return enddate;
	}
	public void setEnddate (Date enddate){
		this.enddate=enddate;
	}


	/**
	 *  一级职称
	 */

	public String getTitleseries (){
		return titleseries;
	}
	public void setTitleseries (String titleseries){
		this.titleseries=titleseries;
	}


	/**
	 *  二级职称
	 */

	public String getTitlename (){
		return titlename;
	}
	public void setTitlename (String titlename){
		this.titlename=titlename;
	}


	/**
	 *  最终职称
	 */

	public String getDuty (){
		return duty;
	}
	public void setDuty (String duty){
		this.duty=duty;
	}


	/**
	 *  国籍
	 */

	public String getNationality (){
		return nationality;
	}
	public void setNationality (String nationality){
		this.nationality=nationality;
	}


	/**
	 *  qq号
	 */

	public String getQqnumber (){
		return qqnumber;
	}
	public void setQqnumber (String qqnumber){
		this.qqnumber=qqnumber;
	}


	/**
	 *  微信号
	 */

	public String getVxnumber (){
		return vxnumber;
	}
	public void setVxnumber (String vxnumber){
		this.vxnumber=vxnumber;
	}


	/**
	 *  父表关联id
	 */

	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		this.sourceid=sourceid;
	}


	/**
	 *  类型
	 */

	public String getType (){
		return type;
	}
	public void setType (String type){
		this.type=type;
	}


	public String getCollegename() {return collegename;}
	public void setCollegename(String collegename) {this.collegename = collegename;}

	public String getShehuixingyong() {return shehuixingyong;}
	public void setShehuixingyong(String shehuixingyong) {this.shehuixingyong = shehuixingyong;}

	public String getDegreenationality() {return degreenationality;}
	public void setDegreenationality(String degreenationality) {this.degreenationality = degreenationality;}
}