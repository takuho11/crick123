package cn.topcheer.pms2.web.controller.plan;

import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.plan.service.impl.BatchConfigService;
import cn.topcheer.pms2.biz.plan.service.impl.BatchGuideService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectService;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.biz.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import net.sf.json.JSONObject;
import org.springblade.core.tenant.annotation.NonDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器
 *
 * @author whq
 * @date 2023-11-12
 */
@NonDS
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/plan/BatchConfigController")
@Api(value = "大批次业务配置", tags = "批次-大批次业务配置")
public class BatchConfigController {
    @Autowired
    private BatchConfigService batchConfigService;
    @Autowired
    private BatchGuideService batchGuideService;
    @Autowired
    private PmsPlanprojectService pmsPlanprojectService;

    /**
     * 【大批次业务配置】--新增or修改大批次
     */
    @PostMapping(value = "/addAndEditBigbatch.do",produces="text/plain;charset=utf-8")
    public @ResponseBody
    String addAndEditBigbatch(@RequestBody String example,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        try{
            JSONObject json = Util.checkFromJson(example);
            this.batchConfigService.addAndEditBigbatch(json);
            returnToJs = new ReturnToJs(null,true);
        }catch (Exception e){
            returnToJs = new ReturnToJs(null,false,"后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/BatchConfig/addAndEditBigbatch.do 方法报错。");
        }
        return JSONObject.fromObject(returnToJs).toString();
    }

    /**
     * 【小批次业务配置】--新增or修改小批次
     */
    @PostMapping(value = "/addAndEditBatch.do",produces="text/plain;charset=utf-8")
    public @ResponseBody String addAndEditBatch(@RequestBody String example,
                                                @RequestParam(value="bigbatchid") String bigbatchid,
                                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        try{
            JSONObject json = Util.checkFromJson(example);
            JSONObject resJson = this.batchConfigService.addAndEditBatch(json,bigbatchid);
            returnToJs = new ReturnToJs(null,resJson.getBoolean("success"),resJson.get("reason")+"");
        }catch (Exception e){
            returnToJs = new ReturnToJs(null,false,"后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/BatchConfig/addAndEditBatch.do 方法报错。");
            log.error("/BatchConfig/addAndEditBatch.do 方法报错,错误信息："+e);
        }
        return JSONObject.fromObject(returnToJs).toString();
    }

    /**
     * 【小批次业务配置】--通过大批次id获取小批次数据
     */
    @PostMapping(value = "/getBatchByBigId.do",produces="text/plain;charset=utf-8")
    public @ResponseBody String getBatchByBigId(@RequestParam(value="bigbatchid") String bigbatchid,@RequestParam(value="flow",required = false,defaultValue = "") String flow,
                                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        try{
            JSONObject resJson = new JSONObject();
            //todo
            resJson.put("batchList",this.batchConfigService.getBatchByBigbatchid(bigbatchid,flow));
            resJson.put("bigBatchName",this.pmsPlanprojectService.getBigbatchName(bigbatchid));
            returnToJs = new ReturnToJs(resJson,true);
        }catch (Exception e){
            returnToJs = new ReturnToJs(null,false,"后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/BatchConfig/getBatchByBigId.do 方法报错。");
            log.error("/BatchConfig/getBatchByBigId.do 方法报错,错误信息："+e);

        }
        return JSONObject.fromObject(returnToJs).toString();
    }

    /**
     * 【大批次业务配置】--根据业务类型获取大批次数据（基于获取模式的情况下）
     */
    @ApiModelProperty(value = "根据业务类型获取大批次数据",notes = "根据业务类型获取大批次数据")
    @PostMapping(value = "/getBigbatchBySystem.do",produces="text/plain;charset=utf-8")
    public @ResponseBody String getBigbatchBySystem(@RequestParam(value="system") String system,
                                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        try{
            returnToJs = new ReturnToJs(this.batchConfigService.getBigbatchBySystem(system),true);
        }catch (Exception e){
            returnToJs = new ReturnToJs(null,false,"后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/BatchConfig/getBigbatchBySystem.do 方法报错。");
            log.error("/BatchConfig/getBigbatchBySystem.do 方法报错,错误信息："+e);
        }
        return JSONObject.fromObject(returnToJs).toString();
    }

