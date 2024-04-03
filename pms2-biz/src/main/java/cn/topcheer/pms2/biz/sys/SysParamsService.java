package cn.topcheer.pms2.biz.sys;

import cn.topcheer.pms2.api.sys.SysParams;
import cn.topcheer.pms2.api.sys.vo.SysParamsVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface SysParamsService extends IService<SysParams> {

    List<SysParamsVO> selectByCon(String applyType);

    List<SysParamsVO> selectByParentvalue(Map param);

    void saveData(SysParamsVO sysParamsVO);

    void deleteById(String id);

}
