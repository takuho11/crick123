/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-2-22 16:50:10
 */
package  cn.topcheer.pms2.api.subject;

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
 *  学科字典表
 */
@Entity
@Table(name = "PMS_SUBJECT_TYPE")
@ClassInfo(name = "学科字典表", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsSubjectType {

    /**
     *  主键ID
     */
    @ApiModelProperty("主键ID")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "主键ID")
    private String id;

    /**
     *  父级学科ID
     */
    @ApiModelProperty("父级学科ID")
    @TableField("PARENTID")
    @Column(columnDefinition = "PARENTID")
    @FieldDes(name = "parentid", lenth = "255", type = "String", memo = "父级学科ID")
    private String parentid;

    /**
     *  编码
     */
    @ApiModelProperty("编码")
    @TableField("CODE")
    @Column(columnDefinition = "CODE")
    @FieldDes(name = "code", lenth = "255", type = "String", memo = "编码")
    private String code;

    /**
     *  名称
     */
    @ApiModelProperty("名称")
    @TableField("NAME")
    @Column(columnDefinition = "NAME")
    private String name;

    /**
     *  排序
     */
    @ApiModelProperty("排序")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Long", memo = "排序")
    private Long seq;

    /**
     *  学科分类类型
     */
    @ApiModelProperty("学科分类类型")
    @TableField("TYPE")
    @Column(columnDefinition = "TYPE")
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "学科分类类型")
    private String type;

    /**
     *  行业领域工商接口来源对比
     */
    @ApiModelProperty("行业领域工商接口来源对比")
    @TableField("YWHY")
    @Column(columnDefinition = "YWHY")
    @FieldDes(name = "ywhy", lenth = "255", type = "String", memo = "行业领域工商接口来源对比")
    private String ywhy;

    /**
     *  学科级别
     */
    @ApiModelProperty("学科级别")
    @TableField("RANK")
    @Column(columnDefinition = "RANK")
    @FieldDes(name = "rank", lenth = "255", type = "String", memo = "学科级别")
    private String rank;

    /**
     *  原始学科编码
     */
    @ApiModelProperty("原始学科编码")
    @TableField("ORIGINALCODE")
    @Column(columnDefinition = "ORIGINALCODE")
    @FieldDes(name = "originalcode", lenth = "255", type = "String", memo = "原始学科编码")
    private String originalcode;

    /**
     *  学科类型
     */
    @ApiModelProperty("学科类型")
    @TableField("SUBJECTTYPE")
    @Column(columnDefinition = "SUBJECTTYPE")
    @FieldDes(name = "subjecttype", lenth = "255", type = "String", memo = "学科类型")
    private String subjecttype;

    /**
     *  判断是否为财务学科(默认/不是:false,是:true)
     */
    @ApiModelProperty("判断是否为财务学科(默认/不是:false,是:true)")
    @TableField("XKFLAG")
    @Column(columnDefinition = "XKFLAG")
    @FieldDes(name = "xkflag", lenth = "255", type = "String", memo = "判断是否为财务学科(默认/不是:false,是:true)")
    private String xkflag;

    /**
     *  父级学科code
     */
    @ApiModelProperty("父级学科code")
    @TableField("PARENTCODE")
    @Column(columnDefinition = "PARENTCODE")
    @FieldDes(name = "parentcode", lenth = "255", type = "String", memo = "父级学科code")
    private String parentcode;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("CID")
    @Column(columnDefinition = "CID")
    @FieldDes(name = "cid", lenth = "50", type = "String", memo = "")
    private String cid;


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
    *  父级学科ID
    */
    public String getParentid (){
        return parentid;
    }
    public void setParentid (String parentid){
        this.parentid = parentid;
    }

    /**
    *  编码
    */
    public String getCode (){
        return code;
    }
    public void setCode (String code){
        this.code = code;
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
    *  排序
    */
    public Long getSeq (){
        return seq;
    }
    public void setSeq (Long seq){
        this.seq = seq;
    }

    /**
    *  学科分类类型
    */
    public String getType (){
        return type;
    }
    public void setType (String type){
        this.type = type;
    }

    /**
    *  行业领域工商接口来源对比
    */
    public String getYwhy (){
        return ywhy;
    }
    public void setYwhy (String ywhy){
        this.ywhy = ywhy;
    }

    /**
    *  学科级别
    */
    public String getRank (){
        return rank;
    }
    public void setRank (String rank){
        this.rank = rank;
    }

    /**
    *  原始学科编码
    */
    public String getOriginalcode (){
        return originalcode;
    }
    public void setOriginalcode (String originalcode){
        this.originalcode = originalcode;
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
    *  判断是否为财务学科(默认/不是:false,是:true)
    */
    public String getXkflag (){
        return xkflag;
    }
    public void setXkflag (String xkflag){
        this.xkflag = xkflag;
    }

    /**
    *  父级学科code
    */
    public String getParentcode (){
        return parentcode;
    }
    public void setParentcode (String parentcode){
        this.parentcode = parentcode;
    }

    /**
    *  
    */
    public String getCid (){
        return cid;
    }
    public void setCid (String cid){
        this.cid = cid;
    }

}