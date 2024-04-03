/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-1-19 15:17:52
 *
 */
package cn.topcheer.pms2.biz.pdfDownload.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.pdfDownload.PmsDownconfigconvert;
import cn.topcheer.pms2.dao.jpa.pdf.PmsDownconfigconvertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsDownconfigconvert 服务类
 */
@Service(value="PmsDownconfigconvertService")
public class PmsDownconfigconvertService extends GenericService<PmsDownconfigconvert> {

    public PmsDownconfigconvertDao getPmsDownconfigconvertDao() {
        return (PmsDownconfigconvertDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsDownconfigconvertDao(PmsDownconfigconvertDao pmsDownconfigconvertDao) {

        this.setGenericDao(pmsDownconfigconvertDao);
    }

	
}
