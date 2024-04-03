package cn.topcheer.halberd.app.dao.jpa.json;

import net.sf.json.util.PropertyFilter;

public class JsonPropertyFilter implements PropertyFilter
{
	private String[]	strs	= null;

	public JsonPropertyFilter() {}

	public JsonPropertyFilter(String[] strs) {
		this.strs = strs;
	}

	public boolean apply(Object source, String name, Object value)
	{
		if (strs != null)
		{
			for (String str : strs)
			{
				if (name.equals(str))
				{
					return true;
				}
			}
		}
		return false;
	}

}
