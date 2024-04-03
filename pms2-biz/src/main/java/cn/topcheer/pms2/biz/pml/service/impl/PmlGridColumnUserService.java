/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-12-30 13:41:35
 *
 */
package cn.topcheer.pms2.biz.pml.service.impl;


import cn.topcheer.halberd.app.api.framework.service.sys.ISysUserService;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.pml.entity.PmlGridColumnUser;
import cn.topcheer.pms2.dao.pml.PmlGridColumnUserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PmlGridColumnUser 服务类
 */
@Service(value="PmlGridColumnUserService")
public class PmlGridColumnUserService extends GenericPageService<PmlGridColumnUser> {

	@Autowired
	private ISysUserService sysUserService;

	public PmlGridColumnUserDao getPmlGridColumnUserDao() {
		return (PmlGridColumnUserDao) this.getGenericDao();
	}

	@Autowired
	public void setPmlGridColumnUserDao(PmlGridColumnUserDao pmsGridColumnUserDao) {

		this.setGenericDao(pmsGridColumnUserDao);
	}

	@Autowired
	DBHelper dbHelper;

	public Boolean saveGridColumnUserByGridId(JSONObject jsonObject) {

		BladeUser currentUser = AuthUtil.getUser();

		if(Util.isEoN(currentUser)) {
			throw new ServiceException("未获取到用户信息!!!");
		}
		String gridid = jsonObject.get("gridid") + "";
		if(Util.isEoN(gridid)) {
			throw new ServiceException("未获取到列表信息!!!");
		}
		JSONArray columnarr = jsonObject.getJSONArray("columnarr");
		if(Util.isEoN(columnarr)) {
			throw new ServiceException("未获取到勾选的字段信息!!!");
		}
		String hql = " select t from PmlGridColumnUser t where t.gridid = ? and t.userid = ? ";
		List<PmlGridColumnUser> pmsGridColumnUserList = this.findByHql(hql, gridid, currentUser.getUserId());
		this.deleteList(pmsGridColumnUserList);
		for(int i = 0; i < columnarr.size(); i++) {
			if(!"true".equals(columnarr.getJSONObject(i).get("ischeck") + "")) {
				columnarr.getJSONObject(i).put("ischeck", "false");
			}
			PmlGridColumnUser pmsGridColumnUser = new PmlGridColumnUser();
			Util.ApplyObject(pmsGridColumnUser, columnarr.getJSONObject(i));
			pmsGridColumnUser.setGridid(gridid);
			pmsGridColumnUser.setUserid(currentUser.getUserId());
			pmsGridColumnUser.setId(Util.NewGuid());
			pmsGridColumnUser.setSeq(i);
			this.merge(pmsGridColumnUser);
		}
		return true;
	}

	public List<Map> fetchGridColumnUserByGridId(JSONObject jsonObject) {

		List<Map> result = new ArrayList<>();
		BladeUser securityUser = AuthUtil.getUser();
		if(Util.isEoN(securityUser)) {
			throw new ServiceException("未获取到用户信息!!!");
		}
		String gridid = jsonObject.get("gridid") + "";
		if(Util.isEoN(gridid)) {
			throw new ServiceException("未获取到列表信息!!!");
		}
		String sql = " SELECT U.GRIDID, U.COLUMNID ,C.COLUMNNAME_CH NAME, U.ISCHECK\n" +
				" FROM PMS_GRID_COLUMN_USER U \n" +
				" INNER JOIN PMS_COLUMN C ON U.COLUMNID = C.ID \n" +
				" WHERE  U.USERID = ? AND U.GRIDID = ? \n" +
				" ORDER BY U.SEQ ";
		result = dbHelper.getRows(sql, new Object[] {securityUser.getUserId(), gridid});
		return result;
	}
}
