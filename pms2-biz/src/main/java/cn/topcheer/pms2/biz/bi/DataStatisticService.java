package cn.topcheer.pms2.biz.bi;

import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.annotation.FieldDes;
import cn.topcheer.pms2.api.pml.vo.Page;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxInfoUpdate;
import cn.topcheer.pms2.biz.pml.service.impl.PageService;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author:shenxian
 * @othername:liuxue
 * @Date:2024-03-12 14:47
 */
@Service(value="DataStatisticService")
public class DataStatisticService {

    @Autowired
    private DBHelper dbHelper;
    @Autowired
    private PageService pageService;

    public List findStatisticForQYLocation(){
        String sql = "SELECT count(*) as num, case when bei.city then city else '其他' end as city " +
                "from PMS_ENTERPRISE pe " +
                "left join bi_ent_bi bei on pe.id = bei.MAINID " +
                "GROUP by case when bei.city then city else '其他' end, bei.PROVINCE HAVING bei.PROVINCE = '贵州省'";
        List<Map> list = this.dbHelper.getRows(sql, null);
        return list;
    }

    public List findStatisticForQYRegisterCaptial(){
        String sql = "select sum(case when bei.REGISTERCAPITAL < 50 or bei.REGISTERCAPITAL is null then 1 else 0 end ) as under50, " +
                "sum(case when bei.REGISTERCAPITAL >= 50 and bei.REGISTERCAPITAL < 500 then 1 else 0 end ) as more50to500, " +
                "sum(case when bei.REGISTERCAPITAL >= 500 and bei.REGISTERCAPITAL < 3000 then 1 else 0 end ) as more500to3000, " +
                "sum(case when bei.REGISTERCAPITAL >= 3000 then 1 else 0 end ) as morethan3000  " +
                "from bi_ent_bi bei " +
                "left join PMS_ENTERPRISE pe on pe.id = bei.MAINID " +
                "left join sys_identity si on si.PURVIEWORGANIZEID = pe.id " +
                "left join sys_user su on su.ID = si.USERID " +
                "WHERE si.ROLEID = 'C7004168-4E0C-4F1F-B341-A225B5644DC5'";
        List<Map> list = this.dbHelper.getRows(sql, null);
        Map map = list.get(0);
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        Map map4 = new HashMap();
        map1.put("text","50万以下");
        map1.put("count",map.get("under50"));
        map2.put("text","50万（含）-500万");
        map2.put("count",map.get("more50to500"));
        map3.put("text","500万（含）-3000万");
        map3.put("count",map.get("more500to3000"));
        map4.put("text","3000万以上");
        map4.put("count",map.get("morethan3000"));
        List list1 = new ArrayList();
        list1.add(map1);
        list1.add(map2);
        list1.add(map3);
        list1.add(map4);
        return list1;
    }

