/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 15:56:31
 */
package cn.topcheer.pms2.api.bi;

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
 *  数据仓-人才-个人信息
 */
@Entity
@Table(name = "BI_TALENT_BI")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiTalentBi {

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "")
    private String id;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "")
    private Integer seq;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "")
    private String type;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("NAME")
    @Column(columnDefinition = "NAME")
    @FieldDes(name = "name", lenth = "255", type = "String", memo = "")
    private String name;

    /**
     *
     */
    @ApiModelProperty("英文名")
    @TableField("FOREIGNNAME")
    @Column(columnDefinition = "FOREIGNNAME")
    @FieldDes(name = "foreignname", lenth = "255", type = "String", memo = "")
    private String foreignname;

    /**
     *
     */
    @ApiModelProperty("国籍")
    @TableField("NATIONALITY")
    @Column(columnDefinition = "NATIONALITY")
    @FieldDes(name = "nationality", lenth = "255", type = "String", memo = "")
    private String nationality;

    /**
     *
     */
    @ApiModelProperty("名族")
    @TableField("NATION")
    @Column(columnDefinition = "NATION")
    @FieldDes(name = "nation", lenth = "255", type = "String", memo = "")
    private String nation;

    /**
     *
     */
    @ApiModelProperty("证件类型")
    @TableField("CERTIFICATENAME")
    @Column(columnDefinition = "CERTIFICATENAME")
    @FieldDes(name = "certificatename", lenth = "255", type = "String", memo = "")
    private String certificatename;

    /**
     *
     */
    @ApiModelProperty("证件号码")
    @TableField("CERTIFICATENUMBER")
    @Column(columnDefinition = "CERTIFICATENUMBER")
    @FieldDes(name = "certificatenumber", lenth = "255", type = "String", memo = "")
    private String certificatenumber;

    /**
     *
     */
    @ApiModelProperty("党派")
    @TableField("POLITICALSTATUS")
    @Column(columnDefinition = "POLITICALSTATUS")
    @FieldDes(name = "politicalstatus", lenth = "255", type = "String", memo = "")
    private String politicalstatus;

    /**
     *
     */
    @ApiModelProperty("生日")
    @TableField("BIRTHDAY")
    @Column(columnDefinition = "BIRTHDAY")
    @FieldDes(name = "birthday", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date birthday;

    /**
     *
     */
    @ApiModelProperty("性别")
    @TableField("GENDER")
    @Column(columnDefinition = "GENDER")
    @FieldDes(name = "gender", lenth = "255", type = "String", memo = "")
    private String gender;

    /**
     *
     */
    @ApiModelProperty("年龄")
    @TableField("AGE")
    @Column(columnDefinition = "AGE")
    @FieldDes(name = "age", type = "Integer", memo = "")
    private Integer age;

    /**
     *
     */
    @ApiModelProperty("手机")
    @TableField("MOBILE")
    @Column(columnDefinition = "MOBILE")
    @FieldDes(name = "mobile", lenth = "255", type = "String", memo = "")
    private String mobile;

    /**
     *
     */
    @ApiModelProperty("办公电话")
    @TableField("TELEPHONE")
    @Column(columnDefinition = "TELEPHONE")
    @FieldDes(name = "telephone", lenth = "255", type = "String", memo = "")
    private String telephone;

    /**
     *
     */
    @ApiModelProperty("传真")
    @TableField("FAX")
    @Column(columnDefinition = "FAX")
    @FieldDes(name = "fax", lenth = "255", type = "String", memo = "")
    private String fax;

    /**
     *
     */
    @ApiModelProperty("邮箱")
    @TableField("EMAIL")
    @Column(columnDefinition = "EMAIL")
    @FieldDes(name = "email", lenth = "255", type = "String", memo = "")
    private String email;

    /**
     *
     */
    @ApiModelProperty("通讯地址")
    @TableField("ADDRESS")
    @Column(columnDefinition = "ADDRESS")
    @FieldDes(name = "address", lenth = "255", type = "String", memo = "")
    private String address;

    /**
     *
     */
    @ApiModelProperty("工作单位")
    @TableField("WORKUNIT")
    @Column(columnDefinition = "WORKUNIT")
    @FieldDes(name = "workunit", lenth = "255", type = "String", memo = "")
    private String workunit;

    /**
     *
     */
    @ApiModelProperty("工作类型")
    @TableField("WORKTYPE")
    @Column(columnDefinition = "WORKTYPE")
    @FieldDes(name = "worktype", lenth = "255", type = "String", memo = "")
    private String worktype;

    /**
     *
     */
    @ApiModelProperty("所学专业")
    @TableField("MAJOR")
    @Column(columnDefinition = "MAJOR")
    @FieldDes(name = "major", lenth = "255", type = "String", memo = "")
    private String major;

    /**
     *
     */
    @ApiModelProperty("职业")
    @TableField("PROFESSION")
    @Column(columnDefinition = "PROFESSION")
    @FieldDes(name = "profession", lenth = "255", type = "String", memo = "")
    private String profession;

    /**
     *
     */
    @ApiModelProperty("专业领域")
    @TableField("PROFESSIONALFIELD")
    @Column(columnDefinition = "PROFESSIONALFIELD")
    @FieldDes(name = "professionalfield", lenth = "255", type = "String", memo = "")
    private String professionalfield;

    /**
     *
     */
    @ApiModelProperty("最高学位")
    @TableField("DEGREE")
    @Column(columnDefinition = "DEGREE")
    @FieldDes(name = "degree", lenth = "255", type = "String", memo = "")
    private String degree;

    /**
     *
     */
    @ApiModelProperty("最高学历")
    @TableField("EDUCATION")
    @Column(columnDefinition = "EDUCATION")
    @FieldDes(name = "education", lenth = "255", type = "String", memo = "")
    private String education;

    /**
     *
     */
    @ApiModelProperty("毕业学校")
    @TableField("GRADUATEDSCHOOL")
    @Column(columnDefinition = "GRADUATEDSCHOOL")
    @FieldDes(name = "graduatedschool", lenth = "255", type = "String", memo = "")
    private String graduatedschool;

    /**
     *
     */
    @ApiModelProperty("毕业时间")
    @TableField("GRADUATEDDATE")
    @Column(columnDefinition = "GRADUATEDDATE")
    @FieldDes(name = "graduateddate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date graduateddate;

    /**
     *
     */
    @ApiModelProperty("技术职称")
    @TableField("TITLE")
    @Column(columnDefinition = "TITLE")
    @FieldDes(name = "title", lenth = "255", type = "String", memo = "")
    private String title;

    /**
     *
     */
    @ApiModelProperty("行政职务")
    @TableField("POST")
    @Column(columnDefinition = "POST")
    @FieldDes(name = "post", lenth = "255", type = "String", memo = "")
    private String post;

    /**
     *
     */
    @ApiModelProperty("人才类型")
    @TableField("TALENTSTYPE")
    @Column(columnDefinition = "TALENTSTYPE")
    @FieldDes(name = "talentstype", lenth = "255", type = "String", memo = "")
    private String talentstype;

    /**
     *
     */
    @ApiModelProperty("是否归国人员")
    @TableField("ISRETURNEES")
    @Column(columnDefinition = "ISRETURNEES")
    @FieldDes(name = "isreturnees", lenth = "255", type = "String", memo = "")
    private String isreturnees;

    /**
     *
     */
    @ApiModelProperty("归国时间")
    @TableField("RETURNDATE")
    @Column(columnDefinition = "RETURNDATE")
    @FieldDes(name = "returndate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date returndate;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SUBJECTCODE")
    @Column(columnDefinition = "SUBJECTCODE")
    @FieldDes(name = "subjectcode", lenth = "255", type = "String", memo = "")
    private String subjectcode;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SUBJECTNAME")
    @Column(columnDefinition = "SUBJECTNAME")
    @FieldDes(name = "subjectname", lenth = "255", type = "String", memo = "")
    private String subjectname;

    /**
     *
     */
    @ApiModelProperty("全名")
    @TableField("FAMILYNAME")
    @Column(columnDefinition = "FAMILYNAME")
    @FieldDes(name = "familyname", lenth = "255", type = "String", memo = "")
    private String familyname;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("GIVENNAME")
    @Column(columnDefinition = "GIVENNAME")
    @FieldDes(name = "givenname", lenth = "255", type = "String", memo = "")
    private String givenname;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("DEGREESCHOOL")
    @Column(columnDefinition = "DEGREESCHOOL")
    @FieldDes(name = "degreeschool", lenth = "255", type = "String", memo = "")
    private String degreeschool;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("DEGREEDATE")
    @Column(columnDefinition = "DEGREEDATE")
    @FieldDes(name = "degreedate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date degreedate;

    /**
     *
     */
    @ApiModelProperty("邮政编码")
    @TableField("POSTALCODE")
    @Column(columnDefinition = "POSTALCODE")
    @FieldDes(name = "postalcode", lenth = "255", type = "String", memo = "")
    private String postalcode;

    /**
     *
     */
    @ApiModelProperty("是否为院士")
    @TableField("ISACADEMICIAN")
    @Column(columnDefinition = "ISACADEMICIAN")
    @FieldDes(name = "isacademician", lenth = "255", type = "String", memo = "")
    private String isacademician;

    /**
     *
     */
    @ApiModelProperty("是否为博士后")
    @TableField("ISPOSTDOCTORAL")
    @Column(columnDefinition = "ISPOSTDOCTORAL")
    @FieldDes(name = "ispostdoctoral", lenth = "255", type = "String", memo = "")
    private String ispostdoctoral;

    /**
     *
     */
    @ApiModelProperty("备用email")
    @TableField("EMAIL_SPARE")
    @Column(columnDefinition = "EMAIL_SPARE")
    @FieldDes(name = "email_spare", lenth = "255", type = "String", memo = "")
    private String emailSpare;

    /**
     *
     */
    @ApiModelProperty("博士生导师")
    @TableField("DOCTORTUTOR")
    @Column(columnDefinition = "DOCTORTUTOR")
    @FieldDes(name = "doctortutor", lenth = "255", type = "String", memo = "")
    private String doctortutor;

    /**
     *
     */
    @ApiModelProperty("家庭电话")
    @TableField("FAMILYPHONE")
    @Column(columnDefinition = "FAMILYPHONE")
    @FieldDes(name = "familyphone", lenth = "255", type = "String", memo = "")
    private String familyphone;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("WEBSITE")
    @Column(columnDefinition = "WEBSITE")
    @FieldDes(name = "website", lenth = "255", type = "String", memo = "")
    private String website;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("DEGREENATIONALITY")
    @Column(columnDefinition = "DEGREENATIONALITY")
    @FieldDes(name = "degreenationality", lenth = "255", type = "String", memo = "")
    private String degreenationality;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("MAINID")
    @Column(columnDefinition = "MAINID")
    @FieldDes(name = "mainid", lenth = "255", type = "String", memo = "")
    private String mainid;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    @FieldDes(name = "sourceid", lenth = "255", type = "String", memo = "")
    private String sourceid;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("KEYWORDS")
    @Column(columnDefinition = "KEYWORDS")
    @FieldDes(name = "keywords", lenth = "2000", type = "String", memo = "")
    private String keywords;

    /**
     *
     */
    @ApiModelProperty("出生地")
    @TableField("BIRTHPLACE")
    @Column(columnDefinition = "BIRTHPLACE")
    @FieldDes(name = "birthplace", lenth = "255", type = "String", memo = "")
    private String birthplace;

    /**
     *
     */
    @ApiModelProperty("外语")
    @TableField("FOREIGNLANGUAGE")
    @Column(columnDefinition = "FOREIGNLANGUAGE")
    @FieldDes(name = "foreignlanguage", lenth = "255", type = "String", memo = "")
    private String foreignlanguage;

    /**
     *
     */
    @ApiModelProperty("语言等级")
    @TableField("LANGUAGELEVEL")
    @Column(columnDefinition = "LANGUAGELEVEL")
    @FieldDes(name = "languagelevel", lenth = "255", type = "String", memo = "")
    private String languagelevel;

    /**
     *
     */
    @ApiModelProperty("汇款地址")
    @TableField("REMITTANCEADDRESS")
    @Column(columnDefinition = "REMITTANCEADDRESS")
    @FieldDes(name = "remittanceaddress", lenth = "255", type = "String", memo = "")
    private String remittanceaddress;

    /**
     * 职称
     */
    @ApiModelProperty("职称")
    @TableField("TITLE_DETAIL")
    @Column(columnDefinition = "TITLE_DETAIL")
    @FieldDes(name = "titleDetail", lenth = "255", type = "String", memo = "")
    private String titleDetail;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("TITLEDETAILONE")
    @Column(columnDefinition = "TITLEDETAILONE")
    @FieldDes(name = "titledetailone", lenth = "255", type = "String", memo = "")
    private String titledetailone;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("TITLEDETAILTWO")
    @Column(columnDefinition = "TITLEDETAILTWO")
    @FieldDes(name = "titledetailtwo", lenth = "255", type = "String", memo = "")
    private String titledetailtwo;

    /**
     *  保存时间
     */
    @ApiModelProperty("保存时间")
    @TableField("SAVEDATE")
    @Column(columnDefinition = "SAVEDATE")
    @FieldDes(name = "savedate", type = "Date", memo = "保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;

    /**
     *  最后一次更新时间
     */
    @ApiModelProperty("最后一次更新时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "最后一次更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;

    /**
     *
     */
    @ApiModelProperty("个人简介")
    @TableField("INTRODUCE")
    @Column(columnDefinition = "INTRODUCE")
    @FieldDes(name = "introduce", lenth = "4000", type = "String", memo = "")
    private String introduce;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("WQCHECK")
    @Column(columnDefinition = "WQCHECK")
    private String wqcheck;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SBBH")
    @Column(columnDefinition = "SBBH")
    @FieldDes(name = "sbbh", lenth = "255", type = "String", memo = "")
    private String sbbh;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SBUPDATETIME")
    @Column(columnDefinition = "SBUPDATETIME")
    @FieldDes(name = "sbupdatetime", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date sbupdatetime;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("SBSTATUS")
    @Column(columnDefinition = "SBSTATUS")
    @FieldDes(name = "sbstatus", lenth = "255", type = "String", memo = "")
    private String sbstatus;

    /**
     *  实验室/省重点实验室人员（是、否）
     */
    @ApiModelProperty("实验室/省重点实验室人员（是、否）")
    @TableField("LABPERSON")
    @Column(columnDefinition = "LABPERSON")
    @FieldDes(name = "labperson", lenth = "50", type = "String", memo = "实验室/省重点实验室人员（是、否）")
    private String labperson;

    /**
     *  实验室名称
     */
    @ApiModelProperty("实验室名称")
    @TableField("LABNAME")
    @Column(columnDefinition = "LABNAME")
    @FieldDes(name = "labname", lenth = "255", type = "String", memo = "实验室名称")
    private String labname;

    /**
     *  申请项目对应实验室内设研究方向
     */
    @ApiModelProperty("申请项目对应实验室内设研究方向")
    @TableField("LABRESEARCHDIRECTION")
    @Column(columnDefinition = "LABRESEARCHDIRECTION")
    @FieldDes(name = "labresearchdirection", lenth = "255", type = "String", memo = "申请项目对应实验室内设研究方向")
    private String labresearchdirection;

    /**
     *  是否拥有楚才卡
     */
    @ApiModelProperty("是否拥有楚才卡")
    @TableField("ISHAVECCK")
    @Column(columnDefinition = "ISHAVECCK")
    @FieldDes(name = "ishavecck", lenth = "255", type = "String", memo = "是否拥有楚才卡")
    private String ishavecck;
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
    *
    */
        @Id
    public String getId (){
        return id;
    }
    public void setId (String id){
        this.id = id;
    }

    /**
    *
    */
    public Integer getSeq (){
        return seq;
    }
    public void setSeq (Integer seq){
        this.seq = seq;
    }

    /**
    *
    */
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
    }

    /**
    *
    */
    public String getName (){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }

    /**
    *
    */
    public String getForeignname (){
        return foreignname;
    }
    public void setForeignname (String foreignname){
        this.foreignname = foreignname;
    }

    /**
    *
    */
    public String getNationality (){
        return nationality;
    }
    public void setNationality (String nationality){
        this.nationality = nationality;
    }

    /**
    *
    */
    public String getNation (){
        return nation;
    }
    public void setNation (String nation){
        this.nation = nation;
    }

    /**
    *
    */
    public String getCertificatename (){
        return certificatename;
    }
    public void setCertificatename (String certificatename){
        this.certificatename = certificatename;
    }

    /**
    *
    */
    public String getCertificatenumber (){
        return certificatenumber;
    }
    public void setCertificatenumber (String certificatenumber){
        this.certificatenumber = certificatenumber;
    }

    /**
    *
    */
    public String getPoliticalstatus (){
        return politicalstatus;
    }
    public void setPoliticalstatus (String politicalstatus){
        this.politicalstatus = politicalstatus;
    }

    /**
    *
    */
    public Date getBirthday (){
        return birthday;
    }
    public void setBirthday (Date birthday){
        this.birthday = birthday;
    }

    /**
    *
    */
    public String getGender (){
        return gender;
    }
    public void setGender (String gender){
        this.gender = gender;
    }

    /**
    *
    */
    public Integer getAge (){
        return age;
    }
    public void setAge (Integer age){
        this.age = age;
    }

    /**
    *
    */
    public String getMobile (){
        return mobile;
    }
    public void setMobile (String mobile){
        this.mobile = mobile;
    }

    /**
    *
    */
    public String getTelephone (){
        return telephone;
    }
    public void setTelephone (String telephone){
        this.telephone = telephone;
    }

    /**
    *
    */
    public String getFax (){
        return fax;
    }
    public void setFax (String fax){
        this.fax = fax;
    }

    /**
    *
    */
    public String getEmail (){
        return email;
    }
    public void setEmail (String email){
        this.email = email;
    }

    /**
    *
    */
    public String getAddress (){
        return address;
    }
    public void setAddress (String address){
        this.address = address;
    }

    /**
    *
    */
    public String getWorkunit (){
        return workunit;
    }
    public void setWorkunit (String workunit){
        this.workunit = workunit;
    }

    /**
    *
    */
    public String getWorktype (){
        return worktype;
    }
    public void setWorktype (String worktype){
        this.worktype = worktype;
    }

    /**
    *
    */
    public String getMajor (){
        return major;
    }
    public void setMajor (String major){
        this.major = major;
    }

    /**
    *
    */
    public String getProfession (){
        return profession;
    }
    public void setProfession (String profession){
        this.profession = profession;
    }

    /**
    *
    */
    public String getProfessionalfield (){
        return professionalfield;
    }
    public void setProfessionalfield (String professionalfield){
        this.professionalfield = professionalfield;
    }

    /**
    *
    */
    public String getDegree (){
        return degree;
    }
    public void setDegree (String degree){
        this.degree = degree;
    }

    /**
    *
    */
    public String getEducation (){
        return education;
    }
    public void setEducation (String education){
        this.education = education;
    }

    /**
    *
    */
    public String getGraduatedschool (){
        return graduatedschool;
    }
    public void setGraduatedschool (String graduatedschool){
        this.graduatedschool = graduatedschool;
    }

    /**
    *
    */
    public Date getGraduateddate (){
        return graduateddate;
    }
    public void setGraduateddate (Date graduateddate){
        this.graduateddate = graduateddate;
    }

    /**
    *
    */
    public String getTitle (){
        return title;
    }
    public void setTitle (String title){
        this.title = title;
    }

    /**
    *
    */
    public String getPost (){
        return post;
    }
    public void setPost (String post){
        this.post = post;
    }

    /**
    *
    */
    public String getTalentstype (){
        return talentstype;
    }
    public void setTalentstype (String talentstype){
        this.talentstype = talentstype;
    }

    /**
    *
    */
    public String getIsreturnees (){
        return isreturnees;
    }
    public void setIsreturnees (String isreturnees){
        this.isreturnees = isreturnees;
    }

    /**
    *
    */
    public Date getReturndate (){
        return returndate;
    }
    public void setReturndate (Date returndate){
        this.returndate = returndate;
    }

    /**
    *
    */
    public String getSubjectcode (){
        return subjectcode;
    }
    public void setSubjectcode (String subjectcode){
        this.subjectcode = subjectcode;
    }

    /**
    *
    */
    public String getSubjectname (){
        return subjectname;
    }
    public void setSubjectname (String subjectname){
        this.subjectname = subjectname;
    }

    /**
    *
    */
    public String getFamilyname (){
        return familyname;
    }
    public void setFamilyname (String familyname){
        this.familyname = familyname;
    }

    /**
    *
    */
    public String getGivenname (){
        return givenname;
    }
    public void setGivenname (String givenname){
        this.givenname = givenname;
    }

    /**
    *
    */
    public String getDegreeschool (){
        return degreeschool;
    }
    public void setDegreeschool (String degreeschool){
        this.degreeschool = degreeschool;
    }

    /**
    *
    */
    public Date getDegreedate (){
        return degreedate;
    }
    public void setDegreedate (Date degreedate){
        this.degreedate = degreedate;
    }

    /**
    *
    */
    public String getPostalcode (){
        return postalcode;
    }
    public void setPostalcode (String postalcode){
        this.postalcode = postalcode;
    }

    /**
    *
    */
    public String getIsacademician (){
        return isacademician;
    }
    public void setIsacademician (String isacademician){
        this.isacademician = isacademician;
    }

    /**
    *
    */
    public String getIspostdoctoral (){
        return ispostdoctoral;
    }
    public void setIspostdoctoral (String ispostdoctoral){
        this.ispostdoctoral = ispostdoctoral;
    }

    /**
    *
    */
    public String getEmailSpare (){
        return emailSpare;
    }
    public void setEmailSpare (String emailSpare){
        this.emailSpare = emailSpare;
    }

    /**
    *
    */
    public String getDoctortutor (){
        return doctortutor;
    }
    public void setDoctortutor (String doctortutor){
        this.doctortutor = doctortutor;
    }

    /**
    *
    */
    public String getFamilyphone (){
        return familyphone;
    }
    public void setFamilyphone (String familyphone){
        this.familyphone = familyphone;
    }

    /**
    *
    */
    public String getWebsite (){
        return website;
    }
    public void setWebsite (String website){
        this.website = website;
    }

    /**
    *
    */
    public String getDegreenationality (){
        return degreenationality;
    }
    public void setDegreenationality (String degreenationality){
        this.degreenationality = degreenationality;
    }

    /**
    *
    */
    public String getMainid (){
        return mainid;
    }
    public void setMainid (String mainid){
        this.mainid = mainid;
    }

    /**
    *
    */
    public String getSourceid (){
        return sourceid;
    }
    public void setSourceid (String sourceid){
        this.sourceid = sourceid;
    }

    /**
    *
    */
    public String getKeywords (){
        return keywords;
    }
    public void setKeywords (String keywords){
        this.keywords = keywords;
    }

    /**
    *
    */
    public String getBirthplace (){
        return birthplace;
    }
    public void setBirthplace (String birthplace){
        this.birthplace = birthplace;
    }

    /**
    *
    */
    public String getForeignlanguage (){
        return foreignlanguage;
    }
    public void setForeignlanguage (String foreignlanguage){
        this.foreignlanguage = foreignlanguage;
    }

    /**
    *
    */
    public String getLanguagelevel (){
        return languagelevel;
    }
    public void setLanguagelevel (String languagelevel){
        this.languagelevel = languagelevel;
    }

    /**
    *
    */
    public String getRemittanceaddress (){
        return remittanceaddress;
    }
    public void setRemittanceaddress (String remittanceaddress){
        this.remittanceaddress = remittanceaddress;
    }

    /**
    *
    */
    public String getTitledetailone (){
        return titledetailone;
    }
    public void setTitledetailone (String titledetailone){
        this.titledetailone = titledetailone;
    }

    /**
    *
    */
    public String getTitledetailtwo (){
        return titledetailtwo;
    }
    public void setTitledetailtwo (String titledetailtwo){
        this.titledetailtwo = titledetailtwo;
    }

    /**
    *  保存时间
    */
    public Date getSavedate (){
        return savedate;
    }
    public void setSavedate (Date savedate){
        this.savedate = savedate;
    }

    /**
    *  最后一次更新时间
    */
    public Date getUpdatelasttime (){
        return updatelasttime;
    }
    public void setUpdatelasttime (Date updatelasttime){
        this.updatelasttime = updatelasttime;
    }

    /**
    *
    */
    public String getIntroduce (){
        return introduce;
    }
    public void setIntroduce (String introduce){
        this.introduce = introduce;
    }

    /**
    *
    */
    public String getWqcheck (){
        return wqcheck;
    }
    public void setWqcheck (String wqcheck){
        this.wqcheck = wqcheck;
    }

    /**
    *
    */
    public String getSbbh (){
        return sbbh;
    }
    public void setSbbh (String sbbh){
        this.sbbh = sbbh;
    }

    /**
    *
    */
    public Date getSbupdatetime (){
        return sbupdatetime;
    }
    public void setSbupdatetime (Date sbupdatetime){
        this.sbupdatetime = sbupdatetime;
    }

    /**
    *
    */
    public String getSbstatus (){
        return sbstatus;
    }
    public void setSbstatus (String sbstatus){
        this.sbstatus = sbstatus;
    }

    /**
    *  实验室/贵州省重点实验室人员（是、否）
    */
    public String getLabperson (){
        return labperson;
    }
    public void setLabperson (String labperson){
        this.labperson = labperson;
    }

    /**
    *  实验室名称
    */
    public String getLabname (){
        return labname;
    }
    public void setLabname (String labname){
        this.labname = labname;
    }

    /**
    *  申请项目对应实验室内设研究方向
    */
    public String getLabresearchdirection (){
        return labresearchdirection;
    }
    public void setLabresearchdirection (String labresearchdirection){
        this.labresearchdirection = labresearchdirection;
    }

    /**
    *  是否拥有楚才卡
    */
    public String getIshavecck (){
        return ishavecck;
    }
    public void setIshavecck (String ishavecck){
        this.ishavecck = ishavecck;
    }

    public String getTitleDetail() {
        return titleDetail;
    }

    public void setTitleDetail(String titleDetail) {
        this.titleDetail = titleDetail;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
