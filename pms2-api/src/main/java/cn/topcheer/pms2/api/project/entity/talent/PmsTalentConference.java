/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:30:14
 */
package cn.topcheer.pms2.api.project.entity.talent;

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
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  科技人才-会议表
 */
@Entity
@Table(name="PMS_TALENT_CONFERENCE")
@ClassInfo(name = "科技人才-会议表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentConference {

	/**
     *  固定字段:唯一标识
     */
	@ApiModelProperty("固定字段:唯一标识")
	@TableField("ID")
	@Column(columnDefinition = "ID")
	@FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
	private String id;

	/**
     *  固定字段:数据类型
     */
	@ApiModelProperty("固定字段:数据类型")
	@TableField("TYPE")
	@Column(columnDefinition = "TYPE")
	private String type;

	/**
     *  固定字段:关联主表id
     */
	@ApiModelProperty("固定字段:关联主表id")
	@TableField("MAINID")
	@Column(columnDefinition = "MAINID")
	private String mainid;

	/**
     *  固定字段:关联子表id
     */
	@ApiModelProperty("固定字段:关联子表id")
	@TableField("SOURCEID")
	@Column(columnDefinition = "SOURCEID")
	private String sourceid;

	/**
     *  固定字段:第一次保存时间
     */
	@ApiModelProperty("固定字段:第一次保存时间")
	@TableField("SAVEDATE")
	@Column(columnDefinition = "SAVEDATE")
	@FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date savedate;

	/**
     *  固定字段:每次更新数据时间
     */
	@ApiModelProperty("固定字段:每次更新数据时间")
	@TableField("UPDATELASTTIME")
	@Column(columnDefinition = "UPDATELASTTIME")
	@FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date updatelasttime;

	/**
     *  固定字段:排序
     */
	@ApiModelProperty("固定字段:排序")
	@TableField("SEQ")
	@Column(columnDefinition = "SEQ")
	@FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
	private Integer seq;

	/**
     *  固定字段:备注
     */
	@ApiModelProperty("固定字段:备注")
	@TableField("MEMO")
	@Column(columnDefinition = "MEMO")
	private String memo;

	/**
     *  会议名称
     */
	@ApiModelProperty("会议名称")
	@TableField("NAME")
	@Column(columnDefinition = "NAME")
	private String name;

	/**
     *  报告名称
     */
	@ApiModelProperty("报告名称")
	@TableField("REPORT_NAME")
	@Column(columnDefinition = "REPORT_NAME")
	private String reportName;

	/**
     *  会议时间
     */
	@ApiModelProperty("会议时间")
	@TableField("CONFERENCE_DATE")
	@Column(columnDefinition = "CONFERENCE_DATE")
	@FieldDes(name = "conference_date", type = "Date", memo = "会议时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date conferenceDate;

	/**
     *  会议类型
     */
	@ApiModelProperty("会议类型")
	@TableField("CONFERENCTTYPE")
	@Column(columnDefinition = "CONFERENCTTYPE")
	private String conferencttype;

	/**
     *  主持/参与
     */
	@ApiModelProperty("主持/参与")
	@TableField("HOSTANDPARTICIPATE")
	@Column(columnDefinition = "HOSTANDPARTICIPATE")
	private String hostandparticipate;

	/**
     *  主办方
     */
	@ApiModelProperty("主办方")
	@TableField("SPONSOR")
	@Column(columnDefinition = "SPONSOR")
	private String sponsor;

	/**
     *  地点
     */
	@ApiModelProperty("地点")
	@TableField("ADDRESS")
	@Column(columnDefinition = "ADDRESS")
	private String address;

	/**
     *  报告类别
     */
	@ApiModelProperty("报告类别")
	@TableField("REPORT_TYPE")
	@Column(columnDefinition = "REPORT_TYPE")
	private String reportType;


	/**
	*  固定字段:唯一标识
	*/
		@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		this.id = id;
	}

	/**
	*  固定字段:数据类型
	*/
	public String getType (){
		return type;
	}
	public void setType (String type){
		this.type = type;
	}

	/**
	*  固定字段:关联主表id
	*/
	public String getMainid (){
		return mainid;
	}
	public void setMainid (String mainid){
		this.mainid = mainid;
	}

	/**
	*  固定字段:关联子表id
	*/
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		this.sourceid = sourceid;
	}

	/**
	*  固定字段:第一次保存时间
	*/
	public Date getSavedate (){
		return savedate;
	}
	public void setSavedate (Date savedate){
		this.savedate = savedate;
	}

	/**
	*  固定字段:每次更新数据时间
	*/
	public Date getUpdatelasttime (){
		return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
		this.updatelasttime = updatelasttime;
	}

	/**
	*  固定字段:排序
	*/
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		this.seq = seq;
	}

	/**
	*  固定字段:备注
	*/
	public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		this.memo = memo;
	}

	/**
	*  会议名称
	*/
	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name = name;
	}

	/**
	*  报告名称
	*/
	public String getReportName (){
		return reportName;
	}
	public void setReportName (String reportName){
		this.reportName = reportName;
	}

	/**
	*  会议时间
	*/
	public Date getConferenceDate (){
		return conferenceDate;
	}
	public void setConferenceDate (Date conferenceDate){
		this.conferenceDate = conferenceDate;
	}

	/**
	*  会议类型
	*/
	public String getConferencttype (){
		return conferencttype;
	}
	public void setConferencttype (String conferencttype){
		this.conferencttype = conferencttype;
	}

	/**
	*  主持/参与
	*/
	public String getHostandparticipate (){
		return hostandparticipate;
	}
	public void setHostandparticipate (String hostandparticipate){
		this.hostandparticipate = hostandparticipate;
	}

	/**
	*  主办方
	*/
	public String getSponsor (){
		return sponsor;
	}
	public void setSponsor (String sponsor){
		this.sponsor = sponsor;
	}

	/**
	*  地点
	*/
	public String getAddress (){
		return address;
	}
	public void setAddress (String address){
		this.address = address;
	}

	/**
	*  报告类别
	*/
	public String getReportType (){
		return reportType;
	}
	public void setReportType (String reportType){
		this.reportType = reportType;
	}

}