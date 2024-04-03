/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-5-24 14:59:28
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbasePatent;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbasePatentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PmsProjectbasePatent 服务类
 */
@Service(value="PmsProjectbasePatentService")
public class PmsProjectbasePatentService extends GenericService<PmsProjectbasePatent> {

	public PmsProjectbasePatentDao getPmsProjectbasePatentDao() {
		return (PmsProjectbasePatentDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbasePatentDao(PmsProjectbasePatentDao pmsProjectbasePatentDao) {

		this.setGenericDao(pmsProjectbasePatentDao);
	}

	public List<PmsProjectbasePatent> findPatentByPidandType(String projectbaseid,String type){
		List<PmsProjectbasePatent> list = this.getPmsProjectbasePatentDao().findByHql("select t from PmsProjectbasePatent t where t.projectbaseid = ? and type = ? order by t.seq",new Object[]{projectbaseid,type});
		return list;
	}


}
