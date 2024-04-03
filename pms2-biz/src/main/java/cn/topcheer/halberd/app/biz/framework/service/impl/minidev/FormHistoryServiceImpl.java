package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.entity.FormHistory;
import cn.topcheer.halberd.app.api.minidev.service.FormHistoryService;
import cn.topcheer.halberd.app.dao.mapper.minidev.FormHistoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *  服务实现类
 *
 * @author Chill
 */
@Service
public class FormHistoryServiceImpl extends ServiceImpl<FormHistoryMapper, FormHistory> implements FormHistoryService {

    @Override
    public void saveBefore(FormHistory formHistory) {
        formHistory.setCreateTime(LocalDateTime.now());
        formHistory.setUpdateTime(LocalDateTime.now());
        BladeUser user = AuthUtil.getUser();
        if(user != null){
            formHistory.setCreateUser(user.getUserId());
            formHistory.setUpdateUser(user.getUserId());
        }
        formHistory.setIsDeleted(0);
    }
}
