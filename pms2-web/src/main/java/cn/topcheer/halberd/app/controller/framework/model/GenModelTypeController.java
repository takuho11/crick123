package cn.topcheer.halberd.app.controller.framework.model;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.entity.GenModelType;
import cn.topcheer.halberd.app.api.minidev.service.GenModelTypeService;
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
 * 模型类型
 *
 * @author szs
 * @date 2023-08-09
 */

@RestController
@AllArgsConstructor
@RequestMapping("/genModelType")
@Api(value = "模型类型", tags = "系统-模型类型")
public class GenModelTypeController extends BladeController {


    private final GenModelTypeService genModelTypeService;

    private final SysUserServiceImpl sysUserService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部")
    public R<List<GenModelType>> all() {
        QueryWrapper<GenModelType> qw = new QueryWrapper<>();
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");

        return R.data(genModelTypeService.list(qw));
    }


    /**
     * 分页
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "分页")
    public R<IPage<GenModelType>> list(GenModelType genModelType, Query query) {
        QueryWrapper<GenModelType> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(genModelType.getModelTypeCode()), "model_type_code", genModelType.getModelTypeCode());
        qw.like(StringUtils.isNotBlank(genModelType.getModelTypeName()), "model_type_name", genModelType.getModelTypeName());
        qw.eq(genModelType.getModelTypeUse() != null, "model_type_use", genModelType.getModelTypeUse());
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("id");

        return R.data(genModelTypeService.page(Condition.getPage(query), qw));
    }


    /**
     * 详情
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "详情", notes = "详情")
    public R<GenModelType> detail(@Valid IdDTO dto) {
        GenModelType detail = genModelTypeService.getById(dto.getId());
        if (detail == null) {
            throw new ServiceException("查无此数据");
        }

        return R.data(detail);
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-08-09
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody GenModelType genModelType) {
        // 保存

        if (genModelType.getId() == null) {
            // 新增
            genModelType.setIsDeleted(0);
            genModelType.setCreateTime(new Date());
            genModelType.setCreateUser(AuthUtil.getUserId());
            genModelType.setEnterpriseId(sysUserService.getEnterPriseId());

        } else {
            // 编辑
            genModelType.setUpdateTime(new Date());
            genModelType.setUpdateUser(AuthUtil.getUserId());
        }

        boolean bo = genModelTypeService.saveOrUpdate(genModelType);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.success("保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2023-08-09
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdDTO idDTO) {
        // 查询
        GenModelType genModelType = genModelTypeService.getById(idDTO.getId());
        if (genModelType == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = genModelTypeService.removeById(genModelType);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


}
