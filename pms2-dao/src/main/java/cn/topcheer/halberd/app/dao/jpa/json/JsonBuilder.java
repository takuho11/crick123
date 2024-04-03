package cn.topcheer.halberd.app.dao.jpa.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessorMatcher;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import java.util.*;

public class JsonBuilder
{

	public static JsonConfig getJsonConfig(Map<String, String> propertySelector)
	{
		return getJsonConfig(propertySelector, false);
	}

	/**
	 * 
	 * @param propertySelector
	 *            选取对象的属性集合，格式位 Key:类名称 Value: [属性1],[属性2],[属性3].... null表示全部选取
	 * @return
	 */
	public static JsonConfig getJsonConfig(Map<String, String> propertySelector, Boolean simpleAssociation)
	{
		JsonConfig config = new JsonConfig();
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.setIgnoreJPATransient(false);
		config.setExcludes(new String[]
		{ "handler", "hibernateLazyInitializer" });
		// config.setExcludes(new
		// String[]{"appraisalAssignment","taskAssignments"});
		if (propertySelector == null)
			propertySelector = new HashMap<String, String>();

		CommonJsonValueProcessor commonProcessor = new CommonJsonValueProcessor(simpleAssociation);
		commonProcessor.setPropertySelector(propertySelector);
		config.registerJsonValueProcessor(Date.class, commonProcessor);
		config.registerJsonValueProcessor(java.sql.Date.class, commonProcessor);
		config.registerJsonValueProcessor(java.sql.Timestamp.class, commonProcessor);
		CommonPropertyFilter pf = new CommonPropertyFilter(propertySelector);
		config.setJsonPropertyFilter(pf);

		config.setDefaultValueProcessorMatcher(new DefaultValueProcessorMatcher()
		{

			@Override
			public Object getMatch(Class target, Set set)
			{
				for (Iterator i = set.iterator(); i.hasNext();)
				{
					Class c = (Class) i.next();
					if (Number.class.isAssignableFrom(c))
					{
						return c;
					}
				}
				return null;
			}
		});

		return config;

	}

	static class CommonPropertyFilter implements PropertyFilter
	{
		private Map<String, String>	propertySelector;

		public CommonPropertyFilter(Map<String, String> propertySelector) {
			this.propertySelector = propertySelector;
		}

		public boolean apply(Object source, String name, Object value)
		{
			// System.out.println(source.toString()+ ":"+name);
			if(value==null)       //
				return true;
			
			if (propertySelector == null)
				propertySelector = new HashMap<String, String>();
			Boolean isSet = false; // 是否已经配置，若未设置则全部选择
			if (propertySelector != null)
			{
				/*****      *******/
				String className = source.getClass().getName();
				int lazyIndex = className.indexOf("_$$_");
				if (lazyIndex > 0)
					className = className.substring(0, lazyIndex);
				if (propertySelector.containsKey(className))
				{
					if (propertySelector.get(className).indexOf("[" + name + "]") >= 0)
					{
						return false;
					}
					isSet = true;
				}

				/************************/

				JsonPropertyFilterController jpfc = JsonPropertyFilterController.instance;
				String propertyFilter = jpfc.getPropertyFilterString(source.getClass());
				if (propertyFilter != null)
				{
					isSet = true;
					if (propertyFilter.indexOf("[" + name + "]") >= 0)
						return false;
				}

				if (!isSet)
				{
					return false;
				}
			}
			return true;
		}

	}
}
