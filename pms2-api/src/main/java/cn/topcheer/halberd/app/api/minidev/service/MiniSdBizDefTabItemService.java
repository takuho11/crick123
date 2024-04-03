package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 标准业务分页子项表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
public interface MiniSdBizDefTabItemService extends IService<MiniSdBizDefTabItem> {


    /**
     * 检测是否死循环
     *
     * @param item MiniSdBizDefTabItem
     * @author szs
     * @date 2023-08-09
     */
    void checkIsEndlessLoop(MiniSdBizDefTabItem item);


    /**
     * 检测编码是否重复
     *
     * @param miniSdBizDefTabItem MiniSdBizDefTabItem
     * @author szs
     * @date 2023-12-15
     */
    void checkCodeIsRepeat(MiniSdBizDefTabItem miniSdBizDefTabItem);


}
