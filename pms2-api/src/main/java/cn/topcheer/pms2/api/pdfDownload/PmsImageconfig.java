/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-11-29 15:52:33
 *
 */
package cn.topcheer.pms2.api.pdfDownload;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="PMS_IMAGECONFIG")
public class PmsImageconfig {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  标识符---对应模板里的唯一标识符
 	 */
	private String position;
	

	/**
 	 *  附件类别
 	 */
	private String category;


	/**
	 *  标识符类别--如果是repeat则需循环position+seq,positiontype=orther 则是先关联相关表再根据该表的id查附件
	 */
	private String positiontype;


	/**
	 *  sql--如果positiontype=orther,则这边取sql
	 */
	private String positionsql;
	

	/**
 	 *  图片宽度
 	 */
	private String width;
	

	/**
 	 *  图片高度
 	 */
	private String height;
	

	/**
 	 *  创建时间
 	 */
	private Date createdate;
	

	/**
 	 *  创建人
 	 */
	private String createuserid;
	

	/**
 	 *  批次名称
 	 */
	private String batchname;
	

	/**
 	 *  批次id
 	 */
	private String batchid;
	

	/**
 	 *  区别是合同还是申报
 	 */
	private String type;
	

	/**
 	 *  
 	 */
	private String demo;
	



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
 	 *  标识符---对应模板里的唯一标识符
 	 */
	
	public String getPosition (){
		return position;
	}
	public void setPosition (String position){
		  this.position=position;
	}


	/**
 	 *  附件类别
 	 */
	
	public String getCategory (){
		return category;
	}
	public void setCategory (String category){
		  this.category=category;
	}


	/**
 	 *  标识符类别--如果是repeat则需循环position+seq
 	 */
	
	public String getPositiontype (){
		return positiontype;
	}
	public void setPositiontype (String positiontype){
		  this.positiontype=positiontype;
	}

	public String getPositionsql() {
		return positionsql;
	}

	public void setPositionsql(String positionsql) {
		this.positionsql = positionsql;
	}

	/**
 	 *  图片宽度
 	 */
	
	public String getWidth (){
		return width;
	}
	public void setWidth (String width){
		  this.width=width;
	}


	/**
 	 *  图片高度
 	 */
	
	public String getHeight (){
		return height;
	}
	public void setHeight (String height){
		  this.height=height;
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
 	 *  创建人
 	 */
	
	public String getCreateuserid (){
		return createuserid;
	}
	public void setCreateuserid (String createuserid){
		  this.createuserid=createuserid;
	}


	/**
 	 *  批次名称
 	 */
	
	public String getBatchname (){
		return batchname;
	}
	public void setBatchname (String batchname){
		  this.batchname=batchname;
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
 	 *  区别是合同还是申报
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}


	/**
 	 *  
 	 */
	
	public String getDemo (){
		return demo;
	}
	public void setDemo (String demo){
		  this.demo=demo;
	}




}