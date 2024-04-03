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

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysUserVO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;

import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class SysUserWrapper extends BaseEntityWrapper<SysUser, SysUserVO> {

    public static SysUserWrapper build() {
        return new SysUserWrapper();
    }

    @Override
    public SysUserVO entityVO(SysUser user) {
        SysUserVO userVO = Objects.requireNonNull(BeanUtil.copy(user, SysUserVO.class));

        userVO.setRoleId(user.getRoleId());
        userVO.setRoleIds(Func.toStrList(user.getRoleId()));
        userVO.setRoleName(user.getRoleName());

        userVO.setEnterPriseId(user.getEnterPriseId());
        userVO.setEnterPriseIds(Func.toStrList(user.getEnterPriseId()));
        userVO.setEnterPriseName(user.getEnterPriseName());

        userVO.setDeptId(user.getDeptId());
        userVO.setDeptIds(Func.toStrList(user.getDeptId()));
        userVO.setDeptName(user.getDeptName());


        return userVO;
    }


}
