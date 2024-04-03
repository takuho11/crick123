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
 *  计划项目-奖励
 */
@Entity
@Table(name="PMS_PROJECTBASE_AWARD")
@ClassInfo(name = "计划项目-奖励", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseAward {

	/**
     *  固定字段:唯一标识
     */
	@FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
	private String id;
	/**
     *  固定字段:数据类型
     */
	private String type;
	/**
     *  固定字段:关联主表id
     */
	private String mainid;
	/**
     *  固定字段:关联子表id
     */
	private String sourceid;
	/**
     *  固定字段:第一次保存时间
     */
	@FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date savedate;
	/**
     *  固定字段:每次更新数据时间
     */
	@FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date updatelasttime;
	/**
     *  固定字段:排序
     */
	@FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
	private Integer seq;
	/**
     *  固定字段:备注
     */
	private String memo;
	/**
     *  类别
     */
	private String awardtype;
	/**
     *  获奖项目
     */
	private String awardprojectname;
	/**
     *  奖励级别
     */
	private String awardlevel;
	/**
     *  奖励名称
     */
	private String awardname;
	/**
     *  获奖人员
     */
	private String username;
	/**
     *  获奖单位类型
     */
	private String unittype;
	/**
     *  获奖排名
     */
	private String awardranking;
	/**
     *  授奖部门
     */
	private String awarddepartment;
	/**
     *  证书编号
     */
	private String certificateno;
	/**
     *  获奖时间
     */
	private String awarddate;
	/**
     *  本单位获奖排序
     */
	private String unitseq;
	/**
     *  本单位主要获奖人员排序
     */
	private String userseq;

	/**
	*  固定字段:唯一标识
	*/
		@Id
	public String getId (){
	return id;
	}
	public void setId (String id){
	this.id=id;
	}

	/**
	*  固定字段:数据类型
	*/
	public String getType (){
	return type;
	}
	public void setType (String type){
	this.type=type;
	}

	/**
	*  固定字段:关联主表id
	*/
	public String getMainid (){
	return mainid;
	}
	public void setMainid (String mainid){
	this.mainid=mainid;
	}

	/**
	*  固定字段:关联子表id
	*/
	public String getSourceid (){
	return sourceid;
	}
	public void setSourceid (String sourceid){
	this.sourceid=sourceid;
	}

	/**
	*  固定字段:第一次保存时间
	*/
	public Date getSavedate (){
	return savedate;
	}
	public void setSavedate (Date savedate){
	this.savedate=savedate;
	}

	/**
	*  固定字段:每次更新数据时间
	*/
	public Date getUpdatelasttime (){
	return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
	this.updatelasttime=updatelasttime;
	}

	/**
	*  固定字段:排序
	*/
	public Integer getSeq (){
	return seq;
	}
	public void setSeq (Integer seq){
	this.seq=seq;
	}

	/**
	*  固定字段:备注
	*/
	public String getMemo (){
	return memo;
	}
	public void setMemo (String memo){
	this.memo=memo;
	}

	/**
	*  类别
	*/
	public String getAwardtype (){
	return awardtype;
	}
	public void setAwardtype (String awardtype){
	this.awardtype=awardtype;
	}

	/**
	*  获奖项目
	*/
	public String getAwardprojectname (){
	return awardprojectname;
	}
	public void setAwardprojectname (String awardprojectname){
	this.awardprojectname=awardprojectname;
	}

	/**
	*  奖励级别
	*/
	public String getAwardlevel (){
	return awardlevel;
	}
	public void setAwardlevel (String awardlevel){
	this.awardlevel=awardlevel;
	}

	/**
	*  奖励名称
	*/
	public String getAwardname (){
	return awardname;
	}
	public void setAwardname (String awardname){
	this.awardname=awardname;
	}

	/**
	*  获奖人员
	*/
	public String getUsername (){
	return username;
	}
	public void setUsername (String username){
	this.username=username;
	}

	/**
	*  获奖单位类型
	*/
	public String getUnittype (){
	return unittype;
	}
	public void setUnittype (String unittype){
	this.unittype=unittype;
	}

	/**
	*  获奖排名
	*/
	public String getAwardranking (){
	return awardranking;
	}
	public void setAwardranking (String awardranking){
	this.awardranking=awardranking;
	}

	/**
	*  授奖部门
	*/
	public String getAwarddepartment (){
	return awarddepartment;
	}
	public void setAwarddepartment (String awarddepartment){
	this.awarddepartment=awarddepartment;
	}

	/**
	*  证书编号
	*/
	public String getCertificateno (){
	return certificateno;
	}
	public void setCertificateno (String certificateno){
	this.certificateno=certificateno;
	}

	/**
	*  获奖时间
	*/
	public String getAwarddate (){
	return awarddate;
	}
	public void setAwarddate (String awarddate){
	this.awarddate=awarddate;
	}

	/**
	*  本单位获奖排序
	*/
	public String getUnitseq (){
	return unitseq;
	}
	public void setUnitseq (String unitseq){
	this.unitseq=unitseq;
	}

	/**
	*  本单位主要获奖人员排序
	*/
	public String getUserseq (){
	return userseq;
	}
	public void setUserseq (String userseq){
	this.userseq=userseq;
	}
}
