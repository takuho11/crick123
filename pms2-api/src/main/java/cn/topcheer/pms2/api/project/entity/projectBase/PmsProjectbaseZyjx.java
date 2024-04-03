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
 *  计划项目-主要绩效
 */
@Entity
@Table(name = "PMS_PROJECTBASE_ZYJX")
@ClassInfo(name = "计划项目-主要绩效", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseZyjx {

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
     *  论文收录检索情况（含SCI、SCIE/SSCI、EI、北大中文核心期刊、CSSCI、CSTPCD论文）
     */
    @ApiModelProperty("论文收录检索情况（含SCI、SCIE/SSCI、EI、北大中文核心期刊、CSSCI、CSTPCD论文）")
    @TableField("RETRIEVAL_NUM")
    @Column(columnDefinition = "RETRIEVAL_NUM")
    @FieldDes(name = "retrieval_num", type = "Integer", memo = "论文收录检索情况（含SCI、SCIE/SSCI、EI、北大中文核心期刊、CSSCI、CSTPCD论文）")
    private Integer retrievalNum;

    /**
     *  专利申请（受理）数量
     */
    @ApiModelProperty("专利申请（受理）数量")
    @TableField("PATENT_NUM")
    @Column(columnDefinition = "PATENT_NUM")
    @FieldDes(name = "patent_num", type = "Integer", memo = "专利申请（受理）数量")
    private Integer patentNum;

    /**
     *  硕士以上人员培养数量
     */
    @ApiModelProperty("硕士以上人员培养数量")
    @TableField("TRAIN_NUM")
    @Column(columnDefinition = "TRAIN_NUM")
    @FieldDes(name = "train_num", type = "Integer", memo = "硕士以上人员培养数量")
    private Integer trainNum;

    /**
     *  新增设备原值
     */
    @ApiModelProperty("新增设备原值")
    @TableField("DEVICE_WORTH")
    @Column(columnDefinition = "DEVICE_WORTH")
    @FieldDes(name = "device_worth", type = "BigDecimal", memo = "新增设备原值")
    private BigDecimal deviceWorth;

    /**
     *  转化科技成果数量
     */
    @ApiModelProperty("转化科技成果数量")
    @TableField("TRANS_NUM")
    @Column(columnDefinition = "TRANS_NUM")
    @FieldDes(name = "trans_num", type = "Integer", memo = "转化科技成果数量")
    private Integer transNum;

    /**
     *  支持高新技术企业数量
     */
    @ApiModelProperty("支持高新技术企业数量")
    @TableField("GXJSQY_NUM")
    @Column(columnDefinition = "GXJSQY_NUM")
    @FieldDes(name = "gxjsqy_num", type = "Integer", memo = "支持高新技术企业数量")
    private Integer gxjsqyNum;

    /**
     *  支持科技型中小企业数量
     */
    @ApiModelProperty("支持科技型中小企业数量")
    @TableField("KJXZXQY_NUM")
    @Column(columnDefinition = "KJXZXQY_NUM")
    @FieldDes(name = "kjxzxqy_num", type = "Integer", memo = "支持科技型中小企业数量")
    private Integer kjxzxqyNum;

    /**
     *  新增在孵企业数量
     */
    @ApiModelProperty("新增在孵企业数量")
    @TableField("ZFQY_NUM")
    @Column(columnDefinition = "ZFQY_NUM")
    @FieldDes(name = "zfqy_num", type = "Integer", memo = "新增在孵企业数量")
    private Integer zfqyNum;

    /**
     *  促进技术合同成交额
     */
    @ApiModelProperty("促进技术合同成交额")
    @TableField("JSHTCJ_AMOUNT")
    @Column(columnDefinition = "JSHTCJ_AMOUNT")
    @FieldDes(name = "jshtcj_amount", type = "BigDecimal", memo = "促进技术合同成交额")
    private BigDecimal jshtcjAmount;

    /**
     *  促进科技投融资金额
     */
    @ApiModelProperty("促进科技投融资金额")
    @TableField("KJTRZ_AMOUNT")
    @Column(columnDefinition = "KJTRZ_AMOUNT")
    @FieldDes(name = "kjtrz_amount", type = "BigDecimal", memo = "促进科技投融资金额")
    private BigDecimal kjtrzAmount;

    /**
     *  带动地方投入东西科技合作及区域协同创新资金
     */
    @ApiModelProperty("带动地方投入东西科技合作及区域协同创新资金")
    @TableField("KJHZCXZJ_AMOUNT")
    @Column(columnDefinition = "KJHZCXZJ_AMOUNT")
    @FieldDes(name = "kjhzcxzj_amount", type = "BigDecimal", memo = "带动地方投入东西科技合作及区域协同创新资金")
    private BigDecimal kjhzcxzjAmount;

    /**
     *  培训从事技术创新服务人员数量
     */
    @ApiModelProperty("培训从事技术创新服务人员数量")
    @TableField("JSCXFWRY_NUM")
    @Column(columnDefinition = "JSCXFWRY_NUM")
    @FieldDes(name = "jscxfwry_num", type = "Integer", memo = "培训从事技术创新服务人员数量")
    private Integer jscxfwryNum;

    /**
     *  提供技术咨询/技术服务数量
     */
    @ApiModelProperty("提供技术咨询/技术服务数量")
    @TableField("JSZXFW_NUM")
    @Column(columnDefinition = "JSZXFW_NUM")
    @FieldDes(name = "jszxfw_num", type = "Integer", memo = "提供技术咨询/技术服务数量")
    private Integer jszxfwNum;

    /**
     *  培训技术经纪人数量
     */
    @ApiModelProperty("培训技术经纪人数量")
    @TableField("JSJJR_NUM")
    @Column(columnDefinition = "JSJJR_NUM")
    @FieldDes(name = "jsjjr_num", type = "Integer", memo = "培训技术经纪人数量")
    private Integer jsjjrNum;

    /**
     *  开展创业辅导活动数量
     */
    @ApiModelProperty("开展创业辅导活动数量")
    @TableField("CYFDHD_NUM")
    @Column(columnDefinition = "CYFDHD_NUM")
    @FieldDes(name = "cyfdhd_num", type = "Integer", memo = "开展创业辅导活动数量")
    private Integer cyfdhdNum;

    /**
     *  被服务对象满意度
     */
    @ApiModelProperty("被服务对象满意度")
    @TableField("SATISFACTION")
    @Column(columnDefinition = "SATISFACTION")
    @FieldDes(name = "satisfaction", type = "BigDecimal", memo = "被服务对象满意度")
    private BigDecimal satisfaction;


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
    *  论文收录检索情况（含SCI、SCIE/SSCI、EI、北大中文核心期刊、CSSCI、CSTPCD论文）
    */
    public Integer getRetrievalNum (){
        return retrievalNum;
    }
    public void setRetrievalNum (Integer retrievalNum){
        this.retrievalNum = retrievalNum;
    }

    /**
    *  专利申请（受理）数量
    */
    public Integer getPatentNum (){
        return patentNum;
    }
    public void setPatentNum (Integer patentNum){
        this.patentNum = patentNum;
    }

    /**
    *  硕士以上人员培养数量
    */
    public Integer getTrainNum (){
        return trainNum;
    }
    public void setTrainNum (Integer trainNum){
        this.trainNum = trainNum;
    }

    /**
    *  新增设备原值
    */
    public BigDecimal getDeviceWorth (){
        return deviceWorth;
    }
    public void setDeviceWorth (BigDecimal deviceWorth){
        this.deviceWorth = deviceWorth;
    }

    /**
    *  转化科技成果数量
    */
    public Integer getTransNum (){
        return transNum;
    }
    public void setTransNum (Integer transNum){
        this.transNum = transNum;
    }

    /**
    *  支持高新技术企业数量
    */
    public Integer getGxjsqyNum (){
        return gxjsqyNum;
    }
    public void setGxjsqyNum (Integer gxjsqyNum){
        this.gxjsqyNum = gxjsqyNum;
    }

    /**
    *  支持科技型中小企业数量
    */
    public Integer getKjxzxqyNum (){
        return kjxzxqyNum;
    }
    public void setKjxzxqyNum (Integer kjxzxqyNum){
        this.kjxzxqyNum = kjxzxqyNum;
    }

    /**
    *  新增在孵企业数量
    */
    public Integer getZfqyNum (){
        return zfqyNum;
    }
    public void setZfqyNum (Integer zfqyNum){
        this.zfqyNum = zfqyNum;
    }

    /**
    *  促进技术合同成交额
    */
    public BigDecimal getJshtcjAmount (){
        return jshtcjAmount;
    }
    public void setJshtcjAmount (BigDecimal jshtcjAmount){
        this.jshtcjAmount = jshtcjAmount;
    }

    /**
    *  促进科技投融资金额
    */
    public BigDecimal getKjtrzAmount (){
        return kjtrzAmount;
    }
    public void setKjtrzAmount (BigDecimal kjtrzAmount){
        this.kjtrzAmount = kjtrzAmount;
    }

    /**
    *  带动地方投入东西科技合作及区域协同创新资金
    */
    public BigDecimal getKjhzcxzjAmount (){
        return kjhzcxzjAmount;
    }
    public void setKjhzcxzjAmount (BigDecimal kjhzcxzjAmount){
        this.kjhzcxzjAmount = kjhzcxzjAmount;
    }

    /**
    *  培训从事技术创新服务人员数量
    */
    public Integer getJscxfwryNum (){
        return jscxfwryNum;
    }
    public void setJscxfwryNum (Integer jscxfwryNum){
        this.jscxfwryNum = jscxfwryNum;
    }

    /**
    *  提供技术咨询/技术服务数量
    */
    public Integer getJszxfwNum (){
        return jszxfwNum;
    }
    public void setJszxfwNum (Integer jszxfwNum){
        this.jszxfwNum = jszxfwNum;
    }

    /**
    *  培训技术经纪人数量
    */
    public Integer getJsjjrNum (){
        return jsjjrNum;
    }
    public void setJsjjrNum (Integer jsjjrNum){
        this.jsjjrNum = jsjjrNum;
    }

    /**
    *  开展创业辅导活动数量
    */
    public Integer getCyfdhdNum (){
        return cyfdhdNum;
    }
    public void setCyfdhdNum (Integer cyfdhdNum){
        this.cyfdhdNum = cyfdhdNum;
    }

    /**
    *  被服务对象满意度
    */
    public BigDecimal getSatisfaction (){
        return satisfaction;
    }
    public void setSatisfaction (BigDecimal satisfaction){
        this.satisfaction = satisfaction;
    }

}