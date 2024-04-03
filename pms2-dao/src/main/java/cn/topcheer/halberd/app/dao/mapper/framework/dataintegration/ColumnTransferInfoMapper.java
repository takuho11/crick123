package cn.topcheer.halberd.app.dao.mapper.framework.dataintegration;

import cn.topcheer.halberd.app.api.framework.entity.dolphin.ColumnTransferInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColumnTransferInfoMapper extends BaseMapper<ColumnTransferInfo> {


    List<ColumnTransferInfo> getByTaskDefId(@Param("taskDefId")String taskDefId);


}
