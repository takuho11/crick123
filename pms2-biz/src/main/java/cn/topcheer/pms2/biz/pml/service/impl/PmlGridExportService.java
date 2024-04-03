/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-12-14 9:51:07
 *
 */
package cn.topcheer.pms2.biz.pml.service.impl;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.pms2.api.pml.entity.PmlGrid;
import cn.topcheer.pms2.api.pml.entity.PmlGridExport;
import cn.topcheer.pms2.api.pml.entity.PmlGridExportConfig;
import cn.topcheer.pms2.biz.utils.GenericFetchAndSave;
import cn.topcheer.pms2.biz.pml.service.impl.enumUtil.TableEnum;
import cn.topcheer.pms2.dao.pml.PmlGridExportDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PmlGridExport 服务类
 * @author GaoGongxin
 */
@Service(value="PmlGridExportService")
public class PmlGridExportService extends GenericPageService<PmlGridExport> {

	public PmlGridExportDao getPmlGridExportDao() {
		return (PmlGridExportDao) this.getGenericDao();
	}

	@Autowired
	public void setPmlGridExportDao(PmlGridExportDao pmsGridExportDao) {

		this.setGenericDao(pmsGridExportDao);
	}

	@Autowired
	PmlGridService pmsGridService;

	@Autowired
	PmlCommonGridService pmsCommonGridService;

	@Autowired
	DBHelper dbHelper;

	@Autowired
	private SysUserServiceImpl sysUserService;

//	@Autowired
//	SysVersionService sysVersionService;

//	@Autowired
//	SysVersionObjectService sysVersionObjectService;

//	@Autowired
//	SysModuleFieldService sysModuleFieldService;

	@Autowired
	GenericFetchAndSave genericFetchAndSave;

	@Autowired
	PmlGridExportConfigService pmsGridExportConfigService;

	public Map fetchExportFeildForConfig(JSONObject jsonObject) {

		Map result = new HashMap();
		JSONArray completeFeildArr = new JSONArray();
		BladeUser securityUser = AuthUtil.getUser();
		if(Util.isEoN(securityUser)) {
			throw new ServiceException("未获取到当前登录人的信息");
		}
		String gridid = jsonObject.get("gridid")+"";
		//获取列表配置数据
		if (Util.isEoN(gridid)) {
			throw new ServiceException("未获取到列表参数");
		}
		PmlGrid pmsGrid = pmsGridService.findById(gridid);
		if(Util.isEoN(pmsGrid)) {
			throw new ServiceException("未获取到列表配置");
		}
		String configid = jsonObject.get("configid")+"";
		String businesstype = jsonObject.get("businesstype") + "";
		List<PmlGridExport> pmsGridExportList = new ArrayList<>();
		List<PmlGridExport> tempPmlGridExportList = new ArrayList<>();
		if(!Util.isEoN(businesstype)) {
			String hql = "select t from PmlGridExport t where (t.type = ? and t.value = ?) or (t.type = ? and t.value = ?) or (t.type = ? and t.value like ?) ";
			pmsGridExportList = this.findByHql(hql, "public", businesstype, "default", businesstype, "grid", "%;" + pmsGrid.getId() + ";%");
		} else {
			String hql = "select t from PmlGridExport t where (t.type = ? and t.value like ?) ";
			pmsGridExportList = this.findByHql(hql, "grid", "%;" + pmsGrid.getId() + ";%");
		}
		if(!Util.isEoN(configid)) {
			String hql = "select t from PmlGridExport t, PmlGridExportConfig c where t.id = c.customid and c.id = ? and t.type = ? and t.value like ? ";
			tempPmlGridExportList = this.findByHql(hql, configid, "custom", "%;" + pmsGrid.getId() + ";%");
		}
		pmsGridExportList.addAll(tempPmlGridExportList);
		// 1.获取列表已有字段
		completeFeildArr = dealOriginalExportFeildInfo(completeFeildArr, pmsGrid.getGridtype());
		// 2.获取业务公共字段 public
		completeFeildArr = dealConfigExportFeildInfo(completeFeildArr, pmsGridExportList, "public", "业务公共字段");
		// 如果版本 versionid 为空，则使用默认业务详情字段
		// 若 非空 则说明只有一个版本，则按业务详情版本 进行导出
		String versionid = jsonObject.get("versionid") + "";
		if(!Util.isEoN(versionid)) {
			// 3.1 获取业务详情字段
			completeFeildArr = dealBusinessExportFeildInfo(completeFeildArr, versionid);
		} else {
			// 3.2 获取业务默认详情字段 default
			completeFeildArr = dealConfigExportFeildInfo(completeFeildArr, pmsGridExportList, "default", "默认业务详情字段");
		}
		// 4.列表定制化字段 grid
		completeFeildArr = dealConfigExportFeildInfo(completeFeildArr, pmsGridExportList, "grid", "列表定制化字段");
		// 5.定制化字段 custom
		completeFeildArr = dealConfigExportFeildInfo(completeFeildArr, pmsGridExportList, "custom", "定制化字段");
		result.put("complete_feild", completeFeildArr);
		JSONArray configFeildArr = new JSONArray();
		String enable_unique = "否";
		if(!Util.isEoN(configid)) {
			PmlGridExportConfig pmsGridExportConfig = pmsGridExportConfigService.findById(configid);
			if(!Util.isEoN(pmsGridExportConfig) && !Util.isEoN(pmsGridExportConfig.getConfig_feild()) && Util.isJSONArray(pmsGridExportConfig.getConfig_feild())) {
				configFeildArr = JSONArray.fromObject(pmsGridExportConfig.getConfig_feild());
			}
			enable_unique = pmsGridExportConfig.getEnable_unique();
		}
		result.put("config_feild", configFeildArr);
		result.put("enable_unique", enable_unique);
		return result;
	}


