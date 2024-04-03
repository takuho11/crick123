package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.minidev.dto.DefAndVersionDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefConfig;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefConfigService;
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
 * 业务配置
 *
 * @author szs
 * @date 2024-03-04
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizDefConfig")
@Api(value = "业务配置", tags = "低代码-业务配置")
public class MiniBizDefConfigController extends BladeController {


    private final MiniBizDefConfigService miniBizDefConfigService;


    /**
     * 详情
     *
     * @author szs
     * @date 2024-03-04
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "详情")
    public R<MiniBizDefConfig> detail(@Valid DefAndVersionDTO dto) {

        return R.data(miniBizDefConfigService.getMiniBizDefConfig(dto.getBizDefId(), dto.getBizVersionId()));
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
    public R<Long> submit(@Valid @RequestBody MiniBizDefConfig miniBizDefConfig) {

        if (miniBizDefConfig.getId() == null) {
            // 新增
            miniBizDefConfig.setIsDeleted(0);
            miniBizDefConfig.setCreateTime(new Date());
            miniBizDefConfig.setCreateUser(AuthUtil.getUserId());
        } else {
            // 编辑
            miniBizDefConfig.setUpdateTime(new Date());
            miniBizDefConfig.setUpdateUser(AuthUtil.getUserId());
        }

        // 保存
        boolean bo = miniBizDefConfigService.saveOrUpdate(miniBizDefConfig);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.data(miniBizDefConfig.getId(), "保存成功");
    }


}
