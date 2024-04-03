package cn.topcheer.pms2.dao.plan.mapper;

import cn.topcheer.pms2.api.plan.entity.PmsPlanproject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PmsPlanprojectMapper extends BaseMapper<PmsPlanproject> {

    List<Map<String, String>> selectByCon(@Param("system") String system, @Param("annually") String annually, @Param("bigBatchId") String bigBatchId);

}
