/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2023-11-14 13:52:23
 *
 */
package cn.topcheer.pms2.api.project.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *  
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="SJHJ_XMRY")
public class SjhjXmry {


	/**
     *  主键
     */
    @FieldDes(name="id",lenth="255",type="String",memo="主键")
	private String id;

	/**
     *  
     */
    @FieldDes(name="mainid",lenth="255",type="String",memo="")
	private String mainid;

	/**
     *  
     */
    @FieldDes(name="type",lenth="255",type="String",memo="")
	private String type;

	/**
     *  
     */
    @FieldDes(name="seq",type="Integer",memo="")
	private Integer seq;

	/**
     *  
     */
    @FieldDes(name="sourceid",lenth="255",type="String",memo="")
	private String sourceid;

	/**
     *  
     */
    @FieldDes(name="updatelasttime",type="Date",memo="")
	private Date updatelasttime;

	/**
     *  
     */
    @FieldDes(name="savedate",type="Date",memo="")
	private Date savedate;

	/**
     *  姓名
     */
    @FieldDes(name="name",lenth="255",type="String",memo="姓名")
	private String name;

	/**
     *  联系方式
     */
    @FieldDes(name="mobilephone",lenth="255",type="String",memo="联系方式")
	private String mobilephone;

	/**
     *  作者姓名
     */
    @FieldDes(name="datautnam",lenth="255",type="String",memo="作者姓名")
	private String datautnam;

	/**
     *  工作单位
     */
    @FieldDes(name="datorg",lenth="500",type="String",memo="工作单位")
	private String datorg;

	/**
     *  电子邮箱
     */
    @FieldDes(name="datema",type="Date",memo="电子邮箱")
	private Date datema;

	/**
     *  工作贡献
     */
    @FieldDes(name="datcon",lenth="2000",type="String",memo="工作贡献")
	private String datcon;

	/**
     *  作者简介
     */
    @FieldDes(name="datauthintr",lenth="2000",type="String",memo="作者简介")
	private String datauthintr;



	/**
 	 *  主键
 	 */
	
	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  
 	 */
	
	public String getMainid (){
		return mainid;
	}
	public void setMainid (String mainid){
		  this.mainid=mainid;
	}


	/**
 	 *  
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
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
	}


	/**
 	 *  
 	 */
	
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		  this.sourceid=sourceid;
	}


	/**
 	 *  
 	 */
	
	public Date getUpdatelasttime (){
		return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
		  this.updatelasttime=updatelasttime;
	}


	/**
 	 *  
 	 */
	
	public Date getSavedate (){
		return savedate;
	}
	public void setSavedate (Date savedate){
		  this.savedate=savedate;
	}


	/**
 	 *  姓名
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  联系方式
 	 */
	
	public String getMobilephone (){
		return mobilephone;
	}
	public void setMobilephone (String mobilephone){
		  this.mobilephone=mobilephone;
	}


	/**
 	 *  作者姓名
 	 */
	
	public String getDatautnam (){
		return datautnam;
	}
	public void setDatautnam (String datautnam){
		  this.datautnam=datautnam;
	}


	/**
 	 *  工作单位
 	 */
	
	public String getDatorg (){
		return datorg;
	}
	public void setDatorg (String datorg){
		  this.datorg=datorg;
	}


	/**
 	 *  电子邮箱
 	 */
	
	public Date getDatema (){
		return datema;
	}
	public void setDatema (Date datema){
		  this.datema=datema;
	}


	/**
 	 *  工作贡献
 	 */
	
	public String getDatcon (){
		return datcon;
	}
	public void setDatcon (String datcon){
		  this.datcon=datcon;
	}


	/**
 	 *  作者简介
 	 */
	
	public String getDatauthintr (){
		return datauthintr;
	}
	public void setDatauthintr (String datauthintr){
		  this.datauthintr=datauthintr;
	}




}