/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-1-19 15:17:52
 *
 */
package cn.topcheer.pms2.biz.pdfDownload.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.pdfDownload.PmsDownconfigwater;
import cn.topcheer.pms2.dao.jpa.pdf.PmsDownconfigwaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsDownconfigwater 服务类
 */
@Service(value="PmsDownconfigwaterService")
public class PmsDownconfigwaterService extends GenericService<PmsDownconfigwater> {
    public PmsDownconfigwaterDao getPmsDownconfigwaterDao() {
        return (PmsDownconfigwaterDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsDownconfigwaterDao(PmsDownconfigwaterDao pmsDownconfigwaterDao) {

        this.setGenericDao(pmsDownconfigwaterDao);
    }


	
}
