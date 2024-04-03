/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-7-25 16:10:05
 *
 */
package cn.topcheer.pms2.api.announcement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  
 */
@Entity
@Table(name="REMIND_ROLE")
public class RemindRole {


	/**
 	 *  唯一标识
 	 */
	private String id;
	

	/**
 	 *  remind_config表的id
 	 */
	private String configid;
	

	/**
 	 *  sys_role表的id
 	 */
	private String roleid;

	/**
	 * 排序
	 */
	private Integer seq;


	/*==============================================完美分割线==============================================*/


	/**
 	 *  唯一标识
 	 */
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  remind_config表的id
 	 */
	public String getConfigid (){
		return configid;
	}
	public void setConfigid (String configid){
		  this.configid=configid;
	}


	/**
 	 *  sys_role表的id
 	 */
	public String getRoleid (){
		return roleid;
	}
	public void setRoleid (String roleid){
		  this.roleid=roleid;
	}


	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}