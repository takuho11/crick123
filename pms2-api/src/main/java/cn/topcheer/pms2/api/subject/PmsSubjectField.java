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
 *  
 */
@Entity
@Table(name = "PMS_SUBJECT_FIELD")
@ClassInfo(name = "", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsSubjectField {

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "1024", type = "String", memo = "")
    private String id;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("BATCHID")
    @Column(columnDefinition = "BATCHID")
    @FieldDes(name = "batchid", lenth = "1024", type = "String", memo = "")
    private String batchid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("FIELD")
    @Column(columnDefinition = "FIELD")
    @FieldDes(name = "field", lenth = "1024", type = "String", memo = "")
    private String field;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("XKCODES")
    @Column(columnDefinition = "XKCODES")
    @FieldDes(name = "xkcodes", lenth = "4000", type = "String", memo = "")
    private String xkcodes;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("XKTYPE")
    @Column(columnDefinition = "XKTYPE")
    @FieldDes(name = "xktype", lenth = "400", type = "String", memo = "")
    private String xktype;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Integer", memo = "")
    private Integer seq;


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
    public String getBatchid (){
        return batchid;
    }
    public void setBatchid (String batchid){
        this.batchid = batchid;
    }

    /**
    *  
    */
    public String getField (){
        return field;
    }
    public void setField (String field){
        this.field = field;
    }

    /**
    *  
    */
    public String getXkcodes (){
        return xkcodes;
    }
    public void setXkcodes (String xkcodes){
        this.xkcodes = xkcodes;
    }

    /**
    *  
    */
    public String getXktype (){
        return xktype;
    }
    public void setXktype (String xktype){
        this.xktype = xktype;
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

}