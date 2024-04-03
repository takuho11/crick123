package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.MiniBizAuditRemarkDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizAuditRemark;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizAuditRemarkResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 业务批注表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-11-16
 */
public interface MiniBizAuditRemarkService extends IService<MiniBizAuditRemark> {


    /**
     * 获取全部列表
     *
     * @param dto MiniBizAuditRemarkDTO
     * @return List
     * @author szs
     * @date 2023-11-16
     */
    List<MiniBizAuditRemarkResult> getAllList(MiniBizAuditRemarkDTO dto);


}
