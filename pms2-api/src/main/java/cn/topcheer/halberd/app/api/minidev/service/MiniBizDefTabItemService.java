package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDefTabItem;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONObject;

/**
 * <p>
 * 业务分页子项表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
public interface MiniBizDefTabItemService extends IService<MiniBizDefTabItem> {


    /**
     * 检测是否死循环
     *
     * @param miniBizDefTabItem MiniBizDefTabItem
     * @author szs
     * @date 2023-10-27
     */
    void checkIsEndlessLoop(MiniBizDefTabItem miniBizDefTabItem);


    /**
     * 获取全部子项配置
     *
     * @param bizDefId     业务模板id
     * @param bizVersionId 业务版本id
     * @return JSONObject
     * @author szs
     * @date 2023-11-17
     */
    JSONObject getAllItemConfig(Long bizDefId, Long bizVersionId);


    /**
     * 检测编码是否重复
     *
     * @param miniBizDefTabItem MiniBizDefTabItem
     * @author szs
     * @date 2023-12-15
     */
    void checkCodeIsRepeat(MiniBizDefTabItem miniBizDefTabItem);


}
