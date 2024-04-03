/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024年3月8日 上午9:19:59
 */
package  cn.topcheer.pms2.api.sys;

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
 *  政务网结果保存表
 */
@Entity
@Table(name = "PMS_ZWW_RECORD")
@ClassInfo(name = "政务网结果保存表", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsZwwRecord {

    /**
     *
     */
    @ApiModelProperty("")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date savedate;

    /**
     *  固定字段:每次更新数据时间
     */
    @ApiModelProperty("固定字段:每次更新数据时间")
    @TableField("UPDATELASTTIME")
    @Column(columnDefinition = "UPDATELASTTIME")
    @FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
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
     *  状态码
     */
    @ApiModelProperty("状态码")
    @TableField("STATE")
    @Column(columnDefinition = "STATE")
    private String state;

    /**
     *  事项办件唯一编号
     */
    @ApiModelProperty("事项办件唯一编号")
    @TableField("PROJECT_NO")
    @Column(columnDefinition = "PROJECT_NO")
    private String projectNo;


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
     *  状态码
     */
    public String getState (){
        return state;
    }
    public void setState (String state){
        this.state = state;
    }

    /**
     *  事项办件唯一编号
     */
    public String getProjectNo (){
        return projectNo;
    }
    public void setProjectNo (String projectNo){
        this.projectNo = projectNo;
    }

}