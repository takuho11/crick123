package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.framework.dto.api.Result;
import cn.topcheer.halberd.app.api.minidev.dto.ModelDTO;
import cn.topcheer.halberd.app.api.minidev.service.ModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ModelConverterManager {

    @Autowired
    private List<ModelConverter> converters = new ArrayList<>();

    public Result convert(ModelDTO model) {
        if (converters.size() == 0)
            return Result.failOf("no ApiConverter exists");

        for (ModelConverter converter : converters) {
            try {
                if (converter.canHandle(model)) {

                    converter.convert(model);

                    return Result.successOf("");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.failOf(e.getMessage());
            }

        }

        return Result.failOf("no ApiConverter can handle this model");

    }

}
