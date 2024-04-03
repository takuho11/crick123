package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.entity.ComponentHistory;
import cn.topcheer.halberd.app.api.minidev.service.ComponentHistoryService;
import cn.topcheer.halberd.app.dao.mapper.minidev.ComponentHistoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author Chill
 */
@Service
public class ComponentHistoryServiceImpl extends ServiceImpl<ComponentHistoryMapper, ComponentHistory> implements ComponentHistoryService {

}
