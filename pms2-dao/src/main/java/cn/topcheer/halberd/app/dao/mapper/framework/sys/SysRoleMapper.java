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
package cn.topcheer.halberd.app.dao.mapper.framework.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysRoleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param role
	 * @return
	 */
	List<SysRoleVO> selectRolePage(IPage page, SysRoleVO role);

	/**
	 * 获取树形节点
	 *
	 * @param tenantId
	 * @param excludeRole
	 * @return
	 */
	List<SysRoleVO> tree(String tenantId, String excludeRole);

	/**
	 * 获取角色名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getRoleNames(String[] ids);

	/**
	 * 获取角色名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getRoleAliases(String[] ids);

}