    public List findStatisticForYH(){
        String gryhsql = "SELECT " +
                "SUM(case when (si.ROLEID = '129947C6-94DC-4A7D-84D2-E78A80E518A3' or si.ROLEID = 'f8a87c80-f89d-48bc-ad96-840ab6aa81b2') then 1 else 0 end) as gryhnum, " +
                "SUM(case when (si.ROLEID = '129947C6-94DC-4A7D-84D2-E78A80E518A3' or si.ROLEID = 'f8a87c80-f89d-48bc-ad96-840ab6aa81b2') and DATEDIFF(MONTH, su.REGISTERDATE, GETDATE()) = 0 then 1 else 0 end) as newgryhnum " +
                "from SYS_USER su left join SYS_IDENTITY si on su.id = si.USERID " +
                "left join BI_TALENT_BI btb on su.id = btb.MAINID;";
        List<Map> gryhlist = this.dbHelper.getRows(gryhsql, null);
        String fryhsql = "SELECT count(*) as fryhnum, " +
                "SUM(case when DATEDIFF(MONTH, su.REGISTERDATE, GETDATE()) = 0 then 1 else 0 end) as newfryhnum " +
                "from sys_identity si " +
                "left join PMS_ENTERPRISE pe on si.PURVIEWORGANIZEID = pe.id " +
                "left join bi_ent_bi beb on pe.id = beb.MAINID " +
                "left join sys_user su on su.ID = si.USERID " +
                "WHERE si.ROLEID = 'C7004168-4E0C-4F1F-B341-A225B5644DC5' or si.roleid = 'aaaa-bbbb-cccc-dddd'";
        List<Map> fryhlist = this.dbHelper.getRows(fryhsql, null);
        String zjyhsql = "SELECT count(*) as zjyhnum, " +
                "SUM(case when DATEDIFF(MONTH, zru.REGISTERDATE, GETDATE()) = 0 then 1 else 0 end) as newzjyhnum " +
                "from ZJK_RYJBXX_UPDATE zru left join BI_TALENT_BI btb " +
                "on zru.ID = btb.mainid;";
        List<Map> zjyhlist = this.dbHelper.getRows(zjyhsql, null);
        Map gryhmap = gryhlist.get(0);
        Map fryhmap = fryhlist.get(0);
        Map zjyhmap = zjyhlist.get(0);
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        map1.put("type","个人用户");
        map1.put("total",gryhmap.get("gryhnum"));
        map1.put("new",gryhmap.get("newgryhnum"));
        map2.put("type","法人用户");
        map2.put("total",fryhmap.get("fryhnum"));
        map2.put("new",fryhmap.get("newfryhnum"));
        map3.put("type","专家用户");
        map3.put("total",zjyhmap.get("zjyhnum"));
        map3.put("new",zjyhmap.get("newzjyhnum"));
        List list = new ArrayList();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        return list;
    }

    //高等院校、科研单位、国企、民营企业、其他、外企、事业单位、政府部门
    public List findStatisticForQY(){
        String sql = "select " +
                "sum(case when bei.simpleunittype = '高等院校' then 1 else 0 end ) as gdyx, " +
                "sum(case when bei.simpleunittype = '科研单位' then 1 else 0 end ) as kydw, " +
                "sum(case when bei.simpleunittype = '国企' then 1 else 0 end ) as gq, " +
                "sum(case when bei.simpleunittype = '民营企业' then 1 else 0 end ) as myqy, " +
                "sum(case when bei.simpleunittype = '外企' then 1 else 0 end ) as wq, " +
                "sum(case when bei.simpleunittype = '事业单位' then 1 else 0 end ) as sydw, " +
                "sum(case when bei.simpleunittype = '政府部门' then 1 else 0 end ) as zfbm, " +
                "sum(case when (bei.simpleunittype <> '高等院校' and bei.simpleunittype <> '科研单位' " +
                "and bei.simpleunittype <> '国企' and bei.simpleunittype <> '民营企业' and bei.simpleunittype <> '外企' " +
                "and bei.simpleunittype <> '事业单位' and bei.simpleunittype <> '政府部门') or bei.simpleunittype is null then 1 else 0 end ) as qt " +
                "from bi_ent_bi bei " +
                "left join PMS_ENTERPRISE pe on pe.id = bei.MAINID " +
                "left join sys_identity si on si.PURVIEWORGANIZEID = pe.id " +
                "left join sys_user su on su.ID = si.USERID " +
                "WHERE si.ROLEID = 'C7004168-4E0C-4F1F-B341-A225B5644DC5';";
        List<Map> list = this.dbHelper.getRows(sql, null);
        return list;
    }

    public List findStatisticForQYHYLY(){
        String sql = "SELECT info.num, pst2.name, pst2.code from " +
                "(select count(*) as num, pst.code as code " +
                "from PMS_ENTERPRISE pe " +
                "left join bi_ent_bi bei on pe.id = bei.mainid " +
                "left join pms_subject_type pst on pst.code = bei.BELONGINDUSTRYCODE " +
                "group by pst.code) info " +
                "LEFT join pms_subject_type pst2 on pst2.code = info.code " +
                "WHERE pst2.type = 'hyly' " +
                "order by info.num desc;";
        List<Map> list = this.dbHelper.getRows(sql, null);
        return list;
    }

