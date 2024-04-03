package cn.topcheer.pms2.web.controller.plan;

import cn.topcheer.halberd.biz.modules.system.vo.ResDataListVO;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.api.plan.vo.DropdownListVO;
import cn.topcheer.pms2.biz.plan.service.impl.DropdownListService;
import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/DropdownListController")
@Api(value = "大小批次下拉控制",tags = "大小批次下拉控制")
public class DropdownListController {

    @Autowired
    private DropdownListService dropdownListService;

    /**
     * 获取下拉列表
     */
    @PostMapping("/getDropdownList")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取下拉列表", notes = "传入dict")
    public R getDropdownList(@RequestBody DropdownListVO params) {
        return dropdownListService.getDropdownList(params);
    }

    /**
     * 获取已完善信息的单位信息
     * @param serachStr
     * @param type
     * @return
     */
    @PostMapping("/getCompeleteUnit")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取已完善信息的单位信息", notes = "传入dict")
    public R getCompeleteUnit(@RequestParam(value = "serachStr") String serachStr,@RequestParam(value = "type") String type){
        return dropdownListService.getCompeleteUnit(serachStr,type);
    }
}
