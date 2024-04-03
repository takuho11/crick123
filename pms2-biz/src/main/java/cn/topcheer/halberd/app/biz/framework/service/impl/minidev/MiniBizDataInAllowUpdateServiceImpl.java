package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDataInAllowUpdate;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizDataInAllowUpdateService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizDataInAllowUpdateMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 业务数据跟允许修改中间表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-12-07
 */
@Service
public class MiniBizDataInAllowUpdateServiceImpl extends ServiceImpl<MiniBizDataInAllowUpdateMapper, MiniBizDataInAllowUpdate> implements MiniBizDataInAllowUpdateService {


    /**
     * 批量保存
     *
     * @param mainIds              主表IDS
     * @param miniBizAllowUpdateId 业务允许修改ID
     * @author szs
     * @date 2023-12-07
     */
    @Override
    public void batchSave(List<String> mainIds, Long miniBizAllowUpdateId) {
        // 查询原有数据
        QueryWrapper<MiniBizDataInAllowUpdate> qw = new QueryWrapper<>();
        qw.in("main_id", mainIds);
        qw.eq("is_deleted", 0);
        List<MiniBizDataInAllowUpdate> list = this.list(qw);

        // Ids
        List<Long> ids = new ArrayList<>();
        for (MiniBizDataInAllowUpdate data : list) {
            if (miniBizAllowUpdateId == null) {
                ids.add(data.getId());
            } else {
                data.setMiniBizAllowUpdateId(miniBizAllowUpdateId);
                mainIds.remove(data.getMainId());
            }
        }

        // 如果miniBizAllowUpdateId为空，那就删除，否则修改
        if (miniBizAllowUpdateId == null) {
            if (ids.size() > 0) {
                // 批量删除
                boolean bo = this.removeByIds(ids);
                if (!bo) {
                    throw new ServiceException("批量删除失败");
                }
            }

        } else {
            // 组装新数据
            for (String mainId : mainIds) {
                MiniBizDataInAllowUpdate data = new MiniBizDataInAllowUpdate();
                data.setMiniBizAllowUpdateId(miniBizAllowUpdateId);
                data.setMainId(mainId);
                data.setIsDeleted(0);
                list.add(data);
            }

            // 批量保存
            boolean bo = this.saveOrUpdateBatch(list);
            if (!bo) {
                throw new ServiceException("批量保存失败");
            }
        }

    }


    /**
     * 获取业务数据跟允许修改中间表
     *
     * @param mainId 主表id
     * @return MiniBizDataInAllowUpdate
     * @author szs
     * @date 2023-12-07
     */
    @Override
    public MiniBizDataInAllowUpdate getDataInAllowUpdate(String mainId) {
        QueryWrapper<MiniBizDataInAllowUpdate> qw = new QueryWrapper<>();
        qw.eq("main_id", mainId);
        qw.eq("is_deleted", 0);
        qw.last(" LIMIT 1 ");

        return this.getOne(qw);
    }


}
