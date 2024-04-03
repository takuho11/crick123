/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-4-27 14:41:48
 *
 */
package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.SysParamsTitle;
import cn.topcheer.pms2.biz.sys.impl.SysParamsTitleService;
import cn.topcheer.pms2.biz.utils.Util;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping({"/SysParamsTitle" })
public class SysParamsTitleController extends GenericController<SysParamsTitle> {
	 	 
	
	public SysParamsTitleController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(SysParamsTitle.class.getName(),"[id],[parentid],[name],[value],[code],[applytype],[seq],[memo]"); 
		PS_SET_SIMPLE.put(SysParamsTitle.class.getName(), "[id],[parentid],[name],[value],[code],[applytype],[seq],[memo]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public SysParamsTitleService getSysParamsTitleService()
	{
		return (SysParamsTitleService) this.getGenericService();
	}
	
	@Autowired
	private void setSysParamsTitleService(SysParamsTitleService service)
	{
		this.setGenericService(service);
	}
	 
	
	/**
	 *  根据传入的id返回相应的SysParamsTitle 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysParamsTitle 对象（json格式，字符串形式）
	 */
	@PostMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 SysParamsTitle 对象（json格式的字符串）
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysParamsTitle 对象（json格式，字符串形式）
	 */
	@PostMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 SysParamsTitle 对象（json格式的字符串）
	 * @param sysParamsTitle 传入的sysParamsTitle对象
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping(value={ "/save.do"})
	public   @ResponseBody String saveSysParamsTitle(@RequestBody String sysParamsTitle,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(sysParamsTitle, request, response);
	}

	/**
	 *  根据传入的id删除相应的 SysParamsTitle 对象（json格式的字符串）
	 * @param id 要删除的 SysParamsTitle 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@PostMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 SysParamsTitle 对象（json格式的字符串）
	 * @param ids 要删除的 SysParamsTitle 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@PostMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}


	/**
	 * 【职称字典】---初始化职称系列
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping("/initData.do")
	public void initData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			List<Map> list = this.getSysParamsTitleService().initData();
			returnToJs.setSuccess(true);
			returnToJs.setData(list);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+ Util.linkNumber);
			log.error("/SysParamsTitle/initData.do方法报错"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 【职称字典】---根据职称系列获取他的子级
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping("/initDataByparentvalue.do")
	public void initDataByparentvalue(@RequestBody String example,
									  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			List<Map> list = this.getSysParamsTitleService().initDataByparentvalue(json.get("name")+"");
			returnToJs.setSuccess(true);
			returnToJs.setData(list);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+Util.linkNumber);
			log.error("/SysParamsTitle/initDataByparentvalue.do方法报错"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}
}
