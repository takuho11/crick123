package cn.topcheer.pms2.biz.sys;

import cn.topcheer.pms2.api.sys.SysGuideConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface SysGuideConfigService extends IService<SysGuideConfig> {

    List<SysGuideConfig> initGuide(String batchId);

    void saveGuide(SysGuideConfig sysGuideConfig);

    JSONObject judgeGuide(Map param);

}
