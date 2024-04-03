package cn.topcheer.pms2.biz.sys.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.pms2.api.sys.SysGuide;
import cn.topcheer.pms2.api.sys.SysGuideConfig;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.project.service.impl.projectBase.PmsProjectbaseCddwService;
import cn.topcheer.pms2.biz.project.service.impl.PmsSaveAndInitNewService;
import cn.topcheer.pms2.biz.sys.SysGuideConfigService;
import cn.topcheer.pms2.biz.sys.SysGuideService;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.common.exception.BusinessException;
import cn.topcheer.pms2.dao.sys.mapper.SysGuideConfigMapper;
import cn.topcheer.pms2.dao.sys.mapper.SysGuideMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SysGuideConfigServiceImpl
        extends ServiceImpl<SysGuideConfigMapper, SysGuideConfig>
        implements SysGuideConfigService {

    @Resource
    private SysGuideConfigMapper sysGuideConfigMapper;
    @Resource
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;
    @Lazy//解决循环依赖，springboot未开启全局允许循环依赖
    @Resource
    private SysGuideService sysGuideService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysGuideMapper sysGuideMapper;
    @Resource
    private PmsSaveAndInitNewService pmsSaveAndInitNewService;
    @Resource
    private PmsProjectbaseCddwService pmsProjectbaseCddwService;

    public List<SysGuideConfig> initGuide(String batchId){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("batchid",batchId);
        queryWrapper.orderByAsc("seq");
        return sysGuideConfigMapper.selectList(queryWrapper);
    }

    public void saveGuide(SysGuideConfig sysGuideConfig){
        String sourceid=sysGuideConfig.getSourceid();
        String sourcetype=sysGuideConfig.getSourcetype();
        SysGuide sysGuideDirection = new SysGuide();
        SysGuide sysGuideChildDirection = new SysGuide();
        SysGuide sysGuideProject = new SysGuide();
        if("direction".equals(sourcetype)){ //方向配置
            //方向
            sysGuideDirection = sysGuideService.getById(sourceid);
        }else if("childdirection".equals(sourcetype)){ //子方向配置
            //子方向
            sysGuideChildDirection = this.sysGuideService.getById(sourceid);
            //方向
            sysGuideDirection = this.sysGuideService.getById(sysGuideChildDirection.getParentid());
        }else if("project".equals(sourcetype)){ //项目配置
            //项目
            sysGuideProject = this.sysGuideService.getById(sourceid);
            //子方向
            sysGuideChildDirection = this.sysGuideService.getById(sysGuideProject.getParentid());
            //方向
            sysGuideDirection = this.sysGuideService.getById(sysGuideChildDirection.getParentid());
        }
        sysGuideConfig.setBatchname(pmsPlanprojectbatchService.findById(sysGuideConfig.getBatchid()).getName());
        sysGuideConfig.setTopicname(sysGuideDirection.getName());
        sysGuideConfig.setSupportdirection(sysGuideChildDirection.getName());
        sysGuideConfig.setBelonglab(sysGuideDirection.getBelonglab());
        sysGuideConfig.setBelonglabid(sysGuideDirection.getBelonglabid());
        saveOrUpdate(sysGuideConfig);
    }

    public JSONObject judgeGuide(Map param){
        JSONObject resObj = new JSONObject();
        SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
        String projectbaseid = (String) param.get("projectbaseid");
        String supportdirectionchild =(String) param.get("supportdirectionchild");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("name",supportdirectionchild);
        queryWrapper.eq("type","childdirection");
        SysGuide sysGuide=sysGuideMapper.selectOne(queryWrapper);
        if(null==sysGuide){
            throw new BusinessException("supportdirectionchild参数查询数据为空。");
        }
        SysGuideConfig sysGuideConfig=getById(sysGuide.getId());
        if(null==sysGuideConfig){
            throw new BusinessException("supportdirectionchild参数查询数据为空。");
        }
        String demand = sysGuideConfig.getDemand();
        JSONObject demandObj =JSONObject.fromObject(demand);
        JSONArray demandArr = demandObj.getJSONArray("array");
        String errMsg = "";
        int errNum = 1;
        if(demandArr.size()>0){
            for (int i = 0; i < demandArr.size(); i++) {
                JSONObject json = this.judgeGuideCore(demandArr.getJSONObject(i),user,projectbaseid);
                if(!json.getBoolean("success")){
                    errMsg = errMsg + String.valueOf(errNum) + "." + json.get("errMsg") + "\r\n";
                    errNum ++;
                }else{
                    continue;
                }
            }
        }

        resObj.put("success", Util.isEoN(errMsg)?true:false);
        resObj.put("reason",errMsg);

        return resObj;
    }

    public JSONObject judgeGuideCore(JSONObject json, SysUser sysUser, String projectbaseid){
        JSONObject resObj = new JSONObject();
        String supportdirectionchild = json.get("supportdirectionchild")+""; //子方向
        String username = sysUser.getName();
        JSONObject unitInfo = this.pmsSaveAndInitNewService.getUserEnterForSb(sysUser);

        String type = json.getString("type");

        if("unit".equals(type)){ //承担单位限制
            String targettype = json.get("targettype")+"";
            String targetname = json.get("targetname")+"";

            if(!Util.isEoN(targetname)){ //指定单位名称
                String unitname = unitInfo.get("unitname")+"";

                if(!unitname.equals(targetname)){
                    resObj.put("success",false);
                    resObj.put("errMsg",supportdirectionchild+"：该子方向要求“"+targetname+"”作为承担单位。");
                }else{
                    resObj.put("success",true);
                }
            }else if(!Util.isEoN(targetname)){ //指定单位类型
                String unittype = unitInfo.get("unittype")+"";

                if(!unittype.equals(targettype)){
                    resObj.put("success",false);
                    resObj.put("errMsg",supportdirectionchild+"：该子方向要求承担单位的性质为“"+targettype+"”。");
                }else{
                    resObj.put("success",true);
                }
            }
        }else if("coopunit".equals(type)){ //合作单位限制
            String targetname = json.get("targetname")+"";
            String isExist =pmsProjectbaseCddwService.findOne(new HqlBuilder().
                    eq("mainid",projectbaseid).
                    eq("type","principal").
                    eq("name","targetname")).getId();

            if(Util.isEoN(isExist)){
                resObj.put("success",false);
                resObj.put("errMsg",supportdirectionchild+"：该子方向要求合作单位中包含“"+targetname+"”。");
            }else{
                resObj.put("success",true);
            }

        }else if("coopunitnum".equals(type)){ //合作单位数量限制
            String compare = json.get("targettype")+""; //大于、小于、等于
            String targetnumstr = json.get("targetname")+""; //具体数量
            Long targetnum = Long.parseLong(targetnumstr);
            Long coopunitnum =pmsProjectbaseCddwService.findCount(new HqlBuilder().
                    eq("mainid",projectbaseid).
                    eq("type","principal"));

            if("大于".equals(compare)){
                if(coopunitnum>targetnum){
                    resObj.put("success",true);
                }else{
                    resObj.put("success",false);
                    resObj.put("errMsg","现有合作单位"+coopunitnum+"家，应"+compare+targetnum+"。");
                }
            }else if("小于".equals(compare)){
                if(coopunitnum<targetnum){
                    resObj.put("success",true);
                }else{
                    resObj.put("success",false);
                    resObj.put("errMsg","现有合作单位"+coopunitnum+"家，应"+compare+targetnum+"。");
                }
            }else{
                if(coopunitnum==targetnum){
                    resObj.put("success",true);
                }else{
                    resObj.put("success",false);
                    resObj.put("errMsg","现有合作单位"+coopunitnum+"家，应"+compare+targetnum+"。");
                }
            }
        }else if("person".equals(type)){ //负责人限制
            String targetname = json.get("targetname")+"";

            if(!username.equals(targetname)){
                resObj.put("success",false);
                resObj.put("errMsg",supportdirectionchild+"：该子方向要求“"+targetname+"”作为项目负责人。");
            }else{
                resObj.put("success",true);
            }
        }

        return null;
    }

}
