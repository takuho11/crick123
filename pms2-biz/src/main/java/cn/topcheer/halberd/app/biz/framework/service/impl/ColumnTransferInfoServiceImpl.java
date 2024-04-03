package cn.topcheer.halberd.app.biz.framework.service.impl;
 import cn.topcheer.halberd.app.api.framework.entity.dolphin.ColumnTransferInfo;
import cn.topcheer.halberd.app.api.framework.service.ColumnTransferInfoService;
import cn.topcheer.halberd.app.dao.mapper.framework.dataintegration.ColumnTransferInfoMapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnTransferInfoServiceImpl extends BaseServiceImpl<ColumnTransferInfoMapper, ColumnTransferInfo>  implements ColumnTransferInfoService {

    @Autowired
    ColumnTransferInfoMapper columnTransferInfoMapper;

    @Override
    public List<ColumnTransferInfo> getByDefId(String taskDefId) {
        List<ColumnTransferInfo> columnTransferInfos = columnTransferInfoMapper.getByTaskDefId(taskDefId);
        return columnTransferInfos;
    }


}
