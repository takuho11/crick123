/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-4-19 11:02:54
 *
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.pms2.api.pml.entity.PmlGridtabsGrid;
import cn.topcheer.pms2.dao.pml.PmlGridtabsGridDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmlGridtabsGrid 服务类
 */
@Service(value="PmlGridtabsGridService")
public class PmlGridtabsGridService extends GenericPageService<PmlGridtabsGrid> {

	public PmlGridtabsGridDao getPmlGridtabsGridDao() {
		return (PmlGridtabsGridDao) this.getGenericDao();
	}

	@Autowired
	public void setPmlGridtabsGridDao(PmlGridtabsGridDao pmsGridtabsGridDao) {

		this.setGenericDao(pmsGridtabsGridDao);
	}

	
}
