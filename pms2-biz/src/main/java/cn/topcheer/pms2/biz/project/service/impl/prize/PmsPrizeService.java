/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2024-1-4 9:32:16
 */
package cn.topcheer.pms2.biz.project.service.impl.prize;


import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.plan.entity.PmsPlanproject;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.PmsAcceptanceSave;
import cn.topcheer.pms2.api.sys.PmsProcessSave;
import cn.topcheer.pms2.api.sys.PmsResultSave;
import cn.topcheer.pms2.api.sys.enums.HandleResultEnum;
import cn.topcheer.pms2.api.sys.enums.HandleStageEnum;
import cn.topcheer.pms2.api.sys.enums.HandleStatusEnum;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectService;
import cn.topcheer.pms2.biz.sys.PmsZwwAcceptanceSaveService;
import cn.topcheer.pms2.biz.sys.PmsZwwProcessSaveService;
import cn.topcheer.pms2.biz.sys.PmsZwwResultSaveService;
import cn.topcheer.pms2.biz.sys.ZwwRemoteService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.project.utils.ApplicationNoUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.prize.*;
import cn.topcheer.pms2.dao.project.prize.*;

import cn.topcheer.halberd.biz.common.db.DBHelper;

import java.util.*;

/**
 * PmsPrize 服务类
 */
@Service(value = "PmsPrizeService")
public class PmsPrizeService extends GenericService<PmsPrize> {

