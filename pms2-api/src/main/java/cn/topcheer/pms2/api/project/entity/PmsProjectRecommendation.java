/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-6-11 11:33:04
 *
 */
package cn.topcheer.pms2.api.project.entity;

import cn.topcheer.pms2.api.annotation.ClassInfo;
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
 *  申报推荐函主表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ClassInfo(name = "申报推荐函主表", module = SysModuleEnum.UNDEFINE, level = ClassLevelEnum.POJO)
@Entity
@Table(name="PMS_PROJECT_RECOMMENDATION")
public class PmsProjectRecommendation {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  名称
 	 */
	@FieldDes(name="recommendationname",lenth="500",type="String",memo="名称")
	private String recommendationname;
	

	/**
 	 *  推荐单位id
 	 */
	private String casemanagementid;
	

	/**
 	 *  推荐单位
 	 */
	@FieldDes(name="casemanagement",lenth="500",type="String",memo="推荐单位")
	private String casemanagement;
	

	/**
 	 *  大批次id
 	 */
	private String planprojectid;
	

	/**
 	 *  大批次
 	 */
	@FieldDes(name="planproject",lenth="500",type="String",memo="大批次")
	private String planproject;
	

	/**
 	 *  年度
 	 */
	@FieldDes(name="year",lenth="64",type="String",memo="年度")
	private String year;
	

	/**
 	 *  高级管理员id
 	 */
	private String declarantid;
	

	/**
 	 *  联系人姓名
 	 */
	@FieldDes(name="linkname",lenth="64",type="String",memo="联系人姓名")
	private String linkname;
	

	/**
 	 *  联系人手机
 	 */
	@FieldDes(name="linkmobilephone",lenth="64",type="String",memo="联系人手机")
	private String linkmobilephone;
	

	/**
 	 *  联系人电话
 	 */
	@FieldDes(name="linktelephone",lenth="64",type="String",memo="联系人电话")
	private String linktelephone;
	

	/**
 	 *  提交时间
 	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date submittime;
	

	/**
 	 *  最后一次保存时间
 	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date updatelasttime;
	

	/**
 	 *  项目状态
 	 */
	private String minicurrentstate;


	/**
	 *  项目状态id
	 */
	private String minicurrentstateid;

	/**
 	 *  小批次id
 	 */
	private String planprojectbatchid;

	/**
	 *  小批次
	 */
	private String planprojectbatch;


	/**
 	 *  
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  名称
 	 */
	
	public String getRecommendationname (){
		return recommendationname;
	}
	public void setRecommendationname (String recommendationname){
		  this.recommendationname=recommendationname;
	}


	/**
 	 *  推荐单位id
 	 */
	
	public String getCasemanagementid (){
		return casemanagementid;
	}
	public void setCasemanagementid (String casemanagementid){
		  this.casemanagementid=casemanagementid;
	}


	/**
 	 *  推荐单位
 	 */
	
	public String getCasemanagement (){
		return casemanagement;
	}
	public void setCasemanagement (String casemanagement){
		  this.casemanagement=casemanagement;
	}


	/**
 	 *  大批次id
 	 */
	
	public String getPlanprojectid (){
		return planprojectid;
	}
	public void setPlanprojectid (String planprojectid){
		  this.planprojectid=planprojectid;
	}


	/**
 	 *  大批次
 	 */
	
	public String getPlanproject (){
		return planproject;
	}
	public void setPlanproject (String planproject){
		  this.planproject=planproject;
	}


	/**
 	 *  年度
 	 */
	
	public String getYear (){
		return year;
	}
	public void setYear (String year){
		  this.year=year;
	}


	/**
 	 *  高级管理员id
 	 */
	
	public String getDeclarantid (){
		return declarantid;
	}
	public void setDeclarantid (String declarantid){
		  this.declarantid=declarantid;
	}


	/**
 	 *  联系人姓名
 	 */
	
	public String getLinkname (){
		return linkname;
	}
	public void setLinkname (String linkname){
		  this.linkname=linkname;
	}


	/**
 	 *  联系人手机
 	 */
	
	public String getLinkmobilephone (){
		return linkmobilephone;
	}
	public void setLinkmobilephone (String linkmobilephone){
		  this.linkmobilephone=linkmobilephone;
	}


	/**
 	 *  联系人电话
 	 */
	
	public String getLinktelephone (){
		return linktelephone;
	}
	public void setLinktelephone (String linktelephone){
		  this.linktelephone=linktelephone;
	}


	/**
 	 *  提交时间
 	 */
	
	public Date getSubmittime (){
		return submittime;
	}
	public void setSubmittime (Date submittime){
		  this.submittime=submittime;
	}


	/**
 	 *  最后一次保存时间
 	 */
	
	public Date getUpdatelasttime (){
		return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
		  this.updatelasttime=updatelasttime;
	}


	/**
 	 *  项目状态
 	 */
	
	public String getMinicurrentstate (){
		return minicurrentstate;
	}
	public void setMinicurrentstate (String minicurrentstate){
		  this.minicurrentstate=minicurrentstate;
	}


	/**
 	 *  项目状态id
 	 */
	
	public String getMinicurrentstateid (){
		return minicurrentstateid;
	}
	public void setMinicurrentstateid (String minicurrentstateid){
		  this.minicurrentstateid=minicurrentstateid;
	}

	/**
	 *  小批次id
	 */

	public String getPlanprojectbatchid (){
		return planprojectbatchid;
	}
	public void setPlanprojectbatchid (String planprojectbatchid){
		this.planprojectbatchid=planprojectbatchid;
	}


	/**
	 *  小批次
	 */

	public String getPlanprojectbatch (){
		return planprojectbatch;
	}
	public void setPlanprojectbatch (String planprojectbatch){
		this.planprojectbatch=planprojectbatch;
	}
}