package cn.topcheer.pms2.biz.pml.service.impl;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericPageService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.pml.entity.FlAuthorityGrid;
import cn.topcheer.pms2.biz.pml.service.impl.enumUtil.FiltSqlEnum;
import cn.topcheer.pms2.dao.pml.FlAuthorityGridDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by peanut.huang on 2020/3/18.
 */
@Service(value="flAuthorityGridService")
public class FlAuthorityGridService extends GenericPageService<FlAuthorityGrid> {
    @Autowired
    DBHelper dbHelper;
    public FlAuthorityGridDao getFlAuthorityGridDao() {
        return (FlAuthorityGridDao) this.getGenericDao();
    }

    @Autowired
    public void setFlowDefineDao(FlAuthorityGridDao flAuthorityGridDao) {

        this.setGenericDao(flAuthorityGridDao);
    }

    /**
     *
     * @param type 业务类型
     * @param gridtype 列表类型
     * @param roleid 角色id
     * @param userid 用户id
     * @return 返回对应的wheresql
     */
    public String getAuthorityGridSql(String type,String gridtype,String roleid,String userid){
        FlAuthorityGrid flAuthorityGrid = this.getFlAuthorityGridDao().findByTypeAndGridtypeAndRoleid(type,gridtype,roleid);
        //if(flAuthorityGrid!=null){
            String whereSql = flAuthorityGrid==null?"":(Util.isEoN(flAuthorityGrid.getWheresql())?"":flAuthorityGrid.getWheresql());
            String filts = flAuthorityGrid==null?"flAuthorityFpService":flAuthorityGrid.getFilt();
            String[] filtArrays = filts!=null?filts.split("@"):new String[]{};
            for(String flit:filtArrays){
                try {
                    Object[] obj = FiltSqlEnum.valueOf(flit).getParams(type,gridtype,roleid,userid,flAuthorityGrid);
                    String flitSql = (String)Util.springInvokeMethod(flit,"getFiltSql",obj);
                    whereSql += flitSql;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return whereSql;
        //}
        //return "";
    }

    public List<FlAuthorityGrid> findAllFlAuthorityGridtygrid(){
        return this.findAll();
    }

    public boolean saveAndModifyFlAuthorityGrid(JSONObject jsonObject){
        FlAuthorityGrid flAuthorityGrid = new FlAuthorityGrid();
        Util.ApplyObject(flAuthorityGrid,jsonObject);
        this.getFlAuthorityGridDao().merge(flAuthorityGrid);
        return true;
    }

    public boolean deleteFlAuthorityById(String id){
        this.getFlAuthorityGridDao().deleteById(id);
        return true;
    }

    /**
     *
     * @param id 配置的id
     * @return 获取对应的流程分支和流程节点 用于页面显示
     */
    public JSONArray getFlowDefineAndFlowPoint(String id){
        JSONArray jsonArray = new JSONArray();
        FlAuthorityGrid flAuthorityGrid = this.getFlAuthorityGridDao().findById(id);
        String flowpoints = flAuthorityGrid.getFlowpoints();
        if(flowpoints!=null){
            String[] flowpointArray = flowpoints.split(",");
            String sql = "select fl.FLOWDEFINEFPID,t.NAME as fdname,fl.ID,fl.NAME as flname from FL_FLOWDEFINE t inner join FL_FLOWPOINT fl on t.ID = fl.FLOWDEFINEFPID where 1=1 ";
            String flowpointWhereSql = Arrays.asList(flowpointArray).stream().map(m -> m).collect(Collectors.joining("','"," and fl.id in ('","')")).toString();
            return JSONArray.fromObject(dbHelper.getRows(sql+flowpointWhereSql,null));
        }
        return jsonArray;
    }
}
