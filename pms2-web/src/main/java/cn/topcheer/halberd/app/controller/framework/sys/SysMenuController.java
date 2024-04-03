package cn.topcheer.halberd.app.controller.framework.sys;


import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.biz.modules.system.service.IMenuService;
import cn.topcheer.halberd.biz.modules.system.vo.GrantTreeVO;
import cn.topcheer.halberd.biz.modules.system.vo.MenuVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.node.TreeNode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 控制器
 *
 * @author szs
 * @date 2024-03-28
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.APPLICATION_SYSTEM_NAME + "/menu")
@Api(value = "系统菜单", tags = "用户体系-系统菜单")
public class SysMenuController extends BladeController {


    private final IMenuService menuService;

    private final SysUserServiceImpl sysUserService;


    /**
     * 获取权限分配树形结构
     *
     * @author szs
     * @date 2024-03-28
     */
    @GetMapping("/grant-tree-new")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "权限分配树形结构", notes = "权限分配树形结构")
    public R<GrantTreeVO> grantTree(BladeUser user) {
        GrantTreeVO vo = new GrantTreeVO();
        vo.setDataScope(menuService.grantDataScopeTree(user));
        vo.setApiScope(menuService.grantApiScopeTree(user));

        // 菜单树
        List<TreeNode> menuTree = menuService.grantTree(user);

        // 获取用户单位等级
        Integer level = sysUserService.getUserEnterpriseLevel();

        // 2级及以上单位，给下级角色设置菜单权限时，需要过滤掉下级单位、下级角色、下级用户
        if (level >= 2) {
            menuTree = this.getNewMenuTree(menuTree);
        }

        vo.setMenu(menuTree);
        return R.data(vo);
    }


    /**
     * 获取新的菜单树
     *
     * @param menuTree 菜单树
     * @return List
     * @author szs
     * @date 2024-03-28
     */
    private List<TreeNode> getNewMenuTree(List<TreeNode> menuTree) {
        List<TreeNode> list = new ArrayList<>();
        for (TreeNode data : menuTree) {
            if ("下级单位".equals(data.getTitle()) || "下级角色".equals(data.getTitle()) || "下级用户".equals(data.getTitle())) {
                continue;
            }

            if (data.getChildren() != null && data.getChildren().size() > 0) {
                data.setChildren(this.getNewMenuTree(data.getChildren()));
            }

            list.add(data);
        }

        return list;
    }


    /**
     * 获取用户菜单树
     *
     * @author szs
     * @date 2024-03-28
     */
    @GetMapping("routes-new")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "获取用户菜单树")
    public R<List<MenuVO>> routes(BladeUser user, String topMenuId) {

        // 用户菜单权限
        List<MenuVO> list = menuService.routes((user == null) ? null : user.getRoleId(), topMenuId);

        // 获取用户单位等级
        Integer level = sysUserService.getUserEnterpriseLevel();

        // 3级及以上单位，获取左侧菜单时，需要过滤掉下级单位、下级角色、下级用户
        if (level >= 3) {
            list = this.getNewRoutes(list);
        }

        return R.data(list);
    }


    /**
     * 获取新的用户菜单树
     *
     * @param routes 权限树
     * @return List
     * @author szs
     * @date 2024-03-28
     */
    private List<MenuVO> getNewRoutes(List<MenuVO> routes) {
        List<MenuVO> list = new ArrayList<>();
        for (MenuVO data : routes) {
            if ("下级单位".equals(data.getName()) || "下级角色".equals(data.getName()) || "下级用户".equals(data.getName())) {
                continue;
            }

            if (data.getChildren() != null && data.getChildren().size() > 0) {
                data.setChildren(this.getNewRoutes(data.getChildren()));
            }

            list.add(data);
        }

        return list;
    }


}
