package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.dto.MiniBizAuditRemarkDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAuditRemark;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizAuditRemarkResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizAuditRemarkService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizAuditRemarkMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业务批注表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-11-16
 */
@Service
public class MiniBizAuditRemarkServiceImpl extends ServiceImpl<MiniBizAuditRemarkMapper, MiniBizAuditRemark> implements MiniBizAuditRemarkService {


    /**
     * 获取全部列表
     *
     * @param dto MiniBizAuditRemarkDTO
     * @return List
     * @author szs
     * @date 2023-11-16
     */
    @Override
    public List<MiniBizAuditRemarkResult> getAllList(MiniBizAuditRemarkDTO dto) {

        QueryWrapper<MiniBizAuditRemark> qw = new QueryWrapper<>();
        qw.eq(StringUtils.isNotBlank(dto.getMainId()), "a.main_id", dto.getMainId());
        qw.eq(StringUtils.isNotBlank(dto.getTableId()), "a.table_id", dto.getTableId());
        qw.eq(StringUtils.isNotBlank(dto.getFieldCode()), "a.field_code", dto.getFieldCode());
        qw.eq("a.is_deleted", 0);
        qw.orderByDesc("a.create_time");

        return baseMapper.getAllList(qw);
    }


}
