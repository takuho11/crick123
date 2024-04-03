
package cn.topcheer.halberd.app.biz.framework.service.impl.sys;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysAgency;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysAgencyService;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.SysAgencyDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 服务实现类
 *
 * @author szs
 * @date 2024-01-12
 */
@Service
@AllArgsConstructor
public class SysAgencyServiceImpl extends GenericService<SysAgency> implements ISysAgencyService {


    @Autowired
    public void setSysAgencyDao(SysAgencyDao sysAgencyDao) {
        this.setGenericDao(sysAgencyDao);
    }


}
