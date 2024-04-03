/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-6-11 11:33:04
 *
 */
package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * PmsProjectRecommendation 服务类
 */
@Service(value="PmsProjectRecommendationService")
public class PmsProjectRecommendationService extends GenericService<PmsProjectRecommendation> {

	public PmsProjectRecommendationDao getPmsProjectRecommendationDao() {
		return (PmsProjectRecommendationDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectRecommendationDao(PmsProjectRecommendationDao pmsProjectRecommendationDao) {

		this.setGenericDao(pmsProjectRecommendationDao);
	}
//	@Autowired
//	private SysOperationrecordService sysOperationRecordService;
//	@Autowired
//	private PmsPlanprojectbatchService pmsPlanprojectbatchService;
//	@Autowired
//	private PmsTxtSave pmsTxtSave;
//	@Autowired
//	private DBHelper dbHelper;
//
//	/*申报推荐函基本信息保存*/
//	public boolean saveRecommendation(HttpServletRequest request, SecurityUser sysuser, JSONObject jsonObject, String recommendationid, String planprojectbatchid) {
//		// 操作记录
//		SysOperationrecord sysOpera = new SysOperationrecord();
//		sysOpera.setNote("保存申报推荐函，该报告ID为--" + recommendationid + "");
//		sysOpera.setType("保存");
//		sysOperationRecordService.saveCurrentUserOperation(request, sysOpera);
//
//		PmsPlanprojectbatch plan = this.pmsPlanprojectbatchService.findById(planprojectbatchid);
//		PmsPlanproject planproject = plan.getPmsPlanproject();
//
//		String oldid = recommendationid;//如果前面没传id进来就new一个当成id
//
//
//		PmsProjectRecommendation recommendation = this.findById(oldid);
//		if(recommendation!=null){
//			Util.ApplyObject(recommendation,jsonObject);
//			recommendation.setId(oldid);
//			recommendation.setPlanprojectbatchid(planprojectbatchid);
//			recommendation.setPlanprojectbatch(plan.getName());
//			recommendation.setPlanproject(planproject.getProjectname());
//			recommendation.setPlanprojectid(planproject.getId());
//			recommendation.setMinicurrentstateid("1");
//			recommendation.setMinicurrentstate("申报推荐函：用户填报");
//			recommendation.setUpdatelasttime(new Date());
//			this.merge(recommendation);
//		}else{//id，但是数据库没有，就重新插入
//			recommendation = new PmsProjectRecommendation();
//			Util.ApplyObject(recommendation,jsonObject);
//			recommendation.setId(oldid);
//			recommendation.setPlanprojectbatchid(planprojectbatchid);
//			recommendation.setPlanprojectbatch(plan.getName());
//			recommendation.setPlanproject(planproject.getProjectname());
//			recommendation.setPlanprojectid(planproject.getId());
//			recommendation.setMinicurrentstateid("1");
//			recommendation.setMinicurrentstate("申报推荐函：用户填报");
//			recommendation.setUpdatelasttime(new Date());
//			this.merge(recommendation);
//		}
//		pmsTxtSave.saveTxt(recommendationid,jsonObject,"TecReport","saveReport");
//		return true;
//	}
//
//	/**
//	 * 【科技报告获取方法】
//	 * @param jsonObject
//	 * @param recommendationid
//	 * @return
//	 */
//	public JSONObject initAllRecommendation(HttpServletRequest request, JSONObject jsonObject, String recommendationid, String planprojectid){
//		jsonObject.keySet();
//		Iterator<String> sIterator = jsonObject.keys();
//		while(sIterator.hasNext()){
//			// 获得key
//			String key = sIterator.next();
//			// 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
//			JSONObject tableObject = jsonObject.getJSONObject(key);
//
//			if("pms_project_recommendation".equals(tableObject.get("database"))){//主表
//				List<Map> jbxxlist = this.findByRecommendationid(request, recommendationid, planprojectid);
//				JsonConfig jsonConfig = new JsonConfig();
//				jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
//				JSONArray jsonArray = JSONArray.fromObject(jbxxlist,jsonConfig);
//				tableObject.put("data",jsonArray.get(0));
//				jsonObject.put(key,tableObject);
//			}
//
//		}
//		return jsonObject;
//	}
//
//	public List<Map> findByRecommendationid(HttpServletRequest request, String recommendationid, String planprojectid){
//		String sql = "select t.id as recommendationid,t.* from pms_project_recommendation t where t.id = ?";
//		List<Map> list = this.getListBySql(sql, new Object[]{recommendationid});
//		if(list!=null&&list.size()>0){
//			return list;
//		}else{
//			SecurityUser sysuser = SessionUtil.getCurrentSecurityUser(request);//当前登录用户sysuser
//			SysUser user = sysuser.getUser();
//			String sqlU = "select u.id declarantid,u.name linkname,e.name casemanagement from sys_user u " +
//					"left join sys_identity i on u.id=i.userid left join sys_userinfo uu on uu.id=u.userinfoid " +
//					"left join sys_role r on r.id = i.roleid " +
//					"left join pms_enterprise e on e.id=i.purvieworganizeid  where r.id='2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487' and i.userid = ?";
//			List<Map> allList = dbHelper.getRows(sqlU,new Object[]{user.getId()});
//			Map allMap = allList.get(0);
//			PmsPlanprojectbatch plan = this.pmsPlanprojectbatchService.findById(planprojectid);
//			PmsPlanproject planproject = plan.getPmsPlanproject();
//			allMap.put("planprojectbatch",plan.getName());
//			allMap.put("planproject",planproject.getProjectname());
//			allMap.put("year",plan.getAnnually());
//			return allList;
//		}
//	}
//
//	public void reportRecommendation(String recommendationid){
//		PmsProjectRecommendation recommendation = this.findById(recommendationid);
//		recommendation.setMinicurrentstate("申报推荐函：上报成功");
//		recommendation.setMinicurrentstateid("2");
//		recommendation.setSubmittime(new Date());
//		this.merge(recommendation);
//	}

}
