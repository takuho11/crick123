/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-9-29 8:45:51
 *
 */
package cn.topcheer.pms2.web.controller.sys.excel;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.utils.HttpUtil;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysIdentityServiceImpl;

import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.app.dao.jpa.json.JsonBuilder;
import cn.topcheer.pms2.api.sys.excel.SysExcel;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.sys.excel.SysExcelService;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.biz.utils.Util;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/SysExcel" })
@Api(value = "系统excel控制器", tags = "excel-控制器")
@Slf4j
public class SysExcelController  extends GenericController<SysExcel> {

    @Autowired
	private SysIdentityServiceImpl sysIdentityService;

	public SysExcelController() {
		// TODO Auto-generated constructor stub

		/**
		 * JSON转换时对象及父对象的列选择器集合初始赋值，请不要更新下面的代码，重新生成时会被覆盖
		 */

		PS_SET.put(SysExcel.class.getName(),"[id],[name],[roleid],[memo],[creatname],[creattime],[creatuserid],[updatetime]");
		PS_SET_SIMPLE.put(SysExcel.class.getName(), "[id],[name],[roleid],[memo],[creatname],[creattime],[creatuserid],[updatetime]");
 		PS_Config= JsonBuilder.getJsonConfig(PS_SET);
		PS_Config_SIMPLE= JsonBuilder.getJsonConfig(PS_SET);

	}
    private static Map<String,String> planStatusMap = new HashMap<String,String>();
	public SysExcelService getSysExcelService()
	{
		return (SysExcelService) this.getGenericService();
	}

	@Autowired
	private void setSysExcelService(SysExcelService service)
	{
		this.setGenericService(service);
	}
    @Autowired
    private SysExcelService sysExcelService;
    @Autowired
    private SysOperationrecordService sysOperationrecordService;


	/**
	 *  根据传入的id返回相应的SysExcel 对象（json格式的字符串）
	 * @param id
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysExcel 对象（json格式，字符串形式）
	 */
	@PostMapping({ "/findById.do" })
	public @ResponseBody
    String findById(@RequestParam(value="id") String id, @RequestParam(value="fetchParent",defaultValue="true") Boolean fetchParent,
                    HttpServletRequest request, HttpServletResponse response){

		return super._findById(id, fetchParent, request, response);

	}

	/**
	 *  根据传入的id返回相应的 SysExcel 对象（json格式的字符串）
	 * @param fetchParent 是否同时获取父对象，仅一级，不递归
	 * @return SysExcel 对象（json格式，字符串形式）
	 */
	@PostMapping({ "/findByExample.do" })
	public @ResponseBody
    String findByExample(@RequestParam(value="example",required=false) String example, @RequestParam(value="fetchParent",defaultValue="false") Boolean fetchParent,
                         HttpServletRequest request, HttpServletResponse response){
		return super._findByExample(example, fetchParent, request, response);
	}

	/**
	 *  保存传入的 SysExcel 对象（json格式的字符串）
	 * @param sysExcel 传入的sysExcel对象
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping(value={ "/save.do"})
	public   @ResponseBody
    String saveSysExcel(@RequestBody String sysExcel,
                        HttpServletRequest request, HttpServletResponse response){
		return super._save(sysExcel, request, response);
	}

	/**
	 *  根据传入的id删除相应的 SysExcel 对象（json格式的字符串）
	 * @param id 要删除的 SysExcel 对象 id值
	 * @return 操作结果 一般是 {"success":"true"}
	 */
	@PostMapping({ "/deleteById.do" })
	public @ResponseBody
    String deleteById(@RequestParam(value="id") String id,
                      HttpServletRequest request, HttpServletResponse response){
		return super._deleteById(id, request, response);
	}

