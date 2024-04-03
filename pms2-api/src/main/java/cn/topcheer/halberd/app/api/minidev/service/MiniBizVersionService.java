package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizVersion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 业务版本表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
public interface MiniBizVersionService extends IService<MiniBizVersion> {


    /**
     * 获取最新的版本
     *
     * @param bizDefId 业务定义id
     * @return MiniBizVersion
     * @author szs
     * @date 2023-08-24
     */
    MiniBizVersion getLastMiniBizVersion(Long bizDefId);


}
