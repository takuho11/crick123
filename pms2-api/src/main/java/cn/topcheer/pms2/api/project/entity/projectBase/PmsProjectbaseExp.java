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
 *  计划项目-教育/工作经历
 */
@Entity
@Table(name="PMS_PROJECTBASE_EXP")
@ClassInfo(name = "计划项目-教育/工作经历", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseExp {

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
     *  起始时间
     */
	@FieldDes(name = "startdate", type = "Date", memo = "起始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date startdate;
	/**
     *  结束时间
     */
	@FieldDes(name = "enddate", type = "Date", memo = "结束时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date enddate;
	/**
     *  毕业院校/工作单位
     */
	private String name;
	/**
     *  国家
     */
	private String nation;
	/**
     *  所学专业/从事专业
     */
	private String major;
	/**
     *  学历
     */
	private String education;
	/**
     *  学位
     */
	private String degree;
	/**
     *  职称
     */
	private String title;
	/**
     *  职务
     */
	private String post;
	/**
     *  导师
     */
	private String tutor;
	/**
     *  院系
     */
	private String jtgzdw;
	/**
     *  申请人\参与人
     */
	private String username;




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
	*  起始时间
	*/
	public Date getStartdate (){
	return startdate;
	}
	public void setStartdate (Date startdate){
	this.startdate=startdate;
	}

	/**
	*  结束时间
	*/
	public Date getEnddate (){
	return enddate;
	}
	public void setEnddate (Date enddate){
	this.enddate=enddate;
	}

	/**
	*  毕业院校/工作单位
	*/
	public String getName (){
	return name;
	}
	public void setName (String name){
	this.name=name;
	}

	/**
	*  国家
	*/
	public String getNation (){
	return nation;
	}
	public void setNation (String nation){
	this.nation=nation;
	}

	/**
	*  所学专业/从事专业
	*/
	public String getMajor (){
	return major;
	}
	public void setMajor (String major){
	this.major=major;
	}

	/**
	*  学历
	*/
	public String getEducation (){
	return education;
	}
	public void setEducation (String education){
	this.education=education;
	}

	/**
	*  学位
	*/
	public String getDegree (){
	return degree;
	}
	public void setDegree (String degree){
	this.degree=degree;
	}

	/**
	*  职称
	*/
	public String getTitle (){
	return title;
	}
	public void setTitle (String title){
	this.title=title;
	}

	/**
	*  职务
	*/
	public String getPost (){
	return post;
	}
	public void setPost (String post){
	this.post=post;
	}

	/**
	*  导师
	*/
	public String getTutor (){
	return tutor;
	}
	public void setTutor (String tutor){
	this.tutor=tutor;
	}

	/**
	*  院系
	*/
	public String getJtgzdw (){
	return jtgzdw;
	}
	public void setJtgzdw (String jtgzdw){
	this.jtgzdw=jtgzdw;
	}

	/**
	*  申请人\参与人
	*/
	public String getUsername (){
	return username;
	}
	public void setUsername (String username){
	this.username=username;
	}

}