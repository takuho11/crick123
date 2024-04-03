/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-4-15 14:46:39
 *
 */
package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.halberd.app.api.utils.HttpUtil;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.SysParams;
import cn.topcheer.pms2.biz.sys.OldSysParamsService;
import cn.topcheer.pms2.biz.sys.SysParamsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/OldSysParams" })
public class OldSysParamsController extends GenericController<SysParams> {


	public OldSysParamsController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(SysParams.class.getName(),"[id],[code],[fathercode],[name],[value],[applytype],[memo],[seq]"); 
		PS_SET_SIMPLE.put(SysParams.class.getName(), "[id],[code],[fathercode],[name],[value],[applytype],[memo],[seq]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public OldSysParamsService getOldSysParamsService()
	{
		return (OldSysParamsService) this.getGenericService();
	}
	
	@Autowired
	private void setOldSysParamsService(OldSysParamsService service)
	{
		this.setGenericService(service);
	}
	@Autowired
	private OldSysParamsService oldSysParamsService;
	@Autowired
	DBHelper dbHelper;
	
	/**
	 *  根据传入的id返回相应的SysParams 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysParams 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 SysParams 对象（json格式的字符串）
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysParams 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 SysParams 对象（json格式的字符串）
	 * @param sysParams 传入的sysParams对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String saveSysParams(@RequestBody String sysParams,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(sysParams, request, response);
	}

	/**
	 *  根据传入的id删除相应的 SysParams 对象（json格式的字符串）
	 * @param id 要删除的 SysParams 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 SysParams 对象（json格式的字符串）
	 * @param ids 要删除的 SysParams 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}

	/**
	 * 【字典配置】---初始化获取所有父节点
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/initData.do")
	public void initData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		JSONObject param = HttpUtil.getBodyJSON(request, "utf-8");
		try{
			//接口：初始化获取所有父节点
			List<Map> list = this.oldSysParamsService.initData(param);
			returnToJs.setSuccess(true);
			returnToJs.setData(list);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+ Util.linkNumber);
//			log.error("/SysParams/initData.do方法报错。"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 【字典配置】---根据parentid获取所有子节点
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/initDataByparentid.do")
	public void initDataByparentid(@RequestParam(value = "parentid") String parentid,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			//接口：根据parentid获取所有子节点
			List<Map> list = this.oldSysParamsService.initDataByparentid(parentid);
			returnToJs.setSuccess(true);
			returnToJs.setData(list);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+Util.linkNumber);
//			log.error("/SysParams/initDataByparentid.do方法报错。"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 【字典配置】---保存方法(包含子级的删除)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/saveData.do")
	public void saveData(@RequestBody String example,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			//接口：保存方法
			JSONObject json = Util.checkFromJson(example);
			this.oldSysParamsService.saveData(json);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+Util.linkNumber);
//			log.error("/SysParams/saveData.do方法报错。"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 【字典配置】---根据parentid删除父级和他的子级
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/deleteDataByid.do")
	public void deleteDataByid(@RequestParam(value = "id") String id,
								   HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			//接口：根据parentid获取所有子节点
			this.oldSysParamsService.deleteDataByid(id);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+Util.linkNumber);
//			log.error("/SysParams/deleteDataByparentid.do方法报错。"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 【字典配置】---根据父级value获取他的子级
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/initDataByparentvalue.do")
	public void initDataByparentvalue(@RequestBody String example,
							   HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			Map map = this.oldSysParamsService.initDataByparentvalue(json);
			returnToJs.setSuccess(true);
			returnToJs.setData(map);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护人员："+Util.linkNumber);
//			log.error("/SysParams/initDataByparentvalue.do方法报错。"+Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 【配置】---配置导出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/outputDataByClass.do")
	public void outputDataByClass(@RequestParam(value="type") String type,
								  HttpServletRequest request,
								  HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			String[] key = new String[]{};

			List<Map> List = new ArrayList<>();
			if("SysParams".equals(type)){
				List = dbHelper.getRows("select t.* from sys_params t order by t.parentid,t.seq",null);
			}else if("SysParamsProvinces".equals(type)){
				List = dbHelper.getRows("select t.* from sys_params_provinces t order by t.parentid,t.seq",null);
			}else if("PmsMainapp".equals(type)){
				List = dbHelper.getRows("select t.* from pms_mainapp t order by t.name",null);
			}

			key = getKeys(List);

			if (List != null) {
				// 创建文档对象(其他对象都是通过文档对象创建)
				HSSFWorkbook wb = new HSSFWorkbook();
				// 创建样式对象（HSSFCellStyle ）
				HSSFCellStyle cellStyle = wb.createCellStyle();

				wb = getSheetAll(List, wb, cellStyle, key, key, type);

				// 输出excel文件
				OutputStream out = response.getOutputStream();
				response.reset();
				response.setHeader("Content-disposition",
						"attachment; filename="+type+".xls");
				response.setContentType("application/msexcel");
				wb.write(out);
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private String[] getKeys(List<Map> List) {
		Map map = List.get(0);
		String temp = map.keySet().toString();
		temp = temp.replaceAll("[\\[\\]]", "");
		temp = temp.replaceAll(" ", "");

		String[] Syskey = temp.split(",");
		return Syskey;
	}


	public HSSFWorkbook getSheetAll(List list, HSSFWorkbook wb, HSSFCellStyle cellStyle,String[] key1,String[] key,String name) {
		HSSFCellStyle linkStyle = wb.createCellStyle();
		HSSFFont cellFont= wb.createFont();
		cellFont.setUnderline((byte) 1);
		cellFont.setColor(IndexedColors.BLUE.getIndex());
		linkStyle.setFont(cellFont);
		//上面为链接样式
		JSONArray jsonArray = JSONArray.fromObject(list);
		HSSFSheet sheet = wb.createSheet(name);
		HSSFRow row1 = sheet.createRow(0);
		HSSFRow row;
		for (int i = 0; i < key1.length; i++) {
			HSSFCell cell = row1.createCell(i);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(key1[i]);
		}

		for (int j = 1; j < jsonArray.size() + 1; j++) {
			row = sheet.createRow(j);
			JSONObject job = jsonArray.getJSONObject(j - 1);
			for (int s = 0; s < key.length; s++) {
				String mycell = key[s];
				if (job.containsKey(mycell)) {
					row.createCell(s).setCellValue(job.getString(mycell));
					if(key[s].equals("gslink")){
						row.createCell(s).setCellFormula("HYPERLINK(\"" + job.getString(mycell) + "\")");
						//row.createCell(s).setCellStyle(linkStyle);
					}
				}
			}
		}
		return wb;
	}


	/**
	 * 【配置】---配置导入
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/importData.do")
	public ModelAndView importData(HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		JSONObject result = new JSONObject();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		MultipartFile file = multipartRequest.getFile("file");
		String extendname = request.getParameter("extendname");
		if("xlsx".equals(extendname)||"xls".equals(extendname)){
			CommonsMultipartFile cf= (CommonsMultipartFile)file; //这个myfile是MultipartFile的
			DiskFileItem fi = (DiskFileItem)cf.getFileItem();
			File excelFile = fi.getStoreLocation();
			String res = oldSysParamsService.importData(excelFile);
			if(!Util.isEoN(res)){
				result.put("success",res);
				result.put("res", "excel格式不正确");
				writer.write(result.toString());
				return null;
			}else{
				result.put("success",res);
				result.put("res", "导入成功");
				writer.write(result.toString());
				return null;
			}
		}else{
			result.put("success",false);
			result.put("res", "excel格式不正确");
			writer.write(result.toString());
			return null;
		}
	}

	/**
	 * 导出列表相关的配置数据
	 * @param ids
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/outputDataByIds.do")
	public void outputDataByIds(@RequestParam(value="ids") String ids,
								@RequestParam(value="type") String type,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			HSSFWorkbook wb = this.oldSysParamsService.outputDataById(ids, type);

			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			// 输出excel文件
			OutputStream out = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition",
					"attachment; filename=grid_" + formatter.format(currentTime) + ".xls");
			response.setContentType("application/msexcel");
			wb.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出excel数据到数据库
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/importDataByExcel.do")
	public void importDataByExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile file = multipartRequest.getFile("file");
			String extendname=request.getParameter("extendname");
			if("xlsx".equals(extendname)||"xls".equals(extendname)){
				CommonsMultipartFile cf= (CommonsMultipartFile)file; //这个myfile是MultipartFile的
				DiskFileItem fi = (DiskFileItem)cf.getFileItem();
				File excelFile = fi.getStoreLocation();
				returnToJs.setSuccess(this.oldSysParamsService.newImportData(excelFile));
				if(!returnToJs.isSuccess()){
					returnToJs.setErrMsg("导入失败！");
				}
			}else{
				returnToJs.setSuccess(false);
				returnToJs.setErrMsg("excel格式不正确！");
			}
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/PmsGrid/importData.do 方法报错。");
//			log.error("/PmsGrid/importData.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}
}
