package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsDepartment;
import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsDepartmentServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;


import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbase;
import cn.topcheer.pms2.api.sys.SysGuide;


import cn.topcheer.pms2.biz.limit.service.CommonLimitService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.sys.SysGuideService;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseDao;
import jodd.util.StringUtil;
import liquibase.pro.packaged.J;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.beetl.ext.fn.Json;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service(value = "PmsProjectbaseService")
public class PmsProjectbaseService extends GenericService<PmsProjectbase> {

    @Autowired
    private PmsDepartmentServiceImpl pmsDepartmentService;

    @Autowired
    private PmsEnterpriseServiceImpl pmsEnterpriseService;

    @Autowired
    private SysGuideService sysGuideService;

    @Autowired
    @Lazy
    private SysUserService sysUserService;

    @Autowired
    private CommonLimitService commonLimitService;

    @Autowired
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;

    @Autowired
    public void setPmsProjectbaseDao(PmsProjectbaseDao pmsProjectbaseDao) {
        this.setGenericDao(pmsProjectbaseDao);
    }

    public PmsProjectbaseDao getPmsProjectbaseDao() {
        return (PmsProjectbaseDao) this.getGenericDao();
    }

    @Override
    protected JSONObject afterGenericSaveFun(JSONObject result, String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {

        // 查询主表
        PmsProjectbase pmsProjectbase = this.findById(mainid);
        if (pmsProjectbase == null) {
            return result;
        }

        // 如果一级推荐单位、二级推荐单位为空，那么系统默认自己得单位以及上级单位
        if (StringUtil.isBlank(pmsProjectbase.getCitycasemanagement()) && StringUtil.isBlank(pmsProjectbase.getCitycasemanagementid())
                && StringUtil.isBlank(pmsProjectbase.getCountycasemanagement()) && StringUtil.isBlank(pmsProjectbase.getCountycasemanagementid())) {
            // 推荐单位名称
            PmsEnterprise casemanagementEnter = pmsEnterpriseService.findById(pmsProjectbase.getEnterpriseid());
            if (casemanagementEnter != null) {
                if (casemanagementEnter.getPmsParentEnterprise() == null || "98C57F262CB74E12AAC31C9D37533A17".equals(casemanagementEnter.getPmsParentEnterprise().getId())) {
                    // 当前属于一级单位，无上级或上级等于省科技厅
                    pmsProjectbase.setCitycasemanagement(casemanagementEnter.getName());
                    pmsProjectbase.setCitycasemanagementid(casemanagementEnter.getId());

                } else {
                    // 当前属于二级单位，有上级
                    pmsProjectbase.setCitycasemanagement(casemanagementEnter.getPmsParentEnterprise().getName());
                    pmsProjectbase.setCitycasemanagementid(casemanagementEnter.getPmsParentEnterprise().getId());
                }

                pmsProjectbase.setCountycasemanagement(casemanagementEnter.getName());
                pmsProjectbase.setCountycasemanagementid(casemanagementEnter.getId());

            }
        }


        // 判断方向id是否有值
        if (!Util.isEoN(pmsProjectbase.getSupportdirectionid())) {
            // 获取方向相关信息
            SysGuide sysGuide = sysGuideService.getById(pmsProjectbase.getSupportdirectionid());
            if (!Util.isEoN(sysGuide) && !Util.isEoN(sysGuide.getBelonglabid())) {
                // 获取处室信息
                PmsDepartment pmsDepartment = pmsDepartmentService.findById(sysGuide.getBelonglabid());
                if (!Util.isEoN(pmsDepartment)) {
                    pmsProjectbase.setBelonglabid(pmsDepartment.getId());
                    pmsProjectbase.setBelonglab(pmsDepartment.getName());
                }
            }
        }
        this.merge(pmsProjectbase);
        return result;
    }


    /**
     * 修改流程状态
     *
     * @param param 参数
     * @author szs
     * @date 2024-03-08
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
        PmsProjectbase data = this.findById(mainId);
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

    /**
     * 判断成员是否能被设置为负责人。判断条件为：
     * 1. 该成员是否在系统内有账号且账号已经通过审核
     * 2. 通过判定批次绑定的一些申报限制
     *
     * @param params 人员的证件号码，当前批次ID
     * @return 是否允许设置为负责人，不允许将返回原因
     */
    public R setTeamLeader(HashMap<String, Object> params) {
        JSONObject accountParams = new JSONObject();
        accountParams.put("certificateno", params.get("idNumber").toString());
        // 科研人员的roleID
        accountParams.put("roleid", "129947C6-94DC-4A7D-84D2-E78A80E518A3");
        // 是否通过审核
        Boolean haveApproved = sysUserService.verifyCertificateno(accountParams);
        if (!haveApproved) {
            // 新用户的roleID
            accountParams.put("roleid", "f8a87c80-f89d-48bc-ad96-840ab6aa81b2");
            // 是否注册过账号
            Boolean haveAccount = sysUserService.verifyCertificateno(accountParams);
            if (!haveAccount) {
                return R.fail("所选择成员未创建系统账号！请联系成员使用贵州省政务网账号登陆访问系统并完善个人信息。");
            } else {
                return R.fail("所选择成员系统账号未通过单位管理员审核！");
            }
        }
        // 批次相关限制
        params.put("type", "kjxm-sb");

        try {
            commonLimitService.judgeCondition(params);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }

        return R.success("设置负责人成功！");
    }

}