/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-1-4 15:56:31
 *
 */
package cn.topcheer.pms2.biz.bi;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.bi.*;
import cn.topcheer.pms2.dao.bi.*;

/**
 * BiEntRy 服务类
 */
@Service(value="BiEntRyService")
public class BiEntRyService extends GenericService<BiEntRy> {

    public BiEntRyDao getBiEntRyDao() {
        return (BiEntRyDao) this.getGenericDao();
    }

    @Autowired
    public void setBiEntRyDao(BiEntRyDao biEntRyDao) {

        this.setGenericDao(biEntRyDao);
    }

    public List<BiEntRy> findByMainid(String mainid){
        List<BiEntRy> list = new ArrayList<>();
        list = this.getBiEntRyDao().findByHql("select t from BiEntRy t where t.mainid = ?0 and (t.rytype = '法定代表人' or t.rytype = '单位联系人' or t.rytype = '科研负责人')" ,mainid);
        return list;
    }
}
