package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.MiniConnectorTreeDTO;
import cn.topcheer.halberd.app.api.minidev.result.MiniConnectorTreeResult;
import cn.topcheer.halberd.app.api.minidev.entity.MiniConnector;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 服务类
 *
 * @author Chill
 */
public interface MiniConnectorService extends IService<MiniConnector> {


    /**
     * 获取连接器树形数据
     * 连接器类别+数据二级树
     *
     * @param dto MiniConnectorTreeDTO
     * @return List
     * @author szs
     * @date 2023-08-30
     */
    List<MiniConnectorTreeResult> getMiniConnectorTree(MiniConnectorTreeDTO dto);


}
