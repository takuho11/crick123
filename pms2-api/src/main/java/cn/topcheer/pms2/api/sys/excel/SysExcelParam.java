/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-10-16 15:55:24
 *
 */
package cn.topcheer.pms2.api.sys.excel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  excel配置---参数表--method参数，前台传参，keyhead，keyvalue
 */
@Entity
@Table(name="SYS_EXCEL_PARAM")
public class SysExcelParam implements Cloneable{


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  参数名字
 	 */
	private String name;
	

	/**
 	 *  类别--method参数，前台传参，keyhead，keyvalue
 	 */
	private String type;
	

	/**
 	 *  关联id
 	 */
	private String sourceid;
	

	/**
 	 *  
 	 */
	private String key;
	

	/**
 	 *  备注,宽度
 	 */
	private String memo;


	/**
	 *  排序
	 */
	private Integer seq;


	/**
	 *
	 */
	private String value;


	/**
	 *其他内容、备注
	 */
	private String othermemo;
	



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
 	 *  参数名字
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  类别--method参数，前台传参，keyhead，keyvalue
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}


	/**
 	 *  关联id
 	 */
	
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		  this.sourceid=sourceid;
	}


	/**
 	 *  
 	 */
	
	public String getKey (){
		return key;
	}
	public void setKey (String key){
		  this.key=key;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOthermemo() {
		return othermemo;
	}

	public void setOthermemo(String othermemo) {
		this.othermemo = othermemo;
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