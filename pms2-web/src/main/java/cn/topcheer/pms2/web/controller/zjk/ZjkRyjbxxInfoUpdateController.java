/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-11-27 17:57:05
 *
 */
package cn.topcheer.pms2.web.controller.zjk;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxInfoUpdate;
import cn.topcheer.pms2.biz.zjk.ZjkRyjbxxInfoUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping({"/ZjkRyjbxxInfoUpdate" })
public class ZjkRyjbxxInfoUpdateController  extends GenericController<ZjkRyjbxxInfoUpdate> {


	public ZjkRyjbxxInfoUpdateController() {
		// TODO Auto-generated constructor stub

		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */

		PS_SET.put(ZjkRyjbxxInfoUpdate.class.getName(),"[id],[zjkryjbxxupdateid],[finance],[finance_subsenior],[finance_highsenior],[finance_lustrum],[finance_universities_leader],[finance_company_leader],[management],[management_company_senior],[management_sciencepark_senior],[management_organization_senior],[management_laboratory_senior],[management_consulting_senior],[technology],[technology_senior_certificate],[technology_subject_leader],[technology_technology_award],[technology_talent],[technology_technical_backbone],[technology_scholars],[law],[law_legal_experts],[law_lawyer],[law_partner],[law_professional],[law_professional_civil_commercial],[law_professional_company],[law_professional_financial],[law_professional_realty],[law_professional_intellectual_property],[law_professional_international],[law_professional_criminal],[financial],[financial_angel_investment],[financial_senior],[professional],[professional_investment],[professional_bank],[professional_brokers],[professional_insurance],[investment],[investment_angel],[investment_vc],[investment_pe],[investment_other],[talent],[talent_academician],[talent_engineering_academy],[talent_expert],[major],[major_chief_expert],[major_subject_expert],[major_project_leader],[major_chief_scientist],[major_jjzs],[major_jjfzs],[major_zxxmfzr],[major_subject_emcee],[major_jjcxqtxm_leader],[major_cjxz_leader],[major_xmfzr],[major_ktfzr],[major_jjzdxm_emcee],[reward],[reward_nbej],[reward_zgkxjsj],[reward_zrkxj],[reward_jsfmj],[reward_kxjsjbj],[reward_gjkxjshzj],[reward_tsjtzj],[reward_gdszrkxj],[reward_gdsjsfmj],[reward_gdsjsjbj],[national],[national_cjxztpjs],[national_cjxzjzjs],[national_cjxzqnxz],[national_jcqnkxjjxm],[national_xsjyxrczcjh],[national_bqwgjjrxz],[national_kjcxljrc],[national_kjcxcyrc],[national_jcrc],[national_ljrc],[national_qnbjrc],[provincial],[provincial_zkybrjh],[provincial_zjjcrc],[provincial_zjcxljrc],[provincial_zjcyljrc],[provincial_zjqnbjrc],[provincial_zjtddtr],[provincial_zjtdhxcy],[provincial_gdjcqnxm],[provincial_gdtddtr],[provincial_gdtdhxcy],[provincial_gdjcrc],[provincial_gdkjcxljrc],[provincial_gdkjcyljrc],[provincial_gdqnbjrc],[provincial_ydxtdhxcy],[provincial_ydxtddtr],[other],[other_qksgr],[other_zdkxdtr],[other_zdsysfzr],[other_xkdtr],[other_jybsysfzr],[other_sjsysfzr]");
		PS_SET_SIMPLE.put(ZjkRyjbxxInfoUpdate.class.getName(), "[id],[zjkryjbxxupdateid],[finance],[finance_subsenior],[finance_highsenior],[finance_lustrum],[finance_universities_leader],[finance_company_leader],[management],[management_company_senior],[management_sciencepark_senior],[management_organization_senior],[management_laboratory_senior],[management_consulting_senior],[technology],[technology_senior_certificate],[technology_subject_leader],[technology_technology_award],[technology_talent],[technology_technical_backbone],[technology_scholars],[law],[law_legal_experts],[law_lawyer],[law_partner],[law_professional],[law_professional_civil_commercial],[law_professional_company],[law_professional_financial],[law_professional_realty],[law_professional_intellectual_property],[law_professional_international],[law_professional_criminal],[financial],[financial_angel_investment],[financial_senior],[professional],[professional_investment],[professional_bank],[professional_brokers],[professional_insurance],[investment],[investment_angel],[investment_vc],[investment_pe],[investment_other],[talent],[talent_academician],[talent_engineering_academy],[talent_expert],[major],[major_chief_expert],[major_subject_expert],[major_project_leader],[major_chief_scientist],[major_jjzs],[major_jjfzs],[major_zxxmfzr],[major_subject_emcee],[major_jjcxqtxm_leader],[major_cjxz_leader],[major_xmfzr],[major_ktfzr],[major_jjzdxm_emcee],[reward],[reward_nbej],[reward_zgkxjsj],[reward_zrkxj],[reward_jsfmj],[reward_kxjsjbj],[reward_gjkxjshzj],[reward_tsjtzj],[reward_gdszrkxj],[reward_gdsjsfmj],[reward_gdsjsjbj],[national],[national_cjxztpjs],[national_cjxzjzjs],[national_cjxzqnxz],[national_jcqnkxjjxm],[national_xsjyxrczcjh],[national_bqwgjjrxz],[national_kjcxljrc],[national_kjcxcyrc],[national_jcrc],[national_ljrc],[national_qnbjrc],[provincial],[provincial_zkybrjh],[provincial_zjjcrc],[provincial_zjcxljrc],[provincial_zjcyljrc],[provincial_zjqnbjrc],[provincial_zjtddtr],[provincial_zjtdhxcy],[provincial_gdjcqnxm],[provincial_gdtddtr],[provincial_gdtdhxcy],[provincial_gdjcrc],[provincial_gdkjcxljrc],[provincial_gdkjcyljrc],[provincial_gdqnbjrc],[provincial_ydxtdhxcy],[provincial_ydxtddtr],[other],[other_qksgr],[other_zdkxdtr],[other_zdsysfzr],[other_xkdtr],[other_jybsysfzr],[other_sjsysfzr]");
		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);

	}

	public ZjkRyjbxxInfoUpdateService getZjkRyjbxxInfoUpdateService()
	{
		return (ZjkRyjbxxInfoUpdateService) this.getGenericService();
	}

	@Autowired
	private void setZjkRyjbxxInfoUpdateService(ZjkRyjbxxInfoUpdateService service)
	{
		this.setGenericService(service);
	}


	/**
	 *  根据传入的id返回相应的ZjkRyjbxxInfoUpdate 对象（json格式的字符串）
	 * @param id
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return ZjkRyjbxxInfoUpdate 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
										 HttpServletRequest request, HttpServletResponse response){

		return super._findById(id, fetchParent, request, response);

	}

	/**
	 *  根据传入的id返回相应的 ZjkRyjbxxInfoUpdate 对象（json格式的字符串）
	 * @param id
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return ZjkRyjbxxInfoUpdate 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
											  HttpServletRequest request, HttpServletResponse response){
		return super._findByExample(example, fetchParent, request, response);
	}

	/**
	 *  保存传入的 ZjkRyjbxxInfoUpdate 对象（json格式的字符串）
	 * @param zjkRyjbxxInfoUpdate 传入的zjkRyjbxxInfoUpdate对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})
	public   @ResponseBody String saveZjkRyjbxxInfoUpdate(@RequestBody String zjkRyjbxxInfoUpdate,
														  HttpServletRequest request, HttpServletResponse response){
		return super._save(zjkRyjbxxInfoUpdate, request, response);
	}

	/**
	 *  根据传入的id删除相应的 ZjkRyjbxxInfoUpdate 对象（json格式的字符串）
	 * @param id 要删除的 ZjkRyjbxxInfoUpdate 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
										   HttpServletRequest request, HttpServletResponse response){
		return super._deleteById(id, request, response);
	}

	/**
	 *  根据传入的ids删除相应的 ZjkRyjbxxInfoUpdate 对象（json格式的字符串）
	 * @param ids 要删除的 ZjkRyjbxxInfoUpdate 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
											HttpServletRequest request, HttpServletResponse response){
		return super._deleteByIds(ids, request, response);
	}

}
