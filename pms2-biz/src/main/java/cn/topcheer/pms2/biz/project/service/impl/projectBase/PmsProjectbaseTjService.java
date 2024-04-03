/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2022-10-26 16:26:50
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseTj;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseTjDao;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PmsProjectbaseTj 服务类
 */
@Service(value="PmsProjectbaseTjService")
public class PmsProjectbaseTjService extends GenericService<PmsProjectbaseTj> {

	public PmsProjectbaseTjDao getPmsProjectbaseTjDao() {
		return (PmsProjectbaseTjDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaseTjDao(PmsProjectbaseTjDao pmsProjectbaseTjDao) {

		this.setGenericDao(pmsProjectbaseTjDao);
	}

	public List<PmsProjectbaseTj> findTjByPidandType(String projectbaseid,String type){
		JSONArray array = new JSONArray();
		array.add(projectbaseid);
		String insql = " and 1=1 ";
		if(!"".equals(type)){
			insql = " and t.type = ? ";
			array.add(type);
		}
		List<PmsProjectbaseTj> list = this.getPmsProjectbaseTjDao().findByHql("select t from PmsProjectbaseTj t where t.projectbaseid = ? "+insql+" order by t.seq",array.toArray());
		return list;
	}
	
}
