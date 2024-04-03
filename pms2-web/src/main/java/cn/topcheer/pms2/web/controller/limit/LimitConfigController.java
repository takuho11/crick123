package cn.topcheer.pms2.web.controller.limit;

import cn.topcheer.pms2.api.limit.entity.LimitConfig;
import cn.topcheer.pms2.biz.limit.service.LimitConfigService;
import io.swagger.annotations.*;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/LimitConfig")
@Api(value = "限制配置",tags = "限制条件-限制配置")
public class LimitConfigController {

    @Resource
    private LimitConfigService limitConfigService;

    @ApiOperation(value = "查询限制配置",notes = "根据搜索条件不同查询限制配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "batchId",value = "小批次id",required = true),
            @ApiImplicitParam(name = "conditionId",value = "限制条件id",required = false),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",required = false)})
    @PostMapping("/selectByCon")
    public R selectByCon(@RequestParam("batchId") String batchId,
                         @RequestParam(value = "conditionId",required = false) String conditionId,
                         @RequestParam(value = "keyWord",required = false) String keyWord){
        return R.data(limitConfigService.selectByCon(batchId,conditionId,keyWord));
    }

    @ApiOperation(value = "新增或修改单个对象",notes = "新增或修改单个对象")
    @PostMapping("/saveOrUpdate")
    public R saveOrUpdate(@RequestBody @Valid LimitConfig limitConfig){
        return R.data(limitConfigService.saveOrUpdate(limitConfig));
    }

    @ApiOperation(value = "删除单个对象",notes = "根据id删除单个对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "限制条件配置id",required = true)})
    @DeleteMapping("/deleteById")
    public R deleteById(@RequestParam("id") String id){
        limitConfigService.removeById(id);
        return R.success("删除成功。");
    }

    @ApiOperation(value = "复制限制配置",notes = "把目标小批次id下的所有限制复制到源小批次id下")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceBatchId",value = "源小批次id",required = true),
            @ApiImplicitParam(name = "targetBatchId",value = "目标小批次id",required = true)})
    @GetMapping("/copyBatchConfig")
    public R copyBatchConfig(@RequestParam("sourceBatchId") String sourceBatchId,
                             @RequestParam("targetBatchId") String targetBatchId){
        limitConfigService.copyBatchConfig(sourceBatchId,targetBatchId);
        return R.success("复制成功。");
    }

}
