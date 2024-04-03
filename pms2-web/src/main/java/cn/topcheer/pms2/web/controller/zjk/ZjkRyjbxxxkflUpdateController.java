/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2017-1-23 9:49:28
 */
package cn.topcheer.pms2.web.controller.zjk;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxxkflUpdate;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.biz.zjk.ZjkRyjbxxxkflUpdateService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/ZjkRyjbxxxkflUpdate"})
public class ZjkRyjbxxxkflUpdateController extends GenericController<ZjkRyjbxxxkflUpdate> {
    @Autowired
    DBHelper dbHelper;
    @Autowired
    private SysUserService sysUserService;


    public ZjkRyjbxxxkflUpdateController() {
        // TODO Auto-generated constructor stub

        /**
         * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
         */

        PS_SET.put(ZjkRyjbxxxkflUpdate.class.getName(), "[id],[firstleveldiscipline],[subordinatediscipline],[thirdleveldiscipline],[person_id],[project_id],[seq],[xmzlfl],[discipline],[cdxmlx],[panels],[managedomain]");
        PS_SET_SIMPLE.put(ZjkRyjbxxxkflUpdate.class.getName(), "[id],[firstleveldiscipline],[subordinatediscipline],[thirdleveldiscipline],[person_id],[project_id],[seq],[xmzlfl],[discipline],[cdxmlx],[panels],[managedomain]");
        PS_Config = JsonBuilder.getJsonConfig(PS_SET);
        PS_Config_SIMPLE = JsonBuilder.getJsonConfig(PS_SET);

    }

    public ZjkRyjbxxxkflUpdateService getZjkRyjbxxxkflUpdateService() {
        return (ZjkRyjbxxxkflUpdateService) this.getGenericService();
    }

    @Autowired
    private void setZjkRyjbxxxkflUpdateService(ZjkRyjbxxxkflUpdateService service) {
        this.setGenericService(service);
    }

    @Autowired
    ZjkRyjbxxxkflUpdateService zjkRyjbxxxkflUpdateService;

    /**
     *  根据传入的id返回相应的ZjkRyjbxxxkflUpdate 对象（json格式的字符串）
     * @param id
     * @param fetchParent 是否同时获取父对象，仅一级，不递归
     * @return ZjkRyjbxxxkflUpdate 对象（json格式，字符串形式）
     */
    @RequestMapping({"/findById.do"})
    public @ResponseBody
    String findById(@RequestParam(value = "id") String id, @RequestParam(value = "fetchParent", defaultValue = "true") Boolean fetchParent,
                    HttpServletRequest request, HttpServletResponse response) {

        return super._findById(id, fetchParent, request, response);

    }

    /**
     *  根据传入的id返回相应的 ZjkRyjbxxxkflUpdate 对象（json格式的字符串）
     * @param fetchParent 是否同时获取父对象，仅一级，不递归
     * @return ZjkRyjbxxxkflUpdate 对象（json格式，字符串形式）
     */
    @RequestMapping({"/findByExample.do"})
    public @ResponseBody
    String findByExample(@RequestParam(value = "example", required = false) String example, @RequestParam(value = "fetchParent", defaultValue = "false") Boolean fetchParent,
                         HttpServletRequest request, HttpServletResponse response) {
        return super._findByExample(example, fetchParent, request, response);
    }

    /**
     *  保存传入的 ZjkRyjbxxxkflUpdate 对象（json格式的字符串）
     * @param zjkRyjbxxxkflUpdate 传入的zjkRyjbxxxkflUpdate对象
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/save.do"})
    public @ResponseBody
    String saveZjkRyjbxxxkflUpdate(@RequestBody String zjkRyjbxxxkflUpdate,
                                   HttpServletRequest request, HttpServletResponse response) {
        return super._save(zjkRyjbxxxkflUpdate, request, response);
    }

    /**
     *  根据传入的id删除相应的 ZjkRyjbxxxkflUpdate 对象（json格式的字符串）
     * @param id 要删除的 ZjkRyjbxxxkflUpdate 对象 id值
     * @return 操作结果 一般是 {"success":"true"}
     */
    @RequestMapping({"/deleteById.do"})
    public @ResponseBody
    String deleteById(@RequestParam(value = "id") String id,
                      HttpServletRequest request, HttpServletResponse response) {
        return super._deleteById(id, request, response);
    }

