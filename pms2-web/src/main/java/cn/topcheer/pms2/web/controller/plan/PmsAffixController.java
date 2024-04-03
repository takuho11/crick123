package cn.topcheer.pms2.web.controller.plan;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.plan.service.impl.PmsAffixService;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:shenxian
 * @othername:liuxue
 * @Date:2024-01-26 10:17
 */
@Slf4j
@Controller
@RequestMapping({"/PmsAffix" })
public class PmsAffixController {
    @Autowired
    private PmsAffixService pmsAffixService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * @param request
     * 读取附件列表 但是不包括附件内容
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAffixlistsByCategory.do")
    public @ResponseBody JSONObject getAffixlistsByCategory(@RequestParam String sourceid, @RequestParam(required = false) String category, @RequestParam(required = false) String clobTypeName,
            HttpServletRequest request)
            throws Exception {
        JSONObject result = new JSONObject();
        if (!Util.isEoN(category))
            category = URLDecoder.decode(category, "utf-8");
        JSONArray jsonArr = null;
        String webPath = request.getContextPath();
        if (!Util.isEoN(sourceid)) {
            List<Map> lists = null;
            if (!Util.isEoN(clobTypeName)) {
                lists = pmsAffixService.getAffixsAndBlobByCategory(sourceid, category, URLDecoder.decode(clobTypeName, "utf-8"));
            } else {
                if (!Util.isEoN(category)) {
                    lists = pmsAffixService.getAffixsByCategory(sourceid, category);
                } else {
                    lists = pmsAffixService.getAffixsByCategory(sourceid);
                }
            }

            JSONArray configArr = new JSONArray();
            JSONArray arrayurls = new JSONArray();
            if (lists != null && lists.size() > 0) {
                for (int i = 0; i < lists.size(); i++) {
                    Map tempMap = lists.get(i);
                    Map map = new HashMap();
                    map.put("caption", tempMap.get("name"));
                    map.put("width", "120px");
                    map.put("size", tempMap.get("filesize"));
                    if (!Util.isEoN(category)) {
                        map.put("url", webPath + "/deleteAffixFileByCategory.do?sourceId=" + sourceid + "&ispost=false&category=" + URLEncoder.encode(URLEncoder.encode(category, "utf-8"), "utf-8"));
                    } else {
                        map.put("url", webPath + "/deleteAffixFileByCategory.do?sourceId=" + sourceid + "&delete=id");
                    }
                    map.put("key", tempMap.get("id"));
                    map.put("frameClass", "my-custom-frame-css");
                    map.put("ftype", "image");
                    configArr.add(map);
                }
            }
            jsonArr = JSONArray.fromObject(lists);
            result.put("data", jsonArr);
            result.put("downurl", arrayurls);
            result.put("filesConfig", configArr);
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    /**
     * 【黑龙江开发】 --- 给个id集合，返回对应的附件
     *
     * @param example
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getAffixlistsBySourceids.do")
    public void getAffixlistsBySourceids(@RequestBody String example,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            JSONObject json = Util.checkFromJson(example);
            returnToJs.setData(this.pmsAffixService.getAffixlistsBySourceids(json, request));
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            System.out.println("/getAffixlistsBySourceids.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
}
