/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2016-8-24 10:08:46
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaselinkuser;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaselinkuserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PmsProjectbaselinkuser 服务类
 */
@Service(value="PmsProjectbaselinkuserService")
public class PmsProjectbaselinkuserService extends GenericService<PmsProjectbaselinkuser> {
	
	@Autowired
	DBHelper dbHelper;
	@Autowired
	private PmsEnterpriseServiceImpl pmsEnterpriseService;
	
	public PmsProjectbaselinkuserDao getPmsProjectbaselinkuserDao() {
		return (PmsProjectbaselinkuserDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaselinkuserDao(PmsProjectbaselinkuserDao pmsProjectbaselinkuserDao) {

		this.setGenericDao(pmsProjectbaselinkuserDao);
	}
	//
	public List<Map> GetProject(String bid) {
		List<Map> res = null;		
			String sqlbid = "select pu.distributionone,p.batchname,p.casemanagement casemanagement,ph.mobilephone mobilephone,ps.zjzongf zjzongf," +
					"ps.zjpingjf zjpingjf,ps.zjpiaoshu zjpiaoshu,pu.id id,p.id projectbaseid,p.PROJECTPLANTYPE projectplantype," +
					"p.MAINORGANIZERS mainorganizers,p.SHOWPROJECTBASENAME showprojectbasename,p.PROJECTLEADER projectleader " +
					"from  pms_projectbaselinkuser pu inner join (select casemanagement,id,PROJECTPLANTYPE,showprojectbasename,MAINORGANIZERS," +
					"PROJECTLEADER,planprojectbatchid,batchname from pms_projectbase) p on p.id=pu.projectbaseid " +
					"left join（select s.projectbaseid,SUM(s.totalscore) zjzongf,Round(AVG(s.totalscore),2) as zjpingjf,count(s.totalscore) as zjpiaoshu " +
					"from (select row_number() over(partition by projectbaseid order by filltime asc) as sal_order,t.* from REV_EXPERTTABLE t) s " +
					"where sal_order <= 7 GROUP BY s.projectbaseid)ps on ps.projectbaseid=p.id  " +
					"left join (select pe.name,pe.projectbaseid,pe.mobilephone from pms_projectparty pe where pe.programrole='项目负责人') ph on ph.projectbaseid=p.id  " +
					"where  p.planprojectbatchid=? and ((pu.distribution='0' and pu.distributionone is null) " +
					"or (pu.distributionone='jgcz'and pu.distribution='1')) order by ps.zjpingjf desc";
			res = dbHelper.getRows(sqlbid, new Object[]{bid});
		return res;
	}
	public List<Map> GetProjects(String bid) {
		
		List<Map> res = null;		
			String sqlbid = "select pu.distributionone,p.applicationno,p.casemanagement casemanagement,u.name as zgxm," +
					"p.belonglab,ph.mobilephone mobilephone,ps.zjzongf zjzongf,ps.zjpingjf zjpingjf,ps.zjpiaoshu zjpiaoshu," +
					"pu.id id,p.id projectbaseid,p.PROJECTPLANTYPE projectplantype,p.MAINORGANIZERS mainorganizers," +
					"p.SHOWPROJECTBASENAME showprojectbasename,p.PROJECTLEADER projectleader,p.finalprojectname," +
					"p.finaltaskprojectname,p.cscsjg,p.csjyjf,p.csczshjg,p.csczjyjf  from  pms_projectbaselinkuser pu " +
					"inner join (select casemanagement,id,PROJECTPLANTYPE,showprojectbasename,MAINORGANIZERS,PROJECTLEADER," +
					"planprojectbatchid,finalprojectname,finaltaskprojectname,cscsjg,csczshjg,csczjyjf,csjyjf,belonglab," +
					"applicationno,xmzgid  from pms_projectbase ) p on p.id=pu.projectbaseid  left join sys_user u on u.id = p.xmzgid " +
					"left join（select s.projectbaseid,SUM(s.totalscore) zjzongf,Round(AVG(s.totalscore),2) as zjpingjf," +
					"count(s.totalscore) as zjpiaoshu from (select row_number() over(partition by projectbaseid order by filltime asc) as sal_order,t.* from REV_EXPERTTABLE t) s where sal_order <= 7 " +
					"GROUP BY s.projectbaseid)ps on ps.projectbaseid=p.id  left join (select pe.name,pe.projectbaseid,pe.mobilephone " +
					"from pms_projectparty pe where pe.programrole='项目负责人') ph on ph.projectbaseid=p.id  where ((pu.distribution='0' " +
					"and pu.distributionone is null) or (pu.distributionone='jccz' and pu.distribution='1')) and p.planprojectbatchid = ? " +
					"order by ps.zjpingjf desc";
			res = dbHelper.getRows(sqlbid, new Object[]{bid});
		return res;
	}
	
	
	public List<Map> GetProjectone(String userid, String bid) {
		List<Map> res = null;		
		String sqlbid = "select pu.distributionone,p.applicationno,p.casemanagement casemanagement,u.name as zgxm,p.belonglab,ph.mobilephone mobilephone,ps.zjzongf zjzongf," +
				"ps.zjpingjf zjpingjf,ps.zjpiaoshu zjpiaoshu,pu.id id,p.id projectbaseid,p.PROJECTPLANTYPE projectplantype,p.MAINORGANIZERS mainorganizers," +
				"p.SHOWPROJECTBASENAME showprojectbasename,p.PROJECTLEADER projectleader,p.finalprojectname,p.finaltaskprojectname,p.cscsjg,p.csjyjf,p.csczshjg,p.csczjyjf  " +
				"from  pms_projectbaselinkuser pu inner join (select casemanagement,id,PROJECTPLANTYPE,showprojectbasename,MAINORGANIZERS,PROJECTLEADER,planprojectbatchid," +
				"finalprojectname,finaltaskprojectname,cscsjg,csczshjg,csczjyjf,csjyjf,belonglab,applicationno,xmzgid  from pms_projectbase ) p on p.id=pu.projectbaseid  " +
				"left join sys_user u on u.id = p.xmzgid left join（select s.projectbaseid,SUM(s.totalscore) zjzongf,Round(AVG(s.totalscore),2) as zjpingjf,count(s.totalscore) " +
				"as zjpiaoshu from (select row_number() over(partition by projectbaseid order by filltime asc) as sal_order,t.* from REV_EXPERTTABLE t) s " +
				"where sal_order <= 7 GROUP BY s.projectbaseid)ps on ps.projectbaseid=p.id  left join (select pe.name,pe.projectbaseid,pe.mobilephone " +
				"from pms_projectparty pe where pe.programrole='项目负责人') ph on ph.projectbaseid=p.id  where (pu.distributionone='jccz' and pu.distribution='1')" +
				"and pu.userid=? and p.planprojectbatchid = ? order by ps.zjpingjf desc";
		res = dbHelper.getRows(sqlbid, new Object[]{userid,bid});
	return res;
	}
	public List<Map> GetProjectones(String userid) {
		
		List<Map> res = null;		
			String sqlbid = "select p.casemanagement casemanagement,ph.mobilephone mobilephone,ps.zjzongf zjzongf,ps.zjpingjf zjpingjf,ps.zjpiaoshu zjpiaoshu,pu.id id," +
					"p.id projectbaseid,p.PROJECTPLANTYPE projectplantype,p.MAINORGANIZERS mainorganizers,p.SHOWPROJECTBASENAME showprojectbasename,p.PROJECTLEADER projectleader " +
					"from  pms_projectbaselinkuser pu inner join (select casemanagement,id,PROJECTPLANTYPE,showprojectbasename,MAINORGANIZERS,PROJECTLEADER from pms_projectbase)" +
					" p on p.id=pu.projectbaseid left join(select s.projectbaseid,SUM(s.totalscore) zjzongf,Round(AVG(s.totalscore),2) as zjpingjf," +
					"count(s.totalscore) as zjpiaoshu from (select row_number() over(partition by projectbaseid order by filltime asc) as sal_order,t.* " +
					"from REV_EXPERTTABLE t) s where sal_order <= 7 GROUP BY s.projectbaseid)ps on ps.projectbaseid=p.id " +
					" left join (select pe.name,pe.projectbaseid,pe.mobilephone from pms_projectparty pe where pe.programrole='项目负责人') ph on ph.projectbaseid=p.id " +
					"where pu.userid=? and pu.distribution='1' and (pu.distributionone='jccz' or pu.distributionone='jgczjccz') order by ps.zjpingjf desc";
			res = dbHelper.getRows(sqlbid, new Object[]{userid});
		return res;
	}
	// 项目分配处室的时候 抄送一份到机关纪委
//	public void projectbaseCopyToCheck(String baseid){
//		String userid = pmsEnterpriseService.getUserByEDR("98C57F262CB74E12AAC31C9D37533A17","kjt_015","jcscz01");
//		if(!Util.isEoN(userid)){
//			PmsProjectbaselinkuser ppl = new PmsProjectbaselinkuser();
//			ppl.setId(Util.NewGuid());
//			ppl.setProjectbaseid(baseid);
//			ppl.setUserid(userid);
//			ppl.setDistribution("0");
//			this.getPmsProjectbaselinkuserDao().save(ppl);
//		}
//	}
	
}
