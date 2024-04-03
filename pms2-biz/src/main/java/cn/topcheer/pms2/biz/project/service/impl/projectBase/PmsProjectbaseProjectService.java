/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2022-10-26 16:26:50
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseProject;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsProjectbaseProject 服务类
 */
@Service(value="PmsProjectbaseProjectService")
public class PmsProjectbaseProjectService extends GenericService<PmsProjectbaseProject> {

	public PmsProjectbaseProjectDao getPmsProjectbaseProjectDao() {
		return (PmsProjectbaseProjectDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaseProjectDao(PmsProjectbaseProjectDao pmsProjectbaseProjectDao) {

		this.setGenericDao(pmsProjectbaseProjectDao);
	}

	
}
