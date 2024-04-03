package cn.topcheer.halberd.app.controller.framework.api;

import cn.hutool.core.util.ObjectUtil;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiAuth;
import cn.topcheer.halberd.app.api.framework.entity.client.BladeClient;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.framework.service.api.ApiAuthService;
import cn.topcheer.halberd.app.api.framework.service.api.ApiLogService;
import cn.topcheer.halberd.app.api.framework.service.api.ApiService;
import cn.topcheer.halberd.app.api.framework.service.client.BladeClientService;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysRoleService;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysUserService;
import cn.topcheer.halberd.biz.modules.base.entity.HalberdRole;
import cn.topcheer.halberd.biz.modules.base.service.IHalberdRoleService;
import cn.topcheer.pms2.common.utils.IpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dataApi")
@Api(value = "动态API", tags = "API-动态API")
public class DataApiController {

    private final ApiService apiService;

    private final ApiLogService apiLogService;

    private final ApiAuthService apiAuthService;

    private final BladeClientService bladeClientService;

    private final ISysUserService iUserService;

    private final IHalberdRoleService iRoleService;


//    /**
//     * 处理接口请求
//     */
//    @PostMapping("/process/**")
//    @ApiOperationSupport(order = 1)
//    @ApiOperation(value = "处理接口请求", notes = "传入接口入参")
//    public Object process(@RequestBody JSONObject params, HttpServletRequest request, HttpServletResponse response) {
//        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
//        String apiName = new AntPathMatcher().extractPathWithinPattern(pattern, request.getServletPath());
//
//        String paramValue = ParamCache.getValue("api.auth");
//        if ("on".equals(paramValue)) {
//            R result = apiAuthService.validApiAuth(apiName);
//            if (!result.isSuccess()) {
//                return result;
//            }
//
//            return apiService.process((Api) result.getData(), params, request, response);
//        }
//
//        LambdaQueryWrapper<Api> queryWrapper = new LambdaQueryWrapper<Api>()
//                .eq(Api::getPath, "/" + apiName)
//                .eq(Api::getIsDeleted, 0);
//        Api api = apiService.getOne(queryWrapper);
//        if (api == null) {
//            return Result.failOf("访问不存在的Api：" + apiName);
//        }
//
//        return apiService.process(api, params, request, response);


//        //获取授权用户
////        BladeUser user = AuthUtil.getUser();
////        if(Objects.nonNull(user)){
//        String clientId = AuthUtil.getClientId();
//        //如果授权用户不为空，则校验用户和API权限
//        if(StringUtils.isNotEmpty(clientId)){
//
//
//
//            //根据访客ID查询不在删除状态的应用
//            queryWrapper = new LambdaQueryWrapper<Application>()
////                    .eq(Application::getClientId,user.getClientId())
//                    .eq(Application::getClientId,clientId)
//                    .eq(Application::getIsDeleted,0);
//            Application application = applicationService.getOne(queryWrapper);
//            if(application == null)
//                return R.fail("该访客未关联授权应用 或者 授权应用已过期！");
//
//            //根据api路径查询是否存在该Api
//            queryWrapper = new LambdaQueryWrapper<Api>()
//                    .eq(Api::getPath,"/"+apiName)
//                    .eq(Api::getIsDeleted,0);
//            api = apiService.getOne(queryWrapper);
//
//            //根据应用ID和Api ID查询未删除、启用状态的Api授权
//            queryWrapper = new LambdaQueryWrapper<ApiAuth>()
//                    .eq(ApiAuth::getRoleId,application.getId())
//                    .eq(ApiAuth::getApiId,api.getId())
//                    .eq(ApiAuth::getStatus,0)
//                    .eq(ApiAuth::getIsDeleted,0);
//            List<ApiAuth> apiAuths = apiAuthService.list(queryWrapper);
//            if(apiAuths == null || apiAuths.size() == 0)
//                return R.fail("应用中没有API授权 或者 API授权已过期！");
//
//        }else{
//            //如果授权用户为空，则不校验 直接获取API
//            //根据api路径查询是否存在该Api
//            queryWrapper = new LambdaQueryWrapper<Api>()
//                    .eq(Api::getPath,"/"+apiName)
//                    .eq(Api::getIsDeleted,0);
//            api = apiService.getOne(queryWrapper);
//            if(api == null)
//                return Result.failOf("访问不存在的Api！"+apiName);
//        }
//        return apiService.process(api,params,request,response);
    //   }


