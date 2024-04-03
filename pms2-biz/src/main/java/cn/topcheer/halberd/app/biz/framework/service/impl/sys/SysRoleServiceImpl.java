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
package cn.topcheer.halberd.app.biz.framework.service.impl.sys;

import cn.topcheer.halberd.app.api.framework.dto.sys.SysRoleDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysRoleService;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysRoleVO;
import cn.topcheer.halberd.app.api.framework.wrapper.SysRoleWrapper;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.SysRoleDao;
import cn.topcheer.halberd.biz.modules.system.entity.RoleMenu;
import cn.topcheer.halberd.biz.modules.system.entity.RoleScope;
import cn.topcheer.halberd.biz.modules.system.service.IRoleMenuService;
import cn.topcheer.halberd.biz.modules.system.service.IRoleScopeService;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static cn.topcheer.halberd.biz.common.constant.CommonConstant.API_SCOPE_CATEGORY;
import static cn.topcheer.halberd.biz.common.constant.CommonConstant.DATA_SCOPE_CATEGORY;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@Validated
@AllArgsConstructor
public class SysRoleServiceImpl extends GenericService<SysRole> implements ISysRoleService {

    private final IRoleMenuService roleMenuService;
    private final IRoleScopeService roleScopeService;

    private final PmsEnterpriseServiceImpl pmsEnterpriseService;

    private final SysUserService sysUserService;


    public SysRoleDao getSysRoleDao() {
        return (SysRoleDao) this.getGenericDao();
    }

