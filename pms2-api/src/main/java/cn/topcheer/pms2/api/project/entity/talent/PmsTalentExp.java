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
 *  科技人才-教育工作表
 */
@Entity
@Table(name="PMS_TALENT_EXP")
@ClassInfo(name = "科技人才-教育工作表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentExp {

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
     *  起始时间
     */
	@ApiModelProperty("起始时间")
	@TableField("START_DATE")
	@Column(columnDefinition = "START_DATE")
	@FieldDes(name = "start_date", type = "Date", memo = "起始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date startDate;

	/**
     *  结束时间
     */
	@ApiModelProperty("结束时间")
	@TableField("END_DATE")
	@Column(columnDefinition = "END_DATE")
	@FieldDes(name = "end_date", type = "Date", memo = "结束时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date endDate;

	/**
     *  毕业院校/工作单位/学术组织或重要期刊名称
     */
	@ApiModelProperty("毕业院校/工作单位/学术组织或重要期刊名称")
	@TableField("NAME")
	@Column(columnDefinition = "NAME")
	private String name;

	/**
     *  国家
     */
	@ApiModelProperty("国家")
	@TableField("NATION")
	@Column(columnDefinition = "NATION")
	private String nation;

	/**
     *  所学专业/从事专业
     */
	@ApiModelProperty("所学专业/从事专业")
	@TableField("MAJOR")
	@Column(columnDefinition = "MAJOR")
	private String major;

	/**
     *  学历
     */
	@ApiModelProperty("学历")
	@TableField("EDUCATION")
	@Column(columnDefinition = "EDUCATION")
	private String education;

	/**
     *  学位
     */
	@ApiModelProperty("学位")
	@TableField("DEGREE")
	@Column(columnDefinition = "DEGREE")
	private String degree;

	/**
     *  职称
     */
	@ApiModelProperty("职称")
	@TableField("TITLE")
	@Column(columnDefinition = "TITLE")
	private String title;

	/**
     *  职务
     */
	@ApiModelProperty("职务")
	@TableField("POST")
	@Column(columnDefinition = "POST")
	private String post;

	/**
     *  导师
     */
	@ApiModelProperty("导师")
	@TableField("MENTOR")
	@Column(columnDefinition = "MENTOR")
	private String mentor;

	/**
     *  任职时间
     */
	@ApiModelProperty("任职时间")
	@TableField("TENURE_DATE")
	@Column(columnDefinition = "TENURE_DATE")
	@FieldDes(name = "tenure_date", type = "Date", memo = "任职时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date tenureDate;

	/**
	 * 单位名称
	 */
	@ApiModelProperty("")
	@TableField("UNITNAME")
	@Column(columnDefinition = "UNITNAME")
	@FieldDes(name = "unitname", lenth = "255", type = "String", memo = "")
	private String unitname;


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
	*  起始时间
	*/
	public Date getStartDate (){
		return startDate;
	}
	public void setStartDate (Date startDate){
		this.startDate = startDate;
	}

	/**
	*  结束时间
	*/
	public Date getEndDate (){
		return endDate;
	}
	public void setEndDate (Date endDate){
		this.endDate = endDate;
	}

	/**
	*  毕业院校/工作单位/学术组织或重要期刊名称
	*/
	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name = name;
	}

	/**
	*  国家
	*/
	public String getNation (){
		return nation;
	}
	public void setNation (String nation){
		this.nation = nation;
	}

	/**
	*  所学专业/从事专业
	*/
	public String getMajor (){
		return major;
	}
	public void setMajor (String major){
		this.major = major;
	}

	/**
	*  学历
	*/
	public String getEducation (){
		return education;
	}
	public void setEducation (String education){
		this.education = education;
	}

	/**
	*  学位
	*/
	public String getDegree (){
		return degree;
	}
	public void setDegree (String degree){
		this.degree = degree;
	}

	/**
	*  职称
	*/
	public String getTitle (){
		return title;
	}
	public void setTitle (String title){
		this.title = title;
	}

	/**
	*  职务
	*/
	public String getPost (){
		return post;
	}
	public void setPost (String post){
		this.post = post;
	}

	/**
	*  任职时间
	*/
	public Date getTenureDate (){
		return tenureDate;
	}
	public void setTenureDate (Date tenureDate){
		this.tenureDate = tenureDate;
	}

	public String getMentor() {
		return mentor;
	}

	public void setMentor(String mentor) {
		this.mentor = mentor;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
}