package cn.topcheer.pms2.biz.pms;

import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.biz.modules.resource.builder.oss.OssBuilder;
import cn.topcheer.pms2.api.pdfDownload.PmsDownloadtype;
import cn.topcheer.pms2.api.sys.SysParamvalue;
import cn.topcheer.pms2.biz.enums.SysParamDataEnum;
import cn.topcheer.pms2.biz.pdfDownload.impl.PmsDownLoadService;
import cn.topcheer.pms2.biz.pdfDownload.impl.PmsDownconfigService;
import cn.topcheer.pms2.biz.pdfDownload.impl.PmsDownloadtypeService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsAffixService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchInBizService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.sys.SysParamvalueService;
import cn.topcheer.pms2.biz.utils.*;
import com.itextpdf.text.DocumentException;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "CreatePdfOrWordNew")
public class CreatePdfOrWordNew {

    @Autowired
    private OssBuilder ossBuilder;
    @Autowired
    private PmsPlanprojectbatchInBizService planprojectbatchInBizService;
//
//    private static Map<String,String> planStatusMap = ProgressBarController.planStatusMap;
//
//	@Autowired
//	private GetDataMapSource getDataMapSource;
	@Autowired
	private PmsPlanprojectbatchService pmsPlanprojectbatchService;
	@Autowired
    DBHelper dbHelper;
    //    @Autowired
//    DBHelperBusiness dbHelperBusiness;
//	@Autowired
//	CrtContractJbxxService crtContractJbxxService;
//	@Autowired
//	PmsClobService pmsClobService;
	@Autowired
    PmsAffixService pmsAffixService;
//	@Autowired
//	PmsContractService pmsContractService;
    @Autowired
    PmsDownLoadService pmsDownLoadService;
//    @Autowired
//    private TwoDimCodeService twoDimCodeService;
    @Autowired
    private SysParamvalueService sysParamvalueService;
//    @Autowired
//    PmsProjectbaseService pmsProjectbaseService;
//    @Autowired
//    SysOperationrecordService sysOperationrecordService;
//    @Autowired
//    FlowRecordService flowRecordService;
//    @Autowired
//    PmsRewardService pmsRewardService;
//    @Autowired
//    PmsAttachmentService pmsAttachmentService;
//    @Autowired
//    RevDpConfigService revDpConfigService;

    @Autowired
    GenericFetchAndSave genericFetchAndSave;
    //    @Autowired
//    SysDownRecordService sysDownRecordService;
//    @Autowired
//    ElectronicsealchoiceService electronicsealchoiceService;
//    public final Logger log = Logger.getLogger(getClass());
//    @Autowired
//    SysVersionService sysVersionService;
//    @Autowired
//    private BatchVersionService batchVersionService;
    @Autowired
    PmsDownloadtypeService pmsDownloadtypeService;

    @Autowired
    PmsDownconfigService pmsDownconfigService;

//
//
//
//	/**
//	 * 批量生成pdf
//	 *
//	 * @param projectbaseid
//	 * @param projectbatchid
//	 * @param projectbaseName
//	 * @param modelFilePath
//	 * @param tmpWordFile
//	 * @param tmpPDFFile
//	 * @param waterMarkName
//	 * @param opacity
//	 * @param response
//	 * @throws MalformedTemplateNameException
//	 * @throws ParseException
//	 * @throws IOException
//	 * @throws TemplateException
//	 * @throws DocumentException
//	 */
//	public void PL_downloadPdfByModel(String projectbaseid,
//			String projectbatchid, String projectbaseName,
//			String modelFilePath, String tmpWordFile, String tmpPDFFile,
//			String waterMarkName, float opacity, HttpServletResponse response)
//			throws MalformedTemplateNameException, ParseException, IOException,
//			TemplateException, DocumentException {
//
//		byte[] bytes = this.generatePdf(projectbaseid, projectbatchid,
//				modelFilePath, tmpWordFile, tmpPDFFile, waterMarkName, opacity,"");
//
//	}
//
	/**
	 * 通过模板生成pdf的方式向用户浏览器提供下载pdf的功能
	 *
	 * @param response
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 * @throws DocumentException
	 */
	public void downloadPdfByModelFortype(String sourceid, String pdfname, String plOrnot, PmsDownloadtype pyd,
                                          HttpServletResponse response, HttpServletRequest request, String ...otherInfo)
            throws MalformedTemplateNameException, ParseException, IOException,
            TemplateException, DocumentException, java.text.ParseException {
        // 获取项目全路径
        String errmsg="";
        String filePath = pyd.getInprogram();//"D:/"
        if("true".equals(pyd.getInprogram())){
            filePath=request.getSession().getServletContext().getRealPath("/");
        }else{
            filePath= Util.GetFileRealPath("");
        }
        // 创建属于该项目id的文件夹
        String tmpFolderPath = filePath + "/PdfTmpFile/Allpdf/pdf";
        File tmpFolderPathFile = new File(tmpFolderPath);
        if (!tmpFolderPathFile.exists()) {
            tmpFolderPathFile.mkdirs();
        }
        // 存储生成后的word临时文件路径
        String tmpWordFile = pyd.getTmpwordfile();//tmpFolderPath + "/tempdocCg.doc";
        // 存储生成后的pdf临时文件路径
        String tmpPDFFile = pyd.getTmppdffile();//tmpFolderPath + "/tempdocCg.pdf";
        String idtype=pyd.getIdtype()+"";
        if(tmpWordFile.indexOf(idtype)>-1){//20200414---分项目id放文件夹
            tmpWordFile=filePath+tmpWordFile.replace("【"+idtype+"】",sourceid);
            System.out.println("tmpWordFile："+tmpWordFile);
        }
        if(tmpPDFFile.indexOf(idtype)>-1){
            tmpPDFFile=filePath+tmpPDFFile.replace("【"+idtype+"】",sourceid);
            System.out.println("tmpPDFFile："+tmpPDFFile);
        }
        // 选择使用的模板路径
//        String modelFilePath = request.getSession().getServletContext()
//                .getRealPath("/")
//                + "pdfmodel";
//        String modelFilePath ="target/classes/pdfmodel";
//        String modelFilePath ="D:/workspace/pms2/pms2-java/pms2-web/src/main/resources/pdfmodel";
        String modelFilePath =  getClass().getClassLoader().getResource("pdfmodel").getPath();
		Map<String, Object> hashmap = new HashMap<String, Object>();
        /*各个参数*/
        String justdown = pyd.getJustdown()+"";//request.getParameter("justdown")
		String openname = pyd.getOpenname()+"";//"";
        if(!Util.isEoN(pyd.getOpennamesql())){
            openname=this.dbHelper.getOnlyStringValue(pyd.getOpennamesql(),new Object[]{sourceid});
        }
        String downLoadType = pyd.getDownloadtype()+"";//下载格式，如果是doc，加个赋为doc"pdf";
        String methodparamtyp=pyd.getMethodparamtype()+"";//调用数据源传参个数
        try {
            //WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            //Class cls = wac.getBean(pyd.getMethodservice()+"").getClass();//pyd.getMethodservice()+""  PmsAwardService
            //cls.getMethod(pyd.getMethodname()+"", new Class[]{JSONArray.class,String.class}).invoke(wac.getBean(pyd.getMethodservice()+""), new Object[]{"",""});
            if("request".equals(methodparamtyp)){
                hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{request});
            }else if("requestAndId".equals(methodparamtyp)){
                hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{request,sourceid});
            }else if("id".equals(methodparamtyp)){
                if((otherInfo.length == 2) && !Util.isEoN(otherInfo[0]) && !Util.isEoN(Util.checkFromJson(otherInfo[0]))
                        && Util.checkFromJson(otherInfo[0]).containsKey("dataObject") && Util.checkFromJson(otherInfo[0]).containsKey("type")) {
//                    JSONObject json = Util.checkFromJson(otherInfo[0]);
//                    //判断参数为配置版本还是通用版本
//                    if(Util.isEoN(json.get("type")) || "normal".equals(json.get("type"))) {
//                        hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{sourceid});
//                    }else if(!Util.isEoN(json.get("businesstype"))&&(Util.isEoN(json.getJSONObject("dataObject"))||json.getJSONObject("dataObject").size()==0)){
//                        // 新增传businesstype和sourceid下载，不用前台传参,当businesstype有值，dataObject没值
//                        String batchid = otherInfo[1];
//                        String type = json.get("businesstype").toString();
//                        JSONObject dataObject =getConfigData(request,sourceid,batchid,type);
//                        hashmap = genericFetchAndSave.downloadData(dataObject, sourceid, batchid, request);
//                    } else {
//                        String batchid = otherInfo[1];
//                        JSONObject dataObject = json.getJSONObject("dataObject");
//                        hashmap = genericFetchAndSave.downloadData(dataObject, sourceid, batchid, request);
//                    }
                } else {
                    hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{sourceid});
                }
            }else if("getDataMap".equals(methodparamtyp)){
                hashmap = pmsDownLoadService.getDataMap(sourceid,pyd.getMemo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            errmsg="获取数据源失败";
//            log.error("/PmsDown/downloadPdfByConfig.do 方法报错。"+errmsg);
        }
        //用来生成_PDF格式的数据源
        hashmap = GenericSpecialPDFMap(hashmap);
        if (!Util.isEoN(pyd.getTwodimsql())) {
//            String TwoDimImgPath=twoDimCodeService.getTwoDimImgPath(sourceid,pyd);
//            hashmap.put("TwoDimImgPath",TwoDimImgPath);
        }

		if (!"D://PdfTmpFile/Allpdf/pdf/tempdocCg.doc".equals(tmpWordFile)) {
			tmpFolderPathFile = new File(tmpWordFile.replace(
					"/tempdocCg.doc", "").replace("tempdoc.doc",""));//tempdoc.doc也替换下
			if (!tmpFolderPathFile.exists()) {
				tmpFolderPathFile.mkdirs();
			}
		}
        byte[] bytes =null;
        if(justdown.equals("true")){
            // 将生成好的pdf文件转byte数组
            bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
        }else{
            bytes = this.generatePdfByModelFortype(pyd,sourceid,pdfname,modelFilePath,
                    tmpWordFile, tmpPDFFile,hashmap,request);
        }
        if(plOrnot.equals("false")){//批量的不提供
		// 通过response向客户提供下载
            if(justdown.equals("true")||justdown.equals("")){
                if(downLoadType.equals("doc")){
                    bytes = OperationPdfUtil.fileToByteArray(tmpWordFile);
                    OperationPdfUtil.outResponseword(response,openname,bytes);
                }
		        OperationPdfUtil.outResponse(response, openname, bytes);
            }
        }
	}
