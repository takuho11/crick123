package cn.topcheer.pms2.biz.pdfDownload.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.pdfDownload.PmsImageconfig;
import cn.topcheer.pms2.dao.jpa.pdf.PmsImageconfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PmsImageconfig 服务类
 */
@Service(value="PmsImageconfigService")
public class PmsImageconfigService extends GenericService<PmsImageconfig> {
    @Autowired
    DBHelper dbHelper;
    public PmsImageconfigDao getPmsImageconfigDao() {
        return (PmsImageconfigDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsImageconfigDao(PmsImageconfigDao pmsImageconfigDao) {

        this.setGenericDao(pmsImageconfigDao);
    }
    /**
     * 【模板插入图片】--根据类型获取要插入的附件类型
     * @param type
     */
    public List<Map> getImageConfig(String sourceid, String type){
        List<Map> list=new ArrayList<>();
        if("合同".equals(type)||"ht".equals(type)){
            list = dbHelper.getRows("select t.* from pms_imageconfig t " +
                            " inner join crt_contract_jbxx pb on pb.planprojectbatchid=t.batchid " +
                            " where pb.id=? and t.type=?  "
                    ,new Object[]{sourceid,type});
        }else if("奖励".equals(type)||"reward".equals(type)){
            list = dbHelper.getRows("select t.* from pms_imageconfig t " +
                            " inner join pms_reward pb on pb.planprojectbatchid=t.batchid " +
                            " where pb.id=? and t.type=?  "
                    ,new Object[]{sourceid,type});
        }else{
            list = dbHelper.getRows("select t.* from pms_imageconfig t " +
                            " inner join pms_projectbase pb on pb.planprojectbatchid=t.batchid " +
                            " where pb.id=? and t.type=?  "
                    ,new Object[]{sourceid,type});
        }
        return list;
    }
}
