package cn.topcheer.halberd.app.controller.framework;


import cn.topcheer.halberd.app.api.framework.service.api.ApiAuthService;
import org.springblade.plugin.workflow.design.entity.WfModel;
import org.springblade.plugin.workflow.design.service.IWfModelService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@Api(value = "审批流程", tags = "流程-审批流程")
@RequestMapping("/process")
@Validated
public class ProcessController {


    private final ApiAuthService apiAuthService;

    private final IWfModelService modelService;


    /**
     * 发起审批
     *
     * @author szs
     * @date 2023-07-17
     */
    @PostMapping("/startProcess")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "发起审批", notes = "发起审批")
    public R<String> startProcess(@ApiIgnore @RequestBody JSONObject body) {

        // 发起审批
        apiAuthService.startProcess(body);

        return R.success("发起成功");
    }


    /**
     * 获取全部设计模型列表
     *
     * @author szs
     * @date 2023-08-16
     */
    @GetMapping("/getDesignModels")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "获取全部模型列表", notes = "获取全部模型列表")
    public R<List<WfModel>> getDesignModels() {
        QueryWrapper<WfModel> qw = new QueryWrapper<>();
        qw.orderByDesc("last_updated");

        return R.data(modelService.list(qw));
    }


}
