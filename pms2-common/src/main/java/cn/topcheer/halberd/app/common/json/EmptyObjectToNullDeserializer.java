package cn.topcheer.halberd.app.common.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Iterator;

public abstract class EmptyObjectToNullDeserializer<T> extends JsonDeserializer<T> {

    public abstract Class<T> getType();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JacksonException {
        JsonNode node = jsonParser.readValueAsTree();
        if (objectAllNullOrEmpty(node)) {
            return null;
        }
        return objectMapper.readValue(node.toString(), this.getType());
    }

    private static boolean objectAllNullOrEmpty(JsonNode node){
        if(node.isNull() || isEmptyObject(node) || isBlankString(node)){
            return true;
        }
        if(! node.isObject()){
            return false;
        }

        Iterator<JsonNode> jsonNodeIterator = node.elements();
        while (jsonNodeIterator.hasNext()){
            JsonNode field = jsonNodeIterator.next();
            if(!objectAllNullOrEmpty(field)){
                return false;
            }
        }
        return true;
    }

    private static boolean isBlankString(JsonNode node){
        return node.isTextual() && StringUtils.isBlank(node.asText());
    }

    private static boolean isEmptyObject(JsonNode node){
        return node.isObject() && node.isEmpty();
    }




}
