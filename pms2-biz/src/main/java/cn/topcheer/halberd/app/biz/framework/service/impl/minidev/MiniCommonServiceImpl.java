package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.minidev.dto.*;
import cn.topcheer.halberd.app.api.minidev.result.JudgeTaskResult;
import cn.topcheer.halberd.app.api.minidev.result.ProcessTransferDataResult;
import cn.topcheer.halberd.app.api.minidev.result.ProcessTransferResult;
import cn.topcheer.halberd.app.api.minidev.service.*;
import cn.topcheer.halberd.app.api.utils.PmsTxtSave;
import cn.topcheer.halberd.app.api.utils.ProccessUtil;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.halberd.flow.entity.FlowBusiness;
import cn.topcheer.halberd.flow.entity.FlowBusinessTaskLog;
import cn.topcheer.halberd.flow.model.BatchCompleteTaskParameter;
import cn.topcheer.halberd.flow.service.FlowBusinessService;
import cn.topcheer.halberd.flow.service.FlowBusinessTaskLogService;
import cn.topcheer.halberd.flow.service.FlowProcessService;
import cn.topcheer.pms2.api.annotation.ClassInfo;
import cn.topcheer.pms2.api.annotation.MainTable;
import cn.topcheer.pms2.api.bi.BiEntBi;
import cn.topcheer.pms2.api.plan.entity.PmsAttachment;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInBiz;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInFlow;
import cn.topcheer.halberd.app.api.minidev.result.ProcessPathResult;
import cn.topcheer.pms2.biz.bi.BiEntBiService;
import cn.topcheer.pms2.biz.limit.service.impl.CommonLimitServiceImpl;
import cn.topcheer.pms2.biz.plan.service.impl.*;
import cn.topcheer.pms2.biz.pml.service.impl.enumUtil.TableEnum;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseService;
import cn.topcheer.pms2.biz.project.service.impl.enumUtil.TableMappingEnum;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.pms2.biz.sys.PmsEnterpriselimitService;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.plugin.workflow.process.model.WfProcess;
import org.springblade.plugin.workflow.process.service.IWfProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 公共 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-11-03
 */
@Slf4j
@Service
public class MiniCommonServiceImpl implements MiniCommonService {


    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private PmsTxtSave pmsTxtSave;

    @Autowired
    private SysOperationrecordService sysOperationrecordService;

    @Autowired
    private SysUserServiceImpl sysUserService;

    @Autowired
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;

    @Autowired
    private PmsAttachmentService pmsAttachmentService;

    @Autowired
    private PmsAffixService pmsAffixService;

    @Autowired
    private MiniBizHistoryVersionService miniBizHistoryVersionService;

    @Autowired
    private MiniBizDefService miniBizDefService;

    @Autowired
    private IWfProcessService processService;

    @Autowired
    private PmsEnterpriseServiceImpl pmsEnterpriseService;

    @Autowired
    private PmsPlanprojectbatchInFlowService pmsPlanprojectbatchInFlowService;

    @Autowired
    private PmsPlanprojectbatchInBizService pmsPlanprojectbatchInBizService;

    @Autowired
    private MiniBizDataInAllowUpdateService miniBizDataInAllowUpdateService;

    @Autowired
    private FlowBusinessService flowBusinessService;

    @Autowired
    private FlowBusinessTaskLogService flowBusinessTaskLogService;

    @Autowired
    private FlowProcessService flowProcessService;

    @Autowired
    private BiEntBiService biEntBiService;

    @Autowired
    private PmsPlanDeadlineService pmsPlanDeadlineService;

    @Autowired
    @Lazy
    private PmsEnterpriselimitService pmsEnterpriselimitService;

    @Autowired
    private CommonLimitServiceImpl commonLimitService;

    @Autowired
    private SysOperationLogService sysOperationLogService;


    /**
     * 获取数据对象配置
     *
     * @param dataObject   JSONObject
     * @param bizDefId     业务模板id
     * @param bizVersionId 业务版本id
     * @return JSONObject
     * @author szs
     * @since 2023-12-19
     */
    private JSONObject getDataObject(JSONObject dataObject, Long bizDefId, Long bizVersionId) {
        if (dataObject == null) {
            dataObject = new JSONObject();
        }

        // 为空时，用业务模板id和业务版本id去查询全部子项配置
        if (bizDefId != null && bizVersionId != null) {
            dataObject = JSONObject.fromObject(miniBizDefService.getDataObject(bizDefId, bizVersionId));
        }

        if (dataObject == null || dataObject.size() == 0) {
            throw new ServiceException("业务模板配置数据为空");
        }

        return dataObject;
    }


