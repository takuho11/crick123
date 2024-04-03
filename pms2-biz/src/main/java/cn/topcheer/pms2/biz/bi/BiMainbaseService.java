/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2024-1-4 15:56:31
 */
package cn.topcheer.pms2.biz.bi;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.topcheer.halberd.app.api.framework.entity.sys.*;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysUserService;
import cn.topcheer.halberd.app.api.minidev.dto.StartProcessChangeDTO;
import cn.topcheer.halberd.app.api.minidev.dto.StartProcessDTO;
import cn.topcheer.halberd.app.biz.framework.service.impl.minidev.MiniCommonServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.flow.entity.FlowBusiness;
import cn.topcheer.halberd.flow.service.FlowBusinessService;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.api.sys.enums.GeneralRoleEnum;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.sys.PmsRoleService;
import cn.topcheer.pms2.biz.sys.SysIdentityService;
import cn.topcheer.pms2.biz.utils.Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.plugin.workflow.process.model.WfProcess;
import org.springblade.plugin.workflow.process.service.IWfProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.bi.*;
import cn.topcheer.pms2.dao.bi.*;

/**
 * BiMainbase 服务类
 */
@Service(value = "BiMainbaseService")
public class BiMainbaseService extends GenericService<BiMainbase> {
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private PmsEnterpriseServiceImpl pmsEnterpriseService;
    @Autowired
    private SysUserServiceImpl sysUserService;
    @Autowired
    private IWfProcessService processService;
    @Autowired
    private MiniCommonServiceImpl miniCommonService;
    @Autowired
    private FlowBusinessService flowBusinessService;
    @Autowired
    private PmsRoleService sysRoleService;
    @Autowired
    private SysIdentityService sysIdentityService;
    @Autowired
    private BiTalentWeService biTalentWeService;
    @Autowired
    private BiEntBiService biEntBiService;
    @Autowired
    private DBHelper dbHelper;

    public BiMainbaseDao getBiMainbaseDao() {
        return (BiMainbaseDao) this.getGenericDao();
    }

    @Autowired
    public void setBiMainbaseDao(BiMainbaseDao biMainbaseDao) {

        this.setGenericDao(biMainbaseDao);
    }

    @Autowired
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;

    @Override
    public Object getProjectName(String mainId) {
        // 查询
        String userId = AuthUtil.getUserId();
        SysUser byId = iSysUserService.getById(userId);
        return byId.getName();
    }

