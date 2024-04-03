package cn.topcheer.halberd.app.biz.framework.service.impl;


import cn.topcheer.halberd.app.api.framework.dto.api.AmApplicationDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApplication;
import cn.topcheer.halberd.app.api.framework.service.api.ApplicationService;
import cn.topcheer.halberd.app.api.framework.wrapper.AmApplicationWrapper;
import cn.topcheer.halberd.app.dao.mapper.framework.api.ApplicationMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  服务实现类
 *
 * @author Chill
 */
@Service
public class ApplicationServiceImpl extends BaseServiceImpl<ApplicationMapper, AmApplication> implements ApplicationService {

    @Override
    public <E extends IPage<AmApplication>> E page(E page, Wrapper<AmApplication> queryWrapper) {
        page = super.page(page, queryWrapper);

        List<AmApplication> applications = new ArrayList<>();
        page.getRecords().forEach(service -> {
            AmApplicationDTO dto = AmApplicationWrapper.build().entityVO(service);
            dto.setValidCount(10);
            applications.add(dto);
        });
        page.setRecords(applications);
        return page;
    }

}