    /**
     * 判断全部Table
     *
     * @param dto MiniJudgeAllTableDTO
     * @author szs
     * @since 2023-12-19
     */
    @Override
    public void judgeAllTable(MiniJudgeAllTableDTO dto) {
        String mainid = dto.getMainid();
        String batchid = dto.getBatchid();
        JSONObject dataObject = this.getDataObject(dto.getDataObject(), dto.getBizDefId(), dto.getBizVersionId());
        dto.setDataObject(dataObject);

        for (Object key : dataObject.keySet()) {
            // 根据key获得当前对象的所有信息
            JSONObject tableObject = dataObject.getJSONObject(key.toString());

            // 如果数据库表不存在，那就跳过
            if (!tableObject.containsKey("database") || Util.isEoN(tableObject.getString("database"))) {
                continue;
            }

            // 前台表名与后台数据表映射信息 枚举对象
            TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.getString("database"));

            // 判断枚举信息中是否包含service名称
            if (Util.isEoN(tableMappingEnum) || Util.isEoN(tableMappingEnum.getServiceclassname()) || Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
                log.error("枚举信息中缺少service名称：" + tableObject.getString("database"));
                throw new ServiceException("枚举信息中缺少service名称：" + tableObject.getString("database"));
            }

            // 服务名称
            String serviceName = this.getServiceName(tableMappingEnum);

            // 主表权限判断
            if (tableMappingEnum.getClassname().isAnnotationPresent(MainTable.class)) {

                // 1. 判断当前项目的状态是否可以进行编辑（例如：状态为 空、XXX:用户填报等）
                if (dto.getIsJudgeState() == null || dto.getIsJudgeState() == 1) {
                    this.judgeMinicurrentStateSatify(mainid, serviceName);
                }

                // 2. 判断用户是否有该批次的保存权限
                this.judgeUserHasApplyAuthorityForBatch(batchid, tableObject.get("database").toString());

                // 3. 判断该项目是否属于当前用户
                this.judgeProjectBelongToUser(mainid, batchid, serviceName);

                // 4. 判断批次限制
                HashMap<String, Object> requestParameter = new HashMap<>();
                requestParameter.put("type", dto.getType());
                requestParameter.put("batchId", dto.getBatchid());
                requestParameter.put("mainId", dto.getMainid());
                commonLimitService.judgeCondition(requestParameter);

                // 退出循环
                break;
            }

        }

    }


    /**
     * 初始化全部Table
     *
     * @param dto MiniCommonDataDTO
     * @return MiniCommonDataDTO
     * @author szs
     * @since 2023-11-03
     */
    @Override
    public MiniInitAllTableDTO initAllTable(MiniInitAllTableDTO dto) {
        String mainid = dto.getMainid();
        String batchid = dto.getBatchid();
        JSONObject dataObject = this.getDataObject(dto.getDataObject(), dto.getBizDefId(), dto.getBizVersionId());
        dto.setDataObject(dataObject);

        // 是否保存
        Boolean saveFlag;


        // id集合
        List<String> ids = new ArrayList<>();

        for (Object key : dataObject.keySet()) {
            // 根据key获得当前对象的所有信息
            JSONObject tableObject = dataObject.getJSONObject(key.toString());

            // 如果数据库表不存在，那就跳过
            if (!tableObject.containsKey("database") || Util.isEoN(tableObject.getString("database"))) {
                continue;
            }

            // 前台表名与后台数据表映射信息 枚举对象
            TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.getString("database"));

            // 判断枚举信息中是否包含service名称
            if (Util.isEoN(tableMappingEnum) || Util.isEoN(tableMappingEnum.getServiceclassname()) || Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
                log.error("枚举信息中缺少service名称：" + tableObject.getString("database"));
                continue;
            }

            // 主表
            MainTable mainTable = (MainTable) tableMappingEnum.getClassname().getAnnotation(MainTable.class);

            // 表
            Table table = (Table) tableMappingEnum.getClassname().getAnnotation(Table.class);
            if (Func.isNull(table)) {
                continue;
            }

            // 判断是否保存过
            saveFlag = this.isSave(mainid, tableObject.getString("type"), table.name(), !Util.isEoN(mainTable));

            // 通过反射查看单个对象数据
            JSONObject result = this.initDataUseReflect(tableObject, mainid, batchid, saveFlag, new String[]{});

            // 字段配置信息
            JSONArray fieldArray = tableObject.has("children") ? tableObject.getJSONArray("children") : new JSONArray();

            // 移除配置信息
            tableObject.remove("children");

            // 格式化查询数据
            result.put("fetchResult", this.formatTableData(result.getJSONArray("fetchResult"), fieldArray, dto.getIsToNow()));

            // 查询数据
            JSONArray fetchResult = result.getJSONArray("fetchResult");

            // 收集ids，用于最后统一查询附件
            for (int i = 0; i < fetchResult.size(); i++) {
                if (fetchResult.getJSONObject(i).has("id")) {
                    ids.add(fetchResult.getJSONObject(i).getString("id"));
                }
            }

            // 合并数据
            tableObject.put("data", this.dealTabledata(saveFlag, tableObject, fetchResult));
            dataObject.put(key, tableObject);

        }

        // 根据主表id和子表的id集合作为sourceid的附件
        JSONObject tpAllAffixsJo = new JSONObject();
        tpAllAffixsJo.put("data", JSONArray.fromObject(JSON.toJSONString(pmsAffixService.getPmsAttachmentByIds(ids)).replaceAll("null", "\"\"")));
        dataObject.put("TPAllAffixs", tpAllAffixsJo);

        return dto;
    }


    /**
     * 处理Table数据
     *
     * @param saveFlag    主表是否保存过
     * @param tableObject Table对象
     * @param fetchResult 查询数据
     * @return Table数据
     * @author szs
     * @since 2024-01-19
     */
    private Object dealTabledata(boolean saveFlag, JSONObject tableObject, JSONArray fetchResult) {
        // 对象数据（已有默认数据）
        Object data = tableObject.get("data");

        // 处理数据，查询数据和默认数据
        if ("normal".equals(tableObject.getString("datatype"))) {
            // 对象，单条数据
            JSONObject newData = fetchResult.size() > 0 ? fetchResult.getJSONObject(0) : new JSONObject();

            if (saveFlag) {
                // 如果是主表保存过，取查询数据
                data = newData;

            } else {
                // 如果主表未保存过，那就合并数据，相同字段查询数据覆盖默认数据
                JSONObject oldData = JSONObject.fromObject(data);
                oldData.putAll(newData);
                data = oldData;
            }

        } else {
            // 数组，多条数据
            if (saveFlag) {
                // 如果是主表保存过，取查询数据
                data = fetchResult;

            } else {
                // 如果主表未保存过，那就合并数据，查询数据放在后面
                JSONArray oldData = JSONArray.fromObject(data);
                oldData.addAll(fetchResult);
                data = oldData;
            }

        }

        return data;
    }


    /**
     * 格式化Table数据
     *
     * @param array      数据
     * @param fieldArray 字段配置信息
     * @param isToNow    是否转至今，用于日期，9999 转 至今
     * @return Object
     * @author szs
     * @since 2023-12-26
     */
    private JSONArray formatTableData(JSONArray array, JSONArray fieldArray, Boolean isToNow) {
        if (fieldArray.size() == 0) {
            return array;
        }

        for (int i = 0; i < array.size(); i++) {
            JSONObject dataObject = array.getJSONObject(i);
            for (Object key : dataObject.keySet()) {
                String keyStr = key.toString();
                dataObject.put(keyStr, formatFieldData(dataObject.getString(keyStr), keyStr, fieldArray, isToNow));
            }

            array.set(i, dataObject);
        }

        return array;
    }


    /**
     * 格式化字段数据
     *
     * @param data       数据
     * @param fieldName  字段编码
     * @param fieldArray 字段配置信息
     * @param isToNow    是否转至今，用于日期，9999 转 至今
     * @return 格式化后数据
     * @author szs
     * @since 2023-12-26
     */
    private String formatFieldData(String data, String fieldName, JSONArray fieldArray, Boolean isToNow) {
        // "null" 数据处理
        if (StringUtils.isBlank(data)) {
            return "";
        }

        try {
            for (int i = 0; i < fieldArray.size(); i++) {
                JSONObject fieldObject = fieldArray.getJSONObject(i);
                if (fieldObject.getString("fieldName").equals(fieldName)) {
                    // 组件类型
                    String componentType = fieldObject.getString("componentType");

                    // 数字格式化
                    if ("cov-el-input-number".equals(componentType)) {
                        // 数字小数位数处理
                        int decaimalNum = (int) fieldObject.getOrDefault("decaimalNum", 0);

                        // 格式化小数点，采用四舍五入
                        data = new BigDecimal(data).setScale(decaimalNum, BigDecimal.ROUND_HALF_UP).toString();
                        continue;
                    }

                    // 日期格式化，对于多个日期逗号隔开的，不用进行日期格式化
                    // 一般在实体日期字段注解
                    //  @DateTimeFormat(pattern = "yyyy-MM-dd")
                    //	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
                    // 这里再次格式化下，防止万一
                    if ("cov-el-date-picker".equals(componentType) && !data.contains(",")) {
                        // 目标格式
                        String valueFormat = (String) fieldObject.getOrDefault("valueFormat", "yyyy-MM-dd");

                        // 原有格式
                        String oldFormat = "yyyy-MM-dd";
                        if (data.length() == 19) {
                            oldFormat = "yyyy-MM-dd HH:mm:ss";
                        } else if (data.length() == 21) {
                            oldFormat = "yyyy-MM-dd HH:mm:ss.S";
                        } else if (data.length() == 23) {
                            oldFormat = "yyyy-MM-dd HH:mm:ss.SSS";
                        } else if (data.length() == 26) {
                            oldFormat = "yyyy-MM-dd HH:mm:ss.SSSSSS";
                        }

                        // 长度不同可以看出格式不同，需要格式化
                        if (valueFormat.length() != data.length()) {
                            // 格式化日期
                            if (data.length() <= 10) {
                                data = LocalDate.parse(data, DateTimeFormatter.ofPattern(oldFormat)).format(DateTimeFormatter.ofPattern(valueFormat));
                            } else {
                                data = LocalDateTime.parse(data, DateTimeFormatter.ofPattern(oldFormat)).format(DateTimeFormatter.ofPattern(valueFormat));
                            }
                        }

                        // 日期，9999 转至今，用于界面显示
                        if (isToNow != null && isToNow && data.contains("9999")) {
                            data = "至今";
                        }

                    }

                    break;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }


    /**
     * 判断该表记录是否保存过
     *
     * @param mainid    主表id
     * @param type      保存数据类型
     * @param tablename 主表名称（例如：PMS_PROJECTBASE)
     * @return Boolean
     * @author szs
     * @since 2023-11-07
     */
    private Boolean isSave(String mainid, String type, String tablename, boolean isMain) {
        if (StringUtils.isBlank(tablename)) {
            return false;
        }

        // 根据sql语句查询
        if (isMain) {
            // 主表
            String sql = "select t.id from " + tablename + " t where t.id = ? ";
            String id = dbHelper.getOnlyStringValue(sql, new Object[]{mainid});

            return !Util.isEoN(id);
        } else {
            // 子表
            String sql = "select t.id from " + tablename + " t where t.mainid = ? and t.type = ?";
            String id = dbHelper.getOnlyStringValue(sql, new Object[]{mainid, type});

            return !Util.isEoN(id);
        }

    }


    /**
     * 通过反射查看单个对象数据
     *
     * @param tableObject  前台传来的完整对象
     * @param mainid       主表id
     * @param batchid      批次id
     * @param saveFlag     是否保存过
     * @param initParamArr 特殊初始化参数
     * @return JSONObject
     * @author szs
     * @since 2023-11-07
     */
    private JSONObject initDataUseReflect(JSONObject tableObject, String mainid, String batchid, Boolean saveFlag, String[] initParamArr) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        result.put("fetchResult", new ArrayList<>());

        // 前台表名与后台数据表映射信息 枚举对象
        TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.getString("database"));

        // 判断枚举信息中是否包含service名称
        if (Util.isEoN(tableMappingEnum) || Util.isEoN(tableMappingEnum.getServiceclassname()) || Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
            log.error("枚举信息中缺少service名称：" + tableObject.getString("database"));
            return result;
        }

        // 服务名称
        String serviceName = this.getServiceName(tableMappingEnum);

        // 保存类型
        String type = tableObject.getString("type");

        try {
            // 动态执行Service方法
            List<Map> fetchResult = (List<Map>) Util.springInvokeMethod(serviceName, "genericFetchFun", new Object[]{mainid, type, saveFlag, batchid, tableObject, initParamArr});

            // 添加执行结果
            result.put("fetchResult", fetchResult);
            result.put("success", true);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return result;
        }

        return result;
    }


    /**
     * 保存全部Table
     *
     * @param dto     MiniSaveAllTableDTO
     * @param request HttpServletRequest
     * @author szs
     * @since 2023-11-07
     */
    @Override
    public void saveAllTable(MiniSaveAllTableDTO dto, HttpServletRequest request) {

        // 保存主表
        saveMainTable(dto, request);

        // 保存子表
        saveTable(dto);

        // 添加历史版本
        addHistoryVersion(dto);

    }


    /**
     * 添加历史版本
     *
     * @param dto MiniSaveAllTableDTO
     * @author szs
     * @since 2024-02-05
     */
    private void addHistoryVersion(MiniSaveAllTableDTO dto) {
        if (dto.getIsAddHistoryVersion() == null || dto.getIsAddHistoryVersion() != 1) {
            return;
        }

        // 数据格式化处理
        if (dto.getBizDefId() != null && dto.getBizVersionId() != null) {
            // 获取配置信息
            JSONObject dataObject = this.getDataObject(null, dto.getBizDefId(), dto.getBizVersionId());

            // 遍历处理
            for (Object key : dataObject.keySet()) {
                String keyStr = key.toString();
                if (!dto.getDataObject().containsKey(keyStr)) {
                    continue;
                }

                // 根据key获得当前对象的所有信息
                JSONObject tableObject = dataObject.getJSONObject(keyStr);

                // 数据类型
                String datatype = tableObject.getString("datatype");


                // 字段配置信息
                JSONArray fieldArray = tableObject.has("children") ? tableObject.getJSONArray("children") : new JSONArray();

                // 数据
                JSONArray data = new JSONArray();
                if ("normal".equals(datatype)) {
                    data.add(dto.getDataObject().getJSONObject(keyStr).getJSONObject("data"));
                } else {
                    data = dto.getDataObject().getJSONObject(keyStr).getJSONArray("data");
                }

                if (data.size() == 0) {
                    continue;
                }

                // 格式化查询数据
                this.formatTableData(data, fieldArray, true);

                // 数据回赋
                if ("normal".equals(datatype)) {
                    dto.getDataObject().getJSONObject(keyStr).put("data", data.getJSONObject(0));
                } else {
                    dto.getDataObject().getJSONObject(keyStr).put("data", data);
                }

            }
        }

        // 保存历史版本数据
        miniBizHistoryVersionService.addHistoryVersion(dto.getMainid(), JSON.toJSONString(dto.getDataObject()));
    }


    /**
     * 保存主表
     *
     * @param dto     MiniSaveAllTableDTO
     * @param request HttpServletRequest
     * @author szs
     * @since 2023-11-07
     */
    private void saveMainTable(MiniSaveAllTableDTO dto, HttpServletRequest request) {
        String mainid = dto.getMainid();
        String batchid = dto.getBatchid();
        String mold = dto.getMold();
        String flowtype = dto.getFlowtype();
        String planprojectType = dto.getType();
        JSONObject dataObject = dto.getDataObject();

        // 主表保存是否成功
        boolean mainTableSaveFlag = false;

        // 先保存主表，主表保存成功后，再去保存子表
        for (Object key : dataObject.keySet()) {
            // 根据key获得当前对象的所有信息
            JSONObject tableObject = dataObject.getJSONObject(key.toString());

            // 如果数据库表不存在，那就跳过
            if (!tableObject.containsKey("database") || Util.isEoN(tableObject.getString("database"))) {
                continue;
            }

            // 批次业务（业务类型）
            tableObject.put("planprojectType", planprojectType);

            // 前台表名与后台数据表映射信息 枚举对象
            TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.getString("database"));

            // 判断枚举信息中是否包含service名称
            if (Util.isEoN(tableMappingEnum) || Util.isEoN(tableMappingEnum.getServiceclassname()) || Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
                continue;
            }

            // 主表
            MainTable mainTable = (MainTable) tableMappingEnum.getClassname().getAnnotation(MainTable.class);
            if (!Util.isEoN(mainTable)) {
                String modulename = "未定义类型";

                ClassInfo classInfo = (ClassInfo) tableMappingEnum.getClassname().getAnnotation(ClassInfo.class);
                if (!Util.isEoN(classInfo)) {
                    modulename = classInfo.module().getName();
                }

                // 主表数据处理，可能存在主表数据为空的情况，为空时，至少需要空对象
                if (!tableObject.has("data")) {
                    tableObject.put("data", new JSONObject());
                }

                // 操作记录
                sysOperationrecordService.commonSaveOperation(request, mainid, "申报保存", "批次id:" + batchid + "项目id:" + mainid);

                // 把当前数据添加到txt文件中
                String savetype = "对应表：" + tableObject.get("database") + "，类别：" + tableObject.get("type");
                pmsTxtSave.saveTxt(mainid, tableObject, modulename + "存前数据", savetype);

                // 通过反射保存单个对象数据
                mainTableSaveFlag = this.saveDataUseReflect(tableObject, mainid, batchid, mold, flowtype, dto.getIsJudgeState());
                break;
            }
        }

        if (!mainTableSaveFlag) {
            throw new ServiceException("主表保存失败");
        }

    }


    /**
     * 保存子表
     *
     * @param dto MiniSaveAllTableDTO
     * @author szs
     * @since 2023-11-07
     */
    private void saveTable(MiniSaveAllTableDTO dto) {
        String mainid = dto.getMainid();
        String batchid = dto.getBatchid();
        String mold = dto.getMold();
        String flowtype = dto.getFlowtype();
        JSONObject dataObject = dto.getDataObject();

        // 循环保存子表
        for (Object key : dataObject.keySet()) {
            // 根据key获得当前对象的所有信息
            JSONObject tableObject = dataObject.getJSONObject(key.toString());

            // 如果数据库表不存在，那就跳过
            if (!tableObject.containsKey("database") || Util.isEoN(tableObject.getString("database"))) {
                continue;
            }

            // 前台表名与后台数据表映射信息 枚举对象
            TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.getString("database"));

            // 判断枚举信息中是否包含service名称
            if (Util.isEoN(tableMappingEnum) || Util.isEoN(tableMappingEnum.getServiceclassname()) || Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
                continue;
            }

            // 主表
            MainTable mainTable = (MainTable) tableMappingEnum.getClassname().getAnnotation(MainTable.class);
            if (Util.isEoN(mainTable)) {
                String modulename = "未定义类型";

                ClassInfo classInfo = (ClassInfo) tableMappingEnum.getClassname().getAnnotation(ClassInfo.class);
                if (!Util.isEoN(classInfo)) {
                    modulename = classInfo.module().getName();
                }

                // 把当前数据添加到txt文件中
                String savetype = "对应表：" + tableObject.get("database") + "，类别：" + tableObject.get("type");
                pmsTxtSave.saveTxt(mainid, tableObject, modulename + "存前数据", savetype);

                // 通过反射保存单个对象数据
                this.saveDataUseReflect(tableObject, mainid, batchid, mold, flowtype, dto.getIsJudgeState());
            }
        }

    }


    /**
     * 通过反射保存单个对象数据
     *
     * @param tableObject  当前需保存的对象
     * @param mainid       主表id
     * @param batchid      批次id
     * @param mold         类型
     * @param flowtype     流程类型
     * @param isJudgeState 是否判断流转状态（0否，1是）,为空默认需要判断1
     * @return boolean
     * @author szs
     * @since 2023-11-07
     */
    private boolean saveDataUseReflect(JSONObject tableObject, String mainid, String batchid, String mold, String flowtype, Integer isJudgeState) {
        boolean result = false;

        // 前台表名与后台数据表映射信息 枚举对象
        TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.getString("database"));

        // 判断枚举信息中是否包含service名称
        if (Util.isEoN(tableMappingEnum) || Util.isEoN(tableMappingEnum.getServiceclassname()) || Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
            log.error("枚举信息中缺少service名称：" + tableObject.getString("database"));
            return false;
        }

        // 服务名称
        String serviceName = this.getServiceName(tableMappingEnum);

        // 保存类型
        String type = tableObject.getString("type");
        // 数据格式类型（normal普通对象Object等）
        String datatype = tableObject.getString("datatype");

        // 通过数据类型将前台传来的数据转换转换为JSONArray数组类型
        JSONArray data = this.getDataFromJson(tableObject, datatype);

        // 主表权限判断
        if (tableMappingEnum.getClassname().isAnnotationPresent(MainTable.class)) {

            if (isJudgeState == null || isJudgeState == 1) {
                // 判断当前项目的状态是否可以进行编辑（例如：状态为 空、XXX:用户填报等）
                judgeMinicurrentStateSatify(mainid, serviceName);
            }

            // 判断用户是否有该批次的保存权限
            judgeUserHasApplyAuthorityForBatch(batchid, tableObject.get("database").toString());

            // 判断该项目是否属于当前用户
            judgeProjectBelongToUser(mainid, batchid, serviceName);

        }

        try {
            // 动态执行Service方法
            Util.springInvokeMethod(serviceName, "genericSaveFun", new Object[]{mainid, type, data, batchid, flowtype, mold, tableObject});
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }

        return result;
    }


    /**
     * 获取服务名称
     *
     * @param tableMappingEnum TableMappingEnum
     * @return 服务名称
     * @author szs
     * @since 2023-12-19
     */
    private String getServiceName(TableMappingEnum tableMappingEnum) {
        // 存储字符串型的service类名，例如：Pms_ProjectbaseService
        String tempServiceNameStr;
        // serveic全路径名称（包名 + 类名），例如：Pms_ProjectbaseService
        String servicePathNameStr;

        //判断枚举信息中是否包含service名称
        if (!Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
            tempServiceNameStr = tableMappingEnum.getServiceclassname().getSimpleName();
            servicePathNameStr = tableMappingEnum.getServiceclassname().getName();
        } else {
            tempServiceNameStr = (tableMappingEnum.getClassname().getSimpleName() + "Service");
            servicePathNameStr = PmsProjectbaseService.class.getPackage().toString().substring(8) + "." + tempServiceNameStr;
        }

        Class clazz;
        try {
            // 通过反射创建
            clazz = Class.forName(servicePathNameStr);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            throw new ServiceException(e.getMessage());
        }

        // 判断是否通过反射已经成功创建对象
        if (Util.isEoN(clazz)) {
            log.error("创建对象失败：" + servicePathNameStr);
            throw new ServiceException("创建对象失败：" + servicePathNameStr);
        }

        // service Bean对象的对象名称,获取中定义的spring的service注解的名称
        String serviceName = ((Service) (clazz.getAnnotation(Service.class))).value();
        // 判断是否已为service名称是否存在
        if (Util.isEoN(serviceName)) {
            // 如不存在使用默认的类名首字母小写作为service名称
            serviceName = Character.toLowerCase(tempServiceNameStr.charAt(0)) + tempServiceNameStr.substring(1);
        }

        return serviceName;
    }


    /**
     * 判断当前项目的状态是否可以进行编辑（例如：状态为 空、XXX:用户填报等）
     *
     * @param mainid      主表id
     * @param serviceName 服务名称
     * @author szs
     * @since 2023-11-07
     */
    private void judgeMinicurrentStateSatify(String mainid, String serviceName) {
        try {
            // 动态执行Service方法
            JSONObject result = JSONObject.fromObject(Util.springInvokeMethod(serviceName, "judgeMinicurrentStateSatify", new Object[]{mainid}));

            // 判断用户是否有该批次的保存权限
            if (!result.getBoolean("success")) {
                throw new ServiceException(result.getString("errMsg"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }

    }


    /**
     * 判断用户是否拥有对此批次申报填写的权限
     *
     * @param batchid    批次id
     * @param dabasename 数据表
     * @author szs
     * @since 2023-11-07
     */
    private void judgeUserHasApplyAuthorityForBatch(String batchid, String dabasename) {
        // 当前登录用户id
        String userid = AuthUtil.getUserId();

        // 用户未登录，那就不用判断用户权限
        if (StringUtils.isBlank(userid)) {
            return;
        }

        // 未登录（用户注册及单位注册）
        if (Util.isEoN(userid)) {
            if ("DATAWAREHOUSE-USER-REGISTER".equals(batchid) || "DATAWAREHOUSE-UNIT-REGISTER".equals(batchid)) {
                return;
            }
        }

        // 已登录，管理员 或 KJTPZRY-FDW123SD123123 具备权限
        if ("admin".equals(userid) || "KJTPZRY-FDW123SD123123".equals(userid)) {
            return;
        }

        // 获取项目批次
        PmsPlanprojectbatch pmsPlanprojectbatch = pmsPlanprojectbatchService.findById(batchid);
        if (pmsPlanprojectbatch == null) {
            throw new ServiceException("当前项目批次不存在：" + batchid);
        }

        // 项目id
        String planprojectid = pmsPlanprojectbatch.getPmsPlanproject().getId();

        // 搜索结果最多两条，按照小批次id不为空，为空的顺序进行排序，默认取第一条即可
        String limitSql = "select l.* from PMS_BATCH_PORT_LIMIT l where l.planprojectid = ? and (l.planprojectbatchid = ? or l.planprojectbatchid is null) and (',' || l.DATABASENAMES || ',' like ? or l.DATABASENAMES is null) order by l.planprojectbatchid nulls last, l.DATABASENAMES nulls last ";
        List<Map> limitList = dbHelper.getRows(limitSql, new Object[]{planprojectid, batchid, "%," + dabasename + ",%"});
        if (!Util.isEoN(limitList) && limitList.size() == 0) {
            return;
        }

        if (!Util.isEoN(limitList) && limitList.size() > 0) {
            String userids = limitList.get(0).get("userids").toString();

            if (Util.isEoN(userids)) {
                // 没有配置针对用户userid的设置，只要有一个角色，那就是有权限
                String roleids = limitList.get(0).get("roleids").toString();
                SysUser user = sysUserService.findById(userid);
                if (user == null) {
                    throw new ServiceException("当前系统用户不存在：" + userid);
                }

                List<String> roleIds = user.getRoleIds();
                for (String roleId : roleIds) {
                    if ((";" + roleids + ";").contains(";" + roleId + ";")) {
                        return;
                    }
                }

            } else {
                // 根据用户id来判断， 只要有一个用户，那就是有权限
                if ((";" + userids + ";").contains(";" + userid + ";")) {
                    return;
                }
            }
        }

        throw new ServiceException("当前用户角色没有保存的权限，请联系系统维护员：" + Util.linkNumber);
    }


    /**
     * 判断用户是否拥有对此批次申报填写的权限
     *
     * @param mainid      主表id
     * @param batchid     批次id
     * @param serviceName 服务名称
     * @author szs
     * @since 2023-11-07
     */
    private void judgeProjectBelongToUser(String mainid, String batchid, String serviceName) {
        // 当前登录用户id
        String userid = AuthUtil.getUserId();

        // 用户未登录，那就不用判断用户权限
        if (StringUtils.isBlank(userid)) {
            return;
        }

//        // 未登录（用户注册及单位注册）
//        if (Util.isEoN(userid)) {
//            if ("DATAWAREHOUSE-USER-REGISTER".equals(batchid) || "DATAWAREHOUSE-UNIT-REGISTER".equals(batchid)) {
//                return;
//            }
//        }

        // 特殊人员
        if ("admin".equals(userid) || "KJTPZRY-FDW123SD123123".equals(userid)) {
            return;
        }

        // 获取系统用户
        SysUser user = this.sysUserService.findById(userid);
        if (user == null) {
            throw new ServiceException("当前系统用户不存在：" + userid);
        }

        if ("DATAWAREHOUSE-UNIT-REGISTER".equals(batchid) && user.getRoleIds().contains("2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487")) {
            return;
        }

        try {
            // 动态执行Service方法
            String declarantid = (String) Util.springInvokeMethod(serviceName, "getDeclarantid", new Object[]{mainid});

            // 具体业务
            if (!Util.isEoN(declarantid) && !userid.equals(declarantid)) {
                throw new ServiceException("当前项目不属于该用户，没有权限保存，请联系系统维护员：" + Util.linkNumber);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }

    }


    /**
     * 通过数据类型将前台传来的数据转换转换为JSONArray数组类型
     *
     * @param tableObject Table数据
     * @param datatype    数据格式类型（normal普通对象Object等）
     * @return JSONArray
     * @author szs
     * @since 2023-11-07
     */
    private JSONArray getDataFromJson(JSONObject tableObject, String datatype) {
        JSONArray data = new JSONArray();
        if (!tableObject.has("data")) {
            return data;
        }

        try {
            if ("normal".equals(datatype)) {
                JSONObject datanewobject = tableObject.getJSONObject("data");
                data.add(datanewobject);
            } else {
                data = tableObject.getJSONArray("data");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }


    /**
     * 获取批次绑定流程定义Key
     *
     * @param processDefKey 流程定义可以
     * @param batchId       批次id
     * @param type          业务类型
     * @return String
     * @author szs
     * @date 2023-11-22
     */
    private String getBatchInFlow(String processDefKey, String batchId, String type) {
        if (StringUtils.isNotBlank(processDefKey)) {
            return processDefKey;
        }

        // 获取小批次关联流程
        List<PmsPlanprojectbatchInFlow> list = pmsPlanprojectbatchInFlowService.getAllList(batchId, type);
        if (list.size() == 0) {
            throw new ServiceException("该申报批次还未绑定审批流程：" + batchId);
        }

        return list.get(0).getProcessDefKey();
    }


    /**
     * 获取当前用户单位
     *
     * @return PmsEnterprise
     * @author szs
     * @date 2023-11-22
     */
    private PmsEnterprise getCurrentUserEnterprise() {
        // 当前用户id
        String userId = AuthUtil.getUserId();
        if (StringUtils.isBlank(userId)) {
            throw new ServiceException("用户未登录");
        }

        // 获取用户
        SysUser sysUser = sysUserService.findById(userId);
        if (sysUser == null) {
            throw new ServiceException("用户不存在：" + userId);
        }

        // 单位
        List<PmsEnterprise> list = sysUser.getEnterPriseList();
        if (list.size() == 0) {
            throw new ServiceException("请联系单位管理员补全单位信息");
        }

        return list.get(0);
    }


    /**
     * 获取科技厅单位
     *
     * @return PmsEnterprise
     * @author szs
     * @date 2023-11-22
     */
    private PmsEnterprise getStaEnterprise() {
        // TODO 形审单位，省科学技术厅 具体业务具体分析
        String id = "98C57F262CB74E12AAC31C9D37533A17";
        PmsEnterprise enterprise = pmsEnterpriseService.findById(id);
        if (enterprise == null) {
            throw new ServiceException("省科学技术厅：" + id);
        }

        return enterprise;
    }


    /**
     * 获取推荐单位
     *
     * @param mainTableServiceName 主表服务名称
     * @param mainId               主表id
     * @return JSONObject
     * @author szs
     * @date 2024-03-08
     */
    private JSONObject getCasemanagement(String mainTableServiceName, String mainId) {
        JSONObject object = new JSONObject();
        try {
            // 动态执行Service方法
            object = (JSONObject) Util.springInvokeMethod(mainTableServiceName, "getCasemanagement", new Object[]{mainId});
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 数据处理，避免数据为空，导致KEY不存在
        object.put("citycasemanagementid", object.getOrDefault("citycasemanagementid", ""));
        object.put("citycasemanagement", object.getOrDefault("citycasemanagement", ""));
        object.put("countycasemanagementid", object.getOrDefault("countycasemanagementid", ""));
        object.put("countycasemanagement", object.getOrDefault("countycasemanagement", ""));

        // 是否有二级审核
        object.put("has_two_level_unit", StringUtil.isNotBlank(object.getString("countycasemanagementid")));

        // 是否有单位内审，一级推荐单位和二级推荐单位相同
        object.put("has_nb_unit", !object.getString("citycasemanagementid").equals(object.getString("countycasemanagementid")));

        return object;
    }


    /**
     * 获取单位
     *
     * @param enterpriseId 单位id
     * @return PmsEnterprise
     * @author szs
     * @date 2024-03-08
     */
    private PmsEnterprise getEnterprise(String enterpriseId) {
        if (StringUtil.isBlank(enterpriseId)) {
            return null;
        }

        return pmsEnterpriseService.findById(enterpriseId);
    }


    /**
     * 获取流程路径
     *
     * @param dto ProcessPathDTO
     * @return ProcessPathVO
     * @author szs
     * @date 2023-11-22
     */
    @Override
    public List<ProcessPathResult> getProcessPath(ProcessPathDTO dto) {
        List<ProcessPathResult> list = new ArrayList<>();
        list.add(new ProcessPathResult("", "上报", "", "", ""));

        // 获取批次绑定流程定义key
        String processDefKey = getBatchInFlow(dto.getProcessDefKey(), dto.getBatchId(), dto.getType());

        // 获取批次绑定业务模板
        PmsPlanprojectbatchInBiz batchInBiz = pmsPlanprojectbatchInBizService.getPmsPlanprojectbatchInBiz(dto.getBatchId(), dto.getType());
        if (batchInBiz == null) {
            throw new ServiceException("该申报批次还未绑定业务模板：" + dto.getBatchId());
        }

        // 主表服务名称
        String mainTableServiceName = this.getMainTableServiceName(batchInBiz.getMiniBizDefId(), batchInBiz.getMiniBizVersionId());

        // 获取推荐单位
        JSONObject casemanagement = this.getCasemanagement(mainTableServiceName, dto.getMainId());

        // 二级归口单位
        PmsEnterprise countycasemanagement = this.getEnterprise(casemanagement.getString("countycasemanagementid"));

        // 一级归口单位
        PmsEnterprise citycasemanagement = this.getEnterprise(casemanagement.getString("citycasemanagementid"));

        // 科技厅单位
        PmsEnterprise staEnterprise = this.getStaEnterprise();

        // 获取当前用户所属单位
        PmsEnterprise enterprise = this.getCurrentUserEnterprise();

        // 流程定义KEY
        switch (processDefKey) {
            case "process_application_project":
                // 申报项目审核流程
                // 二级归口
                if (countycasemanagement != null) {
                    list.add(new ProcessPathResult(countycasemanagement.getId(), countycasemanagement.getName(), countycasemanagement.getLinkman(), countycasemanagement.getMobile(), countycasemanagement.getTelephone()));
                }

                // 一级归口
                if (citycasemanagement != null && casemanagement.getBoolean("has_two_level_unit")) {
                    list.add(new ProcessPathResult(citycasemanagement.getId(), citycasemanagement.getName(), citycasemanagement.getLinkman(), citycasemanagement.getMobile(), citycasemanagement.getTelephone()));
                }

                // 形审单位
                list.add(new ProcessPathResult(staEnterprise.getId(), staEnterprise.getName(), staEnterprise.getLinkman(), staEnterprise.getMobile(), staEnterprise.getTelephone()));
                break;

            case "process_DATAWAREHOUSE_USER":
                // 个人完善信息审核流程
                // 当前单位
                list.add(new ProcessPathResult(enterprise.getId(), enterprise.getName(), enterprise.getLinkman(), enterprise.getMobile(), enterprise.getTelephone()));
                break;

            case "process_DATAWAREHOUSE_UNIT":
                // 法人完善信息审核流程
                // 当前单位
                enterprise = getUserCountyEnterprise();
                list.add(new ProcessPathResult(enterprise.getId(), enterprise.getName(), enterprise.getLinkman(), enterprise.getMobile(), enterprise.getTelephone()));
                break;

            case "process_PRIZE_USER":
                // 奖励个人审核
                // 受理单位
                list.add(new ProcessPathResult(staEnterprise.getId(), staEnterprise.getName(), staEnterprise.getLinkman(), staEnterprise.getMobile(), staEnterprise.getTelephone()));
                break;

            case "process_SOFT_SCIENCE":
                // 软科学审核
            case "process_SCIENTIST_WORKSTATION":
                // 科学家工作站
                // 内部单位
                if (countycasemanagement != null && casemanagement.getBoolean("has_nb_unit")) {
                    list.add(new ProcessPathResult(countycasemanagement.getId(), countycasemanagement.getName(), countycasemanagement.getLinkman(), countycasemanagement.getMobile(), countycasemanagement.getTelephone()));
                }

                // 归口单位
                if (citycasemanagement != null) {
                    // 内部单位和归口单位，如果相同，那就只需要审核一次，也就是一个单位即可
                    list.add(new ProcessPathResult(citycasemanagement.getId(), citycasemanagement.getName(), citycasemanagement.getLinkman(), citycasemanagement.getMobile(), citycasemanagement.getTelephone()));
                }

                // 受理单位
                list.add(new ProcessPathResult(staEnterprise.getId(), staEnterprise.getName(), staEnterprise.getLinkman(), staEnterprise.getMobile(), staEnterprise.getTelephone()));
                break;

            default:
                break;
        }


        return list;
    }


    /**
     * 发起流程
     *
     * @param dto           StartProcessDTO
     * @param processDefKey 流程定义KEY
     * @author whq
     * @date 2024-03-06
     */
    public void startProcessChange(StartProcessDTO dto, String processDefKey) {

        startProcess(dto, processDefKey);

    }


    /**
     * 发起流程
     *
     * @param dto StartProcessDTO
     * @author szs
     * @date 2023-11-22
     */
    @Override
    public void startProcess(StartProcessDTO dto) {

        // 判断申报时间，时间编码固定，不能随便更改
        JSONObject object = pmsPlanDeadlineService.judgeTime(dto.getBatchId(), "applicationPeriod");
        if (!object.getBoolean("result")) {
            throw new ServiceException(object.getString("note"));
        }

        // 获取批次绑定流程定义key
        String processDefKey = this.getBatchInFlow(dto.getProcessDefKey(), dto.getBatchId(), dto.getType());

        // 获取业务流程中间表数据
        FlowBusiness flowBusiness = this.getFlowBusiness(dto.getMainId(), processDefKey);

        if (flowBusiness != null) {
            // 完成任务
            this.completeTask(flowBusiness);

        } else {
            // 发起流程
            this.startProcess(dto, processDefKey);

        }

    }


    /**
     * 完成任务
     *
     * @param flowBusiness FlowBusiness
     * @author szs
     * @since 2023-12-26
     */
    private void completeTask(FlowBusiness flowBusiness) {
        // 获取当前用户
        SysUser sysUser = sysUserService.findById(AuthUtil.getUserId());
        if (sysUser == null) {
            throw new ServiceException("当前登录用户不存在");
        }

        // 判断权限
        if (!checkTaskUser(flowBusiness, sysUser)) {
            throw new ServiceException("没有审核权限");
        }

        // 完成任务
        WfProcess process = new WfProcess();
        process.setProcessInstanceId(flowBusiness.getProcessInstanceId());
        process.setTaskId(flowBusiness.getTaskId());
        process.setPass(true);
        processService.completeTask(process);

    }


    /**
     * 发起流程
     *
     * @param dto           StartProcessDTO
     * @param processDefKey 流程定义KEY
     * @author szs
     * @since 2023-12-26
     */
    private void startProcess(StartProcessDTO dto, String processDefKey) {
        // 获取批次绑定业务模板
        PmsPlanprojectbatchInBiz batchInBiz = pmsPlanprojectbatchInBizService.getPmsPlanprojectbatchInBiz(dto.getBatchId(), dto.getType());
        if (batchInBiz == null) {
            throw new ServiceException("该申报批次还未绑定业务模板：" + dto.getBatchId());
        }

        // 主表服务名称
        String mainTableServiceName = this.getMainTableServiceName(batchInBiz.getMiniBizDefId(), batchInBiz.getMiniBizVersionId());

        // 组装数据
        Map<String, Object> body = new HashMap<>();
        body.put("processDefKey", processDefKey);
        body.put("business_code", processDefKey);
        body.put("business_id", dto.getMainId());
        body.put("business_title", this.getMainTableProjectName(dto.getMainId(), mainTableServiceName));

        // 获取推荐单位
        JSONObject casemanagement = this.getCasemanagement(mainTableServiceName, dto.getMainId());

        // 流程表达式参数（一般都是判断条件）
        switch (processDefKey) {
            case "process_application_project":
                // 申报项目审核流程
                body.put("has_two_level_unit", casemanagement.getBoolean("has_two_level_unit"));
                break;

            case "process_SOFT_SCIENCE":
                // 软科学审核
            case "process_SCIENTIST_WORKSTATION":
                // 科学家工作站
                body.put("has_nb_unit", casemanagement.getBoolean("has_nb_unit"));
                break;
            default:
                break;
        }


        // 额外参数
        HashMap<String, String> startVars = new HashMap<>();
        startVars.put("id", batchInBiz.getMiniBizDefId().toString());
        startVars.put("bizVersionId", batchInBiz.getMiniBizVersionId().toString());
        startVars.put("type", "2");
        startVars.put("batchid", dto.getBatchId());
        startVars.put("mainid", dto.getMainId());
        startVars.put("gridType", dto.getType());
        startVars.put("mainTableServiceName", mainTableServiceName);
        body.put("startVars", startVars);

        // 发起流程
        processService.startProcessInstanceByKey(processDefKey, body);

    }


    /**
     * 获取主表项目名称
     *
     * @param mainId               主表id
     * @param mainTableServiceName 主表服务名称
     * @return 主表项目名称
     * @author szs
     * @since 2023-12-26
     */
    private String getMainTableProjectName(String mainId, String mainTableServiceName) {
        String mainTableProjectName = "";
        try {
            // 动态执行Service方法
            mainTableProjectName = (String) Util.springInvokeMethod(mainTableServiceName, "getProjectName", new Object[]{mainId});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mainTableProjectName;
    }


    /**
     * 获取主表服务名称
     *
     * @param bizDefId     业务模板id
     * @param bizVersionId 业务版本id
     * @return String
     * @author szs
     * @since 2023-12-19
     */
    private String getMainTableServiceName(Long bizDefId, Long bizVersionId) {
        // 为空时，用业务模板id和业务版本id去查询全部子项配置
        if (bizDefId == null || bizVersionId == null) {
            throw new ServiceException("绑定业务模板数据异常");
        }

        // 获取数据对象配置
        JSONObject dataObject = this.getDataObject(null, bizDefId, bizVersionId);

        // 主表服务名称
        String mainTableServiceName = "";
        for (Object key : dataObject.keySet()) {
            // 根据key获得当前对象的所有信息
            JSONObject tableObject = dataObject.getJSONObject(key.toString());

            // 如果数据库表不存在，那就跳过
            if (!tableObject.containsKey("database") || Util.isEoN(tableObject.getString("database"))) {
                continue;
            }

            // 前台表名与后台数据表映射信息 枚举对象
            TableMappingEnum tableMappingEnum = TableMappingEnum.getEnumByTablename(tableObject.getString("database"));

            // 判断枚举信息中是否包含service名称
            if (Util.isEoN(tableMappingEnum) || Util.isEoN(tableMappingEnum.getServiceclassname()) || Util.isEoN(tableMappingEnum.getServiceclassname().getSimpleName())) {
                continue;
            }

            // 主表权限判断
            if (tableMappingEnum.getClassname().isAnnotationPresent(MainTable.class)) {
                mainTableServiceName = this.getServiceName(tableMappingEnum);
                break;
            }
        }

        if (StringUtils.isBlank(mainTableServiceName)) {
            throw new ServiceException("业务模板配置数据异常：未配置主表");
        }

        return mainTableServiceName;
    }


    /**
     * 获取主表服务名称
     *
     * @param type 业务类型
     * @return String
     * @author szs
     * @since 2024-03-26
     */
    private String getMainTableServiceName(String type) {

        return TableMappingEnum.getEnumByClassname(TableEnum.valueOf(type).getClassname()).getServiceclassname().getSimpleName();
    }


    /**
     * 删除附件
     *
     * @param attachId 附件id
     * @author szs
     * @date 2023-11-24
     */
    @Override
    public void deleteFileByAttachId(String attachId) {
        // 查询附件
        PmsAttachment data = pmsAttachmentService.findById(attachId);
        if (data == null) {
            throw new ServiceException("查无此数据");
        }

        // 删除
        boolean bo = pmsAttachmentService.deleteById(attachId);
        if (!bo) {
            throw new ServiceException("删除失败");
        }

    }


    /**
     * 获取流程流转列表
     *
     * @param dto ProcessTransferDTO
     * @return List
     * @author szs
     * @date 2023-11-30
     */
    @Override
    public List<ProcessTransferResult> getProcessTransferList(ProcessTransferDTO dto) {
        List<ProcessTransferResult> list = new ArrayList<>();

        // 获取业务任务日志列表
        QueryWrapper<FlowBusinessTaskLog> qw = new QueryWrapper<>();
        qw.eq("business_id", dto.getMainId());
        qw.eq(StringUtils.isNotBlank(dto.getProcessDefKey()), "process_def_key", dto.getProcessDefKey());
        qw.eq("change_type", "taskCompleted");
        List<FlowBusinessTaskLog> businessTaskLogs = flowBusinessTaskLogService.list(qw);

        // 存储用户信息，避免重复查询
        HashMap<String, SysUser> useMap = new HashMap<>();

        // 遍历组装数据
        for (FlowBusinessTaskLog taskLog : businessTaskLogs) {
            ProcessTransferResult result = new ProcessTransferResult();

            // 获取用户
            if (StringUtils.isNotBlank(taskLog.getTaskAssignee())) {
                // 获取用户信息
                SysUser sysUser = useMap.getOrDefault(taskLog.getTaskAssignee(), sysUserService.findById(taskLog.getTaskAssignee()));
                useMap.put(taskLog.getTaskAssignee(), sysUser);
                if (sysUser != null) {
                    result.setOperationUserName(sysUser.getName());
                    result.setEnterpriseName(sysUser.getEnterPriseName());
                }
            }

            // {"taskResult":"wf_pass_submit","taskResultName":"提交","taskComment":"提交"}
            String changeComment = taskLog.getChangeComment();
            if (StringUtils.isBlank(changeComment) || !Util.isJSONObject(changeComment)) {
                // 为空或者非JSON
                result.setOperationResult(ProccessUtil.getOperationName(taskLog.getChangeResult()));
                result.setOperationOpinion(taskLog.getChangeComment());

            } else {
                // JSON
                JSONObject object = JSONObject.fromObject(changeComment);
                result.setOperationResult(object.getString("taskResultName"));
                result.setOperationOpinion(object.getString("taskComment"));
            }

            result.setOperationNode(taskLog.getTaskName());
            result.setOperationDate(taskLog.getChangeTime());
            list.add(result);
        }

        return list;
    }


    /**
     * 获取流程流转数据
     *
     * @param dto ProcessTransferDTO
     * @return ProcessTransferDataResult
     * @author szs
     * @date 2023-12-21
     */
    @Override
    public ProcessTransferDataResult getProcessTransferData(ProcessTransferDTO dto) {
        ProcessTransferDataResult result = new ProcessTransferDataResult();

        // 判断是否有进行中的审核流程
        FlowBusiness flowBusiness = this.getFlowBusiness(dto.getMainId(), dto.getProcessDefKey());

        result.setTaskName(flowBusiness != null ? flowBusiness.getTaskName() : "无");
        result.setTransferList(getProcessTransferList(dto));

        return result;
    }


    /**
     * 获取业务流程中间表数据
     *
     * @param businessId    业务id
     * @param processDefKey 流程定义KEY
     * @return FlowBusiness
     * @author szs
     * @date 2023-12-21
     */
    private FlowBusiness getFlowBusiness(String businessId, String processDefKey) {
        QueryWrapper<FlowBusiness> qw = new QueryWrapper<>();
        qw.eq("business_id", businessId);
        qw.eq(StringUtils.isNotBlank(processDefKey), "process_def_key", processDefKey);
        qw.isNull("end_time");
        qw.orderByDesc("start_time");
        qw.last(" LIMIT 1 ");

        return flowBusinessService.getOne(qw);
    }


    /**
     * 判断任务
     *
     * @param dto JudgeTaskDTO
     * @return List
     * @author szs
     * @date 2023-12-20
     */
    @Override
    public List<JudgeTaskResult> judgeTask(JudgeTaskDTO dto) {
        List<JudgeTaskResult> list = new ArrayList<>();

        if (dto.getBusinessTaskList() == null || dto.getBusinessTaskList().size() == 0) {
            return list;
        }

        // 获取第一个业务任务
        BusinessTaskDTO firstBusinessTask = dto.getBusinessTaskList().get(0);

        // 当前登录人
        String userId = AuthUtil.getUserId();

        // 获取当前用户
        SysUser sysUser = sysUserService.findById(userId);
        if (sysUser == null) {
            throw new ServiceException("当前登录用户不存在");
        }

        // 获取批次绑定流程定义key
        String processDefKey = getBatchInFlow(firstBusinessTask.getMiniCurrentProcessDefKey(), dto.getBatchId(), dto.getType());

        // 遍历判断
        for (BusinessTaskDTO businessTask : dto.getBusinessTaskList()) {
            JudgeTaskResult result = new JudgeTaskResult();
            result.setMainId(businessTask.getMainId());
            result.setStatus(true);
            result.setMsg("正常");

            // 1. 判断审核流程是否一致
            if (StringUtils.isNotBlank(businessTask.getMiniCurrentProcessDefKey()) && !businessTask.getMiniCurrentProcessDefKey().equals(processDefKey)) {
                result.setStatus(false);
                result.setMsg("不在当前审核流程");
                list.add(result);
                continue;
            }

            // 2. 判断审核节点id是否一致
            if (StringUtils.isNotBlank(businessTask.getMiniCurrentTaskDefKey()) && StringUtils.isNotBlank(firstBusinessTask.getMiniCurrentTaskDefKey()) && !businessTask.getMiniCurrentTaskDefKey().equals(firstBusinessTask.getMiniCurrentTaskDefKey())) {
                result.setStatus(false);
                result.setMsg("不在当前审核节点");
                list.add(result);
                continue;
            }

            // 3. 判断审核节点是否一致
            if (StringUtils.isNotBlank(businessTask.getMinicurrentstate()) && StringUtils.isNotBlank(firstBusinessTask.getMinicurrentstate()) && !businessTask.getMinicurrentstate().equals(firstBusinessTask.getMinicurrentstate())) {
                result.setStatus(false);
                result.setMsg("不在当前审核节点");
                list.add(result);
                continue;
            }

            // 4. 判断是否有进行中的审核流程
            FlowBusiness flowBusiness = getFlowBusiness(businessTask.getMainId(), processDefKey);
            if (flowBusiness == null) {
                result.setStatus(false);
                result.setMsg("没有正在进行的审核流程");
                list.add(result);
                continue;
            }

            // 5. 检查任务用户权限
            if (!checkTaskUser(flowBusiness, sysUser)) {
                result.setStatus(false);
                result.setMsg("没有审核权限");
                list.add(result);
                continue;
            }

            list.add(result);
        }

        return list;
    }


    /**
     * 检查任务用户权限
     *
     * @param flowBusiness FlowBusiness
     * @param sysUser      SysUser
     * @return boolean
     * @author szs
     * @date 2023-12-26
     */
    private boolean checkTaskUser(FlowBusiness flowBusiness, SysUser sysUser) {
        // 1. 如果存在审核人，那就判断用户id
        boolean flag = false;
        if (StringUtils.isNotBlank(flowBusiness.getTaskCandidateUser())) {
            flag = flowBusiness.getTaskCandidateUser().contains(sysUser.getId());
        }

        // 2. 如果存在审核人组，那就判断角色id
        if (StringUtils.isNotBlank(flowBusiness.getTaskCandidateGroup())) {
            for (String roleId : sysUser.getRoleIds()) {
                if (flowBusiness.getTaskCandidateGroup().contains(roleId)) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }


    /**
     * 批量完成任务
     *
     * @param dto BatchCompleteTaskDTO
     * @author szs
     * @date 2023-12-05
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchCompleteTask(BatchCompleteTaskDTO dto) {

        // 1. 审核权限判断
        JudgeTaskDTO judgeTaskDTO = new JudgeTaskDTO();
        judgeTaskDTO.setBatchId(dto.getBatchId());
        judgeTaskDTO.setType(dto.getType());
        judgeTaskDTO.setBusinessTaskList(dto.getBusinessTaskList());
        List<JudgeTaskResult> judgeTaskResults = this.judgeTask(judgeTaskDTO);
        for (JudgeTaskResult judgeTaskResult : judgeTaskResults) {
            if (!judgeTaskResult.isStatus()) {
                throw new ServiceException(judgeTaskResult.getMsg());
            }
        }

        // 2. 批次时间判断
        if (dto.getIsJudgeTime() != null && dto.getIsJudgeTime() && StringUtil.isNotBlank(dto.getTimeCode())) {
            JSONObject object = pmsPlanDeadlineService.judgeTime(dto.getBatchId(), dto.getTimeCode());
            if (!object.getBoolean("result")) {
                throw new ServiceException(object.getString("note"));
            }
        }

        // 3. 消耗或退回指标
        if (dto.getIsLimit() != null && dto.getIsLimit() && dto.getLimitNum() != null && StringUtil.isNotBlank(dto.getLimitResult()) && StringUtil.isNotBlank(dto.getLimitGridType())) {
            // 指标一个一个的消耗，并判断是否足够，如果有一个不足够，那就事务回滚
            for (String businessId : dto.getBusinessIds()) {
                // 消耗或退回
                JSONObject object;
                try {
                    object = pmsEnterpriselimitService.updateDirectionLimit(businessId, dto.getLimitGridType(), dto.getLimitNum(), dto.getLimitResult());
                } catch (Exception e) {
                    e.printStackTrace();
                    // updateDirectionLimit报错后，需要继续抛出异常，让事务回滚
                    throw new ServiceException(e.getMessage());
                }

                if (!object.getBoolean("result")) {
                    throw new ServiceException(object.getString("note"));
                }
            }

        }

        // 4. 批量审核
        this.batchFlowUserTask(AuthUtil.getUserId(), dto.getTaskIds(), dto.getFirstTaskName(), dto.getTaskResult(), dto.getTaskResultName(), dto.getTaskComment(), dto.getType());

        // 5. 保存业务数据跟退回修改字段版本中间数据
        miniBizDataInAllowUpdateService.batchSave(dto.getBusinessIds(), dto.getMiniBizAllowUpdateId());

    }


    /**
     * 批量流程用户任务
     *
     * @param userId               操作用户id
     * @param taskIds              任务ids
     * @param taskName             任务节点名称
     * @param taskResultCustom     任务操作结果自定义
     * @param taskResultCustomName 任务操作结果自定义名称
     * @param taskComment          任务操作意见
     * @param gridType             业务类型
     * @author szs
     * @date 2024-03-14
     */
    @Override
    public void batchFlowUserTask(String userId, List<String> taskIds, String taskName, String taskResultCustom, String taskResultCustomName, String taskComment, String gridType) {
        // 请求参数
        BatchCompleteTaskParameter parameter = new BatchCompleteTaskParameter();
        parameter.setBatchName("批量审核：" + taskIds.size());
        parameter.setUser(sysUserService.getBladeUser(userId));
        parameter.setTaskIds(taskIds);
        parameter.setBusinessType(gridType);
        parameter.setAllowTaskBePreempted(false);

        // 自定义参数
        Map<String, Object> variables = new HashMap<>();
        variables.put("task_user_id", userId);
        variables.put("task_result_custom", taskResultCustom);
        variables.put("task_result_custom_name", taskResultCustomName);
        variables.put("task_name", taskName);

        // 任务结果
        String taskResult = taskResultCustom;

        // 自定义按钮处理
        if (taskResultCustom.contains("wf_pass")) {
            taskResult = "wf_pass";
        } else if (taskResultCustom.contains("wf_reject")) {
            taskResult = "wf_reject";
        } else if (taskResultCustom.contains("wf_terminate")) {
            taskResult = "wf_terminate";
        } else if (taskResultCustom.contains("wf_withdraw")) {
            taskResult = "wf_withdraw";
            variables.put("withdraw_type", "wf_withdraw_start");
        }

        parameter.setTaskResult(taskResult);
        variables.put("task_result", taskResult);

        // 额外参数处理
        switch (taskResultCustom) {
            case "wf_pass_inadmissible":
                // 不予受理，表示审核不通过
            case "wf_pass_review":
                // 复审，表示审核不通过
                variables.put("audit_result", false);
                break;
            default:
                variables.put("audit_result", true);
                break;
        }

        parameter.setVariables(variables);

        // 任务操作意见，{taskResult: 'wf_reject', taskResultName: '驳回', taskComment: '信息不全'}，方面后续流程记录查看
        JSONObject comment = new JSONObject();
        comment.put("taskResult", taskResultCustom);
        comment.put("taskResultName", taskResultCustomName);
        comment.put("taskComment", taskComment);
        parameter.setTaskComment(JSON.toJSONString(comment));

        // 批量审核
        Result<String> result = flowProcessService.batchFlowUserTask(parameter);
        if (!result.isSuccess()) {
            throw new ServiceException(result.getMsg());
        }

    }


    /**
     * 获取当前用户二级归口单位
     *
     * @return PmsEnterprise
     * @author szs
     * @date 2023-11-22
     */
    private PmsEnterprise getUserCountyEnterprise() {
        // 当前用户id
        String userId = AuthUtil.getUserId();
        if (StringUtils.isBlank(userId)) {
            throw new ServiceException("用户未登录");
        }

        // 获取用户
        SysUser sysUser = sysUserService.findById(userId);
        if (sysUser == null) {
            throw new ServiceException("用户不存在：" + userId);
        }

        // 单位
        String id = sysUser.getId();
        String enterId;
        List<BiEntBi> byMainid = biEntBiService.findByMainid(id);
        if (byMainid != null && byMainid.size() > 0) {
            BiEntBi biEntBi = byMainid.get(0);
            if (biEntBi.getCountrycasemanagementid() != null) {
                enterId = biEntBi.getCountrycasemanagementid();
            } else if (biEntBi.getCitycasemanagementid() != null) {
                enterId = biEntBi.getCitycasemanagementid();
            } else {
                throw new ServiceException("请联系单位管理员补全单位信息");
            }
            PmsEnterprise enterprise = pmsEnterpriseService.getById(enterId);
            if (enterprise != null) {
                return enterprise;
            } else {
                throw new ServiceException("请联系单位管理员补全单位信息");
            }
        } else {
            throw new ServiceException("请联系单位管理员补全单位信息");
        }
    }


    /**
     * 撤回任务
     *
     * @param dto WithdrawTaskDTO
     * @author szs
     * @date 2024-03-15
     */
    @Override
    public void withdrawTask(WithdrawTaskDTO dto) {
        // 1. 判断申报时间，时间编码固定，不能随便更改
        JSONObject object = pmsPlanDeadlineService.judgeTime(dto.getBatchId(), StringUtil.isNotBlank(dto.getTimeCode()) ? dto.getTimeCode() : "applicationPeriod");
        if (!object.getBoolean("result")) {
            throw new ServiceException(object.getString("note"));
        }

        // 获取批次绑定流程定义key
        String processDefKey = this.getBatchInFlow(dto.getProcessDefKey(), dto.getBatchId(), dto.getType());

        // 获取业务流程中间表数据
        FlowBusiness flowBusiness = this.getFlowBusiness(dto.getMainId(), processDefKey);
        if (flowBusiness == null) {
            throw new ServiceException("当前流程任务不存在");
        }

        // 2. 判断流程状态
        if (dto.getAllowStatusList() != null && !dto.getAllowStatusList().contains(flowBusiness.getTaskName())) {
            throw new ServiceException("当前流程状态不能撤回：" + flowBusiness.getTaskName());
        }

        // 3. 消耗或退回指标
        if (dto.getIsLimit() != null && dto.getIsLimit() && dto.getLimitNum() != null && StringUtil.isNotBlank(dto.getLimitResult()) && StringUtil.isNotBlank(dto.getLimitGridType())) {
            try {
                object = pmsEnterpriselimitService.updateDirectionLimit(dto.getMainId(), dto.getLimitGridType(), dto.getLimitNum(), dto.getLimitResult());
                if (!object.getBoolean("result")) {
                    throw new ServiceException(object.getString("note"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                // updateDirectionLimit报错后，需要继续抛出异常，让事务回滚
                throw new ServiceException(e.getMessage());
            }

        }

        // 4. 撤回任务
        List<String> taskIds = new ArrayList<>();
        taskIds.add(flowBusiness.getTaskId());
        this.batchFlowUserTask(AuthUtil.getUserId(), taskIds, "", "wf_withdraw", "撤回", "撤回任务", dto.getType());

    }


    /**
     * 终止任务
     *
     * @param dto ProcessTaskDTO
     * @author szs
     * @date 2024-03-26
     */
    @Override
    public void terminateTask(ProcessTaskDTO dto) {

        // 获取批次绑定流程定义key
        String processDefKey = this.getBatchInFlow(dto.getMiniCurrentProcessDefKey(), dto.getBatchId(), dto.getType());

        // 获取业务流程中间表数据
        FlowBusiness flowBusiness = this.getFlowBusiness(dto.getMainId(), processDefKey);
        if (flowBusiness == null) {
            throw new ServiceException("当前流程任务不存在，不能终止任务");
        }

        // 终止任务
        List<String> taskIds = new ArrayList<>();
        taskIds.add(flowBusiness.getTaskId());
        this.batchFlowUserTask(AuthUtil.getUserId(), taskIds, dto.getMinicurrentstate(), "wf_terminate", "终止", "终止任务", dto.getType());

        // 添加日志
        sysOperationLogService.addLog(dto.getType(), dto.getMainId(), "终止任务", "终止任务", dto.getMinicurrentstate());

    }


    /**
     * 受理任务
     *
     * @param dto ProcessTaskDTO
     * @author szs
     * @date 2024-03-26
     */
    @Override
    public void admissibleTask(ProcessTaskDTO dto) {

        // 获取批次绑定流程定义key
        String processDefKey = this.getBatchInFlow(dto.getMiniCurrentProcessDefKey(), dto.getBatchId(), dto.getType());

        // 获取业务流程中间表数据
        FlowBusiness flowBusiness = this.getFlowBusiness(dto.getMainId(), processDefKey);
        if (flowBusiness != null) {
            throw new ServiceException("当前流程任务未结束，不能受理任务");
        }

        // 获取主表Service
        String serviceName = this.getMainTableServiceName(dto.getType());

        // 修改主表为受理通过
        try {
            // 动态执行Service方法
            Util.springInvokeMethod(serviceName, "updateMainState", new Object[]{dto.getMainId(), "受理通过"});
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 添加日志
        sysOperationLogService.addLog(dto.getType(), dto.getMainId(), "受理任务", "受理任务", dto.getMinicurrentstate());

    }


    /**
     * 重置任务
     *
     * @param dto ProcessTaskDTO
     * @author szs
     * @date 2024-03-26
     */
    @Override
    public void resetTask(ProcessTaskDTO dto) {

        // 获取批次绑定流程定义key
        String processDefKey = this.getBatchInFlow(dto.getMiniCurrentProcessDefKey(), dto.getBatchId(), dto.getType());

        // 获取业务流程中间表数据
        FlowBusiness flowBusiness = this.getFlowBusiness(dto.getMainId(), processDefKey);
        if (flowBusiness != null) {
            throw new ServiceException("当前流程任务未结束，不能重置任务");
        }

        // 获取主表Service
        String serviceName = this.getMainTableServiceName(dto.getType());

        // 修改主表为受理通过
        try {
            // 动态执行Service方法
            Util.springInvokeMethod(serviceName, "updateMainState", new Object[]{dto.getMainId(), "用户填报"});
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 添加日志
        sysOperationLogService.addLog(dto.getType(), dto.getMainId(), "重置任务", "重置任务", dto.getMinicurrentstate());

    }


}