    public PmsPrizeDao getPmsPrizeDao() {
        return (PmsPrizeDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPrizeDao(PmsPrizeDao pmsPrizeDao) {

        this.setGenericDao(pmsPrizeDao);
    }


    @Autowired
    private PmsEnterpriseServiceImpl pmsEnterpriseService;
    @Autowired
    private DBHelper dbHelper;
    @Autowired
    private ZwwRemoteService zwwRemoteService;
    @Autowired
    private PmsZwwAcceptanceSaveService pmsZwwAcceptanceSaveService;
    @Autowired
    private PmsZwwProcessSaveService pmsZwwProcessSaveService;
    @Autowired
    private PmsZwwResultSaveService pmsZwwResultSaveService;
    @Autowired
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;


    @Override
    protected JSONObject afterGenericSaveFun(JSONObject result, String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {
        PmsPrize pmsPrize = this.findById(mainid);

        // 推荐单位名称
        PmsEnterprise casemanagementEnter = pmsEnterpriseService.findById(pmsPrize.getEnterpriseid());
        if (casemanagementEnter != null) {
            if (casemanagementEnter.getPmsParentEnterprise() == null || "98C57F262CB74E12AAC31C9D37533A17".equals(casemanagementEnter.getPmsParentEnterprise().getId())) {
                // 当前属于一级单位，无上级或上级等于省科技厅
                pmsPrize.setCitycasemanagement(casemanagementEnter.getName());
                pmsPrize.setCitycasemanagementid(casemanagementEnter.getId());

            } else {
                // 当前属于二级单位，有上级
                pmsPrize.setCitycasemanagement(casemanagementEnter.getPmsParentEnterprise().getName());
                pmsPrize.setCitycasemanagementid(casemanagementEnter.getPmsParentEnterprise().getId());
            }

            pmsPrize.setCountycasemanagement(casemanagementEnter.getName());
            pmsPrize.setCountycasemanagementid(casemanagementEnter.getId());

        }


        this.merge(pmsPrize);

        return result;
    }


    /**
     * 获取项目名称
     *
     * @param mainId 主表id
     * @author szs
     * @since 2024-03-05
     */
    @Override
    public Object getProjectName(String mainId) {
        // 查询
        PmsPrize data = this.findById(mainId);

        return data != null ? data.getProjectName() : "";
    }


    /**
     * 修改流程状态
     *
     * @param param 参数
     * @author szs
     * @date 2024-03-01
     */
    @Override
    public void updateMinicurrentState(JSONObject param) {
        String mainId = param.getString("mainId");
        String minicurrentstate = param.getString("minicurrentstate");
        String minicurrentstateid = param.getString("minicurrentstateid");
        String miniCurrentTaskDefKey = param.getString("miniCurrentTaskDefKey");
        String miniCurrentProcessDefKey = param.getString("miniCurrentProcessDefKey");
        String taskResult = param.getString("taskResult");
        String taskName = param.getString("taskName");

        // 查询
        PmsPrize data = this.findById(mainId);
        if (data == null) {
            return;
        }

        // 状态
        if (StringUtils.isNotBlank(minicurrentstate)) {
            // 流程状态
            switch (minicurrentstate) {
                case "完成":
                    // 任务操作结果
                    switch (taskResult) {
                        case "wf_pass_inadmissible":
                            minicurrentstate = taskName + "不通过";
                            break;
                        default:
                            minicurrentstate = taskName + "通过";

                            // 通过时，生成申请编号
                            data.setApplicationno(pmsPlanprojectbatchService.getApplicationNo(data.getPlanprojectbatchid(), data.getDeclarantid()));
                            break;
                    }

                    break;
                case "终止":
                    // 任务操作结果
                    switch (taskResult) {
                        case "wf_terminate_inadmissible":
                            // 不予受理
                            minicurrentstate = taskName + "不通过";
                            break;
                        case "wf_terminate_abandon":
                            // 放弃
                            minicurrentstate = "用户补正放弃";
                            break;
                        case "wf_terminate_timeout":
                            // 超时自动办结
                            minicurrentstate = "超时自动办结";
                            break;
                        default:
                            // 默认管理员终止
                            minicurrentstate = "管理员终止";
                            break;
                    }

                    break;
                case "撤销":
                    minicurrentstate = "用户填报";
                    break;
                default:
                    break;
            }
        }

        data.setMiniCurrentProcessDefKey(miniCurrentProcessDefKey);
        data.setMiniCurrentTaskDefKey(miniCurrentTaskDefKey);
        data.setMinicurrentstate(minicurrentstate);
        data.setMinicurrentstateid(minicurrentstateid);

        //受理
//        if (minicurrentstate.equals("申报受理")){
//            PmsAcceptanceSave acceptanceSave = super.acceptanceSave(param);
//            if (acceptanceSave != null) {
//                pmsZwwAcceptanceSaveService.saveOrUpdate(acceptanceSave);
//                zwwRemoteService.remoteZWW("acceptance", JSONObject.fromObject(acceptanceSave), mainId);
//            }
//        }
//        //过程
//        if (minicurrentstate.equals("申报受理通过") || minicurrentstate.equals("申报受理不通过")
//                || minicurrentstate.equals("补正受理通过") || minicurrentstate.equals("补正受理不通过")
//                || minicurrentstate.equals("复审审核通过") || minicurrentstate.equals("复审审核不通过")) {
//            Map map = getStageAndStatus(minicurrentstate);
//            PmsProcessSave processSave = super.processSave(param,map);
//            if (processSave != null) {
//                pmsZwwProcessSaveService.saveOrUpdate(processSave);
//                zwwRemoteService.remoteZWW("process", JSONObject.fromObject(processSave), mainId);
//            }
//        }
//        //结果
//        if (minicurrentstate.equals("申报受理通过") || minicurrentstate.equals("补正受理通过")
//                || minicurrentstate.equals("复审审核通过") || minicurrentstate.equals("复审审核不通过")
//                || minicurrentstate.equals("超时自动办结")) {
//            Map map = getResultType(minicurrentstate);
//            PmsResultSave resultSave = super.resultSave(param, map);
//            if (resultSave != null) {
//                pmsZwwResultSaveService.saveOrUpdate(resultSave);
//                zwwRemoteService.remoteZWW("result", JSONObject.fromObject(resultSave), mainId);
//            }
//        }
        this.merge(data);

    }

    /**
     * 获取办理结果
     * @param minicurrentstate
     * @return
     */
    private Map getResultType(String minicurrentstate) {
        Map<String, String> map = new HashMap<>();
        switch (minicurrentstate){
            case "申报受理通过":
            case "补正受理通过":
            case "复审审核通过":
                map.put("resultType",HandleResultEnum.ALLOWED.getCode());
                break;
            case "复审审核不通过":
                map.put("resultType",HandleResultEnum.NOT_ALLOWED.getCode());
            case "超时自动办结":
//                map.put("resultType",HandleResultEnum.);
                break;
            default:
        }
        return map;
    }

    //获取办件阶段和办件状态
    private Map getStageAndStatus(String minicurrentstate) {
        Map<String, String> map = new HashMap<>();
        switch (minicurrentstate){
            case "申报受理通过" :
                map.put("Stage",HandleStageEnum.ACCEPTANCE.getCode());
                map.put("State",HandleStatusEnum.COMPLETION.getCode());
                break;
            case "申报受理不通过":
                map.put("Stage",HandleStageEnum.EXAMINE.getCode());
                map.put("State",HandleStatusEnum.IN_PROGRESS.getCode());
                break;
            case "补正受理通过":
                map.put("Stage",HandleStageEnum.COMPLETION.getCode());
                map.put("State",HandleStatusEnum.COMPLETION.getCode());
                break;
            case "补正受理不通过":
                map.put("Stage",HandleStageEnum.EXAMINE.getCode());
                map.put("State",HandleStatusEnum.IN_PROGRESS.getCode());
                break;
            case "复审审核通过":
                map.put("Stage",HandleStageEnum.COMPLETION.getCode());
                map.put("State",HandleStatusEnum.INADMISSIBLE.getCode());
                break;
            case "复审审核不通过":
                map.put("Stage",HandleStageEnum.DECLARE.getCode());
                map.put("State",HandleStatusEnum.INADMISSIBLE.getCode());
                break;
            default:
        }
        return map;
    }

    /*
     * 科技奖数据源
     * */
    public Map<String, Object> getDataPdf(String id) {
        Map<String, Object> hashmap = new HashMap<String, Object>();
        //主表
        String sql = "select *,to_char(submitdate,'yyyy-MM-dd') as submitdatebook,to_char(awardsdate,'yyyy-MM-dd') as awardsdatebook,to_char(startdate,'yyyy-MM-dd') as startdatebook,to_char(enddate,'yyyy-MM-dd') as enddatebook from PMS_Prize where id=?";
        List<Map> lisyPmsprize = dbHelper.getRows(sql, new Object[]{id});
        this.dataToHashMap("true", "normal", hashmap, lisyPmsprize, "jbxx");
        //年份获取
        String sqlyear = "select b.annually from PMS_PLANPROJECTBATCH b inner join PMS_PRIZE z on z.PLANPROJECTBATCHID = b.ID where z.ID =?";
        List<Map> lisyPmsprizeyear = dbHelper.getRows(sqlyear, new Object[]{id});
        String year = "";
        if (lisyPmsprizeyear.size() > 0) {
            year = lisyPmsprizeyear.get(0).get("annually") + "";
        }
        hashmap.put("year", year);
        //工作单位表
        String sqlPmsprizeCddw = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(cop_start_date,'yyyy-MM-dd') as cop_start_datebook from PMS_Prize_CDDW where mainid=? and type='mainUnit' order by seq";
        List<Map> lisyPmsprizeCddw = dbHelper.getRows(sqlPmsprizeCddw, new Object[]{id});
        this.dataToHashMap("true", "normal", hashmap, lisyPmsprizeCddw, "cddw");
        //完成单位
        String sqlPmsprizeCddw1 = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(cop_start_date,'yyyy-MM-dd') as cop_start_datebook from PMS_Prize_CDDW where mainid=? and (type='mainUnit' or type='joinUnit')order by type desc, RANKING,seq";
        List<Map> lisyPmsprizeCddw1 = dbHelper.getRows(sqlPmsprizeCddw1, new Object[]{id});
        for (int n = 0; n < lisyPmsprizeCddw1.size(); n++) {
            List<Map<String, Object>> pageList = new ArrayList<Map<String, Object>>();
            if (n != lisyPmsprizeCddw1.size() - 1) {
                pageList.add(new HashMap<String, Object>());
            }
            lisyPmsprizeCddw1.get(n).put("pageList", pageList);
        }
        hashmap.put("wcdwList", lisyPmsprizeCddw1);
        //合作单位
        String sqlPmsprizeCddw2 = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(cop_start_date,'yyyy-MM-dd') as cop_start_datebook from PMS_Prize_CDDW where mainid=? and type='hzdw' order by seq";
        List<Map> lisyPmsprizeCddw2 = dbHelper.getRows(sqlPmsprizeCddw2, new Object[]{id});
        for (int m = 0; m < lisyPmsprizeCddw2.size(); m++) {
            List<Map<String, Object>> pageList = new ArrayList<Map<String, Object>>();
            if (m != lisyPmsprizeCddw2.size() - 1) {
                pageList.add(new HashMap<String, Object>());
            }
            lisyPmsprizeCddw2.get(m).put("pageList", pageList);
        }
        hashmap.put("hzdwList", lisyPmsprizeCddw2);
        //推荐单位
        String sqlPmsprizeCddw3 = "select *,Row_Number()over(ORDER BY seq) as seqbook from PMS_PRIZE_RECOMMENDED_UNIT where mainid=? and type='recommendUnit' order by seq";
        List<Map> lisyPmsprizeCddw3 = dbHelper.getRows(sqlPmsprizeCddw3, new Object[]{id});
        this.dataToHashMap("true", "normal", hashmap, lisyPmsprizeCddw3, "tjdw");
        //负责人
        String sqlPmsprizeXmry = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(birthday,'yyyy-MM-dd') as birthdaybook,to_char(returneedate,'yyyy-MM-dd') as returneedatebook,to_char(graduatedtime,'yyyy-MM-dd') as graduatedtimebook from PMS_prize_XMRY where mainid=? and type='mainMember' order by seq";
        List<Map> lisyPmsprizeXmry = dbHelper.getRows(sqlPmsprizeXmry, new Object[]{id});
        this.dataToHashMap("true", "normal", hashmap, lisyPmsprizeXmry, "xmry");
        //候选人
        String sqlPmsprizeXmry1 = "select *,Row_Number()over(ORDER BY seq) as seqbook,(case when is_academician='是' then '是' when is_academician='否' then '否' else '' end) as academiciancn,to_char(birthday,'yyyy-MM-dd') as birthdaybook,to_char(academician_date,'yyyy-MM-dd') as academician_datebook,to_char(returneedate,'yyyy-MM-dd') as returneedatebook,to_char(degreedate,'yyyy-MM-dd') as degreedatebook,to_char(graduatedtime,'yyyy-MM-dd') as graduatedtimebook from PMS_prize_XMRY where mainid=? and type='basicInfo' order by seq";
        List<Map> lisyPmsprizeXmry1 = dbHelper.getRows(sqlPmsprizeXmry1, new Object[]{id});
        this.dataToHashMap("true", "normal", hashmap, lisyPmsprizeXmry1, "hxr");
        //完成人
        String sqlPmsprizeXmry2 = "select *,Row_Number()over(ORDER BY seq) as seqbook,(to_char(startdate,'yyyy-MM-dd')||'至'||to_char(enddate,'yyyy-MM-dd')) as startdatebookcn,to_char(birthday,'yyyy-MM-dd') as birthdaybook,to_char(returneedate,'yyyy-MM-dd') as returneedatebook,to_char(graduatedtime,'yyyy-MM-dd') as graduatedtimebook from PMS_prize_XMRY where mainid=? and (type='mainMember' or type='joinMember') order by type desc, ranking,seq";
        List<Map> lisyPmsprizeXmry2 = dbHelper.getRows(sqlPmsprizeXmry2, new Object[]{id});
        hashmap.put("wcrList", lisyPmsprizeXmry2);
        //工作单位候选人联系人
        String sqlPmsprizeXmrylink = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(cop_start_date,'yyyy-MM-dd') as cop_start_datebook from PMS_Prize_CDDW where mainid=? and type='linkMember' order by seq ";
        List<Map> lisyPmsprizeXmrylink = dbHelper.getRows(sqlPmsprizeXmrylink, new Object[]{id});
        this.dataToHashMap("true", "normal", hashmap, lisyPmsprizeXmrylink, "link");
        //合作单位联系人
        String sqlPmsprizeXmrylink1 = "select *,Row_Number()over(ORDER BY seq) as seqbook from PMS_PRIZE_CALINK where mainid=? and type='joinUnitPerson' order by seq ";
        List<Map> lisyPmsprizeXmrylink1 = dbHelper.getRows(sqlPmsprizeXmrylink1, new Object[]{id});
        this.dataToHashMap("true", "normal", hashmap, lisyPmsprizeXmrylink1, "hzlink");
        //候选人单位意见
        String sqlPmsprizecddw = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(cop_start_date,'yyyy-MM-dd') as cop_start_datebook from PMS_Prize_CDDW where mainid=? and type='unitOpinion' order by seq ";
        List<Map> lisPmsprizecddw = dbHelper.getRows(sqlPmsprizecddw, new Object[]{id});
        this.dataToHashMap("true", "normal", hashmap, lisPmsprizecddw, "cddw1");
        //专家推荐
        String sqlPmsprizeKpi1 = "select *,Row_Number()over(ORDER BY seq) as seqbook from PMS_PRIZE_RECOMMENDED_EXPERTS where mainid=? and type='recommendExpert' order by seq";
        List<Map> lisyPmsprizeKpi1 = dbHelper.getRows(sqlPmsprizeKpi1, new Object[]{id});
        String strzjtj = "";
        if (lisyPmsprizeKpi1.size() > 0) {
            for (int i = 0; i < lisyPmsprizeKpi1.size(); i++) {
                if (i == lisyPmsprizeKpi1.size() - 1) {
                    strzjtj = strzjtj + lisyPmsprizeKpi1.get(i).get("name") + "";
                } else {
                    strzjtj = strzjtj + lisyPmsprizeKpi1.get(i).get("name") + ";";
                }
            }
        }
        hashmap.put("strzjtj", strzjtj);
        hashmap.put("zjtjList", lisyPmsprizeKpi1);
        //学科
        String sqlPmsprizeKpi2 = "select *,Row_Number()over(ORDER BY seq) as seqbook from PMS_PRIZE_SUBJECT where mainid=? and type='subject' order by seq";
        List<Map> lisyPmsprizeKpi2 = dbHelper.getRows(sqlPmsprizeKpi2, new Object[]{id});
        hashmap.put("xkList", lisyPmsprizeKpi2);
        //主要知识产权目录
        String sqlPmsprizeKpi4 = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(grantdate,'yyyy-MM-dd') as grantdatebook from PMS_PRIZE_PATENT where mainid=? and type='intellectualProperty' order by seq";
        List<Map> lisyPmsprizeKpi4 = dbHelper.getRows(sqlPmsprizeKpi4, new Object[]{id});
        hashmap.put("patent1List", lisyPmsprizeKpi4);
        //主要知识产权和标准规范等目录
        String sqlPmsprizeKpi5 = "select *,Row_Number()over(ORDER BY seq) as seqbook,(case when status='1' then '有效' when status='0' then '无效' else '' end) as statuscn,to_char(grantdate,'yyyy-MM-dd') as grantdatebook from PMS_PRIZE_PATENT where mainid=? and type='catalogue' order by seq";
        List<Map> lisyPmsprizeKpi5 = dbHelper.getRows(sqlPmsprizeKpi5, new Object[]{id});
        hashmap.put("patentList", lisyPmsprizeKpi5);
        //论文他引
        String sqlPmsprizeKpi3 = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(publishdate,'yyyy-MM-dd') as publishdatebook,name||'/'||firstauthor as namefirstauthor from PMS_PRIZE_THESIS_OTHER where mainid=? and type='papersQuote' order by seq";
        List<Map> lisyPmsprizeKpi3 = dbHelper.getRows(sqlPmsprizeKpi3, new Object[]{id});
        hashmap.put("paperyyList", lisyPmsprizeKpi3);
        //论文专著
        String sqlbzcl = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(publishdate,'yyyy-MM-dd') as publishdatebook,name||'/'||publicationname||'/'||authors as namepublicationnameauthors from PMS_PRIZE_THESIS where mainid=? and type='papers'  order by seq";
        List<Map> lisybzcl = dbHelper.getRows(sqlbzcl, new Object[]{id});
        hashmap.put("paperList", lisybzcl);
        //工作经历
        String sqlbzcl1 = "select *,Row_Number()over(ORDER BY seq) as seqbook,(to_char(startdate,'yyyy-MM-dd') ||'至'|| to_char(enddate,'yyyy-MM-dd'))as startdatebook,(post||'、'||title) as postcn from PMS_PRIZE_EXP where mainid=? and type='experience'  order by seq";
        List<Map> lisybzcl1 = dbHelper.getRows(sqlbzcl1, new Object[]{id});
        hashmap.put("workList", lisybzcl1);
        //曾获奖情况
        String sqlbzcl2 = "select *,Row_Number()over(ORDER BY seq) as seqbook,to_char(awarddate,'yyyy-MM-dd') as awarddatebook,(rank||'/'||ranking) as rankcn from PMS_PRIZE_AWARD where mainid=? and type='reward'  order by seq";
        List<Map> lisybzcl2 = dbHelper.getRows(sqlbzcl2, new Object[]{id});
        hashmap.put("awardList", lisybzcl2);

        //大字段
        List<Map> cloblist = this.dbHelper.getRows("select t.* from PMS_prize_CLOB t where t.mainid = ? ", new Object[]{id});
        String objname = "clob_";
        if (cloblist.size() > 0) {
            for (int i = 0; i < cloblist.size(); i++) {
                // 所有的大字段都进行特殊字符转换
                hashmap.put(objname + (String) cloblist.get(i).get("columnname"),
                        ((String) cloblist.get(i).get("source")));//书签用
                hashmap.put(objname + (String) cloblist.get(i).get("columnname") + "Pdf",
                        this.changeTopdf((String) cloblist.get(i).get("source")));
            }
        }
        return hashmap;
    }

    /*
     * 按一定的格式把不循环的数据放到hashmap
     * */
    public void ObjInHashMap(JSONObject obj, Map<String, Object> hashmap, String headcontent) {
        obj.keySet();
        Iterator<String> sIterator = obj.keys();
        while (sIterator.hasNext()) {
            // 获得key
            String key = sIterator.next();
            // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
            String value = obj.get(key) + "";
            if ("null".equals(obj.get(key))) {
                value = "";
            }
            hashmap.put(headcontent + "_" + key, value);
        }
    }

    /*
     * 按一定的格式把不循环的数据放到hashmap
     * */
    public void ObjInHashMapConver(JSONObject obj, Map<String, Object> hashmap, String headcontent) {
        obj.keySet();
        Iterator<String> sIterator = obj.keys();
        while (sIterator.hasNext()) {
            // 获得key
            String key = sIterator.next();
            // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
            String value = obj.get(key) + "";
            if ("null".equals(obj.get(key))) {
                value = "";
            }
            hashmap.put(headcontent + "_" + key, converRm(value));
        }
    }

    /*
     * 根据type,datatype,objname判断，将数据放到hashmap里
     * */
    public void dataToHashMap(String type, String datatype, Map<String, Object> hashmap, List<Map> listPis, String objname) {
        if (!("true".equals(type) && "normal".equals(datatype))) {//除了即是对象传参又为true的
            objname = type + objname;
        }
        if ("normal".equals(datatype)) {
            if (listPis.size() > 0) {
                //hashmap.put(objname, listPis.get(0));
                ObjInHashMap(JSONObject.fromObject(listPis.get(0)), hashmap, objname);
            } else {
                hashmap.put(objname, new JSONObject());
            }
        } else {
            if (listPis.size() > 0) {
                hashmap.put(objname, listPis);
            } else {
                hashmap.put(objname, new JSONArray());
            }
        }
    }

    /*如果数组个数少于最小数，就默认加到最小个数*/
    public List<Map> addForMinLength(List<Map> list, int Minlength) {
        if (list.size() < Minlength) {
            int needlength = Minlength - list.size();
            Map a = new HashMap<>();
            for (int i = 0; i < needlength; i++) {
                list.add(a);
            }
        }
        return list;
    }

    /**
     * 转义特殊字符
     *
     * @param content
     * @return
     */
    public static String converRm(String content) {
        if (content != null) {
            content = content.replaceAll("[\u0001-\u0009]", " ").replaceAll("\f", "").replaceAll("\u000B", "");//老系统乱码数据替换
        } else {
            content = "";
        }
        return content;
    }

    /**
     * 用来处理pdf数据
     *
     * @param str
     * @return
     */
    public static String changeTopdf(String str) {
        if (str != null) {
            String strForpdf = str.replaceAll("&", "&amp;");
            strForpdf = strForpdf.replaceAll("<", "&lt;");
            strForpdf = strForpdf.replaceAll(">", "&gt;");
            strForpdf = strForpdf.replaceAll("\n", "<w:br/>");
            strForpdf = strForpdf.replaceAll("\\u0003", "");//老系统乱码数据替换
            return strForpdf;
        } else {
            return "";
        }
    }
}
