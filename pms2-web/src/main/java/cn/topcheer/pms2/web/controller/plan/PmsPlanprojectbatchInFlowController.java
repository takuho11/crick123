package cn.topcheer.pms2.web.controller.plan;


import cn.topcheer.pms2.api.plan.dto.PmsPlanprojectbatchDTO;
import cn.topcheer.pms2.api.plan.dto.SavePmsPlanprojectbatchInFlowDTO;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInFlow;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchInFlowService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 控制器
 *
 * @author szs
 * @date 2023-11-21
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping("/plan/pmsPlanprojectbatchInFlow")
@Api(value = "小批次绑定流程", tags = "批次-小批次绑定流程")
public class PmsPlanprojectbatchInFlowController extends BladeController {


    private final PmsPlanprojectbatchInFlowService pmsPlanprojectbatchInFlowService;


    /**
     * 全部列表
     *
     * @author szs
     * @date 2023-11-21
     */
    @GetMapping("/all")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "全部列表", notes = "全部列表")
    public R<List<PmsPlanprojectbatchInFlow>> all(@Valid PmsPlanprojectbatchDTO dto) {

        return R.data(pmsPlanprojectbatchInFlowService.getAllList(dto.getPmsPlanprojectbatchId(), dto.getPlanprojectType()));
    }


    /**
     * 保存
     *
     * @author szs
     * @date 2023-11-21
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "保存", notes = "保存")
    public R<String> submit(@Valid @RequestBody SavePmsPlanprojectbatchInFlowDTO dto) {

        // 保存
        pmsPlanprojectbatchInFlowService.submit(dto);

        return R.success("保存成功");
    }


}
