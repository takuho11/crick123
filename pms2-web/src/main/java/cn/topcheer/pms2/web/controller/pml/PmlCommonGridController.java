package cn.topcheer.pms2.web.controller.pml;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.pml.entity.PmlGrid;
import cn.topcheer.pms2.biz.pml.service.impl.PmlCommonGridService;
import cn.topcheer.pms2.biz.pml.service.impl.PmlGridService;
import cn.topcheer.pms2.common.utils.PmlHttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by ZK on 2019/3/12.
 * 通用的列表相关后台方法集合
 */
@Controller
@Api(value = "公共网格控制器", tags = "列表-公共网格控制器")
@RequestMapping("/pml/PmlCommonGrid")
public class PmlCommonGridController {

    @Autowired
    private PmlCommonGridService pmsCommonGridService;
    @Autowired
    private PmlGridService pmsGridService;


    /**
     * 【通用列表】--通用获取数据源
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getData.do")
    public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject resObject = new JSONObject();
        try {
            JSONObject json = PmlHttpUtil.getBodyJSON(request, "utf-8");
            if (json != null) {
                json.put("userid", AuthUtil.getUserId());
                if (!Util.isEoN(json.get("gridid"))) {
                    PmlGrid pmsGrid = this.pmsGridService.findById(json.get("gridid") + "");
                    //判断使用sql获取数据源 还是用 hql获取数据源
                    if ("sql".equals(pmsGrid.getSqlorhql())) {
                        PageResult<List<Map>> page = this.pmsCommonGridService.getDataBySql(json, pmsGrid);
                        resObject.put("data", page);
                    } else if ("hql".equals(pmsGrid.getSqlorhql())) {
                        PageResult<List<Map>> page = this.pmsCommonGridService.getDataByHql(json, pmsGrid);
                        resObject.put("data", page);
                    } else {
                        resObject.put("success", false);
                    }
                    resObject.put("success", true);
                } else {
                    resObject.put("success", false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        }
        writer.write(resObject.toString());
    }


//    /**
//     * 【通用列表】--通用获取数据源，根据角色，列表url等配置信息获取
//     * @param example
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/getDataByConfig.do")
//    public void getDataByConfig(@RequestBody String example,
//                                HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer=response.getWriter();
//        ReturnToJs returnToJs = new ReturnToJs();
//        try{
//            JSONObject json = Util.checkFromJson(example);
//            PageResult<List<Map>> page = this.pmsCommonGridService.getDataByConfig(json);
//            returnToJs.setData(page);
//            returnToJs.setSuccess(true);
//        }catch (Exception e){
//            returnToJs.setSuccess(false);
//            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
//            System.out.println("/PmlCommonGrid/getDataByConfig.do 方法报错。");
//        }
//        writer.write(JSONObject.fromObject(returnToJs).toString());
//    }


    /**
     * 【通用列表】--获取列表配置数据
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getConfigData.do")
    public void getConfigData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject resObject = new JSONObject();
        try {
            JSONObject json = PmlHttpUtil.getBodyJSON(request, "utf-8");
            if (!Util.isEoN(json.get("type"))) {
                Map resMap = this.pmsCommonGridService.getConfigData(json.get("type") + "");
                if (!Util.isEoN(resMap) && resMap.size() > 0) {
                    resObject.put("data", resMap);
                    resObject.put("success", true);
                } else {
                    resObject.put("success", false);
                }
            } else {
                resObject.put("success", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        }
        writer.write(resObject.toString());
    }

//    /**
//     * 【黑龙江系统合同验收】 --- 待验收合同列表获取方法
//     * @param example
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/htys_dysht_getData.do")
//    public void htys_dysht_getData(@RequestBody String example,
//                                   HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer=response.getWriter();
//        ReturnToJs returnToJs = new ReturnToJs();
//        try{
//            JSONObject json = Util.checkFromJson(example);
//            returnToJs.setData(this.pmsCommonGridService.htys_dysht_getData(json));
//            returnToJs.setSuccess(true);
//        }catch (Exception e){
//            returnToJs.setSuccess(false);
//            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
//            System.out.println("/PmlCommonGrid/htys_dysht_getData.do 方法报错。");
//        }
//        writer.write(JSONObject.fromObject(returnToJs).toString());
//    }


//    /**
//     * 【黑龙江系统合同验收】 --- 待验收合同列表获取方法
//     * @param example
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/ht_random_getData.do")
//    public void ht_random_getData(@RequestBody String example,
//                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer=response.getWriter();
//        ReturnToJs returnToJs = new ReturnToJs();
//        try{
//            JSONObject json = Util.checkFromJson(example);
//            returnToJs.setData(this.pmsCommonGridService.ht_random_getData(json));
//            returnToJs.setSuccess(true);
//        }catch (Exception e){
//            returnToJs.setSuccess(false);
//            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
//            System.out.println("/PmlCommonGrid/ht_random_getData.do 方法报错。");
//        }
//        writer.write(JSONObject.fromObject(returnToJs).toString());
//    }


//    /**
//     * 【黑龙江系统季报】 --- 未上报
//     * @param example
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/jb_wsb_getData.do")
//    public void jb_wsb_getData(@RequestBody String example,
//                               HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer=response.getWriter();
//        ReturnToJs returnToJs = new ReturnToJs();
//        try{
//            SecurityUser suser = SessionUtil.getCurrentSecurityUser(request);
//            SysUser user_ = suser.getUser();
//            SysUser user = this.sysUserService.findById(user_.getId());
//            JSONObject json = Util.checkFromJson(example);
//            returnToJs.setData(this.pmsCommonGridService.jb_wsb_getData(json,user));
//            returnToJs.setSuccess(true);
//        }catch (Exception e){
//            returnToJs.setSuccess(false);
//            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
//            System.out.println("/PmlCommonGrid/kjjl_cx_dwtm.do 方法报错。");
//        }
//        writer.write(JSONObject.fromObject(returnToJs).toString());
//    }
//
//    /**
//     * 【黑龙江系统年度报告】 --- 未上报
//     * @param example
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/ndbg_wsb_getData.do")
//    public void ndbg_wsb_getData(@RequestBody String example,
//                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer=response.getWriter();
//        ReturnToJs returnToJs = new ReturnToJs();
//        try{
//            SecurityUser suser = SessionUtil.getCurrentSecurityUser(request);
//            SysUser user_ = suser.getUser();
//            SysUser user = this.sysUserService.findById(user_.getId());
//            JSONObject json = Util.checkFromJson(example);
//            returnToJs.setData(this.pmsCommonGridService.ndbg_wsb_getData(json,user));
//            returnToJs.setSuccess(true);
//        }catch (Exception e){
//            returnToJs.setSuccess(false);
//            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
//            System.out.println("/PmlCommonGrid/kjjl_cx_dwtm.do 方法报错。");
//        }
//        writer.write(JSONObject.fromObject(returnToJs).toString());
//    }
//
//    /**
//     * 【科研诚信】 --- 有用户、单位、专家
//     * @param example
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/kycx_all.do")
//    public void kycx_all(@RequestBody String example,
//                         HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer=response.getWriter();
//        ReturnToJs returnToJs = new ReturnToJs();
//        try{
//            JSONObject json = Util.checkFromJson(example);
//            returnToJs.setData(this.pmsCommonGridService.kycx_all(json));
//            returnToJs.setSuccess(true);
//        }catch (Exception e){
//            returnToJs.setSuccess(false);
//            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
//            System.out.println("/PmlCommonGrid/kycx_all.do 方法报错。");
//        }
//        writer.write(JSONObject.fromObject(returnToJs).toString());
//    }
//
//
//    /**
//     * 【预申报】 --- 获取当前用户所有预申报数据
//     * @param example
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/ysb_yhsy_getData.do")
//    public void ysb_yhsy_getData(@RequestBody String example,
//                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer=response.getWriter();
//        ReturnToJs returnToJs = new ReturnToJs();
//        try{
//            JSONObject json = Util.checkFromJson(example);
//            returnToJs.setData(this.pmsCommonGridService.ysb_yhsy_getData(json));
//            returnToJs.setSuccess(true);
//        }catch (Exception e){
//            returnToJs.setSuccess(false);
//            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
//            System.out.println("/PmlCommonGrid/ysb_yhsy_getData.do 方法报错。");
//        }
//        writer.write(JSONObject.fromObject(returnToJs).toString());
//    }
//
//    /**
//     * 【专家信息】 --- 获取获取专家列表，用户打标签
//     * @param jsonObject
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @PostMapping("/expertlist_tjbq_getData.do")
//    public @ResponseBody
//    ReturnToJs expertlist_tjbq_getData(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
//        return ReturnToJs.success(this.pmsCommonGridService.expertlist_tjbq_getData(jsonObject));
//    }
//
//    @RequestMapping("/expert_tj_static.do")
//    public @ResponseBody
//    ReturnToJs expert_tj_static(HttpServletRequest request, HttpServletResponse response) {
//        return ReturnToJs.success(this.pmsCommonGridService.expert_tj_static());
//    }
//
//    @PostMapping("/expert_tj_dynamic.do")
//    public @ResponseBody
//    ReturnToJs expert_tj_dynamic(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
//        return ReturnToJs.success(this.pmsCommonGridService.expert_tj_dynamic(jsonObject));
//    }
//
//    @PostMapping("/expert_rk.do")
//    public @ResponseBody
//    ReturnToJs expert_rk(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
//        return ReturnToJs.success(this.pmsCommonGridService.expert_rk(jsonObject));
//    }
//
//
//
//    /**
//     * 【经办人评审】 --- 获取当前经办人所有项目
//     * @param example
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/jbrReview.do")
//    public void jbrReview(@RequestBody String example,
//                          HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer=response.getWriter();
//        ReturnToJs returnToJs = new ReturnToJs();
//        try{
//            JSONObject json = Util.checkFromJson(example);
//            returnToJs.setData(this.pmsCommonGridService.jbrReview(json));
//            returnToJs.setSuccess(true);
//        }catch (Exception e){
//            returnToJs.setSuccess(false);
//            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
//            System.out.println("/PmlCommonGrid/jbrReview.do 方法报错。");
//        }
//        writer.write(JSONObject.fromObject(returnToJs).toString());
//    }


}
