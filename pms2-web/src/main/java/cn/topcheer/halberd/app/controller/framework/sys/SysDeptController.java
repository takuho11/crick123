/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package cn.topcheer.halberd.app.controller.framework.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysDept;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysDeptVO;
import cn.topcheer.halberd.app.api.framework.wrapper.SysDeptWrapper;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysDeptServiceImpl;
import cn.topcheer.halberd.biz.common.cache.DictCache;
import cn.topcheer.halberd.biz.common.cache.UserCache;
import cn.topcheer.halberd.biz.common.enums.DictEnum;
import cn.topcheer.halberd.biz.modules.base.entity.HalberdUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.constant.AuthConstant;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springblade.core.cache.constant.CacheConstant.SYS_CACHE;

/**
 * 控制器
 *
 * @author Chill
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.APPLICATION_SYSTEM_NAME + "/dept")
@Api(value = "部门管理", tags = "用户体系-部门管理")
@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
public class SysDeptController extends BladeController {

	private final SysDeptServiceImpl deptService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入dept")
	public R<SysDeptVO> detail(SysDept dept) {
		SysDept detail = deptService.getOne(Condition.getQueryWrapper(dept));
		return R.data(SysDeptWrapper.build().entityVO(detail));
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "fullName", value = "部门全称", paramType = "query", dataType = "string")
	})
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "列表", notes = "传入dept")
	public R<List<SysDeptVO>> list(@ApiIgnore @RequestParam Map<String, Object> dept, BladeUser bladeUser) {
		QueryWrapper<SysDept> queryWrapper = Condition.getQueryWrapper(dept, SysDept.class);
		List<SysDept> list = deptService.list((!bladeUser.getTenantId().equals(BladeConstant.ADMIN_TENANT_ID)) ? queryWrapper.lambda().eq(SysDept::getTenantId, bladeUser.getTenantId()) : queryWrapper);
		return R.data(SysDeptWrapper.build().listNodeVO(list));
	}

	/**
	 * 懒加载列表
	 */
	@GetMapping("/lazy-list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "fullName", value = "部门全称", paramType = "query", dataType = "string")
	})
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "懒加载列表", notes = "传入dept")
	public R<List<SysDeptVO>> lazyList(@ApiIgnore @RequestParam Map<String, Object> dept, String parentId, BladeUser bladeUser) {
		List<SysDeptVO> list = deptService.lazyList(bladeUser.getTenantId(), parentId, dept);
		return R.data(SysDeptWrapper.build().listNodeLazyVO(list));
	}

	/**
	 * 获取部门树形结构
	 */
	@GetMapping("/tree")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "树形结构", notes = "树形结构")
	public R<List<SysDeptVO>> tree(String tenantId, BladeUser bladeUser) {
		List<SysDeptVO> tree = deptService.tree(Func.toStrWithEmpty(tenantId, bladeUser.getTenantId()));
		return R.data(tree);
	}

	/**
	 * 懒加载获取部门树形结构
	 */
	@GetMapping("/lazy-tree")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "懒加载树形结构", notes = "树形结构")
	public R<List<SysDeptVO>> lazyTree(String tenantId, String parentId, BladeUser bladeUser) {
		List<SysDeptVO> tree = deptService.lazyTree( parentId);
		return R.data(tree);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入dept")
	public R submit(@Valid @RequestBody SysDept dept) {
		if (deptService.submit(dept)) {
			CacheUtil.clear(SYS_CACHE);
			// 返回懒加载树更新节点所需字段
			Kv kv = Kv.create().set("id", String.valueOf(dept.getId())).set("tenantId", dept.getTenantId())
				.set("deptCategoryName", DictCache.getValue(DictEnum.ORG_CATEGORY, dept.getDeptCategory()));
			return R.data(kv);
		}
		return R.fail("操作失败");
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		CacheUtil.clear(SYS_CACHE);
		return R.status(deptService.removeDept(ids));
	}

	/**
	 * 下拉数据源
	 */
	@PreAuth(AuthConstant.PERMIT_ALL)
	@GetMapping("/select")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "下拉数据源", notes = "传入id集合")
	public R<List<SysDept>> select(String userId, String deptId) {
		if (Func.isNotEmpty(userId)) {
			HalberdUser user = UserCache.getUser(userId);
			deptId = user.getDeptId();
		}
		List<SysDept> list = deptService.list(Wrappers.<SysDept>lambdaQuery().in(SysDept::getId, Func.toStrList(deptId)));
		return R.data(list);
	}


}
