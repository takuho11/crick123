package cn.topcheer.halberd.app.controller.framework.sys;


import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.framework.dto.IdsDTO;
import cn.topcheer.halberd.app.api.framework.dto.sys.BatchSaveSysUserPhoneDTO;
import cn.topcheer.halberd.app.api.framework.dto.sys.SysUserDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysUserVO;
import cn.topcheer.halberd.app.api.framework.wrapper.SysUserWrapper;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserService;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;

import cn.topcheer.halberd.biz.modules.system.excel.UserExcel;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.sys.SysUserPhone;
import cn.topcheer.pms2.biz.sys.SysUserPhoneService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springblade.core.cache.constant.CacheConstant.USER_CACHE;

/**
 * 控制器
 *
 * @author Chill
 */
@NonDS
@ApiIgnore
@RestController
@RequestMapping(AppConstant.APPLICATION_USER_NAME)
@AllArgsConstructor
@Api(value = "用户管理", tags = "用户体系-用户管理")
public class SysUserController {


    private final SysUserServiceImpl userService;

    private final SysUserService sysUserService;

    private final SysUserPhoneService sysUserPhoneService;


    /**
     * 全部列表
     *
     * @author szs
     * @date 2023-10-31
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部列表", notes = "全部列表")
    public R<List<SysUser>> all() {
        HqlBuilder<SysUser> builder = HqlBuilder.builder();
        builder.eq(SysUser::getIsDeleted, 0);
        return R.data(userService.find(builder));
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
    public PageResult<List<SysUserVO>> page(@ApiIgnore SysUserVO sysUser, Query query) {
        Page page = Page.of(query.getCurrent(), query.getSize());

        HqlBuilder<SysUser> builder = HqlBuilder.builder();
        builder.like(StringUtils.isNotBlank(sysUser.getUserid()), SysUser::getUserid, "%" + sysUser.getUserid() + "%");
        builder.like(StringUtils.isNotBlank(sysUser.getName()), SysUser::getName, "%" + sysUser.getName() + "%");
        builder.eq(SysUser::getIsDeleted, 0);
        PageResult<List<SysUser>> pages = userService.getPagination(page, builder);
        return PageResult.data(SysUserWrapper.build().listVO(pages.getData()), pages.getTotal());
    }

    /**
     * 我的分页列表
     *
     * @author szs
     * @date 2024-03-24
     */
    @GetMapping("/myPage")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页列表", notes = "分页列表")
    public PageResult<List<Map>> myPage(@ApiIgnore SysUserVO sysUser, Query query) {

        return sysUserService.myPage(sysUser, query);
    }


