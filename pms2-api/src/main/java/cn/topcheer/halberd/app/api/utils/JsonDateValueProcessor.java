package cn.topcheer.halberd.app.api.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JsonDateValueProcessor implements JsonValueProcessor {  
    private String format ="yyyy-MM-dd";  
    private String format1 ="yyyy-MM-dd HH:mm:ss.SSS";

    public JsonDateValueProcessor() {
        super();  
    }  
      
    public JsonDateValueProcessor(String format) {  
        super();  
        this.format = format;  
    }  
  
    @Override  
    public Object processArrayValue(Object paramObject,  
            JsonConfig paramJsonConfig) {  
        return process(paramObject);  
    }  
  
    @Override  
    public Object processObjectValue(String paramString, Object paramObject, JsonConfig paramJsonConfig) {
        if("savedate".equals(paramString) || "updatelasttime".equals(paramString) || "submitdate".equals(paramString) || "flowpointupdatetime".equals(paramString) || "importdate".equals(paramString) || "gsdate".equals(paramString) || "dissentdate".equals(paramString) || "cnsdate".equals(paramString)) {
            return process1(paramObject);
        } else {
            return process(paramObject);
        }
    }  
      
      
    private Object process(Object value){  
        if(value instanceof Date){    
            SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);    
            return sdf.format(value);  
        }    
        return value == null ? "" : value.toString();    
    }

    private Object process1(Object value){
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format1,Locale.CHINA);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }

}  