package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDataInAllowUpdate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 * 业务数据跟允许修改中间表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-12-07
 */
public interface MiniBizDataInAllowUpdateService extends IService<MiniBizDataInAllowUpdate> {


    /**
     * 批量保存
     *
     * @param mainIds              主表IDS
     * @param miniBizAllowUpdateId 业务允许修改ID
     * @author szs
     * @date 2023-12-07
     */
    void batchSave(List<String> mainIds, Long miniBizAllowUpdateId);


    /**
     * 获取业务数据跟允许修改中间表
     *
     * @param mainId 主表id
     * @return MiniBizDataInAllowUpdate
     * @author szs
     * @date 2023-12-07
     */
    MiniBizDataInAllowUpdate getDataInAllowUpdate(String mainId);


}
