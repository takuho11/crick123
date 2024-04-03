/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:30:13
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
 *  科技人才-申报情况表
 */
@Entity
@Table(name="PMS_TALENT_APPLICATION")
@ClassInfo(name = "科技人才-申报情况表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentApplication {

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
     *  是否申请经济贡献奖励
     */
	@ApiModelProperty("是否申请经济贡献奖励")
	@TableField("ECONOMY_REWARD")
	@Column(columnDefinition = "ECONOMY_REWARD")
	private String economyReward;

	/**
     *  企业当年给予申报人研发成果转化后的奖励和报酬（或绩效奖励）金额（万元）
     */
	@ApiModelProperty("企业当年给予申报人研发成果转化后的奖励和报酬（或绩效奖励）金额（万元）")
	@TableField("ENTERPRISE_AMOUNT")
	@Column(columnDefinition = "ENTERPRISE_AMOUNT")
	@FieldDes(name = "enterprise_amount", type = "BigDecimal", memo = "企业当年给予申报人研发成果转化后的奖励和报酬（或绩效奖励）金额（万元）")
	private BigDecimal enterpriseAmount;

	/**
     *  申报人当年所得研发成果转化后的奖励和报酬（或绩效奖励）缴纳个人所得税金额
     */
	@ApiModelProperty("申报人当年所得研发成果转化后的奖励和报酬（或绩效奖励）缴纳个人所得税金额")
	@TableField("TAX_AMOUNT")
	@Column(columnDefinition = "TAX_AMOUNT")
	@FieldDes(name = "tax_amount", type = "BigDecimal", memo = "申报人当年所得研发成果转化后的奖励和报酬（或绩效奖励）缴纳个人所得税金额")
	private BigDecimal taxAmount;

	/**
     *  是否申请科研贡献奖励（限领军人才，拔尖人才或优秀人才取得更高层次科技成果的，按照就高原则给予奖励）
     */
	@ApiModelProperty("是否申请科研贡献奖励（限领军人才，拔尖人才或优秀人才取得更高层次科技成果的，按照就高原则给予奖励）")
	@TableField("CONTRIBUTE_REWARD")
	@Column(columnDefinition = "CONTRIBUTE_REWARD")
	private String contributeReward;

	/**
     *  符合科研贡献奖励的条件
     */
	@ApiModelProperty("符合科研贡献奖励的条件")
	@TableField("CONFORM_CONTRIBUTE")
	@Column(columnDefinition = "CONFORM_CONTRIBUTE")
	private String conformContribute;


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
	*  是否申请经济贡献奖励
	*/
	public String getEconomyReward (){
		return economyReward;
	}
	public void setEconomyReward (String economyReward){
		this.economyReward = economyReward;
	}

	/**
	*  企业当年给予申报人研发成果转化后的奖励和报酬（或绩效奖励）金额（万元）
	*/
	public BigDecimal getEnterpriseAmount (){
		return enterpriseAmount;
	}
	public void setEnterpriseAmount (BigDecimal enterpriseAmount){
		this.enterpriseAmount = enterpriseAmount;
	}

	/**
	*  申报人当年所得研发成果转化后的奖励和报酬（或绩效奖励）缴纳个人所得税金额
	*/
	public BigDecimal getTaxAmount (){
		return taxAmount;
	}
	public void setTaxAmount (BigDecimal taxAmount){
		this.taxAmount = taxAmount;
	}

	/**
	*  是否申请科研贡献奖励（限领军人才，拔尖人才或优秀人才取得更高层次科技成果的，按照就高原则给予奖励）
	*/
	public String getContributeReward (){
		return contributeReward;
	}
	public void setContributeReward (String contributeReward){
		this.contributeReward = contributeReward;
	}

	/**
	*  符合科研贡献奖励的条件
	*/
	public String getConformContribute (){
		return conformContribute;
	}
	public void setConformContribute (String conformContribute){
		this.conformContribute = conformContribute;
	}

}