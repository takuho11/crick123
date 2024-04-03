package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizType;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizTypeService;
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
 * 业务分类
 *
 * @author szs
 * @date 2023-08-09
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizType")
@Api(value = "业务分类", tags = "低代码-业务分类")
public class MiniBizTypeController extends BladeController {


    private final MiniBizTypeService miniBizTypeService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-09
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部")
    public R<List<MiniBizType>> all() {
        QueryWrapper<MiniBizType> qw = new QueryWrapper<>();
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");
        return R.data(miniBizTypeService.list(qw));
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
    public R<IPage<MiniBizType>> list(MiniBizType miniBizType, Query query) {
        QueryWrapper<MiniBizType> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(miniBizType.getBizTypeCode()), "biz_type_code", miniBizType.getBizTypeCode());
        qw.like(StringUtils.isNotBlank(miniBizType.getBizTypeName()), "biz_type_name", miniBizType.getBizTypeName());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");

        return R.data(miniBizTypeService.page(Condition.getPage(query), qw));
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
    public R<MiniBizType> detail(@Valid IdDTO dto) {
        MiniBizType detail = miniBizTypeService.getById(dto.getId());
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
    public R<String> submit(@Valid @RequestBody MiniBizType miniBizType) {

        if (miniBizType.getId() == null) {
            // 新增
            miniBizType.setIsDeleted(0);
            miniBizType.setCreateUser(AuthUtil.getUserId());
            miniBizType.setCreateTime(new Date());
        } else {
            // 编辑
            miniBizType.setUpdateUser(AuthUtil.getUserId());
            miniBizType.setUpdateTime(new Date());
        }

        // 保存
        boolean bo = miniBizTypeService.saveOrUpdate(miniBizType);
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
        MiniBizType miniBizType = miniBizTypeService.getById(idDTO.getId());
        if (miniBizType == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniBizTypeService.removeById(miniBizType);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


}
