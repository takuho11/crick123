/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2016-2-17 9:57:57
 *
 */
package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.plan.entity.PmsPlanproject;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.dao.plan.mapper.PmsPlanprojectMapper;
import cn.topcheer.pms2.dao.plan.PmsPlanprojectDao;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * PmsPlanproject 服务类
 */
@Service(value="PmsPlanprojectService")
public class PmsPlanprojectService extends GenericService<PmsPlanproject> {

//	@Autowired
//	private DBHelper dbHelper;
//
//	@Autowired
//	private PageService pageService;
//
//	@Autowired
//	private PmsPlanprojectbatchService pmsPlanprojectbatchService;
//	@Autowired
//	private SysOperationrecordService sysOperationrecordService;
	@Autowired
	private SysUserService sysUserService;


	@Resource
	private PmsPlanprojectMapper pmsPlanprojectMapper;

	public PmsPlanprojectDao getPmsPlanprojectDao() {
		return (PmsPlanprojectDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsPlanprojectDao(PmsPlanprojectDao pmsPlanprojectDao) {

		this.setGenericDao(pmsPlanprojectDao);
	}

//	//获取所有小批次数据源
//	public Page<Map> getSubBatchData(JSONObject json){
//		//参数集合
//		List paramList = new ArrayList<>();
//		//判断是否有搜索条件
//		String insql = "";
//		if(json.has("searchContent")){
//			insql = " and (e.name like ?  )";
//			paramList.add("%"+json.get("searchContent")+"%");
//		}
//		//sql语句
//		String sql = "select e.*,b.projectname," +
//				" (case when e.currentstate = '2' then '是' else '否' end) as currentstate_as," +
//				" (case when e.appliylimit = '1' then '是' else '否' end) as appliylimit_as," +
//				" (case when e.sblimitflag = '1' then '能' else '否' end) as sblimitflag_as," +
//				" (case when e.overage = '1' then '是' else '否' end) as overage_as," +
//				" (case when e.iscanhtchange = '1' then '是' else '否' end) as iscanhtchange_as," +
//				" (case when e.shlimitflag = '1' then '能' else '否' end) as shlimitflag_as" +
//				" from pms_planprojectbatch e left join pms_planproject b on e.planprojectid = b.id " +
//				" where 1=1	"+insql ;
//		//分页配置
//		JSONObject pageConfig = json.getJSONObject("pageConfig");
//		//分页处理
//		Page<Map> page = this.pageService.findPageBySql(sql,paramList,pageConfig);
//		return page;
//	}
//
//
//	/**
//	 * 【小批次新增和修改方法】
//	 * @param request
//	 * @param json
//	 */
//	public void addAndEditSubBatch(HttpServletRequest request, JSONObject json){
//		String type = "";//新增或者修改
//		PmsPlanprojectbatch pmsPlanprojectbatch = new PmsPlanprojectbatch();
//		if(json.has("id")){
//			//说明是修改
//			pmsPlanprojectbatch = pmsPlanprojectbatchService.findById(json.get("id")+"");
//			type = "小批次修改";
//		}else{
//			//说明是新增
//			pmsPlanprojectbatch.setId(Util.NewGuid());
//			type = "小批次新增";
//		}
//		Util.ApplyObject(pmsPlanprojectbatch,json);
//		//保存小批次中的大批次
//		PmsPlanproject pmsPlanproject = new PmsPlanproject();
//		pmsPlanproject = this.findById(json.get("planprojectid")+"");
//		pmsPlanprojectbatch.setPmsPlanproject(pmsPlanproject);
//		//特殊字段处理
//		//是否启用（页面必填，是：后台存2，否：后台存0）
//		if("是".equals(json.get("currentstate_as")+"")){
//			pmsPlanprojectbatch.setCurrentstate("2");
//		}else{
//			pmsPlanprojectbatch.setCurrentstate("0");
//		}
//		//是否申报限额
//		if("是".equals(json.get("appliylimit_as")+"")){
//			pmsPlanprojectbatch.setAppliyLimit(true);
//		}else{
//			pmsPlanprojectbatch.setAppliyLimit(false);
//		}
//		//是否允许超龄用户申报
//		if("是".equals(json.get("overage_as")+"")){
//			pmsPlanprojectbatch.setOverage("1");
//		}else{
//			pmsPlanprojectbatch.setOverage(null);
//		}
//		//是否允许合同变更
//		if("是".equals(json.get("iscanhtchange_as")+"")){
//			pmsPlanprojectbatch.setIscanhtchange("1");
//		}else{
//			pmsPlanprojectbatch.setIscanhtchange(null);
//		}
//
//		//默认值：
//		pmsPlanprojectbatch.setPdfurlforbase("D:/PdfTmpFile/PmsProjectbase");
//		pmsPlanprojectbatch.setPdfurlforcontract("D:/PdfTmpFile/CrtContract");
//		pmsPlanprojectbatch.setPdfdowntypeforbase("bookmark");
//		pmsPlanprojectbatch.setPdfdowntypeforcontract("bookmark");
//		//保存或修改
//		pmsPlanprojectbatchService.merge(pmsPlanprojectbatch);
//	}
//
//
//
//	//获取所有大批次数据源
//	public Page<Map> getMajorBatchData(JSONObject json){
//		//参数集合
//		List paramList = new ArrayList<>();
//		//判断是否有搜索条件
//		String insql = "";
//		if(json.has("searchContent")){
//			insql = " and (e.projectname like ?  )";
//			paramList.add("%"+json.get("searchContent")+"%");
//		}
//		//sql语句
//		String sql = "select e.*" +
//				" from pms_planproject e " +
//				" where 1=1	"+insql +" order by e.system,e.applicationtypename1,e.seq";
//		//分页配置
//		JSONObject pageConfig = json.getJSONObject("pageConfig");
//		//分页处理
//		Page<Map> page = this.pageService.findPageBySql(sql,paramList,pageConfig);
//		return page;
//	}
//
//	/**
//	 * 【大批次新增和修改方法】
//	 * @param request
//	 * @param json
//	 */
//	public void addAndEditMajorBatch(HttpServletRequest request, JSONObject json){
//		String type = "";//新增或者修改
//		PmsPlanproject pmsPlanproject = new PmsPlanproject();
//		if(json.has("id")){
//			//说明是修改
//			pmsPlanproject = this.findById(json.get("id")+"");
//			type = "大批次修改";
//		}else{
//			//说明是新增
//			pmsPlanproject.setId(Util.NewGuid());
//			type = "大批次新增";
//		}
//		Util.ApplyObject(pmsPlanproject,json);
//		//默认值：
////		pmsPlanproject.setIsonlineassign(1);
//		pmsPlanproject.setState(1);
//		//保存或修改
//		this.merge(pmsPlanproject);
//	}
//
//	/**
//	 *  【获取所有大批次】
//	 * @return
//	 */
//	public List<Map> getAllBigBatch(){
//		List<Map> list = this.getListBySql("select t.id as bigbatchid,t.projectname as bigbatchname,t.* from Pms_Planproject t",new Object[]{});
//		return list;
//	}
//
//	/**
//	 *  【获取所有小批次】
//	 * @return
//	 */
//	public List<Map> getAllBatch(){
//		List<Map> list = this.getListBySql("select t.id as batchid,t.name as batchname,t.*,pp.id as bigbatchid,pp.projectname as bigbatchname" +
//				" from Pms_Planprojectbatch t left join pms_planproject pp on t.planprojectid = pp.id",new Object[]{});
//		return list;
//	}
//
//	/**
//	 *  通过小批次id获取大批次id
//	 * @param batchid
//	 * @return
//     */
//	public String getBigbatchidBybatchid(String batchid){
//		return this.getOnlyValueBySql("select planprojectid from pms_planprojectbatch where id = ?",new Object[]{batchid});
//	}
//
//	/**
//	 *  【产品化整体配置】-- 根据大批次类型来获取大批次数据
//	 * @param system
//	 * @return
//     */
//	public List<Map> getBigbatchBySystem(String system){
//		return this.getListBySql("select tt.*,rownum as xh from (" +
//				" select e.*,d.name as departmentname,e.projectname as name from pms_planproject e " +
//				" left join pms_department d on e.departmentid = d.id  " +
//				" where e.system = ? order by e.seq) tt",new Object[]{system});
//	}
//
//	/**
//	 * 【产品化整体配置】-- 新增或修改大批次
//	 * @param request
//	 * @param json
//	 */
//	public void addAndEditBigbatch(HttpServletRequest request, JSONObject json){
//		String type = "";//新增或者修改
//		PmsPlanproject pmsPlanproject = this.findById(json.get("id")+"");
//		if(!Util.isEoN(pmsPlanproject)){
//			//说明是修改
//			type = "大批次修改";
//		}else{
//			//说明是新增
//			pmsPlanproject = new PmsPlanproject();
//			type = "大批次新增";
//		}
//		Util.ApplyObject(pmsPlanproject,json);
//		//默认值（暂无用）：
////		pmsPlanproject.setIsonlineassign(1);
//		pmsPlanproject.setState(1);
//		//特殊字段处理：
//		//默认首页显示处室与责任处室相同，有特殊情况先自行改写Home_departmentid，这种太特殊了...
//		if(!Util.isEoN(pmsPlanproject.getDepartmentid())){
//			pmsPlanproject.setHome_departmentid(pmsPlanproject.getDepartmentid());
//		}
//		//保存或修改
//		this.merge(pmsPlanproject);
//		//操作记录
//		this.sysOperationrecordService.commonSaveOperation(request,json.get("id")+"",type,"本次保存的大批次数据："+json.toString());
//	}
//
//	/**
//	 * 【产品化整体配置】-- 删除大批次
//	 * @param request
//	 * @param id
//     * @return
//     */
//	public JSONObject deleteBigbatch(HttpServletRequest request, String id){
//		JSONObject resJson = new JSONObject();
//		//判断大批次下是否有小批次
//		List<Map> batchList = this.getListBySql("select e.id from pms_planprojectbatch e where e.planprojectid = ?",new Object[]{id});
//		if(batchList.size()>0){
//			resJson.put("success",false);
//			resJson.put("reason","当前大批次下有存在小批次，无法删除。");
//		}else{
//			resJson.put("success",true);
//			PmsPlanproject p = this.findById(id);
//			this.delete(p);
//			//操作记录
//			this.sysOperationrecordService.commonSaveOperation(request,id,"大批次删除","本次删除的大批次数据："+JSONObject.fromObject(p).toString());
//		}
//		return resJson;
//	}
//
	/**
	 * 【产品化整体配置】-- 获取大批次名称
	 * @param bigbatchid
	 * @return
     */
	public String getBigbatchName(String bigbatchid){
		PmsPlanproject p = this.findById(bigbatchid);
		return p.getProjectname();
	}

	/**
	 * 返回大批次以及对应小批次数据
	 */
	public List<Map<String,String>> selectByCon(String system, String annually, String bigBatchId) {
		String userId = AuthUtil.getUserId();
		SysUser user = sysUserService.getById(userId);
		List<Map<String, String>> list = pmsPlanprojectMapper.selectByCon(system, annually, bigBatchId).stream().filter(map -> {
//			if (map != null
//					&& map.get("ORGNIZATION_ID").startsWith(user.getSysOrganization().getId())
//					&& map.get("AGENCY_ID").startsWith(user.getEnterPriseId())) {
//				return true;
//			}
			return true;
		}).collect(Collectors.toList());
		return list;

	}

//
//
//	/**
//	 * 根据小批次id处理小批次表的所属领域
//	 */
//	public List<String> dealBelongcategory(JSONObject json){
//		String batchids = json.get("batchids")+"";
//		List<String> resList = new ArrayList<>();
//		if(Util.isEoN(batchids)||batchids.contains(",")){
//			//为空，或者存在多个小批次，都不允许获取所属领域
//			return resList;
//		}
//		//=================获取所属领域字符串=================
//		List<Map> list = this.getListBySql("select e.belongcategory " +
//				" from pms_planprojectbatch e where e.belongcategory is not null and e.id = ?",new Object[]{batchids});
//		String belongcategoryStr = "";
//		for(Map m : list){
//			belongcategoryStr = belongcategoryStr + m.get("belongcategory")+";";
//		}
//		if(Util.isEoN(belongcategoryStr)){
//			return resList;
//		}
//		belongcategoryStr = belongcategoryStr.substring(0,belongcategoryStr.length()-1);
//		//=================处理成数组=================
//		resList =  Arrays.asList(belongcategoryStr.split(";"));
//		return resList.stream().distinct().collect(Collectors.toList());//去重处理
//	}
	
}