package cn.topcheer.halberd.app.controller.framework.sys;


import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.framework.dto.sys.SysRoleDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysRoleVO;
import cn.topcheer.halberd.app.api.framework.wrapper.SysRoleWrapper;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysRoleServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.biz.modules.base.entity.HalberdUser;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import cn.topcheer.halberd.biz.common.cache.SysCache;
import cn.topcheer.halberd.biz.common.cache.UserCache;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.constant.AuthConstant;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import cn.topcheer.halberd.biz.modules.system.vo.GrantVO;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springblade.core.cache.constant.CacheConstant.SYS_CACHE;

/**
 * 控制器
 *
 * @author Chill
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.APPLICATION_SYSTEM_NAME + "/role")
@Api(value = "角色管理", tags = "用户体系-角色管理")
public class SysRoleController extends BladeController {


    private final SysRoleServiceImpl roleService;

    private final SysUserServiceImpl sysUserService;


    /**
     * 全部列表
     *
     * @author szs
     * @date 2023-10-31
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部列表", notes = "全部列表")
    public R<List<SysRole>> all() {
        HqlBuilder<SysRole> builder = HqlBuilder.builder();
        builder.eq(SysRole::getEnable, 1);
        builder.addOrder(SysRole::getSeq, "asc");
        return R.data(roleService.find(builder));
    }


    /**
     * 分页列表
     *
     * @author szs
     * @date 2023-10-30
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页列表", notes = "分页列表")
    public PageResult<List<SysRoleVO>> page(@ApiIgnore SysRoleVO sysRole, Query query) {
        Page page = Page.of(query.getCurrent(), query.getSize());

        HqlBuilder<SysRole> builder = HqlBuilder.builder();
        builder.like(StringUtils.isNotBlank(sysRole.getRoleName()), SysRole::getRoleName, "%" + sysRole.getRoleName() + "%");
        builder.like(StringUtils.isNotBlank(sysRole.getRoleCode()), SysRole::getRoleCode, "%" + sysRole.getRoleCode() + "%");

        // 查询自己单位或者公共单位
        if (StringUtils.isNotBlank(sysRole.getEnterPriseId())) {
            builder.addLeftBracket();
            builder.eq("enter_prise_id", sysRole.getEnterPriseId());
            builder.addOr();
            builder.eq(SysRole::getIsPublic, 1);
            builder.addRightBracket();
        }

        builder.eq(sysRole.getIsNext() != null && sysRole.getIsNext() == 1, "pmsEnterprise.pmsParentEnterprise.id", sysUserService.getEnterPriseId());
        builder.eq(sysRole.getIsPublic() != null, SysRole::getIsPublic, sysRole.getIsPublic());
        builder.eq(SysRole::getEnable, 1);
        builder.addOrder(SysRole::getSeq, "asc");
        PageResult<List<SysRole>> pages = roleService.getPagination(page, builder);

        return PageResult.data(SysRoleWrapper.build().listVO(pages.getData()), pages.getTotal());
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-10-30
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody SysRoleDTO dto) {

        // 清除缓存
        CacheUtil.clear(SYS_CACHE);
        CacheUtil.clear(SYS_CACHE, Boolean.FALSE);

        // 保存
        roleService.submit(dto);

        return R.success("保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2023-10-30
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdStrDTO dto) {

        // 清除缓存
        CacheUtil.clear(SYS_CACHE);
        CacheUtil.clear(SYS_CACHE, Boolean.FALSE);

        // 查询
        SysRole sysRole = roleService.getById(dto.getId());
        if (sysRole == null) {
            throw new ServiceException("查无此数据");
        }

        // 保存
        boolean bo = roleService.deleteById(dto.getId());
        if (!bo) {
            throw new ServiceException("删除失败");
        }

        return R.success("删除成功");
    }


    /**
     * 批量删除
     *
     * @author szs
     * @date 2023-10-30
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "批量删除", notes = "传入ids")
    public R<Boolean> remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {

        // 清除缓存
        CacheUtil.clear(SYS_CACHE);
        CacheUtil.clear(SYS_CACHE, Boolean.FALSE);

        // 批量删除
        return R.status(roleService.deleteByIds(Func.toStrList(ids)));
    }


    /**
     * 设置角色权限
     *
     * @author szs
     * @date 2023-11-01
     */
    @PostMapping("/grant")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
    public R<String> grant(@RequestBody GrantVO grantVO) {
        CacheUtil.clear(SYS_CACHE);
        CacheUtil.clear(SYS_CACHE, Boolean.FALSE);

        // 权限设置
        roleService.grant(grantVO.getRoleIds(), grantVO.getMenuIds(), grantVO.getDataScopeIds(), grantVO.getApiScopeIds());

        return R.success("设置成功");
    }


    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入role")
    public R<SysRoleVO> detail(SysRole role) {
        SysRole detail = roleService.findById(role.getId());
        return R.data(SysRoleWrapper.build().entityVO(detail));
    }


    /**
     * 获取角色树形结构
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<SysRoleVO>> tree(BladeUser bladeUser) {
        List<SysRoleVO> tree = roleService.tree();
        return R.data(tree);
    }

    /**
     * 获取指定角色树形结构
     */
    @GetMapping("/tree-by-id")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<SysRoleVO>> treeById(String roleId, BladeUser bladeUser) {
        SysRole role = (SysRole) SysCache.getRole(roleId);
        List<SysRoleVO> tree = roleService.tree();
        return R.data(tree);
    }


    /**
     * 下拉数据源
     */
    @PreAuth(AuthConstant.PERMIT_ALL)
    @GetMapping("/select")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "下拉数据源", notes = "传入id集合")
    public R<List<SysRole>> select(String userId, String roleId) {
        if (Func.isNotEmpty(userId)) {
            HalberdUser user = UserCache.getUser(userId);
            roleId = user.getRoleId();
        }
        List<SysRole> list = new ArrayList<>(); //roleService.list(Wrappers.<SysRole>lambdaQuery().in(SysRole::getId, Func.toLongList(roleId)));
        return R.data(list);
    }

}
