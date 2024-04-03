package cn.topcheer.pms2.web.controller.usual;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.usual.UsualOpinion;
import cn.topcheer.pms2.biz.usual.UsualOpinionService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
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
@RequestMapping("/Opinion")
public class UsualOpinionController extends GenericController<UsualOpinion> {
    @Autowired
    UsualOpinionService usualOpinionService;
    public UsualOpinionService getUsualOpinionService()
    {
        return (UsualOpinionService) this.getGenericService();
    }

    @Autowired
    private void setUsualOpinionService(UsualOpinionService service)
    {
        this.setGenericService(service);
    }


    @PostMapping("/saveOpinion.do")
    public void save(@RequestBody String example,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        JSONObject jsonObject = Util.checkFromJson(example);
        try {
            this.usualOpinionService.saveorAddOpinions(jsonObject);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            e.printStackTrace();
            System.out.println("/Opinion/saveOpinion.do 方法报错。" + Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }

    @PostMapping("/deleteOpinion.do")
    public void deleteYsInfo(@RequestBody String example,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        JSONObject jsonObject = Util.checkFromJson(example);
        try {
            this.usualOpinionService.deleteOneOpinion(jsonObject);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            e.printStackTrace();
            System.out.println("/Opinion/deleteOpinion.do 方法报错。" + Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }

    @PostMapping("/getOpinions.do")
    public void getUnitInfoById(@RequestBody String example,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try {
            JSONObject json = Util.checkFromJson(example);
            List<Map> mapList = this.usualOpinionService.selectOpinions(json);
            returnToJs.setData(mapList);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
            e.printStackTrace();
            System.out.println("/Opinion/getOpinions.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


}
