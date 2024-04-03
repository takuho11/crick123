package cn.topcheer.pms2.web.controller.limit;

import cn.topcheer.pms2.api.limit.entity.LimitCondition;
import cn.topcheer.pms2.biz.limit.service.LimitConditionService;
import cn.topcheer.pms2.biz.limit.service.LimitConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/LimitCondition")
@Api(value = "限制条件",tags = "限制条件-限制条件")
public class LimitConditionController {

    @Resource
    private LimitConditionService limitConditionService;
    @Resource
    private LimitConfigService limitConfigService;

    @ApiOperation(value = "查询限制条件",notes = "根据搜索条件不同查询限制条件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "system",value = "业务类型",required = true),
            @ApiImplicitParam(name = "type",value = "业务type",required = true),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",required = false)})
    @GetMapping("/selectByCon")
    public R selectByCon(@RequestParam("system") String system,
                         @RequestParam("type") String type,
                         @RequestParam(value = "keyWord",required = false) String keyWord){
        return R.data(limitConditionService.selectByCon(system,type,keyWord));
    }

    @ApiOperation(value = "新增或修改单个对象",notes = "新增或修改单个对象")
    @PostMapping("/saveOrUpdate")
    public R saveOrUpdate(@RequestBody @Valid LimitCondition limitCondition){
        return R.data(limitConditionService.saveOrUpdate(limitCondition));
    }

    @ApiOperation(value = "删除单个对象",notes = "根据id删除单个对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "限制条件id",required = true)})
    @DeleteMapping("/deleteById")
    public R deleteById(@RequestParam("id") String id){
        List limitConfigList=limitConfigService.selectByCon("all",id,null);
        if(null!=limitConfigList&&limitConfigList.size()!=0){
            return R.fail("当前条件已绑定相关批次，无法删除，请先删除绑定信息。");
        }
        limitConditionService.removeById(id);
        return R.success("删除成功。");
    }

}
