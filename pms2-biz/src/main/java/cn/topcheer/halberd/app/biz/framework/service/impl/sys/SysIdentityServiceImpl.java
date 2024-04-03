package cn.topcheer.halberd.app.biz.framework.service.impl.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysIdentityService;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.SysIdentityDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Slf4j
@Validated
@AllArgsConstructor
public class SysIdentityServiceImpl extends GenericService<SysIdentity> implements ISysIdentityService {
        public SysIdentityDao getSysIdentityDao(){
                return (SysIdentityDao) this.getGenericDao();
        }

        @Autowired
        public void setSysIdentityDao(SysIdentityDao sysIdentityDao)
        {
                this.setGenericDao(sysIdentityDao);
        }


        @Override
        public List<SysIdentity> findByUserId(String userId) {
                return this.getSysIdentityDao().findByHql("select t from SysIdentity t join t.sysUser u where u.id=?0 ",userId);

        }
}
