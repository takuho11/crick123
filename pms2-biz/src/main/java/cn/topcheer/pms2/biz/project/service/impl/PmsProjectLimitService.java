package cn.topcheer.pms2.biz.project.service.impl;


import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.plan.entity.PmsPlancondition;
import cn.topcheer.pms2.dao.project.PmsProjectLimitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="PmsProjectLimitService")
public class PmsProjectLimitService  extends GenericService<PmsPlancondition> {

    public PmsProjectLimitDao getPmsProjectLimitDao() {
        return (PmsProjectLimitDao) this.getGenericDao();
    }
    @Autowired
    public void setPmsProjectLimitDao(PmsProjectLimitDao pmsProjectLimitDao) {
        this.setGenericDao(pmsProjectLimitDao);
    }

//    @Autowired
//    private PmsPlanprojectbatchService pmsPlanprojectbatchService;
//    @Autowired
//    private PmsProjectbaseService pmsProjectbaseService;
//    @Autowired
//    private PmsEnterpriseServiceIM pmsEnterpriseService;
//    @Autowired
//    private PmsProjectPartyService pmsProjectPartyService;
//    @Autowired
//    private SysUserinfoService sysUserinfoService;
//    @Autowired
//    private SysOperationrecordService sysOperationrecordService;
//    @Autowired
//    private PmsEnterpriseSblimitService pmsEnterpriseSblimitService;
//    @Autowired
//    private SysParamsAdditionalService sysParamsAdditionalService;
//    @Autowired
//    private PmsRecordLibraryService pmsRecordLibraryService;
//    @Autowired
//    private PmsEnterpriselimitService pmsEnterpriselimitService;
//    @Autowired
//    private BatchVersionService batchVersionService;

//    /**
//     * 【科技项目申报限制】 -- 入口+上报时判断
//     */
//    public JSONObject jugdeCondition(SysUser user, String projectbaseid, String batchid, HttpServletRequest request){
//        JSONObject resJson = new JSONObject();
//
//        //==============必须判断的条件==================
//        if(Util.isEoN(batchid)){
//            resJson.put("success",false);
//            resJson.put("reason","当前批次id为空。");
//            return resJson;
//        }
//        if(Util.isEoN(user)){
//            resJson.put("success",false);
//            resJson.put("reason","未获取到当前用户信息。");
//            return resJson;
//        }
//        PmsPlanprojectbatch batch = this.pmsPlanprojectbatchService.findById(batchid);
//        BatchVersion bv = this.batchVersionService.findById(batchid);
//        //判断是否配置了科技项目申报模板
//        if(Util.isEoN(bv.getSb())){
//            resJson.put("success",false);
//            resJson.put("reason","当前批次没有配置科技项目申报权限。");
//            return resJson;
//        }
//
//        //先获取当前批次对应的所有条件
//        List<Map> conditionList = this.getListBySql("select e.*,c.code as conditioncode from limit_config e" +
//                " left join limit_condition c on e.conditionid = c.id" +
//                " where e.batchid = ? and c.type = 'sb' order by c.seq",new Object[]{batchid});
//        if(conditionList.size()==0){
//            resJson.put("success",true);
//            return resJson;
//        }
//
//        //==============需要的参数处理==================
//        Boolean issb = true;//true表示上报，false表示入口
//        PmsProjectbase base = null;//当前项目对象
//        String username="";//负责人姓名
//        String certificatenumber="";//负责人身份证
//        PmsEnterprise unit = null;
//        String enterpriseid = "";//单位id
//        String organizationname="";//依托单位名称
//        String shehuixingyong="";//依托单位统一社会信用代码
//        if(Util.isEoN(projectbaseid)){
//            issb = false;//true表示上报，false表示入口
//            enterpriseid = this.getOnlyValueBySql("select e.purvieworganizeid as eid from sys_identity e where e.userid = ? and rownum < 2",new Object[]{user.getId()});
//            unit = this.pmsEnterpriseService.findById(enterpriseid);
//            organizationname=unit.getName();
//            shehuixingyong=unit.getUniformcode();
//            username=user.getName();
//            certificatenumber=user.getCertificateno();
//        }else{
//            base = this.pmsProjectbaseService.findById(projectbaseid);//当前项目对象
//            List<Map> list_rydw = this.getListBySql("select distinct base.id,fzr.name,fzr.certificatenumber,cddw.organizationname,cddw.shehuixingyong  " +
//                    " from pms_projectbase base " +
//                    " left join pms_projectparty fzr on base.id = fzr.projectbaseid and fzr.rytype = 'true' " +
//                    " left join pms_enterpriseorganization cddw on base.id = cddw.projectbaseid and cddw.ismainenterprise = 'true'" +
//                    " where base.id = ? ",new Object[]{projectbaseid});
//            if(list_rydw!=null&&list_rydw.size()>0){
//                username = list_rydw.get(0).get("name")+"";
//                certificatenumber = list_rydw.get(0).get("certificatenumber")+"";
//                organizationname = list_rydw.get(0).get("organizationname")+"";
//                shehuixingyong = list_rydw.get(0).get("shehuixingyong")+"";
//            }
//            enterpriseid = base.getEnterpriseid();
//            unit = pmsEnterpriseService.findById(enterpriseid);
//        }
//        return this.forConditionsArr(conditionList,resJson,issb,user,batch,batchid,base,projectbaseid,
//                                username,certificatenumber,unit,enterpriseid,organizationname,shehuixingyong,request);
//    }
//
//    /**
//     * 【科技项目申报限制】 -- 根据方法名称去执行对应方法
//     */
//    public JSONObject forConditionsArr(List<Map> conditionList,JSONObject resJson,Boolean issb,SysUser user,
//                                       PmsPlanprojectbatch batch,String batchid,PmsProjectbase base,String projectbaseid,
//                                       String username,String certificatenumber,PmsEnterprise unit,String enterpriseid,String organizationname,String shehuixingyong,
//                                       HttpServletRequest request){
//        JSONObject recordJson = new JSONObject();//保留记录
//        for (Map conditionMap : conditionList) {
//            //条件代码
//            String conditionCode = conditionMap.get("conditioncode") + "";
//            //配置参数对象
//            String param = conditionMap.get("param") + "";
//            JSONObject paramJson = new JSONObject();
//            if (!Util.isEoN(param)) {
//                paramJson = JSONObject.fromObject(param);
//            }
//            switch (conditionCode) {
////                case "judgeFzrBirthday":
////                    resJson = this.judgeFzrBirthday(issb,batch,user,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeFzrZcAndXw":
////                    resJson = this.judgeFzrZcAndXw(issb,user,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeFzrNgzsj":
////                    resJson = this.judgeFzrNgzsj(issb,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeFzrLxq":
////                    resJson = this.judgeFzrLxq(issb,user,batchid,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeFzrZyq":
////                    resJson = this.judgeFzrZyq(issb,user,batchid,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeFzrXyjl":
////                    resJson = this.judgeFzrXyjl(issb,user,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeCyryLxq":
////                    resJson = this.judgeCyryLxq(issb,batchid,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeCyryZyq":
////                    resJson = this.judgeCyryZyq(issb,batchid,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeCyryLxqAndZyq":
////                    resJson = this.judgeCyryLxqAndZyq(issb,batchid,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeCyryGzdw":
////                    resJson = this.judgeCyryGzdw(issb,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeCyryNum":
////                    resJson = this.judgeCyryNum(issb,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeCyryZcxw":
////                    resJson = this.judgeCyryZcxw(issb,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeYtdwZyqNum":
////                    resJson = this.judgeYtdwZyqNum(batchid,unit,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeYtdwAndFzrZcxw":
////                    resJson = this.judgeYtdwAndFzrZcxw(issb,user,unit,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeYtdwXyjl":
////                    resJson = this.judgeYtdwXyjl(unit,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeYtdwInData":
////                    resJson = this.judgeYtdwInData(batchid,unit,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeDwNum":
////                    resJson = this.judgeDwNum(issb,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeSrAndrynum":
////                    resJson = this.judgeSrAndrynum(issb,batch,batchid,projectbaseid,unit);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeGjsjzzcc":
////                    resJson = this.judgeGjsjzzcc(issb,batchid,base);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeBzzj":
////                    resJson = this.judgeBzzj(issb,batchid,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "judgeZyyddf":
////                    resJson = this.judgeZyyddf(issb,batch,batchid,base,unit);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "zrjj_judgeFzrLxq":
////                    resJson = this.zrjj_judgeFzrLxq(issb,user,batchid,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "zrjj_judgeFzrZyq":
////                    resJson = this.zrjj_judgeFzrZyq(issb,user,batchid,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "zrjj_judgeFzrSameBatchSb":
////                    resJson = this.zrjj_judgeFzrSameBatchSb(issb,user,batch,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "zrjj_judgeCyryLxq":
////                    resJson = this.zrjj_judgeCyryLxq(issb,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "zrjj_judgeCyryScale":
////                    resJson = this.zrjj_judgeCyryScale(issb,projectbaseid,paramJson);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
////                case "zrjj_judgeYtdwZb":
////                    resJson = this.zrjj_judgeYtdwZb(unit,batchid);
////                    recordJson.put(conditionCode,resJson.toString());
////                    break;
//                default:
//                    //找不到对应的现在判断方法，默认不通过
//                    resJson.put("success",false);
//                    resJson.put("reason","后台限制配置有误，请联系系统维护员。");
//                    break;
//            }
//            if(!resJson.getBoolean("success")){
//                break;
//            }
//        }
//        this.sysOperationrecordService.commonSaveOperation(request,Util.isEoN(projectbaseid)?user.getId():projectbaseid,"科技项目-申报验证",recordJson.toString());
//        return resJson;
//    }
//
//
//    /**
//     * =============================================================以下是限制条件=============================================================
//     */


}