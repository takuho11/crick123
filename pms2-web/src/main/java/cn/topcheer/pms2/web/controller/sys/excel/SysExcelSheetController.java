/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-9-24 11:57:03
 *
 */
package cn.topcheer.pms2.web.controller.sys.excel;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.sys.excel.SysExcelSheet;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.sys.excel.SysExcelSheetService;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import io.swagger.annotations.Api;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@Api(value = "系统excelsheet控制器", tags = "excel-sheet控制器")
@RequestMapping({"/SysExcelSheet" })
public class SysExcelSheetController  extends GenericController<SysExcelSheet> {
	 	 
	
	public SysExcelSheetController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(SysExcelSheet.class.getName(),"[id],[excelid],[sheetname],[datasourcetype],[datasourcesql],[datasourcemethodservice],[datasourcemethodname],[seq],[memo]");
		PS_SET_SIMPLE.put(SysExcelSheet.class.getName(), "[id],[excelid],[sheetname],[datasourcetype],[datasourcesql],[datasourcemethodservice],[datasourcemethodname],[seq],[memo]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public SysExcelSheetService getSysExcelSheetService()
	{
		return (SysExcelSheetService) this.getGenericService();
	}
	
	@Autowired
	private void setSysExcelSheetService(SysExcelSheetService service)
	{
		this.setGenericService(service);
	}
    @Autowired
    private SysExcelSheetService sysExcelSheetService;
    @Autowired
    private SysOperationrecordService sysOperationrecordService;
	
	/**
	 *  根据传入的id返回相应的SysExcelSheet 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysExcelSheet 对象（json格式，字符串形式）
	 */
	@PostMapping({ "/findById.do" })
	public @ResponseBody
	String findById(@RequestParam(value="id") String id, @RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
					HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 SysExcelSheet 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysExcelSheet 对象（json格式，字符串形式）
	 */
	@PostMapping({ "/findByExample.do" })
	public @ResponseBody
	String findByExample(@RequestParam(value="example",required=false) String example, @RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
						 HttpServletRequest request, HttpServletResponse response){
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 SysExcelSheet 对象（json格式的字符串）
	 * @param sysExcelSheet 传入的sysExcelSheet对象
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping(value={ "/save.do"})
	public   @ResponseBody
	String saveSysExcelSheet(@RequestBody String sysExcelSheet,
							 HttpServletRequest request, HttpServletResponse response){
		return super._save(sysExcelSheet, request, response);
	}

	/**
	 *  根据传入的id删除相应的 SysExcelSheet 对象（json格式的字符串）
	 * @param id 要删除的 SysExcelSheet 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@PostMapping({ "/deleteById.do" })
	public @ResponseBody
	String deleteById(@RequestParam(value="id") String id,
					  HttpServletRequest request, HttpServletResponse response){
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 SysExcelSheet 对象（json格式的字符串）
	 * @param ids 要删除的 SysExcelSheet 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@PostMapping({ "/deleteByIds.do" })
	public @ResponseBody
	String deleteByIds(@RequestParam(value="ids") String ids,
					   HttpServletRequest request, HttpServletResponse response){
		return super._deleteByIds(ids, request, response); 
	}
    /**
     * 【浙江系统】  --- 保存修改方法
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelSheetSave.do")
    public void sysExcelSheetSave(@RequestBody String example,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONObject json = Util.checkFromJson(example);
            BladeUser jsonUser = AuthUtil.getUser();
            String sysUserId = jsonUser.getUserId();
            returnToJs = this.sysExcelSheetService.sysExcelSheetSave(json,returnToJs);
            this.sysOperationrecordService.commonSaveOperation(request,sysUserId,"sheet表新增修改","用户id:"+sysUserId+"，其他内容："+jsonUser.toString());
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcelSheet/sysExcelSheetSave.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【浙江系统】  --- 查询方法
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelSheetFind.do")
    public void sysExcelSheetFind(@RequestBody String example,
                             HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONObject json = Util.checkFromJson(example);
            JSONArray array = this.sysExcelSheetService.sysExcelSheetFind(json);
            returnToJs.setData(array);
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcelSheet/sysExcelSheetFind.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【浙江系统】  --- 删除方法
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelSheetDelete.do")
    public void sysExcelSheetDelete(@RequestBody String example,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONObject json = Util.checkFromJson(example);
            BladeUser jsonUser =  AuthUtil.getUser(); //SessionUtil.getCurrentSecurityUser(request);
            String sysUserId = jsonUser.getUserId();
            returnToJs = this.sysExcelSheetService.sysExcelSheetDelete(json,returnToJs);
            this.sysOperationrecordService.commonSaveOperation(request,sysUserId,"sheet表删除","用户id:"+sysUserId+"，其他内容："+jsonUser.toString());
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcelSheet/sysExcelSheetDelete.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
}
