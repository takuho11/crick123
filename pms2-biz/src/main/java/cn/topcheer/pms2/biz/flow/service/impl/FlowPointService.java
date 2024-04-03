package cn.topcheer.pms2.biz.flow.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.flow.entity.FlowPoint;
import cn.topcheer.pms2.dao.flow.FlowPointDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by peanut.huang on 2019/4/22.
 */
@Service
public class FlowPointService extends GenericService<FlowPoint> {
    public FlowPointDao getFlowPointDao() {
        return (FlowPointDao) this.getGenericDao();
    }

    @Autowired
    public void setFlowPointDao(FlowPointDao flowPointDao) {

        this.setGenericDao(flowPointDao);
    }

    @Autowired
    private FlowDefineService flowDefineService;

//    @Autowired
//    private FlAuthorityFpDao flAuthorityFpDao;
//
//    public List<Map> getFlowPointListMapByFDid(String fdid){
//        List<Map> list = this.getListBySql("select * from FL_FLOWPOINT t where t.FLOWDEFINEFPID = ?",new Object[]{fdid});
//        return list;
//    }
//
//    //根据流程定义id获取所有节点
//    public List<FlowPoint> getFlowPointListByFDid(String fdid){
//        String hql = "select t from FlowPoint t where t.fLowDefine.id = ?";
//        Query query = this.getFlowPointDao().getQuery(hql);
//        query.setParameter(0,fdid);
//        List<FlowPoint> list = query.list();
//        return list;
//    }
//
//    //
//    public FlowPoint getFirstFlowPointByBatchidAndType(String batchid,String type){
//        String hql = "select t from FlowPoint t where t.fLowDefine.sourceids like ? and t.fLowDefine.type = ? order by t.seq";
//        Query query = this.getFlowPointDao().getQuery(hql);
//        query.setParameter(0,"%"+batchid+"%");
//        query.setParameter(1,type);
//        List<FlowPoint> list = query.list();
//        if(list.size()>0){
//            return list.get(0);
//        }else{
//            return null;
//        }
//    }
//
//
//
    public FlowPoint getFirstFlowPointByBatchidAndTypeAndMold(String batchid,String type,String mold){
        String hql = "select t from FlowPoint t where t.fLowDefine.sourceids like ?0 and t.fLowDefine.type = ?1 and t.fLowDefine.mold =?2 order by t.seq";
        Query query = this.getFlowPointDao().getQuery(hql);
        query.setParameter(0,"%"+batchid+"%");
        query.setParameter(1,type);
        query.setParameter(2,mold);
        List<FlowPoint> list = query.list();
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
//
//    public FlowPoint getLastFlowPointByBatchidAndType(String batchid,String type){
//        String hql = "select t from FlowPoint t where t.fLowDefine.sourceids like ? and t.fLowDefine.type = ? order by t.seq desc";
//        Query query = this.getFlowPointDao().getQuery(hql);
//        query.setParameter(0,"%"+batchid+"%");
//        query.setParameter(1,type);
//        List<FlowPoint> list = query.list();
//        if(list.size()>0){
//            return list.get(0);
//        }else{
//            return null;
//        }
//    }
//
//    public List<Map> getFlowPointListByFlowDefineId(String flowDefineId){
//        String sql = "select t.*,fp.roleid from FL_FLOWPOINT t left join  FL_AUTHORITY_FP fp on t.ID = fp.FLOWPOINTID where t.flowdefinefpid = ? order by t.seq desc ";
//        List<Map> list = this.flowDefineService.getListBySql(sql,new Object[]{flowDefineId});
//        LinkedHashMap<String,Map> hashMap = new LinkedHashMap();
//        list.forEach(m -> {
//            if(hashMap.containsKey(m.get("id"))){
//                Map source = hashMap.get(m.get("id"));
//                source.put("roleid",source.get("roleid")+","+m.get("roleid"));
//                hashMap.put(m.get("id").toString(),source);
//            }else{
//                hashMap.put(m.get("id").toString(),m);
//            }
//        });
//        List<Map> valueList = new ArrayList<Map>(hashMap.values());
//        return valueList;
//    }
//
//    public void mergerFlowPoint(JSONObject jsonObject){
//        FlowPoint flowPoint = new FlowPoint();
//        Util.ApplyObject(flowPoint,jsonObject);
//        String id = flowPoint.getId();
//        String flowdefinefpid = jsonObject.getString("flowdefinefpid");
//        FLowDefine flowDefine = this.flowDefineService.findById(flowdefinefpid);
//        flowPoint.setFLowDefine(flowDefine);
//        if(id!=null&&id.length()>1){
//            this.getFlowPointDao().merge(flowPoint);
//        }else{
//            flowDefine.setId(UUID.randomUUID().toString());
//            this.getFlowPointDao().save(flowPoint);
//        }
//        JSONArray jsonArray = jsonObject.getJSONArray("roleid");
//        this.flAuthorityFpDao.deleteFlAuthorityFpByFlowpointid(flowPoint.getId());
//
//        jsonArray.forEach(m -> {
//            JSONObject roleJo = (JSONObject)m;
//            String roleid = roleJo.getString("id");
//            FlAuthorityFp flAuthorityFp = new FlAuthorityFp();
//            flAuthorityFp.setId(UUID.randomUUID().toString());
//            flAuthorityFp.setFlowdefineid(flowdefinefpid);
//            flAuthorityFp.setFlowpointid(flowPoint.getId());
//            flAuthorityFp.setType(flowDefine.getType());
//            flAuthorityFp.setRoleid(roleid);
//            this.flAuthorityFpDao.merge(flAuthorityFp);
//        });
//
//    }
//
//    public boolean deleteFlowPointById(String id){
//        this.getFlowPointDao().deleteById(id);
//        return true;
//    }
//
//
//    /**
//     * 根据小批次id获取对应流程阶段
//     */
//    public List<Map> getStateByBatchid(String batchid,String type){
//        return this.getListBySql("select e.name from fl_flowpoint e " +
//                " left join fl_flowdefine d on e.flowdefinefpid = d.id " +
//                " where d.sourceids like ? and d.type = ? " +
//                " order by e.seq",new Object[]{"%"+batchid+"%",type});
//    }



}
