package cn.topcheer.halberd.app.biz.framework.service.impl;


import cn.topcheer.halberd.app.api.framework.dto.api.AmServiceDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmService;
import cn.topcheer.halberd.app.api.framework.service.api.AmServiceService;
import cn.topcheer.halberd.app.api.framework.wrapper.AmServiceWrapper;
import cn.topcheer.halberd.app.dao.mapper.framework.api.ServiceMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *  服务实现类
 *
 * @author Chill
 */
@org.springframework.stereotype.Service
public class AmServiceServiceImpl extends BaseServiceImpl<ServiceMapper, AmService> implements AmServiceService {

    @Override
    public <E extends IPage<AmService>> E page(E page, Wrapper<AmService> queryWrapper) {
        page = super.page(page, queryWrapper);

        List<AmService> services = new ArrayList<>();
        page.getRecords().forEach(service -> {
            AmServiceDTO dto = AmServiceWrapper.build().entityVO(service);
            dto.setValidCount(10);
            services.add(dto);
        });
        page.setRecords(services);
        return page;
    }
}
