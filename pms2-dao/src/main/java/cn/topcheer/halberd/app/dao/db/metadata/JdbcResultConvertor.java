package cn.topcheer.halberd.app.dao.db.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class JdbcResultConvertor {

    public JdbcResultConvertor(ObjectMapper objectMapper){
        JdbcResultConvertor.objectMapper = objectMapper;
    }

    private static ObjectMapper objectMapper;

    public static <T> T getOne(List<T> list){
        if(list == null || list.isEmpty()){
            return null;
        }
        if(list.size() > 1){
            throw new IllegalStateException("jdbc返回结果数为" + list.size() + ",大于1");
        }
        return list.get(0);
    }

    /**
     * todo:优化映射
     * @param map
     * @param clazz
     * @return
     * @param <U>
     */
    public static <U> U convertResult(Map<String, Object> map, Class<U> clazz){
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * todo:优化映射
     * @param mapList
     * @param clazz
     * @return
     * @param <U>
     */
    public static <U> List<U> convertResult(List<Map<String, Object>> mapList, Class<U> clazz){
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.convertValue(mapList, collectionType);
    }




}
