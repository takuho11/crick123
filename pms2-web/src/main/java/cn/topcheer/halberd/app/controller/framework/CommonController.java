package cn.topcheer.halberd.app.controller.framework;

import cn.topcheer.halberd.app.biz.framework.common.data.CommonDataService;
import cn.topcheer.halberd.biz.modules.system.service.IDataListConfigService;
import cn.topcheer.halberd.biz.modules.system.vo.ResDataListVO;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.common.utils.EnumBaseUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/common")
@Api(value = "公共接口", tags = "系统-公共接口")
public class CommonController {
    @Autowired
    private CommonDataService commonService;

    @GetMapping("/enumParams")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取枚举参数列表", notes = "传入枚举类名和包名")
    public R enumParams(String packageName,String enumName) {
        if(packageName == null) {
            packageName = "cn.topcheer.halberd.app.api.framework.enumerate";
        }
        return EnumBaseUtils.getEnumParams(packageName,enumName);
    }





    /**
     * 获取下拉列表
     */
    @PostMapping("/getDropdownList")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取下拉列表", notes = "传入dict")
    public Result<List<ResDataListVO>> getDropdownList(@RequestParam String code, @RequestBody JSONObject params) {
        return commonService.getDropdownList(code,params);
    }

    /**
     * 批量获取下拉列表
     *参数格式: List  {code,params}
     */
    @PostMapping("/getBatchDropdownList")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "批量获取下拉列表", notes = "传入dict")
    public Result getBatchDropdownList( @RequestBody JSONArray paramArr) {
        List<Result<List<ResDataListVO>>>  arr=new ArrayList<>();
        for(int i=0;i<paramArr.size();i++)
        {
            JSONObject param=paramArr.getJSONObject(i);
            Result<List<ResDataListVO>> result = commonService.getDropdownList(param.getString("code"),param.getJSONObject("params"));
            result.setTag(param.getString("code"));
            arr.add(result);
        }
        return Result.data(arr,"成功");

    }


    /**
     * 通用下拉获取(开放)
     */
    @PostMapping("/getDropdownListOpen")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取下拉列表", notes = "传入dict")
    public Result<List<ResDataListVO>> getDropdownListOpen(@RequestParam String code, @RequestBody JSONObject params) {
        return commonService.getDropdownListOpen(code, params);
    }
}
