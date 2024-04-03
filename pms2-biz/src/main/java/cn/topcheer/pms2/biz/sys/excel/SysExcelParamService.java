/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2020-10-16 15:55:24
 *
 */
package cn.topcheer.pms2.biz.sys.excel;

import cn.topcheer.halberd.app.api.utils.PmsTxtSave;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.excel.SysExcelParam;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.sys.excel.SysExcelParamDao;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * SysExcelParam 服务类
 */
@Service(value="SysExcelParamService")
public class SysExcelParamService extends GenericService<SysExcelParam> {

	public SysExcelParamDao getSysExcelParamDao() {
		return (SysExcelParamDao) this.getGenericDao();
	}

	@Autowired
	public void setSysExcelParamDao(SysExcelParamDao sysExcelParamDao) {

		this.setGenericDao(sysExcelParamDao);
	}
    @Autowired
    private DBHelper dbHelper;
    @Autowired
    PmsTxtSave pmsTxtSave;

    //修改新增信息
    public ReturnToJs sysExcelParamSave(JSONObject json, ReturnToJs returnToJs) {
        String type = "";
        JSONArray jsonArray = json.getJSONArray("data");
        String sourceid = "";
        type = json.get("type") + "";
        if(jsonArray.size()>0){
            sourceid=jsonArray.getJSONObject(0).get("sourceid")+"";
        }
        List<SysExcelParam> pmsList = this.findByHql("select t from SysExcelParam t where t.sourceid =?0 and t.type=?1 ", new Object[]{sourceid,type});
        List<SysExcelParam> pmsArray = JSON.parseArray(jsonArray.toString(), SysExcelParam.class);
        if (pmsList.size() == 0) {
            if (pmsArray != null && pmsArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    SysExcelParam ppa = new SysExcelParam();
                    Util.ApplyObject(ppa, jsonArray.getJSONObject(i));
                    if (type.equals("sheetbig") || type.equals("mergedregion")|| type.equals("style")) {
                        ppa.setValue(jsonArray.getJSONObject(i).get("value").toString());
                    }
                    if (!Util.isEoN(jsonArray.getJSONObject(i).get("othermemo"))) {
                        ppa.setOthermemo(jsonArray.getJSONObject(i).get("othermemo").toString());
                    }
                    ppa.setType(type);
                    ppa.setSeq(i);
                    ppa.setId(Util.NewGuid().toString());
                    this.save(ppa);
                    returnToJs.setSuccess(true);
                }
            }
        } else {
            String currentIds = "";
            if (pmsArray != null && pmsArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    SysExcelParam ppa = this.findById(jsonArray.getJSONObject(i).get("id") + "");
                    if (ppa == null) {
                        ppa = JSON.parseObject(jsonArray.getJSONObject(i).toString(), SysExcelParam.class);
                        ppa.setId(UUID.randomUUID().toString());
                    } else {
                        Util.ApplyObject(ppa, jsonArray.getJSONObject(i));
                    }
                    currentIds += ppa.getId() + ",";
                    if (type.equals("sheetbig") || type.equals("mergedregion")|| type.equals("style")) {
                        ppa.setValue(jsonArray.getJSONObject(i).get("value").toString());
                    }
                    if (!Util.isEoN(jsonArray.getJSONObject(i).get("othermemo"))) {
                        ppa.setOthermemo(jsonArray.getJSONObject(i).get("othermemo").toString());
                    }
                    ppa.setType(type);
                    ppa.setSeq(i);
                    this.merge(ppa);
                    returnToJs.setSuccess(true);
                }
            }
            for (int i = 0; i < pmsList.size(); i++) {
                if (currentIds.indexOf(pmsList.get(i).getId()) > -1) {

                } else {
                    this.deleteById(pmsList.get(i).getId());
                }
            }
        }
        pmsTxtSave.saveTxt(sourceid,json,"SysExcelParam","sysExcelParamSave");
        return returnToJs;
    }

    //查询信息
    public JSONArray sysExcelParamFind(JSONObject json) {
        String sourceid = "";
        if (!Util.isEoN(json.get("id"))) {
            sourceid = (json.get("id")).toString().trim();
        }
        String sql = "select t.* from sys_excel_param t left join sys_excel_tab s on s.id=t.sourceid  where t.sourceid like ? order by t.seq";
        List<Map> listMap = dbHelper.getRows(sql, new Object[]{sourceid});
        if (listMap != null && listMap.size() > 0)
            return JSONArray.fromObject(listMap);
        else
            return new JSONArray();
    }

    //删除信息
    public ReturnToJs sysExcelParamDelete(JSONObject json, ReturnToJs returnToJs) {
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
	
}
