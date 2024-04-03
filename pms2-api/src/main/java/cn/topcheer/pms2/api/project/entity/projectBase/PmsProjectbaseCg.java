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
 *  计划项目-成果
 */
@Entity
@Table(name="PMS_PROJECTBASE_CG")
@ClassInfo(name = "计划项目-成果", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseCg {

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
     *  成果名称
     */
	private String name;
	/**
     *  完成人
     */
	private String finishman;
	/**
     *  年卷页码
     */
	private String page;
	/**
     *  发表时间
     */
	private String publishdate;
	/**
     *  成果说明
     */
	private String instruction;
	/**
     *  期刊名称
     */
	private String publicationname;
	/**
     *  收录情况
     */
	private String include;
	/**
     *  会议名称
     */
	private String conferencename;
	/**
     *  地点
     */
	private String address;
	/**
     *  出版社
     */
	private String publishinghouse;
	/**
     *  总字数
     */
	private String totalwords;
	/**
     *  奖励类别
     */
	private String awardtype;
	/**
     *  奖励等级
     */
	private String rank;
	/**
     *  授奖部门
     */
	private String department;
	/**
     *  专利号
     */
	private String patentno;




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
	*  成果名称
	*/
	public String getName (){
	return name;
	}
	public void setName (String name){
	this.name=name;
	}

	/**
	*  完成人
	*/
	public String getFinishman (){
	return finishman;
	}
	public void setFinishman (String finishman){
	this.finishman=finishman;
	}

	/**
	*  年卷页码
	*/
	public String getPage (){
	return page;
	}
	public void setPage (String page){
	this.page=page;
	}

	/**
	*  发表时间
	*/
	public String getPublishdate (){
	return publishdate;
	}
	public void setPublishdate (String publishdate){
	this.publishdate=publishdate;
	}

	/**
	*  成果说明
	*/
	public String getInstruction (){
	return instruction;
	}
	public void setInstruction (String instruction){
	this.instruction=instruction;
	}

	/**
	*  期刊名称
	*/
	public String getPublicationname (){
	return publicationname;
	}
	public void setPublicationname (String publicationname){
	this.publicationname=publicationname;
	}

	/**
	*  收录情况
	*/
	public String getInclude (){
	return include;
	}
	public void setInclude (String include){
	this.include=include;
	}

	/**
	*  会议名称
	*/
	public String getConferencename (){
	return conferencename;
	}
	public void setConferencename (String conferencename){
	this.conferencename=conferencename;
	}

	/**
	*  地点
	*/
	public String getAddress (){
	return address;
	}
	public void setAddress (String address){
	this.address=address;
	}

	/**
	*  出版社
	*/
	public String getPublishinghouse (){
	return publishinghouse;
	}
	public void setPublishinghouse (String publishinghouse){
	this.publishinghouse=publishinghouse;
	}

	/**
	*  总字数
	*/
	public String getTotalwords (){
	return totalwords;
	}
	public void setTotalwords (String totalwords){
	this.totalwords=totalwords;
	}

	/**
	*  奖励类别
	*/
	public String getAwardtype (){
	return awardtype;
	}
	public void setAwardtype (String awardtype){
	this.awardtype=awardtype;
	}

	/**
	*  奖励等级
	*/
	public String getRank (){
	return rank;
	}
	public void setRank (String rank){
	this.rank=rank;
	}

	/**
	*  授奖部门
	*/
	public String getDepartment (){
	return department;
	}
	public void setDepartment (String department){
	this.department=department;
	}

	/**
	*  专利号
	*/
	public String getPatentno (){
	return patentno;
	}
	public void setPatentno (String patentno){
	this.patentno=patentno;
	}

}