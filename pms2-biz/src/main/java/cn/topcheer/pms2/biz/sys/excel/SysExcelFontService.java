/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-9-25 18:23:38
 *
 */
package cn.topcheer.pms2.biz.sys.excel;

import cn.topcheer.halberd.app.api.utils.PmsTxtSave;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.api.sys.excel.SysExcelFont;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.dao.jpa.sys.excel.SysExcelFontDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysExcelFont 服务类
 */
@Service(value="SysExcelFontService")
public class SysExcelFontService extends GenericService<SysExcelFont> {

	public SysExcelFontDao getSysExcelFontDao() {
		return (SysExcelFontDao) this.getGenericDao();
	}

	@Autowired
	public void setSysExcelFontDao(SysExcelFontDao sysExcelFontDao) {

		this.setGenericDao(sysExcelFontDao);
	}
    @Autowired
    private DBHelper dbHelper;
    @Autowired
    PmsTxtSave pmsTxtSave;

    //修改新增信息
    public ReturnToJs sysExcelFontSave(JSONObject json, ReturnToJs returnToJs) {
        String id = "";
        SysExcelFont pdtype = new SysExcelFont();
        if (Util.isEoN(json.get("id"))) {
            id = Util.NewGuid();
            pdtype.setId(id);
        } else {
            id = json.get("id") + "";
            pdtype = this.findById(id);
        }
        Util.ApplyObject(pdtype, json);
        this.merge(pdtype);
        returnToJs.setSuccess(true);
        pmsTxtSave.saveTxt(id,json,"SysExcelFont","sysExcelFontSave");
        return returnToJs;
    }

    //查询信息
    public JSONArray sysExcelFontFind() {
        String sql = "select t.* from sys_excel_font t ";
        List<Map> listMap = dbHelper.getRows(sql,new Object[]{});
        if (listMap != null && listMap.size() > 0)
            return JSONArray.fromObject(listMap);
        else
            return new JSONArray();
    }

    //删除信息
    public ReturnToJs sysExcelFontDelete(JSONObject json, ReturnToJs returnToJs) {
        String id = (json.get("id") + "").trim();
        try {
            this.deleteById(id);
            returnToJs.setSuccess(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            returnToJs.setSuccess(false);
        }
        return returnToJs;
    }

    public byte getFontUnderline (String Underline){
        switch (Util.isEoN(Underline)?"":Underline){
            case "U_SINGLE":
                return Font.U_SINGLE;
            case "U_DOUBLE":
                return Font.U_DOUBLE;
            case "U_SINGLE_ACCOUNTING":
                return Font.U_SINGLE_ACCOUNTING;
            case "U_DOUBLE_ACCOUNTING":
                return Font.U_DOUBLE_ACCOUNTING;
            case "U_NONE":
                return Font.U_NONE;
            default:
                return Font.U_NONE;
        }
    }

    public short getTypeOffset (String TypeOffset){
        switch (Util.isEoN(TypeOffset)?"":TypeOffset){
            case "SS_SUPER":
                return Font.SS_SUPER;
            case "SS_SUB":
                return Font.SS_SUB;
            case "SS_NONE":
                return Font.SS_NONE;
            default:
                return Font.SS_NONE;
        }
    }


}
