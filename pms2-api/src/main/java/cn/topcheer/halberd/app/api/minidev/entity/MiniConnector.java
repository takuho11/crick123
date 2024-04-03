package cn.topcheer.halberd.app.api.minidev.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类
 *
 * @author templateTool
 */
@Data
@TableName("mini_connector")
@EqualsAndHashCode()
@ApiModel(value = "MiniConnector对象", description = "MiniConnector对象")
public class MiniConnector implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("主键id")
    @TableId(
            value = "id",
            type = IdType.ASSIGN_UUID
    )
    private String id;


    @ApiModelProperty(value = "连接器编码")
    @TableField(value = "code")
    private String code;


    @ApiModelProperty(value = "连接器名称")
    @TableField(value = "name")
    private String name;


    @ApiModelProperty(value = "连接器类型（2请求，3字典）")
    @TableField(value = "type")
    private Integer type;


    @ApiModelProperty(value = "是否表单（0否，1是）")
    @TableField(value = "is_form")
    private Boolean isForm;


    @ApiModelProperty(value = "是否添加运行（0否，1是）")
    @TableField(value = "add_to_runtime")
    private Boolean addToRuntime;


    @ApiModelProperty(value = "URL地址")
    @TableField(value = "url")
    private String url;


    @ApiModelProperty(value = "请求类型（GET，POST）")
    @TableField(value = "method")
    private String method;


    @ApiModelProperty(value = "说明")
    @TableField(value = "description", updateStrategy = FieldStrategy.IGNORED)
    private String description;


    @ApiModelProperty(value = "结果处理")
    @TableField(value = "data_script")
    private String dataScript;


    @ApiModelProperty(value = "数据处理")
    @TableField(value = "fix_script")
    private String fixScript;


    @ApiModelProperty(value = "请求前处理")
    @TableField(value = "before_req_script")
    private String beforeReqScript;


    @ApiModelProperty(value = "类别    miniForm、sdTemplate(标准模板) 、 bizTemplate（业务模板")
    @TableField(value = "categary")
    private String categary;


    @ApiModelProperty(value = "资源ID 分别对应 miniForm 、sdTemplate、bizTemplate 的ID")
    @TableField(value = "source_id")
    private String sourceId;


    @ApiModelProperty(value = "业务名称 ,便于查询")
    @TableField(value = "source_name")
    private String sourceName;


    @ApiModelProperty(value = "版本号")
    @TableField(value = "version")
    private String version;


    @ApiModelProperty(value = "备注说明")
    @TableField(value = "memo")
    private String memo;


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
    @TableField(value = "is_deleted")
    private Integer isDeleted;


}
