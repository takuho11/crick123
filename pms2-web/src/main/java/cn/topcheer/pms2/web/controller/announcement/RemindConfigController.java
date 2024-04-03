/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-7-25 16:10:05
 *
 */
package cn.topcheer.pms2.web.controller.announcement;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.pms2.api.announcement.RemindConfig;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.announcement.impl.RemindConfigService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping({"/RemindConfig" })
public class RemindConfigController  extends GenericController<RemindConfig> {

	@Lazy
	@Autowired
	private RemindConfigService remindConfigService;


	/*==============================================完美分割线==============================================*/


	/**
	 *【主动提醒配置】 -- 根据参数获取对应的配置数据
	 */
	@RequestMapping(value = "/getAllConfigData.do")
	public @ResponseBody ReturnToJs getAllConfigData(@RequestParam(value="system") String system,
													 @RequestParam(value="type") String type){
		return ReturnToJs.success(remindConfigService.getAllConfigData(system,type));
	}

	/**
	 * 【主动提醒配置】 -- 新增+修改 配置数据
	 */
	@RequestMapping(value = "/addAndEditConfig.do")
	public @ResponseBody ReturnToJs addAndEditConfig(@RequestBody JSONObject json){
		this.remindConfigService.addAndEditConfig(json);
		return ReturnToJs.success();
	}

	/**
	 * 【主动提醒配置】 -- 通过id删除配置
	 */
	@RequestMapping(value = "/deleteById.do")
	public @ResponseBody ReturnToJs deleteById(@RequestParam(value="id") String id){
		this.remindConfigService.deleteData(id);
		return ReturnToJs.success();
	}

	/**
	 * 【主动提醒配置】 -- 给主动提醒配置角色
	 */
	@RequestMapping(value = "/configureRoles.do")
	public @ResponseBody ReturnToJs configureRoles(@RequestBody JSONObject json) throws SQLException {
		this.remindConfigService.configureRoles(json);
		return ReturnToJs.success();
	}

	/**
	 *【主动提醒配置】 -- 根据参数获取对应的配置数据
	 */
	@RequestMapping(value = "/getRolesByConfigid.do")
	public @ResponseBody ReturnToJs getRolesByConfigid(@RequestParam(value="id") String configid){
		return ReturnToJs.success(this.remindConfigService.getRolesByConfigid(configid));
	}




	/**
	 *【主动提醒配置】 -- 导出主动提醒配置相关的配置数据
	 */
	@RequestMapping("/outputDataByIds.do")
	public void outputDataByIds(@RequestParam(value="ids") String ids,
								@RequestParam(value="type") String type,
								HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		try{
			HSSFWorkbook wb = this.remindConfigService.outputDataById(ids,type);
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			// 输出excel文件
			OutputStream out = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition",
					"attachment; filename=remind_"+formatter.format(currentTime)+".xls");
			response.setContentType("application/msexcel");
			wb.write(out);
			out.close();
		}catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出excel数据到数据库
	 */
	@RequestMapping("/importData.do")
	public void importData(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
				returnToJs.setSuccess(this.remindConfigService.importData(excelFile));
				if(!returnToJs.isSuccess()){
					returnToJs.setErrMsg("导入失败！");
				}
			}else{
				returnToJs.setSuccess(false);
				returnToJs.setErrMsg("excel格式不正确！");
			}
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
			System.out.println("/RemindConfig/importData.do 方法报错。");
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

}
