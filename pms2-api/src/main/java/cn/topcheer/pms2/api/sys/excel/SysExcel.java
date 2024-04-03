/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-9-24 11:57:03
 *
 */
package cn.topcheer.pms2.api.sys.excel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  excel配置主表
 */
@Entity
@Table(name="SYS_EXCEL")
public class SysExcel implements Cloneable{


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  excel名称
 	 */
	private String name;
	

	/**
 	 *  excel用户角色权限
 	 */
	private String roleid;
	

	/**
 	 *  备注
 	 */
	private String memo;
	

	/**
 	 *  创建用户名
 	 */
	private String creatname;
	

	/**
 	 *  创建时间
 	 */
	private Date creattime;
	

	/**
 	 *  创建用户id
 	 */
	private String creatuserid;
	

	/**
 	 *  更新时间
 	 */
	private Date updatetime;


	/**
	 *  条件语句--可用于名称
	 */
	private String conditionsql;
	



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
 	 *  excel名称
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  excel用户角色权限
 	 */
	
	public String getRoleid (){
		return roleid;
	}
	public void setRoleid (String roleid){
		  this.roleid=roleid;
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
 	 *  创建用户名
 	 */
	
	public String getCreatname (){
		return creatname;
	}
	public void setCreatname (String creatname){
		  this.creatname=creatname;
	}


	/**
 	 *  创建时间
 	 */
	
	public Date getCreattime (){
		return creattime;
	}
	public void setCreattime (Date creattime){
		  this.creattime=creattime;
	}


	/**
 	 *  创建用户id
 	 */
	
	public String getCreatuserid (){
		return creatuserid;
	}
	public void setCreatuserid (String creatuserid){
		  this.creatuserid=creatuserid;
	}


	/**
 	 *  更新时间
 	 */
	
	public Date getUpdatetime (){
		return updatetime;
	}
	public void setUpdatetime (Date updatetime){
		  this.updatetime=updatetime;
	}

	public String getConditionsql() {
		return conditionsql;
	}

	public void setConditionsql(String conditionsql) {
		this.conditionsql = conditionsql;
	}

	/**
	 * 用于深度拷贝
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}



}