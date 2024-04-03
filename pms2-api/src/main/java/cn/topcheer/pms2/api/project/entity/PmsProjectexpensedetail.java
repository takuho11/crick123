/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2016-10-10 10:10:33
 *
 */
package cn.topcheer.pms2.api.project.entity;

import cn.topcheer.halberd.app.api.utils.Util;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 *  
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="PMS_PROJECTEXPENSEDETAIL")
public class PmsProjectexpensedetail {


	/**
 	 *  合作费
 	 */
	private Double cooperationsum;
	

	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  名称及规格型号
 	 */
	private String nameandtypecode;
	

	/**
 	 *  "数量(台/套)
"
 	 */
	private Double instrumentcount;
	
	
	/**
 	 *  单价(万元)
 	 */
	private BigDecimal instrumentprise;
	

	/**
 	 *  金额(万元)
 	 */
	private BigDecimal instrumenttotalsum;
	

	/**
 	 *  资金来源
 	 */
	private String capitalsource;
	

	/**
 	 *  用途说明
 	 */
	private String capitaluse;
	

	/**
 	 *  项目ID
 	 */
	private String projectbaseid;
	

	/**
 	 *  企业ID
 	 */
	private String enterpriseoruserid;
	

	/**
 	 *  企业已有仪器id
 	 */
	private String enterprisehasinstrument;
	

	/**
 	 *  省科技厅拨款
 	 */
	private Double skjtbk;
	

	/**
 	 *  自筹或其他
 	 */
	private Double zcqt;
	

	/**
 	 *  
 	 */
	private Integer seq;
	

	/**
 	 *  生产国别
 	 */
	private String procountry;
	

	/**
 	 *  主要技术指标
 	 */
	private String maintechnology;
	

	/**
 	 *  租赁单位
 	 */
	private String rentunit;
	

	/**
 	 *  租赁费用
 	 */
	private Double rentsum;
	

	/**
 	 *  计量单位
 	 */
	private String jldw;
	

	/**
 	 *  类别 设备、材料、测试加工、合作交流、劳务费、
 	 */
	private String type;
	

	/**
 	 *  
 	 */
	private String rentname;
	

	/**
 	 *  
 	 */
	private Double rentprise;
	

	/**
 	 *  
 	 */
	private Double rentcapitaluse;
	

	/**
 	 *  
 	 */
	private String country;
	

	/**
 	 *  
 	 */
	private String performance;
	

	/**
 	 *  项目申报书ID
 	 */
	private String xmsbsid;
	



	/**
 	 *  合作费
 	 */
	

	/**
 	 *  
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public Double getCooperationsum() {
		return cooperationsum;
	}
	public void setCooperationsum(Double cooperationsum) {
		this.cooperationsum = cooperationsum;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  名称及规格型号
 	 */
	
	public String getNameandtypecode (){
		return Util.convertFormatString(nameandtypecode);
	}
	public void setNameandtypecode (String nameandtypecode){
		  this.nameandtypecode=nameandtypecode;
	}


	/**
 	 *  "数量(台/套)
"
 	 */
	
	public Double getInstrumentcount (){
		return instrumentcount;
	}
	public void setInstrumentcount (Double instrumentcount){
		  this.instrumentcount=instrumentcount;
	}


	/**
 	 *  单价(万元)
 	 */
	
	/*public Double getInstrumentprise (){
		return instrumentprise;
	}
	public void setInstrumentprise (Double instrumentprise){
		  this.instrumentprise=instrumentprise;
	}*/


	/**
 	 *  金额(万元)
 	 */
	
	/*public Double getInstrumenttotalsum (){
		return instrumenttotalsum;
	}
	public void setInstrumenttotalsum (Double instrumenttotalsum){
		  this.instrumenttotalsum=instrumenttotalsum;
	}*/


	/**
 	 *  资金来源
 	 */
	
	public String getCapitalsource (){
		return capitalsource;
	}
	public BigDecimal getInstrumenttotalsum() {
		return instrumenttotalsum;
	}
	public void setInstrumenttotalsum(BigDecimal instrumenttotalsum) {
		this.instrumenttotalsum = instrumenttotalsum;
	}
	public void setCapitalsource (String capitalsource){
		  this.capitalsource=capitalsource;
	}


