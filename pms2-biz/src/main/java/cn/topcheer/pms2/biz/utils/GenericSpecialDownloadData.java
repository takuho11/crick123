package cn.topcheer.pms2.biz.utils;

import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.PmsPlanproject;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 通用数据查询保存方法
 *
 * @author GaoGongxin
 * @date 2020/9/16 16:29
 */
@Component
public class GenericSpecialDownloadData {
    //    public Logger log = Logger.getLogger(getClass());
//
//    @Autowired
//    PmsTxtSave pmsTxtSave;
//
//    @Autowired
//    PmsAttachmentService pmsAttachmentService;
//
//    @Autowired
//    PmsAffixService pmsAffixService;
//
    @Autowired
    DBHelper dbHelper;
//
//    @Autowired
//    SysOperationrecordService sysOperationrecordService;
//
    @Autowired
    PmsPlanprojectbatchService pmsPlanprojectbatchService;
//
//    @Autowired
//    SysVersionAffixService sysVersionAffixService;
//
//    @Autowired
//    PmsProjectbaseCddwService pmsProjectbaseCddwService;
//
//    @Autowired
//    FlowRecordService flowRecordService;
//
//    @Autowired
//    PmsSubsidyDao pmsSubsidyDao;

    public Map<String, Object> SpecialDownloadData(Map<String, Object> hashmap, JSONObject dataObject, String mainid, String batchid) {
        PmsPlanproject pmsPlanproject = this.pmsPlanprojectbatchService.findById(batchid).getPmsPlanproject();

        /*-------------------------------------------------附件模块处理开始*/
//        List<SysVersionAffix> sysVersionAffixListSb = this.sysVersionAffixService.findVersionAffix(batchid, "sb");
//        String affixtype = "";
//        String affixkey = "";
//        String affixshowtip = "";
//        for (int i = 0; i < sysVersionAffixListSb.size(); i++) {
//            /*affixtype = sysVersionAffixListSb.get(i).getType();
//            affixkey = sysVersionAffixListSb.get(i).getAffixkey();
//            affixshowtip = sysVersionAffixListSb.get(i).getShowtip();*/
//            JSONObject obj=JSONObject.fromObject(sysVersionAffixListSb.get(i));
//            affixtype = obj.get("type").toString();
//            affixkey = obj.get("affixkey").toString();
//            affixshowtip = obj.get("showtip").toString();
//            switch (affixtype) {
//                case "合作协议"://如果有合作单位必传
//                    List<PmsProjectbaseCddw> cddwList = this.pmsProjectbaseCddwService.findByMainidAndType(mainid, "principal");
//                    if (cddwList.size() > 0) {
//                        hashmap.put("affixMust_" + affixkey, "是");
//                    } else {
//                        hashmap.put("affixMust_" + affixkey, "否");
//                    }
//                    hashmap = getAffixMemo(hashmap, mainid, affixkey, affixtype);
//                    break;
//                case "国外合作方合作合同"://如果合作单位有国外的必传
//                    List<PmsProjectbaseCddw> cddwListGw = this.pmsProjectbaseCddwService.findByHql("select c.id from cn.topcheer.pms.pojo.PmsProjectbaseCddw c where c.mainid=? and c.type=? and c.belongnation !='中国' ", new Object[]{mainid, "principal"});
//                    if (cddwListGw.size() > 0) {
//                        hashmap.put("affixMust_" + affixkey, "是");
//                    } else {
//                        hashmap.put("affixMust_" + affixkey, "否");
//                    }
//                    hashmap = getAffixMemo(hashmap, mainid, affixkey, affixtype);
//                    break;
//                default:
//                    hashmap.put("affixMust_" + affixkey, affixshowtip);
//                    hashmap = getAffixMemo(hashmap, mainid, affixkey, affixtype);
//            }
//        }
        if ("784BD9E9-DE73-4B4D-A88E-DFF12E1AA6FF".equals(pmsPlanproject.getId())) {//重大科技专项（关键核心技术攻关、卡脖子技术攻关）
        } else if ("BATCHID2".equals(batchid)) {

        }
/*        //项目当前流程节点
        String currentstatus = dbHelper.getOnlyStringValue("select t.STATE from FL_FLOWRELATIONDETAIL t where t.MAINSOURCEID = ? ", new Object[]{mainid});
        if (Util.isEoN(currentstatus)){
            currentstatus="";
        }*/

        String operationcontent = dbHelper.getOnlyStringValue("select t.OPERATIONCONTENT from FL_FLOWRECORD t where t.sourceid = ? and t.sourceflowpointname like '%所在单位审核%' order by t.operationtime desc", new Object[]{mainid});
        String operationtime = dbHelper.getOnlyStringValue("select t.OPERATIONtime from FL_FLOWRECORD t where t.sourceid = ? and t.sourceflowpointname like '%所在单位审核%' order by t.operationtime desc", new Object[]{mainid});
        String operationcontent1 = dbHelper.getOnlyStringValue("select t.OPERATIONCONTENT from FL_FLOWRECORD t where t.sourceid = ? and t.sourceflowpointname like '%组织单位审核%' order by t.operationtime desc", new Object[]{mainid});
        String operationtime1 = dbHelper.getOnlyStringValue("select t.OPERATIONtime from FL_FLOWRECORD t where t.sourceid = ? and t.sourceflowpointname like '%组织单位审核%' order by t.operationtime desc", new Object[]{mainid});
//       //审核完成后 退回操作的时间
//        String backtime = dbHelper.getOnlyStringValue("select t.OPERATIONtime from FL_FLOWRECORD t where t.sourceid = ? and t.sourceflowpointname like '%组织单位审核%' order by t.operationtime desc", new Object[]{mainid});
//        //审核完成后 退回的意见
//        String backcontent = dbHelper.getOnlyStringValue("select t.OPERATIONCONTENT from FL_FLOWRECORD t where t.sourceid = ? and t.sourceflowpointname like '%完成%' order by t.operationtime desc", new Object[]{mainid});
        String currentoperationresultname = dbHelper.getOnlyStringValue("select t.currentoperationresultname,t.*\n" +
                "from FL_FLOWRECORD t where (t.SOURCEFLOWPOINTNAME like '%完成%' or t.SOURCEFLOWPOINTNAME like '%单位审核%') and  t.sourceid = ? order by t.operationtime desc", new Object[]{mainid});

        //获取申报项目 受理编号
        String applicationno = dbHelper.getOnlyStringValue("SELECT  e.applicationno FROM PMS_PROJECTBASE e WHERE e.id=?",new Object[]{mainid});
        hashmap.put("applicationno",applicationno);

        String sql ="select t.id from pms_subsidy t where t.id = ?";
        String onlyStringValue = dbHelper.getOnlyStringValue(sql, new Object[]{mainid});
        if (!Util.isEoN(onlyStringValue)){
            //说明是补助类项目
            //获取补助项目 受理编号 计划类别 提交时间

            List<Map> rows = dbHelper.getRows("SELECT " +
                    " (case when e.APPLICATIONNO is null then '' else e.APPLICATIONNO end) as subsidyapplicationno ," +
                    " projectplantype , to_char(SUBMITDATE,'yyyy-mm-dd') as submitdate " +
                    " FROM PMS_SUBSIDY e WHERE e.id=?", new Object[]{mainid});
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR)+1)+"年";

