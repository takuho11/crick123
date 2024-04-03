/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2017-1-23 9:49:28
 */
package cn.topcheer.pms2.biz.zjk;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxUpdate;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxxkflUpdate;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.zjk.ZjkRyjbxxxkflUpdateDao;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.*;

/**
 * ZjkRyjbxxxkflUpdate 服务类
 */
@Slf4j
@Service(value = "ZjkRyjbxxxkflUpdateService")
public class ZjkRyjbxxxkflUpdateService extends GenericService<ZjkRyjbxxxkflUpdate> {

    public ZjkRyjbxxxkflUpdateDao getZjkRyjbxxxkflUpdateDao() {
        return (ZjkRyjbxxxkflUpdateDao) this.getGenericDao();
    }

    @Autowired
    DBHelper dbHelper;
    @Autowired
    SysUserService sysUserService;
    @Lazy
    @Autowired
    ZjkRyjbxxUpdateService zjkRyjbxxUpdateService;

    @Autowired
    public void setZjkRyjbxxxkflUpdateDao(ZjkRyjbxxxkflUpdateDao zjkRyjbxxxkflUpdateDao) {

        this.setGenericDao(zjkRyjbxxxkflUpdateDao);
    }


    /**
     * 【专家注册】---保存学科--广州新改
     * @param expertid
     * @param json
     */
    public void saveRyjbxxXkflNew(String expertid, JSONObject json) {
        JSONArray subjectArr = json.getJSONArray("subjectArr");

        if (subjectArr.size() > 0) {
            for (int i = 0; i < subjectArr.size(); i++) {
                String subjecttype = subjectArr.getJSONObject(i).get("type") + "";
                if ("zj".equals(subjecttype)) {
                    this.saveZrjj(expertid, subjectArr.getJSONObject(i));
                } else if ("xm".equals(subjecttype)) {
                    this.saveGbxk(expertid, subjectArr.getJSONObject(i));
                } else if ("jsly".equals(subjecttype)) {
                    this.saveJsly(expertid, subjectArr.getJSONObject(i));
                } else if ("hyly".equals(subjecttype)) {
                    this.saveHyly(expertid, subjectArr.getJSONObject(i));
                } else if ("scly".equals(subjecttype)) {
                    this.savePsly(expertid, subjectArr.getJSONObject(i));
                }
            }
        }
    }


