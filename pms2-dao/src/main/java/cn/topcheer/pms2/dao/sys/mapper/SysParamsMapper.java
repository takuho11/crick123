package cn.topcheer.pms2.dao.sys.mapper;

import cn.topcheer.pms2.api.sys.SysParams;
import cn.topcheer.pms2.api.sys.vo.SysParamsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysParamsMapper extends BaseMapper<SysParams> {

    List<SysParamsVO> selectByCon(@Param("applyType") String applyType);

    List<SysParamsVO> selectByParentvalue(@Param("selectInitNameMap") List<String> selectInitNameMap);

}
