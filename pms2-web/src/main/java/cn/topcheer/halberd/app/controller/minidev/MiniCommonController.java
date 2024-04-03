package cn.topcheer.halberd.app.controller.minidev;

import cn.topcheer.halberd.app.api.framework.dto.IdStrDTO;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.minidev.dto.*;
import cn.topcheer.halberd.app.api.minidev.result.*;
import cn.topcheer.halberd.app.api.minidev.service.MiniCommonService;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.halberd.flow.entity.BatchTaskDetail;
import cn.topcheer.halberd.flow.entity.BatchTaskJob;
import cn.topcheer.halberd.flow.service.FlowProcessService;
import cn.topcheer.pms2.biz.project.service.impl.PmsSaveAndInitNewService;
import cn.topcheer.pms2.biz.project.service.impl.enumUtil.TableMappingEnum;
import cn.topcheer.pms2.biz.utils.GenericFetchAndSave;
import com.alibaba.fastjson.JSON;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公共接口
 *
 * @author szs
 * @date 2023-11-03
 */

@RestController
@AllArgsConstructor
@RequestMapping("/minidev/miniCommon")
@Api(value = "低代码-公共接口", tags = "低代码-公共接口")
public class MiniCommonController extends BladeController {


    private final MiniCommonService miniCommonService;

    private final GenericFetchAndSave genericFetchAndSave;

    private final FlowProcessService processService;

    private final PmsSaveAndInitNewService pmsSaveAndInitNewService;

    private final SysUserServiceImpl sysUserService;


