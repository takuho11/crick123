/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2017-1-23 9:49:28
 *
 */
package cn.topcheer.pms2.web.controller.zjk;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.SysOperationrecord;
import cn.topcheer.pms2.api.sys.SysParamvalue;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxUpdate;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxxkflUpdate;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseService;
import cn.topcheer.pms2.biz.sys.*;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.biz.zjk.ZjkRyjbxxUpdateService;
import cn.topcheer.pms2.biz.zjk.ZjkRyjbxxxkflUpdateService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping({"/ZjkRyjbxxUpdate" })
public class ZjkRyjbxxUpdateController  extends GenericController<ZjkRyjbxxUpdate> {
	 	 
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private PmsRoleService sysRoleService;
	
	@Autowired
	private SysIdentityService sysIdentityService;

	@Autowired
	DBHelper dbHelper;
	@Autowired
	PmsProjectbaseService pmsProjectbaseSerice;
	@Autowired
	private PmsEnterpriseService pmsEnterpriseService;
	@Autowired
	private SysOperationrecordService sysOperationRecordService;

	@Autowired
	private ZjkRyjbxxUpdateService zjkRyjbxxUpdateService;
	@Autowired
	private ZjkRyjbxxxkflUpdateService zjkRyjbxxxkflUpdateService;


