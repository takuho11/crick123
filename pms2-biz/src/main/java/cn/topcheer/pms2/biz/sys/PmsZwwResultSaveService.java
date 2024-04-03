/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024年3月8日 上午9:19:59
 *
 */
package cn.topcheer.pms2.biz.sys;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.dao.sys.PmsZwwResultSaveDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.sys.*;
import cn.topcheer.pms2.dao.project.*;

/**
 * PmsZwwResultSave 服务类
 */
@Service(value="PmsZwwResultSaveService")
public class PmsZwwResultSaveService extends GenericService<PmsResultSave> {

    public PmsZwwResultSaveDao getPmsZwwResultSaveDao() {
        return (PmsZwwResultSaveDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsZwwResultSaveDao(PmsZwwResultSaveDao pmsZwwResultSaveDao) {

        this.setGenericDao(pmsZwwResultSaveDao);
    }

}
