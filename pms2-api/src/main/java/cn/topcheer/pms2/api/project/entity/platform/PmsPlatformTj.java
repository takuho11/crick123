/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:33:52
 */
package cn.topcheer.pms2.api.project.entity.platform;

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
 *  平台载体统计表
 */
@Entity
@Table(name="PMS_PLATFORM_TJ")
@ClassInfo(name = "平台载体统计表", module = SysModuleEnum.TECH_PLATFORM, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPlatformTj {

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
     *  固定字段:关联主表ID
     */
	@ApiModelProperty("固定字段:关联主表ID")
	@TableField("MAINID")
	@Column(columnDefinition = "MAINID")
	private String mainid;

	/**
     *  固定字段:关联子表ID
     */
	@ApiModelProperty("固定字段:关联子表ID")
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
	private Integer seq;

	/**
     *  固定字段:备注
     */
	@ApiModelProperty("固定字段:备注")
	@TableField("MEMO")
	@Column(columnDefinition = "MEMO")
	private String memo;

	/**
     *  单位研发支出
     */
	@ApiModelProperty("单位研发支出")
	@TableField("RDFUND")
	@Column(columnDefinition = "RDFUND")
	@FieldDes(name = "rdfund", type = "BigDecimal", memo = "单位研发支出")
	private BigDecimal rdfund;

	/**
     *  单位支出总额
     */
	@ApiModelProperty("单位支出总额")
	@TableField("TOTALCOST")
	@Column(columnDefinition = "TOTALCOST")
	@FieldDes(name = "totalcost", type = "BigDecimal", memo = "单位支出总额")
	private BigDecimal totalcost;

	/**
     *  单位收入总额
     */
	@ApiModelProperty("单位收入总额")
	@TableField("TOTALREVENUE")
	@Column(columnDefinition = "TOTALREVENUE")
	@FieldDes(name = "totalrevenue", type = "BigDecimal", memo = "单位收入总额")
	private BigDecimal totalrevenue;

	/**
     *  职工总数
     */
	@ApiModelProperty("职工总数")
	@TableField("RYTOTALNUM")
	@Column(columnDefinition = "RYTOTALNUM")
	private Integer rytotalnum;

	/**
     *  研发人员
     */
	@ApiModelProperty("研发人员")
	@TableField("YFRYNUM")
	@Column(columnDefinition = "YFRYNUM")
	private Integer yfrynum;

	/**
     *  博士学位或高级职称人数
     */
	@ApiModelProperty("博士学位或高级职称人数")
	@TableField("DOCTORORSENIOR")
	@Column(columnDefinition = "DOCTORORSENIOR")
	private Integer doctororsenior;


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
	*  固定字段:关联主表ID
	*/
	public String getMainid (){
		return mainid;
	}
	public void setMainid (String mainid){
		this.mainid = mainid;
	}

	/**
	*  固定字段:关联子表ID
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
	*  单位研发支出
	*/
	public BigDecimal getRdfund (){
		return rdfund;
	}
	public void setRdfund (BigDecimal rdfund){
		this.rdfund = rdfund;
	}

	/**
	*  单位支出总额
	*/
	public BigDecimal getTotalcost (){
		return totalcost;
	}
	public void setTotalcost (BigDecimal totalcost){
		this.totalcost = totalcost;
	}

	/**
	*  单位收入总额
	*/
	public BigDecimal getTotalrevenue (){
		return totalrevenue;
	}
	public void setTotalrevenue (BigDecimal totalrevenue){
		this.totalrevenue = totalrevenue;
	}

	/**
	*  职工总数
	*/
	public Integer getRytotalnum (){
		return rytotalnum;
	}
	public void setRytotalnum (Integer rytotalnum){
		this.rytotalnum = rytotalnum;
	}

	/**
	*  研发人员
	*/
	public Integer getYfrynum (){
		return yfrynum;
	}
	public void setYfrynum (Integer yfrynum){
		this.yfrynum = yfrynum;
	}

	/**
	*  博士学位或高级职称人数
	*/
	public Integer getDoctororsenior (){
		return doctororsenior;
	}
	public void setDoctororsenior (Integer doctororsenior){
		this.doctororsenior = doctororsenior;
	}

}