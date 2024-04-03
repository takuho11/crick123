package cn.topcheer.halberd.app.api.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.math.BigDecimal;

public class JsonBigDecimalValueProcessor implements JsonValueProcessor {

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        return value;
    }
}