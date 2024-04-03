/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-4-19 11:02:54
 *
 */
package cn.topcheer.pms2.api.pml.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="PML_GRIDTABS")
public class PmlGridtabs {


	/**
 	 *  唯一主键id
 	 */
	private String id;
	

	/**
 	 *  当前位置（列表集合tabs名词）
 	 */
	private String name;
	

	/**
 	 *  通用url上的参数，tabsType = xxxx
 	 */
	private String type;
	

	/**
 	 *  备注
 	 */
	private String remarks;
	

	/**
 	 *  排序
 	 */
	private Integer seq;
	

	/**
 	 *  创建时间
 	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date createdate;




	/**
 	 *  唯一主键id
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  当前位置（列表集合tabs名词）
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  通用url上的参数，tabsType = xxxx
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
	
	public String getRemarks (){
		return remarks;
	}
	public void setRemarks (String remarks){
		  this.remarks=remarks;
	}


	/**
 	 *  排序
 	 */
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
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



}