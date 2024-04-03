package cn.topcheer.pms2.biz.officeHall.impl;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.api.plan.entity.PmsPlanproject;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.enums.GeneralRoleEnum;
import cn.topcheer.pms2.api.sys.enums.UserLimitEnum;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.common.enumerate.ErrorCodeEnum;
import cn.topcheer.pms2.common.exception.BusinessException;
import cn.topcheer.pms2.dao.officeHall.OfficeHallDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.topcheer.pms2.api.sys.enums.GeneralRoleEnum.ORDINARY_PEOPLE;

@Service(value="OfficeHallService")
public class OfficeHallService extends GenericService<PmsPlanproject> {

    public OfficeHallDao getOfficeHallDao() {
        return (OfficeHallDao) this.getGenericDao();
    }
    @Autowired
    public void setOfficeHallDao(OfficeHallDao officeHallDao) {
        this.setGenericDao(officeHallDao);
    }
    @Autowired
    DBHelper dbHelper;

    @Autowired
    private SysUserService sysUserService;


    /**
     * 【办事大厅】 -- 获取分类数据
     */
    public List<Map> getSort(JSONObject json){
        List<Map> resList = new ArrayList<>();
        String system = json.get("system")+"";//系统类型，目前是 all or guide_collect or no_guide_collect
        String batchtype = json.get("batchtype")+"";//是获取大批次or小批次
        String content = json.get("content")+"";//全局搜索内容
        String transactcode = json.get("transactcode")+"";//政务网办理code
        //1、项目类型
        Map map1 = new HashMap<>();
        String name1 = "项目类型";
        map1.put("name",name1);
        map1.put("key","innovatechain");
        map1.put("data",this.getDataForSort(name1,system,batchtype,content,transactcode));
        resList.add(map1);
        //2、业务处室
        Map map2 = new HashMap<>();
        String name2 = "业务处室";
        map2.put("name",name2);
        map2.put("key","department");
        map2.put("data",this.getDataForSort(name2,system,batchtype,content,transactcode));
        resList.add(map2);
        //3、计划类型
        Map map3 = new HashMap<>();
        String name3 = "计划类型";
        map3.put("name",name3);
        map3.put("key","plantype");
        map3.put("data",this.getDataForSort(name3,system,batchtype,content,transactcode));
        resList.add(map3);
        return resList;
    }


