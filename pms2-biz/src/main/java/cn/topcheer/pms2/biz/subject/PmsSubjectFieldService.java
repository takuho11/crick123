/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-2-22 16:50:10
 *
 */
package cn.topcheer.pms2.biz.subject;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.subject.*;
import cn.topcheer.pms2.dao.subject.*;

/**
 * PmsSubjectField 服务类
 */
@Service(value="PmsSubjectFieldService")
public class PmsSubjectFieldService extends GenericService<PmsSubjectField> {

    public PmsSubjectFieldDao getPmsSubjectFieldDao() {
        return (PmsSubjectFieldDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsSubjectFieldDao(PmsSubjectFieldDao pmsSubjectFieldDao) {

        this.setGenericDao(pmsSubjectFieldDao);
    }

}
