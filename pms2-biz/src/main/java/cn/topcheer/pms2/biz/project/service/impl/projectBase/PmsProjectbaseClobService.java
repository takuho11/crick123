/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2022-10-26 16:26:50
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseClob;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseClobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsProjectbaseClob 服务类
 */
@Service(value="PmsProjectbaseClobService")
public class PmsProjectbaseClobService extends GenericService<PmsProjectbaseClob> {

	public PmsProjectbaseClobDao getPmsProjectbaseClobDao() {
		return (PmsProjectbaseClobDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaseClobDao(PmsProjectbaseClobDao pmsProjectbaseClobDao) {

		this.setGenericDao(pmsProjectbaseClobDao);
	}

	
}
