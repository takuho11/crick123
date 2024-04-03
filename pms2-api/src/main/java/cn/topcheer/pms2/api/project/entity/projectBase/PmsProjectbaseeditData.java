/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-3 13:58:57
 */
package cn.topcheer.pms2.api.project.entity.projectBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *  
 */
@Entity
@Table(name="PMS_PROJECTBASEEDIT_DATA")
@ClassInfo(name = "", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseeditData {

	/**
     *  
     */
	@FieldDes(name = "id", lenth = "1024", type = "String", memo = "")
	private String id;
	/**
     *  
     */
	@FieldDes(name = "projectbaseid", lenth = "1020", type = "String", memo = "")
	private String projectbaseid;
	/**
     *  
     */
	@FieldDes(name = "editdata", lenth = "2147483647", type = "CLOB", memo = "")
	private String editdata;
	/**
     *  
     */
	@FieldDes(name = "editnum", type = "Integer", memo = "")
	private Integer editnum;
	/**
     *  
     */
	@FieldDes(name = "createdate", type = "Date", memo = "")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date createdate;
	/**
     *  
     */
	@FieldDes(name = "affixchange", lenth = "2147483647", type = "CLOB", memo = "")
	private String affixchange;




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
	*  
	*/
	public String getProjectbaseid (){
	return projectbaseid;
	}
	public void setProjectbaseid (String projectbaseid){
	this.projectbaseid=projectbaseid;
	}

	/**
	*  
	*/
	public String getEditdata (){
	return editdata;
	}
	public void setEditdata (String editdata){
	this.editdata=editdata;
	}

	/**
	*  
	*/
	public Integer getEditnum (){
	return editnum;
	}
	public void setEditnum (Integer editnum){
	this.editnum=editnum;
	}

	/**
	*  
	*/
	public Date getCreatedate (){
	return createdate;
	}
	public void setCreatedate (Date createdate){
	this.createdate=createdate;
	}

	/**
	*  
	*/
	public String getAffixchange (){
	return affixchange;
	}
	public void setAffixchange (String affixchange){
	this.affixchange=affixchange;
	}

}