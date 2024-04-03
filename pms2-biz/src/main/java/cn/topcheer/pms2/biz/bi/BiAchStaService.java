/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-1-4 15:56:30
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
 * BiAchSta 服务类
 */
@Service(value="BiAchStaService")
public class BiAchStaService extends GenericService<BiAchSta> {

    public BiAchStaDao getBiAchStaDao() {
        return (BiAchStaDao) this.getGenericDao();
    }

    @Autowired
    public void setBiAchStaDao(BiAchStaDao biAchStaDao) {

        this.setGenericDao(biAchStaDao);
    }

}
