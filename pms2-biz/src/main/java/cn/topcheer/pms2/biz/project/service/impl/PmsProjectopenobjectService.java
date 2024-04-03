/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2015-12-25 10:01:31
 *
 */
package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsProjectopenobject 服务类
 */
@Service(value="PmsProjectopenobjectService")
public class PmsProjectopenobjectService extends GenericService<PmsProjectopenobject> {

	public PmsProjectopenobjectDao getPmsProjectopenobjectDao() {
		return (PmsProjectopenobjectDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectopenobjectDao(PmsProjectopenobjectDao pmsProjectopenobjectDao) {

		this.setGenericDao(pmsProjectopenobjectDao);
	}

	
}
