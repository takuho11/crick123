package cn.topcheer.pms2.biz.limit;

import cn.topcheer.pms2.api.limit.entity.LimitConfig;
import cn.topcheer.pms2.biz.limit.exception.LimitException;
import cn.topcheer.pms2.biz.limit.service.LimitConditionService;
import cn.topcheer.pms2.biz.limit.service.LimitConfigService;
import org.springblade.core.tool.utils.Func;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractLimitStrategy implements LimitStrategy {

    @Resource
    private LimitConfigService limitConfigService;
    @Resource
    private LimitConditionService limitConditionService;

    @Override
    public void judgeCondition(HashMap<String, Object> requestParameter) {
        preExecuteConfigCondition(requestParameter);
        List<Map<String, String>> limitConfigConditionList = getConfigCondition(requestParameter);
        if (limitConfigConditionList.size() > 0) {
            executeConfigCondition(limitConfigConditionList, requestParameter);
        }
        postExecuteConfigCondition(requestParameter);
    }

    /**
     * 执行当前批次配置相关限制条件前，执行自定义限制判断
     */
    @Override
    public void preExecuteConfigCondition(HashMap<String, Object> requestParameter) {
    }

    /**
     * 获取当前批次配置的所有限制条件，返回map数据，包括当前条件code以及对应配置param
     */
    private List<Map<String, String>> getConfigCondition(HashMap<String, Object> requestParameter) {
        String batchId = (String) requestParameter.get("batchId");
        if (Func.hasEmpty(batchId)) {
            throw new LimitException("前端参数小批次batchId必传。");
        }
        List<LimitConfig> limitConfigList = limitConfigService.selectByCon(batchId, null, null);
        List<Map<String, String>> limitConfigConditionList = new ArrayList<>();
        limitConfigList.forEach(limitConfig -> {
            String limitConditionCode = limitConditionService.getById(limitConfig.getConditionId()).getCode();
            String limitConfigParam = limitConfig.getParam();
            HashMap<String, String> limitCodeAndParam = new HashMap<>();
            limitCodeAndParam.put("limitConditionCode", limitConditionCode);
            limitCodeAndParam.put("limitConfigParam", limitConfigParam);
            limitConfigConditionList.add(limitCodeAndParam);
        });
        return limitConfigConditionList;
    }

    /**
     * 执行当前批次配置相关限制条件
     */
    @Override
    public void executeConfigCondition(List<Map<String, String>> limitConfigConditionList, HashMap<String, Object> requestParameter) {
    }

    /**
     * 执行当前批次配置相关限制条件后，执行自定义限制判断
     */
    @Override
    public void postExecuteConfigCondition(HashMap<String, Object> requestParameter) {
    }

    @Override
    public void judgeTime(HashMap<String, Object> requestParameter) {
    }

}
