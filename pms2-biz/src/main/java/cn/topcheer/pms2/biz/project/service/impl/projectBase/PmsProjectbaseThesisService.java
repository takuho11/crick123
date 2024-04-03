/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-5-24 14:59:28
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseThesis;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseThesisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PmsProjectbaseThesis 服务类
 */
@Service(value="PmsProjectbaseThesisService")
public class PmsProjectbaseThesisService extends GenericService<PmsProjectbaseThesis> {
	@Autowired
	DBHelper dbHelper;

	public PmsProjectbaseThesisDao getPmsProjectbaseThesisDao() {
		return (PmsProjectbaseThesisDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaseThesisDao(PmsProjectbaseThesisDao pmsProjectbaseThesisDao) {

		this.setGenericDao(pmsProjectbaseThesisDao);
	}

	public List<PmsProjectbaseThesis> findThesisByPidandType(String projectbaseid,String type){
		List<PmsProjectbaseThesis> list = this.getPmsProjectbaseThesisDao().findByHql("select t from PmsProjectbaseThesis t where t.projectbaseid = ? and type = ? order by t.seq",new Object[]{projectbaseid,type});
		return list;
	}

	public List<Map> findByPid(String projectbaseid){
		return dbHelper.getRows("select * from PMS_PROJECTBASE_THESIS where projectbaseid = ?",new Object[]{projectbaseid});
	}
}
