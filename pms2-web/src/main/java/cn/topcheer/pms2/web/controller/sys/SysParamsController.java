package cn.topcheer.pms2.web.controller.sys;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.vo.SysParamsVO;
import cn.topcheer.pms2.biz.sys.impl.SysParamsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SysParams" )
@Api(value = "系统参数",tags = "系统参数-系统参数")
public class SysParamsController {

	@Resource
	private SysParamsServiceImpl sysParamsService;

	@ApiOperation(value = "根据applytype获取全部父子数据",
			notes = "根据applytype获取全部父子数据；对应省级系统原方法：/SysParams/initData以及/SysParams/initDataByparentid.do")
	@ApiImplicitParam(name = "applytype",value = "applytype",required = false)
	@GetMapping("/selectByCon")
	public R selectByCon(@RequestParam(value = "applytype",required = false) String applyType){
		return R.data(sysParamsService.selectByCon(applyType));
	}

	@ApiOperation(value = "根据父级value获取他的子级",
			notes = "根据父级value获取他的子级；对应省级系统原方法：/SysParams/initDataByparentvalue.do")
	@PostMapping("/selectByParentvalue")
	public R selectByParentvalue(@RequestBody ModelMap modelMap){
		return R.data(sysParamsService.selectByParentvalue(modelMap));
	}

	@ApiOperation(value = "保存方法",
			notes = "保存方法，父子同时保存；对应省级系统原方法：/SysParams/saveData.do")
	@PostMapping("/saveData")
	public R saveData(@RequestBody @Valid SysParamsVO sysParamsVO){
		sysParamsService.saveData(sysParamsVO);
		return R.success("保存成功。");
	}

	@ApiOperation(value = "根据id删除数据",
			notes = "根据id删除数据；对应省级系统原方法：/SysParams/deleteDataByid.do")
	@ApiImplicitParam(name = "id",value = "id",required = true)
	@DeleteMapping("/deleteById")
	public R deleteById(@RequestParam("id") String id){
		sysParamsService.deleteById(id);
		return R.success("删除成功。");
	}

	@Autowired
	DBHelper dbHelper;

	/**
	 * 【配置】---配置导出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping("/outputDataByClass")
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
	@PostMapping(value = "/importData")
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
			String res = sysParamsService.importData(excelFile);
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
	@PostMapping("/outputDataByIds")
	public void outputDataByIds(@RequestParam(value="ids") String ids,
								@RequestParam(value="type") String type,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			HSSFWorkbook wb = this.sysParamsService.outputDataById(ids, type);

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
	@PostMapping("/importDataByExcel")
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
				returnToJs.setSuccess(this.sysParamsService.newImportData(excelFile));
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
