/**
 *  本代码由代码生成工具自动生成（自定义块除外）
 *  创建时间 : 2024年1月16日 上午9:36:33
 */
package cn.topcheer.pms2.api.message;

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
 *  验证码、短信记录表
 */
@Entity
@Table(name = "PMS_MESSAGERECORD")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmsMessagerecord {

    /**
     *  id
     */
    @ApiModelProperty("id")
    @TableField("ID")
    @Column(columnDefinition = "ID")
    @FieldDes(name = "id", lenth = "255", type = "String", memo = "固定字段:唯一标识")
    private String id;

    /**
     *  内容
     */
    @ApiModelProperty("内容")
    @TableField("CONTENT")
    @Column(columnDefinition = "CONTENT")
    private String content;

    /**
     *  发送时间
     */
    @ApiModelProperty("发送时间")
    @TableField("CREATE_DATE")
    @Column(columnDefinition = "CREATE_DATE")
    @FieldDes(name = "create_date", type = "Date", memo = "发送时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+08:00")
    private Date createDate;

    /**
     *  手机号
     */
    @ApiModelProperty("手机号")
    @TableField("MOBILE")
    @Column(columnDefinition = "MOBILE")
    private String mobile;

    /**
     *  IP
     */
    @ApiModelProperty("IP")
    @TableField("IP")
    @Column(columnDefinition = "IP")
    private String ip;

    /**
     *  LABEL
     */
    @ApiModelProperty("LABEL")
    @TableField("LABEL")
    @Column(columnDefinition = "LABEL")
    private String label;


    /**
    *  id
    */
    @Id
    public String getId (){
        return id;
    }
    public void setId (String id){
        this.id = id;
    }

    /**
    *  内容
    */
    public String getContent (){
        return content;
    }
    public void setContent (String content){
        this.content = content;
    }

    /**
    *  发送时间
    */
    public Date getCreateDate (){
        return createDate;
    }
    public void setCreateDate (Date createDate){
        this.createDate = createDate;
    }

    /**
    *  手机号
    */
    public String getMobile (){
        return mobile;
    }
    public void setMobile (String mobile){
        this.mobile = mobile;
    }

    /**
    *  
    */
    public String getIp (){
        return ip;
    }
    public void setIp (String ip){
        this.ip = ip;
    }

    /**
    *  
    */
    public String getLabel (){
        return label;
    }
    public void setLabel (String label){
        this.label = label;
    }

}