package cn.topcheer.pms2.biz.utils;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.annotation.MainTable;
import cn.topcheer.pms2.api.enumUtil.SysModuleEnum;
import cn.topcheer.pms2.api.plan.entity.PmsAttachment;
import cn.topcheer.pms2.biz.plan.service.impl.PmsAffixService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsAttachmentService;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseService;
import cn.topcheer.pms2.biz.project.service.impl.enumUtil.TableMappingEnum;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通用数据查询保存方法
 *
 * @author GaoGongxin
 * @date 2020/9/16 16:29
 */
@Component
public class GenericFetchAndSave {

    //    public Logger log = Logger.getLogger(getClass());
//
//    @Autowired
//    private PmsTxtSave pmsTxtSave;

    @Autowired
    private PmsAttachmentService pmsAttachmentService;

    @Autowired
    private PmsAffixService pmsAffixService;

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private SysOperationrecordService sysOperationrecordService;

//    @Autowired
//    private PmsEnterpriseService pmsEnterpriseService;
//
//    @Autowired
//    private PmsPlanprojectbatchService pmsPlanprojectbatchService;
//
    @Autowired
    private GenericSpecialDownloadData genericSpecialDownloadData;

//    @Autowired
//    private PmsEditDataService pmsEditDataService;
//
//    @Autowired
//    private PmsReturnDataService pmsReturnDataService;
//
//    @Autowired
//    private FlowRelationDetailService flowRelationDetailService;
//
//    @Autowired
//    FlowRecordService flowRecordService;
//
//    @Autowired
//    private PmsAllattachmentsService pmsAllattachmentsService;

