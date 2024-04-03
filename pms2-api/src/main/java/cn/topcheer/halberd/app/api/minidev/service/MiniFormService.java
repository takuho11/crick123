package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.MiniForm;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.mp.support.Query;

/**
 *  服务类
 *
 * @author Chill
 */
public interface MiniFormService extends IService<MiniForm> {

    /**
     * 保存
     * @param miniForm
     * @return
     */
    boolean saveForm(MiniForm miniForm);

    /**
     * 保存前的操作
     * @param miniForm
     * @return
     */
    MiniForm saveBefore(MiniForm miniForm);

    boolean submit(MiniForm miniForm);

    boolean removeById(String id);

    IPage<MiniForm> pageList(MiniForm miniForm, Query query);
}