    /**
     * 【办事大厅】 -- 辅助方法：根据name获取分类数据
     */
    private List<Map> getDataForSort(String name,String system,String batchtype,String content,String transactcode){
        List<Map> resList = new ArrayList<>();
        String sql = "";//最终sql
        String insql = "";//条件sql
        String table = "";//主表名称
        //========处理条件语句========
        if("guide_collect".equals(system)){
            //说明是需求征集，在办事大厅是单独模块
            insql = " and e.system = 'guide_collect' ";
        }else if("no_guide_collect".equals(system)){
            //说明是非需求征集
            insql = " and e.system <> 'guide_collect' and e.system is not null ";
        } else{
            //说明是全部类型展示（全局搜索时用到）
            insql = " and e.system is not null ";
        }
        //========处理主表名称========
        if("small".equals(batchtype)){
            //说明是小批次
            table = " pms_planprojectbatch ";
            //判断是否为全局搜索 和 政务网办理
            if(Util.isEoN(content)&&Util.isEoN(transactcode)){
                //说明不是全局搜索 和 政务网办理，只取在申报期的数据
                insql = insql + " and sysdate>=e.starttime and sysdate<=e.endtime  ";
            }else if(!Util.isEoN(content)){
                //说明是全局搜索，取搜索到的所有数据（无论是否在申报期）
                insql = insql + " and e.name like '%"+content+"%' ";
            }else if(!Util.isEoN(transactcode)){
                //说明是全局搜索，取搜索到的所有数据（无论是否在申报期）
                insql = insql + " and e.transactcode = '"+transactcode+"' ";
            }
        }else if("big".equals(batchtype)){
            //说明是“所有业务类型”，但是统计项，需要根据大批次下所有小批次的情况进行统计，包括申报期和非申报期
            table = " pms_planprojectbatch ";
        }
        //========根据name处理最终sql========
        switch (name){
            case "项目类型":
                sql = "select e.innovatechain as name,count(e.innovatechain) as num " +
                        " from "+table+" e  " +
                        " where e.innovatechain is not null and e.ishallshow = '1' " + insql +
                        " group by e.innovatechain " +
                        " order by e.innovatechain ";
                break;
            case "业务处室":
                sql = "select d.name ,count(d.name) as num " +
                        " from "+table+" e  " +
                        " left join pms_department d on e.departmentid = d.id " +
                        " where e.departmentid is not null and e.ishallshow = '1' " +insql +
                        " group by d.name";
                break;
            case "计划类型":
                sql = "select e.applicationtypename1 as name,count(e.applicationtypename1) as num " +
                        " from "+table+" e  " +
                        " where e.applicationtypename1 is not null and e.ishallshow = '1' " + insql +
                        " group by e.applicationtypename1 " +
                        " order by decode(e.applicationtypename1, '技术创新计划', 1, '科技创新人才计划', 2, '区域科技创新计划', 3, '基础研究计划', 4,'科技创新基地（平台）计划', 5, '其他', 99)";
                break;
            default:
                return resList;
        }
        //“全部”的对象
        Map allMap = new HashMap<>();
        allMap.put("name",name);
        //数组
        List<Map> list = this.getListBySql(sql,new Object[]{});
        int totalNum = 0;//全部数量
        for(Map m : list){
            String numStr = m.get("num")+"";
            int num = Util.isEoN(numStr)?0:Integer.parseInt(numStr);
            totalNum = totalNum +num;
        }
        allMap.put("num",totalNum);
        resList.add(allMap);
        resList.addAll(list);
        return resList;
    }


