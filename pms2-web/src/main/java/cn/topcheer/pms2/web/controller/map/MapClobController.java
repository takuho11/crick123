package cn.topcheer.pms2.web.controller.map;


import cn.topcheer.pms2.biz.map.MapClobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 前端控制器
 * @author zk
 * @since 2024-03-09
 */
@RestController
@RequestMapping("/MapClob")
@Api(value = "创新地图资源-大字段",tags = "创新地图资源")
public class MapClobController {

    @Resource
    private MapClobService mapClobService;

    /*============================（↑ 注入 丨 ↓ 接口）============================*/

    @ApiOperation(value = "查询-根据类型获取数据", notes = "根据类型获取数据")
    @ApiImplicitParam(name = "type",value = "类型",required = true)
    @GetMapping("/getData")
    public R getData(@RequestParam("type") String type){
        return R.data(mapClobService.getData(type));
    }

}