    /**
     * 下级分页列表
     *
     * @author szs
     * @date 2023-10-30
     */
    @GetMapping("/nextPageOld")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "下级分页列表", notes = "下级分页列表")
    public PageResult<List<SysUserVO>> nextPageOld(@ApiIgnore SysUserVO sysUser, Query query) {
        Page page = Page.of(query.getCurrent(), query.getSize());

        HqlBuilder<SysUser> builder = HqlBuilder.builder();
        builder.like(StringUtils.isNotBlank(sysUser.getUserid()), "t.userid", "%" + sysUser.getUserid() + "%");
        builder.like(StringUtils.isNotBlank(sysUser.getName()), "t.name", "%" + sysUser.getName() + "%");
        builder.eq("t.isDeleted", 0);
        builder.eq("a.pmsEnterprise.pmsParentEnterprise.id", userService.getEnterPriseId());
        builder.addJoin("t.sysIdentitys", "a", "inner join");

        // 分页查询
        PageResult<List<SysUser>> pages = userService.getPagination(page, builder);
        List<SysUser> list = new ArrayList<>();
        for (Object object : pages.getData()) {
            Object[] objectArr = (Object[]) object;
            list.add((SysUser) objectArr[0]);
        }

        return PageResult.data(SysUserWrapper.build().listVO(list), pages.getTotal());
    }


    /**
     * 下级分页列表
     *
     * @author szs
     * @date 2023-10-30
     */
    @GetMapping("/nextPage")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "下级分页列表", notes = "下级分页列表")
    public PageResult<List<Map>> nextPage(@ApiIgnore SysUserVO sysUser, Query query) {

        return sysUserService.nextPage(sysUser, query);
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-10-30
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "保存", notes = "保存")
    public R<Boolean> submit(@Valid @RequestBody SysUserDTO dto) {

        // 清理缓存
        CacheUtil.clear(USER_CACHE);

        // 保存数据
        userService.submit(dto);

        return R.success("保存成功");
    }


    /**
     * 删除
     *
     * @author szs
     * @date 2023-10-30
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除", notes = "删除")
    public R<String> delete(@Valid @RequestBody IdStrDTO dto) {

        // 清理缓存
        CacheUtil.clear(USER_CACHE);

        // 查询
        SysUser sysUser = userService.getById(dto.getId());
        if (sysUser == null) {
            throw new ServiceException("查无此数据");
        }

        // 逻辑删除
        sysUser.setIsDeleted(1);

        // 保存
        userService.update(sysUser);

        return R.success("删除成功");
    }


    /**
     * 重置密码
     *
     * @author szs
     * @date 2023-10-30
     */
    @PostMapping("/resetPassword")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "初始化密码", notes = "传入userId集合")
    public R<String> resetPassword(@Valid @RequestBody IdsDTO dto) {

        // 重置密码
        userService.resetPassword(dto.getIds());

        return R.success("重置成功");
    }


    /**
     * 获取用户手机号列表
     *
     * @author szs
     * @date 2024-02-27
     */
    @GetMapping("/getUserPhoneList")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "获取用户手机号列表", notes = "获取用户手机号列表")
    public R<List<SysUserPhone>> getUserPhoneList(@Valid IdStrDTO dto) {

        return R.data(sysUserPhoneService.getUserPhoneList(dto.getId()));
    }


    /**
     * 批量保存用户手机号
     *
     * @author szs
     * @date 2024-02-27
     */
    @PostMapping("/batchSaveUserPhone")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "批量保存用户手机号", notes = "批量保存用户手机号")
    public R<String> batchSaveUserPhone(@Valid @RequestBody BatchSaveSysUserPhoneDTO dto) {

        // 批量保存用户手机号
        sysUserPhoneService.batchSave(dto.getUserid(), dto.getUserPhoneList());

        return R.success("保存成功");
    }


    /**
     * 查询当前用户
     */
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "查看详情", notes = "传入id")
    @GetMapping("/currentUser")
    public R<SysUserVO> currentUser() {

        // 查询
        SysUser sysUser = userService.getById(AuthUtil.getUserId());

        return R.data(SysUserWrapper.build().entityVO(sysUser));
    }


    /**
     * 修改密码
     */
    @PostMapping("/update-password")
    @ApiOperationSupport(order = 21)
    @ApiOperation(value = "修改密码", notes = "传入密码")
    public R<String> updatePassword(@ApiParam(value = "旧密码", required = true) @RequestParam String oldPassword,
                                    @ApiParam(value = "新密码", required = true) @RequestParam String newPassword) {
        // 修改密码
        userService.updatePassword(oldPassword, newPassword);

        return R.success("保存成功");
    }


    /**
     * 修改基本信息
     */
    @PostMapping("/update-info")
    @ApiOperationSupport(order = 22)
    @ApiOperation(value = "修改基本信息", notes = "传入User")
    public R<String> updateInfo(@Valid @RequestBody SysUser user) {
        CacheUtil.clear(USER_CACHE);

        // 修改基本信息
        userService.updateUserInfo(user);

        return R.success("保存成功");
    }


    /**
     * 查询单条
     */
    @ApiOperationSupport(order = 23)
    @ApiOperation(value = "查看详情", notes = "传入id")
    @GetMapping("/detail")
    public R<SysUserVO> detail(SysUser user) {
        SysUser detail = userService.getById(user.getId());
        return R.data(SysUserWrapper.build().entityVO(detail));
    }

    /**
     * 查询单条
     */
    @ApiOperationSupport(order = 24)
    @ApiOperation(value = "查看详情", notes = "传入id")
    @GetMapping("/info")
    public R<SysUserVO> info(BladeUser user) {
        SysUser detail = userService.getById(user.getUserId());
        return R.data(SysUserWrapper.build().entityVO(detail));
    }


    /**
     * 用户列表
     */
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号名", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "realName", value = "姓名", paramType = "query", dataType = "string")
    })
    @ApiOperationSupport(order = 25)
    @ApiOperation(value = "列表", notes = "传入account和realName")
    public PageResult<List<SysUserVO>> list(@ApiIgnore @RequestParam Map<String, Object> user, Query query, BladeUser bladeUser) {
        //QueryWrapper<SysUser> queryWrapper = Condition.getQueryWrapper(user, SysUser.class);
        SysUser sysUser = JSON.toJavaObject(new JSONObject(user), SysUser.class);

        Page page = Page.of(query.getCurrent(), query.getSize());
        PageResult<List<SysUser>> pages = userService.getPagination(page, HqlBuilder.builder(SysUser.class).eq(SysUser::getUserid, sysUser.getAccount()));  //.page(Condition.getPage(query), queryWrapper);

        return PageResult.data(SysUserWrapper.build().listVO(pages.getData()), pages.getTotal());

        //return R.data(SysUserWrapper.build().pageVO(pages));
    }


    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 26)
    @ApiOperation(value = "修改", notes = "传入User")
    public R update(@Valid @RequestBody SysUser user) {
        CacheUtil.clear(USER_CACHE);
        return R.status(userService.updateUser(user));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 27)
    @ApiOperation(value = "删除", notes = "传入id集合")
    //@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
    public R remove(@RequestParam String ids) {

        // 清理缓存
        CacheUtil.clear(USER_CACHE);

        return R.status(userService.removeUser(ids));
    }

    /**
     * 设置菜单权限
     */
    @PostMapping("/grant")
    @ApiOperationSupport(order = 28)
    @ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
    //@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
    public R grant(@ApiParam(value = "userId集合", required = true) @RequestParam String userIds,
                   @ApiParam(value = "roleId集合", required = true) @RequestParam String roleIds) {
        boolean temp = userService.grant(userIds, roleIds);
        return R.status(temp);
    }


    /**
     * 用户列表
     */
    @GetMapping("/user-list")
    @ApiOperationSupport(order = 29)
    @ApiOperation(value = "用户列表", notes = "传入user")
    public R<List<SysUser>> userList(SysUser user, BladeUser bladeUser) {
//        QueryWrapper<SysUser> queryWrapper = Condition.getQueryWrapper(user);
//        List<SysUser> list = userService.list(  queryWrapper);
//        return R.data(list);
        return R.data(new ArrayList<>());
    }

    /**
     * 导入用户
     */
    @PostMapping("import-user")
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "导入用户", notes = "传入excel")
    public R importUser(MultipartFile file, Integer isCovered) {
//        UserImporter userImporter = new UserImporter(userService, isCovered == 1);
//        ExcelUtil.save(file, userImporter, UserExcel.class);
        return R.success("操作成功");
    }

    /**
     * 导出用户
     */
    @GetMapping("export-user")
    @ApiOperationSupport(order = 33)
    @ApiOperation(value = "导出用户", notes = "传入user")
    public void exportUser(@ApiIgnore @RequestParam Map<String, Object> user, BladeUser bladeUser, HttpServletResponse response) {
        QueryWrapper<SysUser> queryWrapper = Condition.getQueryWrapper(user, SysUser.class);

//        queryWrapper.lambda().eq(User::getIsDeleted, BladeConstant.DB_NOT_DELETED);
//        List<UserExcel> list = userService.exportUser(queryWrapper);
//        ExcelUtil.export(response, "用户数据" + DateUtil.time(), "用户数据表", list, UserExcel.class);
    }

    /**
     * 导出模板
     */
    @GetMapping("export-template")
    @ApiOperationSupport(order = 33)
    @ApiOperation(value = "导出模板")
    public void exportUser(HttpServletResponse response) {
        List<UserExcel> list = new ArrayList<>();
        ExcelUtil.export(response, "用户数据模板", "用户数据表", list, UserExcel.class);
    }


    /**
     * 第三方注册用户
     */
    @PostMapping("/register-guest")
    @ApiOperationSupport(order = 33)
    @ApiOperation(value = "第三方注册用户", notes = "传入user")
    public R registerGuest(SysUser user, Long oauthId) {
        return R.status(userService.registerGuest(user, oauthId));
    }

    /**
     * 配置用户平台信息
     */
    @PostMapping("/update-platform")
    @ApiOperationSupport(order = 33)
    @ApiOperation(value = "配置用户平台信息", notes = "传入user")
    public R updatePlatform(String userId, Integer userType, String userExt) {
        return R.status(userService.updatePlatform(userId, userType, userExt));
    }

    /**
     * 查看平台详情
     */
    @ApiOperationSupport(order = 33)
    @ApiOperation(value = "查看平台详情", notes = "传入id")
    @GetMapping("/platform-detail")
    @PreAuth(RoleConstant.HAS_ROLE_ADMIN)
    public R<SysUserVO> platformDetail(SysUser user) {
        return R.data(userService.platformDetail(user));
    }


    /**
     * 用户列表查询
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "人员姓名", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "postName", value = "职位名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "current", value = "当前页数", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页数量", paramType = "query", dataType = "int")
    })
    @ApiOperationSupport(order = 33)
    @ApiOperation(value = "用户列表查询", notes = "用户列表查询")
    @GetMapping("/search/user")
    public PageResult<List<SysUserVO>> userSearch(@ApiIgnore SysUserVO user, @ApiIgnore Query query) {

        // PageResult<SysUser> pages =userService.selectUserSearch(user, query);

        return userService.selectUserSearch(user, query);
    }

    /**
     * 用户解锁
     */
    @PostMapping("/unlock")
    @ApiOperationSupport(order = 33)
    @ApiOperation(value = "账号解锁", notes = "传入id")
    public R unlock(String userIds) {
//        if (StringUtil.isBlank(userIds)) {
//            return R.fail("请至少选择一个用户");
//        }
//        List<SysUser> userList = userService.list(Wrappers.<SysUser>lambdaQuery().in(SysUser::getId, Func.toLongList(userIds)));
//        userList.forEach(user -> bladeRedis.set(CacheNames.tenantKey(user.getTenantId(), CacheNames.USER_FAIL_KEY, user.getAccount()),null));
        return R.success("操作成功");
    }

}
