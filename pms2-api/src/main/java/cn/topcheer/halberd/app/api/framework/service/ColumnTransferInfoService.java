package cn.topcheer.halberd.app.api.framework.service;

import cn.topcheer.halberd.app.api.framework.entity.dolphin.ColumnTransferInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ColumnTransferInfoService extends IService<ColumnTransferInfo> {


    List<ColumnTransferInfo> getByDefId(String flowDefId);



}
