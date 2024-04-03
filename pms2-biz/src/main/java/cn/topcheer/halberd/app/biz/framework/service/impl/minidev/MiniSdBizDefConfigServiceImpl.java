package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefConfig;
import cn.topcheer.halberd.app.api.minidev.service.MiniSdBizDefConfigService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniSdBizDefConfigMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标准模板配置表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2024-03-04
 */
@Service
public class MiniSdBizDefConfigServiceImpl extends ServiceImpl<MiniSdBizDefConfigMapper, MiniSdBizDefConfig> implements MiniSdBizDefConfigService {


    /**
     * 获取标准模板配置
     *
     * @param sdBizDefId 标准模板id
     * @return MiniSdBizDefConfig
     * @author szs
     * @date 2024-03-04
     */
    @Override
    public MiniSdBizDefConfig getMiniSdBizDefConfig(Long sdBizDefId) {
        QueryWrapper<MiniSdBizDefConfig> qw = new QueryWrapper<>();
        qw.eq("sd_biz_def_id", sdBizDefId);
        qw.eq("is_deleted", 0);
        qw.last(" LIMIT 1 ");
        return this.getOne(qw);
    }

}
