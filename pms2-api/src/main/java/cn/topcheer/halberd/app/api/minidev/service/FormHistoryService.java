package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.FormHistory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  服务类
 *
 * @author Chill
 */
public interface FormHistoryService extends IService<FormHistory> {

    void saveBefore(FormHistory formHistory);
}
