package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.SysIdentityDao;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.SysUserDao;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.biz.flow.service.impl.FlowRecordService;
import cn.topcheer.pms2.biz.pml.service.impl.PageService;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "SysIdentityService")
public class SysIdentityService extends GenericService<SysIdentity> {
    public SysIdentityDao getSysIdentityDao() {
        return (SysIdentityDao) this.getGenericDao();
    }

    @Autowired
    private void setSysIdentityDao(SysIdentityDao sysIdentityDao) {
        this.setGenericDao(sysIdentityDao);
    }



    //129947C6-94DC-4A7D-84D2-E78A80E518A3 普通用户
    public void generateIdentity(SysUser user, PmsEnterprise unit, SysRole role){
        //sys_identity表
        SysIdentity sysIdentity = new SysIdentity();
        sysIdentity.setId(Util.NewGuid());
        sysIdentity.setSysRole(role);
        sysIdentity.setSysUser(user);//用户信息关联
        sysIdentity.setPmsEnterprise(unit);//单位信息关联
        sysIdentity.setCreatedate(new Date());//创建时间
        sysIdentity.setEnabled(0);//0:表示申请中，1:表示通过
        sysIdentity.setIdentityType("sb");//参考之前的注册，暂时先保留
        this.merge(sysIdentity);
    }


}
