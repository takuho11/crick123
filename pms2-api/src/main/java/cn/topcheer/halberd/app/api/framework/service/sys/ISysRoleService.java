package cn.topcheer.halberd.app.api.framework.service.sys;

import cn.topcheer.halberd.app.api.framework.dto.sys.SysRoleDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysRoleVO;
import cn.topcheer.halberd.biz.modules.base.service.IHalberdRoleService;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface ISysRoleService extends IHalberdRoleService<SysRole> {

    /**
     * 保存
     *
     * @param dto SysRoleDTO
     * @author szs
     * @date 2023-10-30
     */
    void submit(SysRoleDTO dto);


    /**
     * 自定义分页
     *
     * @param page
     * @param role
     * @return
     */
    PageResult<List<SysRole>> selectRolePage(Page page, SysRoleVO role);

    /**
     * 树形结构
     *
     * @return
     */
    List<SysRoleVO> tree();

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
    void grant(@NotEmpty List<String> roleIds, List<String> menuIds, List<String> dataScopeIds, List<String> apiScopeIds);


    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    boolean removeRole(String ids);
}
