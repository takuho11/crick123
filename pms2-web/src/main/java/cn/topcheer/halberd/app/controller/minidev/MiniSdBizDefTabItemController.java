package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItem;
import cn.topcheer.halberd.app.api.minidev.service.MiniSdBizDefTabItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * 标准业务分页子项
 *
 * @author szs
 * @date 2023-08-09
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniSdBizDefTabItem")
@Api(value = "标准业务分页子项", tags = "低代码-标准业务分页子项")
public class MiniSdBizDefTabItemController extends BladeController {


    private final MiniSdBizDefTabItemService miniSdBizDefTabItemService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部(sd_biz_def_tab_id)")
    public R<List<MiniSdBizDefTabItem>> all(@Valid IdDTO dto) {
        QueryWrapper<MiniSdBizDefTabItem> qw = new QueryWrapper<>();
        qw.eq(dto.getId() != null, "sd_biz_def_tab_id", dto.getId());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniSdBizDefTabItemService.list(qw));
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
    public R<IPage<MiniSdBizDefTabItem>> list(MiniSdBizDefTabItem miniSdBizDefTabItem, Query query) {
        QueryWrapper<MiniSdBizDefTabItem> qw = new QueryWrapper<>();
        qw.eq(miniSdBizDefTabItem.getSdBizDefTabId() != null, "sd_biz_def_tab_id", miniSdBizDefTabItem.getSdBizDefTabId());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniSdBizDefTabItemService.page(Condition.getPage(query), qw));
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
    public R<MiniSdBizDefTabItem> detail(@Valid IdDTO dto) {
        MiniSdBizDefTabItem detail = miniSdBizDefTabItemService.getById(dto.getId());
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
    public R<Long> submit(@Valid @RequestBody MiniSdBizDefTabItem miniSdBizDefTabItem) {

        // 检测是否死循环
        miniSdBizDefTabItemService.checkIsEndlessLoop(miniSdBizDefTabItem);

        if (miniSdBizDefTabItem.getId() == null) {
            // 新增
            miniSdBizDefTabItem.setIsDeleted(0);
            miniSdBizDefTabItem.setCreateUser(AuthUtil.getUserId());
            miniSdBizDefTabItem.setCreateTime(new Date());
        } else {
            // 编辑
            miniSdBizDefTabItem.setUpdateUser(AuthUtil.getUserId());
            miniSdBizDefTabItem.setUpdateTime(new Date());
        }

        // 保存
        boolean bo = miniSdBizDefTabItemService.saveOrUpdate(miniSdBizDefTabItem);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.data(miniSdBizDefTabItem.getId(), "保存成功");
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
        MiniSdBizDefTabItem miniSdBizDefTabItem = miniSdBizDefTabItemService.getById(idDTO.getId());
        if (miniSdBizDefTabItem == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniSdBizDefTabItemService.removeById(miniSdBizDefTabItem);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


}