	public JSONArray fetchExportFeild(JSONObject jsonObject) {

		JSONArray result = new JSONArray();
		String printType = jsonObject.get("printType") + "";
		if (Util.isEoN(printType)) {
			throw new ServiceException("未选择导出类型");
		}
		BladeUser securityUser = AuthUtil.getUser();
		if(Util.isEoN(securityUser)) {
			throw new ServiceException("未获取到当前登录人的信息");
		}
		String gridType = jsonObject.get("gridType")+"";
		//获取列表配置数据
		if (Util.isEoN(gridType)) {
			throw new ServiceException("未获取到列表类型");
		}
		List<PmlGrid> pmsGridList = pmsGridService.findByProperty("gridtype",gridType);
		PmlGrid pmsGrid = new PmlGrid();
		if(!Util.isEoN(pmsGridList) && pmsGridList.size() > 0) {
			pmsGrid = pmsGridList.get(0);
		} else {
			throw new ServiceException("未获取到列表配置");
		}
		String businesstype = jsonObject.get("businesstype") + "";
		List<PmlGridExport> pmsGridExportList = new ArrayList<>();
		List<PmlGridExport> tempPmlGridExportList = new ArrayList<>();
		if(!Util.isEoN(businesstype)) {
			String hql = "select t from PmlGridExport t where (t.type = ? and t.value = ?) or (t.type = ? and t.value = ?) or (t.type = ? and t.value like ?) ";
			pmsGridExportList = this.findByHql(hql, "public", businesstype, "default", businesstype, "grid", "%;" + pmsGrid.getId() + ";%");
		} else {
			String hql = "select t from PmlGridExport t where (t.type = ? and t.value like ?) ";
			pmsGridExportList = this.findByHql(hql, "grid", "%;" + pmsGrid.getId() + ";%");
		}
		String hql = "select t from PmlGridExport t, PmlGridExportConfig c where t.id = c.customid and t.type = ? and t.value like ? ";
		tempPmlGridExportList = this.findByHql(hql, "custom", "%;" + pmsGrid.getId() + ";%");
		// 1.获取列表已有字段
		result = dealOriginalExportFeildInfo(result, gridType);
		// 获取业务ids
		SysUser user = sysUserService.findById(securityUser.getUserId());
		JSONArray idArr = fetchExportIdArr(printType, pmsGrid, jsonObject, user);
		if(!Util.isEoN(businesstype)) {
			// 2.获取业务公共字段 public
			result = dealConfigExportFeildInfo(result, pmsGridExportList, "public", "业务公共字段");
			//根据业务id获取批次id，进而获取版本id
			TableEnum tableEnum = TableEnum.valueOf(businesstype);
			if(Util.isEoN(tableEnum)) {
				throw new ServiceException("业务类型错误!!!");
			}
			List<Map> versionidList = fetchVersionidByMainid(idArr, tableEnum);
			// 如果版本versionidList 长度为 0 或者 版本数大于1 种，则使用默认业务详情字段
			// 若等于 1 则说明只有一个版本，则按业务详情版本 进行导出
			if(versionidList.size() == 1) {
				// 3.1 获取业务详情字段
				result = dealBusinessExportFeildInfo(result, versionidList.get(0).get("versionid") + "");
			} else {
				// 3.2 获取业务默认详情字段 default
				result = dealConfigExportFeildInfo(result, pmsGridExportList, "default", "默认业务详情字段");
			}
		}
		// 4.列表定制化字段 grid
		result = dealConfigExportFeildInfo(result, pmsGridExportList, "grid", "列表定制化字段");
		// 5.根据业务id判断，导出的数据是否来源于同一个定制化字段模板（custom）
		result = dealConfigCustomExportFeildInfo(result, idArr, tempPmlGridExportList);
		return result;
	}

	/**
	 * 根据业务id及业务类型获取版本信息
	 * @param idArr
	 * @param tableEnum
	 * @return
	 */
	public List<Map> fetchVersionidByMainid(JSONArray idArr, TableEnum tableEnum) {
		List<Map> result = new ArrayList<>();
		if("xssc".equals(tableEnum.name()) || "psrw".equals(tableEnum.name()) || "kycx".equals(tableEnum.name()) || "tybg".equals(tableEnum.name())) {
			return result;
		}
		String tablename = ((Table) tableEnum.getClassname().getAnnotation(Table.class)).name();
		String idArrStr = Util.sqlSplicingForInStatement(idArr, "t.id");
		if(Util.isEoN(idArrStr)) {
			return result;
		}
		String sql = "select distinct v." + tableEnum.name() + " versionid from " + tablename + " t inner join BATCH_VERSION v on t.planprojectbatchid = v.id where 1 = 1 and ( " + idArrStr + " ) ";
//		result = dbHelper.getRows(sql, idArr.toArray());
		result = dbHelper.getRowsAll(sql, idArr.toArray(), idArr.size(), 500);
		return result;
	}

	/**
	 * 获取需导出项目的id
	 * @param printType
	 * @param pmsGrid
	 * @param jsonObject
	 * @param sysUser
	 * @return
	 */
	public JSONArray fetchExportIdArr(String printType, PmlGrid pmsGrid, JSONObject jsonObject, SysUser sysUser) {
		JSONArray idArr = new JSONArray();
		if("select".equals(printType)) {
			idArr = jsonObject.getJSONArray("seletedData");
		} else {
			//获取数据源
			JSONArray jsonArray = pmsCommonGridService.getAllDataByClass(sysUser, pmsGrid, jsonObject);
			for (int i = 0; i < jsonArray.size(); i++) {
				idArr.add(jsonArray.getJSONObject(i).get("id"));
			}
		}
		return idArr;
	}

	/**
	 * 处理配置的导出字段内容
	 * @param result
	 * @param pmsGridExportList
	 * @param type
	 * @param name
	 * @return
	 */
	public JSONArray dealConfigExportFeildInfo(JSONArray result, List<PmlGridExport> pmsGridExportList, String type, String name) {
		JSONObject resultObj = new JSONObject();
		JSONArray dataArr = new JSONArray();
		List<PmlGridExport> tempPmlGridExportList = Util.ListDoWhere(pmsGridExportList, "type", type);
		if(tempPmlGridExportList.size() > 1) {
			throw new ServiceException(name + "配置错误!!!");
		} else if(tempPmlGridExportList.size() == 1) {
			PmlGridExport pmsGridExport = tempPmlGridExportList.get(0);
			String field_reflect = pmsGridExport.getField_reflect();
			if(Util.isEoN(field_reflect)) {
				throw new ServiceException("字段映射关系未配置!!!");
			}
			String[] split = field_reflect.split("#");
			for(int i = 0; i < split.length; i++) {
				JSONObject obj = new JSONObject();
				if(split[i].split("@").length != 2) {
					throw new ServiceException("字段映射关系配置不正确!!!");
				}
				obj.put("field", split[i].split("@")[0]);
				obj.put("name", split[i].split("@")[1]);
				dataArr.add(obj);
			}
			JSONObject tempObj = new JSONObject();
			tempObj.put("modulename", name);
			tempObj.put("fieldtype", type);
			tempObj.put("objecttype", "normal");
			tempObj.put("idstring", type);
			tempObj.put("showmodulename", false);
			tempObj.put("data", dataArr);
			resultObj.put("type", type);
			resultObj.put("name", name);
			JSONArray temArr = new JSONArray();
			temArr.add(tempObj);
			resultObj.put("data", temArr);
			result.add(resultObj);
		} else {
			//未配置，无需处理
		}
		return result;
	}