    @Override
    protected JSONObject afterGenericSaveFun(JSONObject result, String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {
        BiMainbase biMainbase = this.findById(mainid);

        // 推荐单位名称
        PmsEnterprise casemanagementEnter = pmsEnterpriseService.findById(biMainbase.getEnterpriseid());
        if (casemanagementEnter != null) {
            if (casemanagementEnter.getPmsParentEnterprise() == null || "98C57F262CB74E12AAC31C9D37533A17".equals(casemanagementEnter.getPmsParentEnterprise().getId())) {
                // 当前属于一级单位，无上级或上级等于省科技厅
                biMainbase.setCitycasemanagement(casemanagementEnter.getName());
                biMainbase.setCitycasemanagementid(casemanagementEnter.getId());

            } else {
                // 当前属于二级单位，有上级
                biMainbase.setCitycasemanagement(casemanagementEnter.getPmsParentEnterprise().getName());
                biMainbase.setCitycasemanagementid(casemanagementEnter.getPmsParentEnterprise().getId());
            }

            biMainbase.setCountycasemanagement(casemanagementEnter.getName());
            biMainbase.setCountycasemanagementid(casemanagementEnter.getId());

        }
        biMainbase.setMinicurrentstate(null);
        biMainbase.setMinicurrentstateid(null);
        this.merge(biMainbase);

        return result;
    }

    public void startProcess(StartProcessChangeDTO dto) {
        StartProcessDTO spDTO = new StartProcessDTO();
        spDTO.setProcessDefKey(dto.getProcessDefKey());
        spDTO.setType(dto.getType());
        spDTO.setMainId(dto.getMainId());
        spDTO.setBatchId(dto.getBatchId());
        // 获取批次
        PmsPlanprojectbatch planprojectbatch = pmsPlanprojectbatchService.findById(dto.getBatchId());
        if (planprojectbatch == null) {
            throw new ServiceException("批次不存在：" + dto.getBatchId());
        }

        // 获取业务流程中间表数据
        QueryWrapper<FlowBusiness> qw = new QueryWrapper<>();
        qw.eq("business_id", dto.getMainId());
        qw.eq("process_def_key", dto.getProcessDefKey());
        qw.isNull("end_time");
        qw.orderByDesc("start_time");
        qw.last(" LIMIT 1 ");
        FlowBusiness flowBusiness = flowBusinessService.getOne(qw);

        if (flowBusiness != null) {
            // 获取当前用户
            SysUser sysUser = sysUserService.findById(AuthUtil.getUserId());
            if (sysUser == null) {
                throw new ServiceException("当前登录用户不存在");
            }

            // 判断权限
            if (!checkTaskUser(flowBusiness, sysUser)) {
                throw new ServiceException("没有审核权限");
            }

            // 完成任务
            WfProcess process = new WfProcess();
            process.setProcessInstanceId(flowBusiness.getProcessInstanceId());
            process.setTaskId(flowBusiness.getTaskId());
            process.setPass(true);
            processService.completeTask(process);

        } else {
            // 发起流程
            BiMainbase mainbase = this.getById(dto.getMainId());
            //主体变更字段更新

            switch (spDTO.getProcessDefKey()) {
                case "process_CHANGEDEOPT_UNIT":
                    HqlBuilder<BiEntBi> builder = new HqlBuilder<>();
                    builder.eq("mainid", mainbase.getId())
                            .eq("type", "unitInfo_basic");
                    BiEntBi biEntBi = biEntBiService.findOne(builder);
                    if (biEntBi != null) {
                        biEntBi.setCasemanagementtype(dto.getCasemanagementtype());
                        biEntBi.setCitycasemanagement(dto.getCitycasemanagement());
                        biEntBi.setCitycasemanagementid(dto.getCitycasemanagementid());
                        biEntBi.setCountrycasemanagement(dto.getCountrycasemanagement());
                        biEntBi.setCountrycasemanagementid(dto.getCountrycasemanagementid());
                        biEntBiService.merge(biEntBi);
                    }
                    mainbase.setCountycasemanagement(dto.getCountrycasemanagement());
                    mainbase.setCountycasemanagementid(dto.getCountrycasemanagementid());
                    mainbase.setCountrycasemanagement(dto.getCountrycasemanagement());
                    mainbase.setCountrycasemanagementid(dto.getCountrycasemanagementid());
                    mainbase.setCitycasemanagement(dto.getCitycasemanagement());
                    mainbase.setCitycasemanagementid(dto.getCitycasemanagementid());
                    //spDTO.setAuditor(miniCommonService.getApproveUserIds(StringUtils.isEmpty(dto.getCountrycasemanagementid()) ? dto.getCitycasemanagementid() : dto.getCountrycasemanagementid()));
                    break;
                case "process_CHANGEDEPT_USER":
                    //更新所在单位信息
                    String code = dto.getCreditcode();
                    String oldEnterpriseID = mainbase.getEnterpriseid();
                    HqlBuilder<PmsEnterprise> enterBuilder = new HqlBuilder<>();
                    enterBuilder.eq("uniformcode", code);
                    PmsEnterprise enterprise = pmsEnterpriseService.findOne(enterBuilder);
                    //spDTO.setAuditor(miniCommonService.getApproveUserIds(enterprise.getId()));
//            PmsEnterprise enterprise = pmsEnterpriseService.getById(enterpriseID);
                    mainbase.setEnterpriseid(enterprise.getId());
//                    changeIdentity(sysUserService.getById(mainbase.getDeclarantid()), enterprise, "user", oldEnterpriseID);
                    HqlBuilder<BiTalentWe> hqlBuilder = new HqlBuilder<>();
                    hqlBuilder.eq("mainid", mainbase.getId())
                            .eq("type", "currentInfo_work");
                    //更新剩余字段
                    BiTalentWe one = biTalentWeService.findOne(hqlBuilder);
                    one.setUnitname(enterprise.getName());
                    one.setCreditcode(enterprise.getUniformcode());
                    one.setStartdate(dto.getStartdate());
                    one.setWorkplace(dto.getWorkplace());
                    one.setPost(dto.getPost());
                    one.setAddress(dto.getAddress());
                    one.setDegreedate(dto.getDegreedate());
                    one.setDegree(dto.getDegree());
                    one.setTitledetailone(dto.getTitledetailone());
                    one.setTitledetailtwo(dto.getTitledetailtwo());
                    one.setEmployerstatus(dto.getEmployerstatus());
                    biTalentWeService.merge(one);
//                    changeIdentity(user, enterprise, "user", oldEnterpriseID);
                    break;
                default:
                    break;
            }
            this.merge(mainbase);

            miniCommonService.startProcessChange(spDTO, spDTO.getProcessDefKey());

        }
    }

    private void changeIdentity(SysUser user, PmsEnterprise enterprise, String type) {
        String roleID = "";
        switch (type) {
            case "user":
                roleID = "129947C6-94DC-4A7D-84D2-E78A80E518A3";//科研人员
                break;
            case "unit":
                roleID = "C7004168-4E0C-4F1F-B341-A225B5644DC5";//企业单位管理员
                break;
            default:
                break;
        }
        String finalRoleID = roleID;
        //查找要变更的identity记录
        List<SysIdentity> collect = user.getSysIdentitys().stream().filter(si -> finalRoleID.equals(si.getSysRole().getId())).collect(Collectors.toList());
        if (collect.size() > 0) {
            collect.forEach(identity -> {
                //变更至新主体
                identity.setPmsEnterprise(enterprise);
                sysIdentityService.merge(identity);
            });
        }
    }

    /**
     * 检查任务用户权限
     *
     * @param flowBusiness FlowBusiness
     * @param sysUser      SysUser
     * @return boolean
     * @author szs
     * @date 2023-12-26
     */
    private boolean checkTaskUser(FlowBusiness flowBusiness, SysUser sysUser) {
        // 1. 如果存在审核人，那就判断用户id
        boolean flag = false;
        if (StringUtils.isNotBlank(flowBusiness.getTaskCandidateUser())) {
            flag = flowBusiness.getTaskCandidateUser().contains(sysUser.getId());
        }

        // 2. 如果存在审核人组，那就判断角色id
        if (StringUtils.isNotBlank(flowBusiness.getTaskCandidateGroup())) {
            for (String roleId : sysUser.getRoleIds()) {
                if (flowBusiness.getTaskCandidateGroup().contains(roleId)) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }


    /**
     * 通过流转记录状态判断是否可以编辑
     *
     * @param mainid 主表id
     * @return JSONObject
     * @author szs
     * @date 2023-11-22
     */
    @Override
    public JSONObject judgeMinicurrentStateSatify(String mainid) {
        JSONObject result = new JSONObject();

        // 查询
        BiMainbase mainbase = this.findById(mainid);
        if (mainbase == null) {
            result.put("success", true);
            return result;
        }

        // 获取流程状态
        String minicurrentstate = mainbase.getMinicurrentstate();

        if (StringUtils.isNotBlank(minicurrentstate) && !minicurrentstate.contains("用户填报")
                && !minicurrentstate.contains("用户完善信息") && !minicurrentstate.contains("完善信息失败")
        ) {
            result.put("success", false);
            result.put("errMsg", "该项目当前的状态为：" + minicurrentstate + "，未在用户填报阶段，无法进行保存！！！");
            return result;
        }

        result.put("success", true);
        return result;
    }


    /**
     * 修改流程状态
     *
     * @param param 参数
     * @author whq
     * @date 2024-03-01
     */
    @Override
    public void updateMinicurrentState(JSONObject param) {
        String mainId = param.getString("mainId");
        String minicurrentstate = param.getString("minicurrentstate");
        String minicurrentstateid = param.getString("minicurrentstateid");
        String miniCurrentTaskDefKey = param.getString("miniCurrentTaskDefKey");
        String miniCurrentProcessDefKey = param.getString("miniCurrentProcessDefKey");

        // 查询
        BiMainbase mainbase = this.findById(mainId);
        if (mainbase == null) {
            throw new ServiceException("暂无数据");
        }
        // 查询用户
        SysUser user = sysUserService.getById(mainbase.getDeclarantid());

        mainbase.setMinicurrentstateid(minicurrentstateid);
        mainbase.setMiniCurrentTaskDefKey(miniCurrentTaskDefKey);
        mainbase.setMiniCurrentProcessDefKey(miniCurrentProcessDefKey);
        switch (minicurrentstate) {
            case "完成":
                minicurrentstate = "完善信息完成";
                mainbase.setMinicurrentstate(minicurrentstate);
                user.setUserstate(1);
                if ("process_DATAWAREHOUSE_UNIT".equals(miniCurrentProcessDefKey) ){
                    //更新对应账号角色中间表数据
                    addIdentity(mainbase, user, "unit");
                }else if("process_DATAWAREHOUSE_USER".equals(miniCurrentProcessDefKey)) {
                    //更新对应账号角色中间表数据
                    addIdentity(mainbase, user, "user");
                } else if ("process_CHANGEDEOPT_UNIT".equals(miniCurrentProcessDefKey)){
                    minicurrentstate = "变更主体完成";
                    mainbase.setMinicurrentstate(minicurrentstate);
                    changeUserEnterprice(mainbase);
                }else if("process_CHANGEDEPT_USER".equals(miniCurrentProcessDefKey)) {
                    minicurrentstate = "变更主体完成";
                    mainbase.setMinicurrentstate(minicurrentstate);
                    changeIdentity(user, pmsEnterpriseService.getById(mainbase.getEnterpriseid()), "user");
                }else {
                    minicurrentstate = "申报完成";
                }
                break;
            case "终止":
            case "撤销":
                user.setUserstate(0);
                if ("process_DATAWAREHOUSE_UNIT".equals(miniCurrentProcessDefKey) || "process_DATAWAREHOUSE_USER".equals(miniCurrentProcessDefKey)) {
                    minicurrentstate = "完善信息失败";
                } else if ("process_CHANGEDEOPT_UNIT".equals(miniCurrentProcessDefKey) || "process_CHANGEDEPT_USER".equals(miniCurrentProcessDefKey)) {
                    minicurrentstate = "变更主体失败";
                } else {
                    minicurrentstate = "用户填报";
                }
                break;
            default:
                break;
        }
        mainbase.setMinicurrentstate(minicurrentstate);
        // 保存
        this.merge(mainbase);

        // 判断回写：变更主体和变更归口影响申报，状态回写到User。单位信息审核通过状态回写到Enterprise。

        user.setMinicurrentstateid(minicurrentstateid);
        user.setMinicurrentstate(minicurrentstate);
        user.setMiniCurrentTaskDefKey(miniCurrentTaskDefKey);
        user.setMiniCurrentProcessDefKey(miniCurrentProcessDefKey);
        sysUserService.merge(user);
        if (miniCurrentProcessDefKey.contains("_UNIT")) {
            PmsEnterprise enterprise = pmsEnterpriseService.getById(mainbase.getEnterpriseid());
            enterprise.setMinicurrentstateid(minicurrentstateid);
            enterprise.setMinicurrentstate(minicurrentstate);
            enterprise.setMiniCurrentTaskDefKey(miniCurrentTaskDefKey);
            enterprise.setMiniCurrentTaskDefKey(miniCurrentTaskDefKey);
            pmsEnterpriseService.merge(enterprise);
        }
        sysUserService.merge(user);

    }

    private void changeUserEnterprice(BiMainbase mainbase) {
        PmsEnterprise enterprise = pmsEnterpriseService.getById(mainbase.getEnterpriseid());
        enterprise.setCountycasemanagement(mainbase.getCountycasemanagement());
        enterprise.setCountycasemanagementid(mainbase.getCountycasemanagementid());
        enterprise.setCitycasemanagement(mainbase.getCitycasemanagement());
        enterprise.setCitycasemanagementid(mainbase.getCitycasemanagementid());
        pmsEnterpriseService.merge(enterprise);
    }

    private void addIdentity(BiMainbase mainbase, SysUser user, String type) {
        String roleId = "";
        String delRoleId = "";
        String id = Util.NewGuid();
        switch (type) {
            case "user":
                roleId = GeneralRoleEnum.ORDINARY_PEOPLE.getRoleId();
                delRoleId = GeneralRoleEnum.NEW_PEOPLE.getRoleId();
                break;
            case "unit":
                roleId = GeneralRoleEnum.ORDINARY_ENTERPRISE.getRoleId();
                delRoleId = GeneralRoleEnum.NEW_UNIT.getRoleId();
                id = id + "DWGLY";
                break;
            default:
                break;
        }

        PmsEnterprise pmsEnterprise = pmsEnterpriseService.getById(mainbase.getEnterpriseid());
        HqlBuilder<SysIdentity> hqlBuilder = new HqlBuilder<>();
        hqlBuilder.eq("USERID", user.getId())
                .eq("PURVIEWORGANIZEID", pmsEnterprise.getId())
                .eq("ROLEID", delRoleId);
        SysIdentity one = sysIdentityService.findOne(hqlBuilder);
        if (one != null) {
            try {
                boolean b = dbHelper.runSql("delete from SYS_IDENTITY WHERE id = ?", new Object[]{one.getId()});
                System.out.println(b);
            } catch (SQLException throwables) {
                System.out.println("删除失败");
                throwables.printStackTrace();
            }
        }
        HqlBuilder<SysIdentity> hqlBuilder2 = new HqlBuilder<>();
        hqlBuilder2.eq("USERID", user.getId())
                .eq("PURVIEWORGANIZEID", pmsEnterprise.getId())
                .eq("ROLEID", roleId);
        SysIdentity two = sysIdentityService.findOne(hqlBuilder2);
        //没有该中间表数据，则新增
        if (two == null) {
            SysIdentity sysIdentity = new SysIdentity();
            sysIdentity.setId(id);
            SysRole sysRole = this.sysRoleService.findById(roleId);//企业单位管理员/科研人员
            if (sysRole != null) {
                sysIdentity.setSysRole(sysRole);//赋予 企业单位管理员/科研人员 角色
            }
            sysIdentity.setSysUser(user);//用户信息关联
            sysIdentity.setPmsEnterprise(pmsEnterprise);//单位信息关联
            sysIdentity.setCreatedate(new Date());//创建时间
            sysIdentity.setEnabled(0);//0:表示申请中，1:表示通过
            sysIdentity.setIdentityType("sb");//参考之前的注册，暂时先保留
            sysIdentityService.merge(sysIdentity);
        } else if (two != null && type.equals("unit") && !two.getId().contains("DWGLY")) {
            two.setId(two.getId() + "DWGLY");
            sysIdentityService.merge(two);
        }
    }
}
