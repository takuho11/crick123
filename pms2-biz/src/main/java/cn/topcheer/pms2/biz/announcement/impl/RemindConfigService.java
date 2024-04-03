/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-7-25 16:10:05
 *
 */
package cn.topcheer.pms2.biz.announcement.impl;

import cn.topcheer.halberd.app.api.utils.PmsTxtSave;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.announcement.RemindConfig;
import cn.topcheer.pms2.api.announcement.RemindRole;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.common.enumerate.ErrorCodeEnum;
import cn.topcheer.pms2.common.exception.BusinessException;
import cn.topcheer.pms2.dao.announcement.RemindConfigDao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * RemindConfig 服务类
 */
@Service(value="RemindConfigService")
public class RemindConfigService extends GenericService<RemindConfig> {

	public RemindConfigDao getRemindConfigDao() {
		return (RemindConfigDao) this.getGenericDao();
	}
	@Autowired
	public void setRemindConfigDao(RemindConfigDao remindConfigDao) {
		this.setGenericDao(remindConfigDao);
	}
	@Autowired
	private SysOperationrecordService sysOperationrecordService;
	@Autowired
	private RemindRoleService remindRoleService;
	@Autowired
	private PmsTxtSave pmsTxtSave;
	@Autowired
	private DBHelper dbHelper;

	/*==============================================完美分割线==============================================*/


	/**
	 * 【主动提醒配置】 -- 根据参数获取对应的配置数据
     */
	public List<Map> getAllConfigData(String system,String type){
		return  this.dbHelper.getRows("select g.*,aa.rolename,rownum as xh " +
				" from ( " +
				" select e.id,wmsys.wm_concat(o.rolename) as rolename " +
				" from remind_config e " +
				" left join remind_role r on e.id = r.configid  " +
				" left join sys_role o on r.roleid = o.id " +
				" where e.system = ? and e.type = ? " +
				" group by e.id) aa  " +
				" left join remind_config g on aa.id = g.id  " +
				" order by g.seq",new Object[]{system,type});
	}


	/**
	 * 【主动提醒配置】 -- 新增+修改 配置数据
	 */
	public void addAndEditConfig(JSONObject json){
		String type = "";//新增或者修改
		String id = json.get("id")+"";
		if(Util.isEoN(id)){
			throw new BusinessException(ErrorCodeEnum.PARAM_COMPLETE_ERROR);
		}
		RemindConfig p = this.findById(id);
		if(!Util.isEoN(p)){
			//说明是修改，判断是否可以修改
			type = "修改主动提醒配置";
		}else{
			//说明是新增，判断是否可以新增
			p = new RemindConfig();
			type = "新增主动提醒配置";
		}
		Util.ApplyObject(p,json);
		//保存或修改
		this.merge(p);
		//操作记录
		this.sysOperationrecordService.commonSaveOperation(p.getId(),type,"保存了主动提醒配置数据。");
		this.pmsTxtSave.saveTxt(p.getId(),json,"RemindConfigService","addAndEditConfig");
	}

	/**
	 * 【主动提醒配置】 -- 通过id删除配置
	 */
	public void deleteData(String id){
		if(Util.isEoN(id)){
			throw new BusinessException(ErrorCodeEnum.PARAM_COMPLETE_ERROR);
		}
		RemindConfig p = this.findById(id);
		List<RemindRole> list = this.remindRoleService.findByHql("from RemindRole where configid = ?0",new Object[]{id});
		JSONObject json = new JSONObject();//删除对象记录
		json.put("obj",JSONObject.fromObject(p));
		json.put("arr",JSONArray.fromObject(list));
		//操作记录
		this.sysOperationrecordService.commonSaveOperation(id,"删除主动提醒配置","删除了主动提醒配置数据。");
		this.pmsTxtSave.saveTxt(id,json,"RemindConfigService","deleteById");
		//删除
		this.remindRoleService.deleteList(list);
		this.delete(p);
	}


