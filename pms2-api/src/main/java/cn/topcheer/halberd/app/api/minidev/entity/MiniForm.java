package cn.topcheer.halberd.app.api.minidev.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("mini_form")
@EqualsAndHashCode()
@ApiModel(value = "MiniForm对象", description = "")
public class MiniForm implements Serializable {

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
	
	
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	@TableField(value = "create_user", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NOT_NULL)
    private String createUser;
          
	/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人")
	@TableField(value = "update_user", fill = FieldFill.UPDATE, updateStrategy = FieldStrategy.NOT_NULL)
    private String updateUser;
          
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField(value = "create_time", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime createTime;
          
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	@TableField(value = "update_time", fill = FieldFill.UPDATE, updateStrategy = FieldStrategy.NOT_NULL)
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
	 * 表单名称
	 */
	@ApiModelProperty(value = "表单名称")
	@TableField(value = "name")
    private String name;
          
	/**
	 * 表单内容
	 */
	@ApiModelProperty(value = "表单内容")
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
	@TableField(value = "form_key")
    private String formKey;
          
	/**
	 * 版本号
	 */
	@ApiModelProperty(value = "版本号")
	@TableField(value = "version")
    private Integer version;
          


	/**
	 *  模型ID
	 */
	@ApiModelProperty(value = "模型id")
	@TableField(value = "model_id")
    private String modelId;

 


	/**
	 *  模板ID
	 */
	@ApiModelProperty(value = "模板ID")
	@TableField(value = "template_id")
    private String templateId;



	/**
	 *  表单类型
	 */
	@ApiModelProperty(value = "表单类型")
	@TableField(value = "form_type")
	private String formType;

	/**
	 *  页面类型(10-系统,20-业务)
	 */
	@ApiModelProperty(value = "页面类型(10-系统,20-业务)")
	@TableField(value = "type")
	private String type;

}
