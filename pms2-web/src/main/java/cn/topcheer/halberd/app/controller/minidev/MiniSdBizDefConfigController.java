package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefConfig;
import cn.topcheer.halberd.app.api.minidev.service.MiniSdBizDefConfigService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 标准配置
 *
 * @author szs
 * @date 2024-03-04
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniSdBizDefConfig")
@Api(value = "标准配置", tags = "低代码-标准配置")
public class MiniSdBizDefConfigController extends BladeController {


    private final MiniSdBizDefConfigService miniSdBizDefConfigService;


    /**
     * 详情（标准模板id）
     *
     * @author szs
     * @date 2024-03-04
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情（标准模板id）", notes = "详情（标准模板id）")
    public R<MiniSdBizDefConfig> detail(@Valid IdDTO dto) {

        return R.data(miniSdBizDefConfigService.getMiniSdBizDefConfig(dto.getId()));
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2024-03-04
     */
    @ApiLog("保存")
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "保存", notes = "保存")
    public R<Long> submit(@Valid @RequestBody MiniSdBizDefConfig miniSdBizDefConfig) {

        if (miniSdBizDefConfig.getId() == null) {
            // 新增
            miniSdBizDefConfig.setIsDeleted(0);
            miniSdBizDefConfig.setCreateTime(new Date());
            miniSdBizDefConfig.setCreateUser(AuthUtil.getUserId());
        } else {
            // 编辑
            miniSdBizDefConfig.setUpdateTime(new Date());
            miniSdBizDefConfig.setUpdateUser(AuthUtil.getUserId());
        }

        // 保存
        boolean bo = miniSdBizDefConfigService.saveOrUpdate(miniSdBizDefConfig);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.data(miniSdBizDefConfig.getId(), "保存成功");
    }


}
