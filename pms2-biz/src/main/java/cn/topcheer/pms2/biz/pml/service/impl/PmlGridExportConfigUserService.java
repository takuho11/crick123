/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-12-31 11:39:20
 *
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.pms2.api.pml.entity.PmlGridExportConfigUser;
import cn.topcheer.pms2.dao.pml.PmlGridExportConfigUserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PmlGridExportConfigUser 服务类
 */
@Service(value="PmlGridExportConfigUserService")
public class PmlGridExportConfigUserService extends GenericPageService<PmlGridExportConfigUser> {

	public PmlGridExportConfigUserDao getPmlGridExportConfigUserDao() {
		return (PmlGridExportConfigUserDao) this.getGenericDao();
	}

	@Autowired
	public void setPmlGridExportConfigUserDao(PmlGridExportConfigUserDao pmsGridExportConfigUserDao) {

		this.setGenericDao(pmsGridExportConfigUserDao);
	}

	@Autowired
	DBHelper dbHelper;
	public Boolean saveGridExportConfigUser(JSONObject jsonObject){
		String configid = jsonObject.get("configid") + "";
		if(Util.isEoN(configid)){
			throw new ServiceException("未获取到配置表id");
		}
		String type = jsonObject.get("type") + "";
		if(Util.isEoN(type)){
			throw new ServiceException("未获取到类型");
		}
		JSONArray arr = jsonObject.getJSONArray("arr");
		if(Util.isEoN(arr)) {
			throw new ServiceException("未获取到勾选的字段信息!!!");
		}
		String hql = " select t from PmlGridExportConfigUser t where t.configid = ? and type = ? ";
		List<PmlGridExportConfigUser> pmsGridExportConfigUserList = this.findByHql(hql, configid, type);
		this.deleteList(pmsGridExportConfigUserList);
		for(int i = 0; i < arr.size(); i++) {
			PmlGridExportConfigUser pmsGridExportConfigUser = new PmlGridExportConfigUser();
			pmsGridExportConfigUser.setId(Util.NewGuid());
			pmsGridExportConfigUser.setConfigid(configid);
			pmsGridExportConfigUser.setSourceid(arr.getJSONObject(i).get("sourceid") + "");
			pmsGridExportConfigUser.setType(type);
			pmsGridExportConfigUser.setSeq(i);
			this.merge(pmsGridExportConfigUser);
		}
		return true;
	}

	public List<Map> fetchGridExportConfigUser(JSONObject jsonObject) {

		String configid = jsonObject.get("configid") + "";
		if(Util.isEoN(configid)){
			throw new ServiceException("未获取到配置表id");
		}
		String type = jsonObject.get("type") + "";
		if(Util.isEoN(type)){
			throw new ServiceException("未获取到类型");
		}
		String sql = " select t.* from PMS_GRID_EXPORT_CONFIG_USER t where t.configid = ? and type = ? ";
		List<Map> list = dbHelper.getRows(sql, new Object[]{configid, type});
		return list;
	}
}
