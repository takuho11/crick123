package cn.topcheer.halberd.gateway.service.impl;

import cn.topcheer.halberd.resauth.service.ResAuthResolver;
import cn.topcheer.halberd.resauth.service.ResAuthService;

public class SubSystemApiAuthResolver implements ResAuthResolver{

    @Override
    public boolean hasAuth(String resourceType, String resourceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasAuth'");
    }

    @Override
    public boolean hasAuth(String resourceType, String resourceId, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasAuth'");
    }

    @Override
    public boolean register(ResAuthService resAuthService) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }
    
}
