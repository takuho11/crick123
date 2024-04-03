package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.pms2.api.plan.dto.SavePmsPlanprojectbatchInFlowDTO;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInFlow;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.plan.PmsPlanprojectbatchInFlowDao;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Service 服务类
 *
 * @author szs
 * @date 2023-11-21
 */
@Service(value = "PmsPlanprojectbatchInFlowService")
public class PmsPlanprojectbatchInFlowService extends GenericService<PmsPlanprojectbatchInFlow> {


    public PmsPlanprojectbatchInFlowDao getPmsPlanprojectbatchInFlowDao() {
        return (PmsPlanprojectbatchInFlowDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPlanprojectbatchInFlowDao(PmsPlanprojectbatchInFlowDao pmsPlanprojectbatchInFlowDao) {

        this.setGenericDao(pmsPlanprojectbatchInFlowDao);
    }


    /**
     * 获取全部列表
     *
     * @param pmsPlanprojectbatchId 小批次id
     * @param planprojectType       批次类型
     * @return List
     * @author szs
     * @date 2023-11-21
     */
    public List<PmsPlanprojectbatchInFlow> getAllList(String pmsPlanprojectbatchId, String planprojectType) {

        // 查询
        HqlBuilder<PmsPlanprojectbatchInFlow> builder = new HqlBuilder<>();
        builder.eq("PMS_PLANPROJECTBATCH_ID", pmsPlanprojectbatchId);
        builder.eq("PLANPROJECT_TYPE", planprojectType);
        builder.addOrder("SEQ", "asc");

        return this.find(builder);
    }


    /**
     * 保存
     *
     * @param dto SavePmsPlanprojectbatchInFlowDTO
     * @author szs
     * @date 2023-11-21
     */
    public void submit(SavePmsPlanprojectbatchInFlowDTO dto) {
        // 获取已存在全部列表
        List<PmsPlanprojectbatchInFlow> list = this.getAllList(dto.getPmsPlanprojectbatchId(), dto.getPlanprojectType());
        List<String> toDelIds = list.stream().map(PmsPlanprojectbatchInFlow::getId).collect(Collectors.toList());

        // 当前用户id
        String userId = AuthUtil.getUserId();

        // 当前时间
        Date date = new Date();

        // 遍历处理数据
        int seq = 1;
        for (PmsPlanprojectbatchInFlow data : dto.getBatchInFlowList()) {
            if (StringUtils.isNotBlank(data.getId())) {
                toDelIds.remove(data.getId());

                // 查询
                PmsPlanprojectbatchInFlow batchInFlow = this.findById(data.getId());
                if (batchInFlow == null) {
                    throw new ServiceException("查无此数据：" + data.getId());
                }

                batchInFlow.setPmsPlanprojectbatchId(dto.getPmsPlanprojectbatchId());
                batchInFlow.setPlanprojectType(dto.getPlanprojectType());
                batchInFlow.setProcessDefKey(data.getProcessDefKey());
                batchInFlow.setSeq(seq);
                batchInFlow.setUpdateTime(date);
                batchInFlow.setUpdateUser(userId);
                this.update(batchInFlow);

            } else {
                data.setId(Util.NewGuid());
                data.setPmsPlanprojectbatchId(dto.getPmsPlanprojectbatchId());
                data.setPlanprojectType(dto.getPlanprojectType());
                data.setSeq(seq);
                data.setCreateTime(date);
                data.setCreateUser(userId);
                this.save(data);

            }

            seq++;
        }

        // 批量删除
        if (toDelIds.size() > 0) {
            this.deleteByIds(toDelIds);
        }

    }


}