    public List findStatisticForQYEstablishDate(){
        /**  这样写也行
         select
         sum(case when DATEDIFF(day, bei.ESTABLISHDATE, '2000-01-01') > 0 then 1 else 0 end ) as before2000 ,
         sum(case when DATEDIFF(day, bei.ESTABLISHDATE, '2010-01-01') > 0 and DATEDIFF(day, bei.ESTABLISHDATE, '2000-01-01') <= 0 then 1 else 0 end ) as before2010 ,
         sum(case when DATEDIFF(day, bei.ESTABLISHDATE, '2020-01-01') > 0 and DATEDIFF(day, bei.ESTABLISHDATE, '2010-01-01') <= 0 then 1 else 0 end ) as before2020 ,
         sum(case when DATEDIFF(day, bei.ESTABLISHDATE, '2020-01-01') <= 0 then 1 else 0 end ) as after2020
         from PMS_ENTERPRISE pe
         left join bi_ent_bi bei on pe.id = bei.MAINID;
         */
        String sql = "select " +
                "sum(case when bei.ESTABLISHDATE < '2000-01-01' then 1 else 0 end ) as before2000 ," +
                "sum(case when bei.ESTABLISHDATE < '2010-01-01' and bei.ESTABLISHDATE >= '2000-01-01' then 1 else 0 end ) as before2010 ," +
                "sum(case when bei.ESTABLISHDATE < '2020-01-01' and bei.ESTABLISHDATE >= '2010-01-01' then 1 else 0 end ) as before2020 ," +
                "sum(case when bei.ESTABLISHDATE >= '2020-01-01' then 1 else 0 end ) as after2020 " +
                "from PMS_ENTERPRISE pe " +
                "left join bi_ent_bi bei on pe.id = bei.MAINID;";
        List<Map> list = this.dbHelper.getRows(sql, null);
        Map map = list.get(0);
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        Map map4 = new HashMap();
        map1.put("text","2000年前");
        map1.put("count",map.get("before2000"));
        map2.put("text","2000年-2010年");
        map2.put("count",map.get("before2010"));
        map3.put("text","2010年-2020年");
        map3.put("count",map.get("before2020"));
        map4.put("text","2020年至今");
        map4.put("count",map.get("after2020"));
        List list1 = new ArrayList();
        list1.add(map1);
        list1.add(map2);
        list1.add(map3);
        list1.add(map4);
        return list1;
    }

