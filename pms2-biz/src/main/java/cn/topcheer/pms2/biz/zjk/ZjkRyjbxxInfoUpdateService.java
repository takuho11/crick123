/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-11-27 17:57:05
 *
 */
package cn.topcheer.pms2.biz.zjk;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxInfoUpdate;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.zjk.ZjkRyjbxxInfoUpdateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ZjkRyjbxxInfoUpdate 服务类
 */
@Service(value="ZjkRyjbxxInfoUpdateService")
public class ZjkRyjbxxInfoUpdateService extends GenericService<ZjkRyjbxxInfoUpdate> {

	public ZjkRyjbxxInfoUpdateDao getZjkRyjbxxInfoUpdateDao() {
		return (ZjkRyjbxxInfoUpdateDao) this.getGenericDao();
	}

	@Autowired
	public void setZjkRyjbxxInfoUpdateDao(ZjkRyjbxxInfoUpdateDao zjkRyjbxxInfoUpdateDao) {

		this.setGenericDao(zjkRyjbxxInfoUpdateDao);
	}

	/**
	 * 【获取】通过专家id
	 * @param zjid
	 * @return
	 */
	public ZjkRyjbxxInfoUpdate getDataByZjid(String zjid){
		List<ZjkRyjbxxInfoUpdate> list = this.findByHql("select t from ZjkRyjbxxInfoUpdate t where t.zjkryjbxxupdateid = ?0 ",new Object[]{zjid});
		return !Util.isEoN(list) && list.size() > 0 ? list.get(0) : null;
	}
}
