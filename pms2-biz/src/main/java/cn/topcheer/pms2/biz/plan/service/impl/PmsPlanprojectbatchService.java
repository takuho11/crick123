package cn.topcheer.pms2.biz.plan.service.impl;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.biz.project.utils.ApplicationNoUtil;
import cn.topcheer.pms2.dao.plan.PmsPlanprojectbatchDao;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "PmsPlanprojectbatchService")
public class PmsPlanprojectbatchService extends GenericService<PmsPlanprojectbatch> {
    @Autowired
    private DBHelper dbHelper;
//
//	@Autowired
//	private PmsEnterpriseServiceImpl pmsEnterpriseService;
//
//	@Autowired
//	private PmsProjectbaseService pmsProjectbaseService;
//
//	@Autowired
//	private SysParamvalueService sysParamvalueService;
//	@Autowired
//	private PmsRewardService pmsRewardService;
//	@Autowired
//	private SysOperationrecordService sysOperationrecordService;
//	@Autowired
//	private BatchVersionService batchVersionService;
//	@Autowired
//	private PmsSubsidyService subsidyService;

    public PmsPlanprojectbatchDao getPmsPlanprojectbatch() {
        return (PmsPlanprojectbatchDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPlanprojectbatch(
            PmsPlanprojectbatchDao pmsPlanprojectbatch) {
        this.setGenericDao(pmsPlanprojectbatch);
    }


    @Autowired
    private SysUserServiceImpl sysUserService;


    public String getApplicationNo(String batchId, String userId) {
        // 查询小批次
        PmsPlanprojectbatch planprojectbatch = this.findById(batchId);
        if (planprojectbatch == null) {
            return "";
        }

        // 查询用户
        SysUser sysUser = sysUserService.findById(userId);
        if (sysUser == null) {
            return "";
        }

        String year = planprojectbatch.getAnnually();
        String batchname = planprojectbatch.getName();
        String numberarithmetic = planprojectbatch.getNumberarithmetic();
        String applicationcode = planprojectbatch.getPmsPlanproject() != null ? planprojectbatch.getPmsPlanproject().getApplicationtypecode1() : "";
        String uniformCode = sysUser.getUniformcode();

        // 获取申请编号
        return ApplicationNoUtil.getApplicationNo(year, uniformCode, applicationcode, batchname, numberarithmetic);
    }


//	@Autowired
//	DBHelper dbHelper;
//	@Autowired
//	DBHelperBusiness dbHelperBusiness;
//	@Autowired
//	private CrtContractJbxxService crtContractJbxxService;
//	@Autowired
//	private PmsInnovationbaseService pmsInnovationbaseService;
//	@Autowired
//	private cn.topcheer.pms.service.PmsPlanprojectService pmsPlanprojectService;
//	@Autowired
//	private TransOrgainService transOrgainService;
//	@Autowired
//	private TransAppraisalService transAppraisalService;
//	@Autowired
//	private TransTechmanagerService transTechmanagerService;
//	@Autowired
//	private SysVersionAffixService sysVersionAffixService;
//	@Autowired
//	private PmsEnterpriselimitService pmsEnterpriselimitService;
//
//	public List<PmsPlanprojectbatch> findCountByBatchId(String batchid) {
//		String hql = "select count(planprojectbatchid) from cn.topcheer.pms.pojo.PmsProjectbase projectbase where projectbase.planprojectbatchid = ?";
//		// 默认获取前50条数据
//		return this.getPmsPlanprojectbatch().findByHql(hql,
//                batchid);
//	}
//
//	// 找到所有在申报的批次
//	public List<PmsPlanprojectbatch> findEnablePlanprojectbatch() {
//		JSONObject jsonExample = new JSONObject();
//		jsonExample.put("currentstate", "0");
//		return this.getPmsPlanprojectbatch().findByExample(jsonExample);
//	}
//
//	// 找到检索条件且所有在申报的批次
//	public List<PmsPlanprojectbatch> findRetrievalPlanprojectbatch(
//			String applicationtypecode1) {
//		JSONObject jsonExample = new JSONObject();
//		if (!"".equals(applicationtypecode1)) {
//			jsonExample.put("applicationtypecode1", applicationtypecode1);
//		}
//		jsonExample.put("currentstate", "0");
//		jsonExample.put("isdisplay", "0");
//		return this.getPmsPlanprojectbatch().findByExample(jsonExample);
//	}
//	// 找到检索条件且所有在申报的批次
//	public List<PmsPlanprojectbatch> findRetrievalPlanprojectbatch(
//			String applicationtypecode1,JSONObject json) {
//		JSONObject jsonExample = new JSONObject();
//		if (!"".equals(applicationtypecode1)) {
//			jsonExample.put("applicationtypecode1", applicationtypecode1);
//		}
//		jsonExample.put("currentstate", "0");
//		jsonExample.put("isdisplay", "0");
//		if(!Util.isEoN(json.get("city")))
//			jsonExample.put("city", json.get("city"));
//		else
//			jsonExample.put("city", "省级");
//		if(!Util.isEoN(json.get("county")))
//			jsonExample.put("county", json.get("county"));
//		return this.getPmsPlanprojectbatch().findByExample(jsonExample);
//	}
//
//	// 找到该批次内的所有项目
//	public List<PmsProjectbase> getPmsprojectbaseByBatch(String batchid) {
//		String hql = "select base from cn.topcheer.pms.pojo.PmsProjectbase base "
//				+ " left join base.pmsPlanprojectbatch batch "
//				+ " where batch.id = ?";
//		return pmsProjectbaseService.findByHql(hql, batchid);
//	}
//
//	// 联审准备批次
//	public List<PmsPlanprojectbatch> findGoingPlanprojectbatch(
//			String prepareState) {
//		String hql = "";
//		if (prepareState == null || "null".equals(prepareState)) {
//			hql = "select batch from cn.topcheer.pms.pojo.PmsPlanprojectbatch batch order by starttime desc";
//			return this.getPmsPlanprojectbatch().findByHql(hql, null);
//		} else {
//			hql = "select batch from cn.topcheer.pms.pojo.PmsPlanprojectbatch batch "
//					+ " where batch.isstartUniteApprove = ? order by starttime desc";
//			return this.getPmsPlanprojectbatch().findByHql(hql, prepareState);
//		}
//	}
//
//	public List<PmsPlanprojectbatch> findEnablePlanprojectbatchByUser_old(
//			SecurityUser SecuUser) {
//		// TODO Auto-generated method stub
//		String enterprisHql = "select en from cn.topcheer.pms.pojo.PmsEnterprise en"
//				+ " left join en.sysIdentitys sysIde"
//				+ " left join sysIde.sysUser u "
//				+ " left join sysIde.sysRole role"
//				+ " where u.id = ? and role.id!=?";
//		List<PmsEnterprise> enList = this.pmsEnterpriseService.findByHql(
//				enterprisHql, SecuUser.getId(),
//				"2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487");
//		if (enList.size() < 1)
//			return null;
//		List<PmsPlanprojectbatch> batchList = new ArrayList<PmsPlanprojectbatch>();
//		for (PmsEnterprise en : enList) {
//			String hql = "select batch from cn.topcheer.pms.pojo.PmsPlanprojectbatch batch"
//					+ " where batch.openuser like ? and currentstate = '0'";
//			if (en.getDepartmentcode() == null)
//				continue;
//			List<PmsPlanprojectbatch> tembatchList = this
//					.getPmsPlanprojectbatch().findByHql(hql,
//							"%" + en.getDepartmentcode() + "%");
//			for (PmsPlanprojectbatch batch : tembatchList) {
//				if (!batchList.contains(batch))
//					batchList.add(batch);
//			}
//		}
//		return batchList;
//	}
//	public List<Map> findEnablePlanprojectbatchByUser(
//			SecurityUser SecuUser) {
//		String enterprisSql = "select distinct(e.departmentcode) from pms_enterprise e"
//				+ " left join sys_identity i on i.purvieworganizeid=e.id"
//				+ " left join sys_user u on u.id=i.userid"
//				+ " left join sys_role r on r.id=i.roleid"
//				+ " where u.id=? and r.id!=?";
//		String departmentcode = dbHelper.getOnlyStringValue(enterprisSql, new Object[]{SecuUser.getId(),"2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487"});
//		if (departmentcode==null||"".equals(departmentcode)){
//			return null;
//		}else{
//			String sql = "select p.applicationtypename1,p.applicationtypename2,t.seq,t.numberarithmetic,t.id,t.isdisplay,t.annually,t.applicationtableexplain,"
//					+ " t.currentstate,t.endtime,t.explain,t.issignonline,t.name,t.openuser,t.starttime,t.memo,t.workflowdefineid,t.city,t.county from pms_planprojectbatch t"
//					+ " left join pms_planproject p on p.id =t.planprojectid where t.openuser like ? and t.currentstate = '0' and t.endtime>=sysdate-1";
//			List<Map> tembatchList = dbHelper.getRows(sql,new Object[]{"%" + departmentcode + "%"});
//			return tembatchList;
//		}
//	}
//
//	// 项目id获取批次id
//	public PmsPlanprojectbatch findPlanprojectbatchByProjectid(String projectid) {
//		// TODO Auto-generated method stub
//		String enterprisHql = "select en from cn.topcheer.pms.pojo.PmsPlanprojectbatch en"
//				+ " left join en.pmsProjectbases base" + " where base.id = ?";
//		List<PmsPlanprojectbatch> enList = this.getPmsPlanprojectbatch()
//				.findByHql(enterprisHql, projectid);
//		if (enList.size() > 0)
//			return enList.get(0);
//		else
//			return null;
//	}
//	// 项目id获取批次id-新
//	public PmsPlanprojectbatch findPlanprojectbatchByProjectid2(String projectid) {
//		// TODO Auto-generated method stub
//		String enterprisHql = "select en from cn.topcheer.pms.pojo.PmsPlanprojectbatch en"
//		        + " where en.id = (select base.planprojectbatchid2 from cn.topcheer.pms.pojo.PmsProjectbase base where base.id=? )";
//		List<PmsPlanprojectbatch> enList = this.getPmsPlanprojectbatch()
//				.findByHql(enterprisHql, projectid);
//		if (enList.size() > 0)
//			return enList.get(0);
//		else
//			return null;
//	}
//
//	// 项目id获取批次id
//	public List<Map> findAllPlanprojectbatchByApprove(SysUser sysUser,String type) {
//		String departmentid = this.dbHelper.getOnlyStringValue("select i.departmentid from sys_user u left join sys_identity i on u.id = i.userid where u.id = ? and rownum <2",new Object[]{sysUser.getId()});
//		if((departmentid!=null&&departmentid.length()>0)||sysUser.getUserid().equals("chakanps")){
//			if("wp".equals(type)){
//				type = "网评";
//			}else if("hp".equals(type)){
//				type = "会评";
//			}else if("xc".equals(type)){
//				type = "现场评审";
//			}
//			String hasRole = "select id,roleid from sys_identity ide where userid = ? ";
//			List<Map> rolesLists = dbHelper.getRows(hasRole, new Object[]{sysUser.getId()});
//			StringBuilder currRoles=new StringBuilder();
//			String currUserId = sysUser.getUserid();
//			//rolesLists.stream()
//			if(rolesLists!=null&&rolesLists.size()>0){
//				for(Map map1 :rolesLists){
//					currRoles.append(map1.get("roleid")+"@");
//				}
//				if(currRoles.indexOf("@")>-1){
//					currRoles.deleteCharAt(currRoles.length()-1);
//				}
//			}
//
//			AtomicBoolean qxFlag = new AtomicBoolean(false);
//			String[] pbwpRoleIds = this.sysParamvalueService.getPbwpRoleIds();
//			Optional.ofNullable(pbwpRoleIds).ifPresent(obj->{
//				Arrays.asList(pbwpRoleIds).stream().filter(o->currRoles.indexOf(o)>-1).findAny().ifPresent(o->{
//					qxFlag.set(true);
//				});
//			});
//
//			Optional.ofNullable(currUserId).filter(o->o.equals("chakanps")).ifPresent(o->{
//				qxFlag.set(true);
//			});
//
////			if(Optional.ofNullable(pbwpRoleIds).isPresent()){
////				for(String s:pbwpRoleIds){
////					if(currRoles.indexOf(s)>-1){
////						qxFlag.set(true);
////					}
////				}
////			}
//
//			if(qxFlag.get()){
//				String sql = "select en.xingshenUsers,en.readscripts,en.limitscripts,en.isdisplay,en.numberarithmetic,en.id,en.annually,en.nocheck,"
//						+ " en.applicationtableexplain,en.currentstate,endtime,en.explain,en.issignonline,en.name,en.openuser,en.starttime,"
//						+ " en.memo,en.workflowdefineid,en.applicationtypecode1,en.applicationtypecode2,en.admindepartmentid,en.psresultmethod,"
//						+ " en.enterpriseParentIdex,en.seq,en.modelName,en.status,en.status,en.pingshenstarttime,en.pingshenendtime,en.avoidtype "
//						+ " from pms_planproject_review pr inner join  pms_planprojectbatch en on  pr.id = en.batchreviewid  left join zjk_batch_plan z on z.projectbatchid = en.id "
//						+ " where z.type = ?  order by en.seq";
//				//System.out.println(sql);
//				return dbHelper.getRows(sql,new Object[]{type});
//			}else{
//// TODO Auto-generated method stub
//				String sql = "select en.xingshenUsers,en.readscripts,en.limitscripts,en.isdisplay,en.numberarithmetic,en.id,en.annually,en.nocheck,"
//						+ " en.applicationtableexplain,en.currentstate,endtime,en.explain,en.issignonline,en.name,en.openuser,en.starttime,"
//						+ " en.memo,en.workflowdefineid,en.applicationtypecode1,en.applicationtypecode2,en.admindepartmentid,en.psresultmethod,"
//						+ " en.enterpriseParentIdex,en.seq,en.modelName,en.status,en.xcstatus,en.pingshenstarttime,en.pingshenendtime,en.avoidtype "
//						+ " from pms_planproject_review pr inner join  pms_planprojectbatch en on  pr.id = en.batchreviewid  left join zjk_batch_plan z on z.projectbatchid = en.id "
//						+ " where z.type = ? and pr.departmentid = ? order by en.seq";
//				//System.out.println(sql);
//				return dbHelper.getRows(sql,new Object[]{type,departmentid});
//			}
//
//		}else{
//			return null;
//		}
//
//	}
//
//
//
//	// 批次状态获取批次id
//	public List<Map> findAllPlanprojectbatchByStatus(
//			String status) {
//		// TODO Auto-generated method stub
//		String sql = "select xingshenUsers,readscripts,limitscripts,isdisplay,numberarithmetic,id,annually," +
//				"applicationtableexplain,currentstate,endtime,explain,issignonline,name,openuser,starttime," +
//				"memo,workflowdefineid,applicationtypecode1,applicationtypecode2,admindepartmentid," +
//				"enterpriseParentIdex,seq,modelName,status,pingshenstarttime,pingshenendtime" +
//				" from pms_planprojectbatch en where en.id=? order by en.seq";
//		return dbHelper.getRows(sql,new Object[]{status});
//	}
//
//
//
//	// 项目id获取批次id
//	public List<PmsPlanprojectbatch> findAllshijiPlanprojectbatchByApprove(
//			String userid) {
//		// TODO Auto-generated method stub
//		String enterprisHql = "select en from cn.topcheer.pms.pojo.PmsPlanprojectbatch en"
//				+ " where en.broseruser like ?  order by en.seq";
//		List<PmsPlanprojectbatch> enList = this.getPmsPlanprojectbatch()
//				.findByHql(enterprisHql,"%"+userid+"%");
//		return enList;
//	}
//
//	// 获取评审说明
//	public JSONObject getPSDisction(String eid) {
//		String sql = "select batch.REVIEWEXPLAIN as revExplanin,batch.UNDERTAKING as underTaking from Pms_Planprojectbatch batch "
//				+ " left join rev_expertinteam base on base.bid = batch.id "
//				+ " where base.eid = ?";
//		List<Map> lists = dbHelper.getRows(sql, new Object[]{eid});
//		JSONObject obj = new JSONObject();
//		if (lists != null && lists.size() > 0) {
//			Object objValue = lists.get(0).get("revexplanin");
//			Object objValue1 = lists.get(0).get("undertaking");
//			obj.put("revexplanin", objValue);
//			obj.put("undertaking", objValue1);
//			return obj;
//		} else {
//			return obj;
//		}
//	}
//
//	//获取状态的批次
//	public List<PmsPlanprojectbatch> FindBatchByStatus(String status) {
//		List<PmsPlanprojectbatch> batch=this.findByHql("from PmsPlanprojectbatch  where status=?",status);
//		return  batch;
//
//	}
//
//	public Boolean endZjPs(String id) {
//		// TODO Auto-generated method stub
//		String updateBatchsql = " update pms_planprojectbatch b set b.nocheck = '无需验证码' where b.id = ?";
//		try {
//			// 更新批次
//			dbHelper.runSql(updateBatchsql,new Object[]{id});
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        return true;
//	}
//	public Boolean findNocheckByid(String id) {
//		String sql = " select b.nocheck from pms_planprojectbatch b where b.id = ?";
//		String necheck=dbHelper.getOnlyStringValue(sql,new Object[]{id});
//		if(necheck==null||"".equals(necheck)){
//			return true;
//		}else{
//			return false;
//		}
//	}
//
//
//	public Boolean stopZjPsXc(String id) {
//		// TODO Auto-generated method stub
//		String sql = "select t.id from sys_user t "+
//				" left join rev_xc_expertinteam e on e.username = t.userid "+
//				" where e.bid =?";
//		String updateBatchsql = " update pms_planprojectbatch b set b.xcstatus = '停止评审' where b.id = ?";
//		String updateTeamsql = " update rev_xc_team b set b.status = '评审完成' where b.bid = ?";
//		try {
//			//更新组
//			dbHelper.runSql(updateTeamsql,new Object[]{id});
//			// 更新批次
//			dbHelper.runSql(updateBatchsql,new Object[]{id});
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		List<Map> lists = dbHelper.getRows(sql, new Object[]{id});
//		if(lists!=null&&lists.size()>0){
//			for(Map map:lists){
//				String userid = map.get("id").toString();
//				String updateSql = "update sys_user set userstate = '3' where id = ?";
//				String updateIdeSql = "update sys_identity ide set ide.enabled = '3' where ide.userid = ?";
//				try {
//					dbHelper.runSql(updateSql,new Object[]{userid});
//					dbHelper.runSql(updateIdeSql,new Object[]{userid});
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					log.error(e.getMessage(),e);
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	public Boolean startZjPsXc(String id) {
//		String updateBatchsql = " update pms_planprojectbatch b set b.xcstatus = '评审中' where b.id = ?";
//		this.runSql(updateBatchsql,new Object[]{id});
//
//		return true;
//	}
//
//	//设置批次的专家组
//	public boolean setBatchPlan(String bid,String pid) {
//		PmsPlanprojectbatch pmsPlanprojectbatch= getPmsPlanprojectbatch().findById(bid);
//		pmsPlanprojectbatch.setPlanid(pid);
//		getPmsPlanprojectbatch().save(pmsPlanprojectbatch);
//		return true;
//	}
//
//	public void saveyzphone(SecurityUser user, JSONObject mydata) {
//		String bid = mydata.getString("id");
//		String yzphone = mydata.getString("yzphone");
//		String id = Util.NewGuid();
//		String operatorid = user.getId();
//		String operator = user.getName();
//		String note = "设置开启手机";
//		String type = "设置网评手机";
//
//		try {
//			String sql = "update pms_planprojectbatch set yzphone = ? where id = ? ";
//			dbHelper.runSql(sql,new Object[]{yzphone,bid});
//			String sql1 = "insert into sys_operationrecord (id,operationdate,operator,operatorid,note,type,sourceid) " +
//					"values(?,sysdate,?,?,?,?,?)";
//			dbHelper.runSql(sql1,new Object[]{id,operator,operatorid,note,type,bid});
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	//项目负责人立项期
//	public void VerificateZDYFone(JSONObject obj) {
//		JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//		if(obj!=null&&array.size()>0){
//			try {
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("1 "+projectbaseid+" "+i);
//					String sql = "select base.id,batch.name as batch,base.mainorganizers,base.projectleader,base.submitdate,t.name as xmfzr,t.certificatenumber " +
//							"from pms_projectbase base " +
//							"left join pms_projectparty t on t.projectbaseid = base.id and (t.rytype in ('true','principal') and t.classify in ('项目负责人','负责人'))" +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' and base.id = ?";
//					List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					String applicationno = "";
//					Integer xmfzrtotal = 0;
//					if(list.size()>0){
//						for (int j = 0; j < list.size(); j++) { //循环负责人
//							String xmfzr = list.get(j).get("xmfzr")+"";
//							String certificatenumber = list.get(j).get("certificatenumber")+"";
//							if (!"".equals(certificatenumber)&&!"".equals(xmfzr)) {
//								Integer xmfzrcount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from pms_projectbase t " +
//										"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//										"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//										"and e.name = ? and e.certificatenumber = ?",new Object[]{xmfzr,certificatenumber}));
//								if(xmfzrcount>0){
//									applicationno += dbHelper.getOnlyStringValue("select t.applicationno from pms_projectbase t " +
//											"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//											"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//											"and e.name = ? and e.certificatenumber = ?",new Object[]{xmfzr,certificatenumber})+",";
//								}
//								xmfzrtotal = xmfzrtotal + xmfzrcount;
//							}else{
//								Integer xmfzrcount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from pms_projectbase t " +
//										"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//										"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//										"and e.name = ? ",new Object[]{xmfzr}));
//								if(xmfzrcount>0){
//									applicationno += dbHelper.getOnlyStringValue("select t.applicationno from pms_projectbase t " +
//											"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//											"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//											"and e.name = ? ",new Object[]{xmfzr})+",";
//								}
//								xmfzrtotal = xmfzrtotal + xmfzrcount;
//							}
//						}
//
//						if(xmfzrtotal==0){
//							dbHelper.runSql("update pms_projectbaseyanzheng set one = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//						} else {
//							dbHelper.runSql("update pms_projectbaseyanzheng set one = '不通过',memo1 = ? where projectbaseid = ?",new Object[]{applicationno,projectbaseid});
//						}
//					}else{
//						dbHelper.runSql("update pms_projectbaseyanzheng set one = '未找到项目负责人' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//				e.printStackTrace();
//			}
//		}
//	}
//	//项目负责人在研记录
//	public void VerificateZDYFtwo(JSONObject obj) {
//		JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//		if(obj!=null&&array.size()>0){
//			try {
//					for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("2 "+projectbaseid+" "+i);
//					String sql = "select base.id,batch.name as batch,base.mainorganizers,base.projectleader,base.submitdate,t.name as xmfzr,t.certificatenumber " +
//							"from pms_projectbase base " +
//							"left join pms_projectparty t on t.projectbaseid = base.id and (t.rytype in ('true','principal') and t.classify in ('项目负责人','负责人')) " +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' and base.id = ?";
//					List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					String applicationno = "";
//					Integer xmfzrtotal = 0;
//					if(list.size()>0){
//						for (int j = 0; j < list.size(); j++) { //循环负责人
//							String xmfzr = list.get(j).get("xmfzr")+"";
//							String certificatenumber = list.get(j).get("certificatenumber")+"";
//							if (!"".equals(certificatenumber)&&!"".equals(xmfzr)) {
//								Integer xmfzrcount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from crt_contract_jbxx jbxx " +
//										"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//										"and (xmry.rytype in ('true','principal') and xmry.classify = '项目负责人')" +
//										"where jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//										"and xmry.name = ? and xmry.certificatenumber = ?",new Object[]{xmfzr,certificatenumber}));
//								if(xmfzrcount>0){
//									applicationno = dbHelper.getOnlyStringValue("select jbxx.contractno from crt_contract_jbxx jbxx " +
//											"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//											"and (xmry.rytype in ('true','principal') and xmry.classify = '项目负责人')" +
//											"where jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//											"and xmry.name = ? and xmry.certificatenumber = ?",new Object[]{xmfzr,certificatenumber})+",";
//								}
//								xmfzrtotal = xmfzrtotal + xmfzrcount;
//							}else{
//								Integer xmfzrcount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from crt_contract_jbxx jbxx " +
//										"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//										"and (xmry.rytype in ('true','principal') and xmry.classify = '项目负责人')" +
//										"where jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//										"and xmry.name = ? ",new Object[]{xmfzr}));
//								if(xmfzrcount>0){
//									applicationno = dbHelper.getOnlyStringValue("select jbxx.contractno from crt_contract_jbxx jbxx " +
//											"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//											"and (xmry.rytype in ('true','principal') and xmry.classify = '项目负责人')" +
//											"where jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//											"and xmry.name = ? ",new Object[]{xmfzr})+",";
//								}
//								xmfzrtotal = xmfzrtotal + xmfzrcount;
//							}
//
//						}
//
//						if(xmfzrtotal==0){
//							dbHelper.runSql("update pms_projectbaseyanzheng set two = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//						} else {
//							dbHelper.runSql("update pms_projectbaseyanzheng set two = '不通过',memo2 = ? where projectbaseid = ?",new Object[]{applicationno,projectbaseid});
//						}
//					}else{
//						dbHelper.runSql("update pms_projectbaseyanzheng set two = '未找到项目负责人' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//				e.printStackTrace();
//			}
//		}
//	}
//	//企业信用记录
//	public void VerificateZDYFthree(JSONObject obj) {
//		JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		if(obj!=null&&array.size()>0){
//			try {
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("3 "+projectbaseid+" "+i);
//					String sql = "select base.id,batch.name as batch,base.mainorganizers,base.projectleader,base.submitdate,t.registerdate as registertime,t.organizationtype as enterprisetype,t.organizationname as enterprisename from pms_projectbase base " +
//							"left join pms_enterpriseorganization t on t.projectbaseid = base.id  " +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id where t.ismainenterprise = 'true' and base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' and base.id = ?";
//					List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					String enterprisename = list.get(0).get("enterprisename").toString();
//					String enterprisetype = list.get(0).get("enterprisetype").toString();
//					if(!"高等学校".equals(enterprisetype)&&!"科研院所".equals(enterprisetype)&&!"医疗机构".equals(enterprisetype)){
//						List<Map> cddwlist = dbHelper.getRows("select * from pms_honestymanage where enterpriseorperson = ?",new Object[]{enterprisename});
//						if(cddwlist.size()==0){
//							dbHelper.runSql("update pms_projectbaseyanzheng set three = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//						}else{
//							Date cddwstartdate = new Date();
//							if("".equals(cddwlist.get(0).get("startdate")+"")||cddwlist.get(0).get("startdate")==null){
//								String reason = cddwlist.get(0).get("reason")+"";
//								String tempdate = reason.substring(8,19);
//								tempdate = tempdate.replace("年","-");
//								tempdate = tempdate.replace("月","-");
//								tempdate = tempdate.replace("日","");
//								cddwstartdate = sdf.parse(tempdate);
//							} else {
//								cddwstartdate = sdf.parse(cddwlist.get(0).get("startdate")+"");//记录开始时间
//							}
//							Integer cddwyear = Integer.parseInt(cddwlist.get(0).get("enabletime")+"");
//							Calendar calendar = Calendar.getInstance();
//							calendar.setTime(cddwstartdate);
//							calendar.add(calendar.YEAR, cddwyear);
//							Date cddwenddate = calendar.getTime();//记录结束时间
//							Date submitdate = sdf.parse(list.get(0).get("submitdate")+"");//上报时间
//							long todate = submitdate.getTime();
//							long cddwend = cddwenddate.getTime();
//							if(cddwend < todate){
//								dbHelper.runSql("update pms_projectbaseyanzheng set three = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//							} else {
//								dbHelper.runSql("update pms_projectbaseyanzheng set three = '不通过',memo3 = ? where projectbaseid = ?",new Object[]{cddwlist.get(0).get("contractno")+"",projectbaseid});
//							}
//						}
//					} else {
//						dbHelper.runSql("update pms_projectbaseyanzheng set three = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//
//				}
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//				e.printStackTrace();
//			} catch (ParseException t) {
//				log.error(t.getMessage());
//				t.printStackTrace();
//			}
//		}
//	}
//	//企业在研记录
//	public void VerificateZDYFfour(JSONObject obj) {
//		JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//		if(obj!=null&&array.size()>0){
//			try {
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("4 "+projectbaseid+" "+i);
//					String sql = "select base.id,batch.name as batch,base.mainorganizers,base.projectleader,base.submitdate,t.registerdate as registertime,t.organizationtype as enterprisetype,t.organizationname as enterprisename from pms_projectbase base " +
//							"left join pms_enterpriseorganization t on t.projectbaseid = base.id  " +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id where t.ismainenterprise = 'true' and base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' and base.id = ?";
//					List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					String enterprisename = list.get(0).get("enterprisename").toString();
//					String conditiontype = "";
//					List<Map> conditionlist = dbHelper.getRows("select t.* from pms_enterprisecondition t where t.year = '2018' and t.enterprisename = ?",new Object[]{enterprisename});
//					if(conditionlist.size()>0){
//						for (int j = 0; j < conditionlist.size(); j++) {
//							conditiontype += conditionlist.get(j).get("conditiontype").toString()+",";
//						}
//					}
//
//					Integer limitcount = 1;
//					Integer cddwzytotal = 0;
//					if(conditionlist.size()==0){
//						limitcount = 1;
//					} else if(conditiontype.indexOf("xzsl")>-1){
//						limitcount = Integer.parseInt(dbHelper.getOnlyStringValue("select t.limitcount from pms_enterprisecondition t where t.year = '2018' " +
//								"and t.conditiontype = 'xzsl' and t.enterprisename = ?",new Object[]{enterprisename}));
//					}
//					String enterprisetype = list.get(0).get("enterprisetype").toString();
//					if(conditiontype.indexOf("szdyjyqd")>-1||"高等学校".equals(enterprisetype)||"科研院所".equals(enterprisetype)||"医疗机构".equals(enterprisetype)){
//						dbHelper.runSql("update pms_projectbaseyanzheng set four = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//					} else {
//						String zycontractno = "";
//						//当前企业在研数量（新系统）
//						Integer cddwzycount1 = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from crt_contract_jbxx jbxx left join crt_contract_cddw cddw on cddw.contractid = jbxx.id " +
//								"where jbxx.contractno not like '%F10%' and jbxx.contractno not like '%F30%' and cddw.dwzc = '主办单位' and jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//								"and cddw.organizationname = ?",new Object[]{enterprisename}));
//						if(cddwzycount1>0){
//							zycontractno = dbHelper.getOnlyStringValue("select jbxx.contractno from crt_contract_jbxx jbxx left join crt_contract_cddw cddw on cddw.contractid = jbxx.id " +
//									"where jbxx.contractno not like '%F10%' and jbxx.contractno not like '%F30%' and cddw.dwzc = '主办单位' and jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//									"and cddw.organizationname = ?",new Object[]{enterprisename})+",";
//						}
//						//当前企业在研数量（老系统）
//						Integer cddwzycount2 = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from lx_contract_temp " +
//								"where contractno not like '%F10%' and contractno not like '%F30%' and unitname = ? and isleader = '1'",new Object[]{enterprisename}));
//						if(cddwzycount2>0){
//							zycontractno += dbHelper.getOnlyStringValue("select contractno from lx_contract_temp " +
//									"where contractno not like '%F10%' and contractno not like '%F30%' and unitname = ? and isleader = '1'",new Object[]{enterprisename})+",";
//						}
//						//当前企业在研数量（自然基金）
//						Integer cddwzycount3 = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from lx_contract_gyjj " +
//								"where xmpzh not like '%F10%' and xmpzh not like '%F30%' and dw = ?",new Object[]{enterprisename}));
//						if(cddwzycount3>0){
//							zycontractno += dbHelper.getOnlyStringValue("select xmpzh from lx_contract_gyjj " +
//									"where xmpzh not like '%F10%' and xmpzh not like '%F30%' and dw = ?",new Object[]{enterprisename})+",";
//						}
//
//						cddwzytotal = cddwzycount1 + cddwzycount2 + cddwzycount3;
//
//						if(cddwzytotal<limitcount){
//							dbHelper.runSql("update pms_projectbaseyanzheng set four = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//						} else {
//							dbHelper.runSql("update pms_projectbaseyanzheng set four = '不通过',memo4 = ? where projectbaseid = ?",new Object[]{zycontractno,projectbaseid});
//
//						}
//					}
//				}
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//				e.printStackTrace();
//			}
//		}
//	}
//	//项目负责人年龄（55周岁）
//	public void VerificateZDYFfive(JSONObject obj) {
//		JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		if(obj!=null&&array.size()>0){
//			try {
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("5 "+projectbaseid+" "+i);
//					String sql = "select w.id from pms_projectparty w " +
//							"where w.rytype in ('true','principal') and w.classify in ('项目负责人','负责人') " +
//							"and w.projectbaseid = ?";
//					List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//					String Submitdate = dbHelper.getOnlyStringValue("select submitdate from pms_projectbase where id = ?",new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					if(list.size()>0){
//						for (int j = 0; j < list.size(); j++) {
//							String birthday = dbHelper.getOnlyStringValue("select birthday from pms_projectparty where id = ?",new Object[]{list.get(j).get("id")+""});
//							if("".equals(birthday)||birthday==null){
//								dbHelper.runSql("update pms_projectbaseyanzheng set five = '负责人生日为空' where projectbaseid = ?",new Object[]{projectbaseid});
//							} else {
//								Date birth = sdf.parse(birthday);
//								Calendar calendar = Calendar.getInstance();
//								calendar.setTime(birth);
//								calendar.add(calendar.YEAR, 55);
//								Date endbirthdate = calendar.getTime();//生日+55
//								Date submitdate = sdf.parse(Submitdate);//上报时间
//								long todate = submitdate.getTime();
//								long endbirthday = endbirthdate.getTime();
//								if(todate<endbirthday){
//									dbHelper.runSql("update pms_projectbaseyanzheng set five = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								} else {
//									dbHelper.runSql("update pms_projectbaseyanzheng set five = '不通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}
//							}
//						}
//					}else{
//						dbHelper.runSql("update pms_projectbaseyanzheng set five = '未找到项目负责人' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//				e.printStackTrace();
//			} catch (ParseException t) {
//				log.error(t.getMessage());
//				t.printStackTrace();
//			}
//		}
//	}
//	//主要参加人在研记录
//	public void VerificateZDYFsix(JSONObject obj) {
//		JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		if(obj!=null&&array.size()>0){
//			try {
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("6 "+projectbaseid+" "+i);
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					List<Map> cyrylist = dbHelper.getRows("select * from (select base.id,batch.name as batch,party.name,party.certificatenumber " +
//							"from pms_projectbase base " +
//							"left join pms_projectparty party on party.projectbaseid = base.id " +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' " +
//							"and party.rytype = 'principal' and party.classify not in ('项目负责人','负责人') and party.projectbaseid = ? order by party.seq) " +
//							"where rownum < 4 ",new Object[]{projectbaseid});
//
//					Integer cyrytotal = 0;
//					if(cyrylist.size()>0){
//						for (int j = 0; j < cyrylist.size(); j++) {
//							String cyryname = cyrylist.get(j).get("name").toString();
//							String cyrycertnumber = cyrylist.get(j).get("certificatenumber").toString();
//							String zycontractno = "";
//							if (!"".equals(cyrycertnumber)&&!"".equals(cyryname)) {
//								Integer cyrycount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from crt_contract_jbxx jbxx " +
//										"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//										"where jbxx.isys is null and jbxx.isendcontract is null " +
//										"and xmry.rytype in ('true','principal') and xmry.classify in ('项目负责人','负责人') and xmry.name = ? " +
//										"and xmry.certificatenumber = ?", new Object[]{cyryname,cyrycertnumber}));
//								if(cyrycount>0){
//									List<Map> contractnolist = dbHelper.getRows("select jbxx.contractno from crt_contract_jbxx jbxx " +
//											"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//											"where jbxx.isys is null and jbxx.isendcontract is null " +
//											"and xmry.rytype in ('true','principal') and xmry.classify in ('项目负责人','负责人') and xmry.name = ? " +
//											"and xmry.certificatenumber = ?", new Object[]{cyryname,cyrycertnumber});
//									for (int k = 0; k < contractnolist.size(); k++) {
//										zycontractno += contractnolist.get(k).get("contractno")+",";
//									}
//								}
//								cyrytotal = cyrytotal + cyrycount;
//							}else{
//								Integer cyrycount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from crt_contract_jbxx jbxx " +
//										"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//										"where jbxx.isys is null and jbxx.isendcontract is null " +
//										"and xmry.rytype in ('true','principal') and xmry.classify in ('项目负责人','负责人') and xmry.name = ? ", new Object[]{cyryname}));
//								if(cyrycount>0){
//									List<Map> contractnolist = dbHelper.getRows("select jbxx.contractno from crt_contract_jbxx jbxx " +
//											"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//											"where jbxx.isys is null and jbxx.isendcontract is null " +
//											"and xmry.rytype in ('true','principal') and xmry.classify in ('项目负责人','负责人') and xmry.name = ? ", new Object[]{cyryname});
//									for (int k = 0; k < contractnolist.size(); k++) {
//										zycontractno += contractnolist.get(k).get("contractno")+",";
//									}
//								}
//								cyrytotal = cyrytotal + cyrycount;
//							}
//
//							if(cyrytotal==0){
//								dbHelper.runSql("update pms_projectbaseyanzheng set six = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//							} else {
//								dbHelper.runSql("update pms_projectbaseyanzheng set six = '不通过',memo5 = ? where projectbaseid = ?",new Object[]{zycontractno,projectbaseid});
//								break;
//							}
//						}
//					} else {
//						dbHelper.runSql("update pms_projectbaseyanzheng set six = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//				e.printStackTrace();
//			}
//		}
//	}
//	//主要参加人立项期
//	public void VerificateZDYFseven(JSONObject obj) {
//		JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		if(obj!=null&&array.size()>0){
//			try {
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("7 "+projectbaseid+" "+i);
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					List<Map> cyrylist = dbHelper.getRows("select * from (select base.id,batch.name as batch,party.name,party.certificatenumber " +
//							"from pms_projectbase base " +
//							"left join pms_projectparty party on party.projectbaseid = base.id " +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' " +
//							"and party.rytype = 'principal' and party.classify not in ('项目负责人','负责人') and party.projectbaseid = ? order by party.seq) " +
//							"where rownum < 4 ",new Object[]{projectbaseid});
//
//					Integer cyrytotal = 0;
//					if(cyrylist.size()>0){
//						for (int j = 0; j < cyrylist.size(); j++) {
//							String cyryname = cyrylist.get(j).get("name").toString();
//							String cyrycertnumber = cyrylist.get(j).get("certificatenumber").toString();
//							String applicationno = "";
//							if (!"".equals(cyrycertnumber)&&!"".equals(cyryname)) {
//								Integer cyrycount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from pms_projectbase t " +
//										"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//										"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//										"and e.name = ? and e.certificatenumber = ?",new Object[]{cyryname,cyrycertnumber}));
//								if(cyrycount>0){
//									List<Map> applicationnolist = dbHelper.getRows("select t.applicationno from pms_projectbase t " +
//											"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//											"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//											"and e.name = ? and e.certificatenumber = ?",new Object[]{cyryname,cyrycertnumber});
//									for (int k = 0; k < applicationnolist.size(); k++) {
//										applicationno += applicationnolist.get(k).get("applicationno")+",";
//									}
//								}
//								cyrytotal = cyrytotal + cyrycount;
//							}else{
//								Integer cyrycount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from pms_projectbase t " +
//										"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//										"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//										"and e.name = ? ",new Object[]{cyryname}));
//								if(cyrycount>0){
//									List<Map> applicationnolist = dbHelper.getRows("select t.applicationno from pms_projectbase t " +
//											"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//											"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//											"and e.name = ? ",new Object[]{cyryname});
//									for (int k = 0; k < applicationnolist.size(); k++) {
//										applicationno += applicationnolist.get(k).get("applicationno")+",";
//									}
//								}
//								cyrytotal = cyrytotal + cyrycount;
//							}
//
//							if(cyrytotal==0){
//								dbHelper.runSql("update pms_projectbaseyanzheng set seven = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//							} else {
//								dbHelper.runSql("update pms_projectbaseyanzheng set seven = '不通过',memo6 = ? where projectbaseid = ?",new Object[]{applicationno,projectbaseid});
//								break;
//							}
//						}
//					} else {
//						dbHelper.runSql("update pms_projectbaseyanzheng set seven = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//				e.printStackTrace();
//			}
//		}
//	}
//	//依托单位申报限制
//	public void VerificateZDYFeight(JSONObject obj) {
//		JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//		if(obj!=null&&array.size()>0){
//			try {
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("8 "+projectbaseid+" "+i);
//					String sql = "select base.id,batch.name as batch,base.mainorganizers,base.projectleader,base.submitdate," +
//							"t.organizationname,t.organizationtype,base.planprojectbatchid,t.legalcode from pms_projectbase base " +
//							"left join pms_enterpriseorganization t on t.projectbaseid = base.id  " +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where t.ismainenterprise = 'true' and base.minicurrentstate is not null " +
//							"and base.minicurrentstate <> '项目申报:用户填报' and base.id = ?";
//					List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					if(list.size()>0){
//						String batchsql = "select e.legalcode,count(e.legalcode) as num from crt_contract_jbxx t " +
//								"left join crt_contract_cddw e on e.contractid = t.id and e.dwzc = 'true' " +
//								"where t.planprojectid in (select limitbigbatchid from pms_batch_limit where batchid = ?) " +
//								"and t.isys is null and t.isendcontract is null and e.legalcode = ? group by e.legalcode ";
//						String num = dbHelper.getValue(batchsql,"num",new Object[]{list.get(0).get("planprojectbatchid")+"",list.get(0).get("legalcode")+""});
//						String organizationtype = list.get(0).get("organizationtype")+"";
//
//						if ("".equals(organizationtype)){
//							dbHelper.runSql("update pms_projectbaseyanzheng set eight = '未找到项目承担单位单位类型' where projectbaseid = ?",new Object[]{projectbaseid});
//						}else{
//							if("企业,政府部门,医疗机构,其他".indexOf(organizationtype)>-1){
//								if(Integer.parseInt(num) < 3||num==null||num==""){
//									dbHelper.runSql("update pms_projectbaseyanzheng set eight = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}else{
//									dbHelper.runSql("update pms_projectbaseyanzheng set eight = '不通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}
//							}else if("高等院校,科研院所".indexOf(organizationtype)>-1){
//								if(Integer.parseInt(num) < 6||num==null||num==""){
//									dbHelper.runSql("update pms_projectbaseyanzheng set eight = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}else{
//									dbHelper.runSql("update pms_projectbaseyanzheng set eight = '不通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}
//							}
//						}
//					}else{
//						dbHelper.runSql("update pms_projectbaseyanzheng set eight = '未找到项目承担单位' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			} catch (SQLException e) {
//				log.error(e.getMessage());
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//	public List<Map> findPublicDetails(String projectbaseid,String type) {
//		String sql = "";
//		if("xm".equals(type)) {
//			sql = "select t.applicationno,t.showprojectbasename," +
//					"(case when e.organizationname is null then t.mainorganizers else e.organizationname end) as organizationname," +
//					"(case when w.name is null then t.projectleader else w.name end) as projectparty,t.minicurrentstate from pms_projectbase t " +
//					"left join pms_enterpriseorganization e on e.projectbaseid = t.id and e.ismainenterprise = 'true' " +
//					"left join pms_projectparty w on w.projectbaseid = t.id and w.programrole = '项目负责人' " +
//					"where t.id = ?";
//
//		}else if("ht".equals(type)) {
//			sql = "select jbxx.contractno,jbxx.showprojectbasename,jbxx.minicurrentstate,xmry.name as projectparty,cddw.organizationname from crt_contract_jbxx jbxx " +
//					"left join crt_contract_cddw cddw on cddw.projectbaseid = jbxx.projectbaseid and (cddw.dwzc = '主办单位' or cddw.dwzc = '承担单位') " +
//					"left join crt_contract_xmry xmry on xmry.projectbaseid = jbxx.projectbaseid and xmry.rytype = '项目负责人' " +
//					"where jbxx.projectbaseid = ?";
//		}
//
//		List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//		return list;
//	}
//
//	/**
//	 * 根据 type获取全部大批次
//	 * @return
//	 */
//	public List<Map> getReallyAllBigBatchData(){
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname,t.* from pms_planproject t order by t.seq",new Object[]{});
//		return resList;
//	}
//
//	public List<Map> getBigBatchBySmallBatchYear(String year, String ywtype){
//		List<Map> resList = null;
//		if(!Util.isEoN(year)){
//			if("other".equals(ywtype)){
//				resList = this.dbHelper.getRows("select big.projectname as bigbatchname,big.* from PMS_PLANPROJECT big " +
//						"WHERE id in (" +
//						"SELECT planprojectid from pms_planprojectbatch where ANNUALLY = ?)", new Object[]{year});
//			}else {
//				resList = this.dbHelper.getRows("select big.projectname as bigbatchname,big.* from PMS_PLANPROJECT big " +
//						"WHERE id in (" +
//						"SELECT planprojectid from pms_planprojectbatch where ANNUALLY = ? and ywtype = ?)", new Object[]{year, ywtype});
//			}
//		}else {
//			if("other".equals(ywtype)){
//				resList = this.dbHelper.getRows("select big.projectname as bigbatchname,big.* from PMS_PLANPROJECT big " +
//						"WHERE id in (" +
//						"SELECT planprojectid from pms_planprojectbatch)", null);
//			}else {
//				resList = this.dbHelper.getRows("select big.projectname as bigbatchname,big.* from PMS_PLANPROJECT big " +
//						"WHERE id in (" +
//						"SELECT planprojectid from pms_planprojectbatch where ywtype = ?)", new Object[]{ywtype});
//			}
//		}
//		return resList;
//	}
//
//	/**
//	 * 根据 科技项目 所有批次，并按照类型排序（申报限制勾选批次用）
//	 * @return
//	 */
//	public List<Map> getKjxmAllBigBatchData(){
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname,t.* from pms_planproject t" +
//				" where t.system = 'kjxm' order by t.applicationtypename2",new Object[]{});
//		return resList;
//	}
//
//	/**
//	 * 根据 type获取全部大批次
//	 * @return
//	 */
//	public List<Map> getBigBatchAllData(String system){
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t where t.system = ?",new Object[]{system});
//		return resList;
//	}
//
//	/**
//	 * 获取全部大批次（去掉 评审专用 批次）
//	 * @return
//     */
//    public List<Map> getBigBatchData(String system){
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t" +
//				" where (t.olddata != '评审专用' or t.olddata is null) and t.system = ? and t.othersystemurl is null order by t.projectname",new Object[]{system});
//		return resList;
//	}
//
//	/**
//	 * 获取全部大批次 for 预申报
//	 * @return
//	 */
//	public List<Map> getBigBatchDataForYsb(String system){
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t" +
//				" where (t.olddata != '评审专用' or t.olddata is null) and t.system = ? and t.othersystemurl is null " +
//				" and t.hasprepare = '1'" +
//				" order by t.projectname",new Object[]{system});
//		return resList;
//	}
//
//	/**
//	 * 通过id获取
//	 * @return
//	 */
//	public List<Map> getBigBatchByIdForYsb(String bigbatchid){
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t" +
//				" where (t.olddata != '评审专用' or t.olddata is null) and t.id = ? and t.othersystemurl is null " +
//				" and t.hasprepare = '1'" +
//				" order by t.projectname",new Object[]{bigbatchid});
//		return resList;
//	}
//
//	/**
//	 * 获取全部大批次针对评审（去掉 评审专用 批次）
//	 * @return
//	 */
//	public List<Map> getBigBatchDataForReview(String groupid){
//		String sql = "select distinct pl1.id, pl1.projectname as bigbatchname from ZJK_BATCH_PLAN p\n" +
//				"inner join ZJK_BATCH_GROUP g on g.PLANID = p.ID\n" +
//				"inner join PMS_PLANPROJECTBATCH b on b.ID = p.PROJECTBATCHID\n" +
//				"inner join PMS_PLANPROJECT pl on pl.ID = b.PLANPROJECTID\n" +
//				"inner join PMS_PLANPROJECT pl1 on pl.REVIEWTYPE = pl1.REVIEWTYPE\n" +
//				"where (pl1.olddata != '评审专用' or pl1.olddata is null) and g.ID = ? and pl1.othersystemurl is null order by pl1.projectname";
//		List<Map> resList = this.dbHelper.getRows(sql,new Object[]{groupid});
//		return resList;
//	}
//
//    /**
//     * 获取全部大批次（去掉 评审专用 批次，老系统批次）
//     * @return
//     */
//    public List<Map> getBigBatchDataNewSys(String system){
//        List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t where t.olddata is null and t.system = ? order by t.projectname ",new Object[]{system});
//        return resList;
//    }
//
//	/**
//	 * 获取所有大批次--免申即享服务机构用
//	 * @return
//	 */
//	public List<Map> getBigBatchDataMsjx(SysUser sysUser){
//		String id = sysUser.getSysIdentitys().get(0).getPmsEnterprise().getId();
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t " +
//				"where t.id in (select planprojectid from pms_planprojectbatch where institutionid like ? group by planprojectid ) " +
//				"and (t.olddata != '评审专用' or t.olddata is null) and t.othersystemurl is null " +
//				"order by t.projectname",new Object[]{'%' + id + '%'});
//		return resList;
//	}
//
//	/**
//	 * 获取大批次  通过大批次表的关联处室id（去掉 评审专用 批次）
//	 * @param sysUser
//     * @return
//     */
//	public List<Map> getBigBatchDataByDepartment(SysUser sysUser,String system){
//		String departmentid = this.getOnlyValueBySql("select i.departmentid from sys_user u left join sys_identity i on u.id = i.userid where u.id = ? and rownum <2",new Object[]{sysUser.getId()});
//		//处室id 去搜索大批次
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t where 1=1 and t.departmentid = ?" +
//				" and (t.olddata != '评审专用' or t.olddata is null) and t.system = ? and t.othersystemurl is null order by t.projectname" ,new Object[]{departmentid,system});
//		String insql = "";
//		if(resList!=null&&resList.size()>0){
//			insql = " and  t.id not in (";
//			for(Map m : resList){
//				insql = insql + "'"+m.get("id")+"',";
//			}
//			insql = insql.substring(0,insql.length()-1)+")";
//		}
//		//根据项目的处室id 去取 大批次
//		List<Map> xmpcList =  this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t where 1=1 " +
//				" and t.id in (select base.planprojectid from pms_projectbase base where base.belonglabid = ? " +
//				" group by  base.planprojectid)  and t.system = ? " + insql ,new Object[]{departmentid,system});
//
//		//合并数组
//		List<Map> allList = new ArrayList<>();
//		allList.addAll(resList);
//		allList.addAll(xmpcList);
////		if(resList.size()==0){
////			//说明当前用户的处室可能是二级处室，比如高新处，实在用户填写项目的时候选择的领域决定的处室，大处室是资配或者战略（pms_planproject绑定的处室）
////			//从项目中取大批次
////			//可能还是有点问题，后续考虑
////			resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t where 1=1 " +
////					" and t.departmentid in (select base.planprojectid from pms_projectbase base where base.belonglabid = ? " +
////					" group by  base.planprojectid) " ,new Object[]{departmentid});
////
////		}
//		return allList;
//	}
//
//
//	/**
//	 * 获取大批次  通过大批次表的关联处室id（去掉 评审专用 批次，老系统批次）
//	 * @param sysUser
//	 * @return
//	 */
//	public List<Map> getBigBatchDataByDepartmentNoOld(SysUser sysUser,String system){
//		String departmentid = this.getOnlyValueBySql("select i.departmentid from sys_user u left join sys_identity i on u.id = i.userid where u.id = ? and rownum <2",new Object[]{sysUser.getId()});
//		//处室id 去搜索大批次
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t where 1=1 and t.departmentid = ? " +
//				" and t.olddata is null and t.system = ?" ,new Object[]{departmentid,system});
//		String insql = "";
//		if(resList!=null&&resList.size()>0){
//			insql = " and  t.id not in (";
//			for(Map m : resList){
//				insql = insql + "'"+m.get("id")+"',";
//			}
//			insql = insql.substring(0,insql.length()-1)+")";
//		}
//		//根据项目的处室id 去取 大批次
//		List<Map> xmpcList =  this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t where 1=1 " +
//				" and t.id in (select base.planprojectid from pms_projectbase base where base.belonglabid = ? " +
//				" group by  base.planprojectid) and t.system =? " + insql ,new Object[]{departmentid,system});
//
//		//合并数组
//		List<Map> allList = new ArrayList<>();
//		allList.addAll(resList);
//		allList.addAll(xmpcList);
////		if(resList.size()==0){
////			//说明当前用户的处室可能是二级处室，比如高新处，实在用户填写项目的时候选择的领域决定的处室，大处室是资配或者战略（pms_planproject绑定的处室）
////			//从项目中取大批次
////			//可能还是有点问题，后续考虑
////			resList = this.dbHelper.getRows("select t.id,t.projectname as bigbatchname from pms_planproject t where 1=1 " +
////					" and t.departmentid in (select base.planprojectid from pms_projectbase base where base.belonglabid = ? " +
////					" group by  base.planprojectid) " ,new Object[]{departmentid});
////
////		}
//		return allList;
//	}
//
//
////
//
//
//	/**
//	 *
//	 * @param bigbatchname
//	 * @return
//     */
//	public List<Map> getBatchDataByBigBatchname(String bigbatchname){
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.name from pms_planprojectbatch t " +
//				"left join pms_planproject tt on t.planprojectid = tt.id " +
//				"where tt.projectname = ? order by t.annually",new Object[]{bigbatchname});
//		return resList;
//	}
//
//	/**
//	 *
//	 * @param bigbatchname
//	 * @return
//	 */
//	public List<Map> getBatchDataByBigBatchnameAndYear(String bigbatchname,String year){
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.name from pms_planprojectbatch t " +
//				"left join pms_planproject tt on t.planprojectid = tt.id " +
//				"where tt.projectname = ? and t.annually = ? order by t.annually",new Object[]{bigbatchname,year});
//		return resList;
//	}
//
//	/**
//	 *
//	 * @param bigbatchname
//	 * @return
//	 */
//	public List<Map> getBatchDataByBigBatchnameNoOld(String bigbatchname){
//		List<Map> resList = this.dbHelper.getRows("select t.id,t.name from pms_planprojectbatch t " +
//				"left join pms_planproject tt on t.planprojectid = tt.id " +
//				"where tt.projectname = ? and t.olddata is null order by t.annually",new Object[]{bigbatchname});
//		return resList;
//	}
//
//    /**
//     *
//     * @param bigbatchname
//     * @return
//     */
//    public List<Map> getBatchDataNewSysByBigBatchname(String bigbatchname){
//        List<Map> resList = this.dbHelper.getRows("select t.id,t.name from pms_planprojectbatch t " +
//                "left join pms_planproject tt on t.planprojectid = tt.id " +
//                "where tt.projectname = ? and t.olddata is null order by t.annually",new Object[]{bigbatchname});
//        return resList;
//    }
//
//
//
//
//	public Boolean mergeBatchsInOne(JSONArray batchs,String newBatchid){
//		PmsPlanprojectbatch pmsPlanprojectbatch = this.getPmsPlanprojectbatch().findById(newBatchid);
//		String hql = "select t from PmsProjectbase t where t.pmsPlanprojectbatch.id in (:batchs)";
//		Query query = this.getQuery(hql);
//		query.setParameterList("batchs",batchs);
//		List<PmsProjectbase> list = query.list();
//		for(PmsProjectbase pmsProjectbase:list){
//			pmsProjectbase.setPlanprojectbatchid(pmsPlanprojectbatch.getId());
//			this.pmsProjectbaseService.merge(pmsProjectbase);
//		}
//		return true;
//	}
//
//	public Boolean splitBatchsInOne(String splitBatchid) throws SQLException {
//		String sql = "update pms_projectbase t set t.planprojectbatchid = t.oldplanbatchid where t.planprojectbatchid = ?";
//		return this.dbHelper.runSql(sql,new Object[]{splitBatchid});
//	}
//
//
//	//负责人职称、学位等
//	public void VerificateZRJJone(JSONObject obj) throws ParseException {
//		try {
//			JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//			if(obj!=null&&array.size()>0){
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("1 "+projectbaseid+" "+i);
//					String sql = "select base.id,batch.name as batch,base.mainorganizers,base.projectleader,base.submitdate,t.name as xmfzr,t.certificatenumber, " +
//							"base.planprojectid,e.organizationtype,t.education,t.degree,t.professionalpost,t.birthday from pms_projectbase base " +
//							"left join pms_projectparty t on t.projectbaseid = base.id and t.rytype = 'true' " +
//							"left join pms_enterpriseorganization e on e.projectbaseid = base.id and e.ismainenterprise = 'true' " +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' and base.id = ?";
//					List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//					String Submitdate = dbHelper.getOnlyStringValue("select submitdate from pms_projectbase where id = ?",new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					if(list.size()>0){
//						for (int j = 0; j < list.size(); j++) {
//							String memo = "";
//							String organizationtype = list.get(j).get("organizationtype")+"";
//							String professionalpost = list.get(j).get("professionalpost")+"";
//							String degree = list.get(j).get("degree")+"";
//							String birthday = list.get(j).get("birthday")+"";
//
//							String planprojectid = list.get(j).get("planprojectid")+"";
//							if(planprojectid.contains("YJTD")){
//								if("正高级".equals(professionalpost)){
//									dbHelper.runSql("update pms_projectbaseyanzheng set one = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}else{
//									memo = "负责人职称需为正高级";
//									dbHelper.runSql("update pms_projectbaseyanzheng set one = '不通过',memo1 = ? where projectbaseid = ?",new Object[]{memo,projectbaseid});
//								}
//							}else if(planprojectid.contains("ZDXM")){
//								if("正高级".equals(professionalpost)||"副高级".equals(professionalpost)||"博士学位".equals(degree)){
//									dbHelper.runSql("update pms_projectbaseyanzheng set one = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}else{
//									memo = "负责人职称职称为正高级或者副高级，或者学位为博士";
//									dbHelper.runSql("update pms_projectbaseyanzheng set one = '不通过',memo1 = ? where projectbaseid = ?",new Object[]{memo,projectbaseid});
//								}
//							}else if(planprojectid.contains("JCQN")){
//								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//								Date birth = sdf.parse(birthday);
//								Calendar calendar = Calendar.getInstance();
//								calendar.setTime(birth);
//								calendar.add(calendar.YEAR, 45);
//								Date endbirthdate = calendar.getTime();//生日+45
//								Date submitdate = sdf.parse(Submitdate);//上报时间
//								long todate = submitdate.getTime();
//								long endbirthday = endbirthdate.getTime();
//								if(todate<endbirthday){
//									if("企业".equals(organizationtype)){
//										if("正高级".equals(professionalpost)||"副高级".equals(professionalpost)||"博士学位".equals(degree)||"硕士学位".equals(degree)){
//											dbHelper.runSql("update pms_projectbaseyanzheng set one = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//										}else{
//											memo = "【企业】负责人职称职称为正高级或者副高级，或者学位为博士或者硕士";
//											dbHelper.runSql("update pms_projectbaseyanzheng set one = '不通过',memo1 = ? where projectbaseid = ?",new Object[]{memo,projectbaseid});
//										}
//									}else{
//										if("正高级".equals(professionalpost)||"副高级".equals(professionalpost)||"博士学位".equals(degree)||"硕士学位".equals(degree)){
//											dbHelper.runSql("update pms_projectbaseyanzheng set one = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//										}else{
//											memo = "【非企业】负责人职称职称为正高级或者副高级，或者学位为博士";
//											dbHelper.runSql("update pms_projectbaseyanzheng set one = '不通过',memo1 = ? where projectbaseid = ?",new Object[]{memo,projectbaseid});
//										}
//									}
//								} else {
//									memo = "负责人年龄应小于45周岁";
//									dbHelper.runSql("update pms_projectbaseyanzheng set one = '不通过',memo1 = ? where projectbaseid = ?",new Object[]{memo,projectbaseid});
//								}
//							}else if(planprojectid.contains("YXQN")){
//								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//								Date birth = sdf.parse(birthday);
//								Calendar calendar = Calendar.getInstance();
//								calendar.setTime(birth);
//								calendar.add(calendar.YEAR, 38);
//								Date endbirthdate = calendar.getTime();//生日+38
//								Date submitdate = sdf.parse(Submitdate);//上报时间
//								long todate = submitdate.getTime();
//								long endbirthday = endbirthdate.getTime();
//								if(todate<endbirthday){
//									if("正高级".equals(professionalpost)||"副高级".equals(professionalpost)||"中级".equals(professionalpost)||"博士学位".equals(degree)){
//										dbHelper.runSql("update pms_projectbaseyanzheng set one = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//									}else{
//										memo = "负责人职称为正高级或者副高级或者中级，或者学位为博士";
//										dbHelper.runSql("update pms_projectbaseyanzheng set one = '不通过',memo1 = ? where projectbaseid = ?",new Object[]{memo,projectbaseid});
//									}
//								} else {
//									memo = "负责人年龄应小于38周岁";
//									dbHelper.runSql("update pms_projectbaseyanzheng set one = '不通过',memo1 = ? where projectbaseid = ?",new Object[]{memo,projectbaseid});
//								}
//							}else if(planprojectid.contains("LHZZ")){
//								if("正高级".equals(professionalpost)||"副高级".equals(professionalpost)||"中级".equals(professionalpost)||"博士学位".equals(degree)||"硕士学位".equals(degree)){
//									dbHelper.runSql("update pms_projectbaseyanzheng set one = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}else{
//									memo = "负责人职称为正高级或者副高级或者中级，或者学位为博士或者硕士";
//									dbHelper.runSql("update pms_projectbaseyanzheng set one = '不通过',memo1 = ? where projectbaseid = ?",new Object[]{memo,projectbaseid});
//								}
//							}
//						}
//					}else{
//						dbHelper.runSql("update pms_projectbaseyanzheng set one = '未找到项目负责人' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	//负责人一生几次
//	public void VerificateZRJJtwo(JSONObject obj) {
//		try {
//			JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//			if(obj!=null&&array.size()>0){
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("2 "+projectbaseid+" "+i);
//					String sql = "select base.id,batch.name as batch,base.mainorganizers,base.projectleader,base.submitdate,t.name as xmfzr,t.certificatenumber, " +
//							"base.planprojectid from pms_projectbase base " +
//							"left join pms_projectparty t on t.projectbaseid = base.id and t.rytype = 'true' " +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' and base.id = ?";
//					List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					if(list.size()>0){
//						for (int j = 0; j < list.size(); j++) {
//							String memo = "";
//							String xmfzr = list.get(j).get("projectleader")+"";
//							String certificatenumber = list.get(j).get("certificatenumber")+"";
//							String planprojectid = list.get(j).get("planprojectid")+"";
//							if(planprojectid.contains("YJTD")||planprojectid.contains("ZDXM")||planprojectid.contains("JCQN")||planprojectid.contains("YXQN")){
//								String fzrsql = "select t.id from pms_projectbase t left join pms_projectparty e on e.projectbaseid = t.id and e.rytype = 'true' " +
//										"where e.certificatenumber = ? and e.name = ? and t.planprojectid = ? and t.projectbaseid <> ? " +
//										"union " +
//										"select e.id from pms_old_contract_xmry e where e.certificatenumber = ? and e.name = ? and e.bigbatchid = ?";
//								List fzrlist = this.dbHelper.getRows(fzrsql,new Object[]{certificatenumber,xmfzr,planprojectid,projectbaseid,certificatenumber,xmfzr,planprojectid});
//
//								if(fzrlist.size()==0||fzrlist==null){
//									dbHelper.runSql("update pms_projectbaseyanzheng set two = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}else{
//									memo = "一生不得超过一项"+"【"+xmfzr+"】";
//									dbHelper.runSql("update pms_projectbaseyanzheng set two = '不通过',memo2 = ?  where projectbaseid = ?",new Object[]{memo,projectbaseid});
//								}
//							}else if(planprojectid.contains("LHZZ")){
//								String fzrsql = "select t.id from pms_projectbase t left join pms_projectparty e on e.projectbaseid = t.id and e.rytype = 'true' " +
//										"where e.certificatenumber = ? and e.name = ? and t.planprojectid = ? and t.projectbaseid <> ? " +
//										"union " +
//										"select e.id from pms_old_contract_xmry e where e.certificatenumber = ? and e.name = ? and e.bigbatchid = ?";
//								List fzrlist = this.dbHelper.getRows(fzrsql,new Object[]{certificatenumber,xmfzr,planprojectid,projectbaseid,certificatenumber,xmfzr,planprojectid });
//
//								if(fzrlist.size()<2||fzrlist==null){
//									dbHelper.runSql("update pms_projectbaseyanzheng set two = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}else{
//									memo = "一生不得超过二项"+"【"+xmfzr+"】";
//									dbHelper.runSql("update pms_projectbaseyanzheng set two = '不通过',memo2 = ?  where projectbaseid = ?",new Object[]{memo,projectbaseid});
//								}
//							}
//						}
//					}else{
//						dbHelper.runSql("update pms_projectbaseyanzheng set two = '未找到项目负责人' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	//负责人申报限制(立项期)
//	public void VerificateZRJJthree(JSONObject obj) {
//		try {
//			JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//			if(obj!=null&&array.size()>0){
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("3 "+projectbaseid+" "+i);
//					String sql = "select base.id,batch.name as batch,base.mainorganizers,base.projectleader,base.submitdate,t.name as xmfzr,t.certificatenumber, " +
//							"base.planprojectid from pms_projectbase base " +
//							"left join pms_projectparty t on t.projectbaseid = base.id and (t.rytype in ('true','principal') and t.classify in ('项目负责人','负责人'))" +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' and base.id = ?";
//					List<Map> list = dbHelper.getRows(sql,new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					String applicationno = "";
//					Integer xmfzrtotal = 0;
//					if(list.size()>0){
//						for (int j = 0; j < list.size(); j++) { //循环负责人
//							String xmfzr = list.get(j).get("xmfzr")+"";
//							String certificatenumber = list.get(j).get("certificatenumber")+"";
//							if (!"".equals(certificatenumber)&&!"".equals(xmfzr)) {
//								Integer xmfzrcount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from pms_projectbase t " +
//										"left join pms_planproject w on w.id = t.planprojectid " +
//										"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//										"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//										"and e.name = ? and e.certificatenumber = ? and w.applicationtypename2 = '竞争类'",new Object[]{xmfzr,certificatenumber}));
//								if(xmfzrcount>0){
//									applicationno += dbHelper.getOnlyStringValue("select t.applicationno from pms_projectbase t " +
//											"left join pms_planproject w on w.id = t.planprojectid " +
//											"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//											"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//											"and e.name = ? and e.certificatenumber = ? and w.applicationtypename2 = '竞争类'",new Object[]{xmfzr,certificatenumber})+",";
//								}
//								xmfzrtotal = xmfzrtotal + xmfzrcount;
//							}else{
//								Integer xmfzrcount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from pms_projectbase t " +
//										"left join pms_planproject w on w.id = t.planprojectid " +
//										"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//										"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//										"and e.name = ? and w.applicationtypename2 = '竞争类'",new Object[]{xmfzr}));
//								if(xmfzrcount>0){
//									applicationno += dbHelper.getOnlyStringValue("select t.applicationno from pms_projectbase t " +
//											"left join pms_planproject w on w.id = t.planprojectid " +
//											"left join pms_projectparty e on e.projectbaseid = t.id where t.lxjg is null " +
//											"and (e.rytype in ('true','principal') and e.classify in ('项目负责人','负责人')) " +
//											"and e.name = ? and w.applicationtypename2 = '竞争类'",new Object[]{xmfzr})+",";
//								}
//								xmfzrtotal = xmfzrtotal + xmfzrcount;
//							}
//						}
//
//						if(xmfzrtotal<2){
//							dbHelper.runSql("update pms_projectbaseyanzheng set three = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//						} else {
//							dbHelper.runSql("update pms_projectbaseyanzheng set three = '不通过',memo3 = ? where projectbaseid = ?",new Object[]{applicationno,projectbaseid});
//						}
//					}else{
//						dbHelper.runSql("update pms_projectbaseyanzheng set three = '未找到项目负责人' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	//负责人在研情况
//	public void VerificateZRJJfour(JSONObject obj) {
//		try {
//
//
//			JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//			if (obj != null && array.size() > 0) {
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("4 " + projectbaseid + " " + i);
//					String sql = "select base.id,batch.name as batch,base.mainorganizers,base.projectleader,base.submitdate,t.name as xmfzr,t.certificatenumber, " +
//							"base.planprojectid from pms_projectbase base " +
//							"left join pms_projectparty t on t.projectbaseid = base.id and (t.rytype in ('true','principal') and t.classify in ('项目负责人','负责人'))" +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' and base.id = ?";
//					List<Map> list = dbHelper.getRows(sql, new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?", new Object[]{projectbaseid});
//					if ("0".equals(yanzheng)) {
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)", new Object[]{Util.NewGuid(), projectbaseid});
//					}
//
//					String applicationno = "";
//					Integer xmfzrtotal = 0;
//					if (list.size() > 0) {
//						for (int j = 0; j < list.size(); j++) { //循环负责人
//							String xmfzr = list.get(j).get("xmfzr") + "";
//							String certificatenumber = list.get(j).get("certificatenumber") + "";
//							if (!"".equals(certificatenumber) && !"".equals(xmfzr)) {
//								Integer xmfzrcount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from crt_contract_jbxx jbxx " +
//										"left join pms_planproject w on w.id = jbxx.planprojectid " +
//										"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//										"and (xmry.rytype in ('true','principal') and xmry.classify = '项目负责人')" +
//										"where jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//										"and xmry.name = ? and xmry.certificatenumber = ? and w.applicationtypename2 = '竞争类'", new Object[]{xmfzr, certificatenumber}));
//								if (xmfzrcount > 0) {
//									applicationno = dbHelper.getOnlyStringValue("select jbxx.contractno from crt_contract_jbxx jbxx " +
//											"left join pms_planproject w on w.id = jbxx.planprojectid " +
//											"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//											"and (xmry.rytype in ('true','principal') and xmry.classify = '项目负责人')" +
//											"where jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//											"and xmry.name = ? and xmry.certificatenumber = ? and w.applicationtypename2 = '竞争类'", new Object[]{xmfzr, certificatenumber}) + ",";
//								}
//								xmfzrtotal = xmfzrtotal + xmfzrcount;
//							} else {
//								Integer xmfzrcount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from crt_contract_jbxx jbxx " +
//										"left join pms_planproject w on w.id = jbxx.planprojectid " +
//										"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//										"and (xmry.rytype in ('true','principal') and xmry.classify = '项目负责人')" +
//										"where jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//										"and xmry.name = ? and w.applicationtypename2 = '竞争类'", new Object[]{xmfzr}));
//								if (xmfzrcount > 0) {
//									applicationno = dbHelper.getOnlyStringValue("select jbxx.contractno from crt_contract_jbxx jbxx " +
//											"left join pms_planproject w on w.id = jbxx.planprojectid " +
//											"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//											"and (xmry.rytype in ('true','principal') and xmry.classify = '项目负责人')" +
//											"where jbxx.isys is null and jbxx.isendcontract is null and jbxx.chieldofficals is not null " +
//											"and xmry.name = ? and w.applicationtypename2 = '竞争类'", new Object[]{xmfzr}) + ",";
//								}
//								xmfzrtotal = xmfzrtotal + xmfzrcount;
//							}
//
//						}
//
//						if (xmfzrtotal == 0) {
//							dbHelper.runSql("update pms_projectbaseyanzheng set four = '通过' where projectbaseid = ?", new Object[]{projectbaseid});
//						} else {
//							dbHelper.runSql("update pms_projectbaseyanzheng set four = '不通过',memo4 = ? where projectbaseid = ?", new Object[]{applicationno, projectbaseid});
//						}
//					} else {
//						dbHelper.runSql("update pms_projectbaseyanzheng set four = '未找到项目负责人' where projectbaseid = ?", new Object[]{projectbaseid});
//					}
//
//				}
//			}
//		} catch (SQLException e){
//			e.printStackTrace();
//		}
//	}
//
//	//参与人员职称、学位等
//	public void VerificateZRJJfive(JSONObject obj) {
//		try {
//			JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//			if (obj != null && array.size() > 0) {
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("5 " + projectbaseid + " " + i);
//					String sql = "select base.id,base.planprojectid,t.name as xmcyr,t.certificatenumber,t.degree,t.professionalpost,t.birthday,base.submitdate from pms_projectbase base " +
//							"left join pms_projectparty t on t.projectbaseid = base.id and t.classify = '研究骨干' " +
//							"where base.id = ?";
//					List<Map> list = dbHelper.getRows(sql, new Object[]{projectbaseid});
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?", new Object[]{projectbaseid});
//					if ("0".equals(yanzheng)) {
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)", new Object[]{Util.NewGuid(), projectbaseid});
//					}
//
//					String planprojectid = list.get(0).get("planprojectid") + "";
//
//					if (planprojectid.contains("YJTD")) {
//						if (list.size() > 0) {
//							if(list.size() > 5) {
//								dbHelper.runSql("update pms_projectbaseyanzheng set five = '研究骨干不得大于5人' where projectbaseid = ?", new Object[]{projectbaseid});
//							}else{
//								String memo = "";
//								int xyws = 0;//小于50岁统计
//								for (int j = 0; j < list.size(); j++) {
//									String birthday = list.get(j).get("birthday") + "";
//									String Submitdate = list.get(j).get("submitdate") + "";
//									String degree = list.get(j).get("degree") + "";
//									String professionalpost = list.get(j).get("professionalpost") + "";
//									if ("正高级".equals(professionalpost) || "副高级".equals(professionalpost) || "博士学位".equals(degree)) {
//										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//										Date birth = sdf.parse(birthday);
//										Calendar calendar = Calendar.getInstance();
//										calendar.setTime(birth);
//										calendar.add(calendar.YEAR, 50);
//										Date endbirthdate = calendar.getTime();//生日+45
//										Date submitdate = sdf.parse(Submitdate);//上报时间
//										long todate = submitdate.getTime();
//										long endbirthday = endbirthdate.getTime();
//										if (todate < endbirthday) { //小于50岁
//											xyws = xyws + 1;
//										}
//									} else {
//										memo = "研究骨干职称职称为正高级或者副高级，或者学位为博士";
//										dbHelper.runSql("update pms_projectbaseyanzheng set five = '不通过',memo5 = ? where projectbaseid = ?", new Object[]{memo, projectbaseid});
//										break;
//									}
//								}
//								float percent = ((float)xyws/list.size())*100;
//								if(percent>40){
//									dbHelper.runSql("update pms_projectbaseyanzheng set five = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//								}else{
//									memo = "年龄小于50周岁的参与人（类型是研究骨干）占参与人（类型是研究骨干）总数，要大于等于五分之二";
//									dbHelper.runSql("update pms_projectbaseyanzheng set five = '不通过',memo5 = ? where projectbaseid = ?",new Object[]{memo,projectbaseid});
//									break;
//								}
//							}
//						}else{
//							dbHelper.runSql("update pms_projectbaseyanzheng set five = '未找到项目参与人' where projectbaseid = ?", new Object[]{projectbaseid});
//						}
//					}else{
//						dbHelper.runSql("update pms_projectbaseyanzheng set five = '通过' where projectbaseid = ?", new Object[]{projectbaseid});
//					}
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}
//
//	//参与人在研记录
//	public void VerificateZRJJsix(JSONObject obj) {
//		try{
//			JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//			if(obj!=null&&array.size()>0){
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("6 "+projectbaseid+" "+i);
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					List<Map> cyrylist = dbHelper.getRows("select * from (select base.id,batch.name as batch,party.name,party.certificatenumber " +
//							"from pms_projectbase base " +
//							"left join pms_projectparty party on party.projectbaseid = base.id " +
//							"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//							"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' " +
//							"and party.rytype = 'principal' and party.classify not in ('项目负责人','负责人') and party.projectbaseid = ? order by party.seq) ",
//							new Object[]{projectbaseid});
//
//					Integer cyrytotal = 0;
//					if(cyrylist.size()>0){
//						for (int j = 0; j < cyrylist.size(); j++) {
//							String cyryname = cyrylist.get(j).get("name").toString();
//							String cyrycertnumber = cyrylist.get(j).get("certificatenumber").toString();
//							String zycontractno = "";
//							if (!"".equals(cyrycertnumber)&&!"".equals(cyryname)) {
//								Integer cyrycount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from crt_contract_jbxx jbxx " +
//										"left join pms_planproject w on w.id = jbxx.planprojectid " +
//										"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//										"where jbxx.isys is null and jbxx.isendcontract is null " +
//										"and xmry.rytype = 'true' and xmry.classify not in ('项目负责人','负责人') and xmry.name = ? " +
//										"and xmry.certificatenumber = ? and w.id like 'HLJSZRKXJJPLAN%'", new Object[]{cyryname,cyrycertnumber}));
//								if(cyrycount>0){
//									List<Map> contractnolist = dbHelper.getRows("select jbxx.contractno from crt_contract_jbxx jbxx " +
//											"left join pms_planproject w on w.id = jbxx.planprojectid " +
//											"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//											"where jbxx.isys is null and jbxx.isendcontract is null " +
//											"and xmry.rytype = 'true' and xmry.classify not in ('项目负责人','负责人') and xmry.name = ? " +
//											"and xmry.certificatenumber = ? and w.id like 'HLJSZRKXJJPLAN%", new Object[]{cyryname,cyrycertnumber});
//									for (int k = 0; k < contractnolist.size(); k++) {
//										zycontractno += contractnolist.get(k).get("contractno")+",";
//									}
//								}
//								cyrytotal = cyrytotal + cyrycount;
//							}else{
//								Integer cyrycount = Integer.parseInt(dbHelper.getOnlyStringValue("select count(*) from crt_contract_jbxx jbxx " +
//										"left join pms_planproject w on w.id = jbxx.planprojectid " +
//										"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//										"where jbxx.isys is null and jbxx.isendcontract is null " +
//										"and xmry.rytype = 'true' and xmry.classify not in ('项目负责人','负责人') and xmry.name = ? " +
//										"and w.id like 'HLJSZRKXJJPLAN%", new Object[]{cyryname}));
//								if(cyrycount>0){
//									List<Map> contractnolist = dbHelper.getRows("select jbxx.contractno from crt_contract_jbxx jbxx " +
//											"left join pms_planproject w on w.id = jbxx.planprojectid " +
//											"left join crt_contract_xmry xmry on xmry.contractid = jbxx.id " +
//											"where jbxx.isys is null and jbxx.isendcontract is null " +
//											"and xmry.rytype = 'true' and xmry.classify not in ('项目负责人','负责人') and xmry.name = ? " +
//											"and w.id like 'HLJSZRKXJJPLAN%", new Object[]{cyryname});
//									for (int k = 0; k < contractnolist.size(); k++) {
//										zycontractno += contractnolist.get(k).get("contractno")+",";
//									}
//								}
//								cyrytotal = cyrytotal + cyrycount;
//							}
//
//							if(cyrytotal<2){
//								dbHelper.runSql("update pms_projectbaseyanzheng set six = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//							} else {
//								dbHelper.runSql("update pms_projectbaseyanzheng set six = '不通过',memo6 = ? where projectbaseid = ?",new Object[]{zycontractno,projectbaseid});
//								break;
//							}
//						}
//					} else {
//						dbHelper.runSql("update pms_projectbaseyanzheng set six = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			}
//		} catch (SQLException e){
//			e.printStackTrace();
//		}
//	}
//
//	//参与人工作单位
//	public void VerificateZRJJseven(JSONObject obj) {
//		try{
//			JSONArray array = (JSONArray) obj.getJSONArray("mySelections");
//			if(obj!=null&&array.size()>0){
//				for (int i = 0; i < array.size(); i++) {
//					String projectbaseid = array.getJSONObject(i).getString("id");
//					System.out.println("7 "+projectbaseid+" "+i);
//
//					String yanzheng = dbHelper.getOnlyStringValue("select count(*) from pms_projectbaseyanzheng where projectbaseid = ?",new Object[]{projectbaseid});
//					if("0".equals(yanzheng)){
//						dbHelper.runSql("insert into pms_projectbaseyanzheng (id,projectbaseid) values (?,?)",new Object[]{Util.NewGuid(),projectbaseid});
//					}
//
//					List<Map> cyrylist = dbHelper.getRows("select * from (select base.id,batch.name as batch,party.name,party.certificatenumber, " +
//									"party.workplace from pms_projectbase base " +
//									"left join pms_projectparty party on party.projectbaseid = base.id " +
//									"left join pms_planprojectbatch batch on base.planprojectbatchid = batch.id " +
//									"where base.minicurrentstate is not null and base.minicurrentstate <> '项目申报:用户填报' " +
//									"and party.rytype = 'principal' and party.classify not in ('项目负责人','负责人') and party.projectbaseid = ? order by party.seq) ",
//							new Object[]{projectbaseid});
//
//					if(cyrylist.size()>0){
//						for (int j = 0; j < cyrylist.size(); j++) {
//							String name = cyrylist.get(j).get("name")+"";
//							String workplace = cyrylist.get(j).get("workplace")+"";
//
//							List dwlist = this.dbHelper.getRows("select id from pms_enterpriseorganization where projectbaseid = ? and organizationname = ?",new Object[]{projectbaseid,workplace});
//							if(dwlist.size()>0){
//								continue;
//							}else{
//								dbHelper.runSql("update pms_projectbaseyanzheng set seven = '不通过',memo7 = ? where projectbaseid = ?",new Object[]{name+"【"+workplace+"】",projectbaseid});
//								break;
//							}
//						}
//						dbHelper.runSql("update pms_projectbaseyanzheng set seven = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//					}else{
//						dbHelper.runSql("update pms_projectbaseyanzheng set seven = '通过' where projectbaseid = ?",new Object[]{projectbaseid});
//					}
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public String getBatchidByBatchname(String batchname){
//		String sql = "select id from pms_planprojectbatch where name = ?";
//		return this.getOnlyValueBySql(sql, new Object[]{batchname});
//	}
//
//	//单独评审表的对应批次
//	public List<Map> getPsBatchByDepartment(String departmentid,String search){
//		ArrayList<String> paraList = new ArrayList<String>();
//
//		String  sql = "select tt.id,t.name as dname, t.id as did ,tt.name as  sname,tt.annually from pms_planproject_review t inner join pms_planprojectbatch tt on t.id = tt.batchreviewid where 1=1";
//
//		if(search!=null&&""!=(search.trim())){
//			sql += " and tt.name like ? ";
//			paraList.add("%"+search+"%");
//		}
//		if(departmentid!=null){
//			sql += " and t.departmentid = ? ";
//			paraList.add(departmentid);
//		}
//		List<Map> list = this.dbHelper.getRows(sql,paraList.toArray());
//		return list;
//	}
//
//	public List<Map> getPsBatchByDepartmentid(String departmentid){
//		String sql = "select * from pms_planproject_review t where t.departmentid = ?";
//		List<Map> list = this.dbHelper.getRows(sql,new Object[]{departmentid});
//		return list;
//	}
//
//	public boolean operatePsBatch(JSONObject jsonObject){
//		PmsPlanprojectbatch pmsPlanprojectbatch = null;
//		String type = jsonObject.getString("type");
//		switch (type){
//			case "add":
//				pmsPlanprojectbatch = new PmsPlanprojectbatch();
//				pmsPlanprojectbatch.setId(UUID.randomUUID().toString());
//				pmsPlanprojectbatch.setName(jsonObject.getString("sname"));
//				pmsPlanprojectbatch.setAnnually(jsonObject.getString("annually"));
//				pmsPlanprojectbatch.setBatchreviewid(jsonObject.getString("did"));
//				this.merge(pmsPlanprojectbatch);
//				break;
//			case "update":
//				pmsPlanprojectbatch = this.getPmsPlanprojectbatch().findById(jsonObject.getString("id"));
//				pmsPlanprojectbatch.setName(jsonObject.getString("sname"));
//				pmsPlanprojectbatch.setAnnually(jsonObject.getString("annually"));
//				pmsPlanprojectbatch.setBatchreviewid(jsonObject.getString("did"));
//				this.merge(pmsPlanprojectbatch);
//				break;
//			case "delete":
//				pmsPlanprojectbatch = this.getPmsPlanprojectbatch().findById(jsonObject.getString("id"));
//				this.delete(pmsPlanprojectbatch);
//				break;
//		}
//		return true;
//	}
//
//
//	/**
//	 * 【合同签订时间范围】 --- 判断是否在合同签订时间范围内
//	 * @param contractid
//     */
//	public boolean judgeContractTime(String contractid) {
//		CrtContractJbxx j = this.crtContractJbxxService.findById(contractid);
//		if (Util.isEoN(j.getPlanprojectbatchid())) {
//			return false;//true表示可以进行接下来操作，false表示不在时间范围内，被拦截
//		}
//		PmsPlanprojectbatch p = this.findById(j.getPlanprojectbatchid());
//		if (Util.isEoN(p)) {
//			return false;//true表示可以进行接下来操作，false表示不在时间范围内，被拦截
//		}
//		//判断有没有设置合同签订开始时间或者结束时间
//		if (Util.isEoN(p.getCrt_startdate()) && Util.isEoN(p.getCrt_enddate())) {
//			//说明该批次没有设置  签订时间，都可以进行接下来操作
//			return true;//true表示可以进行接下来操作，false表示不在时间范围内，被拦截
//		}else{
//			//说明该批次设置了  签订时间，要进行判断
//			//1、判断状态是否是   空、用户填写、所在单位审核，
//			String minicurrentstate = j.getMinicurrentstate();
//			if(Util.isEoN(minicurrentstate)||"合同签订:用户填报".equals(minicurrentstate)||"合同签订:所在单位审核".equals(minicurrentstate)){
//				//2、判断是否在时间范围内
//				Date nowDate = new Date();
//				if(nowDate.getTime()>=p.getCrt_startdate().getTime()&&nowDate.getTime()<=p.getCrt_enddate().getTime()){
//					//说明在时间范围内
//					return true;//true表示可以进行接下来操作，false表示不在时间范围内，被拦截
//				}else{
//					//判断是否有  所在单位以后的环节的退回标记
//					if(Util.isEoN(j.getDwlaterretunflag())){
//						//说明为空
//						return false;//true表示可以进行接下来操作，false表示不在时间范围内，被拦截
//					}else{
//						if("1".equals(j.getDwlaterretunflag())){
//							return true;//true表示可以进行接下来操作，false表示不在时间范围内，被拦截
//						}else{
//							return false;//true表示可以进行接下来操作，false表示不在时间范围内，被拦截
//						}
//					}
//				}
//			}else{
//				//如果不是 空、用户填写、所在单位审核，那么就不用判断时间
//				return true;//true表示可以进行接下来操作，false表示不在时间范围内，被拦截
//			}
//		}
//	}

    /**
     * 【通过批次id获取批次名词】
     *
     * @param id
     * @return
     */
    public Map getBatchnameById(String id) {
        Map map = new HashedMap();
        List<Map> list = this.dbHelper.getRows("select name from pms_planprojectbatch e where e.id = ?", new Object[]{id});
        if (list != null && list.size() > 0) {
            map = list.get(0);
        }
        return map;
    }

//	/**
//	 * 【通过批次id获取批次名词】根据主表id查询对应的批次名字 有old用old
//	 * @param projectbaseid 项目id
//	 * @return
//	 */
//	public Map getBatchNameByIdForShowPage(String projectbaseid, String type){
//		StringBuilder currentBatchId = new StringBuilder();
//		Map map = new HashedMap();
//		String simpleName = TableMappingEnum.getEnumByTablename(type).name();
//		simpleName = Util.isEoN(simpleName) == true ? "pms_projectbase" : simpleName;
//		//龙江有oldbatch
//		//String sql = "select distinct p.NAME from " + simpleName + " t  inner join PMS_PLANPROJECTBATCH p on case when t.OLDPLANBATCHID is not null then t.OLDPLANBATCHID else t.PLANPROJECTBATCHID end = p.ID where t.id = ?";
//		String sql = "select distinct p.NAME from " + simpleName + " t  inner join PMS_PLANPROJECTBATCH p on  t.PLANPROJECTBATCHID  = p.ID where t.id = ?";
//		List<Map> rows = dbHelper.getRows(sql, new Object[]{projectbaseid});
//		if(rows.size() > 0) {
//			map.put("name",rows.get(0).get("name"));
//		}
//		return map;
//	}
//
//
//	/**
//	 * 【判断当前批是否为老批次】
//	 * @param batchid
//	 * @return
//     */
//	public Boolean judgeIsOldBatch(String batchid){
//		PmsPlanprojectbatch b  = this.findById(batchid);
//		if("old".equals(b.getOlddata())){
//			return true;
//		}else{
//			return false;
//		}
//	}
//
//	public List<Map> getOutSysBatchList(){
//		String sql = "select t.id, p.projectname bigbatchname, t.name batchname from pms_planprojectbatch t " +
//				"left join pms_planproject p on p.id=t.planprojectid where t.olddata='outsys' order by p.projectname desc,t.name";
//		return this.dbHelper.getRows(sql, null);
//	}
//
//	/**
//	 * 获取所有申报限制条件
//	 * @return
//     */
//	public List<Map> loadAllConditions(){
//		return this.dbHelper.getRows("select pp.*,clob.source as conditionscript from pms_plancondition pp left join pms_clob clob on pp.id = clob.sourceid order by pp.seq",null);
//	}
//
//	/**
//	 * 根据条件id获取绑定批次
//	 * @param id
//	 * @return
//     */
//	public String getBatchByConditionid(String id){
//		List<Map> list = this.dbHelper.getRows("select b.name from pms_plancondition_project e " +
//				" left join pms_planprojectbatch b on e.planorbatchid = b.id " +
//				" where e.conditionid = ? " +
//				" order by b.planprojectid,b.annually",new Object[]{id});
//		String batchStr = "";
//		for (Map m :list) {
//			batchStr = batchStr +m.get("name")+";\r ";
//		}
//		return batchStr;
//	}
//
//
//	/**
//	 *  【产品化整体配置】-- 根据大批次id获取所有小批次
//	 * @param bigbatchid
//	 * @return
//	 */
//	public List<Map> getBatchByBigbatchid(String bigbatchid){
//		return this.dbHelper.getRows(" select rownum as xh,tt.* from (" +
//				"select e.* from pms_planprojectbatch e where e.planprojectid = ? order by e.annually desc) tt",new Object[]{bigbatchid});
//	}
//
//
//
//
//	/**
//	 * 【产品化整体配置】-- 新增或修改大批次
//	 * @param request
//	 * @param json
//	 */
//	public JSONObject addAndEditBatch(HttpServletRequest request, JSONObject json, String bigbatchid){
//		JSONObject resJson = new JSONObject();
//		String type = "";//新增或者修改
//		String id = json.get("id")+"";
//		PmsPlanprojectbatch p = this.findById(id);
//		if(!Util.isEoN(p)){
//			//说明是修改，判断是否可以修改
//			JSONObject judgeJson = this.judgeEditBatch(json,bigbatchid);
//			if(!judgeJson.getBoolean("success")){
//				return judgeJson;
//			}
//			type = "小批次修改";
//			Util.ApplyObject(p,json);
//		}else{
//			//说明是新增，判断是否可以新增
//			JSONObject judgeJson = this.judgeAddBatch(json,bigbatchid);
//			if(!judgeJson.getBoolean("success")){
//				return judgeJson;
//			}
//			p = new PmsPlanprojectbatch();
//			type = "小批次新增";
//			Util.ApplyObject(p,json);
//			//新增默认值：
//			p.setPdfurlforbase("D:/PdfTmpFile/PmsProjectbase");
//			p.setPdfurlforcontract("D:/PdfTmpFile/CrtContract");
//			p.setPdfdowntypeforbase("bookmark");
//			p.setPdfdowntypeforcontract("bookmark");
//			p.setCurrentstate("2");//默认启用
//			p.setAppliyLimit(false);//默认没有申报限制
//			p.setCity("省级");
//		}
//		//默认值：
//		p.setPmsPlanproject(this.pmsPlanprojectService.findById(bigbatchid));
//		//保存或修改
//		this.merge(p);
//		//保存批次版本表
//		BatchVersion bv = this.batchVersionService.findById(id);
//		if(Util.isEoN(bv)){
//			bv = new BatchVersion();
//			bv.setId(id);
//			this.batchVersionService.merge(bv);
//		}
//		//操作记录
//		this.sysOperationrecordService.commonSaveOperation(request,id,type,"本次保存的小批次数据："+json.toString());
//		resJson.put("success",true);
//		return resJson;
//	}
//
//	/**
//	 * 【产品化整体配置】-- 判断是否可以新增小批次
//	 * @param json
//     * @return
//     */
//	public JSONObject judgeAddBatch(JSONObject json,String bigbatchid){
//		JSONObject resJson = new JSONObject();
//		String year = json.get("annually")+"";
//		List<Map> list = this.dbHelper.getRows("select e.id from pms_planprojectbatch e where e.planprojectid = ? and e.annually = ?",new Object[]{bigbatchid,year});
//		if(list.size()>0){
//			resJson.put("success",false);
//			resJson.put("reason","当前选择的年份已存在。");
//		}else{
//			resJson.put("success",true);
//		}
//		return resJson;
//	}
//
//	/**
//	 * 【产品化整体配置】-- 判断是否可以修改小批次
//	 * @param json
//	 * @return
//	 */
//	public JSONObject judgeEditBatch(JSONObject json,String bigbatchid){
//		JSONObject resJson = new JSONObject();
//		String year = json.get("annually")+"";
//		PmsPlanprojectbatch b = this.findById(json.get("id")+"");
//		if(year.equals(b.getAnnually())){
//			resJson.put("success",true);
//		}else{
//			List<Map> list = this.dbHelper.getRows("select e.id from pms_planprojectbatch e where e.planprojectid = ? and e.annually = ?",new Object[]{bigbatchid,year});
//			if(list.size()>0){
//				resJson.put("success",false);
//				resJson.put("reason","当前选择的年份已存在。");
//			}else{
//				resJson.put("success",true);
//			}
//		}
//		return resJson;
//	}
//
//	/**
//	 * 【产品化整体配置】-- 删除小批次
//	 * @param system
//	 * @param id
//     * @return
//     */
//	public JSONObject deleteBatchById(HttpServletRequest request, String system, String id){
//		JSONObject resJson = new JSONObject();
//		List<String> sqlList = new ArrayList<>();
//		List<String> reasonList = new ArrayList<>();
//		switch (system){
//			case "kjxm":
//				sqlList.add("select e.id from pms_projectbase e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-申报业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_project_recommendation e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-申报推荐函业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from crt_contract_jbxx e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-合同业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_conclusion e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-验收业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_keepcase e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-孵化器备案业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_casereport e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-孵化器年报业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_talentselection e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-特派员发布需求业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_talentselection_tjry e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-特派员工作协议业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_talentmonthreport e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-特派员月度报告业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_talentachievement e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-特派员科技成果业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_talenttrain e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-特派员培训计划业务数据绑定该批次，不能删除。");
//				sqlList.add("select e.id from pms_talentworkreport e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技项目-特派员工作总结业务数据绑定该批次，不能删除。");
//				break;
//			case "kjjl":
//				sqlList.add("select e.id from pms_reward e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技奖励业务数据绑定该批次，不能删除。");
//				break;
//			case "kjcxpt":
//				sqlList.add("select e.id from pms_innovationbase e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技创新平台业务数据绑定该批次，不能删除。");
//				break;
//			case "kjcxpt_ndbg":   //这里表名得换，但还没新建表
//				sqlList.add("select e.id from pms_innovationbase e where e.planprojectbatchid = ?");
//				reasonList.add("还有科技创新平台业务数据绑定该批次，不能删除。");
//				break;
//			default:
//				resJson.put("success",false);
//				resJson.put("reason","没有传批次类型，不能删除。");
//				return resJson;
//		}
//		for (int i = 0; i < sqlList.size(); i++) {
//			List<Map> list = this.dbHelper.getRows(sqlList.get(i),new Object[]{id});
//			if(list.size()>0){
//				resJson.put("success",false);
//				resJson.put("reason",reasonList.get(i));
//				return resJson;
//			}
//		}
//		//因为jsonobject.fromobject 小批次对象会报错
//		List<Map> batchList = this.dbHelper.getRows("select e.* from pms_planprojectbatch e where e.id = ?",new Object[]{id});
//		if(batchList.size()>0){
//			//操作记录
//			this.sysOperationrecordService.commonSaveOperation(request,id,"小批次删除","本次删除的小批次数据："+JSONObject.fromObject(batchList.get(0)).toString());
//		}
//		this.runSql("delete from sys_version_affix where batchid = ?",new Object[]{id});
//		this.runSql("delete from pms_enterpriselimit where batchid = ?",new Object[]{id});
//		this.runSql("delete from batch_version where id = ?",new Object[]{id});
//		this.runSql("delete from pms_planprojectbatch where id = ?",new Object[]{id});
//		resJson.put("success",true);
//		return resJson;
//	}
//
//
//	/**
//	 * 【产品化整体配置】-- 根据小批次id和业务类型来获取批次配置数据
//	 * @param type
//     * @return
//     */
//	public JSONObject getBatchConfigData(String batchid,String type){
//		JSONObject resJson = new JSONObject();
//		PmsPlanprojectbatch batch = this.findById(batchid);
//		BatchVersion bv = this.batchVersionService.findById(batchid);
//		//--------1、页面配置：获取版本ID-----------
//		resJson.put("versionid",getOrSaveVersionid(type,bv,"get",""));
////		//--------2、流程配置：获取流程ID-----------
////		resJson.put("flowid",this.flowDefineService.findByBidAndType(batchid, type)==null?"":this.flowDefineService.findByBidAndType(batchid, type).getId());
//		//--------3、小批次其他参数配置-----------
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setExcludes(new String[]{"pmsPlanprojectbatchs","pmsDeclarationguide","pmsPagetemplate","pmsProjectbases","pmsPlanproject",
//				"revProjectInTeams","revTeams","revHpTeams","revZpTeams","revTeamconditions","revHpTeamconditions","revZpTeamconditions","revExpertInTeams"});//排除pmsPlanproject对象
//		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));//设置时间格式
//		//根据类型特殊处理配置数据，目前是cr：孵化器年报的参数是存在大批次里
//		if("cr".equals(type)){
//			PmsPlanproject plan = batch.getPmsPlanproject();
//			resJson.put("paramData",JSONObject.fromObject(plan,jsonConfig));
//		}else{
//			resJson.put("paramData",JSONObject.fromObject(batch,jsonConfig));
//		}
//		return resJson;
//	}
//
//	/**
//	 * 【产品化整体配置】-- 获取对应的版本id
//	 * @param type
//     * @return
//     */
//	private String getOrSaveVersionid(String type,BatchVersion bv,String getOrSave,String versionid){
//		try{
//			//获取批次表对应的versionid字段名称
//			if(!Util.isEoN(type)){
//				if("save".equals(getOrSave)){
//					Method m = bv.getClass().getMethod("set"+type.substring(0,1).toUpperCase()+type.substring(1,type.length()).toLowerCase(), String.class);
//					m.invoke(bv, versionid);
//				}else{
//					Method m = bv.getClass().getMethod("get"+type.substring(0,1).toUpperCase()+type.substring(1,type.length()).toLowerCase());
//					return (String)m.invoke(bv);
//				}
//			}
//		}catch (Exception e){
//			return "";
//		}
//		if("save".equals(getOrSave)){
//			this.batchVersionService.merge(bv);
//			return "";
//		}else{
//			return "";
//		}
////		switch (type){
////			case "sb":
////				if("save".equals(getOrSave)){
////					batch.setVersionsbid(versionid);
////				}else{
////					return batch.getVersionsbid();
////				}
////				break;
////			case "ht":
////				if("save".equals(getOrSave)){
////					batch.setVersionhtid(versionid);
////				}else{
////					return batch.getVersionhtid();
////				}
////				break;
////			case "zqjc":
////				if("save".equals(getOrSave)){
////					batch.setVersionzqjcid(versionid);
////				}else{
////					return batch.getVersionzqjcid();
////				}
////				break;
////			case "ndbg":
////				if("save".equals(getOrSave)){
////					batch.setVersionndbgid(versionid);
////				}else{
////					return batch.getVersionndbgid();
////				}
////				break;
////			case "kc":
////				if("save".equals(getOrSave)){
////					batch.setVersionkcid(versionid);
////				}else{
////					return batch.getVersionkcid();
////				}
////				break;
////			case "cr":
////				if("save".equals(getOrSave)){
////					batch.setVersioncrid(versionid);
////				}else{
////					return batch.getVersioncrid();
////				}
////				break;
////			case "kjbg":
////				if("save".equals(getOrSave)){
////					batch.setVersionkjbgid(versionid);
////				}else{
////					return batch.getVersionkjbgid();
////				}
////				break;
////			case "techAwards":
////				if("save".equals(getOrSave)){
////					batch.setVersionkjjlid(versionid);
////				}else{
////					return batch.getVersionkjjlid();
////				}
////				break;
////			case "ys":
////				if("save".equals(getOrSave)){
////					batch.setVersionysid(versionid);
////				}else{
////					return batch.getVersionysid();
////				}
////				break;
////			case "fbxq":
////				if("save".equals(getOrSave)){
////					batch.setVersionfbxqid(versionid);
////				}else{
////					return batch.getVersionfbxqid();
////				}
////				break;
////			case "kjtpy_tjb":
////				if("save".equals(getOrSave)){
////					batch.setVersionkjtpytjbid(versionid);
////				}else{
////					return batch.getVersionkjtpytjbid();
////				}
////				break;
////			case "kjtpy_yb":
////				if("save".equals(getOrSave)){
////					batch.setVersionkjtpyybid(versionid);
////				}else{
////					return batch.getVersionkjtpyybid();
////				}
////				break;
////			case "kjtpy_cgfb":
////				if("save".equals(getOrSave)){
////					batch.setVersionkjtpycgfbid(versionid);
////				}else{
////					return batch.getVersionkjtpycgfbid();
////				}
////				break;
////			case "kjtpy_px":
////				if("save".equals(getOrSave)){
////					batch.setVersionkjtpypxid(versionid);
////				}else{
////					return batch.getVersionkjtpypxid();
////				}
////				break;
////			case "kjtpy_gzzj":
////				if("save".equals(getOrSave)){
////					batch.setVersionkjtpygzzjid(versionid);
////				}else{
////					return batch.getVersionkjtpygzzjid();
////				}
////				break;
////			case "sbtjh":
////				if("save".equals(getOrSave)){
////					batch.setVersionsbtjhid(versionid);
////				}else{
////					return batch.getVersionsbtjhid();
////				}
////				break;
////			case "kjcxpt":
////				if("save".equals(getOrSave)){
////					batch.setVersionkjcxptid(versionid);
////				}else{
////					return batch.getVersionkjcxptid();
////				}
////				break;
////			case "kjcxpt_ndbg":
////				if("save".equals(getOrSave)){
////					batch.setVersionkjcxptndbgid(versionid);
////				}else{
////					return batch.getVersionkjcxptndbgid();
////				}
////				break;
////		}
////		if("save".equals(getOrSave)){
////			this.merge(batch);
////			return "";
////		}else{
////			return "";
////		}
//	}
//
//	/**
//	 * 【产品化整体配置】-- 获取其他同大批次下的批次，目前附件配置有用到
//	 * @param batchid
//	 * @return
//     */
//	public List<Map> getOtherBatch(String batchid){
//		String bigbatchid = this.getOnlyValueBySql("select e.planprojectid from pms_planprojectbatch e where e.id = ?",new Object[]{batchid});
//		return this.dbHelper.getRows("select e.id,e.name from pms_planprojectbatch e where e.planprojectid = ? and e.id <> ? order by e.annually",new Object[]{bigbatchid,batchid});
//	}
//
//
//	/**
//	 * 【产品化整体配置】-- 根据小批次Id和业务类型 ，保存版本Id
//	 * @param request
//	 * @param batchid
//	 * @param type
//	 * @param versionid
//     */
//	public void saveVersionid(HttpServletRequest request, String batchid, String type, String versionid){
//		if(Util.isEoN(versionid)){
//			versionid = "";
//		}
////		PmsPlanprojectbatch p = this.findById(batchid);
//		BatchVersion bv = this.batchVersionService.findById(batchid);
//		if(Util.isEoN(bv)){
//			bv = new BatchVersion();
//			bv.setId(batchid);
//		}
//		this.getOrSaveVersionid(type,bv,"save",versionid);
//		//操作记录
//		this.sysOperationrecordService.commonSaveOperation(request,batchid,"保存版本id","当前保存的数据：批次id："+batchid+"；业务类型："+type+"；版本id："+versionid);
//	}
//
//	/**
//	 * 【产品化整体配置】-- 根据小批次id，保存配置参数
//	 * @param request
//	 * @param batchid
//	 * @param type
//     * @param json
//     */
//	public void saveParamData(HttpServletRequest request, String batchid, String type, JSONObject json){
//		PmsPlanprojectbatch b = this.findById(batchid);
//		//type=cr；孵化器年报时，有部分字段是存大批次对象里，需要特殊处理
//		if("cr".equals(type)){
//			PmsPlanproject p = b.getPmsPlanproject();
//			Util.ApplyObject(p,json);
//			this.pmsPlanprojectService.merge(p);
//		}else{
//			Util.ApplyObject(b,json);
//			this.merge(b);
//		}
//		//操作记录
//		this.sysOperationrecordService.commonSaveOperation(request,batchid,"保存批次配置参数","当前保存的配置参数数据："+json.toString());
//	}
//
//	/**
//	 *【产品化整体配置】-- 复制版本id
//	 */
//	public void copyVersionid(String newbatchid,String oldbatchid){
//		BatchVersion oldB = this.batchVersionService.findById(oldbatchid);
//		BatchVersion newB = this.batchVersionService.findById(newbatchid);
//		//复制属性
//		BeanUtils.copyProperties(oldB,newB);
//		newB.setId(newbatchid);
//		this.batchVersionService.merge(newB);
////		String delSql = "";//赋值为空的语句
////		String copySql = "";//复制的语句
////		for(SysVersionEnum e : SysVersionEnum.values()){
////			if(!Util.isEoN(e.getVersionidName())){
////				delSql = delSql + "e."+e.getVersionidName() + " = '',";
////				copySql = copySql + "e."+e.getVersionidName()+"=(select b."+e.getVersionidName()+" from pms_planprojectbatch b where b.id = '"+oldbatchid+"'),";
////			}
////		}
////		if(!Util.isEoN(delSql)){
////			delSql = delSql.substring(0,delSql.length()-1);
////		}
////		if(!Util.isEoN(copySql)){
////			copySql = copySql.substring(0,copySql.length()-1);
////		}
////		//先将所有版本id字段赋值为空
////		this.runSql("update pms_planprojectbatch e set "+delSql+" where e.id = ?",new Object[]{newbatchid});
////		//再执行复制版本id
////		this.runSql("update pms_planprojectbatch e set "+copySql+" where e.id = ?",new Object[]{newbatchid});
//	}
//
//	/**
//	 *【产品化整体配置】-- 复制附件配置数据
//	 */
//	public void copyAffix(String newbatchid,String oldbatchid){
//		//先清空数据
//		this.runSql("delete from sys_version_affix where batchid = ?",new Object[]{newbatchid});
//		//复制数据
//		List<SysVersionAffix> oldList = this.findByHql("from SysVersionAffix where batchid = ?",new Object[]{oldbatchid});
//		for(SysVersionAffix oldObj : oldList){
//			SysVersionAffix newObj = new SysVersionAffix();
//			Util.ApplyObject(newObj,JSONObject.fromObject(oldObj));
//			newObj.setId(Util.NewGuid());
//			newObj.setBatchid(newbatchid);
//			this.sysVersionAffixService.merge(newObj);
//		}
//	}
//
//	/**
//	 *【产品化整体配置】-- 复制指标配置数据
//	 */
//	public boolean copyLimitNum(String newbatchid,String oldbatchid){
//		//判断是否有已经使用指标的数据
//		List<PmsEnterpriselimit> useList = this.findByHql("from PmsEnterpriselimit where batchid = ? " +
//				" and recommendnum is not null and recommendnum<>0",new Object[]{newbatchid});
//		if(useList.size()>0){
//			//说明已经有使用指标的数据，不可执行下面的复制
//			return false;
//		}
//		//先清空数据
//		this.runSql("delete from pms_enterpriselimit where batchid = ?",new Object[]{newbatchid});
//		//复制数据
//		String batchname = this.getOnlyValueBySql("select name from pms_planprojectbatch where id = ?",new Object[]{newbatchid});
//		List<PmsEnterpriselimit> oldList = this.findByHql("from PmsEnterpriselimit where batchid = ?",new Object[]{oldbatchid});
//		for(PmsEnterpriselimit oldObj : oldList){
//			PmsEnterpriselimit newObj = new PmsEnterpriselimit();
//			Util.ApplyObject(newObj,JSONObject.fromObject(oldObj));
//			newObj.setId(Util.NewGuid());
//			newObj.setBatchid(newbatchid);
//			newObj.setBatchname(batchname);
//			newObj.setRecommendnum(0);
//			this.pmsEnterpriselimitService.merge(newObj);
//		}
//		return true;
//	}
//
//	public String getXsfsFlag(String batchid){
//		String sql = "select xsfsflag from pms_planprojectbatch where id = ?";
//		return this.dbHelper.getOnlyStringValue(sql, new Object[]{batchid});
//	}
//
//	public List<Map> getSbPlanprojectBatch(){
//		String sql = "select * from pms_planprojectbatch where currentstate='2' and TO_Char(starttime,'YYYY-MM-DD HH24:MI:SS') < TO_Char(SysDate,'YYYY-MM-DD HH24:MI:SS') and TO_Char(endtime,'YYYY-MM-DD HH24:MI:SS') > TO_Char(SysDate,'YYYY-MM-DD HH24:MI:SS')  ";
//		List<Map> list=dbHelper.getRows(sql,null);
//		return list;
//	}
//
//	public String getMessageFlag(String batchid){
//		String result = this.dbHelper.getOnlyStringValue("select message_sb " +
//				"from pms_planprojectbatch where id = ?",new Object[]{batchid});
//		return result;
//	}
//
//	public List<Map> findBatchByFilter(JSONObject jsonObject){
//		String insql1 = " and 1=1 ";
//		String insql2 = " and 1=1 ";
//		String insql3 = " and 1=1 ";
//		JSONArray array = new JSONArray();
//		String jhlb = jsonObject.get("jhlb")+"";
//		String year = jsonObject.get("year")+"";
//		String type = jsonObject.get("type")+"";
//		String ywtype = jsonObject.get("ywtype")+"";
//		array.add(type);
//
//		if(!Util.isEoN(jhlb)){
//			insql1 = " and t.applicationtypename1 = ? ";
//			array.add(jhlb);
//		}
//
//		if(!Util.isEoN(year)){
//			insql2 = " and t.annually = ? ";
//			array.add(year);
//		}
//
//		if(!Util.isEoN(ywtype)){
//			insql3 = " and t.ywtype = ? ";
//			array.add(ywtype);
//		}
//
//		List list = this.dbHelper.getRows("select t.id,t.name,t.annually,t.applicationtypename1,e.mold,t.ywtype " +
//				"from pms_planprojectbatch t " +
//				"left join fl_flowdefine e on instr(e.sourceids,t.id) <> 0 and e.type = ? " +
//				"where 1=1 and t.city = '1' "+insql1+insql2,array.toArray());
//
//		return list;
//	}
//
//	public List<Map> findTypeAndName(SysUser user){
//		String departmentid = user.getSysIdentitys().get(0).getPmsDepartment().getId();
//		String sql = "SELECT DISTINCT INNOVATECHAIN, APPLICATIONTYPENAME1 from PMS_PLANPROJECTBATCH where ishallshow = '1' and departmentid = ?";
//		List<Map> rows = this.dbHelper.getRows(sql, new Object[]{departmentid});
//		return rows;
//	}
//
//
//	public List<Map> findSmallBatchInYears(SysUser user, JSONObject jsonObject){
//		String type = Optional.ofNullable(jsonObject.opt("type").toString()).orElse("");
//		String startyear = Optional.ofNullable(jsonObject.opt("startyear").toString()).orElse("");
//		String endyear = Optional.ofNullable(jsonObject.opt("endyear").toString()).orElse("");
//		String bigbatchid = Optional.ofNullable(jsonObject.opt("bigbatchid").toString()).orElse("");
//		if(Util.isEoN(startyear) || Util.isEoN(endyear) || Util.isEoN(type)){
//			throw new BusinessException("参数不完整，请联系管理员。");
//		}
//		String departmentid = user.getSysIdentitys().get(0).getPmsDepartment().getId();
//		List<Map> rows = null;
//		if("small".equals(type)){
//			if(Util.isEoN(bigbatchid)){
//				throw new BusinessException("参数不完整，请联系管理员。");
//			}else {
//				String sql = "SELECT id,name from PMS_PLANPROJECTBATCH WHERE YEAR BETWEEN ? and ? and ishallshow = '1' and departmentid = ? and planprojectid = ?";
//				rows = this.dbHelper.getRows(sql, new Object[]{startyear, endyear, departmentid, bigbatchid});
//			}
//		}else {
//			String sql = "SELECT id,projectname from PMS_PLANPROJECT where " +
//					"id in (SELECT planprojectid from PMS_PLANPROJECTBATCH WHERE YEAR BETWEEN ? and ? and ishallshow = '1' and departmentid = ?);";
//			rows = this.dbHelper.getRows(sql, new Object[]{startyear, endyear, departmentid});
//		}
//		return rows;
//	}


}