            hashmap.put("subsidyapplicationno",rows.get(0).get("subsidyapplicationno"));
            //hashmap.put("basicInfo_normal_projectplantype",year+rows.get(0).get("projectplantype"));
            hashmap.put("basicInfo_normal_submitdate",rows.get(0).get("submitdate"));
            if ("21000502-872c-2d04-c88d-af46ed086c21".equals(batchid)){
                hashmap.put("basicInfo_normal_planprojectbatchname","生物医药产业创新补助（2020年）");
            }else if ("760d9cff-42c7-8261-6c3a-9865ec9bb6a1".equals(batchid)){
                hashmap.put("basicInfo_normal_planprojectbatchname","生物医药产业创新补助（2021年）");
            }

            //小数位数取4位
            String projectsumforgov = dbHelper.getOnlyStringValue("select round(PROJECTSUMFORGOV,4) from PMS_SUBSIDY where id= ? ",new Object[]{mainid});
            hashmap.put("basicInfo_normal_projectsumforgov",projectsumforgov);
        }

        String sql1 ="select t.id from pms_innovationbase t where t.id = ?";
        String onlyStringValue1 = dbHelper.getOnlyStringValue(sql1, new Object[]{mainid});
        if (!Util.isEoN(onlyStringValue1)){
            String guide_enter="";//指导单位
            String head_enter ="";//依托单位
            //说明是平台类项目
            List<Map> list1 = (List) hashmap.get("joinGovUnit");
            for (Map o : list1) {
                if (o.get("guide_enter").equals("是")){
                    guide_enter+=o.get("unitname")+";";
                }
                if (o.get("head_enter").equals("是")){
                    head_enter+=o.get("unitname")+";";
                }
            }
            List<Map> list2 = (List) hashmap.get("joinUnit");
            for (Map o : list2) {
                if (o.get("guide_enter").equals("是")){
                    guide_enter+=o.get("unitname")+";";
                }
                if (o.get("head_enter").equals("是")){
                    head_enter+=o.get("unitname")+";";
                }
            }
            if (Util.isEoN(guide_enter)){
                hashmap.put("guide_enter","无");
            }else {
                hashmap.put("guide_enter",guide_enter);
            }
            hashmap.put("head_enter",head_enter);
        }
        hashmap.put("textBox_normal_obligedtarget_normal",hashmap.get("textBox_normal_obligedtarget_normal")!=null?hashmap.get("textBox_normal_obligedtarget_normal"):"");
        hashmap.put("textBox_normal_value_economic_normal",hashmap.get("textBox_normal_value_economic_normal")!=null?hashmap.get("textBox_normal_value_economic_normal"):"");
        hashmap.put("textBox_normal_value_social_normal",hashmap.get("textBox_normal_value_social_normal")!=null?hashmap.get("textBox_normal_value_social_normal"):"");
        hashmap.put("textBox_normal_value_cultural_normal",hashmap.get("textBox_normal_value_cultural_normal")!=null?hashmap.get("textBox_normal_value_cultural_normal"):"");

        /*if ("38347e6e-f0e0-adfa-9312-76668990566e".equals(batchid)|| "adfa3be8-cd86-bc53-7b0e-820091772023".equals(batchid)){
            //说明人工智能重大科技专项或农业和社会科技专题
            List<Map> instrument = (List) hashmap.get("instrument");
            Integer ins_count =0;
            Double ins_total =0.0;
            //仪器设备总数和总价
            for (Map o : instrument) {
                ins_count += Util.isEoN((Integer) o.get("instrumentcount"))?0:(Integer) o.get("instrumentcount");
                ins_total += Util.isEoN((Double) o.get("total"))?0.0:(Double) o.get("total");
            }
            hashmap.put("instrument_count",ins_count);
            hashmap.put("instrument_total",ins_total);
        }*/
