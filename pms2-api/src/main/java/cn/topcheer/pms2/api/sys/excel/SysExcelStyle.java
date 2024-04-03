/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-9-25 18:23:38
 *
 */
package cn.topcheer.pms2.api.sys.excel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  excel配置--样式表
 */
@Entity
@Table(name="SYS_EXCEL_STYLE")
public class SysExcelStyle implements Cloneable{


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  设置图案颜色
 	 */
	private String fillforegroundcolor;


	/**
	 *  设置图案背景色
	 */
	private String fillbackgroundcolor;
	

	/**
 	 *  下边框
 	 */
	private String borderbottom;
	

	/**
 	 *  左边框
 	 */
	private String borderleft;
	

	/**
 	 *  上边框
 	 */
	private String bordertop;
	

	/**
 	 *  右边框
 	 */
	private String borderright;
	

	/**
 	 *  居中
 	 */
	private String alignment;
	

	/**
 	 *  设置自动换行
 	 */
	private String wraptext;
	

	/**
 	 *  关联字体id
 	 */
	private String fontid;


	/**
	 *  纵向居中
	 */
	private String verticalalignment;


	/**
	 *  样式名称
	 */
	private String name;


	/**
	 *  备注
	 */
	private String memo;



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
 	 *  设置背景色
 	 */
	
	public String getFillforegroundcolor (){
		return fillforegroundcolor;
	}
	public void setFillforegroundcolor (String fillforegroundcolor){
		  this.fillforegroundcolor=fillforegroundcolor;
	}


	public String getFillbackgroundcolor() {
		return fillbackgroundcolor;
	}

	public void setFillbackgroundcolor(String fillbackgroundcolor) {
		this.fillbackgroundcolor = fillbackgroundcolor;
	}

	/**
 	 *  下边框
 	 */
	
	public String getBorderbottom (){
		return borderbottom;
	}
	public void setBorderbottom (String borderbottom){
		  this.borderbottom=borderbottom;
	}


	/**
 	 *  左边框
 	 */
	
	public String getBorderleft (){
		return borderleft;
	}
	public void setBorderleft (String borderleft){
		  this.borderleft=borderleft;
	}


	/**
 	 *  上边框
 	 */
	
	public String getBordertop (){
		return bordertop;
	}
	public void setBordertop (String bordertop){
		  this.bordertop=bordertop;
	}


	/**
 	 *  右边框
 	 */
	
	public String getBorderright (){
		return borderright;
	}
	public void setBorderright (String borderright){
		  this.borderright=borderright;
	}


	/**
 	 *  居中
 	 */
	
	public String getAlignment (){
		return alignment;
	}
	public void setAlignment (String alignment){
		  this.alignment=alignment;
	}

	/**
 	 *  设置自动换行
 	 */
	
	public String getWraptext (){
		return wraptext;
	}
	public void setWraptext (String wraptext){
		  this.wraptext=wraptext;
	}


	/**
 	 *  纵向居中
 	 */
	
	public String getFontid (){
		return fontid;
	}
	public void setFontid (String fontid){
		  this.fontid=fontid;
	}

	public String getVerticalalignment() {
		return verticalalignment;
	}

	public void setVerticalalignment(String verticalalignment) {
		this.verticalalignment = verticalalignment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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