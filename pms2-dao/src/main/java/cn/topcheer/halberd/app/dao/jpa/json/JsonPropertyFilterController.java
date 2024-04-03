package cn.topcheer.halberd.app.dao.jpa.json;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;


import java.util.HashMap;
import java.util.Map;
 
 

public class JsonPropertyFilterController
{
	public static JsonPropertyFilterController	instance					= new JsonPropertyFilterController();

	private Map<Class, String>					map							= new HashMap<Class, String>();
	//private String								Default_MainMenu			= "[id],[name],[code],[seq],[image],[menuGroups]";
 	private String Default_SysUser="[id],[name],[userid],[userstate],[qq],[mobile],[sex]";
	 

	public JsonPropertyFilterController() {
		map.put(SysUser.class, Default_SysUser);
//		map.put(Task.class, Default_Task);
		//map.put(MainMenu.class, Default_MainMenu); 
		
	}

	public String getPropertyFilterString(Class c)
	{
		if (map.containsKey(c))
			return map.get(c);
		else
			return null;
	}

	public String getPropertyFilterString(String className)
	{
		try
		{
			Class c = Class.forName(className);
			return getPropertyFilterString(c);
		}
		catch (ClassNotFoundException e)
		{
			return null;
		}

	}

	public void putAll(Map<String, String> outmap)
	{
		for (Class c : map.keySet())
		{
			if (!outmap.containsKey(c.getName()))
			{
				outmap.put(c.getName(), map.get(c));
			}
		}
	}

}
