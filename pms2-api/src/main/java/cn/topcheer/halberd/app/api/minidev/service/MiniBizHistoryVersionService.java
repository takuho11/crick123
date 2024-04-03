package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.MiniInitAllTableDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniVersionCompareDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizHistoryVersion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 业务历史版本表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-11-16
 */
public interface MiniBizHistoryVersionService extends IService<MiniBizHistoryVersion> {


    /**
     * 添加历史版本
     *
     * @param mainId  主表id
     * @param content 内容
     * @author szs
     * @date 2023-11-16
     */
    void addHistoryVersion(String mainId, String content);


    /**
     * 版本比对
     *
     * @param dto MiniVersionCompareDTO
     * @return MiniInitAllTableDTO
     * @author szs
     * @date 2023-11-17
     */
    MiniInitAllTableDTO versionCompare(MiniVersionCompareDTO dto);


}