    @Autowired
    private SysUserServiceImpl sysUserService;
//
//    /**
//     * 通过数据类型将前台传来的数据转换转换为JSONArray数组类型
//     * @return
//     */
//    private static JSONArray getDataFromJson(JSONObject tableObject, String datatype) {
//
//        JSONArray data = new JSONArray();
//        if("normal".equals(datatype)){
//            JSONObject datanewobject=tableObject.getJSONObject("data");
//            data.add(datanewobject);
//        }else{
//            data=tableObject.getJSONArray("data");
//        }
//        return data;
//    }
//
    /**
     * 业务获取方法中--获取循环类附件
     * @param tableObject  对象判断是否有附件
     * @param list  业务数据源
     * @return 返回附件集合
     */
    private JSONArray getRepeatAffixs(JSONObject tableObject,List<Map> list){
        JSONArray repeatAffixs = new JSONArray();
        //判断是否有循环类附件
        if(tableObject.containsKey("ishaveaffix") && tableObject.getBoolean("ishaveaffix")){
            //说明当前对象里有存在循环类附件
            if(!Util.isEoN(list) && list.size() > 0){
                List<String> targetIds =  list.stream().map(m-> {
                    try {
                        if(Util.isEoN(m.get("id"))) {
                            return "";
                        }
                        return (String) m.get("id");
                    } catch (Exception e) {
                        return "";
                    }
                }).collect(Collectors.toList());
                repeatAffixs.addAll(JSONArray.fromObject(pmsAffixService.getPmsAttachmentByIds(targetIds)));
            }
        }
        return repeatAffixs;
    }

//    /**
//     * 保存数据
//     * @param dataObject 前台传来的完整对象
//     * @param mainid 主表id
//     * @param batchid   批次id
//     * @param mold  类型
//     * @param flowtype  流程类型
//     * @param request   请求
//     * @return
//     */
//    public JSONObject savaData(JSONObject dataObject, String mainid, String batchid, String mold, String flowtype, HttpServletRequest request) {
//
//        JSONObject result = new JSONObject();
//        //用于存储保存方法执行的结果
//        JSONArray resultData = new JSONArray();
//        //获取key迭代器
//        Iterator<String> keyIterator0 = dataObject.keys();
//        Boolean mainTableSaveFlag = false;
//        while(keyIterator0.hasNext()){
//            //获得key
//            String key = keyIterator0.next();
//            //根据key获得当前对象的所有信息
//            JSONObject tableObject = dataObject.getJSONObject(key);
//            TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.get("database").toString());
//            if(Util.isEoN(tableMappingEnum)) {
//                JSONObject saveResult = new JSONObject();
//                saveResult = creatRertunData(saveResult, false, tableObject.get("database").toString() + "后台枚举类未配置");
//                resultData.add(saveResult);
//            }
//            //前台表名与后台数据表映射信息 枚举对象
//            MainTable mainTable = (MainTable) TableMappingEnum.getEnumByTablename(tableObject.get("database").toString()).getClassname().getAnnotation(MainTable.class);
//            ClassInfo classInfo = (ClassInfo) TableMappingEnum.getEnumByTablename(tableObject.get("database").toString()).getClassname().getAnnotation(ClassInfo.class);
//            if(!Util.isEoN(mainTable)) {
//                String modulename = "未定义类型";
//                if(!Util.isEoN(classInfo)) {
//                    modulename = classInfo.module().getName();
//                }
//                String savetype = "对应表：" + tableObject.get("database") + "，类别：" + tableObject.get("type");
//                sysOperationrecordService.commonSaveOperation(request,mainid,"申报保存","批次id:"+batchid+"项目id:"+mainid);
//                //把当前数据添加到txt文件中
//                pmsTxtSave.saveTxt(mainid, tableObject, modulename + "存前数据", savetype);
//                //通过反射保存单个对象数据
//                JSONObject saveResult = this.saveDataUseReflect(tableObject, mainid, batchid, mold, flowtype);
//                if("true".equals(saveResult.getJSONObject("saveResult").get("success").toString())) {
//                    mainTableSaveFlag = true;
//                }
//                //将保存的结果添加到数组中
//                resultData.add(saveResult);
//            }
//        }
//        if(mainTableSaveFlag == false) {
//            if(resultData.size() == 0) {
//                JSONObject tempDataResult = new JSONObject();
//                JSONObject saveResult = new JSONObject();
//                saveResult.put("success", false);
//                tempDataResult.put("success", false);
//                tempDataResult.put("errResult", "未添加主表MainTable标记主表");
//                tempDataResult.put("errMsg", "未添加主表MainTable标记主表");
//                tempDataResult.put("saveResult", saveResult);
//                resultData.add(tempDataResult);
//            }
//            result.put("resultData", resultData);
//            return result;
//        }
//        //获取key迭代器
//        Iterator<String> keyIterator = dataObject.keys();
//        while(keyIterator.hasNext()){
//            //获得key
//            String key = keyIterator.next();
//            //根据key获得当前对象的所有信息
//            JSONObject tableObject = dataObject.getJSONObject(key);
//            MainTable mainTable = (MainTable) TableMappingEnum.getEnumByTablename(tableObject.get("database").toString()).getClassname().getAnnotation(MainTable.class);
//            if(!Util.isEoN(mainTable)) {
//                continue;
//            }
//            ClassInfo classInfo = (ClassInfo) TableMappingEnum.getEnumByTablename(tableObject.get("database").toString()).getClassname().getAnnotation(ClassInfo.class);
//            String modulename = "未定义类型";
//            if(!Util.isEoN(classInfo)) {
//                modulename = classInfo.module().getName();
//            }
//            String savetype = "对应表：" + tableObject.get("database") + "，类别：" + tableObject.get("type");
//            //把当前数据添加到txt文件中
//            pmsTxtSave.saveTxt(mainid, tableObject, modulename + "存前数据", savetype);
//            //通过反射保存单个对象数据
//            JSONObject saveResult = this.saveDataUseReflect(tableObject, mainid, batchid, mold, flowtype);
//            //将保存的结果添加到数组中
//            resultData.add(saveResult);
//        }
//        result.put("resultData", resultData);
//        return result;
//    }
//
//    /**
//     * 通过反射保存单个对象数据
//     * @param tableObject 当前需保存的对象
//     * @param mainid 主表id
//     * @return
//     */
//    private JSONObject saveDataUseReflect(JSONObject tableObject, String mainid, String batchid, String mold, String flowtype) {
//        JSONObject result = new JSONObject();
//        //文件记录错误信息
//        String errResult = "";
//        //给用户返回的错误信息
//        String errMsg = "";
//        //前台表名与后台数据表映射信息 枚举对象
//        TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.get("database").toString());
//        if(Util.isEoN(tableMappingEnum)) {
//            result = creatRertunData(result, false, tableObject.get("database").toString() + "后台枚举类未配置");
//            return result;
//        }
//        //存储字符串型的service类名，例如：Pms_ProjectbaseService
//        String tempServiceNameStr = "";
//        //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
//        String servicePathNameStr = "";
//        //判断枚举信息中是否包含service名称
//        if(!Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
//            tempServiceNameStr = tableMappingEnum.getServiceclassname().getSimpleName();
//            servicePathNameStr = tableMappingEnum.getServiceclassname().getName();
//        } else {
//            tempServiceNameStr = (tableMappingEnum.getClassname().getSimpleName() + "Service");
//            servicePathNameStr = PmsProjectbaseService.class.getPackage().toString().substring(8) + "." + tempServiceNameStr;
//        }
//        Class clazz = null;
//        try {
//            //通过反射创建cn.topcheer.pms.service.PmsProjectpartyService
//            clazz = Class.forName(servicePathNameStr);
//        } catch (Exception e) {
//            result.put("success", false);
//            //处理反射创建对象失败的情况，通过continue继续执行循环
//            errResult = tempServiceNameStr + "创建失败\r\n\r\n";
//            //方便用户理解，故将错误改为“项目人员保存失败”
//            errMsg = ((ClassInfo)(tableMappingEnum.getClassname().getAnnotation(ClassInfo.class))).name() + "保存失败\n";
//            //添加虚拟执行错误信息
//            JSONObject saveResult = new JSONObject();
//            saveResult.put("success", false);
//            result.put("success", false);
//            result.put("saveResult", saveResult);
//            log.error(e.toString());
//        }
//        //判断是否通过反射已经成功创建对象
//        if(!Util.isEoN(clazz)) {
//            //service Bean对象的对象名称
//            String serviceName = "";
//            //获取中定义的spring的service注解的名称
//            serviceName = ((Service)(clazz.getAnnotation(Service.class))).value();
//            //判断是否已为service名称是否存在
//            if(Util.isEoN(serviceName)) {
//                //如不存在使用默认的类名首字母小写作为service名称
//                serviceName = (new StringBuilder()).append(Character.toLowerCase(tempServiceNameStr.charAt(0))).append(tempServiceNameStr.substring(1)).toString();
//            }
//            //保存类型
//            String type = tableObject.getString("type");
//            //数据格式类型（normal普通对象Object等）
//            String datatype = tableObject.getString("datatype");
//            //通过数据类型将前台传来的数据转换转换为JSONArray数组类型
//            JSONArray data = GenericFetchAndSave.getDataFromJson(tableObject, datatype);
//            if(tableMappingEnum.getClassname().isAnnotationPresent(MainTable.class)) {
//                SecurityUser securityUser = SecurityUserLocal.getSecurityUser();
//                JSONObject judgeUserHasApplyAuthorityForBatchFlag = new JSONObject();
//                JSONObject judgeProjectBelongToUserFlag = new JSONObject();
//                //判断当前项目的状态是否可以进行编辑（例如：状态为 空、XXX:用户填报等）
//                JSONObject judgeMinicurrentStateSatifyFlag = new JSONObject();
//                try {
//                    //动态执行Service方法
//                    judgeMinicurrentStateSatifyFlag = JSONObject.fromObject(Util.springInvokeMethod(serviceName, "judgeMinicurrentStateSatify", new Object[]{mainid, batchid, mold, flowtype}));
//                } catch (Exception e) {
//                    judgeMinicurrentStateSatifyFlag.put("success", false);
//                    judgeMinicurrentStateSatifyFlag.put("errResult", "后台程序有误,请联系系统维护员："+Util.linkNumber);
//                    judgeMinicurrentStateSatifyFlag.put("errMsg", "后台程序有误,请联系系统维护员："+Util.linkNumber);
//                }
//                //判断用户是否有该批次的保存权限
//                if(!(Boolean) judgeMinicurrentStateSatifyFlag.get("success")) {
//                    result = creatRertunData(result, false, judgeMinicurrentStateSatifyFlag.get("errMsg").toString());
//                    return result;
//                }
//                if(Util.isEoN(securityUser)) {
//                    //针对单位注册及用户注册
//                    judgeUserHasApplyAuthorityForBatchFlag = judgeUserHasApplyAuthorityForBatch(batchid, "", tableObject.get("database").toString());
//                    judgeProjectBelongToUserFlag = judgeProjectBelongToUser(batchid, "", data.getJSONObject(0).get("declarantid") + "");
//                } else {
//                    //通用业务
//                    judgeUserHasApplyAuthorityForBatchFlag = judgeUserHasApplyAuthorityForBatch(batchid, securityUser.getUser().getId(), tableObject.get("database").toString());
//                    judgeProjectBelongToUserFlag = judgeProjectBelongToUser(batchid, securityUser.getUser().getId(), data.getJSONObject(0).get("declarantid") + "");
//                }
//                //判断用户是否有该批次的保存权限
//                if(!(Boolean) judgeUserHasApplyAuthorityForBatchFlag.get("success")) {
//                    result = creatRertunData(result, false, judgeUserHasApplyAuthorityForBatchFlag.get("errMsg").toString());
//                    return result;
//                }
//                //判断该项目是否属于当前用户
//                if(!(Boolean) judgeProjectBelongToUserFlag.get("success")) {
//                    result = creatRertunData(result, false, judgeProjectBelongToUserFlag.get("errMsg").toString());
//                    return result;
//                }
//            }
//            try {
//                //动态执行Service方法
//                JSONObject saveResult = JSONObject.fromObject(Util.springInvokeMethod(serviceName, "genericSaveFun", new Object[]{mainid, type, data, batchid, flowtype, mold, tableObject}));
//                //添加执行结果
//                result.put("saveResult", saveResult);
//                result.put("success", true);
//            } catch (Exception e) {
//                result.put("success", false);
//                errResult = serviceName + "//genericSaveFun"  + "对象保存失败\r\n\r\n";
//                //方便用户理解，故将错误改为“项目人员保存失败”
//                errMsg = ((ClassInfo)(tableMappingEnum.getClassname().getAnnotation(ClassInfo.class))).name() + "保存失败\n";
//                log.error(e.toString());
//            }
//        }
//        result.put("errResult", errResult);
//        result.put("errMsg", errMsg);
//        return result;
//    }
//
    /**
     * 查看数据
     * @param dataObject    前台传来的完整对象
     * @param mainid    主表id
     * @param batchid   批次id
     * @param request   request请求
     * @param initParamArr  特殊初始化参数
     * @return
     */
    public JSONObject initData(JSONObject dataObject, String mainid, String batchid, HttpServletRequest request, String[] initParamArr) {

        Boolean saveFlag = false;
        Iterator<String> keysIterator0 = dataObject.keys();
        while(keysIterator0.hasNext()) {
            // 获得key
            String key = keysIterator0.next();
            // 根据key获得当前对象的所有信息
            JSONObject tableObject = dataObject.getJSONObject(key);
            //前台表名与后台数据表映射信息 枚举对象
            MainTable mainTable = (MainTable) TableMappingEnum.getEnumByTablename(tableObject.get("database").toString()).getClassname().getAnnotation(MainTable.class);
            if(!Util.isEoN(mainTable)) {
                Table table = (Table) TableMappingEnum.getEnumByTablename(tableObject.get("database").toString()).getClassname().getAnnotation(Table.class);
                String tablename = table.name();
                //判断主表是否保存过
                saveFlag = isSave(mainid, tablename);
                JSONObject fetchResult = this.initDataUseReflect(tableObject, mainid, batchid, saveFlag, initParamArr);
                tableObject.put("data", JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")).getJSONObject(0) : new JSONObject());
                dataObject.put(key, tableObject);
            }
        }
        Iterator<String> keysIterator = dataObject.keys();
        //附件信息集合
        JSONArray tpAllAffixs = new JSONArray();
        while(keysIterator.hasNext()) {
            // 获得key
            String key = keysIterator.next();
            // 根据key获得当前对象的所有信息
            JSONObject tableObject = dataObject.getJSONObject(key);
            MainTable mainTable = (MainTable) TableMappingEnum.getEnumByTablename(tableObject.get("database").toString()).getClassname().getAnnotation(MainTable.class);
            if(!Util.isEoN(mainTable)) {
                continue;
            }
            JSONObject fetchResult = this.initDataUseReflect(tableObject, mainid, batchid, saveFlag, initParamArr);
            // 添加循环内附件
            tpAllAffixs.addAll(JSONArray.fromObject(fetchResult.get("fetchResultAffix")));
            //数据格式类型（normal普通对象Object等）
            String datatype = tableObject.getString("datatype");
            if("normal".equals(datatype)) {
                tableObject.put("data", JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")).getJSONObject(0) : new JSONObject());
            } else {
                tableObject.put("data", JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")) : new JSONArray());
            }
            dataObject.put(key, tableObject);
        }
        //验收id作为sourceid的附件
        List<PmsAttachment> sourceList = pmsAttachmentService.findPmsAttachmentBySourceid(mainid);
        tpAllAffixs.addAll(JSONArray.fromObject(sourceList));
        JSONObject tpAllAffixsJo = new JSONObject();
        tpAllAffixsJo.put("data",tpAllAffixs);
        dataObject.put("TPAllAffixs", tpAllAffixsJo);
        return dataObject;
    }

    /**
     * 通过反射查看单个对象数据
     * @param tableObject   前台传来的完整对象
     * @param mainid  主表id
     * @param batchid   批次id
     * @param saveFlag  是否保存过
     * @param initParamArr  特殊初始化参数
     * @return
     */
    private JSONObject initDataUseReflect(JSONObject tableObject, String mainid, String batchid, Boolean saveFlag, String[] initParamArr) {
        JSONObject result = new JSONObject();
        //文件记录错误信息
        String errResult = "";
        //给用户返回的错误信息
        String errMsg = "";
        //前台表名与后台数据表映射信息 枚举对象
        TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.get("database").toString());
        //存储字符串型的service类名，例如：Pms_ProjectbaseService
        String tempServiceNameStr = "";
        //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
        String servicePathNameStr = "";
        //判断枚举信息中是否包含service名称
        if(!Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
            tempServiceNameStr = tableMappingEnum.getServiceclassname().getSimpleName();
            servicePathNameStr = tableMappingEnum.getServiceclassname().getName();
        } else {
            tempServiceNameStr = (tableMappingEnum.getClassname().getSimpleName() + "Service");
            servicePathNameStr = PmsProjectbaseService.class.getPackage().toString().substring(8) + "." + tempServiceNameStr;
        }
        Class clazz = null;
        try {
            //通过反射创建cn.topcheer.pms.service.PmsProjectpartyService
            clazz = Class.forName(servicePathNameStr);
        } catch (Exception e) {
            result.put("success", false);
            //处理反射创建对象失败的情况，通过continue继续执行循环
            errResult = tempServiceNameStr + "创建失败\r\n\r\n";
            //方便用户理解，故将错误改为“项目人员保存失败”
            errMsg = ((ClassInfo)(tableMappingEnum.getClassname().getAnnotation(ClassInfo.class))).name() + "查询失败\n";
            //添加虚拟执行错误信息
            List<Map> fetchResult = new ArrayList<>();
            result.put("success", false);
            result.put("fetchResult", fetchResult);
            result.put("fetchResultAffix", new JSONArray());
//            log.error(e.toString());
        }
        //判断是否通过反射已经成功创建对象
        if(!Util.isEoN(clazz)) {
            //service Bean对象的对象名称
            String serviceName = "";
            //获取中定义的spring的service注解的名称
            serviceName = ((Service)(clazz.getAnnotation(Service.class))).value();
            //判断是否已为service名称是否存在
            if(Util.isEoN(serviceName)) {
                //如不存在使用默认的类名首字母小写作为service名称
                serviceName = (new StringBuilder()).append(Character.toLowerCase(tempServiceNameStr.charAt(0))).append(tempServiceNameStr.substring(1)).toString();
            }
            //保存类型
            String type = tableObject.getString("type");
            //数据格式类型（normal普通对象Object等）
            String datatype = tableObject.getString("datatype");
            try {
                //动态执行Service方法
                List<Map> fetchResult = (List<Map>) Util.springInvokeMethod(serviceName, "genericFetchFun", new Object[]{mainid, type, saveFlag, batchid, tableObject, initParamArr});
                JSONArray repeatAffixs = new JSONArray();
                repeatAffixs = getRepeatAffixs(tableObject,fetchResult);
                //添加执行结果
                result.put("fetchResult", fetchResult);
                result.put("fetchResultAffix", repeatAffixs);
                result.put("success", true);
            } catch (Exception e) {
                result.put("success", false);
                result.put("fetchResult", new ArrayList<>());
                result.put("fetchResultAffix", new JSONArray());
                errResult = serviceName + "//genericFetchFun"  + "对象查询失败\r\n\r\n";
                //方便用户理解，故将错误改为“项目人员查询失败”
                errMsg = ((ClassInfo)(tableMappingEnum.getClassname().getAnnotation(ClassInfo.class))).name() + "查询失败\n";
//                log.error("initDataUseReflectError", e);
            }
        }
        result.put("errResult", errResult);
        result.put("errMsg", errMsg);
        return result;
    }

//    /**
//     * 保存数据（数据仓库）
//     * @param dataObject
//     * @param carrierid
//     * @param carriertype
//     * @param request
//     * @return
//     */
//    public JSONObject savaDatawarehouseData(JSONObject dataObject, String carrierid, String carriertype, HttpServletRequest request) {
//
//        JSONObject result = new JSONObject();
//        //用于存储保存方法执行的结果
//        JSONArray resultData = new JSONArray();
//        //获取key迭代器
//        Iterator<String> keyIterator = dataObject.keys();
//        while(keyIterator.hasNext()){
//            //获得key
//            String key = keyIterator.next();
//            //根据key获得当前对象的所有信息
//            JSONObject tableObject = dataObject.getJSONObject(key);
//            //通过反射保存单个对象数据
//            JSONObject saveResult = this.saveDatawarehouseDataUseReflect(tableObject, carrierid, carriertype);
//            //将保存的结果添加到数组中
//            resultData.add(saveResult);
//        }
//        result.put("resultData", resultData);
//        return result;
//    }
//
//    /**
//     * 通过反射保存单个对象数据（数据仓库）
//     * @param tableObject
//     * @param carrierid
//     * @param carriertype
//     * @return
//     */
//    private JSONObject saveDatawarehouseDataUseReflect(JSONObject tableObject, String carrierid, String carriertype) {
//        JSONObject result = new JSONObject();
//        //文件记录错误信息
//        String errResult = "";
//        //给用户返回的错误信息
//        String errMsg = "";
//        //前台表名与后台数据表映射信息 枚举对象
//        //存储字符串型的service类名，例如：Pms_ProjectbaseService
//        String tempServiceNameStr = tableObject.get("database").toString() + "Service";
//        //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
//        String servicePathNameStr = "cn.topcheer.datawarehouse.service." + tempServiceNameStr;
//        Class clazz = null;
//        try {
//            //通过反射创建cn.topcheer.pms.service.PmsProjectpartyService
//            clazz = Class.forName(servicePathNameStr);
//        } catch (Exception e) {
//            result.put("success", false);
//            //处理反射创建对象失败的情况，通过continue继续执行循环
//            errResult = tempServiceNameStr + "创建失败\r\n\r\n";
//            //方便用户理解，故将错误改为“项目人员保存失败”
//            errMsg = "保存失败\n";
//            //添加虚拟执行错误信息
//            JSONObject saveResult = new JSONObject();
//            saveResult.put("success", false);
//            result.put("success", false);
//            result.put("saveResult", saveResult);
//            log.error(e.toString());
//        }
//        //判断是否通过反射已经成功创建对象
//        if(!Util.isEoN(clazz)) {
//            //service Bean对象的对象名称
//            String serviceName = "";
//            //获取中定义的spring的service注解的名称
//            serviceName = ((Service)(clazz.getAnnotation(Service.class))).value();
//            //判断是否已为service名称是否存在
//            if(Util.isEoN(serviceName)) {
//                //如不存在使用默认的类名首字母小写作为service名称
//                serviceName = (new StringBuilder()).append(Character.toLowerCase(tempServiceNameStr.charAt(0))).append(tempServiceNameStr.substring(1)).toString();
//            }
//            //保存类型
//            String type = tableObject.getString("type");
//            //数据格式类型（normal普通对象Object等）
//            String datatype = tableObject.getString("datatype");
//            //通过数据类型将前台传来的数据转换转换为JSONArray数组类型
//            JSONArray data = GenericFetchAndSave.getDataFromJson(tableObject, datatype);
//            String datatable =tableObject.getString("database");
//            try {
//                //动态执行Service方法
//                JSONObject saveResult = JSONObject.fromObject(Util.springInvokeMethod(serviceName, "genericDatawarehouseSaveFun", new Object[]{datatable, carriertype, carrierid, type, data, tableObject}));
//                //添加执行结果
//                result.put("saveResult", saveResult);
//                result.put("success", true);
//            } catch (Exception e) {
//                result.put("success", false);
//                errResult = serviceName + "//genericDatawarehouseSaveFun"  + "对象保存失败\r\n\r\n";
//                //方便用户理解，故将错误改为“项目人员保存失败”
//                errMsg = "保存失败\n";
//                log.error(e.toString());
//            }
//        }
//        result.put("errResult", errResult);
//        result.put("errMsg", errMsg);
//        return result;
//    }
//
//    /**
//     * 查看数据（数据仓库）
//     * @param dataObject    前台传来的完整对象
//     * @param carrierid     仓库id
//     * @param carriertype   类型
//     * @param request   request请求
//     * @return
//     */
//    public JSONObject initDatawarehouseData(JSONObject dataObject, String carrierid, String carriertype, HttpServletRequest request) {
//
//        Iterator<String> keysIterator = dataObject.keys();
//        while(keysIterator.hasNext()) {
//            // 获得key
//            String key = keysIterator.next();
//            // 根据key获得当前对象的所有信息
//            JSONObject tableObject = dataObject.getJSONObject(key);
//            //通过反射获取数据
//            JSONObject fetchResult = this.initDatawarehouseDataUseReflect(tableObject, carrierid, carriertype);
//            //数据格式类型（normal普通对象Object等）
//            String datatype = tableObject.getString("datatype");
//            if("normal".equals(datatype)) {
//                tableObject.put("data", JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")).getJSONObject(0) : new JSONObject());
//            } else {
//                tableObject.put("data", JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")) : new JSONArray());
//            }
//            dataObject.put(key, tableObject);
//        }
//        return dataObject;
//    }
//
//    /**
//     * 通过反射查看单个对象数据（数据仓库）
//     * @param tableObject   前台传来的完整对象
//     * @param carrierid     id
//     * @param carriertype   类型
//     * @return
//     */
//    private JSONObject initDatawarehouseDataUseReflect(JSONObject tableObject, String carrierid, String carriertype) {
//        JSONObject result = new JSONObject();
//        //文件记录错误信息
//        String errResult = "";
//        //给用户返回的错误信息
//        String errMsg = "";
//        //前台表名与后台数据表映射信息 枚举对象
//        //TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.get("database").toString());
//        //存储字符串型的service类名，例如：Pms_ProjectbaseService
//        String tempServiceNameStr = tableObject.get("database").toString() + "Service";
//        //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
//        String servicePathNameStr = "cn.topcheer.datawarehouse.service." + tempServiceNameStr;
//        Class clazz = null;
//        try {
//            //通过反射创建cn.topcheer.pms.service.PmsProjectpartyService
//            clazz = Class.forName(servicePathNameStr);
//        } catch (Exception e) {
//            result.put("success", false);
//            //处理反射创建对象失败的情况，通过continue继续执行循环
//            errResult = tempServiceNameStr + "创建失败\r\n\r\n";
//            //方便用户理解，故将错误改为“项目人员保存失败”
//            errMsg =  "查询失败\n";
//            //添加虚拟执行错误信息
//            List<Map> fetchResult = new ArrayList<>();
//            result.put("success", false);
//            result.put("fetchResult", fetchResult);
//            result.put("fetchResultAffix", new JSONArray());
//            log.error(e.toString());
//        }
//        //判断是否通过反射已经成功创建对象
//        if(!Util.isEoN(clazz)) {
//            //service Bean对象的对象名称
//            String serviceName = "";
//            //获取中定义的spring的service注解的名称
//            serviceName = ((Service)(clazz.getAnnotation(Service.class))).value();
//            //判断是否已为service名称是否存在
//            if(Util.isEoN(serviceName)) {
//                //如不存在使用默认的类名首字母小写作为service名称
//                serviceName = (new StringBuilder()).append(Character.toLowerCase(tempServiceNameStr.charAt(0))).append(tempServiceNameStr.substring(1)).toString();
//            }
//            //保存类型
//            String type = tableObject.getString("type");
//            //数据格式类型（normal普通对象Object等）
//            String database = tableObject.getString("database");
//            try {
//                //动态执行Service方法
//                List<Map> fetchResult = (List<Map>) Util.springInvokeMethod(serviceName, "genericDatawarehouseFetchFun", new Object[]{database, carriertype, carrierid, type, tableObject});
//                //添加执行结果
//                result.put("fetchResult", fetchResult);
//                result.put("success", true);
//            } catch (Exception e) {
//                result.put("success", false);
//                result.put("fetchResult", new ArrayList<>());
//                errResult = serviceName + "//genericDatawarehouseFetchFun"  + "对象查询失败\r\n\r\n";
//                //方便用户理解，故将错误改为“项目人员查询失败”
//                errMsg = "查询失败\n";
//                log.error(e.toString());
//            }
//        }
//        result.put("errResult", errResult);
//        result.put("errMsg", errMsg);
//        return result;
//    }
//
//
    /**
     * 获取PDF数据源
     * @param dataObject    需下载的对象
     * @param mainid      主表id
     * @param batchid       批次id
     * @param request
     * @return
     */
    public Map<String, Object> downloadData(JSONObject dataObject, String mainid, String batchid, HttpServletRequest request) {
        Map<String, Object> hashmap = new HashMap<>();
        Iterator<String> keysIterator = dataObject.keys();
        while(keysIterator.hasNext()) {
            // 获得key
            String key = keysIterator.next();
            // 根据key获得当前对象的所有信息
            JSONObject tableObject = dataObject.getJSONObject(key);
            //通过反射获取数据源数据
            JSONObject downloadDataResult = this.downloadDataUseReflect(tableObject, mainid, batchid);
            //将数据源统一加入hashmap中
            hashmap.putAll((Map<String, Object>)downloadDataResult.get("downloadDataResult"));
            //数据格式类型（normal普通对象Object等）
            String datatype = tableObject.getString("datatype");
            //获取前台传的对象名称
            String datakey = tableObject.get("tablename") + "";
            if(Util.isEoN(datakey)) {
                datakey = "myclob";
            }
            if("normal".equals(datatype) && ((List<Map>)(((Map<String, Object>)(downloadDataResult.get("downloadDataResult"))).get(datakey))).size() > 0) {
                hashmap.put(datakey + "_normal", ((List<Map>)(((Map<String, Object>)(downloadDataResult.get("downloadDataResult"))).get(datakey))).get(0));
            }
        }
        Set allKeySet = new HashSet();
        for(Object newKey : hashmap.keySet()) {
            allKeySet.add(newKey);
        }
        for(Object key : allKeySet) {
            if(key.toString().endsWith("_normal")) {
                dealDataSource(key.toString(), hashmap);
            }
        }
        hashmap = genericSpecialDownloadData.SpecialDownloadData(hashmap, dataObject, mainid, batchid);
        return hashmap;
    }
//
    /**
     * 通过反射获取单个数据源数据
     * @param tableObject
     * @param mainid
     * @param batchid
     * @return
     */
    private JSONObject downloadDataUseReflect(JSONObject tableObject, String mainid, String batchid) {
        JSONObject result = new JSONObject();
        //文件记录错误信息
        String errResult = "";
        //给用户返回的错误信息
        String errMsg = "";
        //前台表名与后台数据表映射信息 枚举对象
        TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.get("database").toString());
        //存储字符串型的service类名，例如：Pms_ProjectbaseService
        String tempServiceNameStr = "";
        //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
        String servicePathNameStr = "";
        //判断枚举信息中是否包含service名称
        if(!Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
            tempServiceNameStr = tableMappingEnum.getServiceclassname().getSimpleName();
            servicePathNameStr = tableMappingEnum.getServiceclassname().getName();
        } else {
            tempServiceNameStr = (tableMappingEnum.getClassname().getSimpleName() + "Service");
            servicePathNameStr = PmsProjectbaseService.class.getPackage().toString().substring(8) + "." + tempServiceNameStr;
        }
        Class clazz = null;
        try {
            //通过反射创建cn.topcheer.pms.service.PmsProjectvpartySerice
            clazz = Class.forName(servicePathNameStr);
        } catch (Exception e) {
            result.put("success", false);
            //处理反射创建对象失败的情况，通过continue继续执行循环
            errResult = tempServiceNameStr + "创建失败\r\n\r\n";
            //方便用户理解，故将错误改为“项目人员获取失败”
            errMsg = ((ClassInfo)(tableMappingEnum.getClassname().getAnnotation(ClassInfo.class))).name() + "获取失败\n";
            //添加虚拟执行错误信息
            List<Map> downloadDataResult = new ArrayList<>();
            result.put("success", false);
            result.put("downloadDataResult", downloadDataResult);
//            log.error(e.toString());
        }
        //判断是否通过反射已经成功创建对象
        if(!Util.isEoN(clazz)) {
            //service Bean对象的对象名称
            String serviceName = "";
            //获取中定义的spring的service注解的名称
            serviceName = ((Service)(clazz.getAnnotation(Service.class))).value();
            //判断是否已为service名称是否存在
            if(Util.isEoN(serviceName)) {
                //如不存在使用默认的类名首字母小写作为service名称
                serviceName = (new StringBuilder()).append(Character.toLowerCase(tempServiceNameStr.charAt(0))).append(tempServiceNameStr.substring(1)).toString();
            }
            //保存类型
            String type = tableObject.getString("type");
            //数据格式类型（normal普通对象Object等）
            String datatype = tableObject.getString("datatype");
            try {
                //动态执行Service方法
                Map<String, Object> downloadDataResult = (Map<String, Object>) Util.springInvokeMethod(serviceName, "genericPdfDownloadFun", new Object[]{mainid, type, batchid, tableObject});
                //添加执行结果
                result.put("downloadDataResult", downloadDataResult);
                result.put("success", true);
            } catch (Exception e) {
                result.put("success", false);
                result.put("downloadDataResult", new ArrayList<>());
                errResult = serviceName + "//genericPdfDownloadFun"  + "对象获取失败\r\n\r\n";
                //方便用户理解，故将错误改为“项目人员查询失败”
                errMsg = ((ClassInfo)(tableMappingEnum.getClassname().getAnnotation(ClassInfo.class))).name() + "获取失败\n";
//                log.error(e.toString());
            }
        }
        result.put("errResult", errResult);
        result.put("errMsg", errMsg);
        return result;
    }
//
    /**
     * 处理对象型的数据源
     * @param key
     * @param hashmap
     * @return
     */
    private Map<String, Object> dealDataSource(String key, Map<String, Object> hashmap) {
        Set inKeySet = ((Map<String, Object>)(hashmap.get(key))).keySet();
        for(Object inkey : inKeySet) {
            hashmap.put(key + "_" + inkey, ((Map<String, Object>)(hashmap.get(key))).get(inkey));
        }
        return hashmap;
    }
//
    /**
     * 判断该主表记录是否保存过
     * @param mainid    主表id
     * @param tablename 主表名称（例如：PMS_PROJECTBASE)
     * @return
     */
    private Boolean isSave(String mainid, String tablename){
        Boolean result = false;
        if(Util.isEoN(tablename)) {
            return result;
        }
        String sql = "select t.id from " + tablename + " t where t.id = ? ";
        String id = dbHelper.getOnlyStringValue(sql, new Object[]{mainid});
        if(Util.isEoN(id)){
            result = false;
        }else{
            result = true;
        }
        return result;
    }
//
//    /**
//     * 保存推荐单位信息
//     * @param mainid        主表id
//     * @param batchid       批次id
//     * @param dataObject    前台传参
//     */
//    public void saveCasemanagement(String mainid, String batchid, JSONObject dataObject) {
//
//        JSONObject result = new JSONObject();
//        Iterator<String> keysIterator = dataObject.keys();
//        while(keysIterator.hasNext()) {
//            // 获得key
//            String key = keysIterator.next();
//            // 根据key获得当前对象的所有信息
//            JSONObject tableObject = dataObject.getJSONObject(key);
//            TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.get("database").toString());
//            //前台表名与后台数据表映射信息 枚举对象
//            MainTable mainTable = (MainTable) tableMappingEnum.getClassname().getAnnotation(MainTable.class);
//            if(!Util.isEoN(mainTable)) {
//                //存储字符串型的service类名，例如：Pms_ProjectbaseService
//                String tempServiceNameStr = "";
//                //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
//                String servicePathNameStr = "";
//                //判断枚举信息中是否包含service名称
//                if(!Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
//                    tempServiceNameStr = tableMappingEnum.getServiceclassname().getSimpleName();
//                    servicePathNameStr = tableMappingEnum.getServiceclassname().getName();
//                } else {
//                    tempServiceNameStr = (tableMappingEnum.getClassname().getSimpleName() + "Service");
//                    servicePathNameStr = PmsProjectbaseService.class.getPackage().toString().substring(8) + "." + tempServiceNameStr;
//                }
//                Class clazz = null;
//                try {
//                    //通过反射创建cn.topcheer.pms.service.PmsProjectpartyService
//                    clazz = Class.forName(servicePathNameStr);
//                } catch (Exception e) {
//                    result.put("success", false);
//                    log.error(e.toString());
//                }
//                //判断是否通过反射已经成功创建对象
//                if(!Util.isEoN(clazz)) {
//                    //service Bean对象的对象名称
//                    String serviceName = "";
//                    //获取中定义的spring的service注解的名称
//                    serviceName = ((Service)(clazz.getAnnotation(Service.class))).value();
//                    //判断是否已为service名称是否存在
//                    if(Util.isEoN(serviceName)) {
//                        //如不存在使用默认的类名首字母小写作为service名称
//                        serviceName = (new StringBuilder()).append(Character.toLowerCase(tempServiceNameStr.charAt(0))).append(tempServiceNameStr.substring(1)).toString();
//                    }
//                    try {
//                        List<Map> tempCasemanagementList = new ArrayList<>();
//                        PmsPlanprojectbatch pmsPlanprojectbatch = pmsPlanprojectbatchService.findById(batchid);
//                        if(Util.isEoN(pmsPlanprojectbatch.getCasemanagementmold()) || "自动".equals(pmsPlanprojectbatch.getCasemanagementmold())) {
//                            //自动获取推荐单位
//                            tempCasemanagementList = pmsEnterpriseService.fetchAllCasemanagement();
//                        } else {
//                            //有用户手动填写的
//                            tempCasemanagementList = fetchUserSaveAllCasemanagement(tableObject);
//                        }
//                        Map casemanagementMap = fetchCasemanagement(tempCasemanagementList, batchid);
//                        //动态执行Service方法
//                        Util.springInvokeMethod(serviceName, "genericSaveCasemanagementFun", new Object[]{mainid, tempCasemanagementList, casemanagementMap, batchid, dataObject});
//                    } catch (Exception e) {
//                        log.error(e.toString());
//                    }
//                }
//                break;
//            }
//        }
//    }
//
//    /**
//     * 根据市县推荐单位获取最终推荐单位
//     * @param tempList  市县推荐单位
//     * @param batchid   批次id
//     * @return
//     */
//    private Map fetchCasemanagement(List<Map> tempList, String batchid) {
//        Map result = new HashMap();
//        if(tempList.size() == 0) {
//            return result;
//        } else if(tempList.size() == 1) {
//            result = tempList.get(0);
//        } else {
//            // 判断有无指标信息，有指标按使用指标的为推荐单位，先看县再看市，如果没有，则县作为推荐单位
//            String sql = "select * from PMS_ENTERPRISELIMIT where batchid = ? and enterpriseid = ? ";
//            List<Map> countryLimitList = dbHelper.getRows(sql, new Object[]{batchid, tempList.get(1).get("enterpriseid")});
//            List<Map> cityLimitList = dbHelper.getRows(sql, new Object[]{batchid, tempList.get(0).get("enterpriseid")});
//            if(countryLimitList.size() > 0) {
//                result = tempList.get(1);
//            } else if(cityLimitList.size() > 0) {
//                result = tempList.get(0);
//            } else {
//                result = tempList.get(1);
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * 获取用户填写的推荐单位信息
//     * @param tableObject
//     * @return
//     */
//    private List<Map> fetchUserSaveAllCasemanagement(JSONObject tableObject){
//        List<Map> result = new ArrayList<>();
//        JSONObject datanewobject=tableObject.getJSONObject("data");
//        if(!Util.isEoN(datanewobject.get("citycasemanagement")) || !Util.isEoN(datanewobject.get("citycasemanagementid"))) {
//            Map cityMap = new HashMap();
//            if(Util.isEoN(datanewobject.get("citycasemanagement"))) {
//                cityMap.put("enterprisename", pmsEnterpriseService.findById(datanewobject.get("citycasemanagementid").toString()).getName());
//            } else {
//                cityMap.put("enterprisename", datanewobject.get("citycasemanagement"));
//            }
//            if(Util.isEoN(datanewobject.get("citycasemanagementid"))) {
//                cityMap.put("enterpriseid", fetchEnterpriseByname(datanewobject.get("citycasemanagement").toString()).getId());
//            } else {
//                cityMap.put("enterpriseid", datanewobject.get("citycasemanagementid"));
//            }
//            result.add(cityMap);
//        }
//        if(!Util.isEoN(datanewobject.get("countrycasemanagement")) || !Util.isEoN(datanewobject.get("countrycasemanagementid"))) {
//            Map countryMap = new HashMap();
//            if(Util.isEoN(datanewobject.get("countrycasemanagement"))) {
//                countryMap.put("enterprisename", pmsEnterpriseService.findById(datanewobject.get("countrycasemanagementid").toString()).getName());
//            } else {
//                countryMap.put("enterprisename", datanewobject.get("countrycasemanagement"));
//            }
//            if(Util.isEoN(datanewobject.get("countrycasemanagementid"))) {
//                countryMap.put("enterpriseid", fetchEnterpriseByname(datanewobject.get("countrycasemanagement").toString()).getId());
//            } else {
//                countryMap.put("enterpriseid", datanewobject.get("countrycasemanagementid"));
//            }
//            result.add(countryMap);
//        }
//        return result;
//    }
//
//    /**
//     * 通过单位名称获取单位
//     * @param enterprisename
//     * @return
//     */
//    private PmsEnterprise fetchEnterpriseByname(String enterprisename) {
//        PmsEnterprise pmsEnterprise = new PmsEnterprise();
//        String hql = " select t from PmsEnterprise t where t.name = ? ";
//        List<PmsEnterprise> pmsEnterpriseList = pmsEnterpriseService.findByHql(hql, enterprisename);
//        if(!Util.isEoN(pmsEnterpriseList) && pmsEnterpriseList.size() > 0) {
//            pmsEnterprise = pmsEnterpriseList.get(0);
//        }
//        return pmsEnterprise;
//    }
//
//
//
//    /**
//     * 保存数据（数据仓库）
//     * @param dataObject
//     * @param mainid
//     * @param request
//     * @return
//     */
//    public JSONObject savaWarehouseData(JSONObject dataObject, String mainid, HttpServletRequest request) {
//
//        JSONObject result = new JSONObject();
//        //用于存储保存方法执行的结果
//        JSONArray resultData = new JSONArray();
//        //获取key迭代器
//        Iterator<String> keyIterator = dataObject.keys();
//        while(keyIterator.hasNext()){
//            //获得key
//            String key = keyIterator.next();
//            //根据key获得当前对象的所有信息
//            JSONObject tableObject = dataObject.getJSONObject(key);
//            //通过反射保存单个对象数据
//            JSONObject saveResult = this.saveWarehouseDataUseReflect(tableObject, mainid);
//            //将保存的结果添加到数组中
//            resultData.add(saveResult);
//        }
//        result.put("resultData", resultData);
//        return result;
//    }
//
//    /**
//     * 通过反射保存单个对象数据（数据仓库）
//     * @param tableObject
//     * @param mainid
//     * @return
//     */
//    private JSONObject saveWarehouseDataUseReflect(JSONObject tableObject, String mainid) {
//        JSONObject result = new JSONObject();
//        //文件记录错误信息
//        String errResult = "";
//        //给用户返回的错误信息
//        String errMsg = "";
//        //前台表名与后台数据表映射信息 枚举对象
//        //存储字符串型的service类名，例如：Pms_ProjectbaseService
//        String tempServiceNameStr = tableObject.get("database").toString() + "Service";
//        //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
//        String servicePathNameStr = "cn.topcheer.warehouse.service." + tempServiceNameStr;
//        Class clazz = null;
//        try {
//            //通过反射创建cn.topcheer.pms.service.PmsProjectpartyService
//            clazz = Class.forName(servicePathNameStr);
//        } catch (Exception e) {
//            result.put("success", false);
//            //处理反射创建对象失败的情况，通过continue继续执行循环
//            errResult = tempServiceNameStr + "创建失败\r\n\r\n";
//            //方便用户理解，故将错误改为“项目人员保存失败”
//            errMsg = "保存失败\n";
//            //添加虚拟执行错误信息
//            JSONObject saveResult = new JSONObject();
//            saveResult.put("success", false);
//            result.put("success", false);
//            result.put("saveResult", saveResult);
//            log.error(e.toString());
//        }
//        //判断是否通过反射已经成功创建对象
//        if(!Util.isEoN(clazz)) {
//            //service Bean对象的对象名称
//            String serviceName = "";
//            //获取中定义的spring的service注解的名称
//            serviceName = ((Service)(clazz.getAnnotation(Service.class))).value();
//            //判断是否已为service名称是否存在
//            if(Util.isEoN(serviceName)) {
//                //如不存在使用默认的类名首字母小写作为service名称
//                serviceName = (new StringBuilder()).append(Character.toLowerCase(tempServiceNameStr.charAt(0))).append(tempServiceNameStr.substring(1)).toString();
//            }
//            //保存类型
//            String type = tableObject.getString("type");
//            //数据格式类型（normal普通对象Object等）
//            String datatype = tableObject.getString("datatype");
//            //通过数据类型将前台传来的数据转换转换为JSONArray数组类型
//            JSONArray data = GenericFetchAndSave.getDataFromJson(tableObject, datatype);
//            String datatable =tableObject.getString("database");
//            try {
//                //动态执行Service方法
//                JSONObject saveResult = JSONObject.fromObject(Util.springInvokeMethod(serviceName, "genericSaveFun", new Object[]{datatable, mainid, type, data, tableObject}));
//                //添加执行结果
//                result.put("saveResult", saveResult);
//                result.put("success", true);
//            } catch (Exception e) {
//                result.put("success", false);
//                errResult = serviceName + "//genericWarehouseSaveFun"  + "对象保存失败\r\n\r\n";
//                //方便用户理解，故将错误改为“项目人员保存失败”
//                errMsg = "保存失败\n";
//                log.error(e.toString());
//            }
//        }
//        result.put("errResult", errResult);
//        result.put("errMsg", errMsg);
//        return result;
//    }
//
//    /**
//     * 查看数据（数据仓库）
//     * @param dataObject    前台传来的完整对象
//     * @param mainid     仓库id
//     * @param request   request请求
//     * @return
//     */
//    public JSONObject initWarehouseData(JSONObject dataObject, String mainid, HttpServletRequest request) {
//
//        Iterator<String> keysIterator = dataObject.keys();
//        while(keysIterator.hasNext()) {
//            // 获得key
//            String key = keysIterator.next();
//            // 根据key获得当前对象的所有信息
//            JSONObject tableObject = dataObject.getJSONObject(key);
//            //通过反射获取数据
//            JSONObject fetchResult = this.initWarehouseDataUseReflect(tableObject, mainid);
//            //数据格式类型（normal普通对象Object等）
//            String datatype = tableObject.getString("datatype");
//            if("normal".equals(datatype)) {
//                tableObject.put("data", JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")).getJSONObject(0) : new JSONObject());
//            } else {
//                tableObject.put("data", JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")) : new JSONArray());
//            }
//            dataObject.put(key, tableObject);
//        }
//        return dataObject;
//    }
//
//    /**
//     * 通过反射查看单个对象数据（数据仓库）
//     * @param tableObject   前台传来的完整对象
//     * @param mainid     id
//     * @return
//     */
//    private JSONObject initWarehouseDataUseReflect(JSONObject tableObject, String mainid) {
//        JSONObject result = new JSONObject();
//        //文件记录错误信息
//        String errResult = "";
//        //给用户返回的错误信息
//        String errMsg = "";
//        //前台表名与后台数据表映射信息 枚举对象
//        //TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.get("database").toString());
//        //存储字符串型的service类名，例如：Pms_ProjectbaseService
//        String tempServiceNameStr = tableObject.get("database").toString() + "Service";
//        //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
//        String servicePathNameStr = "cn.topcheer.warehouse.service." + tempServiceNameStr;
//        Class clazz = null;
//        try {
//            //通过反射创建cn.topcheer.pms.service.PmsProjectpartyService
//            clazz = Class.forName(servicePathNameStr);
//        } catch (Exception e) {
//            result.put("success", false);
//            //处理反射创建对象失败的情况，通过continue继续执行循环
//            errResult = tempServiceNameStr + "创建失败\r\n\r\n";
//            //方便用户理解，故将错误改为“项目人员保存失败”
//            errMsg =  "查询失败\n";
//            //添加虚拟执行错误信息
//            List<Map> fetchResult = new ArrayList<>();
//            result.put("success", false);
//            result.put("fetchResult", fetchResult);
//            result.put("fetchResultAffix", new JSONArray());
//            log.error(e.toString());
//        }
//        //判断是否通过反射已经成功创建对象
//        if(!Util.isEoN(clazz)) {
//            //service Bean对象的对象名称
//            String serviceName = "";
//            //获取中定义的spring的service注解的名称
//            serviceName = ((Service)(clazz.getAnnotation(Service.class))).value();
//            //判断是否已为service名称是否存在
//            if(Util.isEoN(serviceName)) {
//                //如不存在使用默认的类名首字母小写作为service名称
//                serviceName = (new StringBuilder()).append(Character.toLowerCase(tempServiceNameStr.charAt(0))).append(tempServiceNameStr.substring(1)).toString();
//            }
//            //保存类型
//            String type = tableObject.getString("type");
//            //数据格式类型（normal普通对象Object等）
//            String database = tableObject.getString("database");
//            try {
//                //动态执行Service方法
//                List<Map> fetchResult = (List<Map>) Util.springInvokeMethod(serviceName, "genericFetchFun", new Object[]{database, mainid, type, tableObject});
//                //添加执行结果
//                result.put("fetchResult", fetchResult);
//                result.put("success", true);
//            } catch (Exception e) {
//                result.put("success", false);
//                result.put("fetchResult", new ArrayList<>());
//                errResult = serviceName + "//genericFetchFun"  + "对象查询失败\r\n\r\n";
//                //方便用户理解，故将错误改为“项目人员查询失败”
//                errMsg = "查询失败\n";
//                log.error(e.toString());
//            }
//        }
//        result.put("errResult", errResult);
//        result.put("errMsg", errMsg);
//        return result;
//    }
//

    /**
     * 列表数据删除
     *
     * @param jsonObject JSONObject
     * @return Boolean
     */
    @Transactional
    public Boolean deleteData(JSONObject jsonObject) {

        Boolean result = true;
        //操作记录
        String mainid = jsonObject.get("mainid") + "";
        String sysModuleType = jsonObject.get("sys_module_type") + "";
        SysModuleEnum sysModuleEnum = SysModuleEnum.valueOf(sysModuleType);

        // 获取当前用户
        SysUser sysUser = sysUserService.findById(AuthUtil.getUserId());
        if (sysUser == null) {
            throw new ServiceException("当前登录用户无效");
        }

        this.sysOperationrecordService.commonSaveOperation(mainid, "用户删除_" + sysModuleEnum.getName(), "操作人" + sysUser.getName() + "(" + sysUser.getId() + ")删除了" + sysModuleEnum.getName() + "，主表id：" + mainid);
        if (Util.isEoN(sysModuleEnum)) {
            throw new ServiceException("删除失败，类型传参不正确!!!");
        }

        List<TableMappingEnum> commonTableEnumList = new ArrayList<>();
        TableMappingEnum mainTableEnum = null;
        for (TableMappingEnum tableMappingEnum : TableMappingEnum.values()) {
            Class classname = tableMappingEnum.getClassname();
            if (classname.isAnnotationPresent(ClassInfo.class)) {
                if (sysModuleEnum.equals(((ClassInfo) classname.getAnnotation(ClassInfo.class)).module())) {
                    if (classname.isAnnotationPresent(MainTable.class)) {
                        mainTableEnum = tableMappingEnum;
                    } else {
                        commonTableEnumList.add(tableMappingEnum);
                    }
                }
            }
        }

        if (Util.isEoN(mainTableEnum)) {
            throw new ServiceException("删除失败，未找到业务主表!!!");
        }


        //获取中定义的spring的service注解的名称
        String serviceName = ((Service) mainTableEnum.getServiceclassname().getAnnotation(Service.class)).value();
        //动态执行Service方法
        try {
            boolean flag = (boolean) Util.springInvokeMethod(serviceName, "judgetBelongToCurrentUser", new Object[]{mainid});
            if (flag) {
                // 满足为当前申报人的情况故可以使用删除-逻辑删除
                Util.springInvokeMethod(serviceName, "deleteLogicById", new Object[]{mainid});
//                serviceName = "";
//                for (TableMappingEnum commonTableEnum : commonTableEnumList) {
//                    serviceName = ((Service) commonTableEnum.getServiceclassname().getAnnotation(Service.class)).value();
//                    try {
//                        Util.springInvokeMethod(serviceName, "deleteDataByMainid", new Object[]{mainid});
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        throw new ServiceException("删除失败，请联系系统维护员：" + Util.linkNumber);
//                    }
//                }
                //删除项目的修改记录、退回记录、项目级附件、流程相关内容
//                    pmsEditDataService.deletePmsEditDataBySourceid(mainid);
//                    pmsReturnDataService.deletePmsReturnDataBySourceid(mainid);
//                    pmsAttachmentService.deletePmsAttachmentBySourceid(mainid);
//                    pmsAllattachmentsService.deletePmsAllattachmentsBySourceid(mainid);
//                    flowRelationDetailService.deletePmsEditDataBySourceid(mainid);
//                    flowRecordService.deleteFlowRecordBySourceid(mainid);
            } else {
                throw new ServiceException("删除失败，当前业务不属于该用户，无法删除!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("删除失败，请联系系统维护员：" + Util.linkNumber);
        }

        return result;
    }
//
//    /**
//     * 判断数据是否存在
//     * @param jsonObject
//     * @return
//     */
//    public JSONObject judgeDataExist(JSONObject jsonObject) {
//        JSONObject result = new JSONObject();
//        String database = jsonObject.get("database") + "";
//        if(Util.isEoN(database)) {
//            result.put("success", false);
//            result.put("data", new ArrayList<>());
//            result.put("errMsg", "接口表名未传输!!!");
//            return result;
//        }
//        JSONArray param = jsonObject.getJSONArray("param");
//        if(param.size() == 0) {
//            result.put("success", false);
//            result.put("data", new ArrayList<>());
//            result.put("errMsg", "接口参数未传输!!!");
//            return result;
//        }
//        try {
//            JSONArray paramArr = new JSONArray();
//            String inSql = "";
//            for(int i = 0; i < param.size(); i++) {
//                String key = param.getJSONObject(i).get("key") + "";
//                inSql += " and " + param.getJSONObject(i).get("key") + " = ? ";
//                paramArr.add(param.getJSONObject(i).get("value"));
//            }
//            String tablename = ((Table)TableMappingEnum.getEnumByTablename(database).getClassname().getAnnotation(Table.class)).name();
//            String sql = " select id from " + tablename + " where 1 = 1 ";
//            List<Map> rows = dbHelper.getRows(sql + inSql, paramArr.toArray());
//            JSONArray idArr = new JSONArray();
//            for(int i = 0; i < rows.size(); i++) {
//                idArr.add(rows.get(i).get("id"));
//            }
//            result.put("success", true);
//            result.put("data", idArr);
//        } catch (Exception e) {
//            result.put("success", false);
//            result.put("data", new ArrayList<>());
//            result.put("errMsg", "后台程序有误，请联系系统维护员："+ Util.linkNumber);
//            e.printStackTrace();
//        } finally {
//            return result;
//        }
//    }
//
//    /**
//     * 判断用户是否拥有对此批次申报填写的权限
//     * @return
//     */
//    public JSONObject judgeUserHasApplyAuthorityForBatch(String batchid, String userid, String dabasename) {
//        JSONObject result = new JSONObject();
//        //未登录（用户注册及单位注册）
//        if(("DATAWAREHOUSE-USER-REGISTER".equals(batchid) || "DATAWAREHOUSE-UNIT-REGISTER".equals(batchid)) && Util.isEoN(userid)) {
//            result.put("success", true);
//            return result;
//        }
//        if("admin".equals(userid) || "KJTPZRY-FDW123SD123123".equals(userid)) {
//            result.put("success", true);
//            return result;
//        }
//        String planprojectid = "";
//        try {
//            PmsPlanprojectbatch pmsPlanprojectbatch = (PmsPlanprojectbatch)Util.springInvokeMethod("PmsPlanprojectbatchServvice", "findById", new Object[]{batchid});
//            planprojectid = pmsPlanprojectbatch.getPmsPlanproject().getId();
//            String limitSql = "select l.* from PMS_BATCH_PORT_LIMIT l where l.planprojectid = ? and (l.planprojectbatchid = ? or l.planprojectbatchid is null) and (',' || l.DATABASENAMES || ',' like ? or l.DATABASENAMES is null) order by l.planprojectbatchid nulls last, l.DATABASENAMES nulls last ";
//            //搜索结果最多两条，按照小批次id不为空，为空的顺序进行排序，默认取第一条即可
//            List<Map> limitList = (List<Map>)Util.springInvokeMethod("dbHelper", "getRows", new Object[]{limitSql, new Object[]{planprojectid, batchid, "%," + dabasename + ",%"}});
//            if(!Util.isEoN(limitList) && limitList.size() == 0) {
//                result.put("success", true);
//            } else if(!Util.isEoN(limitList) && limitList.size() > 0) {
//                result.put("success", false);
//                result.put("errMsg", "当前用户角色没有保存的权限,请联系系统维护员："+Util.linkNumber);
//                String userids = limitList.get(0).get("userids").toString();
//                //没有配置针对用户userid的设置
//                if(Util.isEoN(userids)) {
//                    String roleids = limitList.get(0).get("roleids").toString();
//                    SysUser sysUser = (SysUser)Util.springInvokeMethod("sysUserService", "findById", new Object[]{userid});
//                    List<SysIdentity> sysIdentitys = sysUser.getSysIdentitys();
//                    for (int i = 0; i < sysIdentitys.size(); i++) {
//                        if((";" + roleids + ";").indexOf(";" + sysIdentitys.get(i).getSysRole().getId() + ";") > -1) {
//                            result.put("success", true);
//                            result.remove("errMsg");
//                            break;
//                        }
//                    }
//                } else {
//                    //根据用户id来判断
//                    if((";" + userids + ";").indexOf(";" + userid + ";") > -1) {
//                        result.put("success", true);
//                        result.remove("errMsg");
//                    }
//                }
//            } else {
//                result.put("success", false);
//                result.put("errMsg", "后台程序有误,请联系系统维护员："+Util.linkNumber);
//            }
//        } catch (Exception e) {
//            result.put("success", false);
//            result.put("errMsg", "后台程序有误,请联系系统维护员："+Util.linkNumber);
//        } finally {
//            return result;
//        }
//    }
//
//
//    /**
//     * 判断用户是否拥有对此批次申报填写的权限
//     * @return
//     */
//    public JSONObject judgeProjectBelongToUser(String batchid, String userid, String declarantid) {
//        JSONObject result = new JSONObject();
//        //未登录（用户注册及单位注册）
//        if(("DATAWAREHOUSE-USER-REGISTER".equals(batchid) || "DATAWAREHOUSE-UNIT-REGISTER".equals(batchid)) && Util.isEoN(userid)) {
//            result.put("success", true);
//            return result;
//        }
//        //特殊人员
//        if("admin".equals(userid) || "KJTPZRY-FDW123SD123123".equals(userid)) {
//            result.put("success", true);
//            return result;
//        }
//        SysUser user = this.sysUserService.findById(userid);
//        if("DATAWAREHOUSE-UNIT-REGISTER".equals(batchid) && "2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487".equals(user.getSysIdentitys().get(0).getSysRole().getId())){
//            result.put("success", true);
//            return result;
//        }
//        //具体业务
//        if(userid.equals(declarantid) || Util.isEoN(declarantid)) {
//            result.put("success", true);
//        } else {
//            result.put("success", false);
//            result.put("errMsg", "当前项目不属于该用户,没有权限保存,请联系系统维护员："+Util.linkNumber);
//        }
//        return result;
//    }
//
//    /**
//     * 创建返回值
//     * @param result
//     * @param success
//     * @param errMsg
//     * @return
//     */
//    private JSONObject creatRertunData(JSONObject result, Boolean success, String errMsg) {
//        JSONObject saveResult = new JSONObject();
//        saveResult.put("success", success);
//        result.put("saveResult", saveResult);
//        result.put("errResult", errMsg);
//        result.put("errMsg", errMsg);
//        result.put("success", success);
//        return result;
//    }
//

    /**
     * 查看数据
     *
     * @param dataObject 前台传来的完整对象
     * @param mainidArr  主表id
     * @return
     */
    public JSONObject initExportData(JSONObject dataObject, JSONArray mainidArr) {

        Iterator<String> keysIterator = dataObject.keys();
        while (keysIterator.hasNext()) {
            // 获得key
            String key = keysIterator.next();
            // 根据key获得当前对象的所有信息
            JSONObject tableObject = dataObject.getJSONObject(key);
//            JSONObject fetchResult = this.initExportDataUseReflect(tableObject, mainidArr);
            //数据格式类型（normal普通对象Object等）
            String datatype = tableObject.getString("datatype");
//            tableObject.put("data", JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")) : new JSONArray());
            dataObject.put(key, tableObject);
        }
        return dataObject;
    }
//
//    /**
//     * 通过反射查看单个对象数据
//     * @param tableObject   前台传来的完整对象
//     * @param mainidArr  主表id
//     * @return
//     */
//    private JSONObject initExportDataUseReflect(JSONObject tableObject, JSONArray mainidArr) {
//        JSONObject result = new JSONObject();
//        //前台表名与后台数据表映射信息 枚举对象
//        TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.get("database").toString());
//        //存储字符串型的service类名，例如：Pms_ProjectbaseService
//        String tempServiceNameStr = tableMappingEnum.getServiceclassname().getSimpleName();
//        //serveic全路径名称（包名 + 类名），例如：cn.topcheer.pms.service.Pms_ProjectbaseService
//        String servicePathNameStr = tableMappingEnum.getServiceclassname().getName();
//        Class clazz = null;
//        try {
//            //通过反射创建 PmsProjectpartyService
//            clazz = Class.forName(servicePathNameStr);
//        } catch (Exception e) {
//            throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR_CODE_ERROR, e);
//        }
//        //判断是否通过反射已经成功创建对象
//        if(!Util.isEoN(clazz)) {
//            //service Bean对象的对象名称
//            String serviceName = "";
//            //获取中定义的spring的service注解的名称
//            serviceName = ((Service)(clazz.getAnnotation(Service.class))).value();
//            //判断是否已为service名称是否存在
//            if(Util.isEoN(serviceName)) {
//                //如不存在使用默认的类名首字母小写作为service名称
//                throw new BusinessException(serviceName + "不存在!!!");
//            }
//            //保存类型
//            String type = tableObject.getString("type");
//            //数据格式类型（normal普通对象Object等）
//            try {
//                //动态执行Service方法
//                List<Map> fetchResult = (List<Map>) Util.springInvokeMethod(serviceName, "fetchDataFun", new Object[]{mainidArr, type});
//                //添加执行结果
//                result.put("fetchResult", fetchResult);
//                result.put("success", true);
//            } catch (Exception e) {
//                result.put("success", false);
//                result.put("fetchResult", new ArrayList<>());
//                log.error("initDataUseReflectError", e);
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 查看项目信息表数据
//     * @param dataObject
//     * @param mainid
//     * @param batchid
//     * @param request
//     * @param initParamArr
//     * @return
//     */
//    public JSONObject initXMXXBData(JSONObject dataObject, String mainid, String batchid, HttpServletRequest request, String[] initParamArr) {
//        JSONObject result = new JSONObject();
//        Boolean saveFlag = false;
//        Iterator<String> keysIterator0 = dataObject.keys();
//        while(keysIterator0.hasNext()) {
//            // 获得key
//            String key = keysIterator0.next();
//            // 根据key获得当前对象的所有信息
//            JSONObject tableObject = dataObject.getJSONObject(key);
//            //获得枚举
//            TableMappingEnum database = TableMappingEnum.getEnumByTablename(tableObject.get("database").toString());
//            //前台表名与后台数据表映射信息 枚举对象
//            MainTable mainTable = (MainTable) database.getClassname().getAnnotation(MainTable.class);
//            //通过对应表名去库里找所有数据
//            Table table = (Table) database.getClassname().getAnnotation(Table.class);
//            String tablename = table.name();
//            if(!Util.isEoN(mainTable)) {
//                //判断主表是否保存过
//                saveFlag = isSave(mainid, tablename);
//                JSONObject fetchResult = this.initDataUseReflect(tableObject, mainid, batchid, saveFlag, initParamArr);
////                tableObject.put("data", JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")).getJSONObject(0) : new JSONObject());
////                dataObject.put(key, tableObject);
//                result.put(key,JSONArray.fromObject(fetchResult.get("fetchResult")).size() > 0 ? JSONArray.fromObject(fetchResult.get("fetchResult")).getJSONObject(0) : new JSONObject());
//            }else{
//                //非主表 直接通过对应service的findById方法就好
//                if (!Util.isEoN(table)){
//
//                    //条件语句
//                    String wheresql = "where mainid = ? ";
//                    JSONObject param = tableObject.getJSONObject("param");
//                    Iterator paramKeys = param.keys();
//                    while (paramKeys.hasNext()){
//                        Object column = paramKeys.next();//字段
//                        Object value = param.get(column);//属性
//                        wheresql = wheresql +" and "+column +" = '"+value+"'";
//                    }
//
//                    //排序语句
//                    String orderbysql = Util.isEoN(tableObject.get("orderby"))?"":tableObject.get("orderby")+"";
//
//
//                    List<Map> rows = dbHelper.getRows(" select * from " + tablename + wheresql +orderbysql, new Object[]{mainid});
//                    result.put(key,(null!=rows&&rows.size()>0)?JSONObject.fromObject(rows.get(0)):new JSONObject());
//                }
//            }
//
//        }
//        List<Map> rows = dbHelper.getRows("select * from fl_flowrecord where sourceid = ?  order by operationtime asc", new Object[]{mainid});
//        result.put("flowRecord",rows);
//        //和上面的initData差不多，但是这里不用附件，先去掉
//        return result;
//    }
}
