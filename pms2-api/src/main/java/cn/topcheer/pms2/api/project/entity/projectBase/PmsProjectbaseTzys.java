/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024年1月6日 上午11:07:52
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  计划项目-投资预算
 */
@Entity
@Table(name = "PMS_PROJECTBASE_TZYS")
@ClassInfo(name = "计划项目-投资预算", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseTzys {

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
     *  计划投资总额
     */
    @ApiModelProperty("计划投资总额")
    @TableField("PLAN_INVEST")
    @Column(columnDefinition = "PLAN_INVEST")
    @FieldDes(name = "plan_invest", type = "BigDecimal", memo = "计划投资总额")
    private BigDecimal planInvest;

    /**
     *  计划投资总额_单位
     */
    @ApiModelProperty("计划投资总额_单位")
    @TableField("PLAN_INVEST_COMPANY")
    @Column(columnDefinition = "PLAN_INVEST_COMPANY")
    @FieldDes(name = "plan_invest_company", type = "BigDecimal", memo = "计划投资总额_单位")
    private BigDecimal planInvestCompany;

    /**
     *  已完成投资
     */
    @ApiModelProperty("已完成投资")
    @TableField("DONE_INVEST")
    @Column(columnDefinition = "DONE_INVEST")
    @FieldDes(name = "done_invest", type = "BigDecimal", memo = "已完成投资")
    private BigDecimal doneInvest;

    /**
     *  已完成投资_自筹
     */
    @ApiModelProperty("已完成投资_自筹")
    @TableField("DONE_INVEST_SELF")
    @Column(columnDefinition = "DONE_INVEST_SELF")
    @FieldDes(name = "done_invest_self", type = "BigDecimal", memo = "已完成投资_自筹")
    private BigDecimal doneInvestSelf;

    /**
     *  计划新增投资
     */
    @ApiModelProperty("计划新增投资")
    @TableField("PLAN_NEW_INVEST")
    @Column(columnDefinition = "PLAN_NEW_INVEST")
    @FieldDes(name = "plan_new_invest", type = "BigDecimal", memo = "计划新增投资")
    private BigDecimal planNewInvest;

    /**
     *  计划新增投资_科技经费
     */
    @ApiModelProperty("计划新增投资_科技经费")
    @TableField("PLAN_NEW_INVEST_TECH")
    @Column(columnDefinition = "PLAN_NEW_INVEST_TECH")
    @FieldDes(name = "plan_new_invest_tech", type = "BigDecimal", memo = "计划新增投资_科技经费")
    private BigDecimal planNewInvestTech;

    /**
     *  计划新增投资_自筹
     */
    @ApiModelProperty("计划新增投资_自筹")
    @TableField("PLAN_NEW_INVEST_SELF")
    @Column(columnDefinition = "PLAN_NEW_INVEST_SELF")
    @FieldDes(name = "plan_new_invest_self", type = "BigDecimal", memo = "计划新增投资_自筹")
    private BigDecimal planNewInvestSelf;

    /**
     *  计划新增投资_其他拨款
     */
    @ApiModelProperty("计划新增投资_其他拨款")
    @TableField("PLAN_NEW_INVEST_OTHER")
    @Column(columnDefinition = "PLAN_NEW_INVEST_OTHER")
    @FieldDes(name = "plan_new_invest_other", type = "BigDecimal", memo = "计划新增投资_其他拨款")
    private BigDecimal planNewInvestOther;

    /**
     *  预算科目名称
     */
    @ApiModelProperty("预算科目名称")
    @TableField("BUDGET_ACCOUNT_NAME")
    @Column(columnDefinition = "BUDGET_ACCOUNT_NAME")
    @FieldDes(name = "budget_account_name", type = "String", memo = "预算科目名称")
    private String budgetAccountName;

    /**
     *  通用名称
     */
    @ApiModelProperty("通用名称")
    @TableField("NAME")
    @Column(columnDefinition = "NAME")
    @FieldDes(name = "name", type = "String", memo = "通用名称")
    private String name;

    /**
     *  编号
     */
    @ApiModelProperty("编号")
    @TableField("NO")
    @Column(columnDefinition = "NO")
    private String no;

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
    *  计划投资总额
    */
    public BigDecimal getPlanInvest (){
        return planInvest;
    }
    public void setPlanInvest (BigDecimal planInvest){
        this.planInvest = planInvest;
    }

    /**
    *  计划投资总额_单位
    */
    public BigDecimal getPlanInvestCompany (){
        return planInvestCompany;
    }
    public void setPlanInvestCompany (BigDecimal planInvestCompany){
        this.planInvestCompany = planInvestCompany;
    }

    /**
    *  已完成投资
    */
    public BigDecimal getDoneInvest (){
        return doneInvest;
    }
    public void setDoneInvest (BigDecimal doneInvest){
        this.doneInvest = doneInvest;
    }

    /**
    *  已完成投资_自筹
    */
    public BigDecimal getDoneInvestSelf (){
        return doneInvestSelf;
    }
    public void setDoneInvestSelf (BigDecimal doneInvestSelf){
        this.doneInvestSelf = doneInvestSelf;
    }

    /**
    *  计划新增投资
    */
    public BigDecimal getPlanNewInvest (){
        return planNewInvest;
    }
    public void setPlanNewInvest (BigDecimal planNewInvest){
        this.planNewInvest = planNewInvest;
    }

    /**
    *  计划新增投资_科技经费
    */
    public BigDecimal getPlanNewInvestTech (){
        return planNewInvestTech;
    }
    public void setPlanNewInvestTech (BigDecimal planNewInvestTech){
        this.planNewInvestTech = planNewInvestTech;
    }

    /**
    *  计划新增投资_自筹
    */
    public BigDecimal getPlanNewInvestSelf (){
        return planNewInvestSelf;
    }
    public void setPlanNewInvestSelf (BigDecimal planNewInvestSelf){
        this.planNewInvestSelf = planNewInvestSelf;
    }

    /**
    *  计划新增投资_其他拨款
    */
    public BigDecimal getPlanNewInvestOther (){
        return planNewInvestOther;
    }
    public void setPlanNewInvestOther (BigDecimal planNewInvestOther){
        this.planNewInvestOther = planNewInvestOther;
    }

    public String getBudgetAccountName() {
        return budgetAccountName;
    }

    public void setBudgetAccountName(String budgetAccountName) {
        this.budgetAccountName = budgetAccountName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}