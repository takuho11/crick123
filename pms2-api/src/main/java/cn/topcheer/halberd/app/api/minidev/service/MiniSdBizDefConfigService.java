package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 标准模板配置表 服务类
 * </p>
 *
 * @author szs
 * @since 2024-03-04
 */
public interface MiniSdBizDefConfigService extends IService<MiniSdBizDefConfig> {


    /**
     * 获取标准模板配置
     *
     * @param sdBizDefId 标准模板id
     * @return MiniSdBizDefConfig
     * @author szs
     * @date 2024-03-04
     */
    MiniSdBizDefConfig getMiniSdBizDefConfig(Long sdBizDefId);


}
