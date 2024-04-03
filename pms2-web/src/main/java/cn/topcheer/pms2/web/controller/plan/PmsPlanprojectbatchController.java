package cn.topcheer.pms2.web.controller.plan;

import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.utils.Util;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
@RestController
@RequestMapping("/PmsPlanprojectbatch")
@Api(value = "小批次维护",tags = "批次-小批次维护")
public class PmsPlanprojectbatchController {
    @Autowired
    PmsPlanprojectbatchService pmsPlanprojectbatchService;

    /**
     * 【通过批次id获取批次名词】
     * @param id
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/getBatchnameById.do")
    public void getBatchnameById(@RequestParam(value="id") String id,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            returnToJs.setData(this.pmsPlanprojectbatchService.getBatchnameById(id));
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/getBatchnameById.do 方法报错。");
            log.error("/getBatchnameById.do 方法报错,错误信息："+e);
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }

}
