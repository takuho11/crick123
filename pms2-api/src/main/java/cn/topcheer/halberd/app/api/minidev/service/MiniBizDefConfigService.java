package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 业务定义配置表 服务类
 * </p>
 *
 * @author szs
 * @since 2024-03-04
 */
public interface MiniBizDefConfigService extends IService<MiniBizDefConfig> {


    /**
     * 获取业务定义配置
     *
     * @param bizDefId     业务定义id
     * @param bizVersionId 业务版本id
     * @return MiniBizDefConfig
     * @author szs
     * @date 2024-03-04
     */
    MiniBizDefConfig getMiniBizDefConfig(Long bizDefId, Long bizVersionId);


}
