package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.JsonDateValueProcessor;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.BatchVersion;
import cn.topcheer.pms2.api.plan.entity.PmsPlanproject;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.plan.BatchConfigDao;
import cn.topcheer.pms2.dao.plan.PmsPlancategoryDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.beetl.ext.fn.Json;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service(value = "BatchConfigService")
public class BatchConfigService extends GenericService<BatchVersion> {
    @Autowired
    private PmsPlanprojectService pmsPlanprojectService;
    @Autowired
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;
    @Autowired
    private SysOperationrecordService sysOperationrecordService;
    @Autowired
    private BatchVersionService batchVersionService;
    @Autowired
    private PmsPlancategoryService pmsPlancategoryService;
    @Autowired
    private DBHelper dbHelper;
    @Autowired
    private SysUserService sysUserService;

    public BatchConfigDao getBatchConfigDao() {
        return (BatchConfigDao) this.getGenericDao();
    }

    @Autowired
    public void setBatchConfigDao(BatchConfigDao batchConfigDao) {

        this.setGenericDao(batchConfigDao);
    }


    /**
     * 【大批次业务配置】-- 新增或修改大批次
     */
    public void addAndEditBigbatch(JSONObject json) {
        String type = "";//新增或者修改
        String id = json.get("id") + "";
        PmsPlanproject pmsPlanproject = this.pmsPlanprojectService.findById(id);
        if (!Util.isEoN(pmsPlanproject)) {
            if (!pmsPlanproject.getCreateUser().equals(AuthUtil.getUserId())) {
                return;
            }
            //说明是修改
            type = "大批次修改";
            json.put("create_user", pmsPlanproject.getCreateUser());
            json.put("organize_id", pmsPlanproject.getOrganizeId());
        } else {
            //说明是新增
            pmsPlanproject = new PmsPlanproject();
            pmsPlanproject.setId(Util.NewGuid());
            type = "大批次新增";
        }
        Util.ApplyObject(pmsPlanproject, json);
        String userId = AuthUtil.getUserId();
        SysUser user = sysUserService.getById(userId);
        if (user == null) {
            return;
        }
        pmsPlanproject.setOrganizeId(user.getEnterPriseId());
        pmsPlanproject.setCreateUser(userId);
        //默认值（暂无用）：
        pmsPlanproject.setState(1);
        //特殊字段处理：（先不处理）
//        //默认首页显示处室与责任处室相同，有特殊情况先自行改写Home_departmentid，这种太特殊了...
//        if(!Util.isEoN(pmsPlanproject.getDepartmentid())){
//            pmsPlanproject.setHome_departmentid(pmsPlanproject.getDepartmentid());
//        }
        if (Util.isEoN(pmsPlanproject.getCategory())) {
            pmsPlanproject.setCategory(pmsPlanproject.getSystem());
        }
        //保存或修改
        this.pmsPlanprojectService.merge(pmsPlanproject);
        updatePlanProjectBatch(pmsPlanproject);
        //操作记录
        this.sysOperationrecordService.commonSaveOperation(id, type, "本次保存的大批次数据：" + json.toString());
    }

    private void updatePlanProjectBatch(PmsPlanproject pmsPlanproject) {
        List<PmsPlanprojectbatch> batchs = pmsPlanproject.getPmsPlanprojectbatchs();
        if (batchs != null && batchs.size()>0) {
            batchs.forEach(batch -> {
                batch.setIshallshow(pmsPlanproject.getIshallshow());
                pmsPlanprojectbatchService.saveOrUpdate(batch);
            });
        }
    }

