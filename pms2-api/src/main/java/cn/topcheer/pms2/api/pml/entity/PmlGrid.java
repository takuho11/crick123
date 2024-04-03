/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2019-3-3 15:54:33
 *
 */
package cn.topcheer.pms2.api.pml.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 *  
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="PML_GRID")
@Getter
@Setter
public class PmlGrid {


	/**
 	 *  
 	 */
	@Id
	private String id;
	

	/**
 	 *  路由gird?gridtype=xxx
 	 */
	private String gridtype;
	

	/**
 	 *  列表名称
 	 */
	private String gridname;
	

	/**
 	 *  当前位置
 	 */
	private String nowlocation;
	

	/**
 	 *  有无搜索框，1:有，0:无
 	 */
	private Boolean havesearchbox;
	

	/**
 	 *  搜索框类型
 	 */
	private String searchboxtype;

	/**
	 * 搜索框提示语句
	 */
	private String searchboxtips;
	

	/**
 	 *  有无其他按钮，其他按钮指批量处理等按钮，1:有，0:无
 	 */
	private Boolean haveotherbtn;
	

	/**
 	 *  搜索和初始化方法，通用的.do方法和个别特殊的.do方法
 	 */
	private String getdatamethod;
	

	/**
 	 *  是否读取sql脚本，一般情况下，通用的.do方法:1，个别特殊的.do方法：0
 	 */
	private Boolean readscript;
	

	/**
 	 *  sql脚本或者hql脚本
 	 */
	private String script;
	

	/**
 	 *  表创建时间
 	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date createdate;

	/**
	 * 排序
	 */
	private Integer seq;

	/**
	 * 使用sql还是使用hql
	 */
	private String sqlorhql;

	/**
	 * 分页配置
	 */
	private String pageconfig;

	/**
	 * 前台参数集合
	 */
	private String jsonattributes;

	/**
	 * where条件集合
	 */
	private String whereconditions;

	/**
	 *是否判断角色，1：判断，2：不判断
	 */
	private Boolean judgerole;

	/**
	 * 可以用这个列表的角色集合
	 */
	private String roledata;

	/**
	 * 列表业务类型
	 */
	private String businesstype;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 获取列表总数的方法(一般都在PmsCommonGridService里)
	 */
	private String getcountmethod;

	/**
	 * 搜索框组合id
	 */
	private String collectionid;

	/**
	 *  有无提醒颜色，1:有，0:无
	 */
	private Boolean haveremindcolor;

	/**
	 * 初始化获取数据，1:获取，0:不获取
	 */
	private Boolean judgeinit;


	/**
	 * 手机端用到
	 */
	private String hidectrl;

	/**
	 * 手机端用到
	 */
	private String fittype;

	/**
	 * 判断是否启用列表横向滚动条 1启动 0不启用
	 */
	private Boolean scrollBar;

	private String tipsdata;

	private Boolean judgetips;

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
 	 *  路由gird?gridtype=xxx
 	 */

	public String getGridtype (){
		return gridtype;
	}
	public void setGridtype (String gridtype){
		  this.gridtype=gridtype;
	}


	/**
 	 *  列表名称
 	 */

	public String getGridname (){
		return gridname;
	}
	public void setGridname (String gridname){
		  this.gridname=gridname;
	}


	/**
 	 *  当前位置
 	 */

	public String getNowlocation (){
		return nowlocation;
	}
	public void setNowlocation (String nowlocation){
		  this.nowlocation=nowlocation;
	}


	/**
 	 *  有无搜索框，1:有，0:无
 	 */
	@Transient
	public Boolean isHavesearchbox(){
		return havesearchbox;
	}
	public void setHavesearchbox (Boolean havesearchbox){
		  this.havesearchbox=havesearchbox;
	}


	/**
 	 *  搜索框类型
 	 */

	public String getSearchboxtype (){
		return searchboxtype;
	}
	public void setSearchboxtype (String searchboxtype){
		  this.searchboxtype=searchboxtype;
	}

	/**
	 * 搜索框提示语句
	 */
	public String getSearchboxtips() {
		return searchboxtips;
	}

	public void setSearchboxtips(String searchboxtips) {
		this.searchboxtips = searchboxtips;
	}

