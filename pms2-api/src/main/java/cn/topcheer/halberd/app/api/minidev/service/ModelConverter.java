package cn.topcheer.halberd.app.api.minidev.service;

import cn.topcheer.halberd.app.api.minidev.dto.ModelDTO;


public interface ModelConverter {
    void convert(ModelDTO model) throws Exception;

    boolean canHandle(ModelDTO model);
}
