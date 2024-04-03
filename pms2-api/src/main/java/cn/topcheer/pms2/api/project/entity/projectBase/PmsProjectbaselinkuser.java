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
@Table(name="PMS_PROJECTBASELINKUSER")
@ClassInfo(name = "", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaselinkuser {

	/**
     *  
     */
	@FieldDes(name = "id", lenth = "50", type = "String", memo = "")
	private String id;
	/**
     *  
     */
	@FieldDes(name = "projectbaseid", lenth = "50", type = "String", memo = "")
	private String projectbaseid;
	/**
     *  
     */
	@FieldDes(name = "userid", lenth = "50", type = "String", memo = "")
	private String userid;
	/**
     *  
     */
	@FieldDes(name = "distribution", lenth = "20", type = "String", memo = "")
	private String distribution;
	/**
     *  
     */
	@FieldDes(name = "distributionone", lenth = "50", type = "String", memo = "")
	private String distributionone;




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
	public String getUserid (){
	return userid;
	}
	public void setUserid (String userid){
	this.userid=userid;
	}

	/**
	*  
	*/
	public String getDistribution (){
	return distribution;
	}
	public void setDistribution (String distribution){
	this.distribution=distribution;
	}

	/**
	*  
	*/
	public String getDistributionone (){
	return distributionone;
	}
	public void setDistributionone (String distributionone){
	this.distributionone=distributionone;
	}

}