/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-4-13 14:57:18
 *
 */
package cn.topcheer.pms2.api.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  项申报目说明信息表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="PMS_PROJECTDESCRIPTION")
public class PmsProjectdescription {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  申报项目时用户看到的项目简介
 	 */
	private String briefintroduction;
	

	/**
 	 *  详细信息
 	 */
	private String detailedinformation;
	

	public String getBatchname() {
		return batchname;
	}
	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}
	private String batchid;
	private String batchname;
	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
	/**
 	 *  受理条件
 	 */
	private String acceptancecondition;
	

	/**
 	 *  申报材料
 	 */
	private String handlingmaterial;
	

	/**
 	 *  办理流程
 	 */
	private String managementprocess;
	

	/**
 	 *  收费情况
 	 */
	private String chargesituation;
	

	/**
 	 *  法定依据
 	 */
	private String legalbasis;
	



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
 	 *  申报项目时用户看到的项目简介
 	 */
	
	public String getBriefintroduction (){
		return briefintroduction;
	}
	public void setBriefintroduction (String briefintroduction){
		  this.briefintroduction=briefintroduction;
	}


	/**
 	 *  详细信息
 	 */
	
	public String getDetailedinformation (){
		return detailedinformation;
	}
	public void setDetailedinformation (String detailedinformation){
		  this.detailedinformation=detailedinformation;
	}


	/**
 	 *  受理条件
 	 */
	
	public String getAcceptancecondition (){
		return acceptancecondition;
	}
	public void setAcceptancecondition (String acceptancecondition){
		  this.acceptancecondition=acceptancecondition;
	}


	/**
 	 *  申报材料
 	 */
	
	public String getHandlingmaterial (){
		return handlingmaterial;
	}
	public void setHandlingmaterial (String handlingmaterial){
		  this.handlingmaterial=handlingmaterial;
	}


	/**
 	 *  办理流程
 	 */
	
	public String getManagementprocess (){
		return managementprocess;
	}
	public void setManagementprocess (String managementprocess){
		  this.managementprocess=managementprocess;
	}


	/**
 	 *  收费情况
 	 */
	
	public String getChargesituation (){
		return chargesituation;
	}
	public void setChargesituation (String chargesituation){
		  this.chargesituation=chargesituation;
	}


	/**
 	 *  法定依据
 	 */
	
	public String getLegalbasis (){
		return legalbasis;
	}
	public void setLegalbasis (String legalbasis){
		  this.legalbasis=legalbasis;
	}




}