/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024年1月7日 下午2:43:05
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
 * PmsPrizePro 服务类
 */
@Service(value="PmsPrizeProService")
public class PmsPrizeProService extends GenericService<PmsPrizePro> {

    public PmsPrizeProDao getPmsPrizeProDao() {
        return (PmsPrizeProDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPrizeProDao(PmsPrizeProDao pmsPrizeProDao) {

        this.setGenericDao(pmsPrizeProDao);
    }

}