	/**
	 * 处理列表已有字段
	 * @param result
	 * @param gridType
	 * @return
	 */
	public JSONArray dealOriginalExportFeildInfo(JSONArray result, String gridType) {

		String sql = " select distinct c.columnname_ch,c.columnname_en, gc.seq " +
				" from pms_grid_column gc " +
				" left join pms_grid g on gc.gridid = g.id " +
				" left join pms_column c on gc.columnid = c.id " +
				" where g.gridtype = ?	 and c.columnname_ch != '操作' " +
				" order by gc.seq";
		List<Map> columnList = dbHelper.getRows(sql, new Object[]{gridType});
		if (columnList == null || columnList.size() == 0) {
			columnList = this.dbHelper.getRows("select distinct c.columnname_ch,c.columnname_en, gc.seq " +
					" from pml_grid_column gc " +
					" left join pml_grid g on gc.gridid = g.id " +
					" left join pml_column c on gc.columnid = c.id " +
					" where g.gridtype = ?	 and c.columnname_ch != '操作' " +
					" order by gc.seq", new Object[]{gridType});

		}
		JSONObject original = new JSONObject();
		JSONArray originalDataArr = new JSONArray();
		if(columnList.size() > 0) {
			for(int i = 0; i < columnList.size(); i++) {
				JSONObject originalObj = new JSONObject();
				originalObj.put("field", columnList.get(i).get("columnname_en"));
				originalObj.put("name", columnList.get(i).get("columnname_ch"));
				originalDataArr.add(originalObj);
			}
			JSONObject tempObj = new JSONObject();
			tempObj.put("modulename", "列表字段");
			tempObj.put("fieldtype", "original");
			tempObj.put("objecttype", "normal");
			tempObj.put("idstring", "original");
			tempObj.put("showmodulename", false);
			tempObj.put("data", originalDataArr);
			original.put("type", "original");
			original.put("name", "列表字段");
			JSONArray temArr = new JSONArray();
			temArr.add(tempObj);
			original.put("data", temArr);
			result.add(original);
		}
		return result;
	}

	/**
	 * 处理业务详情字段
	 * @param result
	 * @param versionid
	 * @return
	 */
	public JSONArray dealBusinessExportFeildInfo(JSONArray result, String versionid) {
		JSONObject dataByVersionidNew = fetchFeildInfoByVersionid(versionid);
		JSONArray array = JSONArray.fromObject(dataByVersionidNew.get("obj"));
		JSONObject businessObj = new JSONObject();
		JSONArray businessDataArr = new JSONArray();
		JSONArray temArr = new JSONArray();
		for(int i = 0; i < array.size(); i++) {
			JSONArray array1 = JSONArray.fromObject(array.getJSONObject(i).get("fieldarray"));
			businessDataArr = new JSONArray();
			for(int j = 0; j < array1.size(); j++) {
				JSONObject obj = new JSONObject();
				obj.put("field", array1.getJSONObject(j).get("fieldname"));
				obj.put("name", Util.isEoN(array1.getJSONObject(j).get("showname")) == true ? array1.getJSONObject(j).get("defaultshowname") : array1.getJSONObject(j).get("showname"));
				businessDataArr.add(obj);
			}
			JSONObject tempObj = new JSONObject();
			tempObj.put("modulename", array.getJSONObject(i).get("showname"));
			tempObj.put("fieldtype", "business");
			tempObj.put("objecttype", array.getJSONObject(i).get("objecttype"));
			if(Util.isEoN(array.getJSONObject(i).get("moduletype"))) {
				continue;
			}
			tempObj.put("database", array.getJSONObject(i).get("moduletype"));
			tempObj.put("type", array.getJSONObject(i).get("paramtype"));
			tempObj.put("idstring", array.getJSONObject(i).get("idstring"));
			tempObj.put("showmodulename", true);
			tempObj.put("data", businessDataArr);
			temArr.add(tempObj);
		}
		businessObj.put("type", "business");
		businessObj.put("name", "业务详情字段");
		businessObj.put("data", temArr);
		result.add(businessObj);
		return result;
	}

	/**
	 * 处理配置定制化(custom)字段内容
	 * @param result
	 * @param ids
	 * @param pmsGridExportList
	 * @return
	 */
	public JSONArray dealConfigCustomExportFeildInfo(JSONArray result, JSONArray ids, List<PmlGridExport> pmsGridExportList) {

		if(ids.size() == 0) {
			return result;
		}
		JSONObject resultObj = new JSONObject();
		JSONArray dataArr = new JSONArray();
		PmlGridExport pmsGridExport = fetchCostomGridExport(ids, pmsGridExportList);
		if(!Util.isEoN(pmsGridExport)) {
			String field_reflect = pmsGridExport.getField_reflect();
			if(Util.isEoN(field_reflect)) {
				throw new ServiceException("字段映射关系未配置!!!");
			}
			String[] split = field_reflect.split("#");
			for(int i = 0; i < split.length; i++) {
				JSONObject obj = new JSONObject();
				if(split[i].split("@").length != 2) {
					throw new ServiceException("字段映射关系配置不正确!!!");
				}
				obj.put("field", split[i].split("@")[0]);
				obj.put("name", split[i].split("@")[1]);
				dataArr.add(obj);
			}
			JSONObject tempObj = new JSONObject();
			tempObj.put("modulename", "定制化字段");
			tempObj.put("fieldtype", "custom");
			tempObj.put("objecttype", "normal");
			tempObj.put("idstring", "custom");
			tempObj.put("showmodulename", false);
			tempObj.put("data", dataArr);
			resultObj.put("type", "custom");
			resultObj.put("name", "定制化字段");
			JSONArray temArr = new JSONArray();
			temArr.add(tempObj);
			resultObj.put("data", temArr);
			result.add(resultObj);
		} else {
			//未配置，或者定制化字段集大于1个，无需处理
		}
		return result;
	}

	/**
	 * 根据版本id获取业务字段
	 * @param versionid
	 * @return
	 */
	public JSONObject fetchFeildInfoByVersionid(String versionid) {
		//最终的返回结果对象
		JSONObject configObject = new JSONObject();
		String objectSql = " select o.id, o.showname, o.objecttype, o.moduletype, o.paramtype, o.moduleid, o.idstring from SYS_VERSION_TAB t inner join SYS_VERSION_OBJECT o on t.id = o.tabid where t.versionid = ? order by t.seq, o.seq ";
		List<Map> objectList = dbHelper.getRows(objectSql, new Object[]{versionid});
		objectList = objectList.stream().distinct().collect(Collectors.toList());
		String moduleSql = " select f.id, f.fieldname, f.showname, f.defaultshowname, f.moduleid from SYS_VERSION_TAB t inner join SYS_VERSION_OBJECT o on o.tabid = t.id inner join SYS_MODULE_FIELD f on f.moduleid = o.moduleid where t.versionid = ? order by t.seq, o.seq, f.seq ";
		List<Map> moduleList = dbHelper.getRows(moduleSql, new Object[]{versionid});
		moduleList = moduleList.stream().distinct().collect(Collectors.toList());
		for (int i = 0; i < objectList.size(); i++) {
			JSONArray fieldarray = new JSONArray();
			String moduleid = objectList.get(i).get("moduleid") + "";
			for (int j = 0; j < moduleList.size(); j++) {
				if (!Util.isEoN(moduleid) && moduleid.equals(moduleList.get(j).get("moduleid"))) {
					fieldarray.add(moduleList.get(j));
				}
			}
			objectList.get(i).put("fieldarray", fieldarray);
		}
		if (!Util.isEoN(objectList) && objectList.size() > 0) {
			configObject.put("obj", objectList);
		} else {
			configObject.put("obj", new JSONArray());
		}
		return configObject;
	}

