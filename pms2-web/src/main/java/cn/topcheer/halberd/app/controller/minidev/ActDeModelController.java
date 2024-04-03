package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.minidev.entity.ActDeModel;
import cn.topcheer.halberd.app.api.minidev.service.ActDeModelService;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 流程模型
 *
 * @author szs
 * @date 2024-03-23
 */

@RestController
@AllArgsConstructor
@RequestMapping("/workflow/actDeModel")
@Api(value = "流程模型", tags = "流程设计-流程模型")
public class ActDeModelController extends BladeController {


    private final ActDeModelService actDeModelService;

    private final SysUserServiceImpl sysUserService;


    /**
     * 全部
     *
     * @author szs
     * @date 2024-03-23
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部")
    public R<List<ActDeModel>> all() {
        QueryWrapper<ActDeModel> qw = new QueryWrapper<>();
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.orderByDesc("created");

        return R.data(actDeModelService.list(qw));
    }


    /**
     * 分页
     *
     * @author szs
     * @date 2024-03-23
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "分页")
    public R<IPage<ActDeModel>> list(ActDeModel actDeModel, Query query) {
        QueryWrapper<ActDeModel> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(actDeModel.getModelKey()), "model_key", actDeModel.getModelKey());
        qw.like(StringUtils.isNotBlank(actDeModel.getName()), "name", actDeModel.getName());
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.orderByDesc("created");

        return R.data(actDeModelService.page(Condition.getPage(query), qw));
    }


    /**
     * 详情
     *
     * @author szs
     * @date 2024-03-23
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "详情", notes = "详情")
    public R<ActDeModel> detail(@Valid IdStrDTO dto) {
        ActDeModel detail = actDeModelService.getById(dto.getId());
        if (detail == null) {
            throw new ServiceException("查无此数据");
        }

        return R.data(detail);
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2024-03-23
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody ActDeModel actDeModel) {
        // 保存

        if (actDeModel.getId() == null) {
            // 新增
            actDeModel.setCreated(new Date());
            actDeModel.setCreatedBy(AuthUtil.getUserId());
            actDeModel.setEnterpriseId(sysUserService.getEnterPriseId());
            actDeModel.setFormKey("noitem-form");
            actDeModel.setVersion(0);
            actDeModel.setModelType(0);
            actDeModel.setTenantId("000000");

        } else {
            // 编辑
            actDeModel.setLastUpdated(new Date());
            actDeModel.setLastUpdatedBy(AuthUtil.getUserId());
        }

        boolean bo = actDeModelService.saveOrUpdate(actDeModel);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.success("保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2024-03-23
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdStrDTO dto) {
        // 查询
        ActDeModel actDeModel = actDeModelService.getById(dto.getId());
        if (actDeModel == null) {
            throw new ServiceException("查无此数据");
        }

        // 删除
        boolean bo = actDeModelService.removeById(actDeModel);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


    /**
     * 查询本单位和上级单位的数据
     *
     * @author szs
     * @date 2024-03-23
     */
    @GetMapping("/currentAndParent")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "查询本单位和上级单位的数据", notes = "查询本单位和上级单位的数据")
    public R<List<ActDeModel>> currentAndParent() {
        QueryWrapper<ActDeModel> qw = new QueryWrapper<>();
        qw.in("enterprise_id", sysUserService.getCurrentAndParentEnterPriseId());
        qw.orderByDesc("created");

        return R.data(actDeModelService.list(qw));
    }


    /**
     * 查询本单位的数据
     *
     * @author szs
     * @date 2024-03-27
     */
    @GetMapping("/current")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "查询本单位的数据", notes = "查询本单位的数据")
    public R<List<ActDeModel>> current() {
        QueryWrapper<ActDeModel> qw = new QueryWrapper<>();
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.orderByDesc("created");

        return R.data(actDeModelService.list(qw));
    }


}
