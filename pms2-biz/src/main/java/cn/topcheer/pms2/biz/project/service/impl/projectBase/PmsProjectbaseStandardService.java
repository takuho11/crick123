/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-5-29 19:53:08
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseStandard;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseStandardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PmsProjectbaseStandard 服务类
 */
@Service(value="PmsProjectbaseStandardService")
public class PmsProjectbaseStandardService extends GenericService<PmsProjectbaseStandard> {
	@Autowired
	DBHelper dbHelper;

	public PmsProjectbaseStandardDao getPmsProjectbaseStandardDao() {
		return (PmsProjectbaseStandardDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaseStandardDao(PmsProjectbaseStandardDao pmsProjectbaseStandardDao) {

		this.setGenericDao(pmsProjectbaseStandardDao);
	}

	public List<PmsProjectbaseStandard> findStandardByPid(String projectbaseid){
		List<PmsProjectbaseStandard> list = this.getPmsProjectbaseStandardDao().findByHql("select t from PmsProjectbaseStandard t where t.projectbaseid = ?  order by t.seq",new Object[]{projectbaseid});
		return list;
	}

	public List<Map> findByPid(String projectbaseid){
		return dbHelper.getRows("select * from PMS_PROJECTBASE_STANDARD where projectbaseid = ?",new Object[]{projectbaseid});
	}
	
}