    /**
     * 【办事大厅】 -- 根据搜索条件获取大批次或小批次
     */
    public List<Map> getPlanprojetData(JSONObject json){
        String sql = "";//最终sql
        //===============前台参数处理===============
        String system = json.get("system")+"";//系统类型，目前是 all or guide_collect or no_guide_collect
        String batchtype = json.get("batchtype")+"";//是获取大批次or小批次
        String innovatechain = json.get("innovatechain")+"";//项目类型
        String planProjectId = json.get("planProjectId") + "";//批次id
        String department = json.get("department")+"";//业务处室
        String plantype = json.get("plantype")+"";//计划类型
        String content =  json.get("content")+"";//全局搜索内容
        String areaCode = json.get("areaCode") + "";//区域代码
        String transactcode = json.get("transactcode")+"";//政务网办理code
        //===============条件语句处理===============
        StringBuilder inSql = new StringBuilder();//组合条件语句
        //判断system
        if("guide_collect".equals(system)){
            //说明是需求征集，在办事大厅是单独模块
            inSql.append(" and e.system = 'guide_collect' ");
        }else if("no_guide_collect".equals(system)){
            //说明是非需求征集
            inSql.append(" and e.system <> 'guide_collect' and e.system is not null ");
        }else{
            //说明是全部类型展示（全局搜索时用到）
            inSql.append("  and e.system is not null ");
        }
        //判断是否有innovatechain
        if(!Util.isEoN(innovatechain)&&!innovatechain.contains("项目类型")){
            inSql.append(" and e.innovatechain in  "+ "('"+innovatechain.replaceAll(",","','")+"') ");
        }
        //判断是否有planProjectId
        if(!Util.isEoN(planProjectId)&&!planProjectId.contains("批次")){
            inSql.append(" and e.planprojectid in  "+ "('"+planProjectId.replaceAll(",","','")+"') ");
        }
        //判断是否有department
        if(!Util.isEoN(department)&&!department.contains("业务处室")){
            inSql.append(" and d.departmentcode in  "+ "('"+department.replaceAll(",","','")+"') ");
        }
        //判断是否有plantype
        if(!Util.isEoN(plantype)&&!plantype.contains("计划类型")){
            inSql.append(" and e.applicationtypename1 in  "+ "('"+plantype.replaceAll(",","','")+"') ");
        }
        //判断是否有areaCode
        if(!Util.isEoN(areaCode)&&!areaCode.contains("区域代码")){
            inSql.append(" and p.areaid =  "+ "'"+areaCode+"' ");
        }
        //===============处理最终sql===============
        //,g.release
        String smallSql = "select e.id as batchid,e.name,e.applicationtypename1 as plantype,d.name as department,b.projectname,e.system,e.transactcode," +
                "z.mini_biz_def_id,z.mini_biz_version_id,z.planproject_type," +
                " (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                "  then '申报期：'||to_char(e.starttime,'yyyy-MM-dd  HH24:mi:ss')||' -- '||to_char(e.endtime,'yyyy-MM-dd  HH24:mi:ss') " +
                " else '未在申报期内' end ) as sbtips, " +
                " (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                " then 1 else 0 end) as issb " +
                " from pms_planprojectbatch e " +
                " left join pms_planproject b on e.planprojectid = b.id " +
                " left join pms_department d on e.departmentid = d.id " +
//                " left join batch_guide g on e.id = g.id" +
                " left join PMS_PLANPROJECTBATCH_IN_BIZ z on e.id = z.pms_planprojectbatch_id"+
                " left join pms_enterprise p on b.company_id = p.id"+
                " where e.ishallshow = '1' and b.ishallshow = '1'";
        String bigSql = "select e.id as bigbatchid,e.projectname as name,e.applicationtypename1 as plantype,e.system,  " +
                " d.name as department,e.innovatechain   " +
                " from pms_planproject e   " +
                " left join pms_department d on e.departmentid = d.id  " +
                " where e.ishallshow = '1' ";
        if("small".equals(batchtype)){
            //----------说明是小批次----------
            String orderSql = "";
            if(Util.isEoN(content)&&Util.isEoN(transactcode)){
                //说明不是全局搜索 和 政务网办理，只取在申报期的数据
                inSql.append(" and sysdate>=e.starttime and sysdate<=e.endtime  ");
                orderSql = " order by e.departmentid ";
            }else if(!Util.isEoN(content)){
                //说明是全局搜索，取搜索到的所有数据（无论是否在申报期）
                inSql.append(" and (e.name like '%"+content+"%' or b.projectname like '%"+content+"%')  ");
                orderSql = " order by  (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                        "      then 1 else 0 end " +
                        "  ) desc,e.departmentid";
            }else if(!Util.isEoN(transactcode)){
                //说明是政务网办理，取搜索到的所有数据（无论是否在申报期）
                inSql.append(" and e.transactcode = '"+transactcode+"' ");
                orderSql = " order by  (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                        "      then 1 else 0 end " +
                        "  ) desc,e.departmentid";
            }
            sql = smallSql + inSql.toString() + orderSql;
        }else{
            //----------说明是大批次----------
            if((!Util.isEoN(innovatechain)&&!innovatechain.contains("项目类型"))
                ||(!Util.isEoN(department)&&!department.contains("业务处室"))
                ||(!Util.isEoN(plantype)&&!plantype.contains("计划类型"))){
                //说明有进行过滤搜索，那么就需要展示小批次的数据
                sql = smallSql + inSql.toString() + " order by e.departmentid ";

            }else{
                //说明是默认状态，展示大批次的数据
                sql = bigSql +inSql.toString()+" order by e.innovatechain,e.projectname";;
            }
        }
//        this.dbHelper.getRows(sql, null);
        return this.dbHelper.getRows(sql,null);
    }


    /**
     * 【办事大厅】 -- 根据大批次id获取对应小批次数据
     */
    public List<Map> getBatchData(String bigbatchid){
        return this.dbHelper.getRows("select e.id as batchid,e.name,e.applicationtypename1 as plantype,d.name as department,e.innovatechain,e.system,g.release, " +
                " (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                "  then '申报期：'||to_char(e.starttime,'yyyy-MM-dd  HH24:mi:ss')||' -- '||to_char(e.endtime,'yyyy-MM-dd  HH24:mi:ss') " +
                " else '未在申报期内' end ) as sbtips, " +
                " (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                " then 1 else 0 end) as issb " +
                " from pms_planprojectbatch e " +
                " left join pms_department d on e.departmentid = d.id " +
                " left join batch_guide g on e.id = g.id" +
                " where e.ishallshow = '1' and e.planprojectid = ? " +
                " order by  " +
                "  (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                "      then 1 else 0 end " +
                "  ) desc,e.departmentid",new Object[]{bigbatchid});
    }


    /**
     * 【办事大厅】 -- 获取建议征集的东西
     */
    public List<Map> getGuideCollectData(){
        return this.dbHelper.getRows("select e.id as batchid,e.name,e.applicationtypename1 as plantype,d.name as department,e.innovatechain,e.system,g.release, " +
                " (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                "  then '申报期：'||to_char(e.starttime,'yyyy-MM-dd  HH24:mi:ss')||' -- '||to_char(e.endtime,'yyyy-MM-dd  HH24:mi:ss') " +
                " else '未在申报期内' end ) as sbtips, " +
                " (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                " then 1 else 0 end) as issb " +
                " from pms_planprojectbatch e " +
                " left join pms_department d on e.departmentid = d.id " +
                " left join batch_guide g on e.id = g.id" +
                " where e.ishallshow = '1' and e.system = 'guide_collect' " +
                " order by  " +
                "  (case when (e.starttime is not null and e.endtime is not null and sysdate>=e.starttime and sysdate<=e.endtime) " +
                "      then 1 else 0 end " +
                "  ) desc,e.departmentid",new Object[]{});
    }




    /**
     * 【首页】 -- 通过批次id和system获取当前账号已有数据
     */
    public List<Map> getOwnData(String batchid ,String system){
        List<Map> resList = new ArrayList<>();
        //各个层获取用户
        //条件语句
        String insql = "";
        List<String> paramList = new ArrayList<>();
        paramList.add(AuthUtil.getUserId());
        paramList.add(batchid);
        //sql
        String sql = "select e.id,e.projectbasename,e.mainorganizers as cddwname,e.projectleader as fzrname,e.minicurrentstate from ";
        switch (system){
            case "guide_collect":
                //需求征集
                sql = "pms_guide_collect e ";
                break;
            case "kjxm":
                //项目
                sql = "pms_projectbase e ";
                break;
            case "kjcxpt":
                //平台
                sql = "pms_innovationbase e ";
                break;
            default:
                return resList;
        }
        return this.getListBySql(sql+" where e.declarantid = ? and e.planprojectbatchid = ? "+insql,paramList.toArray());
    }

    /**
     * 【首页】--全局搜索
     */
    public JSONObject globalSearch(JSONObject json){
        JSONObject resJson = new JSONObject();
        String content = json.get("content")+"";
        if(Util.isEoN(content)){
            throw new BusinessException(ErrorCodeEnum.PARAM_COMPLETE_ERROR);
        }
        int count = 0;//搜索总条数
        //====================搜索小批次（有显示标记、在申报期的排在前面）====================
        JSONObject paramJson = new JSONObject();
        paramJson.put("system","all");paramJson.put("batchtype","small");paramJson.put("content",content);
        List<Map> xmsbList = this.getPlanprojetData(paramJson);
        count = count+xmsbList.size();
        resJson.put("xmsbList",xmsbList);
        //====================科技资讯+公示公告====================
        List<Map> titleList = this.dbHelper.getRows("select e.id,e.title as name,e.type, " +
                " to_char(e.releasetime,'yyyy-mm-dd') as releasetime,e.otherurl  " +
                " from pms_announcement e  " +
                " where (e.type = '科技要闻' or e.type = '工作动态' or e.type = '各区动态' or e.type = '文件公告') " +
                " and e.title like ? and e.releasetime is not null order by e.type,e.releasetime desc",new Object[]{"%"+content+"%"});
        //------科技要闻------
        List<Map> kjywList = titleList.stream().filter(m -> "科技要闻".equals(m.get("type"))).collect(Collectors.toList());
        count = count+kjywList.size();
        resJson.put("kjywList",kjywList);
        //------工作动态------
        List<Map> gzdtList = titleList.stream().filter(m -> "工作动态".equals(m.get("type"))).collect(Collectors.toList());
        count = count+gzdtList.size();
        resJson.put("gzdtList",gzdtList);
        //------各区动态------
        List<Map> gqdtList = titleList.stream().filter(m -> "各区动态".equals(m.get("type"))).collect(Collectors.toList());
        count = count+gqdtList.size();
        resJson.put("gqdtList",gqdtList);
        //------文件公告------
        List<Map> wjggList = titleList.stream().filter(m -> "文件公告".equals(m.get("type"))).collect(Collectors.toList());
        count = count+wjggList.size();
        resJson.put("wjggList",wjggList);
        //====================合计数量====================
        resJson.put("count",count);
        return resJson;
    }



    /**
     * 获取同时在申报期内的小批次
     */
    public List<Map> getSbqBatch(String bigbatchid){
        return this.dbHelper.getRows("select b.id as batchid,b.name as batchname,  " +
                " ('申报期：'||to_char(e.starttime,'yyyy-MM-dd  HH24:mi:ss')||' -- '||to_char(e.endtime,'yyyy-MM-dd  HH24:mi:ss')) as sbtips,b.ismainfx " +
                " from pms_planprojectbatch b  " +
                " where b.starttime is not null and b.endtime is not null   " +
                " and sysdate>=b.starttime and sysdate<=b.endtime  " +
                " and b.planprojectid = ?  " +
                " order by b.annually",new Object[]{bigbatchid});
    }


    public ReturnToJs getPlanProject(JSONObject json) {
        String userId = AuthUtil.getUserId();
        if (Util.isEoN(userId)) {
            return ReturnToJs.failure("获取用户信息失败");
        }
        String sql = "";//最终sql
        //===============前台参数处理===============
        StringBuilder inSql = new StringBuilder();//组合条件语句
        String batchtype = json.get("batchtype")+"";//是获取大批次or小批次
        String transactcode = json.get("planid") + "";//政务网办理code
        if (batchtype.equals("big")) {
            if (!Util.isEoN(transactcode) && !transactcode.equals("businesshall")) {
                inSql.append(" and pro.MATTER_CODE = '" + transactcode + "'");
            }
            String bigSql = "select pro.id as bigbatchid,pro.projectname as name,pro.applicationtypename1 as plantype,pro.system,  " +
                    " d.name as department,pro.innovatechain   " +
                    " from pms_planproject pro  " +
                    " left join pms_department d on pro.departmentid = d.id  " +
                    " where pro.ishallshow = '1' ";
            sql = bigSql + inSql;
        }else {
            if (!Util.isEoN(transactcode) && !transactcode.equals("businesshall")) {
                inSql.append(" and pro.MATTER_CODE = '" + transactcode + "'");
            }
            String smallSql = "select batch.id as batchid,batch.name,batch.applicationtypename1 as plantype,d.name as department,pro.projectname," +
                    "batch.system,batch.transactcode,z.mini_biz_def_id,z.mini_biz_version_id,z.planproject_type, " +
                    "(case when (batch.starttime is not null and batch.endtime is not null and sysdate>=batch.starttime and sysdate<=batch.endtime)   " +
                    "then '申报期：'||to_char(batch.starttime,'yyyy-MM-dd  HH24:mi:ss')||' -- '||to_char(batch.endtime,'yyyy-MM-dd  HH24:mi:ss')  else '未在申报期内' end ) as sbtips, " +
                    "(case when (batch.starttime is not null and batch.endtime is not null and sysdate>=batch.starttime and sysdate<=batch.endtime) then 1 else 0 end) as issb  " +
                    "from pms_planprojectbatch batch  " +
                    "left join pms_planproject pro on batch.planprojectid = pro.id  " +
                    "left join pms_department d on batch.departmentid = d.id  " +
                    "left join PMS_PLANPROJECTBATCH_IN_BIZ z on batch.id = z.pms_planprojectbatch_id " +
                    "left join pms_enterprise p on pro.company_id = p.id " +
                    "where batch.ishallshow = '1' and pro.ishallshow = '1'";
            sql = smallSql + inSql;
        }
        return ReturnToJs.success(dbHelper.getRows(sql, null));
    }


    public Result judgeUser() {
        Result<Object> result = new Result<>();
        SysUser user = sysUserService.getById(AuthUtil.getUserId());
        if (user == null) {
            result.setMsg(UserLimitEnum.NOT_LOGIN.getCode());
            result.setCode(200);
            return result;
        }
        String state = user.getMinicurrentstate();
        if (Util.isEoN(state)) {
            state = "";
        }
        if (state.contains("完善信息完成") || state.contains("变更主体完成")) {
            result.setMsg(UserLimitEnum.ALL_ALLOW.getCode());
        } else if (!Util.isEoN(state)) {
            result.setMsg(UserLimitEnum.WAIT_EXAMINE.getCode());
        }else {
            result.setMsg(UserLimitEnum.NEED_COM_SUB.getCode());
        }
        String roleId = user.getRoleId();
        if (GeneralRoleEnum.ORDINARY_PEOPLE.getRoleId().equals(roleId) || GeneralRoleEnum.NEW_PEOPLE.getRoleId().equals(roleId)){
            result.setData("gr");
        } else if (GeneralRoleEnum.ORDINARY_ENTERPRISE.getRoleId().equals(roleId) || GeneralRoleEnum.NEW_UNIT.getRoleId().equals(roleId)) {
            result.setData("fr");
        }
        result.setCode(200);
        return result;
    }

    /**
     * 【办事大厅】 -- 根据搜索条件获取大批次或小批次
     */
    public List<Map> getPlanprojetOrBatch(JSONObject json){
        String sql = "";//最终sql
        //===============前台参数处理===============
        String batchtype = json.get("batchtype")+"";//是获取大批次or小批次
        String planProjectId = json.get("planProjectId") + "";//批次id
        String department = json.get("department")+"";//业务处室
        String content =  json.get("content")+"";//全局搜索内容
        String areaCode = json.get("areaCode") + "";//区域代码
        String enterpriseid = String.valueOf(json.get("enterpriseid"));//单位id
        //===============条件语句处理===============
        StringBuilder inSql = new StringBuilder();//组合条件语句
        //判断是否有planProjectId
        if(!Util.isEoN(planProjectId)&&!planProjectId.contains("批次")){
            inSql.append(" and batch.planprojectid in  "+ "('"+planProjectId.replaceAll(",","','")+"') ");
        }
        //判断是否有department
        if(!Util.isEoN(department)&&!department.contains("业务处室")){
            inSql.append(" and batch.ITEM_ORGAN_CODE in  "+ "('"+department.replaceAll(",","','")+"') ");
        }
        //判断是否有enterpriseid，无则再判断是否后areaCode
        if (!Util.isEoN(enterpriseid) && !"null".equals(enterpriseid)) {
            inSql.append(" and pro.organize_id ='" + enterpriseid + "'");
        }
        if(!Util.isEoN(areaCode)&&!areaCode.contains("区域代码") && !"52".equals(areaCode)){
            inSql.append(" and ent.areaid = '"+areaCode+"' ");
        }
        //判断是否有全局搜索
        if (!Util.isEoN(content)) {
            content = "'%"+content+"%'" ;
            inSql.append(" and ( batch.name like " + content + " or pro.projectname like " + content + ")");
        }
        //===============处理最终sql===============
        //,g.release
        String smallSql = "SELECT batch.id AS batchid, batch.name, batch.applicationtypename1 AS plantype, " +
                " d.name AS department, pro.projectname , batch.system, batch.transactcode, z.mini_biz_def_id, " +
                " z.mini_biz_version_id, z.planproject_type, " +
                " CASE WHEN batch.starttime IS NOT NULL " +
                "   AND batch.endtime IS NOT NULL " +
                "   AND sysdate >= batch.starttime " +
                "   AND sysdate <= batch.endtime " +
                " THEN '申报期：'" +
                "   || to_char(batch.starttime, 'yyyy-MM-dd  HH24:mi:ss')" +
                "   || ' -- ' " +
                "   || to_char(batch.endtime, 'yyyy-MM-dd  HH24:mi:ss')" +
                " ELSE '未在申报期内' END AS sbtips , " +
                " CASE WHEN batch.starttime IS NOT NULL " +
                "   AND batch.endtime IS NOT NULL " +
                "   AND sysdate >= batch.starttime " +
                "   AND sysdate <= batch.endtime " +
                " THEN 1 ELSE 0 END AS issb " +
                " FROM pms_planprojectbatch batch " +
                "   LEFT JOIN pms_planproject pro ON batch.planprojectid = pro.id " +
                "   LEFT JOIN pms_department d ON batch.ITEM_ORGAN_CODE = d.id " +
                "   LEFT JOIN PMS_PLANPROJECTBATCH_IN_BIZ z ON batch.id = z.pms_planprojectbatch_id " +
                "   LEFT JOIN Pms_Enterprise ent on pro.organize_id = ent.id" +
                " WHERE batch.ishallshow = '1'" +
                " AND pro.ishallshow = '1'";
        String bigSql = "SELECT pro.id AS bigbatchid, pro.projectname AS name, " +
                " pro.applicationtypename1 AS plantype, pro.system, d.name AS department, pro.innovatechain" +
                " FROM pms_planproject pro " +
                " LEFT JOIN pms_department d ON pro.departmentid = d.id " +
                " LEFT JOIN Pms_Enterprise ent on pro.organize_id = ent.id" +
                " WHERE pro.ishallshow = '1'";
        if("small".equals(batchtype)){
            //----------说明是小批次----------
            String orderSql = "";
                //说明是全局搜索，取搜索到的所有数据（无论是否在申报期）
                orderSql = " order by  (case when (batch.starttime is not null and batch.endtime is not null and sysdate>=batch.starttime and sysdate<=batch.endtime) " +
                        "      then 1 else 0 end " +
                        "  ) desc,batch.ITEM_ORGAN_CODE";
            sql = smallSql + inSql.toString() + orderSql;
        }else{
            //----------说明是大批次----------
            if(!Util.isEoN(department)&&!department.contains("业务处室")){
                //判断是否有department
                if(!Util.isEoN(department)&&!department.contains("业务处室")){
                    inSql.append(" and pro.departmentid in  "+ "('"+department.replaceAll(",","','")+"') ");
                }
                //说明有进行过滤搜索，那么就需要展示小批次的数据
                sql = smallSql + inSql.toString() + " order by pro.departmentid ";
            }else{
                //说明是默认状态，展示大批次的数据
                sql = bigSql +inSql.toString()+" order by pro.projectname";;
            }
        }
//        this.dbHelper.getRows(sql, null);
        return this.dbHelper.getRows(sql,null);
    }

    public ReturnToJs getRegionAndEnterprise(JSONObject json) {
        String region = "";
        String enterpriseId = "";
        String sql = "";
        Map<String, Object> map = new HashMap<>();
        if (!json.containsKey("region") || !json.containsKey("enterpriseId")) {
            return ReturnToJs.failure("请检查参数");
        }
        if (!Util.isEoN(json.get("region"))){
            region = json.getString("region");
        }else {
            region = "50";
        }
        sql="select name as name ,code as region from SF_REGION where parent_code = ?";
        List<Map> rows = dbHelper.getRows(sql, new Object[]{region});
        if (rows == null) {
            rows = new ArrayList<>();
        }
        map.put("region", rows);

        if (!Util.isEoN(json.get("enterpriseId"))) {
            enterpriseId = json.getString("enterpriseId");
            sql = "";
        }else {
            map.put("enterprise", new ArrayList<>());
        }

        return ReturnToJs.success(map);
    }
}
