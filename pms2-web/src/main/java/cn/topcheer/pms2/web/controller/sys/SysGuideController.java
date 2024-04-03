package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.pms2.api.sys.SysGuide;
import cn.topcheer.pms2.api.sys.vo.SysGuideVO;
import cn.topcheer.pms2.biz.sys.SysGuideService;
import cn.topcheer.pms2.biz.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springblade.core.tool.api.R;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SysGuide")
@Api(value = "批次",tags = "批次-批次方向维护")
public class SysGuideController {

    @Resource
    SysGuideService sysGuideService;


    /*============================完美分割线（↑：注入 丨 ↓：方法）============================*/


    /*******************************************************************
     *                            查询数据                              *
     *******************************************************************/

    @ApiOperation(value = "根据小批次id获取方向",
            notes = "根据小批次id获取方向；对应省级系统原方法：/SysGuide/initTopic.do?batchid=")
    @ApiImplicitParam(name = "batchid",value = "小批次id",required = true)
    @GetMapping("/initTopic")
    public R initTopic(@RequestParam("batchid") String batchId){
        return R.data(sysGuideService.initTopic(batchId));
    }



    @ApiOperation(value = "根据小批次id和父id获取下一级(子方向)",
            notes = "根据小批次id和父id获取下一级(子方向)；对应省级系统原方法：/SysGuide/initData.do?batchid=&parentid=")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "batchid",value = "小批次id",required = true),
            @ApiImplicitParam(name = "parentid",value = "父id",required = true)})
    @GetMapping("/initData")
    public R initData(@RequestParam("batchid") String batchId,
                      @RequestParam("parentid") String parentId){
        return R.data(sysGuideService.initData("childDirection",batchId,parentId));
    }


    @ApiOperation(value = "根据小批次id和父id获取下一级(项目)",
            notes = "根据小批次id和父id获取下一级(项目)；对应省级系统原方法：/SysGuide/initDataForChild.do?batchid=&parentid=")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "batchid",value = "小批次id",required = true),
            @ApiImplicitParam(name = "parentid",value = "父id",required = true)})
    @GetMapping("/initDataForChild")
    public R initDataForChild(@RequestParam("batchid") String batchId,
                      @RequestParam("parentid") String parentId){
        return R.data(sysGuideService.initData("project",batchId,parentId));
    }



    @ApiOperation(value = "根据小批次id获取全部配置信息",
            notes = "根据小批次id获取全部配置信息；对应省级系统原方法：/SysGuide/findAllConfigByBatchid.do?batchid=")
    @ApiImplicitParam(name = "batchid",value = "小批次id",required = true)
    @GetMapping("/findAllConfigByBatchid")
    public R findAllConfigByBatchid(@RequestParam("batchid") String batchId){
        List<SysGuideVO> list = sysGuideService.initTopic(batchId);
        for(SysGuideVO vo: list) {
            List<SysGuideVO> directionList = sysGuideService.initData("childDirection",batchId,vo.getDirectionid());
            for(SysGuideVO direction : directionList){
                List<SysGuideVO> childDirectionList = sysGuideService.initData("project",batchId,direction.getChilddirectionid());
                direction.setChilddirectionList(childDirectionList);
            }
            vo.setDirectionList(directionList);
        }
        return R.data(list);
    }


    @ApiOperation(value = "根据处室id获取全部处室下官员",
            notes = "根据处室id获取全部处室下官员；对应省级系统原方法：/SysGuide/findXmzgByBelongid.do?belongid=")
    @ApiImplicitParam(name = "belongid",value = "处室id",required = true)
    @GetMapping("/findXmzgByBelongid")
    public R findXmzgByBelongId(@RequestParam("belongid") String belongId){
        return R.data(sysGuideService.findXmzgByBelongId(belongId));
    }


    @ApiOperation(value = "根据关联id和关联业务类型获取配置信息",
            notes = "根据关联id和关联业务类型获取配置信息；对应省级系统原方法：/SysGuide/findSysGuideConfig.do?sourceid=&sourcetype=")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sourceid",value = "关联id",required = true),
            @ApiImplicitParam(name = "sourcetype",value = "关联业务类型",required = true)})
    @GetMapping("/findSysGuideConfig")
    public R findSysGuideConfig(@RequestParam("sourceid") String sourceId,@RequestParam("sourcetype") String sourceType){
        return R.data(sysGuideService.findSysGuideConfig(sourceId,sourceType));
    }


    @ApiOperation(value = "根据多个方向找子方向",
            notes = "根据多个方向找子方向；对应省级系统原方法：/SysGuide/findChildDirection.do")
    @PostMapping("/findChildDirection")
    public R findChildDirection(@RequestBody ModelMap map){
        return R.data(sysGuideService.findSysGuideByParentIds(map));
    }


    @ApiOperation(value = "根据多个方向找支持内容",
            notes = "根据多个方向找支持内容；对应省级系统原方法：/SysGuide/findzcnr.do")
    @PostMapping("/findzcnr")
    public R findzcnr(@RequestBody ModelMap map){
        return R.data(sysGuideService.findSysGuideByParentIds(map));
    }


    @ApiOperation(value = "根据不同参数获取支持方向",
            notes = "根据不同参数获取支持方向；对应省级系统原方法：/SysGuide/getZcfxByParentOrChildrenAndBatchid.do")
    @PostMapping("/getZcfxByParentOrChildrenAndBatchid")
    public R getZcfxByParentOrChildrenAndBatchid(@RequestBody ModelMap map){
        return R.data(sysGuideService.getZcfxByParentOrChildrenAndBatchid(map));
    }


    /*******************************************************************
     *                            新增、修改数据                         *
     *******************************************************************/

    @ApiOperation(value = "保存数据",
            notes = "保存数据；对应省级系统原方法：/SysGuide/saveData.do")
    @PostMapping("/saveData")
    public R saveData(@RequestBody SysGuide sysGuide){
        //新增或更新sysGuide
        if(Util.isEoN(sysGuide.getId())){ sysGuide.setId(Util.NewGuid()); }
        sysGuideService.saveOrUpdate(sysGuide);
        //更新小批次表的TopicType
        sysGuideService.updateBatchTopicType(sysGuide.getBatchid());
        return R.success("执行成功");
    }


    /*******************************************************************
     *                               删除数据                           *
     *******************************************************************/

    @ApiOperation(value = "删除数据",
            notes = "删除数据；对应省级系统原方法：/SysGuide/deleteGuide.do?id=&type=&batchid=")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "主id",required = true),
            @ApiImplicitParam(name = "type",value = "类型",required = true),
            @ApiImplicitParam(name = "batchid",value = "小批次id",required = true)})
    @PostMapping("/deleteGuide")
    public R deleteGuide(@RequestParam("id") String id,
                         @RequestParam("type") String type,
                         @RequestParam("batchid") String batchId){
        //删除相关数据
        sysGuideService.deleteGuide(id,type);
        //更新小批次表的TopicType
        sysGuideService.updateBatchTopicType(batchId);
        return R.success("执行成功");
    }


}
