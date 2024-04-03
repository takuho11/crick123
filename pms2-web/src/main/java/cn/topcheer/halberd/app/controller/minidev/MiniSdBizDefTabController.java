package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTab;
import cn.topcheer.halberd.app.api.minidev.service.MiniSdBizDefTabService;
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
 * 标准业务分页
 *
 * @author szs
 * @date 2023-08-09
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniSdBizDefTab")
@Api(value = "标准业务分页", tags = "低代码-标准业务分页")
public class MiniSdBizDefTabController extends BladeController {


    private final MiniSdBizDefTabService miniSdBizDefTabService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部(sd_biz_def_id)")
    public R<List<MiniSdBizDefTab>> all(@Valid IdDTO dto) {
        QueryWrapper<MiniSdBizDefTab> qw = new QueryWrapper<>();
        qw.eq(dto.getId() != null, "sd_biz_def_id", dto.getId());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniSdBizDefTabService.list(qw));
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
    public R<IPage<MiniSdBizDefTab>> list(MiniSdBizDefTab miniSdBizDefTab, Query query) {
        QueryWrapper<MiniSdBizDefTab> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(miniSdBizDefTab.getName()), "name", miniSdBizDefTab.getName());
        qw.eq(miniSdBizDefTab.getIsShow() != null, "is_show", miniSdBizDefTab.getIsShow());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniSdBizDefTabService.page(Condition.getPage(query), qw));
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
    public R<MiniSdBizDefTab> detail(@Valid IdDTO dto) {
        MiniSdBizDefTab detail = miniSdBizDefTabService.getById(dto.getId());
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
    public R<Long> submit(@Valid @RequestBody MiniSdBizDefTab miniSdBizDefTab) {

        if (miniSdBizDefTab.getId() == null) {
            // 新增
            miniSdBizDefTab.setIsDeleted(0);
            miniSdBizDefTab.setCreateUser(AuthUtil.getUserId());
            miniSdBizDefTab.setCreateTime(new Date());
        } else {
            // 编辑
            miniSdBizDefTab.setUpdateUser(AuthUtil.getUserId());
            miniSdBizDefTab.setUpdateTime(new Date());
        }

        // 保存
        boolean bo = miniSdBizDefTabService.saveOrUpdate(miniSdBizDefTab);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.data(miniSdBizDefTab.getId(), "保存成功");
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
        MiniSdBizDefTab miniSdBizDefTab = miniSdBizDefTabService.getById(idDTO.getId());
        if (miniSdBizDefTab == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniSdBizDefTabService.removeById(miniSdBizDefTab);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


}
