package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.minidev.dto.MiniInitAllTableDTO;
import cn.topcheer.halberd.app.api.minidev.dto.MiniVersionCompareDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniBizHistoryVersion;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizHistoryVersionService;
import cn.topcheer.halberd.app.api.minidev.service.MiniCommonService;
import cn.topcheer.halberd.app.api.utils.CompareUtil;
import cn.topcheer.halberd.app.dao.mapper.minidev.MiniBizHistoryVersionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 业务历史版本表 服务实现类
 * </p>
 *
 * @author szs
 * @since 2023-11-16
 */
@Service
public class MiniBizHistoryVersionServiceImpl extends ServiceImpl<MiniBizHistoryVersionMapper, MiniBizHistoryVersion> implements MiniBizHistoryVersionService {


    @Lazy
    @Autowired
    private MiniCommonService miniCommonService;


    /**
     * 添加历史版本
     *
     * @param mainId  主表id
     * @param content 内容
     * @author szs
     * @date 2023-11-16
     */
    @Override
    public void addHistoryVersion(String mainId, String content) {
        MiniBizHistoryVersion data = new MiniBizHistoryVersion();
        data.setMainId(mainId);
        data.setContent(content);
        data.setIsDeleted(0);
        data.setCreateTime(new Date());
        data.setCreateUser(AuthUtil.getUserId());
        boolean bo = this.save(data);
        if (!bo) {
            throw new ServiceException("添加历史版本失败");
        }

    }


    /**
     * 版本比对
     *
     * @param dto MiniVersionCompareDTO
     * @return MiniInitAllTableDTO
     * @author szs
     * @date 2023-11-17
     */
    @Override
    public MiniInitAllTableDTO versionCompare(MiniVersionCompareDTO dto) {
        // 获取历史数据
        MiniBizHistoryVersion historyVersion = this.getById(dto.getBizHistoryVersionId());
        if (historyVersion == null) {
            throw new ServiceException("暂无数据");
        }

        // 获取最新数据
        dto.setIsToNow(true);
        MiniInitAllTableDTO tableData = miniCommonService.initAllTable(dto);

        // 比对数据
        JSONObject result = compareData(tableData.getDataObject(), JSONObject.fromObject(historyVersion.getContent()));
        tableData.setDataObject(result);

        return tableData;
    }


    /**
     * 比对数据
     *
     * @param lastData 最新数据
     * @param hisData  历史数据
     * @return JSONObject
     * @author szs
     * @date 2023-11-17
     */
    private JSONObject compareData(JSONObject lastData, JSONObject hisData) {
        if (lastData == null) {
            return null;
        }

        // 遍历每个子项数据
        for (Object key : lastData.keySet()) {
            String keyStr = key.toString();

            // 最新数据TableObject
            JSONObject lastTableObject = lastData.getJSONObject(keyStr);

            // 历史数据TableObject
            JSONObject hisTableObject = hisData.getJSONObject(keyStr);

            // 最新数据Data
            JSONArray lastArray = getArrayData(lastTableObject);

            // 历史数据Data
            JSONArray hisArray = getArrayData(hisTableObject);

            // 比对数据
            JSONArray array = compareJsonArray(lastArray, hisArray);

            // 回填数据
            lastTableObject.put("data", toObjectData(array, lastTableObject));

            lastData.put(keyStr, lastTableObject);
        }

        return lastData;
    }


    /**
     * 比对数组
     *
     * @param lastArray 最新数据
     * @param hisArray  历史数据
     * @return JSONArray
     * @author szs
     * @date 2023-11-17
     */
    private JSONArray compareJsonArray(JSONArray lastArray, JSONArray hisArray) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < lastArray.size(); i++) {
            // 最新数据
            JSONObject lastData = getObjectData(lastArray, i);

            // 历史数据
            JSONObject hisData = getObjectData(hisArray, i);

            // 比对数据
            array.add(compareJsonObject(lastData, hisData));
        }

        return array;
    }


    /**
     * 比对对象
     *
     * @param lastData 最新数据
     * @param hisData  历史数据
     * @return JSONObject
     * @author szs
     * @date 2023-11-17
     */
    private JSONObject compareJsonObject(JSONObject lastData, JSONObject hisData) {
        // 遍历每个子项数据
        for (Object key : lastData.keySet()) {
            String keyStr = key.toString();
            if ("id".equals(keyStr) || "savedate".equals(keyStr) || "updatelasttime".equals(keyStr)) {
                continue;
            }

            // 最新数据
            String lastStr = getStrData(lastData, keyStr);

            // 历史数据
            String hisStr = getStrData(hisData, keyStr);

            // 比对数据、回填数据
            lastData.put(keyStr, CompareUtil.getHighLightDifferent(lastStr, hisStr));
        }

        return lastData;
    }


    /**
     * 转化Object数据
     *
     * @param jsonArray   JSONArray
     * @param tableObject Table数据
     * @return Object
     * @author szs
     * @date 2023-11-17
     */
    private Object toObjectData(JSONArray jsonArray, JSONObject tableObject) {
        Object object;
        if (!tableObject.containsKey("datatype")) {
            return tableObject.get("data");
        }

        if ("normal".equals(tableObject.getString("datatype"))) {
            // 对象，单条数据
            object = jsonArray.size() > 0 ? jsonArray.getJSONObject(0) : new JSONObject();
        } else {
            // 数组，多条数据
            object = jsonArray;
        }

        return object;
    }


    /**
     * 获取数组数据
     *
     * @param jsonData JSONObject
     * @return JSONArray
     * @author szs
     * @date 2023-11-17
     */
    private JSONArray getArrayData(JSONObject jsonData) {
        JSONArray array = new JSONArray();
        if (jsonData.containsKey("datatype") && jsonData.containsKey("data")) {
            if ("normal".equals(jsonData.getString("datatype"))) {
                array.add(jsonData.getJSONObject("data"));
            } else {
                array = jsonData.getJSONArray("data");
            }
        }

        return array;
    }


    /**
     * 获取对象数据
     *
     * @param jsonArray JSONArray
     * @param i         下标
     * @return JSONObject
     * @author szs
     * @date 2023-11-17
     */
    private JSONObject getObjectData(JSONArray jsonArray, int i) {
        JSONObject object = new JSONObject();
        if (jsonArray.size() > i) {
            object = jsonArray.getJSONObject(i);
        }

        return object;
    }


    /**
     * 获取字符串数据
     *
     * @param jsonObject JSONObject
     * @param key        key
     * @return String
     * @author szs
     * @date 2023-11-17
     */
    private String getStrData(JSONObject jsonObject, String key) {

        return jsonObject.containsKey(key) ? jsonObject.getString(key) : "";
    }


}
