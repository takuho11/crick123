/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2019-3-3 15:54:33
 */
package cn.topcheer.pms2.web.controller.pml;


import cn.topcheer.halberd.app.api.utils.HttpUtil;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.pml.service.impl.PmlButtonService;
import cn.topcheer.pms2.biz.pml.service.impl.PmlColumnService;
import cn.topcheer.pms2.biz.pml.service.impl.PmlGridService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "网格控制器", tags = "列表-网格控制器")
@RequestMapping("/pml/PmlGrid")
public class PmlGridController {

    @Autowired
    private PmlGridService pmlGridService;

    @Autowired
    private PmlColumnService pmlColumnService;

    @Autowired
    private PmlButtonService pmlButtonService;

    @Autowired
    private DBHelper dbHelper;


    /**
     * 【列表配置信息的初始化和搜索方法】
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getGridData.do")
    public void getGridData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject resObject = new JSONObject();
        try {
            JSONObject json = HttpUtil.getBodyJSON(request, "utf-8");
            PageResult<List<Map>> page = this.pmlGridService.getGridData(json);
            resObject.put("data", page);
            resObject.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        }
        writer.write(resObject.toString());
    }


    /**
     * 【获取所有列名和所有按钮】
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getCbData.do")
    public void getCbData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject resObject = new JSONObject();
        try {
            resObject.put("columnData", this.pmlColumnService.getAllData());//所有列名数据
            resObject.put("gridBtnData", this.pmlButtonService.getAllDataByType("gridbtn"));//所有列表内按钮
            resObject.put("otherBtnData", this.pmlButtonService.getAllDataByType("otherbtn"));//所有列表外按钮
            resObject.put("roleData", this.pmlButtonService.getRoleDataGroup());//所有角色数据
            resObject.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        }
        writer.write(resObject.toString());
    }


    /**
     * 【根据id获取列表信息】
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getGridDataById.do")
    public void getGridDataById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject resObject = new JSONObject();
        try {
            JSONObject json = HttpUtil.getBodyJSON(request, "utf-8");
            Map resMap = this.pmlGridService.getGridDataById(json);
            resObject.put("data", resMap);
            resObject.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        }
        writer.write(resObject.toString());
    }


    /**
     * 【新增和修改方法】
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/addAndEdit.do")
    public void addAndEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject resObject = new JSONObject();
        try {
            JSONObject json = HttpUtil.getBodyJSON(request, "utf-8");
            this.pmlGridService.addAndEdit(request, json);
            resObject.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        }
        writer.write(resObject.toString());
    }


    /**
     * 【删除方法】--通过id删除
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/deleteDataById.do")
    public void deleteDataById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject resObject = new JSONObject();
        try {
            JSONObject json = HttpUtil.getBodyJSON(request, "utf-8");
            if (json.has("id") && !Util.isEoN(json.get("id"))) {
                Boolean res = this.pmlGridService.deleteDataById(request, json.get("id") + "");
                resObject.put("success", res);
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
//     * 【通用的打印方法】---全部打印+选中打印
//     *
//     * @param example
//     * @param request
//     * @throws Exception
//     */
//    @RequestMapping("/commonPrint.do")
//    @ResponseBody
//    public ReturnToJs commonPrint(@RequestBody String example, HttpServletRequest request) {
//        SecurityUser suser = SessionUtil.getCurrentSecurityUser(request);// 获取用户信息
//        SysUser user = suser.getUser();
//        JSONObject json = Util.checkFromJson(example);
//        Map resMap = this.pmlGridService.commonPrint(SecurityUserLocal.getSecurityUser().getUser(), json, request);
//        if ("true".equals(resMap.get("success"))) {
//            return ReturnToJs.success(resMap.get("data"));
//        } else {
//            return ReturnToJs.failure("/PmlGrid/commonPrint.do 错误");
//        }
//
//    }
//
//
//    /**
//     * 导出列表相关的配置数据
//     *
//     * @param ids
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/outputDataByIds.do")
//    public void outputDataByIds(@RequestParam(value = "ids", required = false) String ids,
//                                @RequestParam(value = "type") String type,
//                                HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        try {
//            HSSFWorkbook wb = this.pmlGridService.outputDataById(ids, type);
//
//            Date currentTime = new Date();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//            // 输出excel文件
//            OutputStream out = response.getOutputStream();
//            response.reset();
//            response.setHeader("Content-disposition",
//                    "attachment; filename=grid_" + formatter.format(currentTime) + ".xls");
//            response.setContentType("application/msexcel");
//            wb.write(out);
//            out.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 导出excel数据到数据库
//     *
//     * @param request
//     * @param response
//     * @throws Exception
//     */
//    @RequestMapping("/importData.do")
//    public void importData(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        ReturnToJs returnToJs = new ReturnToJs();
//        try {
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            MultipartFile file = multipartRequest.getFile("file");
//            String extendname = request.getParameter("extendname");
//            if ("xlsx".equals(extendname) || "xls".equals(extendname)) {
//                CommonsMultipartFile cf = (CommonsMultipartFile) file; //这个myfile是MultipartFile的
//                DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//                File excelFile = fi.getStoreLocation();
//                returnToJs.setSuccess(this.pmlGridService.importData(excelFile));
//                if (!returnToJs.isSuccess()) {
//                    returnToJs.setErrMsg("导入失败！");
//                }
//            } else {
//                returnToJs.setSuccess(false);
//                returnToJs.setErrMsg("excel格式不正确！");
//            }
//        } catch (Exception e) {
//            returnToJs.setSuccess(false);
//            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
//            System.out.println("/PmlGrid/importData.do 方法报错。");
//            log.error("/PmlGrid/importData.do 方法报错,错误信息：" + e);
//        }
//        writer.write(JSONObject.fromObject(returnToJs).toString());
//    }

    /**
     * 初始化
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/fetchGridByBusinesstype.do")
    public @ResponseBody
    ReturnToJs fetchGridByBusinesstype(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return ReturnToJs.success(this.pmlGridService.fetchGridByBusinesstype(jsonObject));
    }
}
