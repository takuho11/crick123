/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2018-3-7 15:05:17
 *
 */
package cn.topcheer.pms2.api.plan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  申报限制条件内容表
 */
@Entity
@Table(name="PMS_PLANCONDITION")
public class PmsPlancondition {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  序号
 	 */
	private Integer seq;
	

	/**
 	 *  条件名称
 	 */
	private String conditionname;
	

	/**
 	 *  条件过滤规则
 	 */
	private String conditionrule;
	

	/**
 	 *  判断信息（输入输出结果说明）
 	 */
	private String information;
	

	/**
 	 *  备注
 	 */
	private String memo;
	



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
 	 *  序号
 	 */
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
	}


	/**
 	 *  条件名称
 	 */
	
	public String getConditionname (){
		return conditionname;
	}
	public void setConditionname (String conditionname){
		  this.conditionname=conditionname;
	}


	/**
 	 *  条件过滤规则
 	 */
	
	public String getConditionrule (){
		return conditionrule;
	}
	public void setConditionrule (String conditionrule){
		  this.conditionrule=conditionrule;
	}


	/**
 	 *  判断信息（输入输出结果说明）
 	 */
	
	public String getInformation (){
		return information;
	}
	public void setInformation (String information){
		  this.information=information;
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




}