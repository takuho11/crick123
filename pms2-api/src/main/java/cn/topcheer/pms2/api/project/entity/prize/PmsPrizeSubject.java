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
 *  科技奖励-学科表
 */
@Entity
@Table(name="PMS_PRIZE_SUBJECT")
@ClassInfo(name = "科技奖励-学科表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizeSubject {

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
     *  学科类型
     */
	@ApiModelProperty("学科类型")
	@TableField("SUBTYPE")
	@Column(columnDefinition = "SUBTYPE")
	private String subtype;

	/**
     *  学科类型编码
     */
	@ApiModelProperty("学科类型编码")
	@TableField("SUBJECTTYPE")
	@Column(columnDefinition = "SUBJECTTYPE")
	private String subjecttype;

	/**
     *  一级学科名称
     */
	@ApiModelProperty("一级学科名称")
	@TableField("SUBJECTONENAME")
	@Column(columnDefinition = "SUBJECTONENAME")
	private String subjectonename;

	/**
     *  一级学科编码
     */
	@ApiModelProperty("一级学科编码")
	@TableField("SUBJECTONECODE")
	@Column(columnDefinition = "SUBJECTONECODE")
	private String subjectonecode;

	/**
     *  二级学科名称
     */
	@ApiModelProperty("二级学科名称")
	@TableField("SUBJECTTWONAME")
	@Column(columnDefinition = "SUBJECTTWONAME")
	private String subjecttwoname;

	/**
     *  二级学科编码
     */
	@ApiModelProperty("二级学科编码")
	@TableField("SUBJECTTWOCODE")
	@Column(columnDefinition = "SUBJECTTWOCODE")
	private String subjecttwocode;

	/**
     *  三级学科名称
     */
	@ApiModelProperty("三级学科名称")
	@TableField("SUBJECTTHREENAME")
	@Column(columnDefinition = "SUBJECTTHREENAME")
	private String subjectthreename;

	/**
     *  三级学科编码
     */
	@ApiModelProperty("三级学科编码")
	@TableField("SUBJECTTHREECODE")
	@Column(columnDefinition = "SUBJECTTHREECODE")
	private String subjectthreecode;

	/**
     *  四级学科名称
     */
	@ApiModelProperty("四级学科名称")
	@TableField("SUBJECTFOURNAME")
	@Column(columnDefinition = "SUBJECTFOURNAME")
	private String subjectfourname;

	/**
     *  四级学科编码
     */
	@ApiModelProperty("四级学科编码")
	@TableField("SUBJECTFOURCODE")
	@Column(columnDefinition = "SUBJECTFOURCODE")
	private String subjectfourcode;

	/**
     *  最终学科名称
     */
	@ApiModelProperty("最终学科名称")
	@TableField("SUBJECTENDNAME")
	@Column(columnDefinition = "SUBJECTENDNAME")
	private String subjectendname;

	/**
     *  最终学科编码
     */
	@ApiModelProperty("最终学科编码")
	@TableField("SUBJECTENDCODE")
	@Column(columnDefinition = "SUBJECTENDCODE")
	private String subjectendcode;


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
	*  学科类型
	*/
	public String getSubtype (){
		return subtype;
	}
	public void setSubtype (String subtype){
		this.subtype = subtype;
	}

	/**
	*  学科类型编码
	*/
	public String getSubjecttype (){
		return subjecttype;
	}
	public void setSubjecttype (String subjecttype){
		this.subjecttype = subjecttype;
	}

	/**
	*  一级学科名称
	*/
	public String getSubjectonename (){
		return subjectonename;
	}
	public void setSubjectonename (String subjectonename){
		this.subjectonename = subjectonename;
	}

	/**
	*  一级学科编码
	*/
	public String getSubjectonecode (){
		return subjectonecode;
	}
	public void setSubjectonecode (String subjectonecode){
		this.subjectonecode = subjectonecode;
	}

	/**
	*  二级学科名称
	*/
	public String getSubjecttwoname (){
		return subjecttwoname;
	}
	public void setSubjecttwoname (String subjecttwoname){
		this.subjecttwoname = subjecttwoname;
	}

	/**
	*  二级学科编码
	*/
	public String getSubjecttwocode (){
		return subjecttwocode;
	}
	public void setSubjecttwocode (String subjecttwocode){
		this.subjecttwocode = subjecttwocode;
	}

	/**
	*  三级学科名称
	*/
	public String getSubjectthreename (){
		return subjectthreename;
	}
	public void setSubjectthreename (String subjectthreename){
		this.subjectthreename = subjectthreename;
	}

	/**
	*  三级学科编码
	*/
	public String getSubjectthreecode (){
		return subjectthreecode;
	}
	public void setSubjectthreecode (String subjectthreecode){
		this.subjectthreecode = subjectthreecode;
	}

	/**
	*  四级学科名称
	*/
	public String getSubjectfourname (){
		return subjectfourname;
	}
	public void setSubjectfourname (String subjectfourname){
		this.subjectfourname = subjectfourname;
	}

	/**
	*  四级学科编码
	*/
	public String getSubjectfourcode (){
		return subjectfourcode;
	}
	public void setSubjectfourcode (String subjectfourcode){
		this.subjectfourcode = subjectfourcode;
	}

	/**
	*  最终学科名称
	*/
	public String getSubjectendname (){
		return subjectendname;
	}
	public void setSubjectendname (String subjectendname){
		this.subjectendname = subjectendname;
	}

	/**
	*  最终学科编码
	*/
	public String getSubjectendcode (){
		return subjectendcode;
	}
	public void setSubjectendcode (String subjectendcode){
		this.subjectendcode = subjectendcode;
	}

}