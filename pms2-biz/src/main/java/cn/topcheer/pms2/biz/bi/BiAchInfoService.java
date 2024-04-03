/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024年1月11日 上午10:28:46
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
 * BiAchInfo 服务类
 */
@Service(value="BiAchInfoService")
public class BiAchInfoService extends GenericService<BiAchInfo> {

    public BiAchInfoDao getBiAchInfoDao() {
        return (BiAchInfoDao) this.getGenericDao();
    }

    @Autowired
    public void setBiAchInfoDao(BiAchInfoDao biAchInfoDao) {

        this.setGenericDao(biAchInfoDao);
    }

}
