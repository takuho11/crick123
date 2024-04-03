package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefHistoryDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefHistory;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizDefHistoryResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.mp.support.Query;

/**
 * <p>
 * 业务定义历史表 服务类
 * </p>
 *
 * @author szs
 * @since 2024-02-19
 */
public interface MiniBizDefHistoryService extends IService<MiniBizDefHistory> {


    /**
     * 添加历史数据
     *
     * @param bizDefId     业务定义id
     * @param bizVersionId 业务版本id
     * @param content      内容
     * @author szs
     * @date 2024-02-19
     */
    void addHistoryData(Long bizDefId, Long bizVersionId, String content);


    /**
     * 分页列表
     *
     * @param dto   MiniBizDefHistoryDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2024-03-27
     */
    IPage<MiniBizDefHistoryResult> pageList(MiniBizDefHistoryDTO dto, Query query);


}
