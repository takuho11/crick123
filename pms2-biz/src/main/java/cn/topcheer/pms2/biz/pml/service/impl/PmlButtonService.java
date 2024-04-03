/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2019-3-3 15:54:33
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.sys.SysOperationrecord;
import cn.topcheer.pms2.api.pml.entity.PmlButton;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.dao.pml.PmlButtonDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * PmlButton 服务类
 */
@Service(value = "PmlButtonService")
public class PmlButtonService extends GenericPageService<PmlButton> {

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private SysOperationrecordService sysOperationrecordService;

    public PmlButtonDao getPmlButtonDao() {
        return (PmlButtonDao) this.getGenericDao();
    }

    @Autowired
    public void setPmlButtonDao(PmlButtonDao pmsButtonDao) {

        this.setGenericDao(pmsButtonDao);
    }

    /**
     * 【初始化和搜索方法】
     *
     * @param json
     * @return
     */
    public PageResult<List<Map>> getData(JSONObject json) {
        //参数集合
        List paramList = new ArrayList<>();
        //判断是否有搜索条件
        String insql = "";
        if (json.has("searchContent")) {
            insql = " and (e.buttonname like ? or e.remarks like ? or e.BUTTONMETHOD like ?)";
            paramList.add("%" + json.get("searchContent") + "%");
            paramList.add("%" + json.get("searchContent") + "%");
            paramList.add("%" + json.get("searchContent") + "%");
        }
        if (json.has("businesstype")) {
            if (!Util.isEoN(json.get("businesstype"))) {
                insql = insql + " and e.businesstype = ? ";
                paramList.add(json.get("businesstype") + "");
            }
        }
        //sql语句
        String sql = "select e.id,e.buttonname,e.buttontype," +
                " (case when e.buttontype='gridbtn' then '列表操作列按钮' when e.buttontype='otherbtn' then '列表外按钮' else '无类型' end) as buttontype_as,  " +
                " (case when e.alwaysshow = '1' then '是' else '否' end) as alwaysshow_as,e.alwaysshow," +
                " (case when e.showbuttonmethod is null then '无' else e.showbuttonmethod end) as showbuttonmethod_as,e.showbuttonmethod," +
                " e.buttonmethod," +
                " to_char(e.createdate,'yyyy-mm-dd') as createdate_as,e.createdate," +
                " e.seq,e.remarks,e.color,e.icon," +
                " (case when e.color='btn blue' then '系统蓝' when e.color='btn btn-info' then '淡蓝色' when e.color='btn btn-success' then '绿色'" +
                "  when e.color='btn btn-dange' then '红色' when e.color='btn btn-warning' then '黄色' else '无' end) as color_as," +
                " (case when e.icon is null then '无' else e.icon end) as icon_as," +
                " e.judgerole,e.roledata,e.gridbtnicon,e.iconortext,e.businesstype,e.fittype " +
                " from pml_button e " +
                " where 1=1	" + insql + " order by e.seq";
        //分页配置
        JSONObject pageConfig = json.getJSONObject("pageConfig");
        //分页处理
        PageResult<List<Map>> page = this.findPageBySql(sql, paramList, Page.of(pageConfig));
        return page;
    }


