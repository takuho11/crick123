/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-6-29 17:08:24
 *
 */
package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.limit.entity.LimitCondition;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.plan.LimitConditionDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * LimitCondition 服务类
 */
@Service(value="LimitConditionService")
public class LimitConditionService extends GenericService<LimitCondition> {

	public LimitConditionDao getLimitConditionDao() {
		return (LimitConditionDao) this.getGenericDao();
	}

	@Autowired
	public void setLimitConditionDao(LimitConditionDao limitConditionDao) {

		this.setGenericDao(limitConditionDao);
	}
	@Autowired
	private SysOperationrecordService sysOperationrecordService;
	@Autowired
	private DBHelper dbHelper;
//	@Autowired
//	private LimitConfigService limitConfigService;
//	@Autowired
//	private PmsBatchLimitService pmsBatchLimitService;

	/**
	 * 【通用后台限制配置】 -- 根据system和type获取条件数据
	 * @param system
	 * @param type
     * @return
     */
	public List<Map> getDataBySystemAndType(String system,String type){
		return dbHelper.getRows("select tt.*,rownum as xh from (" +
				" select e.* from limit_condition e where e.system = ? and e.type = ? order by e.seq) tt",new Object[]{system,type});
	}

	/**
	 * 【通用后台限制配置】 -- 新增+修改 条件
	 * @param request
	 * @param json
     * @return
     */
	public JSONObject addAndEditCondition(HttpServletRequest request, JSONObject json){
		JSONObject resJson = new JSONObject();
		String type = "";//新增或者修改
		LimitCondition p = this.findById(json.get("id")+"");
		if(!Util.isEoN(p)){
			//说明是修改，判断是否可以修改
			JSONObject judgeJson = this.judgeEdiCondition(json);
			if(!judgeJson.getBoolean("success")){
				return judgeJson;
			}
			type = "修改限制条件";
			Util.ApplyObject(p,json);
		}else{
			//说明是新增，判断是否可以新增
			JSONObject judgeJson = this.judgeAddCondition(json);
			if(!judgeJson.getBoolean("success")){
				return judgeJson;
			}
			p = new LimitCondition();
			type = "新增限制条件";
			Util.ApplyObject(p,json);
		}
		//保存或修改
		this.merge(p);
		//操作记录
		this.sysOperationrecordService.commonSaveOperation(request,json.get("id")+"",type,"本次保存的限制条件数据："+json.toString());
		resJson.put("success",true);
		return resJson;
	}

	/**
	 * 【通用后台限制配置】-- 判断是否可以新增小批次
	 * @param json
	 * @return
	 */
	private JSONObject judgeAddCondition(JSONObject json){
		JSONObject resJson = new JSONObject();
		String code = json.get("code")+"";
		String type = json.get("type")+"";
		List<Map> list = dbHelper.getRows("select e.id from limit_condition e where e.code = ? and e.type = ?",new Object[]{code,type});
		if(list.size()>0){
			resJson.put("success",false);
			resJson.put("reason","当前填写的条件代码已存在。");
		}else{
			resJson.put("success",true);
		}
		return resJson;
	}
//
	/**
	 * 【通用后台限制配置】-- 判断是否可以修改条件
	 * @param json
	 * @return
	 */
	private JSONObject judgeEdiCondition(JSONObject json){
		JSONObject resJson = new JSONObject();
		String code = json.get("code")+"";
		String type = json.get("type")+"";
		LimitCondition b = this.findById(json.get("id")+"");
		if(code.equals(b.getCode())){
			resJson.put("success",true);
		}else{
			List<Map> list = dbHelper.getRows("select e.id from limit_condition e where e.code = ? and e.type = ?",new Object[]{code,type});
			if(list.size()>0){
				resJson.put("success",false);
				resJson.put("reason","当前填写的条件代码已存在。");
			}else{
				resJson.put("success",true);
			}
		}
		return resJson;
	}
//
//	/**
//	 * 【通用后台限制配置】 -- 通过条件id获取绑定的批次
//	 * @param conditionid
//	 * @return
//     */
//	public List<Map> getBatchListByCid(String conditionid){
//		return dbHelper.getRows("select tt.*,rownum as xh from (select b.name as batchname from limit_config e " +
//				" left join pms_planprojectbatch b on e.batchid = b.id " +
//				" where e.conditionid = ? order by b.planprojectid,b.annually) tt ",new Object[]{conditionid});
//	}
//

