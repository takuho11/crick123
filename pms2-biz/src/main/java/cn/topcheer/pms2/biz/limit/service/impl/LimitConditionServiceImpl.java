package cn.topcheer.pms2.biz.limit.service.impl;

import cn.topcheer.pms2.api.limit.entity.LimitCondition;
import cn.topcheer.pms2.biz.limit.service.LimitConditionService;
import cn.topcheer.pms2.dao.limit.mapper.LimitConditionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LimitConditionServiceImpl extends ServiceImpl<LimitConditionMapper, LimitCondition> implements LimitConditionService {

    @Resource
    private LimitConditionMapper limitConditionMapper;

    /**
     * 返回限制条件数据
     */
    @Override
    public List<LimitCondition> selectByCon(String system,String type,String keyWord) {
        return limitConditionMapper.selectByCon(system,type,keyWord);
    }

}
