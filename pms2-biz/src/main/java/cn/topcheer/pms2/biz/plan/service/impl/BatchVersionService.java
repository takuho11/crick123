package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.plan.entity.BatchVersion;
import cn.topcheer.pms2.dao.plan.BatchVersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="BatchVersionService")
public class BatchVersionService extends GenericService<BatchVersion> {

    public BatchVersionDao getBatchVersionDao() {
        return (BatchVersionDao) this.getGenericDao();
    }
    @Autowired
    public void setBatchVersionDao(BatchVersionDao batchVersionDao) {
        this.setGenericDao(batchVersionDao);
    }
    
    
    
    
}
