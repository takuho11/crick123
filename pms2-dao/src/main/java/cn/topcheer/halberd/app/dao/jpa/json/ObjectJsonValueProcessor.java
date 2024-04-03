package cn.topcheer.halberd.app.dao.jpa.json;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectJsonValueProcessor implements JsonValueProcessor
{
    private String format = "yyyy-MM-dd HH:mm:ss";   
    private SimpleDateFormat sdf = new SimpleDateFormat(format); 
    private String[] properties = null; 
    private Class<?> clazz;

    public ObjectJsonValueProcessor(){}
    public ObjectJsonValueProcessor(String[] properties,Class<?> clazz){
	this.properties = properties; 
	this.clazz =clazz; 
    }
    public Object processArrayValue(Object value, JsonConfig arg1)
    {
	if (value == null) {
	    return "";
	} else if (value instanceof Date)
	    return sdf.format((Date) value);
	else if(value instanceof Double)
	{
	    return  value.toString();
	}else
	{
	    if(properties!=null){
        	PropertyDescriptor pd = null; 
        	Method method = null; 
        	StringBuffer json = new StringBuffer("{");
        	try{
        	   for(int i=0;i<properties.length;i++){ 
        	       pd = new PropertyDescriptor(properties[i], clazz);
        	       method = pd.getReadMethod();
        	       String v = String.valueOf(method.invoke(value));
        	       json.append("'"+properties[i]+"':'"+v+"'");
        	       json.append(i != properties.length-1?",":"");
        	   }
        	   json.append("}");
        	}
        	catch(Exception e){
        	    e.printStackTrace();
        	}
        	return JSONObject.fromObject(json.toString());
	    }
	    return value.toString();
	}
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig)
    {
	if (value == null) {
	    return "";
	} else if (value instanceof Date)
	    return sdf.format((Date) value);
	else if(value instanceof Double)
	{
	    return  value.toString();
	}else
	{
	    if(properties!=null){
        	PropertyDescriptor pd = null;
        	Method method = null;
        	StringBuffer json = new StringBuffer(); 
        	try{ 
        	    for(int i=0;i<properties.length;i++){ 
        		pd = new PropertyDescriptor(properties[i], clazz); 
        		//反射：通过类PropertyDescriptor可以得到properties数组即相关联的实体中需要传递的属性，它的名称，类型以及getter,setter方法
        		method = pd.getReadMethod(); //得到属性的读取方法，即getter()
        		if (value != null){
        		    String v = String.valueOf(method.invoke(value));//执行getter(),当然也可以看出这里value
        		  //即是一个实体类的字节码，在这里是Company.class,然后在组装JSON格式的字符串
        		    json.append("'" + properties[i] + "':'" + v + "'");
        		    json.append(i != properties.length - 1 ? "," : "");
        		}
        	    }
        	    //json.append("}");
        	    System.out.println("json = "+json.toString());
        	}
        	catch(Exception e){
        	    e.printStackTrace();
        	}
        	return JSONObject.fromObject("{"+json.toString()+"}");
	    }
	    return value.toString();
	}
    }

}
