package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.dto.CopyBizDefDTO;
import cn.topcheer.halberd.app.api.minidev.dto.ImportTemplateDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefConfigDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefTreeDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDef;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizDefTabResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefService;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import com.alibaba.fastjson.JSONObject;
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
 * 业务定义
 *
 * @author szs
 * @date 2023-08-09
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniBizDef")
@Api(value = "业务定义", tags = "低代码-业务定义")
public class MiniBizDefController extends BladeController {


    private final MiniBizDefService miniBizDefService;

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
    public R<List<MiniBizDef>> all() {
        QueryWrapper<MiniBizDef> qw = new QueryWrapper<>();
        qw.eq("is_deleted", 0);
        qw.orderByDesc("id");

        return R.data(miniBizDefService.list(qw));
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
    public R<IPage<MiniBizDef>> list(MiniBizDef miniBizDef, Query query) {
        QueryWrapper<MiniBizDef> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(miniBizDef.getName()), "name", miniBizDef.getName());
        qw.eq(miniBizDef.getBizTypeId() != null, "biz_type_id", miniBizDef.getBizTypeId());
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("id");

        return R.data(miniBizDefService.page(Condition.getPage(query), qw));
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
    public R<MiniBizDef> detail(@Valid IdDTO dto) {
        MiniBizDef detail = miniBizDefService.getById(dto.getId());
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
    @ApiLog("保存业务模板配置")
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody MiniBizDef miniBizDef) {

        if (miniBizDef.getId() == null) {
            // 新增
            miniBizDef.setIsDeleted(0);
            miniBizDef.setCreateTime(new Date());
            miniBizDef.setCreateUser(AuthUtil.getUserId());
            miniBizDef.setEnterpriseId(sysUserService.getEnterPriseId());

        } else {
            // 编辑
            miniBizDef.setUpdateTime(new Date());
            miniBizDef.setUpdateUser(AuthUtil.getUserId());
        }

        // 保存
        boolean bo = miniBizDefService.saveOrUpdate(miniBizDef);
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
    public R<String> delete(@Valid @RequestBody IdDTO dto) {
        // 查询
        MiniBizDef miniBizDef = miniBizDefService.getById(dto.getId());
        if (miniBizDef == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniBizDefService.removeById(miniBizDef);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


    /**
     * 获取业务模板树
     *
     * @author szs
     * @date 2023-08-21
     */
    @GetMapping("/getMiniBizDefTree")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "获取业务模板树", notes = "获取业务模板树（主键ID）")
    public R<List<MiniBizDefTabResult>> getMiniBizDefTree(@Valid MiniBizDefTreeDTO dto) {

        return R.data(miniBizDefService.getMiniBizDefTree(dto.getId(), dto.getBizVersionId()));
    }


    /**
     * 获取业务模板配置
     *
     * @author szs
     * @date 2023-08-22
     */
    @GetMapping("/getMiniBizDefConfig")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "获取业务模板配置", notes = "获取业务模板配置（主键ID）")
    public R<JSONObject> getMiniBizDefConfig(@Valid MiniBizDefConfigDTO dto) {

        return R.data(miniBizDefService.getConfigJson(dto));
    }


    /**
     * 导入标准模板
     *
     * @author szs
     * @date 2023-08-23
     */
    @PostMapping("/importTemplate")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "导入标准模板", notes = "导入标准模板")
    public R<String> importTemplate(@Valid @RequestBody ImportTemplateDTO dto) {

        // 导入标准模板
        miniBizDefService.importTemplate(dto);

        return R.success("操作成功");
    }


    /**
     * 复制
     *
     * @author szs
     * @date 2023-08-24
     */
    @PostMapping("/copy")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "复制", notes = "复制")
    public R<String> copy(@Valid @RequestBody CopyBizDefDTO dto) {

        // 复制
        miniBizDefService.copy(dto);

        return R.success("操作成功");
    }


    /**
     * 转化为配置JSON
     *
     * @author szs
     * @date 2023-12-01
     */
    @PostMapping("/toConfigJson")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "转化为配置JSON", notes = "转化为配置JSON")
    public R<String> toConfigJson(@Valid @RequestBody MiniBizDefConfigDTO dto) {

        // 转化为配置JSON
        miniBizDefService.toConfigJson(dto);

        return R.success("操作成功");
    }


    /**
     * 获取业务模板对象数据
     *
     * @author szs
     * @date 2024-01-05
     */
    @GetMapping("/getMiniBizDefDataObject")
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "获取业务模板对象数据", notes = "获取业务模板对象数据")
    public R<JSONObject> getMiniBizDefDataObject(@Valid MiniBizDefConfigDTO dto) {

        return R.data(JSONObject.parseObject(miniBizDefService.getDataObject(dto.getId(), dto.getBizVersionId())));
    }


    /**
     * 查询本单位和上级单位的数据
     *
     * @author szs
     * @date 2024-03-25
     */
    @GetMapping("/currentAndParent")
    @ApiOperationSupport(order = 13)
    @ApiOperation(value = "查询本单位和上级单位的数据", notes = "查询本单位和上级单位的数据")
    public R<List<MiniBizDef>> currentAndParent() {
        QueryWrapper<MiniBizDef> qw = new QueryWrapper<>();
        qw.in("enterprise_id", sysUserService.getCurrentAndParentEnterPriseId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("id");

        return R.data(miniBizDefService.list(qw));
    }


    /**
     * 查询本单位的数据
     *
     * @author szs
     * @date 2024-03-27
     */
    @GetMapping("/current")
    @ApiOperationSupport(order = 14)
    @ApiOperation(value = "查询本单位的数据", notes = "查询本单位的数据")
    public R<List<MiniBizDef>> current() {
        QueryWrapper<MiniBizDef> qw = new QueryWrapper<>();
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("id");

        return R.data(miniBizDefService.list(qw));
    }


}
