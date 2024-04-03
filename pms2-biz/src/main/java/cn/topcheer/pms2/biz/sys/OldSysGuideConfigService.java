/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2015-12-24 14:55:10
 *
 */
package cn.topcheer.pms2.biz.sys;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.api.sys.SysGuide;
import cn.topcheer.pms2.api.sys.SysGuideConfig;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.project.service.impl.PmsSaveAndInitNewService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.sys.SysGuideConfigDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * SysGuideConfig 服务类
 */
@Service(value="SysGuideConfigService")
public class OldSysGuideConfigService extends GenericService<SysGuideConfig> {

	public SysGuideConfigDao getSysGuideConfigDao() {
		return (SysGuideConfigDao) this.getGenericDao();
	}

	@Autowired
	public void setSysGuideConfigDao(SysGuideConfigDao sysGuideConfigDao) {

		this.setGenericDao(sysGuideConfigDao);
	}
	@Autowired
    DBHelper dbHelper;
    @Autowired
    PmsPlanprojectbatchService pmsPlanprojectbatchService;
    @Autowired
    PmsSaveAndInitNewService pmsSaveAndInitNewService;
    @Autowired
    SysGuideService sysGuideService;

	public boolean saveGuide(JSONObject jsonObject) {
        String batchid = jsonObject.get("batchid")+"";
        PmsPlanprojectbatch batch = this.pmsPlanprojectbatchService.findById(batchid);

        String id = "";
        if(Util.isEoN(jsonObject.get("id"))){
            id = UUID.randomUUID().toString();
        }else{
            id = jsonObject.get("id")+"";
        }

        String sourcetype = jsonObject.get("sourcetype")+"";

        SysGuide sysGuideDirection = new SysGuide();
        SysGuide sysGuideChildDirection = new SysGuide();
        SysGuide sysGuideProject = new SysGuide();

        if("direction".equals(sourcetype)){ //方向配置
            //方向
            sysGuideDirection = sysGuideService.getById(jsonObject.get("sourceid")+"");
        }else if("childdirection".equals(sourcetype)){ //子方向配置
            //子方向
            sysGuideChildDirection = sysGuideService.getById(jsonObject.get("sourceid")+"");
            //方向
            sysGuideDirection = sysGuideService.getById(sysGuideChildDirection.getParentid());
        }else if("project".equals(sourcetype)){ //项目配置
            //项目
            sysGuideProject = sysGuideService.getById(jsonObject.get("sourceid")+"");
            //子方向
            sysGuideChildDirection = sysGuideService.getById(sysGuideProject.getParentid());
            //方向
            sysGuideDirection = sysGuideService.getById(sysGuideChildDirection.getParentid());
        }


        SysGuideConfig sysGuideConfig = this.findById(id);
        if(sysGuideConfig==null){
            sysGuideConfig = new SysGuideConfig();
        }


        Util.ApplyObject(sysGuideConfig,jsonObject);
        sysGuideConfig.setId(id);
        sysGuideConfig.setBatchid(batchid);
        sysGuideConfig.setBatchname(batch.getName());
        sysGuideConfig.setFund(Double.parseDouble(jsonObject.get("fund")+""));
        sysGuideConfig.setDemand(jsonObject.get("demand")+"");
//        sysGuideConfig.setSupportdirectionchild(jsonObject.get("name")+"");
//        sysGuideConfig.setSupportdirectionchildid(id);
        sysGuideConfig.setTopicname(sysGuideDirection.getName());
        sysGuideConfig.setSupportdirection(sysGuideChildDirection.getName());
        sysGuideConfig.setBelonglab(sysGuideDirection.getBelonglab());
        sysGuideConfig.setBelonglabid(sysGuideDirection.getBelonglabid());
        this.merge(sysGuideConfig);

		return true;
	}

    public List<SysGuideConfig> initGuide(String batchid) {
        // TODO Auto-generated method stub
        String hql = "select t from SysGuideConfig t where t.batchid = ? order by t.seq ";
        List<SysGuideConfig> list = this.findByHql(hql,new Object[]{batchid});
        return list;
    }


    /**
     *  判断指南
     */
    public JSONObject judgeGuide(JSONObject jsonObject, SysUser user){
        JSONObject resObj = new JSONObject();
        String errMsg = "";
//        SysUser sysUser = user.getUser();
        String projectbaseid = jsonObject.get("projectbaseid")+"";
        String supportdirectionchild = jsonObject.get("supportdirectionchild")+""; //子方向
        String childid = this.dbHelper.getOnlyStringValue("select id from sys_guide " +
                "where name = ? and type = 'childdirection' ",new Object[]{supportdirectionchild});

        SysGuideConfig sysGuideConfig = this.findById(childid);
        String demandStr = sysGuideConfig.getDemand();

        JSONObject demandObj = JSONObject.fromObject(demandStr);
        JSONArray demandArr = demandObj.getJSONArray("array");
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

        resObj.put("success",Util.isEoN(errMsg)?true:false);
        resObj.put("reason",errMsg);

        return resObj;
    }


    public JSONObject judgeGuideCore(JSONObject json, SysUser sysUser, String projectbaseid){
        JSONObject resObj = new JSONObject();
        String supportdirectionchild = json.get("supportdirectionchild")+""; //子方向
        String username = sysUser.getName();
        JSONObject unitInfo = this.pmsSaveAndInitNewService.getUserEnterForSb(sysUser);

        String type = json.get("type")+"";

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

            String isExist = this.dbHelper.getOnlyStringValue("select id from pms_projectbase_cddw " +
                    "where mainid = ? and type = 'principal' and name = ?",new Object[]{projectbaseid,targetname});

            if(Util.isEoN(isExist)){
                resObj.put("success",false);
                resObj.put("errMsg",supportdirectionchild+"：该子方向要求合作单位中包含“"+targetname+"”。");
            }else{
                resObj.put("success",true);
            }

        }else if("coopunitnum".equals(type)){ //合作单位数量限制
            String compare = json.get("targettype")+""; //大于、小于、等于
            String targetnumstr = json.get("targetname")+""; //具体数量
            Integer targetnum = Integer.parseInt(targetnumstr);

            String coopunitnumstr = this.dbHelper.getOnlyStringValue("select count(1) from pms_projectbase_cddw " +
                    "where mainid = ? and type = 'principal' ",new Object[]{projectbaseid});
            Integer coopunitnum = Integer.parseInt(coopunitnumstr);

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
