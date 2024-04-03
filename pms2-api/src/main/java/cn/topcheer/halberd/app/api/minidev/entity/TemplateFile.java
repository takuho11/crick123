package cn.topcheer.halberd.app.api.minidev.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseUuidEntity;

import java.io.Serializable;



/**
 * 代码模板文件表实体类
 *
 * @author Chill
 */
@Data
@TableName("gen_template_file")
@EqualsAndHashCode()
@ApiModel(value = "TemplateFile对象", description = "代码模板文件表")
public class TemplateFile  extends BaseUuidEntity implements Serializable {

	 
	/**
	 * 模板主键
	 */
	@ApiModelProperty(value = "模板主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String templateId;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "文件名称")
	private String fileName;
	/**
	 * 编号
	 */
	@ApiModelProperty(value = "文件编号")
	private String fileCode;
	/**
	 * 构建代号
	 */
	@ApiModelProperty(value = "构建代号")
	private String buildCode;
	/**
	 * 系统分类:front-end:前端系统，back-end:后端系统,minicode-end:低代码端
	 */
	@ApiModelProperty(value = "系统分类")
	private String fileCategory;
	/**
	 * 相对路径
	 */
	@ApiModelProperty(value = "相对路径")
	private String filePath;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String fileRemark;
	/**
	 * 内容
	 */
	@ApiModelProperty(value = "bas64格式保存的模板内容")
	private String templateContent;
	/**
	 * 是否覆盖
	 */
	@ApiModelProperty(value = "是否覆盖")
	private Integer isCover;
	/**
	 * 是否有效
	 */
	@ApiModelProperty(value = "是否有效")
	private Integer isUseful;

	/**
	 * 模板引擎，1:beetl,2:freemarker,3:velocity
	 */
	@ApiModelProperty(value = "模板引擎")
	private String templateEngine;

}
