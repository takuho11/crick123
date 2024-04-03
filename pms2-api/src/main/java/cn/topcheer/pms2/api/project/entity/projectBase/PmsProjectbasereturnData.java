/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-3 13:58:58
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
@Table(name="PMS_PROJECTBASERETURN_DATA")
@ClassInfo(name = "", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbasereturnData {

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
	@FieldDes(name = "returndata", lenth = "2147483647", type = "CLOB", memo = "")
	private String returndata;
	/**
     *  
     */
	@FieldDes(name = "returnnum", type = "Integer", memo = "")
	private Integer returnnum;




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
	public String getReturndata (){
	return returndata;
	}
	public void setReturndata (String returndata){
	this.returndata=returndata;
	}

	/**
	*  
	*/
	public Integer getReturnnum (){
	return returnnum;
	}
	public void setReturnnum (Integer returnnum){
	this.returnnum=returnnum;
	}

}