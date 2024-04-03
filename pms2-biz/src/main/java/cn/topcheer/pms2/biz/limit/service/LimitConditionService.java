package cn.topcheer.pms2.biz.limit.service;

import cn.topcheer.pms2.api.limit.entity.LimitCondition;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LimitConditionService extends IService<LimitCondition> {

    List<LimitCondition> selectByCon(String system,String type,String keyWord);

}
