/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-1-8 13:09:41
 *
 */
package cn.topcheer.pms2.api.plan.entity;

import javax.persistence.*;
import java.util.List;

/**
 *  
 */
@Entity
@Table(name="PMS_DECLARATIONGUIDE")
public class PmsDeclarationguide {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  指南名称
 	 */
	private String guidename;
	

	/**
 	 *  项目概述
 	 */
	private String projectovervies;
	

	/**
 	 *  填写说明
 	 */
	private String explanation;
	

	/**
 	 *  备注
 	 */
	private String memo;
	

	/**
 	 *  排序
 	 */
	private Integer sort;
	



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
 	 *  指南名称
 	 */
	
	public String getGuidename (){
		return guidename;
	}
	public void setGuidename (String guidename){
		  this.guidename=guidename;
	}


	/**
 	 *  项目概述
 	 */
	
	public String getProjectovervies (){
		return projectovervies;
	}
	public void setProjectovervies (String projectovervies){
		  this.projectovervies=projectovervies;
	}


	/**
 	 *  填写说明
 	 */
	
	public String getExplanation (){
		return explanation;
	}
	public void setExplanation (String explanation){
		  this.explanation=explanation;
	}


	/**
 	 *  备注
 	 */
	
	public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		  this.memo=memo;
	}


	/**
 	 *  排序
 	 */
	
	public Integer getSort (){
		return sort;
	}
	public void setSort (Integer sort){
		  this.sort=sort;
	}


	private List<PmsPlanprojectbatch> pmsPlanprojectbatchs;
	
	@OneToMany(mappedBy= "pmsDeclarationguide",cascade={CascadeType.ALL})
	public List<PmsPlanprojectbatch> getPmsPlanprojectbatchs(){
		return pmsPlanprojectbatchs;
	}
	
	public void setPmsPlanprojectbatchs (List<PmsPlanprojectbatch> pmsPlanprojectbatchs){
		  this.pmsPlanprojectbatchs=pmsPlanprojectbatchs;
	}	
	
		


}