/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2022-10-26 16:26:50
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseJfysout;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseJfysoutDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PmsProjectbaseJfysout 服务类
 */
@Service(value = "PmsProjectbaseJfysoutService")
public class PmsProjectbaseJfysoutService extends GenericService<PmsProjectbaseJfysout> {
    @Autowired
    DBHelper dbHelper;

    public PmsProjectbaseJfysoutDao getPmsProjectbaseJfysoutDao() {
        return (PmsProjectbaseJfysoutDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsProjectbaseJfysoutDao(PmsProjectbaseJfysoutDao pmsProjectbaseJfysoutDao) {

        this.setGenericDao(pmsProjectbaseJfysoutDao);
    }

    @Autowired
    PmsProjectbaseService pmsProjectbaseService;

    public List<PmsProjectbaseJfysout> findJfysoutByPidAndtype(String projectbaseid, String type) {
        List<PmsProjectbaseJfysout> list = this.getPmsProjectbaseJfysoutDao().findByHql("select t from PmsProjectbaseJfysout t where t.mianid = ? and t.type = ? order by t.seq", new Object[]{projectbaseid, type});
        return list;
    }

    public List<Map> findByPid(String projectbaseid) {
        return dbHelper.getRows("select * from PMS_PROJECTBASE_JFYSOUT where mianid = ?", new Object[]{projectbaseid});
    }

    /**
     * 获取项目预算科目name和打分情况
     * @param jsonObject
     * @return
     */
    public JSONObject findNameByJson(JSONObject jsonObject) {
        JSONObject resObj = new JSONObject();
        String planid = String.valueOf(jsonObject.get("planid"));
        String pid = String.valueOf(jsonObject.get("pid"));
        String tid = String.valueOf(jsonObject.get("tid"));
        String eid = String.valueOf(jsonObject.get("eid"));
        String count = this.getOnlyValueBySql("select count(*) from rev_special_jfysout " +
                "where planid = ? and tid = ? and eid = ? and pid = ? ", new Object[]{planid, tid, eid, pid});
        //打过分,有意见项了可以直接返回
        if (Integer.valueOf(count) > 0) {
            List<Map> pmsProjectbaseJfysoutList = dbHelper.getRows("select p.totalsum as jfystotal,p.id as jfysid,p.name,rsj.id, " +
                    "rsj.totalsum,rsj.hzje,rsj.pid,rsj.opinion,rsj.type,? as planid,? as eid,? as tid " +
                    "from rev_special_jfysout rsj " +
                    "left join pms_projectbase_jfysout p on rsj.jfysid = p.id  " +
                    "where rsj.planid = ? and rsj.tid = ? and rsj.eid = ? and rsj.pid = ? " +
                    "and p.id is not null " +
                    "order by p.seq ", new Object[]{planid, eid, tid, planid, tid, eid, pid});

            resObj.put("jfysoutList", pmsProjectbaseJfysoutList);

            List<Map> pmsProjectbaseJfysinList = dbHelper.getRows("select p.ysjftotal as jfystotal,p.id as jfysid,p.name,rsj.id, " +
                    "rsj.totalsum,rsj.hzje,rsj.pid,rsj.opinion,rsj.type,? as planid,? as eid,? as tid " +
                    "from rev_special_jfysout rsj " +
                    "left join pms_projectbase_jfysin p on rsj.jfysid = p.id  " +
                    "where rsj.planid = ? and rsj.tid = ? and rsj.eid = ? and rsj.pid = ? " +
                    "and (p.id is not null or rsj.type = '意见项') " +
                    "order by p.seq nulls last ", new Object[]{planid, eid, tid, planid, tid, eid, pid});

            resObj.put("jfysinList", pmsProjectbaseJfysinList);

        } else {
            List<Map> pmsProjectbaseJfysoutList = dbHelper.getRows("select p.totalsum as jfystotal,p.id as jfysid,p.name,rsj.id, " +
                    "rsj.totalsum,rsj.hzje,p.mainid as pid,rsj.opinion,? as planid,? as eid,? as tid,'打分项' as type " +
                    "from pms_projectbase_jfysout p " +
                    "left join rev_special_jfysout rsj on rsj.jfysid = p.id and rsj.planid = ? and rsj.tid = ? and rsj.eid = ? " +
                    "where p.mainid = ? order by p.seq ", new Object[]{planid, eid, tid, planid, tid, eid, pid});

            resObj.put("jfysoutList", pmsProjectbaseJfysoutList);

            List<Map> pmsProjectbaseJfysinList = dbHelper.getRows("select p.ysjftotal as jfystotal,p.id as jfysid,p.name,rsj.id, " +
                    "rsj.totalsum,rsj.hzje,p.mainid as pid,rsj.opinion,? as planid,? as eid,? as tid,'打分项' as type " +
                    "from pms_projectbase_jfysin p " +
                    "left join rev_special_jfysout rsj on rsj.jfysid = p.id and rsj.planid = ? and rsj.tid = ? and rsj.eid = ? " +
                    "where p.mainid = ? order by p.seq ", new Object[]{planid, eid, tid, planid, tid, eid, pid});

            Map<String, String> map = new HashMap<>();
            map.put("planid", planid);
            map.put("tid", tid);
            map.put("eid", eid);
            map.put("pid", pid);
            map.put("opinion", "");
            map.put("type", "意见项");
            pmsProjectbaseJfysinList.add(map);

            resObj.put("jfysinList", pmsProjectbaseJfysinList);
        }


        PmsProjectbase pmsProjectbase = this.pmsProjectbaseService.findById(pid);
        //resObj.put("projecttotalsum", Util.isEoN(pmsProjectbase.getProjecttotalsum()) ? "0" : pmsProjectbase.getProjecttotalsum().toString());
        resObj.put("projectsumforgov", Util.isEoN(pmsProjectbase.getProjectsumforgov()) ? "0" : pmsProjectbase.getProjectsumforgov().toString());
        resObj.put("projectsumforself", Util.isEoN(pmsProjectbase.getProjectsumforself()) ? "0" : pmsProjectbase.getProjectsumforself().toString());
        resObj.put("projectsumforother", Util.isEoN(pmsProjectbase.getProjectsumforother()) ? "0" : pmsProjectbase.getProjectsumforother().toString());


        return resObj;
    }
}
