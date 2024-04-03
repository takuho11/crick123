package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.hutool.core.bean.BeanUtil;
import cn.topcheer.halberd.app.api.minidev.dto.TemplateDTO;
import cn.topcheer.halberd.app.api.minidev.entity.Template;
import cn.topcheer.halberd.app.api.minidev.entity.TemplateFile;
import cn.topcheer.halberd.app.api.minidev.service.TemplateFileService;
import cn.topcheer.halberd.app.api.minidev.service.TemplateService;
import cn.topcheer.halberd.app.dao.mapper.minidev.TemplateMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TemplateServiceImpl extends BaseServiceImpl<TemplateMapper, Template> implements TemplateService {

    @Autowired
    TemplateFileService templateFileService;

    @Override
    public TemplateDTO getTemplateDTO(String id) {

        Template template = getById(id);
        // 构建模板文件数据
        List<TemplateFile> files = templateFileService
                .list(Wrappers.<TemplateFile>query().lambda().eq(TemplateFile::getTemplateId, id));
        

        TemplateDTO templateDTO = BeanUtil.copyProperties(template, TemplateDTO.class);
        templateDTO.setFiles(files);
        return templateDTO;
    }

    private static final Pattern base64Pattern = Pattern.compile("^[A-Za-z0-9+/]+={0,2}$");

    public static boolean maybeBase64(String s) {
        // 检查字符串长度是否为 4 的倍数
        if (s.length() % 4 != 0) {
            return false;
        }

        // 检查字符串中是否只包含 Base64 编码所需的字符

        Matcher matcher = base64Pattern.matcher(s);
        boolean isBase64 = matcher.matches();

        return isBase64;
    }

    @Override
    public R<String> copy(String templateId) {
        Template template = this.getBaseMapper().selectById(templateId);
        if (template == null)
            return R.fail(-101, "操作错误，错误码:-101");

        List<TemplateFile> templateFiles = templateFileService
                .list(Wrappers.<TemplateFile>query().lambda().eq(TemplateFile::getTemplateId, templateId));

        template.setId(null);
        template.setTemplateName(template.getTemplateName() + " copy");
        this.save(template);
        for (TemplateFile templateFile : templateFiles) {
            templateFile.setTemplateId(template.getId());
            templateFile.setId(null);
            templateFileService.save(templateFile);
        }

        return R.success("");
    }
}
