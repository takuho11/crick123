/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2024-1-3 13:58:58
 */
package cn.topcheer.pms2.api.project.entity.projectBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *  计划项目-项目人员信息
 */
@Entity
@Table(name = "PMS_PROJECTBASE_XMRY")
@ClassInfo(name = "计划项目-项目人员信息", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseXmry {

    /**
     *  固定字段:唯一标识
     */
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
    private String id;
    /**
     *  固定字段:数据类型
     */
    private String type;
    /**
     *  固定字段:关联主表id
     */
    private String mainid;
    /**
     *  固定字段:关联子表id
     */
    private String sourceid;
    /**
     *  固定字段:第一次保存时间
     */
    @FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;
    /**
     *  固定字段:每次更新数据时间
     */
    @FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;
    /**
     *  固定字段:排序
     */
    @FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
    private Integer seq;
    /**
     *  固定字段:备注
     */
    private String memo;
    /**
     *  姓名
     */
    private String name;
    /**
     *  外文名
     */
    private String foreignname;
    /**
     *  国籍
     */
    private String nationality;
    /**
     *  民族
     */
    private String nation;
    /**
     *  政治面貌
     */
    private String politicalstatus;
    /**
     *  证件类型
     */
    private String certificatename;
    /**
     *  证件号码
     */
    private String certificatenumber;
    /**
     *  出生日期
     */
    @FieldDes(name = "birthday", type = "Date", memo = "出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date birthday;
    /**
     *  性别
     */
    private String gender;
    /**
     *  手机号码
     */
    private String mobile;
    /**
     *  电子邮箱
     */
    private String email;
    /**
     *  职务
     */
    private String post;
    /**
     *  职称
     */
    private String title;
    /**
     *  最高学历
     */
    private String education;
    /**
     *  最高学位
     */
    private String degree;
    /**
     *  最高学历毕业学校
     */
    private String graduatedschool;
    /**
     *  从事专业
     */
    private String profession;
    /**
     *  地址
     */
    private String address;
    /**
     *  省
     */
    private String province;
    /**
     *  市
     */
    private String city;
    /**
     *  县
     */
    private String county;
    /**
     *  街道
     */
    private String street;
    /**
     *  所学专业
     */
    private String major;
    /**
     *  籍贯
     */
    private String nativeplace;
    /**
     *  每年工作时间
     */
    private String worktimemonth;
    /**
     *  工作单位
     */
    private String workunit;
    /**
     *  研究领域
     */
    private String researchdirection;
    /**
     *  项目分工
     */
    private String programrole;
    /**
     *  电话（座机）
     */
    private String telephone;

    /**
     *  统一社会信用代码
     */
    @ApiModelProperty("统一社会信用代码")
    @TableField("CREDITCODE")
    @Column(columnDefinition = "CREDITCODE")
    private String creditcode;

    /**
     *  传真
     */
    @ApiModelProperty("传真")
    @TableField("FAX")
    @Column(columnDefinition = "FAX")
    private String fax;

    /**
     *  是否负责人
     */
    @ApiModelProperty("是否负责人")
    @TableField("IS_RESPONSIBLE_PERSON")
    @Column(columnDefinition = "IS_RESPONSIBLE_PERSON")
    private String isResponsiblePerson;

    /**
     *  排名
     */
    @ApiModelProperty("排名")
    @TableField("RANKING")
    @Column(columnDefinition = "RANKING")
    private String ranking;

    /**
     *  固定字段:唯一标识
     */
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     *  固定字段:数据类型
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *  固定字段:关联主表id
     */
    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    /**
     *  固定字段:关联子表id
     */
    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    /**
     *  固定字段:第一次保存时间
     */
    public Date getSavedate() {
        return savedate;
    }

    public void setSavedate(Date savedate) {
        this.savedate = savedate;
    }

    /**
     *  固定字段:每次更新数据时间
     */
    public Date getUpdatelasttime() {
        return updatelasttime;
    }

    public void setUpdatelasttime(Date updatelasttime) {
        this.updatelasttime = updatelasttime;
    }

    /**
     *  固定字段:排序
     */
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     *  固定字段:备注
     */
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     *  姓名
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *  外文名
     */
    public String getForeignname() {
        return foreignname;
    }

    public void setForeignname(String foreignname) {
        this.foreignname = foreignname;
    }

    /**
     *  国籍
     */
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     *  民族
     */
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     *  政治面貌
     */
    public String getPoliticalstatus() {
        return politicalstatus;
    }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus;
    }

    /**
     *  证件类型
     */
    public String getCertificatename() {
        return certificatename;
    }

    public void setCertificatename(String certificatename) {
        this.certificatename = certificatename;
    }

    /**
     *  证件号码
     */
    public String getCertificatenumber() {
        return certificatenumber;
    }

    public void setCertificatenumber(String certificatenumber) {
        this.certificatenumber = certificatenumber;
    }

    /**
     *  出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     *  性别
     */
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *  手机号码
     */
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *  电子邮箱
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *  职务
     */
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    /**
     *  职称
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *  最高学历
     */
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    /**
     *  最高学位
     */
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     *  最高学历毕业学校
     */
    public String getGraduatedschool() {
        return graduatedschool;
    }

    public void setGraduatedschool(String graduatedschool) {
        this.graduatedschool = graduatedschool;
    }

    /**
     *  从事专业
     */
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     *  地址
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *  省
     */
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     *  市
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     *  县
     */
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    /**
     *  街道
     */
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *  所学专业
     */
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    /**
     *  籍贯
     */
    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    /**
     *  每年工作时间
     */
    public String getWorktimemonth() {
        return worktimemonth;
    }

    public void setWorktimemonth(String worktimemonth) {
        this.worktimemonth = worktimemonth;
    }

    /**
     *  工作单位
     */
    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }

    /**
     *  研究领域
     */
    public String getResearchdirection() {
        return researchdirection;
    }

    public void setResearchdirection(String researchdirection) {
        this.researchdirection = researchdirection;
    }

    /**
     *  项目分工
     */
    public String getProgramrole() {
        return programrole;
    }

    public void setProgramrole(String programrole) {
        this.programrole = programrole;
    }

    /**
     *  电话（座机）
     */
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreditcode() {
        return creditcode;
    }

    public void setCreditcode(String creditcode) {
        this.creditcode = creditcode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getIsResponsiblePerson() {
        return isResponsiblePerson;
    }

    public void setIsResponsiblePerson(String isResponsiblePerson) {
        this.isResponsiblePerson = isResponsiblePerson;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}