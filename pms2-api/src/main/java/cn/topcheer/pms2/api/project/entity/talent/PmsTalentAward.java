/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:30:13
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
 *  科技人才-奖励表
 */
@Entity
@Table(name="PMS_TALENT_AWARD")
@ClassInfo(name = "科技人才-奖励表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentAward {

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
	@TableField("AWARDNAME")
	@Column(columnDefinition = "AWARDNAME")
	private String awardname;

	/**
     *  奖励类别
     */
	@ApiModelProperty("奖励类别")
	@TableField("AWARDTYPE")
	@Column(columnDefinition = "AWARDTYPE")
	private String awardtype;

	/**
     *  奖励等级
     */
	@ApiModelProperty("奖励等级")
	@TableField("AWARDLEVEL")
	@Column(columnDefinition = "AWARDLEVEL")
	private String awardlevel;

	/**
     *  获奖时间
     */
	@ApiModelProperty("获奖时间")
	@TableField("AWARDDATE")
	@Column(columnDefinition = "AWARDDATE")
	@FieldDes(name = "awarddate", type = "Date", memo = "获奖时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date awarddate;

	/**
     *  授奖部门
     */
	@ApiModelProperty("授奖部门")
	@TableField("AWARDDEPARTMENT")
	@Column(columnDefinition = "AWARDDEPARTMENT")
	private String awarddepartment;

	/**
     *  获奖编号
     */
	@ApiModelProperty("获奖编号")
	@TableField("CERTIFICATENO")
	@Column(columnDefinition = "CERTIFICATENO")
	private String certificateno;

	/**
     *  获奖单位
     */
	@ApiModelProperty("获奖单位")
	@TableField("ORGANIZATION")
	@Column(columnDefinition = "ORGANIZATION")
	private String organization;

	/**
     *  主要完成人
     */
	@ApiModelProperty("主要完成人")
	@TableField("MAINMEMBER")
	@Column(columnDefinition = "MAINMEMBER")
	private String mainmember;

	/**
     *  项目名称
     */
	@ApiModelProperty("项目名称")
	@TableField("AWARDPROJECTNAME")
	@Column(columnDefinition = "AWARDPROJECTNAME")
	private String awardprojectname;

	/**
	 *  排名
	 */
	@ApiModelProperty("排名")
	@TableField("AWARD_RANK")
	@Column(columnDefinition = "AWARD_RANK")
	private Integer awardRank;


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
	public String getAwardname (){
		return awardname;
	}
	public void setAwardname (String awardname){
		this.awardname = awardname;
	}

	/**
	*  奖励类别
	*/
	public String getAwardtype (){
		return awardtype;
	}
	public void setAwardtype (String awardtype){
		this.awardtype = awardtype;
	}

	/**
	*  奖励等级
	*/
	public String getAwardlevel (){
		return awardlevel;
	}
	public void setAwardlevel (String awardlevel){
		this.awardlevel = awardlevel;
	}

	/**
	*  获奖时间
	*/
	public Date getAwarddate (){
		return awarddate;
	}
	public void setAwarddate (Date awarddate){
		this.awarddate = awarddate;
	}

	/**
	*  授奖部门
	*/
	public String getAwarddepartment (){
		return awarddepartment;
	}
	public void setAwarddepartment (String awarddepartment){
		this.awarddepartment = awarddepartment;
	}

	/**
	*  获奖编号
	*/
	public String getCertificateno (){
		return certificateno;
	}
	public void setCertificateno (String certificateno){
		this.certificateno = certificateno;
	}

	/**
	*  获奖单位
	*/
	public String getOrganization (){
		return organization;
	}
	public void setOrganization (String organization){
		this.organization = organization;
	}

	/**
	*  主要完成人
	*/
	public String getMainmember (){
		return mainmember;
	}
	public void setMainmember (String mainmember){
		this.mainmember = mainmember;
	}

	/**
	*  项目名称
	*/
	public String getAwardprojectname (){
		return awardprojectname;
	}
	public void setAwardprojectname (String awardprojectname){
		this.awardprojectname = awardprojectname;
	}

	public Integer getAwardRank() {
		return awardRank;
	}

	public void setAwardRank(Integer awardRank) {
		this.awardRank = awardRank;
	}
}