/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024年1月7日 下午2:43:05
 */
package cn.topcheer.pms2.api.project.entity.prize;

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
 *  科技奖励-计划(基金)项目表
 */
@Entity
@Table(name = "PMS_PRIZE_PRO")
@ClassInfo(name = "科技奖励-计划(基金)项目表", module = SysModuleEnum.TECH_AWARDS, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsPrizePro {

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
     *  计划(基金)名称
     */
    @ApiModelProperty("计划(基金)名称")
    @TableField("PROJECT_SOURCE_PLAN")
    @Column(columnDefinition = "PROJECT_SOURCE_PLAN")
    private String projectSourcePlan;

    /**
     *  项目名称
     */
    @ApiModelProperty("项目名称")
    @TableField("PROJECT_SOURCE_NAME")
    @Column(columnDefinition = "PROJECT_SOURCE_NAME")
    private String projectSourceName;

    /**
     *  计划(合同)书编号
     */
    @ApiModelProperty("计划(合同)书编号")
    @TableField("PROJECT_SOURCE_NO")
    @Column(columnDefinition = "PROJECT_SOURCE_NO")
    private String projectSourceNo;


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
    *  计划(基金)名称
    */
    public String getProjectSourcePlan (){
        return projectSourcePlan;
    }
    public void setProjectSourcePlan (String projectSourcePlan){
        this.projectSourcePlan = projectSourcePlan;
    }

    /**
    *  项目名称
    */
    public String getProjectSourceName (){
        return projectSourceName;
    }
    public void setProjectSourceName (String projectSourceName){
        this.projectSourceName = projectSourceName;
    }

    /**
    *  计划(合同)书编号
    */
    public String getProjectSourceNo (){
        return projectSourceNo;
    }
    public void setProjectSourceNo (String projectSourceNo){
        this.projectSourceNo = projectSourceNo;
    }

}