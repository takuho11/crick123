/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2024-1-4 9:30:14
 */
package cn.topcheer.pms2.api.project.entity.talent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.annotation.MainTable;
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
 * 科技人才类主表
 */
@Entity
@MainTable
@Table(name = "PMS_TALENT")
@ClassInfo(name = "科技人才类主表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalent {

    /**
     * 固定字段:唯一标识
     */
    @ApiModelProperty("固定字段:唯一标识")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
    private String id;

    /**
     * 固定字段:数据类型
     */
    @ApiModelProperty("固定字段:数据类型")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    private String type;


    /**
     * 固定字段:第一次保存时间
     */
    @ApiModelProperty("固定字段:第一次保存时间")
    @TableField("SAVEDATE")
    @Column(columnDefinition = "SAVEDATE")
    @FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;

    /**
     * 固定字段:每次更新数据时间
     */
    @ApiModelProperty("固定字段:每次更新数据时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;


    /**
     * 单位id(pms_enterprise)
     */
    @ApiModelProperty("单位id(pms_enterprise)")
    @TableField("ENTERPRISEID")
    @Column(columnDefinition = "ENTERPRISEID")
    private String enterpriseid;

    /**
     * 用户id(sys_user)
     */
    @ApiModelProperty("用户id(sys_user)")
    @TableField("DECLARANTID")
    @Column(columnDefinition = "DECLARANTID")
    private String declarantid;

    /**
     * 大批次id(pms_planproject)
     */
    @ApiModelProperty("大批次id(pms_planproject)")
    @TableField("PLANPROJECTID")
    @Column(columnDefinition = "PLANPROJECTID")
    private String planprojectid;

    /**
     * 小批次id(pms_planprojectbatch)
     */
    @ApiModelProperty("小批次id(pms_planprojectbatch)")
    @TableField("PLANPROJECTBATCHID")
    @Column(columnDefinition = "PLANPROJECTBATCHID")
    private String planprojectbatchid;

    /**
     * 流程状态
     */
    @ApiModelProperty("流程状态")
    @TableField("MINICURRENTSTATE")
    @Column(columnDefinition = "MINICURRENTSTATE")
    private String minicurrentstate;

    /**
     * 流程状态id
     */
    @ApiModelProperty("流程状态id")
    @TableField("MINICURRENTSTATEID")
    @Column(columnDefinition = "MINICURRENTSTATEID")
    private String minicurrentstateid;

    /**
     *  流程节点定义key
     */
    @ApiModelProperty("流程节点定义key")
    @TableField("MINI_CURRENT_TASK_DEF_KEY")
    @Column(columnDefinition = "MINI_CURRENT_TASK_DEF_KEY")
    private String miniCurrentTaskDefKey;

    /**
     *  流程定义key
     */
    @ApiModelProperty("流程定义key")
    @TableField("MINI_CURRENT_PROCESS_DEF_KEY")
    @Column(columnDefinition = "MINI_CURRENT_PROCESS_DEF_KEY")
    private String miniCurrentProcessDefKey;

    /**
     * 申请编号
     */
    @ApiModelProperty("申请编号")
    @TableField("APPLICATIONNO")
    @Column(columnDefinition = "APPLICATIONNO")
    private String applicationno;

    /**
     * 项目名称
     */
    @ApiModelProperty("项目名称")
    @TableField("PROJECTNAME")
    @Column(columnDefinition = "PROJECTNAME")
    private String projectname;

    /**
     * 计划类别
     */
    @ApiModelProperty("计划类别")
    @TableField("PLANPROJECTTYPE")
    @Column(columnDefinition = "PLANPROJECTTYPE")
    private String planprojecttype;

    /**
     * 负责人
     */
    @ApiModelProperty("负责人")
    @TableField("PROJECTLEADER")
    @Column(columnDefinition = "PROJECTLEADER")
    private String projectleader;

    /**
     * 承担单位
     */
    @ApiModelProperty("承担单位")
    @TableField("MAINORGANIZERS")
    @Column(columnDefinition = "MAINORGANIZERS")
    private String mainorganizers;

    /**
     * 提交时间
     */
    @ApiModelProperty("提交时间")
    @TableField("SUBMITDATE")
    @Column(columnDefinition = "SUBMITDATE")
    @FieldDes(name = "submitdate", type = "Date", memo = "提交时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date submitdate;

    /**
     * 处室id
     */
    @ApiModelProperty("处室id")
    @TableField("BELONGLABID")
    @Column(columnDefinition = "BELONGLABID")
    private String belonglabid;

    /**
     * 处室名称
     */
    @ApiModelProperty("处室名称")
    @TableField("BELONGLAB")
    @Column(columnDefinition = "BELONGLAB")
    private String belonglab;

    /**
     * 推荐单位退回
     */
    @ApiModelProperty("推荐单位退回")
    @TableField("TJDWBACK")
    @Column(columnDefinition = "TJDWBACK")
    private String tjdwback;

    /**
     * 一直允许上报或审核
     */
    @ApiModelProperty("一直允许上报或审核")
    @TableField("ALLOWSBSH")
    @Column(columnDefinition = "ALLOWSBSH")
    private String allowsbsh;

    /**
     * 流程更新时间
     */
    @ApiModelProperty("流程更新时间")
    @TableField("FLOWPOINTUPDATETIME")
    @Column(columnDefinition = "FLOWPOINTUPDATETIME")
    @FieldDes(name = "flowpointupdatetime", type = "Date", memo = "流程更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date flowpointupdatetime;

    /**
     * 一级推荐单位id
     */
    @ApiModelProperty("一级推荐单位id")
    @TableField("CITYCASEMANAGEMENTID")
    @Column(columnDefinition = "CITYCASEMANAGEMENTID")
    private String citycasemanagementid;

    /**
     * 一级推荐单位名称
     */
    @ApiModelProperty("一级推荐单位名称")
    @TableField("CITYCASEMANAGEMENT")
    @Column(columnDefinition = "CITYCASEMANAGEMENT")
    private String citycasemanagement;

    /**
     * 二级推荐单位id
     */
    @ApiModelProperty("二级推荐单位id")
    @TableField("COUNTYCASEMANAGEMENTID")
    @Column(columnDefinition = "COUNTYCASEMANAGEMENTID")
    private String countycasemanagementid;

    /**
     * 二级推荐单位名称
     */
    @ApiModelProperty("二级推荐单位名称")
    @TableField("COUNTYCASEMANAGEMENT")
    @Column(columnDefinition = "COUNTYCASEMANAGEMENT")
    private String countycasemanagement;

    /**
     * 推荐单位性质
     */
    @ApiModelProperty("推荐单位性质")
    @TableField("CASEMANAGEMENTNATURE")
    @Column(columnDefinition = "CASEMANAGEMENTNATURE")
    private String casemanagementnature;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @TableField("STARTDATE")
    @Column(columnDefinition = "STARTDATE")
    @FieldDes(name = "startdate", type = "Date", memo = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date startdate;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    @TableField("ENDDATE")
    @Column(columnDefinition = "ENDDATE")
    @FieldDes(name = "enddate", type = "Date", memo = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date enddate;

    /**
     * 自筹经费
     */
    @ApiModelProperty("自筹经费")
    @TableField("PROJECTSUMFORSELF")
    @Column(columnDefinition = "PROJECTSUMFORSELF")
    @FieldDes(name = "projectsumforself", type = "BigDecimal", memo = "自筹经费")
    private BigDecimal projectsumforself;

    /**
     * 省拨经费
     */
    @ApiModelProperty("省拨经费")
    @TableField("PROJECTSUMFORGOV")
    @Column(columnDefinition = "PROJECTSUMFORGOV")
    @FieldDes(name = "projectsumforgov", type = "BigDecimal", memo = "省拨经费")
    private BigDecimal projectsumforgov;

    /**
     * 其他经费
     */
    @ApiModelProperty("其他经费")
    @TableField("PROJECTSUMFOROTHER")
    @Column(columnDefinition = "PROJECTSUMFOROTHER")
    @FieldDes(name = "projectsumforother", type = "BigDecimal", memo = "其他经费")
    private BigDecimal projectsumforother;

    /**
     * 总经费
     */
    @ApiModelProperty("总经费")
    @TableField("PROJECTSUMTOTAL")
    @Column(columnDefinition = "PROJECTSUMTOTAL")
    @FieldDes(name = "projectsumtotal", type = "BigDecimal", memo = "总经费")
    private BigDecimal projectsumtotal;

    /**
     * 申报种类
     */
    @ApiModelProperty("申报种类")
    @TableField("PROJECTAPPLYTYPE")
    @Column(columnDefinition = "PROJECTAPPLYTYPE")
    private String projectapplytype;

    /**
     * 申报、项目类型
     */
    @ApiModelProperty("申报、项目类型")
    @TableField("PROJECTTYPE")
    @Column(columnDefinition = "PROJECTTYPE")
    private String projecttype;

    /**
     * 所属类别
     */
    @ApiModelProperty("所属类别")
    @TableField("CATEGORY")
    @Column(columnDefinition = "CATEGORY")
    private String category;

    /**
     * 水印
     */
    @ApiModelProperty("水印")
    @TableField("ISADDWATERMARK")
    @Column(columnDefinition = "ISADDWATERMARK")
    private String isaddwatermark;

    /**
     * 合同编号
     */
    @ApiModelProperty("合同编号")
    @TableField("CONTRACTNO")
    @Column(columnDefinition = "CONTRACTNO")
    private String contractno;

    /**
     * 支持方向id
     */
    @ApiModelProperty("支持方向id")
    @TableField("SUPPORTDIRECTIONID")
    @Column(columnDefinition = "SUPPORTDIRECTIONID")
    private String supportdirectionid;

    /**
     * 支持方向
     */
    @ApiModelProperty("支持方向")
    @TableField("SUPPORTDIRECTION")
    @Column(columnDefinition = "SUPPORTDIRECTION")
    private String supportdirection;

    /**
     * 立项标记
     */
    @ApiModelProperty("立项标记")
    @TableField("LXFLAG")
    @Column(columnDefinition = "LXFLAG")
    private String lxflag;

    /**
     * 公示时间
     */
    @ApiModelProperty("公示时间")
    @TableField("GSDATE")
    @Column(columnDefinition = "GSDATE")
    @FieldDes(name = "gsdate", type = "Date", memo = "公示时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date gsdate;

    /**
     * 申请人
     */
    @ApiModelProperty("申请人")
    @TableField("APPLICATION_PERSON")
    @Column(columnDefinition = "APPLICATION_PERSON")
    private String applicationPerson;

    /**
     * 申报批次
     */
    @ApiModelProperty("申报批次")
    @TableField("APPLICATION_GRADE")
    @Column(columnDefinition = "APPLICATION_GRADE")
    private String applicationGrade;

    /**
     * 专业领域
     */
    @ApiModelProperty("专业领域")
    @TableField("PROFESSIONAL_FIELD")
    @Column(columnDefinition = "PROFESSIONAL_FIELD")
    private String professionalField;

    /**
     * 专业技术职务
     */
    @ApiModelProperty("专业技术职务")
    @TableField("PROFESSIONAL_TECH_POSITION")
    @Column(columnDefinition = "PROFESSIONAL_TECH_POSITION")
    private String professionalTechPosition;

    /**
     * 依托单位
     */
    @ApiModelProperty("依托单位")
    @TableField("RELY_ENTERPRISE")
    @Column(columnDefinition = "RELY_ENTERPRISE")
    private String relyEnterprise;

    /**
     * 填报日期
     */
    @ApiModelProperty("填报日期")
    @TableField("FILL_DATE")
    @Column(columnDefinition = "FILL_DATE")
    @FieldDes(name = "fill_date", type = "Date", memo = "填报日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date fillDate;

    /**
     * 推荐单位意见
     */
    @ApiModelProperty("推荐单位意见")
    @TableField("OPINION")
    @Column(columnDefinition = "OPINION")
    private String opinion;

    /**
     * 符合申报条件情况
     */
    @ApiModelProperty("符合申报条件情况")
    @TableField("CONFORM_SITUATION")
    @Column(columnDefinition = "CONFORM_SITUATION")
    private String conformSituation;

    /**
     * 符合本年度引才目录情况
     */
    @ApiModelProperty("符合本年度引才目录情况")
    @TableField("CONFORM_MENU_SITUATION")
    @Column(columnDefinition = "CONFORM_MENU_SITUATION")
    private String conformMenuSituation;

    /**
     * 申报引才目录之外，其他领域人才的理由
     */
    @ApiModelProperty("申报引才目录之外，其他领域人才的理由")
    @TableField("APPLICATION_REASON")
    @Column(columnDefinition = "APPLICATION_REASON")
    private String applicationReason;

    /**
     * 是否申请“一事一议”
     */
    @ApiModelProperty("是否申请“一事一议”")
    @TableField("IS_APPLICATION")
    @Column(columnDefinition = "IS_APPLICATION")
    private String isApplication;

    /**
     * 申报人才层次
     */
    @ApiModelProperty("申报人才层次”")
    @TableField("APPLICANT_TIER")
    @Column(columnDefinition = "APPLICANT_TIER")
    private String applicantTier;

    /**
     * 是否删除（0未删除，1已删除）
     */
    @ApiModelProperty("是否删除（0未删除，1已删除）")
    @TableField("IS_DELETED")
    @Column(columnDefinition = "IS_DELETED")
    private Integer isDeleted;

    /**
     * 人才/工作站类别
     */
    @ApiModelProperty("人才/工作站类别”")
    @TableField("TALENT_TYPE")
    @Column(columnDefinition = "TALENT_TYPE")
    private String talentType;

    /**
     * 是否市(州)级工作站
     */
    @ApiModelProperty("是否市(州)级工作站”")
    @TableField("IS_CITY_PLATFORM")
    @Column(columnDefinition = "IS_CITY_PLATFORM")
    private String isCityPlatform;

    /**
     * 建站时间
     */
    @ApiModelProperty("建站时间”")
    @TableField("PLATFORM_ESTABLISHED")
    @Column(columnDefinition = "PLATFORM_ESTABLISHED")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date platformEstablished;

    /**
     * 承建单位为工作站3年内拟投入研发经费
     */
    @ApiModelProperty("承建单位为工作站3年内拟投入研发经费”")
    @TableField("FUNDING_RD")
    @Column(columnDefinition = "FUNDING_RD")
    private String fundingRd;

    @ApiModelProperty("符合申请奖励的贡献描述(限500字)”")
    @TableField("APPLY_FOR_REWARD_CONDITION_DESC")
    @Column(columnDefinition = "APPLY_FOR_REWARD_CONDITION_DESC")
    private String applyForRewardConditionDesc;


    @ApiModelProperty("已取得科技创新成果概况(限500字)")
    @TableField("GAIN_TECHNOLOGICAL_INNOVATION_ACHIEVEMENT_OVERVIEW")
    @Column(columnDefinition = "GAIN_TECHNOLOGICAL_INNOVATION_ACHIEVEMENT_OVERVIEW")
    private String gainTechnologicalInnovationAchievementOverview;

    @ApiModelProperty("申请经济贡献奖励")
    @TableField("APPLYING_FOR_ECONOMIC_CONTRIBUTION_REWARDS")
    @Column(columnDefinition = "APPLYING_FOR_ECONOMIC_CONTRIBUTION_REWARDS")
    private String applyingForEconomicContributionRewards;

    @ApiModelProperty("申请科研贡献奖励")
    @TableField("APPLYING_FOR_SCIENTIFIC_RESEARCH_CONTRIBUTION_REWARDS")
    @Column(columnDefinition = "APPLYING_FOR_SCIENTIFIC_RESEARCH_CONTRIBUTION_REWARDS")
    private String applyingForScientificResearchContributionRewards;

    @ApiModelProperty("企业当年给予申报人研发成果转化后的奖励和报酬")
    @TableField("ENTERPRISE_CURRENT_YEAR_REWARDS_AND_REMUNERATION")
    @Column(columnDefinition = "ENTERPRISE_CURRENT_YEAR_REWARDS_AND_REMUNERATION")
    private String enterpriseCurrentYearRewardsAndRemuneration;

    @ApiModelProperty("申报人当年所得研发成果转化后的奖励和报酬")
    @TableField("DECLARER_CURRENT_YEAR_REWARDS_AND_REMUNERATION")
    @Column(columnDefinition = "DECLARER_CURRENT_YEAR_REWARDS_AND_REMUNERATION")
    private String declarerCurrentYearRewardsAndRemuneration;


    @ApiModelProperty("符合科研贡献奖励的条件")
    @TableField("SCIENTIFIC_RESEARCH_CONTRIBUTION_REWARDS")
    @Column(columnDefinition = "SCIENTIFIC_RESEARCH_CONTRIBUTION_REWARDS")
    private String scientificResearchContributionRewards;

    @ApiModelProperty("申报经济贡献奖励的研发成果转化情况描述")
    @TableField("DESCRIPTION_AWARDWINNING_ACHIEVEMENTS_OR_PATENTS")
    @Column(columnDefinition = "DESCRIPTION_AWARDWINNING_ACHIEVEMENTS_OR_PATENTS")
    private String descriptionAwardwinningAchievementsOrPatents;


    /**
     * 固定字段:唯一标识
     */
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSavedate() {
        return savedate;
    }

    public void setSavedate(Date savedate) {
        this.savedate = savedate;
    }

    public Date getUpdatelasttime() {
        return updatelasttime;
    }

    public void setUpdatelasttime(Date updatelasttime) {
        this.updatelasttime = updatelasttime;
    }

    /**
     * 单位id(pms_enterprise)
     */
    public String getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(String enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    /**
     * 用户id(sys_user)
     */
    public String getDeclarantid() {
        return declarantid;
    }

    public void setDeclarantid(String declarantid) {
        this.declarantid = declarantid;
    }

    /**
     * 大批次id(pms_planproject)
     */
    public String getPlanprojectid() {
        return planprojectid;
    }

    public void setPlanprojectid(String planprojectid) {
        this.planprojectid = planprojectid;
    }

    /**
     * 小批次id(pms_planprojectbatch)
     */
    public String getPlanprojectbatchid() {
        return planprojectbatchid;
    }

    public void setPlanprojectbatchid(String planprojectbatchid) {
        this.planprojectbatchid = planprojectbatchid;
    }

    /**
     * 流程状态
     */
    public String getMinicurrentstate() {
        return minicurrentstate;
    }

    public void setMinicurrentstate(String minicurrentstate) {
        this.minicurrentstate = minicurrentstate;
    }

    /**
     * 流程状态id
     */
    public String getMinicurrentstateid() {
        return minicurrentstateid;
    }

    public void setMinicurrentstateid(String minicurrentstateid) {
        this.minicurrentstateid = minicurrentstateid;
    }

    public String getMiniCurrentTaskDefKey() {
        return miniCurrentTaskDefKey;
    }

    public void setMiniCurrentTaskDefKey(String miniCurrentTaskDefKey) {
        this.miniCurrentTaskDefKey = miniCurrentTaskDefKey;
    }

    public String getMiniCurrentProcessDefKey() {
        return miniCurrentProcessDefKey;
    }

    public void setMiniCurrentProcessDefKey(String miniCurrentProcessDefKey) {
        this.miniCurrentProcessDefKey = miniCurrentProcessDefKey;
    }

    /**
     * 申请编号
     */
    public String getApplicationno() {
        return applicationno;
    }

    public void setApplicationno(String applicationno) {
        this.applicationno = applicationno;
    }

    /**
     * 项目名称
     */
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    /**
     * 计划类别
     */
    public String getPlanprojecttype() {
        return planprojecttype;
    }

    public void setPlanprojecttype(String planprojecttype) {
        this.planprojecttype = planprojecttype;
    }

    /**
     * 负责人
     */
    public String getProjectleader() {
        return projectleader;
    }

    public void setProjectleader(String projectleader) {
        this.projectleader = projectleader;
    }

    /**
     * 承担单位
     */
    public String getMainorganizers() {
        return mainorganizers;
    }

    public void setMainorganizers(String mainorganizers) {
        this.mainorganizers = mainorganizers;
    }

    /**
     * 提交时间
     */
    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    /**
     * 处室id
     */
    public String getBelonglabid() {
        return belonglabid;
    }

    public void setBelonglabid(String belonglabid) {
        this.belonglabid = belonglabid;
    }

    /**
     * 处室名称
     */
    public String getBelonglab() {
        return belonglab;
    }

    public void setBelonglab(String belonglab) {
        this.belonglab = belonglab;
    }

    /**
     * 推荐单位退回
     */
    public String getTjdwback() {
        return tjdwback;
    }

    public void setTjdwback(String tjdwback) {
        this.tjdwback = tjdwback;
    }

    /**
     * 一直允许上报或审核
     */
    public String getAllowsbsh() {
        return allowsbsh;
    }

    public void setAllowsbsh(String allowsbsh) {
        this.allowsbsh = allowsbsh;
    }

    /**
     * 流程更新时间
     */
    public Date getFlowpointupdatetime() {
        return flowpointupdatetime;
    }

    public void setFlowpointupdatetime(Date flowpointupdatetime) {
        this.flowpointupdatetime = flowpointupdatetime;
    }

    /**
     * 一级推荐单位id
     */
    public String getCitycasemanagementid() {
        return citycasemanagementid;
    }

    public void setCitycasemanagementid(String citycasemanagementid) {
        this.citycasemanagementid = citycasemanagementid;
    }

    /**
     * 一级推荐单位名称
     */
    public String getCitycasemanagement() {
        return citycasemanagement;
    }

    public void setCitycasemanagement(String citycasemanagement) {
        this.citycasemanagement = citycasemanagement;
    }

    /**
     * 二级推荐单位id
     */
    public String getCountycasemanagementid() {
        return countycasemanagementid;
    }

    public void setCountycasemanagementid(String countycasemanagementid) {
        this.countycasemanagementid = countycasemanagementid;
    }

    /**
     * 二级推荐单位名称
     */
    public String getCountycasemanagement() {
        return countycasemanagement;
    }

    public void setCountycasemanagement(String countycasemanagement) {
        this.countycasemanagement = countycasemanagement;
    }

    /**
     * 推荐单位性质
     */
    public String getCasemanagementnature() {
        return casemanagementnature;
    }

    public void setCasemanagementnature(String casemanagementnature) {
        this.casemanagementnature = casemanagementnature;
    }

    /**
     * 开始时间
     */
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 结束时间
     */
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 自筹经费
     */
    public BigDecimal getProjectsumforself() {
        return projectsumforself;
    }

    public void setProjectsumforself(BigDecimal projectsumforself) {
        this.projectsumforself = projectsumforself;
    }

    /**
     * 省拨经费
     */
    public BigDecimal getProjectsumforgov() {
        return projectsumforgov;
    }

    public void setProjectsumforgov(BigDecimal projectsumforgov) {
        this.projectsumforgov = projectsumforgov;
    }

    /**
     * 其他经费
     */
    public BigDecimal getProjectsumforother() {
        return projectsumforother;
    }

    public void setProjectsumforother(BigDecimal projectsumforother) {
        this.projectsumforother = projectsumforother;
    }

    /**
     * 总经费
     */
    public BigDecimal getProjectsumtotal() {
        return projectsumtotal;
    }

    public void setProjectsumtotal(BigDecimal projectsumtotal) {
        this.projectsumtotal = projectsumtotal;
    }

    /**
     * 申报种类
     */
    public String getProjectapplytype() {
        return projectapplytype;
    }

    public void setProjectapplytype(String projectapplytype) {
        this.projectapplytype = projectapplytype;
    }

    /**
     * 申报、项目类型
     */
    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    /**
     * 所属类别
     */
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 水印
     */
    public String getIsaddwatermark() {
        return isaddwatermark;
    }

    public void setIsaddwatermark(String isaddwatermark) {
        this.isaddwatermark = isaddwatermark;
    }

    /**
     * 合同编号
     */
    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    /**
     * 支持方向id
     */
    public String getSupportdirectionid() {
        return supportdirectionid;
    }

    public void setSupportdirectionid(String supportdirectionid) {
        this.supportdirectionid = supportdirectionid;
    }

    /**
     * 支持方向
     */
    public String getSupportdirection() {
        return supportdirection;
    }

    public void setSupportdirection(String supportdirection) {
        this.supportdirection = supportdirection;
    }

    /**
     * 立项标记
     */
    public String getLxflag() {
        return lxflag;
    }

    public void setLxflag(String lxflag) {
        this.lxflag = lxflag;
    }

    /**
     * 公示时间
     */
    public Date getGsdate() {
        return gsdate;
    }

    public void setGsdate(Date gsdate) {
        this.gsdate = gsdate;
    }

    /**
     * 申请人
     */
    public String getApplicationPerson() {
        return applicationPerson;
    }

    public void setApplicationPerson(String applicationPerson) {
        this.applicationPerson = applicationPerson;
    }

    /**
     * 申报批次
     */
    public String getApplicationGrade() {
        return applicationGrade;
    }

    public void setApplicationGrade(String applicationGrade) {
        this.applicationGrade = applicationGrade;
    }

    /**
     * 专业领域
     */
    public String getProfessionalField() {
        return professionalField;
    }

    public void setProfessionalField(String professionalField) {
        this.professionalField = professionalField;
    }

    /**
     * 专业技术职务
     */
    public String getProfessionalTechPosition() {
        return professionalTechPosition;
    }

    public void setProfessionalTechPosition(String professionalTechPosition) {
        this.professionalTechPosition = professionalTechPosition;
    }

    /**
     * 依托单位
     */
    public String getRelyEnterprise() {
        return relyEnterprise;
    }

    public void setRelyEnterprise(String relyEnterprise) {
        this.relyEnterprise = relyEnterprise;
    }

    /**
     * 填报日期
     */
    public Date getFillDate() {
        return fillDate;
    }

    public void setFillDate(Date fillDate) {
        this.fillDate = fillDate;
    }

    /**
     * 推荐单位意见
     */
    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    /**
     * 符合申报条件情况
     */
    public String getConformSituation() {
        return conformSituation;
    }

    public void setConformSituation(String conformSituation) {
        this.conformSituation = conformSituation;
    }

    /**
     * 符合本年度引才目录情况
     */
    public String getConformMenuSituation() {
        return conformMenuSituation;
    }

    public void setConformMenuSituation(String conformMenuSituation) {
        this.conformMenuSituation = conformMenuSituation;
    }

    /**
     * 申报引才目录之外，其他领域人才的理由
     */
    public String getApplicationReason() {
        return applicationReason;
    }

    public void setApplicationReason(String applicationReason) {
        this.applicationReason = applicationReason;
    }

    /**
     * 是否申请“一事一议”
     */
    public String getIsApplication() {
        return isApplication;
    }

    public void setIsApplication(String isApplication) {
        this.isApplication = isApplication;
    }

    public String getApplicantTier() {
        return applicantTier;
    }

    public void setApplicantTier(String applicantTier) {
        this.applicantTier = applicantTier;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTalentType() {
        return talentType;
    }

    public void setTalentType(String talentType) {
        this.talentType = talentType;
    }

    public String getIsCityPlatform() {
        return isCityPlatform;
    }

    public void setIsCityPlatform(String isCityPlatform) {
        this.isCityPlatform = isCityPlatform;
    }

    public Date getPlatformEstablished() {
        return platformEstablished;
    }

    public void setPlatformEstablished(Date platformEstablished) {
        this.platformEstablished = platformEstablished;
    }

    public String getFundingRd() {
        return fundingRd;
    }

    public void setFundingRd(String fundingRd) {
        this.fundingRd = fundingRd;
    }

    public String getApplyForRewardConditionDesc() {
        return applyForRewardConditionDesc;
    }

    public void setApplyForRewardConditionDesc(String applyForRewardConditionDesc) {
        this.applyForRewardConditionDesc = applyForRewardConditionDesc;
    }

    public String getGainTechnologicalInnovationAchievementOverview() {
        return gainTechnologicalInnovationAchievementOverview;
    }

    public void setGainTechnologicalInnovationAchievementOverview(String gainTechnologicalInnovationAchievementOverview) {
        this.gainTechnologicalInnovationAchievementOverview = gainTechnologicalInnovationAchievementOverview;
    }

    public String getApplyingForEconomicContributionRewards() {
        return applyingForEconomicContributionRewards;
    }

    public void setApplyingForEconomicContributionRewards(String applyingForEconomicContributionRewards) {
        this.applyingForEconomicContributionRewards = applyingForEconomicContributionRewards;
    }

    public String getApplyingForScientificResearchContributionRewards() {
        return applyingForScientificResearchContributionRewards;
    }

    public void setApplyingForScientificResearchContributionRewards(String applyingForScientificResearchContributionRewards) {
        this.applyingForScientificResearchContributionRewards = applyingForScientificResearchContributionRewards;
    }

    public String getEnterpriseCurrentYearRewardsAndRemuneration() {
        return enterpriseCurrentYearRewardsAndRemuneration;
    }

    public void setEnterpriseCurrentYearRewardsAndRemuneration(String enterpriseCurrentYearRewardsAndRemuneration) {
        this.enterpriseCurrentYearRewardsAndRemuneration = enterpriseCurrentYearRewardsAndRemuneration;
    }

    public String getDeclarerCurrentYearRewardsAndRemuneration() {
        return declarerCurrentYearRewardsAndRemuneration;
    }

    public void setDeclarerCurrentYearRewardsAndRemuneration(String declarerCurrentYearRewardsAndRemuneration) {
        this.declarerCurrentYearRewardsAndRemuneration = declarerCurrentYearRewardsAndRemuneration;
    }

    public String getScientificResearchContributionRewards() {
        return scientificResearchContributionRewards;
    }

    public void setScientificResearchContributionRewards(String scientificResearchContributionRewards) {
        this.scientificResearchContributionRewards = scientificResearchContributionRewards;
    }

    public String getDescriptionAwardwinningAchievementsOrPatents() {
        return descriptionAwardwinningAchievementsOrPatents;
    }

    public void setDescriptionAwardwinningAchievementsOrPatents(String descriptionAwardwinningAchievementsOrPatents) {
        this.descriptionAwardwinningAchievementsOrPatents = descriptionAwardwinningAchievementsOrPatents;
    }
}
