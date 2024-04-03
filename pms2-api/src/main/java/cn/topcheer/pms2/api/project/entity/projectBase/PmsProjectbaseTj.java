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
 *  计划项目-人员统计
 */
@Entity
@Table(name="PMS_PROJECTBASE_TJ")
@ClassInfo(name = "计划项目-人员统计", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseTj {

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
     *  博后数
     */
	@FieldDes(name = "postdoctor", type = "Integer", memo = "博后数")
	private Integer postdoctor;
	/**
     *  博士数
     */
	@FieldDes(name = "doctor", type = "Integer", memo = "博士数")
	private Integer doctor;
	/**
     *  硕士数
     */
	@FieldDes(name = "master", type = "Integer", memo = "硕士数")
	private Integer master;
	/**
     *  学士数
     */
	@FieldDes(name = "bachelor", type = "Integer", memo = "学士数")
	private Integer bachelor;
	/**
     *  大专及以下
     */
	@FieldDes(name = "juniorcollege", type = "Integer", memo = "大专及以下")
	private Integer juniorcollege;
	/**
     *  正高
     */
	@FieldDes(name = "highsenior", type = "Integer", memo = "正高")
	private Integer highsenior;
	/**
     *  副高
     */
	@FieldDes(name = "subsenior", type = "Integer", memo = "副高")
	private Integer subsenior;
	/**
     *  中级
     */
	@FieldDes(name = "mediumsenior", type = "Integer", memo = "中级")
	private Integer mediumsenior;
	/**
     *  初级
     */
	@FieldDes(name = "primarytitle", type = "Integer", memo = "初级")
	private Integer primarytitle;
	/**
     *  男
     */
	@FieldDes(name = "man", type = "Integer", memo = "男")
	private Integer man;
	/**
     *  女
     */
	@FieldDes(name = "woman", type = "Integer", memo = "女")
	private Integer woman;
	/**
     *  总计
     */
	@FieldDes(name = "total", type = "Integer", memo = "总计")
	private Integer total;
	/**
     *  其他职称
     */
	@FieldDes(name = "othertitle", type = "Integer", memo = "其他职称")
	private Integer othertitle;




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
	*  博后数
	*/
	public Integer getPostdoctor (){
	return postdoctor;
	}
	public void setPostdoctor (Integer postdoctor){
	this.postdoctor=postdoctor;
	}

	/**
	*  博士数
	*/
	public Integer getDoctor (){
	return doctor;
	}
	public void setDoctor (Integer doctor){
	this.doctor=doctor;
	}

	/**
	*  硕士数
	*/
	public Integer getMaster (){
	return master;
	}
	public void setMaster (Integer master){
	this.master=master;
	}

	/**
	*  学士数
	*/
	public Integer getBachelor (){
	return bachelor;
	}
	public void setBachelor (Integer bachelor){
	this.bachelor=bachelor;
	}

	/**
	*  大专及以下
	*/
	public Integer getJuniorcollege (){
	return juniorcollege;
	}
	public void setJuniorcollege (Integer juniorcollege){
	this.juniorcollege=juniorcollege;
	}

	/**
	*  正高
	*/
	public Integer getHighsenior (){
	return highsenior;
	}
	public void setHighsenior (Integer highsenior){
	this.highsenior=highsenior;
	}

	/**
	*  副高
	*/
	public Integer getSubsenior (){
	return subsenior;
	}
	public void setSubsenior (Integer subsenior){
	this.subsenior=subsenior;
	}

	/**
	*  中级
	*/
	public Integer getMediumsenior (){
	return mediumsenior;
	}
	public void setMediumsenior (Integer mediumsenior){
	this.mediumsenior=mediumsenior;
	}

	/**
	*  初级
	*/
	public Integer getPrimarytitle (){
	return primarytitle;
	}
	public void setPrimarytitle (Integer primarytitle){
	this.primarytitle=primarytitle;
	}

	/**
	*  男
	*/
	public Integer getMan (){
	return man;
	}
	public void setMan (Integer man){
	this.man=man;
	}

	/**
	*  女
	*/
	public Integer getWoman (){
	return woman;
	}
	public void setWoman (Integer woman){
	this.woman=woman;
	}

	/**
	*  总计
	*/
	public Integer getTotal (){
	return total;
	}
	public void setTotal (Integer total){
	this.total=total;
	}

	/**
	*  其他职称
	*/
	public Integer getOthertitle (){
	return othertitle;
	}
	public void setOthertitle (Integer othertitle){
	this.othertitle=othertitle;
	}

}