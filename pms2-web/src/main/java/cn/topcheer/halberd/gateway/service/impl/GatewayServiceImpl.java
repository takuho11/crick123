package cn.topcheer.halberd.gateway.service.impl;

import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import cn.topcheer.halberd.gateway.api.dto.ForwardInfo;
import cn.topcheer.halberd.gateway.api.po.SubSystem;
import cn.topcheer.halberd.gateway.constant.AuthMode;
import cn.topcheer.halberd.gateway.dao.mapper.SubSystemMapper;
import cn.topcheer.halberd.resauth.service.ResAuthResolver;

@Component
public class GatewayServiceImpl {

    @Autowired
    SubSystemMapper subjectMapper;

    public ForwardInfo getForwardInfo(String path) {

        int endIndex = path.indexOf("/");
        if (endIndex <= 0) {
            return ForwardInfo.fail("路径中没有子系统信息或起始路径为/");
        }

        String subSystemName = path.substring(0, endIndex);
        LambdaQueryWrapper<SubSystem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubSystem::getGatewayPath, subSystemName);
        SubSystem subSystem = subjectMapper.selectOne(wrapper);
        if (subSystem == null) {
            return ForwardInfo.fail("子系统不存在");
        }

        if (subSystem.getAuthMode() == AuthMode.LOGIN) {
            BladeUser user = AuthUtil.getUser();
            if (user == null) {
                return ForwardInfo.fail("用户未登录");
            }
        } else if (subSystem.getAuthMode() == AuthMode.CUSTOM) {
            BladeUser user = AuthUtil.getUser();
            if (user == null) {
                return ForwardInfo.fail("用户未登录");
            }
            if (StringUtil.isBlank(subSystem.getAuthBean())) {
                return ForwardInfo.fail("未定义权限解析Bean");
            }

            Object beanObject =  SpringUtil.getBean(subSystem.getAuthBean().trim());
            if(beanObject == null) {
                  return ForwardInfo.fail("权限解析Bean未找到:"+subSystem.getAuthBean());
            }

            if(!(beanObject instanceof ResAuthResolver)) {

                return ForwardInfo.fail("权限解析Bean未实现指定ResAuthResolver接口："+subSystem.getAuthBean());
            }
            ResAuthResolver authBean=(ResAuthResolver)beanObject;
            String resourceType=(StringUtil.isBlank(subSystem.getResType())?"api":subSystem.getResType());
            if(!authBean.hasAuth(resourceType, path.substring(endIndex))){
                 return ForwardInfo.fail("权限解析Bean未实现指定ResAuthResolver接口："+subSystem.getAuthBean());
            }

        }
        ForwardInfo result;  

        if (subSystem.getRealUrl().endsWith("/")) {
            result=ForwardInfo.ok(subSystem.getRealUrl() + path.substring(endIndex + 1));
        } else {
            result=ForwardInfo.ok(subSystem.getRealUrl() + path.substring(endIndex));
        }
        return result;

    }

}
