/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-9-23 16:16:01
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.api.utils.JsonDateValueProcessor;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseeditData;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseeditDataDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * PmsProjectbaseeditData 服务类
 */
@Service(value="PmsProjectbaseeditDataService")
public class PmsProjectbaseeditDataService extends GenericService<PmsProjectbaseeditData> {

	public PmsProjectbaseeditDataDao getPmsProjectbaseeditDataDao() {
		return (PmsProjectbaseeditDataDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaseeditDataDao(PmsProjectbaseeditDataDao pmsProjectbaseeditDataDao) {

		this.setGenericDao(pmsProjectbaseeditDataDao);
	}
	@Autowired
	private PmsProjectbaseService pmsProjectbaseService;
	@Autowired
	private DBHelper dbHelper;

//	@Autowired
//	private PmsPlanprojectbatchService pmsPlanprojectbatchService;

	/**
	 * 【申报填报流程】---被退回后重新上报要 保存修改记录
	 * @param json
	 * @param projectbaseid
	 * @return
	 */
	public Boolean saveEditDataByPid(JSONObject json,String projectbaseid){
		try {
			JSONArray editdata = json.getJSONArray("changedata");
			String editdataStr = editdata.toString();
			JSONArray affixchange = json.getJSONArray("affixchange");
			String affixchangeStr = affixchange.toString();
			List<Map> hi_list = dbHelper.getRows("select id from pms_projectbaseedit_data where projectbaseid = ? ",
					new Object[]{projectbaseid});
			PmsProjectbaseeditData data = new PmsProjectbaseeditData();
			data.setId(Util.NewGuid());
			data.setProjectbaseid(projectbaseid);
			data.setEditdata(editdataStr);
			data.setAffixchange(affixchangeStr);
			data.setCreatedate(new Date());
			if(!Util.isEoN(hi_list)&&hi_list.size()>0){
				data.setEditnum(hi_list.size()+1);
			}else{
				data.setEditnum(1);
			}
			this.merge(data);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	/**
	 * 【合同填报流程】---删除最新的一条修改记录
	 * @param json
	 */
	public void deleteEditDataByCid(JSONObject json){
		String projectbaseid = json.get("projectbaseid")+"";
		List<Map> hi_list = dbHelper.getRows("select id from pms_projectbaseedit_data where projectbaseid = ? order by editnum desc",
				new Object[]{projectbaseid});
		if(hi_list!=null&&hi_list.size()>0){
			String id = hi_list.get(0).get("id")+"";
			PmsProjectbaseeditData data = this.findById(id);
			this.delete(data);
		}
	}


	/**
	 * 【合同填报流程】---获取所有修改记录
	 * @param projectbaseid
	 * @return
	 */
	public JSONArray initAllEditDataByPid(String projectbaseid){
		List<PmsProjectbaseeditData> list = this.findByHql("select c from cn.topcheer.pms2.api.project.entity.PmsProjectbaseeditData c" +
				" where c.projectbaseid=? order by c.editnum desc",new Object[]{projectbaseid});
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		if(!Util.isEoN(jsonArray)&&jsonArray.size()>0){
			for (int i = 0; i < jsonArray.size(); i++) {
				String editdataStr = "";
				editdataStr = jsonArray.getJSONObject(i).get("editdata")+"";
				if(!"".equals(editdataStr)){
					jsonArray.getJSONObject(i).put("changedata",JSONArray.fromObject(editdataStr));
				}else{
					jsonArray.getJSONObject(i).put("changedata",new JSONArray());
				}
				String affixchangeStr = "";
				affixchangeStr = jsonArray.getJSONObject(i).get("affixchange")+"";
				if(!"".equals(affixchangeStr)){
					jsonArray.getJSONObject(i).put("affixchange",JSONArray.fromObject(affixchangeStr));
				}else{
					jsonArray.getJSONObject(i).put("affixchange",new JSONArray());
				}
			}
		}
		return jsonArray;
	}



	/**
	 * 【修改记录】  --- 用户在申报编辑页面时，判断是否需要生成修改记录，查看页面是否显示修改记录
	 * @param projectbaseid
	 * @return
	 */
	public Boolean judgeSbEditFalg(String projectbaseid){
		//先判断其他配置表是否标记了 是否有修改记录标记
		PmsProjectbase p = this.pmsProjectbaseService.findById(projectbaseid);
		if(p==null){
			return false;//true表示需要显示修改记录，false表示不用显示修改记录
		}
//		PmsPlanprojectbatch pmsPlanprojectbatch = pmsPlanprojectbatchService.findById(p.getPlanprojectbatchid());
//		String xstheditflag = this.getOnlyValueBySql("select xstheditflag from sys_version_other where versionid = ?",new Object[]{pmsPlanprojectbatch.getVersionsbid()});
//		if(Util.isEoN(xstheditflag)||"0".equals(xstheditflag)){
//			//说明 没有修改记录
//			return false;//true表示需要显示修改记录，false表示不用显示修改记录
//		}else{
			//说明 有修改记录，判断是否是形审退回
			List<Map> recordList = dbHelper.getRows("select * from fl_flowrecord e where e.sourceid = ? order by e.operationtime desc",new Object[]{projectbaseid});
			//流转记录有退回的话，肯定要有2条
			if(recordList!=null&&recordList.size()>1){
				//判断第一条 是不是用户填写，并且意见是退回
				if(("项目申报:用户填报".equals(recordList.get(0).get("currentflowpointname")))&&("退回".equals(recordList.get(0).get("currentoperationresultname")))){
					//判断第二条 是不是 形审
					return true;//true表示需要显示修改记录，false表示不用显示修改记录
//					if("项目申报:形审".equals(recordList.get(1).get("currentflowpointname"))){
//						return true;//true表示需要显示修改记录，false表示不用显示修改记录
//					}else{
//						return false;//true表示需要显示修改记录，false表示不用显示修改记录
//					}
				}else{
					return false;//true表示需要显示修改记录，false表示不用显示修改记录
				}
			}else{
				return false;//true表示需要显示修改记录，false表示不用显示修改记录
			}
//		}
	}

}
