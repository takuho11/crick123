/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2023-5-26 9:55:41
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;

import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseExp;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseExpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsProjectbaseExp 服务类
 */
@Service(value="PmsProjectbaseExpService")
public class PmsProjectbaseExpService extends GenericService<PmsProjectbaseExp> {

	public PmsProjectbaseExpDao getPmsProjectbaseExpDao() {
		return (PmsProjectbaseExpDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaseExpDao(PmsProjectbaseExpDao pmsProjectbaseExpDao) {

		this.setGenericDao(pmsProjectbaseExpDao);
	}

	
}
