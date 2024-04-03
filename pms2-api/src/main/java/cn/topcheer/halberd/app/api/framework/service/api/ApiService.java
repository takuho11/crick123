package cn.topcheer.halberd.app.api.framework.service.api;

import cn.topcheer.halberd.app.api.framework.dto.api.AmApiDTO;
import cn.topcheer.halberd.app.api.framework.dto.api.ApiDTO;
import cn.topcheer.halberd.app.api.framework.dto.api.Result;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiRequest;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jetbrains.annotations.NotNull;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 服务类
 *
 * @author Chill
 */
public interface ApiService extends IService<AmApi> {
    R saveOrUpdate(ApiDTO dto);

    ApiDTO getApiDTO(AmApi api);

    Object debug(ApiDTO apiDTO, List<AmApiRequest> requests, HttpServletRequest req, HttpServletResponse res);

    Object process(@NotNull String apiPath, JSONObject params, HttpServletRequest req, HttpServletResponse res);

    Object process(@NotNull AmApi api, JSONObject params, HttpServletRequest req, HttpServletResponse res);

    Result process(AmApi api, JSONObject params);

    //根据数据库用户查询所有可访问用户
    R getAllUsersByDataSource(String dataSourceId);

    //根据数据库用户查询表结构
    R getAllTablesByDataSource(String dataSourceId, String dataBase);

    //根据数据库用户查询表结构
    R getTableStructByUser(AmApi api);

    IPage listAndAuth(IPage page, Map params);


    /**
     * 分页获取API列表
     *
     * @param dto   AmApiDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2023-07-26
     */
    IPage<AmApiDTO> getApiList(AmApiDTO dto, Query query);

    /**
     * 查询api路径是否存在
     *
     * @param dataResourceId 数据资源ID
     * @param tableName      数据资源表名
     * @return 是否存在
     * @author zuowentao
     */
    Boolean verifyApiRoute(Long dataResourceId, String tableName);


    /**
     * 根据API查询表结构
     *
     * @param apiId APIID
     * @return ApiDTO
     * @author szs
     * @date 2023-08-01
     */
    ApiDTO getTableStructByApi(String apiId);

    /**
     * 查询api路径是否是本人创建
     *
     * @param dataResourceId 数据资源ID
     * @param tableName      数据资源表名
     * @return 是否存在
     * @author zuowentao
     */
    Boolean verifyMyApiRoute(Long dataResourceId, String tableName);


    /**
     * 保存API
     *
     * @param dto ApiDTO
     * @author szs
     * @date 2023-09-14
     */
    void submit(ApiDTO dto);


}
