package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniApplication;
import cn.topcheer.halberd.app.api.minidev.service.MiniApplicationService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniApplicationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author Chill
 */
@Service
public class MiniApplicationServiceImpl extends ServiceImpl<MiniApplicationMapper, MiniApplication> implements MiniApplicationService {

}
