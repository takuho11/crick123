/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-3-5 11:23:05
 *
 */
package cn.topcheer.pms2.api.zjk;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="ZJK_BATCH_CNS")
public class ZjkBatchCns {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  标题
 	 */
	private String titlename;
	

	/**
 	 *  内容
 	 */
	private String content;
	

	/**
 	 *  按钮
 	 */
	private String modulebtns;
	

	/**
 	 *  至少查看时长
 	 */
	private Integer limitsecond;
	

	/**
 	 *  应用批次
 	 */
	private String batchliststring;
	

	/**
 	 *  备注
 	 */
	private String memo;
	

	/**
 	 *  
 	 */
	private Date creatdate;
	



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
 	 *  标题
 	 */
	
	public String getTitlename (){
		return titlename;
	}
	public void setTitlename (String titlename){
		  this.titlename=titlename;
	}


	/**
 	 *  内容
 	 */
	
	public String getContent (){
		return content;
	}
	public void setContent (String content){
		  this.content=content;
	}


	/**
 	 *  按钮
 	 */
	
	public String getModulebtns (){
		return modulebtns;
	}
	public void setModulebtns (String modulebtns){
		  this.modulebtns=modulebtns;
	}


	/**
 	 *  至少查看时长
 	 */
	
	public Integer getLimitsecond (){
		return limitsecond;
	}
	public void setLimitsecond (Integer limitsecond){
		  this.limitsecond=limitsecond;
	}


	/**
 	 *  应用批次
 	 */
	
	public String getBatchliststring (){
		return batchliststring;
	}
	public void setBatchliststring (String batchliststring){
		  this.batchliststring=batchliststring;
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
 	 *  
 	 */
	
	public Date getCreatdate (){
		return creatdate;
	}
	public void setCreatdate (Date creatdate){
		  this.creatdate=creatdate;
	}




}