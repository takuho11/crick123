package cn.topcheer.halberd.app.common.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.compress.utils.Sets;
import java.io.IOException;
import java.util.Set;

public class BooleanStringDeserializer extends JsonDeserializer<Boolean> {


    private static Set<String> TRUE_LABELS = Sets.newHashSet("1", "active", "true", "enabled", "yes", "y");

    private static Set<String> FALSE_LABELS = Sets.newHashSet("0", "inactive", "false", "disabled", "no", "n");

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        if(StringUtils.isBlank(p.getText())){
            return null;
        }
        if (TRUE_LABELS.contains(p.getText().toLowerCase())) {
            return Boolean.TRUE;
        } else if (FALSE_LABELS.contains(p.getText().toLowerCase())) {
            return Boolean.FALSE;
        }
        return null;
    }
}