    /**
     *  根据传入的ids删除相应的 ZjkRyjbxxxkflUpdate 对象（json格式的字符串）
     * @param ids 要删除的 ZjkRyjbxxxkflUpdate 对象 id值,多个id之间用逗号(,)分隔
     * @return 操作结果 一般是 {"success":"true"}
     */
    @RequestMapping({"/deleteByIds.do"})
    public @ResponseBody
    String deleteByIds(@RequestParam(value = "ids") String ids,
                       HttpServletRequest request, HttpServletResponse response) {
        return super._deleteByIds(ids, request, response);
    }


    /**
     * 根据type查询所有学科
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findXkflTree.do")
    public ModelAndView findXkflTree(@RequestParam(value = "type") String type,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
            List<Map> listPpc = this.zjkRyjbxxxkflUpdateService.findXkflTree(type);
            JSONArray jsonArray = JSONArray.fromObject(listPpc);
            returnToJs.setData(jsonArray);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/ZjkRyjbxxxkflUpdate/findXkflTree.do 方法报错。" + Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
        return null;
    }


    /**
     * 根据type查询所有学科
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateXkflTree.do")
    public ModelAndView updateXkflTree(@RequestParam(value = "type") String type,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
            // List<Map> listPpc = this.zjkRyjbxxxkflUpdateService.updateXkflTree(type);
            // JSONArray jsonArray = JSONArray.fromObject(listPpc);
            // returnToJs.setData(jsonArray);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/ZjkRyjbxxxkflUpdate/updateXkflTree.do 方法报错。" + Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
        return null;
    }


    @RequestMapping(value = "/newfindXkflTree.do")
    public ModelAndView newfindXkflTree(@RequestParam(value = "type") String type,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            List<Map> listPpc = this.zjkRyjbxxxkflUpdateService.newfindXkflTree(type);
            returnToJs.setData(!Util.isEoN(listPpc) && listPpc.size() > 0 ? listPpc.get(0).get("source") : null);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/ZjkRyjbxxxkflUpdate/newfindXkflTree.do 方法报错。" + Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
        return null;
    }


    @RequestMapping(value = "/dealXmzlfl.do")
    public ModelAndView dealXmzlfl(@RequestParam(value = "project_id") String project_id,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            if ("1".equals(project_id)) {
                String datasql = "select * from zjk_ryjbxxxkfl_update where project_id = '1' " +
                        "and seq is not null and person_id is not null and subjectonecode is null";
                List<Map> dataList = this.dbHelper.getRows(datasql, null);
                Session session = zjkRyjbxxxkflUpdateService.getZjkRyjbxxxkflUpdateDao().getSessionFactory().getCurrentSession();
                if (dataList.size() > 0) {
                    for (int i = 0; i < dataList.size(); i++) {
                        String id = dataList.get(i).get("id") + "";
                        String discipline = dataList.get(i).get("discipline") + "";
                        String type = dataList.get(i).get("xmzlfl") + "";
                        ZjkRyjbxxxkflUpdate zjkRyjbxxxkflUpdate = this.getZjkRyjbxxxkflUpdateService().dealData(id, discipline, type, dataList.size(), i);
                        session.save(zjkRyjbxxxkflUpdate);
                        if (i % 50 == 0) {
                            session.flush();
                            session.clear();
                        }
                    }
                }
                session.clear();
            }
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/ZjkRyjbxxxkflUpdate/dealXmzlfl.do 方法报错。" + Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
        return null;
    }


//    @RequestMapping(value = "/ycetest.do")
//    public void test() {
//        List<ZjkRyjbxxxkflUpdate> all = zjkRyjbxxxkflUpdateService.findAll();
//        List<ZjkRyjbxxxkflUpdate> err = new ArrayList<>();
//
//        for (ZjkRyjbxxxkflUpdate zjkRyjbxxxkflUpdate : all) {
//            if (!Util.isEoN(zjkRyjbxxxkflUpdate.getSubjectfourcode()) && zjkRyjbxxxkflUpdate.getSubjectfourcode().contains(zjkRyjbxxxkflUpdate.getSubjectthreecode())) {
//
//            }
//
//        }
//        System.out.println(all.size());
//    }

}
