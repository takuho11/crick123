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
package cn.topcheer.halberd.app.api.framework.wrapper;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysDept;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysDeptVO;
import cn.topcheer.halberd.biz.common.cache.DictCache;
import cn.topcheer.halberd.biz.common.cache.SysCache;
import cn.topcheer.halberd.biz.common.enums.DictEnum;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class SysDeptWrapper extends BaseEntityWrapper<SysDept, SysDeptVO> {


	public static SysDeptWrapper build() {
		return new SysDeptWrapper();
	}

	@Override
	public SysDeptVO entityVO(SysDept dept) {
		SysDeptVO deptVO = Objects.requireNonNull(BeanUtil.copy(dept, SysDeptVO.class));
		if (Func.equals(dept.getParentId(), BladeConstant.TOP_PARENT_ID)) {
			deptVO.setParentName(BladeConstant.TOP_PARENT_NAME);
		} else {
			SysDept parent = (SysDept) SysCache.getDept(dept.getParentId());
			if(parent!=null) {
				deptVO.setParentName(parent.getDeptName());
			}
		}
		String category = DictCache.getValue(DictEnum.ORG_CATEGORY, dept.getDeptCategory());
		deptVO.setDeptCategoryName(category);
		return deptVO;
	}

	public List   listNodeVO(List<SysDept> list) {
		List<SysDeptVO> collect = list.stream().map(dept -> {
			SysDeptVO deptVO = BeanUtil.copy(dept, SysDeptVO.class);
			String category = DictCache.getValue(DictEnum.ORG_CATEGORY, dept.getDeptCategory());
			Objects.requireNonNull(deptVO).setDeptCategoryName(category);
			return deptVO;
		}).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

	public List listNodeLazyVO(List<SysDeptVO> list) {
		List<SysDeptVO> collect = list.stream().peek(dept -> {
			String category = DictCache.getValue(DictEnum.ORG_CATEGORY, dept.getDeptCategory());
			Objects.requireNonNull(dept).setDeptCategoryName(category);
		}).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}


}
