/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-3-5 11:23:05
 *
 */
package cn.topcheer.pms2.web.controller.zjk;

import cn.topcheer.halberd.app.api.utils.JsonDateValueProcessor;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.zjk.ZjkBatchCns;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.biz.zjk.ZjkBatchCnsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/ZjkBatchCns" })
public class ZjkBatchCnsController extends GenericController<ZjkBatchCns> {
	 	 
	
	public ZjkBatchCnsController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(ZjkBatchCns.class.getName(),"[id],[titlename],[content],[modulebtns],[limitsecond],[batchliststring],[memo],[creatdate]"); 
		PS_SET_SIMPLE.put(ZjkBatchCns.class.getName(), "[id],[titlename],[content],[modulebtns],[limitsecond],[batchliststring],[memo],[creatdate]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public ZjkBatchCnsService getZjkBatchCnsService()
	{
		return (ZjkBatchCnsService) this.getGenericService();
	}
	
	@Autowired
	private void setZjkBatchCnsService(ZjkBatchCnsService service)
	{
		this.setGenericService(service);
	}
	 
//
//	/**
//	 *  根据传入的id返回相应的ZjkBatchCns 对象（json格式的字符串）
//	 * @param id
//	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
//	 * @return ZjkBatchCns 对象（json格式，字符串形式）
//	 */
//	@RequestMapping({ "/findById.do" })
//	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
//			HttpServletRequest request, HttpServletResponse response){
//
//		return super._findById(id, fetchParent, request, response);
//
//	}
//
//	/**
//	 *  根据传入的id返回相应的 ZjkBatchCns 对象（json格式的字符串）
//	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
//	 * @return ZjkBatchCns 对象（json格式，字符串形式）
//	 */
//	@RequestMapping({ "/findByExample.do" })
//	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
//			HttpServletRequest request, HttpServletResponse response){
//		return super._findByExample(example, fetchParent, request, response);
//	}
//
//	/**
//	 *  保存传入的 ZjkBatchCns 对象（json格式的字符串）
//	 * @param zjkBatchCns 传入的zjkBatchCns对象
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value={ "/save.do"})
//	public   @ResponseBody String saveZjkBatchCns(@RequestBody String zjkBatchCns,
//			HttpServletRequest request, HttpServletResponse response){
//		return super._save(zjkBatchCns, request, response);
//	}
//
//	/**
//	 *  根据传入的id删除相应的 ZjkBatchCns 对象（json格式的字符串）
//	 * @param id 要删除的 ZjkBatchCns 对象 id值
//	 * @return 操作结果 一般是 {"success":"true"}
//	 */
//	@RequestMapping({ "/deleteById.do" })
//	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
//			HttpServletRequest request, HttpServletResponse response){
//		return super._deleteById(id, request, response);
//	}
//
//	/**
//	 *  根据传入的ids删除相应的 ZjkBatchCns 对象（json格式的字符串）
//	 * @param ids 要删除的 ZjkBatchCns 对象 id值,多个id之间用逗号(,)分隔
//	 * @return 操作结果 一般是 {"success":"true"}
//	 */
//	@RequestMapping({ "/deleteByIds.do" })
//	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
//			HttpServletRequest request, HttpServletResponse response){
//		return super._deleteByIds(ids, request, response);
//	}


	/**
	 * 查询所有已经建立的承诺书
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findCnsList")
	public ModelAndView findCnsList(@RequestBody String example,
									  HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		JSONObject jsonObject = Util.checkFromJson(example);
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			List<Map> listPpc = this.getZjkBatchCnsService().findCnsList(jsonObject);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
			JSONArray jsonArray = JSONArray.fromObject(listPpc,jsonConfig);
			returnToJs.setData(jsonArray);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkBatchCns/findCnsList.do 方法报错。"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
		return null;
	}


	/**
	 * 保存承诺书
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveCns")
	public ModelAndView saveCns(@RequestBody String json,
								  HttpServletRequest request,
								  HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		JSONObject jsonObject = Util.checkFromJson(json);
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			this.getZjkBatchCnsService().saveCns(jsonObject);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
			System.out.println("/ZjkBatchCns/saveCns.do 方法报错。"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
		return null;
	}



	/**
	 * 删除承诺书
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCns")
	public ModelAndView deleteCns(@RequestParam(value = "id") String id,
									HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			Boolean result = this.getZjkBatchCnsService().deleteCns(id);
			if(result){
				returnToJs.setData("删除成功");
			}else{
				returnToJs.setData("删除失败，已有批次使用该承诺书");
			}
			returnToJs.setSuccess(result);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
			System.out.println("/ZjkBatchCns/deleteCns.do 方法报错。"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
		return null;
	}


//	/**
//	 * 根据批次查询承诺书
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/findCnsListByBatchid.do")
//	public ModelAndView findCnsListByBatchid(@RequestParam(value = "batchid") String batchid,
//									HttpServletRequest request,
//									HttpServletResponse response) throws Exception {
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter writer = response.getWriter();
//		ReturnToJs returnToJs = new ReturnToJs();
//		try{
//			List<Map> list = this.getZjkBatchCnsService().findCnsListByBatchid(batchid);
//			returnToJs.setData(list);
//			returnToJs.setSuccess(true);
//		}catch (Exception e){
//			returnToJs.setSuccess(false);
//			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
//			System.out.println("/ZjkBatchCns/findCnsListByBatchid.do 方法报错。"+Util.getExceptionAllinformation(e));
//		}
//		writer.write(JSONObject.fromObject(returnToJs).toString());
//		return null;
//	}



}
