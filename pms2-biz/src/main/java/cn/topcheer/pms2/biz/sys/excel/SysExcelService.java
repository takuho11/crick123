/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-9-24 11:57:03
 *
 */
package cn.topcheer.pms2.biz.sys.excel;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.utils.HttpUtil;
import cn.topcheer.halberd.app.api.utils.PmsTxtSave;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysIdentityServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.biz.modules.resource.builder.oss.OssBuilder;
import cn.topcheer.pms2.api.pml.entity.SysDownRecord;
import cn.topcheer.pms2.api.pml.vo.ReturnToExcel;
import cn.topcheer.pms2.api.pml.vo.ReturnToExcelMerge;
import cn.topcheer.pms2.api.pml.vo.ReturnToExcelStyle;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.excel.*;
import cn.topcheer.pms2.biz.pml.service.impl.PmlGridExportService;
import cn.topcheer.pms2.biz.pml.service.impl.SysDownRecordService;
import cn.topcheer.pms2.biz.pms.CreatePdfOrWordNew;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.sys.excel.SysExcelDao;
import jxl.Cell;
import jxl.Sheet;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * SysExcel 服务类
 */
@Service(value="SysExcelService")
@Slf4j
public class SysExcelService extends GenericService<SysExcel> {

	public SysExcelDao getSysExcelDao() {
		return (SysExcelDao) this.getGenericDao();
	}

	@Autowired
	public void setSysExcelDao(SysExcelDao sysExcelDao) {

		this.setGenericDao(sysExcelDao);
	}

    @Autowired
    private OssBuilder ossBuilder;


    @Autowired
    private DBHelper dbHelper;
    @Autowired
    SysExcelSheetService sysExcelSheetService;
    @Autowired
    SysExcelTabService sysExcelTabService;
    @Autowired
    SysExcelParamService sysExcelParamService;
    @Autowired
    SysExcelStyleService sysExcelStyleService;
    @Autowired
    SysExcelFontService sysExcelFontService;
    @Autowired
    SysOperationrecordService sysOperationrecordService;
    @Autowired
    SysDownRecordService sysDownRecordService;

    @Autowired
    SysIdentityServiceImpl sysIdentityService;

    @Autowired
    private CreatePdfOrWordNew createPdfOrWord;
//    @Autowired
//    PmsClobService pmsClobService;
    @Autowired
    PmsTxtSave pmsTxtSave;
    @Autowired
    PmlGridExportService pmsGridExportService;

    //修改新增信息
    public ReturnToJs sysExcelSave(JSONObject json, ReturnToJs returnToJs){
        String id ="";
        BladeUser user= AuthUtil.getUser();
        SysExcel pdtype = new SysExcel();
        Date aa=new Date();
        if(Util.isEoN(json.get("id"))){
            id= Util.NewGuid();
            Util.ApplyObject(pdtype,json);
            pdtype.setId(id);
            pdtype.setCreattime(aa);
            pdtype.setCreatname(user.getUserName());
            pdtype.setCreatuserid(user.getUserId());
        }else{
            id=json.get("id")+"";
            pdtype = this.findById(id);
        }
        Util.ApplyObject(pdtype,json);
        pdtype.setUpdatetime(aa);
        this.merge(pdtype);
        returnToJs.setData(id);
        returnToJs.setSuccess(true);
        pmsTxtSave.saveTxt(id,json,"SysExcel","sysExcelSave");
        return  returnToJs;
    }

    //查询信息
    public JSONArray sysExcelFind(JSONObject json) {
        String type="";
        String insql=" ";
        JSONArray paramvalueList=new JSONArray();
        if(!Util.isEoN(json.get("searchContent"))){
            type="%"+json.get("searchContent").toString().trim()+"%";
            insql+="and (t.name like ? or t.roleid like ?) ";
            paramvalueList.add(type);

        }
        if(!Util.isEoN(json.get("datasourcetype"))){
            type=json.get("datasourcetype").toString().trim();
            insql+="and t.id in ( select s.excelid from sys_excel_tab tab inner join sys_excel_sheet s" +
                    " on s.id=tab.sheetid where tab.datasourcetype=? group by s.excelid) ";
            paramvalueList.add(type);
        }
        if(!Util.isEoN(json.get("sheetName"))){
            type="%"+json.get("sheetName").toString().trim()+"%";
            insql+=" and t.name like ? ";
            paramvalueList.add(type);
        }
        String sql = "select t.* from sys_excel t where 1=1"+insql+" order by t.creattime desc";
        List<Map> listMap = dbHelper.getRows(sql,paramvalueList.toArray());
        if (listMap != null && listMap.size() > 0)
            return JSONArray.fromObject(listMap);
        else
            return null;
    }
    //删除信息
    public ReturnToJs sysExcelDelete(JSONObject json, ReturnToJs returnToJs) {
        String id=(json.get("id")+"").trim();
        SysExcel isHaveSysVersion = this.findById(id);
        try {
            if(!Util.isEoN(isHaveSysVersion)){
                /*说明这个版本id在数据库已经保存过了，要先清除相关联的数据，重新保存新给的对象*/
                //1、sysExcelSheet表
                List<SysExcelSheet> sysExcelSheetList = this.sysExcelSheetService.findByProperty("excelid",id);
                if(!Util.isEoN(sysExcelSheetList)&&sysExcelSheetList.size()>0){
                    for (int i = 0; i < sysExcelSheetList.size(); i++) {
                        String sheetid = sysExcelSheetList.get(i).getId();
                        //2、sysExcelTab表
                        List<SysExcelTab> oldSysVersionObjectList = this.sysExcelTabService.findByProperty("sheetid",sheetid);
                        if(!Util.isEoN(oldSysVersionObjectList)&&oldSysVersionObjectList.size()>0){
                            for (int j = 0; j < oldSysVersionObjectList.size(); j++) {
                                String sourceid = oldSysVersionObjectList.get(j).getId();
                                String styleid = oldSysVersionObjectList.get(j).getStyleid();
                                //3、sysExcelParam表
                                List<SysExcelParam> oldSysVersionFieldList = this.sysExcelParamService.findByProperty("sourceid",sourceid);
                                if(!Util.isEoN(oldSysVersionFieldList)&&oldSysVersionFieldList.size()>0){
                                    //1、删除sysExcelParam表
                                    this.sysExcelParamService.deleteList(oldSysVersionFieldList);
                                }
                            }
                            //2、删除sysExcelTab表
                            this.sysExcelTabService.deleteList(oldSysVersionObjectList);
                        }
                        //3、sysExcelParam表--sheet
                        List<SysExcelParam> oldSysVersionFieldSheetList = this.sysExcelParamService.findByProperty("sourceid",sheetid);
                        if(!Util.isEoN(oldSysVersionFieldSheetList)&&oldSysVersionFieldSheetList.size()>0){
                            //1、删除sysExcelParam表
                            this.sysExcelParamService.deleteList(oldSysVersionFieldSheetList);
                        }
                    }
                    //3、删除sysExcelSheet表
                    this.sysExcelSheetService.deleteList(sysExcelSheetList);
                }
                //3、sysExcelParam表--excel
                List<SysExcelParam> oldSysVersionFieldExcelList = this.sysExcelParamService.findByProperty("sourceid",id);
                if(!Util.isEoN(oldSysVersionFieldExcelList)&&oldSysVersionFieldExcelList.size()>0){
                    //1、删除sysExcelParam表
                    this.sysExcelParamService.deleteList(oldSysVersionFieldExcelList);
                }
                //4、删除excel表
                this.deleteById(id);
            }
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            returnToJs.setSuccess(false);
        }
        return  returnToJs;

    }

    //获取信息
    public JSONArray sysExcelFindbyid(String id) {
            String sql = "select t.* from sys_excel t where t.id like ? order by t.creattime";
            List<Map> listMap = dbHelper.getRows(sql,new Object[]{id});
            if (listMap != null && listMap.size() > 0) {
                return JSONArray.fromObject(listMap);
            }
        else{
                return new JSONArray();
            }
    }

    //初始化信息
    public JSONArray sysExcelInit() {
        String sql = "select t.* from sys_excel t order by t.creattime desc";
        List<Map> listMap = dbHelper.getRows(sql,new Object[]{});
        if (listMap != null && listMap.size() > 0) {
            return JSONArray.fromObject(listMap);
        }else {
            return null;
        }
    }

    /**
     * 【版本配置】---复制：通过excelid生成新的excel版本以及相关信息，返回一个全新的版本id
     * @param excelid
     * @return
     */
    public String excelCopyById(String excelid,	BladeUser scuser){
        try{
            {
                //版本表
                SysExcel oldSysExcel = this.findById(excelid);
                SysExcel newSysExcel = (SysExcel)oldSysExcel.clone();
                String newexcelid= Util.NewGuid();
                newSysExcel.setId(newexcelid);
                newSysExcel.setName(newSysExcel.getName()+"(复制版)");
                newSysExcel.setCreatuserid(scuser.getUserId());
                newSysExcel.setCreattime(new Date());
                newSysExcel.setCreatname(scuser.getUserName());
                //复制版本表
                newSysExcel = this.merge(newSysExcel);
                if(!Util.isEoN(oldSysExcel)){
                    //excelsheet 工作簿
                    List<SysExcelSheet> oldSysExcelSheetList = this.sysExcelSheetService.findByProperty("excelid",excelid);
                    if(!Util.isEoN(oldSysExcelSheetList)&&oldSysExcelSheetList.size()>0){
                        for (int i = 0; i < oldSysExcelSheetList.size(); i++) {
                            String sheetid = oldSysExcelSheetList.get(i).getId();
                            this.sysExcelSheetCopyById(sheetid,newexcelid,scuser);
                        }
                    }
                    //参数表
                    List<SysExcelParam> oldSysExcelParamList = this.sysExcelParamService.findByProperty("sourceid",excelid);
                    if(!Util.isEoN(oldSysExcelParamList)&&oldSysExcelParamList.size()>0){
                        //复制参数表
                        for (int k = 0; k <oldSysExcelParamList.size() ; k++) {
                            String paramid = oldSysExcelParamList.get(k).getId();
                            this.sysExcelParamCopyById(paramid,newexcelid,scuser);
                        }
                    }
                    return newSysExcel.getId();
                }else{
                    return "";
                }
            }
        }catch(Exception e){
            return "";
        }
    }


