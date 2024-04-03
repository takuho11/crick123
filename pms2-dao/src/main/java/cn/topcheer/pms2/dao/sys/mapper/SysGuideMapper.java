package cn.topcheer.pms2.dao.sys.mapper;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.pms2.api.sys.SysGuide;
import cn.topcheer.pms2.api.sys.vo.SysGuideVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@SuppressWarnings("MybatisXMapperMethodInspection")//取消Mybatis插件产生的警告，比如@MapKey
public interface SysGuideMapper extends BaseMapper<SysGuide> {

    List<SysGuideVO> initTopic(@Param("batchId") String batchId);

    List<SysGuideVO> initData(@Param("type") String type,@Param("batchId") String batchId,@Param("parentId") String parentId);

    List<SysGuideVO> initGuide(@Param("parentIdList") List<String> parentIdList);

    List<SysUser> findXmzgByBelongId(@Param("belongId") String belongId);

    List<SysGuideVO> initConfigData(@Param("batchId") String batchId,@Param("sourceId") String sourceId,@Param("sourceType") String sourceType);

}
