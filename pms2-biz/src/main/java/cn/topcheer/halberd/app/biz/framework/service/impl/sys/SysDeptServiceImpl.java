package cn.topcheer.halberd.app.biz.framework.service.impl.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysDept;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysDeptService;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysRoleService;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysDeptVO;
import cn.topcheer.halberd.app.api.framework.wrapper.SysDeptWrapper;
import cn.topcheer.halberd.app.dao.mapper.framework.sys.SysDeptMapper;
import cn.topcheer.halberd.app.dao.mapper.framework.sys.SysRoleMapper;
import cn.topcheer.halberd.biz.common.cache.SysCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@Validated
@AllArgsConstructor
public class SysDeptServiceImpl  extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    private static final String TENANT_ID = "tenantId";
    private static final String PARENT_ID = "parentId";



    @Override
    public List<SysDeptVO> lazyList(String tenantId, String parentId, Map<String, Object> param) {
        // 设置租户ID
        if (AuthUtil.isAdministrator()) {
            tenantId = StringPool.EMPTY;
        }
        String paramTenantId = Func.toStr(param.get(TENANT_ID));
        if (Func.isNotEmpty(paramTenantId) && AuthUtil.isAdministrator()) {
            tenantId = paramTenantId;
        }
        // 判断点击搜索但是没有查询条件的情况
        if (Func.isEmpty(param.get(PARENT_ID)) && param.size() == 1) {
            parentId = "";
        }
        // 判断数据权限控制,非超管角色只可看到本级及以下数据
        if (Func.toLong(parentId) == 0L && !AuthUtil.isAdministrator()) {
            Long deptId = Func.firstLong(AuthUtil.getDeptId());
            SysDept dept = (SysDept) SysCache.getDept(Func.toStr(deptId));
            if (Func.isNotEmpty(dept.getParentId())) {
                parentId = dept.getParentId();
            }
        }
        // 判断点击搜索带有查询条件的情况
        if (Func.isEmpty(param.get(PARENT_ID)) && param.size() > 1 && Func.toLong(parentId) == 0L) {
            parentId = null;
        }
        return  baseMapper.lazyList(tenantId, parentId, param) ;
    }

    @Override
    public List<SysDeptVO> tree(String tenantId) {
        return ForestNodeMerger.merge(baseMapper.tree(tenantId));
    }

    @Override
    public List<SysDeptVO> lazyTree( String parentId) {

        return ForestNodeMerger.merge(baseMapper.lazyTree(BladeConstant.ADMIN_TENANT_ID, parentId));
    }

    @Override
    public String getDeptIds( String deptNames) {
        List<SysDept> deptList = baseMapper.selectList(Wrappers.<SysDept>query().lambda().in(SysDept::getDeptName, Func.toStrList(deptNames)));
        if (deptList != null && deptList.size() > 0) {
            return deptList.stream().map(dept -> Func.toStr(dept.getId())).distinct().collect(Collectors.joining(","));
        }
        return null;
    }

    @Override
    public String getDeptIdsByFuzzy(String deptNames) {
        LambdaQueryWrapper<SysDept> queryWrapper = Wrappers.<SysDept>query().lambda();
        queryWrapper.and(wrapper -> {
            List<String> names = Func.toStrList(deptNames);
            names.forEach(name -> wrapper.like(SysDept::getDeptName, name).or());
        });
        List<SysDept> deptList = baseMapper.selectList(queryWrapper);
        if (deptList != null && deptList.size() > 0) {
            return deptList.stream().map(dept -> Func.toStr(dept.getId())).distinct().collect(Collectors.joining(","));
        }
        return null;
    }

    @Override
    public List<String> getDeptNames(String deptIds) {
        return baseMapper.getDeptNames(Func.toStrArray(deptIds));
    }

    @Override
    public List<SysDept> getDeptChild(String deptId) {
        return baseMapper.selectList(Wrappers.<SysDept>query().lambda().like(SysDept::getAncestors, deptId));
    }

    @Override
    public boolean removeDept(String ids) {
        Long cnt = baseMapper.selectCount(Wrappers.<SysDept>query().lambda().in(SysDept::getParentId, Func.toLongList(ids)));
        if (cnt > 0L) {
            throw new ServiceException("请先删除子节点!");
        }
        return removeByIds(Func.toLongList(ids));
    }

    @Override
    public boolean submit(SysDept dept) {
        if (Func.isEmpty(dept.getParentId())) {
            dept.setTenantId(AuthUtil.getTenantId());
            dept.setParentId(BladeConstant.TOP_PARENT_ID);
            dept.setAncestors(String.valueOf(BladeConstant.TOP_PARENT_ID));
        }
        if (Func.isNotEmpty(dept.getParentId())) {
            SysDept parent = getById(dept.getParentId());
            if (Func.toLong(dept.getParentId()) == Func.toLong(dept.getId())) {
                throw new ServiceException("父节点不可选择自身!");
            }
            dept.setTenantId(parent.getTenantId());
            String ancestors = parent.getAncestors() + StringPool.COMMA + dept.getParentId();
            dept.setAncestors(ancestors);
        }
        dept.setIsDeleted(BladeConstant.DB_NOT_DELETED);
        return saveOrUpdate(dept);
    }

    @Override
    public SysDept getById(String id) {
        //return this.g;
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<SysDeptVO> search(String deptName, Long parentId) {
        String tenantId = AuthUtil.getTenantId();
        LambdaQueryWrapper<SysDept> queryWrapper = Wrappers.<SysDept>query().lambda();
        if (Func.isNotEmpty(tenantId)) {
            queryWrapper.eq(SysDept::getTenantId, tenantId);
        }
        if (Func.isNotEmpty(deptName)) {
            queryWrapper.like(SysDept::getDeptName, deptName);
        }
        if (Func.isNotEmpty(parentId) && parentId > 0L) {
            queryWrapper.eq(SysDept::getParentId, parentId);
        }
        List<SysDept> deptList = baseMapper.selectList(queryWrapper);
        return SysDeptWrapper.build().listNodeVO(deptList);
    }


    public boolean deleteLogic(@NotEmpty List<String> ids) {
        return false;
    }


    public boolean changeStatus(@NotEmpty List<String> ids, Integer status) {
        return false;
    }
}
