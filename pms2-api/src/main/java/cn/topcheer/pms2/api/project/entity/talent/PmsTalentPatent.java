/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:30:14
 */
package cn.topcheer.pms2.api.project.entity.talent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  科技人才-专利知识产权表
 */
@Entity
@Table(name="PMS_TALENT_PATENT")
@ClassInfo(name = "科技人才-专利知识产权表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentPatent {

	/**
     *  固定字段:唯一标识
     */
	@ApiModelProperty("固定字段:唯一标识")
	@TableField("ID")
	@Column(columnDefinition = "ID")
	@FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
	private String id;

	/**
     *  固定字段:数据类型
     */
	@ApiModelProperty("固定字段:数据类型")
	@TableField("TYPE")
	@Column(columnDefinition = "TYPE")
	private String type;

	/**
     *  固定字段:关联主表id
     */
	@ApiModelProperty("固定字段:关联主表id")
	@TableField("MAINID")
	@Column(columnDefinition = "MAINID")
	private String mainid;

	/**
     *  固定字段:关联子表id
     */
	@ApiModelProperty("固定字段:关联子表id")
	@TableField("SOURCEID")
	@Column(columnDefinition = "SOURCEID")
	private String sourceid;

	/**
     *  固定字段:第一次保存时间
     */
	@ApiModelProperty("固定字段:第一次保存时间")
	@TableField("SAVEDATE")
	@Column(columnDefinition = "SAVEDATE")
	@FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date savedate;

	/**
     *  固定字段:每次更新数据时间
     */
	@ApiModelProperty("固定字段:每次更新数据时间")
	@TableField("UPDATELASTTIME")
	@Column(columnDefinition = "UPDATELASTTIME")
	@FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
	private Date updatelasttime;

	/**
     *  固定字段:排序
     */
	@ApiModelProperty("固定字段:排序")
	@TableField("SEQ")
	@Column(columnDefinition = "SEQ")
	@FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
	private Integer seq;

	/**
     *  固定字段:备注
     */
	@ApiModelProperty("固定字段:备注")
	@TableField("MEMO")
	@Column(columnDefinition = "MEMO")
	private String memo;

	/**
     *  名称
     */
	@ApiModelProperty("名称")
	@TableField("PATENTNAME")
	@Column(columnDefinition = "PATENTNAME")
	private String patentname;

	/**
     *  类别
     */
	@ApiModelProperty("类别")
	@TableField("PATENTTYPE")
	@Column(columnDefinition = "PATENTTYPE")
	private String patenttype;

	/**
     *  授权国家
     */
	@ApiModelProperty("授权国家")
	@TableField("AUTHORIZEDCOUNTRY")
	@Column(columnDefinition = "AUTHORIZEDCOUNTRY")
	private String authorizedcountry;

	/**
     *  专利号
     */
	@ApiModelProperty("专利号")
	@TableField("AUTHORIZATIONNO")
	@Column(columnDefinition = "AUTHORIZATIONNO")
	private String authorizationno;

	/**
     *  申请日期
     */
	@ApiModelProperty("申请日期")
	@TableField("APPLYDATE")
	@Column(columnDefinition = "APPLYDATE")
	@FieldDes(name = "applydate", type = "Date", memo = "申请日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date applydate;

	/**
     *  授权日期
     */
	@ApiModelProperty("授权日期")
	@TableField("AUTHORIZATIONDATE")
	@Column(columnDefinition = "AUTHORIZATIONDATE")
	@FieldDes(name = "authorizationdate", type = "Date", memo = "授权日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date authorizationdate;

	/**
     *  申请（专利权）人
     */
	@ApiModelProperty("申请（专利权）人")
	@TableField("PATENTEE")
	@Column(columnDefinition = "PATENTEE")
	private String patentee;

	/**
     *  发明（设计）人
     */
	@ApiModelProperty("发明（设计）人")
	@TableField("INVENTORS")
	@Column(columnDefinition = "INVENTORS")
	private String inventors;

	/**
     *  专利状态
     */
	@ApiModelProperty("专利状态")
	@TableField("STATUS")
	@Column(columnDefinition = "STATUS")
	private String status;

	/**
     *  分类号
     */
	@ApiModelProperty("分类号")
	@TableField("IPCNO")
	@Column(columnDefinition = "IPCNO")
	private String ipcno;

	/**
     *  发明（设计）人排序
     */
	@ApiModelProperty("发明（设计）人排序")
	@TableField("INVENTOR_RANK")
	@Column(columnDefinition = "INVENTOR_RANK")
	private String inventorRank;

	/**
     *  证书编号
     */
	@ApiModelProperty("证书编号")
	@TableField("CERTIFICATENO")
	@Column(columnDefinition = "CERTIFICATENO")
	private String certificateno;


	/**
	*  固定字段:唯一标识
	*/
		@Id
	public String getId (){
		return id;
	}
	public void setId (String id){
		this.id = id;
	}

	/**
	*  固定字段:数据类型
	*/
	public String getType (){
		return type;
	}
	public void setType (String type){
		this.type = type;
	}

	/**
	*  固定字段:关联主表id
	*/
	public String getMainid (){
		return mainid;
	}
	public void setMainid (String mainid){
		this.mainid = mainid;
	}

	/**
	*  固定字段:关联子表id
	*/
	public String getSourceid (){
		return sourceid;
	}
	public void setSourceid (String sourceid){
		this.sourceid = sourceid;
	}

	/**
	*  固定字段:第一次保存时间
	*/
	public Date getSavedate (){
		return savedate;
	}
	public void setSavedate (Date savedate){
		this.savedate = savedate;
	}

	/**
	*  固定字段:每次更新数据时间
	*/
	public Date getUpdatelasttime (){
		return updatelasttime;
	}
	public void setUpdatelasttime (Date updatelasttime){
		this.updatelasttime = updatelasttime;
	}

	/**
	*  固定字段:排序
	*/
	public Integer getSeq (){
		return seq;
	}
	public void setSeq (Integer seq){
		this.seq = seq;
	}

	/**
	*  固定字段:备注
	*/
	public String getMemo (){
		return memo;
	}
	public void setMemo (String memo){
		this.memo = memo;
	}

	/**
	*  名称
	*/
	public String getPatentname (){
		return patentname;
	}
	public void setPatentname (String patentname){
		this.patentname = patentname;
	}

	/**
	*  类别
	*/
	public String getPatenttype (){
		return patenttype;
	}
	public void setPatenttype (String patenttype){
		this.patenttype = patenttype;
	}

	/**
	*  授权国家
	*/
	public String getAuthorizedcountry (){
		return authorizedcountry;
	}
	public void setAuthorizedcountry (String authorizedcountry){
		this.authorizedcountry = authorizedcountry;
	}

	/**
	*  专利号
	*/
	public String getAuthorizationno (){
		return authorizationno;
	}
	public void setAuthorizationno (String authorizationno){
		this.authorizationno = authorizationno;
	}

	/**
	*  申请日期
	*/
	public Date getApplydate (){
		return applydate;
	}
	public void setApplydate (Date applydate){
		this.applydate = applydate;
	}

	/**
	*  授权日期
	*/
	public Date getAuthorizationdate (){
		return authorizationdate;
	}
	public void setAuthorizationdate (Date authorizationdate){
		this.authorizationdate = authorizationdate;
	}

	/**
	*  申请（专利权）人
	*/
	public String getPatentee (){
		return patentee;
	}
	public void setPatentee (String patentee){
		this.patentee = patentee;
	}

	/**
	*  发明（设计）人
	*/
	public String getInventors (){
		return inventors;
	}
	public void setInventors (String inventors){
		this.inventors = inventors;
	}

	/**
	*  专利状态
	*/
	public String getStatus (){
		return status;
	}
	public void setStatus (String status){
		this.status = status;
	}

	/**
	*  分类号
	*/
	public String getIpcno (){
		return ipcno;
	}
	public void setIpcno (String ipcno){
		this.ipcno = ipcno;
	}

	/**
	*  发明（设计）人排序
	*/
	public String getInventorRank (){
		return inventorRank;
	}
	public void setInventorRank (String inventorRank){
		this.inventorRank = inventorRank;
	}

	/**
	*  证书编号
	*/
	public String getCertificateno (){
		return certificateno;
	}
	public void setCertificateno (String certificateno){
		this.certificateno = certificateno;
	}

}