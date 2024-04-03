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
import cn.topcheer.pms2.api.plan.entity.PmsRecommendletterConfig;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.common.exception.BusinessException;
import cn.topcheer.pms2.dao.plan.PmsRecommendletterConfigDao;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * PmsRecommendletterConfig 服务类
 */
@Service(value="PmsRecommendletterConfigService")
public class PmsRecommendletterConfigService extends GenericService<PmsRecommendletterConfig> {

	@Autowired
	DBHelper dbHelper;
	@Autowired
	PmsTxtSave pmsTxtSave;
	@Autowired
	PmsPlanprojectbatchService pmsPlanprojectbatchService;

	@Autowired
	SysUserService sysUserService;

	public PmsRecommendletterConfigDao getPmsRecommendletterConfigDao() {
		return (PmsRecommendletterConfigDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsRecommendletterConfigDao(PmsRecommendletterConfigDao pmsRecommendletterConfigDao) {

		this.setGenericDao(pmsRecommendletterConfigDao);
	}

	/*保存+修改*/
	public Boolean saveRecommendletterConfig(JSONObject object){
		if (!Util.isEoN(object.get("batchid"))) {
			String batchid=object.get("batchid").toString();
			String batchname=this.pmsPlanprojectbatchService.findById(batchid).getName();
			//获取当前用户信息
			SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
			PmsRecommendletterConfig pmsRecommendletterConfig=new PmsRecommendletterConfig();
			List<PmsRecommendletterConfig> list=this.findByHql("select t from PmsRecommendletterConfig t where t.batchid = ? ",new Object[]{batchid});
			if (list.size()>0){
				pmsRecommendletterConfig=list.get(0);
				Util.ApplyObject(pmsRecommendletterConfig,object);
			}else{
				Util.ApplyObject(pmsRecommendletterConfig,object);
				pmsRecommendletterConfig.setId(Util.isEoN(object.get("id"))? UUID.randomUUID().toString():object.get("id").toString());
				pmsRecommendletterConfig.setSavedate(new Date());
			}
			pmsRecommendletterConfig.setBatchid(batchid);
			pmsRecommendletterConfig.setContent(object.getString("content"));
			pmsRecommendletterConfig.setBatchname(batchname);
			pmsRecommendletterConfig.setUserid(user.getId());
			pmsRecommendletterConfig.setUsername(user.getName());
			this.merge(pmsRecommendletterConfig);
			this.pmsTxtSave.saveTxt(batchid,JSONObject.fromObject(pmsRecommendletterConfig),"PmsRecommendletterConfigService","saveRecommendletterConfig");
		} else {
			throw new BusinessException("未传批次id；");
		}
		return true;
	}


	/**
	 * 根据批次id
	 */
	public List<Map> findLetterConfigByBatchid(String batchid){
		List<Map> list=this.dbHelper.getRows("select t.* from pms_recommendletter_config t where t.batchid=? ",new Object[]{batchid});
		return list;
	}

	
}
