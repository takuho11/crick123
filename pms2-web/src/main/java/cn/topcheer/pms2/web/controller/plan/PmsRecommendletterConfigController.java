/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2022-12-15 16:05:04
 *
 */
package cn.topcheer.pms2.web.controller.plan;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.plan.entity.PmsRecommendletterConfig;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.plan.service.impl.PmsRecommendletterConfigService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping({"/PmsRecommendletterConfig" })
public class PmsRecommendletterConfigController  extends GenericController<PmsRecommendletterConfig> {

	
	@Autowired
	PmsRecommendletterConfigService pmsRecommendletterConfigService;
	 
	
	/**
	 *  根据传入的id返回相应的PmsRecommendletterConfig 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsRecommendletterConfig 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 PmsRecommendletterConfig 对象（json格式的字符串）
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsRecommendletterConfig 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 PmsRecommendletterConfig 对象（json格式的字符串）
	 * @param pmsRecommendletterConfig 传入的pmsRecommendletterConfig对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String savePmsRecommendletterConfig(@RequestBody String pmsRecommendletterConfig,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(pmsRecommendletterConfig, request, response);
	}

	/**
	 *  根据传入的id删除相应的 PmsRecommendletterConfig 对象（json格式的字符串）
	 * @param id 要删除的 PmsRecommendletterConfig 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 PmsRecommendletterConfig 对象（json格式的字符串）
	 * @param ids 要删除的 PmsRecommendletterConfig 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}


	/**
	 *  根据批次id，保存数据
	 */
	@RequestMapping(value = "/saveRecommendletterConfig.do")
	public @ResponseBody ReturnToJs saveRecommendletterConfig(@RequestBody JSONObject json){
		return ReturnToJs.success(this.pmsRecommendletterConfigService.saveRecommendletterConfig(json));
	}


	/**
	 *  根据批次id查配置信息
	 */
	@RequestMapping(value = "/findLetterConfigByBatchid.do")
	public @ResponseBody ReturnToJs findLetterConfigByBatchid(@RequestParam(value="batchid") String batchid){
		return ReturnToJs.success(this.pmsRecommendletterConfigService.findLetterConfigByBatchid(batchid));
	}
	
}
