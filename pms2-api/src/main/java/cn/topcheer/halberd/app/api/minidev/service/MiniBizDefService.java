package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.CopyBizDefDTO;
import cn.topcheer.halberd.app.api.minidev.dto.ImportTemplateDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniBizDefConfigDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizDef;
import cn.topcheer.halberd.app.api.minidev.entity.MiniConnector;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizDefConfigResult;
import cn.topcheer.halberd.app.api.minidev.result.MiniBizDefTabResult;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 业务模板表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-08-09
 */
public interface MiniBizDefService extends IService<MiniBizDef> {


    /**
     * 获取业务模板树
     *
     * @param id           主键ID
     * @param bizVersionId 版本id
     * @return List
     * @author szs
     * @date 2023-08-21
     */
    List<MiniBizDefTabResult> getMiniBizDefTree(Long id, Long bizVersionId);


    /**
     * 获取业务模板配置
     *
     * @param dto MiniBizDefConfigDTO
     * @return MiniBizDefConfigResult
     * @author szs
     * @date 2023-08-22
     */
    MiniBizDefConfigResult getMiniBizDefConfig(MiniBizDefConfigDTO dto);


    /**
     * 导入标准模板
     *
     * @param dto ImportTemplateDTO
     * @author szs
     * @date 2023-08-23
     */
    void importTemplate(ImportTemplateDTO dto);


    /**
     * 复制
     *
     * @param dto CopyBizDefDTO
     * @author szs
     * @date 2023-08-24
     */
    void copy(CopyBizDefDTO dto);


    /**
     * 复制业务模板数据
     *
     * @param copyBizDefId     复制业务模板id
     * @param copyBizVersionId 复制业务版本id
     * @param newBizDefId      新的业务模板id
     * @param newBizVersionId  新的业务版本id
     * @author szs
     * @date 2023-08-24
     */
    void copyBizDefData(Long copyBizDefId, Long copyBizVersionId, Long newBizDefId, Long newBizVersionId);


    /**
     * 获取连接器列表
     *
     * @param id 主键ID
     * @return List
     * @author szs
     * @date 2023-08-31
     */
    List<MiniConnector> getMiniConnectorList(Long id);


    /**
     * 获取业务模板配置
     *
     * @param dto MiniBizDefConfigDTO
     * @return MiniBizDefConfigResult
     * @author szs
     * @date 2023-10-27
     */
    MiniBizDefConfigResult getConfig(MiniBizDefConfigDTO dto);


    /**
     * 获取业务模板配置-JSON
     *
     * @param dto MiniBizDefConfigDTO
     * @return JSONObject
     * @author szs
     * @date 2023-12-01
     */
    JSONObject getConfigJson(MiniBizDefConfigDTO dto);


    /**
     * 转化为配置JSON
     *
     * @param dto MiniBizDefConfigDTO
     * @author szs
     * @date 2023-12-01
     */
    void toConfigJson(MiniBizDefConfigDTO dto);


    /**
     * 获取对象配置
     *
     * @param bizDefId     业务模板id
     * @param bizVersionId 业务版本id
     * @return String
     * @author szs
     * @date 2023-12-29
     */
    String getDataObject(Long bizDefId, Long bizVersionId);


}
