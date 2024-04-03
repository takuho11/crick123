/**
 * 本代码由代码生成工具自动生成（自定义块除外）
 * 创建时间 : 2024-1-3 13:58:57
 */
package cn.topcheer.pms2.api.project.entity.projectBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.annotation.ClobTable;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *  计划项目-大字段
 */
@ClobTable
@Entity
@Table(name = "PMS_PROJECTBASE_CLOB")
@ClassInfo(name = "计划项目-大字段", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsProjectbaseClob {

    /**
     *  固定字段:唯一标识
     */
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
    private String id;
    /**
     *  固定字段:数据类型
     */
    private String type;
    /**
     *  固定字段:关联主表id
     */
    private String mainid;
    /**
     *  固定字段:关联子表id
     */
    private String sourceid;
    /**
     *  固定字段:第一次保存时间
     */
    @FieldDes(name = "savedate", type = "Date", memo = "固定字段:第一次保存时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date savedate;
    /**
     *  固定字段:每次更新数据时间
     */
    @FieldDes(name = "updatelasttime", type = "Date", memo = "固定字段:每次更新数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date updatelasttime;
    /**
     *  固定字段:排序
     */
    @FieldDes(name = "seq", type = "Integer", memo = "固定字段:排序")
    private Integer seq;
    /**
     *  固定字段:备注
     */
    private String memo;
    /**
     *  字段别名
     */
    private String columnname;
    /**
     *  内容
     */
    @FieldDes(name = "source", lenth = "2147483647", type = "CLOB", memo = "内容")
    private String source;
    /**
     *  真实id
     */
    private String realid;


    /**
     *  固定字段:唯一标识
     */
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     *  固定字段:数据类型
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *  固定字段:关联主表id
     */
    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    /**
     *  固定字段:关联子表id
     */
    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    /**
     *  固定字段:第一次保存时间
     */
    public Date getSavedate() {
        return savedate;
    }

    public void setSavedate(Date savedate) {
        this.savedate = savedate;
    }

    /**
     *  固定字段:每次更新数据时间
     */
    public Date getUpdatelasttime() {
        return updatelasttime;
    }

    public void setUpdatelasttime(Date updatelasttime) {
        this.updatelasttime = updatelasttime;
    }

    /**
     *  固定字段:排序
     */
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     *  固定字段:备注
     */
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     *  字段别名
     */
    public String getColumnname() {
        return columnname;
    }

    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    /**
     *  内容
     */
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     *  真实id
     */
    public String getRealid() {
        return realid;
    }

    public void setRealid(String realid) {
        this.realid = realid;
    }

}