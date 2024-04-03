package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.GenModelType;
import cn.topcheer.halberd.app.api.minidev.service.GenModelTypeService;
import cn.topcheer.halberd.app.dao.mapper.minidev.GenModelTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模型类型表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Service
public class GenModelTypeServiceImpl extends ServiceImpl<GenModelTypeMapper, GenModelType> implements GenModelTypeService {

}
