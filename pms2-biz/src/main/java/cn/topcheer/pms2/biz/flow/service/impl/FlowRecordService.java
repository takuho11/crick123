package cn.topcheer.pms2.biz.flow.service.impl;


import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.flow.entity.FlowRecord;

import cn.topcheer.pms2.dao.flow.FlowPointDao;
import cn.topcheer.pms2.dao.flow.FlowRecordDao;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by peanut.huang on 2019/4/22.
 */
@Service
public class FlowRecordService extends GenericService<FlowRecord> {
    public FlowRecordDao getFlowRecordDao() {
        return (FlowRecordDao) this.getGenericDao();
    }

    @Autowired
    public void setFlowRecordDao(FlowRecordDao flowRecordDao) {

        this.setGenericDao(flowRecordDao);
    }

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private FlowPointDao flowPointDao;

//    @Autowired
//    OperationDao operationDao;
//
//    @Autowired
//    OperationResultDao operationResultDao;
//
//
//    public void insertFlowRecord(String currentflowpointid, String currentoperationid, String currentoperationresultid, String currentoperatorid, String currentoperatorname, String sourceid, String operationcontent, String souceflowpointid) {
//        FlowPoint flowPoint = this.flowPointDao.findById(currentflowpointid);
//        FlowPoint sflowpoint = this.flowPointDao.findById(souceflowpointid);
//        Operation operation = this.operationDao.findById(currentoperationid);
//        OperationResult operationResult = this.operationResultDao.findById(currentoperationresultid);
//        FlowRecord flowRecord = new FlowRecord();
//        flowRecord.setId(UUID.randomUUID().toString());
//        flowRecord.setCurrentflowpointid(flowPoint.getId());
//        flowRecord.setCurrentflowpointname(flowPoint.getName());
//        flowRecord.setCurrentoperationid(operation.getId());
//        flowRecord.setCurrentoperationname(operation.getName());
//        flowRecord.setCurrentoperationresultid(operationResult.getId());
//        flowRecord.setCurrentoperationresultname(operationResult.getName());
//        flowRecord.setSourceid(sourceid);
//        flowRecord.setCurrentoperatorid(currentoperatorid);
//        flowRecord.setCurrentoperatorname(currentoperatorname);
//        flowRecord.setOperationtime(new Date());
//        flowRecord.setOperationcontent(operationcontent);
//        flowRecord.setSourceflowpointid(sflowpoint.getId());
//        flowRecord.setSourceflowpointname(sflowpoint.getName());
//        this.getFlowRecordDao().merge(flowRecord);
//    }
//
//    /**
//     * 获取该项目流程操作的记录
//     *
//     * @param projectbaseid
//     * @return
//     */
//    public List<FlowRecord> findAllFlowRecord(String projectbaseid) {
//        String hql = "from cn.topcheer.flow.pojo.FlowRecord as u where u.sourceid = ? order by u.operationtime desc";
//        @SuppressWarnings("unchecked")
//        List<FlowRecord> list = this.getFlowRecordDao()
//                .findByHql(hql, new Object[]{projectbaseid});
//        return list;
//    }
//
    /**
     * 根据当前sourceid 和 流程名 获取最后一条操作的流程
     *
     * @param sourceId
     * @param flowpointname
     * @return
     */
    public FlowRecord getLastRecordBySourceidAndFpName(String sourceId, String flowpointname) {
        String hql = "from cn.topcheer.flow.pojo.FlowRecord as u where u.sourceid = ? and u.sourceflowpointname = ? order by u.operationtime desc";
        Query query = this.getFlowRecordDao().getQuery(hql);
        query.setParameter(0, sourceId);
        query.setParameter(1, flowpointname);
        query.setMaxResults(1);
        FlowRecord flowRecord = (FlowRecord) query.uniqueResult();
        return flowRecord;
    }
//
//    //获取传入状态 对应的最后一条 操作时间和内容
//    public Map<String, String> getRecommendDeteal(String sourceId, List<String> flowpointnames) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
//        Map<String, String> map = new HashMap<>();
//        flowpointnames.stream().forEach(m -> {
//            FlowRecord flowRecord = this.getLastRecordBySourceidAndFpName(sourceId, m);
//            if (flowRecord != null) {
//                map.put(m + "操作时间", simpleDateFormat.format(flowRecord.getOperationtime()));
//                map.put(m + "操作内容", flowRecord.getOperationcontent());
//            } else {
//                map.put(m + "操作时间", "");
//                map.put(m + "操作内容", "");
//            }
//        });
//        return map;
//    }
//
    //获取传入状态 对应的最后一条 操作时间和内容的数据源
    public Map<String, Object> getRecommendDeteal(String sourceId, List<String> flowpointnames, Map<String, Object> hashmap) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        flowpointnames.stream().forEach(m -> {
            FlowRecord flowRecord = this.getLastRecordBySourceidAndFpName(sourceId, m);
            if (flowRecord != null) {
                if (m.indexOf("项目申报:所在单位审核") > -1) {
                    hashmap.put("szdwshdate", simpleDateFormat.format(flowRecord.getOperationtime()));
                    hashmap.put("szdwshyj", flowRecord.getOperationcontent());
                } else if (m.indexOf("项目申报:推荐单位审核") > -1) {
                    hashmap.put("tjdwshdate", simpleDateFormat.format(flowRecord.getOperationtime()));
                    hashmap.put("tjdwshyj", flowRecord.getOperationcontent());
                }
            } else {
                hashmap.put("szdwshdate", "年 月 日");
                hashmap.put("szdwshyj", "");
                hashmap.put("tjdwshdate", "年 月 日");
                hashmap.put("tjdwshyj", "");
            }
        });
        return hashmap;
    }
