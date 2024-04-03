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
@Table(name="PMS_PROJECTBASEREPORTFLOW")
@ClassInfo(name = "", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbasereportflow {

	/**
     *  
     */
	@FieldDes(name = "id", lenth = "1020", type = "String", memo = "")
	private String id;
	/**
     *  
     */
	@FieldDes(name = "bussinessid", lenth = "200", type = "String", memo = "")
	private String bussinessid;
	/**
     *  
     */
	@FieldDes(name = "reportenterpriseflow", lenth = "2000", type = "String", memo = "")
	private String reportenterpriseflow;
	/**
     *  
     */
	@FieldDes(name = "reportdepartmentflow", lenth = "2000", type = "String", memo = "")
	private String reportdepartmentflow;
	/**
     *  
     */
	@FieldDes(name = "currententerprise", lenth = "400", type = "String", memo = "")
	private String currententerprise;
	/**
     *  
     */
	@FieldDes(name = "currentdepartment", lenth = "400", type = "String", memo = "")
	private String currentdepartment;
	/**
     *  
     */
	@FieldDes(name = "bussinesstype", lenth = "50", type = "String", memo = "")
	private String bussinesstype;




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
	public String getBussinessid (){
	return bussinessid;
	}
	public void setBussinessid (String bussinessid){
	this.bussinessid=bussinessid;
	}

	/**
	*  
	*/
	public String getReportenterpriseflow (){
	return reportenterpriseflow;
	}
	public void setReportenterpriseflow (String reportenterpriseflow){
	this.reportenterpriseflow=reportenterpriseflow;
	}

	/**
	*  
	*/
	public String getReportdepartmentflow (){
	return reportdepartmentflow;
	}
	public void setReportdepartmentflow (String reportdepartmentflow){
	this.reportdepartmentflow=reportdepartmentflow;
	}

	/**
	*  
	*/
	public String getCurrententerprise (){
	return currententerprise;
	}
	public void setCurrententerprise (String currententerprise){
	this.currententerprise=currententerprise;
	}

	/**
	*  
	*/
	public String getCurrentdepartment (){
	return currentdepartment;
	}
	public void setCurrentdepartment (String currentdepartment){
	this.currentdepartment=currentdepartment;
	}

	/**
	*  
	*/
	public String getBussinesstype (){
	return bussinesstype;
	}
	public void setBussinesstype (String bussinesstype){
	this.bussinesstype=bussinesstype;
	}

}