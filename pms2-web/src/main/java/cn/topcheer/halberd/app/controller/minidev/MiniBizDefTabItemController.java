package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItem;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefTabItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * 业务分页子项
 *
 * @author szs
 * @date 2023-08-09
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizDefTabItem")
@Api(value = "业务分页子项", tags = "低代码-业务分页子项")
public class MiniBizDefTabItemController extends BladeController {


    private final MiniBizDefTabItemService miniBizDefTabItemService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部（biz_def_tab_id）")
    public R<List<MiniBizDefTabItem>> all(@Valid IdDTO dto) {
        QueryWrapper<MiniBizDefTabItem> qw = new QueryWrapper<>();
        qw.eq(dto.getId() != null, "biz_def_tab_id", dto.getId());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniBizDefTabItemService.list(qw));
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
    public R<IPage<MiniBizDefTabItem>> list(MiniBizDefTabItem miniBizDefTabItem, Query query) {
        QueryWrapper<MiniBizDefTabItem> qw = new QueryWrapper<>();
        qw.eq(miniBizDefTabItem.getBizDefTabId() != null, "biz_def_tab_id", miniBizDefTabItem.getBizDefTabId());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniBizDefTabItemService.page(Condition.getPage(query), qw));
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
    public R<MiniBizDefTabItem> detail(@Valid IdDTO dto) {
        MiniBizDefTabItem detail = miniBizDefTabItemService.getById(dto.getId());
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
    @ApiLog("保存业务模板子项配置")
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "保存", notes = "保存")
    public R<Long> submit(@Valid @RequestBody MiniBizDefTabItem miniBizDefTabItem) {

        // 检测是否死循环
        miniBizDefTabItemService.checkIsEndlessLoop(miniBizDefTabItem);

        if (miniBizDefTabItem.getId() == null) {
            // 新增
            miniBizDefTabItem.setIsDeleted(0);
            miniBizDefTabItem.setCreateTime(new Date());
            miniBizDefTabItem.setCreateUser(AuthUtil.getUserId());
        } else {
            // 编辑
            miniBizDefTabItem.setUpdateTime(new Date());
            miniBizDefTabItem.setUpdateUser(AuthUtil.getUserId());
        }

        // 保存
        boolean bo = miniBizDefTabItemService.saveOrUpdate(miniBizDefTabItem);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.data(miniBizDefTabItem.getId(), "保存成功");
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
        MiniBizDefTabItem miniBizDefTabItem = miniBizDefTabItemService.getById(idDTO.getId());
        if (miniBizDefTabItem == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniBizDefTabItemService.removeById(miniBizDefTabItem);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


}
