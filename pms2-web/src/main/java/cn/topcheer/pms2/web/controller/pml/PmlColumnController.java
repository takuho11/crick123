/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2019-3-3 15:54:33
 */
package cn.topcheer.pms2.web.controller.pml;

import cn.topcheer.halberd.app.api.utils.HttpUtil;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.pml.service.impl.PmlColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "专栏控制器", tags = "列表-专栏控制器")
@RequestMapping("/pml/PmlColumn")
public class PmlColumnController {

    @Autowired
    private PmlColumnService pmlColumnService;


    /**
     * 【初始化和搜索方法】
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
            JSONObject json = HttpUtil.getBodyJSON(request, "utf-8");
            PageResult<List<Map>> page = this.pmlColumnService.getData(json);
            resObject.put("data", page);
            resObject.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        }
        writer.write(resObject.toString());
    }

    /**
     * 【新增和修改方法】
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
            this.pmlColumnService.addAndEdit(request, json);
            resObject.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        }
        writer.write(resObject.toString());
    }

    /**
     * 【删除方法】--通过id删除
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
                Boolean res = this.pmlColumnService.deleteDataById(request, json.get("id") + "");
                if (res) {
                    resObject.put("success", true);
                } else {
                    resObject.put("success", false);
                    resObject.put("data", "该列名已经被列表选择，不能删除");
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

    /**
     * 查看列名绑定的列表
     */
    @PostMapping("/getGridData.do")
    public @ResponseBody
    ReturnToJs getGridData(@RequestBody JSONObject json) {
        return this.pmlColumnService.getGridData(json);
    }

}
