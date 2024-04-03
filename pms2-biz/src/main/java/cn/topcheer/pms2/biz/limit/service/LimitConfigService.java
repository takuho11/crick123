package cn.topcheer.pms2.biz.limit.service;

import cn.topcheer.pms2.api.limit.entity.LimitConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LimitConfigService extends IService<LimitConfig> {

    List<LimitConfig> selectByCon(String batchId,String conditionId,String keyWord);

    void copyBatchConfig(String sourceBatchId, String targetBatchId);
}
