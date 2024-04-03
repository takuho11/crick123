package cn.topcheer.halberd.app.api.minidev.dto;


import cn.topcheer.halberd.app.api.minidev.entity.Template;
import cn.topcheer.halberd.app.api.minidev.entity.TemplateFile;
import lombok.Data;

import java.util.List;

/**
 * 代码模板DTO
 *
 */
@Data
	public class TemplateDTO extends Template {

	private static final long serialVersionUID = 1L;

	/**
	 * 代码模板文件
	 */
	private List<TemplateFile> files;
}
