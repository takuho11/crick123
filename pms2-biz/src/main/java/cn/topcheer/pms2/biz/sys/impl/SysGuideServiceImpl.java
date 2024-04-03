package cn.topcheer.pms2.biz.sys.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.api.sys.SysGuide;
import cn.topcheer.pms2.api.sys.SysGuideConfig;
import cn.topcheer.pms2.api.sys.vo.SysGuideVO;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.sys.SysGuideConfigService;
import cn.topcheer.pms2.biz.sys.SysGuideService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.common.exception.BusinessException;
import cn.topcheer.pms2.dao.sys.mapper.SysGuideMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.impl.bpmn.listener.ScriptTaskListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysGuideServiceImpl
        extends ServiceImpl<SysGuideMapper, SysGuide>
        implements SysGuideService {

    @Resource
    PmsPlanprojectbatchService pmsPlanprojectbatchService;
    @Lazy
    @Resource
    SysGuideConfigService sysGuideConfigService;


    /*============================完美分割线（↑：注入 丨 ↓：方法）============================*/


    @Override
    public List<SysGuideVO> initTopic(String batchId) {
        return baseMapper.initTopic(batchId);
    }

    @Override
    public List<SysGuideVO> initData(String type, String batchId, String parentId) {
        return baseMapper.initData(type,batchId,parentId);
    }


    @Override
    public List<SysUser> findXmzgByBelongId(String belongId) {
        return baseMapper.findXmzgByBelongId(belongId);
    }

    @Override
    public SysGuideVO findSysGuideConfig(String sourceId, String sourceType) {
        //根据关联id和关联业务类型获取配置信息的集合
        List<SysGuideVO> list = baseMapper.initConfigData(null,sourceId,sourceType);
        if(list.size()>0){
            return list.get(0);
        }
        //如果没有查询到数据，根据关联业务类型进行判断
        SysGuide sysGuide = this.getById(sourceId);
        switch (sourceType){
            case "direction":
                //说明是：方向
                return null;
            case "childdirection":
                //说明是：子方向
                list = baseMapper.initConfigData(null,sysGuide.getParentid(),"direction");
                return (list.size()>0)?list.get(0):null;
            default:
                //说明是：项目
                //去找子方向
                list = baseMapper.initConfigData(null,sysGuide.getParentid(),"childdirection");
                if(list.size()>0){
                    return list.get(0);
                }
                //去找方向
                SysGuide sysGuideParent = this.getById(sysGuide.getParentid());
                list = baseMapper.initConfigData(null,sysGuideParent.getParentid(),"direction");
                return (list.size()>0)?list.get(0):null;
        }
    }

    @Override
    public List<SysGuideVO> findSysGuideByParentIds(Map<String,Object> map) {
        List<String> parentIdList = (List<String>) map.get("array");
        if(parentIdList.size()==0){ return null; }
        return baseMapper.initGuide(parentIdList);
    }

    @Override
    public List<SysGuideConfig> getZcfxByParentOrChildrenAndBatchid(Map<String, Object> map) {
        if(!(map.containsKey("batchid")&&map.containsKey("type")&&map.containsKey("supportdirection"))){
            throw new BusinessException("前端参数不完整");
        }
        String batchId = map.get("batchid").toString();
        String type = map.get("type").toString();
        String supportDirection = map.get("supportdirection").toString();
        return sysGuideConfigService.list(new LambdaQueryWrapper<SysGuideConfig>()
                .eq(SysGuideConfig::getBatchid,batchId)
                .eq(SysGuideConfig::getSourcetype,type)
                .eq(("direction".equals(type)?SysGuideConfig::getTopicname:SysGuideConfig::getSupportdirection),supportDirection));
    }

    @Override
    public void updateBatchTopicType(String batchId) {
        PmsPlanprojectbatch batch = pmsPlanprojectbatchService.findById(batchId);
        if(Util.isEoN(batch)){
            throw new BusinessException("获取不到当前小批次对象。");
        }
        //判断各个类型的count
        String topicType = "";
        if(0==getSysGuidCount("project",batchId)){
            if(0==getSysGuidCount("childdirection",batchId)){
                if(getSysGuidCount("direction",batchId)>0){
                    topicType = "方向";
                }
            }else{
                topicType = "方向+子方向";
            }
        }else{
            topicType = "方向+子方向+项目";
        }
        //给小批次表的topicType赋值
        batch.setTopictype(topicType);
        pmsPlanprojectbatchService.merge(batch);
    }

    /*辅助方法：根据type和batchId获取SysGuide的count值*/
    private Long getSysGuidCount(String type, String batchId) {
        return this.count(new LambdaQueryWrapper<SysGuide>()
                .eq(SysGuide::getType, type)
                .eq(!Util.isEoN(batchId), SysGuide::getBatchid, batchId)
        );
    }

    @Override
    public void deleteGuide(String id, String type) {
        switch (type){
            case "direction":
                //========从方向开始删========
                //找子方向
                List<SysGuide> childDirectionList = this.list(new LambdaQueryWrapper<SysGuide>()
                        .eq(SysGuide::getParentid, id));
                List<String> childIds = childDirectionList.stream().map(e->e.getId()).collect(Collectors.toList());
                if (childIds.size()>0) {
                    //找项目
                    List<SysGuide> projectList = this.list(new LambdaQueryWrapper<SysGuide>()
                            .in(SysGuide::getParentid, childIds));
                    List<String> projectIds = projectList.stream().map(e->e.getId()).collect(Collectors.toList());
                    if(projectIds.size()>0){
                        //删除配置
                        sysGuideConfigService.remove(new LambdaQueryWrapper<SysGuideConfig>()
                                .in(SysGuideConfig::getSourceid, projectIds));
                        //删除项目
                        this.remove(new LambdaQueryWrapper<SysGuide>()
                                .in(SysGuide::getId, projectIds));
                    }
                    //删除子方向
                    this.remove(new LambdaQueryWrapper<SysGuide>()
                            .in(SysGuide::getId, childIds));
                }
                //删除方向
                this.removeById(id);
                break;
            case "childdirection":
                //找项目
                List<SysGuide> projectList = this.list(new LambdaQueryWrapper<SysGuide>()
                        .eq(SysGuide::getParentid, id));
                List<String> projectIds = projectList.stream().map(e->e.getId()).collect(Collectors.toList());
                if(projectIds.size()>0){
                    //删除配置
                    sysGuideConfigService.remove(new LambdaQueryWrapper<SysGuideConfig>()
                            .in(SysGuideConfig::getSourceid, projectIds));
                    //删除项目
                    this.remove(new LambdaQueryWrapper<SysGuide>()
                            .in(SysGuide::getId, projectIds));
                }
                //删子方向
                this.removeById(id);
                break;
            case "project":
                //删除配置
                sysGuideConfigService.remove(new LambdaQueryWrapper<SysGuideConfig>()
                        .eq(SysGuideConfig::getSourceid, id));
                //删除项目
                this.removeById(id);
                break;
            default:
                //删配置
                sysGuideConfigService.removeById(id);
                break;
        }
    }

    public SysGuide findById(String id) {
        SysGuide sysGuide = baseMapper.selectById(id);
        return sysGuide;
    }
}