	/**
	 * 【通用后台限制配置】 -- 通过条件id删除条件
	 * @param request
	 * @param conditionid
     * @return
     */
	public JSONObject deleteByCid(HttpServletRequest request,String conditionid){
		JSONObject resJson = new JSONObject();
		//判断当前条件Id是否绑定了批次
		List<Map> list = dbHelper.getRows("select e.id from limit_config e where e.conditionid = ?",new Object[]{conditionid});
		if(list.size()>0){
			resJson.put("success",false);
			resJson.put("reason","当前条件下有绑定的批次，不能删除。");
			return resJson;
		}else{
			resJson.put("success",true);
		}
		LimitCondition p = this.findById(conditionid);
		//操作记录
		this.sysOperationrecordService.commonSaveOperation(request,conditionid,"删除限制条件","本次删除的限制条件数据："+JSONObject.fromObject(p).toString());
		this.delete(p);
		return resJson;
	}
//
//
//
//
//	/**
//	 * 导出列表的配置数据
//	 */
//	public HSSFWorkbook outputDataById(String ids, String type){
//		String[] idArr = ids.split(",");
//		String id_insql = "";
//		for (int i = 0; i < idArr.length; i++) {
//			if(i==0){
//				id_insql = "('";
//			}
//			id_insql = id_insql +idArr[i]+ "','";
//		}
//		if(!Util.isEoN(id_insql)){
//			id_insql = id_insql.substring(0,id_insql.length()-2)+")";
//		}
//		// 创建文档对象(其他对象都是通过文档对象创建)
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// 创建样式对象（HSSFCellStyle ）
//		HSSFCellStyle cellStyle = wb.createCellStyle();
//		// 导出excel的名称
//		if("condition".equals(type)){
//			//条件配置数据
//			List<Map> list = this.dbHelper.getRows("select t.* from limit_condition t where t.id in "+id_insql, new Object[]{});
//			String[] keys = Util.getKeys(list);
//			wb = Util.getSheetAll(list, wb, cellStyle, keys, keys, "LimitCondition");
//		}else if("config".equals(type)){
//			//批次绑定配置数据
//			List<Map> list = this.dbHelper.getRows("select t.* from limit_config t where t.batchid in "+id_insql+"order by t.batchid", new Object[]{});
//			String[] keys = Util.getKeys(list);
//			wb = Util.getSheetAll(list, wb, cellStyle, keys, keys, "LimitConfig");
//		}else if("batchLimit".equals(type)){
//			//批次绑定配置数据
//			List<Map> list = this.dbHelper.getRows("select t.* from pms_batch_limit t where t.batchid in "+id_insql+"order by t.batchid", new Object[]{});
//			String[] keys = Util.getKeys(list);
//			wb = Util.getSheetAll(list, wb, cellStyle, keys, keys, "PmsBatchLimit");
//		}
//		return wb;
//	}
//
//
//	/**
//	 * 指定表名导入数据
//	 * @param excelFile
//	 * @return
//	 */
//	public Boolean importData(File excelFile){
//		try{
//			Workbook rwb = Workbook.getWorkbook(excelFile);
//			// 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...
//			for (int i = 0; i < rwb.getSheets().length; i++) {
//				Sheet sheet = rwb.getSheets()[i];
//				int rsColumns = sheet.getColumns();// 列数
//				int rsRows = sheet.getRows();// 行数
//				String tablename = sheet.getName();
//				this.cellMerge(sheet, rsColumns, rsRows, "cn.topcheer.pms.pojo.", tablename);
//			}
//			return true;
//		}catch (Exception e){
//			return false;
//		}
//	}
//
//	/**
//	 * 处理大字段
//	 * @param sheet
//	 * @param rsColumns
//	 * @param rsRows
//	 * @param preffix
//	 * @param classname
//	 */
//	private void cellMerge(Sheet sheet, int rsColumns, int rsRows, String preffix, String classname) {
//		try {
//			Object service = Util.getBeanObject(classname + "Service");
//			Class<?> clazz = null;
//			try {
//				clazz = Class.forName(preffix + classname);
//			} catch (ClassNotFoundException e) {
//				preffix ="cn.topcheer.pms.pojo.";
//				try {
//					clazz = Class.forName(preffix + classname);
//				} catch (ClassNotFoundException e1) {
//					e1.printStackTrace();
//				}
//			}
//			Object object = clazz.newInstance();
//			Method merge = service.getClass().getMethod("merge", Object.class);
//			Cell[] cellkey = sheet.getRow(0);
//			//先删除原始数据
//			if("LimitConfig".equals(classname)||"PmsBatchLimit".equals(classname)){
//				String lastBathcid = "";
//				for (int j = 1; j < rsRows; j++) {
//					JSONObject json = new JSONObject();
//					Cell[] cellvalue = sheet.getRow(j);
//					for (int k = 0; k < rsColumns; k++) {
//						json.put(cellkey[k].getContents(), cellvalue[k].getContents());
//					}
//					//判断是否还有值
//					if("LimitConfig".equals(classname)){
//						String batchid = json.getString("batchid");
//						if(!batchid.equals(lastBathcid)){
//							List<LimitConfig> list = this.findByHql("select t from LimitConfig t where t.batchid = ?",new Object[]{batchid});
//							if(list.size()>0){
//								this.limitConfigService.deleteList(list);
//							}
//						}
//					}
//					if("PmsBatchLimit".equals(classname)){
//						String batchid = json.getString("batchid");
//						if(!batchid.equals(lastBathcid)){
//							List<PmsBatchLimit> list = this.findByHql("select t from PmsBatchLimit t where t.batchid = ?",new Object[]{batchid});
//							if(list.size()>0){
//								this.pmsBatchLimitService.deleteList(list);
//							}
//						}
//					}
//					lastBathcid = json.getString("batchid");
//				}
//			}
//			//赋值
//			for (int j = 1; j < rsRows; j++) {
//				JSONObject json = new JSONObject();
//				Cell[] cellvalue = sheet.getRow(j);
//				for (int k = 0; k < rsColumns; k++) {
//					json.put(cellkey[k].getContents(), cellvalue[k].getContents());
//				}
//				System.out.println(json);
//				Util.ApplyObject(object, json);
//				if("LimitConfig".equals(classname)){
//					if(!Util.isEoN(json.get("param"))){
//						Method method = clazz.getMethod("setParam", String.class);
//						method.invoke(object, json.get("param").toString() );
//					}
//				}
//				merge.invoke(service, object);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	
}