//
    /**
     * 根据业务id获取最后一条操作记录
     *
     * @param sourceid
     * @return
     */
    public List<Map> fetchLastOneFlowRecord(String sourceid) {
        List<Map> result = new ArrayList<>();
        String sql = " select tt.*\n" +
                " from (\n" +
                "     select t.*\n" +
                "     from fl_flowrecord t \n" +
                "     where t.sourceid = ? \n" +
                " order by t.operationtime desc) tt\n" +
                " where ROWNUM = 1";
        result = dbHelper.getRows(sql, new Object[]{sourceid});
        return result;
    }
//
//    /**
//     * 保存自定义流程的流转记录
//     */
//    public void saveCustomData(String result, String operationcontent, String sourceid,
//                               String state, String stateid,
//                               String oldstate, String oldstateid,
//                               Date nowDate) {
//        FlowRecord f = new FlowRecord();
//        f.setId(Util.NewGuid());
//        f.setCurrentflowpointname(state);
//        f.setCurrentflowpointid(stateid);
//        f.setCurrentoperationname(result);
//        f.setCurrentoperationresultname(result);
//        f.setOperationcontent(operationcontent);
//        f.setSourceflowpointname(oldstate);
//        f.setSourceflowpointid(oldstateid);
//        //各个层获取用户
//        SecurityUser securityUser = SecurityUserLocal.getSecurityUser();
//        SysUser user = securityUser.getUser();
//        f.setCurrentoperatorid(user.getId());
//        f.setCurrentoperatorname(user.getName());
//        f.setOperationtime(nowDate);//用传参的形式，是为了跟业务主表的上报时间保持一致
//        f.setSourceid(sourceid);
//        this.merge(f);
//    }
//
//
//    /**
//     * 保存自定义流程的流转记录（在用户未登陆情况下调用）
//     */
//    public void saveCustomData(String result, String operationcontent, String sourceid,
//                               String userid, String username,
//                               String state, String stateid,
//                               String oldstate, String oldstateid,
//                               Date nowDate) {
//        FlowRecord f = new FlowRecord();
//        f.setId(Util.NewGuid());
//        f.setCurrentflowpointname(state);
//        f.setCurrentflowpointid(stateid);
//        f.setCurrentoperationname(result);
//        f.setCurrentoperationresultname(result);
//        f.setOperationcontent(operationcontent);
//        f.setSourceflowpointname(oldstate);
//        f.setSourceflowpointid(oldstateid);
//        f.setCurrentoperatorid(userid);
//        f.setCurrentoperatorname(username);
//        f.setOperationtime(nowDate);//用传参的形式，是为了跟业务主表的上报时间保持一致
//        f.setSourceid(sourceid);
//        this.merge(f);
//    }
//
//
//    public Boolean deleteFlowRecordBySourceid(String sourceid) {
//        String hql = "select t from FlowRecord t where t.sourceid = ? ";
//        List<FlowRecord> flowRecordList = this.findByHql(hql, sourceid);
//        this.deleteList(flowRecordList);
//        return true;
//    }
}
