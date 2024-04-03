/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2019-4-19 11:02:54
 */
package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.sys.SysOperationrecord;
import cn.topcheer.pms2.api.pml.entity.PmlGrid;
import cn.topcheer.pms2.api.pml.entity.PmlGridtabs;
import cn.topcheer.pms2.api.pml.entity.PmlGridtabsGrid;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.dao.pml.PmlGridtabsDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * PmlGridtabs 服务类
 */
@Service(value = "PmlGridtabsService")
public class PmlGridtabsService extends GenericPageService<PmlGridtabs> {

    public PmlGridtabsDao getPmlGridtabsDao() {
        return (PmlGridtabsDao) this.getGenericDao();
    }

    @Autowired
    public void setPmlGridtabsDao(PmlGridtabsDao pmlGridtabsDao) {

        this.setGenericDao(pmlGridtabsDao);
    }

    @Autowired
    private PmlGridService pmlGridService;
    @Autowired
    private PmlGridtabsGridService pmlGridtabsGridService;
    @Autowired
    private SysOperationrecordService sysOperationrecordService;
    @Autowired
    private PmlCommonGridService pmlCommonGridService;
    @Autowired
    private SysUserServiceImpl userService;
    @Autowired
    private DBHelper dbHelper;

    /**
     * 【获取所有列表Tabs数据】
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
            insql = " and (e.name like ? or e.type like ? )";
            paramList.add("%" + json.get("searchContent") + "%");
            paramList.add("%" + json.get("searchContent") + "%");
        }

        //sql语句
        String sql = "select e.*," +
                " to_char(e.createdate,'yyyy-mm-dd') as createdate_as " +
                " from pml_gridtabs e " +
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
        PmlGridtabs pmlGridtabs = new PmlGridtabs();
        String id = json.has("id") ? json.getString("id") : "";
        if (StringUtils.isNotBlank(id)) {
            //说明是修改
            pmlGridtabs = this.findById(id);
            type = "列表Tabs修改";
        } else {
            //说明是新增
            json.remove("id");
            pmlGridtabs.setId(Util.NewGuid());
            pmlGridtabs.setCreatedate(new Date());
            type = "列表Tabs新增";
        }

        // 特殊字段处理
        json.remove("createdate");

        // JSON数据转实体
        Util.ApplyObjectNew(pmlGridtabs, json);

        //处理关联表
        if (json.has("gridConfigData")) {
            //先删除原来选择的关联表
            List<PmlGridtabsGrid> pmlGridtabsGridList = this.pmlGridtabsGridService.findByProperty("gridtabsid", json.get("id") + "");
            if (pmlGridtabsGridList != null && pmlGridtabsGridList.size() > 0) {
                this.pmlGridtabsGridService.deleteList(pmlGridtabsGridList);
            }
            //处理选择的列表配置
            JSONArray jsonArr = json.getJSONArray("gridConfigData");
            if (jsonArr.size() > 0) {
                for (int i = 0; i < jsonArr.size(); i++) {
                    JSONObject nowJson = jsonArr.getJSONObject(i);
                    PmlGrid pmlGrid = this.pmlGridService.findById(nowJson.getString("id"));
                    if (pmlGrid != null) {
                        PmlGridtabsGrid pmlGridtabsGrid = new PmlGridtabsGrid();
                        pmlGridtabsGrid.setId(Util.NewGuid());
                        pmlGridtabsGrid.setGridid(pmlGrid.getId());
                        pmlGridtabsGrid.setGridtabsid(pmlGridtabs.getId());
                        pmlGridtabsGrid.setGridtype(pmlGrid.getGridtype());
                        pmlGridtabsGrid.setGridname(pmlGrid.getGridname());
                        pmlGridtabsGrid.setSeq(i);
                        this.pmlGridtabsGridService.merge(pmlGridtabsGrid);
                    }
                }
            }
        }
        //保存或者更新主表
        this.merge(pmlGridtabs);
        //操作记录
        SysOperationrecord sysOperationrecord = new SysOperationrecord();
        sysOperationrecord.setSourceid(pmlGridtabs.getId());
        sysOperationrecord.setType("通用列表-列表Tabs配置");
        sysOperationrecord.setNote("操作人进行了" + type);
        this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
    }

    /**
     * 【通过id查找列表tabs的数据】
     *
     * @param json
     * @return
     */
    public Map getGridTabsDataById(JSONObject json) {
        String id = json.get("id") + "";
        List<Map> resList = dbHelper.getRows("select * from pml_gridtabs  where id = ?", new Object[]{id});
        Map resMap = new HashMap();
        if (!Util.isEoN(resList) && resList.size() > 0) {
            resMap = resList.get(0);
        }
        //获取列表配置数据
        List<Map> gridConfigData = dbHelper.getRows("select g.id, (g.businesstype || ':'||g.gridname||','||'备注:'||g.remarks) as showname,g.gridname" +
                " from pml_gridtabs_grid gtg" +
                " left join pml_grid g on gtg.gridid = g.id" +
                " where gtg.gridtabsid = ?  order by gtg.seq ", new Object[]{id});
        resMap.put("gridConfigData", gridConfigData);
        return resMap;
    }


