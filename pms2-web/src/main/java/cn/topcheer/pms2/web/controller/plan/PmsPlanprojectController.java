package cn.topcheer.pms2.web.controller.plan;

import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/PmsPlanproject")
@Api(value = "大批次维护",tags = "批次-大批次维护")
public class PmsPlanprojectController {

    @Resource
    private PmsPlanprojectService pmsPlanprojectService;

    @ApiOperation(value = "查询批次信息",notes = "根据业务类型、年度或者大批次id查询批次信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "system",value = "业务类型",required = false),
            @ApiImplicitParam(name = "annually",value = "年度",required = false),
            @ApiImplicitParam(name = "bigBatchId",value = "大批次id",required = false)})
    @GetMapping("/selectByCon")
    public R selectByCon(@RequestParam(value = "system",required = false) String system,
                         @RequestParam(value = "annually",required = false) String annually,
                         @RequestParam(value = "bigBatchId",required = false) String bigBatchId){
        return R.data(pmsPlanprojectService.selectByCon(system,annually,bigBatchId));
    }

}
