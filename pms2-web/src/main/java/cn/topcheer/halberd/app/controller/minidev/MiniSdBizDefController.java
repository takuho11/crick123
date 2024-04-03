package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.minidev.dto.CopySdBizDefDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDef;
import cn.topcheer.halberd.app.api.minidev.result.MiniSdBizDefTabResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniSdBizDefService;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
 * 标准业务模板
 *
 * @author szs
 * @date 2023-08-21
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniSdBizDef")
@Api(value = "标准业务模板", tags = "低代码-标准业务模板")
public class MiniSdBizDefController extends BladeController {


    private final MiniSdBizDefService miniSdBizDefService;

    private final SysUserServiceImpl sysUserService;


    /**
     * 全部
     *
     * @author szs
     * @date 2023-08-21
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部", notes = "全部")
    public R<List<MiniSdBizDef>> all() {
        QueryWrapper<MiniSdBizDef> qw = new QueryWrapper<>();
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.eq("is_deleted", 0);
        qw.eq("status", 1);
        qw.orderByDesc("id");

        return R.data(miniSdBizDefService.list(qw));
    }


    /**
     * 分页
     *
     * @author szs
     * @date 2023-08-21
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "分页")
    public R<IPage<MiniSdBizDef>> list(MiniSdBizDef miniSdBizDef, Query query) {
        QueryWrapper<MiniSdBizDef> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(miniSdBizDef.getName()), "name", miniSdBizDef.getName());
        qw.like(StringUtils.isNotBlank(miniSdBizDef.getCode()), "code", miniSdBizDef.getCode());
        qw.eq(miniSdBizDef.getBizTypeId() != null, "biz_type_id", miniSdBizDef.getBizTypeId());
        qw.eq(miniSdBizDef.getStatus() != null, "status", miniSdBizDef.getStatus());
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.eq("is_deleted", 0);
        qw.orderByDesc("id");

        return R.data(miniSdBizDefService.page(Condition.getPage(query), qw));
    }


    /**
     * 详情
     *
     * @author szs
     * @date 2023-08-21
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "详情", notes = "详情")
    public R<MiniSdBizDef> detail(@Valid IdDTO dto) {
        MiniSdBizDef detail = miniSdBizDefService.getById(dto.getId());
        if (detail == null) {
            throw new ServiceException("查无此数据");
        }

        return R.data(detail);
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-08-21
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody MiniSdBizDef miniSdBizDef) {

        if (miniSdBizDef.getId() == null) {
            // 新增
            miniSdBizDef.setIsDeleted(0);
            miniSdBizDef.setCreateUser(AuthUtil.getUserId());
            miniSdBizDef.setCreateTime(new Date());
            miniSdBizDef.setEnterpriseId(sysUserService.getEnterPriseId());

        } else {
            // 编辑
            miniSdBizDef.setUpdateUser(AuthUtil.getUserId());
            miniSdBizDef.setUpdateTime(new Date());
        }

        // 保存
        boolean bo = miniSdBizDefService.saveOrUpdate(miniSdBizDef);
        if (!bo) {
            throw new ServiceException("保存失败");
        }

        return R.success("保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2023-08-21
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdDTO dto) {
        // 查询
        MiniSdBizDef miniSdBizDef = miniSdBizDefService.getById(dto.getId());
        if (miniSdBizDef == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        boolean bo = miniSdBizDefService.removeById(miniSdBizDef);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


    /**
     * 启用禁用
     *
     * @author szs
     * @date 2023-08-21
     */
    @PostMapping("/openOrClose")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "启用禁用", notes = "启用禁用")
    public R<String> openOrClose(@Valid @RequestBody IdDTO dto) {
        // 查询
        MiniSdBizDef miniSdBizDef = miniSdBizDefService.getById(dto.getId());
        if (miniSdBizDef == null) {
            throw new ServiceException("查无此数据");
        }

        // 启用禁用
        miniSdBizDef.setStatus(miniSdBizDef.getStatus() == 1 ? 0 : 1);
        miniSdBizDef.setUpdateUser(AuthUtil.getUserId());
        miniSdBizDef.setUpdateTime(new Date());

        // 保存
        boolean bo = miniSdBizDefService.updateById(miniSdBizDef);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("操作成功");
    }


    /**
     * 获取标准模板树
     *
     * @author szs
     * @date 2023-08-21
     */
    @GetMapping("/getMiniSdBizDefTree")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "获取标准模板树", notes = "获取标准模板树（主键ID）")
    public R<List<MiniSdBizDefTabResult>> getMiniSdBizDefTree(@Valid IdDTO dto) {

        return R.data(miniSdBizDefService.getMiniSdBizDefTree(dto.getId()));
    }


    /**
     * 获取标准模板配置
     *
     * @author szs
     * @date 2023-08-22
     */
    @GetMapping("/getMiniSdBizDefConfig")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "获取标准模板配置", notes = "获取标准模板配置（主键ID）")
    public R<JSONObject> getMiniSdBizDefConfig(@Valid IdDTO dto) {

        return R.data(miniSdBizDefService.getConfigJson(dto.getId()));
    }


    /**
     * 复制
     *
     * @author szs
     * @date 2023-10-23
     */
    @PostMapping("/copy")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "复制", notes = "复制")
    public R<String> copy(@Valid @RequestBody CopySdBizDefDTO dto) {

        // 复制
        miniSdBizDefService.copy(dto);

        return R.success("操作成功");
    }


    /**
     * 转化为配置JSON
     *
     * @author szs
     * @date 2023-12-01
     */
    @PostMapping("/toConfigJson")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "转化为配置JSON", notes = "转化为配置JSON")
    public R<String> toConfigJson(@Valid @RequestBody IdDTO dto) {

        // 转化为配置JSON
        miniSdBizDefService.toConfigJson(dto.getId());

        return R.success("操作成功");
    }


    /**
     * 获取标准模板对象数据
     *
     * @author szs
     * @date 2024-01-05
     */
    @GetMapping("/getMiniSdBizDefDataObject")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "获取标准模板对象数据", notes = "获取标准模板对象数据")
    public R<JSONObject> getMiniSdBizDefDataObject(@Valid IdDTO dto) {

        return R.data(JSONObject.parseObject(miniSdBizDefService.getDataObject(dto.getId())));
    }


}