    /**
     * 判断全部Table
     *
     * @author szs
     * @date 2023-12-19
     */
    @PostMapping("/judgeAllTable")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "判断全部Table", notes = "判断全部Table")
    public R<String> judgeAllTable(@Valid @RequestBody MiniJudgeAllTableDTO dto) {

        // 判断全部Table
        miniCommonService.judgeAllTable(dto);

        return R.success("操作成功");
    }


    /**
     * 初始化全部Table
     *
     * @author szs
     * @date 2023-11-03
     */
    @PostMapping("/initAllTable")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "初始化全部Table", notes = "初始化全部Table")
    public R<MiniInitAllTableDTO> initAllTable(@Valid @RequestBody MiniInitAllTableDTO dto) {

        return R.data(miniCommonService.initAllTable(dto));
    }


    /**
     * 保存全部Table
     *
     * @author szs
     * @date 2023-11-03
     */
    @PostMapping("/saveAllTable")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "保存全部Table", notes = "保存全部Table")
    public R<String> saveAllTable(@Valid @RequestBody MiniSaveAllTableDTO dto, HttpServletRequest request) {

        // 保存全部Table
        miniCommonService.saveAllTable(dto, request);

        return R.success("保存成功");
    }


    /**
     * 获取流程路径
     *
     * @author szs
     * @date 2023-11-22
     */
    @GetMapping("/getProcessPath")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取流程路径", notes = "获取流程路径")
    public R<List<ProcessPathResult>> getProcessPath(@Valid ProcessPathDTO dto) {

        return R.data(miniCommonService.getProcessPath(dto));
    }


    /**
     * 发起流程
     *
     * @author szs
     * @date 2023-11-21
     */
    @PostMapping("/startProcess")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "发起流程", notes = "发起流程")
    public R<String> startProcess(@Valid @RequestBody StartProcessDTO dto) {

        // 发起流程
        miniCommonService.startProcess(dto);

        return R.success("发起成功");
    }


    /**
     * 删除附件
     *
     * @author szs
     * @date 2023-11-24
     */
    @PostMapping("/deleteFileByAttachId")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除附件", notes = "删除附件")
    public R<String> deleteFileByAttachId(@Valid @RequestBody IdStrDTO dto) {

        // 保存全部Table
        miniCommonService.deleteFileByAttachId(dto.getId());

        return R.success("删除成功");
    }


    /**
     * 获取流程流转列表
     *
     * @author szs
     * @date 2023-11-30
     */
    @GetMapping("/getProcessTransferList")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "获取流程流转列表", notes = "获取流程流转列表")
    public R<List<ProcessTransferResult>> getProcessTransferList(@Valid ProcessTransferDTO dto) {

        return R.data(miniCommonService.getProcessTransferList(dto));
    }


    /**
     * 获取流程流转数据
     *
     * @author szs
     * @date 2023-11-30
     */
    @GetMapping("/getProcessTransferData")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "获取流程流转数据", notes = "获取流程流转数据")
    public R<ProcessTransferDataResult> getProcessTransferData(@Valid ProcessTransferDTO dto) {

        return R.data(miniCommonService.getProcessTransferData(dto));
    }


    /**
     * 删除数据
     *
     * @author szs
     * @date 2023-12-05
     */
    @PostMapping("/deleteData")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "删除数据", notes = "删除数据")
    public R<String> deleteData(@RequestBody JSONObject jsonObject) {

        // 删除数据
        genericFetchAndSave.deleteData(jsonObject);

        return R.success("删除成功");
    }


    /**
     * 判断任务
     *
     * @author szs
     * @date 2023-12-20
     */
    @PostMapping("/judgeTask")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "判断任务", notes = "判断任务")
    public R<List<JudgeTaskResult>> judgeTask(@Valid @RequestBody JudgeTaskDTO dto) {

        return R.data(miniCommonService.judgeTask(dto));
    }


    /**
     * 批量完成任务（审批）
     *
     * @author szs
     * @date 2023-12-05
     */
    @PostMapping("/batchCompleteTask")
    @ApiOperationSupport(order = 11)
    @ApiOperation(value = "批量完成任务", notes = "批量完成任务")
    public R<String> batchCompleteTask(@Valid @RequestBody BatchCompleteTaskDTO dto) {

        // 批量完成任务
        miniCommonService.batchCompleteTask(dto);

        return R.success("操作成功");
    }


    /**
     * 获取自己的批量流转任务列表
     *
     * @author szs
     * @date 2023-12-28
     */
    @PostMapping("/listMyBatchTasks")
    @ApiOperationSupport(order = 12)
    @ApiOperation(value = "获取自己的批量流转任务列表", notes = "获取自己的批量流转任务列表")
    public R<PageResult<List<BatchTaskResult>>> listMyBatchTasks(@Valid @RequestBody JSONObject json) {
        PageResult<List<BatchTaskResult>> list = new PageResult<>();

        int current = 1;
        int size = 10;
        String gridType = "sb";

        if (json != null) {
            if (json.has("pageConfig")) {
                JSONObject pageConfig = json.getJSONObject("pageConfig");
                if (pageConfig.has("currentPage")) {
                    current = pageConfig.getInt("currentPage");
                }

                if (pageConfig.has("pageSize")) {
                    size = pageConfig.getInt("pageSize");
                }
            }

            if (json.has("gridType")) {
                gridType = json.getString("gridType");
            }

        }


        // 获取自己的批量流转任务列表
        BatchTaskJob job = new BatchTaskJob();
        job.setBusinessType(gridType);
        PageResult<List<BatchTaskJob>> listPageResult = processService.listMyBatchTasks(current, size, job);
        BeanUtil.copyProperties(listPageResult, list);
        list.setData(new ArrayList<>());

        // 遍历获取任务名称
        for (BatchTaskJob taskJob : listPageResult.getData()) {
            BatchTaskResult taskResult = new BatchTaskResult();
            BeanUtil.copyProperties(taskJob, taskResult);

            // 获取批量流转任务详情
            Result<List<BatchTaskDetail>> taskDetails = processService.getBatchTaskDetails(taskResult.getBatchId());

            // 收集任务名称，并用逗号拼接
            taskResult.setTaskName(Func.join(taskDetails.getData().stream().map(BatchTaskDetail::getBusinessTitle).collect(Collectors.toList())));

            // 装载数据
            list.getData().add(taskResult);
        }

        return R.data(list);
    }


    /**
     * 获取批量流转任务详情
     *
     * @author szs
     * @date 2023-12-28
     */
    @PostMapping("/getBatchTaskDetails")
    @ApiOperationSupport(order = 14)
    @ApiOperation(value = "获取批量流转任务详情", notes = "获取批量流转任务详情")
    public R<Result<List<BatchTaskDetail>>> getBatchTaskDetails(@Valid @RequestBody JSONObject json) {
        String batchId = "";
        if (json != null && json.has("batchId")) {
            batchId = json.getString("batchId");
        }

        if (StringUtils.isBlank(batchId)) {
            throw new ServiceException("batchId为空");
        }

        return R.data(processService.getBatchTaskDetails(batchId));
    }


    /**
     * 获取业务数据库列表
     *
     * @author szs
     * @date 2023-12-29
     */
    @GetMapping("/getBizTableList")
    @ApiOperationSupport(order = 15)
    @ApiOperation(value = "获取业务数据库列表", notes = "获取业务数据库列表")
    public R<List<MiniBizTableResult>> getBizTableList(@ApiParam(value = "业务类型", required = true) @RequestParam String gridType) {
        List<MiniBizTableResult> list = new ArrayList<>();

        // 数据库枚举数据
        TableMappingEnum[] values = TableMappingEnum.values();
        for (TableMappingEnum value : values) {
            if (value.getType().equals(gridType)) {
                list.add(new MiniBizTableResult(value.getTablename(), value.getCnname()));
            }
        }

        return R.data(list);
    }


    /**
     * 取用户的相关信息并赋到申报
     *
     * @author szs
     * @date 2024-01-18
     */
    @GetMapping("/getUserInfoForSb")
    @ApiOperationSupport(order = 16)
    @ApiOperation(value = "取用户的相关信息并赋到申报", notes = "取用户的相关信息并赋到申报")
    public R<JSONObject> getUserInfoForSb() {

        // 获取当前用户
        SysUser user = sysUserService.findById(AuthUtil.getUserId());
        if (user == null) {
            throw new ServiceException("当前系统用户不存在：" + AuthUtil.getUserId());
        }

        // 取用户的相关信息并赋到申报
        JSONObject userInfoForSb = pmsSaveAndInitNewService.getUserInfoForSb(user);

        // JSON的key下划线转驼峰
        userInfoForSb = Util.jsonKeyToCamelCase(userInfoForSb);

        // 空数据处理
        return R.data(JSONObject.fromObject(JSON.toJSONString(userInfoForSb).replaceAll("null", "\"\"")));
    }


    /**
     * 取用户的单位信息并赋到申报
     *
     * @author szs
     * @date 2024-01-18
     */
    @GetMapping("/getUserEnterForSb")
    @ApiOperationSupport(order = 17)
    @ApiOperation(value = "取用户的单位信息并赋到申报", notes = "取用户的单位信息并赋到申报")
    public R<JSONObject> getUserEnterForSb() {

        // 获取当前用户
        SysUser user = sysUserService.findById(AuthUtil.getUserId());
        if (user == null) {
            throw new ServiceException("当前系统用户不存在：" + AuthUtil.getUserId());
        }

        // 取用户的相关信息并赋到申报
        JSONObject userEnterForSb = pmsSaveAndInitNewService.getUserEnterForSb(user);

        // JSON的key下划线转驼峰
        userEnterForSb = Util.jsonKeyToCamelCase(userEnterForSb);

        // 空数据处理
        return R.data(JSONObject.fromObject(JSON.toJSONString(userEnterForSb).replaceAll("null", "\"\"")));
    }


    /**
     * 撤回流程任务
     *
     * @author szs
     * @date 2024-03-15
     */
    @PostMapping("/withdrawTask")
    @ApiOperationSupport(order = 18)
    @ApiOperation(value = "撤回流程任务", notes = "撤回流程任务")
    public R<String> withdrawTask(@Valid @RequestBody WithdrawTaskDTO dto) {

        // 撤回流程任务
        miniCommonService.withdrawTask(dto);

        return R.success("操作成功");
    }


    /**
     * 终止任务
     *
     * @author szs
     * @date 2024-03-26
     */
    @PostMapping("/terminateTask")
    @ApiOperationSupport(order = 19)
    @ApiOperation(value = "终止任务", notes = "终止任务")
    public R<String> terminateTask(@Valid @RequestBody ProcessTaskDTO dto) {

        // 强制终止任务
        miniCommonService.terminateTask(dto);

        return R.success("操作成功");
    }


    /**
     * 受理任务
     *
     * @author szs
     * @date 2024-03-26
     */
    @PostMapping("/admissibleTask")
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "受理任务", notes = "受理任务")
    public R<String> admissibleTask(@Valid @RequestBody ProcessTaskDTO dto) {

        // 强制受理任务
        miniCommonService.admissibleTask(dto);

        return R.success("操作成功");
    }


    /**
     * 重置任务
     *
     * @author szs
     * @date 2024-03-26
     */
    @PostMapping("/resetTask")
    @ApiOperationSupport(order = 21)
    @ApiOperation(value = "重置任务", notes = "重置任务")
    public R<String> resetTask(@Valid @RequestBody ProcessTaskDTO dto) {

        // 强制终止任务
        miniCommonService.resetTask(dto);

        return R.success("操作成功");
    }


}
