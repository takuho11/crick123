/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-1-8 13:09:43
 *
 */
package cn.topcheer.pms2.api.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="PMS_PROJECTOPENOBJECT")
public class PmsProjectopenobject {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  用户开放对象名称
 	 */
	private String openusername;
	

	/**
 	 *  开放用户类型
 	 */
	private String openusertype;
	

	/**
 	 *  用户开放对象代码
 	 */
	private String openusercode;
	

	/**
 	 *  开放用户类型代码
 	 */
	private String openusertypecode;
	



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
 	 *  用户开放对象名称
 	 */
	
	public String getOpenusername (){
		return openusername;
	}
	public void setOpenusername (String openusername){
		  this.openusername=openusername;
	}


	/**
 	 *  开放用户类型
 	 */
	
	public String getOpenusertype (){
		return openusertype;
	}
	public void setOpenusertype (String openusertype){
		  this.openusertype=openusertype;
	}


	/**
 	 *  用户开放对象代码
 	 */
	
	public String getOpenusercode (){
		return openusercode;
	}
	public void setOpenusercode (String openusercode){
		  this.openusercode=openusercode;
	}


	/**
 	 *  开放用户类型代码
 	 */
	
	public String getOpenusertypecode (){
		return openusertypecode;
	}
	public void setOpenusertypecode (String openusertypecode){
		  this.openusertypecode=openusertypecode;
	}




}