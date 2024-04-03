/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2021-1-19 15:17:52
 *
 */
package cn.topcheer.pms2.api.pdfDownload;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  
 */
@Entity
@Table(name="PMS_DOWNCONFIG")
public class PmsDownconfig implements Cloneable{


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  生成文件名称
 	 */
	private String openname;
	

	/**
 	 *  下载格式
 	 */
	private String downloadtype;
	

	/**
 	 *  最终路径（到文件夹那层）
 	 */
	private String url;
	

	/**
 	 *  
 	 */
	private String isaddpage;
	

	/**
 	 *  
 	 */
	private String isEncry;
	

	/**
 	 *  批次id,关联id
 	 */
	private String sourceid;


	/**
	 *  业务类别
	 */
	private String businesstype;
	

	/**
 	 *  备注
 	 */
	private String memo;


	/**
	 *  word生成单位文件夹
	 */
	private String tmpWordFile;


	/**
	 *  pdf生成单位文件夹（考虑可以通过某个字段作为文件夹名称）
	 */
	private String tmpPDFFile;


	/**
	 *  是否批量生成
	 */
	private String isbatch;


	/**
	 *  是否直接下载
	 */
	private String justdown;


	/**
	 *  取的参数（考虑数据源传参是否统一用一个id）
	 */
	private String idtype;


	/**
	 *  数据源service
	 */
	private String methodservice;


	/**
	 *  数据源方法名
	 */
	private String methodname;


	/**
	 *  调用数据源传参个数
	 */
	private String methodparamtype;


	/**
	 *  是否更改统一路径
	 */
	private String realpath;


	/**
	 *  添加审核通过水印语句，如果该值不为空说明要根据状态判断水印
	 */
	private String isaddpasswatersql;


	/**
	 *  批次名称或关联名称
	 */
	private String sourcename;


	/**
	 *  更新时间
	 */
	private Date updatetime;
	



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
 	 *  生成文件名称
 	 */
	
	public String getOpenname (){
		return openname;
	}
	public void setOpenname (String openname){
		  this.openname=openname;
	}


	/**
 	 *  下载格式
 	 */
	
	public String getDownloadtype (){
		return downloadtype;
	}
	public void setDownloadtype (String downloadtype){
		  this.downloadtype=downloadtype;
	}


	/**
 	 *  最终路径（到文件夹那层）
 	 */
	
	public String getUrl (){
		return url;
	}
	public void setUrl (String url){
		  this.url=url;
	}


	/**
 	 *  
 	 */
	
	public String getIsaddpage (){
		return isaddpage;
	}
	public void setIsaddpage (String isaddpage){
		  this.isaddpage=isaddpage;
	}

	public String getIsEncry() {
		return isEncry;
	}

	public void setIsEncry(String isEncry) {
		this.isEncry = isEncry;
	}

	/**
 	 *  批次id,关联id
 	 */
	
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		  this.sourceid=sourceid;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
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


	public String getTmpWordFile() {
		return tmpWordFile;
	}

	public void setTmpWordFile(String tmpWordFile) {
		this.tmpWordFile = tmpWordFile;
	}

	public String getTmpPDFFile() {
		return tmpPDFFile;
	}

	public void setTmpPDFFile(String tmpPDFFile) {
		this.tmpPDFFile = tmpPDFFile;
	}

	public String getIsbatch() {
		return isbatch;
	}

	public void setIsbatch(String isbatch) {
		this.isbatch = isbatch;
	}

	public String getJustdown() {
		return justdown;
	}

	public void setJustdown(String justdown) {
		this.justdown = justdown;
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	public String getMethodservice() {
		return methodservice;
	}

	public void setMethodservice(String methodservice) {
		this.methodservice = methodservice;
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public String getMethodparamtype() {
		return methodparamtype;
	}

	public void setMethodparamtype(String methodparamtype) {
		this.methodparamtype = methodparamtype;
	}

	public String getRealpath() {
		return realpath;
	}

	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}

	public String getIsaddpasswatersql() {
		return isaddpasswatersql;
	}

	public void setIsaddpasswatersql(String isaddpasswatersql) {
		this.isaddpasswatersql = isaddpasswatersql;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
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