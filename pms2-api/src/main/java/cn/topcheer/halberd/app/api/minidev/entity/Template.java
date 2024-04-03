package cn.topcheer.halberd.app.api.minidev.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseUuidEntity;

import java.io.Serializable;

/**
 * 代码模板表实体类
 *
 */
@Data
@TableName("gen_template")
@EqualsAndHashCode()
@ApiModel(value = "Template对象", description = "代码模板表")
public class Template extends BaseUuidEntity implements Serializable {

	 
	/**
	 * 模板名
	 */
	@ApiModelProperty(value = "模板名")
	private String templateName;
	/**
	 * 模板编号
	 */
	@ApiModelProperty(value = "模板编号")
	private String templateCode;
	/**
	 * 模板引擎
	 */
	@ApiModelProperty(value = "模板引擎")
	private String templateEngine;
	/**
	 * 是否覆盖
	 */
	@ApiModelProperty(value = "是否覆盖")
	private Integer isCover;
	/**
	 * 模板备注
	 */
	@ApiModelProperty(value = "模板备注")
	private String templateRemark;

}
