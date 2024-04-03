
package cn.topcheer.halberd.app.biz.framework.service.impl.sys;


import cn.topcheer.halberd.app.api.framework.entity.sys.PmsArea;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysOrganizationService;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.PmsAreaDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 服务实现类
 *
 * @author szs
 * @date 2024-01-22
 */
@Service
@AllArgsConstructor
public class PmsAreaServiceImpl extends GenericService<PmsArea> implements ISysOrganizationService {


    @Autowired
    public void setSysOrganizationDao(PmsAreaDao pmsAreaDao) {
        this.setGenericDao(pmsAreaDao);
    }


}
