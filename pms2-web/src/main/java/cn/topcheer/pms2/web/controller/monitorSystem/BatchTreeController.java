package cn.topcheer.pms2.web.controller.monitorSystem;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.plan.entity.BatchVersion;
import cn.topcheer.pms2.biz.monitorSystem.BatchTreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/BatchTree")
@Api(value = "批次树",tags = "批次树-获取大小批次")
public class BatchTreeController extends GenericController<BatchVersion> {

    @Resource
    BatchTreeService batchTreeService;

    /*============================（↑ 注入 丨 ↓ 接口）============================*/

    @ApiOperation(value = "查询-根据不同情况获取大批次树",notes = "根据不同情况获取大批次树（只能勾选一级）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "system",value = "批次类型",required = true),
            @ApiImplicitParam(name = "type",value = "业务类型",required = true),
            @ApiImplicitParam(name = "special",value = "特殊值",required = false)})
    @GetMapping("/getBigBatch")
    public R getBigBatch(@RequestParam("system") String system,
                         @RequestParam("type") String type,
                         @RequestParam(value = "special",required = false) String special){
        return R.data(batchTreeService.getBigBatch(system,type,special));
    }


    @ApiOperation(value = "查询-根据不同情况获取小批次树",notes = "根据不同情况获取小批次树（只能勾选二级）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "system",value = "批次类型",required = true),
            @ApiImplicitParam(name = "type",value = "业务类型",required = true),
            @ApiImplicitParam(name = "special",value = "特殊值",required = false)})
    @GetMapping("/getBatch")
    public R getBatch(@RequestParam("system") String system,
                         @RequestParam("type") String type,
                         @RequestParam(value = "special",required = false) String special){
        return R.data(batchTreeService.getBatch(system,type,special));
    }



}
