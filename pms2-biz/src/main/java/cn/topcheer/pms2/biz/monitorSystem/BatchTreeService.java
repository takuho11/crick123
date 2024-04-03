package cn.topcheer.pms2.biz.monitorSystem;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.BatchVersion;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.TreeUtil;
import cn.topcheer.pms2.dao.monitorSystem.BatchTreeDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(value="BatchTreeService")
public class BatchTreeService extends GenericService<BatchVersion> {

    public BatchTreeDao getBatchTreeDao() {
        return (BatchTreeDao) this.getGenericDao();
    }
    @Autowired
    public void setBatchTreeDao(BatchTreeDao batchTreeDao) {
        this.setGenericDao(batchTreeDao);
    }
    @Autowired
    DBHelper dbHelper;
    @Autowired
    SysUserService sysUserService;

    /*====================================================完美分割线====================================================*/

    /**
     * 【大批次树】--根据不同情况获取大批次树（只能勾选一级）
     */
    public List<Map> getBigBatch(String system,String type,String special){
        //=============处理条件参数一=============
        JSONObject paramJson = this.dealParam(system,type,special,false);
        String insql = paramJson.get("insql")+"";
        List<String> paramList = (List<String>)paramJson.get("paramList");
        //=============一级为：大批次=============
        List<Map> treeList = this.dbHelper.getRows("select ee.*,rownum as seq, '' as year " +
                " from (select  distinct e.projectname as levelname, e.id, 1 as levelnum," +
                " '' as uplevelid, e.system as type, " +
                " '' as levelall" +
                " from pms_planproject e " +
                " left join pms_planprojectbatch b on e.id = b.planprojectid " +
                " where e.system is not null "+insql+
                " order by e.projectname ) ee",paramList.toArray());
        for(Map tree : treeList){
            tree.put("ishavevalue",true);
            tree.put("ishavelowlevel",false);
            tree.put("data",new JSONArray());
        }
        return treeList;
    }

    /**
     * 【小批次树】--根据不同情况获取大批次树（只能勾选二级）
     */
    public JSONArray getBatch(String system,String type,String special){
        List<Map> treeList = new ArrayList<>();//整个树的数据结构的数组
        //=============处理条件参数一=============
        JSONObject paramJsonOne = this.dealParam(system,type,special,false);
        String insqlOne = paramJsonOne.get("insql")+"";
        List<String> paramListOne = (List<String>)paramJsonOne.get("paramList");
        //=============一级为：大批次=============
        List<Map> oneList = this.dbHelper.getRows("select ee.*,rownum as seq, '' as year " +
                " from (select distinct e.projectname as levelname, e.id, 1 as levelnum, " +
                " '' as uplevelid, e.system as type, " +
                " '' as levelall" +
                " from pms_planproject e " +
                " left join pms_planprojectbatch b on e.id = b.planprojectid" +
                " where e.system is not null "+insqlOne+
                " and b.name not like '%评审%' and b.name not like '%会评%' " +
                " order by e.projectname ) ee",paramListOne.toArray());
        //=============处理条件参数二=============
        JSONObject paramJsonTwo = this.dealParam(system,type,special,true);
        String insqlTwo = paramJsonTwo.get("insql")+"";
        List<String> paramListTwo = (List<String>)paramJsonTwo.get("paramList");
        //=============二级为：小批次年度=============
        List<Map> twoList = this.dbHelper.getRows("select b.id,2 as levelnum,b.name as levelname, b.name as batchname," +
                " e.id as uplevelid,e.system as type, " +
                " e.projectname||'-'||b.name as levelall, b.annually as year  " +
                " from pms_planprojectbatch b  " +
                " left join pms_planproject e on b.planprojectid = e.id " +
                " left join batch_version v on b.id = v.id  " +
                " where e.system is not null "+insqlTwo+
                " and b.name not like '%评审%' and b.name not like '%会评%' " +
                " order by e.projectname,b.annually",paramListTwo.toArray());
        //=============需要保存的二级菜单数组=============
        List<Map> needTwoList = new ArrayList<>();
        for(Map one : oneList){
            one.put("ishavevalue",false);
            String oneid = one.get("id")+"";//一级id
            //通过一级id获取所有关联二级菜单数组
            List<Map> twoRelationList = twoList.stream().filter(t -> oneid.equals(t.get("uplevelid"))).collect(Collectors.toList());
            if(twoRelationList.size()>0){
                one.put("ishavelowlevel",true);
                //编辑二级级菜单数组，处理 ishavelowlevel、ishavevalue、seq
                int twoSeq = 1;//三级级菜单排序
                for(Map two : twoRelationList){
                    two.put("ishavelowlevel",false);
                    two.put("ishavevalue",true);
                    two.put("seq",twoSeq++);
                }
                needTwoList.addAll(twoRelationList);
            }else{
                one.put("ishavelowlevel",false);
            }
            treeList.add(one);
        }
        treeList.addAll(needTwoList);
        //=============处理前台需要的数据结构=============
        TreeUtil menuTree = new TreeUtil();//树工具类实例
        return JSONArray.fromObject(menuTree.menuList(treeList));
    }


    /**
     * 【辅助方法】 --- 处理前台参数
     *  system：系统类型
     *  type: 业务类型
     *  special: 特殊类型，比如判断是否根据角色+处室获取数据
     *  judgeBatch：是否判断小批次表，true表示是，false表示否
     */
    public JSONObject dealParam(String system,String type,String special,Boolean judgeBatch){
        JSONObject resJson = new JSONObject();
        StringBuilder insql = new StringBuilder("");
        List<String> paramList = new ArrayList<>();
        //=============判断system=============
        if(!"all".equals(system)){
            insql.append(" and e.system = ? ");
            paramList.add(system);
        }
        //=============判断type（等有实际需求再处理）=============

        //=============判断special（等有实际需求再处理）=============

        //=============返回=============
        resJson.put("insql",insql.toString());
        resJson.put("paramList",paramList);
        return resJson;
    }



}
