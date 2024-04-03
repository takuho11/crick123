/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024年3月8日 上午9:19:59
 *
 */
package cn.topcheer.pms2.biz.sys;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.dao.sys.PmsZwwAcceptanceSaveDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.sys.*;
import cn.topcheer.pms2.dao.project.*;

/**
 * PmsZwwAcceptanceSave 服务类
 */
@Service(value="PmsZwwAcceptanceSaveService")
public class PmsZwwAcceptanceSaveService extends GenericService<PmsAcceptanceSave> {

    public PmsZwwAcceptanceSaveDao getPmsZwwAcceptanceSaveDao() {
        return (PmsZwwAcceptanceSaveDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsZwwAcceptanceSaveDao(PmsZwwAcceptanceSaveDao pmsZwwAcceptanceSaveDao) {

        this.setGenericDao(pmsZwwAcceptanceSaveDao);
    }

}
