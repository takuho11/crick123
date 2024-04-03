package cn.topcheer.halberd.app.api.minidev.service;


import cn.topcheer.halberd.app.api.minidev.dto.BatchAddModelDTO;
import cn.topcheer.halberd.app.api.minidev.entity.ModelPrototype;
import cn.topcheer.halberd.app.api.minidev.entity.TableInfo;
import cn.topcheer.halberd.app.api.minidev.result.ModelTreeResult;
import cn.topcheer.halberd.app.api.minidev.dto.ModelDTO;
import cn.topcheer.halberd.app.api.minidev.entity.Model;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ModelService extends IService<Model> {

    ModelDTO getDTO(Model model);


    TableInfo getTableInfo(String datasourceId, String schema, String tableName);


    List<ModelPrototype> createPrototypesByTable(String modelId);


    void convertExchangApi(Model model) throws Exception;


    /**
     * 获取模型树
     *
     * @return List
     * @author szs
     * @date 2023-08-21
     */
    List<ModelTreeResult> getModelTree();


    /**
     * 批次添加
     *
     * @param dto BatchAddModelDTO
     * @author szs
     * @date 2024-01-08
     */
    void batchAdd(BatchAddModelDTO dto);


}
