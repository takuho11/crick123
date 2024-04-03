package cn.topcheer.halberd.app.api.minidev.service;


import cn.topcheer.halberd.app.api.minidev.entity.MiniForm;

/**
 * MiniForm 转换器，从其他模型转换为MiniForm模型
 *
 */
public interface MiniFormConverter {

    void convert(MiniForm miniform) throws Exception;

    boolean canHandle(MiniForm miniform);

}