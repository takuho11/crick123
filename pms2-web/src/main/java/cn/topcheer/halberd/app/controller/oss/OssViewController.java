package cn.topcheer.halberd.app.controller.oss;

import cn.topcheer.halberd.biz.modules.resource.builder.oss.OssBuilder;
import cn.topcheer.halberd.biz.modules.resource.entity.HalberdAttach;
import cn.topcheer.halberd.biz.modules.resource.service.IHalberdAttachService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author zuowentao
 */
@RestController
@AllArgsConstructor
@RequestMapping("/oss")
@Api(value = "oss接口", tags = "oss接口")
public class OssViewController {

    /**
     * 对象存储构建类
     */
    @Resource
    private OssBuilder ossBuilder;

    /**
     * 附件表服务
     */
    @Resource
    private IHalberdAttachService attachService;

    /**
     * 通过oss的attachId获取文件
     */
    @GetMapping("/viewFile")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "通过oss的attachId获取文件", notes = "传入attachId")
    @SneakyThrows
    public void getOssViewFile(@RequestParam String id, HttpServletResponse response) {
        HalberdAttach halberdAttach = attachService.getAttachById(id);
        byte[] fileData;
        response.reset();
        if (halberdAttach != null) {
            MediaType mediaType = MediaTypeFactory.getMediaType(halberdAttach.getLink()).orElse(MediaType.APPLICATION_OCTET_STREAM);
            fileData = ossBuilder.template().getFile(halberdAttach.getLink());
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(halberdAttach.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setContentType(mediaType.toString());
            response.getOutputStream().write(fileData);
            response.getOutputStream().close();
        } else {
            response.getOutputStream().write("未找到该文件！".getBytes(StandardCharsets.UTF_8));
            response.getOutputStream().close();
        }
    }

}
