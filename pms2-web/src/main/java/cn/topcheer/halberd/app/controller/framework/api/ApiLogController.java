package cn.topcheer.halberd.app.controller.framework.api;

import cn.topcheer.halberd.app.api.framework.dto.IdDTO;
import cn.topcheer.halberd.app.api.framework.dto.api.ApiLogDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiAuth;
import cn.topcheer.halberd.app.api.framework.entity.result.api.ApiAccessAuthorizeResult;
import cn.topcheer.halberd.app.api.framework.entity.result.api.AmApiLogResult;
import cn.topcheer.halberd.app.api.framework.service.api.ApiAuthService;
import cn.topcheer.halberd.app.api.framework.service.api.ApiLogService;
import cn.topcheer.halberd.app.api.framework.service.api.ApiService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@AllArgsConstructor
@RequestMapping("/apiLog")
@Api(value = "Api日志", tags = "API-Api日志")
public class ApiLogController extends BladeController {


    private final ApiLogService apiLogService;

    private final ApiAuthService apiAuthService;

    private final ApiService apiService;


    /**
     * 分页
     *
     * @author szs
     * @date 2023-09-11
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "分页", notes = "分页")
    public R<IPage<AmApiLogResult>> list(ApiLogDTO dto, Query query) {
        if (!StringUtil.isEmpty(dto.getCreateTimeStr())) {
            String[] arr = dto.getCreateTimeStr().split(",");
            if (arr.length == 2) {
                dto.setStartDate(LocalDate.parse(arr[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                dto.setEndDate(LocalDate.parse(arr[1], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        }

        return R.data(apiLogService.getList(dto, query));
    }


    /**
     * 获取API访问权限
     *
     * @author szs
     * @date 2023-09-14
     */
    @GetMapping("/getAccessAuthorize")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "获取API访问权限（API申请id）", notes = "获取API访问权限（API申请id）")
    public R<ApiAccessAuthorizeResult> getAccessAuthorize(@Valid IdDTO dto) {
        ApiAccessAuthorizeResult result = new ApiAccessAuthorizeResult();

        // 查询API申请
        AmApiAuth apiAuth = apiAuthService.getById(dto.getId());
        if (apiAuth == null) {
            throw new ServiceException("API申请不存在");
        }

        // 查询API
        AmApi api = apiService.getById(apiAuth.getApiId());
        if (api == null) {
            throw new ServiceException("API不存在");
        }

        // 访问权限, 非管理员，非创建人，api需要访问鉴权
        if (!AuthUtil.isAdmin() && !api.getCreateUser().equals(AuthUtil.getUserId()) && api.getIsAccessAuthorize()) {
            result.setIsAccessAuthorize(true);
            result.setTodayNum(apiLogService.countByDay(apiAuth.getApiId(), apiAuth.getCreateUser()));
            result.setTotalNum(apiLogService.countTotal(apiAuth.getApiId(), apiAuth.getCreateUser()));
            result.setIsExpired(apiAuth.getValidDate().isBefore(LocalDate.now()));
        }

        return R.data(result);
    }


}
