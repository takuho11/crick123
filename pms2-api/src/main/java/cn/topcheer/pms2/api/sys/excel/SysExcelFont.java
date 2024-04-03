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
 *  excel配置--字体表
 */
@Entity
@Table(name="SYS_EXCEL_FONT")
public class SysExcelFont implements Cloneable{


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  是否为粗体
 	 */
	private String bold;
	

	/**
 	 *  设置粗体
 	 */
	private String boldweight;
	

	/**
 	 *  （带下划线的字节）设置要使用的带下划线的文本类型
 	 */
	private String underline;
	

	/**
 	 *  字体颜色HSSFColor.BLUE.index
 	 */
	private String color;
	

	/**
 	 *  字体名称
 	 */
	private String fontname;
	

	/**
 	 *  设置字体大小
 	 */
	private String fontheightinpoints;
	

	/**
 	 *  布尔斜体
 	 */
	private String italic;
	

	/**
 	 *  字体样式名称
 	 */
	private String name;
	

	/**
 	 *  字体备注
 	 */
	private String memo;


	/**
	 *  设置上标下标
	 */
	private String typeoffset;


	/**
	 *  设置删除线
	 */
	private String strikeout;
	



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
 	 *  是否为粗体
 	 */
	
	public String getBold (){
		return bold;
	}
	public void setBold (String bold){
		  this.bold=bold;
	}


	/**
 	 *  设置粗体
 	 */
	
	public String getBoldweight (){
		return boldweight;
	}
	public void setBoldweight (String boldweight){
		  this.boldweight=boldweight;
	}


	/**
 	 *  （带下划线的字节）设置要使用的带下划线的文本类型
 	 */
	
	public String getUnderline (){
		return underline;
	}
	public void setUnderline (String underline){
		  this.underline=underline;
	}


	/**
 	 *  字体颜色HSSFColor.BLUE.index
 	 */
	
	public String getColor (){
		return color;
	}
	public void setColor (String color){
		  this.color=color;
	}


	/**
 	 *  字体名称
 	 */
	
	public String getFontname (){
		return fontname;
	}
	public void setFontname (String fontname){
		  this.fontname=fontname;
	}


	/**
 	 *  设置字体大小
 	 */
	
	public String getFontheightinpoints (){
		return fontheightinpoints;
	}
	public void setFontheightinpoints (String fontheightinpoints){
		  this.fontheightinpoints=fontheightinpoints;
	}


	/**
 	 *  布尔斜体
 	 */
	
	public String getItalic (){
		return italic;
	}
	public void setItalic (String italic){
		  this.italic=italic;
	}


	/**
 	 *  字体样式名称
 	 */
	
	public String getName (){
		return name;
	}
	public void setName (String name){
		  this.name=name;
	}


	/**
 	 *  字体备注
 	 */
	
	public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		  this.memo=memo;
	}

	public String getTypeoffset() {
		return typeoffset;
	}

	public void setTypeoffset(String typeoffset) {
		this.typeoffset = typeoffset;
	}

	public String getStrikeout() {
		return strikeout;
	}

	public void setStrikeout(String strikeout) {
		this.strikeout = strikeout;
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