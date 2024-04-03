/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-1-6 22:48:01
 */
package  cn.topcheer.pms2.api.bi;

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
 *  数据仓-学科表
 */
@Entity
@Table(name = "BI_ENT_SUBJECT")
@ClassInfo(name = "", module = SysModuleEnum.DATAWAREHOUSE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiEntSubject {

    /**
     *  主键ID
     */
    @ApiModelProperty("主键ID")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "256", type = "String", memo = "主键ID")
    private String id;

    /**
     *  学科1级
     */
    @ApiModelProperty("学科1级")
    @TableField("SUBJECTONENAME")
    @Column(columnDefinition = "SUBJECTONENAME")
    @FieldDes(name = "subjectonename", lenth = "100", type = "String", memo = "学科1级")
    private String subjectonename;

    /**
     *  学科1级代码
     */
    @ApiModelProperty("学科1级代码")
    @TableField("SUBJECTONECODE")
    @Column(columnDefinition = "SUBJECTONECODE")
    @FieldDes(name = "subjectonecode", lenth = "100", type = "String", memo = "学科1级代码")
    private String subjectonecode;

    /**
     *  学科2级
     */
    @ApiModelProperty("学科2级")
    @TableField("SUBJECTTWONAME")
    @Column(columnDefinition = "SUBJECTTWONAME")
    @FieldDes(name = "subjecttwoname", lenth = "100", type = "String", memo = "学科2级")
    private String subjecttwoname;

    /**
     *  学科3级
     */
    @ApiModelProperty("学科3级")
    @TableField("SUBJECTTHREENAME")
    @Column(columnDefinition = "SUBJECTTHREENAME")
    @FieldDes(name = "subjectthreename", lenth = "100", type = "String", memo = "学科3级")
    private String subjectthreename;

    /**
     *  学科4级
     */
    @ApiModelProperty("学科4级")
    @TableField("SUBJECTFOURNAME")
    @Column(columnDefinition = "SUBJECTFOURNAME")
    @FieldDes(name = "subjectfourname", lenth = "100", type = "String", memo = "学科4级")
    private String subjectfourname;

    /**
     *  学科最终结果等级
     */
    @ApiModelProperty("学科最终结果等级")
    @TableField("SUBJECTENDNAME")
    @Column(columnDefinition = "SUBJECTENDNAME")
    @FieldDes(name = "subjectendname", lenth = "100", type = "String", memo = "学科最终结果等级")
    private String subjectendname;

    /**
     *  学科2级代码
     */
    @ApiModelProperty("学科2级代码")
    @TableField("SUBJECTTWOCODE")
    @Column(columnDefinition = "SUBJECTTWOCODE")
    @FieldDes(name = "subjecttwocode", lenth = "100", type = "String", memo = "学科2级代码")
    private String subjecttwocode;

    /**
     *  学科3级代码
     */
    @ApiModelProperty("学科3级代码")
    @TableField("SUBJECTTHREECODE")
    @Column(columnDefinition = "SUBJECTTHREECODE")
    @FieldDes(name = "subjectthreecode", lenth = "100", type = "String", memo = "学科3级代码")
    private String subjectthreecode;

    /**
     *  学科4级代码
     */
    @ApiModelProperty("学科4级代码")
    @TableField("SUBJECTFOURCODE")
    @Column(columnDefinition = "SUBJECTFOURCODE")
    @FieldDes(name = "subjectfourcode", lenth = "100", type = "String", memo = "学科4级代码")
    private String subjectfourcode;

    /**
     *  学科最终结果等级代码
     */
    @ApiModelProperty("学科最终结果等级代码")
    @TableField("SUBJECTENDCODE")
    @Column(columnDefinition = "SUBJECTENDCODE")
    @FieldDes(name = "subjectendcode", lenth = "100", type = "String", memo = "学科最终结果等级代码")
    private String subjectendcode;

    /**
     *  学科类别(xm;zj)区别是自然基金的学科还是国标
     */
    @ApiModelProperty("学科类别(xm;zj)区别是自然基金的学科还是国标")
    @TableField("SUBTYPE")
    @Column(columnDefinition = "SUBTYPE")
    @FieldDes(name = "subtype", lenth = "100", type = "String", memo = "学科类别(xm;zj)区别是自然基金的学科还是国标")
    private String subtype;

    /**
     *  传参（参考项目学科建的，不确定什么用）
     */
    @ApiModelProperty("传参（参考项目学科建的，不确定什么用）")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "100", type = "String", memo = "传参（参考项目学科建的，不确定什么用）")
    private String type;

    /**
     *  学科类型
     */
    @ApiModelProperty("学科类型")
    @TableField("SUBJECTTYPE")
    @Column(columnDefinition = "SUBJECTTYPE")
    @FieldDes(name = "subjecttype", lenth = "255", type = "String", memo = "学科类型")
    private String subjecttype;

    /**
     *  父表关联ID
     */
    @ApiModelProperty("父表关联ID")
    @TableField("SOURCEID")
    @Column(columnDefinition = "SOURCEID")
    @FieldDes(name = "sourceid", lenth = "255", type = "String", memo = "父表关联ID")
    private String sourceid;

    /**
     *  主表关联ID
     */
    @ApiModelProperty("主表关联ID")
    @TableField("MAINID")
    @Column(columnDefinition = "MAINID")
    @FieldDes(name = "mainid", lenth = "255", type = "String", memo = "主表关联ID")
    private String mainid;

    /**
     *  排序
     */
    @ApiModelProperty("排序")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "排序")
    private Integer seq;

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
    *  主键ID
    */
    @Id
    public String getId (){
        return id;
    }
    public void setId (String id){
        this.id = id;
    }

    /**
    *  学科1级
    */
    public String getSubjectonename (){
        return subjectonename;
    }
    public void setSubjectonename (String subjectonename){
        this.subjectonename = subjectonename;
    }

    /**
    *  学科1级代码
    */
    public String getSubjectonecode (){
        return subjectonecode;
    }
    public void setSubjectonecode (String subjectonecode){
        this.subjectonecode = subjectonecode;
    }

    /**
    *  学科2级
    */
    public String getSubjecttwoname (){
        return subjecttwoname;
    }
    public void setSubjecttwoname (String subjecttwoname){
        this.subjecttwoname = subjecttwoname;
    }

    /**
    *  学科3级
    */
    public String getSubjectthreename (){
        return subjectthreename;
    }
    public void setSubjectthreename (String subjectthreename){
        this.subjectthreename = subjectthreename;
    }

    /**
    *  学科4级
    */
    public String getSubjectfourname (){
        return subjectfourname;
    }
    public void setSubjectfourname (String subjectfourname){
        this.subjectfourname = subjectfourname;
    }

    /**
    *  学科最终结果等级
    */
    public String getSubjectendname (){
        return subjectendname;
    }
    public void setSubjectendname (String subjectendname){
        this.subjectendname = subjectendname;
    }

    /**
    *  学科2级代码
    */
    public String getSubjecttwocode (){
        return subjecttwocode;
    }
    public void setSubjecttwocode (String subjecttwocode){
        this.subjecttwocode = subjecttwocode;
    }

    /**
    *  学科3级代码
    */
    public String getSubjectthreecode (){
        return subjectthreecode;
    }
    public void setSubjectthreecode (String subjectthreecode){
        this.subjectthreecode = subjectthreecode;
    }

    /**
    *  学科4级代码
    */
    public String getSubjectfourcode (){
        return subjectfourcode;
    }
    public void setSubjectfourcode (String subjectfourcode){
        this.subjectfourcode = subjectfourcode;
    }

    /**
    *  学科最终结果等级代码
    */
    public String getSubjectendcode (){
        return subjectendcode;
    }
    public void setSubjectendcode (String subjectendcode){
        this.subjectendcode = subjectendcode;
    }

    /**
    *  学科类别(xm;zj)区别是自然基金的学科还是国标
    */
    public String getSubtype (){
        return subtype;
    }
    public void setSubtype (String subtype){
        this.subtype = subtype;
    }

    /**
    *  传参（参考项目学科建的，不确定什么用）
    */
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
    }

    /**
    *  学科类型
    */
    public String getSubjecttype (){
        return subjecttype;
    }
    public void setSubjecttype (String subjecttype){
        this.subjecttype = subjecttype;
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
    *  主表关联ID
    */
    public String getMainid (){
        return mainid;
    }
    public void setMainid (String mainid){
        this.mainid = mainid;
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

}