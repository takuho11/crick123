/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-4-15 14:46:39
 *
 */
package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.app.dao.jpa.framework.sys.SysRoleDao;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.SysParams;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.sys.SysParamsDao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysRole 服务类
 */
@Service(value="PmsRoleService")
public class PmsRoleService extends GenericService<SysRole> {

	public SysRoleDao getSysParamsDao() {
		return (SysRoleDao) this.getGenericDao();
	}

	@Autowired
	public void setSysRoleDao(SysRoleDao sysRoleDao) {

		this.setGenericDao(sysRoleDao);
	}

    @Autowired
    private DBHelper dbHelper;


	public List<Map> findSysRole(){
		List<Map> list = this.dbHelper.getRows("select * from sys_role",null);
		return list;
	}


	public SysRole findbyRoleCode(String rolecode){
		String sql = "select t.id from sys_role t where t.rolecode = ?";
		List<Map> roles = this.dbHelper.getRows(sql, new Object[]{rolecode});
		if(roles!=null&&roles.size()>0) {
			SysRole sysRole = this.findById(roles.get(0).get("id")+"");
			return sysRole;
		} else {
			return null;
		}
	}

	public List<Map> findEnterpriseRole() {
		String userId = AuthUtil.getUserId();
		String sql = "SELECT r.* FROM Sys_User u " +
				"LEFT JOIN SYS_IDENTITY i ON u.id = i.USERID " +
				"LEFT JOIN Sys_Role r ON i.PURVIEWORGANIZEID  = r.ENTER_PRISE_ID OR r.ENTER_PRISE_ID IS null " +
				"WHERE u.id = ? ";
		List<Map> rows = this.dbHelper.getRows(sql, new Object[]{userId});
		return rows;
	}
}
