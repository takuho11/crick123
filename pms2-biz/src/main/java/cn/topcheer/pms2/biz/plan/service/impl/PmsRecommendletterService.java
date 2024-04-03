/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2022-12-15 16:05:04
 *
 */
package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.PmsTxtSave;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.api.plan.entity.PmsRecommendletter;
import cn.topcheer.pms2.api.plan.entity.PmsRecommendletterConfig;
import cn.topcheer.pms2.biz.pdfDownload.impl.PmsDownLoadService;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.common.exception.BusinessException;
import cn.topcheer.pms2.dao.plan.PmsRecommendletterDao;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * PmsRecommendletter 服务类
 */
@Service(value="PmsRecommendletterService")
public class PmsRecommendletterService extends GenericService<PmsRecommendletter> {

	@Autowired
	PmsRecommendletterConfigService pmsRecommendletterConfigService;
	@Autowired
	DBHelper dbHelper;
	@Autowired
	PmsTxtSave pmsTxtSave;
	@Autowired
	PmsPlanprojectbatchService pmsPlanprojectbatchService;
	@Autowired
	PmsDownLoadService pmsDownLoadService;

	@Autowired
	SysUserService sysUserService;

	public PmsRecommendletterDao getPmsRecommendletterDao() {
		return (PmsRecommendletterDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsRecommendletterDao(PmsRecommendletterDao pmsRecommendletterDao) {

		this.setGenericDao(pmsRecommendletterDao);
	}

	/*保存+修改*/
	public Boolean saveRecommendletter(JSONObject object){
		if (!Util.isEoN(object.get("batchid"))) {
			String batchid = object.get("batchid").toString();
			PmsPlanprojectbatch batch = this.pmsPlanprojectbatchService.findById(batchid);
			String batchname = batch.getName();
			Date tjhtjdate = batch.getTjhtjdate();
			if(!Util.isEoN(tjhtjdate)){
				if(new Date().after(tjhtjdate)){
					throw new BusinessException("已超出推荐函提交截止日期。");
				}
			}
			//获取当前用户信息
			SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
			String enterpriseid = user.getEnterPriseId();
			PmsRecommendletter pmsRecommendletter=new PmsRecommendletter();
			List<PmsRecommendletter> list=this.findByHql("select t from PmsRecommendletter t where t.batchid = ?0 and t.enterpriseid=?1 ",new Object[]{batchid,enterpriseid});
			if (list.size()>0){
				pmsRecommendletter=list.get(0);
				Util.ApplyObject(pmsRecommendletter,object);
			}else{
				Util.ApplyObject(pmsRecommendletter,object);
				pmsRecommendletter.setId(Util.isEoN(object.get("id"))?UUID.randomUUID().toString():object.get("id").toString());
				pmsRecommendletter.setSavedate(new Date());
			}
			pmsRecommendletter.setEnterpriseid(enterpriseid);
			String enterprisename=this.dbHelper.getOnlyStringValue("select t.name from pms_enterprise t where t.id=?0",new Object[]{enterpriseid});
			pmsRecommendletter.setEnterprisename(enterprisename);
			pmsRecommendletter.setUpdatedate(new Date());
			pmsRecommendletter.setUserid(user.getId());
			pmsRecommendletter.setUsername(user.getName());
			if(Util.isEoN(object.get("minicurrentstate"))){
				pmsRecommendletter.setMinicurrentstate("待提交");
			}else{
				pmsRecommendletter.setMinicurrentstate(object.get("minicurrentstate").toString());
				pmsRecommendletter.setSubmitdate(new Date());
			}
			pmsRecommendletter.setBatchid(batchid);
			pmsRecommendletter.setBatchname(batchname);
			this.merge(pmsRecommendletter);
			this.pmsTxtSave.saveTxt(batchid,JSONObject.fromObject(pmsRecommendletter),"PmsRecommendletterService","saveRecommendletter");
		} else {
			throw new BusinessException("未传批次id；");
		}
		return true;
	}

	/*提交*/
	public Boolean submitRecommendletter(JSONObject object){
		if (!Util.isEoN(object.get("batchid"))) {
			object.put("minicurrentstate","已提交");
			this.saveRecommendletter(object);
		} else {
			throw new BusinessException("未传批次id；");
		}
		return true;
	}


	/**
	 * 根据批次id获取推荐单位数据+数据源
	 */
	public JSONObject findTjdwByBatchid(String batchid,String enterpriseid){
		JSONObject object=new JSONObject();
		//获取当前用户信息
		if (Util.isEoN(enterpriseid)) {
			SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
			enterpriseid = user.getEnterPriseId();
		}
		String content = "";
		String datasql = "";
		String modeltype = "";
		List<PmsRecommendletterConfig> list=this.pmsRecommendletterConfigService.findByHql("select t from PmsRecommendletterConfig t where t.batchid = ?0 ",new Object[]{batchid});
		if(list.size()>0){
			content=list.get(0).getContent();
			datasql=list.get(0).getDatasql();
			modeltype=list.get(0).getModeltype();
			/*推荐项目信息*/
			List<Map> xmlist=this.dbHelper.getRows(datasql,new Object[]{batchid,enterpriseid});

			/*推荐单位信息*/
			List<Map> dwlist=this.dbHelper.getRows("select t.id,t.isable,t.enterpriseid,t.enterprisename,t.linkname," +
					"t.linktelephone,t.batchid,t.batchname,t.minicurrentstate,t.savedate,t.updatedate,t.submitdate," +
					"t.memo,t.userid,t.username,t.lettername from pms_recommendletter t where t.batchid=? and t.enterpriseid=? ",new Object[]{batchid,enterpriseid});

			object.put("xmlist",xmlist);
			object.put("dwlist",dwlist);
			if (dwlist.size()>0) {
				object.put("lettername",dwlist.get(0).get("lettername"));
			} else {
				object.put("lettername","");
			}
			/*前台配置信息*/
			object.put("content",content);
			/*模板生成类别*/
			object.put("modeltype",modeltype);
		}else{
			throw new BusinessException("未找到配置信息,请联系系统维护人员：" + Util.linkNumber);
		}
		return object;
	}


	/**
	 * 根据批次id+推荐单位，查有几条
	 */
	public int findHaveTjdwByBatchid(String batchid){
		//获取当前用户信息
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		String enterpriseid = user.getEnterPriseId();
		/*推荐单位信息*/
		int num=this.dbHelper.getRowsCount("select t.id from pms_recommendletter t where t.batchid=? and t.enterpriseid=? ",new Object[]{batchid,enterpriseid});
		return num;
	}

	/**
	 * 根据批次name+推荐单位，查有几条
	 */
	public JSONObject findHaveTjdwByBatchname(String batchname){
		//获取当前用户信息
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		String enterpriseid = user.getEnterPriseId();
		/*推荐单位信息*/
		int num=this.dbHelper.getRowsCount("select t.id from pms_recommendletter t where t.batchname=? and t.enterpriseid=? ",new Object[]{batchname,enterpriseid});
		String batchid = "";
		JSONObject jsonObject = new JSONObject();
		if(num == 0) {
			batchid = this.dbHelper.getOnlyStringValue("select id from pms_planprojectbatch where name=?", new Object[]{batchname});
		}
		jsonObject.put("num", num);
		jsonObject.put("batchid", batchid);
		return jsonObject;
	}


	/**
	 * 根据批次id获取推荐单位数据+数据源
	 */
	public HashMap findTjdwByBatchidForPdf(HttpServletRequest request){
		String batchid = request.getParameter("batchid");
		String enterpriseid = request.getParameter("enterpriseid");
		JSONObject object = this.findTjdwByBatchid(batchid,enterpriseid);
		HashMap map=new HashMap();
		map.put("xmlist", object.get("xmlist"));
		map.put("dwlist", object.get("dwlist"));
		map.put("lettername", object.get("lettername"));
		return map;
	}

	
}
