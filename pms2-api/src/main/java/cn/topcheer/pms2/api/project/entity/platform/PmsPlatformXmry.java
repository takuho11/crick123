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
 *  平台载体人员表
 */
@Entity
@Table(name="PMS_PLATFORM_XMRY")
@ClassInfo(name = "平台载体人员表", module = SysModuleEnum.TECH_PLATFORM, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPlatformXmry {

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
     *  固定字段:关联主表ID
     */
	@ApiModelProperty("固定字段:关联主表ID")
	@TableField("MAINID")
	@Column(columnDefinition = "MAINID")
	private String mainid;

	/**
     *  固定字段:关联子表ID
     */
	@ApiModelProperty("固定字段:关联子表ID")
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
	@TableField("POLITICALSTATUS")
	@Column(columnDefinition = "POLITICALSTATUS")
	private String politicalstatus;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
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
     *  每年工作时间
     */
	@ApiModelProperty("每年工作时间")
	@TableField("WORKTIMEMONTH")
	@Column(columnDefinition = "WORKTIMEMONTH")
	private String worktimemonth;

	/**
     *  工作单位
     */
	@ApiModelProperty("工作单位")
	@TableField("WORKUNIT")
	@Column(columnDefinition = "WORKUNIT")
	private String workunit;

	/**
     *  研究领域
     */
	@ApiModelProperty("研究领域")
	@TableField("RESEARCHDIRECTION")
	@Column(columnDefinition = "RESEARCHDIRECTION")
	private String researchdirection;

	/**
     *  项目分工
     */
	@ApiModelProperty("项目分工")
	@TableField("PROGRAMROLE")
	@Column(columnDefinition = "PROGRAMROLE")
	private String programrole;

	/**
     *  电话（座机）
     */
	@ApiModelProperty("电话（座机）")
	@TableField("TELEPHONE")
	@Column(columnDefinition = "TELEPHONE")
	private String telephone;


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
	*  固定字段:关联主表ID
	*/
	public String getMainid (){
		return mainid;
	}
	public void setMainid (String mainid){
		this.mainid = mainid;
	}

	/**
	*  固定字段:关联子表ID
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
	public String getPoliticalstatus (){
		return politicalstatus;
	}
	public void setPoliticalstatus (String politicalstatus){
		this.politicalstatus = politicalstatus;
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
	*  每年工作时间
	*/
	public String getWorktimemonth (){
		return worktimemonth;
	}
	public void setWorktimemonth (String worktimemonth){
		this.worktimemonth = worktimemonth;
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
	*  研究领域
	*/
	public String getResearchdirection (){
		return researchdirection;
	}
	public void setResearchdirection (String researchdirection){
		this.researchdirection = researchdirection;
	}

	/**
	*  项目分工
	*/
	public String getProgramrole (){
		return programrole;
	}
	public void setProgramrole (String programrole){
		this.programrole = programrole;
	}

	/**
	*  电话（座机）
	*/
	public String getTelephone (){
		return telephone;
	}
	public void setTelephone (String telephone){
		this.telephone = telephone;
	}

}