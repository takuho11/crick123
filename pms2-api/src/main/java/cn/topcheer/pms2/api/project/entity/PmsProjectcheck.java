/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-3-4 16:23:25
 *
 */
package cn.topcheer.pms2.api.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  项目检测(内审排查,形审排查)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="PMS_PROJECTCHECK")
public class PmsProjectcheck {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  测试类型,内审一键排查,形审一键排查,其实就是用在哪个界面
 	 */
	private String checktype;
	

	/**
 	 *  测试服务,即bean名称
 	 */
	private String checkservice;
	

	/**
 	 *  是否启用 1:启用；0:停用
 	 */
	private Boolean isenabled;
	

	/**
 	 *  标题名称
 	 */
	private String name;
	



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
 	 *  测试类型,内审一键排查,形审一键排查,其实就是用在哪个界面
 	 */
	
	public String getChecktype (){
		return checktype;
	}
	public void setChecktype (String checktype){
		  this.checktype=checktype;
	}


	/**
 	 *  测试服务,即bean名称
 	 */
	
	public String getCheckservice (){
		return checkservice;
	}
	public void setCheckservice (String checkservice){
		  this.checkservice=checkservice;
	}


	/**
 	 *  是否启用 1:启用；0:停用
 	 */
	
	public Boolean getIsenabled(){
		return isenabled;
	}
	public void setIsenabled (Boolean isenabled){
		  this.isenabled=isenabled;
	}


	/**
 	 *  标题名称
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}




}