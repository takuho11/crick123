package cn.topcheer.halberd.app.api.framework.service.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysDept;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysDeptVO;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysRoleVO;
import cn.topcheer.halberd.biz.modules.base.service.IHalberdDeptService;
import cn.topcheer.halberd.biz.modules.base.service.IHalberdRoleService;

import java.util.List;
import java.util.Map;

public interface ISysDeptService  extends IHalberdDeptService<SysDept> {

    /**
     * 懒加载部门列表
     *
     * @param tenantId
     * @param parentId
     * @param param
     * @return
     */
    List<SysDeptVO> lazyList(String tenantId, String parentId, Map<String, Object> param);


    /**
     * 树形结构
     *
     * @param tenantId
     * @return
     */
    List<SysDeptVO> tree(String tenantId);

    /**
     * 懒加载树形结构
     *
     * @param tenantId
     * @param parentId
     * @return
     */
    List<SysDeptVO> lazyTree( String parentId);

    /**
     * 部门信息查询
     *
     * @param deptName
     * @param parentId
     * @return
     */
    List<SysDeptVO> search(String deptName, Long parentId);
}