	/**
	 * 【主动提醒配置】 -- 给主动提醒配置角色
     */
	public void configureRoles(JSONObject json) throws SQLException {
		String configid = json.get("id")+"";
		JSONArray arr = json.getJSONArray("arr");
		//先删除原先配置的所有角色
		this.dbHelper.runSql("delete from remind_role where configid = ?",new Object[]{configid});
		//绑定角色
		for (int i = 0; i < arr.size(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			RemindRole p = new RemindRole();
			p.setId(Util.NewGuid());
			p.setConfigid(configid);
			p.setRoleid(obj.get("id")+"");
			p.setSeq(i);
			this.remindRoleService.merge(p);
		}
		//操作记录
		this.sysOperationrecordService.commonSaveOperation(configid,"主动提醒配置角色","给主动提醒配置了角色。");
		this.pmsTxtSave.saveTxt(configid,json,"RemindConfigService","configureRoles");
	}


	/**
	 * 【主动提醒配置】 -- 通过id获取角色集合
     */
	public List<Map> getRolesByConfigid(String configid){
		return this.dbHelper.getRows("select r.id,r.rolename,r.fz from remind_role e " +
				" left join sys_role r on e.roleid = r.id " +
				" where e.configid = ? order by e.seq",new Object[]{configid});
	}



	/**
	 * 【主动提醒配置】 -- 导出配置数据
	 */
	public HSSFWorkbook outputDataById(String ids, String type){
		String[] idArr = ids.split(",");
		String id_insql = "";
		for (int i = 0; i < idArr.length; i++) {
			if(i==0){
				id_insql = "('";
			}
			id_insql = id_insql +idArr[i]+ "','";
		}
		if(!Util.isEoN(id_insql)){
			id_insql = id_insql.substring(0,id_insql.length()-2)+")";
		}
		// 创建文档对象(其他对象都是通过文档对象创建)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建样式对象（HSSFCellStyle ）
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 导出excel的名称
		if("config".equals(type)){
			//条件配置数据
			List<Map> configList = this.dbHelper.getRows("select t.* from remind_config t where t.id in "+id_insql, new Object[]{});
			String[] configKeys = Util.getKeys(configList);
			configList = Util.changeBoolean(configList,"readscript");
			wb = Util.getSheetAll(configList, wb, cellStyle, configKeys, configKeys, "RemindConfig");
			List<Map> roleList = this.dbHelper.getRows("select t.* from remind_role t where t.configid in "+id_insql +" order by t.configid", new Object[]{});
			String[] roleKeys = Util.getKeys(roleList);
			wb = Util.getSheetAll(roleList, wb, cellStyle, roleKeys, roleKeys, "RemindRole");
		}
		return wb;
	}

	/**
	 * 指定表名导入数据
	 */
	public Boolean importData(File excelFile){
		try{
			Workbook rwb = Workbook.getWorkbook(excelFile);
			// 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...
			for (int i = 0; i < rwb.getSheets().length; i++) {
				Sheet sheet = rwb.getSheets()[i];
				int rsColumns = sheet.getColumns();// 列数
				int rsRows = sheet.getRows();// 行数
				String tablename = sheet.getName();
				this.cellMerge(sheet, rsColumns, rsRows, "cn.topcheer.pms.pojo.", tablename);
			}
			return true;
		}catch (Exception e){
			return false;
		}
	}

	/**
	 * 处理大字段
	 */
	private void cellMerge(Sheet sheet, int rsColumns, int rsRows, String preffix, String classname) {
		try {
			Object service = Util.getBeanObject(classname + "Service");
			Class<?> clazz = null;
			try {
				clazz = Class.forName(preffix + classname);
			} catch (ClassNotFoundException e) {
				preffix ="cn.topcheer.pms.pojo.";
				try {
					clazz = Class.forName(preffix + classname);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			Object object = clazz.newInstance();
			Method merge = service.getClass().getMethod("merge", Object.class);
			Cell[] cellkey = sheet.getRow(0);
			//先删除原始数据
			if("RemindRole".equals(classname)){
				String lastConfigid = "";
				for (int j = 1; j < rsRows; j++) {
					JSONObject json = new JSONObject();
					Cell[] cellvalue = sheet.getRow(j);
					for (int k = 0; k < rsColumns; k++) {
						json.put(cellkey[k].getContents(), cellvalue[k].getContents());
					}
					//判断是否还有值
					if("RemindRole".equals(classname)){
						String configid = json.getString("configid");
						if(!configid.equals(lastConfigid)){
							List<RemindRole> list = this.findByHql("select t from RemindRole t where t.configid = ?0",new Object[]{configid});
							if(list.size()>0){
								this.remindRoleService.deleteList(list);
							}
						}
					}
					lastConfigid = json.getString("configid");
				}
			}
			//赋值
			for (int j = 1; j < rsRows; j++) {
				JSONObject json = new JSONObject();
				Cell[] cellvalue = sheet.getRow(j);
				for (int k = 0; k < rsColumns; k++) {
					json.put(cellkey[k].getContents(), cellvalue[k].getContents());
				}
				System.out.println(json);
				Util.ApplyObject(object, json);
				merge.invoke(service, object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