//    public void downloadPdfByModelFortypeByJson(JSONObject jo,String pdfname,String plOrnot,PmsDownloadtype pyd,
//                                          HttpServletResponse response, HttpServletRequest request, String ...otherInfo)
//            throws MalformedTemplateNameException, ParseException, IOException,
//            TemplateException, DocumentException, java.text.ParseException {
//        // 获取项目全路径
//        String errmsg="";
//        String filePath = pyd.getInprogram();//"D:/"
//        if("true".equals(pyd.getInprogram())){
//            filePath=request.getSession().getServletContext().getRealPath("/");
//        }else{
//            filePath=Util.GetFileRealPath("");
//        }
//        // 创建属于该项目id的文件夹
//        String tmpFolderPath = filePath + "/PdfTmpFile/Allpdf/pdf";
//        File tmpFolderPathFile = new File(tmpFolderPath);
//        if (!tmpFolderPathFile.exists()) {
//            tmpFolderPathFile.mkdirs();
//        }
//        // 存储生成后的word临时文件路径
//        String tmpWordFile = pyd.getTmpwordfile();//tmpFolderPath + "/tempdocCg.doc";
//        // 存储生成后的pdf临时文件路径
//        String tmpPDFFile = pyd.getTmppdffile();//tmpFolderPath + "/tempdocCg.pdf";
//        String idtype=pyd.getIdtype()+"";
//        if(tmpWordFile.indexOf(idtype)>-1){//20200414---分项目id放文件夹
//            tmpWordFile=filePath+tmpWordFile.replace("【"+idtype+"】",jo.get("id")+"");
//            System.out.println("tmpWordFile："+tmpWordFile);
//        }
//        if(tmpPDFFile.indexOf(idtype)>-1){
//            tmpPDFFile=filePath+tmpPDFFile.replace("【"+idtype+"】",jo.get("id")+"");
//            System.out.println("tmpPDFFile："+tmpPDFFile);
//        }
//        // 选择使用的模板路径
//        String modelFilePath = request.getSession().getServletContext()
//                .getRealPath("/")
//                + "pdfmodel";
//        Map<String, Object> hashmap = new HashMap<String, Object>();
//        /*各个参数*/
//        String justdown = pyd.getJustdown()+"";//request.getParameter("justdown")
//        String openname = pyd.getOpenname()+"";//"";
//        if(!Util.isEoN(pyd.getOpennamesql())){
//            openname=this.dbHelper.getOnlyStringValue(pyd.getOpennamesql(),new Object[]{jo.get("id")+""});
//        }
//        String downLoadType = pyd.getDownloadtype()+"";//下载格式，如果是doc，加个赋为doc"pdf";
//        String methodparamtyp=pyd.getMethodparamtype()+"";//调用数据源传参个数
//        try {
//            //WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//            //Class cls = wac.getBean(pyd.getMethodservice()+"").getClass();//pyd.getMethodservice()+""  PmsAwardService
//            //cls.getMethod(pyd.getMethodname()+"", new Class[]{JSONArray.class,String.class}).invoke(wac.getBean(pyd.getMethodservice()+""), new Object[]{"",""});
//            if("request".equals(methodparamtyp)){
//                hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{request,jo});
//            }else if("requestAndId".equals(methodparamtyp)){
//                hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{request,jo});
//            }else if("id".equals(methodparamtyp)){
//                if((otherInfo.length == 2) && !Util.isEoN(otherInfo[0]) && !Util.isEoN(Util.checkFromJson(otherInfo[0]))
//                        && Util.checkFromJson(otherInfo[0]).containsKey("dataObject") && Util.checkFromJson(otherInfo[0]).containsKey("type")) {
//                    JSONObject json = Util.checkFromJson(otherInfo[0]);
//                    //判断参数为配置版本还是通用版本
//                    if(Util.isEoN(json.get("type")) || "normal".equals(json.get("type"))) {
//                        hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{jo});
//                    }else if(!Util.isEoN(json.get("businesstype"))&&(Util.isEoN(json.getJSONObject("dataObject"))||json.getJSONObject("dataObject").size()==0)){
//                        // 新增传businesstype和sourceid下载，不用前台传参,当businesstype有值，dataObject没值
//                        String batchid = otherInfo[1];
//                        String type = json.get("businesstype").toString();
//                        JSONObject dataObject =getConfigData(request,jo.get("id")+"",batchid,type);
//                        hashmap = genericFetchAndSave.downloadData(dataObject, jo.get("id")+"", batchid, request);
//                    } else {
//                        String batchid = otherInfo[1];
//                        JSONObject dataObject = json.getJSONObject("dataObject");
//                        hashmap = genericFetchAndSave.downloadData(dataObject, jo.get("id")+"", batchid, request);
//                    }
//                } else {
//                    hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{jo});
//                }
//            }else if("getDataMap".equals(methodparamtyp)){
//                hashmap = pmsDownLoadService.getDataMap(jo.get("id")+"",pyd.getMemo());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            errmsg="获取数据源失败";
//            log.error("/PmsDown/downloadPdfByConfig.do 方法报错。"+errmsg);
//        }
//        //用来生成_PDF格式的数据源
//        hashmap = GenericSpecialPDFMap(hashmap);
//        if (!Util.isEoN(pyd.getTwodimsql())) {
//            String TwoDimImgPath=twoDimCodeService.getTwoDimImgPath(jo.get("id")+"",pyd);
//            hashmap.put("TwoDimImgPath",TwoDimImgPath);
//        }
//
//        if (!"D://PdfTmpFile/Allpdf/pdf/tempdocCg.doc".equals(tmpWordFile)) {
//            tmpFolderPathFile = new File(tmpWordFile.replace(
//                    "/tempdocCg.doc", "").replace("tempdoc.doc",""));//tempdoc.doc也替换下
//            if (!tmpFolderPathFile.exists()) {
//                tmpFolderPathFile.mkdirs();
//            }
//        }
//        byte[] bytes =null;
//        if(justdown.equals("true")){
//            // 将生成好的pdf文件转byte数组
//            bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//        }else{
//            bytes = this.generatePdfByModelFortype(pyd,jo.get("id")+"",pdfname,modelFilePath,
//                    tmpWordFile, tmpPDFFile,hashmap,request);
//        }
//        if(plOrnot.equals("false")){//批量的不提供
//            // 通过response向客户提供下载
//            if(justdown.equals("true")||justdown.equals("")){
//                if(downLoadType.equals("doc")){
//                    bytes = OperationPdfUtil.fileToByteArray(tmpWordFile);
//                    OperationPdfUtil.outResponseword(response,openname,bytes);
//                }
//                OperationPdfUtil.outResponse(response, openname, bytes);
//            }
//        }
//    }
//
//    //同上 用于生成pdf 加一个是看看能不能改为多线程用
//    /*public void downloadPdfByModelFortypeByJsonNew(JSONObject jo,String pdfname,String filePath ,String plOrnot,PmsDownloadtype pyd,
//                                                HttpServletResponse response, HttpServletRequest request,SecurityUser securityUser,String ip,
//                                                   String ...otherInfo)
//            throws  IOException, TemplateException, DocumentException {
//        // 获取项目全路径
//        String errmsg="";
//        String batchid1 = request.getParameter("batchid"); //值为null 这里是测试request有没有用，因为反射获取数据源的时候还是有可能会用到request
//        log.info(Thread.currentThread().getName() +"的batchid是:"+batchid1);
//        // 创建属于该项目id的文件夹
//        String tmpFolderPath;
//        if ("true".equals(pyd.getInprogram())){
//            tmpFolderPath = filePath + "/PdfTmpFile/Allpdf/pdf";
//        }else {
//            tmpFolderPath = Util.GetFileRealPath("");
//        }
//
//        File tmpFolderPathFile = new File(tmpFolderPath);
//        if (!tmpFolderPathFile.exists()) {
//            tmpFolderPathFile.mkdirs();
//        }
//        // 存储生成后的word临时文件路径
//        String tmpWordFile = pyd.getTmpwordfile();//tmpFolderPath + "/tempdocCg.doc";
//        // 存储生成后的pdf临时文件路径
//        String tmpPDFFile = pyd.getTmppdffile();//tmpFolderPath + "/tempdocCg.pdf";
//        String idtype=pyd.getIdtype()+"";
//        if(tmpWordFile.indexOf(idtype)>-1){//20200414---分项目id放文件夹
//            tmpWordFile=filePath+tmpWordFile.replace("【"+idtype+"】",jo.get("id")+"");
//            System.out.println("tmpWordFile："+tmpWordFile);
//        }
//        if(tmpPDFFile.indexOf(idtype)>-1){
//            tmpPDFFile=filePath+tmpPDFFile.replace("【"+idtype+"】",jo.get("id")+"");
//            System.out.println("tmpPDFFile："+tmpPDFFile);
//        }
//        // 选择使用的模板路径
//        String modelFilePath = filePath + "pdfmodel";
//        Map<String, Object> hashmap = new HashMap<String, Object>();
//        *//*各个参数*//*
//        String justdown = pyd.getJustdown()+"";//request.getParameter("justdown")
//        String openname = pyd.getOpenname()+"";//"";
//        if(!Util.isEoN(pyd.getOpennamesql())){
//            openname=this.dbHelper.getOnlyStringValue(pyd.getOpennamesql(),new Object[]{jo.get("id")+""});
//        }
//        String downLoadType = pyd.getDownloadtype()+"";//下载格式，如果是doc，加个赋为doc"pdf";
//        String methodparamtyp=pyd.getMethodparamtype()+"";//调用数据源传参个数
//        try {
//            //WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//            //Class cls = wac.getBean(pyd.getMethodservice()+"").getClass();//pyd.getMethodservice()+""  PmsAwardService
//            //cls.getMethod(pyd.getMethodname()+"", new Class[]{JSONArray.class,String.class}).invoke(wac.getBean(pyd.getMethodservice()+""), new Object[]{"",""});
//            if("request".equals(methodparamtyp)){
//                hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{request,jo});
//            }else if("requestAndId".equals(methodparamtyp)){
//                hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{request,jo});
//            }else if("id".equals(methodparamtyp)){
//                if((otherInfo.length == 2) && !Util.isEoN(otherInfo[0]) && !Util.isEoN(Util.checkFromJson(otherInfo[0]))
//                        && Util.checkFromJson(otherInfo[0]).containsKey("dataObject") && Util.checkFromJson(otherInfo[0]).containsKey("type")) {
//                    JSONObject json = Util.checkFromJson(otherInfo[0]);
//                    //判断参数为配置版本还是通用版本
//                    if(Util.isEoN(json.get("type")) || "normal".equals(json.get("type"))) {
//                        hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{jo});
//                    }else if(!Util.isEoN(json.get("businesstype"))&&(Util.isEoN(json.getJSONObject("dataObject"))||json.getJSONObject("dataObject").size()==0)){
//                        // 新增传businesstype和sourceid下载，不用前台传参,当businesstype有值，dataObject没值
//                        String batchid = otherInfo[1];
//                        String type = json.get("businesstype").toString();
//                        JSONObject dataObject =getConfigData(request,jo.get("id")+"",batchid,type);
//                        hashmap = genericFetchAndSave.downloadData(dataObject, jo.get("id")+"", batchid, request);
//                    } else {
//                        String batchid = otherInfo[1];
//                        JSONObject dataObject = json.getJSONObject("dataObject");
//                        hashmap = genericFetchAndSave.downloadData(dataObject, jo.get("id")+"", batchid, request);
//                    }
//                } else {
//                    hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{jo});
//                }
//            }else if("getDataMap".equals(methodparamtyp)){
//                hashmap = pmsDownLoadService.getDataMap(jo.get("id")+"",pyd.getMemo());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            errmsg="获取数据源失败";
//            log.error("/PmsDown/downloadPdfByConfig.do 方法报错。"+errmsg);
//        }
//        //用来生成_PDF格式的数据源
//        hashmap = GenericSpecialPDFMap(hashmap);
//        if (!Util.isEoN(pyd.getTwodimsql())) {
//            String TwoDimImgPath=twoDimCodeService.getTwoDimImgPath(jo.get("id")+"",pyd);
//            hashmap.put("TwoDimImgPath",TwoDimImgPath);
//        }
//
//        if (!"D://PdfTmpFile/Allpdf/pdf/tempdocCg.doc".equals(tmpWordFile)) {
//            tmpFolderPathFile = new File(tmpWordFile.replace(
//                    "/tempdocCg.doc", "").replace("tempdoc.doc",""));//tempdoc.doc也替换下
//            if (!tmpFolderPathFile.exists()) {
//                tmpFolderPathFile.mkdirs();
//            }
//        }
//        byte[] bytes =null;
//        if(justdown.equals("true")){
//            // 将生成好的pdf文件转byte数组
//            bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//        }else{
//            bytes = this.generatePdfByModelFortype(pyd,jo.get("id")+"",pdfname,modelFilePath,
//                    tmpWordFile, tmpPDFFile,hashmap,securityUser , ip);
//        }
//        if(plOrnot.equals("false")){//批量的不提供
//            // 通过response向客户提供下载
//            if(justdown.equals("true")||justdown.equals("")){
//                if(downLoadType.equals("doc")){
//                    bytes = OperationPdfUtil.fileToByteArray(tmpWordFile);
//                    OperationPdfUtil.outResponseword(response,openname,bytes);
//                }
//                OperationPdfUtil.outResponse(response, openname, bytes);
//            }
//        }
//    }*/
//
	/**
	 * 通用下载--用于单独的pdf选择
	 *
	 * @return
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 * @throws DocumentException
	 */

	public byte[] generatePdfByModelFortype(PmsDownloadtype pyd,String sourceid,String pdfname,
                                            String modelFilePath,String tmpWordFile, String tmpPDFFile,Map<String, Object> hashmap,
                                            HttpServletRequest request) throws MalformedTemplateNameException,
			ParseException, IOException, TemplateException, DocumentException {

        // 需要添加水印的内容
        String waterMarkName = pyd.getWatername();
        String modelName = pyd.getModelname()+"";//"expertSignCg.xml"
        String waterMarkStyle = pyd.getWatermarkstyle()+"";//水印样式，如果是Watermark则样式是布满全局，其他则在下方
        float opacity = Float.parseFloat(pyd.getOpacity());//0.6f;
        String generateType= pyd.getGeneratetype()+"";//生成方式，用书签还是freemark "free",book
        String idtype=pyd.getIdtype()+"";//id传参参数
        String[]  ModelOrAffixlis;
        /*处理下条件判断*/
        String ModeloraffixlistS=this.pmsDownconfigService.getString(sourceid,pyd.getModeloraffixlist());
        if(Util.isEoN(ModeloraffixlistS)){
              ModelOrAffixlis=new String[]{};
        }else{
            ModelOrAffixlis=ModeloraffixlistS.split("；");
        }
        if(pyd.getType().equals("xmryqd")){
            if(!Util.isEoN(hashmap.get("review_method"))&&(hashmap.get("review_method").toString().equals("现场考察"))){
                ModelOrAffixlis=new String[]{"xmryqd.xml&book"};
            }else{
                ModelOrAffixlis=new String[]{"xmryqdhy.xml&book"};
            }
        }
        byte[] bytes=null;
        // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
        SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
        String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
        if (ModelOrAffixlis.length<1) {
            if("book".equals(generateType)){
                WordUtil.createPdfByordanalyzeNewToPdf(modelFilePath, modelName, tmpWordFile, tmpPDFFile,
                        hashmap,toPdfType);
            }else{
                WordUtil.createPdfNewToPdf(modelFilePath, modelName, tmpWordFile, tmpPDFFile,
                        hashmap,toPdfType);
            }
            // 将生成好的pdf文件转byte数组
            bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
            String destinationFileName = tmpPDFFile;
            // 如果水印名称waterMarkName不为空字符串，说明需要添加水印，并替换以前生成的pdf文件
            if (waterMarkName != "") {
                File file = new File(destinationFileName);
                FileOutputStream outPutStream = new FileOutputStream(file);
                if(waterMarkStyle.equals("Watermark")){//水印样式
                    OperationPdfUtil.setWatermark(bytes, outPutStream, waterMarkName,
                            opacity);
                }else if(waterMarkStyle.equals("Watermarkhx")){
                    OperationPdfUtil.setWatermarkhx(bytes, outPutStream, waterMarkName,
                            opacity);
                }else{
                    OperationPdfUtil.setWatermark01(bytes, outPutStream, waterMarkName,
                            opacity);
                }
                bytes = OperationPdfUtil.fileToByteArray(destinationFileName);
            }
            String realpath=pyd.getRealpath()+"";//最终pdf放的路径
            File realpathFile = new File(realpath);
            try {
                WordUtil.copy(destinationFileName, realpathFile+"/"+pdfname+".pdf");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bytes;
        } else {
//            planStatusMap.put(sourceid,"2");
            bytes=this.jointAffixAndmoreModelForDown(request,pyd,sourceid,pdfname,ModelOrAffixlis,modelFilePath,tmpWordFile,tmpPDFFile,waterMarkName,waterMarkStyle,opacity,hashmap);
            return bytes;
        }

	}
//
//    /*public byte[] generatePdfByModelFortype(PmsDownloadtype pyd,String sourceid,String pdfname,
//                                            String modelFilePath,String tmpWordFile, String tmpPDFFile,Map<String, Object> hashmap,
//                                            SecurityUser securityUser ,String ip) throws MalformedTemplateNameException,
//            ParseException, IOException, TemplateException, DocumentException {
//
//        // 需要添加水印的内容
//        String waterMarkName = pyd.getWatername();
//        String modelName = pyd.getModelname()+"";//"expertSignCg.xml"
//        String waterMarkStyle = pyd.getWatermarkstyle()+"";//水印样式，如果是Watermark则样式是布满全局，其他则在下方
//        float opacity = Float.parseFloat(pyd.getOpacity());//0.6f;
//        String generateType= pyd.getGeneratetype()+"";//生成方式，用书签还是freemark "free",book
//        String idtype=pyd.getIdtype()+"";//id传参参数
//        String[]  ModelOrAffixlis;
//        *//*处理下条件判断*//*
//        String ModeloraffixlistS=this.pmsDownconfigService.getString(sourceid,pyd.getModeloraffixlist());
//        if(Util.isEoN(ModeloraffixlistS)){
//            ModelOrAffixlis=new String[]{};
//        }else{
//            ModelOrAffixlis=ModeloraffixlistS.split("；");
//        }
//        if(pyd.getType().equals("xmryqd")){
//            if(!Util.isEoN(hashmap.get("review_method"))&&(hashmap.get("review_method").toString().equals("现场考察"))){
//                ModelOrAffixlis=new String[]{"xmryqd.xml&book"};
//            }else{
//                ModelOrAffixlis=new String[]{"xmryqdhy.xml&book"};
//            }
//        }
//        byte[] bytes=null;
//        // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
//        SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
//        String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
//        if (ModelOrAffixlis.length<1) {
//            if("book".equals(generateType)){
//                WordUtil.createPdfByordanalyzeNewToPdf(modelFilePath, modelName, tmpWordFile, tmpPDFFile,
//                        hashmap,toPdfType);
//            }else{
//                WordUtil.createPdfNewToPdf(modelFilePath, modelName, tmpWordFile, tmpPDFFile,
//                        hashmap,toPdfType);
//            }
//            // 将生成好的pdf文件转byte数组
//            bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//            String destinationFileName = tmpPDFFile;
//            // 如果水印名称waterMarkName不为空字符串，说明需要添加水印，并替换以前生成的pdf文件
//            if (waterMarkName != "") {
//                File file = new File(destinationFileName);
//                FileOutputStream outPutStream = new FileOutputStream(file);
//                if(waterMarkStyle.equals("Watermark")){//水印样式
//                    OperationPdfUtil.setWatermark(bytes, outPutStream, waterMarkName,
//                            opacity);
//                }else{
//                    OperationPdfUtil.setWatermark01(bytes, outPutStream, waterMarkName,
//                            opacity);
//                }
//                bytes = OperationPdfUtil.fileToByteArray(destinationFileName);
//            }
//            String realpath=pyd.getRealpath()+"";//最终pdf放的路径
//            File realpathFile = new File(realpath);
//            try {
//                WordUtil.copy(destinationFileName, realpathFile+"/"+pdfname+".pdf");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return bytes;
//        } else {
//            planStatusMap.put(sourceid,"2");
//            bytes=this.jointAffixAndmoreModelForDownNew(securityUser , ip ,pyd,sourceid,pdfname,ModelOrAffixlis,modelFilePath,tmpWordFile,tmpPDFFile,waterMarkName,waterMarkStyle,opacity,hashmap);
//            return bytes;
//        }
//
//    }*/
//
//
///*---------------------------------------------------申报下载----------------------------------------------------------------------------------------*/
//
//    public void downloadPdfNew_Public(HttpServletRequest request, HttpServletResponse response, float opacity, String projectbaseid, String justdown) throws IOException, TemplateException, DocumentException {
//        PmsProjectbase projectbase = null;
//        String TwoDimImgPath="";
//        // 获取项目名称和批次名称
//        projectbase = pmsProjectbaseService.findById(projectbaseid);
//        // 获取项目名称和批次名称
//        PmsReward pmsrward=null;
//        pmsrward = pmsRewardService.findById(projectbaseid);
//        String projectbaseName = "";
//        String projectbatchid = "";
//        String isAddwatermark ="";
//        Date updateLastTime =null;
//        Date modelupdatetime =null;//模板更新时间
//
//        //根据对应的项目批次获取对应的项目pdf存储路径
//        String filePath = "D:/PdfTmpFile/PmsProjectbase";
//        String batchname="未定义";
//        /*String pdfurlsql="select b.pdfurlforbase,b.name from pms_planprojectbatch b inner join pms_projectbase p on b.id=p.planprojectbatchid where p.id=?";
//        List<Map> lis=dbHelper.getRows(pdfurlsql, new Object[]{projectbaseid});*/
//        if(pmsrward!=null){//如果科技奖主表不为空
//            TwoDimImgPath=twoDimCodeService.getTwoDimImgPath(projectbaseid,"奖励");
//            projectbaseName=pmsrward.getApplicationno();
//            projectbatchid=pmsrward.getPlanprojectbatchid();
//            /*if(!Util.isEoN(pmsrward.getOldplanbatchid())){//如果有oldplanbatchid说明在评审中
//                projectbatchid=pmsrward.getOldplanbatchid()+"";
//            }*/
//            isAddwatermark=pmsrward.getIsaddwatermark();
//            updateLastTime=pmsrward.getSavedate();
//            /*路径*/
//            PmsPlanprojectbatch pl=pmsPlanprojectbatchService.findById(projectbatchid);
//            if(pl!=null){
//                filePath=pl.getPdfurlforbase();
//                batchname=pl.getName();
//                modelupdatetime=pl.getModelupdatetime();
//            }
//        }else {//否则是项目的
//            TwoDimImgPath=twoDimCodeService.getTwoDimImgPath(projectbaseid,"申报书");
//            if (projectbase.getProjectbasename() != null)
//                projectbaseName = projectbase.getProjectbasename().trim();
//            else
//                projectbaseName = "";
//            projectbatchid=projectbase.getPlanprojectbatchid();
//            /*if(!Util.isEoN(projectbase.getOldplanbatchid())){//如果有oldplanbatchid说明在评审中
//                projectbatchid=projectbase.getOldplanbatchid()+"";
//            }*/
//            isAddwatermark = projectbase.getIsaddwatermark();
//            updateLastTime = projectbase.getUpdatelasttime();
//            /*路径*/
//            PmsPlanprojectbatch pl=pmsPlanprojectbatchService.findById(projectbatchid);
//            if(pl!=null){
//                filePath=pl.getPdfurlforbase();
//                batchname=pl.getName();
//                modelupdatetime=pl.getModelupdatetime();
//            }
//        }
//        // 创建属于该项目id的文件夹
//        String tmpFolderPath = filePath +"/"+batchname+"/"+ projectbaseid;
//        if("137a40ed-0592-459e-8f63-11869fc5f8e8".equals(projectbaseid)){//文件释放不出来
//            tmpFolderPath = filePath +"/"+batchname+"/"+ projectbaseid+"BUG";
//        }
//        File tmpFolderPathFile = new File(tmpFolderPath);
//        if (!tmpFolderPathFile.exists()) {
//            tmpFolderPathFile.mkdirs();
//        }
//        // 存储生成后的word临时文件路径
//        String tmpWordFile = tmpFolderPath + "/tempdoc.doc";
//        // 存储生成后的pdf临时文件路径
//        String tmpPDFFile = tmpFolderPath + "/tempdoc.pdf";
//        // 选择使用的模板路径
//        String modelFilePath = request.getSession().getServletContext().getRealPath("/") + "pdfmodel";
//        if("false".equals(justdown)){
//            planStatusMap.put(projectbaseid,"2");
//            // 需要添加水印的内容
//            String waterMarkName = "";
//            SysParamvalue sysPrintParam = sysParamvalueService.findById("488899");
//            if (!Util.isEoN(isAddwatermark)) {
//                waterMarkName = "审核通过";
//                if (sysPrintParam != null) {
//                    try {
//                        String temValue = sysPrintParam.getValue();
//                        opacity = Float.parseFloat(temValue);
//                    } catch (Exception e) {
//                        Log.error(e.getMessage() + "--申请表水印转换", e);
//                    }
//                } else {
//                    opacity = 0.6f;// 水印的透明度
//                }
//            }else{
//                opacity = 0.6f;// 水印的透明度
//            }
//            //用个公用的,判断是否要重新生成-------开始
//            Boolean isdownloadPdfByModel=this.needCreateForAll(projectbaseid,projectbaseName,tmpPDFFile,modelupdatetime,updateLastTime,"sb");
//            if(isdownloadPdfByModel){
//                // 通过模板生成pdf文件，并提供下载
//                this.downloadPdfByModelNew(projectbaseid,
//                        projectbatchid, projectbaseName, modelFilePath,
//                        tmpWordFile, tmpPDFFile, waterMarkName, opacity,
//                        response,TwoDimImgPath);
//                String recordid=this.sysDownRecordService.commonSaveOperation(request,projectbaseid,"pdf生成","项目名称："+projectbaseName,tmpPDFFile,"sb","");
//            }
//            //用个公用的-------结束
//        }else{
//            planStatusMap.put(projectbaseid,"10");
//            // 通过获取本地pdf的方式向用户浏览器提供下载pdf的功能
//            this.downloadByLocal(projectbaseName,
//                    tmpPDFFile, response,"pdf");
//        }
//        this.sysOperationrecordService.commonSaveOperation(request,projectbaseid,projectbaseName + projectbaseid+ "【pdf下载】","项目id:"+projectbaseid);
//    }
//
//    /**
//     * 通过模板生成pdf的方式向用户浏览器提供下载pdf的功能---拷贝上面的，因为要多传个东西避免影响其他调用的，所以新加了个
//     *
//     *  isjust(是否直接下载)
//     * @param projectbaseid
//     * @param projectbatchid
//     * @param projectbaseName
//     * @param modelFilePath
//     *            （word模板路径）
//     * @param tmpWordFile
//     *            （生成后的word文件）
//     * @param tmpPDFFile
//     *            （生成后的word文件）
//     * @param waterMarkName
//     *            （添加水印的内容，如果为空字符串，表示不需添加水印）
//     * @param response
//     * @throws MalformedTemplateNameException
//     * @throws ParseException
//     * @throws IOException
//     * @throws TemplateException
//     * @throws DocumentException
//     */
//    public void downloadPdfByModelNew(String projectbaseid, String projectbatchid,
//                                      String projectbaseName, String modelFilePath, String tmpWordFile,
//                                      String tmpPDFFile, String waterMarkName, float opacity,
//                                      HttpServletResponse response,String TwoDimImgPath)
//            throws MalformedTemplateNameException, ParseException, IOException,
//            TemplateException, DocumentException {
//
//        byte[] bytes=null;
//            bytes = this.generatePdf(projectbaseid, projectbatchid,
//                    modelFilePath, tmpWordFile, tmpPDFFile, waterMarkName, opacity,TwoDimImgPath);
//
//    }
//
//
//    /**
//     * @author jzz 那么将项目的pdf和附件进行拼接，附件在最后--------------------------------------附件拼接进去，公用方法------------------------------------
//     * @return
//     */
//    public byte[] jointAffixEndModel(String projectbaseid,String modelName,String[] affixList,
//                                     String projectbatchid, String modelFilePath, String tmpWordFile,
//                                     String tmpPDFFile, String waterMarkName, float opacity)
//            throws MalformedTemplateNameException, ParseException, IOException,
//            TemplateException, DocumentException {
//        // 根据批次id获取相应的Map数据源
//        Map<String, Object> hashmap = pmsDownLoadService.getDataMap(projectbaseid,"sb");
//        // 根据批次id获取相应的模板名称
//        // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
//        SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
//        String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
//        WordUtil.createPdfNewToPdf(modelFilePath, modelName,
//                tmpWordFile.replace("tempdoc.doc", "tempdoc1.doc"),
//                tmpPDFFile.replace("tempdoc.pdf", "tempdoc1.pdf"), hashmap,toPdfType);
//        List<String> filesInFolder = new ArrayList<String>();
//        filesInFolder.add(tmpPDFFile.replace("tempdoc.pdf", "tempdoc1.pdf"));
//        if(affixList.length>0){
//            for(int i=0;i<affixList.length;i++){
//                // 将所有的扫描件转换成PDF，为了下一步进行拼接
//                try {
//                    pmsAffixService.getuploadFileAttachment(
//                            filesInFolder,
//                            projectbaseid,
//                            tmpPDFFile.substring(0,
//                                    tmpPDFFile.lastIndexOf("/tempdoc.pdf")),affixList[i]);
//                } catch (Exception e2) {
//                    e2.printStackTrace();
//                }
//
//            }}
//        tmpPDFFile = tmpPDFFile.substring(0,
//                tmpPDFFile.lastIndexOf("/tempdoc.pdf"));
//        PDFMergerUtility mergePdf = new PDFMergerUtility();
//        String destinationFileName = tmpPDFFile + "/" + projectbaseid + ".pdf";
//        for (int i = 0; i < filesInFolder.size(); i++)
//            mergePdf.addSource(filesInFolder.get(i));
//
//        mergePdf.setDestinationFileName(destinationFileName);
//        try {
//            mergePdf.mergeDocuments();
//        } catch (COSVisitorException e1) {
//
//            e1.printStackTrace();
//        }
//
//        byte[] buffer = null;
//        try {
//            File file = new File(destinationFileName);
//            FileInputStream fis = new FileInputStream(file);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
//            byte[] b = new byte[1000];
//            int n;
//            while ((n = fis.read(b)) != -1) {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            bos.close();
//            buffer = bos.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (waterMarkName != "") {
//            File file = new File(destinationFileName);
//            FileOutputStream outPutStream = new FileOutputStream(file);
//            OperationPdfUtil.setWatermark(buffer, outPutStream, waterMarkName,
//                    opacity);
//            buffer = OperationPdfUtil.fileToByteArray(destinationFileName);
//        }
//        return buffer;
//    }
//    /**
//     * 根据word模板生成指定的word文件以及pdf文件，并返回该pdf文件的字节流数组
//     *
//     * @param projectbaseid
//     * @param projectbatchid
//     * @param modelFilePath
//     *            （word模板路径）
//     * @param tmpWordFile
//     *            （生成后的word文件）
//     * @param tmpPDFFile
//     *            （生成后的word文件）
//     * @param waterMarkName
//     *            （添加水印的内容，如果为空字符串，表示不需添加水印）
//     * @return
//     * @throws MalformedTemplateNameException
//     * @throws ParseException
//     * @throws IOException
//     * @throws TemplateException
//     * @throws DocumentException
//     */
//    public byte[] generatePdf(String projectbaseid, String projectbatchid,
//                              String modelFilePath, String tmpWordFile, String tmpPDFFile,
//                              String waterMarkName, float opacity,String TwoDimImgPath)
//            throws MalformedTemplateNameException, ParseException, IOException,
//            TemplateException, DocumentException {
//
//        // 根据批次id获取相应的模板名称,下载方式
//        String modelName = getModelName(projectbatchid);
//        Map batchMap=getBatchObject(projectbatchid,"sb");
//        String downtype=batchMap.get("DownType").toString();//生成方式
//        String[] affixtypeList=getAffixTypelis(projectbatchid);
//        PmsReward pmsrward= pmsRewardService.findById(projectbaseid);
//        String[] ModeloraffixList=getModelOrAffixlis(projectbatchid,"sb");
//        planStatusMap.put(projectbaseid,"3");
//        String datamapType="sb";
//        if(pmsrward!=null){
//            datamapType="reward";
//        }
//        Date date = new Date();
//        SimpleDateFormat sip = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
//        String aa = sip.format(date);
//        String tmpPDFFilenew = tmpPDFFile.replace("tempdoc.pdf", "tempdoc"
//                + aa + ".pdf");// 存一个带时间的版本
//        if(ModeloraffixList.length<1){//如果批次生成方法不大于0，执行原来的方法，不然执行现在的
//            if(affixtypeList.length>0){
//                byte[] bytes = jointAffixEndModel(projectbaseid,modelName,affixtypeList,
//                        projectbatchid, modelFilePath, tmpWordFile, tmpPDFFile,
//                        waterMarkName, opacity);
//                return bytes;
//            } else {
//                // 根据批次id获取相应的Map数据源
//                Map<String, Object> hashmap = pmsDownLoadService.getDataMap(projectbaseid,datamapType);
//                // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
//
//                SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
//                String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
//                planStatusMap.put(projectbaseid,"5");
//                if("bookmark".equals(downtype)){//通过书签生成方式
//                    WordUtil.createPdfByordanalyzeNewToPdf(modelFilePath, modelName, tmpWordFile,
//                            tmpPDFFile, hashmap,toPdfType);
//                }else{//通过freemark
//                    WordUtil.createPdfNewToPdf(modelFilePath, modelName, tmpWordFile,
//                            tmpPDFFile, hashmap,toPdfType);
//                }
//                // 将生成好的pdf文件转byte数组
//                byte[] bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//                // 如果水印名称waterMarkName不为空字符串，说明需要添加水印，并替换以前生成的pdf文件
//                if (waterMarkName != "") {
//                    // 将生成好的pdf文件转byte数组
//                    OperationPdfUtilText.setEwm(tmpPDFFile,tmpPDFFilenew,TwoDimImgPath);//插入二维码图片
//                    File file = new File(tmpPDFFilenew);
//                    bytes = OperationPdfUtil.fileToByteArray(tmpPDFFilenew);
//                    FileOutputStream outPutStream = new FileOutputStream(file);
//                    OperationPdfUtil.setWatermark(bytes, outPutStream,
//                            waterMarkName, opacity);
//                    bytes = OperationPdfUtil.fileToByteArray(tmpPDFFilenew);
//                    try{
//                        WordUtil.copy(tmpPDFFilenew, tmpPDFFile);//复制到初始文档，作下载
//                    }catch (Exception e){
//                        System.out.println("复制失败");
//                    }
//                    outPutStream.close();
//                    /*File file = new File(tmpPDFFile);
//                    FileOutputStream outPutStream = new FileOutputStream(file);
//                    OperationPdfUtil.setWatermark(bytes, outPutStream,
//                            waterMarkName, opacity);
//                    bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//                    outPutStream.close();*/
//                }else{
//                    waterMarkName="尚未通过  打印无效";
//                    File file = new File(tmpPDFFile);
//                    FileOutputStream outPutStream = new FileOutputStream(file);
//                    OperationPdfUtil.setWatermark02(bytes, outPutStream,
//                            waterMarkName, opacity,"");
//                    bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//                    outPutStream.close();
//                }
//                planStatusMap.put(projectbaseid,"8");
//
//                // 存储生成后的pdf临时文件路径
//                File file = new File(tmpPDFFile);
//                FileInputStream intPutStream = new FileInputStream(file);
//                File filerecord = new File(tmpPDFFilenew);
//                FileOutputStream outPutStreamnew = new FileOutputStream(filerecord);
//                byte[] buf = new byte[1024];
//                int by = 0;
//                while ((by = intPutStream.read(buf)) != -1) {
//                    outPutStreamnew.write(buf, 0, by);
//                }
//                intPutStream.close();
//                outPutStreamnew.close();
//                planStatusMap.put(projectbaseid,"10");
//                return bytes;
//            }
//        }else{
//            // 根据批次id获取相应的Map数据源
//            Map<String, Object> hashmap = pmsDownLoadService.getDataMap(projectbaseid,datamapType);
//            /*byte[] bytes = jointAffixAndmoreModel(projectbaseid,TwoDimImgPath,ModeloraffixList,
//                    projectbatchid, modelFilePath, tmpWordFile, tmpPDFFile,
//                    waterMarkName, opacity,hashmap);*/
//            byte[] bytes = jointAffixAndmoreModel(projectbaseid,TwoDimImgPath,ModeloraffixList,
//                    modelFilePath, tmpWordFile, tmpPDFFile,tmpPDFFilenew,
//                    waterMarkName, opacity,hashmap);
//            planStatusMap.put(projectbaseid,"10");
//            return bytes;
//        }
//    }
//
//
//    /*----------------------------------------------------------------------合同下载---------------------------------------------------------------------------*/
//
//
//    public void downloadPdf_hetongNewPublic(HttpServletRequest request, HttpServletResponse response, String contractid, String yulan, String justdown,float opacity) throws Exception {
//        String TwoDimImgPath=twoDimCodeService.getTwoDimImgPath(contractid,"合同");
//        //根据对应的项目批次获取对应的项目合同pdf存储路径
//        String pdfurlsql="select b.pdfurlforcontract,b.name,b.id from pms_planprojectbatch b inner join crt_contract_jbxx p on b.id=p.planprojectbatchid where p.id=?";
//        List<Map> lis=dbHelper.getRows(pdfurlsql, new Object[]{contractid});
//        String filePath = "";
//        String batchname="";
//        if(lis.size()>0){
//            filePath=(String) lis.get(0).get("pdfurlforcontract");
//            batchname=(String) lis.get(0).get("name");
//        }else{
//            filePath="D:/PdfTmpFile/CrtContract";
//            batchname="未定义";
//        }
//        // 创建属于该项目id的文件夹
//        String tmpFolderPath = filePath +"/"+batchname+"/"+ contractid;
//        File tmpFolderPathFile = new File(tmpFolderPath);
//        if (!tmpFolderPathFile.exists()) {
//            tmpFolderPathFile.mkdirs();
//        }
//        // 存储生成后的word临时文件路径
//        String tmpWordFile = tmpFolderPath + "/tempdoc.doc";
//        Date date = new Date();
//        SimpleDateFormat sip = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
//        String aa = sip.format(date);
//        // 存储生成后的pdf临时文件路径
//        String tmpPDFFile = tmpFolderPath + "/tempdoc"   + ".pdf";
//        // 存储生成后的pdf临时文件路径--二维码
//        String tmpPDFFileEwm = tmpFolderPath + "/tempdoc" + aa + "Ewm.pdf";
//        // 选择使用的模板路径
//        String modelFilePath = request.getSession().getServletContext()
//                .getRealPath("/")
//                + "pdfmodel";
//        // 需要添加水印的内容
//        String waterMarkName = "";
//        String sql = "select showprojectbasename,contractno,minicurrentstateid,minicurrentstate,id,isaddwatermark from crt_contract_jbxx where id=?";
//        List<Map> listPis = dbHelper.getRows(sql, new Object[]{contractid});
//        if (listPis.size() > 0) {
//            if (!Util.isEoN(listPis.get(0).get("isaddwatermark"))) {
//                waterMarkName = "审核通过";
//            }
//        }
//        String contractno = (String) listPis.get(0).get(
//                "contractno");
//        SysParamvalue sysPrintParam = sysParamvalueService.findById("488899");
//        if("false".equals(justdown)){
//            // 通过模板生成pdf文件，并提供下载
//            this.downloadPdfByModel_hetong(contractno,
//                    contractid, modelFilePath, tmpWordFile, tmpPDFFile,tmpPDFFileEwm,
//                    waterMarkName, opacity, response, yulan, request,TwoDimImgPath);
//            String recordid=this.sysDownRecordService.commonSaveOperation(request,contractid,"pdf生成","合同编号："+contractno,tmpPDFFile,"ht","");
//            //调用盖章
//            this.electronicsealchoiceService.CreatSignTask(contractid,recordid);
//        }else{
//            this.downloadByLocal(contractno+"合同书",tmpPDFFile,response,"pdf");
//        }
//    }
//
//    public void downloadPdfByModel_hetong(String contractno,
//                                          String contractid, String modelFilePath, String tmpWordFile,
//                                          String tmpPDFFile,String tmpPDFFileEwm, String waterMarkName, float opacity,
//                                          HttpServletResponse response, String yulan,
//                                          HttpServletRequest request,String TwoDimImgPath) throws Exception {
//        if ("yulan".equals(yulan)) {
//            String picDir = request.getRealPath("/") + "/hetongpic/"
//                    + contractid;
//            boolean iscreat = pmsDownLoadService.needCreateYulan(contractid);//needCreate改成判断预览生成的needCreateYulan
//            boolean isExit = pmsDownLoadService.isExitPic(picDir, contractid);
//            PrintWriter writer = response.getWriter();
//            JSONObject result = new JSONObject();
//            if (isExit && iscreat) { // 不需要重新下载
//                List<String> fileNames = Util.getAllFilePathByDir(picDir);
//                List<String> fileDir = new ArrayList<String>();
//                if (fileNames != null) {
//                    for (String ss : fileNames) {
//                        fileDir.add("../../../hetongpic/" + contractid + "/"
//                                + ss);
//                    }
//                }
//                JSONArray jsonArray = JSONArray.fromObject(fileDir);
//                result.put("data", jsonArray);
//                writer.write(result.toString());
//            } else {
//                // 添加预览pdf生成时间
//                String sql = "update crt_contract_jbxx set yulanpdfdate=sysdate where id=?";
//                dbHelper.runSql(sql, new Object[] { contractid });
//                this.generatePdf_hetong(contractid, modelFilePath,
//                        tmpWordFile, tmpPDFFile,tmpPDFFileEwm, waterMarkName, opacity,false,TwoDimImgPath);
//                ExtractImages.PdfToImage(tmpPDFFile, picDir);
//                List<String> fileNames = Util.getAllFilePathByDir(picDir);
//                List<String> fileDir = new ArrayList<String>();
//                if (fileNames != null) {
//                    for (String ss : fileNames) {
//                        fileDir.add("../../../hetongpic/" + contractid + "/"
//                                + ss);
//                    }
//                }
//                JSONArray jsonArray = JSONArray.fromObject(fileDir);
//                result.put("data", jsonArray);
//                writer.write(result.toString());
//            }
//
//        } else {
//            byte[] bytes=null;
//                bytes = this.generatePdf_hetong(contractid,
//                        modelFilePath, tmpWordFile, tmpPDFFile,tmpPDFFileEwm, waterMarkName,
//                        opacity,true,TwoDimImgPath);
//        }
//    }
//
//    /**
//     * @author  合同书模板
//     * @param contractid
//     * @param
//     * @param modelFilePath
//     * @param tmpWordFile
//     * @param tmpPDFFile
//     * @param waterMarkName
//     * @param opacity
//     * @return
//     * @throws MalformedTemplateNameException
//     * @throws ParseException
//     * @throws IOException
//     * @throws TemplateException
//     * @throws DocumentException
//     * @throws SQLException
//     */
//    public byte[] generatePdf_hetong(String contractid,
//                                     String modelFilePath, String tmpWordFile, String tmpPDFFile,String tmpPDFFileEwm,
//                                     String waterMarkName, float opacity,boolean isPass,String TwoDimImgPath)
//            throws MalformedTemplateNameException, ParseException, IOException,
//            TemplateException, DocumentException, SQLException {
//        // 添加下载时间
//        String sql = "update crt_contract_jbxx set downloaddate=sysdate where id=?";
//        dbHelper.runSql(sql, new Object[] { contractid });
//
//        // 根据配置获取合同模板名称
//        String batchsql = "select b.htmodelname,b.pdfdowntypeforcontract,b.id as projectbatchid from pms_planprojectbatch b " +
//                "where b.id = (select a.planprojectbatchid from crt_contract_jbxx a where a.id=?)";
//        List<Map> modelNameList = dbHelper.getRows(batchsql,
//                new Object[] { contractid });
//        String modelName = (String) modelNameList.get(0).get("htmodelname");
//        String pdfdowntypeforcontract = (String) modelNameList.get(0).get("pdfdowntypeforcontract");//单个模板下载方式
//        String projectbatchid = (String) modelNameList.get(0).get("projectbatchid");
//        String[] ModeloraffixList=getModelOrAffixlis(projectbatchid,"ht");//多模板下载方式
//        // 根据不同合同的批次获取不同的数据源
//        Map<String, Object> hashmap = new HashMap<String, Object>();
//        planStatusMap.put(contractid,"2");
//        hashmap = pmsDownLoadService.getDataMap(contractid,"ht");//有时间戳
//        planStatusMap.put(contractid,"3");
//        if(ModeloraffixList.length<2){
//
//            JSONObject isexit = pmsDownLoadService.isexit(contractid);//是否变更
//            if("true".equals(isexit.get("isexit"))){//如果是变更过的就用这个
////                String contractid=isexit.get("contractid")+"";
//                String[] modelandchange=insertArr(ModeloraffixList,"modelHetongbgnew.xml&free");
//                byte[] bytes = jointAffixAndmoreModel(contractid,TwoDimImgPath,modelandchange,
//                        modelFilePath, tmpWordFile, tmpPDFFile,tmpPDFFileEwm,
//                        waterMarkName, opacity,hashmap);
//                return bytes;
//            }else{
//                SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
//                String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
//                // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
//                if("bookmark".equals(pdfdowntypeforcontract)){//通过书签生成方式
//                    WordUtil.createPdfByordanalyzeNewToPdf(modelFilePath, modelName, tmpWordFile,
//                            tmpPDFFile, hashmap,toPdfType);
//                }else {//通过freemark
//                    WordUtil.createPdfNewToPdf(modelFilePath, modelName, tmpWordFile,
//                            tmpPDFFile, hashmap,toPdfType);
//                }
//                planStatusMap.put(contractid,"9");
//                // 如果水印名称waterMarkName不为空字符串，说明需要添加水印，并替换以前生成的pdf文件
//                byte[] bytes=null;
//                bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//                if (waterMarkName != "") {
//                    // 将生成好的pdf文件转byte数组
//                    OperationPdfUtilText.setEwm(tmpPDFFile,tmpPDFFileEwm,TwoDimImgPath);//插入二维码图片
//                    File file = new File(tmpPDFFileEwm);
//                    bytes = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);
//                    FileOutputStream outPutStream = new FileOutputStream(file);
//                    if(isPass){
//                        OperationPdfUtil.setWatermark(bytes, outPutStream, waterMarkName,
//                                opacity);
//                    }else{
//                        OperationPdfUtil.setWatermarkNotEncry(bytes, outPutStream, waterMarkName,
//                                opacity,isPass);
//                    }
//                    bytes = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);
//                    try{
//                        WordUtil.copy(tmpPDFFileEwm, tmpPDFFile);//复制到初始文档，作下载
//                    }catch (Exception e){
//                        System.out.println("复制失败");
//                    }
//                }else{
//                    waterMarkName="尚未通过  打印无效";
//                    // 将生成好的pdf文件转byte数组
//                    File file = new File(tmpPDFFileEwm);
//                    bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//                    FileOutputStream outPutStream = new FileOutputStream(file);
//                    if(isPass){
//                        OperationPdfUtil.setWatermark02(bytes, outPutStream, waterMarkName,
//                                opacity,"");
//                    }else{
//                        OperationPdfUtil.setWatermarkNotEncry(bytes, outPutStream, waterMarkName,
//                                opacity,isPass);
//                    }
//                    bytes = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);
//                    try{
//                        WordUtil.copy(tmpPDFFileEwm, tmpPDFFile);//复制到带时间的，作记录
//                    }catch (Exception e){
//                        System.out.println("复制失败");
//                    }
//                }
//                planStatusMap.put(contractid,"10");
//                return bytes;
//            }
//        }else{//如果是多模板下载
//            byte[] bytes = jointAffixAndmoreModel(contractid,TwoDimImgPath,ModeloraffixList,
//                    modelFilePath, tmpWordFile, tmpPDFFile,tmpPDFFileEwm,
//                    waterMarkName, opacity,hashmap);
//            planStatusMap.put(contractid,"10");
//            return bytes;
//        }
//    }
//
//
//    /**
//     * @author jzz 通过模板生成pdf的方式向用户浏览器提供下载pdf的功能 多个模板
//     * @return
//     */
//    public byte[] jointAffixAndmoreModel(String sourceid,String TwoDimImgPath,String[] ModeloraffixList,
//                                         String modelFilePath, String tmpWordFile,
//                                         String tmpPDFFile,String tmpPDFFileEwm, String waterMarkName, float opacity,Map<String, Object> hashmap)
//            throws MalformedTemplateNameException, ParseException, IOException,
//            TemplateException, DocumentException {
//        // 根据批次id获取相应的模板名称
//        // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
//        List<String> filesInFolder = new ArrayList<String>();
//        // 将所有的扫描件转换成PDF，为了下一步进行拼接
//        SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
//        String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
//        if(ModeloraffixList.length>0){
//            int tempnum=0;
//            for(int i=0;i<ModeloraffixList.length;i++){
//                String showmodelnameobj=ModeloraffixList[i]+"";
//                if(showmodelnameobj.indexOf(".xml")!=-1){//如果不包含.xml，说明不是模板生成，是附件
//                    if(showmodelnameobj.indexOf("@")>-1){
//                        String[]  modeltype=showmodelnameobj.split("@");
//                        if(showmodelnameobj.indexOf("kjj")>-1){
//                            String tjlx=this.dbHelper.getOnlyStringValue("select t.nominatetype from pms_reward t where t.id=? ",new Object[]{sourceid});
//                            tjlx=(tjlx==null?"":tjlx);
//                            if(tjlx.indexOf("专家")>-1){
//                                showmodelnameobj=modeltype[1];
//                            }else{
//                                showmodelnameobj=modeltype[0];
//                            }
//                        }
//                    }
//                    String[]  downtypelis=showmodelnameobj.split("&");
//                    tempnum=tempnum+1;
//                    String showmodelname="";
//                    String downtype="";
//                    if(downtypelis.length>1){
//                        showmodelname=downtypelis[0];//当前模板
//                        downtype=downtypelis[1];//当前模板生成调用方法
//                    }
//                    String tmpWordFileend=tmpWordFile.replace("tempdoc", "tempdoc"+tempnum);
//                    String tmpPDFFileend=tmpWordFileend.replace(".doc",".pdf");
//                    planStatusMap.put(sourceid,"5");
//                    if("book".equals(downtype)){//如果是书签
//                        WordUtil.createPdfByordanalyzeNewToPdf(modelFilePath, showmodelname,
//                                tmpWordFileend,tmpPDFFileend, hashmap,toPdfType);
//                    }else{
//                        WordUtil.createPdfNewToPdf(modelFilePath, showmodelname,
//                                tmpWordFileend,
//                                tmpPDFFileend, hashmap,toPdfType);
//                    }
//                    filesInFolder.add(tmpPDFFileend);
//                }else{
//                    // 将所有的扫描件转换成PDF，为了下一步进行拼接
//                    try {
//                        pmsAffixService.getuploadFileAttachment(
//                                filesInFolder,
//                                sourceid,
//                                tmpPDFFileEwm.substring(0,
//                                        tmpPDFFileEwm.lastIndexOf("/")),ModeloraffixList[i]);
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                }
//
//            }}
//
//
//
//        PDFMergerUtility mergePdf = new PDFMergerUtility();
//        String destinationFileName = tmpPDFFile;
//        for (int i = 0; i < filesInFolder.size(); i++)
//            mergePdf.addSource(filesInFolder.get(i));
//
//        mergePdf.setDestinationFileName(destinationFileName);
//        try {
//            mergePdf.mergeDocuments();
//        } catch (COSVisitorException e1) {
//            e1.printStackTrace();
//        }
//
//        byte[] buffer = null;
//        try {
//            File file = new File(destinationFileName);
//            FileInputStream fis = new FileInputStream(file);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
//            byte[] b = new byte[1000];
//            int n;
//            while ((n = fis.read(b)) != -1) {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            bos.close();
//            buffer = bos.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        planStatusMap.put(sourceid,"8");
//        // 如果水印名称waterMarkName不为空字符串，说明需要添加水印，并替换以前生成的pdf文件
//        byte[] bytes=null;
//        bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//        String addpagenum=this.dbHelper.getOnlyStringValue("select pl.addpagenum from pms_reward t" +
//                " inner join pms_planprojectbatch pl on pl.id=t.planprojectbatchid where t.id=? ",new Object[]{sourceid});
//        if(Util.isEoN(addpagenum)){
//            addpagenum=this.dbHelper.getOnlyStringValue("select pl.addpagenum from pms_projectbase t" +
//                    " inner join pms_planprojectbatch pl on pl.id=t.planprojectbatchid where t.id=? ",new Object[]{sourceid});
//            /*if(Util.isEoN(addpagenum)){//如果有oldplanbatchid说明在评审中
//                addpagenum=this.dbHelper.getOnlyStringValue("select pl.addpagenum from pms_reward t" +
//                        " inner join pms_planprojectbatch pl on pl.id=t.oldplanbatchid where t.id=? ",new Object[]{sourceid});
//                if(Util.isEoN(addpagenum)){
//                    addpagenum=this.dbHelper.getOnlyStringValue("select pl.addpagenum from pms_projectbase t" +
//                            " inner join pms_planprojectbatch pl on pl.id=t.oldplanbatchid where t.id=? ",new Object[]{sourceid});
//                }
//            }*/
//        }
//        if (waterMarkName != "") {
//            OperationPdfUtilText.setEwm(tmpPDFFile,tmpPDFFileEwm,TwoDimImgPath);//插入二维码图片
//            File file = new File(tmpPDFFileEwm);
//            bytes = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);
//            FileOutputStream outPutStream = new FileOutputStream(file);
//            /*OperationPdfUtil.setWatermark(bytes, outPutStream, waterMarkName,
//                        opacity);*/
//            if(Util.isEoN(addpagenum)||"false".equals(addpagenum)){
//                OperationPdfUtil.setWatermark(bytes, outPutStream, waterMarkName,
//                        opacity);
//            }else{
//                OperationPdfUtil.addPageNumAndWatermark(bytes, outPutStream,
//                        waterMarkName, opacity,"");
//            }
//            bytes = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);
//            try{
//                WordUtil.copy(tmpPDFFileEwm, tmpPDFFile);//复制到初始文档，作下载
//            }catch (Exception e){
//                System.out.println("复制失败");
//            }
//            /*File file = new File(destinationFileName);
//            FileOutputStream outPutStream = new FileOutputStream(file);
//            OperationPdfUtilText.setEwm(destinationFileName,tmpPDFFileEwm,TwoDimImgPath);//插入二维码图片
//            buffer = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);
//            OperationPdfUtil.setWatermark(buffer, outPutStream, waterMarkName,
//                    opacity);
//            buffer = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);*/
//        }else{
//            waterMarkName="尚未通过  打印无效";
//            // 将生成好的pdf文件转byte数组
//            File file = new File(tmpPDFFile);
//            FileOutputStream outPutStream = new FileOutputStream(file);
//            if(Util.isEoN(addpagenum)||"false".equals(addpagenum)){
//                OperationPdfUtil.setWatermark02(buffer, outPutStream,
//                        waterMarkName, opacity,"");
//            }else{
//                OperationPdfUtil.addPageNumAndWatermark02(buffer, outPutStream,
//                        waterMarkName, opacity,"");
//            }
//            buffer = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//            outPutStream.close();
//        }
//        buffer = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//        planStatusMap.put(sourceid,"10");
//        return buffer;
//    }
//
    /**
     * @author jzz 通过模板生成pdf的方式向用户浏览器提供下载pdf的功能 多个模板---除项目，合同外的单独下载
     * @return
     */
    public byte[] jointAffixAndmoreModelForDown(HttpServletRequest request,PmsDownloadtype pyd,String sourceid,String pdfname,String[] ModeloraffixList,
                                                String modelFilePath, String tmpWordFile,
                                                String tmpPDFFile, String waterMarkName,String waterMarkStyle, float opacity,Map<String, Object> hashmap)
            throws MalformedTemplateNameException, ParseException, IOException,
            TemplateException, DocumentException {
        // 根据批次id获取相应的模板名称
        // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
        List<String> filesInFolder = new ArrayList<String>();
        // 将所有的扫描件转换成PDF，为了下一步进行拼接
//        planStatusMap.put(sourceid,"5");
        SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
        String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
        if(ModeloraffixList.length>0){
            int tempnum=0;
            for(int i=0;i<ModeloraffixList.length;i++){
                String showmodelnameobj=ModeloraffixList[i]+"";
                if(showmodelnameobj.indexOf(".xml")!=-1){//如果不包含.xml，说明不是模板生成，是附件
                    String[]  downtypelis=showmodelnameobj.split("&");
                    tempnum=tempnum+1;
                    String showmodelname="";
                    String downtype="";
                    if(downtypelis.length>1){
                        showmodelname=downtypelis[0];//当前模板
                        downtype=downtypelis[1];//当前模板生成调用方法
                    }
                    String tmpWordFileend=tmpWordFile.replace("tempdoc", "tempdoc"+tempnum);
                    String tmpPDFFileend=tmpWordFileend.replace(".doc",".pdf");
                    System.out.print("tmpPDFFileend:"+tmpPDFFileend);
                    if("book".equals(downtype) || downtype.contains("book")){//如果是书签
//                        log.warn("word地址："+tmpWordFileend);
//                        log.warn("pdf地址："+tmpPDFFileend);
                        WordUtil.createPdfByordanalyzeNewToPdf(modelFilePath, showmodelname,
                                tmpWordFileend,tmpPDFFileend, hashmap,toPdfType);
                    }else{
//                        log.warn("word地址："+tmpWordFileend);
//                        log.warn("pdf地址："+tmpPDFFileend);
                        WordUtil.createPdfNewToPdf(modelFilePath, showmodelname,
                                tmpWordFileend,
                                tmpPDFFileend, hashmap,toPdfType);
                    }
                    filesInFolder.add(tmpPDFFileend);
                }else{
                    System.out.print("tmpPDFFile:"+tmpPDFFile);
                    // 将所有的扫描件转换成PDF，为了下一步进行拼接
                    try {
                        if(tmpPDFFile.indexOf("tempdocCg.pdf")!=-1) {
                            pmsAffixService.getuploadFileAttachment(
                                    filesInFolder,
                                    sourceid,
                                    tmpPDFFile.substring(0,
                                            tmpPDFFile.lastIndexOf("/tempdocCg.pdf")), ModeloraffixList[i]);
                        }else{
                            if(tmpPDFFile.indexOf(sourceid+".pdf")>-1){
                                pmsAffixService.getuploadFileAttachment(
                                        filesInFolder,
                                        sourceid,
                                        tmpPDFFile.substring(0,
                                                tmpPDFFile.lastIndexOf("/"+sourceid+".pdf")), ModeloraffixList[i]);
                            }else if (tmpPDFFile.indexOf(sourceid+".pdf")>-1){

                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

            }}

/*        //将原来要拼接的文件转为A4大小
        List<String> newFilesInfolder =new ArrayList<>();
        for (String source : filesInFolder) {
            if (source.indexOf("affix")!=-1){
                //说明是附件，将附件转为A4
                String target=source.replace("affix","affix100");
                PdfConvertA4Utils.convert(source,target);
                newFilesInfolder.add(target);
            }else {
                //说明是模板
                newFilesInfolder.add(source);
            }
        }*/


        String destinationFileName = tmpPDFFile;
        /*拼接方式*/
        if (!"word2PdfSuwell".equals(toPdfType)) {
            /*原版拼接*/
//            log.info("mergeDocuments拼接");
            PDFMergerUtility mergePdf = new PDFMergerUtility();
            if(filesInFolder.size()<1){
                return null;
            }
            for (int i = 0; i < filesInFolder.size(); i++)
                mergePdf.addSource(filesInFolder.get(i));
/*            for (int i = 0; i < newFilesInfolder.size(); i++)
                mergePdf.addSource(newFilesInfolder.get(i));*/

            mergePdf.setDestinationFileName(destinationFileName);
            try {
                mergePdf.mergeDocuments();
            } catch (COSVisitorException e1) {
                e1.printStackTrace();
            }
        } else {
            Word2PdfSuwellUtil.mergepdf(filesInFolder,destinationFileName);
/*            Word2PdfSuwellUtil.mergepdf(newFilesInfolder,destinationFileName);*/
        }

        byte[] buffer = null;
        try {
            File file = new File(destinationFileName);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            byte[] data= OperationPdfUtil.fileToByteArray(destinationFileName);
            FileOutputStream outputStream=new FileOutputStream(destinationFileName);
            OperationPdfUtil.setWatermark(data,outputStream,"不能外传",0.8f);
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!Util.isEoN(hashmap.get("TwoDimImgPath"))) {//如果有二维码图片路径
            String tmpPDFFileEwm=tmpPDFFile.replace(".pdf","Ewm.pdf");
            OperationPdfUtilText.setEwm(tmpPDFFile,tmpPDFFileEwm,hashmap.get("TwoDimImgPath").toString());//插入二维码图片
            buffer = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);
            try{
                WordUtil.copy(tmpPDFFileEwm, tmpPDFFile);//复制到初始文档，作下载
            }catch (Exception e){
                System.out.println("复制失败");
            }
        }
        String addpagenum=pyd.getAddpagenum()+"";
        if(!Util.isEoN(addpagenum)&&!"false".equals(addpagenum)){
            String tmpPDFFilePage=tmpPDFFile.replace(".pdf","Page.pdf");
            String pageFormat=Util.isEoN(pyd.getPageformat())?"第%d页 共%d页":pyd.getPageformat();
            tmpPDFFilePage=PdfUtil.addPageNumNew(tmpPDFFile,tmpPDFFilePage,pageFormat);
            buffer = OperationPdfUtil.fileToByteArray(tmpPDFFilePage);
        }
//        //先生成记录，用于盖章
//        String recordid=this.sysDownRecordService.commonSaveOperation(request,sourceid,"pdf生成--通用","pdfname："+pdfname,destinationFileName,pyd.getType(),"");
//        //调用盖章
//        if(!Util.isEoN(pyd.getSignsql())){
//            String signBusinessId=this.dbHelper.getOnlyStringValue(pyd.getSignsql(),new Object[]{sourceid});
//            String resultString=this.electronicsealchoiceService.CreatSignTask(signBusinessId,recordid);
//            System.out.print("盖章情况："+resultString);
//        }
        //调用水印
//        buffer = getWaterBytes(pyd, sourceid, waterMarkName, waterMarkStyle, opacity, destinationFileName, buffer);
//        planStatusMap.put(sourceid,"9");
        String realpath=Util.GetFileRealPath(pyd.getRealpath()+"");//最终pdf放的路径
        if("true".equals(pyd.getInprogram())){
            realpath=modelFilePath.replace("pdfmodel","")+pyd.getRealpath()+"";
        }
        File realpathFile = new File(realpath);
        if (!realpathFile.exists()) {
            realpathFile.mkdirs();
        }
        try {
            WordUtil.copy(destinationFileName, realpathFile+"/"+pdfname+".pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        planStatusMap.put(sourceid,"10");
        return buffer;
    }
//
//    /*public byte[] jointAffixAndmoreModelForDownNew(SecurityUser securityUser , String ip,PmsDownloadtype pyd,String sourceid,String pdfname,String[] ModeloraffixList,
//                                                String modelFilePath, String tmpWordFile,
//                                                String tmpPDFFile, String waterMarkName,String waterMarkStyle, float opacity,Map<String, Object> hashmap)
//            throws MalformedTemplateNameException, ParseException, IOException,
//            TemplateException, DocumentException {
//        // 根据批次id获取相应的模板名称
//        // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
//        List<String> filesInFolder = new ArrayList<String>();
//        // 将所有的扫描件转换成PDF，为了下一步进行拼接
//        planStatusMap.put(sourceid,"5");
//        SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
//        String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
//        if(ModeloraffixList.length>0){
//            int tempnum=0;
//            for(int i=0;i<ModeloraffixList.length;i++){
//                String showmodelnameobj=ModeloraffixList[i]+"";
//                if(showmodelnameobj.indexOf(".xml")!=-1){//如果不包含.xml，说明不是模板生成，是附件
//                    String[]  downtypelis=showmodelnameobj.split("&");
//                    tempnum=tempnum+1;
//                    String showmodelname="";
//                    String downtype="";
//                    if(downtypelis.length>1){
//                        showmodelname=downtypelis[0];//当前模板
//                        downtype=downtypelis[1];//当前模板生成调用方法
//                    }
//                    String tmpWordFileend=tmpWordFile.replace("tempdoc", "tempdoc"+tempnum);
//                    String tmpPDFFileend=tmpWordFileend.replace(".doc",".pdf");
//                    System.out.print("tmpPDFFileend:"+tmpPDFFileend);
//                    if("book".equals(downtype)){//如果是书签
//                        log.warn("word地址："+tmpWordFileend);
//                        log.warn("pdf地址："+tmpPDFFileend);
//                        WordUtil.createPdfByordanalyzeNewToPdf(modelFilePath, showmodelname,
//                                tmpWordFileend,tmpPDFFileend, hashmap,toPdfType);
//                    }else{
//                        log.warn("word地址："+tmpWordFileend);
//                        log.warn("pdf地址："+tmpPDFFileend);
//                        WordUtil.createPdfNewToPdf(modelFilePath, showmodelname,
//                                tmpWordFileend,
//                                tmpPDFFileend, hashmap,toPdfType);
//                    }
//                    filesInFolder.add(tmpPDFFileend);
//                }else{
//                    System.out.print("tmpPDFFile:"+tmpPDFFile);
//                    // 将所有的扫描件转换成PDF，为了下一步进行拼接
//                    try {
//                        if(tmpPDFFile.indexOf("tempdocCg.pdf")!=-1) {
//                            pmsAffixService.getuploadFileAttachment(
//                                    filesInFolder,
//                                    sourceid,
//                                    tmpPDFFile.substring(0,
//                                            tmpPDFFile.lastIndexOf("/tempdocCg.pdf")), ModeloraffixList[i]);
//                        }else{
//                            if(tmpPDFFile.indexOf(sourceid+".pdf")>-1){
//                                pmsAffixService.getuploadFileAttachment(
//                                        filesInFolder,
//                                        sourceid,
//                                        tmpPDFFile.substring(0,
//                                                tmpPDFFile.lastIndexOf("/"+sourceid+".pdf")), ModeloraffixList[i]);
//                            }else if (tmpPDFFile.indexOf(sourceid+".pdf")>-1){
//
//                            }
//                        }
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                }
//
//            }}
//
//        String destinationFileName = tmpPDFFile;
//        *//*拼接方式*//*
//        if (!"word2PdfSuwell".equals(toPdfType)) {
//            *//*原版拼接*//*
//            log.info("mergeDocuments拼接");
//            PDFMergerUtility mergePdf = new PDFMergerUtility();
//            if(filesInFolder.size()<1){
//                return null;
//            }
//            for (int i = 0; i < filesInFolder.size(); i++)
//                mergePdf.addSource(filesInFolder.get(i));
//
//            mergePdf.setDestinationFileName(destinationFileName);
//            try {
//                mergePdf.mergeDocuments();
//            } catch (COSVisitorException e1) {
//                e1.printStackTrace();
//            }
//        } else {
//            Word2PdfSuwellUtil.mergepdf(filesInFolder,destinationFileName);
//        }
//
//        byte[] buffer = null;
//        try {
//            File file = new File(destinationFileName);
//            FileInputStream fis = new FileInputStream(file);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
//            byte[] b = new byte[1000];
//            int n;
//            while ((n = fis.read(b)) != -1) {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            bos.close();
//            buffer = bos.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (!Util.isEoN(hashmap.get("TwoDimImgPath"))) {//如果有二维码图片路径
//            String tmpPDFFileEwm=tmpPDFFile.replace(".pdf","Ewm.pdf");
//            OperationPdfUtilText.setEwm(tmpPDFFile,tmpPDFFileEwm,hashmap.get("TwoDimImgPath").toString());//插入二维码图片
//            buffer = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);
//            try{
//                WordUtil.copy(tmpPDFFileEwm, tmpPDFFile);//复制到初始文档，作下载
//            }catch (Exception e){
//                System.out.println("复制失败");
//            }
//        }
//        String addpagenum=pyd.getAddpagenum()+"";
//        if(!Util.isEoN(addpagenum)&&!"false".equals(addpagenum)){
//            String tmpPDFFilePage=tmpPDFFile.replace(".pdf","Page.pdf");
//            String pageFormat=Util.isEoN(pyd.getPageformat())?"第%d页 共%d页":pyd.getPageformat();
//            tmpPDFFilePage=PdfUtil.addPageNumNew(tmpPDFFile,tmpPDFFilePage,pageFormat);
//            buffer = OperationPdfUtil.fileToByteArray(tmpPDFFilePage);
//        }
//        //先生成记录，用于盖章
//        String recordid = this.sysDownRecordService.commonSaveOperation(securityUser ,ip ,sourceid,"pdf生成--通用","pdfname："+pdfname,destinationFileName,pyd.getType(),"");
//
//        //调用盖章
//        if(!Util.isEoN(pyd.getSignsql())){
//            String signBusinessId=this.dbHelper.getOnlyStringValue(pyd.getSignsql(),new Object[]{sourceid});
//            String resultString=this.electronicsealchoiceService.CreatSignTask(signBusinessId,recordid);
//            System.out.print("盖章情况："+resultString);
//        }
//        //调用水印
//        buffer = getWaterBytes(pyd, sourceid, waterMarkName, waterMarkStyle, opacity, destinationFileName, buffer);
//        planStatusMap.put(sourceid,"9");
//        String realpath=Util.GetFileRealPath(pyd.getRealpath()+"");//最终pdf放的路径
//        if("true".equals(pyd.getInprogram())){
//            realpath=modelFilePath.replace("pdfmodel","")+pyd.getRealpath()+"";
//        }
//        File realpathFile = new File(realpath);
//        if (!realpathFile.exists()) {
//            realpathFile.mkdirs();
//        }
//        try {
//            WordUtil.copy(destinationFileName, realpathFile+"/"+pdfname+".pdf");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        planStatusMap.put(sourceid,"10");
//        return buffer;
//    }*/
//
//    private byte[] getWaterBytes(PmsDownloadtype pyd, String sourceid, String waterMarkName, String waterMarkStyle, float opacity, String destinationFileName, byte[] buffer) throws IOException, DocumentException {
//        if (waterMarkName != ""&& waterMarkName !=null) {
//            if(!Util.isEoN(pyd.getIsaddpasswatersql())){
//                List<Map> listwater=this.dbHelper.getRows(pyd.getIsaddpasswatersql(),new Object[]{sourceid});
//                if(listwater.size()>0){
//                    if(listwater.get(0).get("isaddwatermark").equals("1")){
//                        waterMarkName ="审核通过";
//                    }else {
//                        waterMarkName =listwater.get(0).get("isaddwatermark")+"";
//                    }
//                }else {
//                    waterMarkStyle ="Watermark02";
//                    waterMarkName ="尚未通过  打印无效";
//                }
//            }
//            File file = new File(destinationFileName);
//            FileOutputStream outPutStream = new FileOutputStream(file);
//            if(waterMarkStyle.equals("Watermark")){//水印样式
//                OperationPdfUtil.setWatermark(buffer, outPutStream, waterMarkName,
//                        opacity);
//                /*if(Util.isEoN(addpagenum)||"false".equals(addpagenum)){
//                    OperationPdfUtil.setWatermark(buffer, outPutStream, waterMarkName,
//                            opacity);
//                }else{
//                    OperationPdfUtil.addPageNumAndWatermark(buffer, outPutStream,
//                            waterMarkName, opacity,"");
//                }*/
//            }else if("WatermarkNotEncry".equals(waterMarkStyle)){
//                OperationPdfUtil.setWatermarkNotEncry(buffer, outPutStream, waterMarkName,
//                        opacity,false);
//            }else if("Watermark02".equals(waterMarkStyle)){//一页一个水印
//                OperationPdfUtil.setWatermark02(buffer, outPutStream,
//                        waterMarkName, opacity,"");
//                /*if(Util.isEoN(addpagenum)||"false".equals(addpagenum)){
//                    OperationPdfUtil.setWatermark02(buffer, outPutStream,
//                            waterMarkName, opacity,"");
//                }else{
//                    OperationPdfUtil.addPageNumAndWatermark02(buffer, outPutStream,
//                            waterMarkName, opacity,"");
//                }*/
//            }else if(waterMarkStyle.indexOf("setWatermarkWithSize")>-1){//一页一个水印
//                String[] aa=waterMarkStyle.split("_");
//                if(aa.length==3){
//                    OperationPdfUtil.setWatermarkWithSize(buffer, outPutStream,
//                            waterMarkName, opacity,(Integer.valueOf(aa[2])) ,aa[1],"",true);//以下划线形式配置，如setWatermarkWithSize_
//                }else if(aa.length>3){
//                    OperationPdfUtil.setWatermarkWithSize(buffer, outPutStream,
//                            waterMarkName, opacity,(Integer.valueOf(aa[2])) ,aa[1],"",Boolean.parseBoolean(aa[3].toString()));//以下划线形式配置，如setWatermarkWithSize_
//                }
//            }else{
//                OperationPdfUtil.setWatermark01(buffer, outPutStream, waterMarkName,
//                        opacity);
//            }
//            buffer = OperationPdfUtil.fileToByteArray(destinationFileName);
//        }
//        return buffer;
//    }
//    /*--------------------------------------------------------通用方法-----------------------------------------------------------------------------------------------*/
//
//
//
//    public ReturnToJs downloadBylocalForUser(HttpServletRequest request, HttpServletResponse response,String pathvalue) {
//        ReturnToJs returnToJs = new ReturnToJs();
//        SecurityUser suser = SessionUtil.getCurrentSecurityUser(request);// 获取用户信息
//        if(suser!=null){
//            try {
//                pathvalue= URLDecoder.decode(pathvalue,"UTF-8");
//                this.downloadPdfByLocalexcel("清单",
//                        pathvalue, response);
//            } catch (IOException e) {
//                returnToJs.setSuccess(false);
//                returnToJs.setData("导出失败");
//                e.printStackTrace();
//            }
//        }else{
//            returnToJs.setSuccess(false);
//            returnToJs.setData("该用户没有权限");
//        }
//        return returnToJs;
//    }
//
//
//
//
//    /**
//     * 通过项目批次id，获取对应的word模板名
//     *
//     * @param projectbatchid
//     * @return
//     */
//    public String getModelName(String projectbatchid) {
//        String modelName = "zdyf.xml";
//        PmsPlanprojectbatch pmsPlanprojectbatch = this.pmsPlanprojectbatchService
//                .findById(projectbatchid);
//        if ("".equals(projectbatchid)) {
//            modelName = "reviewScore.xml";
//        } else if (pmsPlanprojectbatch != null
//                && pmsPlanprojectbatch.getModelname() != null) {
//            modelName = pmsPlanprojectbatch.getModelname();
//        }
//        return modelName;
//    }
//
//    /**
//     * 通过项目批次id，获取对应的pdf生成方式,数据源类别等
//     *
//     * @param projectbatchid
//     * @return
//     */
//    public Map getBatchObject(String projectbatchid,String downflow) {
//        Map batch=new HashMap<>();
//        String DownType = "freemark";
//        PmsPlanprojectbatch pmsPlanprojectbatch = this.pmsPlanprojectbatchService
//                .findById(projectbatchid);
//        if("sb".equals(downflow)){//申报生成
//            if (pmsPlanprojectbatch != null
//                    && pmsPlanprojectbatch.getPdfdowntypeforbase() != null) {
//                DownType = pmsPlanprojectbatch.getPdfdowntypeforbase();
//            }
//        }else if("ht".equals(downflow)){//合同生成
//            if (pmsPlanprojectbatch != null
//                    && pmsPlanprojectbatch.getPdfdowntypeforcontract() != null) {
//                DownType = pmsPlanprojectbatch.getPdfdowntypeforcontract();
//            }
//        }
//        batch.put("DownType",DownType);
//        return batch;
//    }
//
//    /**
//     * 通过项目批次id，获取对应的插入附件类型--黑龙江的到时候直接用下面这个getModelOrAffixlis
//     *
//     * @param projectbatchid
//     * @return
//     */
//    public String[] getAffixTypelis(String projectbatchid) {
//        String AffixType = "";
//        String[]  affixtypelis=new String[]{};
//        PmsPlanprojectbatch pmsPlanprojectbatch = this.pmsPlanprojectbatchService
//                .findById(projectbatchid);
//        if ("".equals(projectbatchid)) {
//            AffixType = "";
//        } else if (pmsPlanprojectbatch != null
//                && pmsPlanprojectbatch.getAffixtype() != null) {
//            AffixType = pmsPlanprojectbatch.getAffixtype();
//        }
//        if(AffixType.length()>0){
//            affixtypelis=AffixType.split("；");
//        }
//        return affixtypelis;
//    }
//
//    /**
//     * 通过项目批次id，获取对应的批次生成方法
//     *
//     * @param projectbatchid
//     * @return
//     */
//    public String[] getModelOrAffixlis(String projectbatchid,String type) {
//        String Modeloraffix = "";
//        String[]  ModelOrAffixlis=new String[]{};
//        PmsPlanprojectbatch pmsPlanprojectbatch = this.pmsPlanprojectbatchService
//                .findById(projectbatchid);
//        if ("".equals(projectbatchid)) {
//            Modeloraffix = "";
//        } else if (pmsPlanprojectbatch != null
//                && pmsPlanprojectbatch.getModeloraffix() != null&&"sb".equals(type)) {
//            Modeloraffix = pmsPlanprojectbatch.getModeloraffix();
//        } else if (pmsPlanprojectbatch != null
//                && pmsPlanprojectbatch.getModeloraffixht() != null&&"ht".equals(type)) {
//            Modeloraffix = pmsPlanprojectbatch.getModeloraffixht();
//        }
//        if(Modeloraffix.length()>0){
//            ModelOrAffixlis=Modeloraffix.split("；");
//        }
//        return ModelOrAffixlis;
//    }
//
//
//
//
//    /**
//     * @author  判断打印时间与最后一次保存时间的前后，以此来作为是否需要重新生成合同pdf的依据，减少服务压力
//     */
//    public boolean needCreate(String projectbaseid) {
//        String hql = "select t from cn.topcheer.pms.pojo.CrtContractJbxx t where t.projectbaseid=? ";
//        List<CrtContractJbxx> lis = crtContractJbxxService.findByHql(hql,
//                new Object[] { projectbaseid });
//        if (lis.size() > 0) {
//            Date savedate = lis.get(0).getSavedate(); // 最后一次保存时间
//            Date downloaddate = lis.get(0).getDownloaddate(); // 最后一次下载时间
//            if (savedate != null && downloaddate != null) {
//                long savetime = savedate.getTime();
//                long download = downloaddate.getTime();
//                if (savetime > download) { // 重新下载
//                    return false;
//                } else {
//                    return true;
//                }
//            } else {
//                return false;
//            }
//
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * @author  判断预览pdf生成时间与最后一次保存时间的前后，以此来作为是否需要重新生成合同pdf的依据，减少服务压力
//     */
//    public boolean needCreateYulan(String projectbaseid) {
//        String hql = "select t from cn.topcheer.pms.pojo.CrtContractJbxx t where t.projectbaseid=? ";
//        List<CrtContractJbxx> lis = crtContractJbxxService.findByHql(hql,
//                new Object[] { projectbaseid });
//        if (lis.size() > 0) {
//            Date savedate = lis.get(0).getSavedate(); // 最后一次保存时间
//            Date yulanpdfdate = lis.get(0).getYulanpdfdate(); // 最后一次预览生成时间
//            if (savedate != null && yulanpdfdate != null) {
//                long savetime = savedate.getTime();
//                long yulanpdf = yulanpdfdate.getTime();
//                if (savetime > yulanpdf) { // 重新下载
//                    return false;
//                } else {
//                    return true;
//                }
//            } else {
//                return false;
//            }
//
//        } else {
//            return false;
//        }
//    }
//
//
//
//
//    /**
//     * @author  比较打印时间与各个时间的比对--来判断是否重新生成
//     */
//    public boolean needCreateForAll(String projectbaseid,String projectbaseName,String tmpPDFFile,
//                                    Date modelupdatetime,Date updateLastTime,String type) {
//        // 获取用户下载pdf的记录
//        List<SysOperationrecord> list = sysOperationrecordService
//                .findAllPdfRecord(projectbaseName, projectbaseid);
//        if(list.size()>0){
//            /**
//             * 如果模板更新时间大与下载时间就重新生成
//             * 如果记录表中有该操作的记录，判断上次操作的时间是否超过24小时
//             * 超过24小时，则通过模板生成提供下载，并且复制一份到本地（替换以前的）作为临时pdf
//             * 没有超过24小时，则通过本地的临时pdf提供给用户下载
//             * 如果上面都通过，再判断下流程操作的最新时间是否晚于下载时间，晚于的话重新生成--190701
//             * 如果上面都通过，再判断下附件上传的最新时间是否晚于下载时间，晚于的话重新生成--200324
//             */
//            // 获取当前时间日期，和下载时间比较用
//            Date nowDate = new Date();
//            SysOperationrecord opr = list.get(0);
//            Date opdate = opr.getOperationdate();
//            long diff = nowDate.getTime() - opdate.getTime();
//            long differhours = diff / (1000 * 60 * 60);
//
//
//            //---------------------------------------保存时间---是否保存时间大于下载
//            Boolean isSaveNew=true;
//            if(!Util.isEoN(updateLastTime)){
//                isSaveNew=updateLastTime.getTime() > opdate.getTime();
//            }else {
//                isSaveNew=false;
//            }
//
//            //---------------------------------------模板更新时间---是否模板大于下载
//            Boolean isModelNew=true;
//            if(!Util.isEoN(modelupdatetime)){
//                isModelNew=modelupdatetime.getTime()>opdate.getTime();
//            }else {
//                isModelNew=false;
//            }
//            // ---------------------------------------获取流程操作的记录--是否流转大于下载
//            List<FlowRecord> list2 = flowRecordService
//                    .findAllFlowRecord( projectbaseid);
//            Boolean isFlwNew=true;
//            if(list2.size()>0){//判断流转
//                FlowRecord flr=new FlowRecord();
//                flr = list2.get(0);
//                Date flwdate = flr.getOperationtime();
//                isFlwNew=flwdate.getTime() > opdate.getTime();//流转记录大于pdf最后下载的时间
//            }else{
//                isFlwNew=false;//如果没有直接下
//            }
//
//            // --------------------------------------获取附件上传的记录--是否附件大于下载
//            Boolean isPtaNew=true;
//            List<PmsAttachment> list3 = pmsAttachmentService.findPmsAttachmentBySourceidOrder(projectbaseid);
//            if(list3.size()>0){//判断附件
//                PmsAttachment pat=new PmsAttachment();
//                pat = list3.get(0);
//                Date patdate = pat.getCreatetime();
//                isPtaNew=patdate.getTime() > opdate.getTime();//附件大于pdf最后下载的时间
//            }else{
//                isPtaNew=false;//如果没有直接下
//            }
//
//            //时间是否大于24
//            Boolean isBigthen24=differhours > 24;
//
//            //文件是否不存在
//            Boolean isNotHaveflie=false;
//            File ins = new File(tmpPDFFile);
//            isNotHaveflie=!ins.exists();
//
//            System.out.print("isModelNew:"+isModelNew+"；isNotHaveflie:"+isNotHaveflie+"；isBigthen24:"+isBigthen24+"；isFlwNew:"+isFlwNew
//                    +"；isPtaNew:"+isPtaNew+"；isSaveNew:"+isSaveNew);
//            return (isModelNew||isNotHaveflie||isBigthen24||isFlwNew||isPtaNew||isSaveNew);//一个为true就重新生成
//        }else{
//            return true;
//        }
//    }
//
//    /**
//     * @author  判断硬盘中是否已经有对应的图片
//     */
//    public boolean isExitPic(String picDir, String projectbaseid) {
//        File picFile = new File(picDir);
//        if (!picFile.exists()) {
//            picFile.mkdirs(); // 创建对应的文件夹
//            return false;
//        } else {
//            if (picFile.listFiles().length > 0) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
//
//    /**
//     * 通过获取本地pdf的方式向用户浏览器提供下载pdf的功能
//     *
//     * @param projectbaseName
//     * @param tmpPDFFile
//     * @param response
//     * @throws IOException
//     */

    public void downloadByLocal(String projectbaseName, String tmpPDFFile,
                                   HttpServletResponse response,String downloadtype) throws IOException {
        byte[] bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
        if (projectbaseName == null) {
            projectbaseName="申报书下载PDF";
        } else {
            projectbaseName=projectbaseName.replaceAll(",", "，");
        }
        HashMap methodHs=new HashMap();
        methodHs.put("doc","outResponseword");
        methodHs.put("docx","outResponseword");
        methodHs.put("pdf","outResponse");
        methodHs.put("excel","outResponseexcel");
        // 通过response向客户提供下载
        //OperationPdfUtil.outResponse(response, projectbaseName, bytes);
        try {
            WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            //PmsAwardService a=new PmsAwardService();
            OperationPdfUtil.class.getMethod(methodHs.get(downloadtype)+"", new Class[]{HttpServletResponse.class,String.class,byte[].class}).invoke(OperationPdfUtil.class, new Object[]{response,projectbaseName,bytes});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过获取本地方式向用户浏览器提供下载excel的功能
     *
     * @param projectbaseName
     * @param tmpPDFFile
     * @param response
     * @throws IOException
     */
    public void downloadPdfByLocalexcel(String projectbaseName, String tmpPDFFile,
                                        HttpServletResponse response) throws IOException {


        byte[] bytes =  ossBuilder.template().getFile(tmpPDFFile); //OperationPdfUtil.fileToByteArray(tmpPDFFile);
        if (projectbaseName == null) {
            // 通过response向客户提供下载
            OperationPdfUtil.outResponseexcel(response, "文档", bytes);
        } else {
            // 通过response向客户提供下载
            OperationPdfUtil.outResponseexcel(response,
                    projectbaseName.replaceAll(",", "，"), bytes);
        }
    }
//
//    //往字符串数组追加新数据
//    private static String[] insertArr(String[] arr, String str) {
//        int size = arr.length;  //获取数组长度
//        String[] tmp = new String[size + 1];  //新建临时字符串数组，在原来基础上长度加一
//        for (int i = 0; i < size; i++){  //先遍历将原来的字符串数组数据添加到临时字符串数组
//            tmp[i] = arr[i];
//        }
//        tmp[size] = str;  //在最后添加上需要追加的数据
//        return tmp;  //返回拼接完成的字符串数组
//    }
//
    /*前台配置内容后台获取*/
//    public JSONObject getConfigData(HttpServletRequest request,String projectbaseid,String planprojectid,String type) {
//
//        //select t.* from BATCH_VERSION t where t.ID='45b86279-2416-c6b7-cf43-0888b97cb383';
//        /*造前台传参---用于配置生成*/
//        PmsPlanprojectbatchInBiz bv = this.planprojectbatchInBizService.findById(planprojectid);
//        String versionid="";
//        try {
//            if(!Util.isEoN(bv)){
//                //获取批次表对应的方法
//                Method m = bv.getClass().getMethod("get"+type.substring(0,1).toUpperCase()+type.substring(1,type.length()).toLowerCase());
//                //获取versionid值
//                versionid = (String)m.invoke(bv);
//            }
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        JSONObject configObject = this.sysVersionService.getDataByVersionidNew(versionid);
//        JSONObject otherinfo=new JSONObject();
//        otherinfo.put("type",configObject.getJSONObject("other").get("pdfconfig"));
//        JSONObject useProjectObj=new JSONObject();
//        JSONArray objConfig=configObject.getJSONArray("obj");
//        for (int i = 0; i < objConfig.size(); i++) {
//            JSONObject obj=objConfig.getJSONObject(i);
//            if(!Util.isEoN(obj.get("moduletype"))&&!Util.isEoN(obj.get("objecttype"))){
//                JSONObject tabobj=new JSONObject();
//                tabobj.put("database",obj.get("moduletype"));
//                tabobj.put("type",obj.get("paramtype"));
//                tabobj.put("datatype",obj.get("objecttype"));
//                tabobj.put(" tablenametext",obj.get("showname"));
//                tabobj.put("ishaveaffix",obj.get("ishaveaffix"));
//                tabobj.put("tablename",obj.get("tablename"));
//                useProjectObj.put(obj.get("tablename"),tabobj);
//            }
//        }
//        /*获取项目数据*/
//        String[] initParamArr = dealInitParamArr(projectbaseid, planprojectid, useProjectObj);
//        useProjectObj = genericFetchAndSave.initData(useProjectObj,projectbaseid, planprojectid,request, initParamArr);
//        useProjectObj.remove("TPAllAffixs");
//        otherinfo.put("dataObject",useProjectObj);
//        return useProjectObj;
//    }


    /**
     * 处理初始化参数
     * @param mainid    主表id
     * @param batchid   批次id
     * @param dataObject    前台传参
     * @param otherid   可变参数
     * @return
     */
    private String[] dealInitParamArr(String mainid, String batchid, JSONObject dataObject, String... otherid) {
        JSONArray array = new JSONArray();
        String[] initParamArr = otherid;
        return initParamArr;
    }
//
    /*浙江版本redis下载，在用版*/
    public String downloadPdfByModelFortypeRedis(PmsDownloadtype pyd,HttpServletResponse response, HttpServletRequest request,String ...otherInfo){

        Map<String, Object> hashmap = new HashMap<String, Object>();
        String type = request.getParameter("type");
        String justdown = pyd.getJustdown();
        String errmsg="";
        if(justdown==null){
            justdown="";
        }
        if(!Util.isEoN(pyd)){
            String methodparamtyp=pyd.getMethodparamtype();
            String batchid=pyd.getId();
            String idtype=pyd.getIdtype();
            String openname=pyd.getOpenname();
            String sourceid=request.getParameter(idtype);
            batchid = otherInfo[1];
//            planStatusMap.put(sourceid,"2");
//            log.info("【redis获取配置参数开始】，sourceid为："+sourceid+"；batchid为："+batchid+"；type为："+type);
            Map<String, Object> hashmapConfig=this.pmsDownconfigService.loadPdfConfigByBatchidAndSourceid(batchid,sourceid,type,"");
//            planStatusMap.put(sourceid,"3");
//            log.info("【redis获取配置参数结束】，sourceid为："+sourceid+"；batchid为："+batchid+"；type为："+type);
            if (justdown.equals("false")) {
                try {
                    if("request".equals(methodparamtyp)){
                        hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{request});
                    }else if("requestAndId".equals(methodparamtyp)){
                        hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{request,sourceid});
                    }else if("id".equals(methodparamtyp)){
                        if((otherInfo.length == 2) && !Util.isEoN(otherInfo[0]) && !Util.isEoN(Util.checkFromJson(otherInfo[0]))
                                && Util.checkFromJson(otherInfo[0]).containsKey("dataObject") && Util.checkFromJson(otherInfo[0]).containsKey("type")) {
                            JSONObject json = Util.checkFromJson(otherInfo[0]);
                            //判断参数为配置版本还是通用版本
                            if(Util.isEoN(json.get("type")) || "normal".equals(json.get("type"))) {
                                hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{sourceid});
                            }else if(!Util.isEoN(json.get("businesstype"))&&(Util.isEoN(json.getJSONObject("dataObject"))||json.getJSONObject("dataObject").size()==0)){
                                // 新增传businesstype和sourceid下载，不用前台传参,当businesstype有值，dataObject没值
//                                batchid = otherInfo[1];
//                                type = json.get("businesstype").toString();
//                                JSONObject dataObject =getConfigData(request,sourceid,batchid,type);
//                                hashmap = genericFetchAndSave.downloadData(dataObject, sourceid, batchid, request);
                            } else {
                                batchid = otherInfo[1];
                                JSONObject dataObject = json.getJSONObject("dataObject");
                                hashmap = genericFetchAndSave.downloadData(dataObject, sourceid, batchid, request);
                            }
                        } else {
                            hashmap = (HashMap)Util.springInvokeMethod(pyd.getMethodservice()+"",pyd.getMethodname(),new Object[]{sourceid});
                        }
                    }else if("getDataMap".equals(methodparamtyp)){
                        hashmap = pmsDownLoadService.getDataMap(sourceid,pyd.getMemo());
                    }else if("cspdf".equals(methodparamtyp)){
                        hashmap.put("list",this.dbHelper.getRows("select t.* from SYS_EXCEL_PARAM t " +
                                "where t.SOURCEID='538EA874-09E6-4F36-B7FC-1D652CE762BE' order by t.TYPE,t.seq",new Object[]{}));
                        hashmap.put("mainMember_normal_name","我说我是谁");
                        hashmap.put("mainUnit_normal_unitname","浙江天正测试1");
                        hashmap.put("basicInfo_normal_projectbasename","2022年度成果--流程");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                    log.error("方法：downloadPdfByModelFortypeRedis里的：获取数据源失败失败。错误信息："+e);
                    errmsg="获取数据源失败失败";
                }

//                log.info("【redis获取数据源结束】，sourceid为："+sourceid+"；batchid为："+batchid+"；type为："+type);
                /*获取配置--先注释，测试功能*/
                List<Map> convertArray= null;
                Map<String,Object> pdfData= null;
                Map<String,Object> imageEwmData= null;
                List<Map> imageData= null;
                try {
                    convertArray = (ArrayList)hashmapConfig.get("convertArray");
                    pdfData = (Map<String, Object>) hashmapConfig.get("pdfData");
                    imageEwmData = (Map<String, Object>) hashmapConfig.get("imageEwmData");
                    imageData = (ArrayList)hashmapConfig.get("imageData");
                } catch (Exception e) {
                    e.printStackTrace();
//                    log.error("方法：downloadPdfByModelFortypeRedis里的：pmsDownconfigService.loadPdfConfigByBatchidAndSourceid方法报错，参数获取失败。错误信息："+e);
                    errmsg="参数获取失败";
                }

                /*List<Map> convertArray=new ArrayList<>();
                Map<String,Object> pdfData=new HashMap<>();
                pdfData.put("id","cspdf");
                pdfData.put("tmpPDFFile","/PdfTmpFile/Allpdf/测试pdf功能/【sourceid】//tempdocCg.pdf");*/
                /*-------------------------------最终数据集合--------------------------------------------------------------------*/
                JSONObject jsonObject=new JSONObject();//方法传参
                jsonObject.put("hashmap",hashmap);//数据源
                jsonObject.put("pdfData",pdfData);//主要参数
                jsonObject.put("imageData",imageData);//图片参数
                jsonObject.put("imageEwmData",imageEwmData);//二维码参数
                jsonObject.put("convertArray",convertArray);//模板或附件拼接顺序
                jsonObject.put("isNewDown","true");//是否重新打印
                SysParamvalue sv2=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata", SysParamDataEnum.PDFPOSTURL.getMessage());
                String url=sv2.getValue();
//                planStatusMap.put(sourceid,"7");
                try {
//                    log.info("【redis调用前参数处理结束】，sourceid为："+sourceid+"；batchid为："+batchid+"；type为："+type);
                    pmsDownloadtypeService.doPost2(url,jsonObject);
//                    planStatusMap.put(sourceid,"8.5");
//                    log.info("【redis调用成功】，sourceid为："+sourceid+"；batchid为："+batchid+"；type为："+type);
                } catch (IOException e) {
                    e.printStackTrace();
                    errmsg="调用单独服务器生成失败";
//                    log.error("方法：downloadPdfByModelFortypeRedis里的：pmsDownloadtypeService.doPost2方法报错，调用单独服务器生成失败。错误信息："+e);
                }
            } else {
                Map<String,Object> pdfData = (Map<String, Object>) hashmapConfig.get("pdfData");
                String tmpWordFile=pdfData.get("tmpWordFile").toString();
//                log.info("【redis下载方法开始】，sourceid为："+sourceid+"；batchid为："+batchid+"；type为："+type);
//                errmsg=getRedisOut(response, openname, sourceid,type,tmpWordFile,pyd);
                /*// 通过response向客户提供下载
                String retrunUrlStr=this.pmsDownconfigService.getRetrunUrlStr(batchid,sourceid,type);
                try {
                    this.outResponseFromUrl(response,openname,retrunUrlStr,downloadtype);
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("方法：downloadPdfByModelFortypeRedis里的：outResponseFromUrl方法报错，向客户提供下载失败。错误信息："+e);
                }*/
            }
        }
        return errmsg;
    }
//
//    private String getRedisOut(HttpServletResponse response, String openname, String sourceid,String downLoadType,String tmpWordFile,PmsDownloadtype pyd){
//        byte[] bytes =null;
//        String errmsg="";
//        try{
//            if(downLoadType.equals("doc")){
//                bytes = OperationPdfUtil.fileToByteArray(tmpWordFile);
//                OperationPdfUtil.outResponseword(response,openname,bytes);
//            }
//            /*OperationPdfUtil.outResponse(response, openname, bytes);*/
//            SysParamvalue sv=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata", SysParamDataEnum.PDFPOSTREDIS.getMessage());
//            String url=Util.isEoN(sv)?"":sv.getValue();
//            String port=Util.isEoN(sv)?"":sv.getShowname();
//            Map convertInfo=new HashMap();
//            JedisPool pool=new JedisPool(url,Integer.parseInt(port));
//            Jedis jedis =pool.getResource();
//            jedis.auth("tz_pms");
//            String applyid=sourceid+pyd.getType().replace("down","");
//            log.info("【redis调用地址下载】：url:"+url+"；port："+port+";applyid："+applyid);
//            convertInfo=jedis.hgetAll(applyid);
//            String damage=convertInfo.get("damage")+"";
//            String memo=convertInfo.get("memo")+"";
//            int i=0;
//            String state=convertInfo.get("state")+"";
//            if((Util.isEoN(damage)||"\"\"".equals(damage))&&!"2".equals(state)){
//                while(state.indexOf("1")<1&& i<15){
//                    Thread.sleep(2000);
//                    convertInfo=jedis.hgetAll(applyid);
//                    state=convertInfo.get("state")+"";
//                    i++;
//                }
//                String urlStr=convertInfo.get("downloadUrl")+"";
//                urlStr=urlStr.replace("\"http","http");
//                urlStr=urlStr.replace(".pdf\"",".pdf");
//                /* urlStr=urlStr.replace("115.238.84.52","192.168.1.106");*/
//                log.info("redis生成地址：urlStr:"+urlStr+"；openname："+openname);
//                this.outResponseFromUrl(response,openname,urlStr,"pdf");
//            }else{
//                errmsg=(damage+memo).replaceAll("\"","");
//                WordUtil.convertFailFile2(errmsg);
//            }
//        }catch (JedisDataException e){
//            log.error(e);
//            errmsg="redis数据错误";
//        }catch (JedisConnectionException c){
//            log.error(c);
//            errmsg="redis连接错误";
//        }catch (InterruptedException i){
//            log.error(i);
//            errmsg="网络错误";
//        }catch (IOException o){
//            log.error(o);
//            errmsg="文件错误";
//
//        }
//        return errmsg;
//    }
//
//    /**
//     * 向客户提供下载--url
//     * @param response
//     * @param openname
//     * @param stringUrl
//     * @throws IOException
//     */
//    public static void outResponseFromUrl(HttpServletResponse response,
//                                          String openname, String stringUrl, String opentype) throws IOException {
//        URL url = new URL(stringUrl);
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        //设置超时间为3秒
//        conn.setConnectTimeout(5*1000);
//        //防止屏蔽程序抓取而返回403错误
//        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//        //得到输入流
//        InputStream inputStream = conn.getInputStream();
//        //获取自己数组
//        byte[] getData = OperationPdfUtil.readInputStream(inputStream);
//        switch (opentype){
//            case "pdf":
//                OperationPdfUtil.outResponse(response, openname, getData);
//                break;
//            case "doc":
//                OperationPdfUtil.outResponseword(response, openname, getData);
//                break;
//            case "xls":
//                OperationPdfUtil.outResponseexcel(response, openname, getData);
//                break;
//            default:
//                OperationPdfUtil.outResponse(response, openname, getData);
//
//        }
//    }
//
//    public String getRedisDownurl(ReturnToJs returnToJs, String errmsg, PmsDownloadtype pyd, String sourceid, HttpServletRequest request)  {
//        SysParamvalue sv=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata", SysParamDataEnum.PDFPOSTREDIS.getMessage());
//        String url= Util.isEoN(sv)?"":sv.getValue();
//        String port=Util.isEoN(sv)?"":sv.getShowname();
//        Map convertInfo=new HashMap();
//        JedisPool pool=new JedisPool(url,Integer.parseInt(port));
//        Jedis jedis =pool.getResource();
//        try{
//            jedis.auth("tz_pms");
//            log.info("【redis调用地址--生成调用后】：url:"+url+"；port："+port+";applyid："+sourceid+pyd.getType());
//            convertInfo=jedis.hgetAll(sourceid+pyd.getType());
//            int i=0;
//            String state=convertInfo.get("state")+"";
//            String damage=convertInfo.get("damage")+"";
//            String memo=convertInfo.get("memo")+"";
//            if((Util.isEoN(damage)||"\"\"".equals(damage))&&!"2".equals(state)){
//                while(state.indexOf("1")<1&& i<15){
//                    Thread.sleep(2000);
//                    convertInfo=jedis.hgetAll(sourceid+pyd.getType());
//                    state=convertInfo.get("state")+"";
//                    i++;
//                    planStatusMap.put(sourceid,Float.toString((85+i)/10));
//                }
//                String urlStr=convertInfo.get("downloadUrl")+"";
//                if (!(Util.isEoN(urlStr)||"\"\"".equals(urlStr))) {
//                    urlStr=urlStr.replace("\"http","http");
//                    urlStr=urlStr.replace(".pdf\"",".pdf");
//                    /* urlStr=urlStr.replace("115.238.84.52","192.168.1.106");*/
//                    log.info("【redis生成成功】：urlStr:"+urlStr);
//                    //先生成记录，用于盖章
//                    String recordid=this.sysDownRecordService.commonSaveOperation(request,sourceid,"pdf生成--通用","urlStr："+urlStr,urlStr,pyd.getType(),"");
//                    //调用盖章
//                    if(!Util.isEoN(pyd.getSignsql())){
//                        log.info("【redis生成成功--需盖章】recordid："+recordid);
//                        String signBusinessId=this.dbHelper.getOnlyStringValue(pyd.getSignsql(),new Object[]{sourceid});
//                        String resultString=this.electronicsealchoiceService.CreatSignTask(signBusinessId,recordid);
//                        log.info("【盖章情况】："+resultString);
//                        SysParamvalue sv2=this.sysParamvalueService.findParamsClassByCodeAndTcodeOne("sysparamdata", SysParamDataEnum.PDFDOWNURL.getMessage());
//                        String pdfdownurl=sv2.getValue();
//                        String newPdffile=Util.GetFileRealPath(urlStr.replace(pdfdownurl,"/PMSFILES/document/"));
//                        byte[] bytes = OperationPdfUtil.fileToByteArray(newPdffile);
//                        File file1 = new File(newPdffile);
//                        FileOutputStream outPutStream = new FileOutputStream(file1);
//                        OperationPdfUtil.setPassWorkmark(bytes,outPutStream);
//                    }
//                    /*buffer = getWaterBytes(pyd, sourceid, waterMarkName, waterMarkStyle, opacity, destinationFileName, buffer);
//                    planStatusMap.put(sourceid,"9");
//                    String realpath=pyd.getRealpath()+"";//最终pdf放的路径
//                    File realpathFile = new File(realpath);
//                    if (!realpathFile.exists()) {
//                        realpathFile.mkdirs();
//                    }
//                    try {
//                        WordUtil.copy(destinationFileName, realpathFile+"/"+pdfname+".pdf");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }*/
//                }else{
//                    errmsg="【未生成访问路径】";
//                    returnToJs.setSuccess(false);
//                    returnToJs.setErrMsg(errmsg.replace("\"","")+"，请联系系统维护人员："+Util.linkNumber);
//                    log.error("【redis未生成路径】/PmsDownLoad/downloadPdfByConfig.do方法报错");
//                }
//            }else{
//                errmsg=(damage+memo).replaceAll("\"","");
//                returnToJs.setSuccess(false);
//                returnToJs.setErrMsg(errmsg.replace("\"","")+"，请联系系统维护人员："+Util.linkNumber);
//                log.error("/PmsDownLoad/downloadPdfByConfig.do方法报错");
//            }
//            planStatusMap.put(sourceid,"10");
//        }catch (JedisDataException el){
//            log.error("/PmsDownLoad/downloadPdfByConfig.do方法报错;getRedisDownurl:redis数据错误"+Util.getExceptionAllinformation(el));
//        }catch (JedisConnectionException c1){
//            log.error("/PmsDownLoad/downloadPdfByConfig.do方法报错;getRedisDownurl:redis连接错误"+Util.getExceptionAllinformation(c1));
//        }catch (InterruptedException c2){
//            log.error("/PmsDownLoad/downloadPdfByConfig.do方法报错;getRedisDownurl:sleep错误"+Util.getExceptionAllinformation(c2));
//        }catch (IOException c3){
//            log.error("/PmsDownLoad/downloadPdfByConfig.do方法报错;getRedisDownurl:文件获取错误"+Util.getExceptionAllinformation(c3));
//        }catch (DocumentException c4){
//            log.error("/PmsDownLoad/downloadPdfByConfig.do方法报错;getRedisDownurl:水印错误"+Util.getExceptionAllinformation(c4));
//        }
//        return errmsg;
//    }
//
    /**
     * 这个方法是用来解决那些段落没法换行的
     * @param map
     * @return
     */
    private Map GenericSpecialPDFMap(Map map){
        if ("9ba0a44e-a545-86d9-6096-4da18f8ec9af".equals(map.get("basicInfo_normal_planprojectbatchid")+"")){
            //2023年农村科技特派员专题
            try {
                String expObj_normal_jslyandresults = map.get("expObj_normal_jslyandresults")+"";
                String expObj_normal_jslyandresults_PDF = Util.changeTopdf(expObj_normal_jslyandresults);
                map.put("expObj_normal_jslyandresults_PDF",expObj_normal_jslyandresults_PDF);

                String expObj_normal_jobmemo = map.get("expObj_normal_jobmemo")+"";
                String expObj_normal_jobmemo_PDF = Util.changeTopdf(expObj_normal_jobmemo);
                map.put("expObj_normal_jobmemo_PDF",expObj_normal_jobmemo_PDF);

                List<Map> serviceExp = (List<Map>) map.get("serviceExp");
                List<Map> newServiceExp = new ArrayList<>();
                for (Map map1 : serviceExp) {
                    String experience = map1.get("experience")+"";
                    String experience_PDF = Util.changeTopdf(experience);
                    map1.put("experience_PDF",experience_PDF);
                    newServiceExp.add(map1);
                }
                map.put("serviceExp",newServiceExp);
            }catch (Exception e){
//                log.error("字段转换成pdf格式失败");
//                log.error(e.getMessage());
            }

        }

        return map;
    }

//    public void downloadPdfByModelFortypeNew(String sourceid,String pdfname,String plOrnot,PmsDownloadtype pyd, String ...otherInfo) throws IOException,
//            TemplateException, DocumentException, java.text.ParseException {
//        // 获取项目全路径
//        String errmsg="";
//        String filePath = pyd.getInprogram();//"D:/"
//        filePath=Util.GetFileRealPath("");
//        // 创建属于该项目id的文件夹
//        String tmpFolderPath = filePath + "/PdfTmpFile/Allpdf/pdf";
//        File tmpFolderPathFile = new File(tmpFolderPath);
//        if (!tmpFolderPathFile.exists()) {
//            tmpFolderPathFile.mkdirs();
//        }
//        // 存储生成后的word临时文件路径
//        String tmpWordFile = pyd.getTmpwordfile();//tmpFolderPath + "/tempdocCg.doc";
//        // 存储生成后的pdf临时文件路径
//        String tmpPDFFile = pyd.getTmppdffile();//tmpFolderPath + "/tempdocCg.pdf";
//        String idtype=pyd.getIdtype()+"";
//        if(tmpWordFile.indexOf(idtype)>-1){//20200414---分项目id放文件夹
//            tmpWordFile=filePath+tmpWordFile.replace("【"+idtype+"】",sourceid);
//            System.out.println("tmpWordFile："+tmpWordFile);
//        }
//        if(tmpPDFFile.indexOf(idtype)>-1){
//            tmpPDFFile=filePath+tmpPDFFile.replace("【"+idtype+"】",sourceid);
//            System.out.println("tmpPDFFile："+tmpPDFFile);
//        }
//        // 选择使用的模板路径
//        String modelFilePath = "";
//        modelFilePath = PmsDownLoadController.class.getResource("/").getPath();
//        modelFilePath = modelFilePath.replace("WEB-INF/classes/","pdfmodel");
//
//        Map<String, Object> hashmap = new HashMap<String, Object>();
//        /*各个参数*/
//        String justdown = pyd.getJustdown()+"";
//        String openname = pyd.getOpenname()+"";//"";
//        if(!Util.isEoN(pyd.getOpennamesql())){
//            openname=this.dbHelper.getOnlyStringValue(pyd.getOpennamesql(),new Object[]{sourceid});
//        }
//        String downLoadType = pyd.getDownloadtype()+"";//下载格式，如果是doc，加个赋为doc"pdf";
//        try {
//
//            JSONObject json = Util.checkFromJson(otherInfo[0]);
//            String batchid = otherInfo[1];
//            String type = json.get("businesstype").toString();
//            JSONObject dataObject =getConfigData(null, sourceid, batchid, type);
//            hashmap = genericFetchAndSave.downloadData(dataObject, sourceid, batchid, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            errmsg="获取数据源失败";
//            log.error("/PmsDown/downloadPdfByConfig.do 方法报错。"+errmsg);
//        }
//        //用来生成_PDF格式的数据源
//        hashmap = GenericSpecialPDFMap(hashmap);
//        if (!Util.isEoN(pyd.getTwodimsql())) {
//            String TwoDimImgPath=twoDimCodeService.getTwoDimImgPath(sourceid,pyd);
//            hashmap.put("TwoDimImgPath",TwoDimImgPath);
//        }
//
//        if (!"D://PdfTmpFile/Allpdf/pdf/tempdocCg.doc".equals(tmpWordFile)) {
//            tmpFolderPathFile = new File(tmpWordFile.replace(
//                    "/tempdocCg.doc", "").replace("tempdoc.doc",""));//tempdoc.doc也替换下
//            if (!tmpFolderPathFile.exists()) {
//                tmpFolderPathFile.mkdirs();
//            }
//        }
//        byte[] bytes =null;
//        if(justdown.equals("true")){
//            // 将生成好的pdf文件转byte数组
//            bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//        }else{
//            bytes = this.generatePdfByModelFortypeNew(pyd,sourceid,pdfname,modelFilePath,
//                    tmpWordFile, tmpPDFFile,hashmap);
//        }
//    }
//
//    public byte[] generatePdfByModelFortypeNew(PmsDownloadtype pyd,String sourceid,String pdfname,
//                                               String modelFilePath,String tmpWordFile, String tmpPDFFile,Map<String, Object> hashmap) throws MalformedTemplateNameException,
//            ParseException, IOException, TemplateException, DocumentException {
//
//        // 需要添加水印的内容
//        String waterMarkName = pyd.getWatername();
//        String modelName = pyd.getModelname()+"";//"expertSignCg.xml"
//        String waterMarkStyle = pyd.getWatermarkstyle()+"";//水印样式，如果是Watermark则样式是布满全局，其他则在下方
//        float opacity = Float.parseFloat(pyd.getOpacity());//0.6f;
//        String generateType= pyd.getGeneratetype()+"";//生成方式，用书签还是freemark "free",book
//        String idtype=pyd.getIdtype()+"";//id传参参数
//        String[]  ModelOrAffixlis;
//        /*处理下条件判断*/
//        String ModeloraffixlistS=this.pmsDownconfigService.getString(sourceid,pyd.getModeloraffixlist());
//        if(Util.isEoN(ModeloraffixlistS)){
//            ModelOrAffixlis=new String[]{};
//        }else{
//            ModelOrAffixlis=ModeloraffixlistS.split("；");
//        }
//        if(pyd.getType().equals("xmryqd")){
//            if(!Util.isEoN(hashmap.get("review_method"))&&(hashmap.get("review_method").toString().equals("现场考察"))){
//                ModelOrAffixlis=new String[]{"xmryqd.xml&book"};
//            }else{
//                ModelOrAffixlis=new String[]{"xmryqdhy.xml&book"};
//            }
//        }
//        byte[] bytes=null;
//        // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
//        SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
//        String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
//        if (ModelOrAffixlis.length<1) {
//            if("book".equals(generateType)){
//                WordUtil.createPdfByordanalyzeNewToPdf(modelFilePath, modelName, tmpWordFile, tmpPDFFile,
//                        hashmap,toPdfType);
//            }else{
//                WordUtil.createPdfNewToPdf(modelFilePath, modelName, tmpWordFile, tmpPDFFile,
//                        hashmap,toPdfType);
//            }
//            // 将生成好的pdf文件转byte数组
//            bytes = OperationPdfUtil.fileToByteArray(tmpPDFFile);
//            String destinationFileName = tmpPDFFile;
//            // 如果水印名称waterMarkName不为空字符串，说明需要添加水印，并替换以前生成的pdf文件
//            if (waterMarkName != "") {
//                File file = new File(destinationFileName);
//                FileOutputStream outPutStream = new FileOutputStream(file);
//                if (waterMarkName.contains("【时间】")){
//                    String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//                    waterMarkName = waterMarkName.replace("【时间】",format);
//                }
//                if(waterMarkStyle.equals("Watermark")){//水印样式
//                    OperationPdfUtil.setWatermark(bytes, outPutStream, waterMarkName,
//                            opacity);
//                }else{
//                    OperationPdfUtil.setWatermark01(bytes, outPutStream, waterMarkName,
//                            opacity);
//                }
//                bytes = OperationPdfUtil.fileToByteArray(destinationFileName);
//            }
//            String realpath=pyd.getRealpath()+"";//最终pdf放的路径
//            File realpathFile = new File(realpath);
//            try {
//                WordUtil.copy(destinationFileName, realpathFile+"/"+pdfname+".pdf");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return bytes;
//        } else {
//            planStatusMap.put(sourceid,"2");
//            bytes=this.jointAffixAndmoreModelForDownNew(pyd,sourceid,pdfname,ModelOrAffixlis,modelFilePath,tmpWordFile,tmpPDFFile,waterMarkName,waterMarkStyle,opacity,hashmap);
//            return bytes;
//        }
//    }
//
//    public byte[] jointAffixAndmoreModelForDownNew(PmsDownloadtype pyd,String sourceid,String pdfname,String[] ModeloraffixList,
//                                                   String modelFilePath, String tmpWordFile,
//                                                   String tmpPDFFile, String waterMarkName,String waterMarkStyle, float opacity,Map<String, Object> hashmap)
//            throws MalformedTemplateNameException, ParseException, IOException,
//            TemplateException, DocumentException {
//        // 根据批次id获取相应的模板名称
//        // 通过先生成word模板的方式再转pdf（生成好了word以及pdf）
//        List<String> filesInFolder = new ArrayList<String>();
//        // 将所有的扫描件转换成PDF，为了下一步进行拼接
//        planStatusMap.put(sourceid,"5");
//        SysParamvalue sysParamvalue=sysParamvalueService.findById("WordToPdfType1001");
//        String toPdfType=Util.isEoN(sysParamvalue)?"word2PdfSuwell":sysParamvalue.getValue();
//        if(ModeloraffixList.length>0){
//            int tempnum=0;
//            for(int i=0;i<ModeloraffixList.length;i++){
//                String showmodelnameobj=ModeloraffixList[i]+"";
//                if ("可行性报告".equals(showmodelnameobj) || "基础与应用基础项目可行性报告".equals(showmodelnameobj)){
//                    continue;
//                }
//                if(showmodelnameobj.indexOf(".xml")!=-1){//如果不包含.xml，说明不是模板生成，是附件
//                    String[]  downtypelis=showmodelnameobj.split("&");
//                    tempnum=tempnum+1;
//                    String showmodelname="";
//                    String downtype="";
//                    if(downtypelis.length>1){
//                        showmodelname=downtypelis[0];//当前模板
//                        downtype=downtypelis[1];//当前模板生成调用方法
//                    }
//                    String tmpWordFileend=tmpWordFile.replace("tempdoc", "tempdoc"+tempnum);
//                    String tmpPDFFileend=tmpWordFileend.replace(".doc",".pdf");
//                    System.out.print("tmpPDFFileend:"+tmpPDFFileend);
//                    if("book".equals(downtype)){//如果是书签
//                        log.warn("word地址："+tmpWordFileend);
//                        log.warn("pdf地址："+tmpPDFFileend);
//                        WordUtil.createPdfByordanalyzeNewToPdf(modelFilePath, showmodelname,
//                                tmpWordFileend,tmpPDFFileend, hashmap,toPdfType);
//                    }else{
//                        log.warn("word地址："+tmpWordFileend);
//                        log.warn("pdf地址："+tmpPDFFileend);
//                        WordUtil.createPdfNewToPdf(modelFilePath, showmodelname,
//                                tmpWordFileend,
//                                tmpPDFFileend, hashmap,toPdfType);
//                    }
//                    filesInFolder.add(tmpPDFFileend);
//                }else{
//                    System.out.print("tmpPDFFile:"+tmpPDFFile);
//                    // 将所有的扫描件转换成PDF，为了下一步进行拼接
//                    try {
//                        if(tmpPDFFile.indexOf("tempdocCg.pdf")!=-1) {
//                            pmsAffixService.getuploadFileAttachment(
//                                    filesInFolder,
//                                    sourceid,
//                                    tmpPDFFile.substring(0,
//                                            tmpPDFFile.lastIndexOf("/tempdocCg.pdf")), ModeloraffixList[i]);
//                        }else{
//                            if(tmpPDFFile.indexOf(sourceid+".pdf")>-1){
//                                pmsAffixService.getuploadFileAttachment(
//                                        filesInFolder,
//                                        sourceid,
//                                        tmpPDFFile.substring(0,
//                                                tmpPDFFile.lastIndexOf("/"+sourceid+".pdf")), ModeloraffixList[i]);
//                            }else if (tmpPDFFile.indexOf(sourceid+".pdf")>-1){
//
//                            }
//                        }
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                }
//
//            }}
//
//
//        String destinationFileName = tmpPDFFile;
//        /*拼接方式*/
//        if (!"word2PdfSuwell".equals(toPdfType)) {
//            /*原版拼接*/
//            log.info("mergeDocuments拼接");
//            PDFMergerUtility mergePdf = new PDFMergerUtility();
//            if(filesInFolder.size()<1){
//                return null;
//            }
//            for (int i = 0; i < filesInFolder.size(); i++)
//                mergePdf.addSource(filesInFolder.get(i));
///*            for (int i = 0; i < newFilesInfolder.size(); i++)
//                mergePdf.addSource(newFilesInfolder.get(i));*/
//
//            mergePdf.setDestinationFileName(destinationFileName);
//            try {
//                mergePdf.mergeDocuments();
//            } catch (COSVisitorException e1) {
//                e1.printStackTrace();
//            }
//        } else {
//            Word2PdfSuwellUtil.mergepdf(filesInFolder,destinationFileName);
//            /*            Word2PdfSuwellUtil.mergepdf(newFilesInfolder,destinationFileName);*/
//        }
//
//        byte[] buffer = null;
//        try {
//            File file = new File(destinationFileName);
//            FileInputStream fis = new FileInputStream(file);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
//            byte[] b = new byte[1000];
//            int n;
//            while ((n = fis.read(b)) != -1) {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            bos.close();
//            buffer = bos.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (!Util.isEoN(hashmap.get("TwoDimImgPath"))) {//如果有二维码图片路径
//            String tmpPDFFileEwm=tmpPDFFile.replace(".pdf","Ewm.pdf");
//            OperationPdfUtilText.setEwm(tmpPDFFile,tmpPDFFileEwm,hashmap.get("TwoDimImgPath").toString());//插入二维码图片
//            buffer = OperationPdfUtil.fileToByteArray(tmpPDFFileEwm);
//            try{
//                WordUtil.copy(tmpPDFFileEwm, tmpPDFFile);//复制到初始文档，作下载
//            }catch (Exception e){
//                System.out.println("复制失败");
//            }
//        }
//        String addpagenum=pyd.getAddpagenum()+"";
//        if(!Util.isEoN(addpagenum)&&!"false".equals(addpagenum)){
//            String tmpPDFFilePage=tmpPDFFile.replace(".pdf","Page.pdf");
//            String pageFormat=Util.isEoN(pyd.getPageformat())?"第%d页 共%d页":pyd.getPageformat();
//            tmpPDFFilePage=PdfUtil.addPageNumNew(tmpPDFFile,tmpPDFFilePage,pageFormat);
//            buffer = OperationPdfUtil.fileToByteArray(tmpPDFFilePage);
//        }
//        //调用水印
//        if (!Util.isEoN(waterMarkName)){
//            if (waterMarkName.contains("【时间】")){
//                String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//                waterMarkName = waterMarkName.replace("【时间】",format);
//            }else if (waterMarkName.contains("【flow时间】")){
//                List<FlowRecord> filter = flowRecordService.findFilter("sourceid = '" + sourceid + "' order by operationtime desc");
//                if (!Util.isEoN(filter) && filter.size()>0){
//                    FlowRecord flowRecord = filter.get(0);
//                    String currentflowpointname = flowRecord.getCurrentflowpointname();
//                    if (currentflowpointname.contains("完成")){
//                        Date operationtime = flowRecord.getOperationtime();
//                        String format = new SimpleDateFormat("yyyy-MM-dd").format(operationtime);
//                        waterMarkName = waterMarkName.replace("【flow时间】",format);
//                    }else {
//                        waterMarkName = waterMarkName.replace("【flow时间】","");
//                    }
//                }else{
//                    waterMarkName = waterMarkName.replace("【flow时间】","");
//                }
//            }
//        }
//        buffer = getWaterBytes(pyd, sourceid, waterMarkName, waterMarkStyle, opacity, destinationFileName, buffer);
//        // //调用盖章
//        // if(!Util.isEoN(pyd.getSignsql())){
//        //     String destinationFileNameDecryptUrl=destinationFileName.replace(".pdf","decrypt.pdf");
//        //     try {
//        //         OperationPdfUtil.setDecryptPDF(destinationFileName,destinationFileNameDecryptUrl);
//        //     } catch (Exception e) {
//        //         e.printStackTrace();
//        //     }
//        //     //先生成记录，用于盖章
//        //     String recordid=this.sysDownRecordService.commonSaveOperationNew(sourceid,"pdf生成--通用","pdfname："+pdfname.replace(".pdf","decrypt.pdf"),destinationFileNameDecryptUrl,pyd.getType(),"");
//        //     /*获取业务id，再生成*/
//        //     String signBusinessId=this.dbHelper.getOnlyStringValue(pyd.getSignsql(),new Object[]{sourceid});
//        //     String resultString=this.electronicsealchoiceService.CreatSignTask(signBusinessId,recordid);
//        //     System.out.print("盖章情况："+resultString);
//        // }
//        planStatusMap.put(sourceid,"9");
//        String realpath=Util.GetFileRealPath(pyd.getRealpath()+"");//最终pdf放的路径
//        if("true".equals(pyd.getInprogram())){
//            realpath=modelFilePath.replace("pdfmodel","")+pyd.getRealpath()+"";
//        }
//        File realpathFile = new File(realpath);
//        if (!realpathFile.exists()) {
//            realpathFile.mkdirs();
//        }
//        try {
//            WordUtil.copy(destinationFileName, realpathFile+"/"+pdfname+".pdf");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        planStatusMap.put(sourceid,"10");
//        return buffer;
//    }
}
