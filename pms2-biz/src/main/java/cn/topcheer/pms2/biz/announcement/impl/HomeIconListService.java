package cn.topcheer.pms2.biz.announcement.impl;


import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.announcement.HomeIconList;
import cn.topcheer.pms2.api.pml.vo.Page;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.pml.service.impl.PageService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.announcement.HomeIconListDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "HomeIconListService")
public class HomeIconListService extends GenericService<HomeIconList> {
    public HomeIconListDao getHomeIconListDao() {
        return (HomeIconListDao) this.getGenericDao();
    }

    @Autowired
    public void setHomeIconListDao(HomeIconListDao homeIconListDao) {

        this.setGenericDao(homeIconListDao);
    }
    @Autowired
    DBHelper dbHelper;
    @Autowired
    PageService pageService;


    public void saveOrAddIcon(JSONObject json){
        String id = json.get("id")+"";
        HomeIconList homeIconList = this.findById(id);
        if(!Util.isEoN(homeIconList)){
            Util.ApplyObject(homeIconList,json);
            if (json.has("roledata")) {
                JSONArray roleData = json.getJSONArray("roledata");
                homeIconList.setRoledata(roleData.toString());
            }
        }
        else {
            homeIconList = new HomeIconList();
            Util.ApplyObject(homeIconList,json);
            if (json.has("roledata")) {
                JSONArray roleData = json.getJSONArray("roledata");
                homeIconList.setRoledata(roleData.toString());
            }
            homeIconList.setId(UUID.randomUUID() + "");

        }
        homeIconList.setCreatedate(new Date());
        this.merge(homeIconList);
    }

    public Page<Map> getIconList(JSONObject json){
        String insql = "";
        List<String> paramList = new ArrayList<>();//条件参数集合
        JSONObject pageConfig = json.getJSONObject("pageConfig");//前台分页参数
        if (Util.isEoN(pageConfig)) {
            return null;
        }

        if (json.has("searchContent")) {
            insql = " and (e.name like ? or e.path like ? or e.type like ? )";
            paramList.add("%"+json.get("searchContent")+"%");
            paramList.add("%"+json.get("searchContent")+"%");
            paramList.add("%"+json.get("searchContent")+"%");
        }

        String sql = "select e.* from homeiconlist e where 1=1 "+insql;
        Page<Map> page = new Page<>();
        //分页配置
        if (json.containsKey("pageConfig")) {

            //分页处理
            page = this.pageService.findPageBySql(sql,paramList,pageConfig);
        } else {
            List<Map> list = dbHelper.getRows(sql,paramList.toArray());
            page.setTotal(list.size());
        }

        return page;
    }

    public ReturnToJs deleteIcon(String id){
        HomeIconList byId = this.findById(id);
        if(Util.isEoN(byId)){
            return ReturnToJs.failure("未找到这条数据");
        }else {
            this.deleteById(id);
        }
        return ReturnToJs.success();
    }


    public List<Map> getIconByRole(String userid){
        String sql = "";
        List<Map> rolerows = new ArrayList();
        List<Map> rows = new ArrayList<>();
        List<Map> roleList = this.dbHelper.getRows("select e.roleid,r.fz from sys_identity e left join sys_role r on e.roleid = r.id where e.userid = ? group by e.roleid,r.fz ",new Object[]{userid});
        for (int i = 0; i < roleList.size(); i++) {
            if(!roleList.get(i).get("fz").toString().equals("评审中心")){
                sql = "select * from homeiconlist  where roledata like ? ";
                rolerows = this.dbHelper.getRows(sql, new Object[]{"%"+roleList.get(i).get("roleid")+"%"});
                rows.addAll(rolerows);
            }else{
                sql = "select distinct e.id,e.* from homeiconlist e where e.roledata like ? ";
                rolerows = this.dbHelper.getRows(sql, new Object[]{"%"+roleList.get(i).get("roleid")+"%"});
                rows.addAll(rolerows);
                break;
            }

        }
        return rows;
    }

}
