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
 *  科技人才-推荐汇总表
 */
@Entity
@Table(name="PMS_TALENT_SUMMARY")
@ClassInfo(name = "科技人才-推荐汇总表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentSummary {

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
     *  年度
     */
	@ApiModelProperty("年度")
	@TableField("YEAR")
	@Column(columnDefinition = "YEAR")
	private String year;

	/**
     *  填报人
     */
	@ApiModelProperty("填报人")
	@TableField("TBR_NAME")
	@Column(columnDefinition = "TBR_NAME")
	private String tbrName;

	/**
     *  填报人联系方式
     */
	@ApiModelProperty("填报人联系方式")
	@TableField("TBR_MOBILE")
	@Column(columnDefinition = "TBR_MOBILE")
	private String tbrMobile;

	/**
     *  序号
     */
	@ApiModelProperty("序号")
	@TableField("NUM")
	@Column(columnDefinition = "NUM")
	private String num;

	/**
     *  申报人
     */
	@ApiModelProperty("申报人")
	@TableField("SBR_NAME")
	@Column(columnDefinition = "SBR_NAME")
	private String sbrName;

	/**
     *  申报层次
     */
	@ApiModelProperty("申报层次")
	@TableField("APPLICATION_GRADE")
	@Column(columnDefinition = "APPLICATION_GRADE")
	private String applicationGrade;

	/**
     *  用人单位
     */
	@ApiModelProperty("用人单位")
	@TableField("ENTERPRISE_NAME")
	@Column(columnDefinition = "ENTERPRISE_NAME")
	private String enterpriseName;

	/**
     *  引进方式
     */
	@ApiModelProperty("引进方式")
	@TableField("IMPORT_WAY")
	@Column(columnDefinition = "IMPORT_WAY")
	private String importWay;

	/**
     *  引进时间
     */
	@ApiModelProperty("引进时间")
	@TableField("IMPORT_DATE")
	@Column(columnDefinition = "IMPORT_DATE")
	@FieldDes(name = "import_date", type = "Date", memo = "引进时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date importDate;

	/**
     *  认定条件
     */
	@ApiModelProperty("认定条件")
	@TableField("RECOGNITION_CRITERIA")
	@Column(columnDefinition = "RECOGNITION_CRITERIA")
	private String recognitionCriteria;

	/**
     *  填报时间
     */
	@ApiModelProperty("填报时间")
	@TableField("FILL_DATE")
	@Column(columnDefinition = "FILL_DATE")
	@FieldDes(name = "fill_date", type = "Date", memo = "填报时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date fillDate;

	/**
	 *  填报单位
	 */
	@ApiModelProperty("填报单位")
	@TableField("TBDW")
	@Column(columnDefinition = "TBDW")
	private String tbdw;

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
	*  年度
	*/
	public String getYear (){
		return year;
	}
	public void setYear (String year){
		this.year = year;
	}

	/**
	*  填报人
	*/
	public String getTbrName (){
		return tbrName;
	}
	public void setTbrName (String tbrName){
		this.tbrName = tbrName;
	}

	/**
	*  填报人联系方式
	*/
	public String getTbrMobile (){
		return tbrMobile;
	}
	public void setTbrMobile (String tbrMobile){
		this.tbrMobile = tbrMobile;
	}

	/**
	*  序号
	*/
	public String getNum (){
		return num;
	}
	public void setNum (String num){
		this.num = num;
	}

	/**
	*  申报人
	*/
	public String getSbrName (){
		return sbrName;
	}
	public void setSbrName (String sbrName){
		this.sbrName = sbrName;
	}

	/**
	*  申报层次
	*/
	public String getApplicationGrade (){
		return applicationGrade;
	}
	public void setApplicationGrade (String applicationGrade){
		this.applicationGrade = applicationGrade;
	}

	/**
	*  用人单位
	*/
	public String getEnterpriseName (){
		return enterpriseName;
	}
	public void setEnterpriseName (String enterpriseName){
		this.enterpriseName = enterpriseName;
	}

	/**
	*  引进方式
	*/
	public String getImportWay (){
		return importWay;
	}
	public void setImportWay (String importWay){
		this.importWay = importWay;
	}

	/**
	*  引进时间
	*/
	public Date getImportDate (){
		return importDate;
	}
	public void setImportDate (Date importDate){
		this.importDate = importDate;
	}

	/**
	*  认定条件
	*/
	public String getRecognitionCriteria (){
		return recognitionCriteria;
	}
	public void setRecognitionCriteria (String recognitionCriteria){
		this.recognitionCriteria = recognitionCriteria;
	}

	/**
	*  填报时间
	*/
	public Date getFillDate (){
		return fillDate;
	}
	public void setFillDate (Date fillDate){
		this.fillDate = fillDate;
	}

	public String getTbdw() {
		return tbdw;
	}

	public void setTbdw(String tbdw) {
		this.tbdw = tbdw;
	}
}