/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-4-26 16:26:13
 *
 */
package cn.topcheer.pms2.api.sys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author yun
 *
 */
@Entity
@Table(name="SYS_OPERATIONRECORD")
public class SysOperationrecord implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  操作日期
 	 */
	private Date operationdate;
	

	/**
 	 *  操作者
 	 */
	private String operator;
	

	/**
 	 *  操作者id
 	 */
	private String operatorid;
	

	/**
 	 *  备注(申请信息或者审批信息)
 	 */
	private String note;
	
	/**
 	 *  意见
 	 */
	private String opinion;

	/**
	 * 操作类型
	 * @return
	 */
	private String type;

	public SysOperationrecord() {
	}

	public SysOperationrecord(String id) {
		this.id = id;
	}

	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	/**
 	 *  主键
 	 */
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
 	 *  操作日期
 	 */
	
	public Date getOperationdate (){
		return operationdate;
	}
	public void setOperationdate (Date operationdate){
		  this.operationdate=operationdate;
	}


	/**
 	 *  操作者
 	 */
	
	public String getOperator (){
		return operator;
	}
	public void setOperator (String operator){
		  this.operator=operator;
	}


	/**
 	 *  操作者id
 	 */
	
	public String getOperatorid (){
		return operatorid;
	}
	public void setOperatorid (String operatorid){
		  this.operatorid=operatorid;
	}
	
	private String sourceid;
	private String lasttimeip;
	
	
	
	
	public String getLasttimeip() {
		return lasttimeip;
	}
	public void setLasttimeip(String lasttimeip) {
		this.lasttimeip = lasttimeip;
	}
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	/**
 	 *  备注(申请信息或者审批信息)
 	 */
	
	public String getNote (){
		return note;
	}
	public void setNote (String note){
		  this.note=note;
	}




}