package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.dto.AllowUpdateDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniBizAllowUpdateDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAllowUpdate;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizAllowUpdateResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizAllowUpdateService;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInBiz;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchInBizService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 业务允许修改
 *
 * @author szs
 * @date 2023-12-05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizAllowUpdate")
@Api(value = "业务允许修改", tags = "低代码-业务允许修改")
public class MiniBizAllowUpdateController extends BladeController {


    private final MiniBizAllowUpdateService miniBizAllowUpdateService;

    private final PmsPlanprojectbatchInBizService pmsPlanprojectbatchInBizService;


    /**
     * 分页
     *
     * @author szs
     * @date 2023-12-05
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "业务允许修改分页", notes = "分页")
    public R<IPage<MiniBizAllowUpdateResult>> list(MiniBizAllowUpdateDTO dto, Query query) {

        return R.data(miniBizAllowUpdateService.pageList(dto, query));
    }


    /**
     * 详情
     *
     * @author szs
     * @date 2023-12-05
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "详情", notes = "详情")
    public R<MiniBizAllowUpdate> detail(@Valid IdDTO dto) {
        MiniBizAllowUpdate detail = miniBizAllowUpdateService.getById(dto.getId());
        if (detail == null) {
            throw new ServiceException("查无此数据");
        }

        return R.data(detail);
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-12-05
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "保存", notes = "保存")
    public R<Long> submit(@Valid @RequestBody MiniBizAllowUpdate miniBizAllowUpdate) {

        if (miniBizAllowUpdate.getId() == null) {
            // 新增
            miniBizAllowUpdate.setIsDeleted(0);
            miniBizAllowUpdate.setCreateUser(AuthUtil.getUserId());
            miniBizAllowUpdate.setCreateTime(new Date());
        } else {
            // 编辑
            miniBizAllowUpdate.setUpdateUser(AuthUtil.getUserId());
            miniBizAllowUpdate.setUpdateTime(new Date());
        }

        // 保存
        boolean bo = miniBizAllowUpdateService.saveOrUpdate(miniBizAllowUpdate);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.data(miniBizAllowUpdate.getId(), "保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2023-12-05
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdDTO idDTO) {
        // 查询
        MiniBizAllowUpdate miniBizAllowUpdate = miniBizAllowUpdateService.getById(idDTO.getId());
        if (miniBizAllowUpdate == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniBizAllowUpdateService.removeById(miniBizAllowUpdate);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


    /**
     * 获取允许修改版本列表
     *
     * @author szs
     * @date 2023-12-05
     */
    @GetMapping("/getAllowUpdateList")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "获取允许修改版本列表", notes = "低代码")
    public R<List<MiniBizAllowUpdate>> getAllowUpdateList(@Valid AllowUpdateDTO dto) {
        // 获取批次绑定业务模板
        PmsPlanprojectbatchInBiz batchInBiz = pmsPlanprojectbatchInBizService.getPmsPlanprojectbatchInBiz(dto.getBatchId(), dto.getPlanprojectType());
        if (batchInBiz == null) {
            throw new ServiceException("该申报批次还未绑定业务模板：" + dto.getBatchId());
        }

        // 查询允许修改版本
        QueryWrapper<MiniBizAllowUpdate> qw = new QueryWrapper<>();
        qw.eq("type", dto.getType());
        qw.eq("mini_biz_def_id", batchInBiz.getMiniBizDefId());
        qw.eq("mini_biz_version_id", batchInBiz.getMiniBizVersionId());
        qw.eq(StringUtils.isNotBlank(dto.getBatchId()) && dto.getType() == 2, "batch_id", dto.getBatchId());
        qw.eq(StringUtils.isNotBlank(dto.getMainId()) && dto.getType() == 2, "main_id", dto.getMainId());
        qw.eq("create_user", AuthUtil.getUserId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");

        return R.data(miniBizAllowUpdateService.list(qw));
    }


}
