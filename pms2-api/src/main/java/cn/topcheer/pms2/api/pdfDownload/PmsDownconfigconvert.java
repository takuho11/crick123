/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-1-19 15:17:52
 *
 */
package cn.topcheer.pms2.api.pdfDownload;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  pdf配置表评价顺序
 */
@Entity
@Table(name="PMS_DOWNCONFIGCONVERT")
public class PmsDownconfigconvert implements Cloneable{


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  affix、affixurl、freemark、xml、rtf
 	 */
	private String type;
	

	/**
 	 *  如果type是freemark，xml，rtf就是模板路径，如果是affix，就是附件id，如果是affixurl就是附件地址
 	 */
	private String url;
	

	/**
 	 *  批次id,关联id
 	 */
	private String sourceid;
	

	/**
 	 *  业务类别sb，ht，award
 	 */
	private String businesstype;
	

	/**
 	 *  备注
 	 */
	private String memo;


	/**
	 *  水印id
	 */
	private String waterid;


	/**
	 *  如果type是affix，就是附件类别
	 */
	private String category;


	/**
	 *  排序
	 */
	private Integer seq;
	



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
 	 *  affix、affixurl、freemark、xml、rtf
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}


	/**
 	 *  如果type是freemark，xml，rtf就是模板路径，如果是affix，就是附件id，如果是affixurl就是附件地址
 	 */
	
	public String getUrl (){
		return url;
	}
	public void setUrl (String url){
		  this.url=url;
	}


	/**
 	 *  批次id,关联id
 	 */
	
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		  this.sourceid=sourceid;
	}


	/**
 	 *  业务类别sb，ht，award
 	 */
	
	public String getBusinesstype (){
		return businesstype;
	}
	public void setBusinesstype (String businesstype){
		  this.businesstype=businesstype;
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

	public String getWaterid() {
		return waterid;
	}

	public void setWaterid(String waterid) {
		this.waterid = waterid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	/**
	 * 用于深度拷贝
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}