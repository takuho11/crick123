/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-2-22 16:50:10
 *
 */
package cn.topcheer.pms2.web.controller.subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;

import cn.topcheer.pms2.api.subject.*;
import cn.topcheer.pms2.biz.subject.*;

@Controller
@RequestMapping({"/PmsSubjectType" })
public class PmsSubjectTypeController  extends GenericController<PmsSubjectType> {
	 	 
	
	public PmsSubjectTypeController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(PmsSubjectType.class.getName(),"[id],[parentid],[code],[name],[seq],[type],[ywhy],[rank],[originalcode],[subjecttype],[xkflag],[parentcode],[cid]"); 
		PS_SET_SIMPLE.put(PmsSubjectType.class.getName(), "[id],[parentid],[code],[name],[seq],[type],[ywhy],[rank],[originalcode],[subjecttype],[xkflag],[parentcode],[cid]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public PmsSubjectTypeService getPmsSubjectTypeService()
	{
		return (PmsSubjectTypeService) this.getGenericService();
	}
	
	@Autowired
	private void setPmsSubjectTypeService(PmsSubjectTypeService service)
	{
		this.setGenericService(service);
	}
	 
	
	/**
	 *  根据传入的id返回相应的PmsSubjectType 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsSubjectType 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 PmsSubjectType 对象（json格式的字符串）
	 * @param
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsSubjectType 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 PmsSubjectType 对象（json格式的字符串）
	 * @param pmsSubjectType 传入的pmsSubjectType对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String savePmsSubjectType(@RequestBody String pmsSubjectType,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(pmsSubjectType, request, response);
	}

	/**
	 *  根据传入的id删除相应的 PmsSubjectType 对象（json格式的字符串）
	 * @param id 要删除的 PmsSubjectType 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 PmsSubjectType 对象（json格式的字符串）
	 * @param ids 要删除的 PmsSubjectType 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}

	/**
	 * 根据是否为财务学科获取相关的学科信息
	 * @param jsonObject
	 * @return
	 */
	@PostMapping({ "/findSubjectByXkflag" })
	public @ResponseBody ReturnToJs findSubjectByXkflag(@RequestBody JSONObject jsonObject) {
		List<Map> result = this.getPmsSubjectTypeService().findSubjectByXkflag(jsonObject);
		return ReturnToJs.success(result);
	}
}
