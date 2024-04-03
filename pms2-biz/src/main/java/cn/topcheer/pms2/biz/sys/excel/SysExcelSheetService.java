/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-9-24 11:57:03
 *
 */
package cn.topcheer.pms2.biz.sys.excel;

import cn.topcheer.halberd.app.api.utils.PmsTxtSave;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.excel.SysExcelParam;
import cn.topcheer.pms2.api.sys.excel.SysExcelSheet;
import cn.topcheer.pms2.api.sys.excel.SysExcelTab;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.sys.excel.SysExcelSheetDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysExcelSheet 服务类
 */
@Service(value="SysExcelSheetService")
public class SysExcelSheetService extends GenericService<SysExcelSheet> {

    public SysExcelSheetDao getSysExcelSheetDao() {
        return (SysExcelSheetDao) this.getGenericDao();
    }

    @Autowired
    public void setSysExcelSheetDao(SysExcelSheetDao sysExcelSheetDao) {

        this.setGenericDao(sysExcelSheetDao);
    }

    @Autowired
    private DBHelper dbHelper;
    @Autowired
    SysExcelTabService sysExcelTabService;
    @Autowired
    SysExcelParamService sysExcelParamService;
    @Autowired
    SysExcelStyleService sysExcelStyleService;
    @Autowired
    SysExcelFontService sysExcelFontService;
    @Autowired
    PmsTxtSave pmsTxtSave;

    //修改新增信息
    public ReturnToJs sysExcelSheetSave(JSONObject json, ReturnToJs returnToJs) {
        String id = "";
        SysExcelSheet pdtype = new SysExcelSheet();
        if (Util.isEoN(json.get("id"))) {
            id = Util.NewGuid();
            pdtype.setId(id);
        } else {
            id = json.get("id") + "";
            pdtype = this.findById(id);
        }
        Util.ApplyObject(pdtype, json);
        this.merge(pdtype);
        returnToJs.setData(id);
        returnToJs.setSuccess(true);
        pmsTxtSave.saveTxt(id,json,"SysExcelSheet","sysExcelSheetSave");
        return returnToJs;
    }

    //查询信息
    public JSONArray sysExcelSheetFind(JSONObject json) {
        String excelid = "";
        if (!Util.isEoN(json.get("id"))) {
            excelid = (json.get("id")).toString().trim();
        }
        String sql = "select t.* from sys_excel_sheet t left join sys_excel s on s.id=t.excelid  where t.excelid like ? order by t.seq";
        List<Map> listMap = dbHelper.getRows(sql, new Object[]{excelid});
        if (listMap != null && listMap.size() > 0)
            return JSONArray.fromObject(listMap);
        else
            return new JSONArray();
    }

    //查询信息
    public List<SysExcelSheet> sysExcelSheetFindByid(String sourceid) {
        String hql = "select t from SysExcelSheet t where t.excelid = ?0  order by t.seq";
        List<SysExcelSheet> listMap = this.getSysExcelSheetDao().findByHql(hql, new Object[]{sourceid});
        return listMap;
    }

    //删除信息
    public ReturnToJs sysExcelSheetDelete(JSONObject json, ReturnToJs returnToJs) {
        String id=(json.get("id")+"").trim();
        SysExcelSheet isHaveSysVersion = this.findById(id);
        try {
            if(!Util.isEoN(isHaveSysVersion)){
                /*说明这个版本id在数据库已经保存过了，要先清除相关联的数据，重新保存新给的对象*/
                        //2、对象tab表
                        List<SysExcelTab> oldSysVersionObjectList = this.sysExcelTabService.findByProperty("sheetid",id);
                        if(!Util.isEoN(oldSysVersionObjectList)&&oldSysVersionObjectList.size()>0){
                            for (int j = 0; j < oldSysVersionObjectList.size(); j++) {
                                String sourceid = oldSysVersionObjectList.get(j).getId();
                                //3、param表
                                List<SysExcelParam> oldSysVersionFieldList = this.sysExcelParamService.findByProperty("sourceid",sourceid);
                                if(!Util.isEoN(oldSysVersionFieldList)&&oldSysVersionFieldList.size()>0){
                                    //1、删除param表
                                    this.sysExcelParamService.deleteList(oldSysVersionFieldList);
                                }
                            }
                            //2、删除tab表
                            this.sysExcelTabService.deleteList(oldSysVersionObjectList);
                        }
                    }
                //4、删除sheet表
                this.deleteById(id);
                returnToJs.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            returnToJs.setSuccess(false);
        }
        return  returnToJs;
    }
}