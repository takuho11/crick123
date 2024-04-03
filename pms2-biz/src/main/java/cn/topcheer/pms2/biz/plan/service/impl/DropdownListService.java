package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.biz.modules.system.vo.ResDataListVO;
import cn.topcheer.halberd.core.result.Result;
import cn.topcheer.pms2.api.plan.vo.DropdownListVO;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import com.alibaba.fastjson.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "DropdownListService")
public class DropdownListService {
    @Autowired
    private DBHelper dbHelper;
    @Autowired
    private SysUserService sysUserService;

    public R getDropdownList(DropdownListVO params) {
        String gridType = params.getGridType();
        String type = params.getType();

        List list = judgeRole(gridType, type);
        if (list == null) {
            return R.fail("查询失败,请检查传参!");
        }
        return R.data(list, "查询成功");
    }

    private List judgeRole(String gridType, String type) {
        SysUser user = sysUserService.getById(AuthUtil.getUserId());
        String roleId = String.valueOf(user.getRoleId());
        String enterPriseId = String.valueOf(user.getEnterPriseId());
        String deptId = String.valueOf(user.getDeptId());
        if (type.equals("small")){
            type = "pro.name as name,pro.name as value";
        }else if (type.equals("big")){
            type = "plan.projectname as name,plan.projectname as value";
        } else {
            return null;
        }
        //role条件拼接
        String roleSql = "";
        if (roleId.length()>0){
            String[] split = roleId.split(",");
            for (int i = 0; i < split.length; i++) {
                if ("".equals(roleSql)){
                    roleSql = " and ( pro.ROLE_IDS like '%;" + split[i] +";%'";
                }else {
                    roleSql = roleSql + " or pro.ROLE_IDS like '%;" + split[i] +";%'";
                }
            }
//            if (roleSql.length()>0){
//                roleSql = roleSql +" or pro.ROLE_IDS )";
//            }
        }
        if ("".equals(roleSql)){
            roleSql = " and (pro.ROLE_IDS is null or pro.ROLE_IDS = '')";
        }else {
            roleSql = roleSql + " or pro.ROLE_IDS is null or pro.ROLE_IDS = '')";
        }
        String sql = "select distinct name,value from ( " +
                " select " + type + " from Pms_Planproject plan " +
                " LEFT JOIN PMS_PLANCATEGORY cate ON cate.CODE = plan.CATEGORY " +
                " left join Pms_PlanProjectBatch pro on plan.id = pro.planprojectid " +
                " LEFT JOIN PMS_ENTERPRISE ent ON ent.PARENTID = pro.ORGANIZE_ID" +
                " LEFT JOIN PMS_ENTERPRISE ent2 ON ent2.PARENTID = ent.id" +
//                " left join pms_department d on pro.departmentid = d.id  " +
                " WHERE CONCAT(';', cate.DEFAULT_TYPE) LIKE CONCAT('%;', ?, ';%') " +
                " and pro.departmentid like ? OR pro.departmentid IS null OR pro.departmentid ='' " +
                " and (pro.ORGANIZE_ID = ? or  ent.id = ? or ent2.id = ?)" +
                    roleSql +
//                " and pro.ROLE_IDS like ? " +
                " ) a ";
        List<Map> rows = this.dbHelper.getRows(sql, new Object[]{gridType, "%" + deptId + "%", enterPriseId, enterPriseId, enterPriseId});
        return rows;
    }

    public R getCompeleteUnit(String serachStr, String type) {
        serachStr = serachStr.trim();
        StringBuilder sql = new StringBuilder("select bi.unitname as unitName,bi.creditcode as creaditCode, " +
                "bi.belongindustry as belonginDustry, bi.officeaddress as officeAddress, " +
                "bi.postalcode as postalCode, bi.fax as fax,bi.telephone as telephone, " +
                "ry.name as linkName,ry.mobile as linkMobile,ry.email as linkEmail,ent.unittype as unittype " +
                "from Pms_enterprise ent " +
                "inner join Sys_identity iden on ent.id = iden.PURVIEWORGANIZEID and iden.roleid = 'C7004168-4E0C-4F1F-B341-A225B5644DC5' " +
                "inner join SYS_USER u on iden.userid = u.id and (u.MINICURRENTSTATE = '完善信息完成'or u.MINICURRENTSTATE = '变更主体完成')" +
                "inner join BI_ENT_BI bi on ent.uniformcode = bi.creditcode and bi.type = 'unitInfo_basic'" +
                "inner join BI_ENT_RY  ry on ry.mainid = bi.mainid and ry.type = 'member_link' " +
                "where (ent.name like ? or ent.uniformcode like ?)");
        if ("region".equals(type)){
            if (Util.isEoN(serachStr)) {
                serachStr = "52";
            }
            String sql2 = "select ent.id,ent.name,ent.uniformcode from Pms_Enterprise ent where areaid = ?";
            List<Map> rows = dbHelper.getRows(sql2, new Object[]{serachStr});
            return R.data(rows);
        }else {
            if (!Util.isEoN(type)) {
                sql.append(" and ent.unittype = '" + type + "'");
            }
            List<Map> rows = dbHelper.getRows(sql.toString(), new Object[]{"%" + serachStr + "%", "%" + serachStr + "%"});
            return R.data(rows);
        }
    }
}
