/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-8-17 13:59:15
 *
 */
package cn.topcheer.pms2.biz.sys.statistics;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.statistics.SysQueryParam;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.dao.sys.statistics.SysQueryParamDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysQueryParam 服务类
 */
@Service(value="SysQueryParamService")
public class SysQueryParamService extends GenericService<SysQueryParam> {
	@Autowired
	DBHelper dbHelper;
	@Autowired
	SysUserService sysUserService;

	public SysQueryParamDao getSysQueryParamDao() {
		return (SysQueryParamDao) this.getGenericDao();
	}

	@Autowired
	public void setSysQueryParamDao(SysQueryParamDao sysQueryParamDao) {

		this.setGenericDao(sysQueryParamDao);
	}


	/*******************************************************************
	 * 根据id删除统计查询参数配置           *
	 *******************************************************************/
	public void deleteSysQueryParamById(String id){
		try{
			dbHelper.runSql("delete SYS_QUERY_PARAM  where id=?",new Object[]{id});
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/*******************************************************************
	 * 根据id删除统计查询参数配置--大字段         *
	 *******************************************************************/
	public void deleteSysQueryParamClobById(String id){
		try{
			dbHelper.runSql("delete SYS_QUERY_CLOB  where id=?",new Object[]{id});
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/*******************************************************************
	 * 新增统计查询参数配置            *
	 *******************************************************************/
	public void insertSysQueryParam(JSONObject obj,String pid){
		try{
			JSONObject jsonObject=obj.getJSONObject("data");
			SysUser user = this.sysUserService.getCurrentUser();
			dbHelper.runSql("insert into SYS_QUERY_PARAM (id,creatuserid,memo,updatetime,code,value,type,sourceid,linktype,insql,savedate,updatelasttime)" +
							"values (?,?,?,sysdate,?,?,?,?,?,?,sysdate,sysdate)",
					new Object[]{Util.NewGuid(),user.getId(),jsonObject.get("memo"),jsonObject.get("code"),jsonObject.get("value")
							,jsonObject.get("type"),pid,jsonObject.get("linktype"),jsonObject.get("insql")});
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/*******************************************************************
	 * 新增统计查询参数配置--大字段
	 *******************************************************************/
	public void insertSysQueryParamClob(JSONObject obj,String pid){
		try{
			JSONObject jsonObject=obj.getJSONObject("data");
			String clobtext=jsonObject.get("clobtext")+"";
			dbHelper.runSql("insert into SYS_QUERY_CLOB (id,sourceid,type,source,updatelasttime)" +
							"values (?,?,?,?,sysdate)",
					new Object[]{Util.NewGuid(),pid,"sqlid",clobtext});
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	/*******************************************************************
	 * 根据id修改统计查询参数配置            *
	 *******************************************************************/
	public void updateSysQueryParamById(JSONObject obj,String id){
		JSONObject jsonObject=obj.getJSONObject("data");
		try{
			dbHelper.runSql("update SYS_QUERY_PARAM set memo=?,code=?,value=?,type=?,linktype=?,insql=?,updatelasttime=sysdate where id=?",
					new Object[]{jsonObject.get("memo"),jsonObject.get("code"),jsonObject.get("value"),jsonObject.get("type"),jsonObject.get("linktype")
							,jsonObject.get("insql"),id});
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/*******************************************************************
	 * 根据id修改统计查询参数配置--大字段           *
	 *******************************************************************/
	public void updateSysQueryParamClobById(JSONObject obj,String id){
		try{
			JSONObject jsonObject=obj.getJSONObject("data");
			String clobtext=jsonObject.get("clobtext")+"";
			dbHelper.runSql("update SYS_QUERY_CLOB set source=?,updatelasttime=sysdate where id=?",
					new Object[]{clobtext,id});
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
