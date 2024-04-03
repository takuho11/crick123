/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 16:01:25
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
 *  数据仓-载体信息
 */
@Entity
@Table(name = "BI_C_BI")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiCBi {

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
    @TableField("CARRIERNAME")
    @Column(columnDefinition = "CARRIERNAME")
    @FieldDes(name = "carriername", lenth = "255", type = "String", memo = "")
    private String carriername;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CARRIERRANK")
    @Column(columnDefinition = "CARRIERRANK")
    @FieldDes(name = "carrierrank", lenth = "255", type = "String", memo = "")
    private String carrierrank;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("BELONGFIELD")
    @Column(columnDefinition = "BELONGFIELD")
    @FieldDes(name = "belongfield", lenth = "255", type = "String", memo = "")
    private String belongfield;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ADDRESS")
    @Column(columnDefinition = "ADDRESS")
    @FieldDes(name = "address", lenth = "255", type = "String", memo = "")
    private String address;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("LEADERNAME")
    @Column(columnDefinition = "LEADERNAME")
    @FieldDes(name = "leadername", lenth = "255", type = "String", memo = "")
    private String leadername;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("LEADERMOBILE")
    @Column(columnDefinition = "LEADERMOBILE")
    @FieldDes(name = "leadermobile", lenth = "255", type = "String", memo = "")
    private String leadermobile;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("LEADERPHONE")
    @Column(columnDefinition = "LEADERPHONE")
    @FieldDes(name = "leaderphone", lenth = "255", type = "String", memo = "")
    private String leaderphone;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("LEADEREMAIL")
    @Column(columnDefinition = "LEADEREMAIL")
    @FieldDes(name = "leaderemail", lenth = "255", type = "String", memo = "")
    private String leaderemail;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("RESEARCHDIRECTIONS")
    @Column(columnDefinition = "RESEARCHDIRECTIONS")
    @FieldDes(name = "researchdirections", lenth = "2000", type = "String", memo = "")
    private String researchdirections;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SUMMARY")
    @Column(columnDefinition = "SUMMARY")
    @FieldDes(name = "summary", lenth = "2000", type = "String", memo = "")
    private String summary;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PROVINCE")
    @Column(columnDefinition = "PROVINCE")
    @FieldDes(name = "province", lenth = "255", type = "String", memo = "")
    private String province;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CITY")
    @Column(columnDefinition = "CITY")
    @FieldDes(name = "city", lenth = "255", type = "String", memo = "")
    private String city;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("COUNTY")
    @Column(columnDefinition = "COUNTY")
    @FieldDes(name = "county", lenth = "255", type = "String", memo = "")
    private String county;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AWARDUNIT")
    @Column(columnDefinition = "AWARDUNIT")
    @FieldDes(name = "awardunit", lenth = "255", type = "String", memo = "")
    private String awardunit;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("RECOGNIZEDATE")
    @Column(columnDefinition = "RECOGNIZEDATE")
    @FieldDes(name = "recognizedate", type = "Date", memo = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date recognizedate;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SUBJECTCODE")
    @Column(columnDefinition = "SUBJECTCODE")
    @FieldDes(name = "subjectcode", lenth = "255", type = "String", memo = "")
    private String subjectcode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SUBJECTNAME")
    @Column(columnDefinition = "SUBJECTNAME")
    @FieldDes(name = "subjectname", lenth = "255", type = "String", memo = "")
    private String subjectname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("AGENCYMEMBERNUM")
    @Column(columnDefinition = "AGENCYMEMBERNUM")
    @FieldDes(name = "agencymembernum", type = "Integer", memo = "")
    private Integer agencymembernum;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EQUIPMENTNUM")
    @Column(columnDefinition = "EQUIPMENTNUM")
    @FieldDes(name = "equipmentnum", type = "Integer", memo = "")
    private Integer equipmentnum;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("EQUIPMENTPRICE")
    @Column(columnDefinition = "EQUIPMENTPRICE")
    private String equipmentprice;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CARRIERTYPE")
    @Column(columnDefinition = "CARRIERTYPE")
    @FieldDes(name = "carriertype", lenth = "255", type = "String", memo = "")
    private String carriertype;

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
     *  编码
     */
    @ApiModelProperty("编码")
    @TableField("PRPCODE")
    @Column(columnDefinition = "PRPCODE")
    @FieldDes(name = "prpcode", type = "Long", memo = "编码")
    private Long prpcode;

    /**
     *  研发人员数
     */
    @ApiModelProperty("研发人员数")
    @TableField("DEVELOPNUM")
    @Column(columnDefinition = "DEVELOPNUM")
    @FieldDes(name = "developnum", type = "Long", memo = "研发人员数")
    private Long developnum;

    /**
     *  统一社会信用代码
     */
    @ApiModelProperty("统一社会信用代码")
    @TableField("ORGSHXYNO")
    @Column(columnDefinition = "ORGSHXYNO")
    @FieldDes(name = "orgshxyno", lenth = "400", type = "String", memo = "统一社会信用代码")
    private String orgshxyno;

    /**
     *  到期时间
     */
    @ApiModelProperty("到期时间")
    @TableField("DQDATE")
    @Column(columnDefinition = "DQDATE")
    @FieldDes(name = "dqdate", lenth = "400", type = "String", memo = "到期时间")
    private String dqdate;

    /**
     *  申报单位类型
     */
    @ApiModelProperty("申报单位类型")
    @TableField("SBDWNAME")
    @Column(columnDefinition = "SBDWNAME")
    @FieldDes(name = "sbdwname", lenth = "400", type = "String", memo = "申报单位类型")
    private String sbdwname;

    /**
     *  单位性质
     */
    @ApiModelProperty("单位性质")
    @TableField("SBDWNATURE")
    @Column(columnDefinition = "SBDWNATURE")
    @FieldDes(name = "sbdwnature", lenth = "400", type = "String", memo = "单位性质")
    private String sbdwnature;

    /**
     *  所属地区
     */
    @ApiModelProperty("所属地区")
    @TableField("SBDWAREANAME")
    @Column(columnDefinition = "SBDWAREANAME")
    @FieldDes(name = "sbdwareaname", lenth = "400", type = "String", memo = "所属地区")
    private String sbdwareaname;

    /**
     *  经济类型
     */
    @ApiModelProperty("经济类型")
    @TableField("WORKERSTOTAL")
    @Column(columnDefinition = "WORKERSTOTAL")
    @FieldDes(name = "workerstotal", lenth = "400", type = "String", memo = "经济类型")
    private String workerstotal;

    /**
     *  研究人员数量
     */
    @ApiModelProperty("研究人员数量")
    @TableField("RDPSNNUM")
    @Column(columnDefinition = "RDPSNNUM")
    @FieldDes(name = "rdpsnnum", type = "Long", memo = "研究人员数量")
    private Long rdpsnnum;

    /**
     *  上年总产值
     */
    @ApiModelProperty("上年总产值")
    @TableField("ALLYEAROUTPUT")
    @Column(columnDefinition = "ALLYEAROUTPUT")
    private String allyearoutput;

    /**
     *  新产品产值
     */
    @ApiModelProperty("新产品产值")
    @TableField("NEWPRODUCTOUTPUT")
    @Column(columnDefinition = "NEWPRODUCTOUTPUT")
    private String newproductoutput;

    /**
     *  主营业务收入
     */
    @ApiModelProperty("主营业务收入")
    @TableField("SALESVALUE")
    @Column(columnDefinition = "SALESVALUE")
    private String salesvalue;

    /**
     *  新产品销售额
     */
    @ApiModelProperty("新产品销售额")
    @TableField("NEWPRODUCTSALESVALUE")
    @Column(columnDefinition = "NEWPRODUCTSALESVALUE")
    private String newproductsalesvalue;

    /**
     *  高新技术服务收入额
     */
    @ApiModelProperty("高新技术服务收入额")
    @TableField("GJSFWVALUE")
    @Column(columnDefinition = "GJSFWVALUE")
    private String gjsfwvalue;

    /**
     *  全年总出口额
     */
    @ApiModelProperty("全年总出口额")
    @TableField("EXPORTVALUE")
    @Column(columnDefinition = "EXPORTVALUE")
    private String exportvalue;

    /**
     *  新产品出口额
     */
    @ApiModelProperty("新产品出口额")
    @TableField("NEWPRODUCTEXPORTVALUE")
    @Column(columnDefinition = "NEWPRODUCTEXPORTVALUE")
    private String newproductexportvalue;

    /**
     *  研究开发经费投入
     */
    @ApiModelProperty("研究开发经费投入")
    @TableField("YJKFJFTRTOTAL")
    @Column(columnDefinition = "YJKFJFTRTOTAL")
    private String yjkfjftrtotal;

    /**
     *  固定资产总值
     */
    @ApiModelProperty("固定资产总值")
    @TableField("GDZCZZ")
    @Column(columnDefinition = "GDZCZZ")
    private String gdzczz;

    /**
     *  工程试验用房（平方米）
     */
    @ApiModelProperty("工程试验用房（平方米）")
    @TableField("GCSYYF")
    @Column(columnDefinition = "GCSYYF")
    private String gcsyyf;

    /**
     *  办公用房（平方米）
     */
    @ApiModelProperty("办公用房（平方米）")
    @TableField("BGYF")
    @Column(columnDefinition = "BGYF")
    private String bgyf;

    /**
     *  仪器设备装备总值
     */
    @ApiModelProperty("仪器设备装备总值")
    @TableField("YQSBZBZZ")
    @Column(columnDefinition = "YQSBZBZZ")
    private String yqsbzbzz;

    /**
     *  数据更新时间
     */
    @ApiModelProperty("数据更新时间")
    @TableField("updatetime")
    @Column(columnDefinition = "updatetime")
    @FieldDes(name = "updatetime", type = "Date", memo = "数据更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date updatetime;

    /**
     *  所在地
     */
    @ApiModelProperty("所在地")
    @TableField("AREANAME")
    @Column(columnDefinition = "AREANAME")
    @FieldDes(name = "areaname", lenth = "255", type = "String", memo = "所在地")
    private String areaname;

    /**
     *  组建年份
     */
    @ApiModelProperty("组建年份")
    @TableField("STATYEAR")
    @Column(columnDefinition = "STATYEAR")
    @FieldDes(name = "statyear", lenth = "10", type = "String", memo = "组建年份")
    private String statyear;

    /**
     *  建设单位
     */
    @ApiModelProperty("建设单位")
    @TableField("UNITNAME")
    @Column(columnDefinition = "UNITNAME")
    @FieldDes(name = "unitname", lenth = "200", type = "String", memo = "建设单位")
    private String unitname;

    /**
     *  开始时间有效期
     */
    @ApiModelProperty("开始时间有效期")
    @TableField("STARTTIME")
    @Column(columnDefinition = "STARTTIME")
    @FieldDes(name = "starttime", lenth = "50", type = "String", memo = "开始时间有效期")
    private String starttime;

    /**
     *  结束时间有效期
     */
    @ApiModelProperty("结束时间有效期")
    @TableField("ENDTIME")
    @Column(columnDefinition = "ENDTIME")
    @FieldDes(name = "endtime", lenth = "50", type = "String", memo = "结束时间有效期")
    private String endtime;

    /**
     *  机构类型
     */
    @ApiModelProperty("机构类型")
    @TableField("JGTYPE")
    @Column(columnDefinition = "JGTYPE")
    @FieldDes(name = "jgtype", lenth = "1500", type = "String", memo = "机构类型")
    private String jgtype;

    /**
     *  机构性质
     */
    @ApiModelProperty("机构性质")
    @TableField("JGNATURE")
    @Column(columnDefinition = "JGNATURE")
    @FieldDes(name = "jgnature", lenth = "1500", type = "String", memo = "机构性质")
    private String jgnature;

    /**
     *  研发类型
     */
    @ApiModelProperty("研发类型")
    @TableField("DEVLOPTYPE")
    @Column(columnDefinition = "DEVLOPTYPE")
    @FieldDes(name = "devloptype", lenth = "1500", type = "String", memo = "研发类型")
    private String devloptype;

    /**
     *  万元以上设备总值
     */
    @ApiModelProperty("万元以上设备总值")
    @TableField("WYINSTRUMENTPRICE")
    @Column(columnDefinition = "WYINSTRUMENTPRICE")
    private String wyinstrumentprice;

    /**
     *  国家级、省级创新平台数量
     */
    @ApiModelProperty("国家级、省级创新平台数量")
    @TableField("NATIONALPLATFORM")
    @Column(columnDefinition = "NATIONALPLATFORM")
    @FieldDes(name = "nationalplatform", type = "Long", memo = "国家级、省级创新平台数量")
    private Long nationalplatform;

    /**
     *  财政资金项目总数
     */
    @ApiModelProperty("财政资金项目总数")
    @TableField("CZZJPRPNUM")
    @Column(columnDefinition = "CZZJPRPNUM")
    @FieldDes(name = "czzjprpnum", type = "Long", memo = "财政资金项目总数")
    private Long czzjprpnum;

    /**
     *  财政资金支持总金额
     */
    @ApiModelProperty("财政资金支持总金额")
    @TableField("CZZJALLAMT")
    @Column(columnDefinition = "CZZJALLAMT")
    private String czzjallamt;

    /**
     *  累计专利申请数
     */
    @ApiModelProperty("累计专利申请数")
    @TableField("PATENTALLNUM")
    @Column(columnDefinition = "PATENTALLNUM")
    @FieldDes(name = "patentallnum", type = "Long", memo = "累计专利申请数")
    private Long patentallnum;

    /**
     *  发明专利申请数
     */
    @ApiModelProperty("发明专利申请数")
    @TableField("INVENTPATENTNUM")
    @Column(columnDefinition = "INVENTPATENTNUM")
    @FieldDes(name = "inventpatentnum", type = "Long", memo = "发明专利申请数")
    private Long inventpatentnum;

    /**
     *  PCT专利申请数
     */
    @ApiModelProperty("PCT专利申请数")
    @TableField("PCTPATENTNUM")
    @Column(columnDefinition = "PCTPATENTNUM")
    @FieldDes(name = "pctpatentnum", type = "Long", memo = "PCT专利申请数")
    private Long pctpatentnum;

    /**
     *  有效发明专利数
     */
    @ApiModelProperty("有效发明专利数")
    @TableField("VALIDINVENTPATENTNUM")
    @Column(columnDefinition = "VALIDINVENTPATENTNUM")
    @FieldDes(name = "validinventpatentnum", type = "Long", memo = "有效发明专利数")
    private Long validinventpatentnum;

    /**
     *  牵头制定标准数
     */
    @ApiModelProperty("牵头制定标准数")
    @TableField("QTZDBZNUM")
    @Column(columnDefinition = "QTZDBZNUM")
    @FieldDes(name = "qtzdbznum", type = "Long", memo = "牵头制定标准数")
    private Long qtzdbznum;

    /**
     *  参与制定标准数
     */
    @ApiModelProperty("参与制定标准数")
    @TableField("CYZDBZNUM")
    @Column(columnDefinition = "CYZDBZNUM")
    @FieldDes(name = "cyzdbznum", type = "Long", memo = "参与制定标准数")
    private Long cyzdbznum;

    /**
     *  科技奖励
     */
    @ApiModelProperty("科技奖励")
    @TableField("KJJLNUM")
    @Column(columnDefinition = "KJJLNUM")
    @FieldDes(name = "kjjlnum", type = "Long", memo = "科技奖励")
    private Long kjjlnum;

    /**
     *  近三年新产品数量
     */
    @ApiModelProperty("近三年新产品数量")
    @TableField("JSNXCPSLNUM")
    @Column(columnDefinition = "JSNXCPSLNUM")
    @FieldDes(name = "jsnxcpslnum", type = "Long", memo = "近三年新产品数量")
    private Long jsnxcpslnum;

    /**
     *  上年机构总收入
     */
    @ApiModelProperty("上年机构总收入")
    @TableField("SNJGALLINCOME")
    @Column(columnDefinition = "SNJGALLINCOME")
    private String snjgallincome;

    /**
     *  上年机构总支出
     */
    @ApiModelProperty("上年机构总支出")
    @TableField("SNJGALLEXPEND")
    @Column(columnDefinition = "SNJGALLEXPEND")
    private String snjgallexpend;

    /**
     *  科技成果转化收入
     */
    @ApiModelProperty("科技成果转化收入")
    @TableField("KJCGZHINCOME")
    @Column(columnDefinition = "KJCGZHINCOME")
    private String kjcgzhincome;

    /**
     *  研发经费支出
     */
    @ApiModelProperty("研发经费支出")
    @TableField("RDEXPEND")
    @Column(columnDefinition = "RDEXPEND")
    private String rdexpend;

    /**
     *  技术服务收入
     */
    @ApiModelProperty("技术服务收入")
    @TableField("TECHNOLOGYINCOME")
    @Column(columnDefinition = "TECHNOLOGYINCOME")
    private String technologyincome;

    /**
     *  近三年成果转化收入
     */
    @ApiModelProperty("近三年成果转化收入")
    @TableField("JSNCGZHINCOME")
    @Column(columnDefinition = "JSNCGZHINCOME")
    private String jsncgzhincome;

    /**
     *  创办企业数量
     */
    @ApiModelProperty("创办企业数量")
    @TableField("TAKEENTERPRISENUM")
    @Column(columnDefinition = "TAKEENTERPRISENUM")
    @FieldDes(name = "takeenterprisenum", type = "Long", memo = "创办企业数量")
    private Long takeenterprisenum;

    /**
     *  创办的企业上年度主营业务收入
     */
    @ApiModelProperty("创办的企业上年度主营业务收入")
    @TableField("ENTERPRISEINCOME")
    @Column(columnDefinition = "ENTERPRISEINCOME")
    private String enterpriseincome;

    /**
     *  在孵企业数量
     */
    @ApiModelProperty("在孵企业数量")
    @TableField("INCUBATEENTERPRISENUM")
    @Column(columnDefinition = "INCUBATEENTERPRISENUM")
    @FieldDes(name = "incubateenterprisenum", type = "Long", memo = "在孵企业数量")
    private Long incubateenterprisenum;

    /**
     *  孵化的企业上年度主营业务收入
     */
    @ApiModelProperty("孵化的企业上年度主营业务收入")
    @TableField("INCUBATEENTERPRISEINCOME")
    @Column(columnDefinition = "INCUBATEENTERPRISEINCOME")
    private String incubateenterpriseincome;

    /**
     *  创办企业中属于高新技术企业的数量
     */
    @ApiModelProperty("创办企业中属于高新技术企业的数量")
    @TableField("TAKEENTERPRISEGQNUM")
    @Column(columnDefinition = "TAKEENTERPRISEGQNUM")
    @FieldDes(name = "takeenterprisegqnum", type = "Long", memo = "创办企业中属于高新技术企业的数量")
    private Long takeenterprisegqnum;

    /**
     *  孵化企业中属于高新技术企业的数量
     */
    @ApiModelProperty("孵化企业中属于高新技术企业的数量")
    @TableField("INCUENTERPRISEGQNUM")
    @Column(columnDefinition = "INCUENTERPRISEGQNUM")
    @FieldDes(name = "incuenterprisegqnum", type = "Long", memo = "孵化企业中属于高新技术企业的数量")
    private Long incuenterprisegqnum;

    /**
     *  累计服务企业、机构数量
     */
    @ApiModelProperty("累计服务企业、机构数量")
    @TableField("SERVICEENTERPRISENUM")
    @Column(columnDefinition = "SERVICEENTERPRISENUM")
    @FieldDes(name = "serviceenterprisenum", type = "Long", memo = "累计服务企业、机构数量")
    private Long serviceenterprisenum;

    /**
     *  是否牵头设立产业创新联盟
     */
    @ApiModelProperty("是否牵头设立产业创新联盟")
    @TableField("ISQTSLCYCXLM")
    @Column(columnDefinition = "ISQTSLCYCXLM")
    private String isqtslcycxlm;

    /**
     *  是否牵头成立行业协会
     */
    @ApiModelProperty("是否牵头成立行业协会")
    @TableField("ISQTCLHYXH")
    @Column(columnDefinition = "ISQTCLHYXH")
    private String isqtclhyxh;

    /**
     *  科研场所（平方米）
     */
    @ApiModelProperty("科研场所（平方米）")
    @TableField("SCIENTIFICPLACE")
    @Column(columnDefinition = "SCIENTIFICPLACE")
    private String scientificplace;

    /**
     *  实验室类别
     */
    @ApiModelProperty("实验室类别")
    @TableField("SYSTYPE")
    @Column(columnDefinition = "SYSTYPE")
    @FieldDes(name = "systype", lenth = "1500", type = "String", memo = "实验室类别")
    private String systype;

    /**
     *  有效期
     */
    @ApiModelProperty("有效期")
    @TableField("VALIDDATE")
    @Column(columnDefinition = "VALIDDATE")
    @FieldDes(name = "validdate", lenth = "100", type = "String", memo = "有效期")
    private String validdate;

    /**
     *  技术领域
     */
    @ApiModelProperty("技术领域")
    @TableField("TECHNAME")
    @Column(columnDefinition = "TECHNAME")
    @FieldDes(name = "techname", lenth = "1500", type = "String", memo = "技术领域")
    private String techname;

    /**
     *  行业领域
     */
    @ApiModelProperty("行业领域")
    @TableField("INDUSTRYNAME")
    @Column(columnDefinition = "INDUSTRYNAME")
    @FieldDes(name = "industryname", lenth = "1500", type = "String", memo = "行业领域")
    private String industryname;

    /**
     *  是否涉及实验动物
     */
    @ApiModelProperty("是否涉及实验动物")
    @TableField("ISANIMALNAME")
    @Column(columnDefinition = "ISANIMALNAME")
    private String isanimalname;

    /**
     *  省科技厅资助
     */
    @ApiModelProperty("省科技厅资助")
    @TableField("PROVINCEAMT")
    @Column(columnDefinition = "PROVINCEAMT")
    private String provinceamt;

    /**
     *  科研仪器设备总值
     */
    @ApiModelProperty("科研仪器设备总值")
    @TableField("KYINSTRUMENTPRICE")
    @Column(columnDefinition = "KYINSTRUMENTPRICE")
    private String kyinstrumentprice;

    /**
     *  参与单位
     */
    @ApiModelProperty("参与单位")
    @TableField("CYORGNAMES")
    @Column(columnDefinition = "CYORGNAMES")
    @FieldDes(name = "cyorgnames", lenth = "1500", type = "String", memo = "参与单位")
    private String cyorgnames;

    /**
     *  负责人身份证
     */
    @ApiModelProperty("负责人身份证")
    @TableField("FZRCARDCODE")
    @Column(columnDefinition = "FZRCARDCODE")
    @FieldDes(name = "fzrcardcode", lenth = "100", type = "String", memo = "负责人身份证")
    private String fzrcardcode;

    /**
     *  所在地区
     */
    @ApiModelProperty("所在地区")
    @TableField("SSCITYNAME")
    @Column(columnDefinition = "SSCITYNAME")
    @FieldDes(name = "sscityname", lenth = "10", type = "String", memo = "所在地区")
    private String sscityname;

    /**
     *  运营主体名称
     */
    @ApiModelProperty("运营主体名称")
    @TableField("YYZTORGNAME")
    @Column(columnDefinition = "YYZTORGNAME")
    @FieldDes(name = "yyztorgname", lenth = "255", type = "String", memo = "运营主体名称")
    private String yyztorgname;

    /**
     *  主管单位
     */
    @ApiModelProperty("主管单位")
    @TableField("PARENTORGNAME")
    @Column(columnDefinition = "PARENTORGNAME")
    @FieldDes(name = "parentorgname", lenth = "255", type = "String", memo = "主管单位")
    private String parentorgname;

    /**
     *  场地总面积（平方米）
     */
    @ApiModelProperty("场地总面积（平方米）")
    @TableField("square")
    @Column(columnDefinition = "square")
    private String square;

    /**
     *  种子基金金额（万元）
     */
    @ApiModelProperty("种子基金金额（万元）")
    @TableField("Zzamt")
    @Column(columnDefinition = "Zzamt")
    private String zzamt;

    /**
     *  孵化资金金额（万元）
     */
    @ApiModelProperty("孵化资金金额（万元）")
    @TableField("Ffamt")
    @Column(columnDefinition = "Ffamt")
    private String ffamt;

    /**
     *  可自主支配场地内在孵企业数量
     */
    @ApiModelProperty("可自主支配场地内在孵企业数量")
    @TableField("ZfOrgnum")
    @Column(columnDefinition = "ZfOrgnum")
    @FieldDes(name = "zforgnum", lenth = "255", type = "String", memo = "可自主支配场地内在孵企业数量")
    private String zforgnum;

    /**
     *  累计毕业企业数量
     */
    @ApiModelProperty("累计毕业企业数量")
    @TableField("Byorgnum")
    @Column(columnDefinition = "Byorgnum")
    @FieldDes(name = "byorgnum", lenth = "255", type = "String", memo = "累计毕业企业数量")
    private String byorgnum;

    /**
     *  入驻创业团队和企业数
     */
    @ApiModelProperty("入驻创业团队和企业数")
    @TableField("orgnum")
    @Column(columnDefinition = "orgnum")
    @FieldDes(name = "orgnum", type = "Long", memo = "入驻创业团队和企业数")
    private Long orgnum;

    /**
     *  依托单位
     */
    @ApiModelProperty("依托单位")
    @TableField("orgname")
    @Column(columnDefinition = "orgname")
    @FieldDes(name = "orgname", lenth = "255", type = "String", memo = "依托单位")
    private String orgname;

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
     *  平台/载体类型其他
     */
    @ApiModelProperty("平台/载体类型其他")
    @TableField("CARRIERTYPEQT")
    @Column(columnDefinition = "CARRIERTYPEQT")
    @FieldDes(name = "carriertypeqt", lenth = "255", type = "String", memo = "平台/载体类型其他")
    private String carriertypeqt;

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
    public String getCarriername (){
        return carriername;
    }
    public void setCarriername (String carriername){
        this.carriername = carriername;
    }

    /**
    *  
    */
    public String getCarrierrank (){
        return carrierrank;
    }
    public void setCarrierrank (String carrierrank){
        this.carrierrank = carrierrank;
    }

    /**
    *  
    */
    public String getBelongfield (){
        return belongfield;
    }
    public void setBelongfield (String belongfield){
        this.belongfield = belongfield;
    }

    /**
    *  
    */
    public String getAddress (){
        return address;
    }
    public void setAddress (String address){
        this.address = address;
    }

    /**
    *  
    */
    public String getLeadername (){
        return leadername;
    }
    public void setLeadername (String leadername){
        this.leadername = leadername;
    }

    /**
    *  
    */
    public String getLeadermobile (){
        return leadermobile;
    }
    public void setLeadermobile (String leadermobile){
        this.leadermobile = leadermobile;
    }

    /**
    *  
    */
    public String getLeaderphone (){
        return leaderphone;
    }
    public void setLeaderphone (String leaderphone){
        this.leaderphone = leaderphone;
    }

    /**
    *  
    */
    public String getLeaderemail (){
        return leaderemail;
    }
    public void setLeaderemail (String leaderemail){
        this.leaderemail = leaderemail;
    }

    /**
    *  
    */
    public String getResearchdirections (){
        return researchdirections;
    }
    public void setResearchdirections (String researchdirections){
        this.researchdirections = researchdirections;
    }

    /**
    *  
    */
    public String getSummary (){
        return summary;
    }
    public void setSummary (String summary){
        this.summary = summary;
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
    public String getAwardunit (){
        return awardunit;
    }
    public void setAwardunit (String awardunit){
        this.awardunit = awardunit;
    }

    /**
    *  
    */
    public Date getRecognizedate (){
        return recognizedate;
    }
    public void setRecognizedate (Date recognizedate){
        this.recognizedate = recognizedate;
    }

    /**
    *  
    */
    public String getSubjectcode (){
        return subjectcode;
    }
    public void setSubjectcode (String subjectcode){
        this.subjectcode = subjectcode;
    }

    /**
    *  
    */
    public String getSubjectname (){
        return subjectname;
    }
    public void setSubjectname (String subjectname){
        this.subjectname = subjectname;
    }

    /**
    *  
    */
    public Integer getAgencymembernum (){
        return agencymembernum;
    }
    public void setAgencymembernum (Integer agencymembernum){
        this.agencymembernum = agencymembernum;
    }

    /**
    *  
    */
    public Integer getEquipmentnum (){
        return equipmentnum;
    }
    public void setEquipmentnum (Integer equipmentnum){
        this.equipmentnum = equipmentnum;
    }

    /**
    *  
    */
    public String getEquipmentprice (){
        return equipmentprice;
    }
    public void setEquipmentprice (String equipmentprice){
        this.equipmentprice = equipmentprice;
    }

    /**
    *  
    */
    public String getCarriertype (){
        return carriertype;
    }
    public void setCarriertype (String carriertype){
        this.carriertype = carriertype;
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
    *  固定资产总值
    */
    public String getGdzczz (){
        return gdzczz;
    }
    public void setGdzczz (String gdzczz){
        this.gdzczz = gdzczz;
    }

    /**
    *  工程试验用房（平方米）
    */
    public String getGcsyyf (){
        return gcsyyf;
    }
    public void setGcsyyf (String gcsyyf){
        this.gcsyyf = gcsyyf;
    }

    /**
    *  办公用房（平方米）
    */
    public String getBgyf (){
        return bgyf;
    }
    public void setBgyf (String bgyf){
        this.bgyf = bgyf;
    }

    /**
    *  仪器设备装备总值
    */
    public String getYqsbzbzz (){
        return yqsbzbzz;
    }
    public void setYqsbzbzz (String yqsbzbzz){
        this.yqsbzbzz = yqsbzbzz;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
    *  开始时间有效期
    */
    public String getStarttime (){
        return starttime;
    }
    public void setStarttime (String starttime){
        this.starttime = starttime;
    }

    /**
    *  结束时间有效期
    */
    public String getEndtime (){
        return endtime;
    }
    public void setEndtime (String endtime){
        this.endtime = endtime;
    }

    /**
    *  依托单位
    */
    public String getOrgname (){
        return orgname;
    }
    public void setOrgname (String orgname){
        this.orgname = orgname;
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
    public String getWqcheck (){
        return wqcheck;
    }
    public void setWqcheck (String wqcheck){
        this.wqcheck = wqcheck;
    }

    public Long getPrpcode() {
        return prpcode;
    }

    public void setPrpcode(Long prpcode) {
        this.prpcode = prpcode;
    }

    public Long getDevelopnum() {
        return developnum;
    }

    public void setDevelopnum(Long developnum) {
        this.developnum = developnum;
    }

    public String getOrgshxyno() {
        return orgshxyno;
    }

    public void setOrgshxyno(String orgshxyno) {
        this.orgshxyno = orgshxyno;
    }

    public String getDqdate() {
        return dqdate;
    }

    public void setDqdate(String dqdate) {
        this.dqdate = dqdate;
    }

    public String getSbdwname() {
        return sbdwname;
    }

    public void setSbdwname(String sbdwname) {
        this.sbdwname = sbdwname;
    }

    public String getSbdwnature() {
        return sbdwnature;
    }

    public void setSbdwnature(String sbdwnature) {
        this.sbdwnature = sbdwnature;
    }

    public String getSbdwareaname() {
        return sbdwareaname;
    }

    public void setSbdwareaname(String sbdwareaname) {
        this.sbdwareaname = sbdwareaname;
    }

    public String getWorkerstotal() {
        return workerstotal;
    }

    public void setWorkerstotal(String workerstotal) {
        this.workerstotal = workerstotal;
    }

    public Long getRdpsnnum() {
        return rdpsnnum;
    }

    public void setRdpsnnum(Long rdpsnnum) {
        this.rdpsnnum = rdpsnnum;
    }

    public String getAllyearoutput() {
        return allyearoutput;
    }

    public void setAllyearoutput(String allyearoutput) {
        this.allyearoutput = allyearoutput;
    }

    public String getNewproductoutput() {
        return newproductoutput;
    }

    public void setNewproductoutput(String newproductoutput) {
        this.newproductoutput = newproductoutput;
    }

    public String getSalesvalue() {
        return salesvalue;
    }

    public void setSalesvalue(String salesvalue) {
        this.salesvalue = salesvalue;
    }

    public String getNewproductsalesvalue() {
        return newproductsalesvalue;
    }

    public void setNewproductsalesvalue(String newproductsalesvalue) {
        this.newproductsalesvalue = newproductsalesvalue;
    }

    public String getGjsfwvalue() {
        return gjsfwvalue;
    }

    public void setGjsfwvalue(String gjsfwvalue) {
        this.gjsfwvalue = gjsfwvalue;
    }

    public String getExportvalue() {
        return exportvalue;
    }

    public void setExportvalue(String exportvalue) {
        this.exportvalue = exportvalue;
    }

    public String getNewproductexportvalue() {
        return newproductexportvalue;
    }

    public void setNewproductexportvalue(String newproductexportvalue) {
        this.newproductexportvalue = newproductexportvalue;
    }

    public String getYjkfjftrtotal() {
        return yjkfjftrtotal;
    }

    public void setYjkfjftrtotal(String yjkfjftrtotal) {
        this.yjkfjftrtotal = yjkfjftrtotal;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Long getTakeenterprisegqnum() {
        return takeenterprisegqnum;
    }

    public void setTakeenterprisegqnum(Long takeenterprisegqnum) {
        this.takeenterprisegqnum = takeenterprisegqnum;
    }

    public String getStatyear() {
        return statyear;
    }

    public void setStatyear(String statyear) {
        this.statyear = statyear;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getJgtype() {
        return jgtype;
    }

    public void setJgtype(String jgtype) {
        this.jgtype = jgtype;
    }

    public String getJgnature() {
        return jgnature;
    }

    public void setJgnature(String jgnature) {
        this.jgnature = jgnature;
    }

    public String getDevloptype() {
        return devloptype;
    }

    public void setDevloptype(String devloptype) {
        this.devloptype = devloptype;
    }

    public String getWyinstrumentprice() {
        return wyinstrumentprice;
    }

    public void setWyinstrumentprice(String wyinstrumentprice) {
        this.wyinstrumentprice = wyinstrumentprice;
    }

    public Long getNationalplatform() {
        return nationalplatform;
    }

    public void setNationalplatform(Long nationalplatform) {
        this.nationalplatform = nationalplatform;
    }

    public Long getCzzjprpnum() {
        return czzjprpnum;
    }

    public void setCzzjprpnum(Long czzjprpnum) {
        this.czzjprpnum = czzjprpnum;
    }

    public String getCzzjallamt() {
        return czzjallamt;
    }

    public void setCzzjallamt(String czzjallamt) {
        this.czzjallamt = czzjallamt;
    }

    public Long getPatentallnum() {
        return patentallnum;
    }

    public void setPatentallnum(Long patentallnum) {
        this.patentallnum = patentallnum;
    }

    public Long getInventpatentnum() {
        return inventpatentnum;
    }

    public void setInventpatentnum(Long inventpatentnum) {
        this.inventpatentnum = inventpatentnum;
    }

    public Long getPctpatentnum() {
        return pctpatentnum;
    }

    public void setPctpatentnum(Long pctpatentnum) {
        this.pctpatentnum = pctpatentnum;
    }

    public Long getValidinventpatentnum() {
        return validinventpatentnum;
    }

    public void setValidinventpatentnum(Long validinventpatentnum) {
        this.validinventpatentnum = validinventpatentnum;
    }

    public Long getQtzdbznum() {
        return qtzdbznum;
    }

    public void setQtzdbznum(Long qtzdbznum) {
        this.qtzdbznum = qtzdbznum;
    }

    public Long getCyzdbznum() {
        return cyzdbznum;
    }

    public void setCyzdbznum(Long cyzdbznum) {
        this.cyzdbznum = cyzdbznum;
    }

    public Long getKjjlnum() {
        return kjjlnum;
    }

    public void setKjjlnum(Long kjjlnum) {
        this.kjjlnum = kjjlnum;
    }

    public Long getJsnxcpslnum() {
        return jsnxcpslnum;
    }

    public void setJsnxcpslnum(Long jsnxcpslnum) {
        this.jsnxcpslnum = jsnxcpslnum;
    }

    public String getSnjgallincome() {
        return snjgallincome;
    }

    public void setSnjgallincome(String snjgallincome) {
        this.snjgallincome = snjgallincome;
    }

    public String getSnjgallexpend() {
        return snjgallexpend;
    }

    public void setSnjgallexpend(String snjgallexpend) {
        this.snjgallexpend = snjgallexpend;
    }

    public String getKjcgzhincome() {
        return kjcgzhincome;
    }

    public void setKjcgzhincome(String kjcgzhincome) {
        this.kjcgzhincome = kjcgzhincome;
    }

    public String getRdexpend() {
        return rdexpend;
    }

    public void setRdexpend(String rdexpend) {
        this.rdexpend = rdexpend;
    }

    public String getTechnologyincome() {
        return technologyincome;
    }

    public void setTechnologyincome(String technologyincome) {
        this.technologyincome = technologyincome;
    }

    public String getJsncgzhincome() {
        return jsncgzhincome;
    }

    public void setJsncgzhincome(String jsncgzhincome) {
        this.jsncgzhincome = jsncgzhincome;
    }

    public Long getTakeenterprisenum() {
        return takeenterprisenum;
    }

    public void setTakeenterprisenum(Long takeenterprisenum) {
        this.takeenterprisenum = takeenterprisenum;
    }

    public String getEnterpriseincome() {
        return enterpriseincome;
    }

    public void setEnterpriseincome(String enterpriseincome) {
        this.enterpriseincome = enterpriseincome;
    }

    public Long getIncubateenterprisenum() {
        return incubateenterprisenum;
    }

    public void setIncubateenterprisenum(Long incubateenterprisenum) {
        this.incubateenterprisenum = incubateenterprisenum;
    }

    public String getIncubateenterpriseincome() {
        return incubateenterpriseincome;
    }

    public void setIncubateenterpriseincome(String incubateenterpriseincome) {
        this.incubateenterpriseincome = incubateenterpriseincome;
    }


    public Long getIncuenterprisegqnum() {
        return incuenterprisegqnum;
    }

    public void setIncuenterprisegqnum(Long incuenterprisegqnum) {
        this.incuenterprisegqnum = incuenterprisegqnum;
    }

    public Long getServiceenterprisenum() {
        return serviceenterprisenum;
    }

    public void setServiceenterprisenum(Long serviceenterprisenum) {
        this.serviceenterprisenum = serviceenterprisenum;
    }

    public String getIsqtslcycxlm() {
        return isqtslcycxlm;
    }

    public void setIsqtslcycxlm(String isqtslcycxlm) {
        this.isqtslcycxlm = isqtslcycxlm;
    }

    public String getIsqtclhyxh() {
        return isqtclhyxh;
    }

    public void setIsqtclhyxh(String isqtclhyxh) {
        this.isqtclhyxh = isqtclhyxh;
    }

    public String getScientificplace() {
        return scientificplace;
    }

    public void setScientificplace(String scientificplace) {
        this.scientificplace = scientificplace;
    }

    public String getSystype() {
        return systype;
    }

    public void setSystype(String systype) {
        this.systype = systype;
    }

    public String getValiddate() {
        return validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate;
    }

    public String getTechname() {
        return techname;
    }

    public void setTechname(String techname) {
        this.techname = techname;
    }

    public String getIndustryname() {
        return industryname;
    }

    public void setIndustryname(String industryname) {
        this.industryname = industryname;
    }

    public String getIsanimalname() {
        return isanimalname;
    }

    public void setIsanimalname(String isanimalname) {
        this.isanimalname = isanimalname;
    }

    public String getProvinceamt() {
        return provinceamt;
    }

    public void setProvinceamt(String provinceamt) {
        this.provinceamt = provinceamt;
    }

    public String getKyinstrumentprice() {
        return kyinstrumentprice;
    }

    public void setKyinstrumentprice(String kyinstrumentprice) {
        this.kyinstrumentprice = kyinstrumentprice;
    }

    public String getCyorgnames() {
        return cyorgnames;
    }

    public void setCyorgnames(String cyorgnames) {
        this.cyorgnames = cyorgnames;
    }

    public String getFzrcardcode() {
        return fzrcardcode;
    }

    public void setFzrcardcode(String fzrcardcode) {
        this.fzrcardcode = fzrcardcode;
    }

    public String getSscityname() {
        return sscityname;
    }

    public void setSscityname(String sscityname) {
        this.sscityname = sscityname;
    }

    public String getYyztorgname() {
        return yyztorgname;
    }

    public void setYyztorgname(String yyztorgname) {
        this.yyztorgname = yyztorgname;
    }

    public String getParentorgname() {
        return parentorgname;
    }

    public void setParentorgname(String parentorgname) {
        this.parentorgname = parentorgname;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getZzamt() {
        return zzamt;
    }

    public void setZzamt(String zzamt) {
        this.zzamt = zzamt;
    }

    public String getFfamt() {
        return ffamt;
    }

    public void setFfamt(String ffamt) {
        this.ffamt = ffamt;
    }

    public String getZforgnum() {
        return zforgnum;
    }

    public void setZforgnum(String zforgnum) {
        this.zforgnum = zforgnum;
    }

    public String getByorgnum() {
        return byorgnum;
    }

    public void setByorgnum(String byorgnum) {
        this.byorgnum = byorgnum;
    }

    public Long getOrgnum() {
        return orgnum;
    }

    public void setOrgnum(Long orgnum) {
        this.orgnum = orgnum;
    }

    public String getCarriertypeqt() {
        return carriertypeqt;
    }

    public void setCarriertypeqt(String carriertypeqt) {
        this.carriertypeqt = carriertypeqt;
    }
}