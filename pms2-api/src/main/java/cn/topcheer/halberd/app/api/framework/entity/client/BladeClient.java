package cn.topcheer.halberd.app.api.framework.entity.client;

import cn.topcheer.halberd.app.common.json.WhiteSpaceRemovalDeserializer;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@TableName("sf_client")
@ApiModel(value = "应用表", description = "应用表")
public class BladeClient implements Serializable {


    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("主键id")
    @TableId(
            value = "id",
            type = IdType.ASSIGN_UUID
    )
    private String id;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty("客户端id")
    @TableField(value = "client_id")
    private String clientId;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "客户端密钥")
    @TableField(value = "client_secret")
    private String clientSecret;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "资源集合")
    @TableField(value = "resource_ids")
    private String resourceIds;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "授权范围")
    @TableField(value = "scope")
    private String scope;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "授权类型")
    @TableField(value = "authorized_grant_types")
    private String authorizedGrantTypes;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "回调地址")
    @TableField(value = "web_server_redirect_uri")
    private String webServerRedirectUri;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "权限")
    @TableField(value = "authorities")
    private String authorities;


    @ApiModelProperty(value = "令牌过期秒数")
    @TableField(value = "access_token_validity")
    private Integer accessTokenValidity;


    @ApiModelProperty(value = "刷新令牌过期秒数")
    @TableField(value = "refresh_token_validity")
    private Integer refreshTokenValidity;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "附加说明")
    @TableField(value = "additional_information")
    private String additionalInformation;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "自动授权")
    @TableField(value = "autoapprove")
    private String autoapprove;


    @ApiModelProperty(value = "创建部门")
    @TableField(value = "create_dept")
    private String createDept;


    @ApiModelProperty(value = "状态")
    @TableField(value = "status")
    private Integer status;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "应用名称")
    @TableField(value = "name")
    private String name;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "功能模块")
    @TableField(value = "module")
    private String module;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "应用或功能模块描述")
    @TableField(value = "memo")
    private String memo;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "负责人")
    @TableField(value = "owner")
    private String owner;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "联系方式")
    @TableField(value = "phone")
    private String phone;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "邮箱")
    @TableField(value = "email")
    private String email;


    @ApiModelProperty(value = "关联角色ID")
    @TableField(value = "role_id")
    private String roleId;


    @JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
    @ApiModelProperty(value = "公钥")
    @TableField(value = "public_key")
    private String publicKey;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "create_time", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime createTime;


    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "create_user")
    private String createUser;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "update_time", fill = FieldFill.UPDATE, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime updateTime;


    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "update_user", fill = FieldFill.UPDATE, updateStrategy = FieldStrategy.NOT_NULL)
    private String updateUser;


    @ApiModelProperty(value = "是否删除（0未删除，1已删除）")
    @TableField(value = "is_deleted")
    private Integer isDeleted;


}
