/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-10-23 16:27:58
 *
 */
package cn.topcheer.pms2.api.pml.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  下载记录及路径
 */
@Entity
@Table(name="SYS_DOWN_RECORD")
public class SysDownRecord {


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
 	 *  路径
 	 */
	private String url;
	

	/**
 	 *  操作类型
 	 */
	private String type;
	

	/**
 	 *  源id
 	 */
	private String sourceid;
	

	/**
 	 *  最后一次登录ip
 	 */
	private String lasttimeip;


	/**
	 *  业务类别--一般对应配置
	 */
	private String businesstype;


	/**
	 *  盖章路径
	 */
	private String signed_url;


	/**
	 *  输出名称
	 */
	private String outname;
	



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


	/**
 	 *  备注(申请信息或者审批信息)
 	 */
	
	public String getNote (){
		return note;
	}
	public void setNote (String note){
		  this.note=note;
	}


	/**
 	 *  路径
 	 */
	
	public String getUrl (){
		return url;
	}
	public void setUrl (String url){
		  this.url=url;
	}


	/**
 	 *  操作类型
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}


	/**
 	 *  源id
 	 */
	
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		  this.sourceid=sourceid;
	}


	/**
 	 *  最后一次登录ip
 	 */
	
	public String getLasttimeip (){
		return lasttimeip;
	}
	public void setLasttimeip (String lasttimeip){
		  this.lasttimeip=lasttimeip;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public String getSigned_url() {
		return signed_url;
	}

	public void setSigned_url(String signed_url) {
		this.signed_url = signed_url;
	}

	public String getOutname() {
		return outname;
	}

	public void setOutname(String outname) {
		this.outname = outname;
	}
}