    public Map<String, Object> findExpertXkslByCity(JSONObject jsonObject){
        String city = jsonObject.get("city")+"";
        Map<String, Object> resMap = new HashMap<String, Object>();
        //专家总数
        int totalnum = 0;
        String total = "";
        String totalSql="select count(1) from zjk_ryjbxx_update where rkflag = '1' ";
        if(!"贵州省".equals(city) || Util.isNullOrEmpty(city)) {
            total = dbHelper.getOnlyStringValue(totalSql + " and organization_city = ? ", new Object[]{city});
        }else{
            total = dbHelper.getOnlyStringValue(totalSql + " and organization_province = ? ", new Object[]{city});
        }
        totalnum = Integer.parseInt(total);
        if(totalnum != 0) {
            String mainsql ="";
            if (!"贵州省".equals(city) && !Util.isNullOrEmpty(city)) {
                mainsql ="select * from " +
                        "(select s.subjectonecode code,count(s.subjectonecode) as count " +
                        "from zjk_ryjbxxxkfl_update s " +
                        "left join zjk_ryjbxx_update t on t.id = s.person_id " +
                        "where t.rkflag = '1' and s.subjecttype = ? and t.organization_city= ? " +
                        "group by s.subjectonecode order by count(s.subjectonecode) desc) where rownum < 10";
            }else{
                mainsql="select * from " +
                        "(select s.subjectonecode code,count(s.subjectonecode) as count " +
                        "from zjk_ryjbxxxkfl_update s " +
                        "left join zjk_ryjbxx_update t on t.id = s.person_id " +
                        "where t.rkflag = '1' and s.subjecttype = ? and t.organization_province= ? " +
                        "group by s.subjectonecode order by count(s.subjectonecode) desc) where rownum < 10";
            }

			/*
				国标学科
			 */
            List<Map> resGbxk = new ArrayList<>();
            int gbZjzs = totalnum;
            List<Map> gbxklist = dbHelper.getRows(
                    "select a.name as xkname,b.count from PMS_SUBJECT_TYPE a " +
                            " left join (" + mainsql + ") b on a.code=b.code " +
                            " where a.subjecttype ='gbxk' and b.count is not null", new Object[]{"gbxk", city});
            if (null!=gbxklist && gbxklist.size()>0){
                resGbxk.addAll(gbxklist);
                String gbxktotal = dbHelper.getOnlyStringValue(
                        "select sum(count) from (" + mainsql + ")", new Object[]{"gbxk", city});
                gbZjzs = gbZjzs - Integer.parseInt(gbxktotal);
            }
            if(gbZjzs > 0) {
                Map othergbxkmap = new HashMap<>();
                othergbxkmap.put("xkname", "其他学科");
                othergbxkmap.put("count", gbZjzs);
                resGbxk.add(othergbxkmap);
            }
            resMap.put("gbxk", resGbxk);

			/*
				行业领域
			 */
            List<Map> resHyly = new ArrayList<>();
            int lyZjzs = totalnum;
            List<Map> hylylist = dbHelper.getRows(
                    "select a.name as lyname,b.count from PMS_SUBJECT_TYPE a " +
                            " left join (" + mainsql + ") b on a.code=b.code " +
                            " where a.subjecttype ='hyly' and b.count is not null", new Object[]{"hyly", city});
            if (null!=hylylist && hylylist.size()>0){
                resHyly.addAll(hylylist);
                String hylytotal = dbHelper.getOnlyStringValue(
                        "select sum(count) from (" + mainsql + ")", new Object[]{"hyly", city});
                lyZjzs = lyZjzs - Integer.parseInt(hylytotal);
            }
            if(lyZjzs > 0) {
                Map otherHylyMap = new HashMap<>();
                otherHylyMap.put("lyname", "其他领域");
                otherHylyMap.put("count", lyZjzs);
                resHyly.add(otherHylyMap);
            }
            resMap.put("hyly", resHyly);

			/*
				基金学科
			 */
            List<Map> resJjxk = new ArrayList<>();
            int jjZjzs = totalnum;
            String jjxksql = "";
            if (!"贵州省".equals(city) && !Util.isNullOrEmpty(city)) {
                jjxksql = "select s.subjectonecode as code,count(s.subjectonecode) as count " +
                        "from zjk_ryjbxxxkfl_update s " +
                        "left join zjk_ryjbxx_update t on t.id = s.person_id " +
                        "where t.rkflag = '1' and s.subjecttype = 'jjxk' and t.organization_city=? " +
                        "group by s.subjectonecode order by count(s.subjectonecode) desc";
            }else {
                jjxksql = "select s.subjectonecode as code,count(s.subjectonecode) as count " +
                        "from zjk_ryjbxxxkfl_update s " +
                        "left join zjk_ryjbxx_update t on t.id = s.person_id " +
                        "where t.rkflag = '1' and s.subjecttype = 'jjxk' and t.organization_province=? " +
                        "group by s.subjectonecode order by count(s.subjectonecode) desc";
            }
            List<Map> jjxklist = dbHelper.getRows(
                    "select a.name as jjxkname,b.count from PMS_SUBJECT_TYPE a " +
                            " left join (" + jjxksql + ") b on a.code=b.code " +
                            " where a.subjecttype ='jjxk' and a.rank='1' and b.count is not null", new Object[]{city});
            if (null!=jjxklist && jjxklist.size()>0){
                resJjxk.addAll(jjxklist);
                String jjxktotal = dbHelper.getOnlyStringValue(
                        "select sum(count) from (" + jjxksql + ")", new Object[]{ city});
                jjZjzs = jjZjzs -Integer.parseInt(jjxktotal);
            }
            if(jjZjzs > 0){
                Map otherJjxkMap = new HashMap<>();
                otherJjxkMap.put("jjxkname", "未填写");
                otherJjxkMap.put("count", jjZjzs);
                resJjxk.add(otherJjxkMap);
            }
            resMap.put("jjxk", resJjxk);

			/*
				专家来源
			 */
            List<Map> zjlyList = new ArrayList<>();
            StringBuffer zjlySql = new StringBuffer("select sum(case when t.drbj = '1' then 1 else 0 end) as gzszj, " +
                    "sum(case when t.drbj = '2' then 1 else 0 end) as stzj from zjk_ryjbxx_update t ");
            if (!"贵州省".equals(city) && !Util.isNullOrEmpty(city)) {
                zjlySql.append(" where t.rkflag = '1' and t.organization_city=? ");
                zjlyList = dbHelper.getRows(zjlySql.toString(), new Object[]{city});
            } else {
                zjlySql.append(" where t.rkflag = '1' and t.organization_province=? ");
                zjlyList = dbHelper.getRows(zjlySql.toString(), new Object[]{city});
            }

            resMap.put("zjly", zjlyList.get(0));

        }
        return resMap;
    }

