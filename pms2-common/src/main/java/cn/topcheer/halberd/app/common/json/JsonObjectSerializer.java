package cn.topcheer.halberd.app.common.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * 因为传的string已经是json的格式了，所以就直接转换
 */
public class JsonObjectSerializer extends StdSerializer<String> {

    public JsonObjectSerializer(){
        this(null);
    }

    public JsonObjectSerializer(Class<String> t) {
        super(t);
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeRawValue(s);
    }


}
