package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.plan.entity.BatchGuide;
import cn.topcheer.pms2.api.plan.entity.BatchGuideAffix;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.plan.BatchGuideDao;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="BatchGuideService")
public class BatchGuideService extends GenericService<BatchGuide> {

    public BatchGuideDao getBatchGuideDao() {
        return (BatchGuideDao) this.getGenericDao();
    }
    @Autowired
    public void setBatchGuideDao(BatchGuideDao batchGuideDao) {
        this.setGenericDao(batchGuideDao);
    }

    @Autowired
    BatchGuideAffixService batchGuideAffixService;
    @Autowired
    PmsPlanprojectbatchService pmsPlanprojectbatchService;


    /**
     *【小批次业务配置】--获取小批次指南内容
     */
    public JSONObject initData(String batchid){
        JSONObject resJson = new JSONObject();
        if(Util.isEoN(batchid)){
            return resJson;
        }
        resJson.put("main",this.initMainData(batchid));
        resJson.put("affix",this.initAffixData(batchid));
        return resJson;
    }

    /**
     * 获取小批次指南内容--主表
     */
    public BatchGuide initMainData(String batchid){
        BatchGuide main = this.findById(batchid);
        if(Util.isEoN(main)){
            //说明是第一次获取
            main = new BatchGuide();
            main.setId(batchid);
            PmsPlanprojectbatch batch = this.pmsPlanprojectbatchService.findById(batchid);
            main.setName(batch.getPmsPlanproject().getProjectname());
        }
        return main;
    }

    /**
     * 获取小批次指南内容--附件表
     */
    public List<BatchGuideAffix> initAffixData(String batchid){
        return this.findByHql("from BatchGuideAffix where sourceid = ?0 order by seq",new Object[]{batchid});
    }

    /**
     *【小批次业务配置】--保存小批次指南内容
     */
    public JSONObject saveData(String batchid,JSONObject json){
        JSONObject resJson = new JSONObject();
        if(Util.isEoN(batchid)){
            return Util.dealResJson(resJson,false,"前台参数不完整。");
        }
        //保存主表对象
        JSONObject mainObj = json.getJSONObject("main");//主表对象
        this.saveMainData(batchid,mainObj);
        //保存附件数组
        JSONArray affixArr = json.getJSONArray("affix");//附件数组
        this.saveAffixData(batchid,affixArr);
        return Util.dealResJson(resJson,true);
    }

    /**
     * 保存小批次指南内容--主表
     */
    public void saveMainData(String batchid,JSONObject mainObj){
        BatchGuide main = this.findById(batchid);
        if(Util.isEoN(main)){
            //说明是第一次保存
            main = new BatchGuide();
        }
        Util.ApplyObject(main,mainObj);
        main.setId(batchid);
        this.merge(main);
    }

    /**
     * 保存小批次指南内容--附件表
     */
    public void saveAffixData(String batchid,JSONArray affixArr){
        //后台数据
        List<BatchGuideAffix> dataList = this.initAffixData(batchid);
        //前台数据
        List<BatchGuideAffix> dataArray = JSON.parseArray(affixArr.toString(), BatchGuideAffix.class);
        String sameIds = "";//前后台数据相同的id
        int i = 0;
        for(BatchGuideAffix p : dataArray){
            i++;
            if(Util.isEoN(p.getId())){
                p.setId(Util.NewGuid());
            }
            p.setSourceid(batchid);
            p.setSeq(i);
            this.batchGuideAffixService.merge(p);
            sameIds += p.getId()+",";
        }
        //删除本次保存在后台没有的数据
        for(BatchGuideAffix p : dataList){
            if(!sameIds.contains(p.getId())){
                this.batchGuideAffixService.delete(p);
            }
        }
    }


    
}
