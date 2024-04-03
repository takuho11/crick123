/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-8-17 13:59:15
 *
 */
package cn.topcheer.pms2.biz.sys.statistics;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.statistics.SysQueryClob;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * SysQueryClob 服务类
 */
@Service(value="SysQueryClobService")
public class SysQueryClobService extends GenericService<SysQueryClob> {

    DBHelper dbHelper;
    /**
     * 保存大字段
     * @author J
     * @return
     */
    public boolean saveToClob(String tableid,String sourceid,String type,String source,String columnnameid) {
        List<SysQueryClob> list=this.findByMainidAndType(sourceid,type);
        // TODO Auto-generated method stub
        if(sourceid!=null&&!"".equals(sourceid)){
            String insertSql = "update "+tableid+" set "+type+"=? where id=?";
            String delClob1 = "delete sys_query_clob where sourceid =? and columnname=?";
            try {
                dbHelper.runSql(delClob1,new Object[]{sourceid,type});
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String insqlClob1 = "insert into sys_query_clob(id,sourceid,columnname,source)" + "values(?,?,?,?)";
            try {
                dbHelper.runSql(insertSql,new Object[]{columnnameid,sourceid});
                dbHelper.insertClobSql(insqlClob1,new Object[]{columnnameid,sourceid,type,source});
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;
    }
}