    /**
     * 【版本配置--工作簿】---复制：通过sheetid生成新的sheet版本以及相关信息，返回一个全新的版本id
     * @param sourceid
     * @return
     */
    public String sysExcelSheetCopyById(String sourceid,String newUpperid,BladeUser user){
        try{
            {
                //版本表
                SysExcelSheet oldSysExcelSheet = this.sysExcelSheetService.findById(sourceid);
                String newsheetid = Util.NewGuid();
                SysExcelSheet newExcelSheet = (SysExcelSheet)oldSysExcelSheet.clone();
                newExcelSheet.setId(newsheetid);
                newExcelSheet.setSheetname(!Util.isEoN(newUpperid)?oldSysExcelSheet.getSheetname():oldSysExcelSheet.getSheetname()+"(复制版)");
                newExcelSheet.setExcelid(!Util.isEoN(newUpperid)?newUpperid:oldSysExcelSheet.getExcelid());
                //复制版本表
                newExcelSheet = this.sysExcelSheetService.merge(newExcelSheet);
                if(!Util.isEoN(oldSysExcelSheet)){
                    //模块表
                    List<SysExcelTab> oldSysExcelTabList = this.sysExcelTabService.findByProperty("sheetid",sourceid);
                    if(!Util.isEoN(oldSysExcelTabList)&&oldSysExcelTabList.size()>0){
                        for (int j = 0; j < oldSysExcelTabList.size(); j++) {
                            String tabid = oldSysExcelTabList.get(j).getId();
                            this.sysExcelTabCopyById(tabid,newsheetid,user);
                        }
                    }
                    //参数表
                    List<SysExcelParam> oldSysExcelParamList = this.sysExcelParamService.findByProperty("sourceid",sourceid);
                    if(!Util.isEoN(oldSysExcelParamList)&&oldSysExcelParamList.size()>0){
                        //复制参数表
                        for (int k = 0; k <oldSysExcelParamList.size() ; k++) {
                            String paramid = oldSysExcelParamList.get(k).getId();
                            this.sysExcelParamCopyById(paramid,newsheetid,user);
                        }
                    }
                    return newsheetid;
                }else{
                    return "";
                }
            }
        }catch(Exception e){
            return "";
        }
    }


    /**
     * 【版本配置】---复制：通过tabid生成新的sheet版本以及相关信息，返回一个全新的版本id
     * @param sourceid
     * @return
     */
    public String sysExcelTabCopyById(String sourceid,String newUpperid,	BladeUser user){
        try{
            {
                //版本表
                SysExcelTab oldSysExcelTab = this.sysExcelTabService.findById(sourceid);
                String newtabid = Util.NewGuid();
                SysExcelTab newExcelTab = (SysExcelTab)oldSysExcelTab.clone();
                newExcelTab.setId(newtabid);
                newExcelTab.setTabname(!Util.isEoN(newUpperid)?oldSysExcelTab.getTabname():oldSysExcelTab.getTabname()+"(复制版)");
                newExcelTab.setSheetid(!Util.isEoN(newUpperid)?newUpperid:oldSysExcelTab.getSheetid());
                //复制版本表
                newExcelTab = this.sysExcelTabService.merge(newExcelTab);
                if(!Util.isEoN(oldSysExcelTab)){
                    //参数表
                    List<SysExcelParam> oldSysExcelParamList = this.sysExcelParamService.findByProperty("sourceid",sourceid);
                    if(!Util.isEoN(oldSysExcelParamList)&&oldSysExcelParamList.size()>0){
                        //复制参数表
                        for (int k = 0; k <oldSysExcelParamList.size() ; k++) {
                            String paramid = oldSysExcelParamList.get(k).getId();
                            this.sysExcelParamCopyById(paramid,newtabid,user);
                        }
                    }
                    return newtabid;
                }else{
                    return "";
                }
            }
        }catch(Exception e){
            return "";
        }
    }


    /**
     * 【版本配置】---复制：通过paramid生成新的sheet版本以及相关信息，返回一个全新的版本id
     * @param sourceid
     * @return
     */
    public String sysExcelParamCopyById(String sourceid,String newUpperid,BladeUser user){
        try{
            {
                //版本表
                SysExcelParam oldSysExcelParam = this.sysExcelParamService.findById(sourceid);
                String newparamid = Util.NewGuid();
                SysExcelParam newExcelParam = (SysExcelParam)oldSysExcelParam.clone();
                newExcelParam.setId(newparamid);
                newExcelParam.setName(oldSysExcelParam.getName()+"(复制版)");
                newExcelParam.setSourceid(!Util.isEoN(newUpperid)?newUpperid:oldSysExcelParam.getSourceid());
                //复制版本表
                newExcelParam = this.sysExcelParamService.merge(newExcelParam);
                if(!Util.isEoN(oldSysExcelParam)){
                    return newparamid;
                }else{
                    return "";
                }
            }
        }catch(Exception e){
            return "";
        }
    }


    /**
     * 【版本配置】---复制：通过styleid生成新的样式版本以及相关信息，返回一个全新的版本id
     * @param sourceid
     * @return
     */
    public String sysExcelStyleCopyById(String sourceid,	BladeUser user){
        try{
            {
                //样式版本表
                SysExcelStyle oldSysExcelStyle = this.sysExcelStyleService.findById(sourceid);
                String newstyleid = Util.NewGuid();
                SysExcelStyle newExcelStyle = (SysExcelStyle)oldSysExcelStyle.clone();
                newExcelStyle.setName(oldSysExcelStyle.getName()+"(复制版)");
                newExcelStyle.setId(newstyleid);
                //复制
                newExcelStyle = this.sysExcelStyleService.merge(newExcelStyle);
                if(!Util.isEoN(oldSysExcelStyle)){
                    return newstyleid;
                }else{
                    return "";
                }
            }
        }catch(Exception e){
            return "";
        }
    }


    /**
     * 【版本配置】---复制：通过fontid生成新的字体版本以及相关信息，返回一个全新的版本id
     * @param sourceid
     * @return
     */
    public String sysExcelFontCopyById(String sourceid,	BladeUser user){
        try{
            {
                //字体
                SysExcelFont oldSysExcelFont = this.sysExcelFontService.findById(sourceid);
                String newfontid = Util.NewGuid();
                SysExcelFont newExcelFont = (SysExcelFont)oldSysExcelFont.clone();
                newExcelFont.setId(newfontid);
                newExcelFont.setName(oldSysExcelFont.getName()+"(复制版)");
                //复制版本表
                newExcelFont = this.sysExcelFontService.merge(newExcelFont);
                if(!Util.isEoN(oldSysExcelFont)){
                    return newfontid;
                }else{
                    return "";
                }
            }
        }catch(Exception e){
            return "";
        }
    }
    //获取该excel的参数
    public ReturnToJs sysExcelFindParamkey(ReturnToJs returnToJs, String id, String type) {
        String sql = "select t.value from sys_excel_param t inner join sys_excel_tab tab on tab.id=t.sourceid " +
                "inner join sys_excel_sheet sheet on sheet.id=tab.sheetid " +
                "inner join sys_excel e on e.id=sheet.excelid " +
                "where e.id=? and t.type=? group by t.value";
        List<Map> listMap = dbHelper.getRows(sql,new Object[]{id,type});
        returnToJs.setData(listMap);
        returnToJs.setSuccess(true);
        return returnToJs;
    }
    //获取该excel的参数---通过数据源类型
    public ReturnToJs sysExcelFindParamkeyForJson(ReturnToJs returnToJs, String id, String type, String datatype) {
        if("qtcc".equals(datatype)){
            datatype="前台传参";
        }
        String sql = "select t.value from sys_excel_param t inner join sys_excel_tab tab on tab.id=t.sourceid " +
                "inner join sys_excel_sheet sheet on sheet.id=tab.sheetid " +
                "inner join sys_excel e on e.id=sheet.excelid " +
                "where e.id=? and t.type=? and tab.datasourcetype=? group by t.value";
        List<Map> listMap = dbHelper.getRows(sql,new Object[]{id,type,datatype});
        returnToJs.setData(listMap);
        returnToJs.setSuccess(true);
        return returnToJs;
    }
    //获取该excel的sheet表和tab的列头
    public ReturnToJs sysExcelFindTabKeyHead(ReturnToJs returnToJs, String id, String type) {
        List<SysExcelSheet> excelSheetList=this.sysExcelSheetService.findByHql("select t from SysExcelSheet t where t.excelid = ?0 order by t.seq",new Object[]{id});
        JSONArray sheetArray=new JSONArray();
        for (int i = 0; i < excelSheetList.size(); i++) {
            SysExcelSheet excelSheet=excelSheetList.get(i);
            List<SysExcelTab> excelTabList=this.sysExcelTabService.findByHql("select t from SysExcelTab t where t.sheetid = ?0 order by t.seq",
                    new Object[]{excelSheet.getId()});
            JSONArray tabArray=new JSONArray();
            for (int i1 = 0; i1 < excelTabList.size(); i1++) {
                SysExcelTab exceltab=excelTabList.get(i1);
                List<SysExcelParam> tabKeyHead=this.sysExcelParamService.findByHql("select u.value from SysExcelParam u" +
                        " where u.sourceid = ?0 and u.type = 'tabKeyHead' order by u.seq ",new Object[]{exceltab.getId()});
                JSONArray paramArray=JSONArray.fromObject(tabKeyHead);
                JSONObject tab=JSONObject.fromObject(exceltab);
                tab.put("paramArray",paramArray);
                tabArray.add(tab);
            }
            JSONObject sheet=JSONObject.fromObject(excelSheet);
            sheet.put("tabArray",tabArray);
            sheetArray.add(sheet);
        }
        returnToJs.setData(sheetArray);
        returnToJs.setSuccess(true);
        return returnToJs;
    }

