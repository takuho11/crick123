
package cn.topcheer.halberd.app.biz.framework.service.impl.sys;


import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.service.sys.IPmsEnterpriseService;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.PmsEnterpriseDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 服务实现类
 *
 * @author szs
 * @date 2023-10-31
 */
@Service
@AllArgsConstructor
public class PmsEnterpriseServiceImpl extends GenericService<PmsEnterprise> implements IPmsEnterpriseService {


    @Autowired
    public void setPmsEnterpriseDao(PmsEnterpriseDao pmsEnterpriseDao) {
        this.setGenericDao(pmsEnterpriseDao);
    }


}
