package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.PmsProjectPartyDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value="PmsProjectpartyService")
public class PmsProjectPartyService extends GenericService<PmsProjectparty> {


	public PmsProjectPartyDao getPmsProjectDao() {
		return (PmsProjectPartyDao)this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectDao(PmsProjectPartyDao pmsProjectDao) {
		this.setGenericDao(pmsProjectDao);
	}
	
	@Autowired
	DBHelper dbhelper;
//	@Autowired
//	private PmsPlanconditionService pmsPlanconditionService;
//
//	@Autowired
//	PmsDesensitizationdataService pmsDesensitizationdataService;


    public List<PmsProjectparty> findByPidandType(String projectbaseid,String type) {
        String hql = "select t from cn.topcheer.pms2.api.project.entity.PmsProjectparty t left join t.pmsProjectbase projectbase where projectbase.id=? and t.rytype=?  order by t.seq";
        List<PmsProjectparty> listPpc = this.findByHql(hql,projectbaseid,type);
        if (listPpc.size() > 0) {
            return listPpc;
        } else {
            return listPpc;
        }
    }

    public List<PmsProjectparty> findByJxidType(String jxid,String type){
        String hql = "select t from PmsProjectparty t where t.jxid = ? and t.rytype = ? order by t.seq";
        List<PmsProjectparty> list = this.getPmsProjectDao().findByHql(hql, new Object[]{jxid,type});
        return list;
    }

    public List<PmsProjectparty> findByJxidwcr(String jxid){//成果的项目完成人
        String hql = "select t from PmsProjectparty t where t.jxid = ? and t.ptype = 'xmwcr' order by t.seq";
        List<PmsProjectparty> list = this.getPmsProjectDao().findByHql(hql, new Object[]{jxid});
        return list;
    }

	/**
	 * 【黑龙江申报系统】  --- 通过小批次id ，导出人员诚信清单
	 * @param batchid
	 */
	public JSONArray printRyInfoByBid(String batchid,String type){
		String sql = "";
		if("kjxm".equals(type)){
			sql = "select rownum ,tt.* from (select p.name," +
					" p.certificatename, " +
					" p.certificatenumber, " +
					" p.workplace, " +
					//国家重大项目省级资助 取 课题编号
					" (case when bat.planprojectid = '31F8A577-5B2F-4380-8A14-9827BA8957B0' " +
					"  then b.gjktno else b.applicationno end) as bhno, " +
					" b.projectbasename, " +
					" p.classify, " +
					" (case when b.minicurrentstate is null then '项目申报:用户填报' else b.minicurrentstate end) as minicurrentstate " +
					" from pms_projectparty p " +
					" left join pms_projectbase b on p.projectbaseid = b.id  " +
					" left join pms_planprojectbatch bat on b.planprojectbatchid = bat.id " +
					" where b.planprojectbatchid = ?  " +
					" and (p.rytype = 'true' or (p.rytype != 'true' and p.classify = '项目负责人') )" +
					" and b.minicurrentstate = '项目申报:形审'" +
					" order by (case when b.minicurrentstate is null then '项目申报:用户填报' else b.minicurrentstate end)) tt";
		}else if("kjjl".equals(type)){
			sql = "select rownum ,tt.* from (select p.name,  " +
					"         p.certificatename,   " +
					"         p.certificatenumber,   " +
					"         p.workplace,   " +
					"         b.applicationno as bhno, " +
					"         b.projectbasename,   " +
					"         (case when p.rytype = 'true' then '第一完成人' else '参与人员' end) as classify " +
					"         from pms_projectparty p   " +
					"         left join pms_reward b on p.jxid = b.id    " +
					"         left join pms_planprojectbatch bat on b.planprojectbatchid = bat.id   " +
					"         where b.planprojectbatchid = ?   " +
					"         and b.minicurrentstate = '科技奖励:形审' " +
					"         and (p.rytype = 'true' or p.rytype = 'principal') " +
					"         order by b.applicationno,p.rytype desc,p.seq) tt";
		}else if("kjcxpt".equals(type)){
			sql = "select rownum ,tt.* from (select p.name,   " +
					" p.certificatename,  " +
					" p.certificatenumber,  " +
					" b.projectbasename,  " +
					" (case when p.rytype = 'true' then '负责人' else '参与人员' end) as classify  " +
					" from pms_innovationbase_xmry p  " +
					" left join pms_innovationbase b on p.innovationid = b.id  " +
					" left join pms_planprojectbatch bat on b.planprojectbatchid = bat.id  " +
					" where 1=1  " +
					" and b.planprojectbatchid = ?  " +
					" and b.MINICURRENTSTATE<>'平台:用户填报'  " +
					" and b.MINICURRENTSTATE<>'平台:所在单位审核'  " +
					" and b.MINICURRENTSTATE<>'平台:推荐单位审核'  " +
					" and b.MINICURRENTSTATE is not null  " +
					" and (p.rytype = 'true' or p.rytype = 'principal')  " +
					" order by b.applicationno,p.rytype desc,p.seq) tt";
		}
		List<Map> list = this.dbhelper.getRows(sql,new Object[]{batchid});
		return JSONArray.fromObject(list);
	}


//	/**
//	 * 通过项目id  获取项目负责人关联项目，获取承担单位关联项目
//	 * @param projectbaseid
//	 * @return
//     */
//	public JSONObject getRelationData(String projectbaseid){
//		JSONObject resJson = new JSONObject();
//		//取当前项目的负责人身份证
//		String certificatenumber = this.getOnlyValueBySql("select e.CERTIFICATENUMBER from pms_projectparty e where e.projectbaseid = ? and e.rytype = 'true'",new Object[]{projectbaseid});
//		if(Util.isEoN(certificatenumber)){
//			resJson.put("contractList",new ArrayList<>());
//		}else{
//			List<Map> contractList = new ArrayList<>();
//			JSONObject certificatenumberJson = this.pmsPlanconditionService.handleNameAndCertificatenumber("xxx",certificatenumber);//主要是为了获取身份证
//			String idCard15_a = certificatenumberJson.get("idCard15_a")+"";
//			String idCard15_A = certificatenumberJson.get("idCard15_A")+"";
//			String idCard18_a = certificatenumberJson.get("idCard18_a")+"";
//			String idCard18_A = certificatenumberJson.get("idCard18_A")+"";
//			String sql1 = "select distinct jbxx.id,jbxx.id as contractid,batch.id as batchid, " +
//					" jbxx.contractno,jbxx.showprojectbasename, " +
//					" jbxx.mainorganizers,jbxx.projectleader, " +
//					" batch.name as batachname,";
//			String sql2 =" (case when jbxx.isys is not null then '结题' when jbxx.isendcontract is not null then '终止' else '在研'  end) as contractstate  " +
//					" from  crt_contract_xmry xmry " +
//					" left join crt_contract_jbxx jbxx on (xmry.projectbaseid = jbxx.projectbaseid or xmry.contractid = jbxx.id) " +
//					" left join pms_planprojectbatch batch on jbxx.planprojectbatchid = batch.id" +
//					" where 1=1  " +
//					" and (replace(xmry.certificatenumber,' ', '')=? or replace(xmry.certificatenumber,' ', '')=? or replace(xmry.certificatenumber,' ', '')=? or replace(xmry.certificatenumber,' ', '')=? ) " ;
//			String orderSql = " order by decode((case when jbxx.isys is not null then '结题' when jbxx.isendcontract is not null then '终止' else '在研'  end), '在研', 1, '终止', 2, '结题', 3)";
//			//查询负责人承担的合同
//			List<Map> zyList = this.dbhelper.getRows(sql1+" '承担' as cdcy, "+sql2+" and (xmry.rytype = 'true' or (xmry.classify in ('项目负责人','负责人') and xmry.rytype = 'principal')) "+orderSql,new Object[]{idCard15_a,idCard15_A,idCard18_a,idCard18_A});
//			contractList.addAll(zyList);
//			//查询负责人参与的合同
//			List<Map> cyList = this.getListBySql(sql1+" '参与' as cdcy, "+sql2+" and xmry.classify not in ('项目负责人','负责人') and xmry.rytype = 'principal' "+orderSql,new Object[]{idCard15_a,idCard15_A,idCard18_a,idCard18_A});
//			contractList.addAll(cyList);
//			resJson.put("contractList",contractList);
//		}
//		//查询当前项目的承担单位的 pms_enterprise表的id
//		resJson.put("unitid",this.getOnlyValueBySql("select e.ENTERPRISEORUSERSID from pms_projectbase e where e.id = ?",new Object[]{projectbaseid}));
//		return resJson;
//	}
}
