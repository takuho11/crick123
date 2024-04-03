package cn.topcheer.pms2.web.controller.monitorSystem;


import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.plan.entity.BatchVersion;
import cn.topcheer.pms2.biz.monitorSystem.SuperviseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Supervise")
@Api(value = "监测系统",tags = "监测系统-管理")
public class SuperviseController extends GenericController<BatchVersion> {

    @Resource
    private SuperviseService superviseService;

    /*============================（↑ 注入 丨 ↓ 接口）============================*/


    @ApiOperation(value = "查询-获取流程节点和数量",notes = "根据小批次id和业务类型获取流程节点和数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "batchid",value = "小批次id",required = true),
            @ApiImplicitParam(name = "type",value = "业务类型",required = true)})
    @GetMapping("/getStateNum")
    public R getStateNum(@RequestParam("batchid") String batchId,
                         @RequestParam("type") String type){
        return R.data(superviseService.getStateNum(batchId,type));
    }


    @ApiOperation(value = "查询-获取列表数据",notes = "获取列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "batchid",value = "小批次id",required = true),
            @ApiImplicitParam(name = "type",value = "业务类型",required = true)})
    @PostMapping("/getGridData")
    public R getGridData(@RequestParam("batchid") String batchId,
                         @RequestParam("type") String type,
                         @RequestBody JSONObject json){
        return R.data(superviseService.getGridData(json,batchId,type));
    }



}
