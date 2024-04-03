package cn.topcheer.pms2.biz.limit.service.impl;

import cn.topcheer.pms2.api.limit.entity.LimitConfig;
import cn.topcheer.pms2.biz.limit.service.LimitConfigService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.limit.mapper.LimitConfigMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LimitConfigServiceImpl extends ServiceImpl<LimitConfigMapper, LimitConfig> implements LimitConfigService {

    @Resource
    private LimitConfigMapper limitConfigMapper;

    /**
     * 返回限制条件配置数据
     */
    @Override
    public List<LimitConfig> selectByCon(String batchId,String conditionId,String keyWord){
        return limitConfigMapper.selectByCon(batchId,conditionId,keyWord);
    }

    /**
     * 复制限制配置
     */
    @Override
    public void copyBatchConfig(String sourceBatchId, String targetBatchId) {
        List<LimitConfig> targetLimitConfigList=limitConfigMapper.selectByCon(targetBatchId,null,null);
        targetLimitConfigList.forEach(targetLimitConfig->{
            targetLimitConfig.setId(Util.NewGuid());
            targetLimitConfig.setBatchId(sourceBatchId);
        });
        saveBatch(targetLimitConfigList);
    }

}
