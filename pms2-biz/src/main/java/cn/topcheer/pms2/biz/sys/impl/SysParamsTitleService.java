/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-4-27 14:41:48
 *
 */
package cn.topcheer.pms2.biz.sys.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.SysParamsTitle;
import cn.topcheer.pms2.dao.sys.SysParamsTitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysParamsTitle 服务类
 */
@Service(value="SysParamsTitleService")
public class SysParamsTitleService extends GenericService<SysParamsTitle> {
	@Autowired
	private DBHelper dbHelper;

	public SysParamsTitleDao getSysParamsTitleDao() {
		return (SysParamsTitleDao) this.getGenericDao();
	}

	@Autowired
	public void setSysParamsTitleDao(SysParamsTitleDao sysParamsTitleDao) {

		this.setGenericDao(sysParamsTitleDao);
	}


	/**
	 * 【职称字典】---初始化职称系列
	 */
	public List<Map> initData() {
		String sql = "select id,name,value from sys_params_title where parentid is null and applytype = 'title' order by seq";
		List<Map> list = this.getListBySql(sql,null);

		return list;
	}


	/**
	 * 【职称字典】---根据父级value获取他的子级
	 */
	public List<Map> initDataByparentvalue(String name) {
		String parentid = this.getOnlyValueBySql("select id from sys_params_title where name = ? and parentid is null",new Object[]{name});
		String sql = "select id,name,value,code from sys_params_title where parentid = ? order by seq";
		List<Map> list = dbHelper.getRows(sql,new Object[]{parentid});

		return list;
	}
	
}
