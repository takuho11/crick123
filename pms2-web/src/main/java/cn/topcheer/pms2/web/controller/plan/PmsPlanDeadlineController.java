/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024年3月13日 下午2:57:24
 *
 */
package cn.topcheer.pms2.web.controller.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.topcheer.pms2.api.plan.entity.*;
import cn.topcheer.pms2.biz.plan.service.impl.*;

@Controller
@RequestMapping({"/PmsPlanDeadline" })
public class PmsPlanDeadlineController  extends GenericController<PmsPlanDeadline> {
	 	 
	
	public PmsPlanDeadlineController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(PmsPlanDeadline.class.getName(),"[id],[type],[mainid],[sourceid],[savedate],[updatelasttime],[seq],[memo],[batch_id],[enable],[code],[description],[deadline],[message]"); 
		PS_SET_SIMPLE.put(PmsPlanDeadline.class.getName(), "[id],[type],[mainid],[sourceid],[savedate],[updatelasttime],[seq],[memo],[batch_id],[enable],[code],[description],[deadline],[message]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public PmsPlanDeadlineService getPmsPlanDeadlineService()
	{
		return (PmsPlanDeadlineService) this.getGenericService();
	}
	
	@Autowired
	private void setPmsPlanDeadlineService(PmsPlanDeadlineService service)
	{
		this.setGenericService(service);
	}


	/**
	 *  根据传入的id返回相应的PmsPlanDeadline list
	 * @param id
	 * @param type
	 */
	@ApiOperation(value = "查询-根据batchid获取deadline数据", notes = "查询-根据batchid获取deadline数据")
	@PostMapping({ "/findByBatchId.do" })
	public @ResponseBody ReturnToJs findByBatchId(@RequestParam(value="id") String id,@RequestParam String type,
											 HttpServletRequest request, HttpServletResponse response){
		return ReturnToJs.success(getPmsPlanDeadlineService().findByBatchId(id, type));
	}

	/**
	 * 根据传入的id返回相应的PmsPlanDeadline
	 * @param id
	 */
	@ApiOperation(value = "根据传入的id返回相应的PmsPlanDeadline", notes = "根据传入的id返回相应的PmsPlanDeadline")
	@PostMapping({ "/findById.do" })
	public @ResponseBody ReturnToJs findById(@RequestParam(value="id") String id,
												  HttpServletRequest request, HttpServletResponse response){
		return ReturnToJs.success(getPmsPlanDeadlineService().findById(id));
	}
	
	/**
	 *  根据传入的id返回相应的 PmsPlanDeadline 对象（json格式的字符串）
	 * @param example
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsPlanDeadline 对象（json格式，字符串形式）
	 */
	@PostMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 PmsPlanDeadline 对象（json格式的字符串）
	 * @param json 传入的pmsPlanDeadline对象
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "保存-批量保存batch的时间限制配置deadline", notes = "保存-批量保存batch的时间限制配置deadline")
	@PostMapping(value={ "/save.do"})
	public   @ResponseBody ReturnToJs savePmsPlanDeadline(@RequestBody String json,
			HttpServletRequest request, HttpServletResponse response){
		return getPmsPlanDeadlineService().savePmsPlanDeadline(json);
	}

	/**
	 *  根据传入的id逻辑删除相应的 PmsPlanDeadline 对象（json格式的字符串）
	 * @param id 要删除的 PmsPlanDeadline 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@ApiOperation(value = "删除-逻辑删除单个deadline", notes = "删除-逻辑删除单个deadline")
	@PostMapping({ "/deleteById.do" })
	public @ResponseBody ReturnToJs deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){
		return getPmsPlanDeadlineService().deleteByIdN(id);
	}
	
	/**
	 *  根据传入的ids逻辑删除相应的 PmsPlanDeadline 对象（json格式的字符串）
	 * @param json
	 * @return 操作结果
	 */
	@ApiOperation(value = "删除-批量逻辑删除deadline", notes = "删除-批量逻辑删除deadline")
	@PostMapping({ "/deleteByIds.do" })
	public @ResponseBody ReturnToJs deleteByIdsN(@RequestBody String json,
			HttpServletRequest request, HttpServletResponse response){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return getPmsPlanDeadlineService().deleteByIdsN(jsonObject.getString("ids"),jsonObject.getString("type"));
	}

	/**
	 * 同步大批次的时间配置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "同步-同步plan的时间限制配置", notes = "同步-同步plan的时间限制配置")
	@PostMapping("/copyPlanProjectConfig")
	@ResponseBody
	public ReturnToJs copyPlanProjectConfig(@RequestParam(value="id") String id){
		return getPmsPlanDeadlineService().copyPlanProjectConfig(id);
	}
	
}