	/**
	 * 获取导出的数据源
	 * @param jsonObject
	 * @return
	 */
	public List<Map> fetchExportDataSource(JSONObject jsonObject) {

		String test_export = jsonObject.get("test_export") + "";
		if(Util.isEoN(test_export)) {
			test_export = "";
		}
		String printType = jsonObject.get("printType") + "";
		if (Util.isEoN(printType)) {
			throw new ServiceException("未选择导出类型!!!");
		}
		BladeUser securityUser = AuthUtil.getUser();
		if(Util.isEoN(securityUser)) {
			throw new ServiceException("未获取到当前登录人的信息!!!");
		}
		String gridType = jsonObject.get("gridType")+"";
		//获取列表配置数据
		if (Util.isEoN(gridType)) {
			throw new ServiceException("未获取到列表类型!!!");
		}
		List<PmlGrid> pmsGridList = pmsGridService.findByProperty("gridtype",gridType);
		if(!Util.isEoN(test_export)) {
			List list = new ArrayList();
			list.add(jsonObject.get("gridid"));
			if(Util.isEoN(jsonObject.get("gridid"))) {
				throw new ServiceException("未获取到列表信息!!!");
			}
			pmsGridList = pmsGridService.findByIds(list);
		}
		PmlGrid pmsGrid = new PmlGrid();
		if(!Util.isEoN(pmsGridList) && pmsGridList.size() > 0) {
			pmsGrid = pmsGridList.get(0);
		} else {
			throw new ServiceException("未获取到列表配置!!!");
		}
		JSONArray allGridDataArr = new JSONArray();
		SysUser user = sysUserService.findById(securityUser.getUserId());
		if(Util.isEoN(test_export)) {
			allGridDataArr = this.pmsCommonGridService.getAllDataByClass(user, pmsGrid, jsonObject);
		}
		// 获取业务ids
		JSONArray idArr = new JSONArray();
		for(int i = 0; i < allGridDataArr.size(); i++) {
			idArr.add(allGridDataArr.getJSONObject(i).get("id"));
		}
		String businesstype = jsonObject.get("businesstype") + "";
		TableEnum tableEnum = TableEnum.getEnumByName(businesstype);
		if(Util.isEoN(tableEnum)) {
			businesstype = "";
		}
		List<PmlGridExport> pmsGridExportList = new ArrayList<>();
		List<PmlGridExport> tempPmlGridExportList = new ArrayList<>();


		if(!Util.isEoN(businesstype)) {
			String hql = "select t from PmlGridExport t where (t.type = ?0 and t.value = ?1) or (t.type = ?2 and t.value = ?3) or (t.type = ?4 and t.value like ?5) ";
			pmsGridExportList = this.findByHql(hql, "public", businesstype, "default", businesstype, "grid", "%;" + pmsGrid.getId() + ";%");
		} else {
			String hql = "select t from PmlGridExport t where (t.type = ?0 and t.value like ?1) ";
			pmsGridExportList = this.findByHql(hql, "grid", "%;" + pmsGrid.getId() + ";%");
		}
		String hql = "select t from PmlGridExport t, PmlGridExportConfig c where t.id = c.customid and t.type = ?0 and t.value like ?1 ";
		tempPmlGridExportList = this.findByHql(hql, "custom", "%;" + pmsGrid.getId() + ";%");
		PmlGridExport pmsGridExport = fetchCostomGridExport(idArr, tempPmlGridExportList);
		if(!Util.isEoN(pmsGridExport)) {
			pmsGridExportList.add(pmsGridExport);
		}
		String versionid = "";
		List<Map> versionidList = new ArrayList<>();
		if(!Util.isEoN(businesstype)) {
			if(Util.isEoN(test_export)) {
				versionidList = fetchVersionidByMainid(idArr, tableEnum);
			} else {
				String tempVersionid = jsonObject.get("versionid") + "";
				if(Util.isEoN(tempVersionid)) {
					tempVersionid = "";
				}
				Map map = new HashMap();
				map.put("versionid", tempVersionid);
				versionidList.add(map);
			}
			if(versionidList.size() == 1) {
				versionid = versionidList.get(0).get("versionid") + "";
			} else {
				versionid = "";
			}
		}
		String export_mode = jsonObject.get("export_mode") + "";
		// 是否导出唯一标识符
		String enable_unique = "";
		JSONArray field_arr = new JSONArray();
		// 配置导出模式
		if(!Util.isEoN(export_mode) && "config".equals(export_mode)) {
			// 是否配置导出模板
			Boolean configFlag = false;
			JSONArray paramArr = new JSONArray();
			paramArr.add(securityUser.getUserId());
			JSONArray sysRoleIdArr = new JSONArray();
			List<SysIdentity> sysIdentityList = user.getSysIdentitys();
			Iterator<SysIdentity> iterator = sysIdentityList.iterator();
			while(iterator.hasNext()) {
				sysRoleIdArr.add(iterator.next().getSysRole().getId());
			}
			//sysRoleIdArr.addAll(sysIdentityList);
			paramArr.addAll(sysRoleIdArr);
			String roleidsStrSql = Util.sqlSplicingForInStatement(sysRoleIdArr, "U.SOURCEID");
			String configSql = "";
			if(!Util.isEoN(test_export)) {
				configSql = " or c.ID = ? ";
				paramArr.add(jsonObject.get("configid"));
			}
			paramArr.add(pmsGrid.getId());
			String versionSql = "";
			if(!Util.isEoN(versionid)) {
				// 1.仅有一个版本（具体到版本）
				// 2.使用没有版本详情的版本（无需具体到版本）
				// 通过SQL排序实现
				versionSql = " AND (C.VERSIONID = ? OR C.VERSIONID IS NULL)\n";
				paramArr.add(versionid);
			} else {
				// 含有多个版本
				versionSql = " AND C.VERSIONID IS NULL \n";
			}
			String customSql = "";
			if(!Util.isEoN(pmsGridExport)) {
				customSql = " and c.customid = ? ";
				paramArr.add(pmsGridExport.getId());
			} else {
				customSql = " and c.customid is null ";
			}
			String sql = " SELECT C.EXPORT_FEILD,C.VERSIONID,C.ENABLE_UNIQUE \n" +
					" FROM PMS_GRID_EXPORT_CONFIG C \n" +
					" INNER JOIN PMS_GRID_EXPORT_CONFIG_USER U ON C.ID = U.CONFIGID\n" +
					" WHERE (U.SOURCEID = ? AND U.TYPE = 'user' \n" +
					" OR ( " + roleidsStrSql + " ) AND U.TYPE = 'role' \n" +
					configSql +
					" )\n" +
					" AND C.GRIDID = ? \n" +
					versionSql +
					customSql +
					" ORDER BY ISNULL(C.VERSIONID)";
			List<Map> rows = dbHelper.getRows(sql, paramArr.toArray());
			if(rows!=null&&rows.size() > 0) {
				String export_feild = rows.get(0).get("export_feild") + "";
				enable_unique = rows.get(0).get("enable_unique") + "";
				if(!Util.isEoN(export_feild) && Util.isJSONArray(export_feild)) {
					field_arr = JSONArray.fromObject(export_feild);
				} else {
					throw new ServiceException("请先配置需导出的字段!!!");
				}
				configFlag = true;
			}
			// 未配置导出字段
			if(false == configFlag) {
				// 如果未获取到，则使用列表默认字段集
				// 1.获取列表已有字段
				JSONArray tempFieldArr = new JSONArray();
				tempFieldArr = dealOriginalExportFeildInfo(tempFieldArr, pmsGrid.getGridtype());
				enable_unique = "否";
				if(!Util.isEoN(tempFieldArr) && tempFieldArr.size() > 0) {
					field_arr = tempFieldArr.getJSONObject(0).getJSONArray("data");
				} else {
					throw new ServiceException("列表字段配置获取失败!!!");
				}
			}
		} else {
			field_arr = jsonObject.getJSONArray("field_arr");
			enable_unique = jsonObject.get("enable_unique") + "";
			if (Util.isEoN(field_arr) || field_arr.size() == 0) {
				throw new ServiceException("请先勾选需导出的字段!!!");
			}
		}
		if(Util.isEoN(enable_unique)) {
			enable_unique = "否";
		}
		jsonObject.put("enable_unique", enable_unique);
		// 前台传来的已选字段
		Map<String, JSONArray> fieldTypeMapArr = new HashMap<>();
		for(int i = 0; i < field_arr.size(); i++) {
			String fieldtype = field_arr.getJSONObject(i).get("fieldtype") + "";
			if(Util.isEoN(fieldtype)) {
				throw new ServiceException("参数不完整!!!");
			}
			if(!fieldTypeMapArr.containsKey(fieldtype)) {
				fieldTypeMapArr.put(fieldtype, new JSONArray());
			}
			fieldTypeMapArr.get(fieldtype).add(field_arr.get(i));
		}
		JSONArray businessArr = fieldTypeMapArr.get("business");
		JSONObject dataObject = new JSONObject();
		if(!Util.isEoN(businessArr) && businessArr.size() > 0) {
			for(int i = 0; i < businessArr.size(); i++) {
				String database = businessArr.getJSONObject(i).get("database") + "";
				String type = businessArr.getJSONObject(i).get("type") + "";
				String objecttype = businessArr.getJSONObject(i).get("objecttype") + "";
				JSONObject tempJson = new JSONObject();
				tempJson.put("database", database);
				tempJson.put("type", type);
				tempJson.put("datatype", objecttype);
				dataObject.put(database + "_" + type, tempJson);
			}
		}
		// 处理数据源 将 数据源转为 map 型
		Map<String, List> tempDataSource = dealCompleteDataSource(dataObject, pmsGridExportList, versionid, allGridDataArr);
		// 获取业务公共字段，并将其转化为map型
		List<Map> genericInfoList = tempDataSource.get("public");
		if(genericInfoList.size() == 0) {
			genericInfoList = tempDataSource.get("original");
		}
		for(int i = 0; i < genericInfoList.size(); i++) {
			genericInfoList.get(i).put("mainid", genericInfoList.get(i).get("id"));
		}
		Map<String, String> genericInfoMap = new HashMap<>();
		for(int i = 0; i < genericInfoList.size(); i++) {
			genericInfoMap.put(genericInfoList.get(i).get("id") + "", genericInfoList.get(i).get("projectbasename") + "");
		}
		LinkedHashMap<String, Map<String, Object>> tempResult = new LinkedHashMap<>();
		// 默认sheet 编码为：common 名称为：基本数据
		// 数组完整导出的，sheet编码为："前台表名"+"_"+"前台传参"，例如：TPSubject_jsly，名称：技术领域
		tempResult = initReturnData(tempResult, "common", "基本数据", genericInfoList, genericInfoMap, jsonObject, printType);
		for(int i = 0; i < field_arr.size(); i++) {
			JSONObject fieldJson = field_arr.getJSONObject(i);
			String fieldtype = fieldJson.get("fieldtype") + "";
			JSONArray data = fieldJson.getJSONArray("data");
			String modulename = fieldJson.get("modulename") + "";
			if("business".equals(fieldtype)) {
				String database = fieldJson.get("database") + "";
				String type = fieldJson.get("type") + "";
				String objecttype = fieldJson.get("objecttype") + "";
				String repeattype = fieldJson.get("repeattype") + "";
				if("repeat".equals(objecttype)) {
					// 循环模式，开启新的sheet
					if("complete".equals(repeattype)) {
						List<Map> list = tempDataSource.get(fieldtype + "_" + database + "_" + type);
						tempResult = initReturnData(tempResult, database + "_" + type, modulename, list, genericInfoMap, jsonObject, printType);
						//通用普通对象模式
						tempResult = dealReturnData(tempResult, data, database + "_" + type, tempDataSource, fieldtype + "_" + database + "_" + type, modulename);
					} else {
						//循环模式，使用拼接模式
						String field_split = Util.isEoN(fieldJson.get("field_split")) == true ? "@" : fieldJson.get("field_split") + "";
						String object_split = Util.isEoN(fieldJson.get("object_split")) == true ? "#" : fieldJson.get("object_split") + "";
						String enable_arr_count = Util.isEoN(fieldJson.get("enable_arr_count")) == true ? "否" : fieldJson.get("enable_arr_count") + "";
						List<Map> list = tempDataSource.get(fieldtype + "_" + database + "_" + type);
						Map<String, StringBuilder> stringBuilderMap = new HashMap<>();
						Map<String, Integer> stringIntegerMap = new HashMap<>();
						for(int j = 0; j < ((List<Map>) tempResult.get("common").get("data")).size(); j++) {
							String mainid = ((List<Map>) tempResult.get("common").get("data")).get(j).get("id") + "";
							stringBuilderMap.put(mainid, new StringBuilder());
							stringIntegerMap.put(mainid, 0);
							((List<Map>)tempResult.get("common").get("data")).get(j).put(fieldtype + "_" + database + "_" + type + "_count", 0);
							((List<Map>)tempResult.get("common").get("data")).get(j).put(fieldtype + "_" + database + "_" + type, "");
							//默认拼接标题
							//for(int k = 0; k < data.size(); k++) {
							//	String name = data.getJSONObject(k).get("name") + "";
							//	stringBuilderMap.get(mainid).append(name);
							//	if(k != data.size() - 1) {
							//		stringBuilderMap.get(mainid).append(field_split);
							//	} else if(k == data.size() - 1) {
							//		stringBuilderMap.get(mainid).append(object_split);
							//	}
							//}
						}
						for(int j = 0; j < list.size(); j++) {
							String mainid = list.get(j).get("mainid") + "";
							stringIntegerMap.put(mainid, stringIntegerMap.get(mainid) + 1);
							for(int k = 0; k < data.size(); k++) {
								String field = data.getJSONObject(k).get("field") + "";
								stringBuilderMap.get(mainid).append(list.get(j).get(field));
								if(k != data.size() - 1) {
									stringBuilderMap.get(mainid).append(field_split);
								} else if(k == data.size() - 1) {
									stringBuilderMap.get(mainid).append(object_split);
								}
							}
						}
						int totalrowSize = ((List<Map>) tempResult.get("common").get("data")).size();
						for(String mainid : stringBuilderMap.keySet()) {
							//拼接标题导出模式
							//stringBuilderMap.get(mainid).setLength(stringBuilderMap.get(mainid).length() - object_split.length());
							//不拼接标题导出模式
							if(stringBuilderMap.get(mainid).length() > 0) {
								stringBuilderMap.get(mainid).setLength(stringBuilderMap.get(mainid).length() - object_split.length());
							}
							for(int k = 0; k < totalrowSize; k++) {
								if(((List<Map>) tempResult.get("common").get("data")).get(k).get("id").equals(mainid)) {
									//默认导出数量
									if("是".equals(enable_arr_count)) {
										((List<Map>)tempResult.get("common").get("data")).get(k).put(fieldtype + "_" + database + "_" + type + "_count", Util.isEoN(stringIntegerMap.get(mainid)) == true ? 0 : stringIntegerMap.get(mainid));
										((List<Map>)tempResult.get("common").get("data")).get(k).put(fieldtype + "_" + database + "_" + type, Util.isEoN(stringBuilderMap.get(mainid)) == true ? "" : stringBuilderMap.get(mainid).toString());
									} else {
										((List<Map>)tempResult.get("common").get("data")).get(k).put(fieldtype + "_" + database + "_" + type, Util.isEoN(stringBuilderMap.get(mainid)) == true ? "" : stringBuilderMap.get(mainid).toString());
									}
									break;
								}
							}
						}
						if("是".equals(enable_arr_count)) {
							((LinkedHashMap)tempResult.get("common").get("field")).put(fieldtype + "_" + database + "_" + type + "_count", modulename + "数量");
							((LinkedHashMap)tempResult.get("common").get("field")).put(fieldtype + "_" + database + "_" + type, modulename);
						} else {
							((LinkedHashMap)tempResult.get("common").get("field")).put(fieldtype + "_" + database + "_" + type, modulename);
						}
						Map map = new HashMap();
						map.put("firstcol", Integer.parseInt(tempResult.get("common").get("columnnum") + ""));
						if("是".equals(enable_arr_count)) {
							map.put("lastcol", Integer.parseInt(tempResult.get("common").get("columnnum") + "") + 1);
						} else {
							map.put("lastcol", Integer.parseInt(tempResult.get("common").get("columnnum") + ""));
						}
						map.put("name", modulename);
						((LinkedList<Map>)tempResult.get("common").get("merge")).add(map);
						if("是".equals(enable_arr_count)) {
							tempResult.get("common").put("columnnum", Integer.parseInt(tempResult.get("common").get("columnnum") + "") + 2);
						} else {
							tempResult.get("common").put("columnnum", Integer.parseInt(tempResult.get("common").get("columnnum") + "") + 1);
						}
					}
				} else {
					//通用普通对象模式
					tempResult = dealReturnData(tempResult, data, "common", tempDataSource, fieldtype + "_" + database + "_" + type, modulename);
				}
			} else {
				tempResult = dealReturnData(tempResult, data, "common", tempDataSource, fieldtype, modulename);
			}
		}
		List<Map> result = new ArrayList<>();
		for (String key : tempResult.keySet()) {
			result.add(tempResult.get(key));
		}
		return result;
	}

