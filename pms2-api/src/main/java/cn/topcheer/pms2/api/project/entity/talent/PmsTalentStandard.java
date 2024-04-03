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
 *  科技人才-标准表
 */
@Entity
@Table(name="PMS_TALENT_STANDARD")
@ClassInfo(name = "科技人才-标准表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentStandard {

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
     *  标准编号
     */
	@ApiModelProperty("标准编号")
	@TableField("STANDARDNO")
	@Column(columnDefinition = "STANDARDNO")
	private String standardno;

	/**
     *  标准名称
     */
	@ApiModelProperty("标准名称")
	@TableField("NAME")
	@Column(columnDefinition = "NAME")
	private String name;

	/**
     *  标准类别
     */
	@ApiModelProperty("标准类别")
	@TableField("STANDARDTYPE")
	@Column(columnDefinition = "STANDARDTYPE")
	private String standardtype;

	/**
     *  公布时间
     */
	@ApiModelProperty("公布时间")
	@TableField("ANNOUNCEMENTDATE")
	@Column(columnDefinition = "ANNOUNCEMENTDATE")
	@FieldDes(name = "announcementdate", type = "Date", memo = "公布时间")
	@DateTimeFormat(pattern = "yyyy")
	@JsonFormat(pattern = "yyyy", timezone = "GMT+08:00")
	private Date announcementdate;

	/**
     *  执行时间
     */
	@ApiModelProperty("执行时间")
	@TableField("EXECUTIONDATE")
	@Column(columnDefinition = "EXECUTIONDATE")
	@FieldDes(name = "executiondate", type = "Date", memo = "执行时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date executiondate;

	/**
     *  主持/参与
     */
	@ApiModelProperty("主持/参与")
	@TableField("HOSTANDPARTICIPATE")
	@Column(columnDefinition = "HOSTANDPARTICIPATE")
	private String hostandparticipate;

	/**
     *  起草单位总排名
     */
	@ApiModelProperty("起草单位总排名")
	@TableField("UNITSEQ")
	@Column(columnDefinition = "UNITSEQ")
	private String unitseq;

	/**
     *  制定单位
     */
	@ApiModelProperty("制定单位")
	@TableField("UNITNAME")
	@Column(columnDefinition = "UNITNAME")
	private String unitname;

	/**
     *  本人排序
     */
	@ApiModelProperty("本人排序")
	@TableField("SELF_SEQ")
	@Column(columnDefinition = "SELF_SEQ")
	@FieldDes(name = "self_seq", type = "Integer", memo = "本人排序")
	private Integer selfSeq;


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
	*  标准编号
	*/
	public String getStandardno (){
		return standardno;
	}
	public void setStandardno (String standardno){
		this.standardno = standardno;
	}

	/**
	*  标准名称
	*/
	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name = name;
	}

	/**
	*  标准类别
	*/
	public String getStandardtype (){
		return standardtype;
	}
	public void setStandardtype (String standardtype){
		this.standardtype = standardtype;
	}

	/**
	*  公布时间
	*/
	public Date getAnnouncementdate (){
		return announcementdate;
	}
	public void setAnnouncementdate (Date announcementdate){
		this.announcementdate = announcementdate;
	}

	/**
	*  执行时间
	*/
	public Date getExecutiondate (){
		return executiondate;
	}
	public void setExecutiondate (Date executiondate){
		this.executiondate = executiondate;
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
	*  起草单位总排名
	*/
	public String getUnitseq (){
		return unitseq;
	}
	public void setUnitseq (String unitseq){
		this.unitseq = unitseq;
	}

	/**
	*  制定单位
	*/
	public String getUnitname (){
		return unitname;
	}
	public void setUnitname (String unitname){
		this.unitname = unitname;
	}

	/**
	*  本人排序
	*/
	public Integer getSelfSeq (){
		return selfSeq;
	}
	public void setSelfSeq (Integer selfSeq){
		this.selfSeq = selfSeq;
	}

}