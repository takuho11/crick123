/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-4-29 14:06:10
 *
 */
package cn.topcheer.pms2.api.sys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="PMS_ENTERPRISELIMITDETAIL")
public class PmsEnterpriselimitdetail {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  批次id
 	 */
	private String batchid;
	

	/**
 	 *  项目id
 	 */
	private String projectbaseid;
	

	/**
 	 *  操作时间
 	 */
	private Date createdate;
	

	/**
 	 *  单位id
 	 */
	private String enterpriseid;
	

	/**
 	 *  推荐or退回
 	 */
	private String state;


	/**
	 * 存在 又指南 又申报，单独批次无法满足 增加type字段
	 */
	private String type;

	/**
	 * 方向
	 */
	private String directionid;

	/**
	 * 子方向
	 */
	private String childdirectionid;


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
 	 *  批次id
 	 */
	
	public String getBatchid (){
		return batchid;
	}
	public void setBatchid (String batchid){
		  this.batchid=batchid;
	}


	/**
 	 *  项目id
 	 */
	
	public String getProjectbaseid (){
		return projectbaseid;
	}
	public void setProjectbaseid (String projectbaseid){
		  this.projectbaseid=projectbaseid;
	}


	/**
 	 *  操作时间
 	 */
	
	public Date getCreatedate (){
		return createdate;
	}
	public void setCreatedate (Date createdate){
		  this.createdate=createdate;
	}


	/**
 	 *  单位id
 	 */
	
	public String getEnterpriseid (){
		return enterpriseid;
	}
	public void setEnterpriseid (String enterpriseid){
		  this.enterpriseid=enterpriseid;
	}


	/**
 	 *  推荐or退回
 	 */
	
	public String getState (){
		return state;
	}
	public void setState (String state){
		  this.state=state;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getDirectionid() {
		return directionid;
	}

	public void setDirectionid(String directionid) {
		this.directionid = directionid;
	}

	public String getChilddirectionid() {
		return childdirectionid;
	}

	public void setChilddirectionid(String childdirectionid) {
		this.childdirectionid = childdirectionid;
	}
}