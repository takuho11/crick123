package cn.topcheer.halberd.app.api.minidev.dto;


import cn.topcheer.halberd.app.api.minidev.entity.ModelPrototype;
import cn.topcheer.halberd.app.api.minidev.entity.Model;
import lombok.Data;

import java.util.List;

/**
 * 代码模型DTO
 */
@Data

public class ModelDTO extends Model {

	private static final long serialVersionUID = 1L;

	/**
	 * 模型属性(表字段)集合
	 */
	private List<ModelPrototype> prototypes;
	/**
	 * 构建代号
	 */
	private String buildCode;

	/**
	 * 项目包名
	 */
	private String groupId;

	/**
	 * 项目名
	 */
	private String artifactId;

}
