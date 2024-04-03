/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2017-5-9 17:13:39
 *
 */
package cn.topcheer.pms2.api.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  专利表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="PMS_PROJECTPATENT")
public class PmsProjectpatent {


	/**
 	 *  
 	 */
	@Id
	private String id;
	

	/**
 	 *  知识产权具体名称
 	 */
	private String name;
	

	/**
 	 *  国家（地区）
 	 */
	private String country;
	

	/**
 	 *  授权号
 	 */
	private String authorizationnumber;
	

	/**
 	 *  授权日期
 	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date publishtime;
	

	/**
 	 *   发明人（培育人）
 	 */
	private String inventor;
	

	/**
 	 *  权利人
 	 */
	private String publishman;
	

	/**
 	 *  产业化方向
 	 */
	private String industrialization;
	

	/**
 	 *  目标产品
 	 */
	private String targetproduct;
	

	/**
 	 *  新增产值
 	 */
	private String newproduction;
	

	/**
 	 *  授权截止日期
 	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date publishendtime;
	
	/**
 	 *  项目id
 	 */
	private String projectbaseid;

	
	
	public String getProjectbaseid() {
		return projectbaseid;
	}
	public void setProjectbaseid(String projectbaseid) {
		this.projectbaseid = projectbaseid;
	}
	/**
 	 *  
 	 */
	
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  知识产权具体名称
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}
    public Integer seq;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
 	 *  国家（地区）
 	 */
	
	public String getCountry (){
		return country;
	}
	public void setCountry (String country){
		  this.country=country;
	}


	/**
 	 *  授权号
 	 */
	
	public String getAuthorizationnumber (){
		return authorizationnumber;
	}
	public void setAuthorizationnumber (String authorizationnumber){
		  this.authorizationnumber=authorizationnumber;
	}


	/**
 	 *  授权日期
 	 */
	
	public Date getPublishtime (){
		return publishtime;
	}
	public void setPublishtime (Date publishtime){
		  this.publishtime=publishtime;
	}


	/**
 	 *   发明人（培育人）
 	 */
	
	public String getInventor (){
		return inventor;
	}
	public void setInventor (String inventor){
		  this.inventor=inventor;
	}


	/**
 	 *  权利人
 	 */
	
	public String getPublishman (){
		return publishman;
	}
	public void setPublishman (String publishman){
		  this.publishman=publishman;
	}


	/**
 	 *  产业化方向
 	 */
	
	public String getIndustrialization (){
		return industrialization;
	}
	public void setIndustrialization (String industrialization){
		  this.industrialization=industrialization;
	}


	/**
 	 *  目标产品
 	 */
	
	public String getTargetproduct (){
		return targetproduct;
	}
	public void setTargetproduct (String targetproduct){
		  this.targetproduct=targetproduct;
	}


	/**
 	 *  新增产值
 	 */
	
	public String getNewproduction (){
		return newproduction;
	}
	public void setNewproduction (String newproduction){
		  this.newproduction=newproduction;
	}


	/**
 	 *  授权截止日期
 	 */
	
	public Date getPublishendtime (){
		return publishendtime;
	}
	public void setPublishendtime (Date publishendtime){
		  this.publishendtime=publishendtime;
	}




}