/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2016-2-22 14:53:22
 */
package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.PmsEnterpriselimit;
import cn.topcheer.pms2.biz.sys.PmsEnterpriselimitService;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping({"/PmsEnterpriselimit"})
public class PmsEnterpriselimitController extends GenericController<PmsEnterpriselimit> {

    @Autowired
    private PmsEnterpriselimitService pmsEnterpriselimitService;
    @Autowired
    private SysOperationrecordService sysOperationrecordService;

    public PmsEnterpriselimitController() {
        // TODO Auto-generated constructor stub

        /**
         * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
         */

        PS_SET.put(PmsEnterpriselimit.class.getName(), "[enterprisename],[limitnumber],[isable],[enterprisetype],[recommendlimit],[id],[batchname],[recommendnum],[enterpriseid],[pmsplanprojectbatchid]");
        PS_SET_SIMPLE.put(PmsEnterpriselimit.class.getName(), "[enterprisename],[limitnumber],[isable],[enterprisetype],[recommendlimit],[id]");
        PS_Config = JsonBuilder.getJsonConfig(PS_SET);
        PS_Config_SIMPLE = JsonBuilder.getJsonConfig(PS_SET);

    }

    public PmsEnterpriselimitService getPmsEnterpriselimitService() {
        return (PmsEnterpriselimitService) this.getGenericService();
    }

    @Autowired
    private void setPmsEnterpriselimitService(PmsEnterpriselimitService service) {
        this.setGenericService(service);
    }


    /**
     * 根据传入的id返回相应的PmsEnterpriselimit 对象（json格式的字符串）
     *
     * @param id
     * @param fetchParent 是否同时获取父对象，仅一级，不递归
     * @return PmsEnterpriselimit 对象（json格式，字符串形式）
     */
    @RequestMapping({"/findById.do"})
    public @ResponseBody
    String findById(@RequestParam(value = "id") String id, @RequestParam(value = "fetchParent", defaultValue = "true") Boolean fetchParent,
                    HttpServletRequest request, HttpServletResponse response) {

        return super._findById(id, fetchParent, request, response);

    }

    /**
     * 根据传入的id返回相应的 PmsEnterpriselimit 对象（json格式的字符串）
     *
     * @param fetchParent 是否同时获取父对象，仅一级，不递归
     * @return PmsEnterpriselimit 对象（json格式，字符串形式）
     */
    @RequestMapping({"/findByExample.do"})
    public @ResponseBody
    String findByExample(@RequestBody String example, @RequestParam(value = "fetchParent", defaultValue = "false") Boolean fetchParent,
                         HttpServletRequest request, HttpServletResponse response) {
        return super._findByExample(example, fetchParent, request, response);
    }


    /**
     * 保存传入的 PmsEnterpriselimit 对象（json格式的字符串）
     *
     * @param pmsEnterpriselimit 传入的pmsEnterpriselimit对象
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/save.do"})
    public @ResponseBody
    String savePmsEnterpriselimit(@RequestBody String pmsEnterpriselimit,
                                  HttpServletRequest request, HttpServletResponse response) {
        return super._save(pmsEnterpriselimit, request, response);
    }

    /**
     * 根据传入的id删除相应的 PmsEnterpriselimit 对象（json格式的字符串）
     *
     * @param id 要删除的 PmsEnterpriselimit 对象 id值
     * @return 操作结果 一般是 {"success":"true"}
     */
    @RequestMapping({"/deleteById.do"})
    public @ResponseBody
    String deleteById(@RequestParam(value = "id") String id,
                      HttpServletRequest request, HttpServletResponse response) {
        return super._deleteById(id, request, response);
    }

    /**
     * 根据传入的ids删除相应的 PmsEnterpriselimit 对象（json格式的字符串）
     *
     * @param ids 要删除的 PmsEnterpriselimit 对象 id值,多个id之间用逗号(,)分隔
     * @return 操作结果 一般是 {"success":"true"}
     */
    @RequestMapping({"/deleteByIds.do"})
    public @ResponseBody
    String deleteByIds(@RequestParam(value = "ids") String ids,
                       HttpServletRequest request, HttpServletResponse response) {
        return super._deleteByIds(ids, request, response);
    }

    /**
     * 初始化列表方法
     *
     * @param example
     * @throws Exception
     */
    @RequestMapping(value = {"/initEnterpriselimit.do"})
    public @ResponseBody
    R initEnterpriselimit(@RequestBody JSONObject example) {
        JSONObject resJson = new JSONObject();
        List<Map> list = this.pmsEnterpriselimitService.initEnterpriselimit(example);
        resJson.put("data", list);
        resJson.put("total", Util.isEoN(list) ? 0 : list.size());
        return R.data(resJson);
    }