    /**
     * 【专家注册】---保存自然基金学科--广州新改
     * @param expertid
     * @param json
     */
    public void saveZrjj(String expertid, JSONObject json) {
        JSONArray array = json.getJSONArray("array");
        try {
            this.dbHelper.runSql("delete from zjk_ryjbxxxkfl_update " +
                    "where person_id = ? and xmzlfl = 'jjxk'", new Object[]{expertid});
            this.dbHelper.runSql("delete from zjk_linkbq where zjkryjbxxupdateid = ? and type = 'jjxk'", new Object[]{expertid});
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                ZjkRyjbxxxkflUpdate zrx = new ZjkRyjbxxxkflUpdate();
                Util.ApplyObject(zrx, array.getJSONObject(i));
                zrx.setId(Util.NewGuid());
                zrx.setSeq(i);
                zrx.setXmzlfl("jjxk");
                zrx.setSubjecttype("jjxk");
                zrx.setPerson_id(expertid);
                this.merge(zrx);

                if(Util.isEoN(zrx.getSubjectendcode())) {
                    continue;
                }
//                ZjkLinkbq zjkLinkbq = new ZjkLinkbq();
//                String bqid = zjkBqService.fetchBqidByTypeAndCode("jjxk", zrx.getSubjectendcode());
//                JSONObject bqInfo = zjkBqService.fetchCompleteZjkBqInfo(bqid);
//                Util.ApplyObject(zjkLinkbq, bqInfo);
//                zjkLinkbq.setId(zrx.getId());
//                zjkLinkbq.setSeq(i);
//                zjkLinkbq.setCreatetime(new Date());
//                zjkLinkbq.setZjkryjbxxupdateid(zrx.getPerson_id());
//                zjkLinkbqService.merge(zjkLinkbq);
            }
        }

    }


    /**
     * 【专家注册】---保存国标学科--广州新改
     * @param expertid
     * @param json
     */
    public void saveGbxk(String expertid, JSONObject json) {
        JSONArray array = json.getJSONArray("array");
        try {
            this.dbHelper.runSql("delete from zjk_ryjbxxxkfl_update " +
                    "where person_id = ? and xmzlfl = 'gbxk'", new Object[]{expertid});
            this.dbHelper.runSql("delete from zjk_linkbq where zjkryjbxxupdateid = ? and type = 'gbxk'", new Object[]{expertid});
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                ZjkRyjbxxxkflUpdate zrx = new ZjkRyjbxxxkflUpdate();
                Util.ApplyObject(zrx, array.getJSONObject(i));
                zrx.setId(Util.NewGuid());
                zrx.setSeq(i);
                zrx.setSubjecttype("gbxk");
                zrx.setXmzlfl("gbxk");
                zrx.setPerson_id(expertid);
                this.merge(zrx);
                if(Util.isEoN(zrx.getSubjectendcode())) {
                    continue;
                }
//                ZjkLinkbq zjkLinkbq = new ZjkLinkbq();
//                String bqid = zjkBqService.fetchBqidByTypeAndCode("gbxk", zrx.getSubjectendcode());
//                JSONObject bqInfo = zjkBqService.fetchCompleteZjkBqInfo(bqid);
//                Util.ApplyObject(zjkLinkbq, bqInfo);
//                zjkLinkbq.setId(zrx.getId());
//                zjkLinkbq.setSeq(i);
//                zjkLinkbq.setCreatetime(new Date());
//                zjkLinkbq.setZjkryjbxxupdateid(zrx.getPerson_id());
//                zjkLinkbqService.merge(zjkLinkbq);
            }
        }

    }

    /**
     * 【专家注册】---保存国标学科--广州新改
     * @param expertid
     * @param json
     */
    public void saveJsly(String expertid, JSONObject json) {
        JSONArray array = json.getJSONArray("array");
        try {
            this.dbHelper.runSql("delete from zjk_ryjbxxxkfl_update " +
                    "where person_id = ? and xmzlfl = 'jsly'", new Object[]{expertid});
            this.dbHelper.runSql("delete from zjk_linkbq where zjkryjbxxupdateid = ? and type = 'jsly'", new Object[]{expertid});
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                ZjkRyjbxxxkflUpdate zrx = new ZjkRyjbxxxkflUpdate();
                Util.ApplyObject(zrx, array.getJSONObject(i));
                zrx.setId(Util.NewGuid());
                zrx.setSeq(i);
                zrx.setSubjecttype("jsly");
                zrx.setXmzlfl("jsly");
                zrx.setPerson_id(expertid);
                this.merge(zrx);
                if(Util.isEoN(zrx.getSubjectendcode())) {
                    continue;
                }
//                ZjkLinkbq zjkLinkbq = new ZjkLinkbq();
//                String bqid = zjkBqService.fetchBqidByTypeAndCode("jsly", zrx.getSubjectendcode());
//                JSONObject bqInfo = zjkBqService.fetchCompleteZjkBqInfo(bqid);
//                Util.ApplyObject(zjkLinkbq, bqInfo);
//                zjkLinkbq.setId(zrx.getId());
//                zjkLinkbq.setSeq(i);
//                zjkLinkbq.setCreatetime(new Date());
//                zjkLinkbq.setZjkryjbxxupdateid(zrx.getPerson_id());
//                zjkLinkbqService.merge(zjkLinkbq);
            }
        }

    }


    /**
     * 【专家注册】---保存国标学科--广州新改
     * @param expertid
     * @param json
     */
    public void saveHyly(String expertid, JSONObject json) {
        JSONArray array = json.getJSONArray("array");
        try {
            this.dbHelper.runSql("delete from zjk_ryjbxxxkfl_update " +
                    "where person_id = ? and xmzlfl = 'hyly'", new Object[]{expertid});
            this.dbHelper.runSql("delete from zjk_linkbq where zjkryjbxxupdateid = ? and type = 'hyly'", new Object[]{expertid});
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                ZjkRyjbxxxkflUpdate zrx = new ZjkRyjbxxxkflUpdate();
                Util.ApplyObject(zrx, array.getJSONObject(i));
                zrx.setId(Util.NewGuid());
                zrx.setSeq(i);
                zrx.setXmzlfl("hyly");
                zrx.setSubjecttype("hyly");
                zrx.setPerson_id(expertid);
                this.merge(zrx);
                if(Util.isEoN(zrx.getSubjectendcode())) {
                    continue;
                }
//                ZjkLinkbq zjkLinkbq = new ZjkLinkbq();
//                String bqid = zjkBqService.fetchBqidByTypeAndCode("hyly", zrx.getSubjectendcode());
//                JSONObject bqInfo = zjkBqService.fetchCompleteZjkBqInfo(bqid);
//                Util.ApplyObject(zjkLinkbq, bqInfo);
//                zjkLinkbq.setId(zrx.getId());
//                zjkLinkbq.setSeq(i);
//                zjkLinkbq.setCreatetime(new Date());
//                zjkLinkbq.setZjkryjbxxupdateid(zrx.getPerson_id());
//                zjkLinkbqService.merge(zjkLinkbq);
            }
        }

    }


    /**
     * 【专家注册】---保存擅长评审领域
     * @param expertid
     * @param json
     */
    public void savePsly(String expertid, JSONObject json) {
        JSONArray array = json.getJSONArray("array");
        try {
            this.dbHelper.runSql("delete from zjk_ryjbxxxkfl_update " +
                    "where person_id = ? and xmzlfl = 'scly'", new Object[]{expertid});
            this.dbHelper.runSql("delete from zjk_linkbq where zjkryjbxxupdateid = ? and type = 'scly'", new Object[]{expertid});
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                ZjkRyjbxxxkflUpdate zrx = new ZjkRyjbxxxkflUpdate();
                Util.ApplyObject(zrx, array.getJSONObject(i));
                zrx.setId(Util.NewGuid());
                zrx.setSeq(i);
                zrx.setXmzlfl("scly");
                zrx.setSubjecttype("scly");
                zrx.setPerson_id(expertid);
                this.merge(zrx);
                if(Util.isEoN(zrx.getSubjectendcode())) {
                    continue;
                }
//                ZjkLinkbq zjkLinkbq = new ZjkLinkbq();
//                String bqid = zjkBqService.fetchBqidByTypeAndCode("scly", zrx.getSubjectendcode());
//                JSONObject bqInfo = zjkBqService.fetchCompleteZjkBqInfo(bqid);
//                Util.ApplyObject(zjkLinkbq, bqInfo);
//                zjkLinkbq.setId(zrx.getId());
//                zjkLinkbq.setSeq(i);
//                zjkLinkbq.setCreatetime(new Date());
//                zjkLinkbq.setZjkryjbxxupdateid(zrx.getPerson_id());
//                zjkLinkbqService.merge(zjkLinkbq);
            }
        }

    }


    /**
     * 【专家注册】---获取学科--广州新改
     * @param expertid
     */
    public JSONArray getRyjbxxXkflNew(String expertid) {
        JSONArray resArr = new JSONArray();
        JSONObject resObj_zj = new JSONObject();
        JSONObject resObj_gb = new JSONObject();
        JSONObject resObj_js = new JSONObject();
        JSONObject resObj_hy = new JSONObject();
        JSONObject resObj_ly = new JSONObject();

        //基金学科
        List<Map> list_zj = this.dbHelper.getRows("select t.*, " +
                "e1.name as jjfirstleveldisciplinename,e2.name as firstleveldisciplinename," +
                "(case when t.subordinatediscipline = '无' then '无' else e3.name end) as subordinatedisciplinename," +
                "(case when t.thirdleveldiscipline = '无' then '无' else e4.name end) as thirdleveldisciplinename " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type e1 on t.jjfirstleveldiscipline = e1.code and e1.type = 'zj' " +
                "left join pms_subject_type e2 on t.firstleveldiscipline = e2.code and e2.type = 'zj' " +
                "left join pms_subject_type e3 on t.subordinatediscipline = e3.code and e3.type = 'zj' " +
                "left join pms_subject_type e4 on t.thirdleveldiscipline = e4.code and e4.type = 'zj' " +
                "where t.person_id = ? and t.xmzlfl = 'jjxk' " +
                "order by t.xmzlfl,t.seq", new Object[]{expertid});


        resObj_zj.put("array", list_zj);
        resObj_zj.put("type", "zj");

        //国标学科
        List<Map> list_gb = this.dbHelper.getRows("select t.*, " +
                "e1.name as firstleveldisciplinename,e2.name as subordinatedisciplinename," +
                "(case when t.thirdleveldiscipline = '无' then '无' else e3.name end) as thirdleveldisciplinename " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type e1 on t.firstleveldiscipline = e1.code and e1.type = 'xm' " +
                "left join pms_subject_type e2 on t.subordinatediscipline = e2.code and e2.type = 'xm' " +
                "left join pms_subject_type e3 on t.thirdleveldiscipline = e3.code and e3.type = 'xm' " +
                "where t.person_id = ? and t.xmzlfl = 'gbxk' " +
                "order by t.xmzlfl,t.seq", new Object[]{expertid});


        resObj_gb.put("array", list_gb);
        resObj_gb.put("type", "xm");

        //技术领域
        List<Map> list_js = this.dbHelper.getRows("select t.*, " +
                "e1.name as firstleveldisciplinename,e2.name as subordinatedisciplinename " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type e1 on t.firstleveldiscipline = e1.code and e1.type = 'jsly' " +
                "left join pms_subject_type e2 on t.subordinatediscipline = e2.code and e2.type = 'jsly' " +
                "where t.person_id = ? and t.xmzlfl = 'jsly' " +
                "order by t.xmzlfl,t.seq", new Object[]{expertid});


        resObj_js.put("array", list_js);
        resObj_js.put("type", "jsly");

        //行业领域
        List<Map> list_hy = this.dbHelper.getRows("select t.*, " +
                "e1.name as jjfirstleveldisciplinename,e2.name as firstleveldisciplinename," +
                "(case when t.subordinatediscipline = '无' then '无' else e3.name end) as subordinatedisciplinename," +
                "(case when t.thirdleveldiscipline = '无' then '无' else e4.name end) as thirdleveldisciplinename " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type e1 on t.jjfirstleveldiscipline = e1.code and e1.type = 'hyly' " +
                "left join pms_subject_type e2 on t.firstleveldiscipline = e2.code and e2.type = 'hyly' " +
                "left join pms_subject_type e3 on t.subordinatediscipline = e3.code and e3.type = 'hyly' " +
                "left join pms_subject_type e4 on t.thirdleveldiscipline = e4.code and e4.type = 'hyly' " +
                "where t.person_id = ? and t.xmzlfl = 'hyly' " +
                "order by t.xmzlfl,t.seq", new Object[]{expertid});


        resObj_hy.put("array", list_hy);
        resObj_hy.put("type", "hyly");


        //擅长领域
        List<Map> list_ly = this.dbHelper.getRows("select t.*, " +
                "e1.name as firstleveldisciplinename,e2.name as subordinatedisciplinename," +
                "(case when t.thirdleveldiscipline = '无' then '无' else e3.name end) as thirdleveldisciplinename " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type e1 on t.firstleveldiscipline = e1.code and e1.type = 'scly' " +
                "left join pms_subject_type e2 on t.subordinatediscipline = e2.code and e2.type = 'scly' " +
                "left join pms_subject_type e3 on t.thirdleveldiscipline = e3.code and e3.type = 'scly' " +
                "where t.person_id = ? and t.xmzlfl = 'scly' " +
                "order by t.xmzlfl,t.seq", new Object[]{expertid});


        resObj_ly.put("array", list_ly);
        resObj_ly.put("type", "scly");


        resArr.add(resObj_zj);
        resArr.add(resObj_gb);
        resArr.add(resObj_js);
        resArr.add(resObj_hy);
        resArr.add(resObj_ly);

        return resArr;
    }



    /**
     * 【专家注册】---保存学科
     * @param zjid
     * @param json
     */
    public void saveRyjbxxXkfl(String zjid, JSONObject json) {
        //国标学科
        ZjkRyjbxxxkflUpdate gbxkObj = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflList = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'gbxk'", new Object[]{zjid});
        if (zjkRyjbxxxkflList != null && zjkRyjbxxxkflList.size() > 0) {
            gbxkObj = zjkRyjbxxxkflList.get(0);
        }
        if (Util.isEoN(json.get("xkfl1") + "")) {
            //说明前台没有保存学科
            if (gbxkObj != null) {
                this.delete(gbxkObj);
            }
        } else {
            //说有要保存学科
            if (gbxkObj.getId() == null) {
                gbxkObj.setId(zjid + "gbxk");
            }
            gbxkObj.setFirstleveldiscipline(json.get("xkfl1") + "");
            if (!Util.isEoN(json.get("xkfl2") + "")) {
                gbxkObj.setSubordinatediscipline(json.get("xkfl2") + "");
                if (!Util.isEoN(json.get("xkfl3") + "")) {
                    gbxkObj.setThirdleveldiscipline(json.get("xkfl3") + "");
                    gbxkObj.setDiscipline(json.get("xkfl3") + "");
                } else {
                    gbxkObj.setThirdleveldiscipline("");
                    gbxkObj.setDiscipline(json.get("xkfl2") + "");
                }
            } else {
                gbxkObj.setSubordinatediscipline("");
                gbxkObj.setThirdleveldiscipline("");
                gbxkObj.setDiscipline(json.get("xkfl1") + "");
            }
            gbxkObj.setPerson_id(zjid);
            gbxkObj.setXmzlfl("gbxk");
            this.merge(gbxkObj);
        }
        //从事领域
        ZjkRyjbxxxkflUpdate domainObj = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflList2 = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'csly'", new Object[]{zjid});
        if (zjkRyjbxxxkflList2 != null && zjkRyjbxxxkflList2.size() > 0) {
            domainObj = zjkRyjbxxxkflList2.get(0);
        }
        if (Util.isEoN(json.get("domain1") + "")) {
            //说明前台没有保存学科
            if (domainObj != null) {
                this.delete(domainObj);
            }
        } else {
            //说有要保存学科
            if (domainObj.getId() == null) {
                domainObj.setId(zjid + "csly");
            }
            domainObj.setFirstleveldiscipline(json.get("domain1") + "");
            if (!Util.isEoN(json.get("domain2") + "")) {
                domainObj.setSubordinatediscipline(json.get("domain2") + "");
                if (!Util.isEoN(json.get("domain3") + "")) {
                    domainObj.setThirdleveldiscipline(json.get("domain3") + "");
                    domainObj.setDiscipline(json.get("domain3") + "");
                } else {
                    domainObj.setThirdleveldiscipline("");
                    domainObj.setDiscipline(json.get("domain2") + "");
                }
            } else {
                domainObj.setSubordinatediscipline("");
                domainObj.setThirdleveldiscipline("");
                domainObj.setDiscipline(json.get("domain1") + "");
            }
            domainObj.setPerson_id(zjid);
            domainObj.setXmzlfl("csly");
            this.merge(domainObj);
        }

        //自然基金学科A
        ZjkRyjbxxxkflUpdate zrjjObjA = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflListA = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'jjxk' and t.jjxktype = 'A'", new Object[]{zjid});
        if (zjkRyjbxxxkflListA != null && zjkRyjbxxxkflListA.size() > 0) {
            zrjjObjA = zjkRyjbxxxkflListA.get(0);
        }
        if (Util.isEoN(json.get("zrjjxk1A") + "") && Util.isEoN(json.get("zrjjxk2A") + "")) {
            //说明前台没有保存学科
            if (zrjjObjA != null) {
                this.delete(zrjjObjA);
            }
        } else {
            //说有要保存学科
            if (zrjjObjA.getId() == null) {
                zrjjObjA.setId(zjid + "jjxkA");
            }
            zrjjObjA.setFirstleveldiscipline(json.get("zrjjxk2A") + "");
            if (!Util.isEoN(json.get("zrjjxk3A") + "")) {
                zrjjObjA.setSubordinatediscipline(json.get("zrjjxk3A") + "");
                if (!Util.isEoN(json.get("zrjjxk4A") + "")) {
                    zrjjObjA.setThirdleveldiscipline(json.get("zrjjxk4A") + "");
                    zrjjObjA.setDiscipline(json.get("zrjjxk4A") + "");
                } else {
                    zrjjObjA.setThirdleveldiscipline("");
                    zrjjObjA.setDiscipline(json.get("zrjjxk3A") + "");
                }
            } else {
                zrjjObjA.setSubordinatediscipline("");
                zrjjObjA.setThirdleveldiscipline("");
                zrjjObjA.setDiscipline(json.get("zrjjxk2A") + "");
            }
            zrjjObjA.setPerson_id(zjid);
            zrjjObjA.setXmzlfl("jjxk");
            zrjjObjA.setJjxktype("A");
            this.merge(zrjjObjA);
        }

        //自然基金学科B
        ZjkRyjbxxxkflUpdate zrjjObjB = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflListB = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'jjxk' and t.jjxktype = 'B'", new Object[]{zjid});
        if (zjkRyjbxxxkflListB != null && zjkRyjbxxxkflListB.size() > 0) {
            zrjjObjB = zjkRyjbxxxkflListB.get(0);
        }
        if (Util.isEoN(json.get("zrjjxk1B") + "") && Util.isEoN(json.get("zrjjxk2B") + "")) {
            //说明前台没有保存学科
            if (zrjjObjB != null) {
                this.delete(zrjjObjB);
            }
        } else {
            //说有要保存学科
            if (zrjjObjB.getId() == null) {
                zrjjObjB.setId(zjid + "jjxkB");
            }
            zrjjObjB.setFirstleveldiscipline(json.get("zrjjxk2B") + "");
            if (!Util.isEoN(json.get("zrjjxk3B") + "")) {
                zrjjObjB.setSubordinatediscipline(json.get("zrjjxk3B") + "");
                if (!Util.isEoN(json.get("zrjjxk4B") + "")) {
                    zrjjObjB.setThirdleveldiscipline(json.get("zrjjxk4B") + "");
                    zrjjObjB.setDiscipline(json.get("zrjjxk4B") + "");
                } else {
                    zrjjObjB.setThirdleveldiscipline("");
                    zrjjObjB.setDiscipline(json.get("zrjjxk3B") + "");
                }
            } else {
                zrjjObjB.setSubordinatediscipline("");
                zrjjObjB.setThirdleveldiscipline("");
                zrjjObjB.setDiscipline(json.get("zrjjxk2B") + "");
            }
            zrjjObjB.setPerson_id(zjid);
            zrjjObjB.setXmzlfl("jjxk");
            zrjjObjB.setJjxktype("B");
            this.merge(zrjjObjB);
        }

        //自然基金学科C
        ZjkRyjbxxxkflUpdate zrjjObjC = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflListC = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'jjxk' and t.jjxktype = 'C'", new Object[]{zjid});
        if (zjkRyjbxxxkflListC != null && zjkRyjbxxxkflListC.size() > 0) {
            zrjjObjC = zjkRyjbxxxkflListC.get(0);
        }
        if (Util.isEoN(json.get("zrjjxk1C") + "") && Util.isEoN(json.get("zrjjxk2C") + "")) {
            //说明前台没有保存学科
            if (zrjjObjC != null) {
                this.delete(zrjjObjC);
            }
        } else {
            //说有要保存学科
            if (zrjjObjC.getId() == null) {
                zrjjObjC.setId(zjid + "jjxkC");
            }
            zrjjObjC.setFirstleveldiscipline(json.get("zrjjxk2C") + "");
            if (!Util.isEoN(json.get("zrjjxk3C") + "")) {
                zrjjObjC.setSubordinatediscipline(json.get("zrjjxk3C") + "");
                if (!Util.isEoN(json.get("zrjjxk4C") + "")) {
                    zrjjObjC.setThirdleveldiscipline(json.get("zrjjxk4C") + "");
                    zrjjObjC.setDiscipline(json.get("zrjjxk4C") + "");
                } else {
                    zrjjObjC.setThirdleveldiscipline("");
                    zrjjObjC.setDiscipline(json.get("zrjjxk3C") + "");
                }
            } else {
                zrjjObjC.setSubordinatediscipline("");
                zrjjObjC.setThirdleveldiscipline("");
                zrjjObjC.setDiscipline(json.get("zrjjxk2C") + "");
            }
            zrjjObjC.setPerson_id(zjid);
            zrjjObjC.setXmzlfl("jjxk");
            zrjjObjC.setJjxktype("C");
            this.merge(zrjjObjC);
        }
    }

    /**
     * 【专家注册】---获取学科
     * @param zjid
     */
    public JSONObject getRyjbxxXkfl(String zjid) {
        JSONObject resJson = new JSONObject();
        //国标学科
        ZjkRyjbxxxkflUpdate gbxkObj = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflList = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'gbxk'", new Object[]{zjid});
        if (zjkRyjbxxxkflList != null && zjkRyjbxxxkflList.size() > 0) {
            gbxkObj = zjkRyjbxxxkflList.get(0);
            resJson.put("xkfl1", gbxkObj.getFirstleveldiscipline());
            resJson.put("xkfl2", gbxkObj.getSubordinatediscipline());
            resJson.put("xkfl3", gbxkObj.getThirdleveldiscipline());
        } else {
            resJson.put("xkfl1", "");
            resJson.put("xkfl2", "");
            resJson.put("xkfl3", "");
        }
        //从事领域
        ZjkRyjbxxxkflUpdate cslyObj = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflList2 = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'csly'", new Object[]{zjid});
        if (zjkRyjbxxxkflList2 != null && zjkRyjbxxxkflList2.size() > 0) {
            cslyObj = zjkRyjbxxxkflList2.get(0);
            resJson.put("domain1", cslyObj.getFirstleveldiscipline());
            resJson.put("domain2", cslyObj.getSubordinatediscipline());
            resJson.put("domain3", cslyObj.getThirdleveldiscipline());
        } else {
            resJson.put("domain1", "");
            resJson.put("domain2", "");
            resJson.put("domain3", "");
        }

        //自然基金学科A
        ZjkRyjbxxxkflUpdate zrjjObjA = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflListA = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and t.xmzlfl = 'jjxk' and t.jjxktype = 'A'", new Object[]{zjid});
        if (zjkRyjbxxxkflListA != null && zjkRyjbxxxkflListA.size() > 0) {
            zrjjObjA = zjkRyjbxxxkflListA.get(0);
            resJson.put("zrjjxk2A", zrjjObjA.getFirstleveldiscipline());
            resJson.put("zrjjxk3A", zrjjObjA.getSubordinatediscipline());
            resJson.put("zrjjxk4A", zrjjObjA.getThirdleveldiscipline());
            if (!Util.isEoN(zrjjObjA.getFirstleveldiscipline())) {
                resJson.put("zrjjxk1A", zrjjObjA.getFirstleveldiscipline().substring(0, 1));
            }
        } else {
            resJson.put("zrjjxk1A", "");
            resJson.put("zrjjxk2A", "");
            resJson.put("zrjjxk3A", "");
            resJson.put("zrjjxk4A", "");
        }

        //自然基金学科B
        ZjkRyjbxxxkflUpdate zrjjObjB = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflListB = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and t.xmzlfl = 'jjxk' and t.jjxktype = 'B'", new Object[]{zjid});
        if (zjkRyjbxxxkflListB != null && zjkRyjbxxxkflListB.size() > 0) {
            zrjjObjB = zjkRyjbxxxkflListB.get(0);
            resJson.put("zrjjxk2B", zrjjObjB.getFirstleveldiscipline());
            resJson.put("zrjjxk3B", zrjjObjB.getSubordinatediscipline());
            resJson.put("zrjjxk4B", zrjjObjB.getThirdleveldiscipline());
            if (!Util.isEoN(zrjjObjB.getFirstleveldiscipline())) {
                resJson.put("zrjjxk1B", zrjjObjB.getFirstleveldiscipline().substring(0, 1));
            }
        } else {
            resJson.put("zrjjxk1B", "");
            resJson.put("zrjjxk2B", "");
            resJson.put("zrjjxk3B", "");
            resJson.put("zrjjxk4B", "");
        }

        //自然基金学科C
        ZjkRyjbxxxkflUpdate zrjjObjC = new ZjkRyjbxxxkflUpdate();
        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflListC = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and t.xmzlfl = 'jjxk' and t.jjxktype = 'C'", new Object[]{zjid});
        if (zjkRyjbxxxkflListC != null && zjkRyjbxxxkflListC.size() > 0) {
            zrjjObjC = zjkRyjbxxxkflListC.get(0);
            resJson.put("zrjjxk2C", zrjjObjC.getFirstleveldiscipline());
            resJson.put("zrjjxk3C", zrjjObjC.getSubordinatediscipline());
            resJson.put("zrjjxk4C", zrjjObjC.getThirdleveldiscipline());
            if (!Util.isEoN(zrjjObjC.getFirstleveldiscipline())) {
                resJson.put("zrjjxk1C", zrjjObjC.getFirstleveldiscipline().substring(0, 1));
            }
        } else {
            resJson.put("zrjjxk1C", "");
            resJson.put("zrjjxk2C", "");
            resJson.put("zrjjxk3C", "");
            resJson.put("zrjjxk4C", "");
        }
        return resJson;
    }


    public List<Map> findXkflTree(String type) {
        String table = "";
        if ("ly".equals(type)) {
            table = "pms_domain_type";
            type = "xm";
        } else {
            table = "pms_subject_type";
        }

        String firstsql = "select t.code,t.name,t.rank from " + table + " t where t.type = ? and t.rank = '1' order by t.seq";
        List<Map> firstlist = this.dbHelper.getRows(firstsql, new Object[]{type});

        if (firstlist.size() > 0) {
            for (int i = 0; i < firstlist.size(); i++) {
                String secondsql = "select t.code,t.name,t.rank from " + table + " t where t.type = ? and t.rank = '2' and t.code like ? order by t.seq";
                List<Map> secondlist = this.dbHelper.getRows(secondsql, new Object[]{type, firstlist.get(i).get("code") + "%"});

                if (secondlist.size() > 0) {
                    for (int j = 0; j < secondlist.size(); j++) {
                        String thirdsql = "select t.code,t.name,t.rank from " + table + " t where t.type = ? and t.rank = '3' and t.code like ? order by t.seq";
                        List<Map> thirdlist = this.dbHelper.getRows(thirdsql, new Object[]{type, secondlist.get(j).get("code") + "%"});

                        if (thirdlist.size() > 0) {
                            for (int k = 0; k < thirdlist.size(); k++) {
                                String fourthsql = "select t.code,t.name,t.rank from " + table + " t where t.type = ? and t.rank = '4' and t.code like ? order by t.seq";
                                List<Map> fourthlist = this.dbHelper.getRows(fourthsql, new Object[]{type, thirdlist.get(k).get("code") + "%"});

                                thirdlist.get(k).put("child", fourthlist);
                            }
                        }

                        secondlist.get(j).put("child", thirdlist);
                    }
                }

                firstlist.get(i).put("child", secondlist);
            }
        }

        return firstlist;
    }


    // public List<Map> updateXkflTree(String type) {
    //     String firstsql = "select t.code,t.name,t.rank from pms_subject_type t where t.type = ? and t.rank = '1' order by t.seq";
    //     List<Map> firstlist = this.dbHelper.getRows(firstsql, new Object[]{type});
    //
    //     if (firstlist.size() > 0) {
    //         for (int i = 0; i < firstlist.size(); i++) {
    //             String secondsql = "select t.code,t.name,t.rank from pms_subject_type t where t.type = ? and t.rank = '2' and t.code like ? order by t.seq";
    //             List<Map> secondlist = this.dbHelper.getRows(secondsql, new Object[]{type, firstlist.get(i).get("code") + "%"});
    //
    //             if (secondlist.size() > 0) {
    //                 for (int j = 0; j < secondlist.size(); j++) {
    //                     String thirdsql = "select t.code,t.name,t.rank from pms_subject_type t where t.type = ? and t.rank = '3' and t.code like ? order by t.seq";
    //                     List<Map> thirdlist = this.dbHelper.getRows(thirdsql, new Object[]{type, secondlist.get(j).get("code") + "%"});
    //
    //                     if (thirdlist.size() > 0) {
    //                         for (int k = 0; k < thirdlist.size(); k++) {
    //                             String fourthsql = "select t.code,t.name,t.rank from pms_subject_type t where t.type = ? and t.rank = '4' and t.code like ? order by t.seq";
    //                             List<Map> fourthlist = this.dbHelper.getRows(fourthsql, new Object[]{type, thirdlist.get(k).get("code") + "%"});
    //
    //                             thirdlist.get(k).put("child", fourthlist);
    //                         }
    //                     }
    //
    //                     secondlist.get(j).put("child", thirdlist);
    //                 }
    //             }
    //
    //             firstlist.get(i).put("child", secondlist);
    //         }
    //     }
    //
    //
    //     String xxid = type.toUpperCase() + "SUBJECT";
    //
    //     JSONArray xxx = JSONArray.fromObject(firstlist);
    //     PmsClob pmsClob = this.pmsClobService.findById(xxid);
    //     pmsClob.setSource(xxx.toString());
    //     pmsClobService.merge(pmsClob);
    //
    //     return firstlist;
    // }


    public List<Map> newfindXkflTree(String type) {
        String sql = "select source from pms_clob where mainid = 'xktree' and columnname = ? ";
        List<Map> list = dbHelper.getRows(sql, new Object[]{type});
        return list;
    }


    public JSONObject getSubjectJsonGB(String eid) {
        JSONObject resObj = new JSONObject();

        String sql = "select s1.name as zeroname,s1.code as zerocode,s2.name as onename,s2.code as onecode,s3.name as twoname,s3.code as twocode " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type s1 on s1.code = t.firstleveldiscipline and t.xmzlfl = 'gbxk' and s1.type = 'xm' " +
                "left join pms_subject_type s2 on s2.code = t.subordinatediscipline and t.xmzlfl = 'gbxk' and s2.type = 'xm' " +
                "left join pms_subject_type s3 on s3.code = t.thirdleveldiscipline and t.xmzlfl = 'gbxk' and s3.type = 'xm' " +
                "where t.person_id = ? and t.xmzlfl = 'gbxk' ";
        List<Map> list = this.dbHelper.getRows(sql, new Object[]{eid});

        resObj.put("xktype", "gbxk");
        resObj.put("xklist", list);
        return resObj;
    }


    public JSONObject getSubjectJsonJJ(String eid) {
        JSONObject resObj = new JSONObject();

        String sql = "select s0.name as zeroname,s0.code as zerocode,s1.name as onename,s1.code as onecode," +
                "s2.name as twoname,s2.code as twocode,s3.name as threename,s3.code as threecode " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type s0 on s0.code = t.jjfirstleveldiscipline and t.xmzlfl = 'jjxk' and s0.type = 'zj' " +
                "left join pms_subject_type s1 on s1.code = t.firstleveldiscipline and t.xmzlfl = 'jjxk' and s1.type = 'zj' " +
                "left join pms_subject_type s2 on s2.code = t.subordinatediscipline and t.xmzlfl = 'jjxk' and s2.type = 'zj' " +
                "left join pms_subject_type s3 on s3.code = t.thirdleveldiscipline and t.xmzlfl = 'jjxk' and s3.type = 'zj' " +
                "where t.person_id = ? and t.xmzlfl = 'jjxk' order by t.jjxktype";
        List<Map> list = this.dbHelper.getRows(sql, new Object[]{eid});

        resObj.put("xktype", "jjxk");
        resObj.put("xklist", list);
        return resObj;
    }


    public void saveRyjbxxXkflForChange(String zjid, JSONObject jsonObject) {
        JSONArray afterdata = jsonObject.getJSONArray("afterdata");
        if (afterdata.size() > 0) {
            for (int i = 0; i < afterdata.size(); i++) {
                JSONObject json = afterdata.getJSONObject(i);

                if ("xm".equals(json.get("subtype") + "")) {
                    try {
                        this.dbHelper.runSql("delete from zjk_ryjbxxxkfl_update t where t.person_id = ? and t.xmzlfl = 'gbxk'", new Object[]{zjid});
                    } catch (SQLException e) {

                    }
                    //国标学科
                    ZjkRyjbxxxkflUpdate gbxkObj = new ZjkRyjbxxxkflUpdate();
                    List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflList = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'gbxk'", new Object[]{zjid});
                    if (zjkRyjbxxxkflList != null && zjkRyjbxxxkflList.size() > 0) {
                        gbxkObj = zjkRyjbxxxkflList.get(0);
                    } else {
                        gbxkObj = new ZjkRyjbxxxkflUpdate();
                    }

                    if (Util.isEoN(json.get("zerocode") + "")) {
                        //说明前台没有保存学科
                        if (gbxkObj != null) {
                            this.delete(gbxkObj);
                        }
                    } else {
                        //说有要保存学科
                        if (gbxkObj.getId() == null) {
                            gbxkObj.setId(zjid + "gbxk");
                        }
                        gbxkObj.setFirstleveldiscipline(json.get("zerocode") + "");
                        if (!Util.isEoN(json.get("onecode") + "")) {
                            gbxkObj.setSubordinatediscipline(json.get("onecode") + "");
                            if (!Util.isEoN(json.get("twocode") + "")) {
                                gbxkObj.setThirdleveldiscipline(json.get("twocode") + "");
                                gbxkObj.setDiscipline(json.get("twocode") + "");
                            } else {
                                gbxkObj.setThirdleveldiscipline("");
                                gbxkObj.setDiscipline(json.get("onecode") + "");
                            }
                        } else {
                            gbxkObj.setSubordinatediscipline("");
                            gbxkObj.setThirdleveldiscipline("");
                            gbxkObj.setDiscipline(json.get("zerocode") + "");
                        }
                        gbxkObj.setPerson_id(zjid);
                        gbxkObj.setXmzlfl("gbxk");
                        this.merge(gbxkObj);
                    }
                } else if ("zj".equals(json.get("subtype") + "")) {
                    if (i == 0) {
                        try {
                            this.dbHelper.runSql("delete from zjk_ryjbxxxkfl_update t where t.person_id = ? and t.xmzlfl = 'jjxk'", new Object[]{zjid});
                        } catch (SQLException e) {

                        }
                        //自然基金学科A
                        ZjkRyjbxxxkflUpdate zrjjObjA = new ZjkRyjbxxxkflUpdate();
                        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflListA = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'jjxk' and t.jjxktype = 'A'", new Object[]{zjid});
                        if (zjkRyjbxxxkflListA != null && zjkRyjbxxxkflListA.size() > 0) {
                            zrjjObjA = zjkRyjbxxxkflListA.get(0);
                        } else {
                            zrjjObjA = new ZjkRyjbxxxkflUpdate();
                        }

                        if (Util.isEoN(json.get("zerocode") + "") && Util.isEoN(json.get("onecode") + "")) {
                            //说明前台没有保存学科
                            if (zrjjObjA != null) {
                                this.delete(zrjjObjA);
                            }
                        } else {
                            //说有要保存学科
                            if (zrjjObjA.getId() == null) {
                                zrjjObjA.setId(zjid + "jjxkA");
                            }
                            zrjjObjA.setFirstleveldiscipline(json.get("onecode") + "");
                            if (!Util.isEoN(json.get("twocode") + "")) {
                                zrjjObjA.setSubordinatediscipline(json.get("twocode") + "");
                                if (!Util.isEoN(json.get("threecode") + "")) {
                                    zrjjObjA.setThirdleveldiscipline(json.get("threecode") + "");
                                    zrjjObjA.setDiscipline(json.get("threecode") + "");
                                } else {
                                    zrjjObjA.setThirdleveldiscipline("");
                                    zrjjObjA.setDiscipline(json.get("twocode") + "");
                                }
                            } else {
                                zrjjObjA.setSubordinatediscipline("");
                                zrjjObjA.setThirdleveldiscipline("");
                                zrjjObjA.setDiscipline(json.get("onecode") + "");
                            }
                            zrjjObjA.setPerson_id(zjid);
                            zrjjObjA.setXmzlfl("jjxk");
                            zrjjObjA.setJjxktype("A");
                            zrjjObjA.setJjfirstleveldiscipline((json.get("onecode") + "").substring(0, 1));
                            this.merge(zrjjObjA);
                        }
                    } else if (i == 1) {
                        //自然基金学科B
                        ZjkRyjbxxxkflUpdate zrjjObjB = new ZjkRyjbxxxkflUpdate();
                        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflListB = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'jjxk' and t.jjxktype = 'B'", new Object[]{zjid});
                        if (zjkRyjbxxxkflListB != null && zjkRyjbxxxkflListB.size() > 0) {
                            zrjjObjB = zjkRyjbxxxkflListB.get(0);
                        } else {
                            zrjjObjB = new ZjkRyjbxxxkflUpdate();
                        }

                        if (Util.isEoN(json.get("zerocode") + "") && Util.isEoN(json.get("onecode") + "")) {
                            //说明前台没有保存学科
                            if (zrjjObjB != null) {
                                this.delete(zrjjObjB);
                            }
                        } else {
                            //说有要保存学科
                            if (zrjjObjB.getId() == null) {
                                zrjjObjB.setId(zjid + "jjxkB");
                            }
                            zrjjObjB.setFirstleveldiscipline(json.get("onecode") + "");
                            if (!Util.isEoN(json.get("twocode") + "")) {
                                zrjjObjB.setSubordinatediscipline(json.get("twocode") + "");
                                if (!Util.isEoN(json.get("threecode") + "")) {
                                    zrjjObjB.setThirdleveldiscipline(json.get("threecode") + "");
                                    zrjjObjB.setDiscipline(json.get("threecode") + "");
                                } else {
                                    zrjjObjB.setThirdleveldiscipline("");
                                    zrjjObjB.setDiscipline(json.get("twocode") + "");
                                }
                            } else {
                                zrjjObjB.setSubordinatediscipline("");
                                zrjjObjB.setThirdleveldiscipline("");
                                zrjjObjB.setDiscipline(json.get("onecode") + "");
                            }
                            zrjjObjB.setPerson_id(zjid);
                            zrjjObjB.setXmzlfl("jjxk");
                            zrjjObjB.setJjxktype("B");
                            zrjjObjB.setJjfirstleveldiscipline((json.get("onecode") + "").substring(0, 1));
                            this.merge(zrjjObjB);
                        }
                    } else if (i == 2) {
                        //自然基金学科A
                        ZjkRyjbxxxkflUpdate zrjjObjC = new ZjkRyjbxxxkflUpdate();
                        List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflListC = this.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ?0 and xmzlfl = 'jjxk' and t.jjxktype = 'C'", new Object[]{zjid});
                        if (zjkRyjbxxxkflListC != null && zjkRyjbxxxkflListC.size() > 0) {
                            zrjjObjC = zjkRyjbxxxkflListC.get(0);
                        } else {
                            zrjjObjC = new ZjkRyjbxxxkflUpdate();
                        }

                        if (Util.isEoN(json.get("zerocode") + "") && Util.isEoN(json.get("onecode") + "")) {
                            //说明前台没有保存学科
                            if (zrjjObjC != null) {
                                this.delete(zrjjObjC);
                            }
                        } else {
                            //说有要保存学科
                            if (zrjjObjC.getId() == null) {
                                zrjjObjC.setId(zjid + "jjxkC");
                            }
                            zrjjObjC.setFirstleveldiscipline(json.get("onecode") + "");
                            if (!Util.isEoN(json.get("twocode") + "")) {
                                zrjjObjC.setSubordinatediscipline(json.get("twocode") + "");
                                if (!Util.isEoN(json.get("threecode") + "")) {
                                    zrjjObjC.setThirdleveldiscipline(json.get("threecode") + "");
                                    zrjjObjC.setDiscipline(json.get("threecode") + "");
                                } else {
                                    zrjjObjC.setThirdleveldiscipline("");
                                    zrjjObjC.setDiscipline(json.get("twocode") + "");
                                }
                            } else {
                                zrjjObjC.setSubordinatediscipline("");
                                zrjjObjC.setThirdleveldiscipline("");
                                zrjjObjC.setDiscipline(json.get("onecode") + "");
                            }
                            zrjjObjC.setPerson_id(zjid);
                            zrjjObjC.setXmzlfl("jjxk");
                            zrjjObjC.setJjxktype("C");
                            zrjjObjC.setJjfirstleveldiscipline((json.get("onecode") + "").substring(0, 1));
                            this.merge(zrjjObjC);
                        }
                    }
                }
            }
        }
    }


    public ZjkRyjbxxxkflUpdate dealData(String id, String discipline, String type, Integer size, int i) {
        System.out.println(i + "/" + size + "  id:" + id);
        ZjkRyjbxxxkflUpdate zjkRyjbxxxkflUpdate = this.findById(id);
        if ("gbxk".equals(type)) {
            if (discipline.length() == 7) {
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'xm' ",new Object[]{discipline.substring(0, 3)}));
                zjkRyjbxxxkflUpdate.setSubordinatediscipline(discipline.substring(0, 5));
                zjkRyjbxxxkflUpdate.setSubjecttwocode(discipline.substring(0, 5));
                zjkRyjbxxxkflUpdate.setSubjecttwoname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'xm' ",new Object[]{discipline.substring(0, 5)}));
                zjkRyjbxxxkflUpdate.setThirdleveldiscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjectthreecode(discipline);
                zjkRyjbxxxkflUpdate.setSubjectthreename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'xm' ",new Object[]{discipline}));
            } else if (discipline.length() == 5) {
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'xm' ",new Object[]{discipline.substring(0, 3)}));
                zjkRyjbxxxkflUpdate.setSubordinatediscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjecttwocode(discipline);
                zjkRyjbxxxkflUpdate.setSubjecttwoname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'xm' ",new Object[]{discipline}));
            } else if (discipline.length() == 3) {
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline);
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'xm' ",new Object[]{discipline}));
            }
        } else if ("jsly".equals(type)) {
            if (discipline.length() == 3) {
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline);
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'jsly' ",new Object[]{discipline}));
            } else if (discipline.length() == 6) {
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'jsly' ",new Object[]{discipline.substring(0, 3)}));
                zjkRyjbxxxkflUpdate.setSubordinatediscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjecttwocode(discipline);
                zjkRyjbxxxkflUpdate.setSubjecttwoname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'jsly' ",new Object[]{discipline}));
            }
        } else if ("jjxk".equals(type)) {
            if (discipline.length() == 1) {
                zjkRyjbxxxkflUpdate.setJjfirstleveldiscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline);
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline}));
            } else if (discipline.length() == 3) {
                zjkRyjbxxxkflUpdate.setJjfirstleveldiscipline(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline.substring(0, 1)}));
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjecttwocode(discipline);
                zjkRyjbxxxkflUpdate.setSubjecttwoname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline}));
            } else if (discipline.length() == 5) {
                zjkRyjbxxxkflUpdate.setJjfirstleveldiscipline(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline.substring(0, 1)}));
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjecttwocode(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjecttwoname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline.substring(0, 3)}));
                zjkRyjbxxxkflUpdate.setSubordinatediscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjectthreecode(discipline);
                zjkRyjbxxxkflUpdate.setSubjectthreename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline}));
            } else if (discipline.length() == 7) {
                zjkRyjbxxxkflUpdate.setJjfirstleveldiscipline(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline.substring(0, 1)}));
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjecttwocode(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjecttwoname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline.substring(0, 3)}));
                zjkRyjbxxxkflUpdate.setSubordinatediscipline(discipline.substring(0, 5));
                zjkRyjbxxxkflUpdate.setSubjectthreecode(discipline.substring(0, 5));
                zjkRyjbxxxkflUpdate.setSubjectthreename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline.substring(0, 5)}));
                zjkRyjbxxxkflUpdate.setThirdleveldiscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjectfourcode(discipline);
                zjkRyjbxxxkflUpdate.setSubjectfourname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'zj' ",new Object[]{discipline}));
            }
        } else if ("hyly".equals(type)) {
            if (discipline.length() == 1) {
                zjkRyjbxxxkflUpdate.setJjfirstleveldiscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline);
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline}));
            } else if (discipline.length() == 3) {
                zjkRyjbxxxkflUpdate.setJjfirstleveldiscipline(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline.substring(0, 1)}));
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjecttwocode(discipline);
                zjkRyjbxxxkflUpdate.setSubjecttwoname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline}));
            } else if (discipline.length() == 4) {
                zjkRyjbxxxkflUpdate.setJjfirstleveldiscipline(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline.substring(0, 1)}));
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjecttwocode(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjecttwoname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline.substring(0, 3)}));
                zjkRyjbxxxkflUpdate.setSubordinatediscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjectthreecode(discipline);
                zjkRyjbxxxkflUpdate.setSubjectthreename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline}));
            } else if (discipline.length() == 5) {
                zjkRyjbxxxkflUpdate.setJjfirstleveldiscipline(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonecode(discipline.substring(0, 1));
                zjkRyjbxxxkflUpdate.setSubjectonename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline.substring(0, 1)}));
                zjkRyjbxxxkflUpdate.setFirstleveldiscipline(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjecttwocode(discipline.substring(0, 3));
                zjkRyjbxxxkflUpdate.setSubjecttwoname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline.substring(0, 3)}));
                zjkRyjbxxxkflUpdate.setSubordinatediscipline(discipline.substring(0, 4));
                zjkRyjbxxxkflUpdate.setSubjectthreecode(discipline.substring(0, 4));
                zjkRyjbxxxkflUpdate.setSubjectthreename(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline.substring(0, 4)}));
                zjkRyjbxxxkflUpdate.setThirdleveldiscipline(discipline);
                zjkRyjbxxxkflUpdate.setSubjectfourcode(discipline);
                zjkRyjbxxxkflUpdate.setSubjectfourname(this.dbHelper.getOnlyStringValue("select name from pms_subject_type " +
                        "where code = ? and type = 'hyly' ",new Object[]{discipline}));
            }
        }
