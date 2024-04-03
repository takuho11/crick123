/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-12-6 11:46:02
 *
 */
package cn.topcheer.pms2.api.pdfDownload;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *  单独下载方法配置表
 */
@Entity
@Table(name="PMS_DOWNLOADTYPE")
public class PmsDownloadtype {


	/**
 	 *  
 	 */
	private String id;
	

	/**
 	 *  类别
 	 */
	private String type;
	

	/**
 	 *  是否批量生成
 	 */
	private String isbatch;
	

	/**
 	 *  是否直接下载
 	 */
	private String justdown;
	

	/**
 	 *  生成文件名称
 	 */
	private String openname;
	

	/**
 	 *  模板名称
 	 */
	private String modelname;
	

	/**
 	 *  水印名称
 	 */
	private String watername;
	

	/**
 	 *  水印样式
 	 */
	private String watermarkstyle;
	

	/**
 	 *  水印大小
 	 */
	private String opacity;
	

	/**
 	 *  下载格式
 	 */
	private String downloadtype;
	

	/**
 	 *  生成方式：free是freemark，book是书签
 	 */
	private String generatetype;
	

	/**
 	 *  取的参数（考虑数据源传参是否统一用一个id）
 	 */
	private String idtype;


	/**
	 *  是否放在项目里
	 */
	private String inprogram;
	

	/**
 	 *  word生成单位文件夹
 	 */
	private String tmpwordfile;
	

	/**
 	 *  pdf生成单位文件夹（考虑可以通过某个字段作为文件夹名称）
 	 */
	private String tmppdffile;


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
 	 *  是否多种模板拼接下载
 	 */
	private String modeloraffixlist;
	

	/**
 	 *  备注
 	 */
	private String memo;


    /**
     *  是否更改统一路径
     */
    private String realpath;


	/**
	 *  添加审核通过水印语句，如果该值不为空说明要根据状态判断水印
	 */
	private String isaddpasswatersql;


	/**
     *  是否用系统页码
     */
    private String addpagenum;



    /**
     *  批次id
     */
    private String planprojectid;


    /**
     *  批次名称
     */
    private String planname;


    /**
     *  更新时间
     */
    private Date updatetime;


    /**
     *  pdf最终命名
     */
    private String pdfname;


	/**
	 *  添加盖章方法，查盖章业务id
	 */
	private String signsql;


	/**
	 *  插二维码语句
	 */
	private String twodimsql;


	/**
	 *  取二维码key值
	 */
	private String twodimkey;


	/**
	 *  页码样式：第%d页 共%d页
	 */
	private String pageformat;


	/**
	 *  可变输出文档
	 */
	private String opennamesql;

	/**
	 *  pdf水印id
	 */
	private String waterid;

	/**
	 *  pdf下载是否用redis
	 */
	private String downtypebyredis;

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
 	 *  类别
 	 */
	
	public String getType (){
		return type;
	}
	public void setType (String type){
		  this.type=type;
	}

    public String getPlanprojectid() {
        return planprojectid;
    }

    public void setPlanprojectid(String planprojectid) {
        this.planprojectid = planprojectid;
    }

    /**
 	 *  是否批量生成
 	 */
	
	public String getIsbatch (){
		return isbatch;
	}
	public void setIsbatch (String isbatch){
		  this.isbatch=isbatch;
	}


	/**
 	 *  是否直接下载
 	 */
	
	public String getJustdown (){
		return justdown;
	}
	public void setJustdown (String justdown){
		  this.justdown=justdown;
	}


    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
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
 	 *  模板名称
 	 */
	
	public String getModelname (){
		return modelname;
	}
	public void setModelname (String modelname){
		  this.modelname=modelname;
	}


	/**
 	 *  水印名称
 	 */
	
	public String getWatername (){
		return watername;
	}
	public void setWatername (String watername){
		  this.watername=watername;
	}


	/**
 	 *  水印样式
 	 */
	
	public String getWatermarkstyle (){
		return watermarkstyle;
	}
	public void setWatermarkstyle (String watermarkstyle){
		  this.watermarkstyle=watermarkstyle;
	}


	/**
 	 *  水印大小
 	 */
	
	public String getOpacity (){
		return opacity;
	}
	public void setOpacity (String opacity){
		  this.opacity=opacity;
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
 	 *  生成方式：free是freemark，book是书签
 	 */
	
	public String getGeneratetype (){
		return generatetype;
	}
	public void setGeneratetype (String generatetype){
		  this.generatetype=generatetype;
	}


	/**
 	 *  取的参数（考虑数据源传参是否统一用一个id）
 	 */
	
	public String getIdtype (){
		return idtype;
	}
	public void setIdtype (String idtype){
		  this.idtype=idtype;
	}

	public String getInprogram() {
		return inprogram;
	}

	public void setInprogram(String inprogram) {
		this.inprogram = inprogram;
	}

	/**
 	 *  word生成单位文件夹
 	 */
	
	public String getTmpwordfile (){
		return tmpwordfile;
	}
	public void setTmpwordfile (String tmpwordfile){
		  this.tmpwordfile=tmpwordfile;
	}


	/**
 	 *  pdf生成单位文件夹（考虑可以通过某个字段作为文件夹名称）
 	 */
	
	public String getTmppdffile (){
		return tmppdffile;
	}
	public void setTmppdffile (String tmppdffile){
		  this.tmppdffile=tmppdffile;
	}


	public String getMethodservice() {
		return methodservice;
	}

	public void setMethodservice(String methodservice) {
		this.methodservice = methodservice;
	}

	/**
 	 *  数据源方法名
 	 */
	
	public String getMethodname (){
		return methodname;
	}
	public void setMethodname (String methodname){
		  this.methodname=methodname;
	}


	/**
 	 *  调用数据源传参个数
 	 */
	
	public String getMethodparamtype (){
		return methodparamtype;
	}
	public void setMethodparamtype (String methodparamtype){
		  this.methodparamtype=methodparamtype;
	}


	/**
 	 *  是否多种模板拼接下载
 	 */
	
	public String getModeloraffixlist (){
		return modeloraffixlist;
	}
	public void setModeloraffixlist (String modeloraffixlist){
		  this.modeloraffixlist=modeloraffixlist;
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
     *  更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }



    /**
     *  pdf最终命名
     */
    public String getPdfname() {
        return pdfname;
    }

    public void setPdfname(String pdfname) {
        this.pdfname = pdfname;
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

	public String getAddpagenum() {
		return addpagenum;
	}

	public void setAddpagenum(String addpagenum) {
		this.addpagenum = addpagenum;
	}

	public String getSignsql() {
		return signsql;
	}

	public void setSignsql(String signsql) {
		this.signsql = signsql;
	}

	public String getTwodimsql() {
		return twodimsql;
	}

	public void setTwodimsql(String twodimsql) {
		this.twodimsql = twodimsql;
	}

	public String getTwodimkey() {
		return twodimkey;
	}

	public void setTwodimkey(String twodimkey) {
		this.twodimkey = twodimkey;
	}

	public String getPageformat() {
		return pageformat;
	}

	public void setPageformat(String pageformat) {
		this.pageformat = pageformat;
	}

	public String getOpennamesql() {
		return opennamesql;
	}

	public void setOpennamesql(String opennamesql) {
		this.opennamesql = opennamesql;
	}

	public String getWaterid() {
		return waterid;
	}

	public void setWaterid(String waterid) {
		this.waterid = waterid;
	}

	public String getDowntypebyredis() {
		return downtypebyredis;
	}

	public void setDowntypebyredis(String downtypebyredis) {
		this.downtypebyredis = downtypebyredis;
	}
}