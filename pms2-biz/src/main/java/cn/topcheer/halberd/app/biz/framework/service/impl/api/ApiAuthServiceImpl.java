package cn.topcheer.halberd.app.biz.framework.service.impl.api;


import cn.topcheer.halberd.app.api.framework.dto.api.AmApiAuthDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmService;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiAuth;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.framework.enumerate.ApiType;
import cn.topcheer.halberd.app.api.framework.service.api.ApiAuthService;
import cn.topcheer.halberd.app.api.framework.service.api.ApiService;
import cn.topcheer.halberd.app.api.framework.service.api.AmServiceService;
import cn.topcheer.halberd.app.api.framework.vo.sys.SysUserVO;
import cn.topcheer.halberd.app.api.framework.wrapper.AmApiAuthWrapper;
import cn.topcheer.halberd.app.api.framework.wrapper.SysUserWrapper;
import cn.topcheer.halberd.app.dao.mapper.framework.api.ApiAuthMapper;
import cn.topcheer.halberd.biz.common.cache.SysCache;
import cn.topcheer.halberd.biz.common.cache.UserCache;
import cn.topcheer.halberd.biz.modules.base.service.IHalberdUserService;
import cn.topcheer.halberd.biz.modules.system.entity.AuthClient;

import cn.topcheer.halberd.biz.modules.system.service.IAuthClientService;
import org.springblade.plugin.workflow.core.utils.ObjectUtil;
import org.springblade.plugin.workflow.design.entity.WfProcessDef;
import org.springblade.plugin.workflow.design.service.IWfFormService;
import org.springblade.plugin.workflow.process.service.IWfProcessService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Api授权实现类
 *
 * @author Chill
 */
@Service
public class ApiAuthServiceImpl extends BaseServiceImpl<ApiAuthMapper, AmApiAuth> implements ApiAuthService {


    @Resource
    private ApiService apiService;
    @Resource
    private AmServiceService amServiceService;
    @Resource
    private IAuthClientService authClientService;
    @Resource
    private IHalberdUserService<SysUser> userService;
    @Resource
    private IWfFormService formService;
    @Resource
    private IWfProcessService processService;

    @Override
    public <E extends IPage<AmApiAuth>> E page(E page, Wrapper<AmApiAuth> queryWrapper) {
        page = super.page(page, queryWrapper);

        List<AmApiAuth> apiAuths = new ArrayList<>();
        page.getRecords().forEach(apiAuth -> {
            AmApiAuthDTO dto = AmApiAuthWrapper.build().entityVO(apiAuth);

            LambdaQueryWrapper wrapper = new LambdaQueryWrapper<AmApi>().eq(AmApi::getId, apiAuth.getApiId());
            AmApi api = apiService.getOne(wrapper);
            if (Objects.nonNull(api)) {
                dto.setApi(api);
                dto.setApiTypeName(ApiType.valueOf(api.getType()).getDescribe());
                dto.setDeptName(SysCache.getDept( UserCache.getUser(api.getCreateUser()).getDeptId()).getDeptName());

                wrapper = new LambdaQueryWrapper<AmService>()
                        .eq(AmService::getId, api.getServiceId());
                AmService service = amServiceService.getOne(wrapper);
                if (Objects.nonNull(service)) {
                    dto.setServiceName(service.getName());
                }
            }
            if ("User".equals(dto.getAuthType())) {
                SysUser user = userService.getById(dto.getSourceId());
                if (Objects.nonNull(user)) {
                    dto.setApplier(SysUserWrapper.build().entityVO(user));
                }
            }
            apiAuths.add(dto);
        });
        page.setRecords(apiAuths);
        return page;
    }


    @Override
    public List<AmApiAuth> listAuth() {
        QueryWrapper<AmApiAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_user", AuthUtil.getUserId());
        List<AmApiAuth> apiAuthList = this.list(queryWrapper);


        List<AmApiAuth> apiAuths = new ArrayList<>();
        apiAuthList.forEach(apiAuth -> {
            AmApiAuthDTO dto = AmApiAuthWrapper.build().entityVO(apiAuth);

            LambdaQueryWrapper wrapper = new LambdaQueryWrapper<AmApi>().eq(AmApi::getId, apiAuth.getApiId());
            AmApi api = apiService.getOne(wrapper);
            if (Objects.nonNull(api)) {
                dto.setApi(api);
                dto.setApiTypeName(ApiType.valueOf(api.getType()).getDescribe());
                dto.setDeptName(SysCache.getDept(UserCache.getUser(api.getCreateUser()).getDeptId()).getDeptName());

                wrapper = new LambdaQueryWrapper<AmService>()
                        .eq(AmService::getId, api.getServiceId());
                AmService service = amServiceService.getOne(wrapper);
                if (Objects.nonNull(service)) {
                    dto.setServiceName(service.getName());
                }
            }
//            wrapper = new LambdaQueryWrapper<Application>().eq(Application::getId,apiAuth.getSourceId());
//            Application application = applicationService.getOne(wrapper);
//            if(Objects.nonNull(application)){
//                dto.setAppName(application.getName());
//            }
            apiAuths.add(dto);
        });
        return apiAuths;
    }

