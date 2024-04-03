package cn.topcheer.pms2.web.controller.announcement;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.announcement.HomeIconList;
import cn.topcheer.pms2.api.pml.vo.Page;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.announcement.impl.HomeIconListService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/HomeIconList"})
public class HomeIconListController extends GenericController<HomeIconList> {

    @Autowired
    HomeIconListService homeIconListService;

    @RequestMapping("/saveOrAddIcon.do")
    public @ResponseBody ReturnToJs saveOrAddIcon(@RequestBody String example){
        JSONObject jsonObject = Util.checkFromJson(example);
        homeIconListService.saveOrAddIcon(jsonObject);
        return ReturnToJs.success();
    }

    /**
     * 获取发送记录
     * @param
     * @return
     */
    @RequestMapping("/getIconList.do")
    public @ResponseBody
    ReturnToJs getIconList(@RequestBody String example){
        JSONObject json = Util.checkFromJson(example);
        Page<Map> list = this.homeIconListService.getIconList(json);
        return ReturnToJs.success(list);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteIcon.do")
    public @ResponseBody ReturnToJs deleteIcon(@RequestParam String id){
        return this.homeIconListService.deleteIcon(id);
    }

    /**
     * 首页根据登陆角色展示图标
     * @param userid
     * @return
     */
    @RequestMapping(value = "/getIconByRole.do")
    public @ResponseBody ReturnToJs getIconByRole(@RequestParam String userid){
        List<Map> iconByRole = this.homeIconListService.getIconByRole(userid);
        return ReturnToJs.success(iconByRole);
    }



}
