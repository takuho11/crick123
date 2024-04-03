/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2024年3月13日 下午2:57:24
 */
package cn.topcheer.pms2.biz.plan.service.impl;


import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.utils.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.plan.entity.*;
import cn.topcheer.pms2.dao.plan.*;

import java.util.*;

/**
 * PmsPlanDeadline 服务类
 */
@Service(value = "PmsPlanDeadlineService")
public class PmsPlanDeadlineService extends GenericService<PmsPlanDeadline> {

    @Autowired
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;

    public PmsPlanDeadlineDao getPmsPlanDeadlineDao() {
        return (PmsPlanDeadlineDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPlanDeadlineDao(PmsPlanDeadlineDao pmsPlanDeadlineDao) {

        this.setGenericDao(pmsPlanDeadlineDao);
    }


    /**
     * 获取批次配置时间
     *
     * @param batchId 小批次id
     * @param code    时间编码
     * @return PmsPlanDeadline
     * @author szs
     * @date 2024-03-13
     */
    public PmsPlanDeadline getPmsPlanDeadline(String batchId, String code) {
        HqlBuilder<PmsPlanDeadline> builder = new HqlBuilder<>();
        builder.eq(PmsPlanDeadline::getBatchId, batchId);
        builder.eq(PmsPlanDeadline::getCode, code);
        builder.eq(PmsPlanDeadline::getIsDelete, "0");
        builder.eq(PmsPlanDeadline::getEnable, true);
        return this.findOne(builder);
    }


    /**
     * 判断时间是否在配置的批次时间范围内
     *
     * @param batchId 小批次id
     * @param code    时间编码
     * @author szs
     * @date 2024-03-14
     */
    public JSONObject judgeTime(String batchId, String code) {
        JSONObject result = new JSONObject();
        result.put("result", true);
        result.put("note", "在时间范围内");

        // 当前时间
        Date date = new Date();

        // 获取批次配置时间
        PmsPlanDeadline pmsPlanDeadline = this.getPmsPlanDeadline(batchId, code);

        // 跟当前系统时间进行比较
        if (pmsPlanDeadline != null) {
            // 如果开始时间和结束时间都为空
            if (pmsPlanDeadline.getStartTime() == null && pmsPlanDeadline.getEndTime() == null) {
                result.put("result", false);
                result.put("note", pmsPlanDeadline.getMessage());

            } else {
                // 开始时间不为空，并当前时间小于开始时间
                boolean bo1 = pmsPlanDeadline.getStartTime() != null && date.compareTo(pmsPlanDeadline.getStartTime()) < 0;

                // 结束时间不为空，并当前时间大于结束时间
                boolean bo2 = pmsPlanDeadline.getEndTime() != null && date.compareTo(pmsPlanDeadline.getEndTime()) > 0;

                if (bo1 || bo2) {
                    result.put("result", false);
                    result.put("note", pmsPlanDeadline.getMessage());
                }
            }

        } else {
            // 用户相关时间，如果没有配置时间，那就默认不允许
            switch (code) {
                case "applicationPeriod":
                case "rejectCorrection":
                case "rejectReAudit":
                case "rejectCorrectionReAudit":
                case "applicationTimeout":
                case "correctionTimeout":
                    result.put("result", false);
                    result.put("note", "不在时间范围内");
                    break;
                default:
                    result.put("result", true);
                    result.put("note", "在时间范围内");
                    break;
            }

        }

        return result;
    }


    public ReturnToJs copyPlanProjectConfig(String id) {
        PmsPlanprojectbatch batch = pmsPlanprojectbatchService.findById(id);
        HqlBuilder<PmsPlanDeadline> hqlBuilder = new HqlBuilder<>();
        hqlBuilder.eq(PmsPlanDeadline::getBatchId, batch.getPmsPlanproject().getId())
                .eq(PmsPlanDeadline::getIsDelete, "0");
        List<PmsPlanDeadline> deadlines = this.find(hqlBuilder);
        if (deadlines == null || deadlines.size() == 0) {
            return ReturnToJs.failure("所属大批次无相关配置");
        }
        deadlines.stream().forEach(deadline -> {
            PmsPlanDeadline planDeadline = new PmsPlanDeadline();
            BeanUtil.copyProperties(deadline, planDeadline);
            planDeadline.setId(Util.NewGuid());
            planDeadline.setBatchId(id);
            planDeadline.setSavedate(new Date());
            planDeadline.setMainid(batch.getPmsPlanproject().getId());
            planDeadline.setSourceid(id);
            planDeadline.setIsDelete("0");
            planDeadline.setUpdatelasttime(new Date());
            this.saveOrUpdate(planDeadline);
        });
        return ReturnToJs.success();
    }

    public ReturnToJs save(String params) {
        JSONObject json = JSONObject.fromObject(params);
        String id = json.getString("id");
        String batchId = json.getString("batchId");
        if (Util.isEoN(batchId)) {
            return ReturnToJs.failure("batchid为空");
        }
        PmsPlanDeadline deadline = new PmsPlanDeadline();
        //更新
        if (!Util.isEoN(id)) {
            deadline = this.findById(id);
        } else {
            //新增
            deadline.setId(Util.NewGuid());
            deadline.setSavedate(new Date());
            PmsPlanprojectbatch batch = pmsPlanprojectbatchService.findById(batchId);
            //为小批次保存时间配置
            if (batch != null) {
                deadline.setMainid(batch.getPmsPlanproject().getId());
            } else {
                //大批次
                deadline.setMainid(batchId);
            }
            deadline.setSourceid(batchId);
        }
        json.put("id", deadline.getId());
        deadline.setUpdatelasttime(new Date());

        Util.ApplyObject(deadline, json);
        this.saveOrUpdate(deadline);

        return ReturnToJs.success();
    }

    public List<PmsPlanDeadline> findByBatchId(String id, String type) {
        String batchid = id;
        if (type.equals("sync")) {
            //同步时查询的是小批次对应大批次的配置
            PmsPlanprojectbatch batch = pmsPlanprojectbatchService.findById(id);
            id = batch.getPmsPlanproject().getId();
        }
        //其余情况直接按id查询
        HqlBuilder<PmsPlanDeadline> hqlBuilder = new HqlBuilder<>();
        hqlBuilder.eq(PmsPlanDeadline::getBatchId, id);
        hqlBuilder.eq(PmsPlanDeadline::getIsDelete, "0");
        List<PmsPlanDeadline> deadlines = this.find(hqlBuilder);
        if (deadlines == null) {
            deadlines = new ArrayList<>();
        }
        if (type.equals("sync")) {
            List<PmsPlanDeadline> lines = new ArrayList<>();
            deadlines.forEach(line->{
                PmsPlanDeadline deadline = new PmsPlanDeadline();
                BeanUtil.copyProperties(line, deadline);
                deadline.setId(Util.NewGuid());
                deadline.setBatchId(batchid);
                lines.add(deadline);
            });
            return lines;
        }
        return deadlines;
    }

    public ReturnToJs savePmsPlanDeadline(String json) {
        //检查编码重复情况
        JSONArray deadlineArray = JSONArray.fromObject(json);
        Set<String> set = new LinkedHashSet<>();
        deadlineArray.stream().forEach(line->{
            JSONObject jsonObject = JSONObject.fromObject(line);
            set.add(jsonObject.getString("code"));
        });
        if (deadlineArray.size() != set.size()) {
            return ReturnToJs.failure("编码重复");
        }
        //进行保存
        StringBuilder errorMsg = new StringBuilder();
        deadlineArray.forEach(array -> {
            //数据
            JSONObject jsonObject = JSONObject.fromObject(array);
            String batchId = String.valueOf(jsonObject.get("batchId"));
            String id = String.valueOf(jsonObject.get("id"));
            if (!Util.isEoN(batchId)) {
                //旧对象
                PmsPlanDeadline deadline = getPmsPlanDeadlineDao().findById(jsonObject.getString("id"));
                if (deadline == null) {
                    //新增
                    deadline = new PmsPlanDeadline();
                    deadline.setSavedate(new Date());
                    deadline.setIsDelete("0");
//                deadline.setMainid();
                }
                //赋值
                Util.ApplyObject(deadline, jsonObject);
                deadline.setUpdatelasttime(new Date());

                this.saveOrUpdate(deadline);
                if (deadline.getCode().equals("applicationPeriod")) {
                    PmsPlanprojectbatch batch = pmsPlanprojectbatchService.findById(deadline.getBatchId());
                    if (batch != null) {
                        batch.setStarttime(deadline.getStartTime());
                        batch.setEndtime(deadline.getEndTime());
                        pmsPlanprojectbatchService.saveOrUpdate(batch);
                    }
                }
            } else {
                errorMsg.append("id: " + id + "  的batchid为空;");
            }
        });
        if (errorMsg.length() > 1) {
            return ReturnToJs.failure(errorMsg.toString());
        }
        return ReturnToJs.success();
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    public ReturnToJs deleteByIdN(String id) {
        PmsPlanDeadline deadline = this.findById(id);
        if (deadline != null) {
            deadline.setUpdatelasttime(new Date());
            deadline.setIsDelete("1");
            this.saveOrUpdate(deadline);
            return ReturnToJs.success();
        } else {
            return ReturnToJs.failure("删除失败");
        }
    }

    /**
     * 批量逻辑删除
     *
     * @param ids
     * @return
     */
    public ReturnToJs deleteByIdsN(String ids, String type) {
        JSONArray jsonArray = JSONArray.fromObject(ids);
        StringBuilder msg = new StringBuilder();
        jsonArray.forEach(id -> {
            PmsPlanDeadline deadline = this.findById(String.valueOf(id));
            if (deadline == null){
                msg.append("未找到对应数据,id:" + id + ";");
            }else if (!type.equals("sync") && "applicationPeriod;applicationAudit;correctionPeriod;correctionAudit;timeoutTerminate".contains(deadline.getCode())) {
                msg.append("固定编码" + deadline.getCode() + "不允许删除;");
            }else if (deadline != null && (type.equals("sync") || !"applicationPeriod;applicationAudit;correctionPeriod;correctionAudit;timeoutTerminate".contains(deadline.getCode()))) {
                deadline.setUpdatelasttime(new Date());
                deadline.setIsDelete("1");
                this.saveOrUpdate(deadline);
            }
        });
        if (!Util.isEoN(msg.toString())) {
            return ReturnToJs.failure(msg.toString());
        }
        return ReturnToJs.success();
    }
}