    /**
     * @param apiName
     */
    @Override
    public R validApiAuth(String apiName) {
        BladeUser user = AuthUtil.getUser();
        if (Objects.isNull(user)) {
            return R.fail("用户为空！");
        }

        // 根据api路径查询是否存在该Api
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<AmApi>()
                .eq(AmApi::getPath, "/" + apiName)
                .eq(AmApi::getIsDeleted, 0)
                .last(" LIMIT 1 ");
        AmApi api = apiService.getOne(queryWrapper);
        if (Objects.isNull(api)) {
            return R.fail("API不存在！");
        }

        String roleIds = null;
        // 访客模式
        if (Objects.nonNull(user.getClientId())) {
            // 根据访客ID查询不在删除状态的应用
            queryWrapper = new LambdaQueryWrapper<AuthClient>()
                    .eq(AuthClient::getClientId, user.getClientId())
                    .last(" LIMIT 1 ");
            AuthClient client = authClientService.getOne(queryWrapper);
            if (Objects.isNull(client.getRoleId())) {
                return R.fail("当前访客未设置角色！");
            }

            roleIds = client.getRoleId() + "";
        }

        // 用户模式
        if (Func.isNotEmpty(user.getUserId())) {
            if (StringUtils.isBlank(user.getRoleId()) || user.getRoleId().equals("-1")) {
                return R.fail("当前用户未设置角色！");
            }

            roleIds = user.getRoleId();
        }

        AmApiAuth apiAuth = null;
        // 根据角色ID查询Api权限
        if (StringUtils.isNotEmpty(roleIds)) {
            List<Long> ids = Func.toLongList(roleIds);
            for (Long roleId : ids) {
                queryWrapper = new LambdaQueryWrapper<AmApiAuth>()
                        .eq(AmApiAuth::getSourceId, roleId)
                        .eq(AmApiAuth::getAuthType, "Role")
                        .eq(AmApiAuth::getApiId, api.getId())
                        .eq(AmApiAuth::getIsDeleted, 0)
                        .last(" LIMIT 1 ");
                apiAuth = this.getOne(queryWrapper);
                if (Objects.nonNull(apiAuth)) {
                    break;
                }
            }
        }

        if (Objects.isNull(apiAuth)) {
            return R.fail("未查询到设置的API权限！");
        }

        if (apiAuth.getValidDate().isBefore(LocalDate.now())) {
            return R.fail("Api权限已过期！");
        }

        return R.data(api);
    }


    @Override
    public R updateApi2ApplicationAuth(String apiId, String applicationId, int status) {

        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq(true, "api_id", apiId)
                .eq(true, "source_id", applicationId)
                .eq(true, "auth_type", "Application");
        long count = this.count(wrapper);
        if (count > 0) {
            UpdateWrapper updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status", status);
            updateWrapper.eq(true, "api_id", apiId)
                    .eq(true, "source_id", applicationId)
                    .eq(true, "auth_type", "Application");
            super.update(updateWrapper);
        } else {
            wrapper.clear();
            wrapper.eq(true, "api_id", apiId)
                    .eq(true, "source_id", AuthUtil.getUserId())
                    .eq(true, "auth_type", "User")
                    .eq(true, "status", 0);
            List<AmApiAuth> apiAuths = super.list(wrapper);
            if (apiAuths.size() == 0) {
                return R.fail("ssssss");
            }
            AmApiAuth auth = apiAuths.get(0);

            AmApiAuth apiAuth = new AmApiAuth();
            apiAuth.setSourceId(applicationId.toString());
            apiAuth.setApiId(apiId);
            apiAuth.setStatus(status);
            apiAuth.setAuthType("Application");

            apiAuth.setAuthReason(auth.getAuthReason());
            apiAuth.setValidDate(auth.getValidDate());
            apiAuth.setQpd(auth.getQpd());
            apiAuth.setQps(auth.getQps());
            apiAuth.setTqp(auth.getTqp());
            super.save(apiAuth);
        }
        return R.status(true);
    }