	/**
	 * 处理返回数据源
	 * @param result
	 * @param fieldArr
	 * @param sheetcode
	 * @param tempDataSource
	 * @param tempDataSourceKey
	 * @param modulename
	 * @return
	 */
	public LinkedHashMap<String, Map<String, Object>> dealReturnData(LinkedHashMap<String, Map<String, Object>> result, JSONArray fieldArr, String sheetcode, Map<String, List> tempDataSource, String tempDataSourceKey, String modulename) {
		//通用普通对象模式
		int totalrowSize = ((List<Map>) result.get(sheetcode).get("data")).size();
		List<Map> list = tempDataSource.get(tempDataSourceKey);
		for(int i = 0; i < fieldArr.size(); i++) {
			String field = fieldArr.getJSONObject(i).get("field") + "";
			String name = fieldArr.getJSONObject(i).get("name") + "";
			for(int j = 0; j < list.size(); j++) {
				if("common" == sheetcode) {
					for(int k = 0; k < totalrowSize; k++) {
						if(((List<Map>) result.get(sheetcode).get("data")).get(k).get("id").equals(list.get(j).get("mainid"))
								|| ((List<Map>) result.get(sheetcode).get("data")).get(k).get("id").equals(list.get(j).get("id"))) {
							((List<Map>)result.get(sheetcode).get("data")).get(k).put(tempDataSourceKey + "_" + field, Util.isEoN(list.get(j).get(field)) == true ? "" : list.get(j).get(field));
							break;
						}
					}
				} else {
					((List<Map>)result.get(sheetcode).get("data")).get(j).put(tempDataSourceKey + "_" + field, Util.isEoN(list.get(j).get(field)) == true ? "" : list.get(j).get(field));
				}
			}
			((LinkedHashMap)result.get(sheetcode).get("field")).put(tempDataSourceKey + "_" + field, name);
		}
		Map map = new HashMap();
		map.put("firstcol", Integer.parseInt(result.get(sheetcode).get("columnnum") + ""));
		map.put("lastcol", Integer.parseInt(result.get(sheetcode).get("columnnum") + "") + fieldArr.size() - 1);
		map.put("name", modulename);
		((LinkedList<Map>)result.get(sheetcode).get("merge")).add(map);
		result.get(sheetcode).put("columnnum", Integer.parseInt(result.get(sheetcode).get("columnnum") + "") + fieldArr.size());
		return result;
	}

