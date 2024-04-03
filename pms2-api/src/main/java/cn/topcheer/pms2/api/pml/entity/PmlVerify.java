/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-9-15 9:59:42
 *
 */
package cn.topcheer.pms2.api.pml.entity;

import cn.topcheer.halberd.annotation.FieldDes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="PML_VERIFY")
public class PmlVerify {


	/**
     *  主键id
     */
    @FieldDes(name="id",lenth="255",type="String",memo="主键id")
	private String id;

	/**
     *  批次id
     */
    @FieldDes(name="batchid",lenth="255",type="String",memo="批次id")
	private String batchid;

	/**
     *  关联项目id
     */
    @FieldDes(name="sourceid",lenth="255",type="String",memo="关联项目id")
	private String sourceid;

	/**
     *  验证错误内容
     */
    @FieldDes(name="content",type="Clob",memo="验证结果")
	private String content;

	/**
     *  验证时间
     */
    @FieldDes(name="verifydate",type="Date",memo="验证时间")
	private Date verifydate;

	/**
	 *  验证结果
	 */
	@FieldDes(name="result",lenth="255",type="String",memo="验证结果")
	private String result;




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

	public String getBatchid() { return batchid; }
	public void setBatchid(String batchid) { this.batchid = batchid; }

	public String getSourceid() { return sourceid; }
	public void setSourceid(String sourceid) { this.sourceid = sourceid; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }

	public Date getVerifydate() { return verifydate; }
	public void setVerifydate(Date verifydate) { this.verifydate = verifydate; }

	public String getResult() { return result; }
	public void setResult(String result) { this.result = result; }
}