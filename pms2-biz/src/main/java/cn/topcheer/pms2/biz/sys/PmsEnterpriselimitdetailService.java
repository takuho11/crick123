/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-4-29 14:06:11
 *
 */
package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.sys.PmsEnterpriselimitdetail;
import cn.topcheer.pms2.dao.sys.PmsEnterpriselimitdetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsEnterpriselimitdetail 服务类
 */
@Service(value="PmsEnterpriselimitdetailService")
public class PmsEnterpriselimitdetailService extends GenericService<PmsEnterpriselimitdetail> {

	public PmsEnterpriselimitdetailDao getPmsEnterpriselimitdetailDao() {
		return (PmsEnterpriselimitdetailDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsEnterpriselimitdetailDao(PmsEnterpriselimitdetailDao pmsEnterpriselimitdetailDao) {

		this.setGenericDao(pmsEnterpriselimitdetailDao);
	}

	
}
