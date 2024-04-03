package cn.topcheer.halberd.app.api.minidev.constant;

import java.util.HashMap;
import java.util.Map;

public enum PropertyTypeEnum {
    STRING("string"),
    NUMBER("number"),
    INTEGER("integer"),
    BOOLEAN("boolean"),
    DATETIME("datetime"),
    BLOB("blob");
    
    
    private static final Map<String, PropertyTypeEnum> MAP = new HashMap<>();

    static {

        for (PropertyTypeEnum pt : values()) {
            MAP.put(pt.getValue(), pt);
        }
    }

    private String value;

    PropertyTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PropertyTypeEnum valueOfName(String name) {

        return MAP.get(name);
    }

}