//		try {
//            this.getZjkRyjbxxxkflUpdateDao().merge(zjkRyjbxxxkflUpdate);
//            this.dbHelper.runSql("update ZJK_RYJBXXXKFL_UPDATE set project_id = null where id = ?", new Object[]{id});
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return zjkRyjbxxxkflUpdate;
    }


    /**
     * 广州市科技专家简历
     */
    public Map gzskjzjjl(HttpServletRequest request , JSONObject jo) throws Exception {
        String userid = "";
        String unitid = "";
        String id = jo.get("id")+"";
        //先直接从专家主表里查
        ZjkRyjbxxUpdate zjkRyjbxxUpdate = zjkRyjbxxUpdateService.findById(id);
        unitid = zjkRyjbxxUpdate.getUnitid()+"";

        if (Util.isEoN(unitid)){
            //再通过user表关联查找
            try {
                SysUser byId = sysUserService.findById(id);
                unitid = byId.getSysIdentitys().get(0).getPmsEnterprise().getId();
            }catch (Exception e){
                log.error(e.getMessage());
                log.error("未获取到所选专家的身份信息");
                throw new RuntimeException("未获取到所选专家的身份信息");
            }
        }

        Map map =new HashMap();

        //获取单位信息
        Map unitinfo = new HashMap();
        List<Map> unitinfoList = this.dbHelper.getRows("" +
                "select unitname,unittype,isgxqy,gxqyenddate,OFFICEADDRESS,ber.name as linkman,ber.TELEPHONE as linktelephone " +
                "from BI_ENT_BI beb " +
                "left join BI_ENT_RY ber on beb.MAINID=ber.MAINID and RYTYPE='单位联系人' " +
                "where beb.mainid = ? ", new Object[]{unitid});
        if (null!=unitinfoList && unitinfoList.size()>0){
            unitinfo = unitinfoList.get(0);
        }

        //获取用户注册信息
        Map userinfo =new HashMap();
        List<Map> userinfoList = this.dbHelper.getRows("" +
                "select NAME,MOBILE,NATION,GENDER,CERTIFICATENAME,CERTIFICATENUMBER,to_char(BIRTHDAY,'yyyy-MM-dd') as birthday, " +
                "       EMAIL,NATIONALITY,INTRODUCE,TELEPHONE, " +
                "       btw1.UNITNAME as school,btw1.EDUCATION,btw1.PROFESSION as major,btw1.DEGREE, " +
                "       btw2.post,btw2.DEGREE as position_title,btw2.ADDRESS as postal_address,to_char(btw2.degreedate,'yyyy-MM-dd') as degreedate  " +
                "from BI_TALENT_BI btb " +
                "left join BI_TALENT_WE btw1 on btb.MAINID=btw1.MAINID and btw1.TYPE='learn_current' " +
                "left join BI_TALENT_WE btw2 on btb.MAINID=btw2.MAINID and btw2.TYPE='work_current' " +
                "where btb.MAINID= ? ", new Object[]{id});
        if (null!=userinfoList && userinfoList.size()>0){
            userinfo = userinfoList.get(0);
        }

/*        //获取专家特征信息和称号  老的弃用
        List<Map> expertinfo = this.dbHelper.getRows("" +
                "select ISWILLSW,BANK_NAME,BANK_ACCOUNT,BANK_ACCOUNTNAME,EXPERT_TYPE,EXPERT_CLASSIFY, " +
                "       zriu.TALENT,zriu.MAJOR,zriu.REWARD,zriu.NATIONAL,zriu.PROFESSIONAL,zriu.other," +
                "       zriu.finance,zriu.management,zriu.technology,zriu.law,zriu.financial " +
                "from ZJK_RYJBXX_UPDATE zru " +
                "left join ZJK_RYJBXX_INFO_UPDATE zriu on zru.id=zriu.ZJKRYJBXXUPDATEID " +
                "where zru.id = ?", new Object[]{id});
        if (null!=expertinfo && expertinfo.size()>0){
            map.put("zj",expertinfo.get(0));
        }*/

        //获取专家特征信息
        Map featureinfo = new HashMap();
        List<Map> featureinfoList = this.dbHelper.getRows(
           "select ISWILLSW,BANK_NAME,BANK_ACCOUNT,BANK_ACCOUNTNAME,EXPERT_TYPE,EXPERT_CLASSIFY, " +
                "zriu.finance,zriu.management,zriu.technology,zriu.law,zriu.financial " +
                "from ZJK_RYJBXX_UPDATE zru " +
                "left join ZJK_RYJBXX_INFO_UPDATE zriu on zru.id=zriu.ZJKRYJBXXUPDATEID " +
                "where zru.id = ?", new Object[]{id});
        if (null!=featureinfoList && featureinfoList.size()>0){
            featureinfo = featureinfoList.get(0);
        }

        //获取所获称号
        Map zjch = new HashMap();
        List<Map> zjchList = this.dbHelper.getRows(
           "select zriu.TALENT,zriu.MAJOR,zriu.REWARD,zriu.NATIONAL,zriu.PROFESSIONAL,zriu.other," +
                "       zriu.finance,zriu.management,zriu.technology,zriu.law,zriu.financial " +
                "from ZJK_RYJBXX_UPDATE zru " +
                "left join ZJK_RYJBXX_INFO_UPDATE zriu on zru.id=zriu.ZJKRYJBXXUPDATEID " +
                "where zru.id = ?", new Object[]{id});
        if (null!=zjchList && zjchList.size()>0){
            zjch=zjchList.get(0);
        }



        //获取专家信息变更后的新值
        List<Map> listBySql = this.dbHelper.getRows("select content from expert_temp t where sourceid = ? and status = '审核完成，通过' order by t.changedate desc", new Object[]{id});
        if (null!=listBySql&&listBySql.size()>0){
            //说明有该专家有信息变更且已完成
            // TODO: 2022/6/8 会不会有空指针或者转换异常？
            Clob o = (Clob)listBySql.get(0).get("content");//Clob类型，转为String
            String s = clobToString(o);
            JSONObject content = JSONObject.fromObject(s);
            if (!Util.isEoN(content)){
                Iterator keys = content.keys();
                while (keys.hasNext()){
                    String key = keys.next()+"";
                    JSONArray array;
                    switch (key){//这里是为了变更后的新值 覆盖 老值
                        case "ryjbxx":
                            array = content.getJSONArray(key);
                            for (Object o1 : array) {
                                JSONObject jsonObject = JSONObject.fromObject(o1);
                                String field = jsonObject.get("field")+"";
                                String newvalue = jsonObject.get("newvalue")+"";
                                featureinfo.put(field,newvalue);
                            }
                            break;
                        case "featureinfo":
                            array = content.getJSONArray(key);
                            for (Object o1 : array) {
                                JSONObject jsonObject = JSONObject.fromObject(o1);
                                String field = jsonObject.get("field")+"";
                                String newvalue = jsonObject.get("newvalue")+"";
                                featureinfo.put(field,newvalue);
                            }
                            break;
                        case "ryjbxxinfo":
                            array = content.getJSONArray(key);
                            for (Object o1 : array) {
                                JSONObject jsonObject = JSONObject.fromObject(o1);
                                String field = jsonObject.get("field")+"";
                                String newvalue = jsonObject.get("newvalue")+"";
                                featureinfo.put(field,newvalue);
                            }
                            break;
                        case "subjectArr":
                            //科目类的，暂时没有看到有新老值冲突的情况，不动他
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        //基金学科
        List<Map> list_zj = this.dbHelper.getRows("select e1.name as jjfirstleveldisciplinename,e2.name as firstleveldisciplinename, " +
                "       (case when t.subordinatediscipline = '无' then '无' else e3.name end) as subordinatedisciplinename, " +
                "       (case when t.thirdleveldiscipline = '无' then '无' else e4.name end) as thirdleveldisciplinename, " +
                "       rownum as rn " +
                "from zjk_ryjbxxxkfl_update t " +
                "    left join pms_subject_type e1 on t.jjfirstleveldiscipline = e1.code and e1.type = 'zj' " +
                "    left join pms_subject_type e2 on t.firstleveldiscipline = e2.code and e2.type = 'zj' " +
                "    left join pms_subject_type e3 on t.subordinatediscipline = e3.code and e3.type = 'zj' " +
                "    left join pms_subject_type e4 on t.thirdleveldiscipline = e4.code and e4.type = 'zj' " +
                "where t.person_id = ? and t.xmzlfl = 'jjxk' " +
                "order by t.xmzlfl,t.seq", new Object[]{id});
        map.put("jj",list_zj);

        //国家技术领域
        List<Map> list_gb = this.dbHelper.getRows("select " +
                "e1.name as firstleveldisciplinename,e2.name as subordinatedisciplinename," +
                "(case when t.thirdleveldiscipline = '无' then '无' else e3.name end) as thirdleveldisciplinename," +
                "rownum as rn " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type e1 on t.firstleveldiscipline = e1.code and e1.type = 'xm' " +
                "left join pms_subject_type e2 on t.subordinatediscipline = e2.code and e2.type = 'xm' " +
                "left join pms_subject_type e3 on t.thirdleveldiscipline = e3.code and e3.type = 'xm' " +
                "where t.person_id = ? and t.xmzlfl = 'gbxk' " +
                "order by t.xmzlfl,t.seq", new Object[]{id});
        map.put("gjjsly",list_gb);

        //广东技术领域
        List<Map> list_js = this.dbHelper.getRows("select " +
                "e1.name as firstleveldisciplinename,e2.name as subordinatedisciplinename," +
                "rownum as rn " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type e1 on t.firstleveldiscipline = e1.code and e1.type = 'jsly' " +
                "left join pms_subject_type e2 on t.subordinatediscipline = e2.code and e2.type = 'jsly' " +
                "where t.person_id = ? and t.xmzlfl = 'jsly' " +
                "order by t.xmzlfl,t.seq", new Object[]{id});
        map.put("gdjsly",list_js);

        //行业领域
        List<Map> list_hy = this.dbHelper.getRows("select  " +
                "e1.name as jjfirstleveldisciplinename,e2.name as firstleveldisciplinename," +
                "(case when t.subordinatediscipline = '无' then '无' else e3.name end) as subordinatedisciplinename," +
                "(case when t.thirdleveldiscipline = '无' then '无' else e4.name end) as thirdleveldisciplinename," +
                "rownum as rn " +
                "from zjk_ryjbxxxkfl_update t " +
                "left join pms_subject_type e1 on t.jjfirstleveldiscipline = e1.code and e1.type = 'hyly' " +
                "left join pms_subject_type e2 on t.firstleveldiscipline = e2.code and e2.type = 'hyly' " +
                "left join pms_subject_type e3 on t.subordinatediscipline = e3.code and e3.type = 'hyly' " +
                "left join pms_subject_type e4 on t.thirdleveldiscipline = e4.code and e4.type = 'hyly' " +
                "where t.person_id = ? and t.xmzlfl = 'hyly' " +
                "order by t.xmzlfl,t.seq", new Object[]{id});
        map.put("hyly",list_hy);

        //以下几个在后面才加进去是担心有 变更 这样防止新值放不进去
        map.put("bientbi",unitinfo);

        map.put("user",userinfo);

        map.put("featureinfo",featureinfo);

        map.put("zjch",zjch);


        return map;
    }

    public String clobToString(Clob clob) throws Exception {
        String re = "";
        Reader is = null;
        BufferedReader br = null;
        try {
            // 得到流
            is = clob.getCharacterStream();
            br = new BufferedReader(is);
            String s = br.readLine();
            StringBuffer sb = new StringBuffer();
            // 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            while (s != null) {
                sb.append(s);
                s = br.readLine();
            }
            re = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }
        }
        return re;
    }


}
