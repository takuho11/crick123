/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024年3月12日 下午12:15:32
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.talent;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.talent.PmsTalentStaffedu;
import cn.topcheer.pms2.dao.project.talent.PmsTalentStaffeduDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsTalentStaffedu 服务类
 */
@Service(value="PmsTalentStaffeduService")
public class PmsTalentStaffeduService extends GenericService<PmsTalentStaffedu> {

    public PmsTalentStaffeduDao getPmsTalentStaffeduDao() {
        return (PmsTalentStaffeduDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsTalentStaffeduDao(PmsTalentStaffeduDao pmsTalentStaffeduDao) {

        this.setGenericDao(pmsTalentStaffeduDao);
    }

}
