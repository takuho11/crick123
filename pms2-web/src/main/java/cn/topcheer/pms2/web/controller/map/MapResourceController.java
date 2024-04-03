package cn.topcheer.pms2.web.controller.map;


import cn.topcheer.pms2.api.map.MapResource;
import cn.topcheer.pms2.biz.map.MapResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 前端控制器
 * @author zk/byf
 * @since 2024-03-08
 */
@RestController
@RequestMapping("/MapResource")
@Api(value = "创新地图资源",tags = "创新地图资源")
public class MapResourceController {

    @Resource
    private MapResourceService mapResourceService;

    /*============================（↑ 注入 丨 ↓ 接口）============================*/

    @ApiOperation(value = "保存-手动保存最新创新地图资源数据",notes = "手动保存最新创新地图资源数据")
    @PostMapping("/saveClob")
    public R saveClob(){
        mapResourceService.saveClob();
        return R.status(true);
    }

}
