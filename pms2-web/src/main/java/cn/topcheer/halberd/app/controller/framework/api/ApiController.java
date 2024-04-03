package cn.topcheer.halberd.app.controller.framework.api;


import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.framework.dto.api.AmApiDTO;
import cn.topcheer.halberd.app.api.framework.dto.api.ApiDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiRequest;
import cn.topcheer.halberd.app.api.framework.service.api.ApiService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 控制器
 *
 * @author dpms
 */
@RestController
@AllArgsConstructor
@RequestMapping("/am_api")
@Api(value = "API处理接口", tags = "API-API处理接口")
public class ApiController extends BladeController {

    @Resource
    private final ApiService apiService;


    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入am_api")
    public R detail(AmApi am_api) {
        ApiDTO dto = apiService.getApiDTO(am_api);
        return R.data(dto);
    }

    /**
     * 分页
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入am_api")
    public R<IPage<AmApi>> list(@RequestParam(required = false) Map params, Query query) {
        QueryWrapper<AmApi> wrapper = Condition.getQueryWrapper(params, AmApi.class);
        wrapper.isNull("api_id");
        wrapper.eq("create_user", AuthUtil.getUserId());
        wrapper.orderByDesc("update_time");
        IPage<AmApi> pages = apiService.page(Condition.getPage(query), wrapper);
        return R.data(pages);
    }


    /**
     * 分页获取API列表
     *
     * @author szs
     * @date 2023-07-26
     */
    @GetMapping("/getApiList")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "分页")
    public R<IPage<AmApiDTO>> getApiList(AmApiDTO dto, Query query) {

        return R.data(apiService.getApiList(dto, query));
    }


    /**
     * 分页
     */
    @GetMapping("/listAndAuth")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入查询参数")
    public R<IPage> listAndAuth(@RequestParam(required = false) Map params, Query query) {
        IPage pages = apiService.listAndAuth(Condition.getPage(query), params);
        return R.data(pages);
    }


    /**
     * 分页
     */
    @GetMapping("/select")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "过滤查询", notes = "传入am_api")
    public R<List<AmApi>> select(AmApi api) {
        return R.data(apiService.list(Condition.getQueryWrapper(api)));
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入am_api")
    public R save(@Valid @RequestBody AmApi am_api) {
        return R.status(apiService.save(am_api));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入am_api")
    public R update(@Valid @RequestBody AmApi am_api) {
        return R.status(apiService.updateById(am_api));
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "新增或修改", notes = "传入ApiDTO")
    public R<String> submit(@Valid @RequestBody ApiDTO apiDTO) {

        // 保存
        apiService.submit(apiDTO);

        return R.success("保存成功");
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {

        return R.status(apiService.removeBatchByIds(Func.toStrList(ids)));
    }


    /**
     * 调试出参
     */
    @PostMapping("/outParams")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "调试出参", notes = "传入am_api")
    public R outParams(@Valid @RequestBody ApiDTO apiDTO, HttpServletRequest req, HttpServletResponse res) {
        return R.data(apiService.debug(apiDTO, null, req, res));
    }

    /**
     * 调试
     */
    @PostMapping("/debug")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "调试", notes = "传入am_api")
    public Object debug(@Valid @RequestBody ApiDTO apiDTO, HttpServletRequest req, HttpServletResponse res) {
        List<AmApiRequest> requests = apiDTO.getRequests();
        return apiService.debug(apiDTO, requests, req, res);
    }

    /**
     * 根据数据库用户查询所有数据库
     */
    @GetMapping("/getAllUsersByDataSource")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "根据数据库用户查询所有数据库", notes = "传入am_api")
    public R getAllUsersByDataSource(Long dataSourceId) {
        R data = apiService.getAllUsersByDataSource(dataSourceId.toString());
        return data;
    }

    /**
     * 根据数据库用户查询所有表
     */
    @GetMapping("/getAllTablesByDataSource")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "根据数据库用户查询所有表", notes = "传入am_api")
    public R getAllTablesByDataSource(Long dataSourceId, String schemaName) {
        return apiService.getAllTablesByDataSource(dataSourceId.toString(), schemaName);
    }

    /**
     * 根据数据库用户查询表结构
     */
    @PostMapping("/getTableStructByUser")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "根据数据库用户查询表结构", notes = "传入am_api")
    public R getTableStructByUser(@RequestBody AmApi api) {
        return apiService.getTableStructByUser(api);
    }


    /**
     * 根据API查询表结构
     */
    @GetMapping("/getTableStructByApi")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "根据数据库用户查询表结构", notes = "传入am_api")
    public R<ApiDTO> getTableStructByApi(IdStrDTO dto) {

        return R.data(apiService.getTableStructByApi(dto.getId()));
    }


    /**
     * 发布/取消
     *
     * @author szs
     * @date 2023-07-26
     */
    @PostMapping("/updateStatus")
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "发布/取消", notes = "传入am_api")
    public R<String> updateStatus(@Valid @RequestBody IdStrDTO dto) {
        AmApi api = apiService.getById(dto.getId());
        if (api == null) {
            throw new ServiceException("查无此数据");
        }

        if (api.getApproveStatus() != 2) {
            throw new ServiceException("审批通过后才能进些该操作");
        }

        api.setStatus(api.getStatus() == 0 ? 1 : 0);
        boolean bo = apiService.updateById(api);
        if (!bo) {
            throw new ServiceException("操作失败");
        }

        return R.success("操作成功");
    }


    /**
     * 查询api路径是否存在
     *
     * @param dataResourceId 数据资源ID
     * @param tableName      数据资源表名
     * @return 是否存在
     * @author zuowentao
     */
    @GetMapping("/verifyApiRoute")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "查询api路径是否存在", notes = "")
    public R<Boolean> verifyApiRoute(Long dataResourceId, String tableName) {

        return R.data(apiService.verifyApiRoute(dataResourceId, tableName));
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public R<Boolean> delete(@ApiParam(value = "主键集合", required = true) @RequestParam String id) {
        List<String> ids = new ArrayList<>();
        ids.add(id);

        // 查询子API
        QueryWrapper<AmApi> qw = new QueryWrapper<>();
        qw.eq("api_id", id);
        qw.eq("is_deleted", 0);
        List<AmApi> apiList = apiService.list(qw);
        for (AmApi api : apiList) {
            ids.add(api.getId());
        }

        return R.status(apiService.removeBatchByIds(ids));
    }


}
