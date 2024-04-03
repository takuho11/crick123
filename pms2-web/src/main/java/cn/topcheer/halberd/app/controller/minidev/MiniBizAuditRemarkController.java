package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniBizAuditRemarkDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAuditRemark;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizAuditRemarkResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizAuditRemarkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 业务批注
 *
 * @author szs
 * @date 2023-11-16
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizAuditRemark")
@Api(value = "业务批注", tags = "低代码-业务批注")
public class MiniBizAuditRemarkController extends BladeController {


    private final MiniBizAuditRemarkService miniBizAuditRemarkService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-11-16
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部")
    public R<List<MiniBizAuditRemarkResult>> all(@Valid MiniBizAuditRemarkDTO dto) {

        return R.data(miniBizAuditRemarkService.getAllList(dto));
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-11-16
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody MiniBizAuditRemark miniBizAuditRemark) {

        if (miniBizAuditRemark.getId() == null) {
            // 新增
            miniBizAuditRemark.setIsDeleted(0);
            miniBizAuditRemark.setCreateTime(new Date());
            miniBizAuditRemark.setCreateUser(AuthUtil.getUserId());
        } else {
            // 编辑
            miniBizAuditRemark.setUpdateTime(new Date());
            miniBizAuditRemark.setUpdateUser(AuthUtil.getUserId());
        }

        // 保存
        boolean bo = miniBizAuditRemarkService.saveOrUpdate(miniBizAuditRemark);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.success("保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2023-11-16
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdDTO dto) {
        // 查询
        MiniBizAuditRemark miniBizAuditRemark = miniBizAuditRemarkService.getById(dto.getId());
        if (miniBizAuditRemark == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniBizAuditRemarkService.removeById(miniBizAuditRemark);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


    /**
     * 获取已批注字段列表
     *
     * @author szs
     * @date 2023-11-16
     */
    @GetMapping("/getFieldList")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取已批注字段列表", notes = "获取已批注字段列表")
    public R<List<MiniBizAuditRemark>> getFieldList(@Valid IdStrDTO dto) {
        QueryWrapper<MiniBizAuditRemark> qw = new QueryWrapper<>();
        qw.eq("main_id", dto.getId());
        qw.select("main_id", "table_id", "field_code");
        qw.groupBy("main_id", "table_id", "field_code");

        return R.data(miniBizAuditRemarkService.list(qw));
    }


}
