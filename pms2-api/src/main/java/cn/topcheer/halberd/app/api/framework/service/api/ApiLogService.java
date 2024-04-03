package cn.topcheer.halberd.app.api.framework.service.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApiLog;
import cn.topcheer.halberd.app.api.framework.dto.api.ApiLogDTO;
import cn.topcheer.halberd.app.api.framework.entity.result.api.AmApiLogResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.mp.support.Query;

/**
 * <p>
 * API调用日志表 服务类
 * </p>
 *
 * @author szs
 * @since 2023-09-11
 */
public interface ApiLogService extends IService<AmApiLog> {


    /**
     * 添加API日志
     *
     * @param apiId         APPID
     * @param apiPath       API路径
     * @param userId        用户id
     * @param clientId      应用ID
     * @param requestParam  请求参数
     * @param responseParam 返回参数
     * @param requestIp     请求IP
     * @param status        状态（0失败，1成功）
     * @author szs
     * @date 2023-09-11
     */
    void addAPiLog(String apiId, String apiPath, String userId, String clientId, String requestParam, String responseParam, String requestIp, Integer status);


    /**
     * 按秒统计访问频次
     *
     * @param apiId  APIID
     * @param userId 用户id
     * @return Long
     * @author szs
     * @date 2023-09-11
     */
    Long countBySecond(String apiId, String userId);


    /**
     * 按天统计访问频次
     *
     * @param apiId  APIID
     * @param userId 用户id
     * @return Long
     * @author szs
     * @date 2023-09-11
     */
    Long countByDay(String apiId, String userId);


    /**
     * 统计全部访问频次
     *
     * @param apiId  APIID
     * @param userId 用户id
     * @return Long
     * @author szs
     * @date 2023-09-11
     */
    Long countTotal(String apiId, String userId);


    /**
     * 分页
     *
     * @param dto   ApiLogDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2023-09-11
     */
    IPage<AmApiLogResult> getList(ApiLogDTO dto, Query query);


}
