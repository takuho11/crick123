/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-7-25 16:10:05
 *
 */
package cn.topcheer.pms2.web.controller.announcement;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.announcement.RemindRole;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.announcement.impl.RemindRoleService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/RemindRole" })
public class RemindRoleController  extends GenericController<RemindRole> {

	@Autowired
	private RemindRoleService remindRoleService;


	/*==============================================完美分割线==============================================*/


	/**
	 *【主动提醒功能】 -- 通过用户信息，判断是否显示闹钟；如果显示闹钟，对应的数字是多少，点击后显示提醒内容的数组
	 */
	@RequestMapping(value = "/getRemindCount.do")
	public @ResponseBody ReturnToJs getAllConfigData(){
		return ReturnToJs.success(remindRoleService.getRemindCount());
	}


	/**
	 *【主动提醒功能】 -- 通过用户信息，提醒内容id，获取列表数据
	 */
	@RequestMapping(value = "/getListByRemindid.do")
	public @ResponseBody ReturnToJs getListByRemindid(@RequestBody JSONObject json){
		return ReturnToJs.success(remindRoleService.getListByRemindid(json));
	}



}