    public List<Map> findExpertEducation(){
        String sql = "SELECT count(*) as num, case when zru.degree is null then '其他' else zru.degree end as degree " +
                "from ZJK_RYJBXX_UPDATE zru " +
                "where zru.RKFLAG = '1' " +
                "GROUP by case when zru.degree is null then '其他' else zru.degree end";

        List<Map> list = this.dbHelper.getRows(sql,null);
        return list;
    }

    public List<Map> findExpertTitle(){
        String sql = "SELECT count(*) as num, case when zru.position_title is null then '其他' else zru.position_title end as title " +
                "from ZJK_RYJBXX_UPDATE zru " +
                "where zru.RKFLAG = '1' " +
                "GROUP by case when zru.position_title is null then '其他' else zru.position_title end";

        List<Map> list = this.dbHelper.getRows(sql,null);
        return list;
    }

    public List<Map> findExpertRcch(){
        String sql = "select " +
                "count(t.id) as totalexpert, " +
                "sum(case when (t.expert_type = '技术类专家' or t.expert_type like '%技术类专家%') then 1 else 0 end) as techexpert, " +
                "sum(case when (t.expert_type = '管理类专家' or t.expert_type like '%管理类专家%') then 1 else 0 end) as manageexpert, " +
                "sum(case when t.expert_type = '财务类专家' then 1 else 0 end) as economyexpert, " +
                "sum(case when t.expert_type = '其他类专家' or t.expert_type is null then 1 else 0 end) as otherexpert " +
                "from zjk_ryjbxx_update t " +
                "left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
                "where t.rkflag = '1' ";

        List<Map> list = this.dbHelper.getRows(sql,null);
        return list;
    }

