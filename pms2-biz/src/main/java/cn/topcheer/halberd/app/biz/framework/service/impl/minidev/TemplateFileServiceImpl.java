package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.entity.TemplateFile;
import cn.topcheer.halberd.app.api.minidev.service.TemplateFileService;
import cn.topcheer.halberd.app.dao.mapper.minidev.TemplateFileMapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TemplateFileServiceImpl extends BaseServiceImpl<TemplateFileMapper, TemplateFile> implements TemplateFileService {
}
