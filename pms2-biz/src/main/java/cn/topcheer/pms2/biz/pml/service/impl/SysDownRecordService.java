/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-10-23 16:27:58
 *
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.pms2.api.pml.entity.SysDownRecord;
import cn.topcheer.pms2.dao.pml.SysDownRecordDao;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * SysDownRecord 服务类
 */
@Service(value="SysDownRecordService")
public class SysDownRecordService extends GenericPageService<SysDownRecord> {

	public SysDownRecordDao getSysDownRecordDao() {
		return (SysDownRecordDao) this.getGenericDao();
	}

	@Autowired
	public void setSysDownRecordDao(SysDownRecordDao sysDownRecordDao) {

		this.setGenericDao(sysDownRecordDao);
	}

	@Autowired
	private SysUserServiceImpl sysUserService;


	/**
	 * 【通用新增操作记录】
	 * @param sourceid 关联id
	 * @param type  操作类型
	 * @param note  操作日志
	 */
	public String commonSaveOperation(HttpServletRequest request, String sourceid, String type, String note, String url
	, String businesstype, String signed_url){
		SysDownRecord sysDownRecord = new SysDownRecord();
		sysDownRecord.setSourceid(sourceid);
		sysDownRecord.setType(type);
		sysDownRecord.setNote(note);
		sysDownRecord.setUrl(url);
		sysDownRecord.setBusinesstype(businesstype);
		sysDownRecord.setSigned_url(signed_url);
		String newid=this.saveCurrentUserOperation(request,sysDownRecord);
		return newid;
	}


	/**
	 * 【通用新增操作记录】
	 * @param sourceid 关联id
	 * @param type  操作类型
	 * @param note  操作日志
	 */
	public String commonSaveOperationV2(HttpServletRequest request, String sourceid, String type, String note, String url, String outName){
		SysDownRecord sysDownRecord = new SysDownRecord();
		sysDownRecord.setSourceid(sourceid);
		sysDownRecord.setType(type);
		sysDownRecord.setNote(note);
		sysDownRecord.setUrl(url);
		sysDownRecord.setOutname(outName);
		String newid=this.saveCurrentUserOperation(request,sysDownRecord);
		return newid;
	}


	/**
	 * 【通用新增操作记录】
	 * @param sourceid 关联id
	 * @param type  操作类型
	 * @param note  操作日志
	 */
	public String commonSaveOperation(HttpServletRequest request, String sourceid, String type, String note, String url){
		SysDownRecord sysDownRecord = new SysDownRecord();
		sysDownRecord.setSourceid(sourceid);
		sysDownRecord.setType(type);
		sysDownRecord.setNote(note);
		sysDownRecord.setUrl(url);
		String newid=this.saveCurrentUserOperation(request,sysDownRecord);
		return newid;
	}

	/**
	 * 通用的操作记录的方法
	 * @param request
	 * @param sysDownRecord
	 */
	public String saveCurrentUserOperation(HttpServletRequest request, SysDownRecord sysDownRecord) {
		// TODO Auto-generated method stub
		BladeUser currentUser = AuthUtil.getUser();
		if(currentUser==null)
			return "";
		SysUser user = sysUserService.findById(currentUser.getUserId());
		if(user == null){
			return "";
		}
		sysDownRecord.setOperationdate(new Date());
		String newid= Util.NewGuid();
		sysDownRecord.setId(newid);
		sysDownRecord.setOperator(user.getName());
		sysDownRecord.setOperatorid(user.getId());
		sysDownRecord.setLasttimeip(Util.getIpAddr(request));
		this.getSysDownRecordDao().save(sysDownRecord);
		return newid;
	}
	
}
