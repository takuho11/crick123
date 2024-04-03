package cn.topcheer.pms2.biz.limit.service;

import java.util.HashMap;

public interface CommonLimitService {

    void judgeCondition(HashMap<String,Object> requestParameter);

    void judgeTime(HashMap<String,Object> requestParameter);

}
