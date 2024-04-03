package cn.topcheer.pms2.web.controller.pdfDownload;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericController;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.biz.modules.resource.builder.oss.OssBuilder;
import cn.topcheer.pms2.api.pdfDownload.PmsDownloadtype;
import cn.topcheer.pms2.api.plan.entity.PmsAttachment;
import cn.topcheer.pms2.api.plan.entity.PmsFilePrintRecord;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;

import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import cn.topcheer.pms2.biz.pdfDownload.impl.PmsDownloadtypeService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsFilePrintRecordService;
import cn.topcheer.pms2.biz.pms.CreatePdfOrWordNew;
import cn.topcheer.pms2.biz.utils.OperationPdfUtil;
import cn.topcheer.pms2.biz.utils.Util;
import com.itextpdf.text.DocumentException;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.oss.MinioTemplate;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.NonDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 控制器
 *
 * @author whq
 * @date 2023-12-07
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping("/PmsDownLoad")
@Api(value = "pdf打印下载服务", tags = "PDF-pdf打印下载服务")
public class PmsDownLoadController extends GenericController<PmsProjectbase> {
    @Autowired
    PmsDownloadtypeService pmsDownloadtypeService;
    @Autowired
    DBHelper dbHelper;
    @Autowired
    private CreatePdfOrWordNew createPdfOrWordNew;
    @Autowired
    private OssBuilder ossBuilder;
    @Autowired
    PmsFilePrintRecordService pmsFilePrintRecordService;

