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

import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysRoleVO;
import cn.topcheer.halberd.biz.common.cache.SysCache;
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
public class SysRoleWrapper extends BaseEntityWrapper<SysRole, SysRoleVO> {

    public static SysRoleWrapper build() {
        return new SysRoleWrapper();
    }

    @Override
    public SysRoleVO entityVO(SysRole role) {
        SysRoleVO roleVO = Objects.requireNonNull(BeanUtil.copy(role, SysRoleVO.class));
        if (Func.equals(role.getParentId(), BladeConstant.TOP_PARENT_ID)) {
            roleVO.setParentName(BladeConstant.TOP_PARENT_NAME);
        } else {
            SysRole parent = (SysRole) SysCache.getRole(role.getParentId());
            if (parent != null) {
                roleVO.setParentName(parent.getRoleName());
            }
        }

        if (role.getPmsEnterprise() != null) {
            roleVO.setEnterPriseId(role.getPmsEnterprise().getId());
            roleVO.setEnterPriseName(role.getPmsEnterprise().getName());
        }

        return roleVO;
    }


    public List listNodeVO(List<SysRole> list) {
        List<SysRoleVO> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }

}