	/**
	 * 初始化返回值
	 * @param result
	 * @param sheetcode
	 * @param sheetname
	 * @param list
	 * @param genericInfoMap
	 * @return
	 */
	public LinkedHashMap<String, Map<String, Object>> initReturnData(LinkedHashMap<String, Map<String, Object>> result, String sheetcode, String sheetname, List<Map> list, Map<String, String> genericInfoMap, JSONObject jsonObject, String printType) {
		result.put(sheetcode, new HashMap());
		result.get(sheetcode).put("data", new ArrayList<Map>());
		result.get(sheetcode).put("field", new LinkedHashMap<>());
		result.get(sheetcode).put("merge", new LinkedList<Map>());
		result.get(sheetcode).put("sheetname", sheetname);
		Map map = new HashMap();
		map.put("firstcol", 0);
		map.put("name", "默认内容");
		JSONArray idArr = new JSONArray();
		if("select".equals(printType)) {
			idArr = jsonObject.getJSONArray("seletedData");
		} else {
			for(int i = 0; i < list.size(); i++) {
				idArr.add(list.get(i).get("mainid") + "");
			}
		}
		// list排序
		Collections.sort(list, new ListMapByArrSort(idArr.toArray()));
		for(int i = 0; i < list.size(); i++) {
			((List<Map>)result.get(sheetcode).get("data")).add(new HashMap());
			if("common".equals(sheetcode)) {
				String mainid = list.get(i).get("mainid") + "";
				((List<Map>) result.get(sheetcode).get("data")).get(i).put("row", (i + 1));
				((List<Map>) result.get(sheetcode).get("data")).get(i).put("id", mainid);
				((List<Map>) result.get(sheetcode).get("data")).get(i).put("projectbasename", genericInfoMap.get(mainid));
			} else {
				String mainid = list.get(i).get("mainid") + "";
				String id = list.get(i).get("id") + "";
				((List<Map>) result.get(sheetcode).get("data")).get(i).put("row", (i + 1));
				((List<Map>) result.get(sheetcode).get("data")).get(i).put("id", id);
				((List<Map>) result.get(sheetcode).get("data")).get(i).put("mainid", mainid);
				((List<Map>) result.get(sheetcode).get("data")).get(i).put("projectbasename", genericInfoMap.get(mainid));
			}
		}
		String enable_unique = jsonObject.get("enable_unique") + "";
		if("common".equals(sheetcode)) {
			((LinkedHashMap)result.get(sheetcode).get("field")).put("row", "序号");
			if("是".equals(enable_unique)) {
				((LinkedHashMap)result.get(sheetcode).get("field")).put("id", "项目ID");
				map.put("lastcol", 1);
				result.get(sheetcode).put("columnnum", 2);
			} else {
				map.put("lastcol", 0);
				result.get(sheetcode).put("columnnum", 1);
			}
		} else {
			((LinkedHashMap)result.get(sheetcode).get("field")).put("row", "序号");
			if("是".equals(enable_unique)) {
				((LinkedHashMap)result.get(sheetcode).get("field")).put("id", "记录ID");
				((LinkedHashMap)result.get(sheetcode).get("field")).put("mainid", "项目ID");
				((LinkedHashMap)result.get(sheetcode).get("field")).put("projectbasename", "项目名称");
				map.put("lastcol", 3);
				result.get(sheetcode).put("columnnum", 4);
			} else {
				((LinkedHashMap)result.get(sheetcode).get("field")).put("projectbasename", "项目名称");
				map.put("lastcol", 1);
				result.get(sheetcode).put("columnnum", 2);
			}
		}
		((LinkedList<Map>)result.get(sheetcode).get("merge")).add(map);
		return result;
	}

