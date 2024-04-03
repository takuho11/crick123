/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-7-25 16:10:05
 *
 */
package cn.topcheer.pms2.biz.announcement.impl;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.announcement.RemindConfig;
import cn.topcheer.pms2.api.announcement.RemindRole;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.common.enumerate.ErrorCodeEnum;
import cn.topcheer.pms2.common.exception.BusinessException;
import cn.topcheer.pms2.dao.announcement.RemindRoleDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * RemindRole 服务类
 */
@Service(value="RemindRoleService")
public class RemindRoleService extends GenericService<RemindRole> {

	public RemindRoleDao getRemindRoleDao() {
		return (RemindRoleDao) this.getGenericDao();
	}
	@Autowired
	public void setRemindRoleDao(RemindRoleDao remindRoleDao) {

		this.setGenericDao(remindRoleDao);
	}
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private DBHelper dbHelper;
	@Lazy
	@Autowired
	private RemindConfigService remindConfigService;


	/*==============================================完美分割线==============================================*/


	/**
	 * 【主动提醒功能】 --通过用户信息，判断是否显示闹钟；如果显示闹钟，对应的数字是多少，点击后显示提醒内容的数组
	 */
	public JSONObject getRemindCount(){
		JSONObject resJson = new JSONObject();
		//====================获取当前用户信息====================
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		//====================根据用户的角色去找关联的提醒内容====================
 		List<RemindConfig> configList = new ArrayList<>();//主体提醒配置数组
		if(user.getSysIdentitys().size()>0){
			for(SysIdentity i : user.getSysIdentitys()){
				configList.addAll(this.findByHql("select t from RemindConfig t,RemindRole r " +
						" where t.id = r.configid and r.roleid = ?0 order by t.seq",new Object[]{i.getSysRole().getId()}));
			}
		}
		//====================判断是否有主动提醒配置内容，查询对应是否有提醒数字====================
		if(configList.size()>0){
			JSONArray remindArr = new JSONArray();//主动提醒内容（有数字）的数组
			for(RemindConfig config : configList){
				//查询是否有提醒数字
				int num = this.judgeGetNumByConfig(user,config);
				if(num>0){
					//说明要提醒
					JSONObject remindJson = JSONObject.fromObject(config);
					remindJson.put("num",num);
					remindArr.add(remindJson);
				}
			}
			if(remindArr.size()>0){
				resJson.put("list",remindArr);
			}
		}
		if(!resJson.has("list")){
			resJson.put("list",new JSONArray());
		}
		return resJson;
	}


	/**
	 *  【主动提醒功能】 -- 获取提醒数字，判断通用获取or特殊获取
	 */
	public int judgeGetNumByConfig(SysUser user,RemindConfig config){
		if(config.getReadscript()){
			//====================说明是通过配置的sql获取====================
			return this.getNumOrListByConfig("num",user,config,new JSONObject()).getInt("num");
		}else{
			//====================说明是特殊获取====================
			return 0;
		}
	}



	/**
	 * 【主动提醒功能】 -- 通过用户信息，提醒内容id，获取列表数据
	 */
	public JSONObject getListByRemindid(JSONObject paramJson){
		JSONObject resJson = new JSONObject();
		String remindid = paramJson.get("remindid")+"";
		RemindConfig config = this.remindConfigService.findById(remindid);
		//====================获取当前用户信息====================
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		if(Util.isEoN(config)||Util.isEoN(user)){
			throw new BusinessException(ErrorCodeEnum.PARAM_COMPLETE_ERROR);
		}
		//====================判断是配置sql获取数据，还是特殊获取数据====================
		if(config.getReadscript()){
			//====================说明是通用获取====================
			resJson = this.getNumOrListByConfig("list",user,config,paramJson);
		}else{
			//====================说明是特殊获取====================
			throw new BusinessException("没有调用特殊获取数据的接口。");
		}
		return resJson;
	}



	/**
	 *  【主动提醒功能】 -- 通用获取提醒数字 或者 列表数据
	 */
	public JSONObject getNumOrListByConfig(String type,SysUser user,RemindConfig config,JSONObject paramJson){
		JSONObject resJson = new JSONObject();
		String sql = " "+config.getScript()+" ";//sql语句，加空格是为了下面的分割
		List paramList = new ArrayList<>();//参数数组
		//处理sql语句
		//涉及到的参数：用户id -- userid_value ;
		if(sql.contains("userid_value")){
			int paramNum = sql.split("userid_value").length-1;
			for (int i = 0; i < paramNum; i++) {
				paramList.add(user.getId());
			}
			sql = sql.replace("userid_value","?");
		}
		//判断是获取提醒数字，还是获取列表数据
		if("num".equals(type)){
			//说明是获取数字
			sql = "select ifnull(count(as_count.id),0) from ( " + sql +" ) as_count";
			resJson.put("num",Integer.parseInt(this.dbHelper.getOnlyStringValue(sql,paramList.toArray())));
		}else if("list".equals(type)){
			//说明是获取列表数据，需要进行后台分页
			//判断是弹什么列表窗口，返回搜索条件语句和参数集合
			JSONObject searchJson = this.judgeGridType(config.getGridtype(),paramJson);
			String searchSql = searchJson.get("searchSql")+"";
			//处理sql语句
			if(!Util.isEoN(searchSql)){
				sql = sql + searchSql;
				paramList.addAll(searchJson.getJSONArray("paramList"));
			}
			//分页处理
			JSONObject page = paramJson.getJSONObject("page");
			//获取总数
			String totalCount = this.dbHelper.getOnlyStringValue("select count(as_count.id) from ( " + sql +" ) as_count",paramList.toArray());
			sql = "select levelone.* from(select leveltwo.*,rownum rn from (" +sql+") leveltwo ) levelone where levelone.rn < ? and  levelone.rn > ?";
			paramList.add(Integer.parseInt(page.get("currentPage").toString())*Integer.parseInt(page.get("pageSize").toString())+1);
			paramList.add((Integer.parseInt(page.get("currentPage").toString())-1)*Integer.parseInt(page.get("pageSize").toString()));
			//返回
			resJson.put("data",this.dbHelper.getRows(sql,paramList.toArray()));
			resJson.put("totalCount",totalCount);
		}
		return resJson;
	}




	/**
	 * 判断是弹什么列表窗口，返回搜索条件语句和参数集合
	 */
	public JSONObject judgeGridType(String gridtype,JSONObject paramJson){
		JSONObject resJson = new JSONObject();
		String searchSql = "";
		List<String> paramList = new ArrayList<>();
		if(paramJson.has("searchContent")&&!Util.isEoN(paramJson.get("searchContent"))){
			//判断列表类型
			switch (gridtype){
				case "A":
					//=============================说明是合同列表=============================
					//默认搜索条件：合同编号，合同名称，项目负责人，承担单位
					searchSql = " and (e.contractno like ? or e.showprojectbasename like ? or e.projectleader like ? or e.mainorganizers like ?) ";
					paramList.add("%"+paramJson.get("searchContent")+"%");
					paramList.add("%"+paramJson.get("searchContent")+"%");
					paramList.add("%"+paramJson.get("searchContent")+"%");
					paramList.add("%"+paramJson.get("searchContent")+"%");
					break;
			}
		}
		resJson.put("searchSql",searchSql);
		resJson.put("paramList",paramList);
		return resJson;
	}






}
