/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2016-1-8 14:53:32
 */
package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.plan.entity.PmsFilePrintRecord;
import cn.topcheer.pms2.dao.plan.PmsFilePrintRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsAttachment 服务类
 */
@Service(value = "PmsFilePrintRecordService")
public class PmsFilePrintRecordService extends GenericService<PmsFilePrintRecord>{

    public PmsFilePrintRecordDao getPmsFilePrintRecordDao() {
        return (PmsFilePrintRecordDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsAttachmentDao(PmsFilePrintRecordDao pmsFilePrintRecordDao) {

        this.setGenericDao(pmsFilePrintRecordDao);
    }

}
