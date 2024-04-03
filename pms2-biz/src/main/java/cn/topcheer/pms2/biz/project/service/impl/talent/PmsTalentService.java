/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2024-1-4 9:30:14
 */
package cn.topcheer.pms2.biz.project.service.impl.talent;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsDepartment;
import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsDepartmentServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.sys.SysGuide;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.sys.SysGuideService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.talent.*;
import cn.topcheer.pms2.dao.project.talent.*;

/**
 * PmsTalent 服务类
 */
@Service(value = "PmsTalentService")
public class PmsTalentService extends GenericService<PmsTalent> {

    public PmsTalentDao getPmsTalentDao() {
        return (PmsTalentDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsTalentDao(PmsTalentDao pmsTalentDao) {

        this.setGenericDao(pmsTalentDao);
    }


    @Autowired
    private PmsEnterpriseServiceImpl pmsEnterpriseService;

    @Autowired
    private PmsDepartmentServiceImpl pmsDepartmentService;

    @Autowired
    private SysGuideService sysGuideService;

    @Autowired
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;


    @Override
    protected JSONObject afterGenericSaveFun(JSONObject result, String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {
        PmsTalent pmsTalent = this.findById(mainid);

        // 推荐单位名称
        PmsEnterprise casemanagementEnter = pmsEnterpriseService.findById(pmsTalent.getEnterpriseid());
        if (casemanagementEnter != null) {
            if (casemanagementEnter.getPmsParentEnterprise() == null || "98C57F262CB74E12AAC31C9D37533A17".equals(casemanagementEnter.getPmsParentEnterprise().getId())) {
                // 当前属于一级单位，无上级或上级等于省科技厅
                pmsTalent.setCitycasemanagement(casemanagementEnter.getName());
                pmsTalent.setCitycasemanagementid(casemanagementEnter.getId());

            } else {
                // 当前属于二级单位，有上级
                pmsTalent.setCitycasemanagement(casemanagementEnter.getPmsParentEnterprise().getName());
                pmsTalent.setCitycasemanagementid(casemanagementEnter.getPmsParentEnterprise().getId());
            }

            pmsTalent.setCountycasemanagement(casemanagementEnter.getName());
            pmsTalent.setCountycasemanagementid(casemanagementEnter.getId());

        }

        // 判断方向id是否有值
        if (!Util.isEoN(pmsTalent.getSupportdirectionid())) {
            // 获取方向相关信息
            SysGuide sysGuide = sysGuideService.getById(pmsTalent.getSupportdirectionid());
            if (!Util.isEoN(sysGuide) && !Util.isEoN(sysGuide.getBelonglabid())) {
                // 获取处室信息
                PmsDepartment pmsDepartment = pmsDepartmentService.findById(sysGuide.getBelonglabid());
                if (!Util.isEoN(pmsDepartment)) {
                    pmsTalent.setBelonglabid(pmsDepartment.getId());
                    pmsTalent.setBelonglab(pmsDepartment.getName());
                }
            }
        }

        this.merge(pmsTalent);

        return result;
    }


    /**
     * 修改流程状态
     *
     * @param param 参数
     * @author szs
     * @date 2024-03-01
     */
    @Override
    public void updateMinicurrentState(JSONObject param) {
        String mainId = param.getString("mainId");
        String minicurrentstate = param.getString("minicurrentstate");
        String minicurrentstateid = param.getString("minicurrentstateid");
        String miniCurrentTaskDefKey = param.getString("miniCurrentTaskDefKey");
        String miniCurrentProcessDefKey = param.getString("miniCurrentProcessDefKey");
        String taskResult = param.getString("taskResult");
        String taskName = param.getString("taskName");

        // 查询
        PmsTalent data = this.findById(mainId);
        if (data == null) {
            return;
        }

        // 状态
        if (StringUtils.isNotBlank(minicurrentstate)) {
            // 流程状态
            switch (minicurrentstate) {
                case "完成":
                    // 任务操作结果
                    switch (taskResult) {
                        case "wf_pass_inadmissible":
                            minicurrentstate = taskName + "不通过";
                            break;
                        default:
                            minicurrentstate = taskName + "通过";

                            // 通过时，生成申请编号
                            data.setApplicationno(pmsPlanprojectbatchService.getApplicationNo(data.getPlanprojectbatchid(), data.getDeclarantid()));
                            break;
                    }

                    break;
                case "终止":
                    // 任务操作结果
                    switch (taskResult) {
                        case "wf_terminate_inadmissible":
                            // 不予受理
                            minicurrentstate = taskName + "不通过";
                            break;
                        case "wf_terminate_abandon":
                            // 放弃
                            minicurrentstate = "用户补正放弃";
                            break;
                        case "wf_terminate_timeout":
                            // 超时自动办结
                            minicurrentstate = "超时自动办结";
                            break;
                        default:
                            // 默认管理员终止
                            minicurrentstate = "管理员终止";
                            break;
                    }

                    break;
                case "撤销":
                    minicurrentstate = "用户填报";
                    break;
                default:
                    break;
            }
        }

        data.setMiniCurrentProcessDefKey(miniCurrentProcessDefKey);
        data.setMiniCurrentTaskDefKey(miniCurrentTaskDefKey);
        data.setMinicurrentstate(minicurrentstate);
        data.setMinicurrentstateid(minicurrentstateid);
        this.merge(data);

    }


}
