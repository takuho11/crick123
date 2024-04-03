/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024-3-23 10:07:15
 */
package cn.topcheer.pms2.api.project.entity;

import javax.persistence.*;

import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.enumUtil.ClassLevelEnum;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.annotation.FieldDes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 */
@Entity
@Table(name = "SYS_NUMBERSHOP")
@ClassInfo(name = "", module = SysModuleEnum.DECLARE, level = ClassLevelEnum.POJO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysNumberShop {

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "40", type = "String", memo = "")
    private String id;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("NAME")
    @Column(columnDefinition = "NAME")
    @FieldDes(name = "name", lenth = "100", type = "String", memo = "")
    private String name;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("NUMBERTYPE")
    @Column(columnDefinition = "NUMBERTYPE")
    @FieldDes(name = "numbertype", type = "Long", memo = "")
    private Long numbertype;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("FORMATSTRING")
    @Column(columnDefinition = "FORMATSTRING")
    @FieldDes(name = "formatstring", lenth = "200", type = "String", memo = "")
    private String formatstring;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("NUMBERCALLBACKSTR")
    @Column(columnDefinition = "NUMBERCALLBACKSTR")
    @FieldDes(name = "numbercallbackstr", lenth = "2000", type = "String", memo = "")
    private String numbercallbackstr;

    /**
     *
     */
    @ApiModelProperty("")
    @TableField("MEMO")
    @Column(columnDefinition = "MEMO")
    @FieldDes(name = "memo", lenth = "200", type = "String", memo = "")
    private String memo;


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
    public String getName (){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }

    /**
    *
    */
    public Long getNumbertype (){
        return numbertype;
    }
    public void setNumbertype (Long numbertype){
        this.numbertype = numbertype;
    }

    /**
    *
    */
    public String getFormatstring (){
        return formatstring;
    }
    public void setFormatstring (String formatstring){
        this.formatstring = formatstring;
    }

    /**
    *
    */
    public String getNumbercallbackstr (){
        return numbercallbackstr;
    }
    public void setNumbercallbackstr (String numbercallbackstr){
        this.numbercallbackstr = numbercallbackstr;
    }

    /**
    *
    */
    public String getMemo (){
        return memo;
    }
    public void setMemo (String memo){
        this.memo = memo;
    }

}
