package cn.topcheer.halberd.app.api.framework.service.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;

import java.util.List;

public interface ISysIdentityService {
    List<SysIdentity> findByUserId(String userId);
}