    /**
     * @author jzz
     * @param request
     * @param response
     * @throws Exception
     *             根据传进来的参数，通过不同的模版生成不同的pdf
     */
    @RequestMapping(value = "/downloadPdfByConfig.do",method = RequestMethod.POST)
    public void downloadPdfByConfig(@RequestBody String json,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        ReturnToJs returnToJs = new ReturnToJs();
        String type = request.getParameter("type");
        String planprojectid = request.getParameter("categoryid")+"";
        String errmsg="";
        List<PmsDownloadtype> pdyList = new ArrayList<>();
        if(Util.isEoN(planprojectid)){
            pdyList=this.pmsDownloadtypeService.findByProperty("type",type);
        }else{
            String hql = "from PmsDownloadtype t where t.type = ?0  and t.planprojectid like ?1 ";
            pdyList = this.pmsDownloadtypeService.findByHql(hql,type,"%"+planprojectid+"%");
            if(pdyList.size()<1){
                hql = "from PmsDownloadtype t where t.type = ?0 order by t.planprojectid desc ";//把空planprojectid的排前面
                pdyList = this.pmsDownloadtypeService.findByHql(hql,type);
                //pdyList=this.pmsDownloadtypeService.findByProperty("type",type);
            }
        }
        PmsDownloadtype pyd=new PmsDownloadtype();
        if(pdyList.size()>0){
            pyd=pdyList.get(0);
            String plOrnot=pyd.getIsbatch();//是否批量
            String justdown=pyd.getJustdown();//是否直接下载
            String sourceid="";//sourceid
            String pdfname="";//pdfname
            String idtype=pyd.getIdtype()+"";
            if (Util.isEoN(pyd.getDowntypebyredis())) {
                if(plOrnot != null && plOrnot.equals("true")){
                    JSONObject jsonObject = Util.checkFromJson(json);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i = 0; i < array.size(); i++) {
                        JSONObject obj=array.getJSONObject(i);
                        sourceid=obj.get("id")+"";
                        pdfname=obj.get("pdfname")+"";
                        if(Util.isEoN(array.getJSONObject(0).get("pdfname")+"")){//没传就用sourceid
                            pdfname=sourceid;
                        }
                        if (justdown != null && "true".equals(justdown)) {
                            String openname = pyd.getOpenname() + "";
                            if (!Util.isEoN(pyd.getOpennamesql())) {
                                openname = this.dbHelper.getOnlyStringValue(pyd.getOpennamesql(), new Object[]{sourceid});
                            }
                            String realPDFFile = getRealpathString(request, pyd, sourceid, pdfname, idtype);
                            System.out.print("realPDFFile:" + realPDFFile);
                            this.createPdfOrWordNew.downloadByLocal(openname,
                                    realPDFFile, response, pyd.getDownloadtype());
                        } else {
                            System.out.print("pdfname:" + pdfname);
                            try {
                                createPdfOrWordNew.downloadPdfByModelFortype(sourceid, pdfname, plOrnot, pyd, response, request, json, planprojectid);
                                String realPDFFile = getRealpathString(request, pyd, sourceid, pdfname, idtype);
                                byte[] bytes = OperationPdfUtil.fileToByteArray(realPDFFile);
                                if (Util.isEoN(bytes)) {
                                    errmsg = "未生成文件";
                                }
                            } catch (IOException e) {
                                errmsg = "文件转换出错";
                                e.printStackTrace();
                            } catch (TemplateException e) {
                                errmsg = "模板出错";
                                e.printStackTrace();
                            } catch (DocumentException e) {
                                errmsg = "文档打开出错";
                                e.printStackTrace();
                            } catch (java.text.ParseException e) {
                                errmsg = "类型转换出错";
                                e.printStackTrace();
                            }
                        }
                    }

                }else{
                    if(Util.isEoN(request.getParameter(idtype))&&Util.isEoN(request.getParameter("pdfname"))){//jsonarray传
                        JSONObject jsonObject = Util.checkFromJson(json);
                        JSONArray array=jsonObject.getJSONArray("data");
                        sourceid=array.getJSONObject(0).get("id")+"";
                        pdfname=array.getJSONObject(0).get("pdfname")+"";
                        if(Util.isEoN(array.getJSONObject(0).get("pdfname")+"")){//没传就用sourceid
                            pdfname=sourceid;
                        }
                    }else{//Parameter 传
                        sourceid=request.getParameter(idtype);
                        if(Util.isEoN(request.getParameter("pdfname"))){
                            pdfname=sourceid;
                        }else{
                            pdfname=request.getParameter("pdfname");
                        }
                    }
                    if(justdown != null && "true".equals(justdown)){
                        String openname = pyd.getOpenname()+"";
                        if(!Util.isEoN(pyd.getOpennamesql())){
                            openname=this.dbHelper.getOnlyStringValue(pyd.getOpennamesql(),new Object[]{sourceid});
                        }
                        String realPDFFile = getRealpathString(request, pyd, sourceid, pdfname, idtype);
                        System.out.print("realPDFFile:"+realPDFFile);
                        this.createPdfOrWordNew.downloadByLocal(openname,
                                realPDFFile, response,pyd.getDownloadtype());
                    }else{
//                        planStatusMap.put(sourceid,"1");
                        System.out.print("pdfname:"+pdfname);
                        try {
                            createPdfOrWordNew.downloadPdfByModelFortype(sourceid,pdfname,plOrnot,pyd, response, request, json, planprojectid);
                            String realPDFFile = getRealpathString(request, pyd, sourceid, pdfname, idtype);
                            byte[] bytes = OperationPdfUtil.fileToByteArray(realPDFFile);
                            if(Util.isEoN(bytes)){
                                errmsg="未生成文件";
                            }else {
                                File file = new File(realPDFFile);
                                InputStream inputStream = new FileInputStream(file);
                                BladeFile bladeFile = ossBuilder.template().putFile((Util.isEoN(pyd.getPdfname()) ? pdfname : pyd.getPdfname()) + ".pdf", inputStream, sourceid);
                                String templateUrl = this.ossBuilder.template().getTemplateUrl(bladeFile.getLink(), 300);
                                PmsFilePrintRecord record = new PmsFilePrintRecord();
                                record.setId(UUID.randomUUID().toString());
                                record.setLocalfilepath(templateUrl);
                                record.setCreatetime(new Date());
                                record.setCreator(AuthUtil.getUserId());
//                                attachment.setFilesize(bladeFile.get);
                                record.setName(bladeFile.getOriginalName());
                                record.setSourceid(sourceid);
                                record.setSeq(1);
                                record.setCategory(pyd.getModelname());
                                record.setMemo(pyd.getMemo());
                                record.setHref(bladeFile.getFullLink());
//                                attachment.setOrigid();
                                pmsFilePrintRecordService.saveOrUpdate(record);
                                returnToJs.setData(templateUrl);
                            }
                        } catch (IOException e) {
                            errmsg="文件转换出错";
                            e.printStackTrace();
                        } catch (TemplateException e) {
                            errmsg="模板出错";
                            e.printStackTrace();
                        } catch (DocumentException e) {
                            errmsg="文档打开出错";
                            e.printStackTrace();
                        } catch (java.text.ParseException e) {
                            errmsg="类型转换出错";
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                sourceid=request.getParameter(idtype);
//                planStatusMap.put(sourceid,"1");
                errmsg=createPdfOrWordNew.downloadPdfByModelFortypeRedis(pyd,response,request,json, planprojectid);
                //是否生成成功
//                errmsg = createPdfOrWordNew.getRedisDownurl(returnToJs, errmsg, pyd, sourceid,request);
            }
        }else{
            errmsg="未找到该下载类别,需配置。";
            System.out.print("未找到该下载类别");
        }
        //String onlyip=request.getRemoteAddr();
        String onlyip=Util.getIpAddr(request);
        String idValue="";
        String idtype=pyd.getIdtype();
        if(!Util.isEoN(request.getParameter(idtype))){
            idValue=request.getParameter(idtype).toString();
        }
        // 获取当前时间日期，格式化日期作为此次操作的时间并记录到操作记录表中
        Date nowDate = new Date();
        String da = Util.formatDate(nowDate, "yyyy/MM/dd HH:mm:ss");
        // 添加操作记录到SysOperationrecord表中
//        JSONObject jsonRecord = new JSONObject();
//        jsonRecord.accumulate("type", da.substring(0,4)+type+"类型"+"【pdf下载】");
//        jsonRecord.accumulate("operationdate", da);
//        jsonRecord.accumulate("sourceid", idValue);
//        jsonRecord.accumulate("operator", AuthUtil.getUserName());
//        jsonRecord.accumulate("operatorid", AuthUtil.getUserId());
//        jsonRecord.accumulate("lasttimeip", onlyip);
//        sysOperationrecordService.save(jsonRecord);
        if(Util.isEoN(errmsg)){
            returnToJs.setSuccess(true);
        }else{
            returnToJs.setSuccess(false);
            returnToJs.setErrMsg("后台程序有误，"+errmsg+"请联系系统维护员："+Util.linkNumber);
//            log.error("/PmsDown/downloadPdfByConfig.do 方法报错。"+errmsg);
        }
        writer.write(JSONObject.fromObject(returnToJs).toString());
    }


    private String getRealpathString(HttpServletRequest request, PmsDownloadtype pyd, String sourceid, String pdfname, String idtype) {
        String realPDFFile = pyd.getRealpath();
        if ("true".equals(pyd.getInprogram())) {
            realPDFFile = request.getSession().getServletContext().getRealPath("/") + pyd.getRealpath();
        } else {
            realPDFFile = Util.GetFileRealPath(Util.isEoN(realPDFFile) ? "" : realPDFFile);
        }
//        if (Util.isEoN(pdfname)) {//为空就用原来的，否则就是最终路径和文件名集合
//            realPDFFile = pyd.getRealpath() + "/tempdocCg.pdf";
//        } else if (!pdfname.equals(sourceid)) {
//            realPDFFile = realPDFFile + "/" + pdfname + ".pdf";
//        }
        if (pyd.getDownloadtype().indexOf("doc") < 0) {
            realPDFFile = realPDFFile + pyd.getTmppdffile().replace("【" + idtype + "】", sourceid);
        } else {
            realPDFFile = pyd.getTmpwordfile().replace("【" + idtype + "】", sourceid).replace("tempdoc", "tempdoc1");
        }
        return realPDFFile;
    }
}