	/**
	 * 处理完整数据源
	 * @param dataObject
	 * @param pmsGridExportList
	 * @param versionid
	 * @param allGridDataArr
	 * @return
	 */
	public Map dealCompleteDataSource(JSONObject dataObject, List<PmlGridExport> pmsGridExportList, String versionid, JSONArray allGridDataArr) {
		Map<String, List> tempDataSource = new HashMap<>();
		List<Map> tempList = new ArrayList<>();
		// 1.获取列表已有字段 数据源
		tempList = allGridDataArr;
		tempDataSource.put("original", tempList);
		// 获取业务ids
		JSONArray idArr = new JSONArray();
		for(int i = 0; i < tempList.size(); i++) {
			idArr.add(tempList.get(i).get("id"));
		}
		// 2.获取业务公共字段 public 数据源
		tempList = initConfigExportInfo(idArr, pmsGridExportList, "public", "业务公共字段");
		tempDataSource.put("public", tempList);
		// 如果versionid 为空， 则版本数大于1 种，则使用默认业务详情字段
		// 若versionid 非空 则说明只有一个版本，则按业务详情版本 进行导出
		if(!Util.isEoN(versionid)) {
			// 3.1 获取业务详情字段 数据源
			dataObject = genericFetchAndSave.initExportData(dataObject, idArr);
			Iterator<String> keysIterator = dataObject.keys();
			while(keysIterator.hasNext()) {
				// 获得key
				String key = keysIterator.next();
				String database = dataObject.getJSONObject(key).get("database") + "";
				String type = dataObject.getJSONObject(key).get("type") + "";
				tempList = dataObject.getJSONObject(key).getJSONArray("data");
				tempDataSource.put("business_" + database + "_" + type, tempList);
			}
		} else {
			// 3.2 获取业务默认详情字段 default 数据源
			tempList = initConfigExportInfo(idArr, pmsGridExportList, "default", "默认业务详情字段");
			tempDataSource.put("default", tempList);
		}
		// 4.列表定制化字段 grid 数据源
		tempList = initConfigExportInfo(idArr, pmsGridExportList, "grid", "列表定制化字段");
		tempDataSource.put("grid", tempList);
		// 5.定制化字段 custom 数据源
		tempList = initConfigCustomExportInfo(idArr, pmsGridExportList);
		tempDataSource.put("custom", tempList);
		return tempDataSource;
	}

	/**
	 * 初始化配置模块数据
	 * @param mainidArr
	 * @param pmsGridExportList
	 * @param type
	 * @param name
	 * @return
	 */
	public List<Map> initConfigExportInfo(JSONArray mainidArr, List<PmlGridExport> pmsGridExportList, String type, String name) {
		List<Map> result = new ArrayList<>();
		List<PmlGridExport> tempPmlGridExportList = Util.ListDoWhere(pmsGridExportList, "type", type);
		if(tempPmlGridExportList.size() > 1) {
			throw new ServiceException(name + "配置错误!!!");
		} else if(tempPmlGridExportList.size() == 1) {
			PmlGridExport pmsGridExport = tempPmlGridExportList.get(0);
			String sql_script = pmsGridExport.getSql_script();
			if(Util.isEoN(sql_script)) {
				throw new ServiceException(name + "获取脚本未配置!!!");
			}
			if(mainidArr.size() == 0) {
				return result;
			}
			String insql = Util.sqlSplicingForInStatement(mainidArr, "t.id");
//			result = dbHelper.getRows(sql_script + " and ( " + insql + " ) ", mainidArr.toArray());
			result = dbHelper.getRowsAll(sql_script + " and ( " + insql + " ) ", mainidArr.toArray(), mainidArr.size(), 500);
		} else {
			//未配置，无需处理
		}
		return result;
	}

	/**
	 * 初始化配置模块数据(针对定制化字段)
	 * @param pmsGridExportList
	 * @return
	 */
	public List<Map> initConfigCustomExportInfo(JSONArray mainidArr, List<PmlGridExport> pmsGridExportList) {

		List<Map> result = new ArrayList<>();
		if(mainidArr.size() == 0) {
			return result;
		}
		PmlGridExport pmsGridExport = pmsGridExportList.stream().filter(pms -> "custom".equals(pms.getType())).findFirst().orElse(null);
		if(!Util.isEoN(pmsGridExport)) {
			String sql_script = pmsGridExport.getSql_script();
			if(Util.isEoN(sql_script)) {
				throw new ServiceException("定制化获取脚本未配置!!!");
			}
			String insql = Util.sqlSplicingForInStatement(mainidArr, "t.id");
//			result = dbHelper.getRows(sql_script + " and ( " + insql + " ) ", mainidArr.toArray());
			result = dbHelper.getRowsAll(sql_script + " and ( " + insql + " ) ", mainidArr.toArray(), mainidArr.size(), 500);
		} else {
			//未配置，或者定制化字段集大于1个，无需处理
		}
		return result;
	}

