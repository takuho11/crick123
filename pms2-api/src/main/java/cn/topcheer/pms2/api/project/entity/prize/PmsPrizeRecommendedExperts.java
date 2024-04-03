/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:32:16
 */
package cn.topcheer.pms2.api.project.entity.prize;

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
 *  科技奖励-推荐专家表业务表
 */
@Entity
@Table(name="PMS_PRIZE_RECOMMENDED_EXPERTS")
@ClassInfo(name = "科技奖励-推荐专家表业务表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizeRecommendedExperts {

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
     *  姓名
     */
	@ApiModelProperty("姓名")
	@TableField("NAME")
	@Column(columnDefinition = "NAME")
	private String name;

	/**
     *  证件类型
     */
	@ApiModelProperty("证件类型")
	@TableField("CERTIFICATENAME")
	@Column(columnDefinition = "CERTIFICATENAME")
	private String certificatename;

	/**
     *  证件号码
     */
	@ApiModelProperty("证件号码")
	@TableField("CERTIFICATENUMBER")
	@Column(columnDefinition = "CERTIFICATENUMBER")
	private String certificatenumber;

	/**
     *  院士
     */
	@ApiModelProperty("院士")
	@TableField("ACADEMICIAN")
	@Column(columnDefinition = "ACADEMICIAN")
	private String academician;

	/**
     *  学部
     */
	@ApiModelProperty("学部")
	@TableField("ACADEMY")
	@Column(columnDefinition = "ACADEMY")
	private String academy;

	/**
     *  专家类型
     */
	@ApiModelProperty("专家类型")
	@TableField("EXPERTTYPE")
	@Column(columnDefinition = "EXPERTTYPE")
	private String experttype;

	/**
     *  工作单位
     */
	@ApiModelProperty("工作单位")
	@TableField("WORKPLACE")
	@Column(columnDefinition = "WORKPLACE")
	private String workplace;

	/**
     *  职称
     */
	@ApiModelProperty("职称")
	@TableField("TITLE")
	@Column(columnDefinition = "TITLE")
	private String title;

	/**
     *  学科专业
     */
	@ApiModelProperty("学科专业")
	@TableField("WORKFORPROFESSION")
	@Column(columnDefinition = "WORKFORPROFESSION")
	private String workforprofession;

	/**
     *  通讯地址
     */
	@ApiModelProperty("通讯地址")
	@TableField("ADDRESS")
	@Column(columnDefinition = "ADDRESS")
	private String address;

	/**
     *  邮政编码
     */
	@ApiModelProperty("邮政编码")
	@TableField("POSTALCODE")
	@Column(columnDefinition = "POSTALCODE")
	private String postalcode;

	/**
     *  电子邮箱
     */
	@ApiModelProperty("电子邮箱")
	@TableField("EMAIL")
	@Column(columnDefinition = "EMAIL")
	private String email;

	/**
     *  联系电话
     */
	@ApiModelProperty("联系电话")
	@TableField("MOBILE")
	@Column(columnDefinition = "MOBILE")
	private String mobile;

	/**
     *  办公电话
     */
	@ApiModelProperty("办公电话")
	@TableField("TELPHONE")
	@Column(columnDefinition = "TELPHONE")
	private String telphone;

	/**
     *  责任专家
     */
	@ApiModelProperty("责任专家")
	@TableField("EXPERTFUNCTION")
	@Column(columnDefinition = "EXPERTFUNCTION")
	private String expertfunction;

	/**
     *  推荐意见
     */
	@ApiModelProperty("推荐意见")
	@TableField("SUGGESTION")
	@Column(columnDefinition = "SUGGESTION")
	private String suggestion;

	/**
     *  推荐奖励等级
     */
	@ApiModelProperty("推荐奖励等级")
	@TableField("TJJLDJ")
	@Column(columnDefinition = "TJJLDJ")
	private String tjjldj;

	/**
     *  审核时间
     */
	@ApiModelProperty("审核时间")
	@TableField("RECOMMENDDATE")
	@Column(columnDefinition = "RECOMMENDDATE")
	@FieldDes(name = "recommenddate", type = "Date", memo = "审核时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date recommenddate;

	/**
     *  审核结果
     */
	@ApiModelProperty("审核结果")
	@TableField("RECOMENDRESULT")
	@Column(columnDefinition = "RECOMENDRESULT")
	private String recomendresult;


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
	*  姓名
	*/
	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name = name;
	}

	/**
	*  证件类型
	*/
	public String getCertificatename (){
		return certificatename;
	}
	public void setCertificatename (String certificatename){
		this.certificatename = certificatename;
	}

	/**
	*  证件号码
	*/
	public String getCertificatenumber (){
		return certificatenumber;
	}
	public void setCertificatenumber (String certificatenumber){
		this.certificatenumber = certificatenumber;
	}

	/**
	*  院士
	*/
	public String getAcademician (){
		return academician;
	}
	public void setAcademician (String academician){
		this.academician = academician;
	}

	/**
	*  学部
	*/
	public String getAcademy (){
		return academy;
	}
	public void setAcademy (String academy){
		this.academy = academy;
	}

	/**
	*  专家类型
	*/
	public String getExperttype (){
		return experttype;
	}
	public void setExperttype (String experttype){
		this.experttype = experttype;
	}

	/**
	*  工作单位
	*/
	public String getWorkplace (){
		return workplace;
	}
	public void setWorkplace (String workplace){
		this.workplace = workplace;
	}

	/**
	*  职称
	*/
	public String getTitle (){
		return title;
	}
	public void setTitle (String title){
		this.title = title;
	}

	/**
	*  学科专业
	*/
	public String getWorkforprofession (){
		return workforprofession;
	}
	public void setWorkforprofession (String workforprofession){
		this.workforprofession = workforprofession;
	}

	/**
	*  通讯地址
	*/
	public String getAddress (){
		return address;
	}
	public void setAddress (String address){
		this.address = address;
	}

	/**
	*  邮政编码
	*/
	public String getPostalcode (){
		return postalcode;
	}
	public void setPostalcode (String postalcode){
		this.postalcode = postalcode;
	}

	/**
	*  电子邮箱
	*/
	public String getEmail (){
		return email;
	}
	public void setEmail (String email){
		this.email = email;
	}

	/**
	*  联系电话
	*/
	public String getMobile (){
		return mobile;
	}
	public void setMobile (String mobile){
		this.mobile = mobile;
	}

	/**
	*  办公电话
	*/
	public String getTelphone (){
		return telphone;
	}
	public void setTelphone (String telphone){
		this.telphone = telphone;
	}

	/**
	*  责任专家
	*/
	public String getExpertfunction (){
		return expertfunction;
	}
	public void setExpertfunction (String expertfunction){
		this.expertfunction = expertfunction;
	}

	/**
	*  推荐意见
	*/
	public String getSuggestion (){
		return suggestion;
	}
	public void setSuggestion (String suggestion){
		this.suggestion = suggestion;
	}

	/**
	*  推荐奖励等级
	*/
	public String getTjjldj (){
		return tjjldj;
	}
	public void setTjjldj (String tjjldj){
		this.tjjldj = tjjldj;
	}

	/**
	*  审核时间
	*/
	public Date getRecommenddate (){
		return recommenddate;
	}
	public void setRecommenddate (Date recommenddate){
		this.recommenddate = recommenddate;
	}

	/**
	*  审核结果
	*/
	public String getRecomendresult (){
		return recomendresult;
	}
	public void setRecomendresult (String recomendresult){
		this.recomendresult = recomendresult;
	}

}