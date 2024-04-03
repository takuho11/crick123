/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-11-10 20:43:47
 *
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.pms2.api.pml.entity.PmlSearchboxCollection;
import cn.topcheer.pms2.dao.pml.PmlSearchboxCollectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmlSearchboxCollection 服务类
 */
@Service(value="PmlSearchboxCollectionService")
public class PmlSearchboxCollectionService extends GenericPageService<PmlSearchboxCollection> {

	public PmlSearchboxCollectionDao getPmlSearchboxCollectionDao() {
		return (PmlSearchboxCollectionDao) this.getGenericDao();
	}

	@Autowired
	public void setPmlSearchboxCollectionDao(PmlSearchboxCollectionDao pmsSearchboxCollectionDao) {

		this.setGenericDao(pmsSearchboxCollectionDao);
	}

	
}
