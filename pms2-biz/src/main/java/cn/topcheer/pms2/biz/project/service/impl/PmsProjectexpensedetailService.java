/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2016-10-10 10:10:33
 *
 */
package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PmsProjectexpensedetail 服务类
 */
@Service(value="PmsProjectexpensedetailService")
public class PmsProjectexpensedetailService extends GenericService<PmsProjectexpensedetail> {

	public PmsProjectexpensedetailDao getPmsProjectexpensedetailDao() {
		return (PmsProjectexpensedetailDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectexpensedetailDao(PmsProjectexpensedetailDao pmsProjectexpensedetailDao) {

		this.setGenericDao(pmsProjectexpensedetailDao);
	}

	public List<PmsProjectexpensedetail> findPmsIPartyByIdAndType(
			JSONObject json) {
		// TODO Auto-generated method stub
		String hql = "from cn.topcheer.pms2.api.project.entity.PmsProjectexpensedetail p where p.xmsbsid = ? and"
				+ " p.type = ?  order by p.seq";
		return this.getPmsProjectexpensedetailDao().findByHql(hql, new Object[]{json.get("pid"),json.get("type")});
	}

	
}
