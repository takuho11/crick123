package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTab;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefTabService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
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
 * 业务分页
 *
 * @author szs
 * @date 2023-08-09
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizDefTab")
@Api(value = "业务分页", tags = "低代码-业务分页")
public class MiniBizDefTabController extends BladeController {


    private final MiniBizDefTabService miniBizDefTabService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部（biz_version_id）")
    public R<List<MiniBizDefTab>> all(@Valid IdDTO dto) {
        QueryWrapper<MiniBizDefTab> qw = new QueryWrapper<>();
        qw.eq(dto.getId() != null, "biz_version_id", dto.getId());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniBizDefTabService.list(qw));
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
    public R<IPage<MiniBizDefTab>> list(MiniBizDefTab miniBizDefTab, Query query) {
        QueryWrapper<MiniBizDefTab> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(miniBizDefTab.getName()), "name", miniBizDefTab.getName());
        qw.eq(miniBizDefTab.getBizDefId() != null, "biz_def_id", miniBizDefTab.getBizDefId());
        qw.eq(miniBizDefTab.getBizVersionId() != null, "biz_version_id", miniBizDefTab.getBizVersionId());
        qw.eq(miniBizDefTab.getIsShow() != null, "is_show", miniBizDefTab.getIsShow());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniBizDefTabService.page(Condition.getPage(query), qw));
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
    public R<MiniBizDefTab> detail(@Valid IdDTO dto) {
        MiniBizDefTab detail = miniBizDefTabService.getById(dto.getId());
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
    @ApiLog("保存业务模板分页配置")
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "保存", notes = "保存")
    public R<Long> submit(@Valid @RequestBody MiniBizDefTab miniBizDefTab) {

        if (miniBizDefTab.getId() == null) {
            // 新增
            miniBizDefTab.setIsDeleted(0);
            miniBizDefTab.setCreateTime(new Date());
            miniBizDefTab.setCreateUser(AuthUtil.getUserId());
        } else {
            // 编辑
            miniBizDefTab.setUpdateTime(new Date());
            miniBizDefTab.setUpdateUser(AuthUtil.getUserId());
        }

        // 保存
        boolean bo = miniBizDefTabService.saveOrUpdate(miniBizDefTab);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.data(miniBizDefTab.getId(), "保存成功");
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
        MiniBizDefTab miniBizDefTab = miniBizDefTabService.getById(idDTO.getId());
        if (miniBizDefTab == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniBizDefTabService.removeById(miniBizDefTab);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


}
