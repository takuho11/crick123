/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-9-25 18:23:38
 *
 */
package cn.topcheer.pms2.web.controller.sys.excel;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.sys.excel.SysExcelFont;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.sys.excel.SysExcelFontService;
import cn.topcheer.pms2.biz.sys.excel.SysExcelService;
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
@RequestMapping({"/SysExcelFont"})
@Api(value = "系统excel字体控制器", tags = "excel-字体控制器")
public class SysExcelFontController extends GenericController<SysExcelFont> {


	public SysExcelFontController() {
		// TODO Auto-generated constructor stub

		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */

		PS_SET.put(SysExcelFont.class.getName(), "[id],[bold],[boldweight],[underline],[color],[fontname],[fontheightinpoints],[italic],[name],[memo]");
		PS_SET_SIMPLE.put(SysExcelFont.class.getName(), "[id],[bold],[boldweight],[underline],[color],[fontname],[fontheightinpoints],[italic],[name],[memo]");
		PS_Config = JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE = JsonBuilder.getJsonConfig(PS_SET);

	}

	public SysExcelFontService getSysExcelFontService() {
		return (SysExcelFontService) this.getGenericService();
	}

	@Autowired
	private void setSysExcelFontService(SysExcelFontService service) {
		this.setGenericService(service);
	}

	@Autowired
	private SysExcelFontService sysExcelFontService;
	@Autowired
	private SysOperationrecordService sysOperationrecordService;
	@Autowired
	private SysExcelService sysExcelService;

	/**
	 * 根据传入的id返回相应的SysExcelFont 对象（json格式的字符串）
	 *
	 * @param id
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysExcelFont 对象（json格式，字符串形式）
	 */
	@PostMapping({"/findById.do"})
	public @ResponseBody
	String findById(@RequestParam(value = "id") String id, @RequestParam(value = "fetchParent", defaultValue = "true") Boolean fetchParent,
					HttpServletRequest request, HttpServletResponse response) {

		return super._findById(id, fetchParent, request, response);

	}

	/**
	 * 根据传入的id返回相应的 SysExcelFont 对象（json格式的字符串）
	 *
	 * @param example
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysExcelFont 对象（json格式，字符串形式）
	 */
	@PostMapping({"/findByExample.do"})
	public @ResponseBody
	String findByExample(@RequestParam(value = "example", required = false) String example, @RequestParam(value = "fetchParent", defaultValue = "false") Boolean fetchParent,
						 HttpServletRequest request, HttpServletResponse response) {
		return super._findByExample(example, fetchParent, request, response);
	}

	/**
	 * 保存传入的 SysExcelFont 对象（json格式的字符串）
	 *
	 * @param sysExcelFont 传入的sysExcelFont对象
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping(value = {"/save.do"})
	public @ResponseBody
	String saveSysExcelFont(@RequestBody String sysExcelFont,
							HttpServletRequest request, HttpServletResponse response) {
		return super._save(sysExcelFont, request, response);
	}

	/**
	 * 根据传入的id删除相应的 SysExcelFont 对象（json格式的字符串）
	 *
	 * @param id 要删除的 SysExcelFont 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@PostMapping({"/deleteById.do"})
	public @ResponseBody
	String deleteById(@RequestParam(value = "id") String id,
					  HttpServletRequest request, HttpServletResponse response) {
		return super._deleteById(id, request, response);
	}

	/**
	 * 根据传入的ids删除相应的 SysExcelFont 对象（json格式的字符串）
	 *
	 * @param ids 要删除的 SysExcelFont 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@PostMapping({"/deleteByIds.do"})
	public @ResponseBody
	String deleteByIds(@RequestParam(value = "ids") String ids,
					   HttpServletRequest request, HttpServletResponse response) {
		return super._deleteByIds(ids, request, response);
	}

	/**
	 * 【浙江系统】  --- 保存修改方法
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping("/sysExcelFontSave.do")
	public void sysExcelFontSave(@RequestBody String example,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try {
			JSONObject json = Util.checkFromJson(example);
			BladeUser jsonUser = AuthUtil.getUser();
			String sysUserId = jsonUser.getUserId();
			returnToJs = this.sysExcelFontService.sysExcelFontSave(json, returnToJs);
			this.sysOperationrecordService.commonSaveOperation(request, sysUserId, "font表新增修改", "用户id:" + sysUserId + "，其他内容：" + jsonUser.toString());
			returnToJs.setSuccess(true);
		} catch (Exception e) {
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
			System.out.println("/SysExcelFont/sysExcelFontSave.do 方法报错。");
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

	/**
	 * 【浙江系统】  --- 查询方法
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping("/sysExcelFontFind.do")
	public void sysExcelFontFind(@RequestBody String example,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try {
			Boolean isrole = sysExcelService.getaBoolean(request);
			if (isrole) {
				JSONArray array = this.sysExcelFontService.sysExcelFontFind();
				returnToJs.setData(array);
				returnToJs.setSuccess(true);
			} else {
				returnToJs.setSuccess(false);
				returnToJs.setErrMsg("该用户无权限");
			}
		} catch (Exception e) {
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
			System.out.println("/SysExcelFont/sysExcelFontFind.do 方法报错。");
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

	/**
	 * 【浙江系统】  --- 删除方法
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping("/sysExcelFontDelete.do")
	public void sysExcelFontDelete(@RequestBody String example,
								   HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try {
			JSONObject json = Util.checkFromJson(example);
			BladeUser jsonUser = AuthUtil.getUser();
			String sysUserId = jsonUser.getUserId();
			returnToJs = this.sysExcelFontService.sysExcelFontDelete(json, returnToJs);
			this.sysOperationrecordService.commonSaveOperation(request, sysUserId, "font表删除", "用户id:" + sysUserId + "，其他内容：" + jsonUser.toString());
			returnToJs.setSuccess(true);
		} catch (Exception e) {
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
			System.out.println("/SysExcelFont/sysExcelFontDelete.do 方法报错。");
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}
}
