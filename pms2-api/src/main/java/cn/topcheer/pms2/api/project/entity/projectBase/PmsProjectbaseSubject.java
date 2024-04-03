/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2022-10-26 16:26:50
 */
package cn.topcheer.pms2.api.project.entity.projectBase;

import cn.topcheer.pms2.api.annotation.FieldDes;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "PMS_PROJECTBASE_SUBJECT")
@Data
public class PmsProjectbaseSubject {


    /**
     *  主键ID
     */
    @Id
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "主键ID")
    private String id;


    /**
     *  主表关联ID
     */
    @FieldDes(name = "mainid", lenth = "255", type = "String", memo = "主表关联ID")
    private String mainid;


    /**
     *  一级学科名称
     */
    @FieldDes(name = "subjectonename", lenth = "255", type = "String", memo = "一级学科名称")
    private String subjectonename;

    /**
     *  一级学科编码
     */
    @FieldDes(name = "subjectonecode", lenth = "255", type = "String", memo = "一级学科编码")
    private String subjectonecode;


    /**
     *  二级学科名称
     */
    @FieldDes(name = "subjecttwoname", lenth = "255", type = "String", memo = "二级学科名称")
    private String subjecttwoname;

    /**
     *  三级学科名称
     */
    @FieldDes(name = "subjectthreename", lenth = "255", type = "String", memo = "三级学科名称")
    private String subjectthreename;

    /**
     *  四级学科名称
     */
    @FieldDes(name = "subjectfourname", lenth = "255", type = "String", memo = "四级学科名称")
    private String subjectfourname;

    /**
     *  最终学科名称
     */
    @FieldDes(name = "subjectendname", lenth = "255", type = "String", memo = "最终学科名称")
    private String subjectendname;

    /**
     *  二级学科编码
     */
    @FieldDes(name = "subjecttwocode", lenth = "255", type = "String", memo = "二级学科编码")
    private String subjecttwocode;

    /**
     *  三级学科编码
     */
    @FieldDes(name = "subjectthreecode", lenth = "255", type = "String", memo = "三级学科编码")
    private String subjectthreecode;

    /**
     *  四级学科编码
     */
    @FieldDes(name = "subjectfourcode", lenth = "255", type = "String", memo = "四级学科编码")
    private String subjectfourcode;

    /**
     *  最终学科编码
     */
    @FieldDes(name = "subjectendcode", lenth = "255", type = "String", memo = "最终学科编码")
    private String subjectendcode;

    /**
     *  学科类型
     */
    @FieldDes(name = "subtype", lenth = "255", type = "String", memo = "学科类型")
    private String subtype;

    /**
     *  前台传参类型
     */
    @FieldDes(name = "type", lenth = "255", type = "String", memo = "前台传参类型")
    private String type;

    /**
     *  父表关联ID
     */
    @FieldDes(name = "sourceid", lenth = "255", type = "String", memo = "父表关联ID")
    private String sourceid;

    /**
     *  学科类型编码(gbxk,jjxk,csly)
     */
    @FieldDes(name = "subjecttype", lenth = "255", type = "String", memo = "学科类型编码(gbxk,jjxk,csly)")
    private String subjecttype;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date updatelasttime;


    /**
     *  排序
     */
    @FieldDes(name = "seq", type = "Integer", memo = "排序")
    private Integer seq;


}