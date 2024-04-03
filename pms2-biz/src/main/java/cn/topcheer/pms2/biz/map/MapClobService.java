package cn.topcheer.pms2.biz.map;

import cn.topcheer.pms2.api.map.MapClob;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务类
 * @author zk
 * @since 2024-03-09
 */
public interface MapClobService extends IService<MapClob> {

    /**
     * 保存-根据类型保存数据
     * @param type 类型
     * @param object 数据
     */
    void saveData(String type,Object object);


    /**
     * 查询-根据类型获取数据
     * @param type 类型
     * @return 数据
     */
    Object getData(String type);

}