/*        if (currentstatus.equals("") || currentstatus.contains("用户填报") ||currentstatus.contains("所在单位审核")){
            //项目在用户手里，包括各种 退回 以及 未上报 时的状态
            hashmap.put("szdwshdate", "年 月 日");
            hashmap.put("szdwshyj", "");
            hashmap.put("tjdwshdate", "年 月 日");
            hashmap.put("tjdwshyj", "");
        }else if (currentstatus.contains("组织单位审核")){
            //所在单位已通过，取所在单位意见和通过时间
            hashmap.put("tjdwshdate","年 月 日") ;
            hashmap.put("tjdwshyj", "");
            hashmap.put("szdwshdate", operationtime.substring(0,4)+"年"+operationtime.substring(5,7)+"月"+operationtime.substring(8,10)+"日");
            hashmap.put("szdwshyj", operationcontent);
        }else {
            //更后面的流程，所在单位和组织单位已通过，取二者的意见和通过日期
            hashmap.put("tjdwshdate", operationtime1.substring(0,4)+"年"+operationtime1.substring(5,7)+"月"+operationtime1.substring(8,10)+"日");
            hashmap.put("tjdwshyj", operationcontent1);
            hashmap.put("szdwshdate", operationtime.substring(0,4)+"年"+operationtime.substring(5,7)+"月"+operationtime.substring(8,10)+"日");
            hashmap.put("szdwshyj", operationcontent);
        }*/
        if ("退回".equals(currentoperationresultname)) {
            hashmap.put("szdwshdate", "年 月 日");
            hashmap.put("szdwshyj", "");
            hashmap.put("tjdwshdate", "年 月 日");
            hashmap.put("tjdwshyj", "");
        }
        else if (operationcontent1 != null) {
            hashmap.put("tjdwshdate", operationtime1.substring(0,4)+"年"+operationtime1.substring(5,7)+"月"+operationtime1.substring(8,10)+"日");
            hashmap.put("tjdwshyj", operationcontent1);
            hashmap.put("szdwshdate", operationtime.substring(0,4)+"年"+operationtime.substring(5,7)+"月"+operationtime.substring(8,10)+"日");
            hashmap.put("szdwshyj", operationcontent);
        } else if (operationcontent != null) {
            hashmap.put("szdwshdate", operationtime.substring(0,4)+"年"+operationtime.substring(5,7)+"月"+operationtime.substring(8,10)+"日");
            hashmap.put("szdwshyj", operationcontent);
            hashmap.put("tjdwshdate", "年 月 日");
            hashmap.put("tjdwshyj", "");
        }
        else {
            hashmap.put("szdwshdate", "年 月 日");
            hashmap.put("szdwshyj", "");
            hashmap.put("tjdwshdate", "年 月 日");
            hashmap.put("tjdwshyj", "");
        }

        return hashmap;
    }

    /*获取附件是否上传*/
//    private Map<String, Object> getAffixMemo(Map<String, Object> hashmap, String mainid, String affixkey, String category) {
//        List pmsattachment = this.pmsAttachmentService.findPmsAttachmentBySourceidAndCategory(mainid, category);
//        if (pmsattachment.size() > 0) {
//            hashmap.put("affixMemo_" + affixkey, "申报人上传");
//        } else {
//            hashmap.put("affixMemo_" + affixkey, "");
//        }
//        return hashmap;
//    }
}
