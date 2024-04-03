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
 *  平台载体经费表
 */
@Entity
@Table(name="PMS_PLATFORM_JFYSOUT")
@ClassInfo(name = "平台载体经费表", module = SysModuleEnum.TECH_PLATFORM, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPlatformJfysout {

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
     *  合计
     */
	@ApiModelProperty("合计")
	@TableField("TOTALSUM")
	@Column(columnDefinition = "TOTALSUM")
	@FieldDes(name = "totalsum", type = "BigDecimal", memo = "合计")
	private BigDecimal totalsum;

	/**
     *  专项省拨经费
     */
	@ApiModelProperty("专项省拨经费")
	@TableField("GOVTOTAL")
	@Column(columnDefinition = "GOVTOTAL")
	@FieldDes(name = "govtotal", type = "BigDecimal", memo = "专项省拨经费")
	private BigDecimal govtotal;

	/**
     *  自筹经费
     */
	@ApiModelProperty("自筹经费")
	@TableField("SELFTOTAL")
	@Column(columnDefinition = "SELFTOTAL")
	@FieldDes(name = "selftotal", type = "BigDecimal", memo = "自筹经费")
	private BigDecimal selftotal;

	/**
     *  其他
     */
	@ApiModelProperty("其他")
	@TableField("OTHERTOTAL")
	@Column(columnDefinition = "OTHERTOTAL")
	@FieldDes(name = "othertotal", type = "BigDecimal", memo = "其他")
	private BigDecimal othertotal;

	/**
     *  预算科目名称
     */
	@ApiModelProperty("预算科目名称")
	@TableField("NAME")
	@Column(columnDefinition = "NAME")
	private String name;

	/**
     *  预算数
     */
	@ApiModelProperty("预算数")
	@TableField("YSJFTOTAL")
	@Column(columnDefinition = "YSJFTOTAL")
	@FieldDes(name = "ysjftotal", type = "BigDecimal", memo = "预算数")
	private BigDecimal ysjftotal;

	/**
     *  占总额比重
     */
	@ApiModelProperty("占总额比重")
	@TableField("TOTALPERCENT")
	@Column(columnDefinition = "TOTALPERCENT")
	@FieldDes(name = "totalpercent", type = "BigDecimal", memo = "占总额比重")
	private BigDecimal totalpercent;

	/**
     *  年份
     */
	@ApiModelProperty("年份")
	@TableField("YEAR")
	@Column(columnDefinition = "YEAR")
	private String year;


	/**
	 *  编号
	 */
	@ApiModelProperty("编号")
	@TableField("NO")
	@Column(columnDefinition = "NO")
	private String no;


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
	*  合计
	*/
	public BigDecimal getTotalsum (){
		return totalsum;
	}
	public void setTotalsum (BigDecimal totalsum){
		this.totalsum = totalsum;
	}

	/**
	*  专项省拨经费
	*/
	public BigDecimal getGovtotal (){
		return govtotal;
	}
	public void setGovtotal (BigDecimal govtotal){
		this.govtotal = govtotal;
	}

	/**
	*  自筹经费
	*/
	public BigDecimal getSelftotal (){
		return selftotal;
	}
	public void setSelftotal (BigDecimal selftotal){
		this.selftotal = selftotal;
	}

	/**
	*  其他
	*/
	public BigDecimal getOthertotal (){
		return othertotal;
	}
	public void setOthertotal (BigDecimal othertotal){
		this.othertotal = othertotal;
	}

	/**
	*  预算科目名称
	*/
	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name = name;
	}

	/**
	*  预算数
	*/
	public BigDecimal getYsjftotal (){
		return ysjftotal;
	}
	public void setYsjftotal (BigDecimal ysjftotal){
		this.ysjftotal = ysjftotal;
	}

	/**
	*  占总额比重
	*/
	public BigDecimal getTotalpercent (){
		return totalpercent;
	}
	public void setTotalpercent (BigDecimal totalpercent){
		this.totalpercent = totalpercent;
	}

	/**
	*  年份
	*/
	public String getYear (){
		return year;
	}
	public void setYear (String year){
		this.year = year;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}