/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-11-10 20:43:46
 *
 */
package cn.topcheer.pms2.api.pml.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  列表搜索框与搜索框组合关联
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="PML_SEARCHBOX_COLLECTION")
public class PmlSearchboxCollection {


	/**
 	 *  主键
 	 */
	private String id;
	

	/**
 	 *  搜索框id
 	 */
	private String searchboxid;
	

	/**
 	 *  搜索框组合id
 	 */
	private String collectionid;
	

	/**
 	 *  顺序
 	 */
	private Integer seq;
	



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


	/**
 	 *  搜索框id
 	 */
	
	public String getSearchboxid (){
		return searchboxid;
	}
	public void setSearchboxid (String searchboxid){
		  this.searchboxid=searchboxid;
	}


	/**
 	 *  搜索框组合id
 	 */
	
	public String getCollectionid (){
		return collectionid;
	}
	public void setCollectionid (String collectionid){
		  this.collectionid=collectionid;
	}


	/**
 	 *  顺序
 	 */
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
	}




}