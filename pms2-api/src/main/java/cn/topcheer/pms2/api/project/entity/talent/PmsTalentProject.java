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
 *  科技人才-项目表
 */
@Entity
@Table(name="PMS_TALENT_PROJECT")
@ClassInfo(name = "科技人才-项目表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentProject {

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
     *  项目名称
     */
	@ApiModelProperty("项目名称")
	@TableField("NAME")
	@Column(columnDefinition = "NAME")
	private String name;

	/**
     *  项目类别
     */
	@ApiModelProperty("项目类别")
	@TableField("PROJECTTYPE")
	@Column(columnDefinition = "PROJECTTYPE")
	private String projecttype;

	/**
     *  项目编号
     */
	@ApiModelProperty("项目编号")
	@TableField("APPLICATIONNO")
	@Column(columnDefinition = "APPLICATIONNO")
	private String applicationno;

	/**
     *  开始时间
     */
	@ApiModelProperty("开始时间")
	@TableField("STARTDATE")
	@Column(columnDefinition = "STARTDATE")
	@FieldDes(name = "startdate", type = "Date", memo = "开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
     *  项目经费
     */
	@ApiModelProperty("项目经费")
	@TableField("FUNDS")
	@Column(columnDefinition = "FUNDS")
	@FieldDes(name = "funds", type = "BigDecimal", memo = "项目经费")
	private BigDecimal funds;

	/**
     *  财政经费
     */
	@ApiModelProperty("财政经费")
	@TableField("GOVFUNDS")
	@Column(columnDefinition = "GOVFUNDS")
	@FieldDes(name = "govfunds", type = "BigDecimal", memo = "财政经费")
	private BigDecimal govfunds;

	/**
     *  立项年度
     */
	@ApiModelProperty("立项年度")
	@TableField("YEAR")
	@Column(columnDefinition = "YEAR")
	private String year;

	/**
     *  承担单位
     */
	@ApiModelProperty("承担单位")
	@TableField("MAINORGANIZERS")
	@Column(columnDefinition = "MAINORGANIZERS")
	private String mainorganizers;

	/**
     *  承担单位统一社会信用代码
     */
	@ApiModelProperty("承担单位统一社会信用代码")
	@TableField("CREDITCODE")
	@Column(columnDefinition = "CREDITCODE")
	private String creditcode;

	/**
     *  负责人
     */
	@ApiModelProperty("负责人")
	@TableField("PROJECTLEADER")
	@Column(columnDefinition = "PROJECTLEADER")
	private String projectleader;

	/**
     *  负责人证件类型
     */
	@ApiModelProperty("负责人证件类型")
	@TableField("CERTIFICATENAME")
	@Column(columnDefinition = "CERTIFICATENAME")
	private String certificatename;

	/**
     *  负责人证件号码
     */
	@ApiModelProperty("负责人证件号码")
	@TableField("CERTIFICATENUMBER")
	@Column(columnDefinition = "CERTIFICATENUMBER")
	private String certificatenumber;

	/**
     *  项目来源
     */
	@ApiModelProperty("项目来源")
	@TableField("SOURCE")
	@Column(columnDefinition = "SOURCE")
	private String source;

	/**
     *  项目状态
     */
	@ApiModelProperty("项目状态")
	@TableField("STATUS")
	@Column(columnDefinition = "STATUS")
	private String status;

	/**
     *  项目主要承担人（前三）
     */
	@ApiModelProperty("项目主要承担人（前三）")
	@TableField("MAINLEADERS")
	@Column(columnDefinition = "MAINLEADERS")
	private String mainleaders;

	/**
     *  本人排名
     */
	@ApiModelProperty("本人排名")
	@TableField("RANK")
	@Column(columnDefinition = "RANK")
	private String rank;

	/**
     *  计划名称
     */
	@ApiModelProperty("计划名称")
	@TableField("PLANPROJECTNAME")
	@Column(columnDefinition = "PLANPROJECTNAME")
	private String planprojectname;

	/**
     *  担任角色
     */
	@ApiModelProperty("担任角色")
	@TableField("POST")
	@Column(columnDefinition = "POST")
	private String post;

	/**
     *  批准部门
     */
	@ApiModelProperty("批准部门")
	@TableField("APPROVAL_DEPARTMENT")
	@Column(columnDefinition = "APPROVAL_DEPARTMENT")
	private String approvalDepartment;

	/**
     *  主持或参与
     */
	@ApiModelProperty("主持或参与")
	@TableField("HOST_OR_PARTICIPATE")
	@Column(columnDefinition = "HOST_OR_PARTICIPATE")
	private String hostOrParticipate;


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
	*  项目名称
	*/
	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name = name;
	}

	/**
	*  项目类别
	*/
	public String getProjecttype (){
		return projecttype;
	}
	public void setProjecttype (String projecttype){
		this.projecttype = projecttype;
	}

	/**
	*  项目编号
	*/
	public String getApplicationno (){
		return applicationno;
	}
	public void setApplicationno (String applicationno){
		this.applicationno = applicationno;
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
	*  项目经费
	*/
	public BigDecimal getFunds (){
		return funds;
	}
	public void setFunds (BigDecimal funds){
		this.funds = funds;
	}

	/**
	*  财政经费
	*/
	public BigDecimal getGovfunds (){
		return govfunds;
	}
	public void setGovfunds (BigDecimal govfunds){
		this.govfunds = govfunds;
	}

	/**
	*  立项年度
	*/
	public String getYear (){
		return year;
	}
	public void setYear (String year){
		this.year = year;
	}

	/**
	*  承担单位
	*/
	public String getMainorganizers (){
		return mainorganizers;
	}
	public void setMainorganizers (String mainorganizers){
		this.mainorganizers = mainorganizers;
	}

	/**
	*  承担单位统一社会信用代码
	*/
	public String getCreditcode (){
		return creditcode;
	}
	public void setCreditcode (String creditcode){
		this.creditcode = creditcode;
	}

	/**
	*  负责人
	*/
	public String getProjectleader (){
		return projectleader;
	}
	public void setProjectleader (String projectleader){
		this.projectleader = projectleader;
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
	*  负责人证件号码
	*/
	public String getCertificatenumber (){
		return certificatenumber;
	}
	public void setCertificatenumber (String certificatenumber){
		this.certificatenumber = certificatenumber;
	}

	/**
	*  项目来源
	*/
	public String getSource (){
		return source;
	}
	public void setSource (String source){
		this.source = source;
	}

	/**
	*  项目状态
	*/
	public String getStatus (){
		return status;
	}
	public void setStatus (String status){
		this.status = status;
	}

	/**
	*  项目主要承担人（前三）
	*/
	public String getMainleaders (){
		return mainleaders;
	}
	public void setMainleaders (String mainleaders){
		this.mainleaders = mainleaders;
	}

	/**
	*  本人排名
	*/
	public String getRank (){
		return rank;
	}
	public void setRank (String rank){
		this.rank = rank;
	}

	/**
	*  计划名称
	*/
	public String getPlanprojectname (){
		return planprojectname;
	}
	public void setPlanprojectname (String planprojectname){
		this.planprojectname = planprojectname;
	}

	/**
	*  担任角色
	*/
	public String getPost (){
		return post;
	}
	public void setPost (String post){
		this.post = post;
	}

	public String getApprovalDepartment() {
		return approvalDepartment;
	}

	public void setApprovalDepartment(String approvalDepartment) {
		this.approvalDepartment = approvalDepartment;
	}

	public String getHostOrParticipate() {
		return hostOrParticipate;
	}

	public void setHostOrParticipate(String hostOrParticipate) {
		this.hostOrParticipate = hostOrParticipate;
	}
}