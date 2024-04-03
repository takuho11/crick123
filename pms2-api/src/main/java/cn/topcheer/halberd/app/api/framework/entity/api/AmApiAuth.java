package cn.topcheer.halberd.app.api.framework.entity.api;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 实体类
 *
 * @author templateTool
 */
@Data
@TableName("am_api_auth")
@EqualsAndHashCode()
@ApiModel(value = "Api授权对象", description = "")
public class AmApiAuth extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * apiID
     */
    @ApiModelProperty(value = "apiID")
    @TableField(value = "api_id")
    private String apiId;

    /**
     * 授权理由
     */
    @ApiModelProperty(value = "授权理由")
    @TableField(value = "auth_reason")
    private String authReason;

    /**
     * 授权类型：角色:Role、用户:User、应用:Application
     */
    @ApiModelProperty(value = "授权类型")
    @TableField(value = "auth_type")
    private String authType;

    /**
     * 源Id
     */
    @ApiModelProperty(value = "源Id")
    @TableField(value = "source_id")
    private String sourceId;


    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    @TableField(value = "valid_date")
    private LocalDate validDate;

    /**
     * 每秒访问频次
     */
    @ApiModelProperty(value = "每秒访问频次")
    @TableField(value = "qps")
    private Integer qps;

    /**
     * 每天访问频次
     */
    @ApiModelProperty(value = "每天访问频次")
    @TableField(value = "qpd")
    private Integer qpd;

    /**
     * 总访问频次
     */
    @ApiModelProperty(value = "总访问频次")
    @TableField(value = "tqp")
    private Integer tqp;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField(value = "memo")
    private String memo;


    @ApiModelProperty(value = "是否集成IRS（0否，1是）")
    @TableField(value = "is_irs")
    private Boolean isIrs;


    @ApiModelProperty(value = "IRS应用APPKEY")
    @TableField(value = "irs_app_key", updateStrategy = FieldStrategy.IGNORED)
    private String irsAppKey;


    @ApiModelProperty(value = "IRS应用APP密钥")
    @TableField(value = "irs_app_secret", updateStrategy = FieldStrategy.IGNORED)
    private String irsAppSecret;


}
