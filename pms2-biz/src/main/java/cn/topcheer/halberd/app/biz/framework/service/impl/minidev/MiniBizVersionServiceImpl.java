package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.entity.MiniBizVersion;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizVersionService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizVersionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 业务版本表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Service
public class MiniBizVersionServiceImpl extends ServiceImpl<MiniBizVersionMapper, MiniBizVersion> implements MiniBizVersionService {


    /**
     * 获取最新的版本
     *
     * @param bizDefId 业务定义id
     * @return MiniBizVersion
     * @author szs
     * @date 2023-08-24
     */
    @Override
    public MiniBizVersion getLastMiniBizVersion(Long bizDefId) {
        QueryWrapper<MiniBizVersion> qw = new QueryWrapper<>();
        qw.eq("biz_def_id", bizDefId);
        qw.eq("is_deleted", 0);
        qw.orderByDesc("create_time");
        qw.last(" LIMIT 1 ");

        return this.getOne(qw);
    }


}
