/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2020-11-10 18:31:58
 */
package cn.topcheer.pms2.biz.pml.service.impl;


import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.sys.SysOperationrecord;
import cn.topcheer.pms2.api.pml.entity.PmlCollection;
import cn.topcheer.pms2.api.pml.entity.PmlGrid;
import cn.topcheer.pms2.api.pml.entity.PmlSearchboxCollection;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.dao.pml.PmlCollectionDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * PmlCollection 服务类
 */
@Service(value = "PmlCollectionService")
public class PmlCollectionService extends GenericPageService<PmlCollection> {

    public PmlCollectionDao getPmlCollectionDao() {
        return (PmlCollectionDao) this.getGenericDao();
    }

    @Autowired
    public void setPmlCollectionDao(PmlCollectionDao pmsCollectionDao) {

        this.setGenericDao(pmsCollectionDao);
    }

    @Autowired
    private SysOperationrecordService sysOperationrecordService;

    @Autowired
    private DBHelper dbHelper;


    @Autowired
    private PmlSearchboxCollectionService pmsSearchboxCollectionService;

    @Autowired
    @Lazy
    private PmlGridService pmsGridService;

    /**
     * 搜索框组合初始化和搜索方法
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
            insql = " and (e.name like ? )";
            paramList.add("%" + json.get("searchContent") + "%");
        }
        //sql语句
        String sql = "select e.* from pml_collection e  where 1=1 " + insql + " order by e.seq ";
        //分页配置
        JSONObject pageConfig = json.getJSONObject("pageConfig");
        //分页处理
        PageResult<List<Map>> page = this.findPageBySql(sql, paramList, Page.of(pageConfig));
        return page;
    }


    /**
     * 根据id获取列表组信息
     *
     * @param json
     * @return
     */
    public Map getDataById(JSONObject json) {
        String id = json.get("id") + "";
        List<Map> resList = this.dbHelper.getRows("select e.* from pml_collection e where e.id = ?", new Object[]{id});
        Map resMap = new HashMap();
        if (!Util.isEoN(resList) && resList.size() > 0) {
            resMap = resList.get(0);
        } else {
            return resMap;
        }
        //获取搜索框数据
        List<Map> searchboxData = this.dbHelper.getRows("select s.*,(case when s.memo is null then s.name else s.name || '(备注:' || s.memo || ')' end) as showname " +
                " from pml_collection e " +
                " left join pml_searchbox_collection t on e.id = t.collectionid " +
                " left join pml_searchbox s on s.id = t.searchboxid " +
                " where e.id = ? order by t.seq", new Object[]{id});
        resMap.put("searchboxData", searchboxData);
        resMap.put("widthconfig", Util.isEoN(resMap.get("widthconfig")) ? "" : resMap.get("widthconfig"));
        return resMap;
    }

    /**
     * 新增和修改方法
     *
     * @param request
     * @param json
     */
    public void addAndEdit(HttpServletRequest request, JSONObject json) {
        //新增或者修改
        String type;
        PmlCollection pmsCollection = new PmlCollection();
        String id = json.has("id") ? json.getString("id") : "";
        if (StringUtils.isNotBlank(id)) {
            //说明是修改
            pmsCollection = this.findById(id);
            type = "搜索框组合修改";
        } else {
            //说明是新增
            json.remove("id");
            pmsCollection.setId(Util.NewGuid());
            pmsCollection.setCreatedate(new Date());
            type = "搜索框组合新增";
        }

        // 特殊字段处理，先取出来赋值，之后移除，避免ApplyObjectNew报错
        String widthconfig = "";
        if (!Util.isEoN(json.get("widthconfig"))) {
            if (!Util.isJSONObject(json.get("widthconfig") + "")) {
                throw new ServiceException("宽度配置格式不正确");
            }
            widthconfig = JSONObject.fromObject(json.get("widthconfig")).toString();
            widthconfig = "\"" + widthconfig + "\"";
        }
        pmsCollection.setWidthconfig(widthconfig);
        json.remove("widthconfig");

        // JSON数据转实体
        Util.ApplyObjectNew(pmsCollection, json);

        // 特殊字段处理


        if (json.containsKey("searchboxData")) {
            //先删除原来选择的搜索框
            List<PmlSearchboxCollection> pmsSearchboxCollectionList = this.pmsSearchboxCollectionService.findByProperty("collectionid", pmsCollection.getId());
            if (!Util.isEoN(pmsSearchboxCollectionList) && pmsSearchboxCollectionList.size() > 0) {
                this.pmsSearchboxCollectionService.deleteList(pmsSearchboxCollectionList);
            }
            //选择搜索框
            JSONArray searchboxData = json.getJSONArray("searchboxData");
            if (!Util.isEoN(searchboxData) && searchboxData.size() > 0) {
                for (int i = 0; i < searchboxData.size(); i++) {
                    PmlSearchboxCollection pmsSearchboxCollection = new PmlSearchboxCollection();
                    pmsSearchboxCollection.setId(Util.NewGuid());
                    pmsSearchboxCollection.setSearchboxid(searchboxData.getJSONObject(i).get("id") + "");
                    pmsSearchboxCollection.setCollectionid(pmsCollection.getId());
                    pmsSearchboxCollection.setSeq(i);
                    this.pmsSearchboxCollectionService.merge(pmsSearchboxCollection);
                }
            }
        }

        //保存或修改
        this.merge(pmsCollection);
        //操作记录
        SysOperationrecord sysOperationrecord = new SysOperationrecord();
        sysOperationrecord.setSourceid(pmsCollection.getId());
        sysOperationrecord.setType("通用列表-搜索框组合配置");
        sysOperationrecord.setNote("操作人进行了" + type);
        this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
    }

    /**
     * 删除方法--通过id删除
     *
     * @param request
     * @param id
     * @return
     */
    public boolean deleteDataById(HttpServletRequest request, String id) {
        try {
            //判断该搜索框是否被使用，如被使用则禁止删除
            List<PmlGrid> pmsGridList = pmsGridService.findByProperty("collectionid", id);
            if (!Util.isEoN(pmsGridList) && pmsGridList.size() > 0) {
                return false;
            }
            //删除搜索框和搜索框组合的关联表
            this.dbHelper.runSql("delete from pml_searchbox_collection e where e.collectionid = ? ", new Object[]{id});

            //删除搜索框组合
            PmlCollection pmsCollection = this.findById(id);
            String name = pmsCollection.getName();
            this.deleteById(id);
            //操作记录
            SysOperationrecord sysOperationrecord = new SysOperationrecord();
            sysOperationrecord.setSourceid(id);
            sysOperationrecord.setType("通用列表-搜索框组合配置");
            sysOperationrecord.setNote("操作人进行了列表删除，列表名称:" + name + "；搜索框组合id:" + id);
            this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取所有的列表数据--为了grid选择
     *
     * @return
     */
    public List<Map> getAllDataForGrid() {
        String sql = "select t.id, (t.businesstype || ':'||t.name||','||'备注:'||t.memo) as showname,t.name,t.businesstype " +
                "  from pml_collection t order by t.businesstype,t.seq ";
        List<Map> resList = dbHelper.getRows(sql, null);
        return resList;
    }
}
