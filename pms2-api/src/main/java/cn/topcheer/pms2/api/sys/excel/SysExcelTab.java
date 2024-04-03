/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2020-10-15 16:46:18
 *
 */
package cn.topcheer.pms2.api.sys.excel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  excel配置--tab表--sheet层级下
 */
@Entity
@Table(name="SYS_EXCEL_TAB")
public class SysExcelTab implements Cloneable{


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  工作簿表id
 	 */
	private String sheetid;
	

	/**
 	 *  工作簿--下模块名称
 	 */
	private String tabname;
	

	/**
 	 *  数据获取形式（sql，hql，method）
 	 */
	private String datasourcetype;
	

	/**
 	 *  sql或者hql语句
 	 */
	private String datasourcesql;
	

	/**
 	 *  方法service
 	 */
	private String datasourcemethodservice;
	

	/**
 	 *  方法名
 	 */
	private String datasourcemethodname;
	

	/**
 	 *  排序
 	 */
	private Integer seq;
	

	/**
 	 *  备注
 	 */
	private String memo;
	

	/**
 	 *  
 	 */
	private String tabkeyhead;
	

	/**
 	 *  
 	 */
	private String tabkeyvalue;
	

	/**
 	 *  
 	 */
	private String tabstartrow;


	/**
	 *
	 */
	private String sheetbig;


	/**
	 *  样式id
	 */
	private String styleid;


	/**
	 *  大标题样式id
	 */
	private String bigstyleid;


	/**
	 *  样式标题id
	 */
	private String headstyleid;


	/**
	 *  样式id
	 */
	private String valuestyleid;


	/**
	 *合并单元格标记
	 */
	private String mergedregion;


	/**
	 *是否根据该tab的列标记列宽
	 */
	private String iscolumnwidth;


    /**
     *tab的列标记列宽
     */
    private String columnwidth;


	/**
	 *固定数据源传参
	 */
	private String datamethodtype;


	/**
	 *后台数据源--key
	 */
	private String datamethodkey;


	/**
	 *固定样式标记
	 */
	private String fixstyle;


//    public String getColumnWidth() {
//        return columnwidth;
//    }
//
//    public void setColumnWidth(String columnWidth) {
//        this.columnwidth = columnWidth;
//    }

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
 	 *  工作簿表id
 	 */
	
	public String getSheetid (){
		return sheetid;
	}
	public void setSheetid (String sheetid){
		  this.sheetid=sheetid;
	}


	/**
 	 *  工作簿--下模块名称
 	 */
	
	public String getTabname (){
		return tabname;
	}
	public void setTabname (String tabname){
		  this.tabname=tabname;
	}


	/**
 	 *  数据获取形式（sql，hql，method）
 	 */
	
	public String getDatasourcetype (){
		return datasourcetype;
	}
	public void setDatasourcetype (String datasourcetype){
		  this.datasourcetype=datasourcetype;
	}


	/**
 	 *  sql或者hql语句
 	 */
	
	public String getDatasourcesql (){
		return datasourcesql;
	}
	public void setDatasourcesql (String datasourcesql){
		  this.datasourcesql=datasourcesql;
	}


	/**
 	 *  方法service
 	 */
	
	public String getDatasourcemethodservice (){
		return datasourcemethodservice;
	}
	public void setDatasourcemethodservice (String datasourcemethodservice){
		  this.datasourcemethodservice=datasourcemethodservice;
	}


	/**
 	 *  方法名
 	 */
	
	public String getDatasourcemethodname (){
		return datasourcemethodname;
	}
	public void setDatasourcemethodname (String datasourcemethodname){
		  this.datasourcemethodname=datasourcemethodname;
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


	/**
 	 *  
 	 */
	
	public String getTabkeyhead (){
		return tabkeyhead;
	}
	public void setTabkeyhead (String tabkeyhead){
		  this.tabkeyhead=tabkeyhead;
	}


	/**
 	 *  
 	 */
	
	public String getTabkeyvalue (){
		return tabkeyvalue;
	}
	public void setTabkeyvalue (String tabkeyvalue){
		  this.tabkeyvalue=tabkeyvalue;
	}


	/**
 	 *  
 	 */
	
	public String getTabstartrow (){
		return tabstartrow;
	}
	public void setTabstartrow (String tabstartrow){
		  this.tabstartrow=tabstartrow;
	}

	public String getSheetbig() {
		return sheetbig;
	}

	public void setSheetbig(String sheetbig) {
		this.sheetbig = sheetbig;
	}

	public String getStyleid() {
		return styleid;
	}

	public void setStyleid(String styleid) {
		this.styleid = styleid;
	}

	public String getBigstyleid() {
		return bigstyleid;
	}

	public void setBigstyleid(String bigstyleid) {
		this.bigstyleid = bigstyleid;
	}

	public String getHeadstyleid() {
		return headstyleid;
	}

	public void setHeadstyleid(String headstyleid) {
		this.headstyleid = headstyleid;
	}

	public String getValuestyleid() {
		return valuestyleid;
	}

	public void setValuestyleid(String valuestyleid) {
		this.valuestyleid = valuestyleid;
	}

	public String getMergedregion() {
		return mergedregion;
	}

	public void setMergedregion(String mergedregion) {
		this.mergedregion = mergedregion;
	}

	public String getIscolumnwidth() {
		return iscolumnwidth;
	}

	public void setIscolumnwidth(String iscolumnwidth) {
		this.iscolumnwidth = iscolumnwidth;
	}

	public String getDatamethodtype() {
		return datamethodtype;
	}

	public void setDatamethodtype(String datamethodtype) {
		this.datamethodtype = datamethodtype;
	}

	public String getFixstyle() {
		return fixstyle;
	}

	public void setFixstyle(String fixstyle) {
		this.fixstyle = fixstyle;
	}

	public String getDatamethodkey() {
		return datamethodkey;
	}

	public void setDatamethodkey(String datamethodkey) {
		this.datamethodkey = datamethodkey;
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