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
 *  数据仓-承担项目表
 */
@Entity
@Table(name = "BI_ACH_PRO")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiAchPro {

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
    @TableField("PROJECTTYPE")
    @Column(columnDefinition = "PROJECTTYPE")
    @FieldDes(name = "projecttype", lenth = "255", type = "String", memo = "")
    private String projecttype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("APPLICATIONNO")
    @Column(columnDefinition = "APPLICATIONNO")
    @FieldDes(name = "applicationno", lenth = "255", type = "String", memo = "")
    private String applicationno;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PROJECTNAME")
    @Column(columnDefinition = "PROJECTNAME")
    @FieldDes(name = "projectname", lenth = "2000", type = "String", memo = "")
    private String projectname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PROJECTSOURCE")
    @Column(columnDefinition = "PROJECTSOURCE")
    @FieldDes(name = "projectsource", lenth = "255", type = "String", memo = "")
    private String projectsource;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PROJECTLEADER")
    @Column(columnDefinition = "PROJECTLEADER")
    @FieldDes(name = "projectleader", lenth = "255", type = "String", memo = "")
    private String projectleader;

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
    @TableField("YEAR")
    @Column(columnDefinition = "YEAR")
    @FieldDes(name = "year", lenth = "255", type = "String", memo = "")
    private String year;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PROJECTFUNDS")
    @Column(columnDefinition = "PROJECTFUNDS")
    private BigDecimal projectfunds;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("FINANCIALFUNDS")
    @Column(columnDefinition = "FINANCIALFUNDS")
    private BigDecimal financialfunds;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("IMPLEMENTATION")
    @Column(columnDefinition = "IMPLEMENTATION")
    @FieldDes(name = "implementation", lenth = "255", type = "String", memo = "")
    private String implementation;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PLANPROJECTTYPE")
    @Column(columnDefinition = "PLANPROJECTTYPE")
    @FieldDes(name = "planprojecttype", lenth = "255", type = "String", memo = "")
    private String planprojecttype;

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
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "")
    private Integer seq;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("DEPARTMENT")
    @Column(columnDefinition = "DEPARTMENT")
    @FieldDes(name = "department", lenth = "255", type = "String", memo = "")
    private String department;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISSHOW")
    @Column(columnDefinition = "ISSHOW")
    @FieldDes(name = "isshow", lenth = "255", type = "String", memo = "")
    private String isshow;

    /**
     *  业务
     */
    @ApiModelProperty("业务")
    @TableField("GRANT_NAME")
    @Column(columnDefinition = "GRANT_NAME")
    @FieldDes(name = "grant_name", lenth = "200", type = "String", memo = "业务")
    private String grantName;

    /**
     *  专题
     */
    @ApiModelProperty("专题")
    @TableField("SUBJECT_NAME")
    @Column(columnDefinition = "SUBJECT_NAME")
    @FieldDes(name = "subject_name", lenth = "200", type = "String", memo = "专题")
    private String subjectName;

    /**
     *  主管处（科）室
     */
    @ApiModelProperty("主管处（科）室")
    @TableField("ADMIN_ORG_NAME")
    @Column(columnDefinition = "ADMIN_ORG_NAME")
    @FieldDes(name = "admin_org_name", lenth = "400", type = "String", memo = "主管处（科）室")
    private String adminOrgName;

    /**
     *  申报单位
     */
    @ApiModelProperty("申报单位")
    @TableField("ORG_NAME")
    @Column(columnDefinition = "ORG_NAME")
    @FieldDes(name = "org_name", lenth = "400", type = "String", memo = "申报单位")
    private String orgName;

    /**
     *  统一社会信用代码
     */
    @ApiModelProperty("统一社会信用代码")
    @TableField("ORG_SHXY_NO")
    @Column(columnDefinition = "ORG_SHXY_NO")
    @FieldDes(name = "org_shxy_no", lenth = "400", type = "String", memo = "统一社会信用代码")
    private String orgShxyNo;

    /**
     *  负责人身份证
     */
    @ApiModelProperty("负责人身份证")
    @TableField("FZR_CARD_CODE")
    @Column(columnDefinition = "FZR_CARD_CODE")
    @FieldDes(name = "fzr_card_code", lenth = "100", type = "String", memo = "负责人身份证")
    private String fzrCardCode;

    /**
     *  负责人职称信息
     */
    @ApiModelProperty("负责人职称信息")
    @TableField("PROF_TITLE_NAME")
    @Column(columnDefinition = "PROF_TITLE_NAME")
    @FieldDes(name = "prof_title_name", lenth = "600", type = "String", memo = "负责人职称信息")
    private String profTitleName;

    /**
     *  负责人电话
     */
    @ApiModelProperty("负责人电话")
    @TableField("FZR_MOBILE")
    @Column(columnDefinition = "FZR_MOBILE")
    @FieldDes(name = "fzr_mobile", lenth = "200", type = "String", memo = "负责人电话")
    private String fzrMobile;

    /**
     *  负责人邮箱
     */
    @ApiModelProperty("负责人邮箱")
    @TableField("FZR_EMAIL")
    @Column(columnDefinition = "FZR_EMAIL")
    @FieldDes(name = "fzr_email", lenth = "200", type = "String", memo = "负责人邮箱")
    private String fzrEmail;

    /**
     *  合作单位
     */
    @ApiModelProperty("合作单位")
    @TableField("HZ_ORG_NAMES")
    @Column(columnDefinition = "HZ_ORG_NAMES")
    @FieldDes(name = "hz_org_names", lenth = "1500", type = "String", memo = "合作单位")
    private String hzOrgNames;

    /**
     *  立项金额
     */
    @ApiModelProperty("立项金额")
    @TableField("AWARD_AMT")
    @Column(columnDefinition = "AWARD_AMT")
    private String awardAmt;

    /**
     *  合同状态
     */
    @ApiModelProperty("合同状态")
    @TableField("IS_QD_CTR")
    @Column(columnDefinition = "IS_QD_CTR")
    @FieldDes(name = "is_qd_ctr", lenth = "400", type = "String", memo = "合同状态")
    private String isQdCtr;

    /**
     *  合同到期时间
     */
    @ApiModelProperty("合同到期时间")
    @TableField("CTR_END_DATE")
    @Column(columnDefinition = "CTR_END_DATE")
    @FieldDes(name = "ctr_end_date", type = "Date", memo = "合同到期时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date ctrEndDate;

    /**
     *  验收状态
     */
    @ApiModelProperty("验收状态")
    @TableField("ACCEPTANCE_STATUS")
    @Column(columnDefinition = "ACCEPTANCE_STATUS")
    @FieldDes(name = "acceptance_status", lenth = "200", type = "String", memo = "验收状态")
    private String acceptanceStatus;

    /**
     *  验收时间
     */
    @ApiModelProperty("验收时间")
    @TableField("ACCEPTANCE_DATE")
    @Column(columnDefinition = "ACCEPTANCE_DATE")
    @FieldDes(name = "acceptance_date", type = "Date", memo = "验收时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date acceptanceDate;

    /**
     *  验收形式
     */
    @ApiModelProperty("验收形式")
    @TableField("ACCEPTANCE_NATURE")
    @Column(columnDefinition = "ACCEPTANCE_NATURE")
    @FieldDes(name = "acceptance_nature", lenth = "4000", type = "String", memo = "验收形式")
    private String acceptanceNature;

    /**
     *  验收结论
     */
    @ApiModelProperty("验收结论")
    @TableField("ACT_RESULT")
    @Column(columnDefinition = "ACT_RESULT")
    @FieldDes(name = "act_result", lenth = "200", type = "String", memo = "验收结论")
    private String actResult;

    /**
     *  专利申请
     */
    @ApiModelProperty("专利申请")
    @TableField("ZL_APP")
    @Column(columnDefinition = "ZL_APP")
    @FieldDes(name = "zl_app", type = "Integer", memo = "专利申请")
    private Integer zlApp;

    /**
     *  专利授权
     */
    @ApiModelProperty("专利授权")
    @TableField("ZL_AUTH")
    @Column(columnDefinition = "ZL_AUTH")
    @FieldDes(name = "zl_auth", type = "Integer", memo = "专利授权")
    private Integer zlAuth;

    /**
     *  省级以上奖项
     */
    @ApiModelProperty("省级以上奖项")
    @TableField("PROVIONCE_ABOVE_AWARD")
    @Column(columnDefinition = "PROVIONCE_ABOVE_AWARD")
    @FieldDes(name = "provionce_above_award", type = "Integer", memo = "省级以上奖项")
    private Integer provionceAboveAward;

    /**
     *  新产品
     */
    @ApiModelProperty("新产品")
    @TableField("NEW_PRODUCT")
    @Column(columnDefinition = "NEW_PRODUCT")
    @FieldDes(name = "new_product", type = "Integer", memo = "新产品")
    private Integer newProduct;

    /**
     *  新工艺
     */
    @ApiModelProperty("新工艺")
    @TableField("NEW_GY")
    @Column(columnDefinition = "NEW_GY")
    @FieldDes(name = "new_gy", type = "Integer", memo = "新工艺")
    private Integer newGy;

    /**
     *  引进人才
     */
    @ApiModelProperty("引进人才")
    @TableField("IMPORT_PERSON")
    @Column(columnDefinition = "IMPORT_PERSON")
    @FieldDes(name = "import_person", type = "Integer", memo = "引进人才")
    private Integer importPerson;

    /**
     *  培养人才
     */
    @ApiModelProperty("培养人才")
    @TableField("TRAIN_PERSON")
    @Column(columnDefinition = "TRAIN_PERSON")
    @FieldDes(name = "train_person", type = "Integer", memo = "培养人才")
    private Integer trainPerson;

    /**
     *  技术标准
     */
    @ApiModelProperty("技术标准")
    @TableField("TECHNICAL_STANDARD")
    @Column(columnDefinition = "TECHNICAL_STANDARD")
    @FieldDes(name = "technical_standard", type = "Integer", memo = "技术标准")
    private Integer technicalStandard;

    /**
     *  论文论著
     */
    @ApiModelProperty("论文论著")
    @TableField("PUBICATION_NUM")
    @Column(columnDefinition = "PUBICATION_NUM")
    @FieldDes(name = "pubication_num", type = "Integer", memo = "论文论著")
    private Integer pubicationNum;

    /**
     *  科技报告
     */
    @ApiModelProperty("科技报告")
    @TableField("TECHNOLOGY_NUM")
    @Column(columnDefinition = "TECHNOLOGY_NUM")
    @FieldDes(name = "technology_num", type = "Integer", memo = "科技报告")
    private Integer technologyNum;

    /**
     *  新增销售收入
     */
    @ApiModelProperty("新增销售收入")
    @TableField("NEW_SALES_INCOME")
    @Column(columnDefinition = "NEW_SALES_INCOME")
    private String newSalesIncome;

    /**
     *  新增利税
     */
    @ApiModelProperty("新增利税")
    @TableField("NEW_TAX")
    @Column(columnDefinition = "NEW_TAX")
    private String newTax;

    /**
     *  推荐单位
     */
    @ApiModelProperty("推荐单位")
    @TableField("TJ_ORG_NAME")
    @Column(columnDefinition = "TJ_ORG_NAME")
    @FieldDes(name = "tj_org_name", lenth = "50", type = "String", memo = "推荐单位")
    private String tjOrgName;

    /**
     *  项目负责人所在单位
     */
    @ApiModelProperty("项目负责人所在单位")
    @TableField("PSN_ORG_NAME")
    @Column(columnDefinition = "PSN_ORG_NAME")
    @FieldDes(name = "psn_org_name", lenth = "50", type = "String", memo = "项目负责人所在单位")
    private String psnOrgName;

    /**
     *  开始时间
     */
    @ApiModelProperty("开始时间")
    @TableField("START_TIME")
    @Column(columnDefinition = "START_TIME")
    @FieldDes(name = "start_time", type = "Date", memo = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date startTime;

    /**
     *  结束时间
     */
    @ApiModelProperty("结束时间")
    @TableField("END_TIME")
    @Column(columnDefinition = "END_TIME")
    @FieldDes(name = "end_time", type = "Date", memo = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date endTime;

    /**
     *  性别
     */
    @ApiModelProperty("性别")
    @TableField("SEX")
    @Column(columnDefinition = "SEX")
    @FieldDes(name = "sex", lenth = "20", type = "String", memo = "性别")
    private String sex;

    /**
     *  单位所在省
     */
    @ApiModelProperty("单位所在省")
    @TableField("AREA_PROVINCE")
    @Column(columnDefinition = "AREA_PROVINCE")
    @FieldDes(name = "area_province", lenth = "50", type = "String", memo = "单位所在省")
    private String areaProvince;

    /**
     *  立项主键
     */
    @ApiModelProperty("立项主键")
    @TableField("PRJ_CODE")
    @Column(columnDefinition = "PRJ_CODE")
    @FieldDes(name = "prj_code", lenth = "255", type = "String", memo = "立项主键")
    private String prjCode;

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
     *  数据更新时间
     */
    @ApiModelProperty("数据更新时间")
    @TableField("UPDATE_TIME")
    @Column(columnDefinition = "UPDATE_TIME")
    @FieldDes(name = "update_time", type = "Date", memo = "数据更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updateTime;

    /**
     *  学位
     */
    @ApiModelProperty("学位")
    @TableField("DEGREE")
    @Column(columnDefinition = "DEGREE")
    @FieldDes(name = "degree", lenth = "255", type = "String", memo = "学位")
    private String degree;

    /**
     *  项目摘要
     */
    @ApiModelProperty("项目摘要")
    @TableField("SUMMARY")
    @Column(columnDefinition = "SUMMARY")
    @FieldDes(name = "summary", lenth = "1500", type = "String", memo = "项目摘要")
    private String summary;

    /**
     *  研究内容与关键技术
     */
    @ApiModelProperty("研究内容与关键技术")
    @TableField("DEVELOP_CONTENT_TECHNOLOGY")
    @Column(columnDefinition = "DEVELOP_CONTENT_TECHNOLOGY")
    @FieldDes(name = "develop_content_technology", lenth = "1500", type = "String", memo = "研究内容与关键技术")
    private String developContentTechnology;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("DRBJ")
    @Column(columnDefinition = "DRBJ")
    @FieldDes(name = "drbj", lenth = "255", type = "String", memo = "")
    private String drbj;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MAINLEADER")
    @Column(columnDefinition = "MAINLEADER")
    @FieldDes(name = "mainleader", lenth = "1000", type = "String", memo = "")
    private String mainleader;

    /**
     *  计划名称其他
     */
    @ApiModelProperty("计划名称其他")
    @TableField("PLANPROJECTTYPE_QT")
    @Column(columnDefinition = "PLANPROJECTTYPE_QT")
    @FieldDes(name = "planprojecttype_qt", lenth = "255", type = "String", memo = "计划名称其他")
    private String planprojecttypeQt;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("WQCHECK")
    @Column(columnDefinition = "WQCHECK")
    private String wqcheck;

    /**
     *  负责人证件类型
     */
    @ApiModelProperty("负责人证件类型")
    @TableField("CERTIFICATENAME")
    @Column(columnDefinition = "CERTIFICATENAME")
    @FieldDes(name = "certificatename", lenth = "255", type = "String", memo = "负责人证件类型")
    private String certificatename;

    /**
     *  职称详情一
     */
    @ApiModelProperty("职称详情一")
    @TableField("TITLE_DETAIL_ONE")
    @Column(columnDefinition = "TITLE_DETAIL_ONE")
    @FieldDes(name = "titleDetailOne", lenth = "100", type = "String", memo = "职称详情一")
    private String titleDetailOne;

    /**
     *  职称详情二
     */
    @ApiModelProperty("职称详情二")
    @TableField("TITLE_DETAIL_TWO")
    @Column(columnDefinition = "TITLE_DETAIL_TWO")
    @FieldDes(name = "titleDetailTwo", lenth = "100", type = "String", memo = "职称详情二")
    private String titleDetailTwo;

    /**
     *  职称详情并
     */
    @ApiModelProperty("职称详情并")
    @TableField("TITLE_END")
    @Column(columnDefinition = "TITLE_END")
    @FieldDes(name = "titleend", lenth = "100", type = "String", memo = "职称详情并")
    private String titleend;

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
    public String getProjecttype (){
        return projecttype;
    }
    public void setProjecttype (String projecttype){
        this.projecttype = projecttype;
    }

    /**
    *  
    */
    public String getApplicationno (){
        return applicationno;
    }
    public void setApplicationno (String applicationno){
        this.applicationno = applicationno;
    }

    /**
    *  
    */
    public String getProjectname (){
        return projectname;
    }
    public void setProjectname (String projectname){
        this.projectname = projectname;
    }

    /**
    *  
    */
    public String getProjectsource (){
        return projectsource;
    }
    public void setProjectsource (String projectsource){
        this.projectsource = projectsource;
    }

    /**
    *  
    */
    public String getProjectleader (){
        return projectleader;
    }
    public void setProjectleader (String projectleader){
        this.projectleader = projectleader;
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
    public String getYear (){
        return year;
    }
    public void setYear (String year){
        this.year = year;
    }

    /**
    *  
    */
    public BigDecimal getProjectfunds (){
        return projectfunds;
    }
    public void setProjectfunds (BigDecimal projectfunds){
        this.projectfunds = projectfunds;
    }

    /**
    *  
    */
    public BigDecimal getFinancialfunds (){
        return financialfunds;
    }
    public void setFinancialfunds (BigDecimal financialfunds){
        this.financialfunds = financialfunds;
    }

    /**
    *  
    */
    public String getImplementation (){
        return implementation;
    }
    public void setImplementation (String implementation){
        this.implementation = implementation;
    }

    /**
    *  
    */
    public String getPlanprojecttype (){
        return planprojecttype;
    }
    public void setPlanprojecttype (String planprojecttype){
        this.planprojecttype = planprojecttype;
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
    public Integer getSeq (){
        return seq;
    }
    public void setSeq (Integer seq){
        this.seq = seq;
    }

    /**
    *  
    */
    public String getDepartment (){
        return department;
    }
    public void setDepartment (String department){
        this.department = department;
    }

    /**
    *  
    */
    public String getIsshow (){
        return isshow;
    }
    public void setIsshow (String isshow){
        this.isshow = isshow;
    }

    /**
    *  业务
    */
    public String getGrantName (){
        return grantName;
    }
    public void setGrantName (String grantName){
        this.grantName = grantName;
    }

    /**
    *  专题
    */
    public String getSubjectName (){
        return subjectName;
    }
    public void setSubjectName (String subjectName){
        this.subjectName = subjectName;
    }

    /**
    *  主管处（科）室
    */
    public String getAdminOrgName (){
        return adminOrgName;
    }
    public void setAdminOrgName (String adminOrgName){
        this.adminOrgName = adminOrgName;
    }

    /**
    *  申报单位
    */
    public String getOrgName (){
        return orgName;
    }
    public void setOrgName (String orgName){
        this.orgName = orgName;
    }

    /**
    *  统一社会信用代码
    */
    public String getOrgShxyNo (){
        return orgShxyNo;
    }
    public void setOrgShxyNo (String orgShxyNo){
        this.orgShxyNo = orgShxyNo;
    }

    /**
    *  负责人身份证
    */
    public String getFzrCardCode (){
        return fzrCardCode;
    }
    public void setFzrCardCode (String fzrCardCode){
        this.fzrCardCode = fzrCardCode;
    }

    /**
    *  负责人职称信息
    */
    public String getProfTitleName (){
        return profTitleName;
    }
    public void setProfTitleName (String profTitleName){
        this.profTitleName = profTitleName;
    }

    /**
    *  负责人电话
    */
    public String getFzrMobile (){
        return fzrMobile;
    }
    public void setFzrMobile (String fzrMobile){
        this.fzrMobile = fzrMobile;
    }

    /**
    *  负责人邮箱
    */
    public String getFzrEmail (){
        return fzrEmail;
    }
    public void setFzrEmail (String fzrEmail){
        this.fzrEmail = fzrEmail;
    }

    /**
    *  合作单位
    */
    public String getHzOrgNames (){
        return hzOrgNames;
    }
    public void setHzOrgNames (String hzOrgNames){
        this.hzOrgNames = hzOrgNames;
    }

    /**
    *  立项金额
    */
    public String getAwardAmt (){
        return awardAmt;
    }
    public void setAwardAmt (String awardAmt){
        this.awardAmt = awardAmt;
    }

    /**
    *  合同状态
    */
    public String getIsQdCtr (){
        return isQdCtr;
    }
    public void setIsQdCtr (String isQdCtr){
        this.isQdCtr = isQdCtr;
    }

    /**
    *  合同到期时间
    */
    public Date getCtrEndDate (){
        return ctrEndDate;
    }
    public void setCtrEndDate (Date ctrEndDate){
        this.ctrEndDate = ctrEndDate;
    }

    /**
    *  验收状态
    */
    public String getAcceptanceStatus (){
        return acceptanceStatus;
    }
    public void setAcceptanceStatus (String acceptanceStatus){
        this.acceptanceStatus = acceptanceStatus;
    }

    /**
    *  验收时间
    */
    public Date getAcceptanceDate (){
        return acceptanceDate;
    }
    public void setAcceptanceDate (Date acceptanceDate){
        this.acceptanceDate = acceptanceDate;
    }

    /**
    *  验收形式
    */
    public String getAcceptanceNature (){
        return acceptanceNature;
    }
    public void setAcceptanceNature (String acceptanceNature){
        this.acceptanceNature = acceptanceNature;
    }

    /**
    *  验收结论
    */
    public String getActResult (){
        return actResult;
    }
    public void setActResult (String actResult){
        this.actResult = actResult;
    }

    /**
    *  专利申请
    */
    public Integer getZlApp (){
        return zlApp;
    }
    public void setZlApp (Integer zlApp){
        this.zlApp = zlApp;
    }

    /**
    *  专利授权
    */
    public Integer getZlAuth (){
        return zlAuth;
    }
    public void setZlAuth (Integer zlAuth){
        this.zlAuth = zlAuth;
    }

    /**
    *  省级以上奖项
    */
    public Integer getProvionceAboveAward (){
        return provionceAboveAward;
    }
    public void setProvionceAboveAward (Integer provionceAboveAward){
        this.provionceAboveAward = provionceAboveAward;
    }

    /**
    *  新产品
    */
    public Integer getNewProduct (){
        return newProduct;
    }
    public void setNewProduct (Integer newProduct){
        this.newProduct = newProduct;
    }

    /**
    *  新工艺
    */
    public Integer getNewGy (){
        return newGy;
    }
    public void setNewGy (Integer newGy){
        this.newGy = newGy;
    }

    /**
    *  引进人才
    */
    public Integer getImportPerson (){
        return importPerson;
    }
    public void setImportPerson (Integer importPerson){
        this.importPerson = importPerson;
    }

    /**
    *  培养人才
    */
    public Integer getTrainPerson (){
        return trainPerson;
    }
    public void setTrainPerson (Integer trainPerson){
        this.trainPerson = trainPerson;
    }

    /**
    *  技术标准
    */
    public Integer getTechnicalStandard (){
        return technicalStandard;
    }
    public void setTechnicalStandard (Integer technicalStandard){
        this.technicalStandard = technicalStandard;
    }

    /**
    *  论文论著
    */
    public Integer getPubicationNum (){
        return pubicationNum;
    }
    public void setPubicationNum (Integer pubicationNum){
        this.pubicationNum = pubicationNum;
    }

    /**
    *  科技报告
    */
    public Integer getTechnologyNum (){
        return technologyNum;
    }
    public void setTechnologyNum (Integer technologyNum){
        this.technologyNum = technologyNum;
    }

    /**
    *  新增销售收入
    */
    public String getNewSalesIncome (){
        return newSalesIncome;
    }
    public void setNewSalesIncome (String newSalesIncome){
        this.newSalesIncome = newSalesIncome;
    }

    /**
    *  新增利税
    */
    public String getNewTax (){
        return newTax;
    }
    public void setNewTax (String newTax){
        this.newTax = newTax;
    }

    /**
    *  推荐单位
    */
    public String getTjOrgName (){
        return tjOrgName;
    }
    public void setTjOrgName (String tjOrgName){
        this.tjOrgName = tjOrgName;
    }

    /**
    *  项目负责人所在单位
    */
    public String getPsnOrgName (){
        return psnOrgName;
    }
    public void setPsnOrgName (String psnOrgName){
        this.psnOrgName = psnOrgName;
    }

    /**
    *  开始时间
    */
    public Date getStartTime (){
        return startTime;
    }
    public void setStartTime (Date startTime){
        this.startTime = startTime;
    }

    /**
    *  结束时间
    */
    public Date getEndTime (){
        return endTime;
    }
    public void setEndTime (Date endTime){
        this.endTime = endTime;
    }

    /**
    *  性别
    */
    public String getSex (){
        return sex;
    }
    public void setSex (String sex){
        this.sex = sex;
    }

    /**
    *  单位所在省
    */
    public String getAreaProvince (){
        return areaProvince;
    }
    public void setAreaProvince (String areaProvince){
        this.areaProvince = areaProvince;
    }

    /**
    *  立项主键
    */
    public String getPrjCode (){
        return prjCode;
    }
    public void setPrjCode (String prjCode){
        this.prjCode = prjCode;
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
    *  数据更新时间
    */
    public Date getUpdateTime (){
        return updateTime;
    }
    public void setUpdateTime (Date updateTime){
        this.updateTime = updateTime;
    }

    /**
    *  学位
    */
    public String getDegree (){
        return degree;
    }
    public void setDegree (String degree){
        this.degree = degree;
    }

    /**
    *  项目摘要
    */
    public String getSummary (){
        return summary;
    }
    public void setSummary (String summary){
        this.summary = summary;
    }

    /**
    *  研究内容与关键技术
    */
    public String getDevelopContentTechnology (){
        return developContentTechnology;
    }
    public void setDevelopContentTechnology (String developContentTechnology){
        this.developContentTechnology = developContentTechnology;
    }

    /**
    *  
    */
    public String getDrbj (){
        return drbj;
    }
    public void setDrbj (String drbj){
        this.drbj = drbj;
    }

    /**
    *  
    */
    public String getMainleader (){
        return mainleader;
    }
    public void setMainleader (String mainleader){
        this.mainleader = mainleader;
    }

    /**
    *  计划名称其他
    */
    public String getPlanprojecttypeQt (){
        return planprojecttypeQt;
    }
    public void setPlanprojecttypeQt (String planprojecttypeQt){
        this.planprojecttypeQt = planprojecttypeQt;
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
    *  负责人证件类型
    */
    public String getCertificatename (){
        return certificatename;
    }
    public void setCertificatename (String certificatename){
        this.certificatename = certificatename;
    }

    /**
     * 职称详情一
     */
    public String getTitleDetailOne() {
        return titleDetailOne;
    }

    public void setTitleDetailOne(String titleDetailOne) {
        this.titleDetailOne = titleDetailOne;
    }

    /**
     * 职称详情二
     */
    public String getTitleDetailTwo() {
        return titleDetailTwo;
    }

    public void setTitleDetailTwo(String titleDetailTwo) {
        this.titleDetailTwo = titleDetailTwo;
    }

    public String getTitleend() {
        return titleend;
    }

    public void setTitleend(String titleend) {
        this.titleend = titleend;
    }
}