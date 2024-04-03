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
 *  科技奖励-完成人合作关系表
 */
@Entity
@Table(name="PMS_PRIZE_PARTNERSHIP")
@ClassInfo(name = "科技奖励-完成人合作关系表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizePartnership {

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
     *  合作方式
     */
	@ApiModelProperty("合作方式")
	@TableField("COOPERATIONTYPE")
	@Column(columnDefinition = "COOPERATIONTYPE")
	private String cooperationtype;

	/**
     *  序号
     */
	@ApiModelProperty("序号")
	@TableField("COOPERATIONINDEX")
	@Column(columnDefinition = "COOPERATIONINDEX")
	private String cooperationindex;

	/**
     *  合作者
     */
	@ApiModelProperty("合作者")
	@TableField("COOPERATIONPARTY")
	@Column(columnDefinition = "COOPERATIONPARTY")
	private String cooperationparty;

	/**
     *  合作成果
     */
	@ApiModelProperty("合作成果")
	@TableField("COOPERATIONRESULTS")
	@Column(columnDefinition = "COOPERATIONRESULTS")
	private String cooperationresults;

	/**
     *  证明材料编号
     */
	@ApiModelProperty("证明材料编号")
	@TableField("EVIDENCENO")
	@Column(columnDefinition = "EVIDENCENO")
	private String evidenceno;

	/**
     *  合作开始时间
     */
	@ApiModelProperty("合作开始时间")
	@TableField("COOPERATIONSTARTTIME")
	@Column(columnDefinition = "COOPERATIONSTARTTIME")
	@FieldDes(name = "cooperationstarttime", type = "Date", memo = "合作开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date cooperationstarttime;

	/**
     *  合作结束时间
     */
	@ApiModelProperty("合作结束时间")
	@TableField("COOPERATIONENDTIME")
	@Column(columnDefinition = "COOPERATIONENDTIME")
	@FieldDes(name = "cooperationendtime", type = "Date", memo = "合作结束时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date cooperationendtime;

	/**
     *  合作时间
     */
	@ApiModelProperty("合作时间")
	@TableField("COOPERATIONTIMES")
	@Column(columnDefinition = "COOPERATIONTIMES")
	private String cooperationtimes;


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
	*  合作方式
	*/
	public String getCooperationtype (){
		return cooperationtype;
	}
	public void setCooperationtype (String cooperationtype){
		this.cooperationtype = cooperationtype;
	}

	/**
	*  序号
	*/
	public String getCooperationindex (){
		return cooperationindex;
	}
	public void setCooperationindex (String cooperationindex){
		this.cooperationindex = cooperationindex;
	}

	/**
	*  合作者
	*/
	public String getCooperationparty (){
		return cooperationparty;
	}
	public void setCooperationparty (String cooperationparty){
		this.cooperationparty = cooperationparty;
	}

	/**
	*  合作成果
	*/
	public String getCooperationresults (){
		return cooperationresults;
	}
	public void setCooperationresults (String cooperationresults){
		this.cooperationresults = cooperationresults;
	}

	/**
	*  证明材料编号
	*/
	public String getEvidenceno (){
		return evidenceno;
	}
	public void setEvidenceno (String evidenceno){
		this.evidenceno = evidenceno;
	}

	/**
	*  合作开始时间
	*/
	public Date getCooperationstarttime (){
		return cooperationstarttime;
	}
	public void setCooperationstarttime (Date cooperationstarttime){
		this.cooperationstarttime = cooperationstarttime;
	}

	/**
	*  合作结束时间
	*/
	public Date getCooperationendtime (){
		return cooperationendtime;
	}
	public void setCooperationendtime (Date cooperationendtime){
		this.cooperationendtime = cooperationendtime;
	}

	/**
	*  合作时间
	*/
	public String getCooperationtimes (){
		return cooperationtimes;
	}
	public void setCooperationtimes (String cooperationtimes){
		this.cooperationtimes = cooperationtimes;
	}

}