    /**
     * 【新增和修改方法】
     *
     * @param request
     * @param json
     */
    public void addAndEdit(HttpServletRequest request, JSONObject json) {
        // 新增或者修改
        String type;
        PmlButton pmsButton = new PmlButton();
        String id = json.has("id") ? json.getString("id") : "";
        if (StringUtils.isNotBlank(id)) {
            //说明是修改
            pmsButton = this.findById(id);
            type = "按钮修改";
        } else {
            //说明是新增
            json.remove("id");
            pmsButton.setId(Util.NewGuid());
            pmsButton.setCreatedate(new Date());
            type = "按钮新增";
        }

        // 特殊字段处理
        json.put("alwaysshow", json.has("alwaysshow") && "1".equals(json.getString("alwaysshow")));
        json.put("judgerole", json.has("judgerole") && "1".equals(json.getString("judgerole")));

        // 先取出来赋值，之后移除，避免ApplyObjectNew报错
        if (json.has("roleData")) {
            JSONArray roleData = json.getJSONArray("roleData");
            pmsButton.setRoledata(roleData.toString());
        }
        json.remove("roledata");
        json.remove("roleData");


        // JSON数据转实体
        Util.ApplyObjectNew(pmsButton, json);

        // 特殊字段处理
        if (json.getBoolean("alwaysshow")) {
            pmsButton.setAlwaysshow(true);
            pmsButton.setShowbuttonmethod("");
            pmsButton.setRoledata("");
            pmsButton.setJudgerole(false);
        } else {
            pmsButton.setAlwaysshow(false);
            pmsButton.setJudgerole(json.getBoolean("judgerole"));
        }


        //保存或修改
        this.merge(pmsButton);
        //操作记录
        SysOperationrecord sysOperationrecord = new SysOperationrecord();
        sysOperationrecord.setSourceid(pmsButton.getId());
        sysOperationrecord.setType("通用列表-按钮配置");
        sysOperationrecord.setNote("操作人进行了" + type);
        this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
    }


    /**
     * 【删除方法】--通过id删除
     *
     * @param request
     * @param id
     */
    public boolean deleteDataById(HttpServletRequest request, String id) {
        //如果按钮被列表所选择，不能删除
        List<Map> gridButtonList = this.dbHelper.getRows("select id from pml_grid_button where buttonid = ?", new Object[]{id});
        if (!Util.isEoN(gridButtonList) && gridButtonList.size() > 0) {
            //说明按钮被列表选择，不能删除
            return false;
        } else {
            //可以删除
            PmlButton pmsButton = this.findById(id);
            String buttonname = pmsButton.getButtonname();
            //删除操作
            this.deleteById(id);
            //操作记录
            SysOperationrecord sysOperationrecord = new SysOperationrecord();
            sysOperationrecord.setSourceid(id);
            sysOperationrecord.setType("通用列表-按钮配置");
            sysOperationrecord.setNote("操作人进行了按钮删除，按钮名称:" + buttonname + "；按钮id:" + id);
            this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
            return true;
        }
    }

    /**
     * 【根据类型获取所有的数据】
     *
     * @return
     */
    public List<Map> getAllDataByType(String type) {
        String sql = "select t.*,(case when t.remarks is null then t.buttonname else t.buttonname || '(备注:' || t.remarks || ')' end) as showname" +
                "  from pml_button t where t.buttontype = ? order by t.seq";
        List<Map> resList = this.dbHelper.getRows(sql, new Object[]{type});
        return resList;
    }


    /**
     * 【获取所有角色数据】
     *
     * @return
     */
    public List<Map> getRoleData() {
        String sql = "select e.id,e.rolename,e.fz from sys_role e where e.enable = '1' order by fz ";
        List<Map> resList = this.dbHelper.getRows(sql, null);
        return resList;
    }


    public Map getRoleDataGroup() {
        String sql = "select e.id,e.rolename,e.fz from sys_role e where e.enable = '1' order by fz ";
        List<Map> resList = this.dbHelper.getRows(sql, null);
//		Map<String, List<Map>> map = resList.stream().collect(Collectors.groupingBy(m -> m.get("fz").toString(),Collectors.mapping(i->new HashMap(){{
//		    put("id",i.get("id").toString());
//		    put("rolename",i.get("rolename").toString());
//        }},toList())));
        Map<String, List<Map>> map = resList.stream().collect(Collectors.groupingBy(m -> m.get("fz").toString()));

        return map;
    }


    public ReturnToJs getButtonData(JSONObject json) {
        String id = json.getString("id");
        String sql = "SELECT e.* FROM PML_GRID e " +
                " LEFT JOIN PML_GRID_BUTTON g ON g.GRIDID  = e.id " +
                " LEFT JOIN PML_BUTTON b ON b.id = g.BUTTONID " +
                " WHERE b.id = ? ";
        List<Map> gridList = this.dbHelper.getRows(sql, new Object[]{id});
        return ReturnToJs.success(gridList);

    }
}
