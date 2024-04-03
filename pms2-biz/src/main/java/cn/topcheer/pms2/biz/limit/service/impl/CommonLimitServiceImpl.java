package cn.topcheer.pms2.biz.limit.service.impl;

import cn.topcheer.pms2.biz.limit.exception.LimitException;
import cn.topcheer.pms2.biz.limit.service.CommonLimitService;
import cn.topcheer.pms2.biz.limit.LimitStrategy;
import cn.topcheer.pms2.biz.limit.enums.CommonLimitEnum;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class CommonLimitServiceImpl implements CommonLimitService {

    /**
     * 所有业务后台限制通用接口
     */
    @Override
    public void judgeCondition(HashMap<String,Object> requestParameter) {
        checkCurrentUser();
        LimitStrategy limitStrategy=checkLimitStrategy(requestParameter);
        if (limitStrategy != null) {
            limitStrategy.judgeCondition(requestParameter);
        }
    }

    /**
     * 所有业务后台时间限制通用接口
     */
    @Override
    public void judgeTime(HashMap<String,Object> requestParameter) {
        checkCurrentUser();
        LimitStrategy limitStrategy=checkLimitStrategy(requestParameter);
        if (limitStrategy != null) {
            limitStrategy.judgeTime(requestParameter);
        }
    }

    /**
     * 判断当前业务是否配置限制策略
     */
    private LimitStrategy checkLimitStrategy(HashMap<String,Object> requestParameter){
        String type=(String) requestParameter.get("type");
        if(Func.hasEmpty(type)){
            //throw new LimitException("前端参数业务类型type必传。");
            return null;
        }
        CommonLimitEnum commonLimitEnum=CommonLimitEnum.getByType(type);
        if (!Optional.ofNullable(commonLimitEnum).isPresent()){
            //throw new LimitException("当前业务类型【"+type+"】未配置限制接口，请先在CommonLimitEnum中添加相关限制。");
            return null;
        }
        return commonLimitEnum.getLimitStrategy();
    }

    /**
     * 判断当前用户数据是否完整
     */
    private void checkCurrentUser(){
        BladeUser user=AuthUtil.getUser();
        if(null==user){
            throw new LimitException("获取不到当前登录用户信息，请重新登录。");
        }
    }

}
