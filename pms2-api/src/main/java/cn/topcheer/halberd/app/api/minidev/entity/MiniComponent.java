package cn.topcheer.halberd.app.api.minidev.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  实体类
 *
 * @author templateTool
 */
@Data
@TableName("mini_component")
@EqualsAndHashCode()
@ApiModel(value = "MiniComponent对象", description = "")
public class MiniComponent implements Serializable {

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
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	@TableField(value = "create_user")
    private String createUser;
          
	/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人")
	@TableField(value = "update_user")
    private String updateUser;
          
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField(value = "create_time")
    private LocalDateTime createTime;
          
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	@TableField(value = "update_time")
    private LocalDateTime updateTime;
          
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	@TableField(value = "status")
    private Integer status;
          
	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除")
	@TableField(value = "is_deleted")
    private Integer isDeleted;
          
	/**
	 * 组件名称
	 */
	@ApiModelProperty(value = "组件名称")
	@TableField(value = "name")
    private String name;
          
	/**
	 * 组件内容
	 */
	@ApiModelProperty(value = "组件内容")
	@TableField(value = "content")
    private String content;
          
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	@TableField(value = "description")
    private String description;
          
	/**
	 * 编码标识
	 */
	@ApiModelProperty(value = "编码标识")
	@TableField(value = "component_key")
    private String componentKey;
          
	/**
	 * 版本号
	 */
	@ApiModelProperty(value = "版本号")
	@TableField(value = "version")
    private Integer version;
          
	/**
	 * 图标
	 */
	@ApiModelProperty(value = "图标")
	@TableField(value = "icon")
    private String icon;
          


}