    /**
     * 分页获取申请API列表
     *
     * @param dto   ApiAuthDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2023-07-26
     */
    @Override
    public IPage<AmApiAuthDTO> getApiAuthList(AmApiAuthDTO dto, Query query) {
        QueryWrapper<AmApiAuth> qw = new QueryWrapper<>();
        qw.eq(dto.getId() != null, "a.id", dto.getId());
        qw.eq(dto.getServiceId() != null, "b.service_id", dto.getServiceId());
        qw.like(StringUtils.isNotBlank(dto.getApiName()), "b.name", dto.getApiName());
        qw.eq(StringUtils.isNotBlank(dto.getApiType()), "b.type", dto.getApiType());
        qw.eq(dto.getStatus() != null, "a.status", dto.getStatus());
        qw.like(StringUtils.isNotBlank(dto.getCreateUserName()), "d.name", dto.getCreateUserName());
        qw.eq(dto.getId() == null, "a.create_user", AuthUtil.getUserId());
        qw.eq("a.is_deleted", 0);
        qw.eq("b.is_deleted", 0);
        qw.orderByDesc("a.create_time");

        return baseMapper.getApiAuthList(new Page<>(query.getCurrent(), query.getSize()), qw);
    }


    /**
     * 申请
     *
     * @param dto ApiAuth
     * @author szs
     * @date 2023-07-26
     */
    @Override
    public void apply(AmApiAuth dto) {
        String userId = AuthUtil.getUserId();

        // 获取API
        AmApi api = apiService.getById(dto.getApiId());
        if (api == null) {
            throw new ServiceException("API不存在：" + dto.getApiId());
        }

        if (api.getCreateUser().equals(userId)) {
            throw new ServiceException("不能申请自己创建的API");
        }

        // 检查是否存在
        checkIsExist(dto.getApiId(), userId, api.getName());

        // 管理员直接审批通过
        if (AuthUtil.isAdmin()) {
            dto.setStatus(2);
        }

        // 添加
        boolean bo = this.save(dto);
        if (!bo) {
            throw new ServiceException("申请失败");
        }

        // 非管理员，需要走审批流程
        if (!AuthUtil.isAdmin()) {
            // 组装数据
            JSONObject body = new JSONObject();
            body.put("processDefKey", "process_apply_am_api_auth");
            body.put("business_code", "process_apply_am_api_auth");
            body.put("business_id", dto.getId().toString());
            body.put("business_title", api.getName());

            // 发起审批
            this.startProcess(body);
        }

    }


    /**
     * 检查是否存在
     *
     * @param apiId      APIID
     * @param createUser 创建人id
     * @param apiName    API名称
     * @author szs
     * @date 2023-07-26
     */
    private void checkIsExist(String apiId, String createUser, String apiName) {
        QueryWrapper<AmApiAuth> qw = new QueryWrapper<>();
        qw.eq("api_id", apiId);
        qw.eq("create_user", createUser);
        qw.in("status", 0, 1, 2);
        qw.eq("is_deleted", 0);
        long count = this.count(qw);
        if (count > 0) {


            throw new ServiceException("API已申请：" + apiName);
        }

    }


    /**
     * 获取申请API
     *
     * @param apiId  APIID
     * @param userId 　用户id
     * @return　ApiAuth
     * @author szs
     * @date 2023-09-11
     */
    @Override
    public AmApiAuth getApiAuth(String apiId, String userId) {
        QueryWrapper<AmApiAuth> qw = new QueryWrapper<>();
        qw.eq("api_id", apiId);
        qw.eq("create_user", userId);
        qw.eq("status", 2);
        qw.eq("is_deleted", 0);
        qw.last(" LIMIT 1 ");

        return this.getOne(qw);
    }

    /**
     * 发起审批
     *
     * @param body JSONObject
     * @author szs
     * @date 2023-06-25
     */
    @Override
    public void startProcess(JSONObject body) {
        String processDefKey = body.getString("processDefKey");
        if (ObjectUtil.isAnyEmpty(processDefKey)) {
            throw new ServiceException("processDefId为空");
        }

        // 获取流程表单
        Map<String, Object> formMap = formService.getFormByProcessDefKey(processDefKey);
        if (formMap == null) {
            throw new ServiceException("流程表单不存在");
        }

        WfProcessDef wfProcessDef = (WfProcessDef) formMap.get("process");
        if (wfProcessDef == null) {
            throw new ServiceException("流程表单不存在");
        }

        String processDefId = wfProcessDef.getId();

        // 发起审批
        processService.startProcessInstanceById(processDefId, body);
    }


}
