package cn.topcheer.pms2.biz.flow.service.impl;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.flow.entity.FLowDefine;
import cn.topcheer.pms2.dao.flow.FlowDefineDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by peanut.huang on 2019/4/22.
 */
@Service
public class FlowDefineService extends GenericService<FLowDefine> {
    public FlowDefineDao getFlowDefineDao() {
        return (FlowDefineDao) this.getGenericDao();
    }

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    public void setFlowDefineDao(FlowDefineDao flowDefineDao) {

        this.setGenericDao(flowDefineDao);
    }

//    public List<FLowDefine> getAllFlowDefine(){
//        List<FLowDefine> list = this.getFlowDefineDao().findByHql("select t from FLowDefine t order by t.type",null);
//        return list;
//    }
//
//    public void mergerFlowDefine(JSONObject jsonObject){
//        FLowDefine flowDefine = new FLowDefine();
//        Util.ApplyObject(flowDefine,jsonObject);
//        String id = flowDefine.getId();
//        if(id!=null&&id.length()>1){
//            this.getFlowDefineDao().merge(flowDefine);
//        }else{
//            flowDefine.setId(UUID.randomUUID().toString());
//            this.getFlowDefineDao().save(flowDefine);
//        }
//    }
//
//    /**
//     * 整合页面上的下拉后 选择绑定
//     * @param flowdefinedid
//     * @param bid
//     * @return
//     */
//    public boolean bindingFlowDefine(String flowdefinedid, String bid){
//        boolean flag= false;
//        try{
//            FLowDefine fLowDefine = this.findById(flowdefinedid);
//            String sourceids = fLowDefine.getSourceids();
//            if(fLowDefine!=null){
//                if(sourceids==null||sourceids.indexOf(bid)==-1){
//                    sourceids += bid +",";
//                    fLowDefine.setSourceids(sourceids);
//                    this.merge(fLowDefine);
//                }
//            }
//            flag= true;
//
//        }catch (Exception e){
//            log.error(e);
//        }
//        return flag;
//    }
//
//    /**
//     * 接触绑定绑定
//     * @param
//     * @param bid
//     * @return
//     */
//    public boolean unbindingFlowDefine(String bid, String flowdefineid){
//        boolean flag= false;
//        try{
//            FLowDefine fLowDefine = this.findById(flowdefineid);
//            if(fLowDefine!=null){
//                String sourceids = fLowDefine.getSourceids();
//                sourceids = sourceids.replace(bid+",","");
//                fLowDefine.setSourceids(sourceids);
//                this.merge(fLowDefine);
//                flag= true;
//            }
//        }catch (Exception e){
//            log.error(e);
//        }
//        return flag;
//    }
//
//    /**
//     * 获取当前批次在当前类型中所有绑定的流程
//     * @param bid
//     * @param type
//     * @return
//     */
//    public List<FLowDefine> getAllBindedFlowDefine(String bid, String type){
//        String hql = "select t from FLowDefine t where  t.sourceids like ? and t.type = ?";
//        Query query = this.getFlowDefineDao().getQuery(hql);
//        query.setParameter(0,"%"+bid+"%");
//        query.setParameter(1,type);
//        return query.list();
//    }
//
//    public boolean deleteFlowDefineById(String id){
//        this.getFlowDefineDao().deleteById(id);
//        return true;
//    }
//
//    public FLowDefine findByBidAndTypeAndMold(String bid, String type, String mold) {
//        String hql = "select t from FLowDefine t where t.sourceids like ? and t.type = ? and t.mold = ? ";
//        Query query = this.getFlowDefineDao().getQuery(hql);
//        query.setParameter(0,"%"+bid+"%");
//        query.setParameter(1,type);
//        query.setParameter(2,mold);
//        FLowDefine fLowDefine = (FLowDefine)query.uniqueResult();
//        return fLowDefine;
//    }
//
//    /**
//     * 根据批次id 业务类别 获取绑定的流程
//     * @param bid 批次id
//     * @param type 业务类别 sb ht等
//     * @return
//     */
//    public FLowDefine findByBidAndType(String bid, String type) {
//        String hql = "select t from FLowDefine t where t.sourceids like ? and t.type = ?";
//        Query query = this.getFlowDefineDao().getQuery(hql);
//        query.setParameter(0,"%"+bid+"%");
//        query.setParameter(1,type);
//        FLowDefine fLowDefine = (FLowDefine)query.uniqueResult();
//        return fLowDefine;
//    }
//
//    /**
//     * 流程复制
//     * @param flowdefineid
//     * @return
//     */
//    public boolean copyFlowDefine(String flowdefineid){
//        final Boolean[] flag = {true};
//        String id = flowdefineid;
//        String newfdid = UUID.randomUUID().toString();
//
//        List<Map> fd_list = dbHelper.getRows("select * from FL_FLOWDEFINE t where t.id = ? ",new Object[]{id});
//        Map<String,String> map = fd_list.stream().collect(Collectors.groupingBy(m->m.get("id").toString(),Collectors.mapping(m->m.get("id").toString(),Collectors.joining())));
//        fd_list.forEach(m->{
//            //fp_map.put(m.get("id")+"",UUID.randomUUID().toString());
//            JSONArray array = new JSONArray();
//            array.add(m.get("name")+"(复制)");
//            array.add(m.get("memo")+"");
//            array.add("");
//            array.add(m.get("type")+"");
//            array.add(m.get("isenabled")+"");
//            array.add(m.get("mold")+"");
//            array.add(newfdid);
//            String sql = "insert into fl_flowdefine (name,memo,sourceids,type,isenabled,mold,id) values (?,?,?,?,?,?,?)";
//            try {
//                dbHelper.runSql(sql,array.toArray());
//            } catch (SQLException e) {
//                e.printStackTrace();
//                flag[0] = false;
//            }
//        });
//        List<Map> fp_list = dbHelper.getRows("select * from FL_FLOWPOINT t where t.flowdefinefpid = ?",new Object[]{id});
//        Map<String,String> fp_map = new HashMap<>();
//        fp_list.forEach(m->{
//            String newId = UUID.randomUUID().toString();
//            fp_map.put(m.get("id")+"",newId);
//            JSONArray array = new JSONArray();
//            array.add(m.get("name")+"");
//            array.add(m.get("memo")+"");
//            array.add(newfdid);
//            array.add(m.get("ftype")+"");
//            array.add(m.get("seq")+"");
//            array.add(newId);
//            String sql = "insert into fl_flowpoint (name,memo,flowdefinefpid,ftype,seq,id) values (?,?,?,?,?,?)";
//            try {
//                dbHelper.runSql(sql,array.toArray());
//            } catch (SQLException e) {
//                e.printStackTrace();
//                flag[0] = false;
//            }
//        });
//
//        String opsql = fp_map.keySet().stream().collect(Collectors.joining("','","('","')"));
//        List<Map> op_list = dbHelper.getRows("select * from  FL_OPERATION t where t.flowpointid in "+opsql,null);
//        Map<String,String> op_map = new HashMap<>();
//        op_list.forEach(m->{
//            final String[] newoperationcode = {""};
//            final Boolean[] hasOldPoint = {false};
//            fp_map.forEach((k,v)->{
//                if((m.get("operationcode")+"").indexOf(k)>-1){
//                    newoperationcode[0] = (m.get("operationcode")+"").replaceAll(k,v);
//                    hasOldPoint[0] = true;
//                }else{
//                    if(!hasOldPoint[0]){
//                        newoperationcode[0] = m.get("operationcode")+"";
//                    }
//                }
//            });
//
//
//            String newId = UUID.randomUUID().toString();
//            op_map.put(m.get("id")+"",newId);
//            JSONArray array = new JSONArray();
//            array.add(m.get("name")+"");
//            array.add(m.get("memo")+"");
//            array.add(fp_map.containsKey(m.get("flowpointid")+"")?fp_map.get(m.get("flowpointid")+""):"");
//            array.add(newoperationcode[0]);
//            array.add(m.get("isneedaffix")+"");
//            array.add(m.get("otype")+"");
//            array.add(m.get("seq")+"");
//            array.add(m.get("account")+"");
//            array.add(newId);
//            String sql = "insert into fl_operation (name,memo,flowpointid,operationcode,isneedaffix,otype,seq,account,id) values (?,?,?,?,?,?,?,?,?)";
//            try {
//                dbHelper.runSql(sql,array.toArray());
//            } catch (SQLException e) {
//                e.printStackTrace();
//                flag[0] = false;
//            }
//        });
//
//        String orsql = op_map.keySet().stream().collect(Collectors.joining("','","('","')"));
//        List<Map> or_list = dbHelper.getRows("select * from FL_OPERATIONRESULT t where t.operationid in "+orsql,null);
//        //Map<String,String> op_map = new HashMap<>();
//        or_list.forEach(m->{
//            String newId = UUID.randomUUID().toString();
//            JSONArray array = new JSONArray();
//            array.add(m.get("name")+"");
//            array.add(op_map.containsKey(m.get("operationid")+"")?op_map.get(m.get("operationid")+""):"");
//            array.add(fp_map.containsKey(m.get("nextflowpointid")+"")?fp_map.get(m.get("nextflowpointid")+""):"");
//            array.add(m.get("seq")+"");
//            array.add(newId);
//            String sql = "insert into fl_operationResult (name,operationid,nextflowpointid,seq,id) values (?,?,?,?,?)";
//            try {
//                dbHelper.runSql(sql,array.toArray());
//            } catch (SQLException e) {
//                e.printStackTrace();
//                flag[0] = false;
//            }
//        });
//
//        return flag[0];
//    }
//
//    public JSONObject getAllFlowDetails(String flowdefineid){
//
//
//        FLowDefine fLowDefine = this.getFlowDefineDao().findById(flowdefineid);
//        String fp_sql = "select t.id,t.name,t.seq from FL_FLOWPOINT t where t.flowdefinefpid = ? order by t.seq  ";
//        List<Map> fp_list = this.getListBySql(fp_sql,new Object[]{flowdefineid});
//        String op_sql = "select t.id,t.flowpointid,t.name,t.seq from FL_OPERATION t where t.flowpointid in (select f.id from FL_FLOWPOINT f where f.flowdefinefpid = ?)  order by t.seq  ";
//        List<Map> op_list = this.getListBySql(op_sql,new Object[]{flowdefineid});
//        String or_sql = "select fo.id,fo.operationid,fo.name,fo.seq,fo.nextflowpointid from FL_OPERATIONRESULT fo where fo.operationid in (select t.id from FL_OPERATION t where t.flowpointid in (select f.id from FL_FLOWPOINT f where f.flowdefinefpid = ?) ) order by fo.seq  ";
//        List<Map> or_list = this.getListBySql(or_sql,new Object[]{flowdefineid});
//        JSONArray fpArray = JSONArray.fromObject(fp_list);
//        JSONArray opArray = JSONArray.fromObject(op_list);
//        JSONArray orArray = JSONArray.fromObject(or_list);
//
//        for(int i=0;i<opArray.size();i++){
//            for(int j=0;j<orArray.size();j++){
//                if(opArray.getJSONObject(i).getString("id").equals(orArray.getJSONObject(j).getString("operationid"))){
//                    if(opArray.getJSONObject(i).containsKey("data")){
//                        opArray.getJSONObject(i).getJSONArray("data").add(orArray.getJSONObject(j));
//                    }else{
//                        JSONArray tempJ = new JSONArray();
//                        tempJ.add(orArray.getJSONObject(j));
//                        opArray.getJSONObject(i).put("data",tempJ);
//                    }
//                }
//            }
//        }
//
//        for(int i=0;i<fpArray.size();i++){
//            for(int j=0;j<opArray.size();j++){
//                if(fpArray.getJSONObject(i).getString("id").equals(opArray.getJSONObject(j).getString("flowpointid"))){
//                    if(fpArray.getJSONObject(i).containsKey("data")){
//                        fpArray.getJSONObject(i).getJSONArray("data").add(opArray.getJSONObject(j));
//                    }else{
//                        JSONArray tempJ = new JSONArray();
//                        tempJ.add(opArray.getJSONObject(j));
//                        fpArray.getJSONObject(i).put("data",tempJ);
//                    }
//                }
//            }
//        }
//
//        JSONObject jsonObject = JSONObject.fromObject(fLowDefine);
//        jsonObject.put("data",fpArray);
//
//        return jsonObject;
//    }
//
//    // 指定表名导入数据
//    public String importData(File excelFile) throws BiffException, IOException, SQLException {
//        // TODO Auto-generated method stub
//        Workbook rwb = Workbook.getWorkbook(excelFile);
//        // 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...
//
//        for (int i = 0; i < rwb.getSheets().length; i++) {
//            Sheet sheet = rwb.getSheets()[i];
//            int rsColumns = sheet.getColumns();// 列数
//            int rsRows = sheet.getRows();// 行数
//            String tablename = sheet.getName();
//            cellMerge(sheet, rsColumns, rsRows, "cn.topcheer.flow.pojo.", tablename);
//        }
//
//        return null;
//    }
//
//    private void cellMerge(Sheet sheet, int rsColumns, int rsRows, String preffix, String classname) {
//
//        if("FlowDefine".equals(classname)){
//            Cell[] cellkey = sheet.getRow(0);
//            for (int j = 1; j < rsRows; j++) {
//                JSONObject json = new JSONObject();
//                Cell[] cellvalue = sheet.getRow(j);
//                for (int k = 0; k < rsColumns; k++) {
//                    json.put(cellkey[k].getContents(), cellvalue[k].getContents());
//                }
//
//                JSONArray array = new JSONArray();
//                array.add(json.get("name")+"");
//                array.add(json.get("memo")+"");
//                array.add(json.get("sourceids")+"");
//                array.add(json.get("type")+"");
//                array.add(json.get("isenabled")+"");
//                array.add(json.get("mold")+"");
//                array.add(json.get("showpointsize")+"");
//                array.add(json.get("flowcharttype")+"");
//                array.add(json.get("showdetail")+"");
//                array.add(json.get("id")+"");
//
//                String flag = this.getOnlyValueBySql("select id from fl_flowdefine where id = ?",new Object[]{json.get("id")+""});
//                if(Util.isEoN(flag)){
//                    String sql = "insert into fl_flowdefine (name,memo,sourceids,type,isenabled,mold,showpointsize,flowcharttype,showdetail,id) values (?,?,?,?,?,?,?,?,?,?)";
//                    this.runSql(sql,array.toArray());
//                }else{
//                    String sql = "update fl_flowdefine set name = ?,memo = ?,sourceids = ?,type = ?,isenabled = ?,mold = ?,showpointsize = ?,flowcharttype = ?,showdetail = ? where id = ?";
//                    this.runSql(sql,array.toArray());
//                }
//            }
//        }else if("FlowPoint".equals(classname)){
//            Cell[] cellkey = sheet.getRow(0);
//            for (int j = 1; j < rsRows; j++) {
//                JSONObject json = new JSONObject();
//                Cell[] cellvalue = sheet.getRow(j);
//                for (int k = 0; k < rsColumns; k++) {
//                    json.put(cellkey[k].getContents(), cellvalue[k].getContents());
//                }
//
//                JSONArray array = new JSONArray();
//                array.add(json.get("name")+"");
//                array.add(json.get("memo")+"");
//                array.add(json.get("flowdefinefpid")+"");
//                array.add(json.get("ftype")+"");
//                array.add(json.get("seq")+"");
//                array.add(json.get("filt")+"");
//                array.add(json.get("id")+"");
//
//
//                String flag = this.getOnlyValueBySql("select id from fl_flowpoint where id = ?",new Object[]{json.get("id")+""});
//                if(Util.isEoN(flag)){
//                    String sql = "insert into fl_flowpoint (name,memo,flowdefinefpid,ftype,seq,filt,id) values (?,?,?,?,?,?,?)";
//                    this.runSql(sql,array.toArray());
//                }else{
//                    String sql = "update fl_flowpoint set name = ?,memo = ?,flowdefinefpid = ?,ftype = ?,seq = ?,flit = ? where id = ?";
//                    this.runSql(sql,array.toArray());
//                }
//            }
//        }else if("Operation".equals(classname)){
//            Cell[] cellkey = sheet.getRow(0);
//            for (int j = 1; j < rsRows; j++) {
//                JSONObject json = new JSONObject();
//                Cell[] cellvalue = sheet.getRow(j);
//                for (int k = 0; k < rsColumns; k++) {
//                    json.put(cellkey[k].getContents(), cellvalue[k].getContents());
//                }
//
//                JSONArray array = new JSONArray();
//                array.add(json.get("name")+"");
//                array.add(json.get("memo")+"");
//                array.add(json.get("flowpointid")+"");
//                array.add(json.get("operationcode")+"");
//                array.add(json.get("isneedaffix")+"");
//                array.add(json.get("otype")+"");
//                array.add(json.get("seq")+"");
//                array.add(json.get("account")+"");
//                array.add(json.get("extrainfos")+"");
//                array.add(json.get("id")+"");
//
//
//                String flag = this.getOnlyValueBySql("select id from fl_operation where id = ?",new Object[]{json.get("id")+""});
//                if(Util.isEoN(flag)){
//                    String sql = "insert into fl_operation (name,memo,flowpointid,operationcode,isneedaffix,otype,seq,account,extrainfos,id) values (?,?,?,?,?,?,?,?,?,?)";
//                    this.runSql(sql,array.toArray());
//                }else{
//                    String sql = "update fl_operation set name = ?,memo = ?,flowpointid = ?,operationcode = ?,isneedaffix = ?,otype = ?,seq = ?,account = ?,extrainfos = ? where id = ?";
//                    this.runSql(sql,array.toArray());
//                }
//            }
//        }else if("OperationResult".equals(classname)){
//            Cell[] cellkey = sheet.getRow(0);
//            for (int j = 1; j < rsRows; j++) {
//                JSONObject json = new JSONObject();
//                Cell[] cellvalue = sheet.getRow(j);
//                for (int k = 0; k < rsColumns; k++) {
//                    json.put(cellkey[k].getContents(), cellvalue[k].getContents());
//                }
//
//                JSONArray array = new JSONArray();
//                array.add(json.get("name")+"");
//                array.add(json.get("operationid")+"");
//                array.add(json.get("nextflowpointid")+"");
//                array.add(json.get("seq")+"");
//                array.add(json.get("id")+"");
//
//                String flag = this.getOnlyValueBySql("select id from fl_operationResult where id = ?",new Object[]{json.get("id")+""});
//                if(Util.isEoN(flag)){
//                    String sql = "insert into fl_operationResult (name,operationid,nextflowpointid,seq,id) values (?,?,?,?,?)";
//                    this.runSql(sql,array.toArray());
//                }else{
//                    String sql = "update fl_operationResult set name = ?,operationid = ?,nextflowpointid = ?,seq = ? where id = ?";
//                    this.runSql(sql,array.toArray());
//                }
//            }
//        }
//
//    }
//
//    /**
//     *  产品化页面-所有流程配置复制
//     * @param newbatchid 新的批次id
//     * @param oldbatchid 老的批次id
//     */
//    public void copyFlow(String newbatchid, String oldbatchid){
//        List<FLowDefine> all = this.findAll();
//        all.stream().filter(m->(!Util.isEoN(m.getSourceids())&&m.getSourceids().contains(oldbatchid+","))).forEach(m->{
//            m.setSourceids(m.getSourceids()+newbatchid+",");
//            this.merge(m);
//        });
//    }
//
//
//
//    public List<Map> getFlowPoint(String batchid, String type){
//        List<Map> list = this.dbHelper.getRows("select e.name from fl_flowdefine t " +
//                "left join fl_flowpoint e on e.flowdefinefpid = t.id " +
//                "where t.sourceids like ? and type = ? order by e.seq",new Object[]{"%"+batchid+"%",type});
//        return list;
//    }

}
