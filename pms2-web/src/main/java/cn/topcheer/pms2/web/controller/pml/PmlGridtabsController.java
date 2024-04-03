/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2019-4-19 11:02:54
 */
package cn.topcheer.pms2.web.controller.pml;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysUserService;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.pml.service.impl.PmlCommonGridService;
import cn.topcheer.pms2.biz.pml.service.impl.PmlGridService;
import cn.topcheer.pms2.biz.pml.service.impl.PmlGridtabsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "网格标签控制器", tags = "列表-网格标签控制器")
@RequestMapping("/pml/PmlGridtabs")
public class PmlGridtabsController {


    @Autowired
    private PmlGridService pmsGridService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private PmlGridtabsService pmlGridtabsService;

    @Autowired
    private PmlCommonGridService pmlCommonGridService;


//	/**
//	 *  根据传入的id返回相应的PmlGridtabs 对象（json格式的字符串）
//	 * @param id
//	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
//	 * @return PmlGridtabs 对象（json格式，字符串形式）
//	 */
//	@RequestMapping({ "/findById.do" })
//	public @ResponseBody
//	R<PmlGridtabs> findById(@RequestParam(value="id") String id, @RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
//							HttpServletRequest request, HttpServletResponse response){
//		return R.data(pmlGridtabsService.findById(id));
////		return super._findById(id, fetchParent, request, response);
//	}

//	/**
//	 *  根据传入的id返回相应的 PmlGridtabs 对象（json格式的字符串）
//	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
//	 * @return PmlGridtabs 对象（json格式，字符串形式）
//	 */
//	@RequestMapping({ "/findByExample.do" })
//	public @ResponseBody
//    String findByExample(@RequestParam(value="example",required=false) String example, @RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
//                         HttpServletRequest request, HttpServletResponse response){
//		return super._findByExample(example, fetchParent, request, response);
//	}
//
//	/**
//	 *  保存传入的 PmlGridtabs 对象（json格式的字符串）
//	 * @param pmsGridtabs 传入的pmsGridtabs对象
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value={ "/save.do"})
//	public   @ResponseBody
//    String savePmlGridtabs(@RequestBody String pmsGridtabs,
//                           HttpServletRequest request, HttpServletResponse response){
//		return super._save(pmsGridtabs, request, response);
//	}
//
//	/**
//	 *  根据传入的id删除相应的 PmlGridtabs 对象（json格式的字符串）
//	 * @param id 要删除的 PmlGridtabs 对象 id值
//	 * @return 操作结果 一般是 {"success":"true"}
//	 */
//	@RequestMapping({ "/deleteById.do" })
//	public @ResponseBody
//    String deleteById(@RequestParam(value="id") String id,
//                      HttpServletRequest request, HttpServletResponse response){
//		return super._deleteById(id, request, response);
//	}
//
//	/**
//	 *  根据传入的ids删除相应的 PmlGridtabs 对象（json格式的字符串）
//	 * @param ids 要删除的 PmlGridtabs 对象 id值,多个id之间用逗号(,)分隔
//	 * @return 操作结果 一般是 {"success":"true"}
//	 */
//	@RequestMapping({ "/deleteByIds.do" })
//	public @ResponseBody
//    String deleteByIds(@RequestParam(value="ids") String ids,
//                       HttpServletRequest request, HttpServletResponse response){
//		return super._deleteByIds(ids, request, response);
//	}


    /**
     * 【获取所有列表Tabs数据】
     * @param example
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getData.do")
    public void getData(@RequestBody String example,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            JSONObject json = Util.checkFromJson(example);
            PageResult<List<Map>> page = this.pmlGridtabsService.getData(json);
            returnToJs.setData(page);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/PmlGridtabs/getData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


    /**
     * 【获取列表配置数据】---为了列表tabs配置选择
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getGridConfigData.do")
    public void getGridConfigData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            List<Map> list = this.pmsGridService.getAllDataForGridTabs();
            returnToJs.setData(list);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/PmsGridtabs/getGridConfigData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


    /**
     * 【新增和修改方法】
     * @param example
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/addAndEdit.do")
    public void addAndEdit(@RequestBody String example,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            JSONObject json = Util.checkFromJson(example);
            this.pmlGridtabsService.addAndEdit(request, json);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/PmlGridtabs/addAndEdit.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


    /**
     * 【通过id查找列表tabs的数据】
     * @param example
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getGridTabsDataById.do")
    public void getGridTabsDataById(@RequestBody String example,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            JSONObject json = Util.checkFromJson(example);
            Map resMap = this.pmlGridtabsService.getGridTabsDataById(json);
            returnToJs.setData(resMap);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/PmlGridtabs/getGridTabsDataById.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }

    /**
     * 【删除方法】--通过id删除
     * @param example
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/deleteDataById.do")
    public void deleteDataById(@RequestBody String example,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            JSONObject json = Util.checkFromJson(example);
            if (json.has("id") && !Util.isEoN(json.get("id"))) {
                returnToJs.setSuccess(this.pmlGridtabsService.deleteDataById(request, json.get("id") + ""));
            } else {
                returnToJs.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/PmlGridtabs/deleteDataById.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


    /**
     * 【通用列表】--获取列表Tabs配置数据
     * @param example
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getGridTabsConfigData.do")
    public void getGridTabsConfigData(@RequestBody String example,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            BladeUser bladeUser = AuthUtil.getUser();// 获取用户信息
            if (bladeUser == null) {
                throw new ServiceException("用户未登录！");
            }
            SysUser user = userService.getById(bladeUser.getUserId());
            JSONObject json = Util.checkFromJson(example);
            if (json.has("gridTabs")) {
                List<Map> gridTabsConfigData = this.pmlGridtabsService.getGridTabsConfigData(user, json);
                for (Map map : gridTabsConfigData) {
                    String gridtype = (String) map.get("gridtype");
                    Map configData = this.pmlCommonGridService.getConfigData(gridtype);
                    map.put("pageConfig", configData);
                }
                returnToJs.setData(gridTabsConfigData);
                returnToJs.setSuccess(true);
            } else {
                returnToJs.setSuccess(false);
            }
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            e.printStackTrace();
            System.out.println("/PmlCommonGrid/getGridTabsConfigData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


}
