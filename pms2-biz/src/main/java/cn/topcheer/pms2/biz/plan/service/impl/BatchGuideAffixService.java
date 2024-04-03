package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.plan.entity.BatchGuideAffix;
import cn.topcheer.pms2.dao.plan.BatchGuideAffixDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="BatchGuideAffixService")
public class BatchGuideAffixService extends GenericService<BatchGuideAffix> {
    public BatchGuideAffixDao getBatchGuideAffixDao() {
        return (BatchGuideAffixDao) this.getGenericDao();
    }
    @Autowired
    public void setBatchGuideAffixDao(BatchGuideAffixDao batchGuideAffixDao) {
        this.setGenericDao(batchGuideAffixDao);
    }
}