	/**
	 *  根据传入的ids删除相应的 SysExcel 对象（json格式的字符串）
	 * @param ids 要删除的 SysExcel 对象 id值,多个id之间用逗号(,)分隔
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
    @PostMapping("/sysExcelSave.do")
    public void sysExcelSave(@RequestBody String example,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONObject json = Util.checkFromJson(example);
            BladeUser jsonUser = AuthUtil.getUser();
            String sysUserId = jsonUser.getUserId();
            returnToJs.setData(jsonUser);
            returnToJs = this.sysExcelService.sysExcelSave(json,returnToJs);
            this.sysOperationrecordService.commonSaveOperation(request,sysUserId,"excel表新增修改","用户id:"+sysUserId+"，其他内容："+jsonUser.toString());
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/sysExcelSave.do 方法报错。");
            log.error("/SysExcel/sysExcelSave.do 方法报错,错误信息："+ Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【浙江系统】  --- 查询方法
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelFind.do")
    public void sysExcelFind(@RequestBody String example,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        Boolean isrole = sysExcelService.getaBoolean(request);
        try{
            JSONObject json = Util.checkFromJson(example);
            if(isrole){
                JSONArray array = this.sysExcelService.sysExcelFind(json);
                returnToJs.setData(array);
                returnToJs.setSuccess(true);
            }else{
                returnToJs.setSuccess(false);
                returnToJs.setErrMsg("该用户无权限");
            }
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/sysExcelFind.do 方法报错。");
            log.error("/SysExcel/sysExcelFind.do 方法报错,错误信息："+ Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }

    /**
     * 【浙江系统】  --- 删除方法
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelDelete.do")
    public void sysExcelDelete(@RequestBody String example,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            JSONObject json = Util.checkFromJson(example);
            BladeUser jsonUser = AuthUtil.getUser();
            String sysUserId = jsonUser.getUserId();
            returnToJs = this.sysExcelService.sysExcelDelete(json,returnToJs);
            this.sysOperationrecordService.commonSaveOperation(request,sysUserId,"excel表删除","用户id:"+sysUserId+"，其他内容："+jsonUser.toString());
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/sysExcelDelete.do 方法报错。");
            log.error("/SysExcel/sysExcelDelete.do 方法报错,错误信息："+ Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【浙江系统】  --- 获取方法
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelFindbyid.do")
    public void sysExcelFindbyid(
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            String parentId = request.getParameter("id");
            JSONArray array = this.sysExcelService.sysExcelFindbyid(parentId);
            returnToJs.setData(array);
            returnToJs.setSuccess(true);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/sysExcelFindbyid.do 方法报错。");
            log.error("/SysExcel/sysExcelFindbyid.do 方法报错,错误信息："+ Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【浙江系统】  --- 初始化方法
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelInit.do")
    public void sysExcelInit(
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            Boolean isrole = sysExcelService.getaBoolean(request);
            if (isrole) {
                JSONArray array = this.sysExcelService.sysExcelInit();
                returnToJs.setData(array);
                returnToJs.setSuccess(true);
            } else {
                returnToJs.setSuccess(false);
                returnToJs.setErrMsg("该用户无权限");
            }
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/sysExcelInit.do 方法报错。");
            log.error("/SysExcel/sysExcelInit.do 方法报错,错误信息："+ Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }

    /**
     * 【版本配置】---复制：通过版本id生成新的版本以及相关信息
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/versionCopyById.do")
    public void versionCopyById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        JSONObject resObject = new JSONObject();
        JSONObject json = HttpUtil.getBodyJSON(request, "utf-8");
        BladeUser scuser =   AuthUtil.getUser();//SessionUtil.getCurrentSecurityUser(request);
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            String versionid = json.get("sourceid")+"";
            String newUpperid = json.get("newUpperid")+"";
            String type = json.get("type")+"";
            if(Util.isEoN(versionid)||"".equals(versionid)||"null".equals(versionid)||"undefined".equals(versionid)){
                returnToJs.setSuccess(false);
                returnToJs.setErrMsg("未传id");
            }else{
                String copyVersionid = "";
                switch (type){
                    case "sysExcel":
                        copyVersionid = this.sysExcelService.excelCopyById(versionid,scuser);
                        break;
                    case "sysExcelSheet":
                        copyVersionid = this.sysExcelService.sysExcelSheetCopyById(versionid,"",scuser);
                        break;
                    case "sysExcelTab":
                        copyVersionid = this.sysExcelService.sysExcelTabCopyById(versionid,newUpperid,scuser);
                        break;
                    case "sysExcelParam":
                        copyVersionid = this.sysExcelService.sysExcelParamCopyById(versionid,"",scuser);
                        break;
                    case "sysExcelStyle":
                        copyVersionid = this.sysExcelService.sysExcelStyleCopyById(versionid,scuser);
                        break;
                    case "sysExcelFont":
                        copyVersionid = this.sysExcelService.sysExcelFontCopyById(versionid,scuser);
                        break;
                    default:
                        break;
                }
                if(Util.isEoN(copyVersionid)||"".equals(copyVersionid)){
                    returnToJs.setSuccess(false);
                    returnToJs.setErrMsg("未返回版本id");
                }else{
                    returnToJs.setSuccess(true);
                    returnToJs.setData(copyVersionid);
                }
            }
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/versionCopyById.do 方法报错。");
            log.error("/SysExcel/versionCopyById.do 方法报错,错误信息："+ Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【excel系统】  --- 获取该excel的参数
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelFindParamkey.do")
    public void sysExcelFindParamkey(@RequestParam(value="id") String id, @RequestParam(value="type") String type,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            returnToJs = this.sysExcelService.sysExcelFindParamkey(returnToJs,id,type);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/sysExcelFindParamkey.do 方法报错。");
            log.error("/SysExcel/sysExcelFindParamkey.do 方法报错,错误信息："+ Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【excel系统】  --- 获取该excel的参数--通过数据源类型
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelFindParamkeyForJson.do")
    public void sysExcelFindParamkeyForJson(@RequestParam(value="id") String id, @RequestParam(value="type") String type,
                                            @RequestParam(value="datatype") String datatype,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            returnToJs = this.sysExcelService.sysExcelFindParamkeyForJson(returnToJs,id,type,datatype);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/sysExcelFindParamkey.do 方法报错。");
            log.error("/SysExcel/sysExcelFindParamkey.do 方法报错,错误信息："+ Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
    /**
     * 【浙江系统】  --- 获取该excel的sheet表和tab的列头
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/sysExcelFindTabKeyHead.do")
    public void sysExcelFindTabKeyHead(@RequestParam(value="id") String id, @RequestParam(value="type") String type,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        try{
            returnToJs = this.sysExcelService.sysExcelFindTabKeyHead(returnToJs,id,type);
        }catch (Exception e){
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/sysExcelFindTabKeyHead.do 方法报错。");
            log.error("/SysExcel/sysExcelFindTabKeyHead.do 方法报错,错误信息："+ Util.getExceptionAllinformation(e));
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


    //excel总体导出--根据配置
    @PostMapping(value ="/excelExport.do" )
    public  void excelExport(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(Charsets.UTF_8.name());
        String fileName = URLEncoder.encode("excel-" + System.currentTimeMillis(), Charsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");

        BladeUser sysuser = AuthUtil.getUser();// .getCurrentSecurityUser(request);
        ReturnToJs returnToJs = new ReturnToJs();
        //PrintWriter writer = response.getWriter();
        String excelid=request.getParameter("excelid");
        try {
            SysExcel exceldata=this.sysExcelService.findById(excelid);
            String roles=exceldata.getRoleid();
            List<SysIdentity> sysIdentitys=sysIdentityService.findByUserId(sysuser.getUserId());//   sysuser.getUser().getSysIdentitys();
            Boolean isrole="all".equals(roles)?false:true;//如果是all就是所有用户
            for (SysIdentity sysIdentity:sysIdentitys){
                if(roles.contains(sysIdentity.getSysRole().getId())){
                    isrole=true;
                    break;
                }
            }
            if (isrole) {
                planStatusMap.put(excelid,"1");
                returnToJs=this.sysExcelService.getSheetAllClass(new HashedMap(),returnToJs,request, response);

                this.sysOperationrecordService.commonSaveOperation(request, excelid, "配置excel清单导出", "配置excel清单导出，excelid："+excelid);
            } else {
                returnToJs.setSuccess(false);
                returnToJs.setData("该用户没有权限");
                returnToJs.setErrMsg("该用户没有权限");
                log.error("/SysExcel/excelExport.do 权限问题,信息：该用户没有权限");
            }
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            System.out.println("/SysExcel/excelExport.do 方法报错。");
            log.error("/SysExcel/excelExport.do 方法报错,错误信息："+e);
            e.printStackTrace();
        }
        planStatusMap.put(excelid,"10");
       // writer.write(JSONObject.fromObject(returnToJs).toString());

    }



    /*根据路径或*/
    @PostMapping({"/downloadBylocal.do"})
    public void downloadBylocal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BladeUser sysuser = AuthUtil.getUser();
        ReturnToJs returnToJs = new ReturnToJs();
        this.sysExcelService.findRolePower(returnToJs,request,sysuser,response);
    }

    /*根据路径或*/
    @PostMapping({"/downloadByRecord.do"})
    public void downloadByRecord(HttpServletResponse response, String id) throws Exception {
        this.sysExcelService.downloadByRecord(response, id);
    }


    @PostMapping(value = "/findplanTime.do")
    public ModelAndView findplanTime(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        String projectbaseid = request.getParameter("projectbaseid");
        // 设置输出结束
        JSONObject result = new JSONObject();
        BladeUser user = null;
        user = AuthUtil.getUser();
        String currentState = planStatusMap.get(projectbaseid);
        //System.out.print(planStatus.get());
        //result.put("time", planStatus.get());
        if(currentState!=null&&currentState.equals("10")){
            planStatusMap.remove(projectbaseid);
        }
        result.put("currentState",currentState);
        writer.write(result.toString());
        return null;
    }


    /**
     * 导出列表相关的配置数据
     * @param ids
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/outputDataByIds.do")
    public void outputDataByIds(@RequestParam(value="ids") String ids,
                                @RequestParam(value="type") String type,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        try{
            HSSFWorkbook wb = this.sysExcelService.outputDataById(ids,type);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            // 输出excel文件
            OutputStream out = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition",
                    "attachment; filename="+type+"_"+formatter.format(currentTime)+".xls");
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
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/importData.do")
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
                returnToJs.setSuccess(this.sysExcelService.importData(excelFile));
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
            System.out.println("/SysExcel/importData.do 方法报错。");
            log.error("/SysExcel/importData.do 方法报错,错误信息："+e);
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }
}
