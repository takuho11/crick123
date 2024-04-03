package cn.topcheer.pms2.biz.plan.service.impl;

import cn.hutool.db.sql.Condition;
import cn.hutool.db.sql.SqlBuilder;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInBiz;
import cn.topcheer.pms2.dao.plan.PmsPlanprojectbatchInBizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service 服务类
 *
 * @author szs
 * @date 2023-11-09
 */
@Service(value = "PmsPlanprojectbatchInBizService")
public class PmsPlanprojectbatchInBizService extends GenericService<PmsPlanprojectbatchInBiz> {

    private final DBHelper dbHelper;

    public PmsPlanprojectbatchInBizService(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public PmsPlanprojectbatchInBizDao getPmsPlanprojectbatchInBizDao() {
        return (PmsPlanprojectbatchInBizDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPlanprojectbatchInBizDao(PmsPlanprojectbatchInBizDao pmsPlanprojectbatchInBizDao) {

        this.setGenericDao(pmsPlanprojectbatchInBizDao);
    }


    /**
     * 查询
     *
     * @param pmsPlanprojectbatchId 小批次id
     * @param planprojectType       业务类型
     * @return PmsPlanprojectbatchInBiz
     */
    public PmsPlanprojectbatchInBiz getPmsPlanprojectbatchInBiz(String pmsPlanprojectbatchId, String planprojectType) {
        HqlBuilder<PmsPlanprojectbatchInBiz> builder = new HqlBuilder<>();
        builder.eq(PmsPlanprojectbatchInBiz::getPmsPlanprojectbatchId, pmsPlanprojectbatchId);
        builder.eq("planproject_type", planprojectType);
        List<PmsPlanprojectbatchInBiz> list = this.find(builder);

        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 根据传入的平台code，查询对应的小批次聚合
     *
     * @param code
     * @return
     */
    public List<Map> getGroupByCode(String code) {
        SqlBuilder sqlBuilder = new SqlBuilder();
        Condition condition = new Condition();
        condition.setField("CATEGORY_CODE");
        condition.setValue(code);
        condition.setOperator("=");
        sqlBuilder.select("pms_planprojectbatch_id").from("PMS_PLANPROJECTBATCH_IN_BIZ").where(condition).groupBy("pms_planprojectbatch_id");
        String sql = sqlBuilder.build();
        String[] params = new String[1];
        params[0] = code;
        List<Map> rows = dbHelper.getRows(sql, params);
//        getPmsPlanprojectbatchInBizDao().getSql
//        HqlBuilder<PmsPlanprojectbatchInBiz> hqlBuilder = new HqlBuilder<>();
//        hqlBuilder.eq("PMS_PLANPROJECTBATCH_IN_BIZ",code).addOrder("pms_planprojectbatch_id","desc").addOrder("seq","desc");
//        List<PmsPlanprojectbatchInBiz> planprojectbatchInBizs = getPmsPlanprojectbatchInBizDao().find(hqlBuilder);
//        sqlBuilder.
        return rows;
    }

    public PmsPlanprojectbatchInBiz getLastByBatchidAndCode(String id, String code) {
        HqlBuilder<PmsPlanprojectbatchInBiz> hqlBuilder = new HqlBuilder<>();
        hqlBuilder.eq(PmsPlanprojectbatchInBiz::getPmsPlanprojectbatchId, id)
                .eq(PmsPlanprojectbatchInBiz::getCategoryCode, code)
                .addOrder(PmsPlanprojectbatchInBiz::getSeq, "desc");
        List<PmsPlanprojectbatchInBiz> bizs = getPmsPlanprojectbatchInBizDao().find(hqlBuilder);
        return bizs.size() > 0 ? bizs.get(0) : null;
    }

    public List<PmsPlanprojectbatchInBiz> getAllTypeByBatchId(String id) {
        HqlBuilder<PmsPlanprojectbatchInBiz> hqlBuilder =
                HqlBuilder.builder(PmsPlanprojectbatchInBiz.class)
                .eq(PmsPlanprojectbatchInBiz::getPmsPlanprojectbatchId, id);
        List<PmsPlanprojectbatchInBiz> list = this.find(hqlBuilder);
        return list;
    }
}