/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-1-19 15:17:52
 *
 */
package cn.topcheer.pms2.api.pdfDownload;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 *  pdf配置表水印
 */
@Entity
@Table(name="PMS_DOWNCONFIGWATER")
public class PmsDownconfigwater implements Cloneable{


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  水印内容
 	 */
	private String watermarkname;
	

	/**
 	 *  水印透明度填充不透明
 	 */
	private String fillopacity;
	

	/**
 	 *  中风不透明度
 	 */
	private String strokeopacity;
	

	/**
 	 *  水印文字大小
 	 */
	private BigDecimal fontsize;
	

	/**
 	 *  水印分布形式
 	 */
	private String sybj;
	

	/**
 	 *  宽
 	 */
	private BigDecimal textx;
	

	/**
 	 *  高
 	 */
	private BigDecimal texty;
	

	/**
 	 *  斜度
 	 */
	private BigDecimal rotation;
	

	/**
 	 *  关联id
 	 */
	private String sourceid;


	/**
	 *  排序
	 */
	private Integer seq;
	



	/**
 	 *  
 	 */

	@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		  this.id=id;
	}


	/**
 	 *  水印内容
 	 */
	
	public String getWatermarkname (){
		return watermarkname;
	}
	public void setWatermarkname (String watermarkname){
		  this.watermarkname=watermarkname;
	}


	/**
 	 *  水印透明度填充不透明
 	 */
	
	public String getFillopacity (){
		return fillopacity;
	}
	public void setFillopacity (String fillopacity){
		  this.fillopacity=fillopacity;
	}


	/**
 	 *  中风不透明度
 	 */
	
	public String getStrokeopacity (){
		return strokeopacity;
	}
	public void setStrokeopacity (String strokeopacity){
		  this.strokeopacity=strokeopacity;
	}


	/**
 	 *  水印文字大小
 	 */
	
	public BigDecimal getFontsize (){
		return fontsize;
	}
	public void setFontsize (BigDecimal fontsize){
		  this.fontsize=fontsize;
	}


	/**
 	 *  水印分布形式
 	 */
	
	public String getSybj (){
		return sybj;
	}
	public void setSybj (String sybj){
		  this.sybj=sybj;
	}


	/**
 	 *  宽
 	 */
	
	public BigDecimal getTextx (){
		return textx;
	}
	public void setTextx (BigDecimal textx){
		  this.textx=textx;
	}


	/**
 	 *  高
 	 */
	
	public BigDecimal getTexty (){
		return texty;
	}
	public void setTexty (BigDecimal texty){
		  this.texty=texty;
	}


	/**
 	 *  斜度
 	 */
	
	public BigDecimal getRotation (){
		return rotation;
	}
	public void setRotation (BigDecimal rotation){
		  this.rotation=rotation;
	}


	/**
 	 *  关联id
 	 */
	
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		  this.sourceid=sourceid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * 用于深度拷贝
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}