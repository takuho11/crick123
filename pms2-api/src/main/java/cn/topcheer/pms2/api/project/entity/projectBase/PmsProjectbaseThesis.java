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
 *  计划项目-论文
 */
@Entity
@Table(name="PMS_PROJECTBASE_THESIS")
@ClassInfo(name = "计划项目-论文", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseThesis {

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
     *  论文专著题目
     */
	private String thesisname;
	/**
     *  第一作者
     */
	private String firstauthor;
	/**
     *  通讯作者
     */
	private String correspondingauthor;
	/**
     *  期刊/会议源
     */
	private String issuesource;
	/**
     *  期刊类别
     */
	private String issuetype;
	/**
     *  刊号书号
     */
	private String issueno;
	/**
     *  刊物主办单位/著作出版社
     */
	private String issueunit;
	/**
	 *  影响因子
	 */
	@ApiModelProperty("影响因子")
	@TableField("INFLUENCE")
	@Column(columnDefinition = "INFLUENCE")
	@FieldDes(name = "influence", type = "BigDecimal", memo = "影响因子")
	private BigDecimal influence;
	/**
     *  他引次数
     */
	@FieldDes(name = "thesisquotenum", type = "Integer", memo = "他引次数")
	private Integer thesisquotenum;
	/**
     *  成员姓名
     */
	private String authorname;
	/**
     *  成员排名
     */
	@FieldDes(name = "authorrank", type = "Integer", memo = "成员排名")
	private Integer authorrank;
	/**
     *  年份
     */
	private String year;
	/**
     *  发表时间
     */
	@FieldDes(name = "publishdate", type = "Date", memo = "发表时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date publishdate;
	/**
     *  年卷页码（xx年xx卷xx页）
     */
	private String page;
	/**
     *  被SCI收录情况
     */
	@FieldDes(name = "scinum", type = "Integer", memo = "被SCI收录情况")
	private Integer scinum;
	/**
     *  被EI收录情况
     */
	@FieldDes(name = "einum", type = "Integer", memo = "被EI收录情况")
	private Integer einum;
	/**
     *  被ISTP收录情况
     */
	@FieldDes(name = "istpnum", type = "Integer", memo = "被ISTP收录情况")
	private Integer istpnum;
	/**
     *  出版社
     */
	private String publishinghouse;
	/**
     *  出版时间
     */
	@FieldDes(name = "publishingdate", type = "Date", memo = "出版时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date publishingdate;
	/**
     *  地址
     */
	private String address;
	/**
     *  收录情况
     */
	private String include;
	/**
     *  总字数
     */
	private String totalwords;

	/**
	 *  第一作者单位
	 */
	@ApiModelProperty("第一作者单位")
	@TableField("AUTHOR_UNIT")
	@Column(columnDefinition = "AUTHOR_UNIT")
	private String authorUnit;

	/**
	 *  JCR分区
	 */
	@ApiModelProperty("JCR分区")
	@TableField("JCR")
	@Column(columnDefinition = "JCR")
	private String jcr;

	/**
	 *  CIP核字号
	 */
	@ApiModelProperty("CIP核字号")
	@TableField("CIP_NUM")
	@Column(columnDefinition = "CIP_NUM")
	private String cipNum;

	/**
	 *  中文/外文
	 */
	@ApiModelProperty("中文/外文")
	@TableField("CN")
	@Column(columnDefinition = "CN")
	private String cn;


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
	*  论文专著题目
	*/
	public String getThesisname (){
	return thesisname;
	}
	public void setThesisname (String thesisname){
	this.thesisname=thesisname;
	}

	/**
	*  第一作者
	*/
	public String getFirstauthor (){
	return firstauthor;
	}
	public void setFirstauthor (String firstauthor){
	this.firstauthor=firstauthor;
	}

	/**
	*  通讯作者
	*/
	public String getCorrespondingauthor (){
	return correspondingauthor;
	}
	public void setCorrespondingauthor (String correspondingauthor){
	this.correspondingauthor=correspondingauthor;
	}

	/**
	*  期刊/会议源
	*/
	public String getIssuesource (){
	return issuesource;
	}
	public void setIssuesource (String issuesource){
	this.issuesource=issuesource;
	}

	/**
	*  期刊类别
	*/
	public String getIssuetype (){
	return issuetype;
	}
	public void setIssuetype (String issuetype){
	this.issuetype=issuetype;
	}

	/**
	*  刊号书号
	*/
	public String getIssueno (){
	return issueno;
	}
	public void setIssueno (String issueno){
	this.issueno=issueno;
	}

	/**
	*  刊物主办单位/著作出版社
	*/
	public String getIssueunit (){
	return issueunit;
	}
	public void setIssueunit (String issueunit){
	this.issueunit=issueunit;
	}

	/**
	*  影响因子
	*/
	public BigDecimal getInfluence (){
	return influence;
	}
	public void setInfluence (BigDecimal influence){
	this.influence=influence;
	}

	/**
	*  他引次数
	*/
	public Integer getThesisquotenum (){
	return thesisquotenum;
	}
	public void setThesisquotenum (Integer thesisquotenum){
	this.thesisquotenum=thesisquotenum;
	}

	/**
	*  成员姓名
	*/
	public String getAuthorname (){
	return authorname;
	}
	public void setAuthorname (String authorname){
	this.authorname=authorname;
	}

	/**
	*  成员排名
	*/
	public Integer getAuthorrank (){
	return authorrank;
	}
	public void setAuthorrank (Integer authorrank){
	this.authorrank=authorrank;
	}

	/**
	*  年份
	*/
	public String getYear (){
	return year;
	}
	public void setYear (String year){
	this.year=year;
	}

	/**
	*  发表时间
	*/
	public Date getPublishdate (){
	return publishdate;
	}
	public void setPublishdate (Date publishdate){
	this.publishdate=publishdate;
	}

	/**
	*  年卷页码（xx年xx卷xx页）
	*/
	public String getPage (){
	return page;
	}
	public void setPage (String page){
	this.page=page;
	}

	/**
	*  被SCI收录情况
	*/
	public Integer getScinum (){
	return scinum;
	}
	public void setScinum (Integer scinum){
	this.scinum=scinum;
	}

	/**
	*  被EI收录情况
	*/
	public Integer getEinum (){
	return einum;
	}
	public void setEinum (Integer einum){
	this.einum=einum;
	}

	/**
	*  被ISTP收录情况
	*/
	public Integer getIstpnum (){
	return istpnum;
	}
	public void setIstpnum (Integer istpnum){
	this.istpnum=istpnum;
	}

	/**
	*  出版社
	*/
	public String getPublishinghouse (){
	return publishinghouse;
	}
	public void setPublishinghouse (String publishinghouse){
	this.publishinghouse=publishinghouse;
	}

	/**
	*  出版时间
	*/
	public Date getPublishingdate (){
	return publishingdate;
	}
	public void setPublishingdate (Date publishingdate){
	this.publishingdate=publishingdate;
	}

	/**
	*  地址
	*/
	public String getAddress (){
	return address;
	}
	public void setAddress (String address){
	this.address=address;
	}

	/**
	*  收录情况
	*/
	public String getInclude (){
	return include;
	}
	public void setInclude (String include){
	this.include=include;
	}

	/**
	*  总字数
	*/
	public String getTotalwords (){
	return totalwords;
	}
	public void setTotalwords (String totalwords){
	this.totalwords=totalwords;
	}

	public String getAuthorUnit() {
		return authorUnit;
	}

	public void setAuthorUnit(String authorUnit) {
		this.authorUnit = authorUnit;
	}

	public String getJcr() {
		return jcr;
	}

	public void setJcr(String jcr) {
		this.jcr = jcr;
	}

	public String getCipNum() {
		return cipNum;
	}

	public void setCipNum(String cipNum) {
		this.cipNum = cipNum;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}
}