package cn.topcheer.halberd.app.api.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 对象转json时将改外键对象的id值作为改字段的值
 * @author GaoGongxin
 */
public class JsonForeignKeyValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object o, JsonConfig jsonConfig) {
		JSONObject jsonObject = JSONObject.fromObject(o, jsonConfig);
		return jsonObject;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {

		if(value == null || value instanceof Date || value instanceof java.sql.Date || value instanceof Timestamp || value instanceof BigDecimal || value instanceof List || value instanceof JSONArray) {
			return value;
		}
		if(value != null) {
			Class type = value.getClass();
			if(type.isPrimitive()) {
				return value;
			}
		}
		Method getIdMethod = null;
		String newValue = "";
		try {
			getIdMethod = value.getClass().getDeclaredMethod("getId");
			getIdMethod.setAccessible(true);
			newValue = Util.isEoN(getIdMethod.invoke(value)) == true ? "" : getIdMethod.invoke(value).toString();
		} catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			newValue = "";
			e.printStackTrace();
		}
		return newValue;
	}
}