    /**
     * 【大批次业务配置】--删除大批次
     */
    @PostMapping(value = "/deleteBigbatch.do",produces="text/plain;charset=utf-8")
    public @ResponseBody String deleteBigbatch(@RequestParam(value="id") String id,
                                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        try{
            JSONObject resJson = this.batchConfigService.deleteBigbatch(id);
            returnToJs = new ReturnToJs(null,resJson.getBoolean("success"),resJson.get("reason")+"");
        }catch (Exception e){
            returnToJs = new ReturnToJs(null,false,"后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/BatchConfig/deleteBigbatch.do 方法报错。");
            log.error("/BatchConfig/deleteBigbatch.do 方法报错,错误信息："+e);
        }
        return JSONObject.fromObject(returnToJs).toString();
    }

    /**
     * 【小批次业务配置】--删除小批次
     */
    @PostMapping(value = "/deleteBatch.do",produces="text/plain;charset=utf-8")
    public @ResponseBody String deleteBatch(@RequestParam(value="batchid") String batchid,
                                            @RequestParam(value="system") String system,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        try{
            JSONObject resJson = this.batchConfigService.deleteBatch(batchid,system);
            returnToJs = new ReturnToJs(null,resJson.getBoolean("success"),resJson.get("reason")+"");
        }catch (Exception e){
            returnToJs = new ReturnToJs(null,false,"后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/BatchConfig/deleteBatch.do 方法报错。");
            log.error("/BatchConfig/deleteBatch.do 方法报错,错误信息："+e);
        }
        return JSONObject.fromObject(returnToJs).toString();
    }

    /**
     * 【小批次业务配置】--获取小批次基本信息
     */
    @PostMapping(value = "/getBatchBasicInfo.do",produces="text/plain;charset=utf-8")
    public @ResponseBody String getBatchBasicInfo(@RequestParam(value="batchid") String batchid,
                                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        try{
            returnToJs = new ReturnToJs(this.batchConfigService.getBatchBasicInfo(batchid),true);
        }catch (Exception e){
            returnToJs = new ReturnToJs(null,false,"后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/BatchConfig/getBatchBasicInfo.do 方法报错。");
            log.error("/BatchConfig/getBatchBasicInfo.do 方法报错,错误信息："+e);
        }
        return JSONObject.fromObject(returnToJs).toString();
    }

    /**
     * 【小批次业务配置】--获取小批次指南内容
     */
    @RequestMapping(value = "/getBatchGuide.do",produces="text/plain;charset=utf-8")
    public @ResponseBody String getBatchGuide(@RequestParam(value="batchid") String batchid,
                                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        try{
            returnToJs = new ReturnToJs(this.batchGuideService.initData(batchid),true);
        }catch (Exception e){
            returnToJs = new ReturnToJs(null,false,"后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/BatchConfig/getBatchGuide.do 方法报错。");
            log.error("/BatchConfig/getBatchGuide.do 方法报错,错误信息："+e);
        }
        return JSONObject.fromObject(returnToJs).toString();
    }

    /**
     * 【小批次业务配置】--保存小批次指南内容
     */
    @RequestMapping(value = "/saveBatchGuide.do",produces="text/plain;charset=utf-8")
    public @ResponseBody String saveBatchGuide(@RequestParam(value="batchid") String batchid,
                                               @RequestBody String example,
                                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        ReturnToJs returnToJs = null;
        try{
            JSONObject json = Util.checkFromJson(example);
            JSONObject resJson = this.batchGuideService.saveData(batchid,json);
            returnToJs = new ReturnToJs(null,resJson.getBoolean("success"),resJson.get("reason")+"");
        }catch (Exception e){
            returnToJs = new ReturnToJs(null,false,"后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/BatchConfig/saveBatchGuide.do 方法报错。");
            log.error("/BatchConfig/saveBatchGuide.do 方法报错,错误信息："+e);
        }
        return JSONObject.fromObject(returnToJs).toString();
    }
}
