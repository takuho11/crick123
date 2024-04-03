package cn.topcheer.halberd.app.biz.framework.service.impl.api;


import cn.topcheer.halberd.app.api.framework.dto.api.ApiLogDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiLog;
import cn.topcheer.halberd.app.api.framework.entity.result.api.AmApiLogResult;
import cn.topcheer.halberd.app.api.framework.service.api.ApiLogService;
import cn.topcheer.halberd.app.dao.mapper.framework.api.ApiLogMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * API调用日志表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-09-11
 */
@Service
public class ApiLogServiceImpl extends ServiceImpl<ApiLogMapper, AmApiLog> implements ApiLogService {



    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");


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
    @Override
    public void addAPiLog(String apiId, String apiPath, String userId, String clientId, String requestParam, String responseParam, String requestIp, Integer status) {
        AmApiLog apiLog = new AmApiLog();
        apiLog.setApiId(apiId);
        apiLog.setApiPath(apiPath);
        apiLog.setUserId(userId);
        apiLog.setClientId(clientId);
        apiLog.setRequestParam(requestParam);
        apiLog.setResponseParam(responseParam);
        apiLog.setRequestIp(requestIp);
        apiLog.setStatus(status);
        apiLog.setRequestTime(LocalDateTime.now());
        boolean bo = this.save(apiLog);
        if (!bo) {
            throw new ServiceException("添加API调用日志失败");
        }

    }


    /**
     * 按秒统计访问频次
     *
     * @param apiId  APIID
     * @param userId 用户id
     * @return Long
     * @author szs
     * @date 2023-09-11
     */
    @Override
    public Long countBySecond(String apiId, String userId) {
        QueryWrapper<AmApiLog> qw = new QueryWrapper<>();
        qw.eq("status", 1);
        qw.eq("api_id", apiId);
        qw.eq("user_id", userId);
        qw.ge("request_time", LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter));

        return this.count(qw);
    }


    /**
     * 按天统计访问频次
     *
     * @param apiId  APIID
     * @param userId 用户id
     * @return Long
     * @author szs
     * @date 2023-09-11
     */
    @Override
    public Long countByDay(String apiId, String userId) {
        QueryWrapper<AmApiLog> qw = new QueryWrapper<>();
        qw.eq("status", 1);
        qw.eq("api_id", apiId);
        qw.eq("user_id", userId);
        qw.ge("request_time", LocalDateTime.parse(LocalDate.now().format(formatter2) + " 00:00:00", formatter));

        return this.count(qw);
    }


    /**
     * 统计全部访问频次
     *
     * @param apiId  APIID
     * @param userId 用户id
     * @return int
     * @author szs
     * @date 2023-09-11
     */
    @Override
    public Long countTotal(String apiId, String userId) {
        QueryWrapper<AmApiLog> qw = new QueryWrapper<>();
        qw.eq("status", 1);
        qw.eq("api_id", apiId);
        qw.eq("user_id", userId);

        return this.count(qw);
    }


    /**
     * 分页
     *
     * @param dto   ApiLogDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2023-09-11
     */
    @Override
    public IPage<AmApiLogResult> getList(ApiLogDTO dto, Query query) {
        QueryWrapper<AmApiLog> qw = new QueryWrapper<>();
        qw.eq(StringUtils.isNotBlank(dto.getApiId()), "a.api_id", dto.getApiId());
        qw.like(StringUtils.isNotBlank(dto.getClientId()), "a.client_id", dto.getClientId());
        qw.eq(dto.getStatus() != null, "a.status", dto.getStatus());
        qw.ge(dto.getStartDate() != null, "a.request_time", dto.getStartDate());
        if (dto.getEndDate() != null) {
            qw.lt("a.request_time", dto.getEndDate().plusDays(1));
        }
        qw.eq(!AuthUtil.isAdmin(), "a.user_id", AuthUtil.getUserId());
        qw.orderByDesc("a.request_time");
        qw.orderByAsc("a.api_id");

        return baseMapper.getList(new Page<>(query.getCurrent(), query.getSize()), qw);
    }


}
