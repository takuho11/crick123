package cn.topcheer.halberd.app.biz.framework.service.impl.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApiResponse;
import cn.topcheer.halberd.app.api.framework.service.api.ApiResponseService;
import cn.topcheer.halberd.app.dao.mapper.framework.api.ApiResponseMapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author Chill
 */
@Service
public class ApiResponseServiceImpl extends BaseServiceImpl<ApiResponseMapper, AmApiResponse> implements ApiResponseService {

}
