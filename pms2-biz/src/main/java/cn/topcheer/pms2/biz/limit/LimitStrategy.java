package cn.topcheer.pms2.biz.limit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LimitStrategy {

    void judgeCondition(HashMap<String,Object> requestParameter);

    void preExecuteConfigCondition(HashMap<String,Object> requestParameter);

    void executeConfigCondition(List<Map<String,String>> limitConfigConditionList,HashMap<String, Object> requestParameter);

    void postExecuteConfigCondition(HashMap<String,Object> requestParameter);

    void judgeTime(HashMap<String,Object> requestParameter);

}
