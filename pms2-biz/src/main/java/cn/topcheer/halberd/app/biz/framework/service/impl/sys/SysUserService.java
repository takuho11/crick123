
package cn.topcheer.halberd.app.biz.framework.service.impl.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysUserVO;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;

import cn.topcheer.halberd.app.dao.jpa.framework.sys.SysUserDao;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 服务实现类
 *
 * @author szs
 * @date 2024-01-25
 */
@Service(value = "SysUserService")
public class SysUserService extends GenericPageService<SysUser> {


    public SysUserDao getSysUserDao() {
        return (SysUserDao) this.getGenericDao();
    }

    @Autowired
    public void setSysUsereDao(SysUserDao sysUserDao) {
        this.setGenericDao(sysUserDao);
    }

    /**
     * 下级分页
     *
     * @param sysUser SysUserVO
     * @param query   Query
     * @return PageResult
     */
    public PageResult<List<Map>> nextPage(SysUserVO sysUser, Query query) {
        // 参数集合
        List<String> paramList = new ArrayList<>();

        // 查询条件
        String insql = " AND c.parentid = ? ";
        paramList.add(this.getEnterPriseId());

        if (StringUtils.isNotBlank(sysUser.getUserid())) {
            insql += " AND a.userid like ? ";
            paramList.add("%" + sysUser.getUserid() + "%");
        }

        if (StringUtils.isNotBlank(sysUser.getName())) {
            insql += " AND a.name like ? ";
            paramList.add("%" + sysUser.getName() + "%");
        }

        // sql语句
        String sql = "SELECT a.id,\n" +
                "         wm_concat(distinct a.userid) AS userid,\n" +
                "         wm_concat(distinct a.name) AS name,\n" +
                "         wm_concat(distinct a.sex) AS sex,\n" +
                "         wm_concat(distinct a.mobile) AS mobile,\n" +
                "         wm_concat(distinct a.email) AS email,\n" +
                "         wm_concat(distinct b.roleid) AS role_id,\n" +
                "         wm_concat( DISTINCT b.purvieworganizeid) AS enter_prise_id,\n" +
                "         wm_concat(distinct b.departmentid) AS dept_id,\n" +
                "         wm_concat( DISTINCT c.name) AS enter_prise_name,\n" +
                "         wm_concat( DISTINCT d.rolename) AS role_name,\n" +
                "         wm_concat( DISTINCT e.name) AS dept_name\n" +
                "FROM sys_user a\n" +
                "LEFT JOIN sys_identity b\n" +
                "    ON b.userid = a.id\n" +
                "LEFT JOIN pms_enterprise c\n" +
                "    ON c.id = b.purvieworganizeid\n" +
                "LEFT JOIN sys_role d\n" +
                "    ON d.id = b.roleid\n" +
                "LEFT JOIN pms_department e\n" +
                "    ON e.id = b.departmentid\n" +
                "WHERE a.is_delete = 0	" + insql + " GROUP BY a.id ";


        // 分页查询
        PageResult<List<Map>> pageResult = this.findPageBySql(sql, paramList, Page.of(query.getCurrent(), query.getSize()));

        // 下划线转驼峰
        List<Map> list = new ArrayList<>();
        for (Map map : pageResult.getData()) {
            list.add(Util.mapKeyToCamelCase(map));
        }
        pageResult.setData(list);

        return pageResult;
    }


    /**
     * 我的分页
     *
     * @param sysUser SysUserVO
     * @param query   Query
     * @return PageResult
     */
    public PageResult<List<Map>> myPage(SysUserVO sysUser, Query query) {
        // 参数集合
        List<String> paramList = new ArrayList<>();

        // 查询条件
        String insql = " AND b.purvieworganizeid = ? ";
        paramList.add(this.getEnterPriseId());

        if (StringUtils.isNotBlank(sysUser.getUserid())) {
            insql += " AND a.userid like ? ";
            paramList.add("%" + sysUser.getUserid() + "%");
        }

        if (StringUtils.isNotBlank(sysUser.getName())) {
            insql += " AND a.name like ? ";
            paramList.add("%" + sysUser.getName() + "%");
        }

        // sql语句
        String sql = "SELECT a.id,\n" +
                "         wm_concat(distinct a.userid) AS userid,\n" +
                "         wm_concat(distinct a.name) AS name,\n" +
                "         wm_concat(distinct a.sex) AS sex,\n" +
                "         wm_concat(distinct a.mobile) AS mobile,\n" +
                "         wm_concat(distinct a.email) AS email\n" +
                "FROM sys_user a\n" +
                "LEFT JOIN sys_identity b\n" +
                "    ON b.userid = a.id\n" +
                "WHERE a.is_delete = 0	" + insql + " GROUP BY a.id ";


        // 分页查询
        PageResult<List<Map>> pageResult = this.findPageBySql(sql, paramList, Page.of(query.getCurrent(), query.getSize()));

        // 下划线转驼峰
        List<Map> list = new ArrayList<>();
        for (Map map : pageResult.getData()) {
            list.add(Util.mapKeyToCamelCase(map));
        }
        pageResult.setData(list);

        return pageResult;
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


}
