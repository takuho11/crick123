/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2020-11-10 9:04:02
 */
package cn.topcheer.pms2.biz.pml.service.impl;


import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.sys.SysOperationrecord;
import cn.topcheer.pms2.api.pml.entity.PmlSearchbox;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.dao.pml.PmlSearchboxDao;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * PmlSearchbox 服务类
 *
 * @author GaoGongxin
 */
@Service(value = "PmlSearchboxService")
public class PmlSearchboxService extends GenericPageService<PmlSearchbox> {

    public PmlSearchboxDao getPmlSearchboxDao() {
        return (PmlSearchboxDao) this.getGenericDao();
    }

    @Autowired
    public void setPmlSearchboxDao(PmlSearchboxDao pmlSearchboxDao) {

        this.setGenericDao(pmlSearchboxDao);
    }

    @Autowired
    private SysOperationrecordService sysOperationrecordService;

    @Autowired
    private DBHelper dbHelper;

    /**
     * 初始化和搜索方法
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
        String sql = "select e.* from pml_searchbox e  where 1=1 " + insql + " order by e.seq ";
        //分页配置
        JSONObject pageConfig = json.getJSONObject("pageConfig");
        //分页处理
        PageResult<List<Map>> page = this.findPageBySql(sql, paramList, Page.of(pageConfig));
        return page;
    }

    /**
     * 新增和修改方法
     *
     * @param request
     * @param json
     */
    public void addAndEdit(HttpServletRequest request, JSONObject json) {
        // 新增或者修改
        String type;
        PmlSearchbox pmlSearchbox = new PmlSearchbox();
        String id = json.has("id") ? json.getString("id") : "";
        if (StringUtils.isNotBlank(id)) {
            //说明是修改
            pmlSearchbox = this.findById(id);
            type = "搜索框修改";
        } else {
            //说明是新增
            json.remove("id");
            pmlSearchbox.setId(Util.NewGuid());
            pmlSearchbox.setCreatedate(new Date());
            type = "搜索框新增";
        }

        // 特殊字段处理, 先取出来赋值，之后移除，避免ApplyObjectNew报错
        pmlSearchbox.setSpecialclass(!Util.isEoN(json.get("specialclass")) ? json.get("specialclass") + "" : "");
        json.remove("specialclass");

        // JSON数据转实体
        Util.ApplyObjectNew(pmlSearchbox, json);

        // 保存或修改
        this.merge(pmlSearchbox);
        //操作记录
        SysOperationrecord sysOperationrecord = new SysOperationrecord();
        sysOperationrecord.setSourceid(pmlSearchbox.getId());
        sysOperationrecord.setType("通用列表-搜索框配置");
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
        //如果搜索框被搜索框组合，不能删除
        List<Map> searchboxList = this.dbHelper.getRows("select id from Pms_Searchbox_Collection where searchboxid = ?", new Object[]{id});
        if (!Util.isEoN(searchboxList) && searchboxList.size() > 0) {
            //说明搜索框被列表选择、或被组合，不能删除
            return false;
        } else {
            //可以删除
            PmlSearchbox pmlSearchbox = this.findById(id);
            String boxName = pmlSearchbox.getName();
            //删除操作
            this.deleteById(id);
            //操作记录
            SysOperationrecord sysOperationrecord = new SysOperationrecord();
            sysOperationrecord.setSourceid(id);
            sysOperationrecord.setType("通用列表-搜索框配置");
            sysOperationrecord.setNote("操作人进行了搜索框删除，列名:" + boxName + "；列名id:" + id);
            this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
            return true;
        }
    }

    /**
     * 获取所有的搜索框数据
     *
     * @return
     */
    public List<Map> getAllData() {
        String sql = "select t.*,(case when t.memo is null then t.name else t.name || '(备注:' || t.memo || ')' end) as showname " +
                "  from pml_searchbox t order by t.seq";
        List<Map> resList = this.dbHelper.getRows(sql, null);
        return resList;
    }


}
