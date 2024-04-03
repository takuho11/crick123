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
 * BiTalentWe 服务类
 */
@Service(value="BiTalentWeService")
public class BiTalentWeService extends GenericService<BiTalentWe> {

    public BiTalentWeDao getBiTalentWeDao() {
        return (BiTalentWeDao) this.getGenericDao();
    }

    @Autowired
    public void setBiTalentWeDao(BiTalentWeDao biTalentWeDao) {

        this.setGenericDao(biTalentWeDao);
    }

    public List<BiTalentWe> findByMainid(String mainid){
        List<BiTalentWe> list = new ArrayList<>();
        list = this.getBiTalentWeDao().findByHql("select t from BiTalentWe t where t.mainid = ?0 and (t.type = 'currentInfo_learn' or t.type = 'currentInfo_work')" ,mainid);
        return list;
    }

}
