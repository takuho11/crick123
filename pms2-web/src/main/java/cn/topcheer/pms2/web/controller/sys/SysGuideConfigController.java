package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.pms2.api.sys.SysGuideConfig;
import cn.topcheer.pms2.biz.sys.SysGuideConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/SysGuideConfig")
@Api(value = "批次",tags = "批次-批次方向配置维护")
public class SysGuideConfigController {

    @Resource
    private SysGuideConfigService sysGuideConfigService;

    @ApiOperation(value = "根据batchid获取全部数据",
            notes = "根据batchid获取全部数据；对应省级系统原方法：/SysGuideConfig/initGuide.do")
    @ApiImplicitParam(name = "batchid",value = "batchid",required = true)
    @GetMapping("/initGuide")
    public R initGuide(@RequestParam("batchid") String batchId){
        return R.data(sysGuideConfigService.initGuide(batchId));
    }

    @ApiOperation(value = "保存数据",
            notes = "保存数据；对应省级系统原方法：/SysGuideConfig/saveGuide.do")
    @PostMapping("/saveGuide")
    public R saveGuide(@RequestBody @Valid SysGuideConfig sysGuideConfig){
        sysGuideConfigService.saveGuide(sysGuideConfig);
        return R.success("保存成功。");
    }

    @ApiOperation(value = "判断方法",
            notes = "判断方法；对应省级系统原方法：/SysGuideConfig/judgeGuide.do")
    @PostMapping("/judgeGuide")
    public R judgeGuide(@RequestBody ModelMap modelMap){
        return R.data(sysGuideConfigService.judgeGuide(modelMap));
    }

}
