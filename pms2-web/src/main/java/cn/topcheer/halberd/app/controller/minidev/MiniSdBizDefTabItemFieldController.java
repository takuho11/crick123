package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniSdBizDefTabItemFieldDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItemField;
import cn.topcheer.halberd.app.api.minidev.service.MiniSdBizDefTabItemFieldService;
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
 * 标准业务分页子项字段
 *
 * @author szs
 * @date 2023-08-09
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniSdBizDefTabItemField")
@Api(value = "标准业务分页子项字段", tags = "低代码-标准业务分页子项字段")
public class MiniSdBizDefTabItemFieldController extends BladeController {


    private final MiniSdBizDefTabItemFieldService miniSdBizDefTabItemFieldService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部(sd_biz_def_tab_item_id)")
    public R<List<MiniSdBizDefTabItemField>> all(@Valid IdDTO dto) {
        QueryWrapper<MiniSdBizDefTabItemField> qw = new QueryWrapper<>();
        qw.eq(dto.getId() != null, "sd_biz_def_tab_item_id", dto.getId());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniSdBizDefTabItemFieldService.list(qw));
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
    public R<IPage<MiniSdBizDefTabItemField>> list(MiniSdBizDefTabItemField miniSdBizDefTabItemField, Query query) {
        QueryWrapper<MiniSdBizDefTabItemField> qw = new QueryWrapper<>();
        qw.eq(miniSdBizDefTabItemField.getSdBizDefTabItemId() != null, "sd_biz_def_tab_item_id", miniSdBizDefTabItemField.getSdBizDefTabItemId());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("seq");
        qw.orderByDesc("create_time");

        return R.data(miniSdBizDefTabItemFieldService.page(Condition.getPage(query), qw));
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
    public R<MiniSdBizDefTabItemField> detail(@Valid IdDTO dto) {
        MiniSdBizDefTabItemField detail = miniSdBizDefTabItemFieldService.getById(dto.getId());
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
    public R<String> submit(@Valid @RequestBody MiniSdBizDefTabItemField miniSdBizDefTabItemField) {

        if (miniSdBizDefTabItemField.getId() == null) {
            // 新增
            miniSdBizDefTabItemField.setIsDeleted(0);
            miniSdBizDefTabItemField.setCreateUser(AuthUtil.getUserId());
            miniSdBizDefTabItemField.setCreateTime(new Date());
        } else {
            // 编辑
            miniSdBizDefTabItemField.setUpdateUser(AuthUtil.getUserId());
            miniSdBizDefTabItemField.setUpdateTime(new Date());
        }

        // 保存
        boolean bo = miniSdBizDefTabItemFieldService.saveOrUpdate(miniSdBizDefTabItemField);
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
        MiniSdBizDefTabItemField miniSdBizDefTabItemField = miniSdBizDefTabItemFieldService.getById(idDTO.getId());
        if (miniSdBizDefTabItemField == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniSdBizDefTabItemFieldService.removeById(miniSdBizDefTabItemField);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


    /**
     * 批量保存
     *
     * @author szs
     * @date 2023-08-09
     */
    @PostMapping("/batchSave")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "批量保存", notes = "批量保存")
    public R<String> batchSave(@Valid @RequestBody MiniSdBizDefTabItemFieldDTO dto) {

        // 批量保存
        miniSdBizDefTabItemFieldService.batchSave(dto);

        return R.success("保存成功");
    }


}
