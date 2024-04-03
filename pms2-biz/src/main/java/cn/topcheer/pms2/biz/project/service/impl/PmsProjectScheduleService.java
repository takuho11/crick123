package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.PmsProjectschedule;
import cn.topcheer.pms2.dao.project.PmsProjectscheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "PmsProjectscheduleService")
public class PmsProjectScheduleService extends GenericService<PmsProjectschedule> {

	public PmsProjectscheduleDao getPmsProjectscheduleDao() {
		return (PmsProjectscheduleDao) this.getGenericDao();
	}

    @Autowired
    DBHelper dbHelper;

	@Autowired
	public void setPmsProjectscheduleDao(
			PmsProjectscheduleDao pmsProjectscheduleDao) {
		this.setGenericDao(pmsProjectscheduleDao);
	}
    public List<PmsProjectschedule> findByJxid(String jxid){
        String hql = "select t from PmsProjectschedule t where t.xmid =? order by t.seq";
        List<PmsProjectschedule> list = this.getPmsProjectscheduleDao().findByHql(hql, new Object[]{jxid});
        return list;
    }

    public List<PmsProjectschedule> findByJxidNew(String jxid){
        String hql = "select t from PmsProjectschedule t left join t.pmsProjectbase projectbase where projectbase.id=? order by t.seq";
        List<PmsProjectschedule> list = this.getPmsProjectscheduleDao().findByHql(hql, new Object[]{jxid});
        if(list.size()<1){
            String hql2 = "select t from PmsProjectschedule t where t.xmid =? order by t.seq";
            list = this.getPmsProjectscheduleDao().findByHql(hql2, new Object[]{jxid});
        }
        return list;
    }

    public List<Map> findBypid(String pid){
        String sql = "select t.* from Pms_Projectschedule t where t.projectbaseid = ? order by t.seq";
        List<Map> list = dbHelper.getRows(sql, new Object[]{pid});

        return list;
    }
}
