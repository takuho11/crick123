/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-5-20 16:18:55
 *
 */
package cn.topcheer.pms2.biz.announcement.impl;

import cn.topcheer.halberd.app.api.utils.JsonDateValueProcessor;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.pms2.api.announcement.PmsAnnouncement;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.announcement.PmsAnnouncementDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * PmsAnnouncement 服务类
 */
@Service(value="PmsAnnouncementService")
public class PmsAnnouncementService extends GenericService<PmsAnnouncement> {

	@Autowired
	private SysOperationrecordService sysOperationrecordService;
	@Autowired
	private DBHelper dbHelper;

	public PmsAnnouncementDao getPmsAnnouncementDao() {
		return (PmsAnnouncementDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsAnnouncementDao(PmsAnnouncementDao pmsAnnouncementDao) {

		this.setGenericDao(pmsAnnouncementDao);
	}


	/**
	 * 【通知通告】---获取方法
	 * 获取type为「通知公告」的数据。针对图片新闻的base64暂未处理。
	 * @param json
	 * @return
	 */
	public JSONArray getData(JSONObject json){
		List paramList = new ArrayList();
		String inSql = "";
		String type = json.get("type")+"";
		inSql = inSql + " and e.type = ? ";
		paramList.add(type);
		if(json.has("system")&&"公示公告".equals(type)){
			String system = json.get("system")+"";
			inSql = inSql + " and e.system = ? ";
			paramList.add(system);
		}
		String sql = "select e.* from PMS_ANNOUNCEMENT e where 1=1 " + inSql + " order by e.savedate desc";
		List<Map> resList = dbHelper.getRows(sql, paramList.toArray());
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(resList,jsonConfig);
		return jsonArray;
	}



	/**
	 * 【通知通告】---新增修改方法
	 * @param request
	 * @param json
     */
	public void saveData(HttpServletRequest request, JSONObject json){
		String state = "";
		String id = json.get("id")+"";
		PmsAnnouncement pmsAnnouncement = this.findById(id);
		if(pmsAnnouncement==null){
			//说明是新增
			pmsAnnouncement = new PmsAnnouncement();
			pmsAnnouncement.setId(id);
			state = "新增"+json.get("type");
		}else{
			state = "修改"+json.get("type");
		}
		Util.ApplyObject(pmsAnnouncement,json);
		pmsAnnouncement.setSavedate(new Date());
		//图片新闻的图片需要特殊处理
//		if("图片新闻".equals(json.getString("type"))){
//			String imgsource = json.get("imgsource")+"";
//			if(!Util.isEoN(imgsource)){
//				try{
//					javax.sql.rowset.serial.SerialBlob blob = new SerialBlob(imgsource.getBytes("UTF-8"));//String 转 blob
////					SerializableBlobProxy proxy = (SerializableBlobProxy) Proxy.getInvocationHandler(blob);
////					java.sql.Blob realBlob = proxy.getWrappedBlob();
//					pmsAnnouncement.setImgsource(blob);
//				}catch (Exception e){
//					System.out.println("图片新闻保存失败");
//				}
//			}
//		}
		this.merge(pmsAnnouncement);
		//操作记录
		this.sysOperationrecordService.commonSaveOperation(request,id,state,state);
	}


	/**
	 * 【通知通告】---删除方法
	 * @param request
	 * @param json
     */
	public void deleteData(HttpServletRequest request, JSONObject json){
		String id = json.get("id")+"";
		PmsAnnouncement pmsAnnouncement = this.findById(id);
		String title = pmsAnnouncement.getTitle();
		String type = pmsAnnouncement.getType();
		this.deleteById(id);
		//操作记录
		this.sysOperationrecordService.commonSaveOperation(request,id,type,"删除了标题："+title);
	}

	/**
	 * 【通知通告】---发布方法
	 * @param request
	 * @param json
     */
	public void releaseData(HttpServletRequest request, JSONObject json){
		String id = json.get("id")+"";
		PmsAnnouncement pmsAnnouncement = this.findById(id);
		pmsAnnouncement.setReleasetime(new Date());
		pmsAnnouncement.setIsrelease("发布");
		this.merge(pmsAnnouncement);
		//操作记录
		this.sysOperationrecordService.commonSaveOperation(request,id,pmsAnnouncement.getType(),"发布了标题："+pmsAnnouncement.getTitle());
	}

	/**
	 * 【通知通告】---获取发布的数据
	 * @param json
     */
	public JSONArray getReleaseData(JSONObject json){
		List paramList = new ArrayList();
		String inSql = "and e.isshow='显示' and e.isrelease = '发布' and e.releasetime is not null";
		String type = json.get("type")+"";
		inSql = inSql + " and e.type = ? ";
		paramList.add(type);
		if(json.has("system")&&"公示公告".equals(type)){
			String system = json.get("system")+"";
			if(!"all".equals(system)){
				inSql = inSql + " and e.system = ? ";
				paramList.add(system);
			}
		}
		String hql = "select e from PmsAnnouncement e where 1=1 "+ inSql +" order by e.releasetime desc";
		Query hqlQuery = this.getPmsAnnouncementDao().getQuery(hql);
		if(paramList.size()>0){
			for (int i = 0; i <paramList.size() ; i++) {
				hqlQuery.setParameter(i,paramList.get(i)+"");
			}
		}
		//判断取最新5条、10条还是全部
		if(json.has("getTen")){
			hqlQuery.setFirstResult(0);
			hqlQuery.setMaxResults(10);
		}
		if(json.has("getFive")){
			hqlQuery.setFirstResult(0);
			hqlQuery.setMaxResults(5);
		}
		if(json.has("getThree")){
			hqlQuery.setFirstResult(0);
			hqlQuery.setMaxResults(3);
		}
		if("图片新闻".equals(type)){
			if(!json.has("getAllImg")){
				hqlQuery.setFirstResult(0);
				hqlQuery.setMaxResults(3);
			}
		}
		List list = hqlQuery.list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		return jsonArray;
	}




	/**
	 * 【通知通告】---获取发布的数据  分页
	 * @param json
	 */
	public JSONObject getReleaseDataByPage(JSONObject json){
		JSONObject resJson = new JSONObject();
		List paramList = new ArrayList();
		String inSql = "and e.isshow='显示' and e.isrelease = '发布' and e.releasetime is not null";
		String type = json.get("type")+"";
		inSql = inSql + " and e.type = ? ";
		paramList.add(type);
		if(json.has("system")&&"公示公告".equals(type)){
			String system = json.get("system")+"";
			if(!"all".equals(system)){
				inSql = inSql + " and e.system = ? ";
				paramList.add(system);
			}
		}
		String searchSql = "";
		if(json.has("searchContent")){
			searchSql = " and e.title like '%" + json.get("searchContent").toString() + "%'";
		}
		//总数
		List<Map> rows = dbHelper.getRows("select count(*) as count from pms_announcement e where 1=1 " + inSql + searchSql, paramList.toArray());
		String count = rows.get(0).get("count").toString();
//		String totalcount = this.getOnlyValueBySql("select count(*) from pms_announcement e where 1=1 "+ inSql + searchSql,paramList.toArray());
		if(count==null){
			count = "0";
		}
		HqlBuilder<PmsAnnouncement> builder = HqlBuilder.builder();
		builder.eq("1",1)
				.eq("isshow","显示")
				.eq("isrelease","发布")
				.isNotNull("releasetime")
				.eq("type",json.get("type")+"")
				.eq(json.has("system")&&"公示公告".equals(type),"system",json.get("system")+"")
				.addOrder("releasetime","desc");
		if (json.has("searchContent")){
			builder.like(PmsAnnouncement::getTitle, json.get("searchContent").toString());
		}
		int page = Integer.parseInt(json.get("page")+"");
		int setcount = Integer.parseInt(json.get("setcount")+"");
//		int startPage = (page-1)*setcount;
		Page page1 = new Page(page, setcount);
		List<PmsAnnouncement> list = getPmsAnnouncementDao().getPaginationDao(page1,builder);

//		String hql = "select e from PmsAnnouncement e where 1=1 "+ inSql + searchSql +" order by e.releasetime desc";
//		Query hqlQuery = this.getPmsAnnouncementDao().getQuery(hql);
//		if(paramList.size()>0){
//			for (int i = 0; i <paramList.size() ; i++) {
//				hqlQuery.setParameter(i,paramList.get(i)+"");
//			}
//		}
//
//		hqlQuery.setFirstResult(startPage);
//		hqlQuery.setMaxResults(setcount);
//		List list = hqlQuery.list();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);


		resJson.put("data",jsonArray);
		resJson.put("totalcount",Integer.parseInt(count));
		return resJson;
	}


	/**
	 * 【通知通告】---根据id获取内容
	 * @param id
	 * @return
     */
	public JSONObject getDataById(String id){
		PmsAnnouncement pmsAnnouncement = this.findById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		List<PmsAnnouncement> list = new ArrayList<PmsAnnouncement>();
		list.add(pmsAnnouncement);
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		JSONObject resJson = jsonArray.getJSONObject(0);
		return resJson;
	}

	/**
	 * 【通知通告】---根据id获取附件
	 * @param id
	 * @return
     */
	public List<Map> getFjList(String id){
		List<Map> list = dbHelper.getRows("select e.id as fjid,e.name as fjname from pms_attachment e where e.sourceid = ? and e.category = '发布附件' order by e.createtime",
				new Object[]{id});
		return list;
	}

	public List selectUserByNameAndOffice(JSONObject select){
		List result =new ArrayList();
		String typeSql=" and 1 =1 ";
		String titleSql=" and 1 =1 ";
		String userSql=" and 1 =1 ";
		String timeSql=" and 1 =1 ";
		if (select.has("announcementtype") && !select.get("announcementtype").equals("")){
			String announcementtype = (String) select.get("announcementtype");
			typeSql =" and type like '%"+announcementtype+"%'";
		}
		if (select.has("title") ){
			String title = (String) select.get("title");
			titleSql =" and title like '%"+title+"%'";
		}
		if (select.has("releaseuser") ){
			String releaseuser = (String) select.get("releaseuser");
			userSql =" and releaseuser like '%"+releaseuser+"%'";
		}
		if (select.has("releasetime") ){
			String releasetime = (String) select.get("releasetime");
			timeSql =" and releasetime like '%"+releasetime+"%' ";
		}
		String sql ="select id ,title , isshow , isrelease , releaseuser , to_char(releasetime,'yyyy-mm-dd ') as releasetime " +
				" from PMS_ANNOUNCEMENT " +
				" where 1 = 1" +typeSql+titleSql+userSql+timeSql;
		result= getListBySql(sql, null);
		return result;
	}

	public boolean updateAnnouncement(JSONObject select) throws SQLException {
		List result =new ArrayList();
		if (select!=null){
			String id = (String) select.get("id");
			String isshow = (String) select.get("isshow");
			String isrelease = (String) select.get("isrelease");
			String sql ="update PMS_ANNOUNCEMENT set" +
					" isshow='"+isshow+"', ISRELEASE='"+isrelease+"' " +
					"where ID='"+id+"'";
			boolean b = dbHelper.runSql(sql);
			return b;
		}else {
			return false;
		}
	}

	public boolean checkAll(){
		int count=0;
		String sql1 ="select * from RYXXB";
		List<Map> list1 = dbHelper.getRows(sql1, null);
		for (Map map : list1) {
			String RYID =map.get("id")+ "";
			String sql3 = "select DWNAME from RYCBXXB where RYID = "+RYID;
			String value = dbHelper.getValue(sql3, "DWNAME", null);
			if (value.equals(map.get("unit"))){
				continue;
			}else {
				count++;
			}
		}
		if(count>0){
			return false;
		}
		return true;
	}

	public boolean checkOne(String ID) throws SQLException {
		int count=0;
		Map map = dbHelper.getRows("select * from RYXXB where ID =" + ID, null).get(0);
		String RYID =map.get("id")+ "";
		String sql3 = "select DWNAME from RYCBXXB where RYID = "+RYID;
		String value = dbHelper.getValue(sql3, "DWNAME", null);
		if (value.equals(map.get("unit"))){
		}else {
			String sql4 ="insert into SZDWYCRY VALUES('"+ UUID.randomUUID()+"','"+ID+"','"+map.get("name")+"','"+2+"','"+
					map.get("unit")+"','"+4+"','"+value+"')";
			dbHelper.runSql(sql4);
			count++;
		}
		if(count>0){
			return false;
		}
		return true;
	}
}
