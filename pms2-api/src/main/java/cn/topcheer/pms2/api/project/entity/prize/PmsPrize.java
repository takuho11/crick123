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
 *  科技奖励类主表
 */
@Entity
@MainTable
@Table(name="PMS_PRIZE")
@ClassInfo(name = "科技奖励类主表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrize {

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
	 *  项目名称
	 */
	@ApiModelProperty("项目名称")
	@TableField("PROJECT_NAME")
	@Column(columnDefinition = "PROJECT_NAME")
	private String projectName;

	/**
     *  单位id(pms_enterprise)
     */
	@ApiModelProperty("单位id(pms_enterprise)")
	@TableField("ENTERPRISEID")
	@Column(columnDefinition = "ENTERPRISEID")
	private String enterpriseid;

	/**
     *  用户id(sys_user)
     */
	@ApiModelProperty("用户id(sys_user)")
	@TableField("DECLARANTID")
	@Column(columnDefinition = "DECLARANTID")
	private String declarantid;

	/**
     *  大批次id(pms_planproject)
     */
	@ApiModelProperty("大批次id(pms_planproject)")
	@TableField("PLANPROJECTID")
	@Column(columnDefinition = "PLANPROJECTID")
	private String planprojectid;

	/**
     *  小批次id(pms_planprojectbatch)
     */
	@ApiModelProperty("小批次id(pms_planprojectbatch)")
	@TableField("PLANPROJECTBATCHID")
	@Column(columnDefinition = "PLANPROJECTBATCHID")
	private String planprojectbatchid;

	/**
     *  流程状态
     */
	@ApiModelProperty("流程状态")
	@TableField("MINICURRENTSTATE")
	@Column(columnDefinition = "MINICURRENTSTATE")
	private String minicurrentstate;

	/**
     *  流程状态id
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
     *  申请编号
     */
	@ApiModelProperty("申请编号")
	@TableField("APPLICATIONNO")
	@Column(columnDefinition = "APPLICATIONNO")
	private String applicationno;

	/**
     *  推荐号
     */
	@ApiModelProperty("推荐号")
	@TableField("RECOMMENDNUMBER")
	@Column(columnDefinition = "RECOMMENDNUMBER")
	private String recommendnumber;

	/**
     *  推荐奖励等级
     */
	@ApiModelProperty("推荐奖励等级")
	@TableField("REWARDLEVEL")
	@Column(columnDefinition = "REWARDLEVEL")
	private String rewardlevel;

	/**
     *  推荐奖励类别
     */
	@ApiModelProperty("推荐奖励类别")
	@TableField("REWARDTYPE")
	@Column(columnDefinition = "REWARDTYPE")
	private String rewardtype;

	/**
     *  第一完成人
     */
	@ApiModelProperty("第一完成人")
	@TableField("PROJECTLEADER")
	@Column(columnDefinition = "PROJECTLEADER")
	private String projectleader;

	/**
     *  所有完成人
     */
	@ApiModelProperty("所有完成人")
	@TableField("PARTYALL")
	@Column(columnDefinition = "PARTYALL")
	private String partyall;

	/**
     *  第一完成单位
     */
	@ApiModelProperty("第一完成单位")
	@TableField("MAINORGANIZERS")
	@Column(columnDefinition = "MAINORGANIZERS")
	private String mainorganizers;

	/**
     *  所有完成单位
     */
	@ApiModelProperty("所有完成单位")
	@TableField("ENTERPRISEALL")
	@Column(columnDefinition = "ENTERPRISEALL")
	private String enterpriseall;

	/**
     *  提交时间
     */
	@ApiModelProperty("提交时间")
	@TableField("SUBMITDATE")
	@Column(columnDefinition = "SUBMITDATE")
	@FieldDes(name = "submitdate", type = "Date", memo = "提交时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date submitdate;

	/**
     *  开始时间
     */
	@ApiModelProperty("开始时间")
	@TableField("STARTDATE")
	@Column(columnDefinition = "STARTDATE")
	@FieldDes(name = "startdate", type = "Date", memo = "开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date startdate;

	/**
     *  结束时间
     */
	@ApiModelProperty("结束时间")
	@TableField("ENDDATE")
	@Column(columnDefinition = "ENDDATE")
	@FieldDes(name = "enddate", type = "Date", memo = "结束时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date enddate;

	/**
     *  推荐单位、专家退回
     */
	@ApiModelProperty("推荐单位、专家退回")
	@TableField("TJDWBACK")
	@Column(columnDefinition = "TJDWBACK")
	private String tjdwback;

	/**
     *  流程更新时间
     */
	@ApiModelProperty("流程更新时间")
	@TableField("FLOWPOINTUPDATETIME")
	@Column(columnDefinition = "FLOWPOINTUPDATETIME")
	@FieldDes(name = "flowpointupdatetime", type = "Date", memo = "流程更新时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date flowpointupdatetime;


	/**
     *  所属国民经济行业
     */
	@ApiModelProperty("所属国民经济行业")
	@TableField("INDUSTRY")
	@Column(columnDefinition = "INDUSTRY")
	private String industry;

	/**
     *  所属重点发展产业
     */
	@ApiModelProperty("所属重点发展产业")
	@TableField("INDUSTRIAL")
	@Column(columnDefinition = "INDUSTRIAL")
	private String industrial;

	/**
     *  任务来源
     */
	@ApiModelProperty("任务来源")
	@TableField("SOURCE")
	@Column(columnDefinition = "SOURCE")
	private String source;

	/**
     *  授权发明专利
     */
	@ApiModelProperty("授权发明专利")
	@TableField("PATENT")
	@Column(columnDefinition = "PATENT")
	@FieldDes(name = "patent", type = "Integer", memo = "授权发明专利")
	private Integer patent;

	/**
     *  授权的其他知识产权
     */
	@ApiModelProperty("授权的其他知识产权")
	@TableField("OTHERPATENT")
	@Column(columnDefinition = "OTHERPATENT")
	@FieldDes(name = "otherpatent", type = "Integer", memo = "授权的其他知识产权")
	private Integer otherpatent;

	/**
     *  具体计划、基金的名称和编号
     */
	@ApiModelProperty("具体计划、基金的名称和编号")
	@TableField("PLANTEXT")
	@Column(columnDefinition = "PLANTEXT")
	private String plantext;

	/**
     *  已提交的科技报告编号
     */
	@ApiModelProperty("已提交的科技报告编号")
	@TableField("NUMBERTEXT")
	@Column(columnDefinition = "NUMBERTEXT")
	private String numbertext;

	/**
     *  成果登记编号
     */
	@ApiModelProperty("成果登记编号")
	@TableField("ACHIEVEMENTNO")
	@Column(columnDefinition = "ACHIEVEMENTNO")
	private String achievementno;

	/**
     *  行评通过标记
     */
	@ApiModelProperty("行评通过标记")
	@TableField("HPFLAG")
	@Column(columnDefinition = "HPFLAG")
	private String hpflag;

	/**
     *  行评结果
     */
	@ApiModelProperty("行评结果")
	@TableField("HPRESULT")
	@Column(columnDefinition = "HPRESULT")
	private String hpresult;

	/**
     *  综评通过标记
     */
	@ApiModelProperty("综评通过标记")
	@TableField("ZPFLAG")
	@Column(columnDefinition = "ZPFLAG")
	private String zpflag;

	/**
     *  综评结果
     */
	@ApiModelProperty("综评结果")
	@TableField("ZPRESULT")
	@Column(columnDefinition = "ZPRESULT")
	private String zpresult;

	/**
     *  获奖时间
     */
	@ApiModelProperty("获奖时间")
	@TableField("AWARDSDATE")
	@Column(columnDefinition = "AWARDSDATE")
	@FieldDes(name = "awardsdate", type = "Date", memo = "获奖时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date awardsdate;

	/**
     *  综评通过所有完成人
     */
	@ApiModelProperty("综评通过所有完成人")
	@TableField("ZPPARTY")
	@Column(columnDefinition = "ZPPARTY")
	private String zpparty;

	/**
     *  综评通过所有完成单位
     */
	@ApiModelProperty("综评通过所有完成单位")
	@TableField("ZPENTERPRISE")
	@Column(columnDefinition = "ZPENTERPRISE")
	private String zpenterprise;

	/**
     *  水印
     */
	@ApiModelProperty("水印")
	@TableField("ISADDWATERMARK")
	@Column(columnDefinition = "ISADDWATERMARK")
	private String isaddwatermark;

	/**
     *  公示时间
     */
	@ApiModelProperty("公示时间")
	@TableField("GSDATE")
	@Column(columnDefinition = "GSDATE")
	@FieldDes(name = "gsdate", type = "Date", memo = "公示时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date gsdate;

	/**
	 *  推荐类型
	 */
	@ApiModelProperty("推荐类型")
	@TableField("RECOMMEND_TYPE")
	@Column(columnDefinition = "RECOMMEND_TYPE")
	private String recommendType;

	/**
	 *  项目密级
	 */
	@ApiModelProperty("项目密级")
	@TableField("SECRET_GRADE")
	@Column(columnDefinition = "SECRET_GRADE")
	private String secretGrade;

	/**
	 *  具体计划、基金的名称
	 */
	@ApiModelProperty("具体计划、基金的名称")
	@TableField("PLAN_TEXT_NAME")
	@Column(columnDefinition = "PLAN_TEXT_NAME")
	private String planTextName;

	/**
	 *  具体计划、基金的编号
	 */
	@ApiModelProperty("具体计划、基金的编号")
	@TableField("PLAN_TEXT_NUM")
	@Column(columnDefinition = "PLAN_TEXT_NUM")
	private String planTextNum;

	/**
	 *  学科/专业组
	 */
	@ApiModelProperty("学科/专业组")
	@TableField("TEAM_NAME")
	@Column(columnDefinition = "TEAM_NAME")
	private String teamName;

	/**
	 *  是否删除（0未删除，1已删除）
	 */
	@ApiModelProperty("是否删除（0未删除，1已删除）")
	@TableField("IS_DELETED")
	@Column(columnDefinition = "IS_DELETED")
	private Integer isDeleted;


	/**
	 *  一级推荐单位ID
	 */
	@ApiModelProperty("一级推荐单位ID")
	@TableField("CITYCASEMANAGEMENTID")
	@Column(columnDefinition = "CITYCASEMANAGEMENTID")
	private String citycasemanagementid;

	/**
	 *  一级推荐单位名称
	 */
	@ApiModelProperty("一级推荐单位名称")
	@TableField("CITYCASEMANAGEMENT")
	@Column(columnDefinition = "CITYCASEMANAGEMENT")
	private String citycasemanagement;


	/**
	 *  二级推荐单位ID
	 */
	@ApiModelProperty("二级推荐单位ID")
	@TableField("COUNTYCASEMANAGEMENTID")
	@Column(columnDefinition = "COUNTYCASEMANAGEMENTID")
	private String countycasemanagementid;

	/**
	 *  二级推荐单位名称
	 */
	@ApiModelProperty("二级推荐单位名称")
	@TableField("COUNTYCASEMANAGEMENT")
	@Column(columnDefinition = "COUNTYCASEMANAGEMENT")
	private String countycasemanagement;

	/**
	 *  联系人
	 */
	private String linkman;
	/**
	 *  联系人电话
	 */
	private String linkphone;
	/**
	 *  联系人邮箱
	 */
	private String linkemail;



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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	*  单位id(pms_enterprise)
	*/
	public String getEnterpriseid (){
		return enterpriseid;
	}
	public void setEnterpriseid (String enterpriseid){
		this.enterpriseid = enterpriseid;
	}

	/**
	*  用户id(sys_user)
	*/
	public String getDeclarantid (){
		return declarantid;
	}
	public void setDeclarantid (String declarantid){
		this.declarantid = declarantid;
	}

	/**
	*  大批次id(pms_planproject)
	*/
	public String getPlanprojectid (){
		return planprojectid;
	}
	public void setPlanprojectid (String planprojectid){
		this.planprojectid = planprojectid;
	}

	/**
	*  小批次id(pms_planprojectbatch)
	*/
	public String getPlanprojectbatchid (){
		return planprojectbatchid;
	}
	public void setPlanprojectbatchid (String planprojectbatchid){
		this.planprojectbatchid = planprojectbatchid;
	}

	/**
	*  流程状态
	*/
	public String getMinicurrentstate (){
		return minicurrentstate;
	}
	public void setMinicurrentstate (String minicurrentstate){
		this.minicurrentstate = minicurrentstate;
	}

	/**
	*  流程状态id
	*/
	public String getMinicurrentstateid (){
		return minicurrentstateid;
	}
	public void setMinicurrentstateid (String minicurrentstateid){
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
	*  申请编号
	*/
	public String getApplicationno (){
		return applicationno;
	}
	public void setApplicationno (String applicationno){
		this.applicationno = applicationno;
	}

	/**
	*  推荐号
	*/
	public String getRecommendnumber (){
		return recommendnumber;
	}
	public void setRecommendnumber (String recommendnumber){
		this.recommendnumber = recommendnumber;
	}

	/**
	*  推荐奖励等级
	*/
	public String getRewardlevel (){
		return rewardlevel;
	}
	public void setRewardlevel (String rewardlevel){
		this.rewardlevel = rewardlevel;
	}

	/**
	*  推荐奖励类别
	*/
	public String getRewardtype (){
		return rewardtype;
	}
	public void setRewardtype (String rewardtype){
		this.rewardtype = rewardtype;
	}

	/**
	*  第一完成人
	*/
	public String getProjectleader (){
		return projectleader;
	}
	public void setProjectleader (String projectleader){
		this.projectleader = projectleader;
	}

	/**
	*  所有完成人
	*/
	public String getPartyall (){
		return partyall;
	}
	public void setPartyall (String partyall){
		this.partyall = partyall;
	}

	/**
	*  第一完成单位
	*/
	public String getMainorganizers (){
		return mainorganizers;
	}
	public void setMainorganizers (String mainorganizers){
		this.mainorganizers = mainorganizers;
	}

	/**
	*  所有完成单位
	*/
	public String getEnterpriseall (){
		return enterpriseall;
	}
	public void setEnterpriseall (String enterpriseall){
		this.enterpriseall = enterpriseall;
	}

	/**
	*  提交时间
	*/
	public Date getSubmitdate (){
		return submitdate;
	}
	public void setSubmitdate (Date submitdate){
		this.submitdate = submitdate;
	}

	/**
	*  开始时间
	*/
	public Date getStartdate (){
		return startdate;
	}
	public void setStartdate (Date startdate){
		this.startdate = startdate;
	}

	/**
	*  结束时间
	*/
	public Date getEnddate (){
		return enddate;
	}
	public void setEnddate (Date enddate){
		this.enddate = enddate;
	}

	/**
	*  推荐单位、专家退回
	*/
	public String getTjdwback (){
		return tjdwback;
	}
	public void setTjdwback (String tjdwback){
		this.tjdwback = tjdwback;
	}

	/**
	*  流程更新时间
	*/
	public Date getFlowpointupdatetime (){
		return flowpointupdatetime;
	}
	public void setFlowpointupdatetime (Date flowpointupdatetime){
		this.flowpointupdatetime = flowpointupdatetime;
	}

	/**
	*  所属国民经济行业
	*/
	public String getIndustry (){
		return industry;
	}
	public void setIndustry (String industry){
		this.industry = industry;
	}

	/**
	*  所属重点发展产业
	*/
	public String getIndustrial (){
		return industrial;
	}
	public void setIndustrial (String industrial){
		this.industrial = industrial;
	}

	/**
	*  任务来源
	*/
	public String getSource (){
		return source;
	}
	public void setSource (String source){
		this.source = source;
	}

	/**
	*  授权发明专利
	*/
	public Integer getPatent (){
		return patent;
	}
	public void setPatent (Integer patent){
		this.patent = patent;
	}

	/**
	*  授权的其他知识产权
	*/
	public Integer getOtherpatent (){
		return otherpatent;
	}
	public void setOtherpatent (Integer otherpatent){
		this.otherpatent = otherpatent;
	}

	/**
	*  具体计划、基金的名称和编号
	*/
	public String getPlantext (){
		return plantext;
	}
	public void setPlantext (String plantext){
		this.plantext = plantext;
	}

	/**
	*  已提交的科技报告编号
	*/
	public String getNumbertext (){
		return numbertext;
	}
	public void setNumbertext (String numbertext){
		this.numbertext = numbertext;
	}

	/**
	*  成果登记编号
	*/
	public String getAchievementno (){
		return achievementno;
	}
	public void setAchievementno (String achievementno){
		this.achievementno = achievementno;
	}

	/**
	*  行评通过标记
	*/
	public String getHpflag (){
		return hpflag;
	}
	public void setHpflag (String hpflag){
		this.hpflag = hpflag;
	}

	/**
	*  行评结果
	*/
	public String getHpresult (){
		return hpresult;
	}
	public void setHpresult (String hpresult){
		this.hpresult = hpresult;
	}

	/**
	*  综评通过标记
	*/
	public String getZpflag (){
		return zpflag;
	}
	public void setZpflag (String zpflag){
		this.zpflag = zpflag;
	}

	/**
	*  综评结果
	*/
	public String getZpresult (){
		return zpresult;
	}
	public void setZpresult (String zpresult){
		this.zpresult = zpresult;
	}

	/**
	*  获奖时间
	*/
	public Date getAwardsdate (){
		return awardsdate;
	}
	public void setAwardsdate (Date awardsdate){
		this.awardsdate = awardsdate;
	}

	/**
	*  综评通过所有完成人
	*/
	public String getZpparty (){
		return zpparty;
	}
	public void setZpparty (String zpparty){
		this.zpparty = zpparty;
	}

	/**
	*  综评通过所有完成单位
	*/
	public String getZpenterprise (){
		return zpenterprise;
	}
	public void setZpenterprise (String zpenterprise){
		this.zpenterprise = zpenterprise;
	}

	/**
	*  水印
	*/
	public String getIsaddwatermark (){
		return isaddwatermark;
	}
	public void setIsaddwatermark (String isaddwatermark){
		this.isaddwatermark = isaddwatermark;
	}

	/**
	*  公示时间
	*/
	public Date getGsdate (){
		return gsdate;
	}
	public void setGsdate (Date gsdate){
		this.gsdate = gsdate;
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

	public String getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}

	public String getSecretGrade() {
		return secretGrade;
	}

	public void setSecretGrade(String secretGrade) {
		this.secretGrade = secretGrade;
	}

	public String getPlanTextName() {
		return planTextName;
	}

	public void setPlanTextName(String planTextName) {
		this.planTextName = planTextName;
	}

	public String getPlanTextNum() {
		return planTextNum;
	}

	public void setPlanTextNum(String planTextNum) {
		this.planTextNum = planTextNum;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getCitycasemanagementid() {
		return citycasemanagementid;
	}

	public void setCitycasemanagementid(String citycasemanagementid) {
		this.citycasemanagementid = citycasemanagementid;
	}

	public String getCitycasemanagement() {
		return citycasemanagement;
	}

	public void setCitycasemanagement(String citycasemanagement) {
		this.citycasemanagement = citycasemanagement;
	}

	public String getCountycasemanagementid() {
		return countycasemanagementid;
	}

	public void setCountycasemanagementid(String countycasemanagementid) {
		this.countycasemanagementid = countycasemanagementid;
	}

	public String getCountycasemanagement() {
		return countycasemanagement;
	}

	public void setCountycasemanagement(String countycasemanagement) {
		this.countycasemanagement = countycasemanagement;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkphone() {
		return linkphone;
	}

	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}

	public String getLinkemail() {
		return linkemail;
	}

	public void setLinkemail(String linkemail) {
		this.linkemail = linkemail;
	}
}
