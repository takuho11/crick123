/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2022-12-15 16:05:04
 *
 */
package cn.topcheer.pms2.api.plan.entity;

import cn.topcheer.pms2.api.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="PMS_RECOMMENDLETTER_CONFIG")
public class PmsRecommendletterConfig {


	/**
     *  
     */
    @FieldDes(name="id",lenth="60",type="String",memo="")
	private String id;

	/**
     *  
     */
    @FieldDes(name="batchid",lenth="60",type="String",memo="")
	private String batchid;

	/**
     *  
     */
    @FieldDes(name="batchname",lenth="255",type="String",memo="")
	private String batchname;

	/**
     *  
     */
    @FieldDes(name="content",lenth="2000",type="String",memo="")
	private String content;

	/**
	 *
	 */
	@FieldDes(name="datasql",lenth="2000",type="String",memo="")
	private String datasql;

	/**
	 *
	 */
	@FieldDes(name="userid",lenth="60",type="String",memo="")
	private String userid;

	/**
	 *
	 */
	@FieldDes(name="username",lenth="60",type="String",memo="")
	private String username;

	/**
	 *
	 */
	@FieldDes(name="modeltype",lenth="100",type="String",memo="")
	private String modeltype;

	/**
	 *
	 */
	private Date savedate;



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
 	 *  
 	 */
	
	public String getBatchid (){
		return batchid;
	}
	public void setBatchid (String batchid){
		  this.batchid=batchid;
	}


	/**
 	 *  
 	 */
	
	public String getBatchname (){
		return batchname;
	}
	public void setBatchname (String batchname){
		  this.batchname=batchname;
	}


	/**
 	 *  
 	 */
	
	public String getContent (){
		return content;
	}
	public void setContent (String content){
		  this.content=content;
	}


	public String getDatasql() {
		return datasql;
	}

	public void setDatasql(String datasql) {
		this.datasql = datasql;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getSavedate() {
		return savedate;
	}

	public void setSavedate(Date savedate) {
		this.savedate = savedate;
	}

	public String getModeltype() {
		return modeltype;
	}

	public void setModeltype(String modeltype) {
		this.modeltype = modeltype;
	}
}