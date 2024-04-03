package cn.topcheer.halberd.app.api.minidev.service;


import cn.topcheer.halberd.app.api.minidev.dto.TemplateDTO;
import cn.topcheer.halberd.app.api.minidev.entity.Template;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.tool.api.R;

/**
 * 代码模板表 服务类
 *
 * @author Chill
 */
public interface TemplateService   extends IService<Template>  {

    TemplateDTO getTemplateDTO(String id);

    R<String> copy(String templateId);
}
