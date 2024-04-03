package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizType;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizTypeService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizTypeMapper;
import cn.topcheer.pms2.api.plan.entity.PmsPlantype;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务分类表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
@Service
public class MiniBizTypeServiceImpl extends ServiceImpl<MiniBizTypeMapper, MiniBizType> implements MiniBizTypeService {

    @Override
    public String hasType(PmsPlantype type) {
        QueryWrapper<MiniBizType> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MiniBizType::getBizTypeCode, type.getCode());
        MiniBizType miniBizType = this.getOne(wrapper);
        if (miniBizType == null){
            miniBizType = new MiniBizType();
            miniBizType.setBizTypeCode(type.getCode());
            miniBizType.setBizTypeName(type.getName());
            miniBizType.setIsDeleted(0);
            this.saveOrUpdate(miniBizType);
        }
        return String.valueOf(miniBizType.getId());
    }

}
