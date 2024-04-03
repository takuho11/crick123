/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2020-11-10 9:04:02
 */
package cn.topcheer.pms2.web.controller.pml;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.biz.pml.service.impl.PmlSearchboxService;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pml/PmlSearchbox")
@Api(value = "搜索框", tags = "列表-搜索框")
public class PmlSearchboxController {

    @Autowired
    private PmlSearchboxService pmlSearchboxService;


//    /**
//     *  根据传入的id返回相应的PmlSearchbox 对象（json格式的字符串）
//     * @param id
//     * @param fetchParent 是否同时获取父对象，仅一级，不递归
//     * @return PmlSearchbox 对象（json格式，字符串形式）
//     */
//    @RequestMapping({"/findById.do"})
//    public @ResponseBody
//    String findById(@RequestParam(value = "id") String id, @RequestParam(value = "fetchParent", defaultValue = "true") Boolean fetchParent,
//                    HttpServletRequest request, HttpServletResponse response) {
//
//        return super._findById(id, fetchParent, request, response);
//
//    }
//
//    /**
//     *  根据传入的id返回相应的 PmlSearchbox 对象（json格式的字符串）
//     * @param id
//     * @param fetchParent 是否同时获取父对象，仅一级，不递归
//     * @return PmlSearchbox 对象（json格式，字符串形式）
//     */
//    @RequestMapping({"/findByExample.do"})
//    public @ResponseBody
//    String findByExample(@RequestParam(value = "example", required = false) String example, @RequestParam(value = "fetchParent", defaultValue = "false") Boolean fetchParent,
//                         HttpServletRequest request, HttpServletResponse response) {
//        return super._findByExample(example, fetchParent, request, response);
//    }
//
//    /**
//     *  保存传入的 PmlSearchbox 对象（json格式的字符串）
//     * @param pmlSearchbox 传入的pmlSearchbox对象
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = {"/save.do"})
//    public @ResponseBody
//    String savePmlSearchbox(@RequestBody String pmlSearchbox,
//                            HttpServletRequest request, HttpServletResponse response) {
//        return super._save(pmlSearchbox, request, response);
//    }
//
//    /**
//     *  根据传入的id删除相应的 PmlSearchbox 对象（json格式的字符串）
//     * @param id 要删除的 PmlSearchbox 对象 id值
//     * @return 操作结果 一般是 {"success":"true"}
//     */
//    @RequestMapping({"/deleteById.do"})
//    public @ResponseBody
//    String deleteById(@RequestParam(value = "id") String id,
//                      HttpServletRequest request, HttpServletResponse response) {
//        return super._deleteById(id, request, response);
//    }
//
//    /**
//     *  根据传入的ids删除相应的 PmlSearchbox 对象（json格式的字符串）
//     * @param ids 要删除的 PmlSearchbox 对象 id值,多个id之间用逗号(,)分隔
//     * @return 操作结果 一般是 {"success":"true"}
//     */
//    @RequestMapping({"/deleteByIds.do"})
//    public @ResponseBody
//    String deleteByIds(@RequestParam(value = "ids") String ids,
//                       HttpServletRequest request, HttpServletResponse response) {
//        return super._deleteByIds(ids, request, response);
//    }


    /**
     * 搜索框初始化和搜索方法
     *
     * @param json
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getData.do",method = RequestMethod.POST)
    public @ResponseBody
    String getData(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) {
        JSONObject resObject = new JSONObject();
        try {
            PageResult<List<Map>> page = pmlSearchboxService.getData(json);
            resObject.put("data", page);
            resObject.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        } finally {
            return resObject.toString();
        }
    }

    /**
     * 搜索框新增和修改方法
     *
     * @param json
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/addAndEdit.do",method = RequestMethod.POST)
    public @ResponseBody
    String addAndEdit(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) {
        JSONObject resObject = new JSONObject();
        try {
            pmlSearchboxService.addAndEdit(request, json);
            resObject.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        } finally {
            return resObject.toString();
        }
    }

    /**
     * 删除方法--通过id删除
     *
     * @param json
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ="/deleteDataById.do",method = RequestMethod.POST)
    public @ResponseBody
    String deleteDataById(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) {
        JSONObject resObject = new JSONObject();
        try {
            if (json.containsKey("id") && !Util.isEoN(json.get("id"))) {
                Boolean res = pmlSearchboxService.deleteDataById(request, json.get("id") + "");
                if (res) {
                    resObject.put("success", true);
                } else {
                    resObject.put("success", false);
                    resObject.put("data", "该搜索已经被搜索框组合选择，不能删除");
                }
            } else {
                resObject.put("success", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resObject.put("success", false);
        } finally {
            return resObject.toString();
        }
    }


    @RequestMapping(value ="/getAllData.do",method = RequestMethod.POST)
    public @ResponseBody
    String getAllData(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resObject = new JSONObject();
        List<Map> data = pmlSearchboxService.getAllData();
        resObject.put("data", data);
        resObject.put("success", true);
        return resObject.toString();
    }

}
