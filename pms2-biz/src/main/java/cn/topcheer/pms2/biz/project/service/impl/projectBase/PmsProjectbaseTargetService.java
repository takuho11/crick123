/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-4-27 16:55:36
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseTarget;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseTargetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PmsProjectbaseTarget 服务类
 */
@Service(value="PmsProjectbaseTargetService")
public class PmsProjectbaseTargetService extends GenericService<PmsProjectbaseTarget> {
	@Autowired
	DBHelper dbHelper;

	public PmsProjectbaseTargetDao getPmsProjectbaseTargetDao() {
		return (PmsProjectbaseTargetDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaseTargetDao(PmsProjectbaseTargetDao pmsProjectbaseTargetDao) {

		this.setGenericDao(pmsProjectbaseTargetDao);
	}


    public List<PmsProjectbaseTarget> findTargetByPidAndtype(String projectbaseid,String type){
        List<PmsProjectbaseTarget> list = this.getPmsProjectbaseTargetDao().findByHql("select t from PmsProjectbaseTarget t where t.projectbaseid = ? and t.type = ? order by t.seq",new Object[]{projectbaseid,type});
        return list;
    }
	public List<Map> findByPid(String projectbaseid){
		return dbHelper.getRows("select * from PMS_PROJECTBASE_TARGET where projectbaseid = ?",new Object[]{projectbaseid});
	}
	
}
