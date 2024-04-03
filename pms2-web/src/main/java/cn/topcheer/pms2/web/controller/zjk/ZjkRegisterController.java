package cn.topcheer.pms2.web.controller.zjk;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.zjk.ZjkRegisterService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XL on 2019/5/7.
 */
@Controller
@RequestMapping({"/ZjkRegister" })
public class ZjkRegisterController extends GenericController<SysUser> {
    public ZjkRegisterService getZjkRegisterService()
    {
        return (ZjkRegisterService) this.getGenericService();
    }
    @Autowired
    private ZjkRegisterService zjkRegisterService;

    /**
     * 专家信息和个人信息修改单位信息互斥
     * @param request
     * @param response
     */
    @RequestMapping("/judgeIsCanChange.do")
    public @ResponseBody ReturnToJs judgeIsCanChange(@RequestParam String sourceid, @RequestParam String type,
                                                     HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = this.zjkRegisterService.judgeIsCanChange(sourceid,type);
        return ReturnToJs.success(result);
    }

}
