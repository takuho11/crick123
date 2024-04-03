package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;


import cn.topcheer.halberd.app.api.minidev.dto.MiniConnectorTreeDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniConnector;
import cn.topcheer.halberd.app.api.minidev.result.MiniConnectorTreeResult;
import cn.topcheer.halberd.app.api.minidev.service.MiniConnectorService;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniConnectorMapper;
import cn.topcheer.halberd.biz.modules.system.entity.DictBiz;
import cn.topcheer.halberd.biz.modules.system.service.IDictBizService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
public class MiniConnectorServiceImpl extends ServiceImpl<MiniConnectorMapper, MiniConnector> implements MiniConnectorService {


    @Resource
    private IDictBizService dictService;


    /**
     * 获取连接器树形数据
     * 连接器类别+数据二级树
     *
     * @param dto MiniConnectorTreeDTO
     * @return List
     * @author szs
     * @date 2023-08-30
     */
    @Override
    public List<MiniConnectorTreeResult> getMiniConnectorTree(MiniConnectorTreeDTO dto) {
        List<MiniConnectorTreeResult> list = new ArrayList<>();

        // 获取连接器类别字典数据
        List<DictBiz> dictBizs = dictService.getList(dto.getCode());
        for (DictBiz dictBiz : dictBizs) {
            QueryWrapper<MiniConnector> qw = new QueryWrapper<>();
            qw.eq("categary", dictBiz.getDictKey());
            qw.eq("is_deleted", 0);
            qw.orderByDesc("create_time");
            List<MiniConnector> connectors = this.list(qw);
            if (connectors.size() > 0) {
                list.add(new MiniConnectorTreeResult(dictBiz.getId(), dictBiz.getDictKey(), dictBiz.getDictValue(), connectors));
            }
        }

        return list;
    }


}
