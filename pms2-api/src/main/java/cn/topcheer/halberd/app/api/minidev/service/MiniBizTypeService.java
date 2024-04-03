package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizType;
import cn.topcheer.pms2.api.plan.entity.PmsPlantype;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 业务分类表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
public interface MiniBizTypeService extends IService<MiniBizType> {

    String hasType(PmsPlantype code);
}
