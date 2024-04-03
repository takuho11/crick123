package cn.topcheer.pms2.biz.map.impl;

import cn.topcheer.pms2.api.map.MapClob;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.map.mapper.MapClobMapper;
import cn.topcheer.pms2.biz.map.MapClobService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务实现类
 * @author zk
 * @since 2024-03-09
 */
@Service
public class MapClobServiceImpl extends ServiceImpl<MapClobMapper, MapClob> implements MapClobService {

    @Override
    public void saveData(String type, Object object) {
        MapClob clob;
        //==========根据类型获取数据==========
        List<MapClob> list = this.list(new LambdaQueryWrapper<MapClob>()
                .eq(MapClob::getType,type));
        if(list.size()>0){
            clob = list.get(0);
        }else{
            clob = new MapClob();
            clob.setId(Util.NewGuid());
            clob.setType(type);
        }
        //==========保存数据==========
        clob.setSource(object.toString());
        clob.setLastupdate(new Date());
        this.saveOrUpdate(clob);
    }

    @Override
    public Object getData(String type) {
        //==========根据类型获取数据==========
        List<MapClob> list = this.list(new LambdaQueryWrapper<MapClob>()
                .eq(MapClob::getType,type));
        if(list.size()>0){
            //默认返回数组，如果后续有其他情况再优化
            return JSONArray.fromObject(list.get(0).getSource());
        }else{
            //默认返回空数组，如果后续有其他情况再优化
            return new ArrayList<>();
        }
    }
}
