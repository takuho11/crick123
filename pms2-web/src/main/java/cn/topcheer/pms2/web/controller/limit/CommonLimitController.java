package cn.topcheer.pms2.web.controller.limit;

import cn.topcheer.pms2.biz.limit.service.CommonLimitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/CommonLimit")
@Api(value = "通用限制入口",tags = "限制条件-通用限制入口")
public class CommonLimitController {

    @Resource
    private CommonLimitService commonLimitService;

    @ApiOperation(value = "所有业务后台限制通用接口",notes = "所有业务后台限制通用接口")
    @PostMapping("/judgeCondition")
    public R judgeCondition(@RequestBody ModelMap modelMap){
        try {
            commonLimitService.judgeCondition(modelMap);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.success("限制条件判断通过。");
    }

    @ApiOperation(value = "所有业务后台时间限制通用接口",notes = "所有业务后台时间限制通用接口")
    @PostMapping("/judgeTime")
    public R judgeTime(@RequestBody ModelMap modelMap){
        try {
            commonLimitService.judgeTime(modelMap);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.success("限制时间判断通过。");
    }

}
