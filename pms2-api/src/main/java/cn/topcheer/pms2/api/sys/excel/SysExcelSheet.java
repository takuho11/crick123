/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-9-24 11:57:03
 *
 */
package cn.topcheer.pms2.api.sys.excel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  excel配置--sheet表
 */
@Entity
@Table(name="SYS_EXCEL_SHEET")
public class SysExcelSheet implements Cloneable{
	int CELL_TYPE_NUMERIC = 0;
	int CELL_TYPE_STRING = 1;
	int CELL_TYPE_FORMULA = 2;


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  excel配置表id
 	 */
	private String excelid;
	

	/**
 	 *  工作簿名称
 	 */
	private String sheetname;
	

	/**
 	 *  排序
 	 */
	private Integer seq;
	

	/**
 	 *  备注
 	 */
	private String memo;


    /**
     *  列宽
     */
    private String columnwidth;


	/**
	 *  固定工作簿唯一值（前台sheetarray）
	 */
	private String sheetfixkey;


	/**
	 *  自动生成工作簿---sql获取工作簿名称
	 */
	private String sheetsql;


	/**
	 *  纸张方向
	 */
	private String landscape;


    public String getColumnwidth() {
        return columnwidth;
    }

    public void setColumnwidth(String columnwidth) {
        this.columnwidth = columnwidth;
    }

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
 	 *  excel配置表id
 	 */
	
	public String getExcelid (){
		return excelid;
	}
	public void setExcelid (String excelid){
		  this.excelid=excelid;
	}


	/**
 	 *  工作簿名称
 	 */
	
	public String getSheetname (){
		return sheetname;
	}
	public void setSheetname (String sheetname){
		  this.sheetname=sheetname;
	}


	/**
 	 *  排序
 	 */
	
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		  this.seq=seq;
	}


	/**
 	 *  备注
 	 */
	
	public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		  this.memo=memo;
	}

	public String getSheetfixkey() {
		return sheetfixkey;
	}

	public void setSheetfixkey(String sheetfixkey) {
		this.sheetfixkey = sheetfixkey;
	}

	public String getSheetsql() {
		return sheetsql;
	}

	public void setSheetsql(String sheetsql) {
		this.sheetsql = sheetsql;
	}

	public String getLandscape() {
		return landscape;
	}

	public void setLandscape(String landscape) {
		this.landscape = landscape;
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