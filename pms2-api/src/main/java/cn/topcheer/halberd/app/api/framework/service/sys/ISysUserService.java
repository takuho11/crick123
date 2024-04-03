package cn.topcheer.halberd.app.api.framework.service.sys;

import cn.topcheer.halberd.app.api.framework.dto.sys.SysUserDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysUserVO;
import cn.topcheer.halberd.biz.modules.auth.enums.UserEnum;
import cn.topcheer.halberd.biz.modules.base.service.IHalberdUserService;
import cn.topcheer.halberd.biz.modules.system.entity.UserInfo;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import org.springblade.core.mp.support.Query;

import java.util.List;

public interface ISysUserService extends IHalberdUserService<SysUser> {

    /**
     * 保存
     *
     * @param dto SysUserDTO
     * @author szs
     * @date 2023-10-30
     */
    void submit(SysUserDTO dto);


    /**
     * 重置密码
     *
     * @param ids 用户ids
     * @author szs
     * @date 2023-10-30
     */
    void resetPassword(String ids);


    /**
     * 修改密码
     *
     * @param oldPassword  旧密码
     * @param newPassword  新密码
     * @author szs
     * @date 2024-01-25
     */
    void updatePassword(String oldPassword, String newPassword);


    /**
     * 修改用户基本信息
     *
     * @param user SysUser
     * @author szs
     * @date 2024-01-25
     */
    void updateUserInfo(SysUser user);


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    boolean updateUser(SysUser user);


    /**
     * 创建用户信息（主要用于生成jwt)
     *
     * @param user
     * @param userEnum
     * @return
     */
    public UserInfo buildUserInfo(SysUser user, UserEnum userEnum);


    /**
     * 自定义分页
     *
     * @param page
     * @param user
     * @param deptId
     * @return
     */
    PageResult<List<SysUser>> selectUserPage(Page page, SysUser user, String deptId);

    /**
     * 自定义分页
     *
     * @param user
     * @param query
     * @return
     */
    PageResult<List<SysUserVO>> selectUserSearch(SysUserVO user, Query query);


    SysUser userByAccount(String account);

    UserInfo userInfo(String account, String password);

    UserInfo judgePassword(String account, String password256);

    /**
     * 给用户设置角色
     *
     * @param userIds
     * @param roleIds
     * @return
     */
    boolean grant(String userIds, String roleIds);


    /**
     * 删除用户
     *
     * @param userIds
     * @return
     */
    boolean removeUser(String userIds);

    /**
     * 注册用户
     *
     * @param user
     * @param oauthId
     * @return
     */
    boolean registerGuest(SysUser user, Long oauthId);

    /**
     * 配置用户平台
     *
     * @param userId
     * @param userType
     * @param userExt
     * @return
     */
    boolean updatePlatform(String userId, Integer userType, String userExt);

    /**
     * 用户详细信息
     *
     * @param user
     * @return
     */
    SysUserVO platformDetail(SysUser user);


}
