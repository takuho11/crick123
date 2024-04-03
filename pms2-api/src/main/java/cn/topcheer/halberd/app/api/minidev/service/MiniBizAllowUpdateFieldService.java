package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAllowUpdateField;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 * 业务允许修改字段表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-12-05
 */
public interface MiniBizAllowUpdateFieldService extends IService<MiniBizAllowUpdateField> {


    /**
     * 获取允许修改字段列表
     *
     * @param miniBizAllowUpdateId 业务允许修改ID
     * @return List
     * @author szs
     * @date 2023-12-07
     */
    List<MiniBizAllowUpdateField> getAllowUpdateFieldList(Long miniBizAllowUpdateId);


    /**
     * 保存
     *
     * @param miniBizAllowUpdateField MiniBizAllowUpdateField
     * @author szs
     * @date 2023-12-11
     */
    void submit(MiniBizAllowUpdateField miniBizAllowUpdateField);


}
