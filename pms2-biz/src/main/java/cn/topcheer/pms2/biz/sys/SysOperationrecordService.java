/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2016-4-26 16:26:13
 *
 */
package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.SysOperationrecord;
import cn.topcheer.pms2.dao.jpa.sys.SysOperationrecordDao;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * SysOperationrecord 服务类
 */
@Service(value = "SysOperationrecordService")
public class SysOperationrecordService extends
		GenericService<SysOperationrecord> {

	@Autowired
	private DBHelper dbhelper;
	
	public SysOperationrecordDao getSysOperationrecordDao() {
		return (SysOperationrecordDao) this.getGenericDao();
	}

	@Autowired
	public void setSysOperationrecordDao(
			SysOperationrecordDao sysOperationrecordDao) {

		this.setGenericDao(sysOperationrecordDao);
	}

	@Autowired
	SysUserServiceImpl sysUserService;

	/**
	 * 通用的保存操作记录的方法
	 * @param request
	 * @param sysOpera
	 */
	public void saveCurrentUserOperation(HttpServletRequest request, SysOperationrecord sysOpera) {
		// TODO Auto-generated method stub

		BladeUser user= AuthUtil.getUser();
		if(user==null)
		{
			return;
		}
		sysOpera.setOperationdate(new Date());
		sysOpera.setId(Util.NewGuid());
		sysOpera.setOperator(user.getUserName());
		sysOpera.setOperatorid(user.getUserId());
		sysOpera.setLasttimeip(Util.getIpAddr(request));
		this.getSysOperationrecordDao().save(sysOpera);
	}

	/**
	 * 通用的保存操作记录的方法2
	 * @param sysOpera
	 */
	public void saveCurrentUserOperation(SysOperationrecord sysOpera) {
		BladeUser user= AuthUtil.getUser();
		if(user==null)
		{
			return;
		}
		sysOpera.setOperationdate(new Date());
		sysOpera.setId(Util.NewGuid());
		sysOpera.setOperator(user.getUserName());
		sysOpera.setOperatorid(user.getUserId());
		sysOpera.setLasttimeip(Util.getIPWithoutRequest());
		this.getSysOperationrecordDao().save(sysOpera);
	}

	/**e
	 * 通用的保存操作记录的方法（针对网评专家放弃）
	 * @param sysOpera
	 */
	public void saveCurrentUserOperationForReview(SysOperationrecord sysOpera) {
		BladeUser currentUser= AuthUtil.getUser();

		SysUser user = new SysUser();
		if(currentUser==null) {
			user = sysUserService.findById("g63eb8p9-c7dd-1245-fd82-5fad6ef5c7la");
		} else {
			user =sysUserService.findById(currentUser.getUserId());
		}
		sysOpera.setOperationdate(new Date());
		sysOpera.setId(Util.NewGuid());
		sysOpera.setOperator(user.getName());
		sysOpera.setOperatorid(user.getId());
		this.getSysOperationrecordDao().save(sysOpera);
	}

	public List<SysOperationrecord> initFindSomeExpert(String operationId) {
		String hql = "select expert from SysOperationrecord expert where expert.operatorid=?0 and  (expert.note like '%账户审核%' or expert.note like '%删除用户%' or expert.note like '%审核记录；单位：%' or expert.note like '%单位信息修改%') Order By operationdate desc";
		Query query = this.getSysOperationrecordDao().getQuery(hql);
		query.setParameter(0,operationId);
		return  query.setMaxResults(50)
				.list();
	}

	/**
	 * 获取用户下载pdf的记录
	 * 
	 * @param projectbaseName
	 * @param projectbaseid
	 * @return
	 */
	public List<SysOperationrecord> findAllPdfRecord(String projectbaseName,
			String projectbaseid) {
		String hql = "from cn.topcheer.pms2.api.project.entity.system.SysOperationrecord as u where u.type = ? order by u.operationdate desc";
		@SuppressWarnings("unchecked")
		List<SysOperationrecord> list = this.getSysOperationrecordDao()
				.findByHql(hql, new Object[] { projectbaseName + projectbaseid + "【pdf下载】" });
		return list;
	}


	public List findAllOperationBysql() {
		// TODO Auto-generated method stub
		//默认50条
		String sql = "select * from sys_operationrecord where rownum<50";
		return dbhelper.getRows(sql, null);
		 
	}
	
	public List findbyPropertiesBysql(String operationname) {
		// TODO Auto-generated method stub
		//默认50条
		String sql = "select * from sys_operationrecord where (operator like ? ) and rownum<50";
		return dbhelper.getRows(sql, new Object[]{"%"+operationname+"%"});

	}
	
	public List findbyPropertiesBysqltwo(String operationnameone,String operationnametwo) {
		// TODO Auto-generated method stub
		//默认50条
		String sql = "select * from sys_operationrecord where (operationdate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd')) and rownum<50";
		return dbhelper.getRows(sql, new Object[]{operationnameone,operationnametwo});
		 
	}
	
	public List findbyPropertiesBysqlthree(String operationname,String operationnameone,String operationnametwo) {
		// TODO Auto-generated method stub
		//默认50条
		String sql = "select * from sys_operationrecord where ((operator like ? ) and operationdate between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd')) and rownum<50";
		return dbhelper.getRows(sql, new Object[]{"%"+operationname+"%",operationname,operationnameone,operationnametwo});
		 
	}

	/**
	 * 【通用新增操作记录】 --- 常用
	 * @param sorceid 关联id
	 * @param type  操作类型
	 * @param note  操作日志
     */
	public void commonSaveOperation(HttpServletRequest request,String sorceid,String type,String note){
		SysOperationrecord sysOperationrecord = new SysOperationrecord();
		sysOperationrecord.setSourceid(sorceid);
		sysOperationrecord.setType(type);
		sysOperationrecord.setNote(note);
		this.saveCurrentUserOperation(request,sysOperationrecord);
	}

	/**
	 * 【通用新增操作记录】 --- 常用2
	 * @param sorceid 关联id
	 * @param type  操作类型
	 * @param note  操作日志
	 */
	public void commonSaveOperation(String sorceid,String type,String note){
		SysOperationrecord sysOperationrecord = new SysOperationrecord();
		sysOperationrecord.setSourceid(sorceid);
		sysOperationrecord.setType(type);
		sysOperationrecord.setNote(note);
		this.saveCurrentUserOperation(sysOperationrecord);
	}

	/**
	 * 【通用新增操作记录2】
	 * @param sorceid 关联id
	 * @param type  操作类型
	 * @param note  操作日志
	 * @param note  操作意见
	 */
	public void commonSaveOperation(HttpServletRequest request,String sorceid,String type,String note,String opinion){
		SysOperationrecord sysOperationrecord = new SysOperationrecord();
		sysOperationrecord.setSourceid(sorceid);
		sysOperationrecord.setType(type);
		sysOperationrecord.setNote(note);
		sysOperationrecord.setOpinion(opinion);
		this.saveCurrentUserOperation(request,sysOperationrecord);
	}


	/**
	 * 【通用】：根据sourceid 和 type 获取操作记录
	 */
	public List<Map> getRecord(JSONObject json){
		List<Map> resList = new ArrayList<>();
		if(!(json.containsKey ("sourceid") && json.containsKey("type"))){
			return resList;
		}
		String sourceid = json.get("sourceid") + "";
		String type = json.get("type") + "";
		if(Util.isEoN(sourceid) || Util.isEoN(type)){
			return resList;
		}
		return dbhelper.getRows("select e.*,to_char(e.operationdate,'yyyy-MM-dd HH24:mi:ss') as operationtime " +
				" from SYS_OPERATIONRECORD e where e.type = ? and e.sourceid = ? order by operationdate", new Object[]{type, sourceid});
	}
	

}