    /**
     * 【小批次业务配置】-- 新增或修改小批次
     */
    public JSONObject addAndEditBatch(JSONObject json, String bigbatchid) {
        String userId = AuthUtil.getUserId();
        SysUser user = sysUserService.getById(userId);
        JSONObject resJson = new JSONObject();
        Boolean isAdd = false;
        String type = "";//新增或者修改
        String id = json.get("id") + "";
        PmsPlanprojectbatch batch = this.pmsPlanprojectbatchService.findById(id);
        if (!Util.isEoN(batch)) {
            //说明是修改，判断是否可以修改
//            JSONObject judgeJson = this.judgeEditBatch(json,bigbatchid);
//            if(!judgeJson.getBoolean("success")){
//                return judgeJson;
//            }
            type = "小批次修改";
            Util.ApplyObject(batch, json);
        } else {
            //说明是新增，判断是否可以新增
            isAdd = true;
            JSONObject judgeJson = this.judgeAddBatch(json, bigbatchid);
            if (!judgeJson.getBoolean("success")) {
                return judgeJson;
            }
            batch = new PmsPlanprojectbatch();
            batch.setId(Util.NewGuid());
            type = "小批次新增";
            Util.ApplyObject(batch, json);
            //todo:zrx 特殊默认值
            batch.setPdfurlforbase("/PdfTmpFile/PmsProjectbase");
            batch.setPdfurlforcontract("/PdfTmpFile/CrtContract");
            batch.setPdfdowntypeforbase("bookmark");
            batch.setPdfdowntypeforcontract("bookmark");
            batch.setCurrentstate("2");//默认启用
            batch.setAppliylimit(false);//默认没有申报限制
            //batch.setCity("省级");
            //把大批次的创新链条+支持方式+科技计划赋值给小批次
            PmsPlanproject big = this.pmsPlanprojectService.findById(bigbatchid);
            if (!Util.isEoN(big.getInnovatechain())) {
                batch.setInnovatechain(big.getInnovatechain());
            }
            if (!Util.isEoN(big.getSupportway())) {
                batch.setSupportway(big.getSupportway());
            }
            if (!Util.isEoN(big.getApplicationtypename1())) {
                batch.setApplicationtypename1(big.getApplicationtypename1());
            }
            //新增批次版本表
            BatchVersion bv = new BatchVersion();
            bv.setId(id);
            this.batchVersionService.merge(bv);
        }
        //默认值：
        batch.setPmsPlanproject(this.pmsPlanprojectService.findById(bigbatchid));
        //保存或修改
        batch.setCreateUser(user.getId());
        batch.setOrganizeId(user.getEnterPriseId());
        batch.setDepartmentid(batch.getItemOrganCode());
        this.pmsPlanprojectbatchService.merge(batch);
        //操作记录
        this.sysOperationrecordService.commonSaveOperation(id, type, "本次保存的小批次数据：" + json.toString());

        if (isAdd) {
            this.pmsPlancategoryService.createBizByTypes(batch.getSystem(), batch.getId());
        }
        return Util.dealResJson(resJson, true);
    }

    /**
     * 【小批次业务配置】--判断是否可以新增小批次 for addAndEditBatch
     */
    public JSONObject judgeAddBatch(JSONObject json, String bigbatchid) {
        JSONObject resJson = new JSONObject();
        /*String year = json.get("annually")+"";
        //这里看起来是限制了大批次每年只能有一条记录。不太符合直观的想法，暂时注释掉
        HqlBuilder hqlBuilder = new HqlBuilder<PmsPlanprojectbatch>().eq("planprojectid", bigbatchid).eq("annually", year);
//        List<Map> list = this.dbHelper.getRows("select e.id from PMSGUIZHOU.PMS_PLANPROJECTBATCH e where e.planprojectid = ? and e.annually = ?",new Object[]{bigbatchid,year});
        List list = pmsPlanprojectbatchService.find(hqlBuilder);
        if(list.size()>0){
            return Util.dealResJson(resJson,false,"当前选择的年份已存在。");
        }else{
            return Util.dealResJson(resJson,true);
        }*/
        return Util.dealResJson(resJson, true);
    }

    /**
     * 【大批次业务配置】--删除大批次
     */
    public JSONObject deleteBigbatch(String id) {
        JSONObject resJson = new JSONObject();
        //判断大批次下是否有小批次
        List<Map> batchList = this.dbHelper.getRows("select e.id from pms_planprojectbatch e where e.planprojectid = ?", new Object[]{id});
        if (batchList.size() > 0) {
            return Util.dealResJson(resJson, false, "当前大批次下有存在小批次，无法删除。");
        } else {
            resJson.put("success", true);
            PmsPlanproject p = this.pmsPlanprojectService.findById(id);
            if (!p.getCreateUser().equals(AuthUtil.getUserId())) {
                return Util.dealResJson(resJson, false, "无权删除此大批次！");
            }
            String bigbatchname = p.getProjectname();
            this.pmsPlanprojectService.delete(p);
            //操作记录
            this.sysOperationrecordService.commonSaveOperation(id, "删除大批次", "本次删除的大批次id：" + id + "，大批次名称：" + bigbatchname);
            return Util.dealResJson(resJson, true);
        }
    }

