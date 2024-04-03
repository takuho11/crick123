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
 *  科技奖励-论文表
 */
@Entity
@Table(name="PMS_PRIZE_THESIS")
@ClassInfo(name = "科技奖励-论文表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizeThesis {

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
     *  论文专著名称
     */
	@ApiModelProperty("论文专著名称")
	@TableField("NAME")
	@Column(columnDefinition = "NAME")
	private String name;

	/**
     *  刊名
     */
	@ApiModelProperty("刊名")
	@TableField("PUBLICATIONNAME")
	@Column(columnDefinition = "PUBLICATIONNAME")
	private String publicationname;

	/**
     *  第一作者（含共同）
     */
	@ApiModelProperty("第一作者（含共同）")
	@TableField("FIRSTAUTHOR")
	@Column(columnDefinition = "FIRSTAUTHOR")
	private String firstauthor;

	/**
     *  年卷页码（xx年xx卷xx页）
     */
	@ApiModelProperty("年卷页码（xx年xx卷xx页）")
	@TableField("PAGE")
	@Column(columnDefinition = "PAGE")
	private String page;

	/**
     *  发表时间（年月 日）
     */
	@ApiModelProperty("发表时间（年月 日）")
	@TableField("PUBLISHDATE")
	@Column(columnDefinition = "PUBLISHDATE")
	@FieldDes(name = "publishdate", type = "Date", memo = "发表时间（年月 日）")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date publishdate;

	/**
     *  通讯作者（含共同）
     */
	@ApiModelProperty("通讯作者（含共同）")
	@TableField("TXAUTHOR")
	@Column(columnDefinition = "TXAUTHOR")
	private String txauthor;

	/**
     *  他引总次数
     */
	@ApiModelProperty("他引总次数")
	@TableField("SITENUM")
	@Column(columnDefinition = "SITENUM")
	@FieldDes(name = "sitenum", type = "Integer", memo = "他引总次数")
	private Integer sitenum;

	/**
     *  检索数据库
     */
	@ApiModelProperty("检索数据库")
	@TableField("RETRIEVAL")
	@Column(columnDefinition = "RETRIEVAL")
	private String retrieval;

	/**
     *  论文署名单位是否包含国外单位
     */
	@ApiModelProperty("论文署名单位是否包含国外单位")
	@TableField("CONTAINUNIT")
	@Column(columnDefinition = "CONTAINUNIT")
	private String containunit;

	/**
	 *  作者
	 */
	@ApiModelProperty("作者")
	@TableField("AUTHORS")
	@Column(columnDefinition = "AUTHORS")
	private String authors;


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
	*  论文专著名称
	*/
	public String getName (){
		return name;
	}
	public void setName (String name){
		this.name = name;
	}

	/**
	*  刊名
	*/
	public String getPublicationname (){
		return publicationname;
	}
	public void setPublicationname (String publicationname){
		this.publicationname = publicationname;
	}

	/**
	*  第一作者（含共同）
	*/
	public String getFirstauthor (){
		return firstauthor;
	}
	public void setFirstauthor (String firstauthor){
		this.firstauthor = firstauthor;
	}

	/**
	*  年卷页码（xx年xx卷xx页）
	*/
	public String getPage (){
		return page;
	}
	public void setPage (String page){
		this.page = page;
	}

	/**
	*  发表时间（年月 日）
	*/
	public Date getPublishdate (){
		return publishdate;
	}
	public void setPublishdate (Date publishdate){
		this.publishdate = publishdate;
	}

	/**
	*  通讯作者（含共同）
	*/
	public String getTxauthor (){
		return txauthor;
	}
	public void setTxauthor (String txauthor){
		this.txauthor = txauthor;
	}

	/**
	*  他引总次数
	*/
	public Integer getSitenum (){
		return sitenum;
	}
	public void setSitenum (Integer sitenum){
		this.sitenum = sitenum;
	}

	/**
	*  检索数据库
	*/
	public String getRetrieval (){
		return retrieval;
	}
	public void setRetrieval (String retrieval){
		this.retrieval = retrieval;
	}

	/**
	*  论文署名单位是否包含国外单位
	*/
	public String getContainunit (){
		return containunit;
	}
	public void setContainunit (String containunit){
		this.containunit = containunit;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}
}