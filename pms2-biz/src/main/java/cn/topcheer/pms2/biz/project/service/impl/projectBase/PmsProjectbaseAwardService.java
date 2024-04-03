/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2019-5-24 14:59:28
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseAward;

import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseAwardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PmsProjectbaseAward 服务类
 */
@Service(value = "PmsProjectbaseAwardService")
public class PmsProjectbaseAwardService extends GenericService<PmsProjectbaseAward> {

    public PmsProjectbaseAwardDao getPmsProjectbaseAwardDao() {
        return (PmsProjectbaseAwardDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsProjectbaseAwardDao(PmsProjectbaseAwardDao pmsProjectbaseAwardDao) {

        this.setGenericDao(pmsProjectbaseAwardDao);
    }

    public List<PmsProjectbaseAward> findAwardByPidandType(String projectbaseid, String type) {
        List<PmsProjectbaseAward> list = this.getPmsProjectbaseAwardDao().findByHql("select t from PmsProjectbaseAward t where t.projectbaseid = ? and type = ? order by t.seq", new Object[]{projectbaseid, type});
        return list;
    }

    public List<Map> findByPid(String projectbaseid) {
        return this.getListBySql("select * from PMS_PROJECTBASE_AWARDS where projectbaseid = ?", new Object[]{projectbaseid});
    }

}
