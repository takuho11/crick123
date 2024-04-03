package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefConfig;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefConfigService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizDefConfigMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务定义配置表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2024-03-04
 */
@Service
public class MiniBizDefConfigServiceImpl extends ServiceImpl<MiniBizDefConfigMapper, MiniBizDefConfig> implements MiniBizDefConfigService {


    /**
     * 获取业务定义配置
     *
     * @param bizDefId     业务定义id
     * @param bizVersionId 业务版本id
     * @return MiniBizDefConfig
     * @author szs
     * @date 2024-03-04
     */
    @Override
    public MiniBizDefConfig getMiniBizDefConfig(Long bizDefId, Long bizVersionId) {
        QueryWrapper<MiniBizDefConfig> qw = new QueryWrapper<>();
        qw.eq("biz_def_id", bizDefId);
        qw.eq("biz_version_id", bizVersionId);
        qw.eq("is_deleted", 0);
        qw.last(" LIMIT 1 ");
        return this.getOne(qw);
    }


}
