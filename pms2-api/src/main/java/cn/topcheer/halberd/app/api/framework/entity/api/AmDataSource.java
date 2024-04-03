package cn.topcheer.halberd.app.api.framework.entity.api;

import cn.topcheer.halberd.app.common.json.WhiteSpaceRemovalDeserializer;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 实体类
 *
 * @author templateTool
 */
@Getter
@Setter
@TableName("am_datasource")
@ApiModel(value = "AmDataSource对象", description = "")
public class AmDataSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("主键id")
    @TableId(
            value = "id",
            type = IdType.ASSIGN_ID
    )
    private Long id;


    /**
     * 数据源名称
     */
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "数据源名称")
    @TableField(value = "name", updateStrategy = FieldStrategy.IGNORED)
    private String name;

    /**
     * 数据源类型
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "数据源类型")
    @TableField(value = "type", updateStrategy = FieldStrategy.IGNORED)
    private String type;

    /**
     * 用户名
     */
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "用户名")
    @TableField(value = "user_name", updateStrategy = FieldStrategy.IGNORED)
    private String userName;

    /**
     * 密码
     */
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "密码")
    @TableField(value = "user_pwd", updateStrategy = FieldStrategy.NOT_EMPTY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPwd;

    /**
     * url
     */
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @NotBlank(message = "连接url必填！")
    @ApiModelProperty(value = "url")
    @TableField(value = "url", updateStrategy = FieldStrategy.IGNORED)
    private String url;

    /**
     * jdbc类名
     */
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "jdbc类名")
    @TableField(value = "driver_class_name", updateStrategy = FieldStrategy.IGNORED)
    private String driverClassName;

    /**
     * 数据源描述
     */
    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "数据源描述")
    @TableField(value = "memo", updateStrategy = FieldStrategy.IGNORED)
    private String memo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @TableField(value = "status", updateStrategy = FieldStrategy.NOT_NULL)
    private Boolean enabled;

    @TableField(value = "is_readonly", updateStrategy = FieldStrategy.IGNORED)
    private Boolean readonly;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "create_time", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "create_user", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NOT_NULL)
    private String createUser;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "update_time", fill = FieldFill.UPDATE, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime updateTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "update_user", fill = FieldFill.UPDATE, updateStrategy = FieldStrategy.NOT_NULL)
    private String updateUser;


    @ApiModelProperty(value = "是否被删除（0未删除，1已删除）")
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer isDeleted;


    @ApiModelProperty(value = "审批状态（0待审批，1审批中，2审批通过，3审批驳回）")
    @TableField(value = "approve_status")
    private Integer approveStatus;


    @JsonIgnore
    public long getVersion() {
        return Timestamp.valueOf(getUpdateTime() == null ? getCreateTime() : getUpdateTime()).getTime();
    }
}
