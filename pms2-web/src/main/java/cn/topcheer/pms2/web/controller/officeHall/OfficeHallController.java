package cn.topcheer.pms2.web.controller.officeHall;


import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.api.plan.entity.PmsPlanproject;

import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.officeHall.impl.OfficeHallService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/OfficeHall" })
public class OfficeHallController extends GenericController<PmsPlanproject> {


    @Autowired
    private OfficeHallService officeHallService;

    /*==============================================完美分割线==============================================*/

    /**
     * 【办事大厅】 -- 获取分类数据
     */
    @RequestMapping(value = "/getSort.do")
    public @ResponseBody ReturnToJs getSort(@RequestBody JSONObject json){
        return ReturnToJs.success(this.officeHallService.getSort(json));
    }

    /**
     * 【办事大厅】 -- 获取中间内容（大批次or小批次）
     */
    @PostMapping(value = "/getPlanprojetData.do")
    public @ResponseBody ReturnToJs getPlanprojetData(@RequestBody JSONObject json){
        return ReturnToJs.success(this.officeHallService.getPlanprojetData(json));
    }

    /**
     * 【办事大厅】 -- 根据大批次id获取对应小批次数据
     */
    @RequestMapping(value = "/getBatchData.do")
    public @ResponseBody ReturnToJs getBatchData(@RequestParam(value="bigbatchid") String bigbatchid){
        return ReturnToJs.success(this.officeHallService.getBatchData(bigbatchid));
    }

    /**
     * 【办事大厅】 -- 获取建议征集相关信息
     */
    @RequestMapping(value = "/getGuideCollectData.do")
    public @ResponseBody ReturnToJs getGuideCollectData(){
        return ReturnToJs.success(this.officeHallService.getGuideCollectData());
    }




    /**
     * 【办事大厅】 -- 通过批次id和system获取当前账号已有数据
     */
    @RequestMapping(value = "/getOwnData.do")
    public @ResponseBody ReturnToJs getOwnData(@RequestParam(value="batchid") String batchid,
                                               @RequestParam(value="system") String system){
        return ReturnToJs.success(this.officeHallService.getOwnData(batchid,system));
    }

    /**
     * 【首页】--全局搜索
     */
    @RequestMapping(value = "/globalSearch.do")
    public @ResponseBody ReturnToJs globalSearch(@RequestBody JSONObject json){
        return ReturnToJs.success(this.officeHallService.globalSearch(json));
    }



    /**
     * 获取同时在申报期内的小批次
     */
    @RequestMapping(value = "/getSbqBatch.do")
    public @ResponseBody ReturnToJs getSbqBatch(@RequestParam(value="bigbatchid") String bigbatchid){
        return ReturnToJs.success(this.officeHallService.getSbqBatch(bigbatchid));
    }


    /**
     * 获取大批次列表数据
     */
    @PostMapping(value = "/getPlanProject")
    public @ResponseBody ReturnToJs getPlanProject(@RequestBody JSONObject json){
        return officeHallService.getPlanProject(json);
    }

    /**
     * 点击申报时判断用户当前状态
     * @return
     */
    @PostMapping("/judgeUser")
    @ResponseBody
    private Result judgeUser(){
        System.out.println("1");
        return officeHallService.judgeUser();
    }

    /**
     * 【办事大厅】 -- 获取中间内容（大批次or小批次）
     */
    @PostMapping(value = "/getPlanprojetOrBatch.do")
    public @ResponseBody ReturnToJs getPlanprojetOrBatch(@RequestBody JSONObject json){
        return ReturnToJs.success(this.officeHallService.getPlanprojetOrBatch(json));
    }

    /**
     * 获取下属区域及区域组织
     */
    @PostMapping(value = "/getRegionAndEnterprise")
    public @ResponseBody ReturnToJs getRegionAndEnterprise(@RequestBody JSONObject json){
        return officeHallService.getRegionAndEnterprise(json);
    }


}
