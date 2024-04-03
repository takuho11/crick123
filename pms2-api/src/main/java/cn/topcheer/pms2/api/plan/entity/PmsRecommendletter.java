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
@Table(name="PMS_RECOMMENDLETTER")
public class PmsRecommendletter {


	/**
     *  
     */
    @FieldDes(name="id",lenth="60",type="String",memo="")
	private String id;

	/**
     *  
     */
    @FieldDes(name="isable",lenth="60",type="String",memo="")
	private String isable;

	/**
     *  
     */
    @FieldDes(name="enterpriseid",lenth="60",type="String",memo="")
	private String enterpriseid;

	/**
     *  
     */
    @FieldDes(name="enterprisename",lenth="255",type="String",memo="")
	private String enterprisename;

	/**
     *  
     */
    @FieldDes(name="linkname",lenth="100",type="String",memo="")
	private String linkname;

	/**
     *  
     */
    @FieldDes(name="linktelephone",lenth="100",type="String",memo="")
	private String linktelephone;

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
    @FieldDes(name="minicurrentstate",lenth="60",type="String",memo="")
	private String minicurrentstate;

	/**
	 *推荐函名称
	 */
	@FieldDes(name="lettername",lenth="500",type="String",memo="")
	private String lettername;

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
	private Date savedate;

	/**
     *  
     */
	private Date updatedate;

	/**
     *  
     */
	private Date submitdate;

	/**
     *  
     */
    @FieldDes(name="memo",lenth="500",type="String",memo="")
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
 	 *  
 	 */
	
	public String getIsable (){
		return isable;
	}
	public void setIsable (String isable){
		  this.isable=isable;
	}


	/**
 	 *  
 	 */
	
	public String getEnterpriseid (){
		return enterpriseid;
	}
	public void setEnterpriseid (String enterpriseid){
		  this.enterpriseid=enterpriseid;
	}


	/**
 	 *  
 	 */
	
	public String getEnterprisename (){
		return enterprisename;
	}
	public void setEnterprisename (String enterprisename){
		  this.enterprisename=enterprisename;
	}


	/**
 	 *  
 	 */
	
	public String getLinkname (){
		return linkname;
	}
	public void setLinkname (String linkname){
		  this.linkname=linkname;
	}


	/**
 	 *  
 	 */
	
	public String getLinktelephone (){
		return linktelephone;
	}
	public void setLinktelephone (String linktelephone){
		  this.linktelephone=linktelephone;
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
	
	public String getMinicurrentstate (){
		return minicurrentstate;
	}
	public void setMinicurrentstate (String minicurrentstate){
		  this.minicurrentstate=minicurrentstate;
	}


	/**
 	 *  
 	 */
	
	public Date getSavedate (){
		return savedate;
	}
	public void setSavedate (Date savedate){
		  this.savedate=savedate;
	}


	/**
 	 *  
 	 */
	
	public Date getUpdatedate (){
		return updatedate;
	}
	public void setUpdatedate (Date updatedate){
		  this.updatedate=updatedate;
	}


	/**
 	 *  
 	 */
	
	public Date getSubmitdate (){
		return submitdate;
	}
	public void setSubmitdate (Date submitdate){
		  this.submitdate=submitdate;
	}


	/**
 	 *  
 	 */
	
	public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		  this.memo=memo;
	}

	public String getLettername() {
		return lettername;
	}

	public void setLettername(String lettername) {
		this.lettername = lettername;
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
}