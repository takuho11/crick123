package cn.topcheer.pms2.biz.map.impl;

import cn.topcheer.pms2.api.map.MapResource;
import cn.topcheer.pms2.biz.map.MapClobService;
import cn.topcheer.pms2.biz.map.MapResourceService;
import cn.topcheer.pms2.biz.map.utils.LocationUtil;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.map.mapper.MapResourceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONArray;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现类
 * @author zk/byf
 * @since 2024-03-08
 */
@Service
public class MapResourceServiceImpl extends ServiceImpl<MapResourceMapper, MapResource> implements MapResourceService {

    @Resource
    private MapClobService mapClobService;

    /*============================（↑ 注入 丨 ↓ 接口）============================*/

    /**
     * 每天8点、13点、17点定时保存，因为不确定MapResource表数据是否会更新；也支持调接口手动保存
     */
    @Override
    @Scheduled(cron="0 0 8,13,17 * * ?")
    public void saveClob() {
        //获取所有创新地图资源数据
        List<MapResource> list = this.list();
        //获取其中没有经纬度的数据
        List<MapResource> noLonAndLotList = list.stream().filter(m-> (Util.isEoN(m.getLongitude())||Util.isEoN(m.getLatitude())))
                .collect(Collectors.toList());
        //遍历调取高德接口获取经纬度，并保存单个数据
        for (MapResource map:noLonAndLotList) {
            String address = map.getProvince()+map.getCity()+map.getCounty()+map.getAddress();
            String[] location = LocationUtil.getLonAndLat(address);
            if(location.length==2){
                map.setLongitude(location[0]);
                map.setLatitude(location[1]);
                this.saveOrUpdate(map);
            }
        }
        //保存到大字段里
        mapClobService.saveData("mapResource", JSONArray.fromObject(list));
    }
}