	public PmlGridExport fetchCostomGridExport(JSONArray mainidArr, List<PmlGridExport> pmsGridExportList) {

		PmlGridExport pmsGridExport = null;
		if(mainidArr.size() == 0) {
			return pmsGridExport;
		}
		List<Map> tempList = new ArrayList<>();
		for (PmlGridExport tempPmlGridExport : pmsGridExportList) {
			if(!"custom".equals(tempPmlGridExport.getType())) {
				continue;
			}
			// zjk_batch_project@type@sb#zjk_batch_project@type@ht&;3561EBA7-DC10-42C3-B744-04ADB682F7E9;
			String value = tempPmlGridExport.getValue();
			// zjk_batch_project@type@sb#zjk_batch_project@type@ht
			// ;3561EBA7-DC10-42C3-B744-04ADB682F7E9;
			String[] split = value.split("&");
			// zjk_batch_project@type@sb
			// zjk_batch_project@type@ht
			String[] split1 = split[0].split("#");
			Map map = new HashMap();
			tempList = new ArrayList<>();
			for (String s : split1) {
				String[] split2 = s.split("@");
				if(split2.length != 3) {
					throw new ServiceException("定制化导出参数配置错误!!!");
				}
				map.put("tablename", split2[0]);
				map.put("field", split2[1]);
				map.put("value", split2[2]);
				tempList.add(map);
			}
			// 一轮做一次判断，判断当前PmlGridExport是否已做为定制化字段集

			// 转化后 tempList 的结果集为：
			// [
			//     {"tablename": "zjk_batch_project", "field": "type", "value": "sb"},
			//     {"tablename": "zjk_batch_project", "field": "type_1", "value": "ht"},
			//     {"tablename": "zjk_batch_project", "field": "type_1", "value": "guide_collect"}
			//     {"tablename": "zjk_batch_rpproject", "field": "type_2", "value": "sb"}
			// ]

			// 通过计算，将前台配置的内容转化为对应的SQL语句，用于判断需导出的记录是否存在多种定制化模板，只有一种的时候才按照定制化的模板导出
			// select t.id from zjk_batch_project t where ( t.type_1 in ( 'guide_collect', 'ht' )  or t.type in ( 'sb' )  ) and t.id in ('', '')
			// union
			// select t.id from zjk_batch_rpproject t where ( t.type_2 in ( 'sb' )  ) and t.id in ('', '')

			Map<String, Map<String, Map<String, List<Map>>>> collect = tempList.stream().collect(Collectors.groupingBy(tempmap -> tempmap.get("tablename") + "", Collectors.groupingBy(tempmap -> tempmap.get("field") + "", Collectors.groupingBy(tempmap -> tempmap.get("value") + ""))));

			String tempSql = "";
			JSONArray param = new JSONArray();
			List<String> unionSqlList = new ArrayList();
			for (Map.Entry<String, Map<String, Map<String, List<Map>>>> stringMapEntry : collect.entrySet()) {
				List<String> whereKeyStringList = new ArrayList();
				Map<String, Set> whereKeyValueStringSetMap = new HashMap<>();
				for (Map.Entry<String, Map<String, List<Map>>> mapEntry : stringMapEntry.getValue().entrySet()) {
					whereKeyStringList.add(mapEntry.getKey());
					whereKeyValueStringSetMap.put(mapEntry.getKey(), mapEntry.getValue().keySet());
				}
				tempSql = "";
				tempSql += " select t.id \n";
				tempSql += " from " + stringMapEntry.getKey() + " t \n";
				tempSql += " where ( ";
				for (String inString : whereKeyStringList) {
					String collect1 = whereKeyValueStringSetMap.get(inString).stream().collect(Collectors.joining("', '", "'", "'")) + "";
					tempSql += "t." + inString + " in ( " + collect1 + " ) ";
					tempSql += " or ";
				}
				tempSql = tempSql.substring(0, tempSql.length() - 4);
				String idStrSql = Util.sqlSplicingForInStatement(mainidArr, "t.id");
				param.addAll(mainidArr);
				tempSql += " ) \n and " + idStrSql;
				unionSqlList.add(tempSql);
			}
			String completeSql = unionSqlList.stream().collect(Collectors.joining("\n union \n")) + "";
//			List<Map> rows = dbHelper.getRows(completeSql, param.toArray());
			List<Map> rows = dbHelper.getRowsAll(completeSql, param.toArray(), param.size(), 500);
			if(rows.size() > 0) {
				if(Util.isEoN(pmsGridExport)) {
					pmsGridExport = tempPmlGridExport;
				} else {
					// 定制化字段集大于一个
					pmsGridExport = null;
					break;
				}
			}
		}
		return pmsGridExport;
	}

	public JSONObject initExportData(JSONObject jsonObject) {

		JSONObject result = new JSONObject();
//		JSONObject dataObject = jsonObject.getJSONObject("dataObject");
//		JSONArray idArr = jsonObject.getJSONArray("idArr");
//		dataObject = genericFetchAndSave.initExportData(dataObject, idArr);
//		result.put("dataObject", dataObject.toString());
		JSONArray array = JSONArray.fromObject(fetchExportDataSource(jsonObject));
		result.put("data", array);
		return result;
	}



	public Boolean saveGridExport(JSONObject jsonObject){
		String id = "";
		if(Util.isEoN(jsonObject.get("id"))){
			id = Util.NewGuid();
		}else{
			id = jsonObject.get("id")+"";
		}
		PmlGridExport pmsGridExport = this.findById(id);
		if(Util.isEoN(pmsGridExport)) {
			pmsGridExport = new PmlGridExport();
		}
		Util.ApplyObject(pmsGridExport,jsonObject);
		this.merge(pmsGridExport);
		return true;
	}

	public Boolean deleteGridExport(JSONObject jsonObject) {

		Boolean result = true;
		String id = jsonObject.get("id") + "";
		if(Util.isEoN(id)) {
			throw new ServiceException("删除失败，未获取到id!!!");
		}
		this.deleteById(id);
		return result;
	}

	public List<Map> initGridExport() {
		String sql = " select t.* from PMS_GRID_EXPORT t where 1=1 ";
		List<Map> list = dbHelper.getRows(sql, null);
		return list;
	}

	/**
	 * 根据已知id的顺序对List_Map进行排序
	 */
	private class ListMapByArrSort implements Comparator<Map> {

		Object[] array;

		public Object[] getArray() {
			return array;
		}

		public void setArray(Object[] array) {
			this.array = array;
		}

		ListMapByArrSort(Object[] array) {
			this.array = array;
		}

		@Override
		public int compare(Map map1, Map map2) {
			return Integer.valueOf(ArrayUtils.indexOf(this.array, map1.get("id").toString())).compareTo(ArrayUtils.indexOf(this.array, map2.get("id").toString()));
		}
	}
}