    @Autowired
    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.setGenericDao(sysRoleDao);
    }

    @Override
    public PageResult<List<SysRole>> selectRolePage(Page page, SysRoleVO role) {

        return this.getPagination(page, role);
        //return page.setRecords(baseMapper.selectRolePage(page, role));
    }

    @Override
    public SysRole newRole() {
        return new SysRole();
    }


    /**
     * 保存
     *
     * @param dto SysRoleDTO
     * @author szs
     * @date 2023-10-30
     */
    @Override
    public void submit(SysRoleDTO dto) {
        if (!AuthUtil.isAdministrator()) {
            if (Func.toStr(dto.getRoleCode()).equals(RoleConstant.ADMINISTRATOR)) {
                throw new ServiceException("无权限创建超管角色！");
            }
        }

        // 新增或保存
        SysRole sysRole = new SysRole();
        if (StringUtils.isBlank(dto.getId())) {
            // 新增时，生成主键ID
            dto.setId(Util.NewGuid());
            sysRole.setEnable(1);

        } else {
            // 编辑时，先查询原数据
            sysRole = this.getById(dto.getId());
            if (sysRole == null) {
                throw new ServiceException(StringUtil.format("查无此数据"));
            }

        }

        // 复制数据
        BeanUtil.copyProperties(dto, sysRole);

        // 所属单位
        if (StringUtils.isNotBlank(dto.getEnterPriseId())) {
            sysRole.setPmsEnterprise(pmsEnterpriseService.getById(dto.getEnterPriseId()));
        } else {
            sysRole.setPmsEnterprise(null);
        }

        if (sysRole.getIsPublic() == null) {
            sysRole.setIsPublic(0);
        }

        // 保存
        this.saveOrUpdate(sysRole);
    }


    @Override
    public List<SysRoleVO> tree() {
        String userRole = AuthUtil.getUserRole();
        String excludeRole = null;
        if (!CollectionUtil.contains(Func.toStrArray(userRole), RoleConstant.ADMIN) && !CollectionUtil.contains(Func.toStrArray(userRole), RoleConstant.ADMINISTRATOR)) {
            excludeRole = RoleConstant.ADMIN;
        }

        List<SysRole> list = this.findAll();
        List<SysRoleVO> listVO = (new SysRoleWrapper()).listVO(list);

        //List<SysRole> list=baseMapper.tree(tenantId, excludeRole);
        return ForestNodeMerger.merge(listVO);
    }


    /**
     * 权限配置
     *
     * @param roleIds      角色id集合
     * @param menuIds      菜单id集合
     * @param dataScopeIds 数据权限id集合
     * @param apiScopeIds  接口权限id集合
     * @author szs
     * @date 2023-11-01
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grant(@NotEmpty List<String> roleIds, List<String> menuIds, List<String> dataScopeIds, List<String> apiScopeIds) {

        // 设置菜单权限
        grantRoleMenu(roleIds, menuIds);

        // 设置数据权限
        grantDataScope(roleIds, dataScopeIds);

        // 设置API权限
        grantApiScope(roleIds, apiScopeIds);

    }


    /**
     * 设置角色菜单权限
     *
     * @param roleIds 角色ids
     * @param menuIds 菜单ids
     * @return boolean
     * @author szs
     * @date 2023-11-01
     */
    private boolean grantRoleMenu(List<String> roleIds, List<String> menuIds) {
        // 删除角色配置的菜单集合
        roleMenuService.remove(Wrappers.<RoleMenu>update().lambda().in(RoleMenu::getRoleId, roleIds));

        // 组装配置
        List<RoleMenu> roleMenus = new ArrayList<>();
        roleIds.forEach(roleId -> menuIds.forEach(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenus.add(roleMenu);
        }));

        // 新增配置
        roleMenuService.saveBatch(roleMenus);

        return true;
    }


    /**
     * 递归更新下级角色菜单权限-贵州项目角色没有下级，此方法不用调用
     *
     * @param roleIds 角色ids
     * @param menuIds 菜单ids
     * @author szs
     * @date 2023-11-01
     */
    private void recursionRoleMenu(List<String> roleIds, List<String> menuIds) {
        roleIds.forEach(roleId -> this.find(HqlBuilder.builder(SysRole.class).eq(SysRole::getParentId, roleId)).forEach(role -> {
            List<RoleMenu> roleMenuList = roleMenuService.list(Wrappers.<RoleMenu>query().lambda().eq(RoleMenu::getRoleId, ((SysRole) role).getId()));
            // 子节点过滤出父节点删除的菜单集合
            List<String> collectRoleMenuIds = roleMenuList.stream().map(RoleMenu::getMenuId).filter(menuId -> !menuIds.contains(menuId)).collect(Collectors.toList());
            if (collectRoleMenuIds.size() > 0) {
                // 删除子节点权限外的菜单集合
                roleMenuService.remove(Wrappers.<RoleMenu>update().lambda().eq(RoleMenu::getRoleId, ((SysRole) role).getId()).in(RoleMenu::getMenuId, collectRoleMenuIds));
                // 递归设置下属角色菜单集合
                recursionRoleMenu(Collections.singletonList(((SysRole) role).getId()), menuIds);
            }
        }));
    }


    /**
     * 设置数据权限
     *
     * @param roleIds      角色ids
     * @param dataScopeIds 数据ids
     * @return boolean
     * @author szs
     * @date 2023-11-01
     */
    private boolean grantDataScope(List<String> roleIds, List<String> dataScopeIds) {
        // 删除角色配置的数据权限集合
        roleScopeService.remove(Wrappers.<RoleScope>update().lambda().eq(RoleScope::getScopeCategory, DATA_SCOPE_CATEGORY).in(RoleScope::getRoleId, roleIds));

        // 组装配置
        List<RoleScope> roleDataScopes = new ArrayList<>();
        roleIds.forEach(roleId -> dataScopeIds.forEach(scopeId -> {
            RoleScope roleScope = new RoleScope();
            roleScope.setScopeCategory(DATA_SCOPE_CATEGORY);
            roleScope.setRoleId(roleId);
            roleScope.setScopeId(scopeId);
            roleDataScopes.add(roleScope);
        }));

        // 新增配置
        roleScopeService.saveBatch(roleDataScopes);

        return true;
    }


    /**
     * 设置API权限
     *
     * @param roleIds     角色ids
     * @param apiScopeIds API权限
     * @return boolean
     * @author szs
     * @date 2023-11-01
     */
    private boolean grantApiScope(List<String> roleIds, List<String> apiScopeIds) {
        // 删除角色配置的接口权限集合
        roleScopeService.remove(Wrappers.<RoleScope>update().lambda().eq(RoleScope::getScopeCategory, API_SCOPE_CATEGORY).in(RoleScope::getRoleId, roleIds));

        // 组装配置
        List<RoleScope> roleApiScopes = new ArrayList<>();
        roleIds.forEach(roleId -> apiScopeIds.forEach(scopeId -> {
            RoleScope roleScope = new RoleScope();
            roleScope.setScopeCategory(API_SCOPE_CATEGORY);
            roleScope.setScopeId(scopeId);
            roleScope.setRoleId(roleId);
            roleApiScopes.add(roleScope);
        }));

        // 新增配置
        roleScopeService.saveBatch(roleApiScopes);

        return true;
    }


    @Override
    public String getRoleIds(String roleNames) {
        List<SysRole> roleList = this.getQuery("from SysRole t where roleName in :roleNames")
                .setParameterList("roleNames", Func.toStrList(roleNames)).list();

        if (roleList != null && roleList.size() > 0) {
            return roleList.stream().map(role -> Func.toStr(role.getId())).distinct().collect(Collectors.joining(","));
        }
        return null;
    }

    @Override
    public List<String> getRoleNames(String roleIds) {
        List<SysRole> roleList = this.getQuery("from SysRole t where id in :ids")
                .setParameterList("ids", Func.toStrList(roleIds)).list();
        return roleList.stream().map(r -> r.getRoleName()).collect(Collectors.toList());
    }


    @Override
    public SysRole getById(String id) {
        return this.findById(id);
    }

    @Override
    public List<String> getRoleAliases(String roleIds) {
        String hql = "select t.roleCode from SysRole t where id in :ids";

        List list = this.getQuery(hql).setParameterList("ids", Func.toStrList(roleIds)).list();

        return list;
    }


    /**
     * 获取角色，用于流程，人员配置，选择角色
     *
     * @param roleName 角色名称
     * @param parentId 上级id
     * @return
     */
    @Override
    public List<SysRole> search(String roleName, String parentId) {
        HqlBuilder<SysRole> hqlBuilder = HqlBuilder.builder();
        if (Func.isNotEmpty(roleName)) {
            hqlBuilder.eq(SysRole::getRoleName, roleName);
        }
        if (Func.isNotEmpty(parentId)) {
            hqlBuilder.eq(SysRole::getParentId, parentId);
        }

        // 查询自己单位或者公共单位
        hqlBuilder.addLeftBracket();
        hqlBuilder.eq("enter_prise_id", sysUserService.getEnterPriseId());
        hqlBuilder.addOr();
        hqlBuilder.eq(SysRole::getIsPublic, 1);
        hqlBuilder.addRightBracket();
        hqlBuilder.eq(SysRole::getEnable, 1);
        hqlBuilder.addOrder(SysRole::getSeq, "asc");
        List<SysRole> roleList = this.find(hqlBuilder);
        return SysRoleWrapper.build().listNodeVO(roleList);
    }

    @Override
    public boolean removeRole(String ids) {

        HqlBuilder hqlBuilder = HqlBuilder.builder(SysRole.class).in(SysRole::getParentId, Func.toStrList(ids));

        Long cnt = this.findCount(hqlBuilder);

        //Long cnt = baseMapper.selectCount(Wrappers.<SysRole>query().lambda().in(SysRole::getParentId, Func.toStrList(ids)));
        if (cnt > 0L) {
            throw new ServiceException("请先删除子节点!");
        }
        return this.deleteByIds(Func.toStrList(ids));
    }


    //@Override
    public boolean deleteLogic(@NotEmpty List<String> ids) {
//		BladeUser user = AuthUtil.getUser();
//		List<SysRole> list = new ArrayList<>();
//		ids.forEach(id -> {
//			SysRole entity = BeanUtil.newInstance(currentModelClass());
//
//			entity.setId(id);
//			list.add(entity);
//		});
//		return super.updateBatchById(list) && super.removeByIds(ids);

        return this.deleteByIds(ids);
    }


}
