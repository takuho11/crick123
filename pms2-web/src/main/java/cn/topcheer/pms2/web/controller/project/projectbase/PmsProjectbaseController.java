/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-3-20 9:27:40
 *
 */
package cn.topcheer.pms2.web.controller.project.projectbase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;

import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.biz.project.service.impl.*;

@Controller
@RequestMapping({"/PmsProjectbase" })
public class PmsProjectbaseController  extends GenericController<PmsProjectbase> {
	 	 
	
	public PmsProjectbaseController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(PmsProjectbase.class.getName(),"[id],[enterpriseid],[declarantid],[planprojectid],[planprojectbatchid],[minicurrentstate],[minicurrentstateid],[applicationno],[projectname],[planprojecttype],[projectleader],[mainorganizers],[submitdate],[belonglabid],[belonglab],[tjdwback],[allowsbsh],[flowpointupdatetime],[citycasemanagementid],[citycasemanagement],[countycasemanagementid],[countycasemanagement],[casemanagementnature],[startdate],[enddate],[projectsumforself],[projectsumforgov],[projectsumforother],[projectsumtotal],[projectapplytype],[projecttype],[category],[isaddwatermark],[contractno],[supportdirectionid],[supportdirection],[lxflag],[gsdate],[researchdeadline],[researchdirection],[keywordszw],[keywordsyw],[researchdomain],[projectdescription],[projectconnectivity],[subjectcode],[is_deleted],[guide_domain],[tech_source],[project_act_type],[innovate_type],[application_type],[fzr_mobile],[type],[mainid],[sourceid],[savedate],[updatelasttime],[seq],[memo],[ishistory],[projectbasename],[xmzgid],[projectlibrary],[movelibdate],[supportdirectionchildid],[supportdirectionchild],[id],[enterpriseid],[declarantid],[planprojectid],[planprojectbatchid],[minicurrentstate],[minicurrentstateid],[applicationno],[projectname],[planprojecttype],[projectleader],[mainorganizers],[submitdate],[belonglabid],[belonglab],[tjdwback],[allowsbsh],[flowpointupdatetime],[citycasemanagementid],[citycasemanagement],[countycasemanagementid],[countycasemanagement],[casemanagementnature],[startdate],[enddate],[projectsumforself],[projectsumforgov],[projectsumforother],[projectsumtotal],[projectapplytype],[projecttype],[category],[isaddwatermark],[contractno],[supportdirectionid],[supportdirection],[lxflag],[gsdate],[researchdeadline],[researchdirection],[keywordszw],[keywordsyw],[researchdomain],[projectdescription],[projectconnectivity],[subjectcode],[is_deleted],[guide_domain],[tech_source],[project_act_type],[innovate_type],[application_type],[fzr_mobile],[type],[mainid],[sourceid],[savedate],[updatelasttime],[seq],[memo],[mini_current_task_def_key],[mini_current_process_def_key],[original_countycasemanagementid],[is_secret],[is_suspected_ethics],[project_focus_area],[guide_theme],[guide_other]"); 
		PS_SET_SIMPLE.put(PmsProjectbase.class.getName(), "[id],[enterpriseid],[declarantid],[planprojectid],[planprojectbatchid],[minicurrentstate],[minicurrentstateid],[applicationno],[projectname],[planprojecttype],[projectleader],[mainorganizers],[submitdate],[belonglabid],[belonglab],[tjdwback],[allowsbsh],[flowpointupdatetime],[citycasemanagementid],[citycasemanagement],[countycasemanagementid],[countycasemanagement],[casemanagementnature],[startdate],[enddate],[projectsumforself],[projectsumforgov],[projectsumforother],[projectsumtotal],[projectapplytype],[projecttype],[category],[isaddwatermark],[contractno],[supportdirectionid],[supportdirection],[lxflag],[gsdate],[researchdeadline],[researchdirection],[keywordszw],[keywordsyw],[researchdomain],[projectdescription],[projectconnectivity],[subjectcode],[is_deleted],[guide_domain],[tech_source],[project_act_type],[innovate_type],[application_type],[fzr_mobile],[type],[mainid],[sourceid],[savedate],[updatelasttime],[seq],[memo],[ishistory],[projectbasename],[xmzgid],[projectlibrary],[movelibdate],[supportdirectionchildid],[supportdirectionchild],[id],[enterpriseid],[declarantid],[planprojectid],[planprojectbatchid],[minicurrentstate],[minicurrentstateid],[applicationno],[projectname],[planprojecttype],[projectleader],[mainorganizers],[submitdate],[belonglabid],[belonglab],[tjdwback],[allowsbsh],[flowpointupdatetime],[citycasemanagementid],[citycasemanagement],[countycasemanagementid],[countycasemanagement],[casemanagementnature],[startdate],[enddate],[projectsumforself],[projectsumforgov],[projectsumforother],[projectsumtotal],[projectapplytype],[projecttype],[category],[isaddwatermark],[contractno],[supportdirectionid],[supportdirection],[lxflag],[gsdate],[researchdeadline],[researchdirection],[keywordszw],[keywordsyw],[researchdomain],[projectdescription],[projectconnectivity],[subjectcode],[is_deleted],[guide_domain],[tech_source],[project_act_type],[innovate_type],[application_type],[fzr_mobile],[type],[mainid],[sourceid],[savedate],[updatelasttime],[seq],[memo],[mini_current_task_def_key],[mini_current_process_def_key],[original_countycasemanagementid],[is_secret],[is_suspected_ethics],[project_focus_area],[guide_theme],[guide_other]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public PmsProjectbaseService getPmsProjectbaseService()
	{
		return (PmsProjectbaseService) this.getGenericService();
	}
	
	@Autowired
	private void setPmsProjectbaseService(PmsProjectbaseService service)
	{
		this.setGenericService(service);
	}
	 
	
	/**
	 *  根据传入的id返回相应的PmsProjectbase 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsProjectbase 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 PmsProjectbase 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsProjectbase 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 PmsProjectbase 对象（json格式的字符串）
	 * @param pmsProjectbase 传入的pmsProjectbase对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String savePmsProjectbase(@RequestBody String pmsProjectbase,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(pmsProjectbase, request, response);
	}

	/**
	 *  根据传入的id删除相应的 PmsProjectbase 对象（json格式的字符串）
	 * @param id 要删除的 PmsProjectbase 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 PmsProjectbase 对象（json格式的字符串）
	 * @param ids 要删除的 PmsProjectbase 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}
	
}
