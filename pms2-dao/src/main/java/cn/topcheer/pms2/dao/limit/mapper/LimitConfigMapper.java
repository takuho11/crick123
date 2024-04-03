package cn.topcheer.pms2.dao.limit.mapper;

import cn.topcheer.pms2.api.limit.entity.LimitConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LimitConfigMapper extends BaseMapper<LimitConfig>  {

    List<LimitConfig> selectByCon(@Param("batchId") String batchId,@Param("conditionId") String conditionId,@Param("keyWord") String keyWord);

}
