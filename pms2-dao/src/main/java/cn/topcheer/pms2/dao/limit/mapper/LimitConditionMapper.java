package cn.topcheer.pms2.dao.limit.mapper;

import cn.topcheer.pms2.api.limit.entity.LimitCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LimitConditionMapper extends BaseMapper<LimitCondition> {

    List<LimitCondition> selectByCon(@Param("system") String system,@Param("type") String type,@Param("keyWord") String keyWord);

}
