package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.CopySdBizDefDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniConnector;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDef;
import cn.topcheer.halberd.app.api.minidev.result.MiniSdBizDefConfigResult;
import cn.topcheer.halberd.app.api.minidev.result.MiniSdBizDefTabResult;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 标准业务模板表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-08-21
 */
public interface MiniSdBizDefService extends IService<MiniSdBizDef> {


    /**
     * 获取标准模板树
     *
     * @param id 主键ID
     * @return List
     * @author szs
     * @date 2023-08-21
     */
    List<MiniSdBizDefTabResult> getMiniSdBizDefTree(Long id);


    /**
     * 获取标准模板配置
     *
     * @param id 主键ID
     * @return MiniSdBizDefConfigResult
     * @author szs
     * @date 2023-08-22
     */
    MiniSdBizDefConfigResult getMiniSdBizDefConfig(Long id);


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
     * 复制
     *
     * @param dto CopySdBizDefDTO
     * @author szs
     * @date 2023-10-23
     */
    void copy(CopySdBizDefDTO dto);


    /**
     * 获取标准模板配置
     *
     * @param id 主键ID
     * @return MiniSdBizDefConfigResult
     * @author szs
     * @date 2023-10-27
     */
    MiniSdBizDefConfigResult getConfig(Long id);


    /**
     * 获取标准模板配置-JSON
     *
     * @param id 主键ID
     * @return JSONObject
     * @author szs
     * @date 2023-12-01
     */
    JSONObject getConfigJson(Long id);


    /**
     * 转化为配置JSON
     *
     * @param id 主键ID
     * @author szs
     * @date 2023-12-01
     */
    void toConfigJson(Long id);


    /**
     * 获取对象配置
     *
     * @param id 主键ID
     * @return String
     * @author szs
     * @date 2023-12-29
     */
    String getDataObject(Long id);


}