    public List<Map> findExpertRcchDetail(){
        String sql = "select sum(case when e.lyys is not null then 1 else 0 end) lyys, " +
                "sum(case when e.cjxz is not null then 1 else 0 end) cjxz, " +
                "sum(case when e.gjjcqn is not null then 1 else 0 end) gjjcqn, " +
                "sum(case when e.gjjtcgx is not null then 1 else 0 end) gjjtcgx, " +
                "sum(case when e.sbjtcgx is not null then 1 else 0 end) sbjtcgx, " +
                "sum(case when e.gjjdtr is not null then 1 else 0 end) gjjdtr, " +
                "sum(case when e.sbjdtr is not null then 1 else 0 end) sbjdtr, " +
                "sum(case when e.gjjjxms is not null then 1 else 0 end) gjjjxms, " +
                "sum(case when e.sxkjx is not null then 1 else 0 end) sxkjx, " +
                "sum(case when e.gwytt is not null then 1 else 0 end) gwytt, " +
                "sum(case when e.szftt is not null then 1 else 0 end) szftt, " +
                "sum(case when e.hxzj is not null then 1 else 0 end) hxzj, " +
                "sum(case when e.sgzj is not null then 1 else 0 end) sgzj, " +
                "sum(case when e.brljrc is not null then 1 else 0 end) brljrc, " +
                "sum(case when e.qrcxcyrc is not null then 1 else 0 end) qrcxcyrc, " +
                "sum(case when e.sbqrc is not null then 1 else 0 end) sbqrc " +
                "from zjk_ryjbxx_update t " +
                "left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
                "where t.rkflag = '1' ";

        List<Map> list = this.dbHelper.getRows(sql,null);

        List<Map> resList = new ArrayList<>();

        Iterator iter = list.get(0).entrySet().iterator();
        while (iter.hasNext()) {
            String name = "";
            Map.Entry entry = (Map.Entry) iter.next();
            Map m = new HashMap();
            m.put("type",entry.getKey().toString());
            m.put("value",entry.getValue().toString());
            Class<?> clazz = ZjkRyjbxxInfoUpdate.class;
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                if(field.isAnnotationPresent(FieldDes.class)){
                    FieldDes fieldDes = field.getAnnotation(FieldDes.class);
                    if(fieldDes.name().equals(entry.getKey().toString())){
                        name = fieldDes.memo();
                        break;
                    }
                }
            }
            m.put("name",name);
            resList.add(m);
        }
        return resList;
    }

    public Page<Map> findExpertTypeList(JSONObject jsonObject) {
        String type = jsonObject.get("type")+"";
        JSONObject pageConfig = jsonObject.getJSONObject("pageConfig");
        String insql = "";
        List paramlist = new ArrayList<>();
        if(!Util.isEoN(jsonObject.get("searchContent"))){
            String param = jsonObject.get("searchContent").toString();
            insql += " and (t.name like ? or t.organization like ? or t.position_title like ?)";
            paramlist.add("%" + param + "%");
            paramlist.add("%" + param + "%");
            paramlist.add("%" + param + "%");
        }
        List params = new ArrayList<>();
        String sql = "";
        if("techexpert".equals(type)){//应该就技术和管理有交叉吧
            sql = "select t.name,t.organization,t.position_title " +
                    "from zjk_ryjbxx_update t " +
                    "where t.expert_type like '%技术类专家%'  and t.rkflag = '1' ";
        }else if("manageexpert".equals(type)){
            sql = "select t.name,t.organization,t.position_title " +
                    "from zjk_ryjbxx_update t " +
                    "where t.expert_type like '%管理类专家%'  and t.rkflag = '1' ";
        }else if("economyexpert".equals(type)){
            sql = "select t.name,t.organization,t.position_title " +
                    "from zjk_ryjbxx_update t " +
                    "where t.expert_type = '经济类专家'  and t.rkflag = '1' ";
        }else if("otherexpert".equals(type)){
            sql = "select t.name,t.organization,t.position_title " +
                    "from zjk_ryjbxx_update t " +
                    "where (t.expert_type = '其他类专家' or t.expert_type is null)  and t.rkflag = '1' ";
        }
        Page<Map> page = this.pageService.findPageBySql(sql + insql, paramlist, pageConfig);
        return page;
    }


    public List<Map> findExpertStatistic(String province) {
        List<Map> list = new ArrayList<>();

        if("全国".equals(province)){
            String sql = "select t.organization_province,count(t.organization_province) as total, " +
                    "sum (case when t.gender = '男' then 1 else 0 end) as man, " +
                    "sum (case when t.gender = '女' then 1 else 0 end) as woman, " +
                    "sum (case when t.gender is null then 1 else 0 end) as qtsex, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=30 then 1 else 0 end) as age0_30, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>30 and trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=45 then 1 else 0 end) as age31_45, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>45 and trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=55 then 1 else 0 end) as age46_55, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>55 and trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=65 then 1 else 0 end) as age56_65, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>65 then 1 else 0 end) as age66_999, " +
                    "sum (case when t.degree='博士' then 1 else 0 end) as bs, " +
                    "sum (case when t.degree='硕士' then 1 else 0 end) as ss, " +
                    "sum (case when t.degree='学士' then 1 else 0 end) as bk, " +
                    "sum (case when t.position_title='正高级' then 1 else 0 end) as zgj, " +
                    "sum (case when t.position_title='副高级' then 1 else 0 end) as fgj, " +
                    "sum (case when t.position_title='中级' then 1 else 0 end) as zj, " +
                    "sum (case when t.position_title='初级' or t.position_title='初级员级' or t.position_title='初级助理级' then 1 else 0 end) as cj, " +
                    "sum (case when t.position_title='无' then 1 else 0 end) as wtitle, " +
                    "sum (case when t.position_title='正高级' or t.position_title='副高级' or t.position_title='中级' or t.position_title='初级' " +
                    "or t.position_title='初级员级' or t.position_title='初级助理级' or t.position_title='无' then 0 else 1 end) as qttitle " +
                    "from zjk_ryjbxx_update t where t.rkflag = '1' and t.organization_province is not null and length(t.birthday) = 10 " +
                    "group by t.organization_province";
            list = dbHelper.getRows(sql,null);

            if(list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    String organization_province = list.get(i).get("organization_province")+"";
                    List ageList = this.dbHelper.getRows("select age,count(age) age_count from (select to_char(trunc(months_between(to_date('2022-12-01','yyyy-MM-dd'),to_date(birthday,'yyyy-MM-dd')) / 12)) as age " +
                            "from zjk_ryjbxx_update where length(birthday) = 10 and rkflag = '1' and organization_province = ?) group by age",new Object[]{organization_province});

                    list.get(i).put("ageList",ageList);
                }
            }
        }else{
			/*String sql = "select t.organization_city,count(t.organization_city) as total, " +
					"sum (case when t.gender = '男' then 1 else 0 end) as man, " +
					"sum (case when t.gender = '女' then 1 else 0 end) as woman, " +
					"sum (case when t.gender is null then 1 else 0 end) as qtsex, " +
					"sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=30 then 1 else 0 end) as age0_30, " +
					"sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>30 and trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=45 then 1 else 0 end) as age31_45, " +
					"sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>45 and trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=55 then 1 else 0 end) as age46_55, " +
					"sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>55 and trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=65 then 1 else 0 end) as age56_65, " +
					"sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>65 then 1 else 0 end) as age66_999, " +
					"sum (case when t.position_title='正高级' then 1 else 0 end) as zgj, " +
					"sum (case when t.position_title='副高级' then 1 else 0 end) as fgj, " +
					"sum (case when t.position_title='中级' then 1 else 0 end) as zj, " +
					"sum (case when t.position_title='初级' or t.position_title='初级员级' or t.position_title='初级助理级' then 1 else 0 end) as cj, " +
					"sum (case when t.position_title='无' then 1 else 0 end) as wtitle, " +
					"sum (case when t.position_title='正高级' or t.position_title='副高级' or t.position_title='中级' or t.position_title='初级' " +
					"or t.position_title='初级员级' or t.position_title='初级助理级' or t.position_title='无' then 0 else 1 end) as qttitle " +
					"from zjk_ryjbxx_update t where t.rkflag = '1' and t.organization_city is not null and length(t.birthday) = 10 " +
					"and t.organization_province = ? " +
					"group by t.organization_city";*/
            //上面的sql因为加了 length(t.birthday) = 10 这个条件  导致非岁数的条件有些漏了
            String sql = "" +
                    "select a.*,b.age0_30,b.age31_45,b.age46_55,b.age56_65,b.age66_999 " +
                    "from (select t.organization_city,count(t.organization_city) as total, " +
                    "sum (case when t.gender = '男' then 1 else 0 end) as man, " +
                    "sum (case when t.gender = '女' then 1 else 0 end) as woman, " +
                    "sum (case when t.gender is null then 1 else 0 end) as qtsex, " +
                    "sum (case when t.degree='博士' then 1 else 0 end) as bs, " +
                    "sum (case when t.degree='硕士' then 1 else 0 end) as ss, " +
                    "sum (case when t.degree='本科' then 1 else 0 end) as bk, " +
                    "sum (case when t.position_title='正高级' then 1 else 0 end) as zgj, " +
                    "sum (case when t.position_title='副高级' then 1 else 0 end) as fgj, " +
                    "sum (case when t.position_title='中级' then 1 else 0 end) as zj, " +
                    "sum (case when t.position_title='初级' or t.position_title='初级员级' or t.position_title='初级助理级' then 1 else 0 end) as cj, " +
                    "sum (case when t.position_title='无' then 1 else 0 end) as wtitle, " +
                    "sum (case when t.position_title='正高级' or t.position_title='副高级' or t.position_title='中级' or t.position_title='初级' " +
                    "or t.position_title='初级员级' or t.position_title='初级助理级' or t.position_title='无' then 0 else 1 end) as qttitle " +
                    "from zjk_ryjbxx_update t where t.rkflag = '1' and t.organization_city is not null and t.organization_province = ? " +
                    "group by t.organization_city " +
                    ") a " +
                    "left join (select t.organization_city, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=30 then 1 else 0 end) as age0_30, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>30 and trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=45 then 1 else 0 end) as age31_45, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>45 and trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=55 then 1 else 0 end) as age46_55, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>55 and trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)<=65 then 1 else 0 end) as age56_65, " +
                    "sum (case when trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd'))/12)>65 then 1 else 0 end) as age66_999 " +
                    "from zjk_ryjbxx_update t where t.rkflag = '1' and t.organization_province is not null and length(t.birthday) = 10 " +
                    "and t.organization_province = ? " +
                    "group by t.organization_city) b " +
                    "on a.ORGANIZATION_CITY = b.ORGANIZATION_CITY";
            list = dbHelper.getRows(sql,new Object[]{province,province});

            if(list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    String organization_city = list.get(i).get("organization_city")+"";
                    List ageList = this.dbHelper.getRows("select age,count(age) age_count from (select to_char(trunc(months_between(to_date('2022-12-01','yyyy-MM-dd'),to_date(birthday,'yyyy-MM-dd')) / 12)) as age " +
                            "from zjk_ryjbxx_update where length(birthday) = 10 and rkflag = '1' and organization_city = ?) group by age",new Object[]{organization_city});

                    list.get(i).put("ageList",ageList);
                }
            }
        }
        return list;
    }


    //高等院校、科研单位、国企、民营企业、其他、外企、事业单位、政府部门
    public List findStatisticForExpertUnitType(){
        //工作单位性质为高校、科研院所、企业的专家总数
        //由于专家主表的uniformcode均为空，只能先拿名字来凑数匹配一下了，以后再说
        String sql1="select count(case when pe.simpleunittype = '高等院校' then pe.simpleunittype end ) as gdys, " +
                "count(case when pe.simpleunittype = '科研单位' then pe.simpleunittype end ) as kydw, " +
                "count(case when pe.simpleunittype = '国企' then pe.simpleunittype end ) as gq," +
                "count(case when pe.simpleunittype = '民营企业' then pe.simpleunittype end ) as myqy," +
                "count(case when pe.simpleunittype = '外企' then pe.simpleunittype end ) as wq," +
                "count(case when pe.simpleunittype = '事业单位' then pe.simpleunittype end ) as sydw," +
                "count(case when pe.simpleunittype = '政府部门' then pe.simpleunittype end ) as zfbm," +
                "count(case when pe.simpleunittype <> '高等院校' and pe.simpleunittype <> '科研单位' " +
                "and pe.simpleunittype <> '国企' and pe.simpleunittype <> '民营企业' and pe.simpleunittype <> '外企' " +
                "and pe.simpleunittype <> '事业单位' and pe.simpleunittype <> '政府部门' then '其他' end ) as qt " +
                "from zjk_ryjbxx_update zru " +
                "left join bi_ent_bi pe on zru.ORGANIZATION = pe.UNITNAME " +
                "where zru.rkflag = '1' ";

        List<Map> list = this.dbHelper.getRows(sql1, null);
        Map map = list.get(0);
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        Map map4 = new HashMap();
        Map map5 = new HashMap();
        Map map6 = new HashMap();
        Map map7 = new HashMap();
        Map map8 = new HashMap();
        map1.put("text", "高等院校");
        map1.put("count", map.get("gdys"));
        map2.put("text", "科研单位");
        map2.put("count", map.get("kydw"));
        map3.put("text", "国企");
        map3.put("count", map.get("gq"));
        map4.put("text", "民营企业");
        map4.put("count", map.get("myqy"));
        map5.put("text", "外企");
        map5.put("count", map.get("wq"));
        map6.put("text", "事业单位");
        map6.put("count", map.get("sydw"));
        map7.put("text", "政府部门");
        map7.put("count", map.get("zfbm"));
        map8.put("text", "其他");
        map8.put("count", map.get("qt"));
        List list1 = new ArrayList();
        list1.add(map1);
        list1.add(map2);
        list1.add(map3);
        list1.add(map4);
        list1.add(map5);
        list1.add(map6);
        list1.add(map7);
        list1.add(map8);
        return list1;
    }

}
