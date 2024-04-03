/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-3 13:58:57
 */
package cn.topcheer.pms2.api.project.entity.projectBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *  计划项目-专利
 */
@Entity
@Table(name="PMS_PROJECTBASE_PATENT")
@ClassInfo(name = "计划项目-专利", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbasePatent {

	/**
     *  固定字段:唯一标识
     */
	@FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
	private String id;
	/**
     *  固定字段:数据类型
     */
	private String type;
	/**
     *  固定字段:关联主表id
     */
	private String mainid;
	/**
     *  固定字段:关联子表id
     */
	private String sourceid;
	/**
     *  固定字段:第一次保存时间
     */
	@FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date savedate;
	/**
     *  固定字段:每次更新数据时间
     */
	@FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date updatelasttime;
	/**
     *  固定字段:排序
     */
	@FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
	private Integer seq;
	/**
     *  固定字段:备注
     */
	private String memo;
	/**
     *  授权项目名称
     */
	private String projectname;
	/**
     *  类别
     */
	private String patenttype;
	/**
     *  知识产权名称
     */
	private String patentname;
	/**
     *  发明人
     */
	private String inventors;
	/**
     *  专利权人
     */
	private String patentee;
	/**
     *  是否是国际专利
     */
	private String isinternational;
	/**
     *  IPC分类号
     */
	private String ipcno;
	/**
     *  发明人排序
     */
	@FieldDes(name = "inventorsrank", type = "Integer", memo = "发明人排序")
	private Integer inventorsrank;
	/**
     *  授权国别
     */
	private String authorizedcountry;
	/**
     *  获得方式
     */
	private String accesstype;
	/**
     *  申请号
     */
	private String applicationno;
	/**
     *  授权号
     */
	private String authorizationno;
	/**
     *  授权日期
     */
	@FieldDes(name = "authorizationdate", type = "Date", memo = "授权日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date authorizationdate;
	/**
     *  申请时间
     */
	@FieldDes(name = "applydate", type = "Date", memo = "申请时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date applydate;
	/**
     *  产业领域
     */
	private String industrialfield;
	/**
     *  授权截止时间
     */
	@FieldDes(name = "authorizationdeadline", type = "Date", memo = "授权截止时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date authorizationdeadline;
	/**
     *  转化情况
     */
	private String transformation;

	/**
	 *  状况（申请/授权）
	 */
	@ApiModelProperty("状况（申请/授权）")
	@TableField("CONDITION")
	@Column(columnDefinition = "CONDITION")
	private String condition;

	/**
	 *  是否PCT渠道申请
	 */
	@ApiModelProperty("是否PCT渠道申请")
	@TableField("IS_PCT")
	@Column(columnDefinition = "IS_PCT")
	private String isPct;

	/**
	 *  授权单位
	 */
	@ApiModelProperty("授权单位")
	@TableField("AUTHORIZED_UNIT")
	@Column(columnDefinition = "AUTHORIZED_UNIT")
	private String authorizedUnit;

	/**
	*  固定字段:唯一标识
	*/
		@Id
	public String getId (){
	return id;
	}
	public void setId (String id){
	this.id=id;
	}

	/**
	*  固定字段:数据类型
	*/
	public String getType (){
	return type;
	}
	public void setType (String type){
	this.type=type;
	}

	/**
	*  固定字段:关联主表id
	*/
	public String getMainid (){
	return mainid;
	}
	public void setMainid (String mainid){
	this.mainid=mainid;
	}

	/**
	*  固定字段:关联子表id
	*/
	public String getSourceid (){
	return sourceid;
	}
	public void setSourceid (String sourceid){
	this.sourceid=sourceid;
	}

	/**
	*  固定字段:第一次保存时间
	*/
	public Date getSavedate (){
	return savedate;
	}
	public void setSavedate (Date savedate){
	this.savedate=savedate;
	}

	/**
	*  固定字段:每次更新数据时间
	*/
	public Date getUpdatelasttime (){
	return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
	this.updatelasttime=updatelasttime;
	}

	/**
	*  固定字段:排序
	*/
	public Integer getSeq (){
	return seq;
	}
	public void setSeq (Integer seq){
	this.seq=seq;
	}

	/**
	*  固定字段:备注
	*/
	public String getMemo (){
	return memo;
	}
	public void setMemo (String memo){
	this.memo=memo;
	}

	/**
	*  授权项目名称
	*/
	public String getProjectname (){
	return projectname;
	}
	public void setProjectname (String projectname){
	this.projectname=projectname;
	}

	/**
	*  类别
	*/
	public String getPatenttype (){
	return patenttype;
	}
	public void setPatenttype (String patenttype){
	this.patenttype=patenttype;
	}

	/**
	*  知识产权名称
	*/
	public String getPatentname (){
	return patentname;
	}
	public void setPatentname (String patentname){
	this.patentname=patentname;
	}

	/**
	*  发明人
	*/
	public String getInventors (){
	return inventors;
	}
	public void setInventors (String inventors){
	this.inventors=inventors;
	}

	/**
	*  专利权人
	*/
	public String getPatentee (){
	return patentee;
	}
	public void setPatentee (String patentee){
	this.patentee=patentee;
	}

	/**
	*  是否是国际专利
	*/
	public String getIsinternational (){
	return isinternational;
	}
	public void setIsinternational (String isinternational){
	this.isinternational=isinternational;
	}

	/**
	*  IPC分类号
	*/
	public String getIpcno (){
	return ipcno;
	}
	public void setIpcno (String ipcno){
	this.ipcno=ipcno;
	}

	/**
	*  发明人排序
	*/
	public Integer getInventorsrank (){
	return inventorsrank;
	}
	public void setInventorsrank (Integer inventorsrank){
	this.inventorsrank=inventorsrank;
	}

	/**
	*  授权国别
	*/
	public String getAuthorizedcountry (){
	return authorizedcountry;
	}
	public void setAuthorizedcountry (String authorizedcountry){
	this.authorizedcountry=authorizedcountry;
	}

	/**
	*  获得方式
	*/
	public String getAccesstype (){
	return accesstype;
	}
	public void setAccesstype (String accesstype){
	this.accesstype=accesstype;
	}

	/**
	*  申请号
	*/
	public String getApplicationno (){
	return applicationno;
	}
	public void setApplicationno (String applicationno){
	this.applicationno=applicationno;
	}

	/**
	*  授权号
	*/
	public String getAuthorizationno (){
	return authorizationno;
	}
	public void setAuthorizationno (String authorizationno){
	this.authorizationno=authorizationno;
	}

	/**
	*  授权日期
	*/
	public Date getAuthorizationdate (){
	return authorizationdate;
	}
	public void setAuthorizationdate (Date authorizationdate){
	this.authorizationdate=authorizationdate;
	}

	/**
	*  申请时间
	*/
	public Date getApplydate (){
	return applydate;
	}
	public void setApplydate (Date applydate){
	this.applydate=applydate;
	}

	/**
	*  产业领域
	*/
	public String getIndustrialfield (){
	return industrialfield;
	}
	public void setIndustrialfield (String industrialfield){
	this.industrialfield=industrialfield;
	}

	/**
	*  授权截止时间
	*/
	public Date getAuthorizationdeadline (){
	return authorizationdeadline;
	}
	public void setAuthorizationdeadline (Date authorizationdeadline){
	this.authorizationdeadline=authorizationdeadline;
	}

	/**
	*  转化情况
	*/
	public String getTransformation (){
	return transformation;
	}
	public void setTransformation (String transformation){
	this.transformation=transformation;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getIsPct() {
		return isPct;
	}

	public void setIsPct(String isPct) {
		this.isPct = isPct;
	}

	public String getAuthorizedUnit() {
		return authorizedUnit;
	}

	public void setAuthorizedUnit(String authorizedUnit) {
		this.authorizedUnit = authorizedUnit;
	}
}