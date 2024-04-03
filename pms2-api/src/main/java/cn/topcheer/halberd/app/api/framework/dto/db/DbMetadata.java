package cn.topcheer.halberd.app.api.framework.dto.db;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

public class DbMetadata {
    private Map<String, Object> map;
    private String json;

    private boolean initJson = false;

    public DbMetadata(Map<String, Object> map){
        this.map = map;
    }

    public DbMetadata(String json){
        this.json = json;
        this.initJson = true;
    }

    public String getJson(ObjectMapper mapper){
        if(this.initJson){
            return this.json;
        }
        try{
            return mapper.writeValueAsString(this.map);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> T parse(ObjectMapper mapper, Class<T> clazz){
        if(this.initJson){
            if(StringUtils.isBlank(this.json)){
                return null;
            }
            try {
                return mapper.readValue(this.json, clazz);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        if(this.map == null || this.map.isEmpty()){
            return null;
        }
        return mapper.convertValue(this.map, clazz);
    }
}
