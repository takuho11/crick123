/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:32:16
 */
package cn.topcheer.pms2.api.project.entity.prize;

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
 *  科技奖励-知识产权表
 */
@Entity
@Table(name="PMS_PRIZE_PATENT")
@ClassInfo(name = "科技奖励-知识产权表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizePatent {

	/**
     *  
     */
	@ApiModelProperty("")
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
     *  知识产权类别
     */
	@ApiModelProperty("知识产权类别")
	@TableField("PATENTTYPE")
	@Column(columnDefinition = "PATENTTYPE")
	private String patenttype;

	/**
     *  知识产权具体名称
     */
	@ApiModelProperty("知识产权具体名称")
	@TableField("NAME")
	@Column(columnDefinition = "NAME")
	private String name;

	/**
     *  国家（地区）
     */
	@ApiModelProperty("国家（地区）")
	@TableField("COUNTRY")
	@Column(columnDefinition = "COUNTRY")
	private String country;

	/**
     *  授权号
     */
	@ApiModelProperty("授权号")
	@TableField("PATENTNO")
	@Column(columnDefinition = "PATENTNO")
	private String patentno;

	/**
     *  授权日期
     */
	@ApiModelProperty("授权日期")
	@TableField("GRANTDATE")
	@Column(columnDefinition = "GRANTDATE")
	@FieldDes(name = "grantdate", type = "Date", memo = "授权日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date grantdate;

	/**
     *  证书编号
     */
	@ApiModelProperty("证书编号")
	@TableField("CERTIFICATENO")
	@Column(columnDefinition = "CERTIFICATENO")
	private String certificateno;

	/**
     *  权利人
     */
	@ApiModelProperty("权利人")
	@TableField("PATENTEE")
	@Column(columnDefinition = "PATENTEE")
	private String patentee;

	/**
     *  发明人
     */
	@ApiModelProperty("发明人")
	@TableField("INVENTORS")
	@Column(columnDefinition = "INVENTORS")
	private String inventors;

	/**
     *  发明专利（标准）有效状态
     */
	@ApiModelProperty("发明专利（标准）有效状态")
	@TableField("STATUS")
	@Column(columnDefinition = "STATUS")
	private String status;


	/**
	*  
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
	*  知识产权类别
	*/
	public String getPatenttype (){
		return patenttype;
	}
	public void setPatenttype (String patenttype){
		this.patenttype = patenttype;
	}

	/**
	*  知识产权具体名称
	*/
	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name = name;
	}

	/**
	*  国家（地区）
	*/
	public String getCountry (){
		return country;
	}
	public void setCountry (String country){
		this.country = country;
	}

	/**
	*  授权号
	*/
	public String getPatentno (){
		return patentno;
	}
	public void setPatentno (String patentno){
		this.patentno = patentno;
	}

	/**
	*  授权日期
	*/
	public Date getGrantdate (){
		return grantdate;
	}
	public void setGrantdate (Date grantdate){
		this.grantdate = grantdate;
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

	/**
	*  权利人
	*/
	public String getPatentee (){
		return patentee;
	}
	public void setPatentee (String patentee){
		this.patentee = patentee;
	}

	/**
	*  发明人
	*/
	public String getInventors (){
		return inventors;
	}
	public void setInventors (String inventors){
		this.inventors = inventors;
	}

	/**
	*  发明专利（标准）有效状态
	*/
	public String getStatus (){
		return status;
	}
	public void setStatus (String status){
		this.status = status;
	}

}