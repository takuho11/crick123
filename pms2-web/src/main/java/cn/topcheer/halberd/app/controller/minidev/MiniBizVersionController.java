package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.dto.CopyVersionDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDef;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizVersion;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefService;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizVersionService;
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
 * 业务版本
 *
 * @author szs
 * @date 2023-08-09
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizVersion")
@Api(value = "业务版本", tags = "低代码-业务版本")
public class MiniBizVersionController extends BladeController {


    private final MiniBizVersionService miniBizVersionService;

    private final MiniBizDefService miniBizDefService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部（业务定义id）")
    public R<List<MiniBizVersion>> all(@Valid IdDTO dto) {
        QueryWrapper<MiniBizVersion> qw = new QueryWrapper<>();
        qw.eq("biz_def_id", dto.getId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");

        return R.data(miniBizVersionService.list(qw));
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
    public R<IPage<MiniBizVersion>> list(MiniBizVersion miniBizVersion, Query query) {
        QueryWrapper<MiniBizVersion> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(miniBizVersion.getVersionName()), "version_name", miniBizVersion.getVersionName());
        qw.eq(miniBizVersion.getBizTypeId() != null, "biz_type_id", miniBizVersion.getBizTypeId());
        qw.eq(miniBizVersion.getBizDefId() != null, "biz_def_id", miniBizVersion.getBizDefId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");

        return R.data(miniBizVersionService.page(Condition.getPage(query), qw));
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
    public R<MiniBizVersion> detail(@Valid IdDTO dto) {
        MiniBizVersion detail = miniBizVersionService.getById(dto.getId());
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
    public R<String> submit(@Valid @RequestBody MiniBizVersion miniBizVersion) {
        // 查询业务定义
        MiniBizDef bizDef = miniBizDefService.getById(miniBizVersion.getBizDefId());
        if (bizDef == null) {
            throw new ServiceException("业务模板不存在");
        }

        // 是否需要更新业务定义的默认版本，新增或者最新版本名称修改
        boolean isUpdate = miniBizVersion.getId() == null || bizDef.getDefaultVersionId().equals(miniBizVersion.getId());

        if (miniBizVersion.getId() == null) {
            // 新增
            miniBizVersion.setIsDeleted(0);
            miniBizVersion.setCreateUser(AuthUtil.getUserId());
            miniBizVersion.setCreateTime(new Date());
        } else {
            // 编辑
            miniBizVersion.setUpdateUser(AuthUtil.getUserId());
            miniBizVersion.setUpdateTime(new Date());
        }

        // 保存
        miniBizVersion.setBizTypeId(bizDef.getBizTypeId());
        boolean bo = miniBizVersionService.saveOrUpdate(miniBizVersion);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        // 新增时，更新业务定义中的默认版本id
        if (isUpdate) {
            bizDef.setDefaultVersionId(miniBizVersion.getId());
            bizDef.setDefaultVersionName(miniBizVersion.getVersionName());
            bo = miniBizDefService.updateById(bizDef);
            if (!bo) {
                throw new ServiceException("更新业务模板最新版本失败");
            }
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
    public R<String> delete(@Valid @RequestBody IdDTO dto) {
        // 查询
        MiniBizVersion miniBizVersion = miniBizVersionService.getById(dto.getId());
        if (miniBizVersion == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniBizVersionService.removeById(miniBizVersion);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        // 查询业务定义
        MiniBizDef bizDef = miniBizDefService.getById(miniBizVersion.getBizDefId());
        if (bizDef == null) {
            throw new ServiceException("业务模板不存在");
        }

        // 如果业务模板的默认版本是当前版本，那当前版本删除后，业务模板的默认版本需要更新
        if (bizDef.getDefaultVersionId().equals(miniBizVersion.getId())) {
            MiniBizVersion lastVersion = miniBizVersionService.getLastMiniBizVersion(bizDef.getId());
            bizDef.setDefaultVersionId(lastVersion == null ? null : lastVersion.getId());
            bizDef.setDefaultVersionName(lastVersion == null ? null : lastVersion.getVersionName());
            bo = miniBizDefService.updateById(bizDef);
            if (!bo) {
                throw new ServiceException("更新业务模板最新版本失败");
            }
        }

        return R.success("删除成功");
    }


    /**
     * 复制
     *
     * @author szs
     * @date 2023-08-24
     */
    @PostMapping("/copy")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "复制", notes = "复制")
    public R<String> copy(@Valid @RequestBody CopyVersionDTO dto) {
        // 查询复制的版本
        MiniBizVersion copyBizVersion = miniBizVersionService.getById(dto.getCopyVersionId());
        if (copyBizVersion == null) {
            throw new ServiceException("复制版本不存在");
        }

        // 查询业务模板
        MiniBizDef bizDef = miniBizDefService.getById(copyBizVersion.getBizDefId());
        if (bizDef == null) {
            throw new ServiceException("业务模板不存在");
        }

        // 新增新的版本
        MiniBizVersion newBizVersion = new MiniBizVersion();
        newBizVersion.setBizDefId(bizDef.getId());
        newBizVersion.setBizTypeId(bizDef.getBizTypeId());
        newBizVersion.setVersionName(dto.getCopyVersionName());
        newBizVersion.setIsDeleted(0);
        newBizVersion.setCreateUser(AuthUtil.getUserId());
        newBizVersion.setCreateTime(new Date());
        boolean bo = miniBizVersionService.save(newBizVersion);
        if (!bo) {
            throw new ServiceException("复制业务版本失败");
        }

        // 更新业务模板的默认版本
        bizDef.setDefaultVersionId(newBizVersion.getId());
        bizDef.setDefaultVersionName(newBizVersion.getVersionName());
        bizDef.setUpdateUser(AuthUtil.getUserId());
        bizDef.setUpdateTime(new Date());
        bo = miniBizDefService.updateById(bizDef);
        if (!bo) {
            throw new ServiceException("更新业务模板最新版本失败");
        }

        // 复制业务定义数据
        miniBizDefService.copyBizDefData(bizDef.getId(), copyBizVersion.getId(), bizDef.getId(), newBizVersion.getId());

        return R.success("复制成功");
    }


}
