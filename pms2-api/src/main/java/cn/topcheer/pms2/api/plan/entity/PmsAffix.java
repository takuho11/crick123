/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-1-8 13:09:39
 *
 */
package cn.topcheer.pms2.api.plan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="PMS_AFFIX")
public class PmsAffix {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  名称
 	 */
	private String name;
	

	/**
 	 *  二进制数据
 	 */
	private byte[] modual;
	

	/**
 	 *  扩展名
 	 */
	private String extendname;
	

	/**
 	 *  创建时间
 	 */
	private Date createdate;
	

	/**
 	 *  插入人ID
 	 */
	private String createuserid;
	

	/**
 	 *  源ID
 	 */
	private String sourceid;
	

	/**
 	 *  文件大小
 	 */
	private Long filesize;
	

	/**
 	 *  附件类型
 	 */
	private String type;
	

	/**
 	 *  备注
 	 */
	private String demo;
	

	/**
 	 *  分类名称
 	 */
	private String category;
	

	/**
 	 *  创建人
 	 */
	private String creator;
	

	/**
 	 *  请求类型
 	 */
	private String requesttype;
	
	private String htmltext;
	



	public String getHtmltext() {
		return htmltext;
	}
	public void setHtmltext(String htmltext) {
		this.htmltext = htmltext;
	}
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
 	 *  名称
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  二进制数据
 	 */
	
	public byte[] getModual (){
		return modual;
	}
	public void setModual (byte[] modual){
		  this.modual=modual;
	}


	/**
 	 *  扩展名
 	 */
	
	public String getExtendname (){
		return extendname;
	}
	public void setExtendname (String extendname){
		  this.extendname=extendname;
	}


	/**
 	 *  创建时间
 	 */
	
	public Date getCreatedate (){
		return createdate;
	}
	public void setCreatedate (Date createdate){
		  this.createdate=createdate;
	}


	/**
 	 *  插入人ID
 	 */
	
	public String getCreateuserid (){
		return createuserid;
	}
	public void setCreateuserid (String createuserid){
		  this.createuserid=createuserid;
	}


	/**
 	 *  源ID
 	 */
	
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		  this.sourceid=sourceid;
	}


	/**
 	 *  文件大小
 	 */
	
	public Long getFilesize (){
		return filesize;
	}
	public void setFilesize (Long filesize){
		  this.filesize=filesize;
	}


	/**
 	 *  附件类型
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}


	/**
 	 *  备注
 	 */
	
	public String getDemo (){
		return demo;
	}
	public void setDemo (String demo){
		  this.demo=demo;
	}


	/**
 	 *  分类名称
 	 */
	
	public String getCategory (){
		return category;
	}
	public void setCategory (String category){
		  this.category=category;
	}


	/**
 	 *  创建人
 	 */
	
	public String getCreator (){
		return creator;
	}
	public void setCreator (String creator){
		  this.creator=creator;
	}


	/**
 	 *  请求类型
 	 */
	
	public String getRequesttype (){
		return requesttype;
	}
	public void setRequesttype (String requesttype){
		  this.requesttype=requesttype;
	}




}