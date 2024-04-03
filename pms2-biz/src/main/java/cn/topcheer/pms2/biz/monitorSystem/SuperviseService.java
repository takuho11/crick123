package cn.topcheer.pms2.biz.monitorSystem;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.BatchVersion;
import cn.topcheer.pms2.api.pml.vo.Page;
import cn.topcheer.pms2.biz.pml.service.impl.PageService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.monitorSystem.SuperviseDao;
import com.aliyun.oss.ServiceException;
import liquibase.pro.packaged.O;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value="SuperviseService")
public class SuperviseService extends GenericService<BatchVersion> {

    public SuperviseDao getSuperviseDao() {
        return (SuperviseDao) this.getGenericDao();
    }
    @Resource
    public void setSuperviseDao(SuperviseDao superviseDao) {
        this.setGenericDao(superviseDao);
    }
    @Resource
    private PageService pageService;
    @Resource
    DBHelper dbHelper;

    /*====================================================完美分割线====================================================*/


    /**
     * 【监测管理】 -- 获取流程节点和数量
     */
    public List<Map> getStateNum(String batchId, String type){
        //获取当前批次定义的流程节点
        List<Map> stateList = new ArrayList<>();
        String sql = "";//最终sql语句
        switch (type){
            case "sb":
                //========================申报========================
                stateList = this.dbHelper.getRows("select e.DICT_VALUE as name from sf_dict_biz e " +
                        " where e.code = ? and e.PARENT_ID <> '0' " +
                        " order by e.SORT",new Object[]{"sb_minicurrentstate"});
                sql = " select nvl(e.MINICURRENTSTATE,'用户填报') as state, " +
                        " count(e.id) as num " +
                        " from pms_projectbase e " +
                        " where e.planprojectbatchid = ? " +
                        " group by nvl(e.MINICURRENTSTATE,'用户填报')";
                break;
            default:
                //说明没有匹配上业务类型，返回空数组
                throw new ServiceException("当前业务类型监测功能尚未开发。");
        }
        if(stateList.size()==0){
            throw new ServiceException("当前批次尚未配置流程。");
        }
        //根据定义的流程节点，拼接排序语句
        String orderSql = " order by decode(nvl(e.MINICURRENTSTATE,'用户填报') ";
        for (int i = 1; i <= stateList.size(); i++) {
            orderSql = orderSql + ",'"+ stateList.get(i-1).get("name") +"',"+i;
        }
        orderSql = orderSql + ")";
        //最终返回的数组
        return this.dbHelper.getRows(sql+orderSql,new Object[]{batchId});
    }

    /**
     * 【监测管理】 -- 获取列表数据
     */
    public Page<Map> getGridData(JSONObject json, String batchId, String type){
        String sql = "";//最终sql语句
        StringBuilder insql = new StringBuilder();;//条件语句
        String minicurrentstate = "";//搜索条件-流程节点
        String searchAll = "";//搜索条件-关键字全局搜索
        String searchContent = "";//搜索条件-列表内的搜索框内容
        List<String> paramList = new ArrayList<>();//条件参数集合
        JSONObject pageConfig = json.getJSONObject("pageConfig");//前台分页参数
        if(Util.isEoN(pageConfig)){
            return null;
        }
        //=================判断业务类型=================
        switch (type){
            case "sb":
                //==========================申报==========================
                //处理搜索条件
                //搜索条件：流程节点
                minicurrentstate = json.get("minicurrentstate")+"";
                if(!Util.isEoN(minicurrentstate)){
                    insql.append(" and nvl(e.MINICURRENTSTATE,'用户填报') = ? ");
                    paramList.add(minicurrentstate);
                }
                //搜索条件：关键字全局搜索
                searchAll = json.get("searchAll")+"";
                if(!Util.isEoN(searchAll)){
                    insql.append(" and (e.APPLICATIONNO like ? or e.PROJECTNAME like ? or e.MAINORGANIZERS like ? or e.PROJECTLEADER like ?  ) ");
                    paramList.add("%"+searchAll+"%");paramList.add("%"+searchAll+"%");paramList.add("%"+searchAll+"%");paramList.add("%"+searchAll+"%");
                }
                //搜索条件：列表内的搜索框内容
                searchContent = json.get("searchContent")+"";
                if(!Util.isEoN(searchContent)){
                    insql.append(" and (e.APPLICATIONNO like ? or e.PROJECTNAME like ? or e.MAINORGANIZERS like ? or e.PROJECTLEADER like ?  )  ");
                    paramList.add("%"+searchContent+"%");paramList.add("%"+searchContent+"%");paramList.add("%"+searchContent+"%");paramList.add("%"+searchContent+"%");
                }
                //拼接批次id条件
                paramList.add(batchId);
                sql = "select e.id as mainid," +
                        " e.planprojectbatchid as batchid," +
                        " b.MINI_BIZ_DEF_ID as id," +
                        " b.MINI_BIZ_VERSION_ID as bizversionid," +
                        " b.PLANPROJECT_TYPE as gridtype," +
                        " '2' as type," +//固定的，1标准模板，2业务模板
                        " e.APPLICATIONNO,e.PROJECTNAME," +
                        " e.MAINORGANIZERS,e.PROJECTLEADER," +
                        " nvl(e.MINICURRENTSTATE,'用户填报')  as minicurrentstate " +
                        " from pms_projectbase e " +
                        " left join PMS_PLANPROJECTBATCH_IN_BIZ b on e.planprojectbatchid = b.PMS_PLANPROJECTBATCH_ID " +
                        " where 1=1 " +insql.toString()+
                        " and e.planprojectbatchid = ? " +
                        " order by e.applicationno ";
                break;
            default:
                //说明业务类型没有匹配上
                return null;
        }
        //后台分页获取数据
        return this.pageService.findPageBySql(sql,paramList,pageConfig);
    }



}
