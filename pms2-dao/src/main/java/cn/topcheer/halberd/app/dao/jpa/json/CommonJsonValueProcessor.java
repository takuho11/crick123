package cn.topcheer.halberd.app.dao.jpa.json;

import cn.topcheer.halberd.app.api.utils.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

// Date日期类型的Json值处理器,可自定义一些类的处理方式,是json提供的一个扩展接口
public class CommonJsonValueProcessor implements JsonValueProcessor
{
	private Map<String, String>	propertySelector;
	private String				format				= "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat	sdf					= new SimpleDateFormat(format);
	private Boolean				simpleAssociation	= false;						// 关联的对象简单化处理只保留
																					// {id:}
																					// 主要用于保留关联
	public CommonJsonValueProcessor(Boolean simpleAssociation)
	{
		this.simpleAssociation=simpleAssociation;
	}
	
	// 处理数组的值
	public Object processArrayValue(Object value, JsonConfig jsonConfig)
	{
		
		return this.process(value, jsonConfig);
	}

	// 处理对象的值
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig)
	{
		if (value instanceof List<?>)
		{
			JSONArray jarray = new JSONArray();
			List<?> list = (List<?>) value;
			{
				for (Object obj : list)
				{
					jarray.add(JSONObject.fromObject(obj, jsonConfig));
				}
			}
			return jarray.toString();
		}
		return this.process(value, jsonConfig);
	}

	// 处理Date类型返回的Json数值
	private Object process(Object value, JsonConfig jsonConfig)
	{
		if (value == null)
		{
			return "";
		} else if (value instanceof java.sql.Date)
		{
			java.sql.Date dt = (java.sql.Date) value;
			return sdf.format(new Date(dt.getTime()));
		} else if (value instanceof Date)

			return sdf.format((Date) value);
		else if (value instanceof Timestamp)
		{
			return sdf.format((Date) value);
		} else if (value instanceof Double)
		{
			return value.toString();
		} else if (value instanceof String)
		{
			return (String) value;
		} else
		{
			String className = value.getClass().getName();
			int lazyIndex = className.indexOf("_$$_");
			if (lazyIndex > 0)
				className = className.substring(0, lazyIndex);
			if (propertySelector.containsKey(className))
			{
				if (simpleAssociation)
				{
					return "{id:" + Util.getField(value, "id") + "}";
				} else
				{
					JSONObject jobject = JSONObject.fromObject(value, jsonConfig);
					return jobject.toString();
				}
			} else if (simpleAssociation)
			{
				if (className.startsWith("cn.topcheer"))
				{
					return "{id:" + Util.getField(value, "id") + "}";
				}
			}

			return value.toString();
		}
	}

	/**
	 * @return the propertySelector
	 */
	public Map<String, String> getPropertySelector()
	{
		return propertySelector;
	}

	/**
	 * @param propertySelector
	 *            the propertySelector to set
	 */
	public void setPropertySelector(Map<String, String> propertySelector)
	{
		this.propertySelector = propertySelector;
		JsonPropertyFilterController.instance.putAll(this.propertySelector);
	}

}
