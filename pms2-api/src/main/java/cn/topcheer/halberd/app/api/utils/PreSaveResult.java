package cn.topcheer.halberd.app.api.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 通用保存预处理返回参数
 * @author GaoGongxin
 * @date 2021/1/22 15:27
 */
public class PreSaveResult {

    /**
     * 数据
     */
    JSONArray data;

    /**
     * 完整前台数据（解决特殊传参使用）
     */
    JSONObject tableObject;

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    public JSONObject getTableObject() {
        return tableObject;
    }

    public void setTableObject(JSONObject tableObject) {
        this.tableObject = tableObject;
    }

    public PreSaveResult(JSONArray data, JSONObject tableObject) {
        this.data = data;
        this.tableObject = tableObject;
    }
}
