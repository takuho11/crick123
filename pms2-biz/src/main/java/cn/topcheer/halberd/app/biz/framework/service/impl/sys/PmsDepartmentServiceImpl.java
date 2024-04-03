
package cn.topcheer.halberd.app.biz.framework.service.impl.sys;


import cn.topcheer.halberd.app.api.framework.entity.sys.PmsDepartment;

import cn.topcheer.halberd.app.api.framework.service.sys.IPmsDepartmentService;
import cn.topcheer.halberd.app.dao.jpa.GenericService;

import cn.topcheer.halberd.app.dao.jpa.framework.sys.PmsDepartmentDao;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 服务实现类
 *
 * @author szs
 * @date 2023-10-31
 */
@Service
@AllArgsConstructor
public class PmsDepartmentServiceImpl extends GenericService<PmsDepartment> implements IPmsDepartmentService {

    @Autowired
    public void setPmsDepartmentDao(PmsDepartmentDao pmsDepartmentDao) {
        this.setGenericDao(pmsDepartmentDao);
    }
    @Autowired
    private DBHelper dbHelper;

    @Override
    public List<Map> findAllDepartment() {
        String sql = "select p.* from PMS_DEPARTMENT p where 1=1 and p.enable = ? order by p.seq";
        return this.dbHelper.getRows(sql, new Object[]{"1"});
    }
}
