/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-1-6 22:48:01
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
 * BiClob 服务类
 */
@Service(value="BiClobService")
public class BiClobService extends GenericService<BiClob> {

    public BiClobDao getBiClobDao() {
        return (BiClobDao) this.getGenericDao();
    }

    @Autowired
    public void setBiClobDao(BiClobDao biClobDao) {

        this.setGenericDao(biClobDao);
    }

}
