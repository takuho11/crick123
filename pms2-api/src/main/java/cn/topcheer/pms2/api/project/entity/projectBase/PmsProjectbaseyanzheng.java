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
@Table(name="PMS_PROJECTBASEYANZHENG")
@ClassInfo(name = "", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseyanzheng {

	/**
     *  
     */
	@FieldDes(name = "id", lenth = "255", type = "String", memo = "")
	private String id;
	/**
     *  
     */
	@FieldDes(name = "projectbaseid", lenth = "255", type = "String", memo = "")
	private String projectbaseid;
	/**
     *  
     */
	@FieldDes(name = "one", lenth = "255", type = "String", memo = "")
	private String one;
	/**
     *  
     */
	@FieldDes(name = "two", lenth = "255", type = "String", memo = "")
	private String two;
	/**
     *  
     */
	@FieldDes(name = "three", lenth = "255", type = "String", memo = "")
	private String three;
	/**
     *  
     */
	@FieldDes(name = "four", lenth = "255", type = "String", memo = "")
	private String four;
	/**
     *  
     */
	@FieldDes(name = "five", lenth = "255", type = "String", memo = "")
	private String five;
	/**
     *  
     */
	@FieldDes(name = "six", lenth = "255", type = "String", memo = "")
	private String six;
	/**
     *  
     */
	@FieldDes(name = "seven", lenth = "255", type = "String", memo = "")
	private String seven;
	/**
     *  
     */
	@FieldDes(name = "eight", lenth = "255", type = "String", memo = "")
	private String eight;
	/**
     *  
     */
	@FieldDes(name = "memo1", lenth = "1000", type = "String", memo = "")
	private String memo1;
	/**
     *  
     */
	@FieldDes(name = "memo2", lenth = "1000", type = "String", memo = "")
	private String memo2;
	/**
     *  
     */
	@FieldDes(name = "memo3", lenth = "1000", type = "String", memo = "")
	private String memo3;
	/**
     *  
     */
	@FieldDes(name = "memo4", lenth = "1000", type = "String", memo = "")
	private String memo4;
	/**
     *  
     */
	@FieldDes(name = "memo5", lenth = "1000", type = "String", memo = "")
	private String memo5;
	/**
     *  
     */
	@FieldDes(name = "memo6", lenth = "1000", type = "String", memo = "")
	private String memo6;
	/**
     *  
     */
	@FieldDes(name = "memo7", lenth = "1000", type = "String", memo = "")
	private String memo7;




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
	public String getOne (){
	return one;
	}
	public void setOne (String one){
	this.one=one;
	}

	/**
	*  
	*/
	public String getTwo (){
	return two;
	}
	public void setTwo (String two){
	this.two=two;
	}

	/**
	*  
	*/
	public String getThree (){
	return three;
	}
	public void setThree (String three){
	this.three=three;
	}

	/**
	*  
	*/
	public String getFour (){
	return four;
	}
	public void setFour (String four){
	this.four=four;
	}

	/**
	*  
	*/
	public String getFive (){
	return five;
	}
	public void setFive (String five){
	this.five=five;
	}

	/**
	*  
	*/
	public String getSix (){
	return six;
	}
	public void setSix (String six){
	this.six=six;
	}

	/**
	*  
	*/
	public String getSeven (){
	return seven;
	}
	public void setSeven (String seven){
	this.seven=seven;
	}

	/**
	*  
	*/
	public String getEight (){
	return eight;
	}
	public void setEight (String eight){
	this.eight=eight;
	}

	/**
	*  
	*/
	public String getMemo1 (){
	return memo1;
	}
	public void setMemo1 (String memo1){
	this.memo1=memo1;
	}

	/**
	*  
	*/
	public String getMemo2 (){
	return memo2;
	}
	public void setMemo2 (String memo2){
	this.memo2=memo2;
	}

	/**
	*  
	*/
	public String getMemo3 (){
	return memo3;
	}
	public void setMemo3 (String memo3){
	this.memo3=memo3;
	}

	/**
	*  
	*/
	public String getMemo4 (){
	return memo4;
	}
	public void setMemo4 (String memo4){
	this.memo4=memo4;
	}

	/**
	*  
	*/
	public String getMemo5 (){
	return memo5;
	}
	public void setMemo5 (String memo5){
	this.memo5=memo5;
	}

	/**
	*  
	*/
	public String getMemo6 (){
	return memo6;
	}
	public void setMemo6 (String memo6){
	this.memo6=memo6;
	}

	/**
	*  
	*/
	public String getMemo7 (){
	return memo7;
	}
	public void setMemo7 (String memo7){
	this.memo7=memo7;
	}

}