	/**
 	 *  用途说明
 	 */
	
	public String getCapitaluse (){
		return Util.convertFormatString(capitaluse);
	}
	public void setCapitaluse (String capitaluse){
		  this.capitaluse=capitaluse;
	}


	/**
 	 *  项目ID
 	 */
	
	public String getProjectbaseid (){
		return projectbaseid;
	}
	public void setProjectbaseid (String projectbaseid){
		  this.projectbaseid=projectbaseid;
	}


	/**
 	 *  企业ID
 	 */
	
	public String getEnterpriseoruserid (){
		return enterpriseoruserid;
	}
	public void setEnterpriseoruserid (String enterpriseoruserid){
		  this.enterpriseoruserid=enterpriseoruserid;
	}


	/**
 	 *  企业已有仪器id
 	 */
	
	public String getEnterprisehasinstrument (){
		return enterprisehasinstrument;
	}
	public void setEnterprisehasinstrument (String enterprisehasinstrument){
		  this.enterprisehasinstrument=enterprisehasinstrument;
	}


	/**
 	 *  省科技厅拨款
 	 */
	
	public Double getSkjtbk (){
		return skjtbk;
	}
	public void setSkjtbk (Double skjtbk){
		  this.skjtbk=skjtbk;
	}


	/**
 	 *  自筹或其他
 	 */
	
	public Double getZcqt (){
		return zcqt;
	}
	public void setZcqt (Double zcqt){
		  this.zcqt=zcqt;
	}


	/**
 	 *  
 	 */
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
	}


	/**
 	 *  生产国别
 	 */
	
	public String getProcountry (){
		return procountry;
	}
	public void setProcountry (String procountry){
		  this.procountry=procountry;
	}


	/**
 	 *  主要技术指标
 	 */
	
	public String getMaintechnology (){
		return maintechnology;
	}
	public void setMaintechnology (String maintechnology){
		  this.maintechnology=maintechnology;
	}


	/**
 	 *  租赁单位
 	 */
	
	public String getRentunit (){
		return rentunit;
	}
	public void setRentunit (String rentunit){
		  this.rentunit=rentunit;
	}


	/**
 	 *  租赁费用
 	 */
	
	public Double getRentsum (){
		return rentsum;
	}
	public void setRentsum (Double rentsum){
		  this.rentsum=rentsum;
	}


	/**
 	 *  计量单位
 	 */
	
	public String getJldw (){
		return jldw;
	}
	public void setJldw (String jldw){
		  this.jldw=jldw;
	}


	/**
 	 *  类别 设备、材料、测试加工、合作交流、劳务费、
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}


	/**
 	 *  
 	 */
	
	public String getRentname (){
		return rentname;
	}
	public void setRentname (String rentname){
		  this.rentname=rentname;
	}


	/**
 	 *  
 	 */
	
	public Double getRentprise (){
		return rentprise;
	}
	public void setRentprise (Double rentprise){
		  this.rentprise=rentprise;
	}


	/**
 	 *  
 	 */
	
	public Double getRentcapitaluse (){
		return rentcapitaluse;
	}
	public void setRentcapitaluse (Double rentcapitaluse){
		  this.rentcapitaluse=rentcapitaluse;
	}


	/**
 	 *  
 	 */
	
	public String getCountry (){
		return country;
	}
	public void setCountry (String country){
		  this.country=country;
	}


	/**
 	 *  
 	 */
	
	public String getPerformance (){
		return performance;
	}
	public void setPerformance (String performance){
		  this.performance=performance;
	}


	/**
 	 *  项目申报书ID
 	 */
	
	public String getXmsbsid (){
		return xmsbsid;
	}
	public void setXmsbsid (String xmsbsid){
		  this.xmsbsid=xmsbsid;
	}
	public BigDecimal getInstrumentprise() {
		return instrumentprise;
	}
	public void setInstrumentprise(BigDecimal instrumentprise) {
		this.instrumentprise = instrumentprise;
	}




}