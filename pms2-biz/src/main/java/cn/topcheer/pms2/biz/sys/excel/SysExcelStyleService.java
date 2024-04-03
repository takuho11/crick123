/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-9-25 18:23:38
 *
 */
package cn.topcheer.pms2.biz.sys.excel;

import cn.topcheer.halberd.app.api.utils.PmsTxtSave;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.excel.SysExcelStyle;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.sys.excel.SysExcelStyleDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysExcelStyle 服务类
 */
@Service(value="SysExcelStyleService")
public class SysExcelStyleService extends GenericService<SysExcelStyle> {

	public SysExcelStyleDao getSysExcelStyleDao() {
		return (SysExcelStyleDao) this.getGenericDao();
	}

	@Autowired
	public void setSysExcelStyleDao(SysExcelStyleDao sysExcelStyleDao) {

		this.setGenericDao(sysExcelStyleDao);
	}
    @Autowired
    private DBHelper dbHelper;
    @Autowired
    SysExcelFontService sysExcelFontService;
    @Autowired
    PmsTxtSave pmsTxtSave;

    //修改新增信息
    public ReturnToJs sysExcelStyleSave(JSONObject json, ReturnToJs returnToJs) {
        String id = "";
        SysExcelStyle pdtype = new SysExcelStyle();
        if (Util.isEoN(json.get("id"))) {
            pdtype = new SysExcelStyle();
            id = Util.NewGuid();
            pdtype.setId(id);
        } else {
            id = json.get("id") + "";
            pdtype = this.findById(id);
        }
        Util.ApplyObject(pdtype, json);
        this.merge(pdtype);
        returnToJs.setSuccess(true);
        pmsTxtSave.saveTxt(id,json,"SysExcelStyle","sysExcelStyleSave");
        return returnToJs;
    }

    //查询信息
    public JSONArray sysExcelStyleFind() {
        String sql = "select t.* from sys_excel_style t";
        List<Map> listMap = dbHelper.getRows(sql,new Object[]{});
        if (listMap != null && listMap.size() > 0)
            return JSONArray.fromObject(listMap);
        else
            return new JSONArray();
    }

    //删除信息
    public ReturnToJs sysExcelStyleDelete(JSONObject json, ReturnToJs returnToJs) {
        String id = (json.get("id")+"").trim();
        try {
            this.deleteById(id);
            returnToJs.setSuccess(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            returnToJs.setSuccess(false);
        }
        return returnToJs;
    }
    public short getFillforegroundcolorForRead (String fillforegroundcolor){
        switch (Util.isEoN(fillforegroundcolor)?"":fillforegroundcolor){
            case "RED":
                return IndexedColors.RED.getIndex();
            case "BLACK":
                return IndexedColors.BLACK.getIndex();
            case "WHITE":
                return IndexedColors.WHITE.getIndex();
            case "GREEN":
                return IndexedColors.GREEN.getIndex();
            case "BLUE":
                return IndexedColors.BLUE.getIndex();
            case "YELLOW":
                return IndexedColors.YELLOW.getIndex();
            case "PINK":
                return IndexedColors.PINK.getIndex();
            case "ORANGE":
                return IndexedColors.ORANGE.getIndex();
            case "GREY_25_PERCENT":
                return IndexedColors.GREY_25_PERCENT.getIndex();
            case "GREY_40_PERCENT":
                return IndexedColors.GREY_40_PERCENT.getIndex();
            case "GREY_50_PERCENT":
                return IndexedColors.GREY_50_PERCENT.getIndex();
            case "GREY_80_PERCENT":
                return IndexedColors.GREY_80_PERCENT.getIndex();
            default:
                return (short) 13;
        }
    }

    public BorderStyle getBorderForRead (String border){
        switch (Util.isEoN(border)?"":border){
            case "BORDER_NONE":
                return BorderStyle.NONE;
            case "BORDER_THIN":
                return BorderStyle.THIN;
            case "BORDER_MEDIUM":
                return BorderStyle.THIN;
            case "BORDER_DASHED":
                return BorderStyle.DASHED;
            case "BORDER_HAIR":
                return BorderStyle.HAIR;
            case "BORDER_THICK":
                return BorderStyle.THICK;
            case "BORDER_DOUBLE":
                return BorderStyle.DOUBLE;
            case "BORDER_DOTTED":
                return BorderStyle.DOTTED;
            case "BORDER_MEDIUM_DASHED":
                return BorderStyle.MEDIUM_DASHED;
            case "BORDER_DASH_DOT":
                return BorderStyle.DASH_DOT;
            case "BORDER_MEDIUM_DASH_DOT":
                return BorderStyle.MEDIUM_DASH_DOT;
            case "BORDER_DASH_DOT_DOT":
                return BorderStyle.DASH_DOT_DOT;
            case "BORDER_MEDIUM_DASH_DOT_DOT":
                return BorderStyle.MEDIUM_DASH_DOT_DOT;
            case "BORDER_SLANTED_DASH_DOT":
                return BorderStyle.SLANTED_DASH_DOT;
            default:
                return BorderStyle.NONE;
        }
    }

    public HorizontalAlignment getAlignmentForRead (String alignment){
        switch (Util.isEoN(alignment)?"":alignment){
            case "ALIGN_GENERAL":
                return HorizontalAlignment.GENERAL;
            case "ALIGN_LEFT":
                return HorizontalAlignment.LEFT;
            case "ALIGN_CENTER":
                return HorizontalAlignment.CENTER;
            case "ALIGN_RIGHT":
                return HorizontalAlignment.RIGHT;
            case "ALIGN_FILL":
                return HorizontalAlignment.FILL;
            case "ALIGN_JUSTIFY":
                return HorizontalAlignment.JUSTIFY;
            case "ALIGN_CENTER_SELECTION":
                return HorizontalAlignment.CENTER_SELECTION;
            default:
                return HorizontalAlignment.CENTER;
        }
    }

    public VerticalAlignment getVerticalalignmentForRead (String verticalalignment){
        switch (Util.isEoN(verticalalignment)?"":verticalalignment){
            case "VERTICAL_TOP":
                return VerticalAlignment.TOP;
            case "VERTICAL_CENTER":
                return VerticalAlignment.CENTER;
            case "VERTICAL_BOTTOM":
                return VerticalAlignment.BOTTOM;
            case "VERTICAL_JUSTIFY":
                return VerticalAlignment.JUSTIFY;
            default:
                return VerticalAlignment.CENTER;
        }
    }
	
}
