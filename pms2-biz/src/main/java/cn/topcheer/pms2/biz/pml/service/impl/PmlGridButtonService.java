/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-3-3 15:54:33
 *
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.pms2.api.pml.entity.PmlGridButton;
import cn.topcheer.pms2.dao.pml.PmlGridButtonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmlGridButton 服务类
 */
@Service(value="PmlGridButtonService")
public class PmlGridButtonService extends GenericPageService<PmlGridButton> {

	public PmlGridButtonDao getPmlGridButtonDao() {
		return (PmlGridButtonDao) this.getGenericDao();
	}

	@Autowired
	public void setPmlGridButtonDao(PmlGridButtonDao pmsGridButtonDao) {

		this.setGenericDao(pmsGridButtonDao);
	}






	
}
