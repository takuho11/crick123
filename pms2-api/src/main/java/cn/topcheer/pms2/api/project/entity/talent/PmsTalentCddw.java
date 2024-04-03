/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 9:30:14
 */
package cn.topcheer.pms2.api.project.entity.talent;

import javax.persistence.*;
import java.math.BigDecimal;
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
 *  科技人才-单位表
 */
@Entity
@Table(name="PMS_TALENT_CDDW")
@ClassInfo(name = "科技人才-单位表", module = SysModuleEnum.RCTD, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsTalentCddw {

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
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
     *  统一社会信用代码
     */
	@ApiModelProperty("统一社会信用代码")
	@TableField("CREDITCODE")
	@Column(columnDefinition = "CREDITCODE")
	private String creditcode;

	/**
     *  成立时间
     */
	@ApiModelProperty("成立时间")
	@TableField("ESTABLISHDATE")
	@Column(columnDefinition = "ESTABLISHDATE")
	@FieldDes(name = "establishdate", type = "Date", memo = "成立时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	private Date establishdate;

	/**
     *  注册资本
     */
	@ApiModelProperty("注册资本")
	@TableField("REGISTERCAPTIAL")
	@Column(columnDefinition = "REGISTERCAPTIAL")
	@FieldDes(name = "registercaptial", type = "BigDecimal", memo = "注册资本")
	private BigDecimal registercaptial;

	/**
     *  注册地址
     */
	@ApiModelProperty("注册地址")
	@TableField("REGISTERADDRESS")
	@Column(columnDefinition = "REGISTERADDRESS")
	private String registeraddress;

	/**
     *  注册地-省
     */
	@ApiModelProperty("注册地-省")
	@TableField("PROVINCE")
	@Column(columnDefinition = "PROVINCE")
	private String province;

	/**
     *  注册地-市
     */
	@ApiModelProperty("注册地-市")
	@TableField("CITY")
	@Column(columnDefinition = "CITY")
	private String city;

	/**
     *  注册地-县
     */
	@ApiModelProperty("注册地-县")
	@TableField("COUNTY")
	@Column(columnDefinition = "COUNTY")
	private String county;

	/**
     *  注册地-街道
     */
	@ApiModelProperty("注册地-街道")
	@TableField("STREET")
	@Column(columnDefinition = "STREET")
	private String street;

	/**
     *  主营业务
     */
	@ApiModelProperty("主营业务")
	@TableField("MAINBUSINESS")
	@Column(columnDefinition = "MAINBUSINESS")
	private String mainbusiness;

	/**
     *  单位类型
     */
	@ApiModelProperty("单位类型")
	@TableField("UNITTYPE")
	@Column(columnDefinition = "UNITTYPE")
	private String unittype;

	/**
     *  法人姓名
     */
	@ApiModelProperty("法人姓名")
	@TableField("LEGAL_NAME")
	@Column(columnDefinition = "LEGAL_NAME")
	private String legalName;

	/**
     *  法人手机
     */
	@ApiModelProperty("法人手机")
	@TableField("LEGAL_MOBILE")
	@Column(columnDefinition = "LEGAL_MOBILE")
	private String legalMobile;

	/**
     *  法人座机
     */
	@ApiModelProperty("法人座机")
	@TableField("LEGAL_TELEPHONE")
	@Column(columnDefinition = "LEGAL_TELEPHONE")
	private String legalTelephone;

	/**
     *  法人邮箱
     */
	@ApiModelProperty("法人邮箱")
	@TableField("LEGAL_EMAIL")
	@Column(columnDefinition = "LEGAL_EMAIL")
	private String legalEmail;

	/**
     *  法人传真
     */
	@ApiModelProperty("法人传真")
	@TableField("LEGAL_FAX")
	@Column(columnDefinition = "LEGAL_FAX")
	private String legalFax;

	/**
     *  联系人姓名
     */
	@ApiModelProperty("联系人姓名")
	@TableField("LXR_NAME")
	@Column(columnDefinition = "LXR_NAME")
	private String lxrName;

	/**
     *  联系人手机
     */
	@ApiModelProperty("联系人手机")
	@TableField("LXR_MOBILE")
	@Column(columnDefinition = "LXR_MOBILE")
	private String lxrMobile;

	/**
     *  联系人座机
     */
	@ApiModelProperty("联系人座机")
	@TableField("LXR_TELEPHONE")
	@Column(columnDefinition = "LXR_TELEPHONE")
	private String lxrTelephone;

	/**
     *  联系人邮箱
     */
	@ApiModelProperty("联系人邮箱")
	@TableField("LXR_EMAIL")
	@Column(columnDefinition = "LXR_EMAIL")
	private String lxrEmail;

	/**
     *  联系人传真
     */
	@ApiModelProperty("联系人传真")
	@TableField("LXR_FAX")
	@Column(columnDefinition = "LXR_FAX")
	private String lxrFax;

	/**
     *  高企
     */
	@ApiModelProperty("高企")
	@TableField("ISGXQY")
	@Column(columnDefinition = "ISGXQY")
	private String isgxqy;

	/**
     *  高企编号
     */
	@ApiModelProperty("高企编号")
	@TableField("GXQYNO")
	@Column(columnDefinition = "GXQYNO")
	private String gxqyno;

	/**
     *  科技中小企业
     */
	@ApiModelProperty("科技中小企业")
	@TableField("ISKJXZXQY")
	@Column(columnDefinition = "ISKJXZXQY")
	private String iskjxzxqy;

	/**
     *  科技中小企业编号
     */
	@ApiModelProperty("科技中小企业编号")
	@TableField("KJXZXQYNO")
	@Column(columnDefinition = "KJXZXQYNO")
	private String kjxzxqyno;

	/**
     *  开户银行
     */
	@ApiModelProperty("开户银行")
	@TableField("BANKNAME")
	@Column(columnDefinition = "BANKNAME")
	private String bankname;

	/**
     *  银行账号
     */
	@ApiModelProperty("银行账号")
	@TableField("BANKACCOUNT")
	@Column(columnDefinition = "BANKACCOUNT")
	private String bankaccount;

	/**
     *  开户户名
     */
	@ApiModelProperty("开户户名")
	@TableField("ACCOUNTNAME")
	@Column(columnDefinition = "ACCOUNTNAME")
	private String accountname;

	/**
     *  开户行行号
     */
	@ApiModelProperty("开户行行号")
	@TableField("ACCOUNTNO")
	@Column(columnDefinition = "ACCOUNTNO")
	private String accountno;

	/**
     *  开户行地址
     */
	@ApiModelProperty("开户行地址")
	@TableField("BANKADDRESS")
	@Column(columnDefinition = "BANKADDRESS")
	private String bankaddress;

	/**
     *  开户行省
     */
	@ApiModelProperty("开户行省")
	@TableField("BANKPROVINCE")
	@Column(columnDefinition = "BANKPROVINCE")
	private String bankprovince;

	/**
     *  开户行市
     */
	@ApiModelProperty("开户行市")
	@TableField("BANKCITY")
	@Column(columnDefinition = "BANKCITY")
	private String bankcity;

	/**
     *  开户行县
     */
	@ApiModelProperty("开户行县")
	@TableField("BANKCOUNTY")
	@Column(columnDefinition = "BANKCOUNTY")
	private String bankcounty;

	/**
     *  主管部门
     */
	@ApiModelProperty("主管部门")
	@TableField("MAJOR_DEPT")
	@Column(columnDefinition = "MAJOR_DEPT")
	private String majorDept;

	/**
     *  所在地区
     */
	@ApiModelProperty("所在地区")
	@TableField("REGION")
	@Column(columnDefinition = "REGION")
	private String region;

	/**
     *  办公地址
     */
	@ApiModelProperty("办公地址")
	@TableField("OFFICEADDRESS")
	@Column(columnDefinition = "OFFICEADDRESS")
	private String officeaddress;

	/**
     *  办公地-省
     */
	@ApiModelProperty("办公地-省")
	@TableField("OFFICEPROVINCE")
	@Column(columnDefinition = "OFFICEPROVINCE")
	private String officeprovince;

	/**
     *  办公地-市
     */
	@ApiModelProperty("办公地-市")
	@TableField("OFFICECITY")
	@Column(columnDefinition = "OFFICECITY")
	private String officecity;

	/**
     *  办公地-县
     */
	@ApiModelProperty("办公地-县")
	@TableField("OFFICECOUNTY")
	@Column(columnDefinition = "OFFICECOUNTY")
	private String officecounty;

	/**
     *  办公地-街道
     */
	@ApiModelProperty("办公地-街道")
	@TableField("OFFICESTREET")
	@Column(columnDefinition = "OFFICESTREET")
	private String officestreet;

	/**
     *  邮编
     */
	@ApiModelProperty("邮编")
	@TableField("POSTAL_CODE")
	@Column(columnDefinition = "POSTAL_CODE")
	private String postalCode;

	/**
     *  职工总人数
     */
	@ApiModelProperty("职工总人数")
	@TableField("EMPLOYEE_TOTAL")
	@Column(columnDefinition = "EMPLOYEE_TOTAL")
	private String employeeTotal;

	/**
     *  从事研发人员占职工总人数的比例
     */
	@ApiModelProperty("从事研发人员占职工总人数的比例")
	@TableField("RD_RATIO")
	@Column(columnDefinition = "RD_RATIO")
	private String rdRatio;

	/**
     *  其他单位类别
     */
	@ApiModelProperty("其他单位类别")
	@TableField("OTHER_UNITTYPE")
	@Column(columnDefinition = "OTHER_UNITTYPE")
	private String otherUnittype;

	/**
     *  企业规模
     */
	@ApiModelProperty("企业规模")
	@TableField("ENTERPRISE_SCALE")
	@Column(columnDefinition = "ENTERPRISE_SCALE")
	private String enterpriseScale;


	@ApiModelProperty("单位简称")
	@TableField("UNIT_ABBREVIATION")
	@Column(columnDefinition = "UNIT_ABBREVIATION")
	private String unitAbbreviation;


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
	*  统一社会信用代码
	*/
	public String getCreditcode (){
		return creditcode;
	}
	public void setCreditcode (String creditcode){
		this.creditcode = creditcode;
	}

	/**
	*  成立时间
	*/
	public Date getEstablishdate (){
		return establishdate;
	}
	public void setEstablishdate (Date establishdate){
		this.establishdate = establishdate;
	}

	/**
	*  注册资本
	*/
	public BigDecimal getRegistercaptial (){
		return registercaptial;
	}
	public void setRegistercaptial (BigDecimal registercaptial){
		this.registercaptial = registercaptial;
	}

	/**
	*  注册地址
	*/
	public String getRegisteraddress (){
		return registeraddress;
	}
	public void setRegisteraddress (String registeraddress){
		this.registeraddress = registeraddress;
	}

	/**
	*  注册地-省
	*/
	public String getProvince (){
		return province;
	}
	public void setProvince (String province){
		this.province = province;
	}

	/**
	*  注册地-市
	*/
	public String getCity (){
		return city;
	}
	public void setCity (String city){
		this.city = city;
	}

	/**
	*  注册地-县
	*/
	public String getCounty (){
		return county;
	}
	public void setCounty (String county){
		this.county = county;
	}

	/**
	*  注册地-街道
	*/
	public String getStreet (){
		return street;
	}
	public void setStreet (String street){
		this.street = street;
	}

	/**
	*  主营业务
	*/
	public String getMainbusiness (){
		return mainbusiness;
	}
	public void setMainbusiness (String mainbusiness){
		this.mainbusiness = mainbusiness;
	}

	/**
	*  单位类型
	*/
	public String getUnittype (){
		return unittype;
	}
	public void setUnittype (String unittype){
		this.unittype = unittype;
	}

	/**
	*  法人姓名
	*/
	public String getLegalName (){
		return legalName;
	}
	public void setLegalName (String legalName){
		this.legalName = legalName;
	}

	/**
	*  法人手机
	*/
	public String getLegalMobile (){
		return legalMobile;
	}
	public void setLegalMobile (String legalMobile){
		this.legalMobile = legalMobile;
	}

	/**
	*  法人座机
	*/
	public String getLegalTelephone (){
		return legalTelephone;
	}
	public void setLegalTelephone (String legalTelephone){
		this.legalTelephone = legalTelephone;
	}

	/**
	*  法人邮箱
	*/
	public String getLegalEmail (){
		return legalEmail;
	}
	public void setLegalEmail (String legalEmail){
		this.legalEmail = legalEmail;
	}

	/**
	*  法人传真
	*/
	public String getLegalFax (){
		return legalFax;
	}
	public void setLegalFax (String legalFax){
		this.legalFax = legalFax;
	}

	/**
	*  联系人姓名
	*/
	public String getLxrName (){
		return lxrName;
	}
	public void setLxrName (String lxrName){
		this.lxrName = lxrName;
	}

	/**
	*  联系人手机
	*/
	public String getLxrMobile (){
		return lxrMobile;
	}
	public void setLxrMobile (String lxrMobile){
		this.lxrMobile = lxrMobile;
	}

	/**
	*  联系人座机
	*/
	public String getLxrTelephone (){
		return lxrTelephone;
	}
	public void setLxrTelephone (String lxrTelephone){
		this.lxrTelephone = lxrTelephone;
	}

	/**
	*  联系人邮箱
	*/
	public String getLxrEmail (){
		return lxrEmail;
	}
	public void setLxrEmail (String lxrEmail){
		this.lxrEmail = lxrEmail;
	}

	/**
	*  联系人传真
	*/
	public String getLxrFax (){
		return lxrFax;
	}
	public void setLxrFax (String lxrFax){
		this.lxrFax = lxrFax;
	}

	/**
	*  高企
	*/
	public String getIsgxqy (){
		return isgxqy;
	}
	public void setIsgxqy (String isgxqy){
		this.isgxqy = isgxqy;
	}

	/**
	*  高企编号
	*/
	public String getGxqyno (){
		return gxqyno;
	}
	public void setGxqyno (String gxqyno){
		this.gxqyno = gxqyno;
	}

	/**
	*  科技中小企业
	*/
	public String getIskjxzxqy (){
		return iskjxzxqy;
	}
	public void setIskjxzxqy (String iskjxzxqy){
		this.iskjxzxqy = iskjxzxqy;
	}

	/**
	*  科技中小企业编号
	*/
	public String getKjxzxqyno (){
		return kjxzxqyno;
	}
	public void setKjxzxqyno (String kjxzxqyno){
		this.kjxzxqyno = kjxzxqyno;
	}

	/**
	*  开户银行
	*/
	public String getBankname (){
		return bankname;
	}
	public void setBankname (String bankname){
		this.bankname = bankname;
	}

	/**
	*  银行账号
	*/
	public String getBankaccount (){
		return bankaccount;
	}
	public void setBankaccount (String bankaccount){
		this.bankaccount = bankaccount;
	}

	/**
	*  开户户名
	*/
	public String getAccountname (){
		return accountname;
	}
	public void setAccountname (String accountname){
		this.accountname = accountname;
	}

	/**
	*  开户行行号
	*/
	public String getAccountno (){
		return accountno;
	}
	public void setAccountno (String accountno){
		this.accountno = accountno;
	}

	/**
	*  开户行地址
	*/
	public String getBankaddress (){
		return bankaddress;
	}
	public void setBankaddress (String bankaddress){
		this.bankaddress = bankaddress;
	}

	/**
	*  开户行省
	*/
	public String getBankprovince (){
		return bankprovince;
	}
	public void setBankprovince (String bankprovince){
		this.bankprovince = bankprovince;
	}

	/**
	*  开户行市
	*/
	public String getBankcity (){
		return bankcity;
	}
	public void setBankcity (String bankcity){
		this.bankcity = bankcity;
	}

	/**
	*  开户行县
	*/
	public String getBankcounty (){
		return bankcounty;
	}
	public void setBankcounty (String bankcounty){
		this.bankcounty = bankcounty;
	}

	/**
	*  主管部门
	*/
	public String getMajorDept (){
		return majorDept;
	}
	public void setMajorDept (String majorDept){
		this.majorDept = majorDept;
	}

	/**
	*  所在地区
	*/
	public String getRegion (){
		return region;
	}
	public void setRegion (String region){
		this.region = region;
	}

	/**
	*  办公地址
	*/
	public String getOfficeaddress (){
		return officeaddress;
	}
	public void setOfficeaddress (String officeaddress){
		this.officeaddress = officeaddress;
	}

	/**
	*  办公地-省
	*/
	public String getOfficeprovince (){
		return officeprovince;
	}
	public void setOfficeprovince (String officeprovince){
		this.officeprovince = officeprovince;
	}

	/**
	*  办公地-市
	*/
	public String getOfficecity (){
		return officecity;
	}
	public void setOfficecity (String officecity){
		this.officecity = officecity;
	}

	/**
	*  办公地-县
	*/
	public String getOfficecounty (){
		return officecounty;
	}
	public void setOfficecounty (String officecounty){
		this.officecounty = officecounty;
	}

	/**
	*  办公地-街道
	*/
	public String getOfficestreet (){
		return officestreet;
	}
	public void setOfficestreet (String officestreet){
		this.officestreet = officestreet;
	}

	/**
	*  邮编
	*/
	public String getPostalCode (){
		return postalCode;
	}
	public void setPostalCode (String postalCode){
		this.postalCode = postalCode;
	}

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

	public String getEmployeeTotal() {
		return employeeTotal;
	}

	public void setEmployeeTotal(String employeeTotal) {
		this.employeeTotal = employeeTotal;
	}

	public String getRdRatio() {
		return rdRatio;
	}

	public void setRdRatio(String rdRatio) {
		this.rdRatio = rdRatio;
	}

	public String getOtherUnittype() {
		return otherUnittype;
	}

	public void setOtherUnittype(String otherUnittype) {
		this.otherUnittype = otherUnittype;
	}

	public String getEnterpriseScale() {
		return enterpriseScale;
	}

	public void setEnterpriseScale(String enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}

	public String getUnitAbbreviation() {
		return unitAbbreviation;
	}

	public void setUnitAbbreviation(String unitAbbreviation) {
		this.unitAbbreviation = unitAbbreviation;
	}
}
