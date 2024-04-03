package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefTabItemFieldDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItemField;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 业务分页子项字段表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
public interface MiniBizDefTabItemFieldService extends IService<MiniBizDefTabItemField> {


    /**
     * 批量保存
     *
     * @param dto MiniBizDefTabItemFieldDTO
     * @author szs
     * @date 2023-08-09
     */
    void batchSave(MiniBizDefTabItemFieldDTO dto);


}