    /*
    * {excelName:"",allData:[{sheetData:[],sheetName:"",sheetKeyHead:[{name:""}],sheetKeyValue:[{name:""}],sheetStartRow:"",
    * sheetBig:{sheetBigName:"",sheetBigHeightInPoints:"",sheetBigMergedRegion:""}}]}
    * excel名字，总数据，工作表数据，工作表名字，工作表表头，工作表循环数组字段,开始行
    * 大标题相关：大标题内容，大标题高度，大标题合并单元格列数
    * */
    public ReturnToJs getSheetAllClass(Map<String, Object> map, ReturnToJs returnToJs, HttpServletRequest request, HttpServletResponse response) {
        log.info("【SysExcelService--getSheetAllClass】开始生成excel");
        returnToJs.setSuccess(true);
        String excelName="";
        String excelUrl="";
        String Enname="";
        String sourceid=request.getParameter("excelid");
        /*前台数据*/
        JSONObject jsonObject = HttpUtil.getBodyJSON(request, "UTF-8");
        JSONArray sheetArrayOn=new JSONArray();
        if (!Util.isEoN(jsonObject.get("sheetArray"))) {
            sheetArrayOn=jsonObject.getJSONArray("sheetArray");
        }
        String sheetfixAll="";
        for (int i = 0; i < sheetArrayOn.size(); i++) {
            sheetfixAll+=sheetArrayOn.getJSONObject(i).get("sheetfixkey")+";";
        }
        SysExcel exceldata=this.findById(sourceid);
        List<SysExcelSheet> sheetdataList=this.sysExcelSheetService.sysExcelSheetFindByid(sourceid);
        // 创建文档对象(其他对象都是通过文档对象创建)
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            if(!Util.isEoN(exceldata.getName())&&!Util.isEoN(sheetdataList)){//开始定义excel
                excelName=exceldata.getName()+"";
                if(!Util.isEoN(exceldata.getConditionsql())){
                    //查前台的参数key
                    Object[] paramvalueSheetList = getParamObjects(request, jsonObject, sourceid, "threeCondition");
                    excelName=this.dbHelper.getOnlyStringValue(exceldata.getConditionsql(),paramvalueSheetList);
                }
                for (int i = 0; i < sheetdataList.size(); i++) {
                    log.info("【SysExcelService--getSheetAllClass】共"+sheetdataList.size()+"个sheet，第"+(i+1)+"个；");
                    SysExcelSheet sheetdata=sheetdataList.get(i);
                    String sheetfixkey=sheetdata.getSheetfixkey();
                    String sheetid=sheetdata.getId();
                    List<Map> sheetsqlList=new ArrayList<>();
                    if(!Util.isEoN(sheetdata.getSheetsql())){
                        if ("allGrid".equals(sheetdata.getSheetsql())) {
                            sheetsqlList=JSONArray.fromObject(this.pmsGridExportService.fetchExportDataSource(jsonObject));
                            if (Util.isEoN(sheetsqlList)) {
                                returnToJs.setSuccess(false);
                                returnToJs.setData("");
                                returnToJs.setErrMsg("通用列表数据获取失败");
                                log.error("/SysExcel/excelExport.do 方法报错,错误信息：通用列表数据获取失败");
                            }
                        } else {
                            String sheetsql=sheetdata.getSheetsql();
                            //查前台的参数key
                            Object[] paramvalueSheetList = getParamObjects(request, jsonObject, sheetid, "");
                            sheetsqlList=this.dbHelper.getRows(sheetsql,paramvalueSheetList);
                        }
                    }
                    /*获取前台固定列sheet*/
                    JSONObject sheetObjOn=new JSONObject();
                    if(sheetArrayOn.size()>i&&!Util.isEoN(sheetArrayOn.getJSONObject(i))){
                        sheetObjOn=sheetArrayOn.getJSONObject(i);
                    }
                    //是否显示工作簿，为空显示，前台sheetArray里的fix为空显示，传了array包含显示
                    Boolean isAddSheet= Util.isEoN(sheetfixkey)?true:
                            Util.isEoN(sheetfixAll)?true:
                            sheetfixAll.contains(sheetfixkey+";")?true:false;
                    if (isAddSheet) {
                        if (sheetsqlList.size()>0) {//如果需要根据sql第一sheet
                            for (int sheetsql_i = 0; sheetsql_i < sheetsqlList.size(); sheetsql_i++) {
                                Map sheetsqlMap=sheetsqlList.get(sheetsql_i);
                                returnToJs=creatSheet(returnToJs, request, jsonObject, wb, sheetdata, sheetid, sheetObjOn,sheetsqlMap);
                                if(!returnToJs.isSuccess()){
                                    return returnToJs;
                                }
                            }
                        }else{
                            returnToJs=creatSheet(returnToJs, request, jsonObject, wb, sheetdata, sheetid, sheetObjOn,new HashMap<>());
                            if(!returnToJs.isSuccess()){
                                return returnToJs;
                            }
                        }

                    }
                }
                if(wb.getNumberOfSheets()>0){
                    try {
//                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//                        wb.write(byteArrayOutputStream);
//                        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//                        BladeFile bladeFile = ossBuilder.template().putFile(excelName+".xls",byteArrayInputStream,"download");
//                        byteArrayOutputStream.close();
//                        byteArrayInputStream.close();
//                        String fileId = UUID.randomUUID().toString();
//                        String path = "D:/temp/" + fileId + "/" + excelName + ".xls";
//                        File dest = new File(path);
//                        // 检测是否存在目录
//                        if (!dest.getParentFile().exists()) {
//                            dest.getParentFile().mkdirs();
//                        }
//                        OutputStream tempout = new FileOutputStream(dest);
//                        wb.write(tempout);
//                        tempout.close();
//                        String recordid=this.sysDownRecordService.commonSaveOperationV2(request,sourceid,"excel通用下载","excel名称："+excelName,path,excelName);
//                        returnToJs.setSuccess(true);
//                        returnToJs.setData(byteArrayInputStream);

                        ServletOutputStream outputStream = response.getOutputStream();
                        wb.write(outputStream);
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("/SysExcel/excelExport.do 方法报错,错误信息：service层报错"+e);
                    }

                }else{
                    returnToJs.setSuccess(false);
                    returnToJs.setData("");
                    returnToJs.setErrMsg("未定义工作簿");
                    log.error("/SysExcel/excelExport.do 方法报错,错误信息：未定义工作簿");
                }
            }else{
                returnToJs.setSuccess(false);
                returnToJs.setData("");
                returnToJs.setErrMsg("未定义文档名字或数据");
                log.error("/SysExcel/excelExport.do 方法报错,错误信息：未定义文档名字或数据");
            }
        } catch (Exception e) {
            returnToJs.setSuccess(false);
            returnToJs.setData("");
            returnToJs.setErrMsg("生成失败");
            log.error("/SysExcel/excelExport.do 方法报错,错误信息：service层报错"+e);
            e.printStackTrace();
        }
        return returnToJs;
    }

    private ReturnToJs creatSheet(ReturnToJs returnToJs, HttpServletRequest request, JSONObject jsonObject, HSSFWorkbook wb,
                               SysExcelSheet sheetdata, String sheetid, JSONObject sheetObjOn, Map sheetsqlMap) throws Exception {
        String errorinfo="";
        String sheetname=sheetdata.getSheetname();
        if(!Util.isEoN(sheetdata.getSheetsql())){
            sheetname=sheetsqlMap.get("sheetname").toString();
        }
        log.info("【SysExcelService--creatSheet】开始生成，工作簿名称为："+sheetname);
        /*根据前台列，对应列，用于列宽及列调整*/
        JSONArray sheetfixColumnList=new JSONArray();
        HSSFSheet sheet = wb.createSheet(sheetname.replaceAll("(\\:|\\/)","-"));
        if("true".equals(sheetdata.getLandscape())){
            sheet.getPrintSetup().setLandscape(true);
        }
        //设置页脚
        /*HSSFFooter footer = sheet.getFooter();
        footer.setCenter( "第 " + HSSFFooter.page() +"页 / 共 " + HSSFFooter.numPages()+"页");*/
        //是否自适应界面
        //sheet.setFitToPage(true);

        int startRow=0;
        List<SysExcelTab> tabdataList=this.sysExcelTabService.sysExcelTabFindByid(sheetid);
        /*获取前台tab*/
        JSONArray tabArrayOn=new JSONArray();
        if (!Util.isEoN(sheetObjOn)&&!Util.isEoN(sheetObjOn.get("tabArray"))) {
            tabArrayOn=sheetObjOn.getJSONArray("tabArray");
        }
        for (int i1 = 0; i1 < tabdataList.size(); i1++) {
            SysExcelTab tabObj=tabdataList.get(i1);
            JSONObject tabObjOn=new JSONObject();
            if(tabArrayOn.size()>i1&&!Util.isEoN(tabArrayOn.getJSONObject(i1))){
                tabObjOn=tabArrayOn.getJSONObject(i1);
            }
            String tabid=tabObj.getId();
            log.info("【SysExcelService--creatTab】开始生成，模块id为："+tabid+":tab"+tabObj.getMemo());
            /**========================Start：获取相应样式========================**/
            String styleid=tabObj.getStyleid();//暂时不要
            String bigstyleid=tabObj.getBigstyleid();//大标题样式
            String headstyleid=tabObj.getHeadstyleid();//数据标题样式
            String valuestyleid=tabObj.getValuestyleid();//数据值样式
            String iscolumnwidth=Util.isEoN(tabObj.getIscolumnwidth())?"否":tabObj.getIscolumnwidth();//是否根据该tab的列标记列宽
            CellStyle cellStyle = wb.createCellStyle();
            if(!Util.isEoN(bigstyleid)){
                cellStyle=this.getStyle(wb,styleid);
            }
            CellStyle bigcellStyle = cellStyle;
            if(!Util.isEoN(bigstyleid)){
                bigcellStyle=this.getStyle(wb,bigstyleid);
            }
            CellStyle headcellStyle = cellStyle;
            if(!Util.isEoN(headstyleid)){
                headcellStyle=this.getStyle(wb,headstyleid);
            }
            CellStyle valuecellStyle = cellStyle;
            if(!Util.isEoN(valuestyleid)){
                valuecellStyle=this.getStyle(wb,valuestyleid);
            }
            /**========================end：获取相应样式========================**/
            int tabstartrow=Integer.parseInt(Util.isEoN(tabObj.getTabstartrow())?"0":tabObj.getTabstartrow());
            int lastendrow=startRow;
            startRow=startRow+tabstartrow;

            /*获取固定列--根据前台*/
            JSONArray fixColumnList=new JSONArray();
            if (!Util.isEoN(tabObjOn.get("fixColumnList"))) {//当前tab有
                fixColumnList=tabObjOn.getJSONArray("fixColumnList");
            }else if (!Util.isEoN(jsonObject.get("fixColumnList"))) {//最外层有
                fixColumnList=jsonObject.getJSONArray("fixColumnList");
            }
            if(iscolumnwidth.equals("是")){
                sheetfixColumnList=fixColumnList;
            }
            /**========================Start：大标题========================**/
            if(!Util.isEoN(tabObj.getSheetbig())&&"是".equals(tabObj.getSheetbig())){
                List<SysExcelParam> bigObjList=this.sysExcelParamService.findByHql("select u from SysExcelParam u" +
                        " where u.sourceid=?0 and u.type='sheetbig' order by u.seq ",new Object[]{tabid});
                if(bigObjList.size()>0){
                    SysExcelParam bigObj=bigObjList.get(0);
                    JSONObject mergedObj=JSONObject.fromObject(bigObj.getValue());
                    int HeightInPoints=Integer.parseInt(Util.isEoN(mergedObj.get("heightinpoints"))?"0":mergedObj.getString("heightinpoints"));//行高
                    int firstrow=Integer.parseInt(Util.isEoN(mergedObj.get("firstrow"))?"0":mergedObj.getString("firstrow"));//当前模块的第几行
                    int lastrow=Integer.parseInt(Util.isEoN(mergedObj.get("lastrow"))?"0":mergedObj.getString("lastrow"));//当前模块的第几行
                    int firstcol=Integer.parseInt(Util.isEoN(mergedObj.get("firstcol"))?"0":mergedObj.getString("firstcol"));
                    int lastcol=Integer.parseInt(Util.isEoN(mergedObj.get("lastcol"))?"0":mergedObj.getString("lastcol"));
                    String bigtitle= Util.isEoN(mergedObj.get("bigtitle"))?"":mergedObj.getString("bigtitle");
                    String changecol= Util.isEoN(mergedObj.get("changecol"))?"":mergedObj.getString("changecol");
                    if(fixColumnList.size()>0&&"true".equals(changecol)){
                        lastcol=fixColumnList.size()-1;
                    }
                    Row nRow = null;
                    org.apache.poi.ss.usermodel.Cell nCell = null;
                    nRow = sheet.createRow(startRow+firstrow);
                    nRow.setHeightInPoints(HeightInPoints);//设置行高
                    nCell = nRow.createCell(firstcol);//创建单元格对象
                    //合并单元格 首行、最后一行、首列、最后一列
                    sheet.addMergedRegion(new CellRangeAddress(lastendrow+firstrow,lastendrow+lastrow,firstcol,lastcol));//横向合并单元格
                    //设置单元格的内容
                    nCell.setCellValue(bigtitle);
                    //设置单元格样式
                    if(Util.isEoN(bigcellStyle)){
                        nCell.setCellStyle(this.getBigTitleStyle(wb,"center"));
                    }else{
                        nCell.setCellStyle(bigcellStyle);
                    }
                    startRow=startRow+(lastrow+1);
                }
            }
            /**========================End：大标题========================**/
            String datasourcetyp=tabObj.getDatasourcetype();
            String datasourcesql=tabObj.getDatasourcesql();
            String datasourcemethodservice=tabObj.getDatasourcemethodservice();
            String datasourcemethodname=tabObj.getDatasourcemethodname();
            String dataMethodtype=tabObj.getDatamethodtype();//固定传参，应用与同一个数据源、同一个excel，多个sheet
            if(!Util.isEoN(sheetdata.getSheetsql())&&!Util.isEoN(dataMethodtype)){//如果dataMethodtype不为空，则获取sheet的可变参数
                dataMethodtype=sheetsqlMap.get(dataMethodtype).toString();
            }
            JSONArray tabData = new JSONArray();//放循环数据
            List<ReturnToExcelMerge> mergeArray = new ArrayList<>();//放循环数据--的循环合并数据
            List<ReturnToExcelStyle> styleArray = new ArrayList<>();//放循环数据--的循环样式数据
            List<ReturnToExcelStyle> tabheadArray = new ArrayList<>();//放循环数据--的循环标题数据
            JSONArray tabKeyArray=new JSONArray();//放循环数据--的key自定义
            /*--获取前台传参*/
            //查前台的参数key
            Object[] paramvalueList = getParamObjects(request, jsonObject, tabid, dataMethodtype);
            switch (datasourcetyp){
                case "sql":
                    tabData=JSONArray.fromObject(this.dbHelper.getRows(datasourcesql,paramvalueList));
                    break;
                case "hql":
                    tabData=JSONArray.fromObject(this.sysDownRecordService.findByHql(datasourcesql,paramvalueList));
                    break;
                case "后台方法":
                    //ReturnToExcel methodObj=(ReturnToExcel) Util.springInvokeMethod(datasourcemethodservice,datasourcemethodname,paramvalueList);
                    ReturnToExcel methodObj=new ReturnToExcel();
                    if (!"allGrid".equals(sheetdata.getSheetsql())) {
                        try {
                            Object obj= Util.springInvokeMethod(datasourcemethodservice,datasourcemethodname,paramvalueList);
                            if(obj instanceof ReturnToExcel){//返回的是 正常数据
                                methodObj=(ReturnToExcel) obj;
                                if(methodObj.getData() instanceof JSONArray){
                                    tabData=(JSONArray)methodObj.getData();
                                }else if(methodObj.getData() instanceof ArrayList){
                                    tabData=JSONArray.fromObject(methodObj.getData());
                                }else if(methodObj.getData() instanceof JSONObject){
                                    tabData = getJsonArray(tabObj, tabData, JSONObject.fromObject(methodObj.getData()));
                                }else if(methodObj.getData() instanceof Map){
                                    tabData = getJsonArray(tabObj, tabData, JSONObject.fromObject(methodObj.getData()));
                                }
                                if(!Util.isEoN(methodObj.getMergeArray())){//合并单元格
                                    mergeArray = methodObj.getMergeArray();
                                }
                                if(!Util.isEoN(methodObj.getStyleArray())){//样式数组
                                    styleArray = methodObj.getStyleArray();
                                }
                            }else if(obj instanceof List){//返回的是list数组
                                tabData=JSONArray.fromObject(obj);
                            }else if(obj instanceof JSONArray){//返回的是json数组
                                tabData=(JSONArray)obj;
                            }else if(obj instanceof JSONObject){//返回的是json对象
                                tabData = getJsonArray(tabObj, tabData, JSONObject.fromObject(obj));
                                /*JSONObject dataobj=JSONObject.fromObject(obj);
                                tabData.add(dataobj);*/
                            }else if(obj instanceof Map){//返回的是map对象
                                tabData = getJsonArray(tabObj, tabData, JSONObject.fromObject(obj));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            errorinfo+="【excel导出失败】:后台方法，请检查后台方法是否存在；";
                            log.error(errorinfo+Util.getExceptionAllinformation(e));
                        }
                    } else {
                        tabData=(JSONArray) sheetsqlMap.get("data");
                        if(!Util.isEoN(sheetsqlMap.get("merge"))){//合并单元格
                            JSONArray array=(JSONArray) sheetsqlMap.get("merge");
                            HSSFRow rowmerge = sheet.createRow(startRow);
                            for (int i = 0; i < array.size(); i++) {
                                JSONObject mergeObj=array.getJSONObject(i);
                                ReturnToExcelMerge merge=new ReturnToExcelMerge();
                                Integer firstcol=mergeObj.getInt("firstcol");
                                Integer lastcol=mergeObj.getInt("lastcol");
                                if (firstcol!=lastcol) {
                                    merge.setFirstrow(-1);
                                    merge.setLastrow(-1);
                                    merge.setFirstcol(mergeObj.getInt("firstcol"));
                                    merge.setLastcol(mergeObj.getInt("lastcol"));
                                    mergeArray.add(merge);
                                }
                                for (int j = firstcol; j <lastcol+1 ; j++) {
                                    HSSFCell cell = rowmerge.createCell(j);
                                    cell.setCellStyle(headcellStyle);
                                    cell.setCellValue(mergeObj.getString("name"));
                                }
                            }
                            startRow++;
                        }//fixColumnList
                        if(!Util.isEoN(sheetsqlMap.get("field"))){//表头标题
                            JSONObject fieldObj=(JSONObject) sheetsqlMap.get("field");
                            Iterator<String> sIterator = fieldObj.keys();
                            while(sIterator.hasNext()){
                                // 获得key
                                String key = sIterator.next();
                                fixColumnList.add(fieldObj.get(key));
                                tabKeyArray.add(key);
                            }
                        }
                    }
                    //System.out.print(methodObj.getData());
                    //tabData = (JSONArray) methodObj.getData();
                    break;
                case "前台传参":
                    tabData=jsonObject.getJSONArray("data");
                    break;
                case "无":
                    break;
            }
            List<SysExcelParam> tabkeyhead=this.sysExcelParamService.findByHql("select u from SysExcelParam u" +
                    " where u.sourceid=?0 and u.type='tabKeyHead' order by u.seq ",new Object[]{tabid});
            List<SysExcelParam> tabkeyvalue=this.sysExcelParamService.findByHql("select u from SysExcelParam u" +
                    " where u.sourceid=?0 and u.type='tabKeyValue' order by u.seq ",new Object[]{tabid});
            HSSFRow row;
            HSSFRow row1;
            int datastartRow=startRow+1;
            Boolean ishaveHead=true;
            if(tabkeyhead.size()>0){
                row1 = sheet.createRow(startRow);
                int newIndex=0;
                if(fixColumnList.size()<1){//没传列名
                    for (int k = 0; k < tabkeyhead.size(); k++) {
                        HSSFCell cell = row1.createCell(k);
                        cell.setCellStyle(headcellStyle);
                        cell.setCellValue(Util.isEoN(tabkeyhead.get(k).getValue())?"":tabkeyhead.get(k).getValue()+"");
                    }
                }else{
                    //如果前台有固定列，需同步处理key值数据
                    List<SysExcelParam> tabkeyvalueNew=new ArrayList<>();
                    for (int k = 0; k < tabkeyhead.size(); k++) {
                        if (fixColumnList.contains(tabkeyhead.get(k).getValue())){
                            HSSFCell cell = row1.createCell(newIndex);
                            cell.setCellStyle(headcellStyle);
                            cell.setCellValue(tabkeyhead.get(k).getValue()+"");
                            SysExcelParam param1=tabkeyvalue.get(k);
                            tabkeyvalueNew.add(param1);
                            newIndex++;
                        }
                    }
                    tabkeyvalue=tabkeyvalueNew;
                }
            }else if(fixColumnList.size()>0){
                row1 = sheet.createRow(startRow);
                int newIndex=0;
                for (int k = 0; k < fixColumnList.size(); k++) {
                    HSSFCell cell = row1.createCell(k);
                    cell.setCellStyle(headcellStyle);
                    cell.setCellValue(fixColumnList.get(k).toString());
                }
            }else{
                ishaveHead=false;
                datastartRow=startRow;
            }
            if(tabData.size()>=65536){
                returnToJs.setSuccess(false);
                returnToJs.setData("");
                returnToJs.setErrMsg("生成失败,excel最多65536，行数已超");
                return returnToJs;
            }
            for (int j = datastartRow; j < tabData.size() + datastartRow; j++) {
                row = sheet.createRow(j);
                JSONObject job = tabData.getJSONObject(j - datastartRow);
                if (tabkeyvalue.size()>0) {
                    int mergeCount=this.dbHelper.getRowsCount("select t.id from sys_excel_param t where t.sourceid=? and t.type='tabkeyvalue' and memo='是'",new Object[]{tabid});
                    if (mergeCount<1) {//说明是要跨列的，或者合并单元格的
                        for (int s = 0; s < tabkeyvalue.size(); s++) {
                            String mycell = tabkeyvalue.get(s).getValue()+"";
                            String tabkeyvalueReall = mycell;
                            String tabkeyvalueKey = tabkeyvalue.get(s).getKey()+"";
                            setCellValueAndStyle(wb, valuecellStyle, row, datastartRow, j, job, s, mycell, tabkeyvalueReall, tabkeyvalueKey);
                        }
                    } else {
                        int nowCol=0;
                        for (int s = 0; s < tabkeyvalue.size(); s++) {
                            String mycell = tabkeyvalue.get(s).getValue()+"";
                            String tabkeyvalueReall = mycell;
                            String tabkeyvalueKey = tabkeyvalue.get(s).getKey()+"";
                            if ("是".equals(tabkeyvalue.get(s).getMemo())) {//是否跨列
                                String othermemo=tabkeyvalue.get(s).getOthermemo();
                                JSONObject othermemoObj=JSONObject.fromObject(othermemo);
                                int heightinpoints=Util.isEoN(othermemoObj.get("heightinpoints"))?25:othermemoObj.getInt("heightinpoints");
                                int firstcol=Util.isEoN(othermemoObj.get("firstcol"))?0:othermemoObj.getInt("firstcol");
                                int lastcol=Util.isEoN(othermemoObj.get("lastcol"))?0:othermemoObj.getInt("lastcol");
                                String ismerge=Util.isEoN(othermemoObj.get("ismerge"))?"":othermemoObj.get("ismerge").toString();
                                if ("是".equals(ismerge)) {//是否合并单元格
                                    ReturnToExcelMerge merge=new ReturnToExcelMerge();
                                    merge.setHeightinpoints(heightinpoints);
                                    merge.setFirstrow(j - datastartRow+1);
                                    merge.setLastrow(j - datastartRow+1);
                                    merge.setFirstcol(firstcol);
                                    merge.setLastcol(lastcol);
                                    mergeArray.add(merge);
                                }
                                for (int m = firstcol; m< lastcol+1; m++) {
                                    setCellValueAndStyle(wb, valuecellStyle, row, datastartRow, j, job, m, mycell, tabkeyvalueReall, tabkeyvalueKey);
                                }
                                nowCol=lastcol+1;
                            }else{
                                setCellValueAndStyle(wb, valuecellStyle, row, datastartRow, j, job, nowCol, mycell, tabkeyvalueReall, tabkeyvalueKey);
                                nowCol++;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < tabKeyArray.size(); i++) {
                        HSSFCell cellKey = row.createCell(i);
                        cellKey.setCellStyle(valuecellStyle);
                        if(job.get(tabKeyArray.get(i).toString()) instanceof String){
                            cellKey.setCellValue(job.getString(tabKeyArray.get(i).toString()));
                        }else if(job.get(tabKeyArray.get(i).toString()) instanceof Double||job.get(tabKeyArray.get(i).toString()) instanceof Integer){
                            cellKey.setCellValue(job.getDouble(tabKeyArray.get(i).toString()));
                        }
                    }
                }
            }

            /*if(tabkeyhead.size()>0){//标题有就+1
                startRow=tabData.size() + (startRow+1);
            }else{
                startRow=tabData.size() + (startRow);
            }*/
            startRow=tabData.size()+datastartRow;
            /**========================Start：固定----合并单元格========================**/
            try {
                setTabFixMerge(sheet, tabObj, tabid, lastendrow);
            } catch (Exception e) {
                e.printStackTrace();
                errorinfo+="【excel导出失败】:固定合并单元格，请检查合并单元格是否有对应的行列数据；";
                log.error(errorinfo+Util.getExceptionAllinformation(e));
            }
            /**========================End：固定----合并单元格========================**/

            /**========================Start：根据数据源--灵活----合并单元格========================**/
            try {
                setTabChangeMerge(sheet, cellStyle, lastendrow, mergeArray,ishaveHead);
            } catch (Exception e) {
                e.printStackTrace();
                errorinfo+="【excel导出失败】:灵活合并单元格，请检查合并单元格是否有对应的行列数据；";
                log.error(errorinfo+Util.getExceptionAllinformation(e));
            }
            /**========================End：灵活----合并单元格========================**/

            /**========================Start：固定----样式========================**/
            try {
                setTabFixStyle(wb, sheet, tabObj, tabid, lastendrow);
            } catch (Exception e) {
                e.printStackTrace();
                errorinfo+="【excel导出失败】:固定--样式，请检查固定样式是否有对应的行列数据；";
                log.error(errorinfo+Util.getExceptionAllinformation(e));
            }
            /**========================End：固定----样式========================**/

            /**========================Start：根据数据源--灵活----样式========================**/
            try {
                setTabChangeStyle(wb, sheet, lastendrow, styleArray,ishaveHead);
            } catch (Exception e) {
                e.printStackTrace();
                errorinfo+="【excel导出失败】:灵活--样式，请检查灵活样式是否有对应的行列数据；";
                log.error(errorinfo+Util.getExceptionAllinformation(e));
            }
            /**========================End：灵活----样式========================**/
            log.info("【SysExcelService--creatTab】生成完毕，模块id为："+tabid+";");

        }
        try {
            setSheetColumn(sheetid, sheet,sheetsqlMap);
        } catch (Exception e) {
            e.printStackTrace();
            errorinfo+="【excel导出失败】:未找到目标列宽的tab模块，请设置列宽对应模块；";
            log.error(errorinfo+Util.getExceptionAllinformation(e));
        }
        if(!Util.isEoN(errorinfo)){
            returnToJs.setErrMsg(errorinfo);
            returnToJs.setSuccess(false);
        }
        log.info("【SysExcelService--creatSheet】生成完毕，工作簿名称为："+sheetname+";生成情况："+errorinfo);
        return returnToJs;
    }

    /*给单元格赋值和样式*/
    private void setCellValueAndStyle(HSSFWorkbook wb, CellStyle valuecellStyle, HSSFRow row, int datastartRow, int j, JSONObject job, int s, String mycell, String tabkeyvalueReall, String tabkeyvalueKey) {
        if ("是".equals(tabkeyvalueKey)) {
            int value=Integer.parseInt(mycell);
            HSSFCell cellKey = row.createCell(s);
            cellKey.setCellStyle(valuecellStyle);
            cellKey.setCellValue(value+(j-datastartRow));
        }else {
            HSSFCell cellKey = row.createCell(s);
            Boolean isHaveNewStyle=false;
            //固定值，以【【固定值】】@@name||||【【固定值】】@@sex
            if ("固定部分值".equals(tabkeyvalueKey)) {
                if(mycell.indexOf("@@")>-1){//可能存在多个固定值
                    String[]  KeyLis=mycell.split("@@");
                    for (String keyli : KeyLis) {
                        if(keyli.indexOf("【【")<0){//如果没有说明不是固定值
                            if (job.containsKey(keyli)) {//如果有这个key
                                if (!Util.isEoN(job.get("styleid"))&&
                                        !Util.isEoN(job.get("stylecolumn"))&&
                                        (job.getString("stylecolumn").contains(keyli)||"all".equals(job.getString("stylecolumn")))) {//如果有传样式id就处理下
                                    CellStyle newStyle=this.getStyle(wb,job.getString("styleid"));
                                    cellKey.setCellStyle(newStyle);
                                    isHaveNewStyle=true;
                                }
                                /*处理值*/
                                tabkeyvalueReall = tabkeyvalueReall.replaceAll(keyli,job.getString(keyli));
                            }
                        }
                    }
                    if(!isHaveNewStyle){//如果上面循环时，未设置特殊样式就插入整体样式
                        cellKey.setCellStyle(valuecellStyle);
                    }
                    tabkeyvalueReall = tabkeyvalueReall.replaceAll("@@","").replaceAll("【【","").replaceAll("】】","");
                    cellKey.setCellValue(tabkeyvalueReall);
                }
            }else{
                if (!Util.isEoN(job.get("styleid"))&&
                        !Util.isEoN(job.get("stylecolumn"))&&
                        (job.getString("stylecolumn").contains(mycell)||"all".equals(job.getString("stylecolumn")))) {//如果有传样式id就处理下
                    CellStyle newStyle=this.getStyle(wb,job.getString("styleid"));
                    cellKey.setCellStyle(newStyle);
                }else{
                    cellKey.setCellStyle(valuecellStyle);
                }
                if (job.containsKey(mycell)) {
                    cellKey.setCellValue(job.getString(mycell));
                }
            }
        }
    }

    private JSONArray getJsonArray(SysExcelTab tabObj, JSONArray tabData, JSONObject dataobj) {
        if(!Util.isEoN(tabObj.getDatamethodkey())){//如果是固定key
            String datamethodkey=tabObj.getDatamethodkey();
            if(!Util.isEoN(dataobj.get(datamethodkey))){
                if(dataobj.get(datamethodkey) instanceof JSONArray){
                    tabData=(JSONArray)dataobj.get(datamethodkey);
                }else if(dataobj.get(datamethodkey) instanceof JSONObject){
                    tabData.add(dataobj.get(datamethodkey));
                }else if(dataobj.get(datamethodkey) instanceof String){
                    JSONObject object=new JSONObject();
                    object.put(datamethodkey,dataobj.get(datamethodkey));
                    tabData.add(object);
                }
            }
        }else{
            tabData.add(dataobj);
        }
        return tabData;
    }

    private Object[] getParamObjects(HttpServletRequest request, JSONObject jsonObject, String sourceid, String dataMethodtype) {
        List<SysExcelParam> paramkey;
        if ("threeCondition".equals(dataMethodtype)) {
            paramkey = this.sysExcelParamService.findByHql("select u from SysExcelParam u" +
                    " where u.sourceid=?0 and u.type='paramkey' and u.key='threeCondition' order by u.seq ",new Object[]{sourceid});
        } else {
            paramkey = this.sysExcelParamService.findByHql("select u from SysExcelParam u" +
                    " where u.sourceid=?0 and u.type='paramkey' order by u.seq ",new Object[]{sourceid});
        }
        Object[] paramvalueList=new Object[paramkey.size()];
        if(!Util.isEoN(dataMethodtype)&&!"threeCondition".equals(dataMethodtype)){//如果不为空，并且不是三个条件的threeCondition
            paramvalueList=new Object[paramkey.size()+1];
            paramvalueList[paramkey.size()]=dataMethodtype;
        }
        //根据key获取value
        for (int i2 = 0; i2 < paramkey.size(); i2++) {//获取传参，getParameter获取不到就获取postjson
            if ("jsonObjectType".equals(paramkey.get(i2).getValue())) {
                paramvalueList[i2]= jsonObject;
            } else if ("HttpServletRequest".equals(paramkey.get(i2).getValue())) {
                paramvalueList[i2]= request;
            } else{
                paramvalueList[i2]= Util.isEoN(request.getParameter(paramkey.get(i2).getValue()))?
                        jsonObject.get(paramkey.get(i2).getValue()):request.getParameter(paramkey.get(i2).getValue());
            }
        }
        return paramvalueList;
    }

    /*固定合并单元格*/
    private String setTabFixMerge(HSSFSheet sheet, SysExcelTab tabObj, String tabid, int lastendrow) {
        String errorinfo="";
        if(!Util.isEoN(tabObj.getMergedregion())&&"是".equals(tabObj.getMergedregion())){
            List<SysExcelParam> mergeObjList=this.sysExcelParamService.findByHql("select u from SysExcelParam u" +
                    " where u.sourceid=?0 and u.type='mergedregion' order by u.seq ",new Object[]{tabid});
            if(mergeObjList.size()>0){

                for (int i11=0;i11<mergeObjList.size();i11++) {
                    SysExcelParam bigObj=mergeObjList.get(i11);
                    JSONObject mergedObj=JSONObject.fromObject(bigObj.getValue());
                    int HeightInPoints=Integer.parseInt(Util.isEoN(mergedObj.getString("heightinpoints"))?"0":mergedObj.getString("heightinpoints"));//行高
                    int firstrow=Integer.parseInt(Util.isEoN(mergedObj.getString("firstrow"))?"0":mergedObj.getString("firstrow"));//当前模块的第几行
                    int lastrow=Integer.parseInt(Util.isEoN(mergedObj.getString("lastrow"))?"0":mergedObj.getString("lastrow"));//当前模块的第几行
                    int firstcol=Integer.parseInt(Util.isEoN(mergedObj.getString("firstcol"))?"0":mergedObj.getString("firstcol"));
                    int lastcol=Integer.parseInt(Util.isEoN(mergedObj.getString("lastcol"))?"0":mergedObj.getString("lastcol"));/*Row nRow = null;
                        nRow = sheet.createRow(tabstartrow+firstrow);
                        nRow.setHeightInPoints(HeightInPoints);//设置行高*/
                    sheet.getRow(lastendrow+firstrow).setHeightInPoints(HeightInPoints);//设置行高
                    //合并单元格 首行、最后一行、首列、最后一列
                    sheet.addMergedRegion(new CellRangeAddress(lastendrow+firstrow,lastendrow+lastrow,firstcol,lastcol));//横向合并单元格
                }
                    /*//设置单元格样式
                    nCell.setCellStyle(this.getBigTitleStyle(wb,"center"));*/
            }
        }
        return errorinfo;
    }

    /*灵活合并单元格*/
    private String setTabChangeMerge(HSSFSheet sheet, CellStyle cellStyle, int lastendrow, List<ReturnToExcelMerge> mergeArray,Boolean ishaveHead) {
        String errorinfo="";

        if (mergeArray.size()>0) {
            int firstrow=0;
            int lastrow=0;
            int firstcol=0;
            int lastcol=0;
            int HeightInPoints=0;
            //cellStyle.setAlignment(HorizontalAlignment.CENTER);
            //cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            for (int i2 = 0; i2 < mergeArray.size(); i2++) {
                HeightInPoints=Util.isEoN(mergeArray.get(i2).getHeightinpoints())?0:mergeArray.get(i2).getHeightinpoints();//行高
                firstrow= Util.isEoN(mergeArray.get(i2).getFirstrow())?0:ishaveHead?mergeArray.get(i2).getFirstrow()+1:mergeArray.get(i2).getFirstrow();
                lastrow= Util.isEoN(mergeArray.get(i2).getLastrow())?0:ishaveHead?mergeArray.get(i2).getLastrow()+1:mergeArray.get(i2).getLastrow();
                firstcol= Util.isEoN(mergeArray.get(i2).getFirstcol())?0:mergeArray.get(i2).getFirstcol();
                lastcol= Util.isEoN(mergeArray.get(i2).getLastcol())?0:mergeArray.get(i2).getLastcol();
                sheet.addMergedRegion(new CellRangeAddress(lastendrow+firstrow, lastendrow+lastrow, firstcol, lastcol));
                CellStyle style=sheet.getRow(lastendrow+firstrow).getCell(0).getCellStyle();
                style.setAlignment(HorizontalAlignment.CENTER);
                style.setVerticalAlignment(VerticalAlignment.CENTER);
                sheet.getRow(lastendrow+firstrow).getCell(0).setCellStyle(style);
                if (!Util.isEoN(mergeArray.get(i2).getHeightinpoints())) {
                    sheet.getRow(lastendrow+firstrow).setHeightInPoints(HeightInPoints);//设置行高
                }
            }
        }
        return errorinfo;
    }

    /*固定--样式*/
    private String setTabFixStyle(HSSFWorkbook wb, HSSFSheet sheet, SysExcelTab tabObj, String tabid, int lastendrow) {
        String errorinfo="";
        if(!Util.isEoN(tabObj.getFixstyle())&&"是".equals(tabObj.getFixstyle())){
            List<SysExcelParam> styleObjList=this.sysExcelParamService.findByHql("select u from SysExcelParam u" +
                    " where u.sourceid=?0 and u.type='style' order by u.seq ",new Object[]{tabid});
            if(styleObjList.size()>0){

                for (int i11=0;i11<styleObjList.size();i11++) {
                    SysExcelParam styleParmObj=styleObjList.get(i11);
                    JSONObject styleObj=JSONObject.fromObject(styleParmObj.getValue());
                    int firstrow=Integer.parseInt(Util.isEoN(styleObj.getString("firstrow"))?"0":styleObj.getString("firstrow"));//当前模块的第几行
                    int lastrow=Integer.parseInt(Util.isEoN(styleObj.getString("lastrow"))?"0":styleObj.getString("lastrow"));//当前模块的第几行
                    int firstcol=Integer.parseInt(Util.isEoN(styleObj.getString("firstcol"))?"0":styleObj.getString("firstcol"));
                    int lastcol=Integer.parseInt(Util.isEoN(styleObj.getString("lastcol"))?"0":styleObj.getString("lastcol"));
                    String styleObjId= Util.isEoN(styleObj.getString("styleid"))?"0":styleObj.getString("styleid");
                    CellStyle styleFix=this.getStyle(wb,styleObjId);
                    for (int i3 = lastendrow+firstrow; i3 < lastendrow+lastrow+1; i3++) {
                        for (int i4 = firstcol; i4 < lastcol+1; i4++) {
                            sheet.getRow(i3).getCell(i4).setCellStyle(styleFix);
                        }
                    }
                }
            }
        }
        return errorinfo;
    }

    /*灵活--样式*/
    private String setTabChangeStyle(HSSFWorkbook wb, HSSFSheet sheet, int lastendrow, List<ReturnToExcelStyle> styleArray,Boolean ishaveHead) {
        String errorinfo="";
        if (styleArray.size()>0) {
            int firstrow=0;
            int lastrow=0;
            int firstcol=0;
            int lastcol=0;
            String styleObjId="";
            for (int i2 = 0; i2 < styleArray.size(); i2++) {
                firstrow= Util.isEoN(styleArray.get(i2).getFirstrow())?0:ishaveHead?styleArray.get(i2).getFirstrow()+1:styleArray.get(i2).getFirstrow();
                lastrow= Util.isEoN(styleArray.get(i2).getLastrow())?0:ishaveHead?styleArray.get(i2).getLastrow()+1:styleArray.get(i2).getLastrow();
                firstcol= Util.isEoN(styleArray.get(i2).getFirstcol())?0:styleArray.get(i2).getFirstcol();
                lastcol= Util.isEoN(styleArray.get(i2).getLastcol())?0:styleArray.get(i2).getLastcol();
                styleObjId=styleArray.get(i2).getStyleid();
                CellStyle styleObj=this.getStyle(wb,styleObjId);
                for (int i3 = lastendrow+firstrow; i3 < lastendrow+lastrow+1; i3++) {
                    for (int i4 = firstcol; i4 < lastcol+1; i4++) {
                        sheet.getRow(i3).getCell(i4).setCellStyle(styleObj);
                    }
                }
            }
        }
        return errorinfo;
    }

    private String setSheetColumn(String sheetid, HSSFSheet sheet,Map sheetsqlMap) {
        String errorinfo="";
    /*根据表头自动列宽*/
        List<SysExcelParam> tabkeyheadColumnwidthList=this.sysExcelParamService.findByHql("select u from SysExcelParam u," +
                "SysExcelTab t  where t.sheetid=?0 and u.type='tabKeyHead' and t.id=u.sourceid and t.iscolumnwidth='是' order by u.seq ",new Object[]{sheetid});
                    /*List<Map> tabkeyheadColumnwidthList=this.dbHelper.getRows("select t.* from sys_excel_param t inner join sys_excel_tab tt on tt.id=t.sourceid " +
                            "where tt.sheetid=? and t.type='tabkeyhead' order by t.seq ",new Object[]{sheetid});*/
        if (tabkeyheadColumnwidthList.size()>0) {
            for (int i1 = 0; i1 < tabkeyheadColumnwidthList.size(); i1++) {
                SysExcelParam tabkeyheadColumnwidth=tabkeyheadColumnwidthList.get(i1);
                int length= Util.isEoN(tabkeyheadColumnwidth.getMemo())?
                        tabkeyheadColumnwidth.getValue().getBytes().length*2:(int)(Double.parseDouble(tabkeyheadColumnwidth.getMemo())*8);
                //int length = tabkeyheadColumnwidthList.get(i1).get("value").toString().getBytes().length;
                sheet.setColumnWidth(i1,length*256);
            }
        } else {
            JSONObject fieldObj=(JSONObject) sheetsqlMap.get("field");
            Iterator<String> sIterator = fieldObj.keys();
            int aa=0;
            while(sIterator.hasNext()){
                // 获得key
                String key = sIterator.next();
                int length=fieldObj.get(key).toString().length()*3;
                System.out.print("长度："+length);
                sheet.setColumnWidth(aa,length*256);
                aa++;
            }
        }
        return errorinfo;
    }

    /**
     *@Description 大标题样式
     *@Param  [wb]
     *@Return org.apache.poi.ss.usermodel.CellStyle
     *@Author Mr.Guo
     *@Date 2019/9/18
     *@Time 15:04
     */
    public CellStyle getBigTitleStyle(Workbook wb, String hxType){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)16);
        font.setBold(true);					//字体加粗
        style.setFont(font);
        if("center".equals(hxType)){
            style.setAlignment(HorizontalAlignment.CENTER);					//横向居中
        }else if("left".equals(hxType)){
            style.setAlignment(HorizontalAlignment.LEFT);					//横向居左
        }
        style.setVerticalAlignment(VerticalAlignment.CENTER);			//纵向居中
        return style;
    }

    /**
     *@Description 样式获取
     *@Param  [wb]
     *@Return org.apache.poi.ss.usermodel.CellStyle
     *@Date 2020/9/25
     *@Time 15:04
     */
    public CellStyle getStyle(Workbook wb, String sourceid){
        SysExcelStyle cellstyledata=sysExcelStyleService.findById(sourceid);
        CellStyle style = wb.createCellStyle();
        String fontid=cellstyledata.getFontid();
        //Util.ApplyObject(style,JSONObject.fromObject(cellstyledata));
        style.setAlignment(sysExcelStyleService.getAlignmentForRead(cellstyledata.getAlignment()));//横向居中
        style.setVerticalAlignment(sysExcelStyleService.getVerticalalignmentForRead(cellstyledata.getVerticalalignment()));//纵向居中
        style.setWrapText(Util.isEoN(cellstyledata.getWraptext())?true:Boolean.parseBoolean(cellstyledata.getWraptext()));
        style.setFillForegroundColor(sysExcelStyleService.getFillforegroundcolorForRead(cellstyledata.getFillforegroundcolor()));//设置图案颜色
        style.setFillBackgroundColor(sysExcelStyleService.getFillforegroundcolorForRead(cellstyledata.getFillbackgroundcolor()));//设置图案背景色
        //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //style.setFillPattern(HSSFCellStyle.SQUARES);//设置图案样式
        style.setBorderTop(sysExcelStyleService.getBorderForRead(cellstyledata.getBordertop()));//上细线
        style.setBorderBottom(sysExcelStyleService.getBorderForRead(cellstyledata.getBorderbottom()));
        style.setBorderLeft(sysExcelStyleService.getBorderForRead(cellstyledata.getBorderleft()));
        style.setBorderRight(sysExcelStyleService.getBorderForRead(cellstyledata.getBorderright()));//右细线
        SysExcelFont fontdata=sysExcelFontService.findById(fontid);
        /*字体开始*/
        Font font = wb.createFont();
        //Util.ApplyObject(font,JSONObject.fromObject(fontdatd));
        font.setFontName(Util.isEoN(fontdata.getFontname())?"宋体":fontdata.getFontname());//宋体,仿宋_GB2312,黑体
        font.setFontHeightInPoints(Util.isEoN(fontdata.getFontheightinpoints())?(short) 16:Short.parseShort(fontdata.getFontheightinpoints()));//设置字号
        font.setBold(Util.isEoN(fontdata.getBoldweight())? true:"700".equals(fontdata.getBoldweight())?true:false);//字体加粗,700粗体，400不加粗
        font.setColor(sysExcelStyleService.getFillforegroundcolorForRead(fontdata.getColor()));
        font.setItalic(Util.isEoN(fontdata.getItalic())?false:Boolean.parseBoolean(fontdata.getItalic()));//是否斜体
        font.setUnderline(sysExcelFontService.getFontUnderline(fontdata.getUnderline()));//下划线
        font.setTypeOffset(sysExcelFontService.getTypeOffset(fontdata.getTypeoffset()));//设置上标下标
        font.setStrikeout(Util.isEoN(fontdata.getStrikeout())?false:Boolean.parseBoolean(fontdata.getStrikeout()));//删除线
        //font.setUnderline((short)1);
        style.setFont(font);
        /*if("center".equals(hxType)){
            style.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
        }else if("left".equals(hxType)){
            style.setAlignment(CellStyle.ALIGN_LEFT);					//横向居左
        }*/
        style.setVerticalAlignment(VerticalAlignment.CENTER);			//纵向居中CellStyle.VERTICAL_CENTER  1
        return style;
    }

    public void downloadByRecord(HttpServletResponse response, String recordid) throws IOException {
        SysDownRecord downrecord=this.sysDownRecordService.findById(recordid);
        createPdfOrWord.downloadPdfByLocalexcel(downrecord.getNote(), downrecord.getUrl(), response);
    }

    /*下载本地已生成文件*/
    public ReturnToJs findRolePower(ReturnToJs returnToJs, HttpServletRequest request, BladeUser sysuser, HttpServletResponse response) throws Exception{
        String recordid=request.getParameter("id");
        PrintWriter writer = response.getWriter();
        try {
            if(!Util.isEoN(recordid)){
                    SysDownRecord downrecord=this.sysDownRecordService.findById(recordid);
                    String pathvalue=downrecord.getUrl();
                    SysExcel exceldata=this.findById(downrecord.getSourceid());
                    String excelName=downrecord.getOutname();
                    String roles=exceldata.getRoleid();
                    List<SysIdentity> sysIdentitys= sysIdentityService.findByUserId(sysuser.getUserId());  //sysuser.getUser().getSysIdentitys();
                    Boolean isrole="all".equals(roles)?false:true;//如果是all就是所有用户
                    for (SysIdentity sysIdentity:sysIdentitys){
                        if(roles.contains(sysIdentity.getSysRole().getId())){
                            isrole=true;
                            break;
                        }
                    }
                    if (isrole) {
                        createPdfOrWord.downloadPdfByLocalexcel(excelName,
                                pathvalue, response);
                        returnToJs.setData(downrecord.getUrl());
                    } else {
                        returnToJs.setSuccess(false);
                        returnToJs.setData("该用户没有权限");
                        writer.write(JSONObject.fromObject(returnToJs).toString());
                    }
            } else {
                    returnToJs.setSuccess(false);
                    returnToJs.setData("该用户没有权限");
                    writer.write(JSONObject.fromObject(returnToJs).toString());
            }
        } catch (IOException e) {
            returnToJs.setSuccess(false);
            returnToJs.setData("后台程序有误，请联系系统维护员："+ Util.linkNumber);
            log.error("/SysExcel/findRolePower.do 方法报错,错误信息："+e);
            e.printStackTrace();
        }
        return returnToJs;
    }

    /**
     * 导出列表的配置数据
     */
    public HSSFWorkbook outputDataById(String ids, String type){
        String[] idArr = ids.split(",");
        String id_insql = "";
        for (int i = 0; i < idArr.length; i++) {
            if(i==0){
                id_insql = "('";
            }
            id_insql = id_insql +idArr[i]+ "','";
        }
        if(!Util.isEoN(id_insql)){
            id_insql = id_insql.substring(0,id_insql.length()-2)+")";
        }
        // 创建文档对象(其他对象都是通过文档对象创建)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建样式对象（HSSFCellStyle ）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 导出excel的名称
        if("font".equals(type)){
            //列名
            List<Map> excelFontList = this.dbHelper.getRows("select t.* from sys_excel_font t where t.id in "+id_insql, new Object[]{});
            String[] excelFontkey = Util.getKeys(excelFontList);
            //excelFontList = Util.changeBoolean(excelFontList,"havejump");//处理boolean类型
            wb = Util.getSheetAll(excelFontList, wb, cellStyle, excelFontkey, excelFontkey, "SysExcelFont");
        }else if("style".equals(type)){
            //按钮
            List<Map> excelStyleList = this.dbHelper.getRows("select t.* from sys_excel_style t where t.id in "+id_insql, new Object[]{});
            String[] excelStylekey = Util.getKeys(excelStyleList);
            wb = Util.getSheetAll(excelStyleList, wb, cellStyle, excelStylekey, excelStylekey, "SysExcelStyle");
        }else if("excel".equals(type)){
            //列表
            String[] SysExcelkey = new String[]{};
            String[] SysExcelSheetkey = new String[]{};
            String[] SysExcelTabkey = new String[]{};
            String[] SysExcelParamkey = new String[]{};

            List<Map> SysExcelList = dbHelper.getRows("select t.* from sys_excel t where t.id  in "+id_insql, new Object[]{});
            SysExcelkey = Util.getKeys(SysExcelList);

            List<Map> SysExcelSheetList = new ArrayList<>();
            List<Map> SysExcelTabList = new ArrayList<>();
            List<Map> SysExcelParamList = new ArrayList<>();
            if (SysExcelList != null) {
                SysExcelSheetList = dbHelper.getRows("select t.* from sys_excel_sheet t where t.excelid  in "+id_insql, new Object[]{});
                if (SysExcelSheetList.size() > 0) {
                    SysExcelSheetkey = Util.getKeys(SysExcelSheetList);
                    for (int i = 0; i < SysExcelSheetList.size(); i++) {
                        List<Map> tempSysExcelTabList = dbHelper.getRows("select t.* from sys_excel_tab t where t.sheetid = ?", new Object[]{SysExcelSheetList.get(i).get("id") + ""});
                        if (tempSysExcelTabList.size() > 0) {
                            SysExcelTabkey = Util.getKeys(tempSysExcelTabList);
                            for (int j = 0; j < tempSysExcelTabList.size(); j++) {
                                List<Map> tempSysExcelParamList = dbHelper.getRows("select t.* from sys_excel_param t where t.sourceid = ?", new Object[]{tempSysExcelTabList.get(j).get("id") + ""});
                                if (tempSysExcelParamList.size() > 0) {
                                    SysExcelParamkey = Util.getKeys(tempSysExcelParamList);
                                    SysExcelParamList.addAll(tempSysExcelParamList);
                                }
                            }
                            SysExcelTabList.addAll(tempSysExcelTabList);
                        }

                        /*sheet的参数*/
                        List<Map> tempSysSheetParamList2 = dbHelper.getRows("select t.* from sys_excel_param t where t.sourceid = ?", new Object[]{SysExcelSheetList.get(i).get("id") + ""});
                        if (tempSysSheetParamList2.size() > 0) {
                            SysExcelParamkey = Util.getKeys(tempSysSheetParamList2);
                            SysExcelParamList.addAll(tempSysSheetParamList2);
                        }
                    }
                }

                /*excel的参数*/
                List<Map> tempSysExcelParamList2 = dbHelper.getRows("select t.* from sys_excel_param t where t.sourceid in "+id_insql, new Object[]{});
                if (tempSysExcelParamList2.size() > 0) {
                    SysExcelParamList.addAll(tempSysExcelParamList2);
                }
                wb = Util.getSheetAll(SysExcelList, wb, cellStyle, SysExcelkey, SysExcelkey, "SysExcel");
                wb = Util.getSheetAll(SysExcelSheetList, wb, cellStyle, SysExcelSheetkey, SysExcelSheetkey, "SysExcelSheet");
                wb = Util.getSheetAll(SysExcelTabList, wb, cellStyle, SysExcelTabkey, SysExcelTabkey, "SysExcelTab");
                wb = Util.getSheetAll(SysExcelParamList, wb, cellStyle, SysExcelParamkey, SysExcelParamkey, "SysExcelParam");
            }
        }
        return wb;
    }


    // 指定表名导入数据
    public Boolean importData(File excelFile){
        try{
            jxl.Workbook rwb = jxl.Workbook.getWorkbook(excelFile);
            // 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...
            for (int i = 0; i < rwb.getSheets().length; i++) {
                Sheet sheet = rwb.getSheets()[i];
                int rsColumns = sheet.getColumns();// 列数
                int rsRows = sheet.getRows();// 行数
                String tablename = sheet.getName();
                this.cellMerge(sheet, rsColumns, rsRows,   tablename);//处理大字段类型
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void cellMerge(Sheet sheet, int rsColumns, int rsRows , String classname) {
        try {
            Object service = Util.getBeanObject(classname + "Service");
            Class<?> clazz =  this.getEntityClassByName(classname);

            Object object = clazz.newInstance();
            Method merge = service.getClass().getMethod("merge", Object.class);
            Cell[] cellkey = sheet.getRow(0);
            for (int j = 1; j < rsRows; j++) {
                JSONObject json = new JSONObject();
                Cell[] cellvalue = sheet.getRow(j);
                for (int k = 0; k < rsColumns; k++) {
                    json.put(cellkey[k].getContents(), cellvalue[k].getContents());
                }
                //System.out.println(json);
                Util.ApplyObject(object, json);
                if("SysExcelParam".equals(classname)){
                    if(!Util.isEoN(json.get("value"))&&!"null".equals(json.get("value")+"")&&!"undefined".equals(json.get("value")+"")){
                        if (json.get("value").toString().indexOf("{")>-1&&json.get("value").toString().indexOf("}")>-1) {
                            Method method = clazz.getMethod("setValue", String.class);
                            method.invoke(object, json.get("value").toString());
                        }
                    }
                    if(!Util.isEoN(json.get("othermemo"))&&!"null".equals(json.get("othermemo")+"")&&!"undefined".equals(json.get("othermemo")+"")){
                        if (json.get("othermemo").toString().indexOf("{")>-1&&json.get("othermemo").toString().indexOf("}")>-1) {
                            Method method = clazz.getMethod("setOthermemo", String.class);
                            method.invoke(object, json.get("othermemo").toString());
                        }
                    }
                }
                merge.invoke(service, object);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /*判断权限*/
    public Boolean getaBoolean(HttpServletRequest request) {
        BladeUser sysuser =  AuthUtil.getUser();
        List<SysIdentity> sysIdentitys=sysIdentityService.findByUserId(sysuser.getUserId());
        String roles="C9601B5D-0F0D-498C-9982-A1864294B02B;1231234F078934FSDFEFSD;1123598816738675201";
        Boolean isrole=false;
        for (SysIdentity sysIdentity:sysIdentitys){
            if(roles.contains(sysIdentity.getSysRole().getId())){
                isrole=true;
                break;
            }
        }
        return isrole;
    }

    //查询信息
    public ReturnToExcel testfind() {
        String sheetid = "";
        String sql = "select t.prijectbasename,t.jllb,t.recommendnumber,(case when t.jllb like '%科技大奖%' then '238DBF8C-6A98-4B09-86E0-4EDE956B78CF' else '' end) as styleid" +
                ",'' as stylecolumn" +
                " from pms_award t where t.yearflag ='2019'and t.endawards is not null order by t.jllb ";
        List<Map> listMap = dbHelper.getRows(sql, new Object[]{});
        /*定义回参*/
        ReturnToExcel retrunobj=new ReturnToExcel();
        /*定义合并单元格，设置相应的区间行列*/
        ReturnToExcelMerge aa=new ReturnToExcelMerge();
        aa.setFirstrow(1);
        aa.setLastrow(14);
        aa.setFirstcol(0);
        aa.setLastcol(0);
        List<ReturnToExcelMerge> aas=new ArrayList<>();
        aas.add(aa);
        /*定义第二个合并单元格*/
        ReturnToExcelMerge bb=new ReturnToExcelMerge();
        bb.setFirstrow(15);
        bb.setLastrow(29);
        bb.setFirstcol(0);
        bb.setLastcol(0);
        aas.add(bb);
        /*定义合并单元格数组及数据源*/
        retrunobj.setMergeArray(aas);
        /*定义样式*/

        ReturnToExcelStyle yy=new ReturnToExcelStyle();
        yy.setFirstrow(11);
        yy.setLastrow(12);
        yy.setFirstcol(1);
        yy.setLastcol(2);
        yy.setStyleid("238DBF8C-6A98-4B09-86E0-4EDE956B78CF");
        List<ReturnToExcelStyle> yys=new ArrayList<>();
        yys.add(yy);
        yy=new ReturnToExcelStyle();
        yy.setFirstrow(21);
        yy.setLastrow(23);
        yy.setFirstcol(2);
        yy.setLastcol(2);
        yy.setStyleid("CF0C4FA1-3FBA-4884-A470-A017FFB1F2C0");
        yys.add(yy);
        retrunobj.setStyleArray(yys);
        retrunobj.setData(JSONArray.fromObject(listMap));
        return retrunobj;
    }
}
