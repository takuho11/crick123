package cn.topcheer.halberd.app.api.minidev.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@TableName("gen_custom_query")
@EqualsAndHashCode()
@ApiModel(value = "CustomQuery对象", description = "代码自定义查询")
public class CustomQuery implements Serializable   {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 验证名
	 */
	@ApiModelProperty(value = "查询名")
	private String queryName;
	/**
	 * 验证键值
	 */
	@ApiModelProperty(value = "查询键值")
	private String queryKey;
	/**
	 * 验证规则增强
	 */
	@ApiModelProperty(value = "查询规则增强")
	private String queryRule;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;


}
