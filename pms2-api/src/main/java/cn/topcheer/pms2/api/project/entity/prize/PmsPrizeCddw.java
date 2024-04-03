/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:32:16
 */
package cn.topcheer.pms2.api.project.entity.prize;

import javax.persistence.*;
import java.util.Date;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

/**
 *  科技奖励-单位表
 */
@Entity
@Table(name="PMS_PRIZE_CDDW")
@ClassInfo(name = "科技奖励-单位表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizeCddw {

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
     *  单位名称
     */
	@ApiModelProperty("单位名称")
	@TableField("UNITNAME")
	@Column(columnDefinition = "UNITNAME")
	private String unitname;

	/**
     *  单位译名
     */
	@ApiModelProperty("单位译名")
	@TableField("FOREIGNNAME")
	@Column(columnDefinition = "FOREIGNNAME")
	private String foreignname;

	/**
     *  统一社会信用代码
     */
	@ApiModelProperty("统一社会信用代码")
	@TableField("CREDITCODE")
	@Column(columnDefinition = "CREDITCODE")
	private String creditcode;

	/**
     *  法定代表人
     */
	@ApiModelProperty("法定代表人")
	@TableField("LEGAL_NAME")
	@Column(columnDefinition = "LEGAL_NAME")
	private String legalName;

	/**
     *  省
     */
	@ApiModelProperty("省")
	@TableField("PROVINCE")
	@Column(columnDefinition = "PROVINCE")
	private String province;

	/**
     *  市
     */
	@ApiModelProperty("市")
	@TableField("CITY")
	@Column(columnDefinition = "CITY")
	private String city;

	/**
     *  县
     */
	@ApiModelProperty("县")
	@TableField("COUNTY")
	@Column(columnDefinition = "COUNTY")
	private String county;

	/**
     *  街道
     */
	@ApiModelProperty("街道")
	@TableField("STREET")
	@Column(columnDefinition = "STREET")
	private String street;

	/**
     *  单位性质
     */
	@ApiModelProperty("单位性质")
	@TableField("UNITTYPE")
	@Column(columnDefinition = "UNITTYPE")
	private String unittype;

	/**
     *  传真
     */
	@ApiModelProperty("传真")
	@TableField("FAX")
	@Column(columnDefinition = "FAX")
	private String fax;

	/**
     *  邮政编码
     */
	@ApiModelProperty("邮政编码")
	@TableField("POSTALCODE")
	@Column(columnDefinition = "POSTALCODE")
	private String postalcode;

	/**
     *  通讯地址
     */
	@ApiModelProperty("通讯地址")
	@TableField("COMMINTUCATEADDRESS")
	@Column(columnDefinition = "COMMINTUCATEADDRESS")
	private String commintucateaddress;

	/**
     *  联系人
     */
	@ApiModelProperty("联系人")
	@TableField("LXR_NAME")
	@Column(columnDefinition = "LXR_NAME")
	private String lxrName;

	/**
     *  电话
     */
	@ApiModelProperty("电话")
	@TableField("LXR_TELEPHONE")
	@Column(columnDefinition = "LXR_TELEPHONE")
	private String lxrTelephone;

	/**
     *  移动电话
     */
	@ApiModelProperty("移动电话")
	@TableField("LXR_MOBILE")
	@Column(columnDefinition = "LXR_MOBILE")
	private String lxrMobile;

	/**
     *  电子邮箱
     */
	@ApiModelProperty("电子邮箱")
	@TableField("LXR_EMAIL")
	@Column(columnDefinition = "LXR_EMAIL")
	private String lxrEmail;

	/**
     *  对本项目科技创新和推广应用情况的贡献
     */
	@ApiModelProperty("对本项目科技创新和推广应用情况的贡献")
	@TableField("CONTRIBUTE")
	@Column(columnDefinition = "CONTRIBUTE")
	private String contribute;

	/**
     *  从事专业
     */
	@ApiModelProperty("从事专业")
	@TableField("MAINBUSINESS")
	@Column(columnDefinition = "MAINBUSINESS")
	private String mainbusiness;

	/**
     *  排名
     */
	@ApiModelProperty("排名")
	@TableField("RANKING")
	@Column(columnDefinition = "RANKING")
	private String ranking;

	/**
     *  曾获国家、省级科技奖励情况
     */
	@ApiModelProperty("曾获国家、省级科技奖励情况")
	@TableField("EVERAWARD")
	@Column(columnDefinition = "EVERAWARD")
	private String everaward;

	/**
     *  合作方向
     */
	@ApiModelProperty("合作方向")
	@TableField("DIRECTION")
	@Column(columnDefinition = "DIRECTION")
	private String direction;

	/**
     *  本单位与候选组织的合作情况
     */
	@ApiModelProperty("本单位与候选组织的合作情况")
	@TableField("SITUATION")
	@Column(columnDefinition = "SITUATION")
	private String situation;

	/**
     *  工作单位意见
     */
	@ApiModelProperty("工作单位意见")
	@TableField("OPINION")
	@Column(columnDefinition = "OPINION")
	private String opinion;

	/**
     *  工作单位意见-推荐奖等
     */
	@ApiModelProperty("工作单位意见-推荐奖等")
	@TableField("SUGGEST_LEVEL")
	@Column(columnDefinition = "SUGGEST_LEVEL")
	private String suggestLevel;

	/**
	 *  与我省合作单位名称
	 */
	@ApiModelProperty("工作单位意见-推荐奖等")
	@TableField("COP_COMPANY")
	@Column(columnDefinition = "COP_COMPANY")
	private String copCompany;

	/**
	 *  与我省合作的开始时间
	 */
	@ApiModelProperty("与我省合作的开始时间")
	@TableField("COP_START_DATE")
	@Column(columnDefinition = "COP_START_DATE")
	@FieldDes(name = "COP_START_DATE", type = "Date", memo = "与我省合作的开始时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date copStartDate;
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
	*  单位名称
	*/
	public String getUnitname(){
		return unitname;
	}
	public void setUnitname(String unitname){
		this.unitname = unitname;
	}

	/**
	*  单位译名
	*/
	public String getForeignname (){
		return foreignname;
	}
	public void setForeignname (String foreignname){
		this.foreignname = foreignname;
	}

	/**
	*  统一社会信用代码
	*/
	public String getCreditcode (){
		return creditcode;
	}
	public void setCreditcode (String creditcode){
		this.creditcode = creditcode;
	}

	/**
	*  法定代表人
	*/
	public String getLegalName (){
		return legalName;
	}
	public void setLegalName (String legalName){
		this.legalName = legalName;
	}

	/**
	*  省
	*/
	public String getProvince (){
		return province;
	}
	public void setProvince (String province){
		this.province = province;
	}

	/**
	*  市
	*/
	public String getCity (){
		return city;
	}
	public void setCity (String city){
		this.city = city;
	}

	/**
	*  县
	*/
	public String getCounty (){
		return county;
	}
	public void setCounty (String county){
		this.county = county;
	}

	/**
	*  街道
	*/
	public String getStreet (){
		return street;
	}
	public void setStreet (String street){
		this.street = street;
	}

	/**
	*  单位性质
	*/
	public String getUnittype (){
		return unittype;
	}
	public void setUnittype (String unittype){
		this.unittype = unittype;
	}

	/**
	*  传真
	*/
	public String getFax (){
		return fax;
	}
	public void setFax (String fax){
		this.fax = fax;
	}

	/**
	*  邮政编码
	*/
	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	/**
	*  通讯地址
	*/
	public String getCommintucateaddress (){
		return commintucateaddress;
	}
	public void setCommintucateaddress (String commintucateaddress){
		this.commintucateaddress = commintucateaddress;
	}

	/**
	*  联系人
	*/
	public String getLxrName (){
		return lxrName;
	}
	public void setLxrName (String lxrName){
		this.lxrName = lxrName;
	}

	/**
	*  电话
	*/
	public String getLxrTelephone (){
		return lxrTelephone;
	}
	public void setLxrTelephone (String lxrTelephone){
		this.lxrTelephone = lxrTelephone;
	}

	/**
	*  移动电话
	*/
	public String getLxrMobile (){
		return lxrMobile;
	}
	public void setLxrMobile (String lxrMobile){
		this.lxrMobile = lxrMobile;
	}

	/**
	*  电子邮箱
	*/
	public String getLxrEmail (){
		return lxrEmail;
	}
	public void setLxrEmail (String lxrEmail){
		this.lxrEmail = lxrEmail;
	}

	/**
	*  对本项目科技创新和推广应用情况的贡献
	*/
	public String getContribute (){
		return contribute;
	}
	public void setContribute (String contribute){
		this.contribute = contribute;
	}

	/**
	*  从事专业
	*/
	public String getMainbusiness (){
		return mainbusiness;
	}
	public void setMainbusiness (String mainbusiness){
		this.mainbusiness = mainbusiness;
	}

	/**
	*  排名
	*/
	public String getRanking (){
		return ranking;
	}
	public void setRanking (String ranking){
		this.ranking = ranking;
	}

	/**
	*  曾获国家、省级科技奖励情况
	*/
	public String getEveraward (){
		return everaward;
	}
	public void setEveraward (String everaward){
		this.everaward = everaward;
	}

	/**
	*  合作方向
	*/
	public String getDirection (){
		return direction;
	}
	public void setDirection (String direction){
		this.direction = direction;
	}

	/**
	*  本单位与候选组织的合作情况
	*/
	public String getSituation (){
		return situation;
	}
	public void setSituation (String situation){
		this.situation = situation;
	}

	/**
	*  工作单位意见
	*/
	public String getOpinion (){
		return opinion;
	}
	public void setOpinion (String opinion){
		this.opinion = opinion;
	}

	public String getSuggestLevel() {
		return suggestLevel;
	}

	public void setSuggestLevel(String suggestLevel) {
		this.suggestLevel = suggestLevel;
	}

	public String getCopCompany() {
		return copCompany;
	}

	public void setCopCompany(String copCompany) {
		this.copCompany = copCompany;
	}

	public Date getCopStartDate() {
		return copStartDate;
	}

	public void setCopStartDate(Date copStartDate) {
		this.copStartDate = copStartDate;
	}

}
