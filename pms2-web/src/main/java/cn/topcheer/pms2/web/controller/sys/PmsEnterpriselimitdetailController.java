/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-4-29 14:06:11
 *
 */
package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.sys.PmsEnterpriselimitdetail;
import cn.topcheer.pms2.biz.sys.PmsEnterpriselimitdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping({"/PmsEnterpriselimitdetail" })
public class PmsEnterpriselimitdetailController  extends GenericController<PmsEnterpriselimitdetail> {
	 	 
	
	public PmsEnterpriselimitdetailController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(PmsEnterpriselimitdetail.class.getName(),"[id],[batchid],[projectbaseid],[createdate],[enterpriseid],[state]"); 
		PS_SET_SIMPLE.put(PmsEnterpriselimitdetail.class.getName(), "[id],[batchid],[projectbaseid],[createdate],[enterpriseid],[state]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public PmsEnterpriselimitdetailService getPmsEnterpriselimitdetailService()
	{
		return (PmsEnterpriselimitdetailService) this.getGenericService();
	}
	
	@Autowired
	private void setPmsEnterpriselimitdetailService(PmsEnterpriselimitdetailService service)
	{
		this.setGenericService(service);
	}
	 
	
	/**
	 *  根据传入的id返回相应的PmsEnterpriselimitdetail 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsEnterpriselimitdetail 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 PmsEnterpriselimitdetail 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsEnterpriselimitdetail 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 PmsEnterpriselimitdetail 对象（json格式的字符串）
	 * @param pmsEnterpriselimitdetail 传入的pmsEnterpriselimitdetail对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String savePmsEnterpriselimitdetail(@RequestBody String pmsEnterpriselimitdetail,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(pmsEnterpriselimitdetail, request, response);
	}

	/**
	 *  根据传入的id删除相应的 PmsEnterpriselimitdetail 对象（json格式的字符串）
	 * @param id 要删除的 PmsEnterpriselimitdetail 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 PmsEnterpriselimitdetail 对象（json格式的字符串）
	 * @param ids 要删除的 PmsEnterpriselimitdetail 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}
	
}