    /**
     * 【删除方法】--通过id删除
     *
     * @param request
     * @param id
     */
    public boolean deleteDataById(HttpServletRequest request, String id) {
        try {
            //删除关联表
            this.runSql("delete from pml_gridtabs_grid where gridtabsid = ?", new Object[]{id});
            //删除列表tabs
            PmlGridtabs pmlGridtabs = this.findById(id);
            String name = pmlGridtabs.getName();
            this.deleteById(id);
            //操作记录
            SysOperationrecord sysOperationrecord = new SysOperationrecord();
            sysOperationrecord.setSourceid(id);
            sysOperationrecord.setType("通用列表-列表tabs配置");
            sysOperationrecord.setNote("操作人进行了列表tabs删除，列表tabs名称:" + name + "；列表tabsid:" + id);
            this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 【通用列表】--获取列表Tabs配置数据
     *
     * @param json
     */
    public List<Map> getGridTabsConfigData(SysUser user, JSONObject json) {
        List<Map> returnList = new ArrayList<>();
        String gridTabs = json.get("gridTabs") + "";
        List<Map> resList = dbHelper.getRows("select gtg.id,gtg.gridtabsid,gtg.gridid,gtg.gridtype,gtg.seq,grid.gridname,gt.name as gridtabsname," +
                " grid.fittype,grid.hidectrl " +
                " from pml_gridtabs_grid gtg " +
                " left join pml_gridtabs gt on gtg.gridtabsid = gt.id" +
                " left join pml_grid grid on gtg.gridid = grid.id" +
                " where gt.type = ?" +
                " order by gtg.seq", new Object[]{gridTabs});
        if (resList != null && resList.size() > 0) {
            for (int i = 0; i < resList.size(); i++) {
                //判断当前角色能否使用该列表
                PmlGrid pmlGrid = this.pmlGridService.findById(resList.get(i).get("gridid") + "");
                resList.get(i).put("gridtype", pmlGrid.getGridtype());
                if (pmlGrid.getJudgerole()) {
                    //需要判断角色
                    JSONArray roleData = JSONArray.fromObject(pmlGrid.getRoledata());//列表配置角色
//					String nowRole = this.getOnlyValueBySql("select e.id , listagg(i.roleid) as rid " +   //当前用户角色
//							" from sys_user e " +
//							" left join sf_role i on e.id = i.userid " +
//							" where e.id = ? " +
//							" group by e.id",new Object[]{user.getId()});
                    String userId = AuthUtil.getUserId();
                    if (StringUtils.isBlank(userId)) {
                        throw new ServiceException("用户未登录");
                    }
                    SysUser sysUser = userService.findById(userId);
                    String nowRole = sysUser.getRoleId();

                    if (roleData != null && roleData.size() > 0) {
                        Boolean flag = false;//ture表示当前角色可以使用该列表
                        for (int j = 0; j < roleData.size(); j++) {
                            JSONObject roleJson = roleData.getJSONObject(j);
                            String nowRoleid = roleJson.get("id") + "";
                            if (nowRole.contains(nowRoleid)) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            //获取每个列表的总数
                            resList.get(i).put("totalCount", this.pmlCommonGridService.getCountByClass(user, pmlGrid, json));
                            returnList.add(resList.get(i));
                        }
                    }
                } else {
                    //获取每个列表的总数
                    resList.get(i).put("totalCount", this.pmlCommonGridService.getCountByClass(user, pmlGrid, json));
                    returnList.add(resList.get(i));
                }
            }
        }
        return returnList;
    }


}
