package cn.topcheer.pms2.biz.pdfDownload.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.pdfDownload.PmsContractChange;
import cn.topcheer.pms2.dao.jpa.pdf.PmsContractchangeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsContractChange 服务类
 */
@Service(value="PmsContractChangeService")
public class PmsContractChangeService extends GenericService<PmsContractChange> {
    public PmsContractchangeDao getPmsContractChangeDao() {
        return (PmsContractchangeDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsContractChangeDao(PmsContractchangeDao pmsContractChangeDao) {

        this.setGenericDao(pmsContractChangeDao);
    }
}
