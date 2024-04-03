/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-3 13:58:58
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *  计划项目-项目信息
 */
@Entity
@Table(name="PMS_PROJECTBASE_PROJECT")
@ClassInfo(name = "计划项目-项目信息", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseProject {

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
     *  项目类别
     */
	private String projecttype;
	/**
     *  项目编号
     */
	private String applicationno;
	/**
     *  项目名称、课题名称
     */
	private String projectname;
	/**
     *  负责人
     */
	private String projectleader;
	/**
     *  开始时间
     */
	@FieldDes(name = "startdate", type = "Date", memo = "开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date startdate;
	/**
     *  结束时间
     */
	@FieldDes(name = "enddate", type = "Date", memo = "结束时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date enddate;
	/**
     *  项目经费(万元)、总经费
     */
	@FieldDes(name = "projectfunds", type = "Integer", memo = "项目经费(万元)、总经费")
	private Integer projectfunds;
	/**
     *  执行情况
     */
	private String implementation;
	/**
     *  立项年度
     */
	private String year;
	/**
     *  计划名称
     */
	private String planprojectname;
	/**
     *  财政经费(万元)
     */
	@FieldDes(name = "financialfunds", type = "Integer", memo = "财政经费(万元)")
	private Integer financialfunds;
	/**
     *  承担单位
     */
	private String mainorganizers;
	/**
     *  项目级别省部级
     */
	private String level;
	/**
     *  参与人
     */
	private String participants;




	/**
	*  固定字段:唯一标识
	*/
		@Id
	public String getId (){
	return id;
	}
	public void setId (String id){
	this.id=id;
	}

	/**
	*  固定字段:数据类型
	*/
	public String getType (){
	return type;
	}
	public void setType (String type){
	this.type=type;
	}

	/**
	*  固定字段:关联主表id
	*/
	public String getMainid (){
	return mainid;
	}
	public void setMainid (String mainid){
	this.mainid=mainid;
	}

	/**
	*  固定字段:关联子表id
	*/
	public String getSourceid (){
	return sourceid;
	}
	public void setSourceid (String sourceid){
	this.sourceid=sourceid;
	}

	/**
	*  固定字段:第一次保存时间
	*/
	public Date getSavedate (){
	return savedate;
	}
	public void setSavedate (Date savedate){
	this.savedate=savedate;
	}

	/**
	*  固定字段:每次更新数据时间
	*/
	public Date getUpdatelasttime (){
	return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
	this.updatelasttime=updatelasttime;
	}

	/**
	*  固定字段:排序
	*/
	public Integer getSeq (){
	return seq;
	}
	public void setSeq (Integer seq){
	this.seq=seq;
	}

	/**
	*  固定字段:备注
	*/
	public String getMemo (){
	return memo;
	}
	public void setMemo (String memo){
	this.memo=memo;
	}

	/**
	*  项目类别
	*/
	public String getProjecttype (){
	return projecttype;
	}
	public void setProjecttype (String projecttype){
	this.projecttype=projecttype;
	}

	/**
	*  项目编号
	*/
	public String getApplicationno (){
	return applicationno;
	}
	public void setApplicationno (String applicationno){
	this.applicationno=applicationno;
	}

	/**
	*  项目名称、课题名称
	*/
	public String getProjectname (){
	return projectname;
	}
	public void setProjectname (String projectname){
	this.projectname=projectname;
	}

	/**
	*  负责人
	*/
	public String getProjectleader (){
	return projectleader;
	}
	public void setProjectleader (String projectleader){
	this.projectleader=projectleader;
	}

	/**
	*  开始时间
	*/
	public Date getStartdate (){
	return startdate;
	}
	public void setStartdate (Date startdate){
	this.startdate=startdate;
	}

	/**
	*  结束时间
	*/
	public Date getEnddate (){
	return enddate;
	}
	public void setEnddate (Date enddate){
	this.enddate=enddate;
	}

	/**
	*  项目经费(万元)、总经费
	*/
	public Integer getProjectfunds (){
	return projectfunds;
	}
	public void setProjectfunds (Integer projectfunds){
	this.projectfunds=projectfunds;
	}

	/**
	*  执行情况
	*/
	public String getImplementation (){
	return implementation;
	}
	public void setImplementation (String implementation){
	this.implementation=implementation;
	}

	/**
	*  立项年度
	*/
	public String getYear (){
	return year;
	}
	public void setYear (String year){
	this.year=year;
	}

	/**
	*  计划名称
	*/
	public String getPlanprojectname (){
	return planprojectname;
	}
	public void setPlanprojectname (String planprojectname){
	this.planprojectname=planprojectname;
	}

	/**
	*  财政经费(万元)
	*/
	public Integer getFinancialfunds (){
	return financialfunds;
	}
	public void setFinancialfunds (Integer financialfunds){
	this.financialfunds=financialfunds;
	}

	/**
	*  承担单位
	*/
	public String getMainorganizers (){
	return mainorganizers;
	}
	public void setMainorganizers (String mainorganizers){
	this.mainorganizers=mainorganizers;
	}

	/**
	*  项目级别省部级
	*/
	public String getLevel (){
	return level;
	}
	public void setLevel (String level){
	this.level=level;
	}

	/**
	*  参与人
	*/
	public String getParticipants (){
	return participants;
	}
	public void setParticipants (String participants){
	this.participants=participants;
	}

}