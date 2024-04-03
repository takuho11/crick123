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
@Table(name = "PMS_SUBJECT")
@ClassInfo(name = "", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsSubject {

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "1020", type = "String", memo = "")
    private String id;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("ATTENDUNIT")
    @Column(columnDefinition = "ATTENDUNIT")
    @FieldDes(name = "attendunit", lenth = "1020", type = "String", memo = "")
    private String attendunit;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("DUTYMAN")
    @Column(columnDefinition = "DUTYMAN")
    @FieldDes(name = "dutyman", lenth = "1020", type = "String", memo = "")
    private String dutyman;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("PROJECTBASEID")
    @Column(columnDefinition = "PROJECTBASEID")
    @FieldDes(name = "projectbaseid", lenth = "1020", type = "String", memo = "")
    private String projectbaseid;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("RESPONSIBLEUNIT")
    @Column(columnDefinition = "RESPONSIBLEUNIT")
    @FieldDes(name = "responsibleunit", lenth = "1020", type = "String", memo = "")
    private String responsibleunit;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SEQ")
    @Column(columnDefinition = "SEQ")
    @FieldDes(name = "seq", type = "Long", memo = "")
    private Long seq;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SUBJECTNAME")
    @Column(columnDefinition = "SUBJECTNAME")
    @FieldDes(name = "subjectname", lenth = "1020", type = "String", memo = "")
    private String subjectname;

    /**
     *  
     */
    @ApiModelProperty("")
    @TableField("SUBJECTNUMBER")
    @Column(columnDefinition = "SUBJECTNUMBER")
    @FieldDes(name = "subjectnumber", lenth = "1020", type = "String", memo = "")
    private String subjectnumber;


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
    public String getAttendunit (){
        return attendunit;
    }
    public void setAttendunit (String attendunit){
        this.attendunit = attendunit;
    }

    /**
    *  
    */
    public String getDutyman (){
        return dutyman;
    }
    public void setDutyman (String dutyman){
        this.dutyman = dutyman;
    }

    /**
    *  
    */
    public String getProjectbaseid (){
        return projectbaseid;
    }
    public void setProjectbaseid (String projectbaseid){
        this.projectbaseid = projectbaseid;
    }

    /**
    *  
    */
    public String getResponsibleunit (){
        return responsibleunit;
    }
    public void setResponsibleunit (String responsibleunit){
        this.responsibleunit = responsibleunit;
    }

    /**
    *  
    */
    public Long getSeq (){
        return seq;
    }
    public void setSeq (Long seq){
        this.seq = seq;
    }

    /**
    *  
    */
    public String getSubjectname (){
        return subjectname;
    }
    public void setSubjectname (String subjectname){
        this.subjectname = subjectname;
    }

    /**
    *  
    */
    public String getSubjectnumber (){
        return subjectnumber;
    }
    public void setSubjectnumber (String subjectnumber){
        this.subjectnumber = subjectnumber;
    }

}