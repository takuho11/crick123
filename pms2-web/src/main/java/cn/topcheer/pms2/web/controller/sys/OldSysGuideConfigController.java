/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2015-12-25 11:58:20
 *
 */
package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.SysGuideConfig;
import cn.topcheer.pms2.biz.sys.OldSysGuideConfigService;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping({"/OldSysGuideConfig" })
public class OldSysGuideConfigController extends GenericController<SysGuideConfig> {

	SysOperationrecordService sysOperationrecordService;
	public OldSysGuideConfigController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(SysGuideConfig.class.getName(),"[id],[guidename],[projectovervies],[explanation],[memo],[sort]");
		PS_SET_SIMPLE.put(SysGuideConfig.class.getName(), "[id],[guidename],[projectovervies],[explanation],[memo],[sort]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public OldSysGuideConfigService getSysGuideConfigService()
	{
		return (OldSysGuideConfigService) this.getGenericService();
	}
	
	@Autowired
	private void setSysGuideConfigService(OldSysGuideConfigService service)
	{
		this.setGenericService(service);
	}
	@Autowired
	private SysUserService sysUserService;
	
	 
	
	/**
	 *  根据传入的id返回相应的SysGuideConfig 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysGuideConfig 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 SysGuideConfig 对象（json格式的字符串）
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysGuideConfig 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	
	@RequestMapping({ "/findByExampleBody.do" })
	public @ResponseBody String findByExampleBody(@RequestBody String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	
	
	/**
	 *  保存传入的 SysGuideConfig 对象（json格式的字符串）
	 * @param sysGuideConfig 传入的sysGuideConfig对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String saveSysGuideConfig(@RequestBody String sysGuideConfig,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(sysGuideConfig, request, response);
	}

	
	
	
	
	/**
	 *  根据传入的id删除相应的 SysGuideConfig 对象（json格式的字符串）
	 * @param id 要删除的 SysGuideConfig 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 SysGuideConfig 对象（json格式的字符串）
	 * @param ids 要删除的 SysGuideConfig 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}


	/**
	 *  保存数据
	 */
    @RequestMapping(value={ "/saveGuide.do"})
    public void saveGuide(@RequestBody String example,
						  HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = Util.checkFromJson(example);
        ReturnToJs returnToJs = new ReturnToJs();

        try{
            this.getSysGuideConfigService().saveGuide(jsonObject);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+ Util.linkNumber);
            System.out.println("/SysGuideConfig/saveGuide.do 方法报错。");
//            log.error("/SysGuideConfig/saveGuide.do方法报错"+Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }

    /**
     *  获取数据--根据批次id
     */
    @RequestMapping({ "/initGuide.do" })
    public void initGuide(@RequestParam(value="batchid") String batchid,
								   HttpServletRequest request,HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();

        try{
            List<SysGuideConfig> list = this.getSysGuideConfigService().initGuide(batchid);
			returnToJs.setSuccess(true);
            returnToJs.setData(list);
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+ Util.linkNumber);
            System.out.println("/SysGuideConfig/initGuide.do 方法报错。");
//            log.error("/SysGuideConfig/initGuide.do方法报错"+Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


	/**
	 *  获取数据--根据批次id
	 */
	@RequestMapping({ "/judgeGuide.do" })
	public void judgeGuide(@RequestBody String example,
						  HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try{
			JSONObject jsonObject = Util.checkFromJson(example);
			SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
			JSONObject resObj = this.getSysGuideConfigService().judgeGuide(jsonObject,user);
			if(resObj.getBoolean("success")){
				returnToJs.setSuccess(true);
			}else{
				returnToJs.setSuccess(false);
				returnToJs.setErrMsg(resObj.get("reason")+"");
			}
		} catch (Exception e) {
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+ Util.linkNumber);
			System.out.println("/SysGuideConfig/judgeGuide.do 方法报错。");
//			log.error("/SysGuideConfig/judgeGuide.do方法报错"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


}
