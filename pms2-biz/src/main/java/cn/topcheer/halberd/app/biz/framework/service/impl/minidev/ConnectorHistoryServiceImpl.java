package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.entity.ConnectorHistory;
import cn.topcheer.halberd.app.api.minidev.service.ConnectorHistoryService;
import cn.topcheer.halberd.app.dao.mapper.minidev.ConnectorHistoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author Chill
 */
@Service
public class ConnectorHistoryServiceImpl extends ServiceImpl<ConnectorHistoryMapper, ConnectorHistory> implements ConnectorHistoryService {

}
