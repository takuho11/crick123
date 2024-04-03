package cn.topcheer.halberd.app.controller.framework.api;

import cn.topcheer.halberd.app.api.framework.dto.api.AmApiAuthDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiAuth;
import cn.topcheer.halberd.app.api.framework.service.api.ApiAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * 控制器
 *
 * @author dpms
 */
@RestController
@AllArgsConstructor
@RequestMapping("//api_auth")
@Api(value = "Api申请", tags = "API-Api申请")
public class ApiAuthController extends BladeController {

    @Resource
    private final ApiAuthService apiAuthService;


    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入api_auth")
    public R detail(AmApiAuth api_auth) {
        AmApiAuth detail = apiAuthService.getOne(Condition.getQueryWrapper(api_auth));
        return R.data(detail);
    }

    /**
     * 分页
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入api_auth")
    public R<IPage<AmApiAuth>> list(AmApiAuth api_auth, Query query) {
        QueryWrapper<AmApiAuth> qw = Condition.getQueryWrapper(api_auth);
        qw.orderByDesc("create_time");
        IPage<AmApiAuth> pages = apiAuthService.page(Condition.getPage(query), qw);
        return R.data(pages);
    }


    /**
     * 分页获取申请API列表
     *
     * @author szs
     * @date 2023-07-26
     */
    @GetMapping("/getApiAuthList")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入api_auth")
    public R<IPage<AmApiAuthDTO>> getApiAuthList(AmApiAuthDTO dto, Query query) {

        return R.data(apiAuthService.getApiAuthList(dto, query));
    }


    /**
     * 分页
     */
    @GetMapping("/listByCurrentUser")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入api_auth")
    public R<List<AmApiAuth>> listByCurrentUser() {
        List<AmApiAuth> pages = apiAuthService.listAuth();
        return R.data(pages);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入api_auth")
    public R save(@Valid @RequestBody AmApiAuth api_auth) {
        return R.status(apiAuthService.save(api_auth));
    }

    /**
     * 新增
     */
    @PostMapping("/saveAuth")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入api_auth")
    public R saveAuth(@Valid @RequestBody AmApiAuth api_auth) {
        if ("User".equals(api_auth.getAuthType()) && StringUtils.isEmpty(api_auth.getSourceId())) {
            api_auth.setSourceId(AuthUtil.getUserId().toString());
        }
        apiAuthService.save(api_auth);
        return R.data(api_auth);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入api_auth")
    public R update(@Valid @RequestBody AmApiAuth api_auth) {
        return R.status(apiAuthService.updateById(api_auth));
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "新增或修改", notes = "传入api_auth")
    public R submit(@Valid @RequestBody AmApiAuth api_auth) {
        return R.status(apiAuthService.saveOrUpdate(api_auth));
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(apiAuthService.removeBatchByIds(Func.toLongList(ids)));
    }

    /**
     * 更新api对应的应用权限
     */
    @GetMapping("/updateApplicationAuth")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "更新api对应的应用权限", notes = "传入apiId和applicationId")
    public R updateApi2ApplicationAuth(@RequestParam String apiId, @RequestParam String source_id, @RequestParam Integer status) {
        return apiAuthService.updateApi2ApplicationAuth(apiId, source_id, status);
    }


    /**
     * 申请API
     *
     * @author szs
     * @date 2023-07-26
     */
    @PostMapping("/apply")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "申请API", notes = "申请API")
    public R<String> apply(@Valid @RequestBody AmApiAuth dto) {

        // 申请
        apiAuthService.apply(dto);

        return R.success("申请成功");
    }


}
