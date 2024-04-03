/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024年1月6日 上午11:07:52
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.projectBase.*;
import cn.topcheer.pms2.dao.project.projectBase.*;

/**
 * PmsProjectbaseJfjd 服务类
 */
@Service(value="PmsProjectbaseJfjdService")
public class PmsProjectbaseJfjdService extends GenericService<PmsProjectbaseJfjd> {

    public PmsProjectbaseJfjdDao getPmsProjectbaseJfjdDao() {
        return (PmsProjectbaseJfjdDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsProjectbaseJfjdDao(PmsProjectbaseJfjdDao pmsProjectbaseJfjdDao) {

        this.setGenericDao(pmsProjectbaseJfjdDao);
    }

}
