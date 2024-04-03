/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-4 15:56:31
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
 *  数据仓-财务情况表
 */
@Entity
@Table(name = "BI_ENT_FSS")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiEntFss {

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "")
    private String id;

    /**
     *  年份
     */
    @ApiModelProperty("年份")
    @TableField("YEAR")
    @Column(columnDefinition = "YEAR")
    @FieldDes(name = "year", lenth = "255", type = "String", memo = "年份")
    private String year;

    /**
     *  主营业务收入(万元)
     */
    @ApiModelProperty("主营业务收入(万元)")
    @TableField("MAINBUSINESSINCOME")
    @Column(columnDefinition = "MAINBUSINESSINCOME")
    private String mainbusinessincome;

    /**
     *  营业收入(万元)
     */
    @ApiModelProperty("营业收入(万元)")
    @TableField("BUSINESSINCOME")
    @Column(columnDefinition = "BUSINESSINCOME")
    private String businessincome;

    /**
     *  研发投入(万元)
     */
    @ApiModelProperty("研发投入(万元)")
    @TableField("RDFUND")
    @Column(columnDefinition = "RDFUND")
    private String rdfund;

    /**
     *  研发投入占销售收入比重(%)
     */
    @ApiModelProperty("研发投入占销售收入比重(%)")
    @TableField("RDFUNDPERCENT")
    @Column(columnDefinition = "RDFUNDPERCENT")
    private String rdfundpercent;

    /**
     *  利润总额(万元)
     */
    @ApiModelProperty("利润总额(万元)")
    @TableField("TOTALPROFIT")
    @Column(columnDefinition = "TOTALPROFIT")
    private String totalprofit;

    /**
     *  资产负债率(%)
     */
    @ApiModelProperty("资产负债率(%)")
    @TableField("ASSETSLIABILITYRATIO")
    @Column(columnDefinition = "ASSETSLIABILITYRATIO")
    private String assetsliabilityratio;

    /**
     *  总收入(万元)
     */
    @ApiModelProperty("总收入(万元)")
    @TableField("TOTALREVENUE")
    @Column(columnDefinition = "TOTALREVENUE")
    private String totalrevenue;

    /**
     *  负债总额(万元)
     */
    @ApiModelProperty("负债总额(万元)")
    @TableField("TOTALLIABILITY")
    @Column(columnDefinition = "TOTALLIABILITY")
    private String totalliability;

    /**
     *  所有者权益(万元)
     */
    @ApiModelProperty("所有者权益(万元)")
    @TableField("OWNEREQUITY")
    @Column(columnDefinition = "OWNEREQUITY")
    private String ownerequity;

    /**
     *  专项经费审计报备号
     */
    @ApiModelProperty("专项经费审计报备号")
    @TableField("AUDITREPORTNO")
    @Column(columnDefinition = "AUDITREPORTNO")
    @FieldDes(name = "auditreportno", lenth = "255", type = "String", memo = "专项经费审计报备号")
    private String auditreportno;

    /**
     *  类型
     */
    @ApiModelProperty("类型")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "类型")
    private String type;

    /**
     *  主表关联ID
     */
    @ApiModelProperty("主表关联ID")
    @TableField("MAINID")
    @Column(columnDefinition = "MAINID")
    @FieldDes(name = "mainid", lenth = "255", type = "String", memo = "主表关联ID")
    private String mainid;

    /**
     *  父表关联ID
     */
    @ApiModelProperty("父表关联ID")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    @FieldDes(name = "sourceid", lenth = "255", type = "String", memo = "父表关联ID")
    private String sourceid;

    /**
     *  排序
     */
    @ApiModelProperty("排序")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "排序")
    private Integer seq;

    /**
     *  报告日期
     */
    @ApiModelProperty("报告日期")
    @TableField("REPORTDATE")
    @Column(columnDefinition = "REPORTDATE")
    @FieldDes(name = "reportdate", type = "Date", memo = "报告日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date reportdate;

    /**
     *  事务所统一社会信用代码
     */
    @ApiModelProperty("事务所统一社会信用代码")
    @TableField("CREDITCODE")
    @Column(columnDefinition = "CREDITCODE")
    @FieldDes(name = "creditcode", lenth = "255", type = "String", memo = "事务所统一社会信用代码")
    private String creditcode;

    /**
     *  事务所名称
     */
    @ApiModelProperty("事务所名称")
    @TableField("OFFICENAME")
    @Column(columnDefinition = "OFFICENAME")
    @FieldDes(name = "officename", lenth = "255", type = "String", memo = "事务所名称")
    private String officename;

    /**
     *  总资产(万元)
     */
    @ApiModelProperty("总资产(万元)")
    @TableField("TOTALASSETS")
    @Column(columnDefinition = "TOTALASSETS")
    private String totalassets;

    /**
     *  经营活动现金净流量
     */
    @ApiModelProperty("经营活动现金净流量")
    @TableField("CASHBUSINESS")
    @Column(columnDefinition = "CASHBUSINESS")
    private String cashbusiness;

    /**
     *  投资活动现金净流量
     */
    @ApiModelProperty("投资活动现金净流量")
    @TableField("CASHINVESTMENT")
    @Column(columnDefinition = "CASHINVESTMENT")
    private String cashinvestment;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ZKXBJ")
    @Column(columnDefinition = "ZKXBJ")
    @FieldDes(name = "zkxbj", lenth = "50", type = "String", memo = "")
    private String zkxbj;

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
     *  投资企业数量
     */
    @ApiModelProperty("投资企业数量")
    @TableField("TZQYNUM")
    @Column(columnDefinition = "TZQYNUM")
    private Integer tzqynum;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ZKXBJVALUE")
    @Column(columnDefinition = "ZKXBJVALUE")
    @FieldDes(name = "zkxbjvalue", lenth = "255", type = "String", memo = "")
    private String zkxbjvalue;

    /**
     *  销售收入（万元）
     */
    @ApiModelProperty("销售收入（万元）")
    @TableField("SALESINCOME")
    @Column(columnDefinition = "SALESINCOME")
    private String salesincome;

    /**
     *  净资产（万元）
     */
    @ApiModelProperty("净资产（万元）")
    @TableField("NETASSETS")
    @Column(columnDefinition = "NETASSETS")
    private String netassets;

    /**
     *  出口创汇
     */
    @ApiModelProperty("出口创汇")
    @TableField("EARNFOREIGNEXCHANGE")
    @Column(columnDefinition = "EARNFOREIGNEXCHANGE")
    private String earnforeignexchange;

    /**
     *  实缴税金
     */
    @ApiModelProperty("实缴税金")
    @TableField("PAIDTAX")
    @Column(columnDefinition = "PAIDTAX")
    private String paidtax;

    /**
     *  净利润增长率
     */
    @ApiModelProperty("净利润增长率")
    @TableField("GROWNETPROFIT")
    @Column(columnDefinition = "GROWNETPROFIT")
    private String grownetprofit;

    /**
     *  流动比率
     */
    @ApiModelProperty("流动比率")
    @TableField("FLOWRATE")
    @Column(columnDefinition = "FLOWRATE")
    private String flowrate;


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
    *  年份
    */
    public String getYear (){
        return year;
    }
    public void setYear (String year){
        this.year = year;
    }

    /**
    *  主营业务收入(万元)
    */
    public String getMainbusinessincome (){
        return mainbusinessincome;
    }
    public void setMainbusinessincome (String mainbusinessincome){
        this.mainbusinessincome = mainbusinessincome;
    }

    /**
    *  营业收入(万元)
    */
    public String getBusinessincome (){
        return businessincome;
    }
    public void setBusinessincome (String businessincome){
        this.businessincome = businessincome;
    }

    /**
    *  研发投入(万元)
    */
    public String getRdfund (){
        return rdfund;
    }
    public void setRdfund (String rdfund){
        this.rdfund = rdfund;
    }

    /**
    *  研发投入占销售收入比重(%)
    */
    public String getRdfundpercent (){
        return rdfundpercent;
    }
    public void setRdfundpercent (String rdfundpercent){
        this.rdfundpercent = rdfundpercent;
    }

    /**
    *  利润总额(万元)
    */
    public String getTotalprofit (){
        return totalprofit;
    }
    public void setTotalprofit (String totalprofit){
        this.totalprofit = totalprofit;
    }

    /**
    *  资产负债率(%)
    */
    public String getAssetsliabilityratio (){
        return assetsliabilityratio;
    }
    public void setAssetsliabilityratio (String assetsliabilityratio){
        this.assetsliabilityratio = assetsliabilityratio;
    }

    /**
    *  总收入(万元)
    */
    public String getTotalrevenue (){
        return totalrevenue;
    }
    public void setTotalrevenue (String totalrevenue){
        this.totalrevenue = totalrevenue;
    }

    /**
    *  负债总额(万元)
    */
    public String getTotalliability (){
        return totalliability;
    }
    public void setTotalliability (String totalliability){
        this.totalliability = totalliability;
    }

    /**
    *  所有者权益(万元)
    */
    public String getOwnerequity (){
        return ownerequity;
    }
    public void setOwnerequity (String ownerequity){
        this.ownerequity = ownerequity;
    }

    /**
    *  专项经费审计报备号
    */
    public String getAuditreportno (){
        return auditreportno;
    }
    public void setAuditreportno (String auditreportno){
        this.auditreportno = auditreportno;
    }

    /**
    *  类型
    */
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
    }

    /**
    *  主表关联ID
    */
    public String getMainid (){
        return mainid;
    }
    public void setMainid (String mainid){
        this.mainid = mainid;
    }

    /**
    *  父表关联ID
    */
    public String getSourceid (){
        return sourceid;
    }
    public void setSourceid (String sourceid){
        this.sourceid = sourceid;
    }

    /**
    *  排序
    */
    public Integer getSeq (){
        return seq;
    }
    public void setSeq (Integer seq){
        this.seq = seq;
    }

    /**
    *  报告日期
    */
    public Date getReportdate (){
        return reportdate;
    }
    public void setReportdate (Date reportdate){
        this.reportdate = reportdate;
    }

    /**
    *  事务所统一社会信用代码
    */
    public String getCreditcode (){
        return creditcode;
    }
    public void setCreditcode (String creditcode){
        this.creditcode = creditcode;
    }

    /**
    *  事务所名称
    */
    public String getOfficename (){
        return officename;
    }
    public void setOfficename (String officename){
        this.officename = officename;
    }

    /**
    *  总资产(万元)
    */
    public String getTotalassets (){
        return totalassets;
    }
    public void setTotalassets (String totalassets){
        this.totalassets = totalassets;
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
    *  投资企业数量
    */
    public Integer getTzqynum (){
        return tzqynum;
    }
    public void setTzqynum (Integer tzqynum){
        this.tzqynum = tzqynum;
    }

    public String getCashbusiness() {
        return cashbusiness;
    }

    public void setCashbusiness(String cashbusiness) {
        this.cashbusiness = cashbusiness;
    }

    public String getCashinvestment() {
        return cashinvestment;
    }

    public void setCashinvestment(String cashinvestment) {
        this.cashinvestment = cashinvestment;
    }

    public String getZkxbjvalue() {
        return zkxbjvalue;
    }

    public void setZkxbjvalue(String zkxbjvalue) {
        this.zkxbjvalue = zkxbjvalue;
    }

    /**
    *  销售收入（万元）
    */
    public String getSalesincome (){
        return salesincome;
    }
    public void setSalesincome (String salesincome){
        this.salesincome = salesincome;
    }

    /**
    *  净资产（万元）
    */
    public String getNetassets (){
        return netassets;
    }
    public void setNetassets (String netassets){
        this.netassets = netassets;
    }

    /**
    *  出口创汇
    */
    public String getEarnforeignexchange (){
        return earnforeignexchange;
    }
    public void setEarnforeignexchange (String earnforeignexchange){
        this.earnforeignexchange = earnforeignexchange;
    }

    /**
    *  实缴税金
    */
    public String getPaidtax (){
        return paidtax;
    }
    public void setPaidtax (String paidtax){
        this.paidtax = paidtax;
    }

    /**
    *  净利润增长率
    */
    public String getGrownetprofit (){
        return grownetprofit;
    }
    public void setGrownetprofit (String grownetprofit){
        this.grownetprofit = grownetprofit;
    }

    /**
    *  流动比率
    */
    public String getFlowrate (){
        return flowrate;
    }
    public void setFlowrate (String flowrate){
        this.flowrate = flowrate;
    }

}