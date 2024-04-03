package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefHistoryDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefHistory;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizDefHistoryResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDefHistoryService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizDefHistoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 业务定义历史表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2024-02019
 */
@Service
public class MiniBizDefHistoryServiceImpl extends ServiceImpl<MiniBizDefHistoryMapper, MiniBizDefHistory> implements MiniBizDefHistoryService {


    /**
     * 添加历史数据
     *
     * @param bizDefId     业务定义id
     * @param bizVersionId 业务版本id
     * @param content      内容
     * @author szs
     * @date 2024-02-19
     */
    @Override
    public void addHistoryData(Long bizDefId, Long bizVersionId, String content) {
        MiniBizDefHistory data = new MiniBizDefHistory();
        data.setBizDefId(bizDefId);
        data.setBizVersionId(bizVersionId);
        data.setContent(content);
        data.setCreateUser(AuthUtil.getUserId());
        data.setCreateTime(new Date());
        data.setIsDeleted(0);
        this.save(data);

    }


    /**
     * 分页列表
     *
     * @param dto   MiniBizDefHistoryDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2024-03-27
     */
    @Override
    public IPage<MiniBizDefHistoryResult> pageList(MiniBizDefHistoryDTO dto, Query query) {
        QueryWrapper<MiniBizDefHistory> qw = new QueryWrapper<>();
        qw.eq(dto.getBizDefId() != null, "a.biz_def_id", dto.getBizDefId());
        qw.eq(dto.getBizVersionId() != null, "a.biz_version_id", dto.getBizVersionId());
        qw.eq("a.is_deleted", 0);
        qw.orderByDesc("a.create_time");

        return baseMapper.pageList(new Page<>(query.getCurrent(), query.getSize()), qw);
    }


}
