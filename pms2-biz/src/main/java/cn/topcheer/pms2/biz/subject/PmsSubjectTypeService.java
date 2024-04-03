/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-2-22 16:50:10
 *
 */
package cn.topcheer.pms2.biz.subject;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.common.exception.BusinessException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.subject.*;
import cn.topcheer.pms2.dao.subject.*;

/**
 * PmsSubjectType 服务类
 */
@Service(value="PmsSubjectTypeService")
public class PmsSubjectTypeService extends GenericService<PmsSubjectType> {

    public PmsSubjectTypeDao getPmsSubjectTypeDao() {
        return (PmsSubjectTypeDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsSubjectTypeDao(PmsSubjectTypeDao pmsSubjectTypeDao) {

        this.setGenericDao(pmsSubjectTypeDao);
    }
    @Autowired
    public DBHelper dbHelper;

    public List<Map> findSubjectByXkflag(JSONObject jsonObject) {
        if (Util.isEoN(jsonObject)) {
            throw new BusinessException("未获取到相关参数");
        }
        JSONArray paramArr = new JSONArray();
        // 类型
        String type = jsonObject.get("type") + "";
        if(Util.isEoN(type)) {
            throw new BusinessException("未获取到学科类型");
        }
        paramArr.add(type);
        // 等级
        String rank = jsonObject.get("rank") + "";
        if(Util.isEoN(rank)) {
            throw new BusinessException("未获取到学科等级");
        }
        paramArr.add(rank);
        // code
        String code = jsonObject.get("code") + "";
        if(Util.isEoN(code)) {
            code = "";
        }
        paramArr.add(code + "%");
        // 是否为学科类别（js,cw,gl,qt等，使用逗号分隔）
        String xkflag = jsonObject.get("xkflag") + "";
        if(Util.isEoN(xkflag)) {
            throw new BusinessException("未获取到学科类别");
        }

        String inSql = "";
        String[] xkflags = jsonObject.get("xkflag").toString().split(",");
        if(!Util.isEoN(xkflags) && xkflags.length > 0) {
            inSql = " \n and ( ";
            for(int i = 0; i < xkflags.length; i++) {
                if(i == 0) {
                    inSql += " t.xkflag like ? ";
                } else {
                    inSql += " or t.xkflag like ? ";
                }
                paramArr.add("%" + xkflags[i] + "%");
            }
            inSql += " and 1 = 1 )";
        }

        String sql = " select t.* from pms_subject_type t where t.type = ? and t.rank = ? and t.code like ? " + inSql + " order by t.seq ";
        List<Map> result = dbHelper.getRows(sql, paramArr.toArray());
        return result;
    }

}
