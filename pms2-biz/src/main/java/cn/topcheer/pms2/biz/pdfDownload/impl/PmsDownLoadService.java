package cn.topcheer.pms2.biz.pdfDownload.impl;

import cn.topcheer.halberd.app.api.minidev.dto.MiniInitAllTableDTO;
import cn.topcheer.halberd.app.api.minidev.service.MiniCommonService;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInBiz;

import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import cn.topcheer.pms2.biz.flow.service.impl.FlowRecordService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchInBizService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlantypeService;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.pdf.PmsDownLoadDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PmsDownLoad 服务类
 */
@Service(value = "PmsDownLoadService")
public class PmsDownLoadService extends GenericService<PmsProjectbase> {
    public PmsDownLoadDao getPmsDownLoadDao() {
        return (PmsDownLoadDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsDownLoadDao(PmsDownLoadDao pmsDownLoadDao) {
        this.setGenericDao(pmsDownLoadDao);
    }

    @Autowired
    DBHelper dbHelper;
    @Autowired
    FlowRecordService flowRecordService;
    @Autowired
    PmsImageconfigService pmsImageconfigService;
    @Autowired
    PmsProjectbaseService pmsProjectbaseService;
    @Autowired
    PmsPlanprojectbatchInBizService pmsPlanprojectbatchInBizService;
    @Autowired
    MiniCommonService miniCommonService;
    @Autowired
    PmsPlantypeService pmsPlantypeService;
    /**
     * 根据配置来查数据
     *
     * @param sourceid
     * @return
     */
    public Map<String, Object> getDataMap(String sourceid, String downtype) {
        Map<String, Object> hashmap = new HashMap<String, Object>();
        String sql="select t.* from sys_version_object t" +
                " inner join sys_version_tab ta on ta.id=t.tabid" +
                " inner join sys_version sy on sy.id=ta.versionid" ;
        if("sb".equals(downtype)){ //合同数据源
//            String batchidType="planprojectbatchid";
            /*String oldplanbatchid=this.dbHelper.getOnlyStringValue("select t.oldplanbatchid from crt_contract_jbxx t where t.id=? ",new Object[]{sourceid});
            if(!Util.isEoN(oldplanbatchid)){//如果有oldplanbatchid说明在评审中
                batchidType="oldplanbatchid";
            }*/
//            sql=sql+" inner join batch_version pl on pl.ht=sy.id " +
//                    " inner join crt_contract_jbxx pa on pa."+batchidType+"=pl.id" +
//                    " where pa.id=? ";
//            List<Map> sysVersionObjectList = this.getListBySql(sql,new Object[]{sourceid});
//            for (int i = 0; i < sysVersionObjectList.size(); i++) {
//                Map objectmap=sysVersionObjectList.get(i);
//                if(Util.isEoN(objectmap.get("moduletype"))|| Util.isEoN(objectmap.get("paramtype"))||Util.isEoN(objectmap.get("objecttype"))){
//                    if(!"附件上传".equals(objectmap.get("showname"))){
//                        System.out.print("显示名称为:"+objectmap.get("showname")+"的，表对应为："+objectmap.get("moduletype")+"的，传参为："
//                                +objectmap.get("paramtype")+"的，数据类型为："+objectmap.get("objecttype")+"的模块，有点问题\r\n");
//                    }
//                    continue;
//                }else{
//                    String tablename=objectmap.get("moduletype").toString();
//                    String type=objectmap.get("paramtype").toString();
//                    String datatype=objectmap.get("objecttype").toString();
//
//                    /*合同表数据源*/
//                    if("TableJbxx".equals(tablename)){//主表
//                        hashmap = this.getTableJbxx(sourceid,hashmap);
//                    }
//                    else if("TableCddw".equals(tablename)) {//单位表
//                        hashmap = this.getTableCddwByType(sourceid,type,datatype,hashmap);
//                    }else if("TableXmry".equals(tablename)) {//人员表
//                        hashmap = this.getTableXmryByType(sourceid,type,datatype,hashmap);
//                    }else if("TableJfys".equals(tablename)) {//经费预算表
//                        hashmap = this.getTableJfysByType(sourceid,type,datatype,hashmap);
////                    }else if("TableJf".equals(tablename)) {//经费来源表
////                        hashmap = this.getTableJfByType(sourceid,type,datatype,hashmap);
//                    }else if("TableYqsb".equals(tablename)) {//仪器表
//                        hashmap = this.getTableYqsbByType(sourceid,type,datatype,hashmap);
//                    }else if("TableTj".equals(tablename)) {//仪器表
//                        hashmap = this.getTableTjByType(sourceid,type,datatype,hashmap);
//                    }else if("TableSub".equals(tablename)) {//仪器表
//                        hashmap = this.getTableSubByType(sourceid,type,datatype,hashmap);
//                    }else if("TableJhjd".equals(tablename)) {//进度表
//                        hashmap = this.getTableJhjdByType(sourceid,type,datatype,hashmap);
//                    }else if("TableTarget".equals(tablename)) {//指标表
//                        hashmap = this.getTableTargetByType(sourceid,type,datatype,hashmap);
//                    }else if("TableCrtClob".equals(tablename)) {//大字段表
//                        hashmap = this.getTableCrtClob(sourceid,type,datatype,hashmap);
//                    }else if("TableXmrytj".equals(tablename)) {//人员统计表
//                        hashmap = this.getTableXmrytjByType(sourceid,type,datatype,hashmap);
//                    }else if("TableJfysin".equals(tablename)) {//经费来源表
//                        hashmap = this.getTableJfysinByType(sourceid,type,datatype,hashmap);
//                    }else if("TableJfysout".equals(tablename)) {//经费预算表
//                        hashmap = this.getTableJfysoutByType(sourceid,type,datatype,hashmap);
//                    }else if("TableCgxs".equals(tablename)) {//成果形式
//                        hashmap = this.getTableCrtCgxsByType(sourceid,type,datatype,hashmap);
//                    }else if("TableCgqk".equals(tablename)) {//成果情况
//                        hashmap = this.getTableCrtCgqkByType(sourceid,type,datatype,hashmap);
//                    }else if("TableBenefit".equals(tablename)) {//效益指标
//                        hashmap = this.getTableCrtBenefitByType(sourceid,type,datatype,hashmap);
//                    }
//                }
//            }

            /*备注变更数据*/
//            hashmap = this.getContractchangeDate(sourceid,hashmap);
            PmsProjectbase pmsProjectbase = new PmsProjectbase();
            PmsPlanprojectbatchInBiz planprojectbatchInBiz = new PmsPlanprojectbatchInBiz();
//            Map<String, Object> hashmap = new HashMap<String, Object>();
            HqlBuilder<PmsProjectbase> hqlProjectBase = new HqlBuilder<>();
            hqlProjectBase.eq(PmsProjectbase::getId,sourceid);
            List<PmsProjectbase> pmsProjectbaseList = pmsProjectbaseService.find(hqlProjectBase);
            if (pmsProjectbaseList.size()>0){
                pmsProjectbase = pmsProjectbaseList.get(0);
            }
            MiniInitAllTableDTO miniInitAllTableDTO = new MiniInitAllTableDTO();
            miniInitAllTableDTO.setMainid(pmsProjectbase.getId());
            miniInitAllTableDTO.setBatchid(pmsProjectbase.getPlanprojectbatchid());
            HqlBuilder<PmsPlanprojectbatchInBiz> hqlProjectBiz = new HqlBuilder<>();
            hqlProjectBiz.eq(PmsPlanprojectbatchInBiz::getPmsPlanprojectbatchId,pmsProjectbase.getPlanprojectbatchid())
                    .eq(PmsPlanprojectbatchInBiz::getPmsPlantype,pmsPlantypeService.getPmsPlantype(pmsProjectbase.getPlanprojecttype()));
            List<PmsPlanprojectbatchInBiz> bizs = pmsPlanprojectbatchInBizService.find(hqlProjectBiz);
            if (bizs.size()>0){
                planprojectbatchInBiz = bizs.get(0);
            }
            miniInitAllTableDTO.setBizDefId(planprojectbatchInBiz.getMiniBizDefId());
            miniInitAllTableDTO.setBizVersionId(planprojectbatchInBiz.getMiniBizVersionId());
            miniInitAllTableDTO = miniCommonService.initAllTable(miniInitAllTableDTO);
            JSONObject dataObject = miniInitAllTableDTO.getDataObject();
            String dataString = dataObject.toString();
            if (!StringUtil.isEmpty(dataString)){
                hashmap = com.alibaba.fastjson.JSONObject.parseObject(dataString, HashMap.class);
                hashmap = this.parseHashMap(hashmap);
            }
//            return miniInitAllTableDTO.getDataObject();
        }else if("reward".equals(downtype)){
//            String batchidType="planprojectbatchid";
//            /*String oldplanbatchid=this.dbHelper.getOnlyStringValue("select t.oldplanbatchid from pms_reward t where t.id=? ",new Object[]{sourceid});
//            if(!Util.isEoN(oldplanbatchid)){//如果有oldplanbatchid说明在评审中
//                batchidType="oldplanbatchid";
//            }*/
//            sql=sql+" inner join pms_planprojectbatch pl on pl.versionkjjlid=sy.id " +
//                    " inner join pms_reward pa on pa."+batchidType+"=pl.id" +
//                    " where pa.id=? ";
//            List<Map> sysVersionObjectList = this.getListBySql(sql,new Object[]{sourceid});
//            for (int i = 0; i < sysVersionObjectList.size(); i++) {
//                Map objectmap=sysVersionObjectList.get(i);
//                if(Util.isEoN(objectmap.get("moduletype"))||Util.isEoN(objectmap.get("moduletype"))||Util.isEoN(objectmap.get("moduletype"))){
//                    continue;
//                }else{
//                    String tablename=objectmap.get("moduletype").toString();
//                    String type=objectmap.get("paramtype").toString();
//                    String datatype=objectmap.get("objecttype").toString();
//
//                    HashMap methodHs=new HashMap();
//                    methodHs.put("RW_Jbxx","pms_reward");
//                    methodHs.put("RW_Ent","pms_reward_enterprise");//提名单位表
//                    methodHs.put("RW_Expert"," pms_reward_expert");//提名专家表
//                    methodHs.put("RW_Exp","pms_reward_experience");//学习和工作简历表
//                    methodHs.put("RW_Extend","pms_reward_extend");//转换推广应用表
//                    methodHs.put("RW_Sub","pms_reward_subject");//学科表
//                    methodHs.put("RW_Thesisty","pms_reward_thesisty");//论文他引表
//                    methodHs.put("RW_Thesisfb","pms_reward_thesisfb");//论文发表表
//                    methodHs.put("RW_Tj","pms_reward_tj");//统计表
//                    methodHs.put("RW_Zscq","pms_reward_zscq");//知识产权表
//                    methodHs.put("RW_Award","pms_reward_award");//奖励情况表
//                    methodHs.put("RW_Benefit","pms_reward_benefit");//经济效益表
//                    methodHs.put("RW_Clob","pms_reward_clob");//大字段表
//                    methodHs.put("RW_Cyhyxqk","pms_reward_cyhyxqk");
//                    methodHs.put("RW_Cgzhqk","pms_reward_cgzhqk");//产业化运行情况表
//                    /*科技奖表数据源*/
//                    if("RW_Cddw".equals(tablename)) {//单位表
//                        hashmap = this.getTableEnterpriseorganizationByType(sourceid,type,datatype,hashmap,tablename);
//                    }else if("RW_Xmry".equals(tablename)) {//人员表
//                        hashmap = this.getTableProjectpartyByType(sourceid,type,datatype,hashmap,tablename);
//                    }else if(!"base".equals(tablename)) {//其他
//                        hashmap = this.getDataByTypeAndTable(sourceid,type,datatype,hashmap,methodHs.get(tablename)+"",tablename);
//                    }
//                }
//            }
        }else if("kc".equals(downtype)){//备案
//            String batchidType="planprojectbatchid";
//            /*String oldplanbatchid=this.dbHelper.getOnlyStringValue("select t.oldplanbatchid from pms_keepcase t where t.id=? ",new Object[]{sourceid});
//            if(!Util.isEoN(oldplanbatchid)){//如果有oldplanbatchid说明在评审中
//                batchidType="oldplanbatchid";
//            }*/
//            sql=sql+" inner join pms_planprojectbatch pl on pl.versionkcid=sy.id " +
//                    " inner join pms_keepcase pa on pa."+batchidType+"=pl.id" +
//                    " where pa.id=? ";
//            List<Map> sysVersionObjectList = this.getListBySql(sql,new Object[]{sourceid});
//            for (int i = 0; i < sysVersionObjectList.size(); i++) {
//                Map objectmap=sysVersionObjectList.get(i);
//                if(Util.isEoN(objectmap.get("moduletype"))||Util.isEoN(objectmap.get("moduletype"))||Util.isEoN(objectmap.get("moduletype"))){
//                    continue;
//                }else{
//                    String tablename=objectmap.get("moduletype").toString();
//                    String type=objectmap.get("paramtype").toString();
//                    String datatype=objectmap.get("objecttype").toString();
//
//                    HashMap methodHs=new HashMap();
//                    methodHs.put("KCBase","pms_keepcase");
//                    methodHs.put("KCCddw","Pms_Keepcase_Cddw");//单位表
//                    methodHs.put("KCXmry"," Pms_Keepcase_Xmry");//人员
//                    methodHs.put("KCTdry","Pms_Keepcase_Tdry");//团队人员
//                    methodHs.put("KCInfo","Pms_Keepcase_Info");//特征信息
//                    methodHs.put("KCClob","pms_keepcase_clob");//大字段表
//                    /*备案表数据源*/
//                    hashmap = this.getDataByTypeAndTable(sourceid,type,datatype,hashmap,methodHs.get(tablename)+"",tablename);
//                }
//            }
        }else {//申报数据源
//            String batchidType = "planprojectbatchid";
//            /*String oldplanbatchid=this.dbHelper.getOnlyStringValue("select t.oldplanbatchid from pms_projectbase t where t.id=? ",new Object[]{sourceid});
//            if(!Util.isEoN(oldplanbatchid)){//如果有oldplanbatchid说明在评审中
//                batchidType="oldplanbatchid";
//            }*/
//            sql = sql + " inner join pms_planprojectbatch pl on pl.versionsbid=sy.id " +
//                    " inner join pms_projectbase pa on pa." + batchidType + "=pl.id" +
//                    " where pa.id=? ";
//            List<Map> sysVersionObjectList = this.getListBySql(sql, new Object[]{sourceid});
//            for (int i = 0; i < sysVersionObjectList.size(); i++) {
//                Map objectmap = sysVersionObjectList.get(i);
//                if (Util.isEoN(objectmap.get("moduletype")) || Util.isEoN(objectmap.get("moduletype")) || Util.isEoN(objectmap.get("moduletype"))) {
//                    continue;
//                } else {
//                    String tablename = objectmap.get("moduletype").toString();
//                    String type = objectmap.get("paramtype").toString();
//                    String datatype = objectmap.get("objecttype").toString();
//
//                    /*申报表数据源*/
//                    if ("TPBase".equals(tablename)) {//主表
//                        hashmap = this.getTableProjectbase(sourceid, hashmap);
//                    } else if ("TPOrg".equals(tablename)) {//单位表
//                        hashmap = this.getTableEnterpriseorganizationByType(sourceid, type, datatype, hashmap, tablename);
//                    } else if ("TPParty".equals(tablename)) {//人员表
//                        hashmap = this.getTableProjectpartyByType(sourceid, type, datatype, hashmap, tablename);
//                    } else if ("TPExp".equals(tablename)) {//经费预算表
//                        hashmap = this.getTableProjectexpenseByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPIns".equals(tablename)) {//仪器表
//                        hashmap = this.getTableInstrumentByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPSche".equals(tablename)) {//进度表
//                        hashmap = this.getTableProjectscheduleByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPTarget".equals(tablename)) {//指标表
//                        hashmap = this.getTableProjectTargetByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPClob".equals(tablename)) {//大字段表
//                        hashmap = this.getTableClob(sourceid, type, datatype, hashmap);
//                    } else if ("TPXmrytj".equals(tablename)) {//人员统计表
//                        hashmap = this.getTableProjectXmrytjByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPJfysinOld".equals(tablename)) {//经费来源表
//                        hashmap = this.getTableProjectJfysinByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPJfysout".equals(tablename)) {//经费预算表
//                        hashmap = this.getTableProjectJfysoutByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPBenefit".equals(tablename)) {//经济效益表
//                        hashmap = this.getTableProjectBenefitByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPCgxs".equals(tablename)) {//成果形式表
//                        hashmap = this.getTableProjectCgxsByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPCgqk".equals(tablename)) {//成果情况表
//                        hashmap = this.getTableProjectCgqkByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPFzrqk".equals(tablename)) {//负责人承担项目情况表
//                        hashmap = this.getTableProjectFzrqkByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPAwards".equals(tablename)) {//曾获奖励表
//                        hashmap = this.getTableProjectAwardsByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPThesis".equals(tablename)) {//论文表
//                        hashmap = this.getTableProjectThesisByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPPatent".equals(tablename)) {//专利表
//                        hashmap = this.getTableProjectPatentByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPLeadunit".equals(tablename)) {//牵头企业运行情况
//                        hashmap = this.getTableProjectLeadunitByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPTj".equals(tablename)) {//除人员外其他统计
//                        hashmap = this.getTableProjectTjByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPExperience".equals(tablename)) {//申报人经历
//                        hashmap = this.getTableProjectExperienceByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPProduct".equals(tablename)) {//近3年新产品
//                        hashmap = this.getTableProjectProductByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPResearch".equals(tablename)) {//近5年基础研究情况
//                        hashmap = this.getTableProjectResearchByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPStandard".equals(tablename)) {//标准制定
//                        hashmap = this.getTableProjectStandardByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPSub".equals(tablename)) {//学科
//                        hashmap = this.getTableProjectSubjectByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPExpert".equals(tablename)) {//引进专家表
//                        hashmap = this.getTableProjectExpertByType(sourceid, type, datatype, hashmap);
//                    } else if ("TPInfo".equals(tablename)) {//经济效益
//                        hashmap = this.getTableProjectProinfoByType(sourceid, type, datatype, hashmap);
//                    }
//                    hashmap.put("year", "");
//                }
//            }
//            申报时候增加审核时间和审核内容
            ArrayList<String> list = new ArrayList<String>();
            list.add("项目申报:所在单位审核");
            list.add("项目申报:推荐单位审核");
//            hashmap = this.flowRecordService.getRecommendDeteal(sourceid, list, hashmap);
        }
        /*图片插入---先判断有没有*/
//        List imageList=this.pmsImageconfigService.getImageConfig(sourceid,downtype);
//        if(imageList.size()>0){
//            hashmap = this.pmsAffixService.getTableAffixFortype(imageList,sourceid,hashmap);
//        }
        return hashmap;
    }

    private Map<String, Object> parseHashMap(Map<String, Object> hashmap) {
        Map<String, Object> resultMap = new HashMap<>();
        hashmap.forEach((key,map)->{
//            System.out.println(map instanceof JSONArray);
            if (!StringUtil.isEmpty(map)){
                JSONObject jsonObject = JSONObject.fromObject(map);
                Object dataObject = jsonObject.get("data");
                if (dataObject instanceof JSONObject){
//                    System.out.println("data is" + (dataObject instanceof JSONObject));
                    JSONObject dataObj = JSONObject.fromObject(dataObject);
                    if (dataObj.size()>0){
                        dataObj.forEach((dataKey,data)->{
                            resultMap.put(key+"_"+dataKey,data);
                    });
                    }
                }else if (dataObject instanceof JSONArray){
                    JSONArray jsonArray = JSONArray.fromObject(dataObject);
//                    System.out.println(jsonArray);
                    resultMap.put(key, jsonArray);
                }
            }
        });
        System.out.println("--------------------------------------------");
        System.out.println(resultMap);
        System.out.println("--------------------------------------------");
        return resultMap;
    }

    /**
     * @author  合同项目基本情况--合同
     * @param contractid
     * @param
     * @param hashmap
     * @return
     */
    public Map<String, Object> getTableJbxx(String contractid,
                                            Map<String, Object> hashmap) {
        return null;
    }

    /**
     * 合同pdf备注，获取变更数据
     * @param projectbaseid
     * @return
     */
    public Map<String, Object> getContractchangeDate(String projectbaseid,Map<String, Object> hashmap){
        JSONObject isexit = isexit(projectbaseid);//是否变更
        hashmap.put("isexit",isexit.get("isexit")+"");
        if("true".equals(isexit.get("isexit"))){
            String contractid=isexit.get("contractid")+"";
//            hashmap = this.pmsContractchangeService.getChangeDataForPdf(hashmap,contractid);
        }
        return hashmap;
        /*备注--变更信息*/
        //1、先判断是否有发起变更
//        String contractid = this.dbHelper.getOnlyStringValue("select e.id from crt_contract_jbxx e where e.projectbaseid = ?",new Object[]{projectbaseid});
//        if(Util.isEoN(contractid)){
//            hashmap.put("isexit", "false");
//        }else{
//            //2、判断是否有发起过变更
//            List<Map> isbgList = this.dbHelper.getRows("select e.id from pms_contractchange e where e.contractid = ? and e.whochange='用户发起变更' and e.minicurrentstate = '审核通过'",new Object[]{contractid});
//            if(!Util.isEoN(isbgList)&&isbgList.size()>0){
//                hashmap.put("isexit", "true");
//                // 获取变更信息--老版本变更
//                hashmap = pmsContractService.getChangeListSourcesForall(projectbaseid,
//                        hashmap);
//                // 获取变更信息--新版本变更
//                hashmap = pmsContractService.getChangeListSourcesForall_new(projectbaseid,
//                        hashmap,contractid);
//            }else{
//                hashmap.put("isexit", "false");
//            }
//        }

    }
    public JSONObject isexit(String contractid) {
        JSONObject obj=new JSONObject();
        //判断是否有合同
//        String contractid = this.dbHelper.getOnlyStringValue("select e.id from crt_contract_jbxx e where e.projectbaseid = ?",new Object[]{projectbaseid});
        if(Util.isEoN(contractid)){
            obj.put("isexit","false");
        }else{
            //判断是否有变更
            List<Map> isbgList =this.dbHelper.getRows("select e.id from pms_contractchange e" +
                    "  where e.contractid = ?  and e.changenum is not null",new Object[]{contractid});
            if(!Util.isEoN(isbgList)&&isbgList.size()>0){
                obj.put("contractid",contractid);
                obj.put("isexit","true");
            }else{
                obj.put("isexit","false");
            }
        }
        return obj;
    }

    /**
     * 根据配置来查数据
     *
     * @param sourceid
     * @return
     */
    public Map<String, Object> getDataMap(String sourceid) {
        PmsProjectbase pmsProjectbase = new PmsProjectbase();
        PmsPlanprojectbatchInBiz planprojectbatchInBiz = new PmsPlanprojectbatchInBiz();
        Map<String, Object> hashmap = new HashMap<String, Object>();
        HqlBuilder<PmsProjectbase> hqlProjectBase = new HqlBuilder<>();
        hqlProjectBase.eq(PmsProjectbase::getId,sourceid);
        List<PmsProjectbase> pmsProjectbaseList = pmsProjectbaseService.find(hqlProjectBase);
        if (pmsProjectbaseList.size()>0){
            pmsProjectbase = pmsProjectbaseList.get(0);
        }
        MiniInitAllTableDTO miniInitAllTableDTO = new MiniInitAllTableDTO();
        miniInitAllTableDTO.setMainid(pmsProjectbase.getId());
        miniInitAllTableDTO.setBatchid(pmsProjectbase.getPlanprojectid());
        HqlBuilder<PmsPlanprojectbatchInBiz> hqlProjectBiz = new HqlBuilder<>();
        hqlProjectBiz.eq(PmsPlanprojectbatchInBiz::getPmsPlanprojectbatchId,pmsProjectbase.getPlanprojectbatchid())
                .eq(PmsPlanprojectbatchInBiz::getPlanprojectType,pmsProjectbase.getPlanprojecttype());
        List<PmsPlanprojectbatchInBiz> bizs = pmsPlanprojectbatchInBizService.find(hqlProjectBiz);
        if (bizs.size()>0){
            planprojectbatchInBiz = bizs.get(0);
        }
        miniInitAllTableDTO.setBizDefId(planprojectbatchInBiz.getMiniBizDefId());
        miniInitAllTableDTO.setBizVersionId(planprojectbatchInBiz.getMiniBizVersionId());
        miniInitAllTableDTO = miniCommonService.initAllTable(miniInitAllTableDTO);
        if(true){ //合同数据源
//        if("ht".equals(downtype)){ //合同数据源


        }
        return miniInitAllTableDTO.getDataObject();
    }
}
