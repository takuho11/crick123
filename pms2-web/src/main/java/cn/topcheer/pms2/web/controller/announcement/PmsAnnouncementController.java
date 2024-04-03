/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-5-20 16:18:55
 *
 */
package cn.topcheer.pms2.web.controller.announcement;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.announcement.PmsAnnouncement;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.announcement.impl.PmsAnnouncementService;
import cn.topcheer.pms2.biz.utils.Util;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping({"/PmsAnnouncement" })
public class PmsAnnouncementController extends GenericController<PmsAnnouncement> {
	 	 
	
	public PmsAnnouncementController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(PmsAnnouncement.class.getName(),"[id],[title],[content],[isshow],[isrelease],[savedate],[releaseuser],[releasetime]"); 
		PS_SET_SIMPLE.put(PmsAnnouncement.class.getName(), "[id],[title],[content],[isshow],[isrelease],[savedate],[releaseuser],[releasetime]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public PmsAnnouncementService getPmsAnnouncementService()
	{
		return (PmsAnnouncementService) this.getGenericService();
	}
	
	@Autowired
	private void setPmsAnnouncementService(PmsAnnouncementService service)
	{
		this.setGenericService(service);
	}
	 
	
	/**
	 *  根据传入的id返回相应的PmsAnnouncement 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsAnnouncement 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 PmsAnnouncement 对象（json格式的字符串）
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsAnnouncement 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 PmsAnnouncement 对象（json格式的字符串）
	 * @param pmsAnnouncement 传入的pmsAnnouncement对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String savePmsAnnouncement(@RequestBody String pmsAnnouncement,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(pmsAnnouncement, request, response);
	}

	/**
	 *  根据传入的id删除相应的 PmsAnnouncement 对象（json格式的字符串）
	 * @param id 要删除的 PmsAnnouncement 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 PmsAnnouncement 对象（json格式的字符串）
	 * @param ids 要删除的 PmsAnnouncement 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}

	/**
	 * 【通知通告】---获取数据
	 * @param example
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getData.do")
	public void getData(@RequestBody String example,
						 HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			returnToJs.setData(this.getPmsAnnouncementService().getData(json));
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/PmsAnnouncement/getData.do 方法报错。");
			log.error("/PmsAnnouncement/getData.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

	/**
	 * 【通知通告】---获取发布数据
	 * @param example
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getReleaseData.do")
	public void getReleaseData(@RequestBody String example,
						HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			returnToJs.setData(this.getPmsAnnouncementService().getReleaseData(json));
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/PmsAnnouncement/getReleaseData.do 方法报错。");
			log.error("/PmsAnnouncement/getReleaseData.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 【通知通告】---获取发布数据  分页
	 * @param example
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getReleaseDataByPage.do")
	public void getReleaseDataByPage(@RequestBody String example,
							   HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			returnToJs.setData(this.getPmsAnnouncementService().getReleaseDataByPage(json));
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/PmsAnnouncement/getReleaseData.do 方法报错。");
			log.error("/PmsAnnouncement/getReleaseData.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

	/**
	 * 【通知通告】---新增修改方法
	 * @param example
	 * @param request
	 * @param response
	 * @throws Exception
     */
	@RequestMapping("/saveData.do")
	public void saveData(@RequestBody String example,
					  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			this.getPmsAnnouncementService().saveData(request,json);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/PmsAnnouncement/saveData.do 方法报错。");
			log.error("/PmsAnnouncement/saveData.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 【通知通告】---删除修改方法
	 * @param example
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/deleteData.do")
	public void deleteData(@RequestBody String example,
						 HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			this.getPmsAnnouncementService().deleteData(request,json);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/PmsAnnouncement/deleteData.do 方法报错。");
			log.error("/PmsAnnouncement/deleteData.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

	/**
	 * 【通知通告】---发布方法
	 * @param example
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/releaseData.do")
	public void releaseData(@RequestBody String example,
						   HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			this.getPmsAnnouncementService().releaseData(request,json);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/PmsAnnouncement/releaseData.do 方法报错。");
			log.error("/PmsAnnouncement/releaseData.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

	/**
	 * 【通知通告】---通过id获取内容
	 * @param example
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getDataById.do")
	public void getDataById(@RequestBody String example,
							HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			String id = json.get("id")+"";
			JSONObject resJson = new JSONObject();
			resJson.put("content",this.getPmsAnnouncementService().getDataById(id));
			resJson.put("fjList",this.getPmsAnnouncementService().getFjList(id));
			returnToJs.setData(resJson);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/PmsAnnouncement/releaseData.do 方法报错。");
			log.error("/PmsAnnouncement/releaseData.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

	@RequestMapping(value = "/selectAnnouncementByExample.do")
	public void selectAnnouncementByExample(@RequestBody String myselect2,
												  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		JSONObject select = JSONObject.fromObject(myselect2);
		JSONObject result = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
		List<Map> listUsers = null;
		listUsers = this.getPmsAnnouncementService().selectUserByNameAndOffice(select);
		result.put("data", listUsers);
		writer.write(result.toString());
	}

	@RequestMapping(value = "/updateAnnouncement.do")
	public void updateAnnouncement(@RequestBody String example,
											HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		JSONObject select = JSONObject.fromObject(example);
		ReturnToJs result = new ReturnToJs();
		Boolean b = this.getPmsAnnouncementService().updateAnnouncement(select);
		result.setSuccess(b);
		writer.write(result.toString());
	}
	@RequestMapping("/checkAll")
	public String checkAll(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		boolean check = this.getPmsAnnouncementService().checkAll();
		if (check){
			return "检查无误";
		}else {
			return "存在个人信息注册单位与缴纳社保单位不符合";
		}
	}
	@RequestMapping("/checkOne")
	public String checkOne(@RequestParam String ID, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		boolean check = this.getPmsAnnouncementService().checkOne(ID);
		if (check){
			return "检查无误";
		}else {
			return "存在个人信息注册单位与缴纳社保单位不符合";
		}
	}
}
