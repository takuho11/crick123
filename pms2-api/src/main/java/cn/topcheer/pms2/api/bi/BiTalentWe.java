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
 *  数据仓-人才-经历表
 */
@Entity
@Table(name = "BI_TALENT_WE")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiTalentWe {

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
    @TableField("WORKPLACE")
    @Column(columnDefinition = "WORKPLACE")
    @FieldDes(name = "workplace", lenth = "255", type = "String", memo = "")
    private String workplace;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("UNITNAME")
    @Column(columnDefinition = "UNITNAME")
    @FieldDes(name = "unitname", lenth = "255", type = "String", memo = "")
    private String unitname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PROFESSION")
    @Column(columnDefinition = "PROFESSION")
    @FieldDes(name = "profession", lenth = "255", type = "String", memo = "")
    private String profession;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("STARTDATE")
    @Column(columnDefinition = "STARTDATE")
    @FieldDes(name = "startdate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date startdate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ENDDATE")
    @Column(columnDefinition = "ENDDATE")
    @FieldDes(name = "enddate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date enddate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("POST")
    @Column(columnDefinition = "POST")
    @FieldDes(name = "post", lenth = "1000", type = "String", memo = "")
    private String post;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("DEGREE")
    @Column(columnDefinition = "DEGREE")
    @FieldDes(name = "degree", lenth = "255", type = "String", memo = "")
    private String degree;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EDUCATION")
    @Column(columnDefinition = "EDUCATION")
    @FieldDes(name = "education", lenth = "255", type = "String", memo = "")
    private String education;

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
    @TableField("ISCITY")
    @Column(columnDefinition = "ISCITY")
    @FieldDes(name = "iscity", lenth = "255", type = "String", memo = "")
    private String iscity;

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
    @ApiModelProperty("")
    @TableField("ADDRESS")
    @Column(columnDefinition = "ADDRESS")
    @FieldDes(name = "address", lenth = "255", type = "String", memo = "")
    private String address;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("POSTALCODE")
    @Column(columnDefinition = "POSTALCODE")
    @FieldDes(name = "postalcode", lenth = "255", type = "String", memo = "")
    private String postalcode;

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
    @TableField("EMPLOYERSTATUS")
    @Column(columnDefinition = "EMPLOYERSTATUS")
    @FieldDes(name = "employerstatus", lenth = "255", type = "String", memo = "")
    private String employerstatus;

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
     *  主聘任单位名称
     */
    @ApiModelProperty("主聘任单位名称")
    @TableField("MAIN_UNITNAME")
    @Column(columnDefinition = "MAIN_UNITNAME")
    @FieldDes(name = "main_unitname", lenth = "255", type = "String", memo = "主聘任单位名称")
    private String main_unitname;

    /**
     *  主聘任单位统一社会信用代码
     */
    @ApiModelProperty("主聘任单位统一社会信用代码")
    @TableField("MAIN_UNIFORMCODE")
    @Column(columnDefinition = "MAIN_UNIFORMCODE")
    @FieldDes(name = "main_uniformcode", lenth = "255", type = "String", memo = "主聘任单位统一社会信用代码")
    private String main_uniformcode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CREDITCODE")
    @Column(columnDefinition = "CREDITCODE")
    private String creditcode;

    /**
     *  二级学院
     */
    @ApiModelProperty("二级学院")
    @TableField("JTGZDW")
    @Column(columnDefinition = "JTGZDW")
    @FieldDes(name = "jtgzdw", lenth = "500", type = "String", memo = "二级学院")
    private String jtgzdw;

    /**
     *  是否要显示二级学院字段
     */
    @ApiModelProperty("是否要显示二级学院字段")
    @TableField("SELFLEVEL")
    @Column(columnDefinition = "SELFLEVEL")
    @FieldDes(name = "selflevel", lenth = "255", type = "String", memo = "是否要显示二级学院字段")
    private String selflevel;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("JTGZDW_CREDITCODE")
    @Column(columnDefinition = "JTGZDW_CREDITCODE")
    @FieldDes(name = "jtgzdw", lenth = "500", type = "String", memo = "二级学院统一社会信用代码")
    private String jtgzdw_creditcode;

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
    public String getWorkplace (){
        return workplace;
    }
    public void setWorkplace (String workplace){
        this.workplace = workplace;
    }

    /**
    *  
    */
    public String getUnitname (){
        return unitname;
    }
    public void setUnitname (String unitname){
        this.unitname = unitname;
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
    public Date getStartdate (){
        return startdate;
    }
    public void setStartdate (Date startdate){
        this.startdate = startdate;
    }

    /**
    *  
    */
    public Date getEnddate (){
        return enddate;
    }
    public void setEnddate (Date enddate){
        this.enddate = enddate;
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
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
    }

    /**
    *  
    */
    public String getIscity (){
        return iscity;
    }
    public void setIscity (String iscity){
        this.iscity = iscity;
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
    public String getAddress (){
        return address;
    }
    public void setAddress (String address){
        this.address = address;
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
    public String getEmployerstatus (){
        return employerstatus;
    }
    public void setEmployerstatus (String employerstatus){
        this.employerstatus = employerstatus;
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
    public String getCreditcode (){
        return creditcode;
    }
    public void setCreditcode (String creditcode){
        this.creditcode = creditcode;
    }

    /**
    *  二级学院
    */
    public String getJtgzdw (){
        return jtgzdw;
    }
    public void setJtgzdw (String jtgzdw){
        this.jtgzdw = jtgzdw;
    }

    /**
    *  是否要显示二级学院字段
    */
    public String getSelflevel (){
        return selflevel;
    }
    public void setSelflevel (String selflevel){
        this.selflevel = selflevel;
    }

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

    public String getJtgzdw_creditcode() {
        return jtgzdw_creditcode;
    }

    public void setJtgzdw_creditcode(String jtgzdw_creditcode) {
        this.jtgzdw_creditcode = jtgzdw_creditcode;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}