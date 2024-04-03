/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2019-3-3 15:54:33
 */
package cn.topcheer.pms2.biz.pml.service.impl;


import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.pms2.api.sys.SysOperationrecord;
import cn.topcheer.pms2.api.pml.entity.PmlColumn;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.sys.SysOperationrecordService;
import cn.topcheer.pms2.dao.pml.PmlColumnDao;
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
 * PmlColumn 服务类
 */
@Service(value = "PmlColumnService")
public class PmlColumnService extends GenericPageService<PmlColumn> {

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private SysOperationrecordService sysOperationrecordService;


    public PmlColumnDao getPmlColumnDao() {
        return (PmlColumnDao) this.getGenericDao();
    }

    @Autowired
    public void setPmlColumnDao(PmlColumnDao pmsColumnDao) {

        this.setGenericDao(pmsColumnDao);
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
            insql = " and (e.columnname_ch like ? or e.columnname_en like ? or e.remarks like ? )";
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
        String sql = "select e.id,e.columnname_ch,e.columnname_en," +
                " (case when e.havejump = '1' then '有' else '无' end) as havejump_as,e.havejump," +
                " (case when e.jumpmethod is null then '无' else e.jumpmethod end) as jumpmethod_as,e.jumpmethod," +
                " to_char(e.createdate,'yyyy-mm-dd') as createdate_as,e.createdate," +
                " e.seq,e.columnwidth,e.remarks,e.businesstype,e.fittype,e.sorttype " +
                " from pml_column e " +
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
        PmlColumn pmsColumn = new PmlColumn();
        String id = json.has("id") ? json.getString("id") : "";
        if (StringUtils.isNotBlank(id)) {
            // 说明是修改
            pmsColumn = this.findById(id);
            type = "列名修改";
        } else {
            // 说明是新增
            json.remove("id");
            pmsColumn.setId(Util.NewGuid());
            pmsColumn.setCreatedate(new Date());
            type = "列名新增";
        }

        // 特殊字段处理
        json.put("havejump", json.has("havejump") && "1".equals(json.getString("havejump")));

        // JSON数据转实体
        Util.ApplyObjectNew(pmsColumn, json);

        // 特殊字段处理
        if (json.getBoolean("havejump")) {
            pmsColumn.setHavejump(true);
        } else {
            pmsColumn.setHavejump(false);
            pmsColumn.setJumpmethod("");
            pmsColumn.setJumptips("");
        }

        //保存或修改
        this.merge(pmsColumn);
        //操作记录
        SysOperationrecord sysOperationrecord = new SysOperationrecord();
        sysOperationrecord.setSourceid(pmsColumn.getId());
        sysOperationrecord.setType("通用列表-列名配置");
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
        //如果列名被列表所选择，不能删除
        List<Map> gridColumeList = this.dbHelper.getRows("select id from pml_grid_column where columnid = ?", new Object[]{id});
        if (!Util.isEoN(gridColumeList) && gridColumeList.size() > 0) {
            //说明列名被列表选择，不能删除
            return false;
        } else {
            //可以删除
            PmlColumn pmsColumn = this.findById(id);
            String columnname = pmsColumn.getColumnnameCh();
            //删除操作
            this.deleteById(id);
            //操作记录
            SysOperationrecord sysOperationrecord = new SysOperationrecord();
            sysOperationrecord.setSourceid(id);
            sysOperationrecord.setType("通用列表-列名配置");
            sysOperationrecord.setNote("操作人进行了列名删除，列名:" + columnname + "；列名id:" + id);
            this.sysOperationrecordService.saveCurrentUserOperation(request, sysOperationrecord);
            return true;
        }
    }

    /**
     * 【获取所有的数据】
     *
     * @return
     */
    public List<Map> getAllData() {
        String sql = "select t.*,(case when t.remarks is null then t.columnname_ch else t.columnname_ch || '(备注:' || t.remarks || ')' end) as showname " +
                "  from pml_column t order by t.seq";
        List<Map> resList = this.dbHelper.getRows(sql, null);
        return resList;
    }


    public ReturnToJs getGridData(JSONObject json) {
        String id = json.getString("id");
        String sql = "SELECT e.* FROM PML_GRID e " +
                " LEFT JOIN PML_GRID_COLUMN g ON g.GRIDID  = e.id " +
                " LEFT JOIN PML_COLUMN c ON c.id = g.COLUMNID " +
                " WHERE c.id = ? ";
        List<Map> gridList = this.dbHelper.getRows(sql, new Object[]{id});
        return ReturnToJs.success(gridList);
    }
}
