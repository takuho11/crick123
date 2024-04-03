package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.MiniBizAllowUpdateDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAllowUpdate;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizAllowUpdateResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.mp.support.Query;


/**
 * <p>
 * 业务批注表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-12-05
 */
public interface MiniBizAllowUpdateService extends IService<MiniBizAllowUpdate> {


    /**
     * 分页列表
     *
     * @param dto   MiniBizAllowUpdateDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2023-12-11
     */
    IPage<MiniBizAllowUpdateResult> pageList(MiniBizAllowUpdateDTO dto, Query query);


}
