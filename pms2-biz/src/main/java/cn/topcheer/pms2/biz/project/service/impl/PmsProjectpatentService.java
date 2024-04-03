/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2017-5-9 17:13:39
 *
 */
package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PmsProjectpatent 服务类
 */
@Service(value="PmsProjectpatentService")
public class PmsProjectpatentService extends GenericService<PmsProjectpatent> {

	@Autowired
	DBHelper dbHelper;
	
	public PmsProjectpatentDao getPmsProjectpatentDao() {
		return (PmsProjectpatentDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectpatentDao(PmsProjectpatentDao pmsProjectpatentDao) {

		this.setGenericDao(pmsProjectpatentDao);
	}

    public List findAllpatent(String projectbaseId) {
		String sql = "select t.id,t.name,t.country,t.authorizationnumber,t.publishman,to_char(t.publishtime,'yyyy-mm-dd') as publishtime,t.industrialization,t.targetproduct,t.newproduction,to_char(t.publishendtime,'yyyy-mm-dd') as publishendtime,t.inventor"
				+ " from pms_projectpatent t where t.projectbaseid=? order by t.seq";
        List list = this.dbHelper.getRows(sql, new Object[]{projectbaseId});
        return list;
    }
	
	public List<PmsProjectpatent> findAllpatentForsave(String projectbaseId) {
		/*String sql = "select t.id,t.name,t.country,t.authorizationnumber,t.publishman,to_char(t.publishtime,'yyyy-mm-dd') as publishtime,t.industrialization,t.targetproduct,t.newproduction,to_char(t.publishendtime,'yyyy-mm-dd') as publishendtime,t.inventor"
				+ " from pms_projectpatent t where t.projectbaseid=? order by t.seq";*/
        String hql = "select t from PmsProjectpatent t  where t.projectbaseid=? order by t.seq";
        List<PmsProjectpatent> list = this.getPmsProjectpatentDao().findByHql(hql, new Object[]{projectbaseId});
		//List<PmsProjectpatent> list = this.dbHelper.getRows(sql, new Object[]{projectbaseId});
		return list;
	}

	
}
