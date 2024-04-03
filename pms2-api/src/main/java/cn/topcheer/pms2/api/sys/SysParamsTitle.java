/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-4-27 14:41:48
 *
 */
package cn.topcheer.pms2.api.sys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  
 */
@Entity
@Table(name="SYS_PARAMS_TITLE")
public class SysParamsTitle {


	/**
 	 *  唯一id
 	 */
	private String id;
	

	/**
 	 *  父级id
 	 */
	private String parentid;
	

	/**
 	 *  显示在页面的名称
 	 */
	private String name;
	

	/**
 	 *  需要存在业务数据的值
 	 */
	private String value;
	

	/**
 	 *  非必填代码，如学科代码、所在地代码
 	 */
	private String code;
	

	/**
 	 *  应用类型，比如学科是来自专家库的，或者学科是来自项目系统的...
 	 */
	private String applytype;
	

	/**
 	 *  排序
 	 */
	private Integer seq;
	

	/**
 	 *  备注
 	 */
	private String memo;
	



	/**
 	 *  唯一id
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  父级id
 	 */
	
	public String getParentid (){
		return parentid;
	}
	public void setParentid (String parentid){
		  this.parentid=parentid;
	}


	/**
 	 *  显示在页面的名称
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  需要存在业务数据的值
 	 */
	
	public String getValue (){
		return value;
	}
	public void setValue (String value){
		  this.value=value;
	}


	/**
 	 *  非必填代码，如学科代码、所在地代码
 	 */
	
	public String getCode (){
		return code;
	}
	public void setCode (String code){
		  this.code=code;
	}


	/**
 	 *  应用类型，比如学科是来自专家库的，或者学科是来自项目系统的...
 	 */
	
	public String getApplytype (){
		return applytype;
	}
	public void setApplytype (String applytype){
		  this.applytype=applytype;
	}


	/**
 	 *  排序
 	 */
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
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