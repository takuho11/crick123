package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.MiniSdBizDefTabItemFieldDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItemField;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 标准业务分页子项字段表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
public interface MiniSdBizDefTabItemFieldService extends IService<MiniSdBizDefTabItemField> {


    /**
     * 批量保存
     *
     * @param dto MiniSdBizDefTabItemFieldDTO
     * @author szs
     * @date 2023-08-09
     */
    void batchSave(MiniSdBizDefTabItemFieldDTO dto);


}
