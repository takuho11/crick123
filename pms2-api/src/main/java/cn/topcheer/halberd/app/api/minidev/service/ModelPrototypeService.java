package cn.topcheer.halberd.app.api.minidev.service;


import cn.topcheer.halberd.app.api.minidev.entity.ModelPrototype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 代码建模原型表 服务类
 */
public interface ModelPrototypeService extends IService<ModelPrototype> {

    /**
     * 批量提交
     *
     * @param modelPrototypes 原型集合
     * @param modelId         模型id
     */
    boolean submitList(List<ModelPrototype> modelPrototypes, String modelId);


    public List<ModelPrototype> listByModelId(Long modelId);

}
