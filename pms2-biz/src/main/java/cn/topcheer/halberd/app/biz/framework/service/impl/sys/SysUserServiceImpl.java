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

import cn.topcheer.halberd.app.api.framework.dto.sys.SysUserDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.*;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysUserService;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysUserVO;
import cn.topcheer.halberd.app.api.framework.wrapper.SysUserWrapper;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.SysUserDao;
import cn.topcheer.halberd.biz.common.cache.SysCache;
import cn.topcheer.halberd.biz.modules.auth.enums.UserEnum;
import cn.topcheer.halberd.biz.modules.base.entity.HalberdRole;
import cn.topcheer.halberd.biz.modules.base.entity.HalberdUser;
import cn.topcheer.halberd.biz.modules.system.entity.*;
import cn.topcheer.halberd.biz.modules.system.service.IUserDeptService;
import cn.topcheer.halberd.biz.modules.system.service.IUserOauthService;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends GenericService<SysUser> implements ISysUserService {
    private static final String GUEST_NAME = "guest";

    private final IUserDeptService userDeptService;
    private final IUserOauthService userOauthService;

    @Autowired
    private final SysIdentityServiceImpl sysIdentityService;
    @Autowired

    private final SysRoleServiceImpl sysRoleService;
    @Autowired
    private final PmsEnterpriseServiceImpl pmsEnterpriseService;
    @Autowired
    private final PmsDepartmentServiceImpl pmsDepartmentService;

    @Override
    public SysUser newUser() {
        return new SysUser();
    }

    public SysUserDao getSysUserDao() {
        return (SysUserDao) this.getGenericDao();
    }

    @Autowired
    public void setSysUsereDao(SysUserDao sysUserDao) {
        this.setGenericDao(sysUserDao);
    }

    /**
     * 保存
     *
     * @param dto SysUserDTO
     * @author szs
     * @date 2023-10-30
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(SysUserDTO dto) {
        // 判断是否重复
        checkIsExist(dto.getId(), dto.getUserid());

        // 新增或保存
        SysUser user;
        if (StringUtils.isBlank(dto.getId())) {

            user = new SysUser();
            // 新增时，生成主键ID
            user.setId(Util.NewGuid());
            user.setUserstate(1);
            user.setIsDeleted(0);

            // 加密
            if (Func.isNotEmpty(dto.getPassword())) {
                user.setPassword(this.encrypt(user, dto.getPassword()));
            }

        } else {
            // 编辑时，先查询原数据
            user = this.getById(dto.getId());
            if (user == null) {
                throw new ServiceException(StringUtil.format("当前用户 [{}] 不存在!", dto.getId()));
            }
        }

        // 复制
        BeanUtil.copyProperties(dto, user, "password", "salt", "id");

        if (Func.isEmpty(user.getSalt())) {
            user.setSalt(Util.NewGuid());
        }

        // 保存
        this.saveOrUpdate(user);

        // 新增用户，不提交到数据库会报 object references an unsaved transient instance
        this.getGenericDao().getCurrentSession().flush();

        // 先删除原来的中间表
        List<String> identityIds = user.getIdentityId();
        boolean bo;
        if (identityIds.size() > 0) {
            bo = sysIdentityService.deleteByIds(identityIds);
            if (!bo) {
                throw new ServiceException("删除中间表失败");
            }
        }

        // 添加新的中间表, 用户id-角色id
        saveUserRoles(dto.getRoleIds(), user, dto.getEnterPriseId(), dto.getDeptId());

    }

    /**
     * 检测是否存在
     *
     * @param id     主键id
     * @param userid 账号
     * @author szs
     * @date 2023-10-30
     */
    private void checkIsExist(String id, String userid) {
        HqlBuilder<SysUser> builder = HqlBuilder.builder();
        builder.neq(StringUtils.isNotBlank(id), SysUser::getId, id);
        builder.eq(SysUser::getUserid, userid);
        builder.eq(SysUser::getIsDeleted, 0);
        if (this.findCount(builder) > 0L) {
            throw new ServiceException(StringUtil.format("当前用户 [{}] 已存在!", userid));
        }
    }


    /**
     * 保存用户角色
     *
     * @param ids  IDS
     * @param user SysUser
     * @author szs
     * @date 2023-11-01
     */
    private void saveUserRoles(List<String> ids, SysUser user, String enterPriseId, String deptId) {
        if (ids == null || ids.size() == 0) {
            return;
        }

        // 获取单位
        PmsEnterprise enterprise = null;
        if (StringUtils.isNotBlank(enterPriseId)) {
            enterprise = pmsEnterpriseService.findById(enterPriseId);
        }

        // 获取部门
        PmsDepartment department = null;
        if (StringUtils.isNotBlank(deptId)) {
            department = pmsDepartmentService.findById(deptId);
        }

        boolean bo;
        Date date = new Date();
        for (String id : ids) {
            SysIdentity identity = new SysIdentity();
            identity.setId(Util.NewGuid());
            identity.setEnabled(0);
            identity.setCreatedate(date);
            identity.setSysUser(user);
            identity.setSysRole(sysRoleService.getById(id));
            identity.setPmsEnterprise(enterprise);
            identity.setPmsDepartment(department);
            bo = sysIdentityService.save(identity);
            if (!bo) {
                throw new ServiceException("保存中间表失败");
            }
        }

    }


    /**
     * 保存用户单位,（没有角色的情况，这个还有意义么？）
     *
     * @param ids  IDS
     * @param user SysUser
     * @author szs
     * @date 2023-11-01
     */
    private void saveUserEnterprises(List<String> ids, SysUser user) {
        if (ids != null && ids.size() > 0) {
            boolean bo;
            Date date = new Date();
            for (String id : ids) {
                SysIdentity identity = new SysIdentity();
                identity.setId(Util.NewGuid());
                identity.setEnabled(0);
                identity.setCreatedate(date);
                identity.setSysUser(user);
                //identity.setSysRole(new SysRole());
                identity.setPmsEnterprise(pmsEnterpriseService.getById(id));
                bo = sysIdentityService.save(identity);
                if (!bo) {
                    throw new ServiceException("删除中间表失败");
                }
            }
        }
    }


    /**
     * 保存用户-单位-角色
     * 实际上这种写法是不对的，因为这种写法隐含的意思就是一个用户有可能会有多个单位
     * 而在有多个单位的情况下，每个单位的角色不一定会相同，但用这个方法，没法做到这一点
     * 这么写的唯一目的就是兼容老系统。
     * 如果是新系统，有这类需求，应该是要有个单位角色列表，
     * 也就是在用户编辑界面，应该有个单位角色列表，每条记录是一个单位，一个或多个角色
     *
     * @param eids 单位ids
     * @param rids 角色ids
     * @param user SysUser
     * @date 2023-11-01
     */
    private void saveUserEnterprisesRoles(List<String> eids, List<String> rids, SysUser user) {
        if (eids != null && eids.size() > 0) {
            boolean bo;
            Date date = new Date();
            for (String eid : eids) {
                for (String rid : rids) {
                    SysIdentity identity = new SysIdentity();
                    identity.setId(Util.NewGuid());
                    identity.setEnabled(0);
                    identity.setCreatedate(date);
                    identity.setSysUser(user);
                    // identity.setSysRole(new SysRole());
                    identity.setSysRole(sysRoleService.getById(rid));
                    identity.setPmsEnterprise(pmsEnterpriseService.getById(eid));
                    bo = sysIdentityService.save(identity);
                    if (!bo) {
                        throw new ServiceException("删除中间表失败");
                    }
                }
            }
        }
    }

    /**
     * 保存用户部门
     *
     * @param ids  IDS
     * @param user SysUser
     * @author szs
     * @date 2023-11-01
     */
    private void saveUserDepartment(List<String> ids, SysUser user) {
        if (ids != null && ids.size() > 0) {
            boolean bo;
            Date date = new Date();
            for (String id : ids) {
                SysIdentity identity = new SysIdentity();
                identity.setId(Util.NewGuid());
                identity.setEnabled(0);
                identity.setCreatedate(date);
                identity.setSysUser(user);
                // identity.setSysRole(new SysRole());
                // identity.setPmsEnterprise(new PmsEnterprise());
                identity.setPmsDepartment(pmsDepartmentService.getById(id));
                bo = sysIdentityService.save(identity);
                if (!bo) {
                    throw new ServiceException("删除中间表失败");
                }
            }
        }
    }

    /**
     * 重置密码
     *
     * @param ids 用户ids
     * @author szs
     * @date 2023-10-30
     */
    @Override
    public void resetPassword(String ids) {
        HqlBuilder<SysUser> builder = HqlBuilder.builder();
        builder.in(SysUser::getId, Func.toStrList(ids));
        List<SysUser> sysUsers = this.find(builder);

        // 密码重置为123456
        for (SysUser user : sysUsers) {
            user.setPassword(this.encrypt(user, "GZ_kjt_8587"));
            this.update(user);
        }

    }


    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @author szs
     * @date 2024-01-25
     */
    @Override
    public void updatePassword(String oldPassword, String newPassword) {
        // 查询
        SysUser sysUser = this.getById(AuthUtil.getUserId());
        if (sysUser == null) {
            throw new ServiceException("暂无数据");
        }

        if (!this.encrypt(sysUser, oldPassword).equals(sysUser.getPassword())) {
            throw new ServiceException("原密码不正确");
        }

        // 设置新密码
        sysUser.setPassword(this.encrypt(sysUser, newPassword));

        // 保存
        this.update(sysUser);

    }


    /**
     * 修改用户基本信息
     *
     * @param user SysUser
     * @author szs
     * @date 2024-01-25
     */
    @Override
    public void updateUserInfo(SysUser user) {
        // 查询
        SysUser sysUser = this.getById(AuthUtil.getUserId());
        if (sysUser == null) {
            throw new ServiceException("暂无数据");
        }

        // 修改信息
        sysUser.setName(user.getName());
        sysUser.setSex(user.getSex());
        sysUser.setMobile(user.getMobile());
        sysUser.setEmail(user.getEmail());

        // 保存
        this.update(sysUser);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(SysUser user) {
        // String tenantId = user.getTenantId();
        HqlBuilder<SysUser> hqlBuilder = HqlBuilder.builder();
        Long userCount = findCount(
                hqlBuilder
                        .neq(true, SysUser::getId, user.getId())
                        .eq(SysUser::getUserid, user.getAccount())
        );
        if (userCount > 0L) {
            throw new ServiceException(StringUtil.format("当前用户 [{}] 已存在!", user.getAccount()));
        }
        //return updateUserInfo(user) && submitUserDept(user);

        return true;
    }


    private boolean submitUserDept(SysUser user) {
        List<String> deptIdList = Func.toStrList(user.getDeptId());
        List<UserDept> userDeptList = new ArrayList<>();
        deptIdList.forEach(deptId -> {
            UserDept userDept = new UserDept();
            userDept.setUserId(user.getId());
            userDept.setDeptId(deptId);
            userDeptList.add(userDept);
        });
        userDeptService.remove(Wrappers.<UserDept>update().lambda().eq(UserDept::getUserId, user.getId()));
        return userDeptService.saveBatch(userDeptList);
    }

    @Override
    public PageResult<List<SysUser>> selectUserPage(Page page, SysUser user, String deptId) {
        List<String> deptIdList = SysCache.getDeptChildIds(deptId);

        return PageResult.data(new ArrayList<>(), 0); // page.setRecords(
        // this.find(HqlBuilder.builder().neq(ObjectUtil.isNotEmpty(user.getAccount()),"userid",user.getAccount()).
        // ) baseMapper.selectUserPage(page, user, deptIdList, tenantId));
    }

    @Override
    public PageResult<List<SysUserVO>> selectUserSearch(SysUserVO user, Query query) {

        HqlBuilder hb = HqlBuilder.builder();
        if (StringUtil.isNotBlank(user.getName())) {
            hb.like("name", user.getName());
        }
        return (PageResult) this.getPagination(Page.of(query.getCurrent(), query.getSize()), hb);

        // LambdaQueryWrapper<SysUser> queryWrapper =
        // Wrappers.<SysUser>query().lambda();
        // String tenantId = AuthUtil.getTenantId();
        // if (StringUtil.isNotBlank(user.getName())) {
        // queryWrapper.like(SysUser::getName, user.getName());
        // }
        // if (StringUtil.isNotBlank(user.getDeptName())) {
        // String deptIds = SysCache.getDeptIdsByFuzzy(AuthUtil.getTenantId(),
        // user.getDeptName());
        // if (StringUtil.isNotBlank(deptIds)) {
        // queryWrapper.and(wrapper -> {
        // List<String> ids = Func.toStrList(deptIds);
        // ids.forEach(id -> wrapper.like(SysUser::getDeptId, id).or());
        // });
        // }
        // }
        // if (StringUtil.isNotBlank(user.getPostName())) {
        // String postIds = SysCache.getPostIdsByFuzzy(AuthUtil.getTenantId(),
        // user.getPostName());
        // if (StringUtil.isNotBlank(postIds)) {
        // queryWrapper.and(wrapper -> {
        // List<String> ids = Func.toStrList(postIds);
        // ids.forEach(id -> wrapper.like(SysUser::getPostId, id).or());
        // });
        // }
        // }
        // IPage<SysUser> pages = this.page(Condition.getPage(query), queryWrapper);
        // return SysUserWrapper.build().pageVO(pages);
        // return null;
    }

    @Override
    public SysUser userByAccount(String account) {
        return this.findOne(HqlBuilder.builder().eq("userid", account));// .selectOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getAccount,
        // account).eq(SysUser::getIsDeleted,
        // BladeConstant.DB_NOT_DELETED));
    }

    @Override
    public UserInfo userInfo(String userId) {
        SysUser user = this.getById(userId);
        return buildUserInfo(user);
    }

    @Override
    public UserInfo userInfo(String account, String password) {
        SysUser user = this.findOne(HqlBuilder.builder().eq("userid", account).eq("password", password)); // baseMapper.getUser(account,
        // password);
        return buildUserInfo(user);
    }

    @Override
    public UserInfo judgePassword(String account, String password256) {
        SysUser user = this.userByAccount(account); // baseMapper.getUserByAccount(account);
        if (user != null) {
            if (this.encryptSha256(password256, user.getSalt()).equals(user.getPassword())) {
                return buildUserInfo(user, UserEnum.WEB);
            }
        }

        return null;
    }

    public UserInfo buildUserInfo(SysUser user) {
        return buildUserInfo(user, UserEnum.WEB);
    }

    @Override
    public UserInfo buildUserInfo(SysUser user, UserEnum userEnum) {
        if (ObjectUtil.isEmpty(user)) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(SysUserWrapper.build().entityVO(user));
        if (Func.isNotEmpty(user)) {
            List<String> roleAlias = this.sysRoleService.getRoleAliases(user.getRoleId());// .getRolesByUser(user.getId());
            userInfo.setRoles(roleAlias);
        }
        // 根据每个用户平台，建立对应的detail表，通过查询将结果集写入到detail字段
        Kv detail = Kv.create().set("type", userEnum.getName());
        // if (userEnum == UserEnum.WEB) {
        UserWeb userWeb = new UserWeb();
        UserWeb query = userWeb.selectOne(Wrappers.<UserWeb>lambdaQuery().eq(UserWeb::getUserId, user.getId()));
        if (ObjectUtil.isNotEmpty(query)) {
            detail.set("ext", query.getUserExt());
        }

        userInfo.setDetail(detail);
        return userInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo userInfo(UserOauth userOauth) {
        UserOauth uo = userOauthService.getOne(Wrappers.<UserOauth>query().lambda()
                .eq(UserOauth::getUuid, userOauth.getUuid()).eq(UserOauth::getSource, userOauth.getSource()));
        UserInfo userInfo;
        if (Func.isNotEmpty(uo) && Func.isNotEmpty(uo.getUserId())) {
            userInfo = this.userInfo(uo.getUserId());
            userInfo.setOauthId(Func.toStr(uo.getId()));
        } else {
            userInfo = new UserInfo();
            if (Func.isEmpty(uo)) {
                userOauthService.save(userOauth);
                userInfo.setOauthId(Func.toStr(userOauth.getId()));
            } else {
                userInfo.setOauthId(Func.toStr(uo.getId()));
            }
            HalberdUser user = new SysUser();
            user.setAccount(userOauth.getUsername());
            // user.setTenantId(userOauth.getTenantId());
            userInfo.setUser(user);
            userInfo.setRoles(Collections.singletonList(GUEST_NAME));
        }
        return userInfo;
    }

    @Override
    public boolean grant(String userIds, String roleIds) {
        SysUser user = new SysUser();
        user.setRoleId(roleIds);

        // this.getGenericDao().getQuery(HqlBuilder)

        // return this.update(user,
        // Wrappers.<SysUser>update().lambda().in(SysUser::getId,
        // Func.toLongList(userIds)));
        return true;
    }


    @Override
    public boolean removeUser(String userIds) {
        if (Func.contains(Func.toStrArray(userIds), AuthUtil.getUserId())) {
            throw new ServiceException("不能删除本账号!");
        }
        return this.deleteByIds(Func.toStrList(userIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean registerGuest(SysUser user, Long oauthId) {
        // Tenant tenant = SysCache.getTenant(user.getTenantId());
        // if (tenant == null || tenant.getId() == null) {
        // throw new ServiceException("租户信息错误!");
        // }
        UserOauth userOauth = userOauthService.getById(oauthId);
        if (userOauth == null || userOauth.getId() == null) {
            throw new ServiceException("第三方登陆信息错误!");
        }
        user.setRealName(user.getName());
        user.setAvatar(userOauth.getAvatar());
        user.setRoleId(StringPool.MINUS_ONE);
        user.setDeptId(StringPool.MINUS_ONE);
        user.setPostId(StringPool.MINUS_ONE);
        user.setSalt(UUID.randomUUID().toString());
        boolean userTemp = this.save(user);
        userOauth.setUserId(user.getId());
        userOauth.setTenantId(BladeConstant.ADMIN_TENANT_ID);
        boolean oauthTemp = userOauthService.updateById(userOauth);
        return (userTemp && oauthTemp);
    }

    @Override
    public boolean updatePlatform(String userId, Integer userType, String userExt) {
        if (userType.equals(UserEnum.WEB.getCategory())) {
            UserWeb userWeb = new UserWeb();
            UserWeb query = userWeb.selectOne(Wrappers.<UserWeb>lambdaQuery().eq(UserWeb::getUserId, userId));
            if (ObjectUtil.isNotEmpty(query)) {
                userWeb.setId(query.getId());
            }
            userWeb.setUserId(userId);
            userWeb.setUserExt(userExt);
            return userWeb.insertOrUpdate();
        } else if (userType.equals(UserEnum.APP.getCategory())) {
            UserApp userApp = new UserApp();
            UserApp query = userApp.selectOne(Wrappers.<UserApp>lambdaQuery().eq(UserApp::getUserId, userId));
            if (ObjectUtil.isNotEmpty(query)) {
                userApp.setId(query.getId());
            }
            userApp.setUserId(userId);
            userApp.setUserExt(userExt);
            return userApp.insertOrUpdate();
        } else {
            UserOther userOther = new UserOther();
            UserOther query = userOther.selectOne(Wrappers.<UserOther>lambdaQuery().eq(UserOther::getUserId, userId));
            if (ObjectUtil.isNotEmpty(query)) {
                userOther.setId(query.getId());
            }
            userOther.setUserId(userId);
            userOther.setUserExt(userExt);
            return userOther.insertOrUpdate();
        }
    }

    @Override
    public SysUserVO platformDetail(SysUser user) {
        // SysUser detail = baseMapper.selectOne(Condition.getQueryWrapper(user));
        // SysUserVO userVO = SysUserWrapper.build().entityVO(detail);
        // if (userVO.getUserType().equals(UserEnum.WEB.getCategory())) {
        // UserWeb userWeb = new UserWeb();
        // UserWeb query =
        // userWeb.selectOne(Wrappers.<UserWeb>lambdaQuery().eq(UserWeb::getUserId,
        // user.getId()));
        // if (ObjectUtil.isNotEmpty(query)) {
        // userVO.setUserExt(query.getUserExt());
        // }
        // } else if (userVO.getUserType().equals(UserEnum.APP.getCategory())) {
        // UserApp userApp = new UserApp();
        // UserApp query =
        // userApp.selectOne(Wrappers.<UserApp>lambdaQuery().eq(UserApp::getUserId,
        // user.getId()));
        // if (ObjectUtil.isNotEmpty(query)) {
        // userVO.setUserExt(query.getUserExt());
        // }
        // } else {
        // UserOther userOther = new UserOther();
        // UserOther query =
        // userOther.selectOne(Wrappers.<UserOther>lambdaQuery().eq(UserOther::getUserId,
        // user.getId()));
        // if (ObjectUtil.isNotEmpty(query)) {
        // userVO.setUserExt(query.getUserExt());
        // }
        // }
        // return userVO;
        return null;
    }

    @Override
    public String encrypt(SysUser user, String password) {
        if (user.getId() == null) // 初始用户
        {
            user.setSalt(UUID.randomUUID().toString());
        } else {
            SysUser cUser = getById(user.getId());
            if (cUser == null) {
                user.setSalt(UUID.randomUUID().toString());
            } else {
                if (Func.isEmpty(cUser.getSalt())) {
                    user.setSalt(UUID.randomUUID().toString());
                }
            }
        }
        return encryptSha256(DigestUtil.sha256Hex(password), user.getSalt());
    }


    public String encryptSha256(String password256, String salt) {
        return SM3Utils.encrypt(password256 + salt);
    }

    @Override
    public List<HalberdRole> getListRoleByUser(String userId) {

        return null;
    }

    @Override
    public List<SysUser> listByUser(List<String> userId) {
        return this.find(HqlBuilder.builder(SysUser.class).in("id", userId));// this.list(Wrappers.<SysUser>lambdaQuery().in(SysUser::getId,
        // userId));
    }

    @Override
    public List<SysUser> listByDept(List<String> deptId) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        HqlBuilder hqlBuilder = HqlBuilder.builder();
        // eptId.forEach(id -> hqlBuilder.like("") queryWrapper.like(SysUser::getDeptId,
        // id).or());

        // return this.list(queryWrapper);
        return new ArrayList<>();
    }

    @Override
    public List<SysUser> listByPost(List<String> postId) {
        // LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        // postId.forEach(id -> queryWrapper.like(SysUser::getPostId, id).or());
        // return this.list(queryWrapper);
        return new ArrayList();
    }

    @Override
    public List<SysUser> listByRole(List<String> roleId) {
        if (roleId.size() == 0) {
            return new ArrayList<>();
        }

        List<SysUser> list = new ArrayList<>();

        // 查询
//        HqlBuilder<SysUser> builder = HqlBuilder.builder();
//        builder.in("a.sysRole.id", roleId);
//        builder.addJoin("t.sysIdentitys", "a", "inner join");
//        builder.eq("t.isDeleted", 0);
//        List<SysUser> sysUsers = this.find(builder);
//
//        // 数据处理
//        List<SysUser> list = new ArrayList<>();
//        for (Object object : sysUsers) {
//            Object[] objectArr = (Object[]) object;
//            list.add((SysUser) objectArr[0]);
//        }

//        HqlBuilder<SysRole> builder = HqlBuilder.builder();
//        builder.in(SysRole::getId, roleId);
//        List<SysRole> sysRoles = sysRoleService.find(builder);
//        for (SysRole role : sysRoles) {
//            SysUser user = new SysUser();
//            user.setRealName(role.getRoleName());
//
//        }

        return list;
    }

    @Override
    public List<SysUser> listByDeptRole(List<String> deptId, List<String> roleId) {
        // if(deptId==null||deptId.isEmpty()||roleId==null||roleId.isEmpty()){
        // return new ArrayList<>();
        // }
        // LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        // queryWrapper.and(q -> deptId.forEach(id -> q.like(SysUser::getDeptId,
        // id).or()))
        // .and(q -> roleId.forEach(id -> q.like(SysUser::getRoleId, id).or()));
        // return this.list(queryWrapper);
        return new ArrayList<>();
    }

    @Override
    public List<String> getRolesByUser(String userId) {
        String hql = "select distinct r.id from SysIdentity t " +
                "inner join t.sysUser u " +
                "inner join t.sysRole r " +
                "where   u.id=?0";
        List list = sysIdentityService.findByHql(hql, userId);

        return list;
    }


    /**
     * 获取单位id
     *
     * @return 单位id
     * @author szs
     * @date 2024-01-23
     */
    public String getEnterPriseId() {
        // 获取当前用户
        SysUser sysUser = this.findById(AuthUtil.getUserId());
        if (sysUser == null) {
            throw new ServiceException("当前登录用户不存在：" + AuthUtil.getUserId());
        }

        if (StringUtils.isBlank(sysUser.getEnterPriseId())) {
            throw new ServiceException("当前登录用户未绑定单位：" + AuthUtil.getUserId());
        }

        return sysUser.getEnterPriseId();
    }


    /**
     * 获取单位id
     *
     * @return 单位id
     * @author szs
     * @date 2024-01-23
     */
    public List<String> getCurrentAndParentEnterPriseId() {
        // 获取当前用户
        SysUser sysUser = this.findById(AuthUtil.getUserId());
        if (sysUser == null) {
            throw new ServiceException("当前登录用户不存在：" + AuthUtil.getUserId());
        }

        if (StringUtils.isBlank(sysUser.getEnterPriseId())) {
            throw new ServiceException("当前登录用户未绑定单位：" + AuthUtil.getUserId());
        }

        List<String> list = new ArrayList<>();
        // 当前单位
        PmsEnterprise enterprise = sysUser.getEnterPrise();
        if (enterprise != null) {
            list.add(sysUser.getEnterPriseId());

            if (enterprise.getPmsParentEnterprise() != null) {
                list.add(enterprise.getPmsParentEnterprise().getId());
            }
        }

        return list;
    }


    /**
     * 获取BladeUser
     *
     * @param userId 用户id
     * @return BladeUser
     * @author szs
     * @date 2024-03-15
     */
    public BladeUser getBladeUser(String userId) {
        BladeUser bladeUser = new BladeUser();

        // 查询用户
        SysUser sysUser = findById(userId);
        if (sysUser != null) {
            bladeUser.setUserId(userId);
            bladeUser.setAccount(sysUser.getUserid());
            bladeUser.setUserName(sysUser.getRealName());
            bladeUser.setNickName(sysUser.getRealName());
        }

        return bladeUser;
    }


    /**
     * 获取用户单位等级
     *
     * @return Integer
     * @author szs
     * @date 2024-03-28
     */
    public Integer getUserEnterpriseLevel() {
        // 获取当前用户
        SysUser sysUser = this.findById(AuthUtil.getUserId());
        if (sysUser == null) {
            throw new ServiceException("当前登录用户不存在：" + AuthUtil.getUserId());
        }

        if (StringUtils.isBlank(sysUser.getEnterPriseId())) {
            throw new ServiceException("当前登录用户未绑定单位：" + AuthUtil.getUserId());
        }

        // 等级
        int level = 0;

        // 当前单位
        PmsEnterprise enterprise = sysUser.getEnterPrise();
        while (enterprise != null && StringUtil.isNotBlank(enterprise.getId())) {
            level++;
            enterprise = enterprise.getPmsParentEnterprise();
        }

        return level;
    }


}
