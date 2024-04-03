package cn.topcheer.pms2.biz.map;

import cn.topcheer.pms2.api.map.MapResource;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 服务类
 * @author zk/byf
 * @since 2024-03-08
 */
public interface MapResourceService extends IService<MapResource> {

    /**
     * 保存-保存最新创新地图资源数据
     */
    void saveClob();

}