    /**
     * 根据 id 修改指标限额
     *
     * @param id
     * @param request
     * @throws Exception
     */
    @RequestMapping("/editLimitNum.do")
    public @ResponseBody
    R editLimitNum(@RequestParam(value = "id") String id,
                   @RequestParam(value = "newlimitnum", required = false) String newlimitnum,
                   @RequestParam(value = "endtime", required = false) String endtime,
                   HttpServletRequest request) {
        if (newlimitnum != null) {
            this.pmsEnterpriselimitService.editLimitNum(id, newlimitnum, request);
            return R.success("修改成功");
        } else {
            JSONObject resJson = this.pmsEnterpriselimitService.editEndtime(id, endtime, request);
            if (!resJson.getBoolean("success")) {
                return R.fail(resJson.getString("reason"));
            }
        }
        return R.success("修改成功");
    }

    /**
     * 【通用】：根据sourceid 和 type 获取操作记录
     *
     * @param example
     * @throws Exception
     */
    @RequestMapping("/getRecord.do")
    public @ResponseBody
    R getRecord(@RequestBody JSONObject example) {
        return R.data(this.sysOperationrecordService.getRecord(example));
    }

    /**
     * 新增新单位指标
     */
    @RequestMapping("/addLimitNum.do")
    public @ResponseBody
    R addLimitNum(@RequestBody JSONObject example,
                  HttpServletRequest request) {
        JSONObject resJson = this.pmsEnterpriselimitService.addLimitNum(example, request);
        if (!resJson.getBoolean("success")) {
            return R.fail(resJson.getString("reason"));
        }
        return R.success("修改成功");
    }

    /**
     * 根据 id 删除指标限额
     */
    @RequestMapping("/deleteData.do")
    public @ResponseBody
    R deleteData(@RequestParam(value = "id") String id,
                 HttpServletRequest request) {
        this.pmsEnterpriselimitService.deleteData(id, request);
        return R.success("删除成功");
    }

    /*
     * 导入批次相关单位指标
     * */
    @RequestMapping(value = "/importTableByExcel.do")
    public @ResponseBody
    R importTableByExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String extendname = request.getParameter("extendname");
        if ("xlsx".equals(extendname) || "xls".equals(extendname)) {
            List<Object> read = ExcelUtil.read(file, 0, 0, null);
            String res = this.pmsEnterpriselimitService.importByExcelFile(request, read);
            if (!Util.isEoN(res)) {
                return R.fail(res);
            } else {
                return R.success("导入成功");
            }
        } else {
            return R.fail("excel格式不正确");
        }
    }


    /**
     * 【获取单位指标--根据批次和用户获取指标总算和已用】
     * @param batchid
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping({"/findLimitBybatchid.do"})
    public void findLimitBybatchid(@RequestParam(value = "batchid") String batchid, @RequestParam String type, @RequestParam(required = false) Integer count,
                                   @RequestParam(required = true) String mainid,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            PmsEnterpriselimit pmsEnterpriselimit = this.pmsEnterpriselimitService.getPmsEnterpriseDirectionLimitIfNull(batchid, mainid, type, count);
            returnToJs.setData(pmsEnterpriselimit);
            returnToJs.setSuccess(true);
        } catch (NumberFormatException e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护人员：" + Util.linkNumber);
            log.error("/PmsEnterpriselimit/findLimitBybatchid.do方法报错" + Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


    @RequestMapping({"/findLimitBybatchidMul.do"})
    @ResponseBody
    public ReturnToJs findLimitBybatchidMul(@RequestBody JSONObject jsonObject) throws Exception {

        return this.pmsEnterpriselimitService.getPmsEnterpriseDirectionLimitIfNullMul(jsonObject.getString("planId"),
                jsonObject.getJSONArray("mainids"), jsonObject.getString("type"), jsonObject.has("count") ? jsonObject.getInt("count") : 0);
    }

    /**
     * 获取当前单位可选批次
     */
    @PostMapping("/getAllBigPlan")
    @ApiModelProperty(value = "获取当前单位可选批次", notes = "获取当前单位可选批次")
    @ResponseBody
    public R getAllBigPlan() {
        return this.pmsEnterpriselimitService.getAllBigPlan();
    }

//    /**
//     * 获取批次已分配指标的单位数据
//     */
//    @PostMapping("/getEnterpriseByBatch")
//    @ApiModelProperty(value = "获取批次已分配指标的单位数据", notes = "获取批次已分配指标的单位数据")
//    @ResponseBody
//    public R getEnterpriseByBatch(@RequestParam(value = "batchid") String batchid, @RequestParam(value = "serachStr") String serachStr) {
//        return this.pmsEnterpriselimitService.getEnterpriseByBatch(batchid, serachStr);
//    }

    /**
     * 查询组织信息
     */
    @PostMapping("/limitedOrganization")
    @ApiModelProperty(value = "获取一级单位数据", notes = "获取一级单位数据")
    @ResponseBody
    public R limitedOrganization(@RequestParam(value = "batchid") String batchid,
                                 @RequestParam(value = "serachStr") String serachStr,
                                 @RequestParam(value = "type") String type) {
        return this.pmsEnterpriselimitService.limitedOrganization(batchid,serachStr,type);
    }
}