    /**
     * 【小批次业务配置】--通过大批次id获取小批次数据
     */
    public List<Map> getBatchByBigbatchid(String bigbatchid, String flow) {
        String flowsql;
        switch (flow) {
            case "xssc":
                flowsql = " and e.xs_necessary = 1";
                break;
//            case "ps":
//                flowsql = " and e.ps_necessary = 1";
//                break;
            case "wp":
                flowsql = " and e.ps_necessary = 1";
                break;
            case "hp":
                flowsql = " and e.ps_necessary = 2";
                break;
            case "wpandhp":
                flowsql = " and e.ps_necessary = 3";
                break;
            case "hpandhp":
                flowsql = " and e.ps_necessary = 4";
                break;
            case "gcnlx":
                flowsql = " and e.name like '%自主创新%'";
                break;
            default:
                flowsql = "";
                break;
        }
        List<Map> listBySql = this.dbHelper.getRows("select rownum as xh,tt.* from (" +
                " select e.id,e.annually,e.name,e.ccflag,e.sgsflag," +
                "e.ishallshow, e.numberarithmetic ," +
                "e.MATTER_CODE as matter_Code,e.MATTER_TYPE as matter_Type,e.HANDLE_DOCUMENT_TYPE as handle_Document_Type," +
                "e.HANDLE_GRADE as handle_Grade,e.BUSINESS_SYSTEM as business_System,e.BUSINESS_EXAMINE_SYSTEM as business_Examine_System," +
                "e.ITEM_REGION_CODE as item_Region_Code,e.ITEM_REGION_NAME as item_Region_Name,e.ITEM_ORGAN_CODE as item_Organ_Code," +
                "e.PROMISETIME_LIMIT as promisetime_Limit,e.PROMISETIME_UNIT as promisetime_Unit,e.TIME_LIMIT as time_Limit," +
                "e.TIME_UNIT as timeUnit, xs_necessary as xsnecessary  ,ps_necessary as psnecessary,e.ismainfx," +
                "(case when e.xs_necessary =1 then '是' else '否' end ) as xsnecessaryname," +
                "(case when PS_NECESSARY=1 then '网评' when PS_NECESSARY=2 then '会评' when PS_NECESSARY=3 then '网评+会评' when PS_NECESSARY=4 then '会评+会评' else '不参与评审' end ) as psnecessaryname " +
                " from pms_planprojectbatch e where e.planprojectid = ? " + flowsql + " and e.create_user = '" + AuthUtil.getUserId() + "' order by e.annually desc) tt", new Object[]{bigbatchid});
        List<Map> list = new ArrayList<>();
        for (Map map : listBySql) {
            map = Util.jsonKeyToCamelCase(map);
            map.put("xs_necessary", map.get("xsnecessaryname"));
            map.put("ps_necessary", map.get("psnecessaryname"));
            if (!Util.isEoN(map.get("itemRegionCode"))) {
                String regionCode = map.get("itemRegionCode").toString();
                map.put("itemRegionCode", getRegionString(regionCode));
            }
//            map.put("xs_necessary", map.get("xsnecessary"));
//            map.put("ps_necessary", map.get("psnecessary"));
            list.add(map);
        }
        listBySql = list;
        for (Map map : listBySql) {
            String ccflag = map.get("ccflag") + "";
            String batchid = map.get("id") + "";
            if ("".equals(ccflag) || ccflag == null) {
                return listBySql;
            } else if (ccflag.equals("1")) {
                String sql1 = "select e.id from pms_projectbase e where e.planprojectbatchid = ?";
                List<Map> xmids = this.dbHelper.getRows(sql1, new Object[]{batchid});
                for (Map xmid : xmids) {
                    String ccid = this.dbHelper.getOnlyStringValue("select e.id from cc_record e where e.sourceid = ?", new Object[]{xmid.get("id")});
                    if (ccid != null) {
                        try {
                            this.dbHelper.runSql("update pms_planprojectbatch e set ccflag = '2' where id = ?", new Object[]{batchid});
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return listBySql;
    }

    private JSONArray getRegionString(String regionCode) {
        String sql = "select ancestors from SF_REGION where CODE = ?";
        List<Map> rows = dbHelper.getRows(sql, new Object[]{regionCode});
        if (rows != null && rows.size() > 0) {
            Map map = rows.get(0);
            String[] ancestors = map.get("ancestors").toString()
                    .replace("00,", "").split(",");
//            StringBuilder builder = new StringBuilder("[");
            JSONArray jsonArray = new JSONArray();
            for (String ancestor : ancestors) {
//                builder.append(ancestor + ",");
                jsonArray.add(ancestor);
            }
            jsonArray.add(regionCode);
//            builder.append(regionCode + "]");
//            return builder.toString();
            return jsonArray;
        }
        return new JSONArray();
    }

    /**
     * 【小批次业务配置】--删除小批次
     */
    public JSONObject deleteBatch(String batchid, String system) throws SQLException {
        JSONObject resJson = new JSONObject();
        //判断批次绑定的业务数据是否有内容
        List<String> sqlList = new ArrayList<>();
        List<String> reasonList = new ArrayList<>();
        switch (system) {
            case "kjxm":
                sqlList.add("select e.id from pms_projectbase e where e.planprojectbatchid = ?");
                reasonList.add("还有科技项目-申报业务数据绑定该批次，不能删除。");
//                sqlList.add("select e.id from crt_contract_jbxx e where e.planprojectbatchid = ?");
//                reasonList.add("还有科技项目-合同业务数据绑定该批次，不能删除。");
                break;
            case "kjjl":
                sqlList.add("select e.id from pms_reward e where e.planprojectbatchid = ?");
                reasonList.add("还有科技奖励业务数据绑定该批次，不能删除。");
                break;
            case "kjcxpt":
                sqlList.add("select e.id from pms_innovationbase e where e.planprojectbatchid = ?");
                reasonList.add("还有科技创新平台业务数据绑定该批次，不能删除。");
                break;
            default:
                return Util.dealResJson(resJson, false, "参数有误，不能删除。");
        }
        for (int i = 0; i < sqlList.size(); i++) {
            List<Map> list = this.dbHelper.getRows(sqlList.get(i), new Object[]{batchid});
            if (list.size() > 0) {
                return Util.dealResJson(resJson, false, reasonList.get(i));
            }
        }
        //保存操作记录
        PmsPlanprojectbatch batch = this.pmsPlanprojectbatchService.findById(batchid);
        this.sysOperationrecordService.commonSaveOperation(batchid, "删除小批次", "本次删除的小批次id：" + batch.getId() + "，小批次名称：" + batch.getName());
        //删除相关数据
        dbHelper.runSql("delete from sys_version_affix where batchid = ?", new Object[]{batchid});
        dbHelper.runSql("delete from pms_enterpriselimit where batchid = ?", new Object[]{batchid});
        dbHelper.runSql("delete from limit_config where batch_id = ?", new Object[]{batchid});
        dbHelper.runSql("delete from batch_version where id = ?", new Object[]{batchid});
        dbHelper.runSql("delete from batch_guide where id = ?", new Object[]{batchid});
        this.pmsPlanprojectbatchService.delete(batch);
        return Util.dealResJson(resJson, true);
    }

    /**
     * 【产品化整体配置】-- 根据大批次类型来获取大批次数据
     *
     * @param system
     * @return
     */
    public List getBigbatchBySystem(String system) {
//        HqlBuilder<BatchVersion> hqlBuilder = super.getHqlBuilder();
//        hqlBuilder.eq("SYSTEM", system)
//                .addOrder("seq", "desc");
//        return this.find(hqlBuilder);
//        String roleSql = super.getRoleSql();
        String userId = AuthUtil.getUserId();
        SysUser user = sysUserService.getById(userId);
        if (user == null) {
            return null;
        }
        String sql = "select tt.*,rownum as xh from ( " +
                "select e.*,d.name as departmentname, e.projectname as name " +
                "from pms_planproject e " +
                "left join pms_department d on e.departmentid = d.id " +
//                "left join pms_enterprise ent on e.companyId = ent.parent_Id " +
//                "left join pms_enterprise ent2 on ent.id = ent2.parentId " +
                "where e.system = ? " +
                " and e.create_user = ? " +
//                roleSql +
//                "and e.company_Id = ? " +
                "order by e.seq " +
                ") tt";
        return this.dbHelper.getRows(sql, new Object[]{system, userId});
//        return this.dbHelper.getRows(sql, new Object[]{system,user.getEnterPriseId()});
    }

    /**
     *【小批次业务配置】--获取小批次基本信息
     */
    public JSONObject getBatchBasicInfo(String batchid){
//        List<PmsPlanprojectbatch> list = null;
        List<Map> list = this.dbHelper.getRows("select id as id,starttime as starttime,endtime as endtime," +
                " relyunitendtime as relyunitendtime,recommendedunitendtime as recommendedunitendtime,reconsiderendtime as reconsiderendtime," +
                " releasedate as releasedate,fundslimit as fundslimit,isqzzb as isqzzb,obligedtarget as obligedtarget,year as year," +
                " opencollect as opencollect,releaseway as releaseway,innovatechain as innovatechain,supportway as supportway " +
                " from Pms_Planprojectbatch where id = ?", new Object[]{batchid});
        if(list.size()>0){
//            JsonConfig jsonConfig = new JsonConfig();
//            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));//设置时间格式
//            PmsPlanprojectbatch planprojectbatch = new PmsPlanprojectbatch();
//            Util.ApplyObject(planprojectbatch,JSONObject.fromObject(list.get(0)) );
//            System.out.println(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(planprojectbatch.getStarttime()));
            return JSONObject.fromObject(list.get(0));
        }else{
            return new JSONObject();
        }
    }
}
