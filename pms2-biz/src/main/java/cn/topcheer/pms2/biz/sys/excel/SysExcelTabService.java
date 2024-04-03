/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-10-15 16:46:18
 *
 */
package cn.topcheer.pms2.biz.sys.excel;

import cn.topcheer.halberd.app.api.utils.PmsTxtSave;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.excel.SysExcelParam;
import cn.topcheer.pms2.api.sys.excel.SysExcelTab;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.sys.excel.SysExcelTabDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SysExcelTab 服务类
 */
@Service(value="SysExcelTabService")
public class SysExcelTabService extends GenericService<SysExcelTab> {

	public SysExcelTabDao getSysExcelTabDao() {
		return (SysExcelTabDao) this.getGenericDao();
	}

	@Autowired
	public void setSysExcelTabDao(SysExcelTabDao sysExcelTabDao) {

		this.setGenericDao(sysExcelTabDao);
	}
    @Autowired
    private DBHelper dbHelper;
    @Autowired
    SysExcelParamService sysExcelParamService;
    @Autowired
    SysExcelStyleService sysExcelStyleService;
    @Autowired
    SysExcelFontService sysExcelFontService;
    @Autowired
    PmsTxtSave pmsTxtSave;

    //修改新增信息
    public ReturnToJs sysExcelTabSave(JSONObject json, ReturnToJs returnToJs) {
        String id = "";
        SysExcelTab pdtype =new SysExcelTab();
        if (Util.isEoN(json.get("id"))) {
            pdtype = new SysExcelTab();
            id = Util.NewGuid();
        } else {
            id = json.get("id") + "";
            pdtype = this.findById(id);
        }
        Util.ApplyObject(pdtype, json);
        pdtype.setId(id);
        this.merge(pdtype);
        returnToJs.setSuccess(true);
        returnToJs.setData(id);
        pmsTxtSave.saveTxt(id,json,"SysExcelTab","sysExcelTabSave");
        return returnToJs;
    }

    //查询信息
    public JSONArray sysExcelTabFind(JSONObject json) {
        String sheetid = "";
        if (!Util.isEoN(json.get("id"))) {
            sheetid = (json.get("id")).toString().trim();
        }
        List<Map> listMap = new ArrayList<>();
        if(Util.isEoN(sheetid)){
            String sql = "select e.name,e.id as excelid,s.id as sheetid,s.sheetname,t.* from sys_excel_tab t left join sys_excel_sheet s on s.id=t.sheetid " +
                    " left join sys_excel e on e.id=s.excelid  order by e.name,s.seq,t.seq ";
            listMap = dbHelper.getRows(sql, new Object[]{});
        }else if(!Util.isEoN(json.get("excelid"))){
            String sql = "select e.name,e.id as excelid,s.id as sheetid,s.sheetname,t.* from sys_excel_tab t left join sys_excel_sheet s on s.id=t.sheetid " +
                    " left join sys_excel e on e.id=s.excelid  order by (case when s.id=? then '0' when e.id=? then '1' else e.name end),s.seq,t.seq ";
            listMap = dbHelper.getRows(sql, new Object[]{sheetid,json.get("excelid")});
        }else{
            String sql = "select t.* from sys_excel_tab t left join sys_excel_sheet s on s.id=t.sheetid  where t.sheetid like ? order by t.seq";
            listMap = dbHelper.getRows(sql, new Object[]{sheetid});
            if(!Util.isEoN(json.get("excelid"))&&"px".equals(json.get("type"))){
            }
        }
        if (listMap != null && listMap.size() > 0)
            return JSONArray.fromObject(listMap);
        else
            return new JSONArray();
    }

    //查询信息
    public List<SysExcelTab> sysExcelTabFindByid(String sourceid) {
        String hql = "select t from SysExcelTab t where t.sheetid = ?0  order by t.seq";
        List<SysExcelTab> listMap = this.getSysExcelTabDao().findByHql(hql, new Object[]{sourceid});
        return listMap;
    }

    //删除信息
    public ReturnToJs sysExcelTabDelete(JSONObject json, ReturnToJs returnToJs) {
        String id=(json.get("id")+"").trim();
        SysExcelTab isHaveSysVersion = this.findById(id);
        try {
            if(!Util.isEoN(isHaveSysVersion)){
                /*说明这个版本id在数据库已经保存过了，要先清除相关联的数据，重新保存新给的对象*/
                        //3、字段param表
                        List<SysExcelParam> oldSysVersionFieldList = this.sysExcelParamService.findByProperty("sourceid",id);
                        if(!Util.isEoN(oldSysVersionFieldList)&&oldSysVersionFieldList.size()>0){
                            //1、删除param表
                            this.sysExcelParamService.deleteList(oldSysVersionFieldList);
                        }
                    }
            //4、删除tab表
            this.deleteById(id);
            returnToJs.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            returnToJs.setSuccess(false);
        }
        return  returnToJs;
    }
	
}
