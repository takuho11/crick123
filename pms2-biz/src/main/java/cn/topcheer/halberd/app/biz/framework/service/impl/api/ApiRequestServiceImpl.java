package cn.topcheer.halberd.app.biz.framework.service.impl.api;


import cn.topcheer.halberd.app.api.framework.entity.api.AmApiRequest;
import cn.topcheer.halberd.app.api.framework.service.api.ApiRequestService;
import cn.topcheer.halberd.app.dao.mapper.framework.api.ApiRequestMapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author Chill
 */
@Service
public class ApiRequestServiceImpl extends BaseServiceImpl<ApiRequestMapper, AmApiRequest> implements ApiRequestService {

}
