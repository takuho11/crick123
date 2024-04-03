package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.pms2.api.sys.SysGuide;
import cn.topcheer.pms2.api.sys.SysGuideConfig;
import cn.topcheer.pms2.api.sys.vo.SysGuideVO;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface SysGuideService extends IService<SysGuide> {

    /*根据小批次id获取方向*/
    List<SysGuideVO> initTopic(String batchId);

    /*根据小批次id和父id获取下一级*/
    List<SysGuideVO> initData(String type,String batchId,String parentId);

    /*根据处室id获取全部处室下官员*/
    List<SysUser> findXmzgByBelongId(String belongId);

    /*根据关联id和关联业务类型获取配置信息*/
    SysGuideVO findSysGuideConfig(String sourceId,String sourceType);

    /*根据多个方向找子方向、支持内容*/
    List<SysGuideVO> findSysGuideByParentIds(Map<String,Object> map);

    /*根据不同参数获取支持方向*/
    List<SysGuideConfig> getZcfxByParentOrChildrenAndBatchid(Map<String,Object> map);

    /*更新小批次表的topicType*/
    void updateBatchTopicType(String batchId);

    /*删除数据*/
    void deleteGuide(String id,String type);

    SysGuide findById(String id);

}
