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
 *  科技人才-论文专著表
 */
@Entity
@Table(name="PMS_TALENT_THESIS")
@ClassInfo(name = "科技人才-论文专著表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentThesis {

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
     *  第一作者
     */
	@ApiModelProperty("第一作者")
	@TableField("FIRSTAUTHOR")
	@Column(columnDefinition = "FIRSTAUTHOR")
	private String firstauthor;

	/**
     *  影响因子
     */
	@ApiModelProperty("影响因子")
	@TableField("INFLUENCE")
	@Column(columnDefinition = "INFLUENCE")
	@FieldDes(name = "influence", type = "BigDecimal", memo = "影响因子")
	private BigDecimal influence;

	/**
     *  年卷页码
     */
	@ApiModelProperty("年卷页码")
	@TableField("PAGE")
	@Column(columnDefinition = "PAGE")
	private String page;

	/**
     *  发表时间
     */
	@ApiModelProperty("发表时间")
	@TableField("PUBLISHDATE")
	@Column(columnDefinition = "PUBLISHDATE")
	private String publishdate;

	/**
     *  通讯作者
     */
	@ApiModelProperty("通讯作者")
	@TableField("TXAUTHOR")
	@Column(columnDefinition = "TXAUTHOR")
	private String txauthor;

	/**
     *  所有作者
     */
	@ApiModelProperty("所有作者")
	@TableField("AUTHORS")
	@Column(columnDefinition = "AUTHORS")
	private String authors;

	/**
     *  sci引用次数
     */
	@ApiModelProperty("sci引用次数")
	@TableField("SCINUM")
	@Column(columnDefinition = "SCINUM")
	@FieldDes(name = "scinum", type = "Integer", memo = "sci引用次数")
	private Integer scinum;

	/**
     *  ei引用次数
     */
	@ApiModelProperty("ei引用次数")
	@TableField("EINUM")
	@Column(columnDefinition = "EINUM")
	@FieldDes(name = "einum", type = "Integer", memo = "ei引用次数")
	private Integer einum;

	/**
     *  他引次数
     */
	@ApiModelProperty("他引次数")
	@TableField("SITENUM")
	@Column(columnDefinition = "SITENUM")
	@FieldDes(name = "sitenum", type = "Integer", memo = "他引次数")
	private Integer sitenum;

	/**
     *  收录情况
     */
	@ApiModelProperty("收录情况")
	@TableField("INCLUSIONCONDITION")
	@Column(columnDefinition = "INCLUSIONCONDITION")
	private String inclusioncondition;

	/**
     *  出版社
     */
	@ApiModelProperty("出版社")
	@TableField("PUBLISHHOUSE")
	@Column(columnDefinition = "PUBLISHHOUSE")
	private String publishhouse;

	/**
     *  是否被SCI、EI、SSCI、CSSCI收录
     */
	@ApiModelProperty("是否被SCI、EI、SSCI、CSSCI收录")
	@TableField("BE_EMPLOY")
	@Column(columnDefinition = "BE_EMPLOY")
	private String beEmploy;

	/**
     *  分区
     */
	@ApiModelProperty("分区")
	@TableField("paperpartition")
	@Column(columnDefinition = "paperpartition")
	private String paperpartition;

	/**
     *  发行国家和地区
     */
	@ApiModelProperty("发行国家和地区")
	@TableField("NATION")
	@Column(columnDefinition = "NATION")
	private String nation;

	/**
     *  年份
     */
	@ApiModelProperty("年份")
	@TableField("YEAR")
	@Column(columnDefinition = "YEAR")
	private String year;

	/**
	 *  卷期
	 */
	@ApiModelProperty("卷期")
	@TableField("PERIOD")
	@Column(columnDefinition = "PERIOD")
	private String period;

	/**
	 *  页码
	 */
	@ApiModelProperty("页码")
	@TableField("PAGE_SIZE")
	@Column(columnDefinition = "PAGE_SIZE")
	private String pageSize;

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
	*  第一作者
	*/
	public String getFirstauthor (){
		return firstauthor;
	}
	public void setFirstauthor (String firstauthor){
		this.firstauthor = firstauthor;
	}

	/**
	*  影响因子
	*/
	public BigDecimal getInfluence (){
		return influence;
	}
	public void setInfluence (BigDecimal influence){
		this.influence = influence;
	}

	/**
	*  年卷页码
	*/
	public String getPage (){
		return page;
	}
	public void setPage (String page){
		this.page = page;
	}

	/**
	*  发表时间
	*/
	public String getPublishdate (){
		return publishdate;
	}
	public void setPublishdate (String publishdate){
		this.publishdate = publishdate;
	}

	/**
	*  通讯作者
	*/
	public String getTxauthor (){
		return txauthor;
	}
	public void setTxauthor (String txauthor){
		this.txauthor = txauthor;
	}

	/**
	*  所有作者
	*/
	public String getAuthors (){
		return authors;
	}
	public void setAuthors (String authors){
		this.authors = authors;
	}

	/**
	*  sci引用次数
	*/
	public Integer getScinum (){
		return scinum;
	}
	public void setScinum (Integer scinum){
		this.scinum = scinum;
	}

	/**
	*  ei引用次数
	*/
	public Integer getEinum (){
		return einum;
	}
	public void setEinum (Integer einum){
		this.einum = einum;
	}

	/**
	*  他引次数
	*/
	public Integer getSitenum (){
		return sitenum;
	}
	public void setSitenum (Integer sitenum){
		this.sitenum = sitenum;
	}

	/**
	*  收录情况
	*/
	public String getInclusioncondition (){
		return inclusioncondition;
	}
	public void setInclusioncondition (String inclusioncondition){
		this.inclusioncondition = inclusioncondition;
	}

	/**
	*  出版社
	*/
	public String getPublishhouse (){
		return publishhouse;
	}
	public void setPublishhouse (String publishhouse){
		this.publishhouse = publishhouse;
	}

	/**
	*  是否被SCI、EI、SSCI、CSSCI收录
	*/
	public String getBeEmploy (){
		return beEmploy;
	}
	public void setBeEmploy (String beEmploy){
		this.beEmploy = beEmploy;
	}

	/**
	*  分区
	*/
	public String getPaperpartition (){
		return paperpartition;
	}
	public void setPaperpartition (String paperpartition){
		this.paperpartition = paperpartition;
	}

	/**
	*  发行国家和地区
	*/
	public String getNation (){
		return nation;
	}
	public void setNation (String nation){
		this.nation = nation;
	}

	/**
	*  年份
	*/
	public String getYear (){
		return year;
	}
	public void setYear (String year){
		this.year = year;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
}