    /**
     * 动态API接口请求
     *
     * @author szs
     * @date 2023-09-11
     */
    @PostMapping("/process/**")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "处理接口请求", notes = "传入接口入参")
    public Object process(@RequestBody JSONObject params, HttpServletRequest request, HttpServletResponse response) {
        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String apiPath = "/" + new AntPathMatcher().extractPathWithinPattern(pattern, request.getServletPath());
        String clientId = AuthUtil.getClientId();
        String userId = AuthUtil.getUserId();
        boolean isAdmin = AuthUtil.isAdmin();
        String requestIp = IpUtil.getRealIpByHttpRequest(request);

        // 查询API
        QueryWrapper<AmApi> qw = new QueryWrapper<>();
        qw.eq("path", apiPath);
        qw.eq("is_deleted", 0);
        qw.last(" LIMIT 1 ");
        AmApi api = apiService.getOne(qw);
        if (api == null) {
            throw new ServiceException("API不存在：" + apiPath);
        }

        // 如果是APIID不为空，那就的查询上级API
        AmApi apiChild = api;
        if (StringUtils.isNotBlank(api.getApiId())) {
            api = apiService.getById(api.getApiId());
            if (api == null) {
                throw new ServiceException("API不存在：" + apiPath);
            }
        }


        try {

            if (api.getApproveStatus() != 2) {
                throw new ServiceException("API未审批通过：" + apiPath);
            }

            if (api.getStatus() == 0) {
                throw new ServiceException("API未发布：" + apiPath);
            }

            // 当前登录账号还是应用, 0无登录，1 登录账号，2登录应用
            int loginType = 0;

            // 如果userId != -1, 即 loginType = 1，表明是登录账号，需要清空clientId
            if (ObjectUtil.notEqual(userId ,"-1")) {
                loginType = 1;
                clientId = "";
            }

            // 如果userId = -1 并且 clientId 不为空，即 loginType = 2，表明是clientId登录的，那就根据clientId查询userId
            if (ObjectUtil.notEqual(userId ,"-1") && StringUtils.isNotBlank(clientId)) {
                loginType = 2;
                if (StringUtils.isBlank(clientId)) {
                    throw new ServiceException("登录已过期：" + clientId);
                }

                // 查询应用
                BladeClient bladeClient = bladeClientService.getBladeClientByClientId(clientId);
                if (bladeClient == null) {
                    throw new ServiceException("应用不存在：" + clientId);
                }

                userId = bladeClient.getCreateUser();

                // 查询是否管理员
                isAdmin = checkIsAdmin(userId);
            }


            // 非管理员，需要鉴权时，进行访问权限校验，管理员不需要鉴权
            if (!isAdmin && api.getIsAccessAuthorize()) {
                if (loginType == 0) {
                    throw new ServiceException("API访问需要鉴权，请先登录");
                }

                // 非创建人（申请人）时，需要核对API申请并鉴权
                if (!api.getCreateUser().equals(userId)) {
                    checkIsAccessAuthorize(api.getId(), userId);
                }

            }

            // 请求API
            Object process = apiService.process(apiChild, params, request, response);
            JSONObject data = JSONObject.parseObject(JSON.toJSONString(process));

            // 记录调用日志
            apiLogService.addAPiLog(api.getId(), apiPath, userId, clientId, params.toString(), JSON.toJSONString(process), requestIp, data.getBoolean("success") ? 1 : 0);
            return process;
        } catch (Exception ex) {
            ex.printStackTrace();

            // 记录调用日志
            apiLogService.addAPiLog(api.getId(), apiPath, userId, clientId, params.toString(), JSON.toJSONString(R.fail(ex.getMessage())), requestIp, 0);
            throw new ServiceException(ex.getMessage());
        }

    }


    /**
     * 检测是否是管理员
     *
     * @param userId 用户id
     * @return boolean
     * @author szs
     * @date 2023-09-12
     */
    private boolean checkIsAdmin(String userId) {
        // 查询用户
        SysUser user = iUserService.getById(userId);
        if (user == null) {
            throw new ServiceException("登录用户不存在：" + userId);
        }
        List<String> roleIds = iUserService.getRolesByUser(userId);


        if (roleIds.size()==0) {
            throw new ServiceException("登录用户未分配角色：" + userId);
        }

        // 组装ids
//        List<String> roleIds = new ArrayList<>();
//        String[] ids = user.getRoleId().split(",");
//        for (String id : ids) {
//            roleIds.add(Long.valueOf(id));
//        }

        // 统计角色
//        QueryWrapper<SysRole> qw = new QueryWrapper<>();
//        qw.in("id", roleIds);
//        qw.like("role_alias", "admin");

        return true;// iRoleService.count(qw) > 0;
    }


    /**
     * 检测访问权限
     *
     * @param apiId  APIID
     * @param userId 用户id
     * @author szs
     * @date 2023-09-12
     */
    private void checkIsAccessAuthorize(String apiId, String userId) {
        AmApiAuth apiAuth = apiAuthService.getApiAuth(apiId, userId);
        if (apiAuth == null) {
            throw new ServiceException("没有访问权限");
        }

        if (apiAuth.getValidDate().isBefore(LocalDate.now())) {
            throw new ServiceException("访问权限已过期");
        }


        // 每秒访问频次
        if (apiLogService.countBySecond(apiId, userId) >= apiAuth.getQps()) {
            throw new ServiceException("访问频繁，每秒访问频次：" + apiAuth.getQps());
        }

        // 总访问次数
        if (apiLogService.countTotal(apiId, userId) >= apiAuth.getTqp()) {
            throw new ServiceException("访问次数超限，总访问次数：" + apiAuth.getTqp());
        }

        // 每天访问频次
        if (apiLogService.countByDay(apiId, userId) >= apiAuth.getQpd()) {
            throw new ServiceException("访问频繁，每天访问频次：" + apiAuth.getQpd());
        }

    }


}
