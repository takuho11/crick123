/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 15:56:30
 */
package cn.topcheer.pms2.api.bi;

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
 * 数据仓-单位信息表
 */
@Entity
@Table(name = "BI_ENT_BI")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiEntBi {

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "")
    private String id;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISRJQY")
    @Column(columnDefinition = "ISRJQY")
    @FieldDes(name = "isrjqy", lenth = "255", type = "String", memo = "")
    private String isrjqy;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ENTERPRISESCALE")
    @Column(columnDefinition = "ENTERPRISESCALE")
    @FieldDes(name = "enterprisescale", lenth = "255", type = "String", memo = "")
    private String enterprisescale;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ENTERPRISENATURE")
    @Column(columnDefinition = "ENTERPRISENATURE")
    @FieldDes(name = "enterprisenature", lenth = "255", type = "String", memo = "")
    private String enterprisenature;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TECHNICALFIELD")
    @Column(columnDefinition = "TECHNICALFIELD")
    @FieldDes(name = "technicalfield", lenth = "255", type = "String", memo = "")
    private String technicalfield;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MAINPRODUCT")
    @Column(columnDefinition = "MAINPRODUCT")
    private String mainproduct;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CASEMANAGEMENT")
    @Column(columnDefinition = "CASEMANAGEMENT")
    @FieldDes(name = "casemanagement", lenth = "255", type = "String", memo = "")
    private String casemanagement;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("HIGHTECHFIELDCATALOGUE")
    @Column(columnDefinition = "HIGHTECHFIELDCATALOGUE")
    @FieldDes(name = "hightechfieldcatalogue", lenth = "255", type = "String", memo = "")
    private String hightechfieldcatalogue;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("COMMERCIALAUTHORITIECODE")
    @Column(columnDefinition = "COMMERCIALAUTHORITIECODE")
    @FieldDes(name = "commercialauthoritiecode", lenth = "255", type = "String", memo = "")
    private String commercialauthoritiecode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("RESEARCHTYPE")
    @Column(columnDefinition = "RESEARCHTYPE")
    @FieldDes(name = "researchtype", lenth = "255", type = "String", memo = "")
    private String researchtype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ENTERPRISEBQ")
    @Column(columnDefinition = "ENTERPRISEBQ")
    @FieldDes(name = "enterprisebq", lenth = "4000", type = "String", memo = "")
    private String enterprisebq;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISGSQY")
    @Column(columnDefinition = "ISGSQY")
    @FieldDes(name = "isgsqy", lenth = "255", type = "String", memo = "")
    private String isgsqy;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISMYQY")
    @Column(columnDefinition = "ISMYQY")
    @FieldDes(name = "ismyqy", lenth = "255", type = "String", memo = "")
    private String ismyqy;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISGXQY")
    @Column(columnDefinition = "ISGXQY")
    @FieldDes(name = "isgxqy", lenth = "255", type = "String", memo = "")
    private String isgxqy;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("GXQYNO")
    @Column(columnDefinition = "GXQYNO")
    @FieldDes(name = "gxqyno", lenth = "255", type = "String", memo = "")
    private String gxqyno;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISGXQNQY")
    @Column(columnDefinition = "ISGXQNQY")
    @FieldDes(name = "isgxqnqy", lenth = "255", type = "String", memo = "")
    private String isgxqnqy;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("BELONGGXQ")
    @Column(columnDefinition = "BELONGGXQ")
    @FieldDes(name = "belonggxq", lenth = "255", type = "String", memo = "")
    private String belonggxq;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISLISTEDQY")
    @Column(columnDefinition = "ISLISTEDQY")
    @FieldDes(name = "islistedqy", lenth = "255", type = "String", memo = "")
    private String islistedqy;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("LISTEDSECTOR")
    @Column(columnDefinition = "LISTEDSECTOR")
    @FieldDes(name = "listedsector", lenth = "255", type = "String", memo = "")
    private String listedsector;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISGPQY")
    @Column(columnDefinition = "ISGPQY")
    @FieldDes(name = "isgpqy", lenth = "255", type = "String", memo = "")
    private String isgpqy;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("GPTYPE")
    @Column(columnDefinition = "GPTYPE")
    @FieldDes(name = "gptype", lenth = "255", type = "String", memo = "")
    private String gptype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("HIGHTECHNICALFIELD")
    @Column(columnDefinition = "HIGHTECHNICALFIELD")
    @FieldDes(name = "hightechnicalfield", lenth = "255", type = "String", memo = "")
    private String hightechnicalfield;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISKJXZXQY")
    @Column(columnDefinition = "ISKJXZXQY")
    @FieldDes(name = "iskjxzxqy", lenth = "255", type = "String", memo = "")
    private String iskjxzxqy;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("INNOVATIONTYPE")
    @Column(columnDefinition = "INNOVATIONTYPE")
    @FieldDes(name = "innovationtype", lenth = "255", type = "String", memo = "")
    private String innovationtype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("RDDIRECTION")
    @Column(columnDefinition = "RDDIRECTION")
    @FieldDes(name = "rddirection", lenth = "2000", type = "String", memo = "")
    private String rddirection;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISFHQQY")
    @Column(columnDefinition = "ISFHQQY")
    @FieldDes(name = "isfhqqy", lenth = "255", type = "String", memo = "")
    private String isfhqqy;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("BELONGFHQ")
    @Column(columnDefinition = "BELONGFHQ")
    @FieldDes(name = "belongfhq", lenth = "255", type = "String", memo = "")
    private String belongfhq;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISADDVCUNIT")
    @Column(columnDefinition = "ISADDVCUNIT")
    @FieldDes(name = "isaddvcunit", lenth = "255", type = "String", memo = "")
    private String isaddvcunit;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("VCUNITNAME")
    @Column(columnDefinition = "VCUNITNAME")
    @FieldDes(name = "vcunitname", lenth = "255", type = "String", memo = "")
    private String vcunitname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("VCFUND")
    @Column(columnDefinition = "VCFUND")
    private String vcfund;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ACTUALCAPITAL")
    @Column(columnDefinition = "ACTUALCAPITAL")
    private String actualcapital;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ACTUALCAPITAL_CURRENCY")
    @Column(columnDefinition = "ACTUALCAPITAL_CURRENCY")
    private String actualcapitalCurrency;

    /**
     *  
     */
    @ApiModelProperty("是否独立法人")
    @TableField("ISINDEPENDENTLEGALMAN")
    @Column(columnDefinition = "ISINDEPENDENTLEGALMAN")
    @FieldDes(name = "isindependentlegalman", lenth = "255", type = "String", memo = "是否独立法人")
    private String isindependentlegalman;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("OFFICEADDRESS")
    @Column(columnDefinition = "OFFICEADDRESS")
    @FieldDes(name = "officeaddress", lenth = "1000", type = "String", memo = "")
    private String officeaddress;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CASEMANAGEMENTID")
    @Column(columnDefinition = "CASEMANAGEMENTID")
    @FieldDes(name = "casemanagementid", lenth = "255", type = "String", memo = "")
    private String casemanagementid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("BELONGLEVEL")
    @Column(columnDefinition = "BELONGLEVEL")
    @FieldDes(name = "belonglevel", lenth = "255", type = "String", memo = "")
    private String belonglevel;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EMERGINGINDUSTRY")
    @Column(columnDefinition = "EMERGINGINDUSTRY")
    @FieldDes(name = "emergingindustry", lenth = "255", type = "String", memo = "")
    private String emergingindustry;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TAXAUTHORITY")
    @Column(columnDefinition = "TAXAUTHORITY")
    @FieldDes(name = "taxauthority", lenth = "255", type = "String", memo = "")
    private String taxauthority;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TAXPAYERNO")
    @Column(columnDefinition = "TAXPAYERNO")
    @FieldDes(name = "taxpayerno", lenth = "255", type = "String", memo = "")
    private String taxpayerno;

    /**
     *  
     */
    @ApiModelProperty("手机")
    @TableField("TELEPHONE")
    @Column(columnDefinition = "TELEPHONE")
    @FieldDes(name = "telephone", lenth = "255", type = "String", memo = "手机")
    private String telephone;

    /**
     *  
     */
    @ApiModelProperty("传真")
    @TableField("FAX")
    @Column(columnDefinition = "FAX")
    @FieldDes(name = "fax", lenth = "255", type = "String", memo = "传真")
    private String fax;

    /**
     *  
     */
    @ApiModelProperty("邮箱")
    @TableField("EMAIL")
    @Column(columnDefinition = "EMAIL")
    @FieldDes(name = "email", lenth = "255", type = "String", memo = "邮箱")
    private String email;

    /**
     *  
     */
    @ApiModelProperty("单位名称")
    @TableField("UNITNAME")
    @Column(columnDefinition = "UNITNAME")
    @FieldDes(name = "unitname", lenth = "255", type = "String", memo = "单位名称")
    private String unitname;

    /**
     *  
     */
    @ApiModelProperty("统一社会信用编码")
    @TableField("CREDITCODE")
    @Column(columnDefinition = "CREDITCODE")
    @FieldDes(name = "creditcode", lenth = "255", type = "String", memo = "统一社会信用编码")
    private String creditcode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ESTABLISHDATE")
    @Column(columnDefinition = "ESTABLISHDATE")
    @FieldDes(name = "establishdate", type = "Date", memo = "成立时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date establishdate;

    /**
     *  
     */
    @ApiModelProperty("注册资金")
    @TableField("REGISTERCAPITAL")
    @Column(columnDefinition = "REGISTERCAPITAL")
    @FieldDes(name = "registercapital", lenth = "1000", type = "String", memo = "注册资金")
    private String registercapital;

    /**
     *  
     */
    @ApiModelProperty("注册地")
    @TableField("REGISTERADDRESS")
    @Column(columnDefinition = "REGISTERADDRESS")
    @FieldDes(name = "registeraddress", lenth = "1000", type = "String", memo = "注册地")
    private String registeraddress;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("REGISTERDEPT")
    @Column(columnDefinition = "REGISTERDEPT")
    @FieldDes(name = "registerdept", lenth = "255", type = "String", memo = "")
    private String registerdept;

    /**
     *  
     */
    @ApiModelProperty("省")
    @TableField("PROVINCE")
    @Column(columnDefinition = "PROVINCE")
    @FieldDes(name = "province", lenth = "255", type = "String", memo = "省")
    private String province;

    /**
     *  
     */
    @ApiModelProperty("市")
    @TableField("CITY")
    @Column(columnDefinition = "CITY")
    @FieldDes(name = "city", lenth = "255", type = "String", memo = "市")
    private String city;

    /**
     *  
     */
    @ApiModelProperty("县区")
    @TableField("COUNTY")
    @Column(columnDefinition = "COUNTY")
    @FieldDes(name = "county", lenth = "255", type = "String", memo = "县区")
    private String county;

    /**
     *  
     */
    @ApiModelProperty("所在地编码")
    @TableField("LOCATIONCODE")
    @Column(columnDefinition = "LOCATIONCODE")
    @FieldDes(name = "locationcode", lenth = "255", type = "String", memo = "所在地编码")
    private String locationcode;

    /**
     *  
     */
    @ApiModelProperty("主营业务")
    @TableField("MAINBUSINESS")
    @Column(columnDefinition = "MAINBUSINESS")
    @FieldDes(name = "mainbusiness", lenth = "2000", type = "String", memo = "主营业务")
    private String mainbusiness;

    /**
     *  
     */
    @ApiModelProperty("单位类型")
    @TableField("UNITTYPE")
    @Column(columnDefinition = "UNITTYPE")
    @FieldDes(name = "unittype", lenth = "255", type = "String", memo = "单位类型")
    private String unittype;

    /**
     *  
     */
    @ApiModelProperty("所属行业")
    @TableField("BELONGINDUSTRY")
    @Column(columnDefinition = "BELONGINDUSTRY")
    @FieldDes(name = "belongindustry", lenth = "255", type = "String", memo = "所属行业")
    private String belongindustry;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ENTERPRISETYPE")
    @Column(columnDefinition = "ENTERPRISETYPE")
    @FieldDes(name = "enterprisetype", lenth = "255", type = "String", memo = "")
    private String enterprisetype;

    /**
     *  
     */
    @ApiModelProperty("邮编")
    @TableField("POSTALCODE")
    @Column(columnDefinition = "POSTALCODE")
    @FieldDes(name = "postalcode", lenth = "255", type = "String", memo = "邮编")
    private String postalcode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("UNITNAME_ABBREVIATION")
    @Column(columnDefinition = "UNITNAME_ABBREVIATION")
    @FieldDes(name = "unitname_abbreviation", lenth = "255", type = "String", memo = "")
    private String unitnameAbbreviation;

    /**
     *  
     */
    @ApiModelProperty("开户银行")
    @TableField("BANKNAME")
    @Column(columnDefinition = "BANKNAME")
    @FieldDes(name = "bankname", lenth = "255", type = "String", memo = "开户银行")
    private String bankname;

    /**
     *  
     */
    @ApiModelProperty("银行账号")
    @TableField("BANKACCOUNT")
    @Column(columnDefinition = "BANKACCOUNT")
    @FieldDes(name = "bankaccount", lenth = "255", type = "String", memo = "银行账号")
    private String bankaccount;

    /**
     *  
     */
    @ApiModelProperty("开户名")
    @TableField("ACCOUNTNAME")
    @Column(columnDefinition = "ACCOUNTNAME")
    @FieldDes(name = "accountname", lenth = "255", type = "String", memo = "开户名")
    private String accountname;

    /**
     *  
     */
    @ApiModelProperty("网址")
    @TableField("WIBSITE")
    @Column(columnDefinition = "WIBSITE")
    @FieldDes(name = "wibsite", lenth = "255", type = "String", memo = "网址")
    private String wibsite;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "")
    private String type;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MAINID")
    @Column(columnDefinition = "MAINID")
    @FieldDes(name = "mainid", lenth = "255", type = "String", memo = "")
    private String mainid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    @FieldDes(name = "sourceid", lenth = "255", type = "String", memo = "")
    private String sourceid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "")
    private Integer seq;

    /**
     *  
     */
    @ApiModelProperty("币种")
    @TableField("CURRENCY")
    @Column(columnDefinition = "CURRENCY")
    @FieldDes(name = "currency", lenth = "255", type = "String", memo = "币种")
    private String currency;

    /**
     *  
     */
    @ApiModelProperty("街道")
    @TableField("STREET")
    @Column(columnDefinition = "STREET")
    @FieldDes(name = "street", lenth = "255", type = "String", memo = "街道")
    private String street;

    /**
     *  
     */
    @ApiModelProperty("办公地址-省")
    @TableField("OFFICEPROVINCE")
    @Column(columnDefinition = "OFFICEPROVINCE")
    @FieldDes(name = "officeprovince", lenth = "255", type = "String", memo = "办公地址-省")
    private String officeprovince;

    /**
     *  
     */
    @ApiModelProperty("办公地址-市")
    @TableField("OFFICECITY")
    @Column(columnDefinition = "OFFICECITY")
    @FieldDes(name = "officecity", lenth = "255", type = "String", memo = "办公地址-市")
    private String officecity;

    /**
     *  
     */
    @ApiModelProperty("办公地址-县区")
    @TableField("OFFICECOUNTY")
    @Column(columnDefinition = "OFFICECOUNTY")
    @FieldDes(name = "officecounty", lenth = "255", type = "String", memo = "办公地址-县区")
    private String officecounty;

    /**
     *  
     */
    @ApiModelProperty("办公地址-街道")
    @TableField("OFFICESTREET")
    @Column(columnDefinition = "OFFICESTREET")
    @FieldDes(name = "officestreet", lenth = "255", type = "String", memo = "办公地址-街道")
    private String officestreet;

    /**
     *  
     */
    @ApiModelProperty("是否港澳台或国外企业")
    @TableField("ISOTHERUNITTYPE")
    @Column(columnDefinition = "ISOTHERUNITTYPE")
    @FieldDes(name = "isotherunittype", lenth = "255", type = "String", memo = "是否港澳台或国外企业")
    private String isotherunittype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("OFFICEGETMETHOD")
    @Column(columnDefinition = "OFFICEGETMETHOD")
    @FieldDes(name = "officegetmethod", lenth = "255", type = "String", memo = "")
    private String officegetmethod;

    /**
     *  
     */
    @ApiModelProperty("科技型中小企业编号")
    @TableField("KJXZXQYNO")
    @Column(columnDefinition = "KJXZXQYNO")
    @FieldDes(name = "kjxzxqyno", lenth = "255", type = "String", memo = "科技型中小企业编号")
    private String kjxzxqyno;

    /**
     *  
     */
    @ApiModelProperty("股票代码")
    @TableField("STOCKCODE")
    @Column(columnDefinition = "STOCKCODE")
    @FieldDes(name = "stockcode", lenth = "255", type = "String", memo = "股票代码")
    private String stockcode;

    /**
     *  
     */
    @ApiModelProperty("高企开始时间")
    @TableField("GXQYSTARTDATE")
    @Column(columnDefinition = "GXQYSTARTDATE")
    @FieldDes(name = "gxqystartdate", lenth = "255", type = "String", memo = "高企开始时间")
    private String gxqystartdate;

    /**
     *  
     */
    @ApiModelProperty("高企结束时间")
    @TableField("GXQYENDDATE")
    @Column(columnDefinition = "GXQYENDDATE")
    @FieldDes(name = "gxqyenddate", lenth = "255", type = "String", memo = "高企结束时间")
    private String gxqyenddate;

    /**
     *  
     */
    @ApiModelProperty("上市时间")
    @TableField("GXQYLISTEDDATE")
    @Column(columnDefinition = "GXQYLISTEDDATE")
    @FieldDes(name = "gxqylisteddate", lenth = "255", type = "String", memo = "上市时间")
    private String gxqylisteddate;

    /**
     *  
     */
    @ApiModelProperty("所属国别")
    @TableField("BELONGNATION")
    @Column(columnDefinition = "BELONGNATION")
    @FieldDes(name = "belongnation", lenth = "255", type = "String", memo = "所属国别")
    private String belongnation;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SIMPLEUNITTYPE")
    @Column(columnDefinition = "SIMPLEUNITTYPE")
    @FieldDes(name = "simpleunittype", lenth = "255", type = "String", memo = "")
    private String simpleunittype;

    /**
     *  
     */
    @ApiModelProperty("二级归口")
    @TableField("COUNTRYCASEMANAGEMENT")
    @Column(columnDefinition = "COUNTRYCASEMANAGEMENT")
    @FieldDes(name = "countrycasemanagement", lenth = "255", type = "String", memo = "二级归口")
    private String countrycasemanagement;

    /**
     *  
     */
    @ApiModelProperty("二级归口id")
    @TableField("COUNTRYCASEMANAGEMENTID")
    @Column(columnDefinition = "COUNTRYCASEMANAGEMENTID")
    @FieldDes(name = "countrycasemanagementid", lenth = "255", type = "String", memo = "二级归口id")
    private String countrycasemanagementid;

    /**
     *  
     */
    @ApiModelProperty("一级归口")
    @TableField("CITYCASEMANAGEMENT")
    @Column(columnDefinition = "CITYCASEMANAGEMENT")
    @FieldDes(name = "citycasemanagement", lenth = "255", type = "String", memo = "一级归口")
    private String citycasemanagement;

    /**
     *  
     */
    @ApiModelProperty("一级归口id")
    @TableField("CITYCASEMANAGEMENTID")
    @Column(columnDefinition = "CITYCASEMANAGEMENTID")
    @FieldDes(name = "citycasemanagementid", lenth = "255", type = "String", memo = "一级归口id")
    private String citycasemanagementid;

    @ApiModelProperty("归口部门类型")
    @TableField("CASEMANAGEMENTTYPE")
    @Column(columnDefinition = "CASEMANAGEMENTTYPE")
    @FieldDes(name = "casemanagementtype", lenth = "255", type = "String", memo = "归口部门类型")
    private String casemanagementtype;

    /**
     *  保存时间
     */
    @ApiModelProperty("保存时间")
    @TableField("SAVEDATE")
    @Column(columnDefinition = "SAVEDATE")
    @FieldDes(name = "savedate", type = "Date", memo = "保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;

    /**
     *  最后一次更新时间
     */
    @ApiModelProperty("最后一次更新时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "最后一次更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SIMPLEUNITTYPEID")
    @Column(columnDefinition = "SIMPLEUNITTYPEID")
    @FieldDes(name = "simpleunittypeid", lenth = "255", type = "String", memo = "")
    private String simpleunittypeid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("GSRESULT")
    @Column(columnDefinition = "GSRESULT")
    @FieldDes(name = "gsresult", lenth = "500", type = "String", memo = "")
    private String gsresult;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("DZZZRESULT")
    @Column(columnDefinition = "DZZZRESULT")
    @FieldDes(name = "dzzzresult", lenth = "500", type = "String", memo = "")
    private String dzzzresult;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("JYFW")
    @Column(columnDefinition = "JYFW")
    @FieldDes(name = "jyfw", lenth = "6000", type = "String", memo = "")
    private String jyfw;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("BELONGINDUSTRYCODE")
    @Column(columnDefinition = "BELONGINDUSTRYCODE")
    @FieldDes(name = "belongindustrycode", lenth = "255", type = "String", memo = "")
    private String belongindustrycode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ZGTOTALNUM")
    @Column(columnDefinition = "ZGTOTALNUM")
    @FieldDes(name = "zgtotalnum", type = "Integer", memo = "")
    private Integer zgtotalnum;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("YFRYTOTALNUM")
    @Column(columnDefinition = "YFRYTOTALNUM")
    @FieldDes(name = "yfrytotalnum", type = "Integer", memo = "")
    private Integer yfrytotalnum;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ORGANIZATIONCODE")
    @Column(columnDefinition = "ORGANIZATIONCODE")
    @FieldDes(name = "organizationcode", lenth = "255", type = "String", memo = "")
    private String organizationcode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("zkxbj")
    @Column(columnDefinition = "zkxbj")
    @FieldDes(name = "zkxbj", lenth = "10", type = "String", memo = "")
    private String zkxbj;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TZJGNAME")
    @Column(columnDefinition = "TZJGNAME")
    @FieldDes(name = "tzjgname", lenth = "255", type = "String", memo = "")
    private String tzjgname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("FUNDFOUNDER")
    @Column(columnDefinition = "FUNDFOUNDER")
    @FieldDes(name = "fundfounder", lenth = "500", type = "String", memo = "")
    private String fundfounder;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("GLZJFUNDS")
    @Column(columnDefinition = "GLZJFUNDS")
    @FieldDes(name = "glzjfunds", lenth = "255", type = "String", memo = "")
    private String glzjfunds;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TZQYNUM")
    @Column(columnDefinition = "TZQYNUM")
    @FieldDes(name = "tzqynum", lenth = "255", type = "String", memo = "")
    private String tzqynum;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SYJHSEMAIL")
    @Column(columnDefinition = "SYJHSEMAIL")
    @FieldDes(name = "syjhsemail", lenth = "255", type = "String", memo = "")
    private String syjhsemail;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TZJGLINKMAN")
    @Column(columnDefinition = "TZJGLINKMAN")
    @FieldDes(name = "tzjglinkman", lenth = "255", type = "String", memo = "")
    private String tzjglinkman;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TZJGMOBILE")
    @Column(columnDefinition = "TZJGMOBILE")
    @FieldDes(name = "tzjgmobile", lenth = "255", type = "String", memo = "")
    private String tzjgmobile;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ISTZJG")
    @Column(columnDefinition = "ISTZJG")
    @FieldDes(name = "istzjg", lenth = "255", type = "String", memo = "")
    private String istzjg;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("TZJGTELPHONE")
    @Column(columnDefinition = "TZJGTELPHONE")
    @FieldDes(name = "tzjgtelphone", lenth = "255", type = "String", memo = "")
    private String tzjgtelphone;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("WQCHECK")
    @Column(columnDefinition = "WQCHECK")
    private String wqcheck;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("MAINTECH")
    @Column(columnDefinition = "MAINTECH")
    private String maintech;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("GQ_LASTTIME")
    @Column(columnDefinition = "GQ_LASTTIME")
    private String gqLasttime;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("KX_LASTTIME")
    @Column(columnDefinition = "KX_LASTTIME")
    private String kxLasttime;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SBBH")
    @Column(columnDefinition = "SBBH")
    @FieldDes(name = "sbbh", lenth = "255", type = "String", memo = "")
    private String sbbh;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SBUPDATETIME")
    @Column(columnDefinition = "SBUPDATETIME")
    @FieldDes(name = "sbupdatetime", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date sbupdatetime;

    /**
     *  已建国家级、省级科技创新基地（平台）名称
     */
    @ApiModelProperty("已建国家级、省级科技创新基地（平台）名称")
    @TableField("YJCXJDNAME")
    @Column(columnDefinition = "YJCXJDNAME")
    @FieldDes(name = "yjcxjdname", lenth = "1000", type = "String", memo = "已建国家级、省级科技创新基地（平台）名称")
    private String yjcxjdname;

    /**
     *  其中外资（及港澳台资）比例
     */
    @ApiModelProperty("其中外资（及港澳台资）比例")
    @TableField("FOREIGNPERCENT")
    @Column(columnDefinition = "FOREIGNPERCENT")
    private String foreignpercent;

    /**
     *  研发机构（是、否）
     */
    @ApiModelProperty("研发机构（是、否）")
    @TableField("ISYFJG")
    @Column(columnDefinition = "ISYFJG")
    @FieldDes(name = "isyfjg", lenth = "50", type = "String", memo = "研发机构（是、否）")
    private String isyfjg;

    /**
     *  开户银行地址
     */
    @ApiModelProperty("开户银行地址")
    @TableField("BANKADDRESS")
    @Column(columnDefinition = "BANKADDRESS")
    @FieldDes(name = "bankaddress", lenth = "255", type = "String", memo = "开户银行地址")
    private String bankaddress;

    /**
     *  开户银行所在省
     */
    @ApiModelProperty("开户银行所在省")
    @TableField("BANKPROVINCE")
    @Column(columnDefinition = "BANKPROVINCE")
    @FieldDes(name = "bankprovince", lenth = "255", type = "String", memo = "开户银行所在省")
    private String bankprovince;

    /**
     *  开户银行所在市
     */
    @ApiModelProperty("开户银行所在市")
    @TableField("BANKCITY")
    @Column(columnDefinition = "BANKCITY")
    @FieldDes(name = "bankcity", lenth = "255", type = "String", memo = "开户银行所在市")
    private String bankcity;

    /**
     *  开户银行所在县区
     */
    @ApiModelProperty("开户银行所在县区")
    @TableField("BANKCOUNTY")
    @Column(columnDefinition = "BANKCOUNTY")
    @FieldDes(name = "bankcounty", lenth = "255", type = "String", memo = "开户银行所在县区")
    private String bankcounty;

    /**
     *  银行行号
     */
    @ApiModelProperty("银行行号")
    @TableField("BANKNO")
    @Column(columnDefinition = "BANKNO")
    private String bankno;

    /**
     *  高新区类型（省级、国家级）
     */
    @ApiModelProperty("高新区类型（省级、国家级）")
    @TableField("GXQTYPE")
    @Column(columnDefinition = "GXQTYPE")
    @FieldDes(name = "gxqtype", lenth = "255", type = "String", memo = "高新区类型（省级、国家级）")
    private String gxqtype;

    /**
     *  行业领域类别
     */
    @ApiModelProperty("行业领域类别")
    @TableField("INDUSTRYPHY")
    @Column(columnDefinition = "INDUSTRYPHY")
    private String industryphy;

    /**
     *  省属or部署or啥也不是
     */
    @ApiModelProperty("省属or部署or啥也不是")
    @TableField("SSBS")
    @Column(columnDefinition = "SSBS")
    @FieldDes(name = "ssbs", lenth = "255", type = "String", memo = "省属or部署or啥也不是")
    private String ssbs;


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
    *  
    */
    public String getIsrjqy (){
        return isrjqy;
    }
    public void setIsrjqy (String isrjqy){
        this.isrjqy = isrjqy;
    }

    /**
    *  
    */
    public String getEnterprisescale (){
        return enterprisescale;
    }
    public void setEnterprisescale (String enterprisescale){
        this.enterprisescale = enterprisescale;
    }

    /**
    *  
    */
    public String getEnterprisenature (){
        return enterprisenature;
    }
    public void setEnterprisenature (String enterprisenature){
        this.enterprisenature = enterprisenature;
    }

    /**
    *  
    */
    public String getTechnicalfield (){
        return technicalfield;
    }
    public void setTechnicalfield (String technicalfield){
        this.technicalfield = technicalfield;
    }

    /**
    *  
    */
    public String getMainproduct (){
        return mainproduct;
    }
    public void setMainproduct (String mainproduct){
        this.mainproduct = mainproduct;
    }

    /**
    *  
    */
    public String getCasemanagement (){
        return casemanagement;
    }
    public void setCasemanagement (String casemanagement){
        this.casemanagement = casemanagement;
    }

    /**
    *  
    */
    public String getHightechfieldcatalogue (){
        return hightechfieldcatalogue;
    }
    public void setHightechfieldcatalogue (String hightechfieldcatalogue){
        this.hightechfieldcatalogue = hightechfieldcatalogue;
    }

    /**
    *  
    */
    public String getCommercialauthoritiecode (){
        return commercialauthoritiecode;
    }
    public void setCommercialauthoritiecode (String commercialauthoritiecode){
        this.commercialauthoritiecode = commercialauthoritiecode;
    }

    /**
    *  
    */
    public String getResearchtype (){
        return researchtype;
    }
    public void setResearchtype (String researchtype){
        this.researchtype = researchtype;
    }

    /**
    *  
    */
    public String getEnterprisebq (){
        return enterprisebq;
    }
    public void setEnterprisebq (String enterprisebq){
        this.enterprisebq = enterprisebq;
    }

    /**
    *  
    */
    public String getIsgsqy (){
        return isgsqy;
    }
    public void setIsgsqy (String isgsqy){
        this.isgsqy = isgsqy;
    }

    /**
    *  
    */
    public String getIsmyqy (){
        return ismyqy;
    }
    public void setIsmyqy (String ismyqy){
        this.ismyqy = ismyqy;
    }

    /**
    *  
    */
    public String getIsgxqy (){
        return isgxqy;
    }
    public void setIsgxqy (String isgxqy){
        this.isgxqy = isgxqy;
    }

    /**
    *  
    */
    public String getGxqyno (){
        return gxqyno;
    }
    public void setGxqyno (String gxqyno){
        this.gxqyno = gxqyno;
    }

    /**
    *  
    */
    public String getIsgxqnqy (){
        return isgxqnqy;
    }
    public void setIsgxqnqy (String isgxqnqy){
        this.isgxqnqy = isgxqnqy;
    }

    /**
    *  
    */
    public String getBelonggxq (){
        return belonggxq;
    }
    public void setBelonggxq (String belonggxq){
        this.belonggxq = belonggxq;
    }

    /**
    *  
    */
    public String getIslistedqy (){
        return islistedqy;
    }
    public void setIslistedqy (String islistedqy){
        this.islistedqy = islistedqy;
    }

    /**
    *  
    */
    public String getListedsector (){
        return listedsector;
    }
    public void setListedsector (String listedsector){
        this.listedsector = listedsector;
    }

    /**
    *  
    */
    public String getIsgpqy (){
        return isgpqy;
    }
    public void setIsgpqy (String isgpqy){
        this.isgpqy = isgpqy;
    }

    /**
    *  
    */
    public String getGptype (){
        return gptype;
    }
    public void setGptype (String gptype){
        this.gptype = gptype;
    }

    /**
    *  
    */
    public String getHightechnicalfield (){
        return hightechnicalfield;
    }
    public void setHightechnicalfield (String hightechnicalfield){
        this.hightechnicalfield = hightechnicalfield;
    }

    /**
    *  
    */
    public String getIskjxzxqy (){
        return iskjxzxqy;
    }
    public void setIskjxzxqy (String iskjxzxqy){
        this.iskjxzxqy = iskjxzxqy;
    }

    /**
    *  
    */
    public String getInnovationtype (){
        return innovationtype;
    }
    public void setInnovationtype (String innovationtype){
        this.innovationtype = innovationtype;
    }

    /**
    *  
    */
    public String getRddirection (){
        return rddirection;
    }
    public void setRddirection (String rddirection){
        this.rddirection = rddirection;
    }

    /**
    *  
    */
    public String getIsfhqqy (){
        return isfhqqy;
    }
    public void setIsfhqqy (String isfhqqy){
        this.isfhqqy = isfhqqy;
    }

    /**
    *  
    */
    public String getBelongfhq (){
        return belongfhq;
    }
    public void setBelongfhq (String belongfhq){
        this.belongfhq = belongfhq;
    }

    /**
    *  
    */
    public String getIsaddvcunit (){
        return isaddvcunit;
    }
    public void setIsaddvcunit (String isaddvcunit){
        this.isaddvcunit = isaddvcunit;
    }

    /**
    *  
    */
    public String getVcunitname (){
        return vcunitname;
    }
    public void setVcunitname (String vcunitname){
        this.vcunitname = vcunitname;
    }

    /**
    *  
    */
    public String getVcfund (){
        return vcfund;
    }
    public void setVcfund (String vcfund){
        this.vcfund = vcfund;
    }

    /**
    *  
    */
    public String getActualcapital (){
        return actualcapital;
    }
    public void setActualcapital (String actualcapital){
        this.actualcapital = actualcapital;
    }

    /**
    *  
    */
    public String getActualcapitalCurrency (){
        return actualcapitalCurrency;
    }
    public void setActualcapitalCurrency (String actualcapitalCurrency){
        this.actualcapitalCurrency = actualcapitalCurrency;
    }

    /**
    *  
    */
    public String getIsindependentlegalman (){
        return isindependentlegalman;
    }
    public void setIsindependentlegalman (String isindependentlegalman){
        this.isindependentlegalman = isindependentlegalman;
    }

    /**
    *  
    */
    public String getOfficeaddress (){
        return officeaddress;
    }
    public void setOfficeaddress (String officeaddress){
        this.officeaddress = officeaddress;
    }

    /**
    *  
    */
    public String getCasemanagementid (){
        return casemanagementid;
    }
    public void setCasemanagementid (String casemanagementid){
        this.casemanagementid = casemanagementid;
    }

    /**
    *  
    */
    public String getBelonglevel (){
        return belonglevel;
    }
    public void setBelonglevel (String belonglevel){
        this.belonglevel = belonglevel;
    }

    /**
    *  
    */
    public String getEmergingindustry (){
        return emergingindustry;
    }
    public void setEmergingindustry (String emergingindustry){
        this.emergingindustry = emergingindustry;
    }

    /**
    *  
    */
    public String getTaxauthority (){
        return taxauthority;
    }
    public void setTaxauthority (String taxauthority){
        this.taxauthority = taxauthority;
    }

    /**
    *  
    */
    public String getTaxpayerno (){
        return taxpayerno;
    }
    public void setTaxpayerno (String taxpayerno){
        this.taxpayerno = taxpayerno;
    }

    /**
    *  
    */
    public String getTelephone (){
        return telephone;
    }
    public void setTelephone (String telephone){
        this.telephone = telephone;
    }

    /**
    *  
    */
    public String getFax (){
        return fax;
    }
    public void setFax (String fax){
        this.fax = fax;
    }

    /**
    *  
    */
    public String getEmail (){
        return email;
    }
    public void setEmail (String email){
        this.email = email;
    }

    /**
    *  
    */
    public String getUnitname (){
        return unitname;
    }
    public void setUnitname (String unitname){
        this.unitname = unitname;
    }

    /**
    *  
    */
    public String getCreditcode (){
        return creditcode;
    }
    public void setCreditcode (String creditcode){
        this.creditcode = creditcode;
    }

    /**
    *  
    */
    public Date getEstablishdate (){
        return establishdate;
    }
    public void setEstablishdate (Date establishdate){
        this.establishdate = establishdate;
    }

    /**
    *  
    */
    public String getRegistercapital (){
        return registercapital;
    }
    public void setRegistercapital (String registercapital){
        this.registercapital = registercapital;
    }

    /**
    *  
    */
    public String getRegisteraddress (){
        return registeraddress;
    }
    public void setRegisteraddress (String registeraddress){
        this.registeraddress = registeraddress;
    }

    /**
    *  
    */
    public String getRegisterdept (){
        return registerdept;
    }
    public void setRegisterdept (String registerdept){
        this.registerdept = registerdept;
    }

    /**
    *  
    */
    public String getProvince (){
        return province;
    }
    public void setProvince (String province){
        this.province = province;
    }

    /**
    *  
    */
    public String getCity (){
        return city;
    }
    public void setCity (String city){
        this.city = city;
    }

    /**
    *  
    */
    public String getCounty (){
        return county;
    }
    public void setCounty (String county){
        this.county = county;
    }

    /**
    *  
    */
    public String getLocationcode (){
        return locationcode;
    }
    public void setLocationcode (String locationcode){
        this.locationcode = locationcode;
    }

    /**
    *  
    */
    public String getMainbusiness (){
        return mainbusiness;
    }
    public void setMainbusiness (String mainbusiness){
        this.mainbusiness = mainbusiness;
    }

    /**
    *  
    */
    public String getUnittype (){
        return unittype;
    }
    public void setUnittype (String unittype){
        this.unittype = unittype;
    }

    /**
    *  
    */
    public String getBelongindustry (){
        return belongindustry;
    }
    public void setBelongindustry (String belongindustry){
        this.belongindustry = belongindustry;
    }

    /**
    *  
    */
    public String getEnterprisetype (){
        return enterprisetype;
    }
    public void setEnterprisetype (String enterprisetype){
        this.enterprisetype = enterprisetype;
    }

    /**
    *  
    */
    public String getPostalcode (){
        return postalcode;
    }
    public void setPostalcode (String postalcode){
        this.postalcode = postalcode;
    }

    /**
    *  
    */
    public String getUnitnameAbbreviation (){
        return unitnameAbbreviation;
    }
    public void setUnitnameAbbreviation (String unitnameAbbreviation){
        this.unitnameAbbreviation = unitnameAbbreviation;
    }

    /**
    *  
    */
    public String getBankname (){
        return bankname;
    }
    public void setBankname (String bankname){
        this.bankname = bankname;
    }

    /**
    *  
    */
    public String getBankaccount (){
        return bankaccount;
    }
    public void setBankaccount (String bankaccount){
        this.bankaccount = bankaccount;
    }

    /**
    *  
    */
    public String getAccountname (){
        return accountname;
    }
    public void setAccountname (String accountname){
        this.accountname = accountname;
    }

    /**
    *  
    */
    public String getWibsite (){
        return wibsite;
    }
    public void setWibsite (String wibsite){
        this.wibsite = wibsite;
    }

    /**
    *  
    */
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
    }

    /**
    *  
    */
    public String getMainid (){
        return mainid;
    }
    public void setMainid (String mainid){
        this.mainid = mainid;
    }

    /**
    *  
    */
    public String getSourceid (){
        return sourceid;
    }
    public void setSourceid (String sourceid){
        this.sourceid = sourceid;
    }

    /**
    *  
    */
    public Integer getSeq (){
        return seq;
    }
    public void setSeq (Integer seq){
        this.seq = seq;
    }

    /**
    *  
    */
    public String getCurrency (){
        return currency;
    }
    public void setCurrency (String currency){
        this.currency = currency;
    }

    /**
    *  
    */
    public String getStreet (){
        return street;
    }
    public void setStreet (String street){
        this.street = street;
    }

    /**
    *  
    */
    public String getOfficeprovince (){
        return officeprovince;
    }
    public void setOfficeprovince (String officeprovince){
        this.officeprovince = officeprovince;
    }

    /**
    *  
    */
    public String getOfficecity (){
        return officecity;
    }
    public void setOfficecity (String officecity){
        this.officecity = officecity;
    }

    /**
    *  
    */
    public String getOfficecounty (){
        return officecounty;
    }
    public void setOfficecounty (String officecounty){
        this.officecounty = officecounty;
    }

    /**
    *  
    */
    public String getOfficestreet (){
        return officestreet;
    }
    public void setOfficestreet (String officestreet){
        this.officestreet = officestreet;
    }

    /**
    *  
    */
    public String getIsotherunittype (){
        return isotherunittype;
    }
    public void setIsotherunittype (String isotherunittype){
        this.isotherunittype = isotherunittype;
    }

    /**
    *  
    */
    public String getOfficegetmethod (){
        return officegetmethod;
    }
    public void setOfficegetmethod (String officegetmethod){
        this.officegetmethod = officegetmethod;
    }

    /**
    *  
    */
    public String getKjxzxqyno (){
        return kjxzxqyno;
    }
    public void setKjxzxqyno (String kjxzxqyno){
        this.kjxzxqyno = kjxzxqyno;
    }

    /**
    *  
    */
    public String getStockcode (){
        return stockcode;
    }
    public void setStockcode (String stockcode){
        this.stockcode = stockcode;
    }

    /**
    *  
    */
    public String getGxqystartdate (){
        return gxqystartdate;
    }
    public void setGxqystartdate (String gxqystartdate){
        this.gxqystartdate = gxqystartdate;
    }

    /**
    *  
    */
    public String getGxqyenddate (){
        return gxqyenddate;
    }
    public void setGxqyenddate (String gxqyenddate){
        this.gxqyenddate = gxqyenddate;
    }

    /**
    *  
    */
    public String getGxqylisteddate (){
        return gxqylisteddate;
    }
    public void setGxqylisteddate (String gxqylisteddate){
        this.gxqylisteddate = gxqylisteddate;
    }

    /**
    *  
    */
    public String getBelongnation (){
        return belongnation;
    }
    public void setBelongnation (String belongnation){
        this.belongnation = belongnation;
    }

    /**
    *  
    */
    public String getSimpleunittype (){
        return simpleunittype;
    }
    public void setSimpleunittype (String simpleunittype){
        this.simpleunittype = simpleunittype;
    }

    /**
    *  
    */
    public String getCountrycasemanagement (){
        return countrycasemanagement;
    }
    public void setCountrycasemanagement (String countrycasemanagement){
        this.countrycasemanagement = countrycasemanagement;
    }

    /**
    *  
    */
    public String getCountrycasemanagementid (){
        return countrycasemanagementid;
    }
    public void setCountrycasemanagementid (String countrycasemanagementid){
        this.countrycasemanagementid = countrycasemanagementid;
    }

    /**
    *  
    */
    public String getCitycasemanagement (){
        return citycasemanagement;
    }
    public void setCitycasemanagement (String citycasemanagement){
        this.citycasemanagement = citycasemanagement;
    }

    /**
    *  
    */
    public String getCitycasemanagementid (){
        return citycasemanagementid;
    }
    public void setCitycasemanagementid (String citycasemanagementid){
        this.citycasemanagementid = citycasemanagementid;
    }

    /**
    *  保存时间
    */
    public Date getSavedate (){
        return savedate;
    }
    public void setSavedate (Date savedate){
        this.savedate = savedate;
    }

    /**
    *  最后一次更新时间
    */
    public Date getUpdatelasttime (){
        return updatelasttime;
    }
    public void setUpdatelasttime (Date updatelasttime){
        this.updatelasttime = updatelasttime;
    }

    /**
    *  
    */
    public String getSimpleunittypeid (){
        return simpleunittypeid;
    }
    public void setSimpleunittypeid (String simpleunittypeid){
        this.simpleunittypeid = simpleunittypeid;
    }

    /**
    *  
    */
    public String getGsresult (){
        return gsresult;
    }
    public void setGsresult (String gsresult){
        this.gsresult = gsresult;
    }

    /**
    *  
    */
    public String getDzzzresult (){
        return dzzzresult;
    }
    public void setDzzzresult (String dzzzresult){
        this.dzzzresult = dzzzresult;
    }

    /**
    *  
    */
    public String getJyfw (){
        return jyfw;
    }
    public void setJyfw (String jyfw){
        this.jyfw = jyfw;
    }

    /**
    *  
    */
    public String getBelongindustrycode (){
        return belongindustrycode;
    }
    public void setBelongindustrycode (String belongindustrycode){
        this.belongindustrycode = belongindustrycode;
    }

    /**
    *  
    */
    public Integer getZgtotalnum (){
        return zgtotalnum;
    }
    public void setZgtotalnum (Integer zgtotalnum){
        this.zgtotalnum = zgtotalnum;
    }

    /**
    *  
    */
    public Integer getYfrytotalnum (){
        return yfrytotalnum;
    }
    public void setYfrytotalnum (Integer yfrytotalnum){
        this.yfrytotalnum = yfrytotalnum;
    }

    /**
    *  
    */
    public String getOrganizationcode (){
        return organizationcode;
    }
    public void setOrganizationcode (String organizationcode){
        this.organizationcode = organizationcode;
    }

    /**
    *  
    */
    public String getZkxbj (){
        return zkxbj;
    }
    public void setZkxbj (String zkxbj){
        this.zkxbj = zkxbj;
    }

    /**
    *  
    */
    public String getTzjgname (){
        return tzjgname;
    }
    public void setTzjgname (String tzjgname){
        this.tzjgname = tzjgname;
    }

    /**
    *  
    */
    public String getFundfounder (){
        return fundfounder;
    }
    public void setFundfounder (String fundfounder){
        this.fundfounder = fundfounder;
    }

    /**
    *  
    */
    public String getGlzjfunds (){
        return glzjfunds;
    }
    public void setGlzjfunds (String glzjfunds){
        this.glzjfunds = glzjfunds;
    }

    /**
    *  
    */
    public String getTzqynum (){
        return tzqynum;
    }
    public void setTzqynum (String tzqynum){
        this.tzqynum = tzqynum;
    }

    /**
    *  
    */
    public String getSyjhsemail (){
        return syjhsemail;
    }
    public void setSyjhsemail (String syjhsemail){
        this.syjhsemail = syjhsemail;
    }

    /**
    *  
    */
    public String getTzjglinkman (){
        return tzjglinkman;
    }
    public void setTzjglinkman (String tzjglinkman){
        this.tzjglinkman = tzjglinkman;
    }

    /**
    *  
    */
    public String getTzjgmobile (){
        return tzjgmobile;
    }
    public void setTzjgmobile (String tzjgmobile){
        this.tzjgmobile = tzjgmobile;
    }

    /**
    *  
    */
    public String getIstzjg (){
        return istzjg;
    }
    public void setIstzjg (String istzjg){
        this.istzjg = istzjg;
    }

    /**
    *  
    */
    public String getTzjgtelphone (){
        return tzjgtelphone;
    }
    public void setTzjgtelphone (String tzjgtelphone){
        this.tzjgtelphone = tzjgtelphone;
    }

    /**
    *  
    */
    public String getWqcheck (){
        return wqcheck;
    }
    public void setWqcheck (String wqcheck){
        this.wqcheck = wqcheck;
    }

    /**
    *  
    */
    public String getMaintech (){
        return maintech;
    }
    public void setMaintech (String maintech){
        this.maintech = maintech;
    }

    /**
    *  
    */
    public String getGqLasttime (){
        return gqLasttime;
    }
    public void setGqLasttime (String gqLasttime){
        this.gqLasttime = gqLasttime;
    }

    /**
    *  
    */
    public String getKxLasttime (){
        return kxLasttime;
    }
    public void setKxLasttime (String kxLasttime){
        this.kxLasttime = kxLasttime;
    }

    /**
    *  
    */
    public String getSbbh (){
        return sbbh;
    }
    public void setSbbh (String sbbh){
        this.sbbh = sbbh;
    }

    /**
    *  
    */
    public Date getSbupdatetime (){
        return sbupdatetime;
    }
    public void setSbupdatetime (Date sbupdatetime){
        this.sbupdatetime = sbupdatetime;
    }

    /**
    *  已建国家级、省级科技创新基地（平台）名称
    */
    public String getYjcxjdname (){
        return yjcxjdname;
    }
    public void setYjcxjdname (String yjcxjdname){
        this.yjcxjdname = yjcxjdname;
    }

    /**
    *  其中外资（及港澳台资）比例
    */
    public String getForeignpercent (){
        return foreignpercent;
    }
    public void setForeignpercent (String foreignpercent){
        this.foreignpercent = foreignpercent;
    }

    /**
    *  研发机构（是、否）
    */
    public String getIsyfjg (){
        return isyfjg;
    }
    public void setIsyfjg (String isyfjg){
        this.isyfjg = isyfjg;
    }

    /**
    *  开户银行地址
    */
    public String getBankaddress (){
        return bankaddress;
    }
    public void setBankaddress (String bankaddress){
        this.bankaddress = bankaddress;
    }

    /**
    *  开户银行所在省
    */
    public String getBankprovince (){
        return bankprovince;
    }
    public void setBankprovince (String bankprovince){
        this.bankprovince = bankprovince;
    }

    /**
    *  开户银行所在市
    */
    public String getBankcity (){
        return bankcity;
    }
    public void setBankcity (String bankcity){
        this.bankcity = bankcity;
    }

    /**
    *  开户银行所在县区
    */
    public String getBankcounty (){
        return bankcounty;
    }
    public void setBankcounty (String bankcounty){
        this.bankcounty = bankcounty;
    }

    /**
    *  银行行号
    */
    public String getBankno (){
        return bankno;
    }
    public void setBankno (String bankno){
        this.bankno = bankno;
    }

    /**
    *  高新区类型（省级、国家级）
    */
    public String getGxqtype (){
        return gxqtype;
    }
    public void setGxqtype (String gxqtype){
        this.gxqtype = gxqtype;
    }

    /**
    *  行业领域类别
    */
    public String getIndustryphy (){
        return industryphy;
    }
    public void setIndustryphy (String industryphy){
        this.industryphy = industryphy;
    }

    /**
    *  省属or部署or啥也不是
    */
    public String getSsbs (){
        return ssbs;
    }
    public void setSsbs (String ssbs){
        this.ssbs = ssbs;
    }

    public String getCasemanagementtype() {
        return casemanagementtype;
    }

    public void setCasemanagementtype(String casemanagementtype) {
        this.casemanagementtype = casemanagementtype;
    }
}