	public ZjkRyjbxxUpdateController() {
		// TODO Auto-generated constructor stub
		
		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */
		
		PS_SET.put(ZjkRyjbxxUpdate.class.getName(),"[id],[person_id],[name],[former_name],[gender],[birthday],[birthplace],[nationality],[native_place],[ethnic_group],[organization],[organization_property],[organization_province],[organization_city],[postal_address],[postcode],[position_title],[position_level],[professional_title],[research_type],[education],[degree],[mobile_telephone],[office_telephone],[home_telephone],[fax],[email],[person_urls],[microblog],[id_type],[id_number],[bank_name],[bank_account],[emergency_contact],[emergency_number],[cdc],[discipline],[industry],[other_code],[field],[research_direction],[photo_url],[introduce],[political_status],[honorary],[vocational_qualifications],[expert_type],[firstleveldiscipline],[subordinatediscipline],[thirdleveldiscipline],[cdxmlx],[zjlbxx],[rktj],[zjlbxx1],[keywords],[dwid],[organization_address],[professional_date],[submitdate],[minicurrentstateid],[minicurrentstate],[updatelasttime],[belonglabid],[psgztj],[sscs],[expertratio],[identifyrank],[yuanshi],[userid],[sjzj],[shjzj],[xjzj],[yorngjzj],[status],[xmqpy],[xmszmpy],[managedomain],[panels],[jszc],[jtgzdw],[passer],[backer],[region],[zshszdxm]"); 
		PS_SET_SIMPLE.put(ZjkRyjbxxUpdate.class.getName(), "[id],[person_id],[name],[former_name],[gender],[birthday],[birthplace],[nationality],[native_place],[ethnic_group],[organization],[organization_property],[organization_province],[organization_city],[postal_address],[postcode],[position_title],[position_level],[professional_title],[research_type],[education],[degree],[mobile_telephone],[office_telephone],[home_telephone],[fax],[email],[person_urls],[microblog],[id_type],[id_number],[bank_name],[bank_account],[emergency_contact],[emergency_number],[cdc],[discipline],[industry],[other_code],[field],[research_direction],[photo_url],[introduce],[political_status],[honorary],[vocational_qualifications],[expert_type],[firstleveldiscipline],[subordinatediscipline],[thirdleveldiscipline],[cdxmlx],[zjlbxx],[rktj],[zjlbxx1],[keywords],[dwid],[organization_address],[professional_date],[submitdate],[minicurrentstateid],[minicurrentstate],[updatelasttime],[belonglabid],[psgztj],[sscs],[expertratio],[identifyrank],[yuanshi],[userid],[sjzj],[shjzj],[xjzj],[yorngjzj],[status],[xmqpy],[xmszmpy],[managedomain],[panels],[jszc],[jtgzdw],[passer],[backer],[region],[zshszdxm]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);
		
	} 
	
	public ZjkRyjbxxUpdateService getZjkRyjbxxUpdateService()
	{
		return (ZjkRyjbxxUpdateService) this.getGenericService();
	}
	
	@Autowired
	private void setZjkRyjbxxUpdateService(ZjkRyjbxxUpdateService service)
	{
		this.setGenericService(service);
	}
	 
	
	/**
	 *  根据传入的id返回相应的ZjkRyjbxxUpdate 对象（json格式的字符串）
	 * @param id 
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return ZjkRyjbxxUpdate 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findById.do" })
	public @ResponseBody String findById(@RequestParam(value="id") String id,@RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){
		
		return super._findById(id, fetchParent, request, response);  
			 
	}
	
	/**
	 *  根据传入的id返回相应的 ZjkRyjbxxUpdate 对象（json格式的字符串）
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return ZjkRyjbxxUpdate 对象（json格式，字符串形式）
	 */
	@RequestMapping({ "/findByExample.do" })
	public @ResponseBody String findByExample(@RequestParam(value="example",required=false) String example,@RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._findByExample(example, fetchParent, request, response); 
	}
	
	/**
	 *  保存传入的 ZjkRyjbxxUpdate 对象（json格式的字符串）
	 * @param zjkRyjbxxUpdate 传入的zjkRyjbxxUpdate对象
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={ "/save.do"})	
	public   @ResponseBody String saveZjkRyjbxxUpdate(@RequestBody String zjkRyjbxxUpdate,
			HttpServletRequest request, HttpServletResponse response){
		return super._save(zjkRyjbxxUpdate, request, response);
	}

	/**
	 *  根据传入的id删除相应的 ZjkRyjbxxUpdate 对象（json格式的字符串）
	 * @param id 要删除的 ZjkRyjbxxUpdate 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteById.do" })
	public @ResponseBody String deleteById(@RequestParam(value="id") String id,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteById(id, request, response); 
	}
	
	/**
	 *  根据传入的ids删除相应的 ZjkRyjbxxUpdate 对象（json格式的字符串）
	 * @param ids 要删除的 ZjkRyjbxxUpdate 对象 id值,多个id之间用逗号(,)分隔
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@RequestMapping({ "/deleteByIds.do" })
	public @ResponseBody String deleteByIds(@RequestParam(value="ids") String ids,
			HttpServletRequest request, HttpServletResponse response){ 
		return super._deleteByIds(ids, request, response); 
	}
	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 基本情况
	 * @throws IOException
	 */
	@RequestMapping({ "/findexpertone.do" })
	public @ResponseBody String findexpertone(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pid = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<ZjkRyjbxxUpdate> listproinfor = zjkRyjbxxUpdateService.findexpertones(pid);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}
	
	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 基本情况
	 * @throws IOException
	 */
	@RequestMapping({ "/findexpertbyuserid.do" })
	public @ResponseBody String findexpertbyuserid(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pid = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<ZjkRyjbxxUpdate> listproinfor = zjkRyjbxxUpdateService.findexpertbyuserid(pid);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}
	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 基本情况
	 * @throws IOException
	 */
	@RequestMapping({ "/findbyuserid.do" })
	public @ResponseBody String findbyuserid(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String userid = request.getParameter("userid");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		String listproinfor = zjkRyjbxxUpdateService.findbyuserid(userid);
		if (Util.isEoN(listproinfor)) {
			result.put("data", listproinfor);
			result.put("success", false);
		} else {
			result.put("data", listproinfor);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}


	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 基本情况
	 * @throws IOException
	 */
	@RequestMapping({ "/findexpert.do" })
	public @ResponseBody String findexpert(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pid = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<ZjkRyjbxxUpdate> listproinfor = zjkRyjbxxUpdateService.findexpert(pid);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}

	/**
	 * @author wuchong
	 * @param request
	 * @param response
	 * @return 基本情况
	 * @throws IOException
	 */
	@RequestMapping({ "/findexpertById.do" })
	public @ResponseBody String findexpertById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String id = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<ZjkRyjbxxUpdate> listproinfor = zjkRyjbxxUpdateService.findexpertById(id);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}
	
	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 基本情况
	 * @throws IOException
	 */
	@RequestMapping({ "/finduserbyid.do" })
	public @ResponseBody String finduserbyid(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String id = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<ZjkRyjbxxUpdate> listproinfor = zjkRyjbxxUpdateService.finduserbyid(id);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}

	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 基本情况
	 * @throws IOException
	 */
	@RequestMapping({ "/findexpertByIdone.do" })
	public @ResponseBody String findexpertByIdone(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String id = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<Map> listproinfor = zjkRyjbxxUpdateService.findexpertByIdone(id);
		if (listproinfor!=null&&listproinfor.size()>0) {
			String password = listproinfor.get(0).get("password").toString();
			// DESEncrypt des = new DESEncrypt();
			// password = des.decrypt(password);
			// password = SmUtilAll.decryptAll(password);
			listproinfor.get(0).put("password", password);
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}
	
	
	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 基本情况
	 * @throws IOException
	 */
	@RequestMapping({ "/finduserByIdone.do" })
	public @ResponseBody String finduserByIdone(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String id = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<Map> listproinfor = zjkRyjbxxUpdateService.finduserByIdone(id);
		if (listproinfor.size()>0) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		}
		writer.write(result.toString());
		return null;
	}
	
	
	
	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 专家绑定的单位名称
	 * @throws IOException
	 */
	@RequestMapping({ "/finduntil.do" })
	public @ResponseBody String finduntil(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String id = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<Map> listproinfor = zjkRyjbxxUpdateService.finduntil(id);
		if (listproinfor!=null&&listproinfor.size()>0) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		}
		writer.write(result.toString());
		return null;
	}
	
	
	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 基本情况
	 * @throws IOException
	 */
	@RequestMapping({ "/findexpertByIdtwo.do" })
	public @ResponseBody String findexpertByIdtwo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String usercertificateno = request.getParameter("certificateno");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<Map> listproinfor = zjkRyjbxxUpdateService.findexpertByIdtwo(usercertificateno);
		if (listproinfor!=null&&listproinfor.size()>0) {
			String password = listproinfor.get(0).get("password").toString();
			// DESEncrypt des = new DESEncrypt();
			// password = des.decrypt(password);
			// password = SmUtilAll.decryptAll(password);
			listproinfor.get(0).put("password", password);
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		}
		writer.write(result.toString());
		return null;
	}

	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 专家评审工作条件
	 * @throws IOException
	 */
	@RequestMapping({ "/findzjfsgztj.do" })
	public @ResponseBody String findzjfsgztj(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pid = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<SysParamvalue> listproinfor = zjkRyjbxxUpdateService.findzjfsgztj();
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}

	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 评审科技奖项
	 * @throws IOException
	 */
	@RequestMapping({ "/findpanelss.do" })
	public @ResponseBody String findpanelss(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pid = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<ZjkRyjbxxxkflUpdate> listproinfor = zjkRyjbxxUpdateService.findpanelss(pid);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}

	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 项目学科分类
	 * @throws IOException
	 */
	@RequestMapping({ "/findryjbxxxkfl.do" })
	public @ResponseBody String ryjbxxxkfl(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pid = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<ZjkRyjbxxxkflUpdate> listproinfor = zjkRyjbxxUpdateService.findryjbxxxkfl(pid);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}

	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 自然基金分类
	 * @throws IOException
	 */
	@RequestMapping({ "/findryjbxxxkfl1.do" })
	public @ResponseBody String ryjbxxxkfl1(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pid = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<ZjkRyjbxxxkflUpdate> listproinfor = zjkRyjbxxUpdateService
				.findryjbxxxkfl1(pid);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}

	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 查看单位id
	 * @throws IOException
	 */
	@RequestMapping({ "/finddwid.do" })
	public @ResponseBody String finddwid(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pid = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<SysIdentity> listproinfor = zjkRyjbxxUpdateService.finddwid(pid);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}

	/**
	 * @author chong
	 * @param request
	 * @param response
	 * @return 自然基金分类
	 * @throws IOException
	 */
	@RequestMapping({ "/findryjbxxxone.do" })
	public @ResponseBody String findryjbxxxone(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String pid = request.getParameter("id");
		PrintWriter writer = response.getWriter();
		/* JSONObject objpara = HttpUtil.getBodyJSON(request, "utf-8"); */
		JSONObject result = new JSONObject();
		List<ZjkRyjbxxUpdate> listproinfor = zjkRyjbxxUpdateService.findexpertone(pid);
		if (Util.isEoN(listproinfor)) {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", false);
		} else {
			JSONArray jsonArray = JSONArray.fromObject(listproinfor);
			result.put("data", jsonArray);
			result.put("success", true);
		}
		writer.write(result.toString());
		return null;
	}


	/**
	 * 通过专家Id ，把专家标注成核心专家
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/markCoreExpert.do")
	public void markCoreExpert(@RequestParam(value="id") String id,
					  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			returnToJs.setData(this.zjkRyjbxxUpdateService.markCoreExpert(request,id));
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/markCoreExpert.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/markCoreExpert.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	@RequestMapping("/fetchZjInfoByName")
	public @ResponseBody String fetchZjInfoByName(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		List<Map> data = this.zjkRyjbxxUpdateService.fetchZjInfoByName(jsonObject);
		result.put("success", true);
		result.put("data", data);
		return result.toString();
	}


	/**
	 * 同步用户基本信息到专家库
	 * @param userid
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/synchronizeJbxxToZjk.do")
	public void synchronizeJbxxToZjk(@RequestParam(value="userid") String userid,
							   HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			this.zjkRyjbxxUpdateService.synchronizeJbxxToZjk(userid);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/synchronizeJbxxToZjk.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/synchronizeJbxxToZjk.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 同步用户单位信息到专家库--流程节点用
	 * @param userid
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/synchronizeZcxxToZjk.do")
	public void synchronizeZcxxToZjk(@RequestParam(value="userid") String userid,
									 HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			this.zjkRyjbxxUpdateService.synchronizeZcxxToZjk(userid);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/synchronizeZcxxToZjk.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/synchronizeZcxxToZjk.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 * 专家异议--保存意见+标识
	 * @param example
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/updateDissent.do")
	public void updateDissent(@RequestBody String example,
									 HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try{
			JSONObject jsonObject = Util.checkFromJson(example);
			this.zjkRyjbxxUpdateService.updateDissent(jsonObject);
			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/updateDissent.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/updateDissent.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 专家异议--获取异议中的专家
	 * @param example
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/initDissentExpert.do")
	public void initDissentExpert(@RequestBody String example,
							  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try{
			JSONObject jsonObject = Util.checkFromJson(example);
			List<Map> list = this.zjkRyjbxxUpdateService.initDissentExpert(jsonObject);
			returnToJs.setSuccess(true);
			returnToJs.setData(list);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/initDissentExpert.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/initDissentExpert.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 专家注册-根据身份证号码判断是否已经在专家库
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/judgeIsExistExpert.do")
	public void judgeIsExistExpert(@RequestParam(value="certificateno") String certificateno,
								   @RequestParam(value="userid") String userid,
							  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try{
			JSONObject resObj = this.zjkRyjbxxUpdateService.judgeIsExistExpert(certificateno,userid);

			returnToJs.setSuccess(true);
			returnToJs.setData(resObj);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/judgeIsExistExpert.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/judgeIsExistExpert.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 * 专家同步-从中间库同步省厅专家-20220715改造成定时任务
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/transExpertFromFront.do")
	public void transExpertFromFront(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try{
			String sql = "select id from front_zjk_jbxx where syncflag is null and card_code = '320830197903075619' ";
			List<Map> list = this.dbHelper.getRows(sql,null);

			// if(list.size()>0){
			// 	for (int i = 0; i < list.size(); i++) {
			// 		this.zjkRyjbxxUpdateService.transExpertFromFront(list.get(i).get("id")+"");
			// 	}
			// }

			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/transExpertFromFront.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/transExpertFromFront.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 *  【专家注册】 -- 执行流程
	 */
	@RequestMapping("/executeFlow.do")
	public void executeFlow(@RequestBody String example,
							HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			JSONObject resJson = this.zjkRyjbxxUpdateService.executeFlow(json);
			returnToJs.setSuccess(resJson.getBoolean("success"));
			if(!returnToJs.isSuccess()){
				returnToJs.setErrMsg(resJson.getString("reason"));
			}
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/executeFlow.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/executeFlow.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 *  【专家变更】 -- 执行流程--变更
	 */
	@RequestMapping("/executeFlow_BG.do")
	public void executeFlow_BG(@RequestBody String example,
							HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			JSONObject resJson = this.zjkRyjbxxUpdateService.executeFlow_BG(json);
			returnToJs.setSuccess(resJson.getBoolean("success"));
			if(!returnToJs.isSuccess()){
				returnToJs.setErrMsg(resJson.getString("reason"));
			}
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/executeFlow_BG.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/executeFlow_BG.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 * 【专家注册】--判断是否有在流程中的变更
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/judgeExpertIsInFlow.do")
	public void judgeExpertIsInFlow(@RequestBody String example,
										HttpServletRequest request,
										HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try {
			JSONObject jsonObject = Util.checkFromJson(example);
			Boolean flag = this.zjkRyjbxxUpdateService.judgeExpertIsInFlow(jsonObject);

			if (flag) {
				returnToJs.setSuccess(true);
			} else {
				returnToJs.setSuccess(false);
				returnToJs.setData(this.zjkRyjbxxUpdateService.getFlowInfo(jsonObject));
				returnToJs.setErrMsg("已在审核流程中。");
			}
		} catch (Exception e) {
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
			e.printStackTrace();
			System.out.println("/ZjkRyjbxxUpdate/judgeExpertIsInFlow.do 方法报错。" + Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

	/**
	 * 【专家注册】--获取专家状态
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping(value = "/fetchExpertStatus.do")
	public @ResponseBody ReturnToJs fetchExpertStatus(@RequestBody JSONObject jsonObject,
									HttpServletRequest request,
									HttpServletResponse response) {
		JSONObject result = this.zjkRyjbxxUpdateService.fetchExpertStatus(jsonObject);
		return ReturnToJs.success(result);
	}


	/**
	 * 【专家注册】--判断是否在公示中
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/judgeExpertIsInGS.do")
	public void judgeExpertIsInGS(@RequestParam(value = "expertid") String expertid,
									HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try {
			Boolean flag = this.zjkRyjbxxUpdateService.judgeExpertIsInGS(expertid);
			returnToJs.setSuccess(flag);
		} catch (Exception e) {
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
			e.printStackTrace();
			System.out.println("/ZjkRyjbxxUpdate/judgeExpertIsInGS.do 方法报错。" + Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 * 【变更】--变更专家信息，存在中间表
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/changeInfoToTemp.do")
	public void changeInfoToTemp(@RequestBody String example,
								 HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try {
			JSONObject jsonObject = Util.checkFromJson(example);
			JSONObject resObj = this.zjkRyjbxxUpdateService.changeInfoToTemp(jsonObject);

			if (resObj.getBoolean("success")) {
				returnToJs.setSuccess(true);
			} else {
				returnToJs.setSuccess(false);
				returnToJs.setErrMsg(resObj.get("errMsg") + "");
			}
		} catch (Exception e) {
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
			e.printStackTrace();
			System.out.println("/ZjkRyjbxxUpdate/changeInfoToTemp.do 方法报错。" + Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 * 【变更】--审核通过后，将存在中间表的变更信息，更新上去
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateInfoFromTemp.do")
	public void updateInfoFromTemp(@RequestParam(value = "sourceid") String sourceid,
								   @RequestParam(value = "result") String result,
								   HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try {
			// this.zjkRyjbxxUpdateService.updateInfoFromTemp(sourceid, result);
			returnToJs.setSuccess(true);
		} catch (Exception e) {
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
			e.printStackTrace();
			System.out.println("/ZjkRyjbxxUpdate/updateInfoFromTemp.do 方法报错。" + Util.getExceptionAllinformation(e));
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}




	/**
	 * 【变更】--获取正在变更的变更记录
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping(value = "/fetchChangeInfo.do")
	public @ResponseBody ReturnToJs fetchChangeInfo(@RequestBody JSONObject jsonObject,
								 HttpServletRequest request,
								 HttpServletResponse response) {
		return ReturnToJs.success(this.zjkRyjbxxUpdateService.fetchChangeInfo(jsonObject));
	}

	/**
	 * 【变更】--获取所有的变更记录
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping(value = "/fetchAllChangeInfo.do")
	public @ResponseBody ReturnToJs fetchAllChangeInfo(@RequestBody JSONObject jsonObject,
													HttpServletRequest request,
													HttpServletResponse response) {
		return ReturnToJs.success(this.zjkRyjbxxUpdateService.fetchAllChangeInfo(jsonObject));
	}

	/**
	 * 【变更】--删除变更变更记录
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping(value = "/deleteChangeInfo.do")
	public @ResponseBody ReturnToJs deleteChangeInfo(@RequestBody JSONObject jsonObject,
													   HttpServletRequest request,
													   HttpServletResponse response) {
		this.zjkRyjbxxUpdateService.deleteChangeInfo(jsonObject);
		return ReturnToJs.success();
	}




	/**
	 * 专家统计--从统计表获取数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getExpertStatisticsForEchart.do")
	public void getExpertStatisticsForEchart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try{
			JSONObject resObj= this.zjkRyjbxxUpdateService.getExpertStatisticsForEchart();
			returnToJs.setSuccess(true);
			returnToJs.setData(resObj);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/getExpertStatisticsForEchart.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/getExpertStatisticsForEchart.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 *  【专家注册】 -- 执行流程--人工录入
	 */
	@RequestMapping("/executeFlow_RGLR.do")
	public void executeFlow_RGLR(@RequestBody String example,
							HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			JSONObject resJson = this.zjkRyjbxxUpdateService.executeFlow_RGLR(json);
			returnToJs.setSuccess(resJson.getBoolean("success"));
			if(!returnToJs.isSuccess()){
				returnToJs.setErrMsg(resJson.getString("reason"));
			}
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/executeFlow_RGLR.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/executeFlow_RGLR.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 *  【专家注册】 -- 人工录入专家--页面新增
	 */
	@RequestMapping("/saveExpertByInvite.do")
	public void saveExpertByInvite(@RequestBody String example,
								 HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			JSONObject resJson = this.zjkRyjbxxUpdateService.saveExpertByInvite(json);
			returnToJs.setSuccess(resJson.getBoolean("result"));
			if(!returnToJs.isSuccess()){
				returnToJs.setErrMsg(resJson.getString("errMsg"));
			}
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/saveExpertByInvite.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/saveExpertByInvite.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 * 【专家注册】 -- 人工录入专家--导入excel
	 */
	@RequestMapping(value = "/saveExpertByInviteByExcel.do")
	public ModelAndView saveExpertByInviteByExcel(HttpServletRequest request,
										 HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		JSONObject result = new JSONObject();
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			SysOperationrecord sop = new SysOperationrecord();
			sop.setType("导入Excel");
			sop.setNote("人工录入专家");
			this.sysOperationRecordService.saveCurrentUserOperation(request,sop);
			MultipartFile file = multipartRequest.getFile("file");
			String extendname=request.getParameter("extendname");
			if("xlsx".equals(extendname)||"xls".equals(extendname)){
				CommonsMultipartFile cf = (CommonsMultipartFile)file; //这个myfile是MultipartFile的
				DiskFileItem fi = (DiskFileItem)cf.getFileItem();
				File excelFile = fi.getStoreLocation();
				JSONObject resObj = this.zjkRyjbxxUpdateService.saveExpertByInviteByExcel(excelFile,request);
				result.put("success",resObj.get("success"));
				result.put("data", resObj.get("data"));
			}else{
				result.put("success",false);
				result.put("res", "excel格式不正确");
			}
		}catch (Exception e){
			System.out.println("/ZjkRyjbxxUpdate/saveExpertByInviteByExcel.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/saveExpertByInviteByExcel.do 方法报错,错误信息："+e);
		}
		writer.write(result.toString());
		return null;

	}



	/**
	 *  【专家注册】 -- 执行流程--市外专家
	 */
	@RequestMapping("/executeFlow_SW.do")
	public void executeFlow_SW(@RequestBody String example,
							HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			JSONObject resJson = this.zjkRyjbxxUpdateService.executeFlow_SW(json);
			returnToJs.setSuccess(resJson.getBoolean("success"));
			if(!returnToJs.isSuccess()){
				returnToJs.setErrMsg(resJson.getString("reason"));
			}
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/executeFlow_SW.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/executeFlow_SW.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 *  【专家变更】 -- 执行流程--变更--市外专家
	 */
	@RequestMapping("/executeFlow_BG_SW.do")
	public void executeFlow_BG_SW(@RequestBody String example,
							   HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			JSONObject resJson = this.zjkRyjbxxUpdateService.executeFlow_BG_SW(json);
			returnToJs.setSuccess(resJson.getBoolean("success"));
			if(!returnToJs.isSuccess()){
				returnToJs.setErrMsg(resJson.getString("reason"));
			}
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/executeFlow_BG_SW.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/executeFlow_BG_SW.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 * 【专家】 -- 根据所在省查找专家
	 */
	@RequestMapping("/findExpertByProvince.do")
	public void findExpertByProvince(@RequestBody String example,
								  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			JSONObject resJson = this.zjkRyjbxxUpdateService.findExpertByProvince(json);
			returnToJs.setSuccess(true);
			returnToJs.setData(resJson);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/findExpertByProvince.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/findExpertByProvince.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


//	/**
//	 * 【专家】 -- 省厅专家学科处理
//	 */
//	@RequestMapping(value = "/buildSubject.do")
//	public ModelAndView buildSubject(
//								   HttpServletRequest request,
//								   HttpServletResponse response) throws Exception {
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter writer = response.getWriter();
//		ReturnToJs returnToJs = new ReturnToJs();
//
//		try {
//
//			String datasql = "select id,id_number from zjk_ryjbxx_update where id in (" +
//					"select person_id from (select person_id,subjectendcode,count(subjectendcode) as num " +
//					"from zjk_ryjbxxxkfl_update group by person_id,subjectendcode) " +
//					"where num > 1 )";
//			List<Map> dataList = this.dbHelper.getRows(datasql, null);
//
//			if (dataList.size() > 0) {
//				for (int i = 0; i < dataList.size(); i++) {
//					String id = dataList.get(i).get("id") + "";
//					String id_number = dataList.get(i).get("id_number") + "";
//					System.out.println("【start】"+(i+1)+"/"+dataList.size()+"-----"+id);
//
//					List<Map> list = this.dbHelper.getRows("select * from front_zjk_jbxx " +
//							"where card_code = ?",new Object[]{id_number});
//					if(list.size()>0){
//						this.zjkRyjbxxUpdateService.buildSubject(list,id);
//					}
//
//					System.out.println("【finish】"+(i+1)+"/"+dataList.size()+"-----"+id);
//				}
//			}
//
//
//			returnToJs.setSuccess(true);
//		} catch (Exception e) {
//			returnToJs.setSuccess(false);
//			returnToJs.setErrMsg("后台程序有误，请联系系统维护员：" + Util.linkNumber);
//			System.out.println("/ZjkRyjbxxUpdate/buildSubject.do 方法报错。" + Util.getExceptionAllinformation(e));
//		}
//		writer.write(JSONObject.fromObject(returnToJs).toString());
//		return null;
//	}

	/**
	 * 【信用问题专家出库】 -- 执行流程
	 */
	@RequestMapping("/executeFlow_CK.do")
	public void executeFlow_CK(@RequestBody String example,
							   HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try{
			SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
			JSONObject json = Util.checkFromJson(example);
			JSONObject resJson = this.zjkRyjbxxUpdateService.executeFlow_CK(json, user);
			returnToJs.setSuccess(resJson.getBoolean("success"));
			if(!returnToJs.isSuccess()){
				returnToJs.setErrMsg(resJson.getString("reason"));
			}
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/executeFlow_CK.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/executeFlow_CK.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}



	/**
	 *  【定向邀请专家】 -- 录入后发短信通知
	 */
	@RequestMapping("/sendMessage.do")
	public void sendMessage(@RequestBody String example,
							HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();

		try{
			JSONObject json = Util.checkFromJson(example);
			JSONArray array = json.getJSONArray("expert_Arr");

			if(array.size()>0){
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsonObject = array.getJSONObject(i);
					this.zjkRyjbxxUpdateService.sendMessage(jsonObject.get("id")+"");
				}
			}

			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/sendMessage.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/sendMessage.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}


	/**
	 *  【专家注册】 -- 保存承诺书标记
	 */
	@RequestMapping("/saveCnsFlag.do")
	@ResponseBody
	public ReturnToJs saveCnsFlag(@RequestParam String id){
		Boolean result = this.getZjkRyjbxxUpdateService().saveCnsFlag(id);
		return ReturnToJs.success(result);
	}


	/**
	 *  【专家临时抽取】 -- 专家随机检查
	 */
	@RequestMapping("/findRandomExpert.do")
	@ResponseBody
	public ReturnToJs findRandomExpert(@RequestBody JSONObject jsonObject){
		return ReturnToJs.success(this.getZjkRyjbxxUpdateService().findRandomExpert(jsonObject));
	}


	/**
	 *  【专家临时抽取】 -- 专家随机检查--保存抽取条件
	 */
	@RequestMapping("/saveRandomCondition.do")
	@ResponseBody
	public ReturnToJs saveRandomCondition(@RequestBody JSONObject jsonObject){
		// this.getZjkRyjbxxUpdateService().saveRandomCondition(jsonObject);
		return ReturnToJs.success();
	}


	/**
	 *  【专家临时抽取】 -- 专家随机检查--获取抽取条件
	 */
	@RequestMapping("/getRandomCondition.do")
	@ResponseBody
	public ReturnToJs getRandomCondition(@RequestParam String batchid){
		return ReturnToJs.success(this.getZjkRyjbxxUpdateService().getRandomCondition(batchid));
	}


	/**
	 *  【专家临时抽取】 -- 专家随机检查--保存已抽取专家
	 */
	@RequestMapping("/saveRandomExpert.do")
	@ResponseBody
	public ReturnToJs saveRandomExpert(@RequestBody JSONObject jsonObject){
		// this.getZjkRyjbxxUpdateService().saveRandomExpert(jsonObject);
		return ReturnToJs.success();
	}


	/**
	 *  【专家临时抽取】 -- 专家随机检查--获取已抽取专家
	 */
	@RequestMapping("/getRandomExpert.do")
	@ResponseBody
	public ReturnToJs getRandomExpert(@RequestParam String batchid){
		return ReturnToJs.success(this.getZjkRyjbxxUpdateService().getRandomExpert(batchid));
	}


	/**
	 *  【专家抽取】 -- 执行流程
	 */
	@RequestMapping("/executeFlow_ZJJC.do")
	public void executeFlow_ZJJC(@RequestBody String example,
							HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		ReturnToJs returnToJs = new ReturnToJs();
		try{
			JSONObject json = Util.checkFromJson(example);
			JSONArray array = json.getJSONArray("zjjc_Arr");

			// if(array.size()>0){
				// for (int i = 0; i < array.size(); i++) {
				// 	JSONObject jsonObject = array.getJSONObject(i);
					// JSONObject resObj = this.zjkRyjbxxUpdateService.executeFlow_ZJJC(jsonObject);
					// if(resObj.getBoolean("success")){
					// 	continue;
					// }else{
					// 	returnToJs.setSuccess(false);
					// 	returnToJs.setErrMsg(resObj.get("reason")+"");
					// }
				// }
			// }

			returnToJs.setSuccess(true);
		}catch (Exception e){
			returnToJs.setSuccess(false);
			returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+Util.linkNumber);
			System.out.println("/ZjkRyjbxxUpdate/executeFlow_ZJJC.do 方法报错。");
			log.error("/ZjkRyjbxxUpdate/executeFlow_ZJJC.do 方法报错,错误信息："+e);
		}
		writer.write(JSONObject.fromObject(returnToJs).toString());
	}

	/**
	 * 判断当前专家是否在评审中
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping("/judgeExpertIsReviewing.do")
	@ResponseBody
	public ReturnToJs judgeExpertIsReviewing(@RequestBody JSONObject jsonObject){
		return ReturnToJs.success(this.getZjkRyjbxxUpdateService().judgeExpertIsReviewing(jsonObject));
	}

	/**
	 * 为专家补充新的标签
	 */
	@RequestMapping("/supplementNewBq.do")
	@ResponseBody
	public ReturnToJs supplementNewBq(@RequestBody JSONObject jsonObject){
		// return ReturnToJs.success(zjkRegisterService.supplementNewBq(jsonObject));
		return ReturnToJs.success();
	}


	/**
	 * 专家统计人才称号
	 */
	@RequestMapping("/findExpertRcch.do")
	@ResponseBody
	public ReturnToJs findExpertRcch(){
		return ReturnToJs.success(this.zjkRyjbxxUpdateService.findExpertRcch());
	}


	/**
	 * 专家统计人才称号
	 */
	@RequestMapping("/findExpertRcchDetail.do")
	@ResponseBody
	public ReturnToJs findExpertRcchDetail(@RequestParam String type){
		return ReturnToJs.success(this.zjkRyjbxxUpdateService.findExpertRcchDetail(type));
	}


	/**
	 * 专家统计人才称号
	 */
	@RequestMapping("/findExpertRcchList.do")
	@ResponseBody
	public ReturnToJs findExpertRcchList(@RequestBody JSONObject jsonObject) throws ClassNotFoundException {
		return ReturnToJs.success(this.zjkRyjbxxUpdateService.findExpertRcchList(jsonObject));
	}

	@RequestMapping("/findExpertTypeList.do")
	public @ResponseBody ReturnToJs findExpertTypeList(@RequestBody JSONObject jsonObject){
		return ReturnToJs.success(this.zjkRyjbxxUpdateService.findExpertTypeList(jsonObject));
	}


	/**
	 * @Description 此方法用于历史的专家数据，通过码表匹配，将专家的头衔赋值到对应的字段
	 * @Author shenxian
	 * @Date 2023/2/27
	 * @param request
	 * @param response
	 * @return ReturnToJs
	 * @throws
	*/
	@RequestMapping("/changeExpertTabFromOldData.do")
	@ResponseBody
	public ReturnToJs changeExpertTabFromOldData(HttpServletRequest request, HttpServletResponse response){
		try {
			// this.getZjkRyjbxxUpdateService().changeExpertTabFromOldData();
		}catch (Exception e){
			return ReturnToJs.failure("未知错误");
		}
		return ReturnToJs.success();
	}

	@RequestMapping("/judgeBankInfo.do")
	@ResponseBody
	public ReturnToJs judgeBankInfo(@RequestBody JSONObject jsonObject){
		String eid = String.valueOf(jsonObject.get("eid"));
		// this.getZjkRyjbxxUpdateService().judgeBankInfo(eid);
		return ReturnToJs.success();
	}
	@RequestMapping("/findByIdNew.do")
	@ResponseBody
	public ReturnToJs findByIdNew(@RequestBody JSONObject jsonObject){
		String id = String.valueOf(jsonObject.get("id"));
		ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.getZjkRyjbxxUpdateService().findByIdNew(id);
		return ReturnToJs.success(zjkRyjbxxUpdate);
	}

}
