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
 *  计划项目-新标准
 */
@Entity
@Table(name="PMS_PROJECTBASE_STANDARD")
@ClassInfo(name = "计划项目-新标准", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseStandard {

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
     *  标准编号
     */
	private String standardno;
	/**
     *  标准名称
     */
	private String standardname;
	/**
     *  标准类别
     */
	private String standardtype;
	/**
     *  公布时间
     */
	@FieldDes(name = "announcementdate", type = "Date", memo = "公布时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date announcementdate;
	/**
     *  执行时间
     */
	@FieldDes(name = "executiondate", type = "Date", memo = "执行时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date executiondate;
	/**
     *  主持/参与
     */
	private String hostandparticipate;
	/**
     *  起草单位总排名
     */
	@FieldDes(name = "unitseq", type = "Integer", memo = "起草单位总排名")
	private Integer unitseq;
	/**
     *  制定单位
     */
	private String unitname;




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
	*  标准编号
	*/
	public String getStandardno (){
	return standardno;
	}
	public void setStandardno (String standardno){
	this.standardno=standardno;
	}

	/**
	*  标准名称
	*/
	public String getStandardname (){
	return standardname;
	}
	public void setStandardname (String standardname){
	this.standardname=standardname;
	}

	/**
	*  标准类别
	*/
	public String getStandardtype (){
	return standardtype;
	}
	public void setStandardtype (String standardtype){
	this.standardtype=standardtype;
	}

	/**
	*  公布时间
	*/
	public Date getAnnouncementdate (){
	return announcementdate;
	}
	public void setAnnouncementdate (Date announcementdate){
	this.announcementdate=announcementdate;
	}

	/**
	*  执行时间
	*/
	public Date getExecutiondate (){
	return executiondate;
	}
	public void setExecutiondate (Date executiondate){
	this.executiondate=executiondate;
	}

	/**
	*  主持/参与
	*/
	public String getHostandparticipate (){
	return hostandparticipate;
	}
	public void setHostandparticipate (String hostandparticipate){
	this.hostandparticipate=hostandparticipate;
	}

	/**
	*  起草单位总排名
	*/
	public Integer getUnitseq (){
	return unitseq;
	}
	public void setUnitseq (Integer unitseq){
	this.unitseq=unitseq;
	}

	/**
	*  制定单位
	*/
	public String getUnitname (){
	return unitname;
	}
	public void setUnitname (String unitname){
	this.unitname=unitname;
	}

}