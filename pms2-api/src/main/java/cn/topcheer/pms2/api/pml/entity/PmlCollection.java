/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-11-10 18:31:58
 *
 */
package cn.topcheer.pms2.api.pml.entity;


import cn.topcheer.halberd.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  列表搜索框组合
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="PML_COLLECTION")
public class PmlCollection {


	/**
 	 *  主键
 	 */
	private String id;
	

	/**
 	 *  名称
 	 */
	private String name;
	

	/**
 	 *  业务类型
 	 */
	private String businesstype;
	

	/**
 	 *  顺序
 	 */
	private Integer seq;
	

	/**
 	 *  备注
 	 */
	private String memo;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date createdate;

	/**
	 * 宽度配置
	 */
	@FieldDes(name="widthconfig",lenth="255",type="String",memo="宽度配置")
	private String widthconfig;



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
 	 *  名称
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  业务类型
 	 */
	
	public String getBusinesstype (){
		return businesstype;
	}
	public void setBusinesstype (String businesstype){
		  this.businesstype=businesstype;
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
	 * 创建时间
	 */
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getWidthconfig() {
		return widthconfig;
	}

	public void setWidthconfig(String widthconfig) {
		this.widthconfig = widthconfig;
	}
}