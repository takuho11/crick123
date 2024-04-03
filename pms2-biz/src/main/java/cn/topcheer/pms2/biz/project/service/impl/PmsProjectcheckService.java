/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2016-3-4 16:23:25
 *
 */
package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsProjectcheck 服务类
 */
@Service(value="PmsProjectcheckService")
public class PmsProjectcheckService extends GenericService<PmsProjectcheck> {

	public PmsProjectcheckDao getPmsProjectcheckDao() {
		return (PmsProjectcheckDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectcheckDao(PmsProjectcheckDao pmsProjectcheckDao) {

		this.setGenericDao(pmsProjectcheckDao);
	}

	
}
