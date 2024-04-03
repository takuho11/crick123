package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.dto.MiniBizAllowUpdateDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAllowUpdate;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizAllowUpdateResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizAllowUpdateService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizAllowUpdateMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 业务允许修改表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-12-05
 */
@Service
public class MiniBizAllowUpdateServiceImpl extends ServiceImpl<MiniBizAllowUpdateMapper, MiniBizAllowUpdate> implements MiniBizAllowUpdateService {


    /**
     * 分页列表
     *
     * @param dto   MiniBizAllowUpdateDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2023-12-11
     */
    @Override
    public IPage<MiniBizAllowUpdateResult> pageList(MiniBizAllowUpdateDTO dto, Query query) {
        QueryWrapper<MiniBizAllowUpdate> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(dto.getName()), "a.name", dto.getName());
        qw.eq("a.type", 1);
        qw.eq(dto.getMiniBizDefId() != null, "a.mini_biz_def_id", dto.getMiniBizDefId());
        qw.eq(dto.getMiniBizVersionId() != null, "a.mini_biz_version_id", dto.getMiniBizVersionId());
        qw.eq("a.create_user", AuthUtil.getUserId());
        qw.eq("a.is_deleted", 0);
        qw.orderByDesc("a.create_time");

        return baseMapper.pageList(new Page<>(query.getCurrent(), query.getSize()), qw);
    }


}
