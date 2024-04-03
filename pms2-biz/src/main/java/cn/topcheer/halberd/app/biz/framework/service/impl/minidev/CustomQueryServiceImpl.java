package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.CustomQuery;
import cn.topcheer.halberd.app.api.minidev.service.CustomQueryService;
import cn.topcheer.halberd.app.dao.mapper.minidev.CustomQueryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomQueryServiceImpl extends ServiceImpl<CustomQueryMapper, CustomQuery> implements CustomQueryService {
    
}
