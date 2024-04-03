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
 *  科技奖励-推荐单位表业务表
 */
@Entity
@Table(name="PMS_PRIZE_RECOMMENDED_UNIT")
@ClassInfo(name = "科技奖励-推荐单位表业务表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizeRecommendedUnit {

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
     *  推荐单位
     */
	@ApiModelProperty("推荐单位")
	@TableField("RECOMMENDNAME")
	@Column(columnDefinition = "RECOMMENDNAME")
	private String recommendname;

	/**
     *  通讯地址
     */
	@ApiModelProperty("通讯地址")
	@TableField("RECOMMENDADDRESS")
	@Column(columnDefinition = "RECOMMENDADDRESS")
	private String recommendaddress;

	/**
     *  联系人
     */
	@ApiModelProperty("联系人")
	@TableField("RECOMMENDCONTACT")
	@Column(columnDefinition = "RECOMMENDCONTACT")
	private String recommendcontact;

	/**
     *  联系电话
     */
	@ApiModelProperty("联系电话")
	@TableField("RECOMMENDMOBILPHONE")
	@Column(columnDefinition = "RECOMMENDMOBILPHONE")
	private String recommendmobilphone;

	/**
     *  电子邮箱
     */
	@ApiModelProperty("电子邮箱")
	@TableField("RECOMMENDEMAIL")
	@Column(columnDefinition = "RECOMMENDEMAIL")
	private String recommendemail;

	/**
     *  传真
     */
	@ApiModelProperty("传真")
	@TableField("RECOMMENDFAX")
	@Column(columnDefinition = "RECOMMENDFAX")
	private String recommendfax;

	/**
     *  推荐意见
     */
	@ApiModelProperty("推荐意见")
	@TableField("RECOMMENDSUGGESTION")
	@Column(columnDefinition = "RECOMMENDSUGGESTION")
	private String recommendsuggestion;

	/**
     *  推荐奖励等级
     */
	@ApiModelProperty("推荐奖励等级")
	@TableField("TJJLDJ")
	@Column(columnDefinition = "TJJLDJ")
	private String tjjldj;

	/**
     *  邮政编码
     */
	@ApiModelProperty("邮政编码")
	@TableField("POSTALCODE")
	@Column(columnDefinition = "POSTALCODE")
	private String postalcode;

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
	*  推荐单位
	*/
	public String getRecommendname (){
		return recommendname;
	}
	public void setRecommendname (String recommendname){
		this.recommendname = recommendname;
	}

	/**
	*  通讯地址
	*/
	public String getRecommendaddress (){
		return recommendaddress;
	}
	public void setRecommendaddress (String recommendaddress){
		this.recommendaddress = recommendaddress;
	}

	/**
	*  联系人
	*/
	public String getRecommendcontact (){
		return recommendcontact;
	}
	public void setRecommendcontact (String recommendcontact){
		this.recommendcontact = recommendcontact;
	}

	/**
	*  联系电话
	*/
	public String getRecommendmobilphone (){
		return recommendmobilphone;
	}
	public void setRecommendmobilphone (String recommendmobilphone){
		this.recommendmobilphone = recommendmobilphone;
	}

	/**
	*  电子邮箱
	*/
	public String getRecommendemail (){
		return recommendemail;
	}
	public void setRecommendemail (String recommendemail){
		this.recommendemail = recommendemail;
	}

	/**
	*  传真
	*/
	public String getRecommendfax (){
		return recommendfax;
	}
	public void setRecommendfax (String recommendfax){
		this.recommendfax = recommendfax;
	}

	/**
	*  推荐意见
	*/
	public String getRecommendsuggestion (){
		return recommendsuggestion;
	}
	public void setRecommendsuggestion (String recommendsuggestion){
		this.recommendsuggestion = recommendsuggestion;
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
	*  邮政编码
	*/
	public String getPostalcode (){
		return postalcode;
	}
	public void setPostalcode (String postalcode){
		this.postalcode = postalcode;
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