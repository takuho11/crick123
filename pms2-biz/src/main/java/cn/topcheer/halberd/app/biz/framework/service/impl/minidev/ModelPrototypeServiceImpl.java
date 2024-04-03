package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.entity.ModelPrototype;
import cn.topcheer.halberd.app.api.minidev.service.ModelPrototypeService;
import cn.topcheer.halberd.app.dao.mapper.minidev.ModelPrototypeMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ModelPrototypeServiceImpl extends BaseServiceImpl<ModelPrototypeMapper, ModelPrototype> implements ModelPrototypeService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submitList(List<ModelPrototype> modelPrototypes, String modelId) {
        // 查询已有的数据, 获取待删除的ids
        QueryWrapper<ModelPrototype> qw = new QueryWrapper<>();
        qw.eq("model_id", modelId);
        qw.eq("is_deleted", 0);
        List<ModelPrototype> list = this.list(qw);
        Set<String> toDelIds = list.stream().map(ModelPrototype::getId).collect(Collectors.toSet());

        // 数据处理
        int sortNumber = 0;
        for (ModelPrototype data : modelPrototypes) {
            data.setComment(StringUtils.isBlank(data.getComment()) ? data.getPropertyName() : data.getComment());
            data.setSortNumber(sortNumber);
            data.setIsDeleted(0);
            sortNumber++;

            if (data.getId() != null) {
                toDelIds.remove(data.getId());
            }

        }

        // 批量保存
        if (modelPrototypes.size() > 0) {
            this.saveOrUpdateBatch(modelPrototypes);
        }

        // 批量删除
        if (toDelIds.size() > 0) {
            this.removeBatchByIds(toDelIds);
        }

        return true;

    }

    @Override
    public List<ModelPrototype> listByModelId(Long modelId) {
        return this.list(Wrappers.<ModelPrototype>query().lambda().eq(ModelPrototype::getModelId, modelId));
    }

}
