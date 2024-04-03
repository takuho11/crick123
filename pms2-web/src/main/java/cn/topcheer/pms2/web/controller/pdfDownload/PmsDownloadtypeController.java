/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-12-6 11:46:02
 *
 */
package cn.topcheer.pms2.web.controller.pdfDownload;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.pdfDownload.PmsDownloadtype;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.pdfDownload.impl.PmsDownloadtypeService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping({"/PmsDownloadtype" })
public class PmsDownloadtypeController extends GenericController<PmsDownloadtype> {
	 	 
	
	public PmsDownloadtypeController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(PmsDownloadtype.class.getName(),"[id],[type],[isbatch],[justdown],[openname],[modelname],[watername],[watermarkstyle],[opacity],[downloadtype],[generatetype],[idtype],[tmpwordfile],[tmppdffile],[methodname],[methodparamtype],[modeloraffixlist],[memo]"); 
		PS_SET_SIMPLE.put(PmsDownloadtype.class.getName(), "[id],[type],[isbatch],[justdown],[openname],[modelname],[watername],[watermarkstyle],[opacity],[downloadtype],[generatetype],[idtype],[tmpwordfile],[tmppdffile],[methodname],[methodparamtype],[modeloraffixlist],[memo]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public PmsDownloadtypeService getPmsDownloadtypeService()
	{
		return (PmsDownloadtypeService) this.getGenericService();
	}
	
	@Autowired
	private void setPmsDownloadtypeService(PmsDownloadtypeService service)
	{
		this.setGenericService(service);
	}
    @Autowired
    PmsDownloadtypeService pmsDownloadtypeService;


	/**
	 *  根据传入的id返回相应的PmsDownloadtype 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsDownloadtype 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 PmsDownloadtype 对象（json格式的字符串）
	 * @param example
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return PmsDownloadtype 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 PmsDownloadtype 对象（json格式的字符串）
	 * @param pmsDownloadtype 传入的pmsDownloadtype对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String savePmsDownloadtype(@RequestBody String pmsDownloadtype,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(pmsDownloadtype, request, response);
	}

	/**
	 *  根据传入的id删除相应的 PmsDownloadtype 对象（json格式的字符串）
	 * @param id 要删除的 PmsDownloadtype 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 PmsDownloadtype 对象（json格式的字符串）
	 * @param ids 要删除的 PmsDownloadtype 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}
    /**
     * 【黑龙江系统】  --- 单独下载配置保存修改方法
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/pmsdownSave.do")
    public void pmsDownloadtypeSave(@RequestBody String example,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONObject json = Util.checkFromJson(example);
            returnToJs = this.pmsDownloadtypeService.pmsDownloadtypeSave(json,returnToJs);
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
            System.out.println("/PmsDownLoad/printSearchData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【黑龙江系统】  --- 单独下载配置查询方法
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/pmsdownFind.do")
    public void pmsDownloadtypeFind(@RequestBody String example,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONObject json = Util.checkFromJson(example);
            JSONArray array = this.pmsDownloadtypeService.pmsDownloadtypeFind(json);
            returnToJs.setData(array);
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
            System.out.println("/PmsDownLoad/printSearchData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【黑龙江系统】  --- 单独下载配置初始化方法
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/pmsdownInit.do")
    public void pmsdownInit(
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{

            JSONArray array = this.pmsDownloadtypeService.pmsdownInit();
            returnToJs.setData(array);
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
            System.out.println("/PmsDownLoad/printSearchData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【黑龙江系统】  --- 单独下载配置删除方法
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/pmsdownDelete.do")
    public void pmsdownDelete(@RequestBody String example,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONObject json = Util.checkFromJson(example);
            returnToJs = this.pmsDownloadtypeService.pmsdownDelete(json,returnToJs);
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
            System.out.println("/PmsDownLoad/printSearchData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }

    /**
     * 【黑龙江系统】  --- pdf查看配置方法
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/pmsDownPdfInit.do")
    public void pmsDownPdfInit(@RequestParam(value="type") String type,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONArray array = this.pmsDownloadtypeService.pmsDownPdfInit(type);
            returnToJs.setData(array);
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
            System.out.println("/PmsDownLoad/printSearchData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }

    /**
     * 【黑龙江系统】  --- 单独下载配置删除方法
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/deletePdfPdfPz.do")
    public void deletePdfPdfPz(@RequestParam(value="id") String id,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            returnToJs = this.pmsDownloadtypeService.deletePdfPdfPz(id,returnToJs);
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
            System.out.println("/PmsDownLoad/printSearchData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


    /**
     * 【黑龙江系统】  --- 单独下载配置参数保存修改方法
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/saveMyPdfPz.do")
    public void saveMyPdfPz(@RequestParam(value="number") int number,@RequestBody String example,
                            HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONObject json = Util.checkFromJson(example);
            returnToJs = this.pmsDownloadtypeService.saveMyPdfPz(json,returnToJs,number);
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
            System.out.println("/PmsDownLoad/printSearchData.do 方法报错。");
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
}
