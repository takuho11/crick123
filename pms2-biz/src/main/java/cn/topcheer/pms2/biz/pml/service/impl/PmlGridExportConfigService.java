/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-12-31 11:39:20
 *
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.pms2.api.pml.entity.PmlGridExportConfig;
import cn.topcheer.pms2.dao.pml.PmlGridExportConfigDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * PmlGridExportConfig 服务类
 */
@Service(value="PmlGridExportConfigService")
public class PmlGridExportConfigService extends GenericPageService<PmlGridExportConfig> {

	public PmlGridExportConfigDao getPmlGridExportConfigDao() {
		return (PmlGridExportConfigDao) this.getGenericDao();
	}

	@Autowired
	public void setPmlGridExportConfigDao(PmlGridExportConfigDao pmsGridExportConfigDao) {

		this.setGenericDao(pmsGridExportConfigDao);
	}

	@Autowired
	DBHelper dbHelper;

	public Boolean saveGridExportConfig(JSONObject jsonObject){
		String id = "";
		if(Util.isEoN(jsonObject.get("id"))){
			id = Util.NewGuid();
		}else{
			id = jsonObject.get("id")+"";
		}
		PmlGridExportConfig pmsGridExportConfig = this.findById(id);
		if(Util.isEoN(pmsGridExportConfig)) {
			pmsGridExportConfig = new PmlGridExportConfig();
			pmsGridExportConfig.setSavedate(new Date());
		}
		Util.ApplyObject(pmsGridExportConfig,jsonObject);
		if(!Util.isEoN(jsonObject.get("export_feild"))) {
			pmsGridExportConfig.setExport_feild(jsonObject.get("export_feild") + "");
		}
		if(!Util.isEoN(jsonObject.get("config_feild"))) {
			pmsGridExportConfig.setConfig_feild(jsonObject.get("config_feild") + "");
		}
		this.merge(pmsGridExportConfig);
		return true;
	}

	public Boolean deleteGridExportConfig(JSONObject jsonObject) {

		Boolean result = true;
		String id = jsonObject.get("id") + "";
		if(Util.isEoN(id)) {
			throw new ServiceException("删除失败，未获取到id!!!");
		}
		this.deleteById(id);
		return result;
	}

	public List<Map> initGridExportConfig() {
		String sql = " select t.* from PMS_GRID_EXPORT t where 1=1 ";
		List<Map> list = dbHelper.getRows(sql, null);
		return list;
	}

	public List<Map> fetchConfigByTypeGridid(JSONObject jsonObject) {
		String sql = " select t.* from PMS_GRID_EXPORT t where t.type = 'custom' and t.value like ? ";
		JSONArray paramArr = new JSONArray();
		paramArr.add("%;" + jsonObject.get("gridid") + ";%");
		List<Map> list = dbHelper.getRows(sql, paramArr.toArray());
		return list;
	}
}
