/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-3-3 15:54:33
 *
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.pms2.api.pml.entity.PmlGridColumn;
import cn.topcheer.pms2.dao.pml.PmlGridColumnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmlGridColumn 服务类
 */
@Service(value="PmlGridColumnService")
public class PmlGridColumnService extends GenericPageService<PmlGridColumn> {

	public PmlGridColumnDao getPmlGridColumnDao() {
		return (PmlGridColumnDao) this.getGenericDao();
	}

	@Autowired
	public void setPmlGridColumnDao(PmlGridColumnDao pmsGridColumnDao) {

		this.setGenericDao(pmsGridColumnDao);
	}

	
}