	/**
 	 *  有无其他按钮，其他按钮指批量处理等按钮，1:有，0:无
 	 */
	@Transient
	public Boolean isHaveotherbtn(){
		return haveotherbtn;
	}
	public void setHaveotherbtn (Boolean haveotherbtn){
		  this.haveotherbtn=haveotherbtn;
	}


	/**
 	 *  搜索和初始化方法，通用的.do方法和个别特殊的.do方法
 	 */

	public String getGetdatamethod (){
		return getdatamethod;
	}
	public void setGetdatamethod (String getdatamethod){
		  this.getdatamethod=getdatamethod;
	}


	/**
 	 *  是否读取sql脚本，一般情况下，通用的.do方法:1，个别特殊的.do方法：0
 	 */
	@Transient
	public Boolean isReadscript(){
		return readscript;
	}
	public void setReadscript (Boolean readscript){
		  this.readscript=readscript;
	}


	/**
 	 *  sql脚本或者hql脚本
 	 */

	public String getScript (){
		return script;
	}
	public void setScript (String script){
		  this.script=script;
	}


	/**
 	 *  表创建时间
 	 */

	public Date getCreatedate (){
		return createdate;
	}
	public void setCreatedate (Date createdate){
		  this.createdate=createdate;
	}


	/**
	 * 排序
	 * @return
     */
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}



	/**
	 * 使用sql还是hql
	 * @return
     */
	public String getSqlorhql() {
		return sqlorhql;
	}

	public void setSqlorhql(String sqlorhql) {
		this.sqlorhql = sqlorhql;
	}


	/**
	 * 分页配置
     */
	public String getPageconfig() {
		return pageconfig;
	}

	public void setPageconfig(String pageconfig) {
		this.pageconfig = pageconfig;
	}


	/**
	 * 前台参数集合
     */
	public String getJsonattributes() {
		return jsonattributes;
	}

	public void setJsonattributes(String jsonattributes) {
		this.jsonattributes = jsonattributes;
	}

	/**
	 * where条件集合
     */
	public String getWhereconditions() {
		return whereconditions;
	}

	public void setWhereconditions(String whereconditions) {
		this.whereconditions = whereconditions;
	}

	/**
	 *是否判断角色，1：判断，2：不判断
	 */
	public Boolean getJudgerole() {
		return judgerole;
	}

	public void setJudgerole(Boolean judgerole) {
		this.judgerole = judgerole;
	}

	/**
	 * 可以用这个列表的角色集合
	 */
	public String getRoledata() {
		return roledata;
	}

	public void setRoledata(String roledata) {
		this.roledata = roledata;
	}

	/**
	 * 列表业务类型
     */
	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	/**
	 * 备注
     */
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getGetcountmethod() {
		return getcountmethod;
	}

	public void setGetcountmethod(String getcountmethod) {
		this.getcountmethod = getcountmethod;
	}

	public String getCollectionid() {
		return collectionid;
	}

	public void setCollectionid(String collectionid) {
		this.collectionid = collectionid;
	}

	public Boolean getHaveremindcolor() {
		return haveremindcolor;
	}

	public void setHaveremindcolor(Boolean haveremindcolor) {
		this.haveremindcolor = haveremindcolor;
	}

	public Boolean getJudgeinit() {
		return judgeinit;
	}

	public void setJudgeinit(Boolean judgeinit) {
		this.judgeinit = judgeinit;
	}


	public Boolean getHavesearchbox() {
		return havesearchbox;
	}

	public Boolean getHaveotherbtn() {
		return haveotherbtn;
	}

	public Boolean getReadscript() {
		return readscript;
	}

	public String getHidectrl() {
		return hidectrl;
	}

	public void setHidectrl(String hidectrl) {
		this.hidectrl = hidectrl;
	}

	public String getFittype() {
		return fittype;
	}

	public void setFittype(String fittype) {
		this.fittype = fittype;
	}

	public Boolean getScrollBar() {
		return scrollBar;
	}

	public void setScrollBar(Boolean scrollBar) {
		this.scrollBar = scrollBar;
	}

	public String getTipsdata() {
		return tipsdata;
	}

	public void setTipsdata(String tipsdata) {
		this.tipsdata = tipsdata;
	}

	public Boolean getJudgetips() {
		return judgetips;
	}

	public void setJudgetips(Boolean judgetips) {
		this.judgetips = judgetips;
	}
}
