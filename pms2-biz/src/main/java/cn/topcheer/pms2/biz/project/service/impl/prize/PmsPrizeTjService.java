/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-2-23 15:29:37
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.prize;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.prize.*;
import cn.topcheer.pms2.dao.project.prize.*;

/**
 * PmsPrizeTj 服务类
 */
@Service(value="PmsPrizeTjService")
public class PmsPrizeTjService extends GenericService<PmsPrizeTj> {

    public PmsPrizeTjDao getPmsPrizeTjDao() {
        return (PmsPrizeTjDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPrizeTjDao(PmsPrizeTjDao pmsPrizeTjDao) {

        this.setGenericDao(pmsPrizeTjDao);
    }

}
