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
 *  计划项目-年度经费
 */
@Entity
@Table(name="PMS_PROJECTBASE_JFYSOUT")
@ClassInfo(name = "计划项目-年度经费", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseJfysout {

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
     *  合计
     */
	@FieldDes(name = "totalsum", type = "BigDecimal", memo = "合计")
	private BigDecimal totalsum;
	/**
     *  专项省拨经费
     */
	@FieldDes(name = "govtotal", type = "BigDecimal", memo = "专项省拨经费")
	private BigDecimal govtotal;
	/**
     *  自筹经费
     */
	@FieldDes(name = "selftotal", type = "BigDecimal", memo = "自筹经费")
	private BigDecimal selftotal;
	/**
     *  其他
     */
	@FieldDes(name = "othertotal", type = "BigDecimal", memo = "其他")
	private BigDecimal othertotal;
	/**
     *  预算科目名称
     */
	private String name;
	/**
     *  预算数
     */
	@FieldDes(name = "ysjftotal", type = "BigDecimal", memo = "预算数")
	private BigDecimal ysjftotal;
	/**
     *  占总额比重
     */
	@FieldDes(name = "totalpercent", type = "BigDecimal", memo = "占总额比重")
	private BigDecimal totalpercent;
	/**
     *  年份
     */
	private String year;
	/**
     *  编号
     */
	private String no;




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
	*  合计
	*/
	public BigDecimal getTotalsum (){
	return totalsum;
	}
	public void setTotalsum (BigDecimal totalsum){
	this.totalsum=totalsum;
	}

	/**
	*  专项省拨经费
	*/
	public BigDecimal getGovtotal (){
	return govtotal;
	}
	public void setGovtotal (BigDecimal govtotal){
	this.govtotal=govtotal;
	}

	/**
	*  自筹经费
	*/
	public BigDecimal getSelftotal (){
	return selftotal;
	}
	public void setSelftotal (BigDecimal selftotal){
	this.selftotal=selftotal;
	}

	/**
	*  其他
	*/
	public BigDecimal getOthertotal (){
	return othertotal;
	}
	public void setOthertotal (BigDecimal othertotal){
	this.othertotal=othertotal;
	}

	/**
	*  预算科目名称
	*/
	public String getName (){
	return name;
	}
	public void setName (String name){
	this.name=name;
	}

	/**
	*  预算数
	*/
	public BigDecimal getYsjftotal (){
	return ysjftotal;
	}
	public void setYsjftotal (BigDecimal ysjftotal){
	this.ysjftotal=ysjftotal;
	}

	/**
	*  占总额比重
	*/
	public BigDecimal getTotalpercent (){
	return totalpercent;
	}
	public void setTotalpercent (BigDecimal totalpercent){
	this.totalpercent=totalpercent;
	}

	/**
	*  年份
	*/
	public String getYear (){
	return year;
	}
	public void setYear (String year){
	this.year=year;
	}

	/**
	*  编号
	*/
	public String getNo (){
	return no;
	}
	public void setNo (String no){
	this.no=no;
	}

}