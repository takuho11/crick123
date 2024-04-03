/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-1-4 18:13:07
 *
 */
package cn.topcheer.pms2.web.controller.bi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.bi.BiCBi;
import cn.topcheer.pms2.biz.bi.BiCBiService;
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

import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.biz.project.service.impl.*;

@Controller
@RequestMapping({"/BiCBi" })
public class BiCBiController  extends GenericController<BiCBi> {
	 	 
	
	public BiCBiController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(BiCBi.class.getName(),"[id],[carriername],[carrierrank],[belongfield],[address],[leadername],[leadermobile],[leaderphone],[leaderemail],[researchdirections],[summary],[province],[city],[county],[awardunit],[recognizedate],[subjectcode],[subjectname],[agencymembernum],[equipmentnum],[equipmentprice],[carriertype],[type],[mainid],[sourceid],[seq],[prp_code],[develop_num],[org_shxy_no],[dq_date],[sbdw_name],[sbdw_nature],[sbdw_area_name],[workers_total],[rd_psn_num],[allyear_output],[new_product_output],[sales_value],[new_product_sales_value],[gjsfw_value],[export_value],[new_product_export_value],[yjkfjftr_total],[gdzczz],[gcsyyf],[bgyf],[yqsbzbzz],[update_time],[area_name],[stat_year],[unit_name],[starttime],[endtime],[jg_type],[jg_nature],[devlop_type],[wy_instrument_price],[national_platform],[czzj_prp_num],[czzj_all_amt],[patent_all_num],[invent_patent_num],[pct_patent_num],[valid_invent_patent_num],[qtzdbz_num],[cyzdbz_num],[kjjl_num],[jsnxcpsl_num],[snjg_all_income],[snjg_all_expend],[kjcgzh_income],[rd_expend],[technology_income],[jsncgzh_income],[take_enterprise_num],[enterprise_income],[incubate_enterprise_num],[incubate_enterprise_income],[take_enterprise_gq_num],[incu_enterprise_gq_num],[service_enterprise_num],[is_qtslcycxlm],[is_qtclhyxh],[scientific_place],[sys_type],[valid_date],[tech_name],[industry_name],[is_animal_name],[province_amt],[ky_instrument_price],[cy_org_names],[fzr_card_code],[ss_city_name],[yyzt_org_name],[parent_org_name],[square],[zz_amt],[ff_amt],[zf_org_num],[by_org_num],[org_num],[orgname],[savedate],[updatelasttime],[carriertype_qt],[wqcheck]"); 
		PS_SET_SIMPLE.put(BiCBi.class.getName(), "[id],[carriername],[carrierrank],[belongfield],[address],[leadername],[leadermobile],[leaderphone],[leaderemail],[researchdirections],[summary],[province],[city],[county],[awardunit],[recognizedate],[subjectcode],[subjectname],[agencymembernum],[equipmentnum],[equipmentprice],[carriertype],[type],[mainid],[sourceid],[seq],[prp_code],[develop_num],[org_shxy_no],[dq_date],[sbdw_name],[sbdw_nature],[sbdw_area_name],[workers_total],[rd_psn_num],[allyear_output],[new_product_output],[sales_value],[new_product_sales_value],[gjsfw_value],[export_value],[new_product_export_value],[yjkfjftr_total],[gdzczz],[gcsyyf],[bgyf],[yqsbzbzz],[update_time],[area_name],[stat_year],[unit_name],[starttime],[endtime],[jg_type],[jg_nature],[devlop_type],[wy_instrument_price],[national_platform],[czzj_prp_num],[czzj_all_amt],[patent_all_num],[invent_patent_num],[pct_patent_num],[valid_invent_patent_num],[qtzdbz_num],[cyzdbz_num],[kjjl_num],[jsnxcpsl_num],[snjg_all_income],[snjg_all_expend],[kjcgzh_income],[rd_expend],[technology_income],[jsncgzh_income],[take_enterprise_num],[enterprise_income],[incubate_enterprise_num],[incubate_enterprise_income],[take_enterprise_gq_num],[incu_enterprise_gq_num],[service_enterprise_num],[is_qtslcycxlm],[is_qtclhyxh],[scientific_place],[sys_type],[valid_date],[tech_name],[industry_name],[is_animal_name],[province_amt],[ky_instrument_price],[cy_org_names],[fzr_card_code],[ss_city_name],[yyzt_org_name],[parent_org_name],[square],[zz_amt],[ff_amt],[zf_org_num],[by_org_num],[org_num],[orgname],[savedate],[updatelasttime],[carriertype_qt],[wqcheck]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public BiCBiService getBiCBiService()
	{
		return (BiCBiService) this.getGenericService();
	}
	
	@Autowired
	private void setBiCBiService(BiCBiService service)
	{
		this.setGenericService(service);
	}
	 
	
	/**
	 *  根据传入的id返回相应的BiCBi 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return BiCBi 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 BiCBi 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return BiCBi 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 BiCBi 对象（json格式的字符串）
	 * @param biCBi 传入的biCBi对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String saveBiCBi(@RequestBody String biCBi,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(biCBi, request, response);
	}

	/**
	 *  根据传入的id删除相应的 BiCBi 对象（json格式的字符串）
	 * @param id 要删除的 BiCBi 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 BiCBi 对象（json格式的字符串）
	 * @param ids 要删除的 BiCBi 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}
	
}
